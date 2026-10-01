// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2Ops} whose leading storage
 * parameter is a {@code double[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double2Ops} and its sibling kernel units. Not public API.
 */
public final class Double2OpsKernelsArray {
    private Double2OpsKernelsArray() {}

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double t) {
        double _selfx = src[srcOffset + 0];
        double _selfy = src[srcOffset + 1];
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / Math.sqrt(_t18));
        double _t23 = (1.0 / Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        double _t48 = Math.fma(_t47, _t25, _t43);
        double _t49 = Math.fma(_t47, _t27, _t44);
        return slerp_degenerate_sdd322e24_1(dest, destOffset, otherX, otherY, t, _selfx, _selfy, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, unitScale(_t48, _t49, _t48));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_sdd322e24_1(double[] dest, int destOffset, double otherX, double otherY, double t, double _selfx, double _selfy, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49, double _t51) {
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / Math.sqrt(_t60));
        double _t64 = t * Math.atan2(Math.sqrt(_t60), _t38 * _t51);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        return slerp_degenerate_sdd322e24_2(dest, destOffset, otherX, otherY, t, _selfx, _selfy, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sin(_t64), _t37 * Math.cos(_t64), _t72, _t73);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_sdd322e24_2(double[] dest, int destOffset, double otherX, double otherY, double t, double _selfx, double _selfy, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t38, double _t48, double _t49, double _t68, double _t69, double _t72, double _t73) {
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    dest[destOffset + 0] = Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv;
                    dest[destOffset + 1] = Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv;
                } else {
                    dest[destOffset + 0] = Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv;
                    dest[destOffset + 1] = Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv;
                }
            } else {
                dest[destOffset + 0] = Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv;
                dest[destOffset + 1] = Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv;
            }
        } else {
            dest[destOffset + 0] = Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = Math.fma(t, otherY - _selfy, _selfy);
        }
        return dest;
    }

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        double _selfx = src[srcOffset + 0];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset + 0];
        double _othery = other[otherOffset + 1];
        double _t1 = unitScale(_otherx, _othery, _otherx);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = _otherx * _t1;
        double _t8 = _othery * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / Math.sqrt(_t18));
        double _t23 = (1.0 / Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        return slerp_degenerate_s29a2f4e0_1(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * Math.sqrt(_t19) * (_t11 / _t2), _t38, Math.fma(_t47, _t25, _t43), Math.fma(_t47, _t27, _t44));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s29a2f4e0_1(double[] dest, int destOffset, double t, double _selfx, double _selfy, double _otherx, double _othery, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49) {
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / Math.sqrt(_t60));
        double _t64 = t * Math.atan2(Math.sqrt(_t60), _t38 * _t51);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        return slerp_degenerate_s29a2f4e0_2(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sin(_t64), _t37 * Math.cos(_t64), _t72, _t73);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s29a2f4e0_2(double[] dest, int destOffset, double t, double _selfx, double _selfy, double _otherx, double _othery, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t38, double _t48, double _t49, double _t68, double _t69, double _t72, double _t73) {
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    dest[destOffset + 0] = Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv;
                    dest[destOffset + 1] = Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv;
                } else {
                    dest[destOffset + 0] = Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv;
                    dest[destOffset + 1] = Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv;
                }
            } else {
                dest[destOffset + 0] = Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv;
                dest[destOffset + 1] = Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv;
            }
        } else {
            dest[destOffset + 0] = Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = Math.fma(t, _othery - _selfy, _selfy);
        }
        return dest;
    }

    public static double angleBetween_degenerate(double[] src, int srcOffset, double otherX, double otherY) {
        double _selfx = src[srcOffset + 0];
        double _selfy = src[srcOffset + 1];
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = otherY * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = otherX * _t0;
        double _t9 = _selfy * _t1;
        double _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static double angleBetween_degenerate(double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfx = src[srcOffset + 0];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset + 0];
        double _othery = other[otherOffset + 1];
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static double orientedAngle_degenerate(double[] src, int srcOffset, double otherX, double otherY) {
        double _selfx = src[srcOffset + 0];
        double _selfy = src[srcOffset + 1];
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = otherY * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = otherX * _t0;
        double _t9 = _selfy * _t1;
        double _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
    }

    public static double orientedAngle_degenerate(double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfx = src[srcOffset + 0];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset + 0];
        double _othery = other[otherOffset + 1];
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-126, 2^126], so zero and subnormal
     * values scale up without overflow and the largest floats land in [2, 4).
     */
    private static float unitScale(float a, float b, float c) {
        int e = java.lang.Math.max(java.lang.Math.max(Float.floatToRawIntBits(a) & 0x7F800000,
                Float.floatToRawIntBits(b) & 0x7F800000), Float.floatToRawIntBits(c) & 0x7F800000);
        return Float.intBitsToFloat(0x7F000000 - java.lang.Math.min(java.lang.Math.max(e, 0x00800000), 0x7E800000));
    }

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
