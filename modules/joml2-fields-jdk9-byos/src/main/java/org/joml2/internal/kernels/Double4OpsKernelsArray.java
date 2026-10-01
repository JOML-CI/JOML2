// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double4Ops} whose leading storage
 * parameter is a {@code double[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double4Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double4OpsKernelsArray {
    private Double4OpsKernelsArray() {}

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    dest[destOffset] = java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv;
                    dest[destOffset + 3] = java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv;
                    dest[destOffset + 3] = java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv;
                dest[destOffset + 2] = java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv;
                dest[destOffset + 3] = java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, otherY - _selfy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(t, otherZ - _selfz, _selfz);
            dest[destOffset + 3] = java.lang.Math.fma(t, otherW - _selfw, _selfw);
        }
        return dest;
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 5.048709793414476E-29) {
                    dest[destOffset] = ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv;
                    dest[destOffset + 1] = ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv;
                    dest[destOffset + 2] = ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv;
                    dest[destOffset + 3] = ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv;
                } else {
                    dest[destOffset] = ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv;
                    dest[destOffset + 1] = ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv;
                    dest[destOffset + 2] = ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv;
                    dest[destOffset + 3] = ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv;
                }
            } else {
                dest[destOffset] = ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv;
                dest[destOffset + 1] = ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv;
                dest[destOffset + 2] = ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv;
                dest[destOffset + 3] = ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv;
            }
        } else {
            dest[destOffset] = ((t) * (otherX - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (otherY - _selfy) + (_selfy));
            dest[destOffset + 2] = ((t) * (otherZ - _selfz) + (_selfz));
            dest[destOffset + 3] = ((t) * (otherW - _selfw) + (_selfw));
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
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    dest[destOffset] = java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv;
                    dest[destOffset + 3] = java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv;
                    dest[destOffset + 3] = java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv;
                dest[destOffset + 2] = java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv;
                dest[destOffset + 3] = java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, _othery - _selfy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(t, _otherz - _selfz, _selfz);
            dest[destOffset + 3] = java.lang.Math.fma(t, _otherw - _selfw, _selfw);
        }
        return dest;
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 5.048709793414476E-29) {
                    dest[destOffset] = ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv;
                    dest[destOffset + 1] = ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv;
                    dest[destOffset + 2] = ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv;
                    dest[destOffset + 3] = ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv;
                } else {
                    dest[destOffset] = ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv;
                    dest[destOffset + 1] = ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv;
                    dest[destOffset + 2] = ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv;
                    dest[destOffset + 3] = ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv;
                }
            } else {
                dest[destOffset] = ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv;
                dest[destOffset + 1] = ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv;
                dest[destOffset + 2] = ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv;
                dest[destOffset + 3] = ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv;
            }
        } else {
            dest[destOffset] = ((t) * (_otherx - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (_othery - _selfy) + (_selfy));
            dest[destOffset + 2] = ((t) * (_otherz - _selfz) + (_selfz));
            dest[destOffset + 3] = ((t) * (_otherw - _selfw) + (_selfw));
        }
        return dest;
    }

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    dest[destOffset] = java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv;
                    dest[destOffset + 3] = java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv;
                    dest[destOffset + 3] = java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv;
                dest[destOffset + 2] = java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv;
                dest[destOffset + 3] = java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, otherY - _selfy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(t, otherZ - _selfz, _selfz);
            dest[destOffset + 3] = java.lang.Math.fma(t, otherW - _selfw, _selfw);
        }
        return dest;
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    dest[destOffset] = ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv;
                    dest[destOffset + 1] = ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv;
                    dest[destOffset + 2] = ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv;
                    dest[destOffset + 3] = ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv;
                } else {
                    dest[destOffset] = ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv;
                    dest[destOffset + 1] = ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv;
                    dest[destOffset + 2] = ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv;
                    dest[destOffset + 3] = ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv;
                }
            } else {
                dest[destOffset] = ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv;
                dest[destOffset + 1] = ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv;
                dest[destOffset + 2] = ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv;
                dest[destOffset + 3] = ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv;
            }
        } else {
            dest[destOffset] = ((t) * (otherX - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (otherY - _selfy) + (_selfy));
            dest[destOffset + 2] = ((t) * (otherZ - _selfz) + (_selfz));
            dest[destOffset + 3] = ((t) * (otherW - _selfw) + (_selfw));
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
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    dest[destOffset] = java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv;
                    dest[destOffset + 3] = java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv;
                } else {
                    dest[destOffset] = java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv;
                    dest[destOffset + 1] = java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv;
                    dest[destOffset + 2] = java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv;
                    dest[destOffset + 3] = java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv;
                }
            } else {
                dest[destOffset] = java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv;
                dest[destOffset + 1] = java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv;
                dest[destOffset + 2] = java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv;
                dest[destOffset + 3] = java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv;
            }
        } else {
            dest[destOffset] = java.lang.Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, _othery - _selfy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(t, _otherz - _selfz, _selfz);
            dest[destOffset + 3] = java.lang.Math.fma(t, _otherw - _selfw, _selfw);
        }
        return dest;
    }

    /** {@code slerp_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t, double epsilon) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    dest[destOffset] = ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv;
                    dest[destOffset + 1] = ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv;
                    dest[destOffset + 2] = ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv;
                    dest[destOffset + 3] = ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv;
                } else {
                    dest[destOffset] = ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv;
                    dest[destOffset + 1] = ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv;
                    dest[destOffset + 2] = ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv;
                    dest[destOffset + 3] = ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv;
                }
            } else {
                dest[destOffset] = ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv;
                dest[destOffset + 1] = ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv;
                dest[destOffset + 2] = ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv;
                dest[destOffset + 3] = ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv;
            }
        } else {
            dest[destOffset] = ((t) * (_otherx - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (_othery - _selfy) + (_selfy));
            dest[destOffset + 2] = ((t) * (_otherz - _selfz) + (_selfz));
            dest[destOffset + 3] = ((t) * (_otherw - _selfw) + (_selfw));
        }
        return dest;
    }

    public static double angleBetween_degenerate(double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_fma(double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = otherW * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = otherY * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = otherX * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        double _t31 = -_t22 * _t23;
        double _t32 = -_t22 * _t17;
        double _t33 = _t22 * -_t19;
        double _t34 = -(_t20 * _t17);
        double _t35 = _t20 * -_t19;
        return angleBetween_degenerate_s2a94c890_1_fma(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33), java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34), java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35));
    }

    /** {@code angleBetween_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_mulAdd(double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = otherW * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = otherY * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = otherX * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        double _t31 = -_t22 * _t23;
        double _t32 = -_t22 * _t17;
        double _t33 = _t22 * -_t19;
        double _t34 = -(_t20 * _t17);
        double _t35 = _t20 * -_t19;
        return angleBetween_degenerate_s2a94c890_1_mulAdd(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)), ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34)), ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35)));
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_s2a94c890_1_fma(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t48, double _t49, double _t50, double _t51, double _t52, double _t53) {
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_s2a94c890_1_mulAdd(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t48, double _t49, double _t50, double _t51, double _t52, double _t53) {
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static double angleBetween_degenerate(double[] src, int srcOffset, double[] other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_mulAdd(src, srcOffset, other, otherOffset);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        double _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = _otherw * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = _otherz * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = _othery * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = _otherx * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        double _t31 = -_t22 * _t23;
        double _t32 = -_t22 * _t17;
        double _t33 = _t22 * -_t19;
        return angleBetween_degenerate_sc249d2ad_1_fma(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33));
    }

    /** {@code angleBetween_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_mulAdd(double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        double _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = _otherw * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = _otherz * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = _othery * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = _otherx * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        double _t31 = -_t22 * _t23;
        double _t32 = -_t22 * _t17;
        double _t33 = _t22 * -_t19;
        return angleBetween_degenerate_sc249d2ad_1_mulAdd(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)));
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_sc249d2ad_1_fma(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t34, double _t35, double _t48, double _t49, double _t50, double _t51) {
        double _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        double _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_sc249d2ad_1_mulAdd(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t34, double _t35, double _t48, double _t49, double _t50, double _t51) {
        double _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        double _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
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
