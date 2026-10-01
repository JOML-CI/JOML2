// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float3Ops} and its sibling kernel units. Not public API.
 */
public final class Float3OpsKernelsArray {
    private Float3OpsKernelsArray() {}

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src[srcOffset + 0];
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
        float _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t29 = (1.0f / (float) Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t49, _t50, _t52;
        if (Math.abs(_t31) < Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        return slerp_degenerate_s339548db_1(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t9, _t10, _t11, 1.0f / _t15, _t24, _t25, (1.0f / (float) Math.sqrt(_t24)), _t31, _t33, _t35, t * (float) Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s339548db_1(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float _selfx, float _selfy, float _selfz, float _t9, float _t10, float _t11, float _t15_inv, float _t24, float _t25, float _t28, float _t31, float _t33, float _t35, float _t48, float _t49, float _t50, float _t52) {
        float _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        float _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = Math.fma(_t73, _t31, _t61);
        float _t75 = Math.fma(_t73, _t33, _t62);
        float _t76 = Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        return slerp_degenerate_s339548db_2(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0f / (float) Math.sqrt(_t91)), t * (float) Math.atan2((float) Math.sqrt(_t91), _t53 * _t78));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s339548db_2(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t85, float _t86, float _t87, float _t91, float _t93, float _t95) {
        float _t104, _t105, _t106;
        if (_t91 > 0.0f) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        return slerp_degenerate_s339548db_3(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * (float) Math.sin(_t95), _t48 * (float) Math.cos(_t95), _t104, _t105, _t106);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s339548db_3(float[] dest, int destOffset, float otherX, float otherY, float otherZ, float t, float _selfx, float _selfy, float _selfz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t99, float _t100, float _t104, float _t105, float _t106) {
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    dest[destOffset + 0] = Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv;
                    dest[destOffset + 1] = Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv;
                    dest[destOffset + 2] = Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv;
                } else {
                    dest[destOffset + 0] = Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                    dest[destOffset + 1] = Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                    dest[destOffset + 2] = Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
                }
            } else {
                dest[destOffset + 0] = Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                dest[destOffset + 1] = Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                dest[destOffset + 2] = Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
            }
        } else {
            dest[destOffset + 0] = Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = Math.fma(t, otherY - _selfy, _selfy);
            dest[destOffset + 2] = Math.fma(t, otherZ - _selfz, _selfz);
        }
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
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
        float _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t29 = (1.0f / (float) Math.sqrt(_t25));
        return slerp_degenerate_s83a862c0_1(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t9, _t10, _t11, 1.0f / _t15, _t24, _t25, (1.0f / (float) Math.sqrt(_t24)), _t29 * _t12, _t29 * _t13, _t29 * _t14, t * (float) Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) Math.sqrt(_t25) * (_t15 / _t2));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_1(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t9, float _t10, float _t11, float _t15_inv, float _t24, float _t25, float _t28, float _t31, float _t33, float _t35, float _t48) {
        float _t49, _t50, _t52;
        if (Math.abs(_t31) < Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        float _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = Math.fma(_t73, _t31, _t61);
        float _t75 = Math.fma(_t73, _t33, _t62);
        float _t76 = Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        return slerp_degenerate_s83a862c0_2(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t78, _t74 * _t78, _t75 * _t78, _t76 * _t78);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_2(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t48, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t78, float _t85, float _t86, float _t87) {
        float _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) Math.sqrt(_t91));
        float _t95 = t * (float) Math.atan2((float) Math.sqrt(_t91), _t53 * _t78);
        float _t104, _t105, _t106;
        if (_t91 > 0.0f) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        return slerp_degenerate_s83a862c0_3(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * (float) Math.sin(_t95), _t48 * (float) Math.cos(_t95), _t104, _t105, _t106);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_3(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _t15_inv, float _t24, float _t25, float _t31, float _t33, float _t35, float _t53, float _t69, float _t70, float _t71, float _t74, float _t75, float _t76, float _t99, float _t100, float _t104, float _t105, float _t106) {
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    dest[destOffset + 0] = Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv;
                    dest[destOffset + 1] = Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv;
                    dest[destOffset + 2] = Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv;
                } else {
                    dest[destOffset + 0] = Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                    dest[destOffset + 1] = Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                    dest[destOffset + 2] = Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
                }
            } else {
                dest[destOffset + 0] = Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                dest[destOffset + 1] = Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                dest[destOffset + 2] = Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
            }
        } else {
            dest[destOffset + 0] = Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = Math.fma(t, _othery - _selfy, _selfy);
            dest[destOffset + 2] = Math.fma(t, _otherz - _selfz, _selfz);
        }
        return dest;
    }

    public static float angleBetween_degenerate(float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
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
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        float _t23 = unitScale(_t21, _t22, _t20);
        float _t27 = _t20 * _t23;
        float _t28 = _t21 * _t23;
        float _t29 = _t22 * _t23;
        return (float) Math.atan2((float) Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static float angleBetween_degenerate(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
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
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        float _t23 = unitScale(_t21, _t22, _t20);
        float _t27 = _t20 * _t23;
        float _t28 = _t21 * _t23;
        float _t29 = _t22 * _t23;
        return (float) Math.atan2((float) Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static float orientedAngle_degenerate(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src[srcOffset + 0];
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
        float _t23 = Math.fma(_t9, _t10, -(_t11 * _t12));
        float _t24 = Math.fma(_t13, _t12, -(_t9 * _t14));
        float _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        float _t27 = unitScale(_t24, _t25, _t23);
        float _t31 = _t23 * _t27;
        float _t32 = _t24 * _t27;
        float _t33 = _t25 * _t27;
        float _t40 = (float) Math.atan2((float) Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(normalZ * _t0, _t31, Math.fma(normalX * _t0, _t32, normalY * _t0 * _t33)) < 0.0f ? -_t40 : _t40;
    }

    public static float orientedAngle_degenerate(float[] src, int srcOffset, float[] other, int otherOffset, float[] normal, int normalOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _normalx = normal[normalOffset + 0];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _t0 = unitScale(_normalx, _normaly, _normalz);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _othery * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = _otherx * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = _otherz * _t1;
        float _t14 = _selfz * _t2;
        float _t23 = Math.fma(_t9, _t10, -(_t11 * _t12));
        float _t24 = Math.fma(_t13, _t12, -(_t9 * _t14));
        float _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        float _t27 = unitScale(_t24, _t25, _t23);
        float _t31 = _t23 * _t27;
        float _t32 = _t24 * _t27;
        float _t33 = _t25 * _t27;
        float _t40 = (float) Math.atan2((float) Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(_normalz * _t0, _t31, Math.fma(_normalx * _t0, _t32, _normaly * _t0 * _t33)) < 0.0f ? -_t40 : _t40;
    }

    public static float[] triangleNormal_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t19 = Math.min(1.0f, unitScale(Math.max(Math.abs(_selfz), Math.abs(p1Z)), Math.max(Math.abs(p2Z), Math.abs(Math.max(Math.abs(_selfx), Math.abs(p1X)))), Math.max(Math.abs(Math.max(Math.abs(p2X), Math.abs(_selfy))), Math.abs(Math.max(Math.abs(p1Y), Math.abs(p2Y))))));
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
        return triangleNormal_degenerate_sc0703c02_1(dest, destOffset, Math.fma(_t52, _t53, -(_t54 * _t55)), Math.fma(_t54, _t56, -(_t57 * _t53)), Math.fma(_t57, _t55, -(_t52 * _t56)));
    }

    /** Piece 2 of {@code triangleNormal_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] triangleNormal_degenerate_sc0703c02_1(float[] dest, int destOffset, float _t64, float _t65, float _t66) {
        float _t67 = unitScale(_t65, _t66, _t64);
        float _t71 = _t64 * _t67;
        float _t72 = _t65 * _t67;
        float _t73 = _t66 * _t67;
        float _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        float _t77 = (1.0f / (float) Math.sqrt(_t76));
        if (_t76 != 0.0f) {
            dest[destOffset + 0] = _t77 * _t72;
            dest[destOffset + 1] = _t77 * _t73;
            dest[destOffset + 2] = _t77 * _t71;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    public static float[] triangleNormal_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _p1x = p1[p1Offset + 0];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p2x = p2[p2Offset + 0];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _t19 = Math.min(1.0f, unitScale(Math.max(Math.abs(_selfz), Math.abs(_p1z)), Math.max(Math.abs(_p2z), Math.abs(Math.max(Math.abs(_selfx), Math.abs(_p1x)))), Math.max(Math.abs(Math.max(Math.abs(_p2x), Math.abs(_selfy))), Math.abs(Math.max(Math.abs(_p1y), Math.abs(_p2y))))));
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
        return triangleNormal_degenerate_s1fd73457_1(dest, destOffset, _t38 * _t44, _t41 * _t45, _t39 * _t44, _t42 * _t45, _t43 * _t45, _t40 * _t44);
    }

    /** Piece 2 of {@code triangleNormal_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] triangleNormal_degenerate_s1fd73457_1(float[] dest, int destOffset, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57) {
        float _t64 = Math.fma(_t52, _t53, -(_t54 * _t55));
        float _t65 = Math.fma(_t54, _t56, -(_t57 * _t53));
        float _t66 = Math.fma(_t57, _t55, -(_t52 * _t56));
        float _t67 = unitScale(_t65, _t66, _t64);
        float _t71 = _t64 * _t67;
        float _t72 = _t65 * _t67;
        float _t73 = _t66 * _t67;
        float _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        float _t77 = (1.0f / (float) Math.sqrt(_t76));
        if (_t76 != 0.0f) {
            dest[destOffset + 0] = _t77 * _t72;
            dest[destOffset + 1] = _t77 * _t73;
            dest[destOffset + 2] = _t77 * _t71;
        } else {
            dest[destOffset + 0] = 0.0f;
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

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
