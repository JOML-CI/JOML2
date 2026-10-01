// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3Ops} whose leading storage
 * parameter is a {@code double[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double3Ops} and its sibling kernel units. Not public API.
 */
public final class Double3OpsKernelsArray {
    private Double3OpsKernelsArray() {}

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = java.lang.Math.min(_t2, _t1);
        double _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        return slerp_degenerate_s78c5ed85_1(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s78c5ed85_1(double[] dest, int destOffset, double otherX, double otherY, double otherZ, double t, double _selfx, double _selfy, double _selfz, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48, double _t49, double _t50, double _t52) {
        double _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        double _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = Math.fma(_t73, _t31, _t61);
        double _t75 = Math.fma(_t73, _t33, _t62);
        double _t76 = Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        return slerp_degenerate_s78c5ed85_2(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0 / java.lang.Math.sqrt(_t91)), _t95, _t48 * Math.sin(_t95));
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s78c5ed85_2(double[] dest, int destOffset, double otherX, double otherY, double otherZ, double t, double _selfx, double _selfy, double _selfz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t48, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t85, double _t86, double _t87, double _t91, double _t93, double _t95, double _t99) {
        double _t104, _t105, _t106;
        if (_t91 > 0.0) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        return slerp_degenerate_s78c5ed85_3(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t99, _t48 * Math.cos(_t95), _t104, _t105, _t106);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s78c5ed85_3(double[] dest, int destOffset, double otherX, double otherY, double otherZ, double t, double _selfx, double _selfy, double _selfz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t99, double _t100, double _t104, double _t105, double _t106) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    dest[destOffset] = Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv;
                    dest[destOffset + 1] = Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv;
                    dest[destOffset + 2] = Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv;
                } else {
                    dest[destOffset] = Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                    dest[destOffset + 1] = Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                    dest[destOffset + 2] = Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
                }
            } else {
                dest[destOffset] = Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                dest[destOffset + 1] = Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                dest[destOffset + 2] = Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
            }
        } else {
            dest[destOffset] = Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = Math.fma(t, otherY - _selfy, _selfy);
            dest[destOffset + 2] = Math.fma(t, otherZ - _selfz, _selfz);
        }
        return dest;
    }

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = java.lang.Math.min(_t2, _t1);
        double _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        return slerp_degenerate_s29a2f4e0_1(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t29 * _t12, _t29 * _t13, _t29 * _t14, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s29a2f4e0_1(double[] dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48) {
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        double _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = Math.fma(_t73, _t31, _t61);
        double _t75 = Math.fma(_t73, _t33, _t62);
        double _t76 = Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        return slerp_degenerate_s29a2f4e0_2(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t78, _t74 * _t78, _t75 * _t78, _t76 * _t78);
    }

    /** Piece 3 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s29a2f4e0_2(double[] dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t48, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t78, double _t85, double _t86, double _t87) {
        double _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t104, _t105, _t106;
        if (_t91 > 0.0) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        return slerp_degenerate_s29a2f4e0_3(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * Math.sin(_t95), _t48 * Math.cos(_t95), _t104, _t105, _t106);
    }

    /** Piece 4 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_degenerate_s29a2f4e0_3(double[] dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t99, double _t100, double _t104, double _t105, double _t106) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    dest[destOffset] = Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv;
                    dest[destOffset + 1] = Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv;
                    dest[destOffset + 2] = Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv;
                } else {
                    dest[destOffset] = Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                    dest[destOffset + 1] = Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                    dest[destOffset + 2] = Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
                }
            } else {
                dest[destOffset] = Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv;
                dest[destOffset + 1] = Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv;
                dest[destOffset + 2] = Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv;
            }
        } else {
            dest[destOffset] = Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = Math.fma(t, _othery - _selfy, _selfy);
            dest[destOffset + 2] = Math.fma(t, _otherz - _selfz, _selfz);
        }
        return dest;
    }

    public static double angleBetween_degenerate(double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = otherZ * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = otherY * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = otherX * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        double _t23 = unitScale(_t21, _t22, _t20);
        double _t27 = _t20 * _t23;
        double _t28 = _t21 * _t23;
        double _t29 = _t22 * _t23;
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static double angleBetween_degenerate(double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _t0 = unitScale(_otherx, _othery, _otherz);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = _otherz * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = _othery * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = _otherx * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        double _t23 = unitScale(_t21, _t22, _t20);
        double _t27 = _t20 * _t23;
        double _t28 = _t21 * _t23;
        double _t29 = _t22 * _t23;
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static double orientedAngle_degenerate(double[] src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = unitScale(normalX, normalY, normalZ);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherY * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = otherX * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = _selfz * _t2;
        double _t23 = Math.fma(_t9, _t10, -(_t11 * _t12));
        double _t24 = Math.fma(_t13, _t12, -(_t9 * _t14));
        double _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        double _t27 = unitScale(_t24, _t25, _t23);
        double _t31 = _t23 * _t27;
        double _t32 = _t24 * _t27;
        double _t33 = _t25 * _t27;
        double _t40 = Math.atan2(java.lang.Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(normalZ * _t0, _t31, Math.fma(normalX * _t0, _t32, normalY * _t0 * _t33)) < 0.0 ? -_t40 : _t40;
    }

    public static double orientedAngle_degenerate(double[] src, int srcOffset, double[] other, int otherOffset, double[] normal, int normalOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _normalx = normal[normalOffset];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        double _t0 = unitScale(_normalx, _normaly, _normalz);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _othery * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = _otherx * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = _otherz * _t1;
        double _t14 = _selfz * _t2;
        double _t23 = Math.fma(_t9, _t10, -(_t11 * _t12));
        double _t24 = Math.fma(_t13, _t12, -(_t9 * _t14));
        double _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        double _t27 = unitScale(_t24, _t25, _t23);
        double _t31 = _t23 * _t27;
        double _t32 = _t24 * _t27;
        double _t33 = _t25 * _t27;
        double _t40 = Math.atan2(java.lang.Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(_normalz * _t0, _t31, Math.fma(_normalx * _t0, _t32, _normaly * _t0 * _t33)) < 0.0 ? -_t40 : _t40;
    }

    public static double[] triangleNormal_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t19 = java.lang.Math.min(1.0, unitScale(java.lang.Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), java.lang.Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        return triangleNormal_degenerate_s299ec516_1(dest, destOffset, Math.fma(_t52, _t53, -(_t54 * _t55)), Math.fma(_t54, _t56, -(_t57 * _t53)), Math.fma(_t57, _t55, -(_t52 * _t56)));
    }

    /** Piece 2 of {@code triangleNormal_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] triangleNormal_degenerate_s299ec516_1(double[] dest, int destOffset, double _t64, double _t65, double _t66) {
        double _t67 = unitScale(_t65, _t66, _t64);
        double _t71 = _t64 * _t67;
        double _t72 = _t65 * _t67;
        double _t73 = _t66 * _t67;
        double _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        double _t77 = (1.0 / java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0) {
            dest[destOffset] = _t77 * _t72;
            dest[destOffset + 1] = _t77 * _t73;
            dest[destOffset + 2] = _t77 * _t71;
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    public static double[] triangleNormal_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p2x = p2[p2Offset];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _t19 = java.lang.Math.min(1.0, unitScale(java.lang.Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), java.lang.Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        double _t38 = _p1x * _t19 - _t30;
        double _t39 = _p1y * _t19 - _t32;
        double _t40 = _p1z * _t19 - _t34;
        double _t41 = _p2y * _t19 - _t32;
        double _t42 = _p2x * _t19 - _t30;
        double _t43 = _p2z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        return triangleNormal_degenerate_sd738dc3_1(dest, destOffset, _t38 * _t44, _t41 * _t45, _t39 * _t44, _t42 * _t45, _t43 * _t45, _t40 * _t44);
    }

    /** Piece 2 of {@code triangleNormal_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] triangleNormal_degenerate_sd738dc3_1(double[] dest, int destOffset, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57) {
        double _t64 = Math.fma(_t52, _t53, -(_t54 * _t55));
        double _t65 = Math.fma(_t54, _t56, -(_t57 * _t53));
        double _t66 = Math.fma(_t57, _t55, -(_t52 * _t56));
        double _t67 = unitScale(_t65, _t66, _t64);
        double _t71 = _t64 * _t67;
        double _t72 = _t65 * _t67;
        double _t73 = _t66 * _t67;
        double _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        double _t77 = (1.0 / java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0) {
            dest[destOffset] = _t77 * _t72;
            dest[destOffset + 1] = _t77 * _t73;
            dest[destOffset + 2] = _t77 * _t71;
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
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
