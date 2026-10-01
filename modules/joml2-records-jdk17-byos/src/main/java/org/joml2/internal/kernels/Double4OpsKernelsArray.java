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

    public static double[] add_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        dest[destOffset] = other[otherOffset] + src[srcOffset];
        dest[destOffset + 1] = _othery + _selfy;
        dest[destOffset + 2] = _otherz + _selfz;
        dest[destOffset + 3] = _otherw + _selfw;
        return dest;
    }

    public static double[] div_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset] / scalar;
        dest[destOffset + 1] = _selfy / scalar;
        dest[destOffset + 2] = _selfz / scalar;
        dest[destOffset + 3] = _selfw / scalar;
        return dest;
    }

    public static double[] div_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        dest[destOffset] = src[srcOffset] / other[otherOffset];
        dest[destOffset + 1] = _selfy / _othery;
        dest[destOffset + 2] = _selfz / _otherz;
        dest[destOffset + 3] = _selfw / _otherw;
        return dest;
    }

    public static double[] fma_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] c, int cOffset, double b) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _cy = c[cOffset + 1];
            double _cz = c[cOffset + 2];
            double _cw = c[cOffset + 3];
            dest[destOffset] = java.lang.Math.fma(src[srcOffset], b, c[cOffset]);
            dest[destOffset + 1] = java.lang.Math.fma(_selfy, b, _cy);
            dest[destOffset + 2] = java.lang.Math.fma(_selfz, b, _cz);
            dest[destOffset + 3] = java.lang.Math.fma(_selfw, b, _cw);
            return dest;
        } else {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _cy = c[cOffset + 1];
            double _cz = c[cOffset + 2];
            double _cw = c[cOffset + 3];
            dest[destOffset] = ((src[srcOffset]) * (b) + (c[cOffset]));
            dest[destOffset + 1] = ((_selfy) * (b) + (_cy));
            dest[destOffset + 2] = ((_selfz) * (b) + (_cz));
            dest[destOffset + 3] = ((_selfw) * (b) + (_cw));
            return dest;
        }
    }

    public static double[] fma_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double[] c, int cOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _by = b[bOffset + 1];
            double _bz = b[bOffset + 2];
            double _bw = b[bOffset + 3];
            double _cy = c[cOffset + 1];
            double _cz = c[cOffset + 2];
            double _cw = c[cOffset + 3];
            dest[destOffset] = java.lang.Math.fma(src[srcOffset], b[bOffset], c[cOffset]);
            dest[destOffset + 1] = java.lang.Math.fma(_selfy, _by, _cy);
            dest[destOffset + 2] = java.lang.Math.fma(_selfz, _bz, _cz);
            dest[destOffset + 3] = java.lang.Math.fma(_selfw, _bw, _cw);
            return dest;
        } else {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _by = b[bOffset + 1];
            double _bz = b[bOffset + 2];
            double _bw = b[bOffset + 3];
            double _cy = c[cOffset + 1];
            double _cz = c[cOffset + 2];
            double _cw = c[cOffset + 3];
            dest[destOffset] = ((src[srcOffset]) * (b[bOffset]) + (c[cOffset]));
            dest[destOffset + 1] = ((_selfy) * (_by) + (_cy));
            dest[destOffset + 2] = ((_selfz) * (_bz) + (_cz));
            dest[destOffset + 3] = ((_selfw) * (_bw) + (_cw));
            return dest;
        }
    }

    public static double[] mul_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        dest[destOffset] = scalar * src[srcOffset];
        dest[destOffset + 1] = scalar * _selfy;
        dest[destOffset + 2] = scalar * _selfz;
        dest[destOffset + 3] = scalar * _selfw;
        return dest;
    }

    public static double[] mul_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        dest[destOffset] = other[otherOffset] * src[srcOffset];
        dest[destOffset + 1] = _othery * _selfy;
        dest[destOffset + 2] = _otherz * _selfz;
        dest[destOffset + 3] = _otherw * _selfw;
        return dest;
    }

    public static double[] negate_scalar(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        dest[destOffset] = -src[srcOffset];
        dest[destOffset + 1] = -_selfy;
        dest[destOffset + 2] = -_selfz;
        dest[destOffset + 3] = -_selfw;
        return dest;
    }

    public static double[] sub_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        dest[destOffset] = src[srcOffset] - other[otherOffset];
        dest[destOffset + 1] = _selfy - _othery;
        dest[destOffset + 2] = _selfz - _otherz;
        dest[destOffset + 3] = _selfw - _otherw;
        return dest;
    }

    public static double[] set_scalar(double[] dest, int destOffset, double[] v, int vOffset) {
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        double _vw = v[vOffset + 3];
        dest[destOffset] = v[vOffset];
        dest[destOffset + 1] = _vy;
        dest[destOffset + 2] = _vz;
        dest[destOffset + 3] = _vw;
        return dest;
    }

    public static double[] set_scalar(double[] dest, int destOffset, double s) {
        dest[destOffset] = s;
        dest[destOffset + 1] = s;
        dest[destOffset + 2] = s;
        dest[destOffset + 3] = s;
        return dest;
    }

    public static double[] makeZero_scalar(double[] dest, int destOffset) {
        dest[destOffset] = 0.0;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        return dest;
    }

    public static double[] bezier_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_scalar_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezier_scalar_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezier_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] bezier_scalar_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _p3w = p3[p3Offset + 3];
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest[destOffset] = java.lang.Math.fma(p1[p1Offset], _t7, src[srcOffset] * _t8) + java.lang.Math.fma(p2[p2Offset], _t6, p3[p3Offset] * _t2);
        dest[destOffset + 1] = java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2);
        dest[destOffset + 2] = java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2);
        dest[destOffset + 3] = java.lang.Math.fma(_p1w, _t7, _selfw * _t8) + java.lang.Math.fma(_p2w, _t6, _p3w * _t2);
        return dest;
    }

    /** {@code bezier_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] bezier_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _p3w = p3[p3Offset + 3];
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest[destOffset] = ((p1[p1Offset]) * (_t7) + (src[srcOffset] * _t8)) + ((p2[p2Offset]) * (_t6) + (p3[p3Offset] * _t2));
        dest[destOffset + 1] = ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2));
        dest[destOffset + 2] = ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2));
        dest[destOffset + 3] = ((_p1w) * (_t7) + (_selfw * _t8)) + ((_p2w) * (_t6) + (_p3w * _t2));
        return dest;
    }

    public static double[] bezier2_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_scalar_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest[destOffset] = java.lang.Math.fma(p2[p2Offset], _t0, java.lang.Math.fma(p1[p1Offset], _t3, src[srcOffset] * _t4));
        dest[destOffset + 1] = java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4));
        dest[destOffset + 2] = java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4));
        dest[destOffset + 3] = java.lang.Math.fma(_p2w, _t0, java.lang.Math.fma(_p1w, _t3, _selfw * _t4));
        return dest;
    }

    /** {@code bezier2_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] bezier2_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest[destOffset] = ((p2[p2Offset]) * (_t0) + (((p1[p1Offset]) * (_t3) + (src[srcOffset] * _t4))));
        dest[destOffset + 1] = ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4))));
        dest[destOffset + 2] = ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4))));
        dest[destOffset + 3] = ((_p2w) * (_t0) + (((_p1w) * (_t3) + (_selfw * _t4))));
        return dest;
    }

    public static double[] bezier2Tangent_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            dest[destOffset] = java.lang.Math.fma(p1X - src[srcOffset], _t2, (p2X - p1X) * _t1);
            dest[destOffset + 1] = java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1);
            dest[destOffset + 2] = java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1);
            dest[destOffset + 3] = java.lang.Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1);
            return dest;
        } else {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            dest[destOffset] = ((p1X - src[srcOffset]) * (_t2) + ((p2X - p1X) * _t1));
            dest[destOffset + 1] = ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1));
            dest[destOffset + 2] = ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1));
            dest[destOffset + 3] = ((p1W - _selfw) * (_t2) + ((p2W - p1W) * _t1));
            return dest;
        }
    }

    public static double[] bezier2Tangent_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_scalar_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest[destOffset] = java.lang.Math.fma(_p1x - src[srcOffset], _t2, (p2[p2Offset] - _p1x) * _t1);
        dest[destOffset + 1] = java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1);
        dest[destOffset + 2] = java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1);
        dest[destOffset + 3] = java.lang.Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1);
        return dest;
    }

    /** {@code bezier2Tangent_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] bezier2Tangent_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest[destOffset] = ((_p1x - src[srcOffset]) * (_t2) + ((p2[p2Offset] - _p1x) * _t1));
        dest[destOffset + 1] = ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1));
        dest[destOffset + 2] = ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1));
        dest[destOffset + 3] = ((_p1w - _selfw) * (_t2) + ((_p2w - _p1w) * _t1));
        return dest;
    }

    public static double[] bezierTangent_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_scalar_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest[destOffset] = java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - src[srcOffset], _t6, (p2X - p1X) * _t5));
        dest[destOffset + 1] = java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5));
        dest[destOffset + 2] = java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5));
        dest[destOffset + 3] = java.lang.Math.fma(p3W - p2W, _t2, java.lang.Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5));
        return dest;
    }

    /** {@code bezierTangent_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] bezierTangent_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest[destOffset] = ((p3X - p2X) * (_t2) + (((p1X - src[srcOffset]) * (_t6) + ((p2X - p1X) * _t5))));
        dest[destOffset + 1] = ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5))));
        dest[destOffset + 2] = ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5))));
        dest[destOffset + 3] = ((p3W - p2W) * (_t2) + (((p1W - _selfw) * (_t6) + ((p2W - p1W) * _t5))));
        return dest;
    }

    public static double[] bezierTangent_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_scalar_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezierTangent_scalar_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezierTangent_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] bezierTangent_scalar_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2x = p2[p2Offset];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _p3w = p3[p3Offset + 3];
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest[destOffset] = java.lang.Math.fma(p3[p3Offset] - _p2x, _t2, java.lang.Math.fma(_p1x - src[srcOffset], _t6, (_p2x - _p1x) * _t5));
        dest[destOffset + 1] = java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5));
        dest[destOffset + 2] = java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5));
        dest[destOffset + 3] = java.lang.Math.fma(_p3w - _p2w, _t2, java.lang.Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5));
        return dest;
    }

    /** {@code bezierTangent_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] bezierTangent_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2x = p2[p2Offset];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _p3w = p3[p3Offset + 3];
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest[destOffset] = ((p3[p3Offset] - _p2x) * (_t2) + (((_p1x - src[srcOffset]) * (_t6) + ((_p2x - _p1x) * _t5))));
        dest[destOffset + 1] = ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5))));
        dest[destOffset + 2] = ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5))));
        dest[destOffset + 3] = ((_p3w - _p2w) * (_t2) + (((_p1w - _selfw) * (_t6) + ((_p2w - _p1w) * _t5))));
        return dest;
    }

    public static double[] catmullRom_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_scalar_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRom_scalar_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRom_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] catmullRom_scalar_fma(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest[destOffset] = 0.5 * (java.lang.Math.fma(2.0, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)) * _t1));
        dest[destOffset + 1] = 0.5 * (java.lang.Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1));
        dest[destOffset + 2] = 0.5 * (java.lang.Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1));
        return catmullRom_scalar_s14d5bf0f_1_fma(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0, _t1);
    }

    /** {@code catmullRom_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] catmullRom_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest[destOffset] = 0.5 * (((2.0) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) * (_t0) + (((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X)) * _t1)));
        dest[destOffset + 1] = 0.5 * (((2.0) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) * (_t0) + (((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y)) * _t1)));
        dest[destOffset + 2] = 0.5 * (((2.0) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) * (_t0) + (((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z)) * _t1)));
        return catmullRom_scalar_s14d5bf0f_1_mulAdd(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] catmullRom_scalar_s14d5bf0f_1_fma(double[] dest, int destOffset, double p1W, double p2W, double p3W, double t, double _selfw, double _t0, double _t1) {
        dest[destOffset + 3] = 0.5 * (java.lang.Math.fma(2.0, p1W, t * (p2W - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1W, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0, p2W, java.lang.Math.fma(3.0, p1W, p3W - _selfw)) * _t1));
        return dest;
    }

    /** Piece 2 of {@code catmullRom_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] catmullRom_scalar_s14d5bf0f_1_mulAdd(double[] dest, int destOffset, double p1W, double p2W, double p3W, double t, double _selfw, double _t0, double _t1) {
        dest[destOffset + 3] = 0.5 * (((2.0) * (p1W) + (t * (p2W - _selfw))) + ((((((2.0) * (_selfw) + (((4.0) * (p2W) - (p3W))))) - (5.0) * (p1W))) * (_t0) + (((((3.0) * (p1W) + (p3W - _selfw))) - (3.0) * (p2W)) * _t1)));
        return dest;
    }

    public static double[] catmullRom_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_scalar_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2x = p2[p2Offset];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _p3x = p3[p3Offset];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _p3w = p3[p3Offset + 3];
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest[destOffset] = 0.5 * (java.lang.Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1));
        return catmullRom_scalar_sbfee4b7e_1_fma(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0, _t1);
    }

    /** {@code catmullRom_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] catmullRom_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2x = p2[p2Offset];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _p3x = p3[p3Offset];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _p3w = p3[p3Offset + 3];
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest[destOffset] = 0.5 * (((2.0) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) * (_t0) + (((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x)) * _t1)));
        return catmullRom_scalar_sbfee4b7e_1_mulAdd(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] catmullRom_scalar_sbfee4b7e_1_fma(double[] dest, int destOffset, double t, double _selfy, double _selfz, double _selfw, double _p1y, double _p1z, double _p1w, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t0, double _t1) {
        dest[destOffset + 1] = 0.5 * (java.lang.Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1));
        dest[destOffset + 2] = 0.5 * (java.lang.Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1));
        dest[destOffset + 3] = 0.5 * (java.lang.Math.fma(2.0, _p1w, t * (_p2w - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1w, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, _p2w, -_p3w))), _t0, java.lang.Math.fma(-3.0, _p2w, java.lang.Math.fma(3.0, _p1w, _p3w - _selfw)) * _t1));
        return dest;
    }

    /** Piece 2 of {@code catmullRom_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] catmullRom_scalar_sbfee4b7e_1_mulAdd(double[] dest, int destOffset, double t, double _selfy, double _selfz, double _selfw, double _p1y, double _p1z, double _p1w, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t0, double _t1) {
        dest[destOffset + 1] = 0.5 * (((2.0) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) * (_t0) + (((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y)) * _t1)));
        dest[destOffset + 2] = 0.5 * (((2.0) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) * (_t0) + (((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z)) * _t1)));
        dest[destOffset + 3] = 0.5 * (((2.0) * (_p1w) + (t * (_p2w - _selfw))) + ((((((2.0) * (_selfw) + (((4.0) * (_p2w) - (_p3w))))) - (5.0) * (_p1w))) * (_t0) + (((((3.0) * (_p1w) + (_p3w - _selfw))) - (3.0) * (_p2w)) * _t1)));
        return dest;
    }

    public static double[] catmullRomTangent_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_scalar_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRomTangent_scalar_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRomTangent_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] catmullRomTangent_scalar_fma(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t0 = t * t;
        dest[destOffset] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx));
        dest[destOffset + 1] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy));
        dest[destOffset + 2] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz));
        return catmullRomTangent_scalar_s9b11751c_1_fma(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0);
    }

    /** {@code catmullRomTangent_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] catmullRomTangent_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t0 = t * t;
        dest[destOffset] = 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) + (((3.0 * ((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X))) * (_t0) + (p2X - _selfx))));
        dest[destOffset + 1] = 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) + (((3.0 * ((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y))) * (_t0) + (p2Y - _selfy))));
        dest[destOffset + 2] = 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) + (((3.0 * ((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z))) * (_t0) + (p2Z - _selfz))));
        return catmullRomTangent_scalar_s9b11751c_1_mulAdd(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] catmullRomTangent_scalar_s9b11751c_1_fma(double[] dest, int destOffset, double p1W, double p2W, double p3W, double t, double _selfw, double _t0) {
        dest[destOffset + 3] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1W, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, p2W, -p3W))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2W, java.lang.Math.fma(3.0, p1W, p3W - _selfw)), _t0, p2W - _selfw));
        return dest;
    }

    /** Piece 2 of {@code catmullRomTangent_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] catmullRomTangent_scalar_s9b11751c_1_mulAdd(double[] dest, int destOffset, double p1W, double p2W, double p3W, double t, double _selfw, double _t0) {
        dest[destOffset + 3] = 0.5 * ((t) * (2.0 * ((((2.0) * (_selfw) + (((4.0) * (p2W) - (p3W))))) - (5.0) * (p1W))) + (((3.0 * ((((3.0) * (p1W) + (p3W - _selfw))) - (3.0) * (p2W))) * (_t0) + (p2W - _selfw))));
        return dest;
    }

    public static double[] catmullRomTangent_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_scalar_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2x = p2[p2Offset];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _p3x = p3[p3Offset];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _p3w = p3[p3Offset + 3];
        double _t0 = t * t;
        dest[destOffset] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx));
        return catmullRomTangent_scalar_sf02b90e3_1_fma(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0);
    }

    /** {@code catmullRomTangent_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] catmullRomTangent_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p1w = p1[p1Offset + 3];
        double _p2x = p2[p2Offset];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p2w = p2[p2Offset + 3];
        double _p3x = p3[p3Offset];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _p3w = p3[p3Offset + 3];
        double _t0 = t * t;
        dest[destOffset] = 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) + (((3.0 * ((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x))) * (_t0) + (_p2x - _selfx))));
        return catmullRomTangent_scalar_sf02b90e3_1_mulAdd(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] catmullRomTangent_scalar_sf02b90e3_1_fma(double[] dest, int destOffset, double t, double _selfy, double _selfz, double _selfw, double _p1y, double _p1z, double _p1w, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t0) {
        dest[destOffset + 1] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy));
        dest[destOffset + 2] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz));
        dest[destOffset + 3] = 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1w, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, _p2w, -_p3w))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2w, java.lang.Math.fma(3.0, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw));
        return dest;
    }

    /** Piece 2 of {@code catmullRomTangent_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] catmullRomTangent_scalar_sf02b90e3_1_mulAdd(double[] dest, int destOffset, double t, double _selfy, double _selfz, double _selfw, double _p1y, double _p1z, double _p1w, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t0) {
        dest[destOffset + 1] = 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) + (((3.0 * ((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y))) * (_t0) + (_p2y - _selfy))));
        dest[destOffset + 2] = 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) + (((3.0 * ((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z))) * (_t0) + (_p2z - _selfz))));
        dest[destOffset + 3] = 0.5 * ((t) * (2.0 * ((((2.0) * (_selfw) + (((4.0) * (_p2w) - (_p3w))))) - (5.0) * (_p1w))) + (((3.0 * ((((3.0) * (_p1w) + (_p3w - _selfw))) - (3.0) * (_p2w))) * (_t0) + (_p2w - _selfw))));
        return dest;
    }

    public static double[] hermite_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_scalar_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_scalar_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermite_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] hermite_scalar_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t0y = t0[t0Offset + 1];
        double _t0z = t0[t0Offset + 2];
        double _t0w = t0[t0Offset + 3];
        double _v1y = v1[v1Offset + 1];
        double _v1z = v1[v1Offset + 2];
        double _v1w = v1[v1Offset + 3];
        double _t1y = t1[t1Offset + 1];
        double _t1z = t1[t1Offset + 2];
        double _t1w = t1[t1Offset + 3];
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        dest[destOffset] = java.lang.Math.fma(src[srcOffset], _t10, t0[t0Offset] * _t7) + java.lang.Math.fma(t1[t1Offset], _t5, v1[v1Offset] * _t9);
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9);
        return hermite_scalar_s11bd8206_1_fma(dest, destOffset, _selfz, _selfw, _t0z, _t0w, _v1z, _v1w, _t1z, _t1w, _t5, _t7, _t9, _t10);
    }

    /** {@code hermite_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] hermite_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t0y = t0[t0Offset + 1];
        double _t0z = t0[t0Offset + 2];
        double _t0w = t0[t0Offset + 3];
        double _v1y = v1[v1Offset + 1];
        double _v1z = v1[v1Offset + 2];
        double _v1w = v1[v1Offset + 3];
        double _t1y = t1[t1Offset + 1];
        double _t1z = t1[t1Offset + 2];
        double _t1w = t1[t1Offset + 3];
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * ((t) * (t) - (t));
        double _t7 = ((t - 2.0) * (_t0) + (t));
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        dest[destOffset] = ((src[srcOffset]) * (_t10) + (t0[t0Offset] * _t7)) + ((t1[t1Offset]) * (_t5) + (v1[v1Offset] * _t9));
        dest[destOffset + 1] = ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9));
        return hermite_scalar_s11bd8206_1_mulAdd(dest, destOffset, _selfz, _selfw, _t0z, _t0w, _v1z, _v1w, _t1z, _t1w, _t5, _t7, _t9, _t10);
    }

    /** Piece 2 of {@code hermite_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] hermite_scalar_s11bd8206_1_fma(double[] dest, int destOffset, double _selfz, double _selfw, double _t0z, double _t0w, double _v1z, double _v1w, double _t1z, double _t1w, double _t5, double _t7, double _t9, double _t10) {
        dest[destOffset + 2] = java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9);
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t10, _t0w * _t7) + java.lang.Math.fma(_t1w, _t5, _v1w * _t9);
        return dest;
    }

    /** Piece 2 of {@code hermite_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] hermite_scalar_s11bd8206_1_mulAdd(double[] dest, int destOffset, double _selfz, double _selfw, double _t0z, double _t0w, double _v1z, double _v1w, double _t1z, double _t1w, double _t5, double _t7, double _t9, double _t10) {
        dest[destOffset + 2] = ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9));
        dest[destOffset + 3] = ((_selfw) * (_t10) + (_t0w * _t7)) + ((_t1w) * (_t5) + (_v1w * _t9));
        return dest;
    }

    public static double[] hermiteTangent_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_scalar_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_scalar_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermiteTangent_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] hermiteTangent_scalar_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t0y = t0[t0Offset + 1];
        double _t0z = t0[t0Offset + 2];
        double _t0w = t0[t0Offset + 3];
        double _v1y = v1[v1Offset + 1];
        double _v1z = v1[v1Offset + 2];
        double _v1w = v1[v1Offset + 3];
        double _t1y = t1[t1Offset + 1];
        double _t1z = t1[t1Offset + 2];
        double _t1w = t1[t1Offset + 3];
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        dest[destOffset] = java.lang.Math.fma(src[srcOffset], _t6, t0[t0Offset] * _t9) + java.lang.Math.fma(t1[t1Offset], _t8, v1[v1Offset] * _t7);
        dest[destOffset + 1] = java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7);
        dest[destOffset + 2] = java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7);
        dest[destOffset + 3] = java.lang.Math.fma(_selfw, _t6, _t0w * _t9) + java.lang.Math.fma(_t1w, _t8, _v1w * _t7);
        return dest;
    }

    /** {@code hermiteTangent_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] hermiteTangent_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t0y = t0[t0Offset + 1];
        double _t0z = t0[t0Offset + 2];
        double _t0w = t0[t0Offset + 3];
        double _v1y = v1[v1Offset + 1];
        double _v1z = v1[v1Offset + 2];
        double _v1w = v1[v1Offset + 3];
        double _t1y = t1[t1Offset + 1];
        double _t1z = t1[t1Offset + 2];
        double _t1w = t1[t1Offset + 3];
        double _t0 = t * t;
        double _t6 = 6.0 * ((t) * (t) - (t));
        double _t7 = 6.0 * ((t) - (t) * (t));
        double _t8 = ((3.0) * (_t0) - (t + t));
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        dest[destOffset] = ((src[srcOffset]) * (_t6) + (t0[t0Offset] * _t9)) + ((t1[t1Offset]) * (_t8) + (v1[v1Offset] * _t7));
        dest[destOffset + 1] = ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7));
        dest[destOffset + 2] = ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7));
        dest[destOffset + 3] = ((_selfw) * (_t6) + (_t0w * _t9)) + ((_t1w) * (_t8) + (_v1w * _t7));
        return dest;
    }

    public static double[] lerp_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            dest[destOffset] = java.lang.Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, otherY - _selfy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(t, otherZ - _selfz, _selfz);
            dest[destOffset + 3] = java.lang.Math.fma(t, otherW - _selfw, _selfw);
            return dest;
        } else {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            dest[destOffset] = ((t) * (otherX - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (otherY - _selfy) + (_selfy));
            dest[destOffset + 2] = ((t) * (otherZ - _selfz) + (_selfz));
            dest[destOffset + 3] = ((t) * (otherW - _selfw) + (_selfw));
            return dest;
        }
    }

    public static double[] lerp_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _othery = other[otherOffset + 1];
            double _otherz = other[otherOffset + 2];
            double _otherw = other[otherOffset + 3];
            dest[destOffset] = java.lang.Math.fma(t, other[otherOffset] - _selfx, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(t, _othery - _selfy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(t, _otherz - _selfz, _selfz);
            dest[destOffset + 3] = java.lang.Math.fma(t, _otherw - _selfw, _selfw);
            return dest;
        } else {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _othery = other[otherOffset + 1];
            double _otherz = other[otherOffset + 2];
            double _otherw = other[otherOffset + 3];
            dest[destOffset] = ((t) * (other[otherOffset] - _selfx) + (_selfx));
            dest[destOffset + 1] = ((t) * (_othery - _selfy) + (_selfy));
            dest[destOffset + 2] = ((t) * (_otherz - _selfz) + (_selfz));
            dest[destOffset + 3] = ((t) * (_otherw - _selfw) + (_selfw));
            return dest;
        }
    }

    public static double[] lerp_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double[] t, int tOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_scalar_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        double _ty = t[tOffset + 1];
        double _tz = t[tOffset + 2];
        double _tw = t[tOffset + 3];
        dest[destOffset] = java.lang.Math.fma(t[tOffset], other[otherOffset] - _selfx, _selfx);
        dest[destOffset + 1] = java.lang.Math.fma(_ty, _othery - _selfy, _selfy);
        dest[destOffset + 2] = java.lang.Math.fma(_tz, _otherz - _selfz, _selfz);
        dest[destOffset + 3] = java.lang.Math.fma(_tw, _otherw - _selfw, _selfw);
        return dest;
    }

    /** {@code lerp_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] lerp_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double[] t, int tOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        double _ty = t[tOffset + 1];
        double _tz = t[tOffset + 2];
        double _tw = t[tOffset + 3];
        dest[destOffset] = ((t[tOffset]) * (other[otherOffset] - _selfx) + (_selfx));
        dest[destOffset + 1] = ((_ty) * (_othery - _selfy) + (_selfy));
        dest[destOffset + 2] = ((_tz) * (_otherz - _selfz) + (_selfz));
        dest[destOffset + 3] = ((_tw) * (_otherw - _selfw) + (_selfw));
        return dest;
    }

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

    public static double[] absolute_scalar(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        dest[destOffset] = java.lang.Math.abs(src[srcOffset]);
        dest[destOffset + 1] = java.lang.Math.abs(_selfy);
        dest[destOffset + 2] = java.lang.Math.abs(_selfz);
        dest[destOffset + 3] = java.lang.Math.abs(_selfw);
        return dest;
    }

    public static double[] addScaled_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _by = b[bOffset + 1];
            double _bz = b[bOffset + 2];
            double _bw = b[bOffset + 3];
            dest[destOffset] = java.lang.Math.fma(scalar, b[bOffset], src[srcOffset]);
            dest[destOffset + 1] = java.lang.Math.fma(scalar, _by, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(scalar, _bz, _selfz);
            dest[destOffset + 3] = java.lang.Math.fma(scalar, _bw, _selfw);
            return dest;
        } else {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _by = b[bOffset + 1];
            double _bz = b[bOffset + 2];
            double _bw = b[bOffset + 3];
            dest[destOffset] = ((scalar) * (b[bOffset]) + (src[srcOffset]));
            dest[destOffset + 1] = ((scalar) * (_by) + (_selfy));
            dest[destOffset + 2] = ((scalar) * (_bz) + (_selfz));
            dest[destOffset + 3] = ((scalar) * (_bw) + (_selfw));
            return dest;
        }
    }

    public static double[] addScaled_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double[] c, int cOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _by = b[bOffset + 1];
            double _bz = b[bOffset + 2];
            double _bw = b[bOffset + 3];
            double _cy = c[cOffset + 1];
            double _cz = c[cOffset + 2];
            double _cw = c[cOffset + 3];
            dest[destOffset] = java.lang.Math.fma(b[bOffset], c[cOffset], src[srcOffset]);
            dest[destOffset + 1] = java.lang.Math.fma(_by, _cy, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(_bz, _cz, _selfz);
            dest[destOffset + 3] = java.lang.Math.fma(_bw, _cw, _selfw);
            return dest;
        } else {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _by = b[bOffset + 1];
            double _bz = b[bOffset + 2];
            double _bw = b[bOffset + 3];
            double _cy = c[cOffset + 1];
            double _cz = c[cOffset + 2];
            double _cw = c[cOffset + 3];
            dest[destOffset] = ((b[bOffset]) * (c[cOffset]) + (src[srcOffset]));
            dest[destOffset + 1] = ((_by) * (_cy) + (_selfy));
            dest[destOffset + 2] = ((_bz) * (_cz) + (_selfz));
            dest[destOffset + 3] = ((_bw) * (_cw) + (_selfw));
            return dest;
        }
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

    public static double[] clamp_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double min, double max) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.min(Math.max(src[srcOffset], min), max);
        dest[destOffset + 1] = Math.min(Math.max(_selfy, min), max);
        dest[destOffset + 2] = Math.min(Math.max(_selfz, min), max);
        dest[destOffset + 3] = Math.min(Math.max(_selfw, min), max);
        return dest;
    }

    public static double[] clamp_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] min, int minOffset, double[] max, int maxOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _miny = min[minOffset + 1];
        double _minz = min[minOffset + 2];
        double _minw = min[minOffset + 3];
        double _maxy = max[maxOffset + 1];
        double _maxz = max[maxOffset + 2];
        double _maxw = max[maxOffset + 3];
        dest[destOffset] = Math.min(Math.max(src[srcOffset], min[minOffset]), max[maxOffset]);
        dest[destOffset + 1] = Math.min(Math.max(_selfy, _miny), _maxy);
        dest[destOffset + 2] = Math.min(Math.max(_selfz, _minz), _maxz);
        dest[destOffset + 3] = Math.min(Math.max(_selfw, _minw), _maxw);
        return dest;
    }

    public static double[] faceforward_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0 ? 1.0 : -1.0;
            dest[destOffset] = src[srcOffset] * _t4;
            dest[destOffset + 1] = _selfy * _t4;
            dest[destOffset + 2] = _selfz * _t4;
            dest[destOffset + 3] = _selfw * _t4;
            return dest;
        } else {
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0 ? 1.0 : -1.0;
            dest[destOffset] = src[srcOffset] * _t4;
            dest[destOffset + 1] = _selfy * _t4;
            dest[destOffset + 2] = _selfz * _t4;
            dest[destOffset + 3] = _selfw * _t4;
            return dest;
        }
    }

    public static double[] faceforward_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] I, int IOffset, double[] Nref, int NrefOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_scalar_mulAdd(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t4 = java.lang.Math.fma(I[IOffset + 3], Nref[NrefOffset + 3], java.lang.Math.fma(I[IOffset + 2], Nref[NrefOffset + 2], java.lang.Math.fma(I[IOffset], Nref[NrefOffset], I[IOffset + 1] * Nref[NrefOffset + 1]))) < 0.0 ? 1.0 : -1.0;
        dest[destOffset] = src[srcOffset] * _t4;
        dest[destOffset + 1] = _selfy * _t4;
        dest[destOffset + 2] = _selfz * _t4;
        dest[destOffset + 3] = _selfw * _t4;
        return dest;
    }

    /** {@code faceforward_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] faceforward_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] I, int IOffset, double[] Nref, int NrefOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t4 = ((I[IOffset + 3]) * (Nref[NrefOffset + 3]) + (((I[IOffset + 2]) * (Nref[NrefOffset + 2]) + (((I[IOffset]) * (Nref[NrefOffset]) + (I[IOffset + 1] * Nref[NrefOffset + 1])))))) < 0.0 ? 1.0 : -1.0;
        dest[destOffset] = src[srcOffset] * _t4;
        dest[destOffset + 1] = _selfy * _t4;
        dest[destOffset + 2] = _selfz * _t4;
        dest[destOffset + 3] = _selfw * _t4;
        return dest;
    }

    public static double[] inverse_scalar(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        dest[destOffset] = 1.0 / src[srcOffset];
        dest[destOffset + 1] = 1.0 / _selfy;
        dest[destOffset + 2] = 1.0 / _selfz;
        dest[destOffset + 3] = 1.0 / _selfw;
        return dest;
    }

    public static double[] max_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.max(src[srcOffset], scalar);
        dest[destOffset + 1] = Math.max(_selfy, scalar);
        dest[destOffset + 2] = Math.max(_selfz, scalar);
        dest[destOffset + 3] = Math.max(_selfw, scalar);
        return dest;
    }

    public static double[] max_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        dest[destOffset] = Math.max(src[srcOffset], other[otherOffset]);
        dest[destOffset + 1] = Math.max(_selfy, _othery);
        dest[destOffset + 2] = Math.max(_selfz, _otherz);
        dest[destOffset + 3] = Math.max(_selfw, _otherw);
        return dest;
    }

    public static double[] min_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.min(src[srcOffset], scalar);
        dest[destOffset + 1] = Math.min(_selfy, scalar);
        dest[destOffset + 2] = Math.min(_selfz, scalar);
        dest[destOffset + 3] = Math.min(_selfw, scalar);
        return dest;
    }

    public static double[] min_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _otherw = other[otherOffset + 3];
        dest[destOffset] = Math.min(src[srcOffset], other[otherOffset]);
        dest[destOffset + 1] = Math.min(_selfy, _othery);
        dest[destOffset + 2] = Math.min(_selfz, _otherz);
        dest[destOffset + 3] = Math.min(_selfw, _otherw);
        return dest;
    }

    public static double[] normalize_scalar(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                dest[destOffset] = _selfx * _t4;
                dest[destOffset + 1] = _selfy * _t4;
                dest[destOffset + 2] = _selfz * _t4;
                dest[destOffset + 3] = _selfw * _t4;
            } else {
                dest[destOffset] = 0.0;
                dest[destOffset + 1] = 0.0;
                dest[destOffset + 2] = 0.0;
                dest[destOffset + 3] = 0.0;
            }
            return dest;
        } else {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                dest[destOffset] = _selfx * _t4;
                dest[destOffset + 1] = _selfy * _t4;
                dest[destOffset + 2] = _selfz * _t4;
                dest[destOffset + 3] = _selfw * _t4;
            } else {
                dest[destOffset] = 0.0;
                dest[destOffset + 1] = 0.0;
                dest[destOffset + 2] = 0.0;
                dest[destOffset + 3] = 0.0;
            }
            return dest;
        }
    }

    public static double[] normalizeMul_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double length) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
            double _t5 = length * (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                dest[destOffset] = _selfx * _t5;
                dest[destOffset + 1] = _selfy * _t5;
                dest[destOffset + 2] = _selfz * _t5;
                dest[destOffset + 3] = _selfw * _t5;
            } else {
                dest[destOffset] = 0.0;
                dest[destOffset + 1] = 0.0;
                dest[destOffset + 2] = 0.0;
                dest[destOffset + 3] = 0.0;
            }
            return dest;
        } else {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
            double _t5 = length * (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                dest[destOffset] = _selfx * _t5;
                dest[destOffset + 1] = _selfy * _t5;
                dest[destOffset + 2] = _selfz * _t5;
                dest[destOffset + 3] = _selfw * _t5;
            } else {
                dest[destOffset] = 0.0;
                dest[destOffset + 1] = 0.0;
                dest[destOffset + 2] = 0.0;
                dest[destOffset + 3] = 0.0;
            }
            return dest;
        }
    }

    public static double[] outerProduct_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double rowX, double rowY, double rowZ, double rowW) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        dest[destOffset] = rowX * _selfx;
        dest[destOffset + 1] = rowX * _selfy;
        dest[destOffset + 2] = rowX * _selfz;
        dest[destOffset + 3] = rowX * _selfw;
        dest[destOffset + 4] = rowY * _selfx;
        dest[destOffset + 5] = rowY * _selfy;
        dest[destOffset + 6] = rowY * _selfz;
        dest[destOffset + 7] = rowY * _selfw;
        dest[destOffset + 8] = rowZ * _selfx;
        dest[destOffset + 9] = rowZ * _selfy;
        dest[destOffset + 10] = rowZ * _selfz;
        dest[destOffset + 11] = rowZ * _selfw;
        dest[destOffset + 12] = rowW * _selfx;
        dest[destOffset + 13] = rowW * _selfy;
        dest[destOffset + 14] = rowW * _selfz;
        dest[destOffset + 15] = rowW * _selfw;
        return dest;
    }

    public static double[] outerProduct_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] row, int rowOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _rowx = row[rowOffset];
        double _rowy = row[rowOffset + 1];
        double _rowz = row[rowOffset + 2];
        double _roww = row[rowOffset + 3];
        dest[destOffset] = _rowx * _selfx;
        dest[destOffset + 1] = _rowx * _selfy;
        dest[destOffset + 2] = _rowx * _selfz;
        dest[destOffset + 3] = _rowx * _selfw;
        dest[destOffset + 4] = _rowy * _selfx;
        dest[destOffset + 5] = _rowy * _selfy;
        dest[destOffset + 6] = _rowy * _selfz;
        dest[destOffset + 7] = _rowy * _selfw;
        dest[destOffset + 8] = _rowz * _selfx;
        dest[destOffset + 9] = _rowz * _selfy;
        dest[destOffset + 10] = _rowz * _selfz;
        dest[destOffset + 11] = _rowz * _selfw;
        dest[destOffset + 12] = _roww * _selfx;
        dest[destOffset + 13] = _roww * _selfy;
        dest[destOffset + 14] = _roww * _selfz;
        dest[destOffset + 15] = _roww * _selfw;
        return dest;
    }

    public static double[] project_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] onto, int ontoOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_scalar_mulAdd(dest, destOffset, src, srcOffset, onto, ontoOffset);
        double _ontox = onto[ontoOffset];
        double _ontoy = onto[ontoOffset + 1];
        double _ontoz = onto[ontoOffset + 2];
        double _ontow = onto[ontoOffset + 3];
        double _t9 = java.lang.Math.fma(_ontow, src[srcOffset + 3], java.lang.Math.fma(_ontoz, src[srcOffset + 2], java.lang.Math.fma(_ontox, src[srcOffset], _ontoy * src[srcOffset + 1]))) / java.lang.Math.fma(_ontow, _ontow, java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest[destOffset] = _ontox * _t9;
        dest[destOffset + 1] = _ontoy * _t9;
        dest[destOffset + 2] = _ontoz * _t9;
        dest[destOffset + 3] = _ontow * _t9;
        return dest;
    }

    /** {@code project_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] project_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] onto, int ontoOffset) {
        double _ontox = onto[ontoOffset];
        double _ontoy = onto[ontoOffset + 1];
        double _ontoz = onto[ontoOffset + 2];
        double _ontow = onto[ontoOffset + 3];
        double _t9 = ((_ontow) * (src[srcOffset + 3]) + (((_ontoz) * (src[srcOffset + 2]) + (((_ontox) * (src[srcOffset]) + (_ontoy * src[srcOffset + 1])))))) / ((_ontow) * (_ontow) + (((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))))));
        dest[destOffset] = _ontox * _t9;
        dest[destOffset + 1] = _ontoy * _t9;
        dest[destOffset + 2] = _ontoz * _t9;
        dest[destOffset + 3] = _ontow * _t9;
        return dest;
    }

    public static double[] projectOnPlane_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _normalx = normal[normalOffset];
            double _normaly = normal[normalOffset + 1];
            double _normalz = normal[normalOffset + 2];
            double _normalw = normal[normalOffset + 3];
            double _t3 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
            dest[destOffset] = java.lang.Math.fma(-_normalx, _t3, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(-_normaly, _t3, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(-_normalz, _t3, _selfz);
            dest[destOffset + 3] = java.lang.Math.fma(-_normalw, _t3, _selfw);
            return dest;
        } else {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _normalx = normal[normalOffset];
            double _normaly = normal[normalOffset + 1];
            double _normalz = normal[normalOffset + 2];
            double _normalw = normal[normalOffset + 3];
            double _t3 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
            dest[destOffset] = ((_selfx) - (_normalx) * (_t3));
            dest[destOffset + 1] = ((_selfy) - (_normaly) * (_t3));
            dest[destOffset + 2] = ((_selfz) - (_normalz) * (_t3));
            dest[destOffset + 3] = ((_selfw) - (_normalw) * (_t3));
            return dest;
        }
    }

    public static double[] reflect_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _normalx = normal[normalOffset];
            double _normaly = normal[normalOffset + 1];
            double _normalz = normal[normalOffset + 2];
            double _normalw = normal[normalOffset + 3];
            double _t4 = 2.0 * java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
            dest[destOffset] = java.lang.Math.fma(-_normalx, _t4, _selfx);
            dest[destOffset + 1] = java.lang.Math.fma(-_normaly, _t4, _selfy);
            dest[destOffset + 2] = java.lang.Math.fma(-_normalz, _t4, _selfz);
            dest[destOffset + 3] = java.lang.Math.fma(-_normalw, _t4, _selfw);
            return dest;
        } else {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            double _selfw = src[srcOffset + 3];
            double _normalx = normal[normalOffset];
            double _normaly = normal[normalOffset + 1];
            double _normalz = normal[normalOffset + 2];
            double _normalw = normal[normalOffset + 3];
            double _t4 = 2.0 * ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
            dest[destOffset] = ((_selfx) - (_normalx) * (_t4));
            dest[destOffset + 1] = ((_selfy) - (_normaly) * (_t4));
            dest[destOffset + 2] = ((_selfz) - (_normalz) * (_t4));
            dest[destOffset + 3] = ((_selfw) - (_normalw) * (_t4));
            return dest;
        }
    }

    public static double[] refract_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_scalar_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t4 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        double _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0), eta * eta, 1.0);
        double _t11 = java.lang.Math.fma(eta, _t4, java.lang.Math.sqrt(Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            dest[destOffset] = java.lang.Math.fma(eta, _selfx, -(normalX * _t11));
            dest[destOffset + 1] = java.lang.Math.fma(eta, _selfy, -(normalY * _t11));
            dest[destOffset + 2] = java.lang.Math.fma(eta, _selfz, -(normalZ * _t11));
            dest[destOffset + 3] = java.lang.Math.fma(eta, _selfw, -(normalW * _t11));
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
            dest[destOffset + 3] = 0.0;
        }
        return dest;
    }

    /** {@code refract_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] refract_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _t4 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        double _t8 = ((((_t4) * (_t4) - (1.0))) * (eta * eta) + (1.0));
        double _t11 = ((eta) * (_t4) + (java.lang.Math.sqrt(Math.max(0.0, _t8))));
        if (_t8 >= 0.0) {
            dest[destOffset] = ((eta) * (_selfx) - (normalX * _t11));
            dest[destOffset + 1] = ((eta) * (_selfy) - (normalY * _t11));
            dest[destOffset + 2] = ((eta) * (_selfz) - (normalZ * _t11));
            dest[destOffset + 3] = ((eta) * (_selfw) - (normalW * _t11));
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
            dest[destOffset + 3] = 0.0;
        }
        return dest;
    }

    public static double[] refract_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset, double eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_scalar_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _normalx = normal[normalOffset];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        double _normalw = normal[normalOffset + 3];
        double _t4 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        double _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0), eta * eta, 1.0);
        double _t11 = java.lang.Math.fma(eta, _t4, java.lang.Math.sqrt(Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            dest[destOffset] = java.lang.Math.fma(eta, _selfx, -(_normalx * _t11));
            dest[destOffset + 1] = java.lang.Math.fma(eta, _selfy, -(_normaly * _t11));
            dest[destOffset + 2] = java.lang.Math.fma(eta, _selfz, -(_normalz * _t11));
            dest[destOffset + 3] = java.lang.Math.fma(eta, _selfw, -(_normalw * _t11));
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
            dest[destOffset + 3] = 0.0;
        }
        return dest;
    }

    /** {@code refract_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] refract_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset, double eta) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _normalx = normal[normalOffset];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        double _normalw = normal[normalOffset + 3];
        double _t4 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        double _t8 = ((((_t4) * (_t4) - (1.0))) * (eta * eta) + (1.0));
        double _t11 = ((eta) * (_t4) + (java.lang.Math.sqrt(Math.max(0.0, _t8))));
        if (_t8 >= 0.0) {
            dest[destOffset] = ((eta) * (_selfx) - (_normalx * _t11));
            dest[destOffset + 1] = ((eta) * (_selfy) - (_normaly * _t11));
            dest[destOffset + 2] = ((eta) * (_selfz) - (_normalz * _t11));
            dest[destOffset + 3] = ((eta) * (_selfw) - (_normalw * _t11));
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
            dest[destOffset + 3] = 0.0;
        }
        return dest;
    }

    public static double[] sqrt_scalar(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        dest[destOffset] = java.lang.Math.sqrt(src[srcOffset]);
        dest[destOffset + 1] = java.lang.Math.sqrt(_selfy);
        dest[destOffset + 2] = java.lang.Math.sqrt(_selfz);
        dest[destOffset + 3] = java.lang.Math.sqrt(_selfw);
        return dest;
    }

    public static double[] preMul_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_scalar_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_scalar_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMul_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] preMul_scalar_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _mat10 = mat[matOffset + 1];
        double _mat20 = mat[matOffset + 2];
        double _mat30 = mat[matOffset + 3];
        double _mat11 = mat[matOffset + 5];
        double _mat21 = mat[matOffset + 6];
        double _mat31 = mat[matOffset + 7];
        double _mat12 = mat[matOffset + 9];
        double _mat22 = mat[matOffset + 10];
        double _mat32 = mat[matOffset + 11];
        double _mat13 = mat[matOffset + 13];
        double _mat23 = mat[matOffset + 14];
        double _mat33 = mat[matOffset + 15];
        dest[destOffset] = java.lang.Math.fma(mat[matOffset + 12], _selfw, java.lang.Math.fma(mat[matOffset + 8], _selfz, java.lang.Math.fma(mat[matOffset], _selfx, mat[matOffset + 4] * _selfy)));
        dest[destOffset + 1] = java.lang.Math.fma(_mat13, _selfw, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest[destOffset + 2] = java.lang.Math.fma(_mat23, _selfw, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        dest[destOffset + 3] = java.lang.Math.fma(_mat33, _selfw, java.lang.Math.fma(_mat32, _selfz, java.lang.Math.fma(_mat30, _selfx, _mat31 * _selfy)));
        return dest;
    }

    /** {@code preMul_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] preMul_scalar_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _selfw = src[srcOffset + 3];
        double _mat10 = mat[matOffset + 1];
        double _mat20 = mat[matOffset + 2];
        double _mat30 = mat[matOffset + 3];
        double _mat11 = mat[matOffset + 5];
        double _mat21 = mat[matOffset + 6];
        double _mat31 = mat[matOffset + 7];
        double _mat12 = mat[matOffset + 9];
        double _mat22 = mat[matOffset + 10];
        double _mat32 = mat[matOffset + 11];
        double _mat13 = mat[matOffset + 13];
        double _mat23 = mat[matOffset + 14];
        double _mat33 = mat[matOffset + 15];
        dest[destOffset] = ((mat[matOffset + 12]) * (_selfw) + (((mat[matOffset + 8]) * (_selfz) + (((mat[matOffset]) * (_selfx) + (mat[matOffset + 4] * _selfy))))));
        dest[destOffset + 1] = ((_mat13) * (_selfw) + (((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy))))));
        dest[destOffset + 2] = ((_mat23) * (_selfw) + (((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy))))));
        dest[destOffset + 3] = ((_mat33) * (_selfw) + (((_mat32) * (_selfz) + (((_mat30) * (_selfx) + (_mat31 * _selfy))))));
        return dest;
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
