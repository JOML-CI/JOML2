// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.FloatVector;

/**
 * Tiny holder whose initialization links against {@code jdk.incubator.vector}.
 * Referenced ONLY from {@code Joml.resolveVectorApi()} inside a try/catch, and
 * from {@code SimdSupport.DOUBLE_COLUMNS} once that probe has succeeded -
 * mirrors the {@code UnsafeOpsHolder} resolution pattern. Never touch this
 * class from anywhere else: any other reference would defeat the
 * module-absent fallback.
 */
public final class VectorApiProbe {
    private VectorApiProbe() {}
    public static final int LANES = FloatVector.SPECIES_PREFERRED.length();
    /** Lanes of the preferred double species: 2 on a 128-bit vector unit. */
    public static final int DOUBLE_LANES = DoubleVector.SPECIES_PREFERRED.length();
}
