// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal;

/**
 * The {@code joml.useFma} switch as a constant: the methods that hold a fused and a plain flavour of
 * their work read it once per call to pick one, a field C2 folds where {@code Math.useFma()} was a
 * call it parsed and inlined. Initialized at the first such read, as {@code Math}'s own flags are at
 * their first use, so {@link org.joml2.Math#setUseFma} still works until then.
 */
public final class FmaSwitch {
    /** {@link org.joml2.Math#useFma()}, resolved once. */
    public static final boolean ON = org.joml2.Math.useFma();

    private FmaSwitch() {}
}
