// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import org.joml2.*;
import org.joml2.Math;

/**
 * Runtime Vector-API availability + FMA flags for the SIMD-flavour {@code *Ops}
 * classes. This class is scalar-safe - it contains no {@code jdk.incubator.vector}
 * references - so it loads and initializes even when the incubator module is
 * absent. {@link #VECTOR_API} mirrors {@link Joml#VECTOR_API}, which owns the
 * resolution ({@code JomlConfig} override, {@code -Djoml.vectorApi}, then the
 * {@link VectorApiProbe} catch-all probe); both flags are {@code static final},
 * so the JIT constant-folds the guards to zero cost.
 */
public final class SimdSupport {
    private SimdSupport() {}
    /** Snapshot of {@link Math#useFma()} - the runtime fma/mulAdd dispatch flag
     *  shared by the scalar dispatchers and the {@code *OpsSimd} bodies. */
    public static final boolean USE_FMA = Math.useFma();
    /** True iff {@code jdk.incubator.vector} is present and not opted out. */
    public static final boolean VECTOR_API = Joml.VECTOR_API;
    /** True iff {@link #VECTOR_API} and the vector unit holds a four-lane double column (256 bits:
     *  AVX2, AVX-512): on a 128-bit unit (NEON, SSE) the Vector API runs four-lane double vectors in
     *  its Java fallback, far slower than scalar code. */
    public static final boolean DOUBLE_COLUMNS = VECTOR_API && VectorApiProbe.DOUBLE_LANES >= 4;
    /** True iff {@link #VECTOR_API} on an x86 CPU. Gates the kernels that assemble vectors from
     *  scalars lane by lane: a lane insert is one cheap instruction on x86, but slow on
     *  aarch64/NEON, where those kernels lose to scalar code. */
    public static final boolean X86 = VECTOR_API && isX86(System.getProperty("os.arch", ""));

    private static boolean isX86(String arch) {
        return arch.equals("amd64") || arch.equals("x86_64") || arch.equals("x86") || arch.matches("i[3-6]86");
    }
}
