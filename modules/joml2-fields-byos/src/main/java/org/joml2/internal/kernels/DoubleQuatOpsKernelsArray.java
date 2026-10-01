// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link DoubleQuatOps} whose leading storage
 * parameter is a {@code double[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code DoubleQuatOps}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class DoubleQuatOpsKernelsArray {
    private DoubleQuatOpsKernelsArray() {}

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t19 = Math.fma(_t18, _t12, upX);
        double _t20 = Math.fma(_t18, _t13, upY);
        double _t21 = Math.fma(_t18, _t11, upZ);
        double _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return lookAlong_lh_s62f659ca_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_s62f659ca_1(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41) {
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11));
        double _t72 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        double _t74 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        return lookAlong_lh_s62f659ca_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_lh_s62f659ca_2(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1, double _sp2, double _sp0) {
        double _t119, _t120, _t121, _t122;
        if (Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest[destOffset] = Math.fma(_selfx, _t121, _selfw * _t119) + Math.fma(_selfy, _t122, -(_selfz * _t120));
        dest[destOffset + 1] = Math.fma(_selfy, _t121, _selfz * _t119) + Math.fma(_selfw, _t120, -_selfx * _t122);
        dest[destOffset + 2] = Math.fma(_selfx, _t120, _selfw * _t122) + Math.fma(_selfz, _t121, -(_selfy * _t119));
        dest[destOffset + 3] = Math.fma(_selfw, _t121, -_selfx * _t119) - Math.fma(_selfy, _t120, _selfz * _t122);
        return dest;
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t21 = Math.fma(_t20, _t13, upY);
        double _t22 = Math.fma(_t20, _t12, upX);
        double _t23 = Math.fma(_t20, _t11, upZ);
        double _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_s90611efc_1(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s90611efc_1(double[] dest, int destOffset, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11));
        return lookAlong_rh_s90611efc_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t15)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t15)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11)), _t74, Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0 - _t41 + _t11)), Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s90611efc_2(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest[destOffset] = Math.fma(_selfx, _t123, _selfw * _t121) + Math.fma(_selfy, _t124, -(_selfz * _t122));
        dest[destOffset + 1] = Math.fma(_selfy, _t123, _selfz * _t121) + Math.fma(_selfw, _t122, -_selfx * _t124);
        return lookAlong_rh_s90611efc_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 5 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s90611efc_3(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest[destOffset + 2] = Math.fma(_selfx, _t122, _selfw * _t124) + Math.fma(_selfz, _t123, -(_selfy * _t121));
        dest[destOffset + 3] = Math.fma(_selfw, _t123, -_selfx * _t121) - Math.fma(_selfy, _t122, _selfz * _t124);
        return dest;
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = Math.fma(_t18, _t12, _upx);
        double _t20 = Math.fma(_t18, _t13, _upy);
        double _t21 = Math.fma(_t18, _t11, _upz);
        double _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_sf22bfa3e_1(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_sf22bfa3e_1(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return lookAlong_lh_sf22bfa3e_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_lh_sf22bfa3e_2(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest[destOffset] = Math.fma(_selfx, _t121, _selfw * _t119) + Math.fma(_selfy, _t122, -(_selfz * _t120));
        dest[destOffset + 1] = Math.fma(_selfy, _t121, _selfz * _t119) + Math.fma(_selfw, _t120, -_selfx * _t122);
        return lookAlong_rh_s90611efc_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = Math.fma(_t20, _t13, _upy);
        double _t22 = Math.fma(_t20, _t12, _upx);
        double _t23 = Math.fma(_t20, _t11, _upz);
        double _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_s90611efc_1(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t19 = Math.fma(_t18, _t12, upX);
        double _t20 = Math.fma(_t18, _t13, upY);
        double _t21 = Math.fma(_t18, _t11, upZ);
        double _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return lookAlong_lh_sad3c4201_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_sad3c4201_1(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42) {
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11));
        double _t72 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        double _t74 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        return lookAlong_lh_s62f659ca_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)));
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t21 = Math.fma(_t20, _t13, upY);
        double _t22 = Math.fma(_t20, _t12, upX);
        double _t23 = Math.fma(_t20, _t11, upZ);
        double _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return lookAlong_rh_s63fca90f_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t40, _t41, _t42);
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_s63fca90f_1(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42) {
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11));
        double _t74 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        double _t76 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        return lookAlong_rh_s63fca90f_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t15)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t15)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_s63fca90f_2(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3, double _sp1, double _sp2, double _sp0) {
        double _t121, _t122, _t123, _t124;
        if (Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest[destOffset] = Math.fma(_selfx, _t123, _selfw * _t121) + Math.fma(_selfy, _t124, -(_selfz * _t122));
        dest[destOffset + 1] = Math.fma(_selfy, _t123, _selfz * _t121) + Math.fma(_selfw, _t122, -_selfx * _t124);
        dest[destOffset + 2] = Math.fma(_selfx, _t122, _selfw * _t124) + Math.fma(_selfz, _t123, -(_selfy * _t121));
        dest[destOffset + 3] = Math.fma(_selfw, _t123, -_selfx * _t121) - Math.fma(_selfy, _t122, _selfz * _t124);
        return dest;
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = Math.fma(_t18, _t12, _upx);
        double _t20 = Math.fma(_t18, _t13, _upy);
        double _t21 = Math.fma(_t18, _t11, _upz);
        double _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_s94d34645_1(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_s94d34645_1(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return lookAlong_lh_sf22bfa3e_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = Math.fma(_t20, _t13, _upy);
        double _t22 = Math.fma(_t20, _t12, _upx);
        double _t23 = Math.fma(_t20, _t11, _upz);
        double _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_sa2728713_1(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_sa2728713_1(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11));
        return lookAlong_rh_s90611efc_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t15)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t15)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11)), _t74, Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0 - _t41 + _t11)), Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    public static double[] makeRotationLookAlong_lh(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t19 = Math.fma(_t18, _t12, upX);
        double _t20 = Math.fma(_t18, _t13, upY);
        double _t21 = Math.fma(_t18, _t11, upZ);
        double _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_s4a191ef7_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_lh_s4a191ef7_1(double[] dest, int destOffset, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49) {
        double _t71 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11));
        double _t72 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        double _t74 = Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        return makeRotationLookAlong_lh_s4a191ef7_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_lh_s4a191ef7_2(double[] dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            dest[destOffset] = _sp0 * _t65;
            dest[destOffset + 1] = _sp0 * _t49;
            dest[destOffset + 2] = _sp0 * _t69;
            dest[destOffset + 3] = 0.5 * java.lang.Math.sqrt(_t71);
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest[destOffset] = 0.5 * java.lang.Math.sqrt(_t72);
                dest[destOffset + 1] = _sp3 * _t68;
                dest[destOffset + 2] = _sp3 * _t48;
                dest[destOffset + 3] = _sp3 * _t65;
            } else {
                if (_t60 > _t11) {
                    dest[destOffset] = _sp1 * _t68;
                    dest[destOffset + 1] = 0.5 * java.lang.Math.sqrt(_t73);
                    dest[destOffset + 2] = _sp1 * _t66;
                    dest[destOffset + 3] = _sp1 * _t49;
                } else {
                    dest[destOffset] = _sp2 * _t48;
                    dest[destOffset + 1] = _sp2 * _t66;
                    dest[destOffset + 2] = 0.5 * java.lang.Math.sqrt(_t74);
                    dest[destOffset + 3] = _sp2 * _t69;
                }
            }
        }
        return dest;
    }

    public static double[] makeRotationLookAlong_rh(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t21 = Math.fma(_t20, _t13, upY);
        double _t22 = Math.fma(_t20, _t12, upX);
        double _t23 = Math.fma(_t20, _t11, upZ);
        double _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_s4129997d_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_rh_s4129997d_1(double[] dest, int destOffset, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50) {
        double _t73 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11));
        double _t74 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        double _t76 = Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        return makeRotationLookAlong_rh_s4129997d_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t14)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t14)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_rh_s4129997d_2(double[] dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            dest[destOffset] = _sp0 * _t67;
            dest[destOffset + 1] = _sp0 * _t51;
            dest[destOffset + 2] = _sp0 * _t71;
            dest[destOffset + 3] = 0.5 * java.lang.Math.sqrt(_t73);
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest[destOffset] = 0.5 * java.lang.Math.sqrt(_t74);
                dest[destOffset + 1] = _sp3 * _t70;
                dest[destOffset + 2] = _sp3 * _t50;
                dest[destOffset + 3] = _sp3 * _t67;
            } else {
                if (_t62 > _t15) {
                    dest[destOffset] = _sp1 * _t70;
                    dest[destOffset + 1] = 0.5 * java.lang.Math.sqrt(_t75);
                    dest[destOffset + 2] = _sp1 * _t68;
                    dest[destOffset + 3] = _sp1 * _t51;
                } else {
                    dest[destOffset] = _sp2 * _t50;
                    dest[destOffset + 1] = _sp2 * _t68;
                    dest[destOffset + 2] = 0.5 * java.lang.Math.sqrt(_t76);
                    dest[destOffset + 3] = _sp2 * _t71;
                }
            }
        }
        return dest;
    }

    public static double[] makeRotationLookAlong_lh(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = Math.fma(_t18, _t12, _upx);
        double _t20 = Math.fma(_t18, _t13, _upy);
        double _t21 = Math.fma(_t18, _t11, _upz);
        double _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_s3b595b05_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_lh_s3b595b05_1(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11));
        double _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return makeRotationLookAlong_lh_s3b595b05_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), _t71, Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11)), _t73, Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_lh_s3b595b05_2(double[] dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        if (Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            dest[destOffset] = _sp0 * _t65;
            dest[destOffset + 1] = _sp0 * _t49;
            dest[destOffset + 2] = _sp0 * _t69;
            dest[destOffset + 3] = 0.5 * java.lang.Math.sqrt(_t71);
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest[destOffset] = 0.5 * java.lang.Math.sqrt(_t72);
                dest[destOffset + 1] = _sp3 * _t68;
                dest[destOffset + 2] = _sp3 * _t48;
                dest[destOffset + 3] = _sp3 * _t65;
            } else {
                if (_t60 > _t11) {
                    dest[destOffset] = _sp1 * _t68;
                    dest[destOffset + 1] = 0.5 * java.lang.Math.sqrt(_t73);
                    dest[destOffset + 2] = _sp1 * _t66;
                    dest[destOffset + 3] = _sp1 * _t49;
                } else {
                    dest[destOffset] = _sp2 * _t48;
                    dest[destOffset + 1] = _sp2 * _t66;
                    dest[destOffset + 2] = 0.5 * java.lang.Math.sqrt(_t74);
                    dest[destOffset + 3] = _sp2 * _t69;
                }
            }
        }
        return dest;
    }

    public static double[] makeRotationLookAlong_rh(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = Math.fma(_t20, _t13, _upy);
        double _t22 = Math.fma(_t20, _t12, _upx);
        double _t23 = Math.fma(_t20, _t11, _upz);
        double _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_s1e83df63_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_rh_s1e83df63_1(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11));
        double _t75 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        return makeRotationLookAlong_rh_s1e83df63_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t14)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t14)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), _t73, Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11)), _t75, Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_rh_s1e83df63_2(double[] dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        if (Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            dest[destOffset] = _sp0 * _t67;
            dest[destOffset + 1] = _sp0 * _t51;
            dest[destOffset + 2] = _sp0 * _t71;
            dest[destOffset + 3] = 0.5 * java.lang.Math.sqrt(_t73);
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest[destOffset] = 0.5 * java.lang.Math.sqrt(_t74);
                dest[destOffset + 1] = _sp3 * _t70;
                dest[destOffset + 2] = _sp3 * _t50;
                dest[destOffset + 3] = _sp3 * _t67;
            } else {
                if (_t62 > _t15) {
                    dest[destOffset] = _sp1 * _t70;
                    dest[destOffset + 1] = 0.5 * java.lang.Math.sqrt(_t75);
                    dest[destOffset + 2] = _sp1 * _t68;
                    dest[destOffset + 3] = _sp1 * _t51;
                } else {
                    dest[destOffset] = _sp2 * _t50;
                    dest[destOffset + 1] = _sp2 * _t68;
                    dest[destOffset + 2] = 0.5 * java.lang.Math.sqrt(_t76);
                    dest[destOffset + 3] = _sp2 * _t71;
                }
            }
        }
        return dest;
    }

    public static double[] makeRotationLookAlong_lh(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t19 = Math.fma(_t18, _t12, upX);
        double _t20 = Math.fma(_t18, _t13, upY);
        double _t21 = Math.fma(_t18, _t11, upZ);
        double _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_s4a191ef7_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12);
    }

    public static double[] makeRotationLookAlong_rh(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t21 = Math.fma(_t20, _t13, upY);
        double _t22 = Math.fma(_t20, _t12, upX);
        double _t23 = Math.fma(_t20, _t11, upZ);
        double _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_s4129997d_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12);
    }

    public static double[] makeRotationLookAlong_lh(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = Math.fma(_t18, _t12, _upx);
        double _t20 = Math.fma(_t18, _t13, _upy);
        double _t21 = Math.fma(_t18, _t11, _upz);
        double _t28 = Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = Math.fma(_t28, _t28, Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_s24ef9376_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_lh_s24ef9376_1(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, _t44 + _t11));
        double _t73 = Math.fma(_t38, _t11, Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return makeRotationLookAlong_lh_s3b595b05_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, Math.fma(_t38, _t11, -(_t39 * _t12)), Math.fma(_t42, _t12, Math.fma(_t38, _t13, _t13)), Math.fma(_t40, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t40)), Math.fma(_t39, _t13, Math.fma(_t42, _t11, _t42)), _t71, Math.fma(_t43, _t11, Math.fma(_t39, _t12, _t44 - _t11)), _t73, Math.fma(_t43, _t11, Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    public static double[] makeRotationLookAlong_rh(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = Math.fma(_t20, _t13, _upy);
        double _t22 = Math.fma(_t20, _t12, _upx);
        double _t23 = Math.fma(_t20, _t11, _upz);
        double _t30 = Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_scad1a1b0_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_rh_scad1a1b0_1(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, _t46 - _t11));
        double _t75 = Math.fma(_t40, _t12, Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        return makeRotationLookAlong_rh_s1e83df63_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, Math.fma(_t40, _t12, _t41 * -_t11), Math.fma(_t43, _t13, Math.fma(_t42, _t12, _t14)), Math.fma(_t41, _t13, Math.fma(_t45, _t12, _t14)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t42)), Math.fma(_t42, _t11, Math.fma(_t44, _t13, _t45)), _t73, Math.fma(_t44, _t12, Math.fma(_t41, _t11, _t46 + _t11)), _t75, Math.fma(_t44, _t12, Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }
}
