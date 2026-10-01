// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.classfile.Attributes;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassHierarchyResolver;
import java.lang.classfile.ClassModel;
import java.lang.classfile.ClassTransform;
import java.lang.classfile.CodeElement;
import java.lang.classfile.MethodModel;
import java.lang.classfile.MethodTransform;
import java.lang.classfile.Opcode;
import java.lang.classfile.attribute.NestHostAttribute;
import java.lang.classfile.attribute.NestMembersAttribute;
import java.lang.classfile.attribute.RecordComponentInfo;
import java.lang.classfile.instruction.FieldInstruction;
import java.lang.classfile.instruction.InvokeInstruction;
import java.lang.constant.ClassDesc;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * One JVM nest for a standalone variant's org.joml2 package, run on its compiled classes in place
 * (the compile task's last action, build.gradle.kts): every class of the package names
 * org.joml2.Joml its nest host, Joml lists them all as members, and a call of another record's
 * component accessor (`v.x()` on a Float3 inside Float4x4) becomes a read of its field
 * (`getfield Float3.x`) - what javac emits only for a record's own type, since it nests nothing
 * but lexically nested classes. The JVM grants private access within any nest of one package
 * (JVMS 5.4.4); the same bytes load and verify, and C2 parses a field read where it parsed a whole
 * inlined call (about 29 nodes a call in a record, 41 in a value class - its node budget, see the
 * generator's OwnFieldReads). A delegating constructor's calls become field reads too
 * (`this(v0.x(), v0.y(), v0.z(), w)` of `Float4(Float3, float)`); one that writes a field keeps
 * them: a value class's constructor writing its fields ahead of super() carries larval stack map
 * frames this pass does not regenerate.
 *
 * <p>Runs as a single-file program on JDK 24 or later (java.lang.classfile): {@code java JomlNest.java
 * <classes dir>}. Idempotent: the nest attributes are rebuilt from the classes present, rewritten
 * calls are field reads already.
 */
public final class JomlNest {
    private static final String PACKAGE = "org/joml2/";
    private static final String HOST = "org/joml2/Joml";

    public static void main(String[] args) throws IOException {
        Path classes = Path.of(args[0]);
        Path pkg = classes.resolve(PACKAGE);
        ClassFile cf = ClassFile.of(ClassFile.ClassHierarchyResolverOption.of(ClassHierarchyResolver.defaultResolver()
                .orElse(ClassHierarchyResolver.ofResourceParsing(desc -> open(classes, desc)))));
        Map<String, ClassModel> models = new TreeMap<>();
        try (var files = Files.list(pkg)) {
            for (Path p : files.filter(f -> f.toString().endsWith(".class")).toList()) {
                ClassModel m = cf.parse(Files.readAllBytes(p));
                models.put(m.thisClass().asInternalName(), m);
            }
        }
        if (!models.containsKey(HOST)) throw new IllegalStateException(HOST + " not found under " + classes);
        // the component accessors of the package's records: owner -> component -> field descriptor
        Map<String, Map<String, String>> accessors = new HashMap<>();
        for (ClassModel m : models.values())
            m.findAttribute(Attributes.record()).ifPresent(r -> {
                Map<String, String> components = new HashMap<>();
                for (RecordComponentInfo c : r.components()) components.put(c.name().stringValue(), c.descriptor().stringValue());
                accessors.put(m.thisClass().asInternalName(), components);
            });
        List<ClassDesc> members = models.keySet().stream().filter(n -> !n.equals(HOST)).map(ClassDesc::ofInternalName).toList();
        int calls = 0;
        for (var e : models.entrySet()) {
            String self = e.getKey();
            int[] rewritten = {0};
            ClassTransform nest = (b, el) -> {
                if (!(el instanceof NestHostAttribute) && !(el instanceof NestMembersAttribute)) b.with(el);
            };
            nest = nest.andThen(ClassTransform.endHandler(b -> b.with(self.equals(HOST)
                    ? NestMembersAttribute.ofSymbols(members) : NestHostAttribute.of(ClassDesc.ofInternalName(HOST)))));
            ClassTransform t = nest.andThen(ClassTransform.transformingMethods(m -> rewrites(m, self, accessors),
                    MethodTransform.transformingCode((cb, ce) -> {
                        String field = ce instanceof InvokeInstruction i ? fieldOf(i, self, accessors) : null;
                        if (field == null) {
                            cb.with(ce);
                            return;
                        }
                        InvokeInstruction i = (InvokeInstruction) ce;
                        cb.getfield(i.owner().asSymbol(), i.name().stringValue(), ClassDesc.ofDescriptor(field));
                        rewritten[0]++;
                    })));
            Files.write(pkg.resolve(self.substring(PACKAGE.length()) + ".class"), cf.transformClass(e.getValue(), t));
            calls += rewritten[0];
        }
        System.out.println("JomlNest: " + models.size() + " classes nested under " + HOST + ", " + calls + " accessor calls read as fields");
    }

    /** The descriptor of the field `i` reads through another record's component accessor, or null. */
    private static String fieldOf(InvokeInstruction i, String self, Map<String, Map<String, String>> accessors) {
        if (i.opcode() != Opcode.INVOKEVIRTUAL) return null;
        String owner = i.owner().asInternalName();
        Map<String, String> components = owner.equals(self) ? null : accessors.get(owner);
        String field = components == null ? null : components.get(i.name().stringValue());
        return field != null && i.type().stringValue().equals("()" + field) ? field : null;
    }

    /** Whether `m`, no constructor writing a field, calls another record's component accessor. */
    private static boolean rewrites(MethodModel m, String self, Map<String, Map<String, String>> accessors) {
        if (m.code().isEmpty()) return false;
        boolean calls = false;
        for (CodeElement ce : m.code().get()) {
            if (ce instanceof FieldInstruction f && f.opcode() == Opcode.PUTFIELD && m.methodName().equalsString("<init>")) return false;
            if (ce instanceof InvokeInstruction i && fieldOf(i, self, accessors) != null) calls = true;
        }
        return calls;
    }

    private static InputStream open(Path classes, ClassDesc desc) {
        Path p = classes.resolve(desc.descriptorString().substring(1, desc.descriptorString().length() - 1) + ".class");
        try {
            return Files.exists(p) ? new ByteArrayInputStream(Files.readAllBytes(p)) : null;
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
