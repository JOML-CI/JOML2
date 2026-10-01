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

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t18 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t19 = Math.fma(_t18, _t12, upX);
        float _t20 = Math.fma(_t18, _t13, upY);
        float _t21 = Math.fma(_t18, _t11, upZ);
        float _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_s39a537f0_1(dest, destOffset, src, srcOffset, upX, upY, upZ, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s39a537f0_1(float[] dest, int destOffset, float[] src, int srcOffset, float upX, float upY, float upZ, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return lookAlong_lh_s9859a898_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40);
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_s9859a898_1(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42) {
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t71 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        return lookAlong_lh_s9859a898_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_s9859a898_2(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2, float _sp0) {
        float _t119, _t120, _t121, _t122;
        if (Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
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
        dest[destOffset] = Math.fma(_selfx, _t121, _selfw * _t119) + Math.fma(_selfy, _t122, -(_selfz * _t120));
        dest[destOffset + 1] = Math.fma(_selfy, _t121, _selfz * _t119) + Math.fma(_selfw, _t120, -_selfx * _t122);
        dest[destOffset + 2] = Math.fma(_selfx, _t120, _selfw * _t122) + Math.fma(_selfz, _t121, -(_selfy * _t119));
        dest[destOffset + 3] = Math.fma(_selfw, _t121, -_selfx * _t119) - Math.fma(_selfy, _t120, _selfz * _t122);
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t20 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t21 = Math.fma(_t20, _t13, upY);
        float _t22 = Math.fma(_t20, _t12, upX);
        float _t23 = Math.fma(_t20, _t11, upZ);
        float _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_s1ba54fd6_1(dest, destOffset, src, srcOffset, upX, upY, upZ, _t11, _t12, _t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s1ba54fd6_1(float[] dest, int destOffset, float[] src, int srcOffset, float upX, float upY, float upZ, float _t11, float _t12, float _t13, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return lookAlong_rh_sb1cf8892_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t40, _t41, _t42);
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_sb1cf8892_1(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42) {
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return lookAlong_rh_sb1cf8892_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t15)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t15)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_sb1cf8892_2(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2, float _sp0) {
        float _t121, _t122, _t123, _t124;
        if (Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
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
        dest[destOffset] = Math.fma(_selfx, _t123, _selfw * _t121) + Math.fma(_selfy, _t124, -(_selfz * _t122));
        dest[destOffset + 1] = Math.fma(_selfy, _t123, _selfz * _t121) + Math.fma(_selfw, _t122, -_selfx * _t124);
        dest[destOffset + 2] = Math.fma(_selfx, _t122, _selfw * _t124) + Math.fma(_selfz, _t123, -(_selfy * _t121));
        dest[destOffset + 3] = Math.fma(_selfw, _t123, -_selfx * _t121) - Math.fma(_selfy, _t122, _selfz * _t124);
        return dest;
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t18 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = Math.fma(_t18, _t12, _upx);
        float _t20 = Math.fma(_t18, _t13, _upy);
        float _t21 = Math.fma(_t18, _t11, _upz);
        float _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_seca95b12_1(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_seca95b12_1(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
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
        float _t72 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        return lookAlong_lh_seca95b12_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_seca95b12_2(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
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
        dest[destOffset] = Math.fma(_selfx, _t121, _selfw * _t119) + Math.fma(_selfy, _t122, -(_selfz * _t120));
        dest[destOffset + 1] = Math.fma(_selfy, _t121, _selfz * _t119) + Math.fma(_selfw, _t120, -_selfx * _t122);
        return lookAlong_lh_seca95b12_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_seca95b12_3(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest[destOffset + 2] = Math.fma(_selfx, _t120, _selfw * _t122) + Math.fma(_selfz, _t121, -(_selfy * _t119));
        dest[destOffset + 3] = Math.fma(_selfw, _t121, -_selfx * _t119) - Math.fma(_selfy, _t120, _selfz * _t122);
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t20 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = Math.fma(_t20, _t13, _upy);
        float _t22 = Math.fma(_t20, _t12, _upx);
        float _t23 = Math.fma(_t20, _t11, _upz);
        float _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_s79d54354_1(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s79d54354_1(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
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
        float _t74 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        return lookAlong_rh_s79d54354_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t15)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t15)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11)), _t74, _t75, Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_s79d54354_2(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
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
        dest[destOffset] = Math.fma(_selfx, _t123, _selfw * _t121) + Math.fma(_selfy, _t124, -(_selfz * _t122));
        dest[destOffset + 1] = Math.fma(_selfy, _t123, _selfz * _t121) + Math.fma(_selfw, _t122, -_selfx * _t124);
        return lookAlong_lh_seca95b12_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t18 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t19 = Math.fma(_t18, _t12, upX);
        float _t20 = Math.fma(_t18, _t13, upY);
        float _t21 = Math.fma(_t18, _t11, upZ);
        float _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_s3fad34cc_1(dest, destOffset, src, srcOffset, upX, upY, upZ, epsilon, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s3fad34cc_1(float[] dest, int destOffset, float[] src, int srcOffset, float upX, float upY, float upZ, float epsilon, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return lookAlong_lh_s9859a898_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40);
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t20 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t21 = Math.fma(_t20, _t13, upY);
        float _t22 = Math.fma(_t20, _t12, upX);
        float _t23 = Math.fma(_t20, _t11, upZ);
        float _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_s193180e_1(dest, destOffset, src, srcOffset, upX, upY, upZ, epsilon, _t11, _t12, _t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s193180e_1(float[] dest, int destOffset, float[] src, int srcOffset, float upX, float upY, float upZ, float epsilon, float _t11, float _t12, float _t13, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return lookAlong_rh_sb1cf8892_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t40, _t41, _t42);
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t18 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = Math.fma(_t18, _t12, _upx);
        float _t20 = Math.fma(_t18, _t13, _upy);
        float _t21 = Math.fma(_t18, _t11, _upz);
        float _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_s19a36b8e_1(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s19a36b8e_1(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
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
        float _t72 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        return lookAlong_lh_seca95b12_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t20 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = Math.fma(_t20, _t13, _upy);
        float _t22 = Math.fma(_t20, _t12, _upx);
        float _t23 = Math.fma(_t20, _t11, _upz);
        float _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_s67750d98_1(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s67750d98_1(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
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
        float _t74 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        return lookAlong_rh_s79d54354_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t15)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t15)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11)), _t74, _t75, Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    public static float[] makeRotationLookAlong_lh(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t18 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t19 = Math.fma(_t18, _t12, upX);
        float _t20 = Math.fma(_t18, _t13, upY);
        float _t21 = Math.fma(_t18, _t11, upZ);
        float _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return makeRotationLookAlong_lh_s615e211c_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s615e211c_1(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49) {
        float _t71 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        return makeRotationLookAlong_lh_s615e211c_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s615e211c_2(float[] dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
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
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t20 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t21 = Math.fma(_t20, _t13, upY);
        float _t22 = Math.fma(_t20, _t12, upX);
        float _t23 = Math.fma(_t20, _t11, upZ);
        float _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_sd62b7f1a_1(dest, destOffset, upX, upY, upZ, _t11, _t12, _t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_sd62b7f1a_1(float[] dest, int destOffset, float upX, float upY, float upZ, float _t11, float _t12, float _t13, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeRotationLookAlong_rh_sa38f86b6_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12, _t40 + _t12);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_sa38f86b6_1(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50, float _t51) {
        float _t73 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return makeRotationLookAlong_rh_sa38f86b6_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t14)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t14)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_sa38f86b6_2(float[] dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
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
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t18 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = Math.fma(_t18, _t12, _upx);
        float _t20 = Math.fma(_t18, _t13, _upy);
        float _t21 = Math.fma(_t18, _t11, _upz);
        float _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_s8bb6db3e_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s8bb6db3e_1(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
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
        return makeRotationLookAlong_lh_s8bb6db3e_1_sda96f273_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, _t41, -_t40, _t43, _t44, Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11)), Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0f - _t38 - _t11)), Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0f + _t11 - _t38)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_s8bb6db3e_1}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s8bb6db3e_1_sda96f273_1(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t71, float _t73, float _t74) {
        return makeRotationLookAlong_lh_s8bb6db3e_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), _t71, Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11)), _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s8bb6db3e_2(float[] dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
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
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t20 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = Math.fma(_t20, _t13, _upy);
        float _t22 = Math.fma(_t20, _t12, _upx);
        float _t23 = Math.fma(_t20, _t11, _upz);
        float _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_s41b44b40_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_s41b44b40_1(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
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
        float _t73 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11));
        float _t75 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        return makeRotationLookAlong_rh_s41b44b40_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t14)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t14)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), _t73, Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11)), _t75, Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_s41b44b40_2(float[] dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
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
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t18 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t19 = Math.fma(_t18, _t12, upX);
        float _t20 = Math.fma(_t18, _t13, upY);
        float _t21 = Math.fma(_t18, _t11, upZ);
        float _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return makeRotationLookAlong_lh_s615e211c_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12);
    }

    public static float[] makeRotationLookAlong_rh(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t20 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t21 = Math.fma(_t20, _t13, upY);
        float _t22 = Math.fma(_t20, _t12, upX);
        float _t23 = Math.fma(_t20, _t11, upZ);
        float _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_se03c20c2_1(dest, destOffset, upX, upY, upZ, epsilon, _t11, _t12, _t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_se03c20c2_1(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _t11, float _t12, float _t13, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeRotationLookAlong_rh_sa38f86b6_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12, _t40 + _t12);
    }

    public static float[] makeRotationLookAlong_lh(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t18 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = Math.fma(_t18, _t12, _upx);
        float _t20 = Math.fma(_t18, _t13, _upy);
        float _t21 = Math.fma(_t18, _t11, _upz);
        float _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_s2d7b7dca_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s2d7b7dca_1(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
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
        return makeRotationLookAlong_lh_s2d7b7dca_1_s762ef3d7_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, _t41, -_t40, _t43, _t44, Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11)), Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0f - _t38 - _t11)), Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0f + _t11 - _t38)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_s2d7b7dca_1}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s2d7b7dca_1_s762ef3d7_1(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t71, float _t73, float _t74) {
        return makeRotationLookAlong_lh_s8bb6db3e_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), _t71, Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11)), _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    public static float[] makeRotationLookAlong_rh(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t20 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = Math.fma(_t20, _t13, _upy);
        float _t22 = Math.fma(_t20, _t12, _upx);
        float _t23 = Math.fma(_t20, _t11, _upz);
        float _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_s5700ca94_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_s5700ca94_1(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
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
        float _t73 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11));
        float _t75 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        return makeRotationLookAlong_rh_s41b44b40_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t14)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t14)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), _t73, Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11)), _t75, Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }
}
