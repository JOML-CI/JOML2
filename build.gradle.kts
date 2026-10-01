import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "2.4.21" apply false
}

version = "1.0.0-SNAPSHOT"

// One entry per published variant (modules/<artifactId>): storage mode, Java level of
// the bytecode (0 = JEP 401 preview), the POM description and how the jar uses
// jdk.incubator.vector ("required", "optional" behind a scalar fallback, or "none").
class Variant(val storage: String, val java: Int, val description: String, val vectorApi: String)

val variants = mapOf(
    "joml2-fields"        to Variant("fields",  22, "scalar fields", "none"),
    "joml2-array"         to Variant("array",   22, "array-backed", "none"),
    "joml2-simd"          to Variant("simd",    22, "array-backed with Vector API SIMD", "required"),
    "joml2-fields-jdk9"   to Variant("fields",   9, "scalar fields, JDK 9+ baseline (no MemorySegment store/load)", "none"),
    "joml2-array-jdk9"    to Variant("array",    9, "array-backed, JDK 9+ baseline (no MemorySegment store/load)", "none"),
    "joml2-records"       to Variant("records", 22, "standalone immutable record types (JDK 22+)", "optional"),
    "joml2-records-jdk17" to Variant("records", 17, "standalone immutable record types, JDK 17 LTS baseline (no MemorySegment store/load)", "optional"),
    "joml2-value"         to Variant("value",    0, "standalone JEP 401 value-class types (preview, Valhalla EA JDK)", "optional"),
)

// OSGi wants a numeric micro and a dot before the qualifier: 1.0.0-SNAPSHOT -> 1.0.0.SNAPSHOT.
val osgiVersion = version.toString().replaceFirst('-', '.')

// OSGi headers of a binary jar.
fun Jar.bundle(name: String, description: String, symbolicName: String, exports: List<String>,
               imports: List<String>, javaVersion: Int, vararg extra: Pair<String, String>) {
    manifest.attributes(linkedMapOf(
        "Bundle-ManifestVersion" to "2",
        "Bundle-Name" to name,
        "Bundle-Description" to description,
        "Bundle-SymbolicName" to symbolicName,
        "Bundle-Version" to osgiVersion,
        "Bundle-Vendor" to "JOML",
        "Bundle-DocURL" to "https://github.com/JOML-CI/JOML2",
        "Bundle-License" to "https://opensource.org/licenses/MIT",
        "Bundle-SCM" to "url=\"https://github.com/JOML-CI/JOML2\",connection=\"scm:git:https://github.com/JOML-CI/JOML2.git\"",
        "Bundle-Developers" to "httpdigest;name=\"Kai Burjack\";roles=\"architect,developer\"",
        "Export-Package" to exports.joinToString(",") { "$it;version=\"$osgiVersion\"" },
        "Import-Package" to imports.joinToString(","),
        // osgi.ee advertises every supported JavaSE level, so this reads "this one or newer".
        "Require-Capability" to "osgi.ee;filter:=\"(&(osgi.ee=JavaSE)(version=$javaVersion))\"",
        *extra))
}

// joml2-value compiles with --enable-preview on a Valhalla EA JDK, at that JDK's own
// feature version. It is pinned by path: it reports the same version and vendor as a
// stock JDK, so a toolchain spec cannot select it.
val valhallaJdkHome = findProperty("valhalla.jdk.home") as String? ?: System.getenv("VALHALLA_JDK_HOME")
    ?: "/Library/Java/JavaVirtualMachines/jdk-28.jdk/Contents/Home"
val previewVersion = providers.fileContents(layout.projectDirectory.file("$valhallaJdkHome/release")).asText.orNull
    ?.substringAfter("JAVA_VERSION=\"")?.takeWhile { it.isDigit() }?.toIntOrNull() ?: 27

fun MavenPom.joml(pomName: String, pomDescription: String) {
    name = pomName
    description = pomDescription
    url = "https://github.com/JOML-CI/JOML2"
    inceptionYear = "2015"
    licenses { license { name = "MIT License"; url = "https://opensource.org/licenses/MIT" } }
    developers { developer { id = "httpdigest"; name = "Kai Burjack" } }
    scm {
        connection = "scm:git:https://github.com/JOML-CI/JOML2.git"
        developerConnection = "scm:git:ssh://github.com/JOML-CI/JOML2.git"
        url = "https://github.com/JOML-CI/JOML2"
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")
    group = "org.joml"
    version = rootProject.version

    configure<JavaPluginExtension> {
        withSourcesJar()
        withJavadocJar()
    }
    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.javaModuleVersion = version.toString()
    }
    tasks.withType<Jar>().configureEach {
        from(rootProject.file("LICENSE")) { into("META-INF") }
        manifest.attributes(linkedMapOf(
            // The JDK running the build, not the bytecode level.
            "Build-Jdk-Spec" to System.getProperty("java.specification.version"),
            "Implementation-Title" to project.name,
            "Implementation-Version" to version,
            "Implementation-Vendor" to "JOML",
            "Specification-Title" to "JOML 2",
            // The API line, not the patch level: 1.0.
            "Specification-Version" to version.toString().split('.').take(2).joinToString("."),
            "Specification-Vendor" to "JOML"))
    }
    // Each javadoc JVM would otherwise take a quarter of the machine's memory.
    tasks.withType<Javadoc>().configureEach { maxMemory = "1g" }
    // The native-image metadata belongs to the binary jar only.
    tasks.named<Jar>("sourcesJar") { exclude("META-INF/native-image/**") }
    configure<PublishingExtension> {
        publications.create<MavenPublication>("maven") { from(components["java"]) }
    }
    configure<SigningExtension> {
        sign(the<PublishingExtension>().publications)
        isRequired = !version.toString().endsWith("SNAPSHOT")
    }
}

// ─── The Java variants ───────────────────────────────────────────────────────
for ((artifactId, v) in variants) project(":$artifactId") {
    val preview = v.java == 0
    val javaVersion = if (preview) previewVersion else v.java

    dependencies { "compileOnly"("org.jspecify:jspecify:1.0.1") }

    // --release cannot be combined with --add-exports, so pin source/target instead.
    configure<JavaPluginExtension> { sourceCompatibility = JavaVersion.toVersion(javaVersion) }
    tasks.named<JavaCompile>("compileJava") {
        // A real JDK 17 compiler links against JDK 17's API, not the build JDK's.
        if (v.java == 17) javaCompiler = project.the<JavaToolchainService>().compilerFor { languageVersion = JavaLanguageVersion.of(17) }
        // The Unsafe store/load backend reads jdk.internal.misc.
        options.compilerArgs.addAll(listOf("--add-exports", "java.base/jdk.internal.misc=org.joml2"))
        // Forked compiles (JDK 17, Valhalla) do not inherit the daemon's heap.
        options.forkOptions.memoryMaximumSize = "3g"
        if (preview) {
            // The value records carry @jdk.internal.vm.annotation.LooselyConsistentValue.
            options.compilerArgs.addAll(listOf("--enable-preview",
                "--add-exports", "java.base/jdk.internal.vm.annotation=org.joml2"))
            options.isFork = true
            options.forkOptions.javaHome = file(valhallaJdkHome)
        }
    }
    // Documents the API only: javadoc runs on the classpath, without the module descriptor
    // and the internal packages, which resolve from the compiled classes instead.
    tasks.named<Javadoc>("javadoc") {
        modularity.inferModulePath = false
        exclude("module-info.java", "org/joml2/internal/**")
        if (preview) {
            executable = "$valhallaJdkHome/bin/javadoc"
            val o = options as StandardJavadocDocletOptions
            o.addStringOption("source", "$javaVersion")
            o.addBooleanOption("-enable-preview", true)
            o.addStringOption("-add-exports", "java.base/jdk.internal.vm.annotation=ALL-UNNAMED")
        }
    }

    val displayName = "JOML 2 (${v.storage}, ${if (preview) "JEP 401 preview" else "JDK ${v.java}+"})"
    val description = "Java OpenGL Math Library: pre-built ${v.description} variant"
    tasks.named<Jar>("jar") {
        // Every variant has the same packages: sealing makes a second variant on the
        // classpath fail at class loading instead of silently mixing classes.
        manifest.attributes(mapOf("Sealed" to "true"))
        // All variants are bundle org.joml2, as they are module org.joml2: alternatives
        // a framework refuses to load side by side. The capability tells them apart.
        bundle(displayName, description, "org.joml2", listOf("org.joml2", "org.joml2.ops"),
            listOfNotNull("org.jspecify.annotations;resolution:=optional",
                "sun.misc;resolution:=optional", "jdk.internal.misc;resolution:=optional",
                if (preview) "jdk.internal.vm.annotation;resolution:=optional" else null,
                when (v.vectorApi) {
                    "required" -> "jdk.incubator.vector"
                    "optional" -> "jdk.incubator.vector;resolution:=optional"
                    else -> null
                }),
            javaVersion,
            "Provide-Capability" to "org.joml2.variant;storage=\"${v.storage}\";target:Long=$javaVersion" +
                ";javaTarget=\"$javaVersion${if (preview) "-preview" else ""}\";vectorApi=\"${v.vectorApi}\"")
    }

    configure<PublishingExtension> {
        publications.named<MavenPublication>("maven") { pom.joml(displayName, description) }
    }
}

// ─── Kotlin extensions over the immutable types ──────────────────────────────
// Compiled against joml2-records, and published twice: as joml2-records-kotlin and,
// byte-identical, as joml2-value-kotlin (the value API is signature-identical, and
// kotlinc cannot read preview class files).
project(":joml2-records-kotlin") {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        "api"(project(":joml2-records"))
        // The stdlib of the add-on's API level, not of the compiler.
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
    }
    // Usable from Kotlin 2.0 on, not only from the compiler's own version.
    configure<KotlinJvmProjectExtension> {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_22
            languageVersion = KotlinVersion.KOTLIN_2_0
            apiVersion = KotlinVersion.KOTLIN_2_0
        }
    }
    configure<JavaPluginExtension> { sourceCompatibility = JavaVersion.VERSION_22 }
    // module-info.java exports a package only the Kotlin compiler produces.
    tasks.named<JavaCompile>("compileJava") {
        val kotlinClasses = tasks.named<KotlinCompile>("compileKotlin").flatMap { it.destinationDirectory }
        options.compilerArgumentProviders.add(CommandLineArgumentProvider {
            listOf("--patch-module", "org.joml2.kotlin=${kotlinClasses.get().asFile}")
        })
    }
    // No Java API to document; Central accepts the empty javadoc jar.
    tasks.named("javadoc") { enabled = false }
    // Writes the same file as `sourcesJar`, whose content (Kotlin + module-info.java) is the published one.
    tasks.named("kotlinSourcesJar") { enabled = false }
    // Both publications ship the same jars, so both sign tasks write the same .asc files.
    tasks.withType<AbstractPublishToMaven>().configureEach { dependsOn(tasks.withType<Sign>()) }
    tasks.named<Jar>("jar") {
        // The same jar under two artifactIds: a build pulling both has org.joml2.kotlin twice.
        manifest.attributes(mapOf("Sealed" to "true"))
        val major = version.toString().substringBefore('.').toInt()
        val minor = version.toString().split('.')[1]
        bundle("JOML 2 (Kotlin extensions)",
            "Kotlin operator/infix/destructuring/indexing extensions over the immutable JOML 2 types",
            "org.joml2.kotlin", listOf("org.joml2.kotlin"),
            listOf("org.joml2;version=\"[$major.$minor,${major + 1})\"", "kotlin", "kotlin.jvm", "kotlin.jvm.internal"),
            22)
    }

    fun description(variant: String) = "Kotlin operator/infix/destructuring/indexing extensions over the " +
            "immutable $variant types (joml2-$variant). Java-only users need only joml2-$variant."
    configure<PublishingExtension> {
        publications.named<MavenPublication>("maven") {
            pom.joml("JOML 2 (records, Kotlin extensions)", description("records"))
        }
        publications.create<MavenPublication>("valueKotlin") {
            artifactId = "joml2-value-kotlin"
            artifact(tasks.named("jar"))
            artifact(tasks.named("sourcesJar"))
            artifact(tasks.named("javadocJar"))
            pom.joml("JOML 2 (value, Kotlin extensions)", description("value"))
            pom.withXml(valueKotlinDependencies(version.toString()))
        }
    }
}

// A top-level function, so the action captures only the version string - the
// configuration cache cannot serialize a reference to the build script.
fun valueKotlinDependencies(version: String) = Action<XmlProvider> {
    val deps = asNode().appendNode("dependencies")
    for ((artifact, scope) in listOf("org.joml:joml2-value:$version" to "compile",
                                     "org.jetbrains.kotlin:kotlin-stdlib:2.0.0" to "runtime")) {
        val (g, a, v) = artifact.split(':')
        deps.appendNode("dependency").apply {
            appendNode("groupId", g); appendNode("artifactId", a); appendNode("version", v); appendNode("scope", scope)
        }
    }
}
