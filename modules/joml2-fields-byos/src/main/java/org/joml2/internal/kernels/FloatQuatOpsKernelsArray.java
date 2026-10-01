// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link FloatQuatOps} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code FloatQuatOps}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class FloatQuatOpsKernelsArray {
    private FloatQuatOpsKernelsArray() {}

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t1 = _selfw + targetW;
        float _t2 = _selfz + targetZ;
        float _t3 = _selfx + targetX;
        float _t4 = _selfy + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        dest[destOffset] = java.lang.Math.fma(_selfx, _t13, targetX * _t14);
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t13, targetY * _t14);
        dest[destOffset + 2] = java.lang.Math.fma(_selfz, _t13, targetZ * _t14);
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t13, targetW * _t14);
        return dest;
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t1 = _selfw + targetW;
        float _t2 = _selfz + targetZ;
        float _t3 = _selfx + targetX;
        float _t4 = _selfy + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        dest[destOffset] = ((_selfx) * (_t13) + (targetX * _t14));
        dest[destOffset + 1] = ((_selfy) * (_t13) + (targetY * _t14));
        dest[destOffset + 2] = ((_selfz) * (_t13) + (targetZ * _t14));
        dest[destOffset + 3] = ((_selfw) * (_t13) + (targetW * _t14));
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _targetx = target[targetOffset];
        float _targety = target[targetOffset + 1];
        float _targetz = target[targetOffset + 2];
        float _targetw = target[targetOffset + 3];
        float _t1 = _selfw + _targetw;
        float _t2 = _selfz + _targetz;
        float _t3 = _selfx + _targetx;
        float _t4 = _selfy + _targety;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        dest[destOffset] = java.lang.Math.fma(_selfx, _t13, _targetx * _t14);
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t13, _targety * _t14);
        dest[destOffset + 2] = java.lang.Math.fma(_selfz, _t13, _targetz * _t14);
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t13, _targetw * _t14);
        return dest;
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _targetx = target[targetOffset];
        float _targety = target[targetOffset + 1];
        float _targetz = target[targetOffset + 2];
        float _targetw = target[targetOffset + 3];
        float _t1 = _selfw + _targetw;
        float _t2 = _selfz + _targetz;
        float _t3 = _selfx + _targetx;
        float _t4 = _selfy + _targety;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        dest[destOffset] = ((_selfx) * (_t13) + (_targetx * _t14));
        dest[destOffset + 1] = ((_selfy) * (_t13) + (_targety * _t14));
        dest[destOffset + 2] = ((_selfz) * (_t13) + (_targetz * _t14));
        dest[destOffset + 3] = ((_selfw) * (_t13) + (_targetw * _t14));
        return dest;
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_fma_sd05d2e07_1(dest, destOffset, src, srcOffset, upX, upY, upZ, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_fma_sd05d2e07_1(float[] dest, int destOffset, float[] src, int srcOffset, float upX, float upY, float upZ, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return lookAlong_lh_s9859a898_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40);
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return lookAlong_lh_s9859a898_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40);
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_s9859a898_1_fma(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42) {
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        return lookAlong_lh_s9859a898_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)));
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_s9859a898_1_mulAdd(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42) {
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return lookAlong_lh_s9859a898_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_s9859a898_2_fma(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2, float _sp0) {
        float _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest[destOffset] = java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120));
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122);
        dest[destOffset + 2] = java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119));
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122);
        return dest;
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_s9859a898_2_mulAdd(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2, float _sp0) {
        float _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest[destOffset] = ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120));
        dest[destOffset + 1] = ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122));
        dest[destOffset + 2] = ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119));
        dest[destOffset + 3] = ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122));
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_fma_s666918b9_1(dest, destOffset, src, srcOffset, upX, upY, upZ, _t11, _t12, _t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_fma_s666918b9_1(float[] dest, int destOffset, float[] src, int srcOffset, float upX, float upY, float upZ, float _t11, float _t12, float _t13, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return lookAlong_rh_sb1cf8892_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t40, _t41, _t42);
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return lookAlong_rh_sb1cf8892_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t40, _t41, _t42);
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_sb1cf8892_1_fma(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42) {
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return lookAlong_rh_sb1cf8892_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_sb1cf8892_1_mulAdd(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42) {
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return lookAlong_rh_sb1cf8892_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_sb1cf8892_2_fma(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2, float _sp0) {
        float _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest[destOffset] = java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122));
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124);
        dest[destOffset + 2] = java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121));
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124);
        return dest;
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_sb1cf8892_2_mulAdd(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2, float _sp0) {
        float _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest[destOffset] = ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122));
        dest[destOffset + 1] = ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124));
        dest[destOffset + 2] = ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121));
        dest[destOffset + 3] = ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124));
        return dest;
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_seca95b12_1_fma(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_seca95b12_1_mulAdd(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_seca95b12_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        return lookAlong_lh_seca95b12_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_seca95b12_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        return lookAlong_lh_seca95b12_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_seca95b12_2_fma(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest[destOffset] = java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120));
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122);
        return lookAlong_lh_seca95b12_3_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_seca95b12_2_mulAdd(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest[destOffset] = ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120));
        dest[destOffset + 1] = ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122));
        return lookAlong_lh_seca95b12_3_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_seca95b12_3_fma(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest[destOffset + 2] = java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119));
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122);
        return dest;
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_seca95b12_3_mulAdd(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest[destOffset + 2] = ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119));
        dest[destOffset + 3] = ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122));
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_s79d54354_1_fma(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_s79d54354_1_mulAdd(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s79d54354_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t46 = 1.0f + _t41;
        return lookAlong_rh_s79d54354_1_fma_sbe99f1ec_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14, _t15, _t40, _t41, _t42, _t43, _t44, -_t42, _t46, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_s79d54354_1_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s79d54354_1_fma_sbe99f1ec_1(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t74, float _t75) {
        return lookAlong_rh_s79d54354_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s79d54354_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        return lookAlong_rh_s79d54354_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_s79d54354_2_fma(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest[destOffset] = java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122));
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124);
        return lookAlong_lh_seca95b12_3_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_s79d54354_2_mulAdd(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest[destOffset] = ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122));
        dest[destOffset + 1] = ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124));
        return lookAlong_lh_seca95b12_3_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_fma_sb533ab1d_1(dest, destOffset, src, srcOffset, upX, upY, upZ, epsilon, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_fma_sb533ab1d_1(float[] dest, int destOffset, float[] src, int srcOffset, float upX, float upY, float upZ, float epsilon, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return lookAlong_lh_s9859a898_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40);
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return lookAlong_lh_s9859a898_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40);
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_fma_sc2039bf_1(dest, destOffset, src, srcOffset, upX, upY, upZ, epsilon, _t11, _t12, _t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_fma_sc2039bf_1(float[] dest, int destOffset, float[] src, int srcOffset, float upX, float upY, float upZ, float epsilon, float _t11, float _t12, float _t13, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return lookAlong_rh_sb1cf8892_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t40, _t41, _t42);
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return lookAlong_rh_sb1cf8892_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t40, _t41, _t42);
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_s19a36b8e_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_s19a36b8e_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s19a36b8e_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        return lookAlong_lh_seca95b12_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s19a36b8e_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        return lookAlong_lh_seca95b12_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_s67750d98_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_s67750d98_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s67750d98_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t46 = 1.0f + _t41;
        return lookAlong_rh_s67750d98_1_fma_s1be2de95_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14, _t15, _t40, _t41, _t42, _t43, _t44, -_t42, _t46, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_s67750d98_1_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s67750d98_1_fma_s1be2de95_1(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t74, float _t75) {
        return lookAlong_rh_s79d54354_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s67750d98_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        return lookAlong_rh_s79d54354_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    public static float[] makeRotationLookAlong_lh(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_fma(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return makeRotationLookAlong_lh_s615e211c_1_fma(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_mulAdd(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return makeRotationLookAlong_lh_s615e211c_1_mulAdd(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s615e211c_1_fma(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49) {
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        return makeRotationLookAlong_lh_s615e211c_2_fma(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s615e211c_1_mulAdd(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49) {
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return makeRotationLookAlong_lh_s615e211c_2_mulAdd(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s615e211c_2_fma(float[] dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest[destOffset] = _sp0 * _t65;
            dest[destOffset + 1] = _sp0 * _t49;
            dest[destOffset + 2] = _sp0 * _t69;
            dest[destOffset + 3] = 0.5f * (float) java.lang.Math.sqrt(_t71);
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest[destOffset] = 0.5f * (float) java.lang.Math.sqrt(_t72);
                dest[destOffset + 1] = _sp3 * _t68;
                dest[destOffset + 2] = _sp3 * _t48;
                dest[destOffset + 3] = _sp3 * _t65;
            } else {
                if (_t60 > _t11) {
                    dest[destOffset] = _sp1 * _t68;
                    dest[destOffset + 1] = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    dest[destOffset + 2] = _sp1 * _t66;
                    dest[destOffset + 3] = _sp1 * _t49;
                } else {
                    dest[destOffset] = _sp2 * _t48;
                    dest[destOffset + 1] = _sp2 * _t66;
                    dest[destOffset + 2] = 0.5f * (float) java.lang.Math.sqrt(_t74);
                    dest[destOffset + 3] = _sp2 * _t69;
                }
            }
        }
        return dest;
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s615e211c_2_mulAdd(float[] dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest[destOffset] = _sp0 * _t65;
            dest[destOffset + 1] = _sp0 * _t49;
            dest[destOffset + 2] = _sp0 * _t69;
            dest[destOffset + 3] = 0.5f * (float) java.lang.Math.sqrt(_t71);
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest[destOffset] = 0.5f * (float) java.lang.Math.sqrt(_t72);
                dest[destOffset + 1] = _sp3 * _t68;
                dest[destOffset + 2] = _sp3 * _t48;
                dest[destOffset + 3] = _sp3 * _t65;
            } else {
                if (_t60 > _t11) {
                    dest[destOffset] = _sp1 * _t68;
                    dest[destOffset + 1] = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    dest[destOffset + 2] = _sp1 * _t66;
                    dest[destOffset + 3] = _sp1 * _t49;
                } else {
                    dest[destOffset] = _sp2 * _t48;
                    dest[destOffset + 1] = _sp2 * _t66;
                    dest[destOffset + 2] = 0.5f * (float) java.lang.Math.sqrt(_t74);
                    dest[destOffset + 3] = _sp2 * _t69;
                }
            }
        }
        return dest;
    }

    public static float[] makeRotationLookAlong_rh(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_fma(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_fma_s395f0d9d_1(dest, destOffset, upX, upY, upZ, _t11, _t12, _t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_fma_s395f0d9d_1(float[] dest, int destOffset, float upX, float upY, float upZ, float _t11, float _t12, float _t13, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeRotationLookAlong_rh_sa38f86b6_1_fma(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12, _t40 + _t12);
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_mulAdd(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeRotationLookAlong_rh_sa38f86b6_1_mulAdd(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12, _t40 + _t12);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_sa38f86b6_1_fma(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50, float _t51) {
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return makeRotationLookAlong_rh_sa38f86b6_2_fma(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_sa38f86b6_1_mulAdd(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50, float _t51) {
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return makeRotationLookAlong_rh_sa38f86b6_2_mulAdd(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_sa38f86b6_2_fma(float[] dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest[destOffset] = _sp0 * _t67;
            dest[destOffset + 1] = _sp0 * _t51;
            dest[destOffset + 2] = _sp0 * _t71;
            dest[destOffset + 3] = 0.5f * (float) java.lang.Math.sqrt(_t73);
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest[destOffset] = 0.5f * (float) java.lang.Math.sqrt(_t74);
                dest[destOffset + 1] = _sp3 * _t70;
                dest[destOffset + 2] = _sp3 * _t50;
                dest[destOffset + 3] = _sp3 * _t67;
            } else {
                if (_t62 > _t15) {
                    dest[destOffset] = _sp1 * _t70;
                    dest[destOffset + 1] = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    dest[destOffset + 2] = _sp1 * _t68;
                    dest[destOffset + 3] = _sp1 * _t51;
                } else {
                    dest[destOffset] = _sp2 * _t50;
                    dest[destOffset + 1] = _sp2 * _t68;
                    dest[destOffset + 2] = 0.5f * (float) java.lang.Math.sqrt(_t76);
                    dest[destOffset + 3] = _sp2 * _t71;
                }
            }
        }
        return dest;
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_sa38f86b6_2_mulAdd(float[] dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest[destOffset] = _sp0 * _t67;
            dest[destOffset + 1] = _sp0 * _t51;
            dest[destOffset + 2] = _sp0 * _t71;
            dest[destOffset + 3] = 0.5f * (float) java.lang.Math.sqrt(_t73);
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest[destOffset] = 0.5f * (float) java.lang.Math.sqrt(_t74);
                dest[destOffset + 1] = _sp3 * _t70;
                dest[destOffset + 2] = _sp3 * _t50;
                dest[destOffset + 3] = _sp3 * _t67;
            } else {
                if (_t62 > _t15) {
                    dest[destOffset] = _sp1 * _t70;
                    dest[destOffset + 1] = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    dest[destOffset + 2] = _sp1 * _t68;
                    dest[destOffset + 3] = _sp1 * _t51;
                } else {
                    dest[destOffset] = _sp2 * _t50;
                    dest[destOffset + 1] = _sp2 * _t68;
                    dest[destOffset + 2] = 0.5f * (float) java.lang.Math.sqrt(_t76);
                    dest[destOffset + 3] = _sp2 * _t71;
                }
            }
        }
        return dest;
    }

    public static float[] makeRotationLookAlong_lh(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_s8bb6db3e_1_fma(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_mulAdd(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return makeRotationLookAlong_lh_s8bb6db3e_1_mulAdd(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s8bb6db3e_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        return makeRotationLookAlong_lh_s8bb6db3e_1_fma_s2107db5e_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, _t41, -_t40, _t43, _t44, java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_s8bb6db3e_1_fma}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s8bb6db3e_1_fma_s2107db5e_1(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t71, float _t73, float _t74) {
        return makeRotationLookAlong_lh_s8bb6db3e_2_fma(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s8bb6db3e_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return makeRotationLookAlong_lh_s8bb6db3e_2_mulAdd(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11)))), _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s8bb6db3e_2_fma(float[] dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest[destOffset] = _sp0 * _t65;
            dest[destOffset + 1] = _sp0 * _t49;
            dest[destOffset + 2] = _sp0 * _t69;
            dest[destOffset + 3] = 0.5f * (float) java.lang.Math.sqrt(_t71);
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest[destOffset] = 0.5f * (float) java.lang.Math.sqrt(_t72);
                dest[destOffset + 1] = _sp3 * _t68;
                dest[destOffset + 2] = _sp3 * _t48;
                dest[destOffset + 3] = _sp3 * _t65;
            } else {
                if (_t60 > _t11) {
                    dest[destOffset] = _sp1 * _t68;
                    dest[destOffset + 1] = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    dest[destOffset + 2] = _sp1 * _t66;
                    dest[destOffset + 3] = _sp1 * _t49;
                } else {
                    dest[destOffset] = _sp2 * _t48;
                    dest[destOffset + 1] = _sp2 * _t66;
                    dest[destOffset + 2] = 0.5f * (float) java.lang.Math.sqrt(_t74);
                    dest[destOffset + 3] = _sp2 * _t69;
                }
            }
        }
        return dest;
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s8bb6db3e_2_mulAdd(float[] dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest[destOffset] = _sp0 * _t65;
            dest[destOffset + 1] = _sp0 * _t49;
            dest[destOffset + 2] = _sp0 * _t69;
            dest[destOffset + 3] = 0.5f * (float) java.lang.Math.sqrt(_t71);
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest[destOffset] = 0.5f * (float) java.lang.Math.sqrt(_t72);
                dest[destOffset + 1] = _sp3 * _t68;
                dest[destOffset + 2] = _sp3 * _t48;
                dest[destOffset + 3] = _sp3 * _t65;
            } else {
                if (_t60 > _t11) {
                    dest[destOffset] = _sp1 * _t68;
                    dest[destOffset + 1] = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    dest[destOffset + 2] = _sp1 * _t66;
                    dest[destOffset + 3] = _sp1 * _t49;
                } else {
                    dest[destOffset] = _sp2 * _t48;
                    dest[destOffset + 1] = _sp2 * _t66;
                    dest[destOffset + 2] = 0.5f * (float) java.lang.Math.sqrt(_t74);
                    dest[destOffset + 3] = _sp2 * _t69;
                }
            }
        }
        return dest;
    }

    public static float[] makeRotationLookAlong_rh(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_s41b44b40_1_fma(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_mulAdd(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return makeRotationLookAlong_rh_s41b44b40_1_mulAdd(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_s41b44b40_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        return makeRotationLookAlong_rh_s41b44b40_2_fma(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_s41b44b40_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        return makeRotationLookAlong_rh_s41b44b40_2_mulAdd(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_s41b44b40_2_fma(float[] dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest[destOffset] = _sp0 * _t67;
            dest[destOffset + 1] = _sp0 * _t51;
            dest[destOffset + 2] = _sp0 * _t71;
            dest[destOffset + 3] = 0.5f * (float) java.lang.Math.sqrt(_t73);
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest[destOffset] = 0.5f * (float) java.lang.Math.sqrt(_t74);
                dest[destOffset + 1] = _sp3 * _t70;
                dest[destOffset + 2] = _sp3 * _t50;
                dest[destOffset + 3] = _sp3 * _t67;
            } else {
                if (_t62 > _t15) {
                    dest[destOffset] = _sp1 * _t70;
                    dest[destOffset + 1] = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    dest[destOffset + 2] = _sp1 * _t68;
                    dest[destOffset + 3] = _sp1 * _t51;
                } else {
                    dest[destOffset] = _sp2 * _t50;
                    dest[destOffset + 1] = _sp2 * _t68;
                    dest[destOffset + 2] = 0.5f * (float) java.lang.Math.sqrt(_t76);
                    dest[destOffset + 3] = _sp2 * _t71;
                }
            }
        }
        return dest;
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_s41b44b40_2_mulAdd(float[] dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest[destOffset] = _sp0 * _t67;
            dest[destOffset + 1] = _sp0 * _t51;
            dest[destOffset + 2] = _sp0 * _t71;
            dest[destOffset + 3] = 0.5f * (float) java.lang.Math.sqrt(_t73);
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest[destOffset] = 0.5f * (float) java.lang.Math.sqrt(_t74);
                dest[destOffset + 1] = _sp3 * _t70;
                dest[destOffset + 2] = _sp3 * _t50;
                dest[destOffset + 3] = _sp3 * _t67;
            } else {
                if (_t62 > _t15) {
                    dest[destOffset] = _sp1 * _t70;
                    dest[destOffset + 1] = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    dest[destOffset + 2] = _sp1 * _t68;
                    dest[destOffset + 3] = _sp1 * _t51;
                } else {
                    dest[destOffset] = _sp2 * _t50;
                    dest[destOffset + 1] = _sp2 * _t68;
                    dest[destOffset + 2] = 0.5f * (float) java.lang.Math.sqrt(_t76);
                    dest[destOffset + 3] = _sp2 * _t71;
                }
            }
        }
        return dest;
    }

    public static float[] makeRotationLookAlong_lh(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_fma(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return makeRotationLookAlong_lh_s615e211c_1_fma(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_mulAdd(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return makeRotationLookAlong_lh_s615e211c_1_mulAdd(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12);
    }

    public static float[] makeRotationLookAlong_rh(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_fma(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_fma_s1fe68003_1(dest, destOffset, upX, upY, upZ, epsilon, _t11, _t12, _t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_fma_s1fe68003_1(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _t11, float _t12, float _t13, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeRotationLookAlong_rh_sa38f86b6_1_fma(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12, _t40 + _t12);
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_mulAdd(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeRotationLookAlong_rh_sa38f86b6_1_mulAdd(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12, _t40 + _t12);
    }

    public static float[] makeRotationLookAlong_lh(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_s2d7b7dca_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_mulAdd(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return makeRotationLookAlong_lh_s2d7b7dca_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s2d7b7dca_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        return makeRotationLookAlong_lh_s2d7b7dca_1_fma_sdb1a81d4_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, _t41, -_t40, _t43, _t44, java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_s2d7b7dca_1_fma}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s2d7b7dca_1_fma_sdb1a81d4_1(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t71, float _t73, float _t74) {
        return makeRotationLookAlong_lh_s8bb6db3e_2_fma(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s2d7b7dca_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return makeRotationLookAlong_lh_s8bb6db3e_2_mulAdd(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11)))), _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    public static float[] makeRotationLookAlong_rh(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_s5700ca94_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_mulAdd(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return makeRotationLookAlong_rh_s5700ca94_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_s5700ca94_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        return makeRotationLookAlong_rh_s41b44b40_2_fma(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_s5700ca94_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        return makeRotationLookAlong_rh_s41b44b40_2_mulAdd(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }
}
