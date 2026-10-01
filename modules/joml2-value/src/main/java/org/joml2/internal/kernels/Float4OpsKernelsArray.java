// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float4Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float4Ops} and its sibling kernel units. Not public API.
 */
public final class Float4OpsKernelsArray {
    private Float4OpsKernelsArray() {}

    public static float[] add_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = other[otherOffset] + src[srcOffset];
        dest[destOffset + 1] = _othery + _selfy;
        dest[destOffset + 2] = _otherz + _selfz;
        dest[destOffset + 3] = _otherw + _selfw;
        return dest;
    }

    public static float[] div_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset] / scalar;
        dest[destOffset + 1] = _selfy / scalar;
        dest[destOffset + 2] = _selfz / scalar;
        dest[destOffset + 3] = _selfw / scalar;
        return dest;
    }

    public static float[] div_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = src[srcOffset] / other[otherOffset];
        dest[destOffset + 1] = _selfy / _othery;
        dest[destOffset + 2] = _selfz / _otherz;
        dest[destOffset + 3] = _selfw / _otherw;
        return dest;
    }

    public static float[] fma_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] c, int cOffset, float b) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _cy = c[cOffset + 1];
        float _cz = c[cOffset + 2];
        float _cw = c[cOffset + 3];
        dest[destOffset] = Math.fma(src[srcOffset], b, c[cOffset]);
        dest[destOffset + 1] = Math.fma(_selfy, b, _cy);
        dest[destOffset + 2] = Math.fma(_selfz, b, _cz);
        dest[destOffset + 3] = Math.fma(_selfw, b, _cw);
        return dest;
    }

    public static float[] fma_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _by = b[bOffset + 1];
        float _bz = b[bOffset + 2];
        float _bw = b[bOffset + 3];
        float _cy = c[cOffset + 1];
        float _cz = c[cOffset + 2];
        float _cw = c[cOffset + 3];
        dest[destOffset] = Math.fma(src[srcOffset], b[bOffset], c[cOffset]);
        dest[destOffset + 1] = Math.fma(_selfy, _by, _cy);
        dest[destOffset + 2] = Math.fma(_selfz, _bz, _cz);
        dest[destOffset + 3] = Math.fma(_selfw, _bw, _cw);
        return dest;
    }

    public static float[] mul_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = scalar * src[srcOffset];
        dest[destOffset + 1] = scalar * _selfy;
        dest[destOffset + 2] = scalar * _selfz;
        dest[destOffset + 3] = scalar * _selfw;
        return dest;
    }

    public static float[] mul_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = other[otherOffset] * src[srcOffset];
        dest[destOffset + 1] = _othery * _selfy;
        dest[destOffset + 2] = _otherz * _selfz;
        dest[destOffset + 3] = _otherw * _selfw;
        return dest;
    }

    public static float[] negate_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = -src[srcOffset];
        dest[destOffset + 1] = -_selfy;
        dest[destOffset + 2] = -_selfz;
        dest[destOffset + 3] = -_selfw;
        return dest;
    }

    public static float[] sub_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = src[srcOffset] - other[otherOffset];
        dest[destOffset + 1] = _selfy - _othery;
        dest[destOffset + 2] = _selfz - _otherz;
        dest[destOffset + 3] = _selfw - _otherw;
        return dest;
    }

    public static float[] set_scalar(float[] dest, int destOffset, float[] v, int vOffset) {
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        float _vw = v[vOffset + 3];
        dest[destOffset] = v[vOffset];
        dest[destOffset + 1] = _vy;
        dest[destOffset + 2] = _vz;
        dest[destOffset + 3] = _vw;
        return dest;
    }

    public static float[] set_scalar(float[] dest, int destOffset, float s) {
        dest[destOffset] = s;
        dest[destOffset + 1] = s;
        dest[destOffset + 2] = s;
        dest[destOffset + 3] = s;
        return dest;
    }

    public static float[] makeZero_scalar(float[] dest, int destOffset) {
        dest[destOffset] = 0.0f;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        return dest;
    }

    public static float[] bezier_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p1w = p1[p1Offset + 3];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _p2w = p2[p2Offset + 3];
        float _p3y = p3[p3Offset + 1];
        float _p3z = p3[p3Offset + 2];
        float _p3w = p3[p3Offset + 3];
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest[destOffset] = Math.fma(p1[p1Offset], _t7, src[srcOffset] * _t8) + Math.fma(p2[p2Offset], _t6, p3[p3Offset] * _t2);
        dest[destOffset + 1] = Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2);
        dest[destOffset + 2] = Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2);
        dest[destOffset + 3] = Math.fma(_p1w, _t7, _selfw * _t8) + Math.fma(_p2w, _t6, _p3w * _t2);
        return dest;
    }

    public static float[] bezier2_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p1w = p1[p1Offset + 3];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _p2w = p2[p2Offset + 3];
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest[destOffset] = Math.fma(p2[p2Offset], _t0, Math.fma(p1[p1Offset], _t3, src[srcOffset] * _t4));
        dest[destOffset + 1] = Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4));
        dest[destOffset + 2] = Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4));
        dest[destOffset + 3] = Math.fma(_p2w, _t0, Math.fma(_p1w, _t3, _selfw * _t4));
        return dest;
    }

    public static float[] bezier2Tangent_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest[destOffset] = Math.fma(p1X - src[srcOffset], _t2, (p2X - p1X) * _t1);
        dest[destOffset + 1] = Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1);
        dest[destOffset + 2] = Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1);
        dest[destOffset + 3] = Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1);
        return dest;
    }

    public static float[] bezier2Tangent_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _p1x = p1[p1Offset];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p1w = p1[p1Offset + 3];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _p2w = p2[p2Offset + 3];
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest[destOffset] = Math.fma(_p1x - src[srcOffset], _t2, (p2[p2Offset] - _p1x) * _t1);
        dest[destOffset + 1] = Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1);
        dest[destOffset + 2] = Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1);
        dest[destOffset + 3] = Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1);
        return dest;
    }

    public static float[] bezierTangent_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest[destOffset] = Math.fma(p3X - p2X, _t2, Math.fma(p1X - src[srcOffset], _t6, (p2X - p1X) * _t5));
        dest[destOffset + 1] = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5));
        dest[destOffset + 2] = Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5));
        dest[destOffset + 3] = Math.fma(p3W - p2W, _t2, Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5));
        return dest;
    }

    public static float[] bezierTangent_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _p1x = p1[p1Offset];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p1w = p1[p1Offset + 3];
        float _p2x = p2[p2Offset];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _p2w = p2[p2Offset + 3];
        float _p3y = p3[p3Offset + 1];
        float _p3z = p3[p3Offset + 2];
        float _p3w = p3[p3Offset + 3];
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest[destOffset] = Math.fma(p3[p3Offset] - _p2x, _t2, Math.fma(_p1x - src[srcOffset], _t6, (_p2x - _p1x) * _t5));
        dest[destOffset + 1] = Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5));
        dest[destOffset + 2] = Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5));
        dest[destOffset + 3] = Math.fma(_p3w - _p2w, _t2, Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5));
        return dest;
    }

    public static float[] catmullRom_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest[destOffset] = 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1));
        dest[destOffset + 1] = 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1));
        dest[destOffset + 2] = 0.5f * (Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), _t0, Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1));
        return catmullRom_scalar_s3bbcb0ea_1(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] catmullRom_scalar_s3bbcb0ea_1(float[] dest, int destOffset, float p1W, float p2W, float p3W, float t, float _selfw, float _t0, float _t1) {
        dest[destOffset + 3] = 0.5f * (Math.fma(2.0f, p1W, t * (p2W - _selfw)) + Math.fma(Math.fma(-5.0f, p1W, Math.fma(2.0f, _selfw, Math.fma(4.0f, p2W, -p3W))), _t0, Math.fma(-3.0f, p2W, Math.fma(3.0f, p1W, p3W - _selfw)) * _t1));
        return dest;
    }

    public static float[] catmullRom_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _p1x = p1[p1Offset];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p1w = p1[p1Offset + 3];
        float _p2x = p2[p2Offset];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _p2w = p2[p2Offset + 3];
        float _p3x = p3[p3Offset];
        float _p3y = p3[p3Offset + 1];
        float _p3z = p3[p3Offset + 2];
        float _p3w = p3[p3Offset + 3];
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest[destOffset] = 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1));
        return catmullRom_scalar_sd38c1034_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] catmullRom_scalar_sd38c1034_1(float[] dest, int destOffset, float t, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t0, float _t1) {
        dest[destOffset + 1] = 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1));
        dest[destOffset + 2] = 0.5f * (Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), _t0, Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1));
        dest[destOffset + 3] = 0.5f * (Math.fma(2.0f, _p1w, t * (_p2w - _selfw)) + Math.fma(Math.fma(-5.0f, _p1w, Math.fma(2.0f, _selfw, Math.fma(4.0f, _p2w, -_p3w))), _t0, Math.fma(-3.0f, _p2w, Math.fma(3.0f, _p1w, _p3w - _selfw)) * _t1));
        return dest;
    }

    public static float[] catmullRomTangent_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = t * t;
        dest[destOffset] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx));
        dest[destOffset + 1] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy));
        dest[destOffset + 2] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), Math.fma(3.0f * Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz));
        return catmullRomTangent_scalar_s60460173_1(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] catmullRomTangent_scalar_s60460173_1(float[] dest, int destOffset, float p1W, float p2W, float p3W, float t, float _selfw, float _t0) {
        dest[destOffset + 3] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1W, Math.fma(2.0f, _selfw, Math.fma(4.0f, p2W, -p3W))), Math.fma(3.0f * Math.fma(-3.0f, p2W, Math.fma(3.0f, p1W, p3W - _selfw)), _t0, p2W - _selfw));
        return dest;
    }

    public static float[] catmullRomTangent_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _p1x = p1[p1Offset];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p1w = p1[p1Offset + 3];
        float _p2x = p2[p2Offset];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _p2w = p2[p2Offset + 3];
        float _p3x = p3[p3Offset];
        float _p3y = p3[p3Offset + 1];
        float _p3z = p3[p3Offset + 2];
        float _p3w = p3[p3Offset + 3];
        float _t0 = t * t;
        dest[destOffset] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx));
        dest[destOffset + 1] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy));
        return catmullRomTangent_scalar_sc04a3905_1(dest, destOffset, t, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _p3z, _p3w, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] catmullRomTangent_scalar_sc04a3905_1(float[] dest, int destOffset, float t, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _p3z, float _p3w, float _t0) {
        dest[destOffset + 2] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), Math.fma(3.0f * Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz));
        dest[destOffset + 3] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1w, Math.fma(2.0f, _selfw, Math.fma(4.0f, _p2w, -_p3w))), Math.fma(3.0f * Math.fma(-3.0f, _p2w, Math.fma(3.0f, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw));
        return dest;
    }

    public static float[] hermite_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0y = t0[t0Offset + 1];
        float _t0z = t0[t0Offset + 2];
        float _t0w = t0[t0Offset + 3];
        float _v1y = v1[v1Offset + 1];
        float _v1z = v1[v1Offset + 2];
        float _v1w = v1[v1Offset + 3];
        float _t1y = t1[t1Offset + 1];
        float _t1z = t1[t1Offset + 2];
        float _t1w = t1[t1Offset + 3];
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest[destOffset] = Math.fma(src[srcOffset], _t10, t0[t0Offset] * _t7) + Math.fma(t1[t1Offset], _t5, v1[v1Offset] * _t9);
        dest[destOffset + 1] = Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9);
        dest[destOffset + 2] = Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9);
        dest[destOffset + 3] = Math.fma(_selfw, _t10, _t0w * _t7) + Math.fma(_t1w, _t5, _v1w * _t9);
        return dest;
    }

    public static float[] hermiteTangent_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0y = t0[t0Offset + 1];
        float _t0z = t0[t0Offset + 2];
        float _t0w = t0[t0Offset + 3];
        float _v1y = v1[v1Offset + 1];
        float _v1z = v1[v1Offset + 2];
        float _v1w = v1[v1Offset + 3];
        float _t1y = t1[t1Offset + 1];
        float _t1z = t1[t1Offset + 2];
        float _t1w = t1[t1Offset + 3];
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest[destOffset] = Math.fma(src[srcOffset], _t6, t0[t0Offset] * _t9) + Math.fma(t1[t1Offset], _t8, v1[v1Offset] * _t7);
        dest[destOffset + 1] = Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7);
        dest[destOffset + 2] = Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7);
        dest[destOffset + 3] = Math.fma(_selfw, _t6, _t0w * _t9) + Math.fma(_t1w, _t8, _v1w * _t7);
        return dest;
    }

    public static float[] lerp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.fma(t, otherX - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(t, otherY - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(t, otherZ - _selfz, _selfz);
        dest[destOffset + 3] = Math.fma(t, otherW - _selfw, _selfw);
        return dest;
    }

    public static float[] lerp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = Math.fma(t, other[otherOffset] - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(t, _othery - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(t, _otherz - _selfz, _selfz);
        dest[destOffset + 3] = Math.fma(t, _otherw - _selfw, _selfw);
        return dest;
    }

    public static float[] lerp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float[] t, int tOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _ty = t[tOffset + 1];
        float _tz = t[tOffset + 2];
        float _tw = t[tOffset + 3];
        dest[destOffset] = Math.fma(t[tOffset], other[otherOffset] - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(_ty, _othery - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(_tz, _otherz - _selfz, _selfz);
        dest[destOffset + 3] = Math.fma(_tw, _otherw - _selfw, _selfw);
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = java.lang.Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = Math.fma(_t80, _t43, _t72);
        float _t82 = Math.fma(_t80, _t45, _t73);
        float _t83 = Math.fma(_t80, _t47, _t74);
        float _t84 = Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * Math.sin(_t110);
        float _t115 = _t60 * Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
            _t123 = _t108 * _t98;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
            _t123 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest[destOffset] = Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv;
                    dest[destOffset + 1] = Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv;
                    dest[destOffset + 2] = Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv;
                    dest[destOffset + 3] = Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv;
                } else {
                    dest[destOffset] = Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                    dest[destOffset + 1] = Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                    dest[destOffset + 2] = Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                    dest[destOffset + 3] = Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
                }
            } else {
                dest[destOffset] = Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                dest[destOffset + 1] = Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                dest[destOffset + 2] = Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                dest[destOffset + 3] = Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
            }
        } else {
            dest[destOffset] = Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = Math.fma(t, otherY - _selfy, _selfy);
            dest[destOffset + 2] = Math.fma(t, otherZ - _selfz, _selfz);
            dest[destOffset + 3] = Math.fma(t, otherW - _selfw, _selfw);
        }
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t7 = unitScale(_otherz, _otherw, java.lang.Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = java.lang.Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = Math.fma(_t80, _t43, _t72);
        float _t82 = Math.fma(_t80, _t45, _t73);
        float _t83 = Math.fma(_t80, _t47, _t74);
        float _t84 = Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * Math.sin(_t110);
        float _t115 = _t60 * Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
            _t123 = _t108 * _t98;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
            _t123 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest[destOffset] = Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv;
                    dest[destOffset + 1] = Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv;
                    dest[destOffset + 2] = Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv;
                    dest[destOffset + 3] = Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv;
                } else {
                    dest[destOffset] = Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                    dest[destOffset + 1] = Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                    dest[destOffset + 2] = Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                    dest[destOffset + 3] = Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
                }
            } else {
                dest[destOffset] = Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv;
                dest[destOffset + 1] = Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv;
                dest[destOffset + 2] = Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv;
                dest[destOffset + 3] = Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv;
            }
        } else {
            dest[destOffset] = Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = Math.fma(t, _othery - _selfy, _selfy);
            dest[destOffset + 2] = Math.fma(t, _otherz - _selfz, _selfz);
            dest[destOffset + 3] = Math.fma(t, _otherw - _selfw, _selfw);
        }
        return dest;
    }

    public static float[] absolute_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = java.lang.Math.abs(src[srcOffset]);
        dest[destOffset + 1] = java.lang.Math.abs(_selfy);
        dest[destOffset + 2] = java.lang.Math.abs(_selfz);
        dest[destOffset + 3] = java.lang.Math.abs(_selfw);
        return dest;
    }

    public static float[] addScaled_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float scalar) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _by = b[bOffset + 1];
        float _bz = b[bOffset + 2];
        float _bw = b[bOffset + 3];
        dest[destOffset] = Math.fma(scalar, b[bOffset], src[srcOffset]);
        dest[destOffset + 1] = Math.fma(scalar, _by, _selfy);
        dest[destOffset + 2] = Math.fma(scalar, _bz, _selfz);
        dest[destOffset + 3] = Math.fma(scalar, _bw, _selfw);
        return dest;
    }

    public static float[] addScaled_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _by = b[bOffset + 1];
        float _bz = b[bOffset + 2];
        float _bw = b[bOffset + 3];
        float _cy = c[cOffset + 1];
        float _cz = c[cOffset + 2];
        float _cw = c[cOffset + 3];
        dest[destOffset] = Math.fma(b[bOffset], c[cOffset], src[srcOffset]);
        dest[destOffset + 1] = Math.fma(_by, _cy, _selfy);
        dest[destOffset + 2] = Math.fma(_bz, _cz, _selfz);
        dest[destOffset + 3] = Math.fma(_bw, _cw, _selfw);
        return dest;
    }

    public static float angleBetween_degenerate(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t6 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t36 = Math.fma(_t16, _t17, -(_t18 * _t19));
        float _t37 = Math.fma(_t20, _t21, -(_t22 * _t23));
        float _t38 = Math.fma(_t18, _t21, -(_t22 * _t17));
        float _t39 = Math.fma(_t16, _t21, -(_t22 * _t19));
        float _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        float _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        float _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        return angleBetween_degenerate_s7acb3cff_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t51, _t36 * _t51, _t41 * _t51, _t40 * _t51, _t39 * _t51, _t37 * _t51, _t38 * _t51);
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_s7acb3cff_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t51, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63) {
        return Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static float angleBetween_degenerate(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t6 = unitScale(_otherz, _otherw, java.lang.Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        return angleBetween_degenerate_sbffa6adf_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, Math.fma(_t16, _t17, -(_t18 * _t19)), Math.fma(_t20, _t21, -(_t22 * _t23)), Math.fma(_t18, _t21, -(_t22 * _t17)), Math.fma(_t16, _t21, -(_t22 * _t19)), Math.fma(_t18, _t23, -(_t20 * _t17)), Math.fma(_t16, _t23, -(_t20 * _t19)));
    }

    /** Piece 2 of {@code angleBetween_degenerate}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_sbffa6adf_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t36, float _t37, float _t38, float _t39, float _t40, float _t41) {
        float _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        float _t58 = _t36 * _t51;
        float _t59 = _t41 * _t51;
        float _t60 = _t40 * _t51;
        float _t61 = _t39 * _t51;
        float _t62 = _t37 * _t51;
        float _t63 = _t38 * _t51;
        return Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static float[] clamp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float min, float max) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = java.lang.Math.min(java.lang.Math.max(src[srcOffset], min), max);
        dest[destOffset + 1] = java.lang.Math.min(java.lang.Math.max(_selfy, min), max);
        dest[destOffset + 2] = java.lang.Math.min(java.lang.Math.max(_selfz, min), max);
        dest[destOffset + 3] = java.lang.Math.min(java.lang.Math.max(_selfw, min), max);
        return dest;
    }

    public static float[] clamp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] min, int minOffset, float[] max, int maxOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _miny = min[minOffset + 1];
        float _minz = min[minOffset + 2];
        float _minw = min[minOffset + 3];
        float _maxy = max[maxOffset + 1];
        float _maxz = max[maxOffset + 2];
        float _maxw = max[maxOffset + 3];
        dest[destOffset] = java.lang.Math.min(java.lang.Math.max(src[srcOffset], min[minOffset]), max[maxOffset]);
        dest[destOffset + 1] = java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy);
        dest[destOffset + 2] = java.lang.Math.min(java.lang.Math.max(_selfz, _minz), _maxz);
        dest[destOffset + 3] = java.lang.Math.min(java.lang.Math.max(_selfw, _minw), _maxw);
        return dest;
    }

    public static float[] faceforward_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t4 = Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
        dest[destOffset] = src[srcOffset] * _t4;
        dest[destOffset + 1] = _selfy * _t4;
        dest[destOffset + 2] = _selfz * _t4;
        dest[destOffset + 3] = _selfw * _t4;
        return dest;
    }

    public static float[] faceforward_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] I, int IOffset, float[] Nref, int NrefOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t4 = Math.fma(I[IOffset + 3], Nref[NrefOffset + 3], Math.fma(I[IOffset + 2], Nref[NrefOffset + 2], Math.fma(I[IOffset], Nref[NrefOffset], I[IOffset + 1] * Nref[NrefOffset + 1]))) < 0.0f ? 1.0f : -1.0f;
        dest[destOffset] = src[srcOffset] * _t4;
        dest[destOffset + 1] = _selfy * _t4;
        dest[destOffset + 2] = _selfz * _t4;
        dest[destOffset + 3] = _selfw * _t4;
        return dest;
    }

    public static float[] inverse_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = 1.0f / src[srcOffset];
        dest[destOffset + 1] = 1.0f / _selfy;
        dest[destOffset + 2] = 1.0f / _selfz;
        dest[destOffset + 3] = 1.0f / _selfw;
        return dest;
    }

    public static float[] max_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = java.lang.Math.max(src[srcOffset], scalar);
        dest[destOffset + 1] = java.lang.Math.max(_selfy, scalar);
        dest[destOffset + 2] = java.lang.Math.max(_selfz, scalar);
        dest[destOffset + 3] = java.lang.Math.max(_selfw, scalar);
        return dest;
    }

    public static float[] max_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = java.lang.Math.max(src[srcOffset], other[otherOffset]);
        dest[destOffset + 1] = java.lang.Math.max(_selfy, _othery);
        dest[destOffset + 2] = java.lang.Math.max(_selfz, _otherz);
        dest[destOffset + 3] = java.lang.Math.max(_selfw, _otherw);
        return dest;
    }

    public static float[] min_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = java.lang.Math.min(src[srcOffset], scalar);
        dest[destOffset + 1] = java.lang.Math.min(_selfy, scalar);
        dest[destOffset + 2] = java.lang.Math.min(_selfz, scalar);
        dest[destOffset + 3] = java.lang.Math.min(_selfw, scalar);
        return dest;
    }

    public static float[] min_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = java.lang.Math.min(src[srcOffset], other[otherOffset]);
        dest[destOffset + 1] = java.lang.Math.min(_selfy, _othery);
        dest[destOffset + 2] = java.lang.Math.min(_selfz, _otherz);
        dest[destOffset + 3] = java.lang.Math.min(_selfw, _otherw);
        return dest;
    }

    public static float[] normalize_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest[destOffset] = _selfx * _t4;
            dest[destOffset + 1] = _selfy * _t4;
            dest[destOffset + 2] = _selfz * _t4;
            dest[destOffset + 3] = _selfw * _t4;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    public static float[] normalizeMul_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float length) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest[destOffset] = _selfx * _t5;
            dest[destOffset + 1] = _selfy * _t5;
            dest[destOffset + 2] = _selfz * _t5;
            dest[destOffset + 3] = _selfw * _t5;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    public static float[] outerProduct_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
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

    public static float[] outerProduct_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] row, int rowOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _rowx = row[rowOffset];
        float _rowy = row[rowOffset + 1];
        float _rowz = row[rowOffset + 2];
        float _roww = row[rowOffset + 3];
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

    public static float[] project_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] onto, int ontoOffset) {
        float _ontox = onto[ontoOffset];
        float _ontoy = onto[ontoOffset + 1];
        float _ontoz = onto[ontoOffset + 2];
        float _ontow = onto[ontoOffset + 3];
        float _t9 = Math.fma(_ontow, src[srcOffset + 3], Math.fma(_ontoz, src[srcOffset + 2], Math.fma(_ontox, src[srcOffset], _ontoy * src[srcOffset + 1]))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest[destOffset] = _ontox * _t9;
        dest[destOffset + 1] = _ontoy * _t9;
        dest[destOffset + 2] = _ontoz * _t9;
        dest[destOffset + 3] = _ontow * _t9;
        return dest;
    }

    public static float[] projectOnPlane_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _normalx = normal[normalOffset];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _normalw = normal[normalOffset + 3];
        float _t3 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest[destOffset] = Math.fma(-_normalx, _t3, _selfx);
        dest[destOffset + 1] = Math.fma(-_normaly, _t3, _selfy);
        dest[destOffset + 2] = Math.fma(-_normalz, _t3, _selfz);
        dest[destOffset + 3] = Math.fma(-_normalw, _t3, _selfw);
        return dest;
    }

    public static float[] reflect_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _normalx = normal[normalOffset];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _normalw = normal[normalOffset + 3];
        float _t4 = 2.0f * Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest[destOffset] = Math.fma(-_normalx, _t4, _selfx);
        dest[destOffset + 1] = Math.fma(-_normaly, _t4, _selfy);
        dest[destOffset + 2] = Math.fma(-_normalz, _t4, _selfz);
        dest[destOffset + 3] = Math.fma(-_normalw, _t4, _selfw);
        return dest;
    }

    public static float[] refract_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t4 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dest[destOffset] = Math.fma(eta, _selfx, -(normalX * _t11));
            dest[destOffset + 1] = Math.fma(eta, _selfy, -(normalY * _t11));
            dest[destOffset + 2] = Math.fma(eta, _selfz, -(normalZ * _t11));
            dest[destOffset + 3] = Math.fma(eta, _selfw, -(normalW * _t11));
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    public static float[] refract_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset, float eta) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _normalx = normal[normalOffset];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _normalw = normal[normalOffset + 3];
        float _t4 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dest[destOffset] = Math.fma(eta, _selfx, -(_normalx * _t11));
            dest[destOffset + 1] = Math.fma(eta, _selfy, -(_normaly * _t11));
            dest[destOffset + 2] = Math.fma(eta, _selfz, -(_normalz * _t11));
            dest[destOffset + 3] = Math.fma(eta, _selfw, -(_normalw * _t11));
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    public static float[] sqrt_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = (float) java.lang.Math.sqrt(src[srcOffset]);
        dest[destOffset + 1] = (float) java.lang.Math.sqrt(_selfy);
        dest[destOffset + 2] = (float) java.lang.Math.sqrt(_selfz);
        dest[destOffset + 3] = (float) java.lang.Math.sqrt(_selfw);
        return dest;
    }

    public static float[] preMul_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _mat10 = mat[matOffset + 1];
        float _mat20 = mat[matOffset + 2];
        float _mat30 = mat[matOffset + 3];
        float _mat11 = mat[matOffset + 5];
        float _mat21 = mat[matOffset + 6];
        float _mat31 = mat[matOffset + 7];
        float _mat12 = mat[matOffset + 9];
        float _mat22 = mat[matOffset + 10];
        float _mat32 = mat[matOffset + 11];
        float _mat13 = mat[matOffset + 13];
        float _mat23 = mat[matOffset + 14];
        float _mat33 = mat[matOffset + 15];
        dest[destOffset] = Math.fma(mat[matOffset + 12], _selfw, Math.fma(mat[matOffset + 8], _selfz, Math.fma(mat[matOffset], _selfx, mat[matOffset + 4] * _selfy)));
        dest[destOffset + 1] = Math.fma(_mat13, _selfw, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest[destOffset + 2] = Math.fma(_mat23, _selfw, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        dest[destOffset + 3] = Math.fma(_mat33, _selfw, Math.fma(_mat32, _selfz, Math.fma(_mat30, _selfx, _mat31 * _selfy)));
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
