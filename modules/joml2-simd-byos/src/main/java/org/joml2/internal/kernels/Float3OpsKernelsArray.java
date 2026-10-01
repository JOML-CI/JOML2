// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float3OpsKernelsArray {
    private Float3OpsKernelsArray() {}

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        return slerp_degenerate_s339548db_1_fma(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t9, _t10, _t11, 1.0f / _t15, _t24, _t25, (1.0f / (float) java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        return slerp_degenerate_s339548db_1_mulAdd(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t9, _t10, _t11, 1.0f / _t15, _t24, _t25, (1.0f / (float) java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s339548db_1_fma(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float _selfx, float _selfy, float _selfz, float _t9, float _t10, float _t11, float _t15_inv, float _t24, float _t25, float _t28, float _t31, float _t33, float _t35, float _t48, float _t49, float _t50, float _t52) {
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        return slerp_degenerate_s339548db_1_fma_sf9ab854c_1(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t49, _t50, _t52, _t53, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49)))), _t74, _t75, _t76, _t85, _t86, _t87, _t91, t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78));
    }

    /** Piece 2 of {@code slerp_degenerate_s339548db_1_fma}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s339548db_1_fma_sf9ab854c_1(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t49, float _t50, float _t52, float _t53, float _t68, float _t74, float _t75, float _t76, float _t85, float _t86, float _t87, float _t91, float _t95) {
        return slerp_degenerate_s339548db_2_fma(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0f / (float) java.lang.Math.sqrt(_t91)), _t95, _t48 * Math.sinWithCos(_t95));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s339548db_1_mulAdd(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float _selfx, float _selfy, float _selfz, float _t9, float _t10, float _t11, float _t15_inv, float _t24, float _t25, float _t28, float _t31, float _t33, float _t35, float _t48, float _t49, float _t50, float _t52) {
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        return slerp_degenerate_s339548db_2_mulAdd(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0f / (float) java.lang.Math.sqrt(_t91)), _t95, _t48 * Math.sinWithCos(_t95));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s339548db_2_fma(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t85, float _t86, float _t87, float _t91, float _t93, float _t95, float _t98) {
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_s339548db_3_fma(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t98, _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s339548db_2_mulAdd(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t85, float _t86, float _t87, float _t91, float _t93, float _t95, float _t98) {
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_s339548db_3_mulAdd(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t98, _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s339548db_3_fma(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t98, float _t99, float _t103, float _t104, float _t105) {
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    dest[destOffset] = java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                dest[destOffset + 2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, otherY - _selfy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(t, otherZ - _selfz, _selfz);
        }
        return dest;
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s339548db_3_mulAdd(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t98, float _t99, float _t103, float _t104, float _t105) {
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 1.4551915E-11f) {
                    dest[destOffset] = ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv;
                    dest[destOffset + 1] = ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv;
                    dest[destOffset + 2] = ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv;
                } else {
                    dest[destOffset] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                    dest[destOffset + 1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                    dest[destOffset + 2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
                }
            } else {
                dest[destOffset] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                dest[destOffset + 1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                dest[destOffset + 2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
            }
        } else {
            dest[destOffset] = ((t) * (otherX - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (otherY - _selfy) + (_selfy));
            dest[destOffset + 2] = ((t) * (otherZ - _selfz) + (_selfz));
        }
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        return slerp_degenerate_s83a862c0_1_fma(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t9, _t10, _t11, 1.0f / _t15, _t24, _t25, (1.0f / (float) java.lang.Math.sqrt(_t24)), _t29 * _t12, _t29 * _t13, _t29 * _t14, t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2));
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        return slerp_degenerate_s83a862c0_1_mulAdd(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t9, _t10, _t11, 1.0f / _t15, _t24, _t25, (1.0f / (float) java.lang.Math.sqrt(_t24)), _t29 * _t12, _t29 * _t13, _t29 * _t14, t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_1_fma(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t9, float _t10, float _t11, float _t15_inv, float _t24, float _t25, float _t28, float _t31, float _t33, float _t35, float _t48) {
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        return slerp_degenerate_s83a862c0_2_fma(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t78, _t74 * _t78, _t75 * _t78, _t76 * _t78);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_1_mulAdd(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t9, float _t10, float _t11, float _t15_inv, float _t24, float _t25, float _t28, float _t31, float _t33, float _t35, float _t48) {
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        return slerp_degenerate_s83a862c0_2_mulAdd(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t78, _t74 * _t78, _t75 * _t78, _t76 * _t78);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_2_fma(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t78, float _t85, float _t86, float _t87) {
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_s83a862c0_3_fma(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_2_mulAdd(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t78, float _t85, float _t86, float _t87) {
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_s83a862c0_3_mulAdd(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_3_fma(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t98, float _t99, float _t103, float _t104, float _t105) {
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    dest[destOffset] = java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                dest[destOffset + 2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, _othery - _selfy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(t, _otherz - _selfz, _selfz);
        }
        return dest;
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_3_mulAdd(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t98, float _t99, float _t103, float _t104, float _t105) {
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 1.4551915E-11f) {
                    dest[destOffset] = ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv;
                    dest[destOffset + 1] = ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv;
                    dest[destOffset + 2] = ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv;
                } else {
                    dest[destOffset] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                    dest[destOffset + 1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                    dest[destOffset + 2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
                }
            } else {
                dest[destOffset] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                dest[destOffset + 1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                dest[destOffset + 2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
            }
        } else {
            dest[destOffset] = ((t) * (_otherx - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (_othery - _selfy) + (_selfy));
            dest[destOffset + 2] = ((t) * (_otherz - _selfz) + (_selfz));
        }
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        return slerp_degenerate_sb843d981_1_fma(dest, destOffset, otherX, otherY, otherZ, t, epsilon, _selfx, _selfy, _selfz, _t9, _t10, _t11, 1.0f / _t15, _t24, _t25, (1.0f / (float) java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        return slerp_degenerate_sb843d981_1_mulAdd(dest, destOffset, otherX, otherY, otherZ, t, epsilon, _selfx, _selfy, _selfz, _t9, _t10, _t11, 1.0f / _t15, _t24, _t25, (1.0f / (float) java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sb843d981_1_fma(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _t9, float _t10, float _t11, float _t15_inv, float _t24, float _t25, float _t28, float _t31, float _t33, float _t35, float _t48, float _t49, float _t50, float _t52) {
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        return slerp_degenerate_sb843d981_2_fma(dest, destOffset, otherX, otherY, otherZ, t, epsilon, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0f / (float) java.lang.Math.sqrt(_t91)), t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sb843d981_1_mulAdd(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _t9, float _t10, float _t11, float _t15_inv, float _t24, float _t25, float _t28, float _t31, float _t33, float _t35, float _t48, float _t49, float _t50, float _t52) {
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        return slerp_degenerate_sb843d981_2_mulAdd(dest, destOffset, otherX, otherY, otherZ, t, epsilon, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0f / (float) java.lang.Math.sqrt(_t91)), t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sb843d981_2_fma(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t85, float _t86, float _t87, float _t91, float _t93, float _t95) {
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_sb843d981_3_fma(dest, destOffset, otherX, otherY, otherZ, t, epsilon, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sb843d981_2_mulAdd(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t85, float _t86, float _t87, float _t91, float _t93, float _t95) {
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_sb843d981_3_mulAdd(dest, destOffset, otherX, otherY, otherZ, t, epsilon, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sb843d981_3_fma(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t98, float _t99, float _t103, float _t104, float _t105) {
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    dest[destOffset] = java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                dest[destOffset + 2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, otherY - _selfy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(t, otherZ - _selfz, _selfz);
        }
        return dest;
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sb843d981_3_mulAdd(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t98, float _t99, float _t103, float _t104, float _t105) {
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    dest[destOffset] = ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv;
                    dest[destOffset + 1] = ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv;
                    dest[destOffset + 2] = ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv;
                } else {
                    dest[destOffset] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                    dest[destOffset + 1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                    dest[destOffset + 2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
                }
            } else {
                dest[destOffset] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                dest[destOffset + 1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                dest[destOffset + 2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
            }
        } else {
            dest[destOffset] = ((t) * (otherX - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (otherY - _selfy) + (_selfy));
            dest[destOffset + 2] = ((t) * (otherZ - _selfz) + (_selfz));
        }
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        return slerp_degenerate_sa94ca514_1_fma(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t9, _t10, _t11, 1.0f / _t15, _t24, _t25, (1.0f / (float) java.lang.Math.sqrt(_t24)), _t29 * _t12, _t29 * _t13, _t29 * _t14, t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2));
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        return slerp_degenerate_sa94ca514_1_mulAdd(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t9, _t10, _t11, 1.0f / _t15, _t24, _t25, (1.0f / (float) java.lang.Math.sqrt(_t24)), _t29 * _t12, _t29 * _t13, _t29 * _t14, t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sa94ca514_1_fma(float[] dest, int destOffset, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t9, float _t10, float _t11, float _t15_inv, float _t24, float _t25, float _t28, float _t31, float _t33, float _t35, float _t48) {
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        return slerp_degenerate_sa94ca514_2_fma(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t78, _t74 * _t78, _t75 * _t78, _t76 * _t78);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sa94ca514_1_mulAdd(float[] dest, int destOffset, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t9, float _t10, float _t11, float _t15_inv, float _t24, float _t25, float _t28, float _t31, float _t33, float _t35, float _t48) {
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        return slerp_degenerate_sa94ca514_2_mulAdd(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t78, _t74 * _t78, _t75 * _t78, _t76 * _t78);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sa94ca514_2_fma(float[] dest, int destOffset, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t78, float _t85, float _t86, float _t87) {
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_sa94ca514_3_fma(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sa94ca514_2_mulAdd(float[] dest, int destOffset, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t78, float _t85, float _t86, float _t87) {
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_sa94ca514_3_mulAdd(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sa94ca514_3_fma(float[] dest, int destOffset, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t98, float _t99, float _t103, float _t104, float _t105) {
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    dest[destOffset] = java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv;
                dest[destOffset + 2] = java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, _othery - _selfy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(t, _otherz - _selfz, _selfz);
        }
        return dest;
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sa94ca514_3_mulAdd(float[] dest, int destOffset, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t98, float _t99, float _t103, float _t104, float _t105) {
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    dest[destOffset] = ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv;
                    dest[destOffset + 1] = ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv;
                    dest[destOffset + 2] = ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv;
                } else {
                    dest[destOffset] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                    dest[destOffset + 1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                    dest[destOffset + 2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
                }
            } else {
                dest[destOffset] = ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv;
                dest[destOffset + 1] = ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv;
                dest[destOffset + 2] = ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv;
            }
        } else {
            dest[destOffset] = ((t) * (_otherx - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (_othery - _selfy) + (_selfy));
            dest[destOffset + 2] = ((t) * (_otherz - _selfz) + (_selfz));
        }
        return dest;
    }

    public static float angleBetween_degenerate(float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_mulAdd(src, srcOffset, otherX, otherY, otherZ);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = unitScale(otherX, otherY, otherZ);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = otherZ * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = otherY * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = otherX * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        float _t29 = unitScale(_t27, _t28, _t26);
        float _t33 = _t26 * _t29;
        float _t34 = _t27 * _t29;
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_mulAdd(float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = unitScale(otherX, otherY, otherZ);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = otherZ * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = otherY * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = otherX * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        float _t29 = unitScale(_t27, _t28, _t26);
        float _t33 = _t26 * _t29;
        float _t34 = _t27 * _t29;
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static float angleBetween_degenerate(float[] src, int srcOffset, float[] other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_mulAdd(src, srcOffset, other, otherOffset);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _t0 = unitScale(_otherx, _othery, _otherz);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = _otherz * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = _othery * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = _otherx * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        float _t29 = unitScale(_t27, _t28, _t26);
        float _t33 = _t26 * _t29;
        float _t34 = _t27 * _t29;
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_mulAdd(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _t0 = unitScale(_otherx, _othery, _otherz);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = _otherz * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = _othery * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = _otherx * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        float _t29 = unitScale(_t27, _t28, _t26);
        float _t33 = _t26 * _t29;
        float _t34 = _t27 * _t29;
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static float orientedAngle_degenerate(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_fma(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_degenerate_mulAdd(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@code orientedAngle_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_fma(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = unitScale(normalX, normalY, normalZ);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherY * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = otherX * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = otherZ * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        float _t21 = -(_t13 * _t10);
        float _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        float _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        float _t33 = unitScale(_t31, _t32, _t30);
        float _t39 = _t30 * _t33;
        float _t40 = _t31 * _t33;
        float _t41 = _t32 * _t33;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(normalZ * _t0, _t39, java.lang.Math.fma(normalX * _t0, _t40, normalY * _t0 * _t41)) < 0.0f ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_mulAdd(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = unitScale(normalX, normalY, normalZ);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherY * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = otherX * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = otherZ * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        float _t21 = -(_t13 * _t10);
        float _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        float _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        float _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        float _t33 = unitScale(_t31, _t32, _t30);
        float _t39 = _t30 * _t33;
        float _t40 = _t31 * _t33;
        float _t41 = _t32 * _t33;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((normalZ * _t0) * (_t39) + (((normalX * _t0) * (_t40) + (normalY * _t0 * _t41)))) < 0.0f ? -_t46 : _t46;
    }

    public static float orientedAngle_degenerate(float[] src, int srcOffset, float[] other, int otherOffset, float[] normal, int normalOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_mulAdd(src, srcOffset, other, otherOffset, normal, normalOffset);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _normalx = normal[normalOffset];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _othery * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = _otherx * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = _otherz * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        float _t21 = -(_t13 * _t10);
        float _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        float _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        float _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_s73e6e621_1_fma(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t33, _t30 * _t33, _t31 * _t33, _t32 * _t33);
    }

    /** {@code orientedAngle_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_mulAdd(float[] src, int srcOffset, float[] other, int otherOffset, float[] normal, int normalOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _normalx = normal[normalOffset];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _othery * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = _otherx * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = _otherz * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        float _t21 = -(_t13 * _t10);
        float _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        float _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        float _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        float _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_s73e6e621_1_mulAdd(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t33, _t30 * _t33, _t31 * _t33, _t32 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_s73e6e621_1_fma(float _normalx, float _normaly, float _normalz, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t33, float _t39, float _t40, float _t41) {
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(_normalz * _t0, _t39, java.lang.Math.fma(_normalx * _t0, _t40, _normaly * _t0 * _t41)) < 0.0f ? -_t46 : _t46;
    }

    /** Piece 2 of {@code orientedAngle_degenerate}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_s73e6e621_1_mulAdd(float _normalx, float _normaly, float _normalz, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t33, float _t39, float _t40, float _t41) {
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((_normalz * _t0) * (_t39) + (((_normalx * _t0) * (_t40) + (_normaly * _t0 * _t41)))) < 0.0f ? -_t46 : _t46;
    }

    public static float[] triangleNormal_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return triangleNormal_degenerate_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@code triangleNormal_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] triangleNormal_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = p1X * _t19 - _t30;
        float _t39 = p1Y * _t19 - _t32;
        float _t40 = p1Z * _t19 - _t34;
        float _t41 = p2Y * _t19 - _t32;
        float _t42 = p2X * _t19 - _t30;
        float _t43 = p2Z * _t19 - _t34;
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        float _t61 = -(_t54 * _t55);
        return triangleNormal_degenerate_sc0703c02_1_fma(dest, destOffset, _t52, _t53, _t54, _t55, _t56, _t57, -(_t57 * _t53), -(_t52 * _t56), java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61));
    }

    /** {@code triangleNormal_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] triangleNormal_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = p1X * _t19 - _t30;
        float _t39 = p1Y * _t19 - _t32;
        float _t40 = p1Z * _t19 - _t34;
        float _t41 = p2Y * _t19 - _t32;
        float _t42 = p2X * _t19 - _t30;
        float _t43 = p2Z * _t19 - _t34;
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        float _t61 = -(_t54 * _t55);
        return triangleNormal_degenerate_sc0703c02_1_mulAdd(dest, destOffset, _t52, _t53, _t54, _t55, _t56, _t57, -(_t57 * _t53), -(_t52 * _t56), ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61)));
    }

    /** Piece 2 of {@code triangleNormal_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] triangleNormal_degenerate_sc0703c02_1_fma(float[] dest, int destOffset, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t62, float _t63, float _t70) {
        float _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        float _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        float _t83 = (1.0f / (float) java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0f) {
            dest[destOffset] = _t83 * _t78;
            dest[destOffset + 1] = _t83 * _t79;
            dest[destOffset + 2] = _t83 * _t77;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** Piece 2 of {@code triangleNormal_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] triangleNormal_degenerate_sc0703c02_1_mulAdd(float[] dest, int destOffset, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t62, float _t63, float _t70) {
        float _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        float _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        float _t83 = (1.0f / (float) java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0f) {
            dest[destOffset] = _t83 * _t78;
            dest[destOffset + 1] = _t83 * _t79;
            dest[destOffset + 2] = _t83 * _t77;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    public static float[] triangleNormal_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return triangleNormal_degenerate_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@code triangleNormal_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] triangleNormal_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _p1x = p1[p1Offset];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p2x = p2[p2Offset];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = _p1x * _t19 - _t30;
        float _t39 = _p1y * _t19 - _t32;
        float _t40 = _p1z * _t19 - _t34;
        float _t41 = _p2y * _t19 - _t32;
        float _t42 = _p2x * _t19 - _t30;
        float _t43 = _p2z * _t19 - _t34;
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        return triangleNormal_degenerate_s1fd73457_1_fma(dest, destOffset, _t38 * _t44, _t41 * _t45, _t54, _t55, _t43 * _t45, _t40 * _t44, -(_t54 * _t55));
    }

    /** {@code triangleNormal_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] triangleNormal_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _p1x = p1[p1Offset];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p2x = p2[p2Offset];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = _p1x * _t19 - _t30;
        float _t39 = _p1y * _t19 - _t32;
        float _t40 = _p1z * _t19 - _t34;
        float _t41 = _p2y * _t19 - _t32;
        float _t42 = _p2x * _t19 - _t30;
        float _t43 = _p2z * _t19 - _t34;
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        return triangleNormal_degenerate_s1fd73457_1_mulAdd(dest, destOffset, _t38 * _t44, _t41 * _t45, _t54, _t55, _t43 * _t45, _t40 * _t44, -(_t54 * _t55));
    }

    /** Piece 2 of {@code triangleNormal_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] triangleNormal_degenerate_s1fd73457_1_fma(float[] dest, int destOffset, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t61) {
        float _t62 = -(_t57 * _t53);
        float _t63 = -(_t52 * _t56);
        float _t70 = java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61);
        float _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        float _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        float _t83 = (1.0f / (float) java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0f) {
            dest[destOffset] = _t83 * _t78;
            dest[destOffset + 1] = _t83 * _t79;
            dest[destOffset + 2] = _t83 * _t77;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** Piece 2 of {@code triangleNormal_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] triangleNormal_degenerate_s1fd73457_1_mulAdd(float[] dest, int destOffset, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t61) {
        float _t62 = -(_t57 * _t53);
        float _t63 = -(_t52 * _t56);
        float _t70 = ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61));
        float _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        float _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        float _t83 = (1.0f / (float) java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0f) {
            dest[destOffset] = _t83 * _t78;
            dest[destOffset + 1] = _t83 * _t79;
            dest[destOffset + 2] = _t83 * _t77;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
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
