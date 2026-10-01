// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float4Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float4Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float4OpsKernelsArray {
    private Float4OpsKernelsArray() {}

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
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
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
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

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
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
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
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

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
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
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
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

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
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
    private static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t, float epsilon) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
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

    public static float angleBetween_degenerate(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_fma(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        float _t34 = -(_t20 * _t17);
        float _t35 = _t20 * -_t19;
        return angleBetween_degenerate_s7acb3cff_1_fma(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33), java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34), java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35));
    }

    /** {@code angleBetween_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_mulAdd(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        float _t34 = -(_t20 * _t17);
        float _t35 = _t20 * -_t19;
        return angleBetween_degenerate_s7acb3cff_1_mulAdd(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)), ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34)), ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35)));
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_s7acb3cff_1_fma(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53) {
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_s7acb3cff_1_mulAdd(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53) {
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static float angleBetween_degenerate(float[] src, int srcOffset, float[] other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_mulAdd(src, srcOffset, other, otherOffset);
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        return angleBetween_degenerate_sbffa6adf_1_fma(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33));
    }

    /** {@code angleBetween_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_mulAdd(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        return angleBetween_degenerate_sbffa6adf_1_mulAdd(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)));
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_sbffa6adf_1_fma(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t34, float _t35, float _t48, float _t49, float _t50, float _t51) {
        float _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        float _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_sbffa6adf_1_mulAdd(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t34, float _t35, float _t48, float _t49, float _t50, float _t51) {
        float _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        float _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
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
