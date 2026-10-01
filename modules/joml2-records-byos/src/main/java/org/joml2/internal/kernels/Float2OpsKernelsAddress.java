// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float2Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float2OpsKernelsAddress {
    private Float2OpsKernelsAddress() {}

    public static long add_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, otherX + _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, otherY + _selfy);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _otherx + _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _othery + _selfy);
        return dest;
    }

    public static long div_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx / scalar);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / scalar);
        return dest;
    }

    public static long div_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx / otherX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / otherY);
        return dest;
    }

    public static long div_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx / _otherx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / _othery);
        return dest;
    }

    public static long fma_unsafe(long dest, long src, float b, float cX, float cY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, b, cX));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, b, cY));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (b) + (cX)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (b) + (cY)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, long c, float b) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _cx = UnsafeOpsHolder.U.getFloat(c);
            float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, b, _cx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, b, _cy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _cx = UnsafeOpsHolder.U.getFloat(c);
            float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (b) + (_cx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (b) + (_cy)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, float bX, float bY, float cX, float cY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, bX, cX));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, bY, cY));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (bX) + (cX)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (bY) + (cY)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, long b, long c) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _bx = UnsafeOpsHolder.U.getFloat(b);
            float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
            float _cx = UnsafeOpsHolder.U.getFloat(c);
            float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _bx, _cx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _by, _cy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _bx = UnsafeOpsHolder.U.getFloat(b);
            float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
            float _cx = UnsafeOpsHolder.U.getFloat(c);
            float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_bx) + (_cx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_by) + (_cy)));
            return dest;
        }
    }

    public static long mul_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, scalar * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, scalar * _selfy);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, otherX * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, otherY * _selfy);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _otherx * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _othery * _selfy);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, -_selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfy);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx - otherX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy - otherY);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx - _otherx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy - _othery);
        return dest;
    }

    public static long makeUniformDirection_unsafe(long dest, float u) {
        float _t0 = u * 6.2831855f;
        UnsafeOpsHolder.U.putFloat(dest, Math.cosWithSin(_t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.sinWithCos(_t0));
        return dest;
    }

    public static long set_unsafe(long dest, float vX, float vY) {
        UnsafeOpsHolder.U.putFloat(dest, vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, vY);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _vy);
        return dest;
    }

    public static long set_unsafe(long dest, float s) {
        UnsafeOpsHolder.U.putFloat(dest, s);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s);
        return dest;
    }

    public static long makeZero_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t0 = 1.0f - t;
            float _t1 = t * t;
            float _t2 = t * _t1;
            float _t3 = _t0 * _t0;
            float _t6 = 3.0f * _t0 * _t1;
            float _t7 = 3.0f * t * _t3;
            float _t8 = _t0 * _t3;
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p1X, _t7, _selfx * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p1Y, _t7, _selfy * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t0 = 1.0f - t;
            float _t1 = t * t;
            float _t2 = t * _t1;
            float _t3 = _t0 * _t0;
            float _t6 = 3.0f * _t0 * _t1;
            float _t7 = 3.0f * t * _t3;
            float _t8 = _t0 * _t3;
            UnsafeOpsHolder.U.putFloat(dest, ((p1X) * (_t7) + (_selfx * _t8)) + ((p2X) * (_t6) + (p3X * _t2)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((p1Y) * (_t7) + (_selfy * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2)));
            return dest;
        }
    }

    public static long bezier_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_unsafe_mulAdd(dest, src, p1, p2, p3, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p1x, _t7, _selfx * _t8) + java.lang.Math.fma(_p2x, _t6, _p3x * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        return dest;
    }

    /** {@code bezier_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, ((_p1x) * (_t7) + (_selfx * _t8)) + ((_p2x) * (_t6) + (_p3x * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, _selfx * _t4)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            UnsafeOpsHolder.U.putFloat(dest, ((p2X) * (_t0) + (((p1X) * (_t3) + (_selfx * _t4)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
            return dest;
        }
    }

    public static long bezier2_unsafe(long dest, long src, long p1, long p2, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_unsafe_mulAdd(dest, src, p1, p2, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p2x, _t0, java.lang.Math.fma(_p1x, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        return dest;
    }

    /** {@code bezier2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2_unsafe_mulAdd(long dest, long src, long p1, long p2, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, ((_p2x) * (_t0) + (((_p1x) * (_t3) + (_selfx * _t4)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            UnsafeOpsHolder.U.putFloat(dest, ((p1X - _selfx) * (_t2) + ((p2X - p1X) * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
            return dest;
        }
    }

    public static long bezier2Tangent_unsafe(long dest, long src, long p1, long p2, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _p1x = UnsafeOpsHolder.U.getFloat(p1);
            float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
            float _p2x = UnsafeOpsHolder.U.getFloat(p2);
            float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _p1x = UnsafeOpsHolder.U.getFloat(p1);
            float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
            float _p2x = UnsafeOpsHolder.U.getFloat(p2);
            float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            UnsafeOpsHolder.U.putFloat(dest, ((_p1x - _selfx) * (_t2) + ((_p2x - _p1x) * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
            return dest;
        }
    }

    public static long bezierTangent_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = 1.0f - t;
            float _t2 = 3.0f * t * t;
            float _t5 = 6.0f * t * _t1;
            float _t6 = 3.0f * _t1 * _t1;
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = 1.0f - t;
            float _t2 = 3.0f * t * t;
            float _t5 = 6.0f * t * _t1;
            float _t6 = 3.0f * _t1 * _t1;
            UnsafeOpsHolder.U.putFloat(dest, ((p3X - p2X) * (_t2) + (((p1X - _selfx) * (_t6) + ((p2X - p1X) * _t5)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
            return dest;
        }
    }

    public static long bezierTangent_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_unsafe_mulAdd(dest, src, p1, p2, p3, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p3x - _p2x, _t2, java.lang.Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, ((_p3x - _p2x) * (_t2) + (((_p1x - _selfx) * (_t6) + ((_p2x - _p1x) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_unsafe_mulAdd(dest, src, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (((2.0f) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) * (_t0) + (((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X)) * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) * (_t0) + (((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y)) * _t1))));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_unsafe_mulAdd(dest, src, p1, p2, p3, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (java.lang.Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (java.lang.Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (((2.0f) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) * (_t0) + (((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x)) * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (((2.0f) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) * (_t0) + (((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y)) * _t1))));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_unsafe_mulAdd(dest, src, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return dest;
    }

    /** {@code catmullRomTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) + (((3.0f * ((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X))) * (_t0) + (p2X - _selfx)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) + (((3.0f * ((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_unsafe_mulAdd(dest, src, p1, p2, p3, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        return dest;
    }

    /** {@code catmullRomTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) + (((3.0f * ((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) + (((3.0f * ((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_unsafe_mulAdd(dest, src, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        return dest;
    }

    /** {@code hermite_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_mulAdd(long dest, long src, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, long t0, long v1, long t1, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_unsafe_mulAdd(dest, src, t0, v1, t1, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t10, _t0x * _t7) + java.lang.Math.fma(_t1x, _t5, _v1x * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        return dest;
    }

    /** {@code hermite_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_mulAdd(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t10) + (_t0x * _t7)) + ((_t1x) * (_t5) + (_v1x * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_unsafe_mulAdd(dest, src, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        return dest;
    }

    /** {@code hermiteTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd(long dest, long src, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, long t0, long v1, long t1, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_unsafe_mulAdd(dest, src, t0, v1, t1, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t6, _t0x * _t9) + java.lang.Math.fma(_t1x, _t8, _v1x * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        return dest;
    }

    /** {@code hermiteTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t6) + (_t0x * _t9)) + ((_t1x) * (_t8) + (_v1x * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, float otherX, float otherY, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (_othery - _selfy) + (_selfy)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, float otherX, float otherY, float tX, float tY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((tX) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((tY) * (otherY - _selfy) + (_selfy)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, long other, long t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
            float _tx = UnsafeOpsHolder.U.getFloat(t);
            float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_tx, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
            float _tx = UnsafeOpsHolder.U.getFloat(t);
            float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((_tx) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_ty) * (_othery - _selfy) + (_selfy)));
            return dest;
        }
    }

    public static long slerp_unsafe(long dest, long src, float otherX, float otherY, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, otherX, otherY, t);
        return slerp_unsafe_mulAdd(dest, src, otherX, otherY, t);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, float otherX, float otherY, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t5 = java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        float _t6 = java.lang.Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = java.lang.Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        float _t26 = java.lang.Math.fma(otherX, _t10, -(_t21 * _t12));
        float _t27 = java.lang.Math.fma(otherY, _t10, -(_t21 * _t16));
        float _t30 = -java.lang.Math.fma(_t26, _t12, _t27 * _t16);
        float _t31 = java.lang.Math.fma(_t30, _t12, _t26);
        float _t32 = java.lang.Math.fma(_t30, _t16, _t27);
        return slerp_unsafe_fma_s92299ada_1(dest, src, otherX, otherY, t, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, _t31, _t32, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s92299ada_1(long dest, long src, float otherX, float otherY, float t, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32, float _t35) {
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _t42 = _t20 * Math.cosWithSin(_t39);
        float _sp0 = _t20 * Math.sinWithCos(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t12, _t42, _sp0 * _t31));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t16, _t42, _sp0 * _t32));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t5 = ((_selfx) * (_selfx) + (_selfy * _selfy));
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        float _t6 = ((otherX) * (otherX) + (otherY * otherY));
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = ((otherX * _t10) * (_t12) + (otherY * _t10 * _t16));
        float _t26 = ((otherX) * (_t10) - (_t21 * _t12));
        float _t27 = ((otherY) * (_t10) - (_t21 * _t16));
        float _t30 = -((_t26) * (_t12) + (_t27 * _t16));
        float _t31 = ((_t30) * (_t12) + (_t26));
        float _t32 = ((_t30) * (_t16) + (_t27));
        float _t35 = ((_t31) * (_t31) + (_t32 * _t32));
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        return slerp_unsafe_mulAdd_sb19de4e3_1(dest, t, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, _t31, _t32, _t35);
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_sb19de4e3_1(long dest, float t, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32, float _t35) {
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _t42 = _t20 * Math.cosWithSin(_t39);
        float _sp0 = _t20 * Math.sinWithCos(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        UnsafeOpsHolder.U.putFloat(dest, ((_t12) * (_t42) + (_sp0 * _t31)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t16) * (_t42) + (_sp0 * _t32)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, float otherX, float otherY, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, t);
        Float2OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 8L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, otherX, otherY, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, float otherX, float otherY, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, otherX, otherY, t);
        return slerp_degenerate_unsafe_mulAdd(dest, src, otherX, otherY, t);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, float otherX, float otherY, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
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
        return slerp_degenerate_unsafe_fma_sf84d1c89_1(dest, otherX, otherY, t, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, java.lang.Math.fma(_t47, _t25, _t43), java.lang.Math.fma(_t47, _t27, _t44));
    }

    /** Piece 2 of {@code slerp_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_fma_sf84d1c89_1(long dest, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49) {
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
        return slerp_degenerate_unsafe_fma_sf84d1c89_2(dest, otherX, otherY, t, _selfx, _selfy, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sinWithCos(_t64), _t37 * Math.cosWithSin(_t64), _t71, _t72);
    }

    /** Piece 3 of {@code slerp_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_fma_sf84d1c89_2(long dest, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t67, float _t68, float _t71, float _t72) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (java.lang.Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t28, _t68 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t25, _t68 * _t27) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
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
        return slerp_degenerate_unsafe_mulAdd_s633739c6_1(dest, otherX, otherY, t, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, unitScale(_t48, _t49, _t48));
    }

    /** Piece 2 of {@code slerp_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd_s633739c6_1(long dest, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49, float _t51) {
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = ((_t57) * (_t57) + (_t58 * _t58));
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
        return slerp_degenerate_unsafe_mulAdd_s633739c6_2(dest, otherX, otherY, t, _selfx, _selfy, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sinWithCos(_t64), _t37 * Math.cosWithSin(_t64), _t71, _t72);
    }

    /** Piece 3 of {@code slerp_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd_s633739c6_2(long dest, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t67, float _t68, float _t71, float _t72) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (((_t48) * (_t48) + (_t49 * _t49)) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t28) + (_t68 * _t25)) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t25) + (_t68 * _t27)) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (otherY - _selfy) + (_selfy)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long other, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, other, t);
        return slerp_unsafe_mulAdd(dest, src, other, t);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t5 = java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t6 = java.lang.Math.fma(_otherx, _otherx, _othery * _othery);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = java.lang.Math.fma(_otherx * _t10, _t12, _othery * _t10 * _t16);
        float _t26 = java.lang.Math.fma(_otherx, _t10, -(_t21 * _t12));
        float _t27 = java.lang.Math.fma(_othery, _t10, -(_t21 * _t16));
        float _t30 = -java.lang.Math.fma(_t26, _t12, _t27 * _t16);
        float _t31 = java.lang.Math.fma(_t30, _t12, _t26);
        float _t32 = java.lang.Math.fma(_t30, _t16, _t27);
        return slerp_unsafe_fma_sff32df1b_1(dest, src, other, t, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, _t31, _t32, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sff32df1b_1(long dest, long src, long other, float t, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32, float _t35) {
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _t42 = _t20 * Math.cosWithSin(_t39);
        float _sp0 = _t20 * Math.sinWithCos(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t12, _t42, _sp0 * _t31));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t16, _t42, _sp0 * _t32));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t5 = ((_selfx) * (_selfx) + (_selfy * _selfy));
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t6 = ((_otherx) * (_otherx) + (_othery * _othery));
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = ((_otherx * _t10) * (_t12) + (_othery * _t10 * _t16));
        float _t26 = ((_otherx) * (_t10) - (_t21 * _t12));
        float _t27 = ((_othery) * (_t10) - (_t21 * _t16));
        float _t30 = -((_t26) * (_t12) + (_t27 * _t16));
        float _t31 = ((_t30) * (_t12) + (_t26));
        float _t32 = ((_t30) * (_t16) + (_t27));
        return slerp_unsafe_mulAdd_s4d4fe30a_1(dest, src, other, t, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, _t31, _t32, ((_t31) * (_t31) + (_t32 * _t32)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s4d4fe30a_1(long dest, long src, long other, float t, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32, float _t35) {
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _t42 = _t20 * Math.cosWithSin(_t39);
        float _sp0 = _t20 * Math.sinWithCos(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        UnsafeOpsHolder.U.putFloat(dest, ((_t12) * (_t42) + (_sp0 * _t31)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t16) * (_t42) + (_sp0 * _t32)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t);
        Float2OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 8L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 8L), 0L, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long other, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, other, t);
        return slerp_degenerate_unsafe_mulAdd(dest, src, other, t);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
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
        return slerp_degenerate_unsafe_fma_s8cb4a61c_1(dest, t, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t43, _t44, -java.lang.Math.fma(_t43, _t25, _t44 * _t27));
    }

    /** Piece 2 of {@code slerp_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_fma_s8cb4a61c_1(long dest, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t43, float _t44, float _t47) {
        float _t48 = java.lang.Math.fma(_t47, _t25, _t43);
        float _t49 = java.lang.Math.fma(_t47, _t27, _t44);
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
        return slerp_degenerate_unsafe_fma_s8cb4a61c_2(dest, t, _selfx, _selfy, _otherx, _othery, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sinWithCos(_t64), _t37 * Math.cosWithSin(_t64), _t71, _t72);
    }

    /** Piece 3 of {@code slerp_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_fma_s8cb4a61c_2(long dest, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t67, float _t68, float _t71, float _t72) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (java.lang.Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t28, _t68 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t25, _t68 * _t27) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
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
        return slerp_degenerate_unsafe_mulAdd_s9e0080d7_1(dest, t, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t44, _t47, ((_t47) * (_t25) + (_t43)));
    }

    /** Piece 2 of {@code slerp_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd_s9e0080d7_1(long dest, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t44, float _t47, float _t48) {
        float _t49 = ((_t47) * (_t27) + (_t44));
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = ((_t57) * (_t57) + (_t58 * _t58));
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
        return slerp_degenerate_unsafe_mulAdd_s9e0080d7_2(dest, t, _selfx, _selfy, _otherx, _othery, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sinWithCos(_t64), _t37 * Math.cosWithSin(_t64), _t71, _t72);
    }

    /** Piece 3 of {@code slerp_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd_s9e0080d7_2(long dest, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t67, float _t68, float _t71, float _t72) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (((_t48) * (_t48) + (_t49 * _t49)) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t28) + (_t68 * _t25)) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t25) + (_t68 * _t27)) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (_othery - _selfy) + (_selfy)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, float otherX, float otherY, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, otherX, otherY, t, epsilon);
        return slerp_unsafe_mulAdd(dest, src, otherX, otherY, t, epsilon);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, float otherX, float otherY, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t5 = java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t, epsilon);
        float _t6 = java.lang.Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t, epsilon);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = java.lang.Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        float _t26 = java.lang.Math.fma(otherX, _t10, -(_t21 * _t12));
        float _t27 = java.lang.Math.fma(otherY, _t10, -(_t21 * _t16));
        float _t30 = -java.lang.Math.fma(_t26, _t12, _t27 * _t16);
        float _t31 = java.lang.Math.fma(_t30, _t12, _t26);
        float _t32 = java.lang.Math.fma(_t30, _t16, _t27);
        return slerp_unsafe_fma_sb77487d6_1(dest, src, otherX, otherY, t, epsilon, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, _t31, _t32, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sb77487d6_1(long dest, long src, float otherX, float otherY, float t, float epsilon, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32, float _t35) {
        if (!(_t35 > epsilon && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t, epsilon);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _t42 = _t20 * Math.cosWithSin(_t39);
        float _sp0 = _t20 * Math.sinWithCos(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t12, _t42, _sp0 * _t31));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t16, _t42, _sp0 * _t32));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t5 = ((_selfx) * (_selfx) + (_selfy * _selfy));
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t, epsilon);
        float _t6 = ((otherX) * (otherX) + (otherY * otherY));
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t, epsilon);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = ((otherX * _t10) * (_t12) + (otherY * _t10 * _t16));
        float _t26 = ((otherX) * (_t10) - (_t21 * _t12));
        float _t27 = ((otherY) * (_t10) - (_t21 * _t16));
        float _t30 = -((_t26) * (_t12) + (_t27 * _t16));
        float _t31 = ((_t30) * (_t12) + (_t26));
        float _t32 = ((_t30) * (_t16) + (_t27));
        return slerp_unsafe_mulAdd_sdf301b09_1(dest, src, otherX, otherY, t, epsilon, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, _t31, _t32, ((_t31) * (_t31) + (_t32 * _t32)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_sdf301b09_1(long dest, long src, float otherX, float otherY, float t, float epsilon, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32, float _t35) {
        if (!(_t35 > epsilon && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t, epsilon);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _t42 = _t20 * Math.cosWithSin(_t39);
        float _sp0 = _t20 * Math.sinWithCos(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        UnsafeOpsHolder.U.putFloat(dest, ((_t12) * (_t42) + (_sp0 * _t31)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t16) * (_t42) + (_sp0 * _t32)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, float otherX, float otherY, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, t, epsilon);
        Float2OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 8L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, otherX, otherY, t, epsilon);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, float otherX, float otherY, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, otherX, otherY, t, epsilon);
        return slerp_degenerate_unsafe_mulAdd(dest, src, otherX, otherY, t, epsilon);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, float otherX, float otherY, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
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
        return slerp_degenerate_unsafe_fma_s109f918f_1(dest, otherX, otherY, t, epsilon, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, java.lang.Math.fma(_t47, _t25, _t43), java.lang.Math.fma(_t47, _t27, _t44));
    }

    /** Piece 2 of {@code slerp_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_fma_s109f918f_1(long dest, float otherX, float otherY, float t, float epsilon, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49) {
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
        return slerp_degenerate_unsafe_fma_s109f918f_2(dest, otherX, otherY, t, epsilon, _selfx, _selfy, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sinWithCos(_t64), _t37 * Math.cosWithSin(_t64), _t71, _t72);
    }

    /** Piece 3 of {@code slerp_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_fma_s109f918f_2(long dest, float otherX, float otherY, float t, float epsilon, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t67, float _t68, float _t71, float _t72) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (java.lang.Math.fma(_t48, _t48, _t49 * _t49) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t28, _t68 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t25, _t68 * _t27) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
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
        return slerp_degenerate_unsafe_mulAdd_sba656612_1(dest, otherX, otherY, t, epsilon, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, unitScale(_t48, _t49, _t48));
    }

    /** Piece 2 of {@code slerp_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd_sba656612_1(long dest, float otherX, float otherY, float t, float epsilon, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49, float _t51) {
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = ((_t57) * (_t57) + (_t58 * _t58));
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
        return slerp_degenerate_unsafe_mulAdd_sba656612_2(dest, otherX, otherY, t, epsilon, _selfx, _selfy, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sinWithCos(_t64), _t37 * Math.cosWithSin(_t64), _t71, _t72);
    }

    /** Piece 3 of {@code slerp_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd_sba656612_2(long dest, float otherX, float otherY, float t, float epsilon, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t67, float _t68, float _t71, float _t72) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (((_t48) * (_t48) + (_t49 * _t49)) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t28) + (_t68 * _t25)) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t25) + (_t68 * _t27)) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (otherY - _selfy) + (_selfy)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long other, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, other, t, epsilon);
        return slerp_unsafe_mulAdd(dest, src, other, t, epsilon);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, long other, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t5 = java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t6 = java.lang.Math.fma(_otherx, _otherx, _othery * _othery);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = java.lang.Math.fma(_otherx * _t10, _t12, _othery * _t10 * _t16);
        float _t26 = java.lang.Math.fma(_otherx, _t10, -(_t21 * _t12));
        float _t27 = java.lang.Math.fma(_othery, _t10, -(_t21 * _t16));
        float _t30 = -java.lang.Math.fma(_t26, _t12, _t27 * _t16);
        return slerp_unsafe_fma_s306ec141_1(dest, src, other, t, epsilon, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, java.lang.Math.fma(_t30, _t12, _t26), java.lang.Math.fma(_t30, _t16, _t27));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s306ec141_1(long dest, long src, long other, float t, float epsilon, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32) {
        float _t35 = java.lang.Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > epsilon && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _t42 = _t20 * Math.cosWithSin(_t39);
        float _sp0 = _t20 * Math.sinWithCos(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t12, _t42, _sp0 * _t31));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t16, _t42, _sp0 * _t32));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long other, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t5 = ((_selfx) * (_selfx) + (_selfy * _selfy));
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t6 = ((_otherx) * (_otherx) + (_othery * _othery));
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = ((_otherx * _t10) * (_t12) + (_othery * _t10 * _t16));
        float _t26 = ((_otherx) * (_t10) - (_t21 * _t12));
        float _t27 = ((_othery) * (_t10) - (_t21 * _t16));
        float _t30 = -((_t26) * (_t12) + (_t27 * _t16));
        float _t31 = ((_t30) * (_t12) + (_t26));
        float _t32 = ((_t30) * (_t16) + (_t27));
        return slerp_unsafe_mulAdd_s152962c6_1(dest, src, other, t, epsilon, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, _t31, _t32, ((_t31) * (_t31) + (_t32 * _t32)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s152962c6_1(long dest, long src, long other, float t, float epsilon, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32, float _t35) {
        if (!(_t35 > epsilon && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _t42 = _t20 * Math.cosWithSin(_t39);
        float _sp0 = _t20 * Math.sinWithCos(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        UnsafeOpsHolder.U.putFloat(dest, ((_t12) * (_t42) + (_sp0 * _t31)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t16) * (_t42) + (_sp0 * _t32)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t, epsilon);
        Float2OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 8L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 8L), 0L, t, epsilon);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long other, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, other, t, epsilon);
        return slerp_degenerate_unsafe_mulAdd(dest, src, other, t, epsilon);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, long other, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
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
        return slerp_degenerate_unsafe_fma_s72552f20_1(dest, t, epsilon, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t43, _t44, -java.lang.Math.fma(_t43, _t25, _t44 * _t27));
    }

    /** Piece 2 of {@code slerp_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_fma_s72552f20_1(long dest, float t, float epsilon, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t43, float _t44, float _t47) {
        float _t48 = java.lang.Math.fma(_t47, _t25, _t43);
        float _t49 = java.lang.Math.fma(_t47, _t27, _t44);
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
        return slerp_degenerate_unsafe_fma_s72552f20_2(dest, t, epsilon, _selfx, _selfy, _otherx, _othery, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sinWithCos(_t64), _t37 * Math.cosWithSin(_t64), _t71, _t72);
    }

    /** Piece 3 of {@code slerp_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_fma_s72552f20_2(long dest, float t, float epsilon, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t67, float _t68, float _t71, float _t72) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (java.lang.Math.fma(_t48, _t48, _t49 * _t49) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t28, _t68 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t25, _t68 * _t27) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t67, _t72, _t68 * _t25) * _t11_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t67, _t71, _t68 * _t27) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long other, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
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
        return slerp_degenerate_unsafe_mulAdd_sf6f0bead_1(dest, t, epsilon, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t44, _t47, ((_t47) * (_t25) + (_t43)));
    }

    /** Piece 2 of {@code slerp_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd_sf6f0bead_1(long dest, float t, float epsilon, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t44, float _t47, float _t48) {
        float _t49 = ((_t47) * (_t27) + (_t44));
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = ((_t57) * (_t57) + (_t58 * _t58));
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
        return slerp_degenerate_unsafe_mulAdd_sf6f0bead_2(dest, t, epsilon, _selfx, _selfy, _otherx, _othery, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sinWithCos(_t64), _t37 * Math.cosWithSin(_t64), _t71, _t72);
    }

    /** Piece 3 of {@code slerp_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd_sf6f0bead_2(long dest, float t, float epsilon, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t67, float _t68, float _t71, float _t72) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (((_t48) * (_t48) + (_t49 * _t49)) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t28) + (_t68 * _t25)) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t25) + (_t68 * _t27)) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t67) * (_t72) + (_t68 * _t25)) * _t11_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t67) * (_t71) + (_t68 * _t27)) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (_othery - _selfy) + (_selfy)));
        }
        return dest;
    }

    public static long absolute_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.abs(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.abs(_selfy));
        return dest;
    }

    public static long acos_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.acos(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.acos(_selfy));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, float bX, float bY, float scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(scalar, bX, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(scalar, bY, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((scalar) * (bX) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((scalar) * (bY) + (_selfy)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long b, float scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _bx = UnsafeOpsHolder.U.getFloat(b);
            float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(scalar, _bx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(scalar, _by, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _bx = UnsafeOpsHolder.U.getFloat(b);
            float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((scalar) * (_bx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((scalar) * (_by) + (_selfy)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, float bX, float bY, float cX, float cY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(bX, cX, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(bY, cY, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((bX) * (cX) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((bY) * (cY) + (_selfy)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long b, long c) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _bx = UnsafeOpsHolder.U.getFloat(b);
            float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
            float _cx = UnsafeOpsHolder.U.getFloat(c);
            float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_bx, _cx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_by, _cy, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _bx = UnsafeOpsHolder.U.getFloat(b);
            float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
            float _cx = UnsafeOpsHolder.U.getFloat(c);
            float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
            UnsafeOpsHolder.U.putFloat(dest, ((_bx) * (_cx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_by) * (_cy) + (_selfy)));
            return dest;
        }
    }

    public static float angleBetween_unsafe(long src, float otherX, float otherY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = otherY * _selfy;
            float _t2 = -(otherX * _selfy);
            float _ct0 = java.lang.Math.abs(java.lang.Math.fma(otherY, _selfx, _t2) - java.lang.Math.fma(otherX, _selfy, _t2));
            if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY);
            return Math.atan2(_ct0, java.lang.Math.fma(otherX, _selfx, _t1) + java.lang.Math.fma(otherY, _selfy, -_t1));
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = otherY * _selfy;
            float _t2 = -(otherX * _selfy);
            float _ct0 = java.lang.Math.abs(((otherY) * (_selfx) + (_t2)) - ((otherX) * (_selfy) + (_t2)));
            if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY);
            return Math.atan2(_ct0, ((otherX) * (_selfx) + (_t1)) + ((otherY) * (_selfy) - (_t1)));
        }
    }

    public static float angleBetween_degenerate(long src, float otherX, float otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.angleBetween_degenerate_unsafe(src, otherX, otherY);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && VirtualMemoryHolder.virtualMemory().asSlice(src, 8L).isNative()) return Float2OpsKernelsSegment.angleBetween_degenerate_unsafe(VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, otherX, otherY);
        return Float2OpsKernelsSegment.angleBetween_degenerate_api(VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, otherX, otherY);
    }

    public static float angleBetween_degenerate_unsafe(long src, float otherX, float otherY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
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
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
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

    public static float angleBetween_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
            float _t1 = _othery * _selfy;
            float _t2 = -(_otherx * _selfy);
            float _ct0 = java.lang.Math.abs(java.lang.Math.fma(_othery, _selfx, _t2) - java.lang.Math.fma(_otherx, _selfy, _t2));
            if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.angleBetween_degenerate(src, other);
            return Math.atan2(_ct0, java.lang.Math.fma(_otherx, _selfx, _t1) + java.lang.Math.fma(_othery, _selfy, -_t1));
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
            float _t1 = _othery * _selfy;
            float _t2 = -(_otherx * _selfy);
            float _ct0 = java.lang.Math.abs(((_othery) * (_selfx) + (_t2)) - ((_otherx) * (_selfy) + (_t2)));
            if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.angleBetween_degenerate(src, other);
            return Math.atan2(_ct0, ((_otherx) * (_selfx) + (_t1)) + ((_othery) * (_selfy) - (_t1)));
        }
    }

    public static float angleBetween_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.angleBetween_degenerate_unsafe(src, other);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && VirtualMemoryHolder.virtualMemory().asSlice(src, 8L).isNative() && VirtualMemoryHolder.virtualMemory().asSlice(other, 8L).isNative()) return Float2OpsKernelsSegment.angleBetween_degenerate_unsafe(VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 8L), 0L);
        return Float2OpsKernelsSegment.angleBetween_degenerate_api(VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 8L), 0L);
    }

    public static float angleBetween_degenerate_unsafe(long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_unsafe_mulAdd(src, other);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
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

    /** {@code angleBetween_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_unsafe_mulAdd(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
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

    public static long asin_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.asin(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.asin(_selfy));
        return dest;
    }

    public static long atan_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan(_selfy));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, float x) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_selfx, x));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_selfy, x));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, float xX, float xY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_selfx, xX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_selfy, xY));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, long x) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _xx = UnsafeOpsHolder.U.getFloat(x);
        float _xy = UnsafeOpsHolder.U.getFloat(x + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_selfx, _xx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_selfy, _xy));
        return dest;
    }

    public static long cbrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.cbrt(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cbrt(_selfy));
        return dest;
    }

    public static long ceil_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.ceil(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.ceil(_selfy));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, float min, float max) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(Math.max(_selfx, min), max));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, min), max));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, float minX, float minY, float maxX, float maxY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(Math.max(_selfx, minX), maxX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, minY), maxY));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, long min, long max) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _minx = UnsafeOpsHolder.U.getFloat(min);
        float _miny = UnsafeOpsHolder.U.getFloat(min + 4L);
        float _maxx = UnsafeOpsHolder.U.getFloat(max);
        float _maxy = UnsafeOpsHolder.U.getFloat(max + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(Math.max(_selfx, _minx), _maxx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, _miny), _maxy));
        return dest;
    }

    public static float compAdd_unsafe(long src) {
        return UnsafeOpsHolder.U.getFloat(src) + UnsafeOpsHolder.U.getFloat(src + 4L);
    }

    public static float compMax_unsafe(long src) {
        return Math.max(UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(src + 4L));
    }

    public static float compMin_unsafe(long src) {
        return Math.min(UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(src + 4L));
    }

    public static float compMul_unsafe(long src) {
        return UnsafeOpsHolder.U.getFloat(src) * UnsafeOpsHolder.U.getFloat(src + 4L);
    }

    public static long copySign_unsafe(long dest, long src, float sign) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.copySign(_selfx, sign));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, sign));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, float signX, float signY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.copySign(_selfx, signX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, signY));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, long sign) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _signx = UnsafeOpsHolder.U.getFloat(sign);
        float _signy = UnsafeOpsHolder.U.getFloat(sign + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.copySign(_selfx, _signx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, _signy));
        return dest;
    }

    public static long cos_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.cos(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cos(_selfy));
        return dest;
    }

    public static long cosh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.cosh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cosh(_selfy));
        return dest;
    }

    public static float cross_unsafe(long src, float otherX, float otherY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = -(otherX * _selfy);
            return java.lang.Math.fma(otherY, UnsafeOpsHolder.U.getFloat(src), _t1) - java.lang.Math.fma(otherX, _selfy, _t1);
        } else {
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = -(otherX * _selfy);
            return ((otherY) * (UnsafeOpsHolder.U.getFloat(src)) + (_t1)) - ((otherX) * (_selfy) + (_t1));
        }
    }

    public static float cross_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _t1 = -(_otherx * _selfy);
            return java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(other + 4L), UnsafeOpsHolder.U.getFloat(src), _t1) - java.lang.Math.fma(_otherx, _selfy, _t1);
        } else {
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _t1 = -(_otherx * _selfy);
            return ((UnsafeOpsHolder.U.getFloat(other + 4L)) * (UnsafeOpsHolder.U.getFloat(src)) + (_t1)) - ((_otherx) * (_selfy) + (_t1));
        }
    }

    public static long degrees_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.toDegrees(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.toDegrees(_selfy));
        return dest;
    }

    public static float distance_unsafe(long src, float otherX, float otherY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, _t1 * _t1));
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (_t1 * _t1)));
        }
    }

    public static float distance_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, _t1 * _t1));
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (_t1 * _t1)));
        }
    }

    public static float distanceSquared_unsafe(long src, float otherX, float otherY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return java.lang.Math.fma(_t0, _t0, _t1 * _t1);
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return ((_t0) * (_t0) + (_t1 * _t1));
        }
    }

    public static float distanceSquared_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
            return java.lang.Math.fma(_t0, _t0, _t1 * _t1);
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
            return ((_t0) * (_t0) + (_t1 * _t1));
        }
    }

    public static float dot_unsafe(long src, float otherX, float otherY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherX, UnsafeOpsHolder.U.getFloat(src), otherY * UnsafeOpsHolder.U.getFloat(src + 4L));
        } else {
            return ((otherX) * (UnsafeOpsHolder.U.getFloat(src)) + (otherY * UnsafeOpsHolder.U.getFloat(src + 4L)));
        }
    }

    public static float dot_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(other), UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(other + 4L) * UnsafeOpsHolder.U.getFloat(src + 4L));
        } else {
            return ((UnsafeOpsHolder.U.getFloat(other)) * (UnsafeOpsHolder.U.getFloat(src)) + (UnsafeOpsHolder.U.getFloat(other + 4L) * UnsafeOpsHolder.U.getFloat(src + 4L)));
        }
    }

    public static long exp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.exp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.exp(_selfy));
        return dest;
    }

    public static long exp2_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(2.0f, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(2.0f, _selfy));
        return dest;
    }

    public static long expm1_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.expm1(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.expm1(_selfy));
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, float IX, float IY, float NrefX, float NrefY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t2 = java.lang.Math.fma(IX, NrefX, IY * NrefY) < 0.0f ? 1.0f : -1.0f;
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t2);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t2);
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t2 = ((IX) * (NrefX) + (IY * NrefY)) < 0.0f ? 1.0f : -1.0f;
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t2);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t2);
            return dest;
        }
    }

    public static long faceforward_unsafe(long dest, long src, long I, long Nref) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t2 = java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(I), UnsafeOpsHolder.U.getFloat(Nref), UnsafeOpsHolder.U.getFloat(I + 4L) * UnsafeOpsHolder.U.getFloat(Nref + 4L)) < 0.0f ? 1.0f : -1.0f;
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t2);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t2);
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t2 = ((UnsafeOpsHolder.U.getFloat(I)) * (UnsafeOpsHolder.U.getFloat(Nref)) + (UnsafeOpsHolder.U.getFloat(I + 4L) * UnsafeOpsHolder.U.getFloat(Nref + 4L))) < 0.0f ? 1.0f : -1.0f;
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t2);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t2);
            return dest;
        }
    }

    public static long floor_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.floor(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.floor(_selfy));
        return dest;
    }

    public static long fract_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, float y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.hypot(_selfx, y));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.hypot(_selfy, y));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, float yX, float yY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.hypot(_selfx, yX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.hypot(_selfy, yY));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, long y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _yx = UnsafeOpsHolder.U.getFloat(y);
        float _yy = UnsafeOpsHolder.U.getFloat(y + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.hypot(_selfx, _yx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.hypot(_selfy, _yy));
        return dest;
    }

    public static long inverse_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, 1.0f / _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f / _selfy);
        return dest;
    }

    public static long inverseSqrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, (1.0f / (float) java.lang.Math.sqrt(_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        return dest;
    }

    public static float length_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            return (float) java.lang.Math.sqrt(((_selfx) * (_selfx) + (_selfy * _selfy)));
        }
    }

    public static float lengthSquared_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            return java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy);
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            return ((_selfx) * (_selfx) + (_selfy * _selfy));
        }
    }

    public static long log_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log(_selfy));
        return dest;
    }

    public static long log10_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log10(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log10(_selfy));
        return dest;
    }

    public static long log1p_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log1p(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log1p(_selfy));
        return dest;
    }

    public static long log2_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log2(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log2(_selfy));
        return dest;
    }

    public static float manhattanDistance_unsafe(long src, float otherX, float otherY) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src) - otherX) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 4L) - otherY);
    }

    public static float manhattanDistance_unsafe(long src, long other) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L));
    }

    public static float manhattanLength_unsafe(long src) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 4L));
    }

    public static long max_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.max(_selfx, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, scalar));
        return dest;
    }

    public static long max_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.max(_selfx, otherX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, otherY));
        return dest;
    }

    public static long max_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.max(_selfx, _otherx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, _othery));
        return dest;
    }

    public static long min_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, scalar));
        return dest;
    }

    public static long min_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx, otherX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, otherY));
        return dest;
    }

    public static long min_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx, _otherx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, _othery));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, float y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, flooredMod(_selfx, y));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, y));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, float yX, float yY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, flooredMod(_selfx, yX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, yY));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, long y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _yx = UnsafeOpsHolder.U.getFloat(y);
        float _yy = UnsafeOpsHolder.U.getFloat(y + 4L);
        UnsafeOpsHolder.U.putFloat(dest, flooredMod(_selfx, _yx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, _yy));
        return dest;
    }

    public static long nextDown_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.nextDown(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.nextDown(_selfy));
        return dest;
    }

    public static long nextUp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.nextUp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.nextUp(_selfy));
        return dest;
    }

    public static long normalize_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy);
            float _t2 = (1.0f / (float) java.lang.Math.sqrt(_t1));
            if (_t1 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _selfx * _t2);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t2);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            }
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = ((_selfx) * (_selfx) + (_selfy * _selfy));
            float _t2 = (1.0f / (float) java.lang.Math.sqrt(_t1));
            if (_t1 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _selfx * _t2);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t2);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            }
            return dest;
        }
    }

    public static long normalizeMul_unsafe(long dest, long src, float length) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy);
            float _t3 = length * (1.0f / (float) java.lang.Math.sqrt(_t1));
            if (_t1 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _selfx * _t3);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t3);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            }
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = ((_selfx) * (_selfx) + (_selfy * _selfy));
            float _t3 = length * (1.0f / (float) java.lang.Math.sqrt(_t1));
            if (_t1 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _selfx * _t3);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t3);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            }
            return dest;
        }
    }

    public static float orientedAngle_unsafe(long src, float otherX, float otherY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = otherY * _selfy;
            float _t2 = -(otherX * _selfy);
            float _t5 = java.lang.Math.fma(otherY, _selfx, _t2) - java.lang.Math.fma(otherX, _selfy, _t2);
            float _ct0 = java.lang.Math.abs(_t5);
            if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.orientedAngle_degenerate(src, otherX, otherY);
            return Math.atan2(Math.copySign(_ct0, _t5), java.lang.Math.fma(otherX, _selfx, _t1) + java.lang.Math.fma(otherY, _selfy, -_t1));
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = otherY * _selfy;
            float _t2 = -(otherX * _selfy);
            float _t5 = ((otherY) * (_selfx) + (_t2)) - ((otherX) * (_selfy) + (_t2));
            float _ct0 = java.lang.Math.abs(_t5);
            if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.orientedAngle_degenerate(src, otherX, otherY);
            return Math.atan2(Math.copySign(_ct0, _t5), ((otherX) * (_selfx) + (_t1)) + ((otherY) * (_selfy) - (_t1)));
        }
    }

    public static float orientedAngle_degenerate(long src, float otherX, float otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.orientedAngle_degenerate_unsafe(src, otherX, otherY);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && VirtualMemoryHolder.virtualMemory().asSlice(src, 8L).isNative()) return Float2OpsKernelsSegment.orientedAngle_degenerate_unsafe(VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, otherX, otherY);
        return Float2OpsKernelsSegment.orientedAngle_degenerate_api(VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, otherX, otherY);
    }

    public static float orientedAngle_degenerate_unsafe(long src, float otherX, float otherY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_unsafe_mulAdd(src, otherX, otherY);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
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

    /** {@code orientedAngle_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_unsafe_mulAdd(long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
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

    public static float orientedAngle_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
            float _t1 = _othery * _selfy;
            float _t2 = -(_otherx * _selfy);
            float _t5 = java.lang.Math.fma(_othery, _selfx, _t2) - java.lang.Math.fma(_otherx, _selfy, _t2);
            float _ct0 = java.lang.Math.abs(_t5);
            if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.orientedAngle_degenerate(src, other);
            return Math.atan2(Math.copySign(_ct0, _t5), java.lang.Math.fma(_otherx, _selfx, _t1) + java.lang.Math.fma(_othery, _selfy, -_t1));
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
            float _t1 = _othery * _selfy;
            float _t2 = -(_otherx * _selfy);
            float _t5 = ((_othery) * (_selfx) + (_t2)) - ((_otherx) * (_selfy) + (_t2));
            float _ct0 = java.lang.Math.abs(_t5);
            if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.orientedAngle_degenerate(src, other);
            return Math.atan2(Math.copySign(_ct0, _t5), ((_otherx) * (_selfx) + (_t1)) + ((_othery) * (_selfy) - (_t1)));
        }
    }

    public static float orientedAngle_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.orientedAngle_degenerate_unsafe(src, other);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && VirtualMemoryHolder.virtualMemory().asSlice(src, 8L).isNative() && VirtualMemoryHolder.virtualMemory().asSlice(other, 8L).isNative()) return Float2OpsKernelsSegment.orientedAngle_degenerate_unsafe(VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 8L), 0L);
        return Float2OpsKernelsSegment.orientedAngle_degenerate_api(VirtualMemoryHolder.virtualMemory().asSlice(src, 8L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 8L), 0L);
    }

    public static float orientedAngle_degenerate_unsafe(long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_unsafe_mulAdd(src, other);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
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

    /** {@code orientedAngle_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_unsafe_mulAdd(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
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

    public static long outerProduct_unsafe(long dest, long src, float rowX, float rowY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, rowX * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, rowX * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, rowY * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 12L, rowY * _selfy);
        return dest;
    }

    public static long outerProduct_unsafe(long dest, long src, long row) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _rowx = UnsafeOpsHolder.U.getFloat(row);
        float _rowy = UnsafeOpsHolder.U.getFloat(row + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _rowx * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _rowx * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _rowy * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _rowy * _selfy);
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float exponent) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(_selfx, exponent));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(_selfy, exponent));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float exponentX, float exponentY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(_selfx, exponentX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(_selfy, exponentY));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, long exponent) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _exponentx = UnsafeOpsHolder.U.getFloat(exponent);
        float _exponenty = UnsafeOpsHolder.U.getFloat(exponent + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(_selfx, _exponentx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(_selfy, _exponenty));
        return dest;
    }

    public static long project_unsafe(long dest, long src, float ontoX, float ontoY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t5 = java.lang.Math.fma(ontoX, UnsafeOpsHolder.U.getFloat(src), ontoY * UnsafeOpsHolder.U.getFloat(src + 4L)) / java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY);
            UnsafeOpsHolder.U.putFloat(dest, ontoX * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 4L, ontoY * _t5);
            return dest;
        } else {
            float _t5 = ((ontoX) * (UnsafeOpsHolder.U.getFloat(src)) + (ontoY * UnsafeOpsHolder.U.getFloat(src + 4L))) / ((ontoX) * (ontoX) + (ontoY * ontoY));
            UnsafeOpsHolder.U.putFloat(dest, ontoX * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 4L, ontoY * _t5);
            return dest;
        }
    }

    public static long project_unsafe(long dest, long src, long onto) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _ontox = UnsafeOpsHolder.U.getFloat(onto);
            float _ontoy = UnsafeOpsHolder.U.getFloat(onto + 4L);
            float _t5 = java.lang.Math.fma(_ontox, UnsafeOpsHolder.U.getFloat(src), _ontoy * UnsafeOpsHolder.U.getFloat(src + 4L)) / java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy);
            UnsafeOpsHolder.U.putFloat(dest, _ontox * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _ontoy * _t5);
            return dest;
        } else {
            float _ontox = UnsafeOpsHolder.U.getFloat(onto);
            float _ontoy = UnsafeOpsHolder.U.getFloat(onto + 4L);
            float _t5 = ((_ontox) * (UnsafeOpsHolder.U.getFloat(src)) + (_ontoy * UnsafeOpsHolder.U.getFloat(src + 4L))) / ((_ontox) * (_ontox) + (_ontoy * _ontoy));
            UnsafeOpsHolder.U.putFloat(dest, _ontox * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _ontoy * _t5);
            return dest;
        }
    }

    public static long projectOnPlane_unsafe(long dest, long src, float normalX, float normalY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = java.lang.Math.fma(normalX, _selfx, normalY * _selfy);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-normalX, _t1, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-normalY, _t1, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t1 = ((normalX) * (_selfx) + (normalY * _selfy));
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (normalX) * (_t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (normalY) * (_t1)));
            return dest;
        }
    }

    public static long projectOnPlane_unsafe(long dest, long src, long normal) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _normalx = UnsafeOpsHolder.U.getFloat(normal);
            float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
            float _t1 = java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-_normalx, _t1, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-_normaly, _t1, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _normalx = UnsafeOpsHolder.U.getFloat(normal);
            float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
            float _t1 = ((_normalx) * (_selfx) + (_normaly * _selfy));
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (_normalx) * (_t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (_normaly) * (_t1)));
            return dest;
        }
    }

    public static long radians_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.toRadians(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.toRadians(_selfy));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, float normalX, float normalY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t2 = 2.0f * java.lang.Math.fma(normalX, _selfx, normalY * _selfy);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-normalX, _t2, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-normalY, _t2, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t2 = 2.0f * ((normalX) * (_selfx) + (normalY * _selfy));
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (normalX) * (_t2)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (normalY) * (_t2)));
            return dest;
        }
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _normalx = UnsafeOpsHolder.U.getFloat(normal);
            float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
            float _t2 = 2.0f * java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-_normalx, _t2, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-_normaly, _t2, _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _normalx = UnsafeOpsHolder.U.getFloat(normal);
            float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
            float _t2 = 2.0f * ((_normalx) * (_selfx) + (_normaly * _selfy));
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (_normalx) * (_t2)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (_normaly) * (_t2)));
            return dest;
        }
    }

    public static long refract_unsafe(long dest, long src, float normalX, float normalY, float eta) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t2 = java.lang.Math.fma(normalX, _selfx, normalY * _selfy);
            float _t6 = java.lang.Math.fma(java.lang.Math.fma(_t2, _t2, -1.0f), eta * eta, 1.0f);
            float _t9 = java.lang.Math.fma(eta, _t2, (float) java.lang.Math.sqrt(Math.max(0.0f, _t6)));
            if (_t6 >= 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(eta, _selfx, -(normalX * _t9)));
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(eta, _selfy, -(normalY * _t9)));
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            }
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t2 = ((normalX) * (_selfx) + (normalY * _selfy));
            float _t6 = ((((_t2) * (_t2) - (1.0f))) * (eta * eta) + (1.0f));
            float _t9 = ((eta) * (_t2) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t6))));
            if (_t6 >= 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, ((eta) * (_selfx) - (normalX * _t9)));
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((eta) * (_selfy) - (normalY * _t9)));
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            }
            return dest;
        }
    }

    public static long refract_unsafe(long dest, long src, long normal, float eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_unsafe_mulAdd(dest, src, normal, eta);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _t2 = java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy);
        float _t6 = java.lang.Math.fma(java.lang.Math.fma(_t2, _t2, -1.0f), eta * eta, 1.0f);
        float _t9 = java.lang.Math.fma(eta, _t2, (float) java.lang.Math.sqrt(Math.max(0.0f, _t6)));
        if (_t6 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(eta, _selfx, -(_normalx * _t9)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(eta, _selfy, -(_normaly * _t9)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        }
        return dest;
    }

    /** {@code refract_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_mulAdd(long dest, long src, long normal, float eta) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _t2 = ((_normalx) * (_selfx) + (_normaly * _selfy));
        float _t6 = ((((_t2) * (_t2) - (1.0f))) * (eta * eta) + (1.0f));
        float _t9 = ((eta) * (_t2) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t6))));
        if (_t6 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, ((eta) * (_selfx) - (_normalx * _t9)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((eta) * (_selfy) - (_normaly * _t9)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        }
        return dest;
    }

    public static long round_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.rint(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.rint(_selfy));
        return dest;
    }

    public static long sign_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.signum(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.signum(_selfy));
        return dest;
    }

    public static long sin_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.sin(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.sin(_selfy));
        return dest;
    }

    public static long sinh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.sinh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.sinh(_selfy));
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, float edge0, float edge1) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0_inv = 1.0f / (edge1 - edge0);
            float _t7 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0) * _t0_inv));
            float _t8 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0) * _t0_inv));
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, _t7, 3.0f) * _t7 * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
            return dest;
        } else {
            float _t0_inv = 1.0f / (edge1 - edge0);
            float _t7 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0) * _t0_inv));
            float _t8 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0) * _t0_inv));
            UnsafeOpsHolder.U.putFloat(dest, ((3.0f) - (2.0f) * (_t7)) * _t7 * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((3.0f) - (2.0f) * (_t8)) * _t8 * _t8);
            return dest;
        }
    }

    public static long smoothstep_unsafe(long dest, long src, float edge0X, float edge0Y, float edge1X, float edge1Y) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t8 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0X) / (edge1X - edge0X)));
            float _t9 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0Y) / (edge1Y - edge0Y)));
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, _t9, 3.0f) * _t9 * _t9);
            return dest;
        } else {
            float _t8 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0X) / (edge1X - edge0X)));
            float _t9 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0Y) / (edge1Y - edge0Y)));
            UnsafeOpsHolder.U.putFloat(dest, ((3.0f) - (2.0f) * (_t8)) * _t8 * _t8);
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((3.0f) - (2.0f) * (_t9)) * _t9 * _t9);
            return dest;
        }
    }

    public static long smoothstep_unsafe(long dest, long src, long edge0, long edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0, edge1);
        float _edge0x = UnsafeOpsHolder.U.getFloat(edge0);
        float _edge0y = UnsafeOpsHolder.U.getFloat(edge0 + 4L);
        float _t8 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - _edge0x) / (UnsafeOpsHolder.U.getFloat(edge1) - _edge0x)));
        float _t9 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - _edge0y) / (UnsafeOpsHolder.U.getFloat(edge1 + 4L) - _edge0y)));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, _t9, 3.0f) * _t9 * _t9);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, long edge0, long edge1) {
        float _edge0x = UnsafeOpsHolder.U.getFloat(edge0);
        float _edge0y = UnsafeOpsHolder.U.getFloat(edge0 + 4L);
        float _t8 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - _edge0x) / (UnsafeOpsHolder.U.getFloat(edge1) - _edge0x)));
        float _t9 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - _edge0y) / (UnsafeOpsHolder.U.getFloat(edge1 + 4L) - _edge0y)));
        UnsafeOpsHolder.U.putFloat(dest, ((3.0f) - (2.0f) * (_t8)) * _t8 * _t8);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((3.0f) - (2.0f) * (_t9)) * _t9 * _t9);
        return dest;
    }

    public static long sqrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, (float) java.lang.Math.sqrt(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) java.lang.Math.sqrt(_selfy));
        return dest;
    }

    public static long step_unsafe(long dest, long src, float edge) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx < edge ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static long step_unsafe(long dest, long src, float edgeX, float edgeY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx < edgeX ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < edgeY ? 0.0f : 1.0f);
        return dest;
    }

    public static long step_unsafe(long dest, long src, long edge) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _edgex = UnsafeOpsHolder.U.getFloat(edge);
        float _edgey = UnsafeOpsHolder.U.getFloat(edge + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx < _edgex ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < _edgey ? 0.0f : 1.0f);
        return dest;
    }

    public static long tan_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.tan(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.tan(_selfy));
        return dest;
    }

    public static long tanh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.tanh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.tanh(_selfy));
        return dest;
    }

    public static long trunc_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        return dest;
    }

    public static long ulp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.ulp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.ulp(_selfy));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long mat) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
            float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
            float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 8L);
            float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
            float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
            float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 8L);
            float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((_mat00) * (_selfx) + (_mat01 * _selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat10) * (_selfx) + (_mat11 * _selfy)));
            return dest;
        }
    }

    public static long preMulDirectionMat2x3_unsafe(long dest, long src, long mat) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
            float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
            float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 8L);
            float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
            float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
            float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 8L);
            float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((_mat00) * (_selfx) + (_mat01 * _selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat10) * (_selfx) + (_mat11 * _selfy)));
            return dest;
        }
    }

    public static long preMulDirectionMat3x3_unsafe(long dest, long src, long mat) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
            float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
            float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 12L);
            float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 16L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
            float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
            float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 12L);
            float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 16L);
            UnsafeOpsHolder.U.putFloat(dest, ((_mat00) * (_selfx) + (_mat01 * _selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat10) * (_selfx) + (_mat11 * _selfy)));
            return dest;
        }
    }

    public static long preMulPositionMat4x4_unsafe(long dest, long src, long mat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat4x4_unsafe_mulAdd(dest, src, mat);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat03 = UnsafeOpsHolder.U.getFloat(mat + 48L);
        float _mat13 = UnsafeOpsHolder.U.getFloat(mat + 52L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat00, _selfx, java.lang.Math.fma(_mat01, _selfy, _mat03)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, _mat13)));
        return dest;
    }

    /** {@code preMulPositionMat4x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat4x4_unsafe_mulAdd(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat03 = UnsafeOpsHolder.U.getFloat(mat + 48L);
        float _mat13 = UnsafeOpsHolder.U.getFloat(mat + 52L);
        UnsafeOpsHolder.U.putFloat(dest, ((_mat00) * (_selfx) + (((_mat01) * (_selfy) + (_mat03)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (_mat13)))));
        return dest;
    }

    public static long preMulPositionMat2x3_unsafe(long dest, long src, long mat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat2x3_unsafe_mulAdd(dest, src, mat);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat00, _selfx, java.lang.Math.fma(_mat01, _selfy, _mat02)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    /** {@code preMulPositionMat2x3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat2x3_unsafe_mulAdd(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        UnsafeOpsHolder.U.putFloat(dest, ((_mat00) * (_selfx) + (((_mat01) * (_selfy) + (_mat02)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (_mat12)))));
        return dest;
    }

    public static long preMulPositionMat3x3_unsafe(long dest, long src, long mat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat3x3_unsafe_mulAdd(dest, src, mat);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 28L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat00, _selfx, java.lang.Math.fma(_mat01, _selfy, _mat02)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    /** {@code preMulPositionMat3x3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat3x3_unsafe_mulAdd(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 28L);
        UnsafeOpsHolder.U.putFloat(dest, ((_mat00) * (_selfx) + (((_mat01) * (_selfy) + (_mat02)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (_mat12)))));
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t0) - (_selfy * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfx) * (_t1) + (_selfy * _t0)));
            return dest;
        }
    }

    public static long rotateAround_unsafe(long dest, long src, float angle, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - pivotX;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - pivotY;
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotX)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotY)));
            return dest;
        } else {
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - pivotX;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - pivotY;
            UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (_t0) + (((pivotX) - (_t3) * (_t1)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotY)))));
            return dest;
        }
    }

    public static long rotateAround_unsafe(long dest, long src, long pivot, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
            float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - _pivotx;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - _pivoty;
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivotx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivoty)));
            return dest;
        } else {
            float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
            float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - _pivotx;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - _pivoty;
            UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (_t0) + (((_pivotx) - (_t3) * (_t1)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivoty)))));
            return dest;
        }
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

    /**
     * The floored remainder of x and y, exactly kotlin.Float.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static float flooredMod(float x, float y) {
        float q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p24f && java.lang.Math.abs(y) <= Float.MAX_VALUE) {
            float r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0f), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        float r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }
}
