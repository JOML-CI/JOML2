// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

/**
 * Programmatic configuration for the library's global flags, overriding the
 * {@code joml.returnNew}, {@code joml.storeLoadBackend} and {@code joml.vectorApi}
 * system properties.
 *
 * <p>All setters must be called before the first use of any {@code Joml}
 * method or {@code *Ops} class: the class initializers read the overrides once
 * and freeze them into {@code static final} fields (so the JIT can constant-fold
 * them). Calling a setter afterwards throws {@link IllegalStateException}. The setters are
 * thread-safe: one racing the first use is either honoured or throws, never silently lost.</p>
 *
 * <p>Accepted system-property spellings:</p>
 * <ul>
 *   <li>{@code -Djoml.returnNew=true} / {@code =false} (case-insensitive); a bare
 *       {@code -Djoml.returnNew} (present, empty value) means {@code true}, like
 *       {@code -Djoml.strictMath}. Any other value logs one warning on {@code System.err}
 *       and keeps the default {@code false}.</li>
 *   <li>{@code -Djoml.storeLoadBackend=api} / {@code =unsafe} (case-insensitive,
 *       surrounding whitespace ignored). Unset or empty selects the default (UNSAFE when
 *       {@code sun.misc.Unsafe} is available, else API). {@code unsafe} on a JVM without
 *       {@code sun.misc.Unsafe} logs one warning on {@code System.err} and uses API; an
 *       unrecognised value logs one warning and uses the default.</li>
 *   <li>{@code -Djoml.vectorApi=true} / {@code =false} (case-insensitive, surrounding whitespace
 *       ignored); a bare {@code -Djoml.vectorApi} means {@code true}. Unset leaves the SIMD kernels
 *       enabled when {@code jdk.incubator.vector} is present; any other value logs one warning on
 *       {@code System.err} and disables them.</li>
 * </ul>
 *
 * <p>A setter always takes precedence over its system property, in both directions:
 * {@link #setVectorApi setVectorApi(true)} re-enables the SIMD kernels under
 * {@code -Djoml.vectorApi=false}, and {@code setVectorApi(false)} disables them under
 * {@code =true}. Module presence still wins - nothing can enable SIMD when
 * {@code jdk.incubator.vector} is absent.</p>
 *
 * <p>{@code returnNew} changes only the computing self-form operations ({@code v.add(o)},
 * {@code m.mul(n)}, {@code q.normalize()}, ...): they leave {@code this} unchanged and return
 * a new instance holding the result. The {@code set*}, {@code make*}, {@code load*},
 * {@code composeTRS*} and {@code targetTo} methods are setters, not computations, and still
 * mutate and return {@code this}.</p>
 *
 * <p>The UNSAFE store/load backend uses {@code sun.misc.Unsafe}; on JDK 23+ (JEP 471) run with
 * {@code --sun-misc-unsafe-memory-access=allow} or select {@code -Djoml.storeLoadBackend=api}.</p>
 */
public final class JomlConfig {
    private JomlConfig() {}
    static volatile Boolean returnNewOverride;
    static volatile StoreLoadBackend storeLoadBackendOverride;
    static volatile Boolean vectorApiOverride;
    static volatile boolean jomlInitialized;

    /**
     * Set whether the computing no-dest self-form operations ({@code v.add(o)},
     * {@code m.mul(n)}, {@code q.normalize()}, ...) return a freshly allocated
     * instance instead of mutating and returning {@code this} (equivalent to
     * launching with {@code -Djoml.returnNew=true}). The {@code set*}, {@code make*},
     * {@code load*}, {@code composeTRS*} and {@code targetTo} methods are setters and always
     * mutate {@code this}. Has no effect in the immutable
     * record and value variants, whose operations always return new instances.
     * <p>
     * Valid input: any value.
     *
     * @param value {@code true} to make self-forms allocate and return new instances
     * @throws IllegalStateException if {@code Joml} has already been initialized
     */
    public static void setReturnNew(boolean value) {
        synchronized (JomlConfig.class) {
            requireNotFrozen();
            returnNewOverride = value;
        }
    }

    /**
     * Select how the generated store/load methods access native memory
     * (equivalent to launching with {@code -Djoml.storeLoadBackend=...}).
     * {@link StoreLoadBackend#UNSAFE} on a JVM without {@code sun.misc.Unsafe} logs
     * one warning on {@code System.err} and resolves to {@link StoreLoadBackend#API}.
     * <p>
     * Valid input: any value.
     *
     * @param backend the backend to use
     * @throws IllegalStateException if {@code Joml} has already been initialized
     */
    public static void setStoreLoadBackend(StoreLoadBackend backend) {
        synchronized (JomlConfig.class) {
            requireNotFrozen();
            storeLoadBackendOverride = backend;
        }
    }

    /**
     * Set whether the {@code *Ops} classes may use their SIMD (Vector-API) kernels
     * (equivalent to launching with {@code -Djoml.vectorApi=...}). {@code false}
     * forces the scalar fallback paths even when {@code jdk.incubator.vector} is
     * present; {@code true} (the default) uses the Vector API when the module is
     * available - it cannot enable SIMD on a JVM without the module. The override
     * takes precedence over the {@code joml.vectorApi} system property in both
     * directions: {@code setVectorApi(true)} wins over {@code -Djoml.vectorApi=false}.
     * Has no effect in variants that ship scalar {@code *Ops}, where
     * {@code Joml.VECTOR_API} is constant {@code false}.
     * <p>
     * Valid input: any value.
     *
     * @param enabled {@code false} to force the scalar paths, {@code true} to use the
     *                Vector API when the module is present
     * @throws IllegalStateException if the flags have already been frozen
     */
    public static void setVectorApi(boolean enabled) {
        synchronized (JomlConfig.class) {
            requireNotFrozen();
            vectorApiOverride = enabled;
        }
    }

    /**
     * Freeze the overrides: Joml.<clinit> calls this before it reads any of them. It takes
     * the setters' monitor, so a setter either completes first (and is read) or throws -
     * a check-then-write outside the lock could pass the check after the read and be lost.
     */
    static void freeze() {
        synchronized (JomlConfig.class) {
            jomlInitialized = true;
        }
    }

    private static void requireNotFrozen() {
        if (jomlInitialized) throw new IllegalStateException(alreadyInitializedMessage());
    }

    private static String alreadyInitializedMessage() {
        return "JomlConfig setters must be called before the first use of any Joml method or *Ops class: "
             + "the flags have already been class-initialized into static final fields and are frozen.";
    }
}
