// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.FloatVector;

/**
 * Tiny holder whose initialization links against {@code jdk.incubator.vector}.
 * Referenced ONLY from {@code Joml.resolveVectorApi()} inside a try/catch -
 * mirrors the {@code UnsafeOpsHolder} resolution pattern. Never touch this
 * class from anywhere else: any other reference would defeat the
 * module-absent fallback.
 */
public final class VectorApiProbe {
    private VectorApiProbe() {}
    public static final int LANES = FloatVector.SPECIES_PREFERRED.length();
}
