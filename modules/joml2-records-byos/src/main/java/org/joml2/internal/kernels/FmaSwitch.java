// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

/**
 * The {@code joml.useFma} switch as a constant: the {@code *Ops} kernels that hold a fused and a plain
 * flavour of their work read it once per call to pick one, a field C2 folds where the shadow
 * {@code Math.fma} was a call per leaf. Initialized at the first such read, so
 * {@link org.joml2.Math#setUseFma} still works until then.
 */
public final class FmaSwitch {
    /** {@link org.joml2.Math#useFma()}, resolved once. */
    public static final boolean ON = org.joml2.Math.useFma();

    private FmaSwitch() {}
}
