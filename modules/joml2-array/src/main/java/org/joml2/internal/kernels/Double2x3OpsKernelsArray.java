// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x3Ops} whose leading storage
 * parameter is a {@code double[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double2x3Ops} and its sibling kernel units. Not public API.
 */
public final class Double2x3OpsKernelsArray {
    private Double2x3OpsKernelsArray() {}

    public static double[] invert_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t8 = _self11 * _t0;
        double _t9 = _self00 * _t1;
        double _t10 = _self01 * _t1;
        double _t11 = _self10 * _t0;
        double _t12 = _self02 * _t1;
        double _t13 = _self12 * _t0;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        dest[destOffset] = _t8 * _sp0;
        dest[destOffset + 1] = -(_t11 * _sp0);
        dest[destOffset + 2] = -(_t10 * _sp1);
        dest[destOffset + 3] = _t9 * _sp1;
        dest[destOffset + 4] = -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv);
        dest[destOffset + 5] = -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv);
        return dest;
    }

    public static double[] invertProduct_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        double _other02 = other[otherOffset + 4];
        double _other12 = other[otherOffset + 5];
        double _t6 = Math.fma(_other01, _self10, _other11 * _self11);
        double _t7 = Math.fma(_other00, _self10, _other10 * _self11);
        double _t8 = Math.fma(_other00, _self00, _other10 * _self01);
        double _t9 = Math.fma(_other01, _self00, _other11 * _self01);
        double _t12 = unitScale(_t7, _t6, _t7);
        double _t13 = unitScale(_t8, _t9, _t8);
        double _t18 = _t6 * _t12;
        double _t19 = _t8 * _t13;
        double _t20 = _t7 * _t12;
        double _t21 = _t9 * _t13;
        double _t28_inv = 1.0 / Math.fma(_t19, _t18, -(_t20 * _t21));
        double _sp0 = _t13 * _t28_inv;
        dest[destOffset] = _t18 * _sp0;
        dest[destOffset + 1] = -(_t20 * _sp0);
        return invertProduct_degenerate_s13d521b0_1(dest, destOffset, _t18, _t19, _t20, _t21, Math.fma(_other02, _self00, Math.fma(_other12, _self01, _self02)) * _t13, Math.fma(_other02, _self10, Math.fma(_other12, _self11, _self12)) * _t12, _t28_inv, _t12 * _t28_inv);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_1(double[] dest, int destOffset, double _t18, double _t19, double _t20, double _t21, double _t24, double _t25, double _t28_inv, double _sp1) {
        dest[destOffset + 2] = -(_t21 * _sp1);
        dest[destOffset + 3] = _t19 * _sp1;
        dest[destOffset + 4] = -(Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv);
        dest[destOffset + 5] = -(Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv);
        return dest;
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-1022, 2^1022], so zero and subnormal
     * values scale up without overflow and the largest doubles land in [2, 4).
     */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
