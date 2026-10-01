// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.FloatVector;

/**
 * Vector fused multiply-add with a runtime fallback. {@code fma(a, b, c)}
 * computes {@code a*b + c}: a single hardware {@code .fma} when enabled, or
 * a {@code mul}/{@code add} pair when FMA is disabled via
 * {@link org.joml2.Math#setUseFma}/{@code -Djoml.useFma=false}. The
 * {@code static final} flag is constant-folded by the JIT, so neither path
 * carries a runtime branch.
 *
 * <p>{@code USE_FMA} is package-visible so the {@code *Ops} classes can branch on
 * it ONCE per op - dispatching to a {@code _fma}/{@code _mulAdd} sub-method that
 * uses {@code .fma()}/{@code .mul().add()} <em>directly</em>. Routing every FMA
 * through {@link #fma} instead lets the FloatVector arguments escape into this
 * (often non-inlined, hot, multi-caller) helper, defeating escape analysis and
 * heap-boxing the vectors (~960 B/op for a Mat4 mul).
 */
public final class SimdMath {
    public static final boolean USE_FMA = SimdSupport.USE_FMA;
    private SimdMath() {}
    public static FloatVector fma(FloatVector a, FloatVector b, FloatVector c) {
        if (USE_FMA) return a.fma(b, c);
        return a.mul(b).add(c);
    }
    public static DoubleVector fma(DoubleVector a, DoubleVector b, DoubleVector c) {
        if (USE_FMA) return a.fma(b, c);
        return a.mul(b).add(c);
    }
}
