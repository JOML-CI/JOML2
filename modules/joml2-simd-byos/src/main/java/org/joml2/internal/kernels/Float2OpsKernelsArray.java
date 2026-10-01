// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float2Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float2OpsKernelsArray {
    private Float2OpsKernelsArray() {}

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, t);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t18 = java.lang.Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = java.lang.Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = java.lang.Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = java.lang.Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = java.lang.Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -java.lang.Math.fma(_t43, _t25, _t44 * _t27);
        float _t48 = java.lang.Math.fma(_t47, _t25, _t43);
        float _t49 = java.lang.Math.fma(_t47, _t27, _t44);
        return slerp_degenerate_fma_s22d42c72_1(dest, destOffset, otherX, otherY, t, _selfx, _selfy, _t1, _t2, Math.min(_t2, _t1), _t18, _t19, _t25, _t27, _t38, _t48, _t49, unitScale(_t48, _t49, _t48));
    }

    /** Piece 2 of {@code slerp_degenerate_fma}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_fma_s22d42c72_1(float[] dest, int destOffset, float otherX, float otherY, float t, float _selfx, float _selfy, float _t1, float _t2, float _t11, float _t18, float _t19, float _t25, float _t27, float _t38, float _t48, float _t49, float _t51) {
        return slerp_degenerate_s962af6d5_1_fma(dest, destOffset, otherX, otherY, t, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, _t51, _t48 * _t51);
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = Math.min(_t2, _t1);
        float _t18 = ((_t7) * (_t7) + (_t8 * _t8));
        float _t19 = ((_t9) * (_t9) + (_t10 * _t10));
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = ((_t22 * _t7) * (_t25) + (_t22 * _t8 * _t27));
        float _t43 = ((_t22) * (_t7) - (_t38 * _t25));
        float _t44 = ((_t22) * (_t8) - (_t38 * _t27));
        float _t47 = -((_t43) * (_t25) + (_t44 * _t27));
        float _t48 = ((_t47) * (_t25) + (_t43));
        float _t49 = ((_t47) * (_t27) + (_t44));
        float _t51 = unitScale(_t48, _t49, _t48);
        return slerp_degenerate_s962af6d5_1_mulAdd(dest, destOffset, otherX, otherY, t, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, _t51, _t48 * _t51);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s962af6d5_1_fma(float[] dest, int destOffset, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49, float _t51, float _t57) {
        float _t58 = _t49 * _t51;
        float _t60 = java.lang.Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t67 = _t37 * Math.sinWithCos(_t64);
        float _t68 = _t37 * Math.cosWithSin(_t64);
        float _t71, _t72;
        if (_t60 > 0.0f) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (java.lang.Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
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
    private static float[] slerp_degenerate_s962af6d5_1_mulAdd(float[] dest, int destOffset, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49, float _t51, float _t57) {
        float _t58 = _t49 * _t51;
        float _t60 = ((_t57) * (_t57) + (_t58 * _t58));
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t67 = _t37 * Math.sinWithCos(_t64);
        float _t68 = _t37 * Math.cosWithSin(_t64);
        float _t71, _t72;
        if (_t60 > 0.0f) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (((_t48) * (_t48) + (_t49 * _t49)) <= 1.4551915E-11f) {
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

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t1 = unitScale(_otherx, _othery, _otherx);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = _otherx * _t1;
        float _t8 = _othery * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = Math.min(_t2, _t1);
        float _t18 = java.lang.Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = java.lang.Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = java.lang.Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = java.lang.Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = java.lang.Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -java.lang.Math.fma(_t43, _t25, _t44 * _t27);
        return slerp_degenerate_s83a862c0_1_fma(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, java.lang.Math.fma(_t47, _t25, _t43), java.lang.Math.fma(_t47, _t27, _t44));
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t1 = unitScale(_otherx, _othery, _otherx);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = _otherx * _t1;
        float _t8 = _othery * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = Math.min(_t2, _t1);
        float _t18 = ((_t7) * (_t7) + (_t8 * _t8));
        float _t19 = ((_t9) * (_t9) + (_t10 * _t10));
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = ((_t22 * _t7) * (_t25) + (_t22 * _t8 * _t27));
        float _t43 = ((_t22) * (_t7) - (_t38 * _t25));
        float _t44 = ((_t22) * (_t8) - (_t38 * _t27));
        float _t47 = -((_t43) * (_t25) + (_t44 * _t27));
        return slerp_degenerate_s83a862c0_1_mulAdd(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, ((_t47) * (_t25) + (_t43)), ((_t47) * (_t27) + (_t44)));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_1_fma(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49) {
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = java.lang.Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t67 = _t37 * Math.sinWithCos(_t64);
        float _t68 = _t37 * Math.cosWithSin(_t64);
        float _t71, _t72;
        if (_t60 > 0.0f) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (java.lang.Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
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
    private static float[] slerp_degenerate_s83a862c0_1_mulAdd(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49) {
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = ((_t57) * (_t57) + (_t58 * _t58));
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t67 = _t37 * Math.sinWithCos(_t64);
        float _t68 = _t37 * Math.cosWithSin(_t64);
        float _t71, _t72;
        if (_t60 > 0.0f) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (((_t48) * (_t48) + (_t49 * _t49)) <= 1.4551915E-11f) {
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

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, otherX, otherY, t, epsilon);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, t, epsilon);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t18 = java.lang.Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = java.lang.Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = java.lang.Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = java.lang.Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = java.lang.Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -java.lang.Math.fma(_t43, _t25, _t44 * _t27);
        float _t48 = java.lang.Math.fma(_t47, _t25, _t43);
        float _t49 = java.lang.Math.fma(_t47, _t27, _t44);
        return slerp_degenerate_fma_s9dd9acee_1(dest, destOffset, otherX, otherY, t, epsilon, _selfx, _selfy, _t1, _t2, Math.min(_t2, _t1), _t18, _t19, _t25, _t27, _t38, _t48, _t49, unitScale(_t48, _t49, _t48));
    }

    /** Piece 2 of {@code slerp_degenerate_fma}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_fma_s9dd9acee_1(float[] dest, int destOffset, float otherX, float otherY, float t, float epsilon, float _selfx, float _selfy, float _t1, float _t2, float _t11, float _t18, float _t19, float _t25, float _t27, float _t38, float _t48, float _t49, float _t51) {
        return slerp_degenerate_s12dd52db_1_fma(dest, destOffset, otherX, otherY, t, epsilon, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, _t51, _t48 * _t51);
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = Math.min(_t2, _t1);
        float _t18 = ((_t7) * (_t7) + (_t8 * _t8));
        float _t19 = ((_t9) * (_t9) + (_t10 * _t10));
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = ((_t22 * _t7) * (_t25) + (_t22 * _t8 * _t27));
        float _t43 = ((_t22) * (_t7) - (_t38 * _t25));
        float _t44 = ((_t22) * (_t8) - (_t38 * _t27));
        float _t47 = -((_t43) * (_t25) + (_t44 * _t27));
        float _t48 = ((_t47) * (_t25) + (_t43));
        float _t49 = ((_t47) * (_t27) + (_t44));
        float _t51 = unitScale(_t48, _t49, _t48);
        return slerp_degenerate_s12dd52db_1_mulAdd(dest, destOffset, otherX, otherY, t, epsilon, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, _t51, _t48 * _t51);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s12dd52db_1_fma(float[] dest, int destOffset, float otherX, float otherY, float t, float epsilon, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49, float _t51, float _t57) {
        float _t58 = _t49 * _t51;
        float _t60 = java.lang.Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t67 = _t37 * Math.sinWithCos(_t64);
        float _t68 = _t37 * Math.cosWithSin(_t64);
        float _t71, _t72;
        if (_t60 > 0.0f) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
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
    private static float[] slerp_degenerate_s12dd52db_1_mulAdd(float[] dest, int destOffset, float otherX, float otherY, float t, float epsilon, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49, float _t51, float _t57) {
        float _t58 = _t49 * _t51;
        float _t60 = ((_t57) * (_t57) + (_t58 * _t58));
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t67 = _t37 * Math.sinWithCos(_t64);
        float _t68 = _t37 * Math.cosWithSin(_t64);
        float _t71, _t72;
        if (_t60 > 0.0f) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
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

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t1 = unitScale(_otherx, _othery, _otherx);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = _otherx * _t1;
        float _t8 = _othery * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = Math.min(_t2, _t1);
        float _t18 = java.lang.Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = java.lang.Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = java.lang.Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = java.lang.Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = java.lang.Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -java.lang.Math.fma(_t43, _t25, _t44 * _t27);
        return slerp_degenerate_sa94ca514_1_fma(dest, destOffset, t, epsilon, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, java.lang.Math.fma(_t47, _t25, _t43), java.lang.Math.fma(_t47, _t27, _t44));
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t1 = unitScale(_otherx, _othery, _otherx);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = _otherx * _t1;
        float _t8 = _othery * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = Math.min(_t2, _t1);
        float _t18 = ((_t7) * (_t7) + (_t8 * _t8));
        float _t19 = ((_t9) * (_t9) + (_t10 * _t10));
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = ((_t22 * _t7) * (_t25) + (_t22 * _t8 * _t27));
        float _t43 = ((_t22) * (_t7) - (_t38 * _t25));
        float _t44 = ((_t22) * (_t8) - (_t38 * _t27));
        float _t47 = -((_t43) * (_t25) + (_t44 * _t27));
        return slerp_degenerate_sa94ca514_1_mulAdd(dest, destOffset, t, epsilon, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, ((_t47) * (_t25) + (_t43)), ((_t47) * (_t27) + (_t44)));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sa94ca514_1_fma(float[] dest, int destOffset, float t, float epsilon, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49) {
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = java.lang.Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t71, _t72;
        if (_t60 > 0.0f) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        return slerp_degenerate_sa94ca514_1_fma_sadae0e36_1(dest, destOffset, t, epsilon, _selfx, _selfy, _otherx, _othery, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sinWithCos(_t64), _t37 * Math.cosWithSin(_t64), _t71, _t72);
    }

    /** Piece 2 of {@code slerp_degenerate_sa94ca514_1_fma}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sa94ca514_1_fma_sadae0e36_1(float[] dest, int destOffset, float t, float epsilon, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t67, float _t68, float _t71, float _t72) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
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
    private static float[] slerp_degenerate_sa94ca514_1_mulAdd(float[] dest, int destOffset, float t, float epsilon, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49) {
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = ((_t57) * (_t57) + (_t58 * _t58));
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t67 = _t37 * Math.sinWithCos(_t64);
        float _t68 = _t37 * Math.cosWithSin(_t64);
        float _t71, _t72;
        if (_t60 > 0.0f) {
            _t71 = _t62 * _t58;
            _t72 = _t62 * _t57;
        } else {
            _t71 = _t25;
            _t72 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
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

    public static float angleBetween_degenerate(float[] src, int srcOffset, float otherX, float otherY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _t0 = unitScale(otherX, otherY, otherX);
            float _t1 = unitScale(_selfx, _selfy, _selfx);
            float _t6 = otherY * _t0;
            float _t7 = _selfx * _t1;
            float _t8 = otherX * _t0;
            float _t9 = _selfy * _t1;
            float _t11 = _t6 * _t9;
            float _t12 = -(_t8 * _t9);
            float _t15 = java.lang.Math.fma(_t6, _t7, _t12) - java.lang.Math.fma(_t8, _t9, _t12);
            float _t16 = unitScale(_t15, _t15, _t15);
            return Math.atan2(java.lang.Math.abs(_t15 * _t16), (java.lang.Math.fma(_t8, _t7, _t11) + java.lang.Math.fma(_t6, _t9, -_t11)) * _t16);
        } else {
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _t0 = unitScale(otherX, otherY, otherX);
            float _t1 = unitScale(_selfx, _selfy, _selfx);
            float _t6 = otherY * _t0;
            float _t7 = _selfx * _t1;
            float _t8 = otherX * _t0;
            float _t9 = _selfy * _t1;
            float _t11 = _t6 * _t9;
            float _t12 = -(_t8 * _t9);
            float _t15 = ((_t6) * (_t7) + (_t12)) - ((_t8) * (_t9) + (_t12));
            float _t16 = unitScale(_t15, _t15, _t15);
            return Math.atan2(java.lang.Math.abs(_t15 * _t16), (((_t8) * (_t7) + (_t11)) + ((_t6) * (_t9) - (_t11))) * _t16);
        }
    }

    public static float angleBetween_degenerate(float[] src, int srcOffset, float[] other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_mulAdd(src, srcOffset, other, otherOffset);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t11 = _t6 * _t9;
        float _t12 = -(_t8 * _t9);
        float _t15 = java.lang.Math.fma(_t6, _t7, _t12) - java.lang.Math.fma(_t8, _t9, _t12);
        float _t16 = unitScale(_t15, _t15, _t15);
        return Math.atan2(java.lang.Math.abs(_t15 * _t16), (java.lang.Math.fma(_t8, _t7, _t11) + java.lang.Math.fma(_t6, _t9, -_t11)) * _t16);
    }

    /** {@code angleBetween_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_mulAdd(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t11 = _t6 * _t9;
        float _t12 = -(_t8 * _t9);
        float _t15 = ((_t6) * (_t7) + (_t12)) - ((_t8) * (_t9) + (_t12));
        float _t16 = unitScale(_t15, _t15, _t15);
        return Math.atan2(java.lang.Math.abs(_t15 * _t16), (((_t8) * (_t7) + (_t11)) + ((_t6) * (_t9) - (_t11))) * _t16);
    }

    public static float orientedAngle_degenerate(float[] src, int srcOffset, float otherX, float otherY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_mulAdd(src, srcOffset, otherX, otherY);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t11 = _t6 * _t9;
        float _t12 = -(_t8 * _t9);
        float _t19 = java.lang.Math.fma(_t6, _t7, _t12) - java.lang.Math.fma(_t8, _t9, _t12);
        float _t20 = unitScale(_t19, _t19, _t19);
        float _t24 = _t19 * _t20;
        float _t26 = Math.atan2(java.lang.Math.abs(_t24), (java.lang.Math.fma(_t8, _t7, _t11) + java.lang.Math.fma(_t6, _t9, -_t11)) * _t20);
        return _t24 < 0.0f ? -_t26 : _t26;
    }

    /** {@code orientedAngle_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_mulAdd(float[] src, int srcOffset, float otherX, float otherY) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t11 = _t6 * _t9;
        float _t12 = -(_t8 * _t9);
        float _t19 = ((_t6) * (_t7) + (_t12)) - ((_t8) * (_t9) + (_t12));
        float _t20 = unitScale(_t19, _t19, _t19);
        float _t24 = _t19 * _t20;
        float _t26 = Math.atan2(java.lang.Math.abs(_t24), (((_t8) * (_t7) + (_t11)) + ((_t6) * (_t9) - (_t11))) * _t20);
        return _t24 < 0.0f ? -_t26 : _t26;
    }

    public static float orientedAngle_degenerate(float[] src, int srcOffset, float[] other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_mulAdd(src, srcOffset, other, otherOffset);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t11 = _t6 * _t9;
        float _t12 = -(_t8 * _t9);
        float _t19 = java.lang.Math.fma(_t6, _t7, _t12) - java.lang.Math.fma(_t8, _t9, _t12);
        float _t20 = unitScale(_t19, _t19, _t19);
        float _t24 = _t19 * _t20;
        float _t26 = Math.atan2(java.lang.Math.abs(_t24), (java.lang.Math.fma(_t8, _t7, _t11) + java.lang.Math.fma(_t6, _t9, -_t11)) * _t20);
        return _t24 < 0.0f ? -_t26 : _t26;
    }

    /** {@code orientedAngle_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_mulAdd(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t11 = _t6 * _t9;
        float _t12 = -(_t8 * _t9);
        float _t19 = ((_t6) * (_t7) + (_t12)) - ((_t8) * (_t9) + (_t12));
        float _t20 = unitScale(_t19, _t19, _t19);
        float _t24 = _t19 * _t20;
        float _t26 = Math.atan2(java.lang.Math.abs(_t24), (((_t8) * (_t7) + (_t11)) + ((_t6) * (_t9) - (_t11))) * _t20);
        return _t24 < 0.0f ? -_t26 : _t26;
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
}
