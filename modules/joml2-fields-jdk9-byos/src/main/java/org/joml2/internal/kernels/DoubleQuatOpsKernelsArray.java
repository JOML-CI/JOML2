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

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t1 = _selfw + targetW;
        double _t2 = _selfz + targetZ;
        double _t3 = _selfx + targetX;
        double _t4 = _selfy + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        dest[destOffset] = java.lang.Math.fma(_selfx, _t13, targetX * _t14);
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t13, targetY * _t14);
        dest[destOffset + 2] = java.lang.Math.fma(_selfz, _t13, targetZ * _t14);
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t13, targetW * _t14);
        return dest;
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t1 = _selfw + targetW;
        double _t2 = _selfz + targetZ;
        double _t3 = _selfx + targetX;
        double _t4 = _selfy + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        dest[destOffset] = ((_selfx) * (_t13) + (targetX * _t14));
        dest[destOffset + 1] = ((_selfy) * (_t13) + (targetY * _t14));
        dest[destOffset + 2] = ((_selfz) * (_t13) + (targetZ * _t14));
        dest[destOffset + 3] = ((_selfw) * (_t13) + (targetW * _t14));
        return dest;
    }

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _targetx = target[targetOffset];
        double _targety = target[targetOffset + 1];
        double _targetz = target[targetOffset + 2];
        double _targetw = target[targetOffset + 3];
        double _t1 = _selfw + _targetw;
        double _t2 = _selfz + _targetz;
        double _t3 = _selfx + _targetx;
        double _t4 = _selfy + _targety;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        dest[destOffset] = java.lang.Math.fma(_selfx, _t13, _targetx * _t14);
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t13, _targety * _t14);
        dest[destOffset + 2] = java.lang.Math.fma(_selfz, _t13, _targetz * _t14);
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t13, _targetw * _t14);
        return dest;
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _targetx = target[targetOffset];
        double _targety = target[targetOffset + 1];
        double _targetz = target[targetOffset + 2];
        double _targetw = target[targetOffset + 3];
        double _t1 = _selfw + _targetw;
        double _t2 = _selfz + _targetz;
        double _t3 = _selfx + _targetx;
        double _t4 = _selfy + _targety;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        dest[destOffset] = ((_selfx) * (_t13) + (_targetx * _t14));
        dest[destOffset + 1] = ((_selfy) * (_t13) + (_targety * _t14));
        dest[destOffset + 2] = ((_selfz) * (_t13) + (_targetz * _t14));
        dest[destOffset + 3] = ((_selfw) * (_t13) + (_targetw * _t14));
        return dest;
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return lookAlong_lh_s62f659ca_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39);
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return lookAlong_lh_s62f659ca_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_s62f659ca_1_fma(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41) {
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        double _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        return lookAlong_lh_s62f659ca_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_s62f659ca_1_mulAdd(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41) {
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return lookAlong_lh_s62f659ca_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_lh_s62f659ca_2_fma(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1, double _sp2, double _sp0) {
        double _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
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
    private static double[] lookAlong_lh_s62f659ca_2_mulAdd(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1, double _sp2, double _sp0) {
        double _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
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
        dest[destOffset] = ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120));
        dest[destOffset + 1] = ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122));
        dest[destOffset + 2] = ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119));
        dest[destOffset + 3] = ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122));
        return dest;
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_s90611efc_1_fma(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_s90611efc_1_mulAdd(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s90611efc_1_fma(double[] dest, int destOffset, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
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
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        return lookAlong_rh_s90611efc_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s90611efc_1_mulAdd(double[] dest, int destOffset, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
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
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        return lookAlong_rh_s90611efc_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s90611efc_2_fma(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
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
        dest[destOffset] = java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122));
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124);
        return lookAlong_rh_s90611efc_3_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s90611efc_2_mulAdd(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
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
        dest[destOffset] = ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122));
        dest[destOffset + 1] = ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124));
        return lookAlong_rh_s90611efc_3_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 5 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s90611efc_3_fma(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest[destOffset + 2] = java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121));
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124);
        return dest;
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 5 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s90611efc_3_mulAdd(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest[destOffset + 2] = ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121));
        dest[destOffset + 3] = ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124));
        return dest;
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_sf22bfa3e_1_fma(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_sf22bfa3e_1_mulAdd(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_sf22bfa3e_1_fma(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
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
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return lookAlong_lh_sf22bfa3e_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_sf22bfa3e_1_mulAdd(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
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
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        return lookAlong_lh_sf22bfa3e_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_lh_sf22bfa3e_2_fma(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
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
        dest[destOffset] = java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120));
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122);
        return lookAlong_rh_s90611efc_3_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_lh_sf22bfa3e_2_mulAdd(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
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
        dest[destOffset] = ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120));
        dest[destOffset + 1] = ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122));
        return lookAlong_rh_s90611efc_3_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_s90611efc_1_fma(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_s90611efc_1_mulAdd(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return lookAlong_lh_sad3c4201_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40);
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return lookAlong_lh_sad3c4201_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_sad3c4201_1_fma(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42) {
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        double _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        return lookAlong_lh_s62f659ca_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_sad3c4201_1_mulAdd(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42) {
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return lookAlong_lh_s62f659ca_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)));
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return lookAlong_rh_s63fca90f_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t40, _t41, _t42);
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return lookAlong_rh_s63fca90f_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t40, _t41, _t42);
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_s63fca90f_1_fma(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42) {
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        double _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        return lookAlong_rh_s63fca90f_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_s63fca90f_1_mulAdd(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42) {
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return lookAlong_rh_s63fca90f_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_s63fca90f_2_fma(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3, double _sp1, double _sp2, double _sp0) {
        double _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
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
        dest[destOffset] = java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122));
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124);
        dest[destOffset + 2] = java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121));
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124);
        return dest;
    }

    /** Piece 3 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_s63fca90f_2_mulAdd(double[] dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3, double _sp1, double _sp2, double _sp0) {
        double _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
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
        dest[destOffset] = ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122));
        dest[destOffset + 1] = ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124));
        dest[destOffset + 2] = ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121));
        dest[destOffset + 3] = ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124));
        return dest;
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_s94d34645_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_s94d34645_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_s94d34645_1_fma(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
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
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return lookAlong_lh_sf22bfa3e_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_s94d34645_1_mulAdd(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
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
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        return lookAlong_lh_sf22bfa3e_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_sa2728713_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lookAlong_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_sa2728713_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_sa2728713_1_fma(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
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
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        return lookAlong_rh_s90611efc_2_fma(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_sa2728713_1_mulAdd(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
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
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        return lookAlong_rh_s90611efc_2_mulAdd(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    public static double[] makeRotationLookAlong_lh(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_lh_fma(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_s4a191ef7_1_fma(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_lh_mulAdd(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_s4a191ef7_1_mulAdd(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_lh_s4a191ef7_1_fma(double[] dest, int destOffset, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49) {
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        double _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        return makeRotationLookAlong_lh_s4a191ef7_2_fma(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_lh_s4a191ef7_1_mulAdd(double[] dest, int destOffset, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49) {
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return makeRotationLookAlong_lh_s4a191ef7_2_mulAdd(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_lh_s4a191ef7_2_fma(double[] dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
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

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_lh_s4a191ef7_2_mulAdd(double[] dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_rh_fma(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_s4129997d_1_fma(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12);
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_rh_mulAdd(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_s4129997d_1_mulAdd(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_rh_s4129997d_1_fma(double[] dest, int destOffset, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50) {
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        double _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        return makeRotationLookAlong_rh_s4129997d_2_fma(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_rh_s4129997d_1_mulAdd(double[] dest, int destOffset, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50) {
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return makeRotationLookAlong_rh_s4129997d_2_mulAdd(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_rh_s4129997d_2_fma(double[] dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
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

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_rh_s4129997d_2_mulAdd(double[] dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_s3b595b05_1_fma(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_lh_mulAdd(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return makeRotationLookAlong_lh_s3b595b05_1_mulAdd(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_lh_s3b595b05_1_fma(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
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
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return makeRotationLookAlong_lh_s3b595b05_2_fma(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_lh_s3b595b05_1_mulAdd(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
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
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        return makeRotationLookAlong_lh_s3b595b05_2_mulAdd(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11)))), _t73, ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_lh_s3b595b05_2_fma(double[] dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
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

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_lh_s3b595b05_2_mulAdd(double[] dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_s1e83df63_1_fma(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_rh_mulAdd(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return makeRotationLookAlong_rh_s1e83df63_1_mulAdd(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_rh_s1e83df63_1_fma(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
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
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        return makeRotationLookAlong_rh_s1e83df63_2_fma(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_rh_s1e83df63_1_mulAdd(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
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
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        return makeRotationLookAlong_rh_s1e83df63_2_mulAdd(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_rh_s1e83df63_2_fma(double[] dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
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

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_rh_s1e83df63_2_mulAdd(double[] dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_lh_fma(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_s4a191ef7_1_fma(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_lh_mulAdd(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_s4a191ef7_1_mulAdd(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12);
    }

    public static double[] makeRotationLookAlong_rh(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_rh_fma(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_s4129997d_1_fma(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12);
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_rh_mulAdd(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_s4129997d_1_mulAdd(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12);
    }

    public static double[] makeRotationLookAlong_lh(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_s24ef9376_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_lh_mulAdd(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return makeRotationLookAlong_lh_s24ef9376_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_lh_s24ef9376_1_fma(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
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
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return makeRotationLookAlong_lh_s3b595b05_2_fma(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_lh_s24ef9376_1_mulAdd(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
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
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        return makeRotationLookAlong_lh_s3b595b05_2_mulAdd(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11)))), _t73, ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    public static double[] makeRotationLookAlong_rh(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_scad1a1b0_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] makeRotationLookAlong_rh_mulAdd(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return makeRotationLookAlong_rh_scad1a1b0_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_rh_scad1a1b0_1_fma(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
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
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        return makeRotationLookAlong_rh_s1e83df63_2_fma(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_rh_scad1a1b0_1_mulAdd(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
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
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        return makeRotationLookAlong_rh_s1e83df63_2_mulAdd(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }
}
