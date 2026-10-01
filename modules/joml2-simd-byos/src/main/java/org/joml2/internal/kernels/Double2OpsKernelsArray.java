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
 * to keep generated sources IDE-sized; public only because {@code Double2Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double2OpsKernelsArray {
    private Double2OpsKernelsArray() {}

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, t);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = java.lang.Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = java.lang.Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = java.lang.Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = java.lang.Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = java.lang.Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -java.lang.Math.fma(_t43, _t25, _t44 * _t27);
        double _t48 = java.lang.Math.fma(_t47, _t25, _t43);
        double _t49 = java.lang.Math.fma(_t47, _t27, _t44);
        double _t51 = unitScale(_t48, _t49, _t48);
        return slerp_degenerate_sdd322e24_1_fma(dest, destOffset, otherX, otherY, t, _selfx, _selfy, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, _t51, _t48 * _t51);
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = ((_t7) * (_t7) + (_t8 * _t8));
        double _t19 = ((_t9) * (_t9) + (_t10 * _t10));
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = ((_t22 * _t7) * (_t25) + (_t22 * _t8 * _t27));
        double _t43 = ((_t22) * (_t7) - (_t38 * _t25));
        double _t44 = ((_t22) * (_t8) - (_t38 * _t27));
        double _t47 = -((_t43) * (_t25) + (_t44 * _t27));
        double _t48 = ((_t47) * (_t25) + (_t43));
        double _t49 = ((_t47) * (_t27) + (_t44));
        double _t51 = unitScale(_t48, _t49, _t48);
        return slerp_degenerate_sdd322e24_1_mulAdd(dest, destOffset, otherX, otherY, t, _selfx, _selfy, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, _t51, _t48 * _t51);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_sdd322e24_1_fma(double[] dest, int destOffset, double otherX, double otherY, double t, double _selfx, double _selfy, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49, double _t51, double _t57) {
        double _t58 = _t49 * _t51;
        double _t60 = java.lang.Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t67 = _t37 * Math.sinWithCos(_t64);
        double _t68 = _t37 * Math.cosWithSin(_t64);
        double _t71, _t72;
        if (_t60 > 0.0) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (java.lang.Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    dest[destOffset] = java.lang.Math.fma(_t67, _t28, _t68 * _t25) * _t11_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t67, _t25, _t68 * _t27) * _t11_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, otherY - _selfy, _selfy);
        }
        return dest;
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_sdd322e24_1_mulAdd(double[] dest, int destOffset, double otherX, double otherY, double t, double _selfx, double _selfy, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49, double _t51, double _t57) {
        double _t58 = _t49 * _t51;
        double _t60 = ((_t57) * (_t57) + (_t58 * _t58));
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t67 = _t37 * Math.sinWithCos(_t64);
        double _t68 = _t37 * Math.cosWithSin(_t64);
        double _t71, _t72;
        if (_t60 > 0.0) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (((_t48) * (_t48) + (_t49 * _t49)) <= 5.048709793414476E-29) {
                    dest[destOffset] = ((_t67) * (_t28) + (_t68 * _t25)) * _t11_inv;
                    dest[destOffset + 1] = ((_t67) * (_t25) + (_t68 * _t27)) * _t11_inv;
                } else {
                    dest[destOffset] = ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv;
                    dest[destOffset + 1] = ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv;
                }
            } else {
                dest[destOffset] = ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv;
                dest[destOffset + 1] = ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv;
            }
        } else {
            dest[destOffset] = ((t) * (otherX - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (otherY - _selfy) + (_selfy));
        }
        return dest;
    }

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _t1 = unitScale(_otherx, _othery, _otherx);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = _otherx * _t1;
        double _t8 = _othery * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = java.lang.Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = java.lang.Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = java.lang.Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = java.lang.Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = java.lang.Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -java.lang.Math.fma(_t43, _t25, _t44 * _t27);
        return slerp_degenerate_s29a2f4e0_1_fma(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, java.lang.Math.fma(_t47, _t25, _t43), java.lang.Math.fma(_t47, _t27, _t44));
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _t1 = unitScale(_otherx, _othery, _otherx);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = _otherx * _t1;
        double _t8 = _othery * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = ((_t7) * (_t7) + (_t8 * _t8));
        double _t19 = ((_t9) * (_t9) + (_t10 * _t10));
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = ((_t22 * _t7) * (_t25) + (_t22 * _t8 * _t27));
        double _t43 = ((_t22) * (_t7) - (_t38 * _t25));
        double _t44 = ((_t22) * (_t8) - (_t38 * _t27));
        double _t47 = -((_t43) * (_t25) + (_t44 * _t27));
        return slerp_degenerate_s29a2f4e0_1_mulAdd(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, ((_t47) * (_t25) + (_t43)), ((_t47) * (_t27) + (_t44)));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s29a2f4e0_1_fma(double[] dest, int destOffset, double t, double _selfx, double _selfy, double _otherx, double _othery, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49) {
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = java.lang.Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t67 = _t37 * Math.sinWithCos(_t64);
        double _t68 = _t37 * Math.cosWithSin(_t64);
        double _t71, _t72;
        if (_t60 > 0.0) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (java.lang.Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    dest[destOffset] = java.lang.Math.fma(_t67, _t28, _t68 * _t25) * _t11_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t67, _t25, _t68 * _t27) * _t11_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, _othery - _selfy, _selfy);
        }
        return dest;
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s29a2f4e0_1_mulAdd(double[] dest, int destOffset, double t, double _selfx, double _selfy, double _otherx, double _othery, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49) {
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = ((_t57) * (_t57) + (_t58 * _t58));
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t67 = _t37 * Math.sinWithCos(_t64);
        double _t68 = _t37 * Math.cosWithSin(_t64);
        double _t71, _t72;
        if (_t60 > 0.0) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (((_t48) * (_t48) + (_t49 * _t49)) <= 5.048709793414476E-29) {
                    dest[destOffset] = ((_t67) * (_t28) + (_t68 * _t25)) * _t11_inv;
                    dest[destOffset + 1] = ((_t67) * (_t25) + (_t68 * _t27)) * _t11_inv;
                } else {
                    dest[destOffset] = ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv;
                    dest[destOffset + 1] = ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv;
                }
            } else {
                dest[destOffset] = ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv;
                dest[destOffset + 1] = ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv;
            }
        } else {
            dest[destOffset] = ((t) * (_otherx - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (_othery - _selfy) + (_selfy));
        }
        return dest;
    }

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, otherX, otherY, t, epsilon);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, t, epsilon);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double t, double epsilon) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = java.lang.Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = java.lang.Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = java.lang.Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = java.lang.Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = java.lang.Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -java.lang.Math.fma(_t43, _t25, _t44 * _t27);
        double _t48 = java.lang.Math.fma(_t47, _t25, _t43);
        double _t49 = java.lang.Math.fma(_t47, _t27, _t44);
        double _t51 = unitScale(_t48, _t49, _t48);
        return slerp_degenerate_sc44a20a7_1_fma(dest, destOffset, otherX, otherY, t, epsilon, _selfx, _selfy, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, _t51, _t48 * _t51);
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double t, double epsilon) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = ((_t7) * (_t7) + (_t8 * _t8));
        double _t19 = ((_t9) * (_t9) + (_t10 * _t10));
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = ((_t22 * _t7) * (_t25) + (_t22 * _t8 * _t27));
        double _t43 = ((_t22) * (_t7) - (_t38 * _t25));
        double _t44 = ((_t22) * (_t8) - (_t38 * _t27));
        double _t47 = -((_t43) * (_t25) + (_t44 * _t27));
        double _t48 = ((_t47) * (_t25) + (_t43));
        double _t49 = ((_t47) * (_t27) + (_t44));
        double _t51 = unitScale(_t48, _t49, _t48);
        return slerp_degenerate_sc44a20a7_1_mulAdd(dest, destOffset, otherX, otherY, t, epsilon, _selfx, _selfy, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, _t51, _t48 * _t51);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_sc44a20a7_1_fma(double[] dest, int destOffset, double otherX, double otherY, double t, double epsilon, double _selfx, double _selfy, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49, double _t51, double _t57) {
        double _t58 = _t49 * _t51;
        double _t60 = java.lang.Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t67 = _t37 * Math.sinWithCos(_t64);
        double _t68 = _t37 * Math.cosWithSin(_t64);
        double _t71, _t72;
        if (_t60 > 0.0) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (java.lang.Math.fma(_t48, _t48, _t49 * _t49) <= epsilon) {
                    dest[destOffset] = java.lang.Math.fma(_t67, _t28, _t68 * _t25) * _t11_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t67, _t25, _t68 * _t27) * _t11_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, otherY - _selfy, _selfy);
        }
        return dest;
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_sc44a20a7_1_mulAdd(double[] dest, int destOffset, double otherX, double otherY, double t, double epsilon, double _selfx, double _selfy, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49, double _t51, double _t57) {
        double _t58 = _t49 * _t51;
        double _t60 = ((_t57) * (_t57) + (_t58 * _t58));
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t67 = _t37 * Math.sinWithCos(_t64);
        double _t68 = _t37 * Math.cosWithSin(_t64);
        double _t71, _t72;
        if (_t60 > 0.0) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (((_t48) * (_t48) + (_t49 * _t49)) <= epsilon) {
                    dest[destOffset] = ((_t67) * (_t28) + (_t68 * _t25)) * _t11_inv;
                    dest[destOffset + 1] = ((_t67) * (_t25) + (_t68 * _t27)) * _t11_inv;
                } else {
                    dest[destOffset] = ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv;
                    dest[destOffset + 1] = ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv;
                }
            } else {
                dest[destOffset] = ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv;
                dest[destOffset + 1] = ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv;
            }
        } else {
            dest[destOffset] = ((t) * (otherX - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (otherY - _selfy) + (_selfy));
        }
        return dest;
    }

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t, double epsilon) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _t1 = unitScale(_otherx, _othery, _otherx);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = _otherx * _t1;
        double _t8 = _othery * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = java.lang.Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = java.lang.Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = java.lang.Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = java.lang.Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = java.lang.Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -java.lang.Math.fma(_t43, _t25, _t44 * _t27);
        return slerp_degenerate_s5d56b263_1_fma(dest, destOffset, t, epsilon, _selfx, _selfy, _otherx, _othery, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, java.lang.Math.fma(_t47, _t25, _t43), java.lang.Math.fma(_t47, _t27, _t44));
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t, double epsilon) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _t1 = unitScale(_otherx, _othery, _otherx);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = _otherx * _t1;
        double _t8 = _othery * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t18 = ((_t7) * (_t7) + (_t8 * _t8));
        double _t19 = ((_t9) * (_t9) + (_t10 * _t10));
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = ((_t22 * _t7) * (_t25) + (_t22 * _t8 * _t27));
        double _t43 = ((_t22) * (_t7) - (_t38 * _t25));
        double _t44 = ((_t22) * (_t8) - (_t38 * _t27));
        double _t47 = -((_t43) * (_t25) + (_t44 * _t27));
        return slerp_degenerate_s5d56b263_1_mulAdd(dest, destOffset, t, epsilon, _selfx, _selfy, _otherx, _othery, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, ((_t47) * (_t25) + (_t43)), ((_t47) * (_t27) + (_t44)));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s5d56b263_1_fma(double[] dest, int destOffset, double t, double epsilon, double _selfx, double _selfy, double _otherx, double _othery, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49) {
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = java.lang.Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t67 = _t37 * Math.sinWithCos(_t64);
        double _t68 = _t37 * Math.cosWithSin(_t64);
        double _t71, _t72;
        if (_t60 > 0.0) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (java.lang.Math.fma(_t48, _t48, _t49 * _t49) <= epsilon) {
                    dest[destOffset] = java.lang.Math.fma(_t67, _t28, _t68 * _t25) * _t11_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t67, _t25, _t68 * _t27) * _t11_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, _othery - _selfy, _selfy);
        }
        return dest;
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s5d56b263_1_mulAdd(double[] dest, int destOffset, double t, double epsilon, double _selfx, double _selfy, double _otherx, double _othery, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49) {
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = ((_t57) * (_t57) + (_t58 * _t58));
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t67 = _t37 * Math.sinWithCos(_t64);
        double _t68 = _t37 * Math.cosWithSin(_t64);
        double _t71, _t72;
        if (_t60 > 0.0) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (((_t48) * (_t48) + (_t49 * _t49)) <= epsilon) {
                    dest[destOffset] = ((_t67) * (_t28) + (_t68 * _t25)) * _t11_inv;
                    dest[destOffset + 1] = ((_t67) * (_t25) + (_t68 * _t27)) * _t11_inv;
                } else {
                    dest[destOffset] = ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv;
                    dest[destOffset + 1] = ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv;
                }
            } else {
                dest[destOffset] = ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv;
                dest[destOffset + 1] = ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv;
            }
        } else {
            dest[destOffset] = ((t) * (_otherx - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (_othery - _selfy) + (_selfy));
        }
        return dest;
    }

    public static double angleBetween_degenerate(double[] src, int srcOffset, double otherX, double otherY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _t0 = unitScale(otherX, otherY, otherX);
            double _t1 = unitScale(_selfx, _selfy, _selfx);
            double _t6 = otherY * _t0;
            double _t7 = _selfx * _t1;
            double _t8 = otherX * _t0;
            double _t9 = _selfy * _t1;
            double _t11 = _t6 * _t9;
            double _t12 = -(_t8 * _t9);
            double _t15 = java.lang.Math.fma(_t6, _t7, _t12) - java.lang.Math.fma(_t8, _t9, _t12);
            double _t16 = unitScale(_t15, _t15, _t15);
            return Math.atan2(java.lang.Math.abs(_t15 * _t16), (java.lang.Math.fma(_t8, _t7, _t11) + java.lang.Math.fma(_t6, _t9, -_t11)) * _t16);
        } else {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _t0 = unitScale(otherX, otherY, otherX);
            double _t1 = unitScale(_selfx, _selfy, _selfx);
            double _t6 = otherY * _t0;
            double _t7 = _selfx * _t1;
            double _t8 = otherX * _t0;
            double _t9 = _selfy * _t1;
            double _t11 = _t6 * _t9;
            double _t12 = -(_t8 * _t9);
            double _t15 = ((_t6) * (_t7) + (_t12)) - ((_t8) * (_t9) + (_t12));
            double _t16 = unitScale(_t15, _t15, _t15);
            return Math.atan2(java.lang.Math.abs(_t15 * _t16), (((_t8) * (_t7) + (_t11)) + ((_t6) * (_t9) - (_t11))) * _t16);
        }
    }

    public static double angleBetween_degenerate(double[] src, int srcOffset, double[] other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_mulAdd(src, srcOffset, other, otherOffset);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t11 = _t6 * _t9;
        double _t12 = -(_t8 * _t9);
        double _t15 = java.lang.Math.fma(_t6, _t7, _t12) - java.lang.Math.fma(_t8, _t9, _t12);
        double _t16 = unitScale(_t15, _t15, _t15);
        return Math.atan2(java.lang.Math.abs(_t15 * _t16), (java.lang.Math.fma(_t8, _t7, _t11) + java.lang.Math.fma(_t6, _t9, -_t11)) * _t16);
    }

    /** {@code angleBetween_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_mulAdd(double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t11 = _t6 * _t9;
        double _t12 = -(_t8 * _t9);
        double _t15 = ((_t6) * (_t7) + (_t12)) - ((_t8) * (_t9) + (_t12));
        double _t16 = unitScale(_t15, _t15, _t15);
        return Math.atan2(java.lang.Math.abs(_t15 * _t16), (((_t8) * (_t7) + (_t11)) + ((_t6) * (_t9) - (_t11))) * _t16);
    }

    public static double orientedAngle_degenerate(double[] src, int srcOffset, double otherX, double otherY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_mulAdd(src, srcOffset, otherX, otherY);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = otherY * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = otherX * _t0;
        double _t9 = _selfy * _t1;
        double _t11 = _t6 * _t9;
        double _t12 = -(_t8 * _t9);
        double _t19 = java.lang.Math.fma(_t6, _t7, _t12) - java.lang.Math.fma(_t8, _t9, _t12);
        double _t20 = unitScale(_t19, _t19, _t19);
        double _t24 = _t19 * _t20;
        double _t26 = Math.atan2(java.lang.Math.abs(_t24), (java.lang.Math.fma(_t8, _t7, _t11) + java.lang.Math.fma(_t6, _t9, -_t11)) * _t20);
        return _t24 < 0.0 ? -_t26 : _t26;
    }

    /** {@code orientedAngle_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_mulAdd(double[] src, int srcOffset, double otherX, double otherY) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = otherY * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = otherX * _t0;
        double _t9 = _selfy * _t1;
        double _t11 = _t6 * _t9;
        double _t12 = -(_t8 * _t9);
        double _t19 = ((_t6) * (_t7) + (_t12)) - ((_t8) * (_t9) + (_t12));
        double _t20 = unitScale(_t19, _t19, _t19);
        double _t24 = _t19 * _t20;
        double _t26 = Math.atan2(java.lang.Math.abs(_t24), (((_t8) * (_t7) + (_t11)) + ((_t6) * (_t9) - (_t11))) * _t20);
        return _t24 < 0.0 ? -_t26 : _t26;
    }

    public static double orientedAngle_degenerate(double[] src, int srcOffset, double[] other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_mulAdd(src, srcOffset, other, otherOffset);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t11 = _t6 * _t9;
        double _t12 = -(_t8 * _t9);
        double _t19 = java.lang.Math.fma(_t6, _t7, _t12) - java.lang.Math.fma(_t8, _t9, _t12);
        double _t20 = unitScale(_t19, _t19, _t19);
        double _t24 = _t19 * _t20;
        double _t26 = Math.atan2(java.lang.Math.abs(_t24), (java.lang.Math.fma(_t8, _t7, _t11) + java.lang.Math.fma(_t6, _t9, -_t11)) * _t20);
        return _t24 < 0.0 ? -_t26 : _t26;
    }

    /** {@code orientedAngle_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_mulAdd(double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t11 = _t6 * _t9;
        double _t12 = -(_t8 * _t9);
        double _t19 = ((_t6) * (_t7) + (_t12)) - ((_t8) * (_t9) + (_t12));
        double _t20 = unitScale(_t19, _t19, _t19);
        double _t24 = _t19 * _t20;
        double _t26 = Math.atan2(java.lang.Math.abs(_t24), (((_t8) * (_t7) + (_t11)) + ((_t6) * (_t9) - (_t11))) * _t20);
        return _t24 < 0.0 ? -_t26 : _t26;
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
