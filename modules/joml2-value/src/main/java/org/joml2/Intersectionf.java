// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

public final class Intersectionf {

    private Intersectionf() {}

    /** {@link #findClosestPointOnTriangle} region code: the closest point is the first vertex of the triangle. */
    public static final int POINT_ON_TRIANGLE_VERTEX_0 = 1;

    /** {@link #findClosestPointOnTriangle} region code: the closest point is the second vertex of the triangle. */
    public static final int POINT_ON_TRIANGLE_VERTEX_1 = 2;

    /** {@link #findClosestPointOnTriangle} region code: the closest point is the third vertex of the triangle. */
    public static final int POINT_ON_TRIANGLE_VERTEX_2 = 3;

    /** {@link #findClosestPointOnTriangle} region code: the closest point lies on the edge between the first and second vertex. */
    public static final int POINT_ON_TRIANGLE_EDGE_01 = 4;

    /** {@link #findClosestPointOnTriangle} region code: the closest point lies on the edge between the second and third vertex. */
    public static final int POINT_ON_TRIANGLE_EDGE_12 = 5;

    /** {@link #findClosestPointOnTriangle} region code: the closest point lies on the edge between the third and first vertex. */
    public static final int POINT_ON_TRIANGLE_EDGE_20 = 6;

    /** {@link #findClosestPointOnTriangle} region code: the closest point lies on the face of the triangle. */
    public static final int POINT_ON_TRIANGLE_FACE = 7;

    /** Line-segment classification code: one end point of the segment lies inside the box (rectangle in 2D), so the segment crosses exactly one side. */
    public static final int ONE_INTERSECTION = 1;

    /** Line-segment classification code: the segment crosses two sides of the box (edges in 2D), or lies on a side or an edge of it. */
    public static final int TWO_INTERSECTION = 2;

    /** Line-segment classification code: the segment lies completely inside the box (rectangle in 2D). */
    public static final int INSIDE = 3;

    /**
     * Determine the point on the triangle with the three given vertices that is closest to the
     * given point.
     * <p>
     * The closest point is found by a Voronoi-region cascade: it is one of the three vertices, a
     * point on one of the three edges, or the orthogonal projection of the given point onto the
     * triangle's plane when that projection falls inside the triangle.
     * {@code findClosestPointOnTriangle} computes the very same point and additionally reports
     * which of those seven regions it came from.
     * <p>
     * The triangle is assumed to be non-degenerate: the face case divides by the sum of the three
     * barycentric numerators, which is zero for a triangle of zero area.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.5 "Closest Point on Triangle to
     * Point"
     * <p>
     * Valid input: {@code (v0X, v0Y, v0Z)}, {@code (v1X, v1Y, v1Z)} and {@code (v2X, v2Y, v2Z)}
     * must not be collinear.
     *
     * @param v0X the x coordinate of the first vertex of the triangle
     * @param v0Y the y coordinate of the first vertex of the triangle
     * @param v0Z the z coordinate of the first vertex of the triangle
     * @param v1X the x coordinate of the second vertex of the triangle
     * @param v1Y the y coordinate of the second vertex of the triangle
     * @param v1Z the z coordinate of the second vertex of the triangle
     * @param v2X the x coordinate of the third vertex of the triangle
     * @param v2Y the y coordinate of the third vertex of the triangle
     * @param v2Z the z coordinate of the third vertex of the triangle
     * @param pX the x coordinate of the point to find the closest point to
     * @param pY the y coordinate of the point to find the closest point to
     * @param pZ the z coordinate of the point to find the closest point to
     * @return the point on the triangle closest to the given point
     */
    public static Float3 closestPointOnTriangle(float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z, float pX, float pY, float pZ) {
        float _t0 = pZ - v0Z;
        float _t1 = v1Z - v0Z;
        float _t2 = pX - v0X;
        float _t3 = v1X - v0X;
        float _t4 = pY - v0Y;
        float _t5 = v1Y - v0Y;
        float _t6 = v2Z - v0Z;
        float _t7 = v2X - v0X;
        float _t8 = v2Y - v0Y;
        float _t9 = pZ - v1Z;
        float _t10 = pX - v1X;
        float _t11 = pY - v1Y;
        float _t12 = pZ - v2Z;
        float _t13 = pX - v2X;
        float _t14 = pY - v2Y;
        float _t15 = v2X - v1X;
        float _t16 = v2Z - v1Z;
        float _t17 = v2Y - v1Y;
        float _t18 = -_t14;
        float _t19 = -_t12;
        float _t44 = Math.fma(_t1, _t7, -(_t3 * _t6));
        float _t45 = Math.fma(_t5, _t6, -(_t1 * _t8));
        float _t46 = Math.fma(_t3, _t8, -(_t5 * _t7));
        float _t54 = Math.fma(_t0, _t1, Math.fma(_t2, _t3, _t4 * _t5));
        float _t55 = Math.fma(_t0, _t6, Math.fma(_t2, _t7, _t4 * _t8));
        float _t56 = Math.fma(_t9, _t1, Math.fma(_t10, _t3, _t11 * _t5));
        float _t57 = Math.fma(_t1, _t1, Math.fma(_t3, _t3, _t5 * _t5));
        float _sp0 = _t54 / _t57;
        float _t58 = Math.fma(_t12, _t6, Math.fma(_t13, _t7, _t14 * _t8));
        float _t59 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        float _sp1 = _t55 / _t59;
        float _t60 = Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17));
        float _t64 = Math.fma(_t10, _t15, Math.fma(-_t9, _t1, Math.fma(-_t11, _t5, Math.fma(_t11, _t8, _t9 * _t6))));
        float _sp2 = _t64 / _t60;
        float _t66 = Math.fma(_t2, _t44, -(_t4 * _t45));
        float _t67 = Math.fma(_t4, _t46, -(_t0 * _t44));
        float _t68 = Math.fma(_t0, _t45, -(_t2 * _t46));
        float _t72 = Math.fma(_t46, _t46, Math.fma(_t45, _t45, _t44 * _t44));
        if (!(_t72 > 1.1754944E-38f && _t72 < Float.POSITIVE_INFINITY)) return closestPointOnTriangle_degenerate(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, pX, pY, pZ);
        float _sp3 = Math.fma(_t0, _t46, Math.fma(_t2, _t45, _t4 * _t44)) / _t72;
        if ((_t54 <= 0.0f) && (_t55 <= 0.0f)) return Joml.float3(v0X, v0Y, v0Z);
        if ((_t56 >= 0.0f) && (Math.fma(_t9, _t6, Math.fma(_t10, _t7, _t11 * _t8)) <= _t56)) return Joml.float3(v1X, v1Y, v1Z);
        if ((-Math.fma(_t9, _t66, Math.fma(_t10, _t67, _t11 * _t68)) <= 0.0f) && ((_t54 >= 0.0f) && ((_t56 <= 0.0f) && (_t57 > 0.0f)))) return Joml.float3(v0X + _sp0 * _t3, v0Y + _sp0 * _t5, v0Z + _sp0 * _t1);
        if ((_t58 >= 0.0f) && (Math.fma(_t12, _t1, Math.fma(_t13, _t3, _t14 * _t5)) <= _t58)) return Joml.float3(v2X, v2Y, v2Z);
        if ((Math.fma(_t12, _t66, Math.fma(_t13, _t67, _t14 * _t68)) <= 0.0f) && ((_t55 >= 0.0f) && ((_t58 <= 0.0f) && (_t59 > 0.0f)))) return Joml.float3(v0X + _sp1 * _t7, v0Y + _sp1 * _t8, v0Z + _sp1 * _t6);
        if ((Math.fma(_t11, _t68, Math.fma(_t9, _t66, Math.fma(_t18, _t68, Math.fma(_t19, _t66, Math.fma(_t15, _t67, _t72))))) <= 0.0f) && ((_t64 >= 0.0f) && ((Math.fma(_t13, v1X - v2X, Math.fma(_t19, _t6, Math.fma(_t18, _t8, Math.fma(_t14, _t5, _t12 * _t1)))) >= 0.0f) && (_t60 > 0.0f)))) return Joml.float3(v1X + _t15 * _sp2, v1Y + _t17 * _sp2, v1Z + _t16 * _sp2);
        return Joml.float3(pX - _sp3 * _t45, pY - _sp3 * _t44, pZ - _sp3 * _t46);
    }

    /** Out-of-range path of {@code closestPointOnTriangle}: it leaves here when a value its fast formula needs is zero, NaN or outside the normal floating-point range; reached only through it. */
    private static Float3 closestPointOnTriangle_degenerate(float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z, float pX, float pY, float pZ) {
        float _t0 = pZ - v0Z;
        float _t1 = v1X - v0X;
        float _t2 = v1Y - v0Y;
        float _t3 = v1Z - v0Z;
        float _t4 = v2X - v0X;
        float _t5 = v2Y - v0Y;
        float _t6 = v2Z - v0Z;
        float _t7 = pX - v2X;
        float _t8 = pY - v2Y;
        float _t9 = pZ - v2Z;
        float _t10 = pX - v0X;
        float _t11 = pY - v0Y;
        float _t12 = pX - v1X;
        float _t13 = pY - v1Y;
        float _t14 = pZ - v1Z;
        float _t15 = v2Z - v1Z;
        float _t16 = v2X - v1X;
        float _t17 = v2Y - v1Y;
        float _t48 = Math.min(unitScale(Math.max(Math.abs(_t1), Math.abs(_t2)), Math.max(Math.abs(_t3), Math.abs(_t4)), Math.max(Math.abs(_t5), Math.abs(_t6))), 1.1529215E18f * unitScale(Math.max(Math.abs(_t7), Math.abs(_t8)), Math.max(Math.abs(_t9), Math.abs(Math.max(Math.abs(_t10), Math.abs(_t11)))), Math.max(Math.abs(Math.max(Math.abs(_t0), Math.abs(_t12))), Math.abs(Math.max(Math.abs(_t13), Math.abs(_t14))))));
        float _t67 = _t0 * _t48;
        float _t68 = _t3 * _t48;
        float _t69 = _t10 * _t48;
        float _t70 = _t1 * _t48;
        float _t71 = _t11 * _t48;
        float _t72 = _t2 * _t48;
        float _t73 = _t6 * _t48;
        float _t74 = _t4 * _t48;
        float _t75 = _t5 * _t48;
        float _t76 = _t14 * _t48;
        float _t77 = _t12 * _t48;
        float _t78 = _t13 * _t48;
        float _t79 = _t9 * _t48;
        float _t80 = _t7 * _t48;
        float _t81 = _t8 * _t48;
        float _t82 = _t15 * _t48;
        float _t83 = _t16 * _t48;
        float _t84 = _t17 * _t48;
        float _t109 = Math.fma(_t68, _t74, -(_t70 * _t73));
        float _t110 = Math.fma(_t72, _t73, -(_t68 * _t75));
        float _t111 = Math.fma(_t70, _t75, -(_t72 * _t74));
        float _t119 = Math.fma(_t67, _t68, Math.fma(_t69, _t70, _t71 * _t72));
        float _t120 = Math.fma(_t67, _t73, Math.fma(_t69, _t74, _t71 * _t75));
        float _t121 = Math.fma(_t76, _t68, Math.fma(_t77, _t70, _t78 * _t72));
        float _t122 = Math.fma(_t76, _t73, Math.fma(_t77, _t74, _t78 * _t75));
        float _t123 = Math.fma(_t68, _t68, Math.fma(_t70, _t70, _t72 * _t72));
        float _sp0 = _t119 / _t123;
        float _t124 = Math.fma(_t79, _t73, Math.fma(_t80, _t74, _t81 * _t75));
        float _t125 = Math.fma(_t79, _t68, Math.fma(_t80, _t70, _t81 * _t72));
        float _t126 = Math.fma(_t73, _t73, Math.fma(_t74, _t74, _t75 * _t75));
        float _sp1 = _t120 / _t126;
        float _t127 = Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84));
        float _t130 = Math.fma(_t109, _t69, -(_t110 * _t71));
        float _t131 = Math.fma(_t111, _t71, -(_t109 * _t67));
        float _t132 = Math.fma(_t110, _t67, -(_t111 * _t69));
        float _t134 = _t132 * _t81;
        float _t135 = _t122 - _t121;
        float _sp2 = _t135 / _t127;
        float _t138 = Math.fma(_t131, _t77, _t132 * _t78);
        float _sp3 = Math.fma(_t111, _t67, Math.fma(_t110, _t69, _t109 * _t71)) / (Math.fma(_t111, _t111, Math.fma(_t110, _t110, _t109 * _t109)) * _t48);
        if ((_t119 <= 0.0f) && (_t120 <= 0.0f)) return Joml.float3(v0X, v0Y, v0Z);
        if ((_t121 >= 0.0f) && (_t122 <= _t121)) return Joml.float3(v1X, v1Y, v1Z);
        if ((-Math.fma(_t130, _t76, _t138) <= 0.0f) && ((_t119 >= 0.0f) && ((_t121 <= 0.0f) && (_t123 > 0.0f)))) return Joml.float3(v0X + _sp0 * _t1, v0Y + _sp0 * _t2, v0Z + _sp0 * _t3);
        if ((_t124 >= 0.0f) && (_t125 <= _t124)) return Joml.float3(v2X, v2Y, v2Z);
        if ((Math.fma(_t130, _t79, Math.fma(_t131, _t80, _t134)) <= 0.0f) && ((_t120 >= 0.0f) && ((_t124 <= 0.0f) && (_t126 > 0.0f)))) return Joml.float3(v0X + _sp1 * _t4, v0Y + _sp1 * _t5, v0Z + _sp1 * _t6);
        if ((_t138 + Math.fma(_t130, _t76, _t110 * _t110) + (Math.fma(_t109, _t109, _t111 * _t111) + Math.fma(-_t131, _t80, Math.fma(-_t130, _t79, -_t134))) <= 0.0f) && ((_t135 >= 0.0f) && ((_t125 - _t124 >= 0.0f) && (_t127 > 0.0f)))) return Joml.float3(v1X + _t16 * _sp2, v1Y + _t17 * _sp2, v1Z + _t15 * _sp2);
        return Joml.float3(pX - _sp3 * _t110, pY - _sp3 * _t109, pZ - _sp3 * _t111);
    }

    /**
     * Determine the point on the triangle with the three given vertices that is closest to the
     * given point.
     * <p>
     * The closest point is found by a Voronoi-region cascade: it is one of the three vertices, a
     * point on one of the three edges, or the orthogonal projection of the given point onto the
     * triangle's plane when that projection falls inside the triangle.
     * {@code findClosestPointOnTriangle} computes the very same point and additionally reports
     * which of those seven regions it came from.
     * <p>
     * The triangle is assumed to be non-degenerate: the face case divides by the sum of the three
     * barycentric numerators, which is zero for a triangle of zero area.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.5 "Closest Point on Triangle to
     * Point"
     * <p>
     * Valid input: {@code v0}, {@code v1} and {@code v2} must not be collinear.
     *
     * @param v0 the first vertex of the triangle
     * @param v1 the second vertex of the triangle
     * @param v2 the third vertex of the triangle
     * @param p the point to find the closest point to
     * @return the point on the triangle closest to the given point
     */
    public static Float3 closestPointOnTriangle(Float3 v0, Float3 v1, Float3 v2, Float3 p) {
        return closestPointOnTriangle(v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), p.x(), p.y(), p.z());
    }

    /**
     * Find the point on the line segment between the two given end points which is closest to the
     * specified point.
     * <p>
     * The projection parameter is clamped to <code>[0, 1]</code>, so the closest point never leaves
     * the segment and is one of the end points whenever the perpendicular foot falls outside it. A
     * segment whose two end points coincide is the point they share, which is then the result.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param aX the x coordinate of the first end point of the line segment
     * @param aY the y coordinate of the first end point of the line segment
     * @param aZ the z coordinate of the first end point of the line segment
     * @param bX the x coordinate of the second end point of the line segment
     * @param bY the y coordinate of the second end point of the line segment
     * @param bZ the z coordinate of the second end point of the line segment
     * @param pX the x coordinate of the point to find the closest point to
     * @param pY the y coordinate of the point to find the closest point to
     * @param pZ the z coordinate of the point to find the closest point to
     * @return the point on the line segment closest to the given point
     */
    public static Float3 findClosestPointOnLineSegment(float aX, float aY, float aZ, float bX, float bY, float bZ, float pX, float pY, float pZ) {
        float _t0 = bX - aX;
        float _t1 = bZ - aZ;
        float _t4 = bY - aY;
        float _t14 = java.lang.Math.clamp(Math.fma(_t1, pZ - aZ, Math.fma(_t0, pX - aX, _t4 * (pY - aY))) / Math.max(Math.fma(_t1, _t1, Math.fma(_t0, _t0, _t4 * _t4)), 1.4E-45f), 0.0f, 1.0f);
        return Joml.float3(Math.fma(_t0, _t14, aX), Math.fma(_t4, _t14, aY), Math.fma(_t1, _t14, aZ));
    }

    /**
     * Find the point on the line segment between the two given end points which is closest to the
     * specified point.
     * <p>
     * The projection parameter is clamped to <code>[0, 1]</code>, so the closest point never leaves
     * the segment and is one of the end points whenever the perpendicular foot falls outside it. A
     * segment whose two end points coincide is the point they share, which is then the result.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param a the first end point of the line segment
     * @param b the second end point of the line segment
     * @param p the point to find the closest point to
     * @return the point on the line segment closest to the given point
     */
    public static Float3 findClosestPointOnLineSegment(Float3 a, Float3 b, Float3 p) {
        return findClosestPointOnLineSegment(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), p.x(), p.y(), p.z());
    }

    /**
     * Find the point closest to the specified point {@code p} on the plane that contains the point
     * {@code a} and has the normal {@code n}.
     * <p>
     * The plane's normal must be of unit length. It is not normalized here: the signed distance
     * <i>n . (p - a)</i> is subtracted from {@code p} along {@code n}, so a normal of length
     * <i>k</i> displaces the result by a factor of <i>k</i> squared.
     * <p>
     * Valid input: {@code (nX, nY, nZ)} must have unit length.
     *
     * @param aX the x coordinate of one point on the plane
     * @param aY the y coordinate of one point on the plane
     * @param aZ the z coordinate of one point on the plane
     * @param nX the x coordinate of the plane's unit normal
     * @param nY the y coordinate of the plane's unit normal
     * @param nZ the z coordinate of the plane's unit normal
     * @param pX the x coordinate of the point to find the closest point to
     * @param pY the y coordinate of the point to find the closest point to
     * @param pZ the z coordinate of the point to find the closest point to
     * @return the point on the plane closest to the given point
     */
    public static Float3 findClosestPointOnPlane(float aX, float aY, float aZ, float nX, float nY, float nZ, float pX, float pY, float pZ) {
        float _t6 = Math.fma(nZ, pZ, Math.fma(nX, pX, nY * pY)) - Math.fma(aZ, nZ, Math.fma(aX, nX, aY * nY));
        return Joml.float3(Math.fma(-nX, _t6, pX), Math.fma(-nY, _t6, pY), Math.fma(-nZ, _t6, pZ));
    }

    /**
     * Find the point closest to the specified point {@code p} on the plane that contains the point
     * {@code a} and has the normal {@code n}.
     * <p>
     * The plane's normal must be of unit length. It is not normalized here: the signed distance
     * <i>n . (p - a)</i> is subtracted from {@code p} along {@code n}, so a normal of length
     * <i>k</i> displaces the result by a factor of <i>k</i> squared.
     * <p>
     * Valid input: {@code n} must have unit length.
     *
     * @param a one point on the plane
     * @param n the plane's unit normal
     * @param p the point to find the closest point to
     * @return the point on the plane closest to the given point
     */
    public static Float3 findClosestPointOnPlane(Float3 a, Float3 n, Float3 p) {
        return findClosestPointOnPlane(a.x(), a.y(), a.z(), n.x(), n.y(), n.z(), p.x(), p.y(), p.z());
    }

    /**
     * Find the point on the given rectangle, specified via three of its corners, which is closest
     * to the given point.
     * <p>
     * The rectangle is spanned by the corner <code>a</code> and the two edges from <code>a</code>
     * to <code>b</code> and from <code>a</code> to <code>c</code>, which are assumed to be
     * perpendicular. The given point is projected onto each of the two edges independently and each
     * projection is clamped to its edge, so the result always lies on the rectangle, including its
     * boundary. A degenerate edge of zero length contributes nothing rather than dividing by zero.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.4.2 "Closest Point on 3D
     * Rectangle to Point"
     * <p>
     * Valid input: the edges from {@code (aX, aY, aZ)} to {@code (bX, bY, bZ)} and to
     * {@code (cX, cY, cZ)} must be perpendicular.
     *
     * @param aX the x coordinate of the corner point of the rectangle shared by both edges
     * @param aY the y coordinate of the corner point of the rectangle shared by both edges
     * @param aZ the z coordinate of the corner point of the rectangle shared by both edges
     * @param bX the x coordinate of the corner point of the rectangle at the far end of the first
     *        edge
     * @param bY the y coordinate of the corner point of the rectangle at the far end of the first
     *        edge
     * @param bZ the z coordinate of the corner point of the rectangle at the far end of the first
     *        edge
     * @param cX the x coordinate of the corner point of the rectangle at the far end of the second
     *        edge
     * @param cY the y coordinate of the corner point of the rectangle at the far end of the second
     *        edge
     * @param cZ the z coordinate of the corner point of the rectangle at the far end of the second
     *        edge
     * @param pX the x coordinate of the point to find the closest point to
     * @param pY the y coordinate of the point to find the closest point to
     * @param pZ the z coordinate of the point to find the closest point to
     * @return the point on the rectangle closest to the given point
     */
    public static Float3 findClosestPointOnRectangle(float aX, float aY, float aZ, float bX, float bY, float bZ, float cX, float cY, float cZ, float pX, float pY, float pZ) {
        float _t0 = bX - aX;
        float _t1 = bZ - aZ;
        float _t2 = pZ - aZ;
        float _t3 = pX - aX;
        float _t4 = bY - aY;
        float _t5 = pY - aY;
        float _t6 = cX - aX;
        float _t7 = cZ - aZ;
        float _t8 = cY - aY;
        float _t17 = Math.fma(_t1, _t2, Math.fma(_t0, _t3, _t4 * _t5));
        float _t18 = Math.fma(_t1, _t1, Math.fma(_t0, _t0, _t4 * _t4));
        float _t19 = Math.fma(_t7, _t2, Math.fma(_t6, _t3, _t8 * _t5));
        float _t20 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        float _t25 = _t17 >= _t18 ? 1.0f : _t17 > 0.0f ? _t17 / _t18 : 0.0f;
        float _t26 = _t19 >= _t20 ? 1.0f : _t19 > 0.0f ? _t19 / _t20 : 0.0f;
        return Joml.float3(Math.fma(_t0, _t25, Math.fma(_t6, _t26, aX)), Math.fma(_t4, _t25, Math.fma(_t8, _t26, aY)), Math.fma(_t1, _t25, Math.fma(_t7, _t26, aZ)));
    }

    /**
     * Find the point on the given rectangle, specified via three of its corners, which is closest
     * to the given point.
     * <p>
     * The rectangle is spanned by the corner <code>a</code> and the two edges from <code>a</code>
     * to <code>b</code> and from <code>a</code> to <code>c</code>, which are assumed to be
     * perpendicular. The given point is projected onto each of the two edges independently and each
     * projection is clamped to its edge, so the result always lies on the rectangle, including its
     * boundary. A degenerate edge of zero length contributes nothing rather than dividing by zero.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.4.2 "Closest Point on 3D
     * Rectangle to Point"
     * <p>
     * Valid input: the edges from {@code a} to {@code b} and to {@code c} must be perpendicular.
     *
     * @param a the corner point of the rectangle shared by both edges
     * @param b the corner point of the rectangle at the far end of the first edge
     * @param c the corner point of the rectangle at the far end of the second edge
     * @param p the point to find the closest point to
     * @return the point on the rectangle closest to the given point
     */
    public static Float3 findClosestPointOnRectangle(Float3 a, Float3 b, Float3 c, Float3 p) {
        return findClosestPointOnRectangle(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), c.x(), c.y(), c.z(), p.x(), p.y(), p.z());
    }

    /**
     * Determine the closest point on the triangle formed by the three given vertices to the given
     * point, and classify which feature of the triangle that point lies on.
     * <p>
     * The point is found by the Voronoi-region cascade of the triangle, tested in this order: the
     * vertex region of the first vertex, the vertex region of the second vertex, the region of the
     * edge between those two, the vertex region of the third vertex, the region of the edge from
     * the third vertex back to the first, the region of the edge between the second and third
     * vertices, and last the face region, which claims everything left over. The first region that
     * claims the point decides the result, and the classification reports which region that was -
     * one of <code>POINT_ON_TRIANGLE_VERTEX_0</code>, <code>POINT_ON_TRIANGLE_VERTEX_1</code>,
     * <code>POINT_ON_TRIANGLE_VERTEX_2</code>, <code>POINT_ON_TRIANGLE_EDGE_01</code>,
     * <code>POINT_ON_TRIANGLE_EDGE_12</code>, <code>POINT_ON_TRIANGLE_EDGE_20</code> or
     * <code>POINT_ON_TRIANGLE_FACE</code>. There is always a closest point, so there is no miss
     * case.
     * <p>
     * The point need not lie in the triangle's plane: one whose orthogonal projection falls inside
     * the triangle yields that projection, and the region code is then
     * <code>POINT_ON_TRIANGLE_FACE</code>. The regions are decided with the signed sub-triangle
     * areas (triple products against the triangle's normal), which stays accurate for thin
     * triangles. A degenerate triangle - collinear or coincident vertices - yields the closest
     * point of the segment or point it collapses to.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.5 "Closest Point on Triangle to
     * Point"
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v0X the x coordinate of the first vertex of the triangle
     * @param v0Y the y coordinate of the first vertex of the triangle
     * @param v0Z the z coordinate of the first vertex of the triangle
     * @param v1X the x coordinate of the second vertex of the triangle
     * @param v1Y the y coordinate of the second vertex of the triangle
     * @param v1Z the z coordinate of the second vertex of the triangle
     * @param v2X the x coordinate of the third vertex of the triangle
     * @param v2Y the y coordinate of the third vertex of the triangle
     * @param v2Z the z coordinate of the third vertex of the triangle
     * @param pX the x coordinate of the point to find the closest point to
     * @param pY the y coordinate of the point to find the closest point to
     * @param pZ the z coordinate of the point to find the closest point to
     * @return a {@link FloatHit3} whose {@code code()} is the matching
     *        <code>POINT_ON_TRIANGLE_*</code> constant - never <code>0</code>, since a closest
     *        point always exists - and whose components hold that point
     */
    public static FloatHit3 findClosestPointOnTriangle(float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z, float pX, float pY, float pZ) {
        float _t0 = pZ - v0Z;
        float _t1 = v1Z - v0Z;
        float _t2 = pX - v0X;
        float _t3 = v1X - v0X;
        float _t4 = pY - v0Y;
        float _t5 = v1Y - v0Y;
        float _t6 = v2Z - v0Z;
        float _t7 = v2X - v0X;
        float _t8 = v2Y - v0Y;
        float _t9 = pZ - v1Z;
        float _t10 = pX - v1X;
        float _t11 = pY - v1Y;
        float _t12 = pZ - v2Z;
        float _t13 = pX - v2X;
        float _t14 = pY - v2Y;
        float _t15 = v2X - v1X;
        float _t16 = v2Z - v1Z;
        float _t17 = v2Y - v1Y;
        float _t18 = -_t14;
        float _t19 = -_t12;
        float _t44 = Math.fma(_t1, _t7, -(_t3 * _t6));
        float _t45 = Math.fma(_t5, _t6, -(_t1 * _t8));
        float _t46 = Math.fma(_t3, _t8, -(_t5 * _t7));
        float _t54 = Math.fma(_t0, _t1, Math.fma(_t2, _t3, _t4 * _t5));
        float _t55 = Math.fma(_t0, _t6, Math.fma(_t2, _t7, _t4 * _t8));
        float _t56 = Math.fma(_t9, _t1, Math.fma(_t10, _t3, _t11 * _t5));
        float _t57 = Math.fma(_t1, _t1, Math.fma(_t3, _t3, _t5 * _t5));
        float _sp0 = _t54 / _t57;
        float _t58 = Math.fma(_t12, _t6, Math.fma(_t13, _t7, _t14 * _t8));
        float _t59 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        float _sp1 = _t55 / _t59;
        float _t60 = Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17));
        float _t64 = Math.fma(_t10, _t15, Math.fma(-_t9, _t1, Math.fma(-_t11, _t5, Math.fma(_t11, _t8, _t9 * _t6))));
        float _sp2 = _t64 / _t60;
        float _t66 = Math.fma(_t2, _t44, -(_t4 * _t45));
        float _t67 = Math.fma(_t4, _t46, -(_t0 * _t44));
        float _t68 = Math.fma(_t0, _t45, -(_t2 * _t46));
        float _t72 = Math.fma(_t46, _t46, Math.fma(_t45, _t45, _t44 * _t44));
        if (!(_t72 > 1.1754944E-38f && _t72 < Float.POSITIVE_INFINITY)) return findClosestPointOnTriangle_degenerate(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, pX, pY, pZ);
        float _sp3 = Math.fma(_t0, _t46, Math.fma(_t2, _t45, _t4 * _t44)) / _t72;
        if ((_t54 <= 0.0f) && (_t55 <= 0.0f)) return new FloatHit3(1, v0X, v0Y, v0Z);
        if ((_t56 >= 0.0f) && (Math.fma(_t9, _t6, Math.fma(_t10, _t7, _t11 * _t8)) <= _t56)) return new FloatHit3(2, v1X, v1Y, v1Z);
        if ((-Math.fma(_t9, _t66, Math.fma(_t10, _t67, _t11 * _t68)) <= 0.0f) && ((_t54 >= 0.0f) && ((_t56 <= 0.0f) && (_t57 > 0.0f)))) return new FloatHit3(4, v0X + _sp0 * _t3, v0Y + _sp0 * _t5, v0Z + _sp0 * _t1);
        if ((_t58 >= 0.0f) && (Math.fma(_t12, _t1, Math.fma(_t13, _t3, _t14 * _t5)) <= _t58)) return new FloatHit3(3, v2X, v2Y, v2Z);
        if ((Math.fma(_t12, _t66, Math.fma(_t13, _t67, _t14 * _t68)) <= 0.0f) && ((_t55 >= 0.0f) && ((_t58 <= 0.0f) && (_t59 > 0.0f)))) return new FloatHit3(6, v0X + _sp1 * _t7, v0Y + _sp1 * _t8, v0Z + _sp1 * _t6);
        if ((Math.fma(_t11, _t68, Math.fma(_t9, _t66, Math.fma(_t18, _t68, Math.fma(_t19, _t66, Math.fma(_t15, _t67, _t72))))) <= 0.0f) && ((_t64 >= 0.0f) && ((Math.fma(_t13, v1X - v2X, Math.fma(_t19, _t6, Math.fma(_t18, _t8, Math.fma(_t14, _t5, _t12 * _t1)))) >= 0.0f) && (_t60 > 0.0f)))) return new FloatHit3(5, v1X + _t15 * _sp2, v1Y + _t17 * _sp2, v1Z + _t16 * _sp2);
        return new FloatHit3(7, pX - _sp3 * _t45, pY - _sp3 * _t44, pZ - _sp3 * _t46);
    }

    /** Out-of-range path of {@code findClosestPointOnTriangle}: it leaves here when a value its fast formula needs is zero, NaN or outside the normal floating-point range; reached only through it. */
    private static FloatHit3 findClosestPointOnTriangle_degenerate(float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z, float pX, float pY, float pZ) {
        float _t0 = pZ - v0Z;
        float _t1 = v1X - v0X;
        float _t2 = v1Y - v0Y;
        float _t3 = v1Z - v0Z;
        float _t4 = v2X - v0X;
        float _t5 = v2Y - v0Y;
        float _t6 = v2Z - v0Z;
        float _t7 = pX - v2X;
        float _t8 = pY - v2Y;
        float _t9 = pZ - v2Z;
        float _t10 = pX - v0X;
        float _t11 = pY - v0Y;
        float _t12 = pX - v1X;
        float _t13 = pY - v1Y;
        float _t14 = pZ - v1Z;
        float _t15 = v2Z - v1Z;
        float _t16 = v2X - v1X;
        float _t17 = v2Y - v1Y;
        float _t48 = Math.min(unitScale(Math.max(Math.abs(_t1), Math.abs(_t2)), Math.max(Math.abs(_t3), Math.abs(_t4)), Math.max(Math.abs(_t5), Math.abs(_t6))), 1.1529215E18f * unitScale(Math.max(Math.abs(_t7), Math.abs(_t8)), Math.max(Math.abs(_t9), Math.abs(Math.max(Math.abs(_t10), Math.abs(_t11)))), Math.max(Math.abs(Math.max(Math.abs(_t0), Math.abs(_t12))), Math.abs(Math.max(Math.abs(_t13), Math.abs(_t14))))));
        float _t67 = _t0 * _t48;
        float _t68 = _t3 * _t48;
        float _t69 = _t10 * _t48;
        float _t70 = _t1 * _t48;
        float _t71 = _t11 * _t48;
        float _t72 = _t2 * _t48;
        float _t73 = _t6 * _t48;
        float _t74 = _t4 * _t48;
        float _t75 = _t5 * _t48;
        float _t76 = _t14 * _t48;
        float _t77 = _t12 * _t48;
        float _t78 = _t13 * _t48;
        float _t79 = _t9 * _t48;
        float _t80 = _t7 * _t48;
        float _t81 = _t8 * _t48;
        float _t82 = _t15 * _t48;
        float _t83 = _t16 * _t48;
        float _t84 = _t17 * _t48;
        float _t109 = Math.fma(_t68, _t74, -(_t70 * _t73));
        float _t110 = Math.fma(_t72, _t73, -(_t68 * _t75));
        float _t111 = Math.fma(_t70, _t75, -(_t72 * _t74));
        float _t119 = Math.fma(_t67, _t68, Math.fma(_t69, _t70, _t71 * _t72));
        float _t120 = Math.fma(_t67, _t73, Math.fma(_t69, _t74, _t71 * _t75));
        float _t121 = Math.fma(_t76, _t68, Math.fma(_t77, _t70, _t78 * _t72));
        float _t122 = Math.fma(_t76, _t73, Math.fma(_t77, _t74, _t78 * _t75));
        float _t123 = Math.fma(_t68, _t68, Math.fma(_t70, _t70, _t72 * _t72));
        float _sp0 = _t119 / _t123;
        float _t124 = Math.fma(_t79, _t73, Math.fma(_t80, _t74, _t81 * _t75));
        float _t125 = Math.fma(_t79, _t68, Math.fma(_t80, _t70, _t81 * _t72));
        float _t126 = Math.fma(_t73, _t73, Math.fma(_t74, _t74, _t75 * _t75));
        float _sp1 = _t120 / _t126;
        float _t127 = Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84));
        float _t130 = Math.fma(_t109, _t69, -(_t110 * _t71));
        float _t131 = Math.fma(_t111, _t71, -(_t109 * _t67));
        float _t132 = Math.fma(_t110, _t67, -(_t111 * _t69));
        float _t134 = _t132 * _t81;
        float _t135 = _t122 - _t121;
        float _sp2 = _t135 / _t127;
        float _t138 = Math.fma(_t131, _t77, _t132 * _t78);
        float _sp3 = Math.fma(_t111, _t67, Math.fma(_t110, _t69, _t109 * _t71)) / (Math.fma(_t111, _t111, Math.fma(_t110, _t110, _t109 * _t109)) * _t48);
        if ((_t119 <= 0.0f) && (_t120 <= 0.0f)) return new FloatHit3(1, v0X, v0Y, v0Z);
        if ((_t121 >= 0.0f) && (_t122 <= _t121)) return new FloatHit3(2, v1X, v1Y, v1Z);
        if ((-Math.fma(_t130, _t76, _t138) <= 0.0f) && ((_t119 >= 0.0f) && ((_t121 <= 0.0f) && (_t123 > 0.0f)))) return new FloatHit3(4, v0X + _sp0 * _t1, v0Y + _sp0 * _t2, v0Z + _sp0 * _t3);
        if ((_t124 >= 0.0f) && (_t125 <= _t124)) return new FloatHit3(3, v2X, v2Y, v2Z);
        if ((Math.fma(_t130, _t79, Math.fma(_t131, _t80, _t134)) <= 0.0f) && ((_t120 >= 0.0f) && ((_t124 <= 0.0f) && (_t126 > 0.0f)))) return new FloatHit3(6, v0X + _sp1 * _t4, v0Y + _sp1 * _t5, v0Z + _sp1 * _t6);
        if ((_t138 + Math.fma(_t130, _t76, _t110 * _t110) + (Math.fma(_t109, _t109, _t111 * _t111) + Math.fma(-_t131, _t80, Math.fma(-_t130, _t79, -_t134))) <= 0.0f) && ((_t135 >= 0.0f) && ((_t125 - _t124 >= 0.0f) && (_t127 > 0.0f)))) return new FloatHit3(5, v1X + _t16 * _sp2, v1Y + _t17 * _sp2, v1Z + _t15 * _sp2);
        return new FloatHit3(7, pX - _sp3 * _t110, pY - _sp3 * _t109, pZ - _sp3 * _t111);
    }

    /**
     * Determine the closest point on the triangle formed by the three given vertices to the given
     * point, and classify which feature of the triangle that point lies on.
     * <p>
     * The point is found by the Voronoi-region cascade of the triangle, tested in this order: the
     * vertex region of the first vertex, the vertex region of the second vertex, the region of the
     * edge between those two, the vertex region of the third vertex, the region of the edge from
     * the third vertex back to the first, the region of the edge between the second and third
     * vertices, and last the face region, which claims everything left over. The first region that
     * claims the point decides the result, and the classification reports which region that was -
     * one of <code>POINT_ON_TRIANGLE_VERTEX_0</code>, <code>POINT_ON_TRIANGLE_VERTEX_1</code>,
     * <code>POINT_ON_TRIANGLE_VERTEX_2</code>, <code>POINT_ON_TRIANGLE_EDGE_01</code>,
     * <code>POINT_ON_TRIANGLE_EDGE_12</code>, <code>POINT_ON_TRIANGLE_EDGE_20</code> or
     * <code>POINT_ON_TRIANGLE_FACE</code>. There is always a closest point, so there is no miss
     * case.
     * <p>
     * The point need not lie in the triangle's plane: one whose orthogonal projection falls inside
     * the triangle yields that projection, and the region code is then
     * <code>POINT_ON_TRIANGLE_FACE</code>. The regions are decided with the signed sub-triangle
     * areas (triple products against the triangle's normal), which stays accurate for thin
     * triangles. A degenerate triangle - collinear or coincident vertices - yields the closest
     * point of the segment or point it collapses to.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.5 "Closest Point on Triangle to
     * Point"
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v0 the first vertex of the triangle
     * @param v1 the second vertex of the triangle
     * @param v2 the third vertex of the triangle
     * @param p the point to find the closest point to
     * @return a {@link FloatHit3} whose {@code code()} is the matching
     *        <code>POINT_ON_TRIANGLE_*</code> constant - never <code>0</code>, since a closest
     *        point always exists - and whose components hold that point
     */
    public static FloatHit3 findClosestPointOnTriangle(Float3 v0, Float3 v1, Float3 v2, Float3 p) {
        return findClosestPointOnTriangle(v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), p.x(), p.y(), p.z());
    }

    /**
     * Determine the point on the triangle with the three given vertices that is closest to the
     * given point, and classify which feature of the triangle that point belongs to: one of the
     * three vertices, one of the three edges, or the face.
     * <p>
     * The vertices may be given in either winding order. The Voronoi regions are decided with the
     * signed sub-triangle areas (cross products), which stays accurate for thin triangles, and a
     * point in the face region is returned as it is. A degenerate triangle - collinear or
     * coincident vertices - yields the closest point of the segment or point it collapses to.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.5 "Closest Point on Triangle to
     * Point"
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v0X the x coordinate of the first vertex of the triangle
     * @param v0Y the y coordinate of the first vertex of the triangle
     * @param v1X the x coordinate of the second vertex of the triangle
     * @param v1Y the y coordinate of the second vertex of the triangle
     * @param v2X the x coordinate of the third vertex of the triangle
     * @param v2Y the y coordinate of the third vertex of the triangle
     * @param pX the x coordinate of the point to find the closest point to
     * @param pY the y coordinate of the point to find the closest point to
     * @return a {@link FloatHit2} whose {@code code()} is the matching
     *        <code>POINT_ON_TRIANGLE_*</code> constant and whose components hold the closest point
     *        on the triangle
     */
    public static FloatHit2 findClosestPointOnTriangle(float v0X, float v0Y, float v1X, float v1Y, float v2X, float v2Y, float pX, float pY) {
        float _t0 = pX - v0X;
        float _t1 = v1X - v0X;
        float _t2 = pY - v0Y;
        float _t3 = v1Y - v0Y;
        float _t4 = v2X - v0X;
        float _t5 = v2Y - v0Y;
        float _t6 = pX - v1X;
        float _t7 = pY - v1Y;
        float _t8 = pX - v2X;
        float _t9 = pY - v2Y;
        float _t10 = v2X - v1X;
        float _t11 = v2Y - v1Y;
        float _t22 = Math.fma(_t0, _t1, _t2 * _t3);
        float _t23 = Math.fma(_t0, _t4, _t2 * _t5);
        float _t24 = Math.fma(_t6, _t1, _t7 * _t3);
        float _t25 = Math.fma(_t1, _t1, _t3 * _t3);
        float _sp0 = _t22 / _t25;
        float _t26 = Math.fma(_t8, _t4, _t9 * _t5);
        float _t27 = Math.fma(_t4, _t4, _t5 * _t5);
        float _sp1 = _t23 / _t27;
        float _t28 = Math.fma(_t6, _t10, _t7 * _t11);
        float _t29 = Math.fma(_t10, _t10, _t11 * _t11);
        float _sp2 = _t28 / _t29;
        float _t30 = Math.fma(_t1, _t5, -(_t3 * _t4));
        if ((_t22 <= 0.0f) && (_t23 <= 0.0f)) return new FloatHit2(1, v0X, v0Y);
        if ((_t24 >= 0.0f) && (Math.fma(_t6, _t4, _t7 * _t5) <= _t24)) return new FloatHit2(2, v1X, v1Y);
        if ((Math.fma(_t0, _t7, -(_t6 * _t2)) * _t30 <= 0.0f) && ((_t22 >= 0.0f) && ((_t24 <= 0.0f) && (_t25 > 0.0f)))) return new FloatHit2(4, v0X + _sp0 * _t1, v0Y + _sp0 * _t3);
        if ((_t26 >= 0.0f) && (Math.fma(_t8, _t1, _t9 * _t3) <= _t26)) return new FloatHit2(3, v2X, v2Y);
        if ((Math.fma(_t8, _t2, -(_t0 * _t9)) * _t30 <= 0.0f) && ((_t23 >= 0.0f) && ((_t26 <= 0.0f) && (_t27 > 0.0f)))) return new FloatHit2(6, v0X + _sp1 * _t4, v0Y + _sp1 * _t5);
        if ((Math.fma(_t2, _t10, Math.fma(-_t0, _t7, Math.fma(_t0, _t9, _t30))) * _t30 <= 0.0f) && ((_t28 >= 0.0f) && ((Math.fma(_t8, v1X - v2X, _t9 * (v1Y - v2Y)) >= 0.0f) && (_t29 > 0.0f)))) return new FloatHit2(5, v1X + _t10 * _sp2, v1Y + _t11 * _sp2);
        return new FloatHit2(7, pX, pY);
    }

    /**
     * Determine the point on the triangle with the three given vertices that is closest to the
     * given point, and classify which feature of the triangle that point belongs to: one of the
     * three vertices, one of the three edges, or the face.
     * <p>
     * The vertices may be given in either winding order. The Voronoi regions are decided with the
     * signed sub-triangle areas (cross products), which stays accurate for thin triangles, and a
     * point in the face region is returned as it is. A degenerate triangle - collinear or
     * coincident vertices - yields the closest point of the segment or point it collapses to.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.5 "Closest Point on Triangle to
     * Point"
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v0 the first vertex of the triangle
     * @param v1 the second vertex of the triangle
     * @param v2 the third vertex of the triangle
     * @param p the point to find the closest point to
     * @return a {@link FloatHit2} whose {@code code()} is the matching
     *        <code>POINT_ON_TRIANGLE_*</code> constant and whose components hold the closest point
     *        on the triangle
     */
    public static FloatHit2 findClosestPointOnTriangle(Float2 v0, Float2 v1, Float2 v2, Float2 p) {
        return findClosestPointOnTriangle(v0.x(), v0.y(), v1.x(), v1.y(), v2.x(), v2.y(), p.x(), p.y());
    }

    /**
     * Compute the orthogonal projection of the given point onto the plane with the general plane
     * equation <i>a*x + b*y + c*z + d = 0</i>, where <code>(a, b, c)</code> is the given plane
     * normal.
     * <p>
     * The plane normal does not need to be normalized - the projection divides by its squared
     * length.
     * <p>
     * Valid input: {@code (nX, nY, nZ)} must be non-zero.
     *
     * @param pX the x coordinate of the point to project
     * @param pY the y coordinate of the point to project
     * @param pZ the z coordinate of the point to project
     * @param nX the x coordinate of the plane's normal
     * @param nY the y coordinate of the plane's normal
     * @param nZ the z coordinate of the plane's normal
     * @param d the constant in the plane equation
     * @return the orthogonal projection of the given point onto the plane
     */
    public static Float3 projectPointOnPlane(float pX, float pY, float pZ, float nX, float nY, float nZ, float d) {
        float _sp0 = Math.fma(nX, pX, Math.fma(nY, pY, Math.fma(nZ, pZ, d))) / Math.fma(nZ, nZ, Math.fma(nX, nX, nY * nY));
        return Joml.float3(pX - nX * _sp0, pY - nY * _sp0, pZ - nZ * _sp0);
    }

    /**
     * Compute the orthogonal projection of the given point onto the plane with the general plane
     * equation <i>a*x + b*y + c*z + d = 0</i>, where <code>(a, b, c)</code> is the given plane
     * normal.
     * <p>
     * The plane normal does not need to be normalized - the projection divides by its squared
     * length.
     * <p>
     * Valid input: {@code n} must be non-zero.
     *
     * @param p the point to project
     * @param n the plane's normal
     * @param d the constant in the plane equation
     * @return the orthogonal projection of the given point onto the plane
     */
    public static Float3 projectPointOnPlane(Float3 p, Float3 n, float d) {
        return projectPointOnPlane(p.x(), p.y(), p.z(), n.x(), n.y(), n.z(), d);
    }

    /**
     * Determine the signed distance of the given point to the line specified via its general line
     * equation <i>a*x + b*y + c = 0</i>.
     * <p>
     * The sign follows the line equation: the distance is positive for points on the side the
     * vector <code>(a, b)</code> points to and negative on the other side. The coefficients need
     * not be normalized, since the numerator is divided by the length of <code>(a, b)</code>, but
     * <code>a</code> and <code>b</code> must not both be zero.
     * <p>
     * Reference: <a
     * href="http://mathworld.wolfram.com/Point-LineDistance2-Dimensional.html">http://mathworld.wolfram.com</a>
     * <p>
     * Valid input: {@code (a, b)} must be non-zero.
     *
     * @param pointX the x coordinate of the point to measure the distance to
     * @param pointY the y coordinate of the point to measure the distance to
     * @param a the x factor in the line equation
     * @param b the y factor in the line equation
     * @param c the constant in the line equation
     * @return the signed distance from the point to the line
     */
    public static float distancePointLine(float pointX, float pointY, float a, float b, float c) {
        return Math.fma(a, pointX, Math.fma(b, pointY, c)) * (1.0f / (float) Math.sqrt(Math.fma(a, a, b * b)));
    }

    /**
     * Determine the signed distance of the given point to the line specified via its general line
     * equation <i>a*x + b*y + c = 0</i>.
     * <p>
     * The sign follows the line equation: the distance is positive for points on the side the
     * vector <code>(a, b)</code> points to and negative on the other side. The coefficients need
     * not be normalized, since the numerator is divided by the length of <code>(a, b)</code>, but
     * <code>a</code> and <code>b</code> must not both be zero.
     * <p>
     * Reference: <a
     * href="http://mathworld.wolfram.com/Point-LineDistance2-Dimensional.html">http://mathworld.wolfram.com</a>
     * <p>
     * Valid input: {@code (a, b)} must be non-zero.
     *
     * @param point the point to measure the distance to
     * @param a the x factor in the line equation
     * @param b the y factor in the line equation
     * @param c the constant in the line equation
     * @return the signed distance from the point to the line
     */
    public static float distancePointLine(Float2 point, float a, float b, float c) {
        return distancePointLine(point.x(), point.y(), a, b, c);
    }

    /**
     * Compute the distance of the given point to the line defined by the two points
     * <code>(x0, y0, z0)</code> and <code>(x1, y1, z1)</code>.
     * <p>
     * The line is unbounded: the distance is measured to the whole line through both points, not to
     * the line segment between them. The two points must be distinct, because the computation
     * divides by the squared distance between them.
     * <p>
     * Reference: <a
     * href="http://mathworld.wolfram.com/Point-LineDistance3-Dimensional.html">http://mathworld.wolfram.com</a>
     * <p>
     * Valid input: {@code (x0, y0, z0)} and {@code (x1, y1, z1)} must differ.
     *
     * @param pX the x coordinate of the point to measure the distance to
     * @param pY the y coordinate of the point to measure the distance to
     * @param pZ the z coordinate of the point to measure the distance to
     * @param x0 the x coordinate of the first point on the line
     * @param y0 the y coordinate of the first point on the line
     * @param z0 the z coordinate of the first point on the line
     * @param x1 the x coordinate of the second point on the line
     * @param y1 the y coordinate of the second point on the line
     * @param z1 the z coordinate of the second point on the line
     * @return the distance between the point and the line
     */
    public static float distancePointLine(float pX, float pY, float pZ, float x0, float y0, float z0, float x1, float y1, float z1) {
        float _t0 = x1 - x0;
        float _t1 = y0 - pY;
        float _t2 = x0 - pX;
        float _t3 = y1 - y0;
        float _t4 = z1 - z0;
        float _t5 = z0 - pZ;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t2, _t4, -(_t0 * _t5));
        float _t14 = Math.fma(_t3, _t5, -(_t1 * _t4));
        return (float) Math.sqrt(Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14)) / Math.fma(_t4, _t4, Math.fma(_t0, _t0, _t3 * _t3)));
    }

    /**
     * Compute the distance of the given point to the line defined by the two points
     * <code>(x0, y0, z0)</code> and <code>(x1, y1, z1)</code>.
     * <p>
     * The line is unbounded: the distance is measured to the whole line through both points, not to
     * the line segment between them. The two points must be distinct, because the computation
     * divides by the squared distance between them.
     * <p>
     * Reference: <a
     * href="http://mathworld.wolfram.com/Point-LineDistance3-Dimensional.html">http://mathworld.wolfram.com</a>
     * <p>
     * Valid input: {@code (x0, y0, z0)} and {@code (x1, y1, z1)} must differ.
     *
     * @param p the point to measure the distance to
     * @param x0 the x coordinate of the first point on the line
     * @param y0 the y coordinate of the first point on the line
     * @param z0 the z coordinate of the first point on the line
     * @param x1 the x coordinate of the second point on the line
     * @param y1 the y coordinate of the second point on the line
     * @param z1 the z coordinate of the second point on the line
     * @return the distance between the point and the line
     */
    public static float distancePointLine(Float3 p, float x0, float y0, float z0, float x1, float y1, float z1) {
        return distancePointLine(p.x(), p.y(), p.z(), x0, y0, z0, x1, y1, z1);
    }

    /**
     * Determine the signed distance of the given point to the line defined by the two points
     * <code>(x0, y0)</code> and <code>(x1, y1)</code>.
     * <p>
     * The line is infinite in both directions - the distance is measured to the whole line through
     * the two points, not to the line segment between them. The two points must be distinct: their
     * distance is the divisor of the result and is not guarded against zero. The sign is positive
     * for points lying to the right of the direction from <code>(x0, y0)</code> to
     * <code>(x1, y1)</code> and negative for points to its left, in a coordinate system whose x
     * axis points right and whose y axis points up; swapping the two points flips the sign.
     * <p>
     * Reference: <a
     * href="http://mathworld.wolfram.com/Point-LineDistance2-Dimensional.html">http://mathworld.wolfram.com</a>
     * <p>
     * Valid input: {@code (x0, y0)} and {@code (x1, y1)} must differ.
     *
     * @param pointX the x coordinate of the point to measure the distance to
     * @param pointY the y coordinate of the point to measure the distance to
     * @param x0 the x coordinate of the first point on the line
     * @param y0 the y coordinate of the first point on the line
     * @param x1 the x coordinate of the second point on the line
     * @param y1 the y coordinate of the second point on the line
     * @return the signed distance from the point to the line
     */
    public static float distancePointLine(float pointX, float pointY, float x0, float y0, float x1, float y1) {
        float _t0 = x1 - x0;
        float _t1 = y1 - y0;
        return Math.fma(_t0, y0 - pointY, -((x0 - pointX) * _t1)) * (1.0f / (float) Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1)));
    }

    /**
     * Determine the signed distance of the given point to the line defined by the two points
     * <code>(x0, y0)</code> and <code>(x1, y1)</code>.
     * <p>
     * The line is infinite in both directions - the distance is measured to the whole line through
     * the two points, not to the line segment between them. The two points must be distinct: their
     * distance is the divisor of the result and is not guarded against zero. The sign is positive
     * for points lying to the right of the direction from <code>(x0, y0)</code> to
     * <code>(x1, y1)</code> and negative for points to its left, in a coordinate system whose x
     * axis points right and whose y axis points up; swapping the two points flips the sign.
     * <p>
     * Reference: <a
     * href="http://mathworld.wolfram.com/Point-LineDistance2-Dimensional.html">http://mathworld.wolfram.com</a>
     * <p>
     * Valid input: {@code (x0, y0)} and {@code (x1, y1)} must differ.
     *
     * @param point the point to measure the distance to
     * @param x0 the x coordinate of the first point on the line
     * @param y0 the y coordinate of the first point on the line
     * @param x1 the x coordinate of the second point on the line
     * @param y1 the y coordinate of the second point on the line
     * @return the signed distance from the point to the line
     */
    public static float distancePointLine(Float2 point, float x0, float y0, float x1, float y1) {
        return distancePointLine(point.x(), point.y(), x0, y0, x1, y1);
    }

    /**
     * Determine the signed distance of the given point to the plane specified via its general plane
     * equation <i>a*x + b*y + c*z + d = 0</i>.
     * <p>
     * The plane's normal <code>(a, b, c)</code> need not be of unit length: the result is divided
     * by that normal's length. The distance is positive iff the point lies on the side the normal
     * points to, negative on the opposite side, and zero on the plane itself.
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero.
     *
     * @param pointX the x coordinate of the point to measure the distance to
     * @param pointY the y coordinate of the point to measure the distance to
     * @param pointZ the z coordinate of the point to measure the distance to
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @return the signed distance between the point and the plane
     */
    public static float distancePointPlane(float pointX, float pointY, float pointZ, float a, float b, float c, float d) {
        return Math.fma(a, pointX, Math.fma(b, pointY, Math.fma(c, pointZ, d))) * (1.0f / (float) Math.sqrt(Math.fma(c, c, Math.fma(a, a, b * b))));
    }

    /**
     * Determine the signed distance of the given point to the plane specified via its general plane
     * equation <i>a*x + b*y + c*z + d = 0</i>.
     * <p>
     * The plane's normal <code>(a, b, c)</code> need not be of unit length: the result is divided
     * by that normal's length. The distance is positive iff the point lies on the side the normal
     * points to, negative on the opposite side, and zero on the plane itself.
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero.
     *
     * @param point the point to measure the distance to
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @return the signed distance between the point and the plane
     */
    public static float distancePointPlane(Float3 point, float a, float b, float c, float d) {
        return distancePointPlane(point.x(), point.y(), point.z(), a, b, c, d);
    }

    /**
     * Determine the signed distance of the given point to the plane of the triangle specified by
     * its three vertices.
     * <p>
     * If the point lies on the front-facing side of the triangle's plane, that is, if the triangle
     * has counter-clockwise winding order as seen from the point, then this method returns a
     * positive number.
     * <p>
     * The plane's normal is computed as the cross product of the edges <i>v1 - v0</i> and <i>v2 -
     * v0</i>, and the result is divided by that normal's length, so a degenerate triangle whose
     * three vertices are collinear yields <code>NaN</code>.
     * <p>
     * Valid input: {@code (v0X, v0Y, v0Z)}, {@code (v1X, v1Y, v1Z)} and {@code (v2X, v2Y, v2Z)}
     * must not be collinear.
     *
     * @param pointX the x coordinate of the point to measure the distance to
     * @param pointY the y coordinate of the point to measure the distance to
     * @param pointZ the z coordinate of the point to measure the distance to
     * @param v0X the x coordinate of the first vertex of the triangle
     * @param v0Y the y coordinate of the first vertex of the triangle
     * @param v0Z the z coordinate of the first vertex of the triangle
     * @param v1X the x coordinate of the second vertex of the triangle
     * @param v1Y the y coordinate of the second vertex of the triangle
     * @param v1Z the z coordinate of the second vertex of the triangle
     * @param v2X the x coordinate of the third vertex of the triangle
     * @param v2Y the y coordinate of the third vertex of the triangle
     * @param v2Z the z coordinate of the third vertex of the triangle
     * @return the signed distance between the point and the plane of the triangle
     */
    public static float distancePointPlane(float pointX, float pointY, float pointZ, float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z) {
        float _t0 = v1X - v0X;
        float _t1 = v2Y - v0Y;
        float _t2 = v1Y - v0Y;
        float _t3 = v2X - v0X;
        float _t4 = v2Z - v0Z;
        float _t5 = v1Z - v0Z;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t2, _t4, -(_t5 * _t1));
        float _t14 = Math.fma(_t5, _t3, -(_t0 * _t4));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return distancePointPlane_degenerate(pointX, pointY, pointZ, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z);
        return (Math.fma(pointZ, _t12, Math.fma(pointX, _t13, pointY * _t14)) - Math.fma(v0Z, _t12, Math.fma(v0X, _t13, v0Y * _t14))) * (1.0f / (float) Math.sqrt(_ct0));
    }

    /** Out-of-range path of {@code distancePointPlane}: it leaves here when a value its fast formula needs is zero, NaN or outside the normal floating-point range; reached only through it. */
    private static float distancePointPlane_degenerate(float pointX, float pointY, float pointZ, float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z) {
        float _t0 = v1X - v0X;
        float _t1 = v1Y - v0Y;
        float _t2 = v1Z - v0Z;
        float _t3 = v2X - v0X;
        float _t4 = v2Y - v0Y;
        float _t5 = v2Z - v0Z;
        float _t15 = unitScale(Math.max(Math.abs(_t0), Math.abs(_t1)), Math.max(Math.abs(_t2), Math.abs(_t3)), Math.max(Math.abs(_t4), Math.abs(_t5)));
        float _t22 = _t0 * _t15;
        float _t23 = _t4 * _t15;
        float _t24 = _t1 * _t15;
        float _t25 = _t3 * _t15;
        float _t26 = _t5 * _t15;
        float _t27 = _t2 * _t15;
        float _t34 = Math.fma(_t22, _t23, -(_t24 * _t25));
        float _t35 = Math.fma(_t24, _t26, -(_t27 * _t23));
        float _t36 = Math.fma(_t27, _t25, -(_t22 * _t26));
        return (Math.fma(pointZ, _t34, Math.fma(pointX, _t35, pointY * _t36)) - Math.fma(v0Z, _t34, Math.fma(v0X, _t35, v0Y * _t36))) * (1.0f / (float) Math.sqrt(Math.fma(_t34, _t34, Math.fma(_t35, _t35, _t36 * _t36))));
    }

    /**
     * Determine the signed distance of the given point to the plane of the triangle specified by
     * its three vertices.
     * <p>
     * If the point lies on the front-facing side of the triangle's plane, that is, if the triangle
     * has counter-clockwise winding order as seen from the point, then this method returns a
     * positive number.
     * <p>
     * The plane's normal is computed as the cross product of the edges <i>v1 - v0</i> and <i>v2 -
     * v0</i>, and the result is divided by that normal's length, so a degenerate triangle whose
     * three vertices are collinear yields <code>NaN</code>.
     * <p>
     * Valid input: {@code v0}, {@code v1} and {@code v2} must not be collinear.
     *
     * @param point the point to measure the distance to
     * @param v0 the first vertex of the triangle
     * @param v1 the second vertex of the triangle
     * @param v2 the third vertex of the triangle
     * @return the signed distance between the point and the plane of the triangle
     */
    public static float distancePointPlane(Float3 point, Float3 v0, Float3 v1, Float3 v2) {
        return distancePointPlane(point.x(), point.y(), point.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z());
    }

    /**
     * Compute the lowest root <i>x</i> of the quadratic equation <i>a*x*x + b*x + c = 0</i> that
     * lies strictly between <code>0</code> and <code>maxR</code>.
     * <p>
     * Both bounds are exclusive. The smaller of the two roots is taken when it lies in that open
     * range, otherwise the larger root is taken when it does; when neither root qualifies, or when
     * the discriminant <i>b*b - 4*a*c</i> is negative, the result is positive infinity. The roots
     * are formed with the numerically stable <i>q = -(b + sign(b)*sqrt(b*b - 4*a*c)) / 2</i>, as
     * <i>q / a</i> and <i>c / q</i>, so a root near zero keeps its precision; with
     * <code>a = 0</code> the equation is linear and its root <i>-c / b</i> is used.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param a the quadratic factor
     * @param b the linear factor
     * @param c the constant
     * @param maxR the exclusive upper bound for the root
     * @return the lowest root of the quadratic equation that lies strictly between <code>0</code>
     *        and <code>maxR</code>, or positive infinity when there is no such root
     */
    public static float computeLowestRoot(float a, float b, float c, float maxR) {
        float _t3 = Math.fma(b, b, -(c * 4.0f * a));
        float _t7 = -0.5f * (b + Math.copySign((float) Math.sqrt(_t3), b));
        float _t8 = _t7 / a;
        float _t9 = c / _t7;
        float _t10 = Math.min(_t8, _t9);
        float _t11 = Math.max(_t8, _t9);
        float _t13 = _t11 > 0.0f ? _t11 < maxR ? _t11 : Float.POSITIVE_INFINITY : Float.POSITIVE_INFINITY;
        return _t3 < 0.0f ? Float.POSITIVE_INFINITY : _t10 > 0.0f ? _t10 < maxR ? _t10 : _t13 : _t13;
    }

    /**
     * Determine whether the first circle, with the given center and the square radius
     * {@code radiusSquaredA}, intersects the second circle, with the given center and the square
     * radius {@code radiusSquaredB}, and compute the line segment connecting the two points in
     * which the circles cross.
     * <p>
     * Both radii are passed squared. There is no intersection when the circles are disjoint, when
     * one circle strictly contains the other, or when the two centers coincide. Circles that only
     * touch, externally or internally, are the exact boundary of the test: the line segment
     * degenerates to zero half-length there, and rounding decides whether such a touch is still
     * reported as an intersection.
     * <p>
     * Reference: <a
     * href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     *
     * @param aX the x coordinate of the first circle's center
     * @param aY the y coordinate of the first circle's center
     * @param radiusSquaredA the square of the first circle's radius
     * @param bX the x coordinate of the second circle's center
     * @param bY the y coordinate of the second circle's center
     * @param radiusSquaredB the square of the second circle's radius
     * @return a {@link FloatHit3} whose {@link FloatHit3#hit() hit()} is <code>true</code> iff both
     *        circles intersect, and whose components then hold the center of the line segment of
     *        intersection in the <code>(x, y)</code> components and the half-length of that line
     *        segment in the z component
     */
    public static FloatHit3 intersectCircleCircle(float aX, float aY, float radiusSquaredA, float bX, float bY, float radiusSquaredB) {
        float _t0 = bX - aX;
        float _t1 = bY - aY;
        float _t2 = radiusSquaredB < radiusSquaredA ? radiusSquaredB : radiusSquaredA;
        float _t6 = Math.fma(_t0, _t0, _t1 * _t1);
        float _t10 = 0.5f + (_t2 - (radiusSquaredB < radiusSquaredA ? radiusSquaredA : radiusSquaredB)) / (_t6 + _t6);
        float _t13 = (float) Math.sqrt(Math.fma(-_t6, _t10 * _t10, _t2));
        if ((_t6 != 0.0f) && (_t13 >= 0.0f)) return new FloatHit3(1, Math.fma(_t10, radiusSquaredB < radiusSquaredA ? aX - bX : _t0, radiusSquaredB < radiusSquaredA ? bX : aX), Math.fma(_t10, radiusSquaredB < radiusSquaredA ? aY - bY : _t1, radiusSquaredB < radiusSquaredA ? bY : aY), _t13);
        return FloatHit3.MISS;
    }

    /**
     * Determine whether the first circle, with the given center and the square radius
     * {@code radiusSquaredA}, intersects the second circle, with the given center and the square
     * radius {@code radiusSquaredB}, and compute the line segment connecting the two points in
     * which the circles cross.
     * <p>
     * Both radii are passed squared. There is no intersection when the circles are disjoint, when
     * one circle strictly contains the other, or when the two centers coincide. Circles that only
     * touch, externally or internally, are the exact boundary of the test: the line segment
     * degenerates to zero half-length there, and rounding decides whether such a touch is still
     * reported as an intersection.
     * <p>
     * Reference: <a
     * href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     *
     * @param a the first circle's center
     * @param radiusSquaredA the square of the first circle's radius
     * @param b the second circle's center
     * @param radiusSquaredB the square of the second circle's radius
     * @return a {@link FloatHit3} whose {@link FloatHit3#hit() hit()} is <code>true</code> iff both
     *        circles intersect, and whose components then hold the center of the line segment of
     *        intersection in the <code>(x, y)</code> components and the half-length of that line
     *        segment in the z component
     */
    public static FloatHit3 intersectCircleCircle(Float2 a, float radiusSquaredA, Float2 b, float radiusSquaredB) {
        return intersectCircleCircle(a.x(), a.y(), radiusSquaredA, b.x(), b.y(), radiusSquaredB);
    }

    /**
     * Determine whether the line with the general line equation <i>a*x + b*y + c = 0</i> intersects
     * the circle with the given center and {@code radius}, and compute the line segment in which
     * the two overlap.
     * <p>
     * The line's normal <code>(a, b)</code> need not be of unit length: the signed distance from
     * the circle's center to the line is divided by that normal's length. The chord's center is the
     * point on the line closest to the circle's center. The test is inclusive, so a line that only
     * touches the circle counts as an intersection and yields a half-length of <code>0.0</code>.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a the x factor in the line equation
     * @param b the y factor in the line equation
     * @param c the constant in the line equation
     * @param centerX the x coordinate of the circle's center
     * @param centerY the y coordinate of the circle's center
     * @param radius the radius of the circle
     * @return a {@link FloatHit3} whose {@link FloatHit3#hit() hit()} is <code>true</code> iff the
     *        line intersects the circle, and whose components then hold the center of the line
     *        segment of intersection in the <code>(x, y)</code> components and the half-length of
     *        that line segment in the z component
     */
    public static FloatHit3 intersectLineCircle(float a, float b, float c, float centerX, float centerY, float radius) {
        float _t4 = (1.0f / (float) Math.sqrt(Math.fma(a, a, b * b)));
        float _t6 = Math.fma(a, centerX, Math.fma(b, centerY, c)) * _t4;
        if ((-radius <= _t6) && (_t6 <= radius)) return new FloatHit3(1, Math.fma(-(a * _t6), _t4, centerX), Math.fma(-(b * _t6), _t4, centerY), (float) Math.sqrt((radius + _t6) * (radius - _t6)));
        return FloatHit3.MISS;
    }

    /**
     * Determine whether the line with the general line equation <i>a*x + b*y + c = 0</i> intersects
     * the circle with the given center and {@code radius}, and compute the line segment in which
     * the two overlap.
     * <p>
     * The line's normal <code>(a, b)</code> need not be of unit length: the signed distance from
     * the circle's center to the line is divided by that normal's length. The chord's center is the
     * point on the line closest to the circle's center. The test is inclusive, so a line that only
     * touches the circle counts as an intersection and yields a half-length of <code>0.0</code>.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a the x factor in the line equation
     * @param b the y factor in the line equation
     * @param c the constant in the line equation
     * @param center the circle's center
     * @param radius the radius of the circle
     * @return a {@link FloatHit3} whose {@link FloatHit3#hit() hit()} is <code>true</code> iff the
     *        line intersects the circle, and whose components then hold the center of the line
     *        segment of intersection in the <code>(x, y)</code> components and the half-length of
     *        that line segment in the z component
     */
    public static FloatHit3 intersectLineCircle(float a, float b, float c, Float2 center, float radius) {
        return intersectLineCircle(a, b, c, center.x(), center.y(), radius);
    }

    /**
     * Test whether the line that goes through the two points <code>(x0, y0)</code> and
     * <code>(x1, y1)</code> intersects the circle with the given center and <code>radius</code>,
     * and compute the line segment in which they intersect.
     * <p>
     * The line is the infinite line through both points, not the line segment between them, and the
     * two points must be distinct - for two equal points this method reports no intersection. How
     * far apart the two points are does not matter: the implied line equation is divided by the
     * length of its coefficient vector before it is used.
     * <p>
     * <code>radius</code> is the circle's radius, not its square: the half-length of the chord is
     * <i>sqrt(radius*radius - dist*dist)</i>, with <i>dist</i> the distance from the circle's
     * center to the line.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (x0, y0)} and {@code (x1, y1)} must differ; {@code radius} must not be
     * negative.
     *
     * @param x0 the x coordinate of the first point on the line
     * @param y0 the y coordinate of the first point on the line
     * @param x1 the x coordinate of the second point on the line
     * @param y1 the y coordinate of the second point on the line
     * @param centerX the x coordinate of the circle's center
     * @param centerY the y coordinate of the circle's center
     * @param radius the radius of the circle
     * @return a {@link FloatHit3} whose {@link FloatHit3#hit() hit()} is <code>true</code> iff the
     *        line intersects the circle, and whose components then hold the center of the line
     *        segment of intersection in the <code>(x, y)</code> components and the half-length of
     *        that line segment in the z component
     */
    public static FloatHit3 intersectLineCircle(float x0, float y0, float x1, float y1, float centerX, float centerY, float radius) {
        float _t0 = y0 - y1;
        float _t1 = x1 - x0;
        float _t10 = (1.0f / (float) Math.sqrt(Math.fma(_t1, _t1, _t0 * _t0)));
        float _t13 = (Math.fma(centerX, _t0, centerY * _t1) + Math.fma(x0, y1 - y0, y0 * (x0 - x1))) * _t10;
        if ((-radius <= _t13) && (_t13 <= radius)) return new FloatHit3(1, Math.fma(-(_t0 * _t13), _t10, centerX), Math.fma(-(_t1 * _t13), _t10, centerY), (float) Math.sqrt((radius + _t13) * (radius - _t13)));
        return FloatHit3.MISS;
    }

    /**
     * Test whether the line that goes through the two points <code>(x0, y0)</code> and
     * <code>(x1, y1)</code> intersects the circle with the given center and <code>radius</code>,
     * and compute the line segment in which they intersect.
     * <p>
     * The line is the infinite line through both points, not the line segment between them, and the
     * two points must be distinct - for two equal points this method reports no intersection. How
     * far apart the two points are does not matter: the implied line equation is divided by the
     * length of its coefficient vector before it is used.
     * <p>
     * <code>radius</code> is the circle's radius, not its square: the half-length of the chord is
     * <i>sqrt(radius*radius - dist*dist)</i>, with <i>dist</i> the distance from the circle's
     * center to the line.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (x0, y0)} and {@code (x1, y1)} must differ; {@code radius} must not be
     * negative.
     *
     * @param x0 the x coordinate of the first point on the line
     * @param y0 the y coordinate of the first point on the line
     * @param x1 the x coordinate of the second point on the line
     * @param y1 the y coordinate of the second point on the line
     * @param center the circle's center
     * @param radius the radius of the circle
     * @return a {@link FloatHit3} whose {@link FloatHit3#hit() hit()} is <code>true</code> iff the
     *        line intersects the circle, and whose components then hold the center of the line
     *        segment of intersection in the <code>(x, y)</code> components and the half-length of
     *        that line segment in the z component
     */
    public static FloatHit3 intersectLineCircle(float x0, float y0, float x1, float y1, Float2 center, float radius) {
        return intersectLineCircle(x0, y0, x1, y1, center.x(), center.y(), radius);
    }

    /**
     * Determine whether the two lines, each specified via two points lying on it, intersect each
     * other, and compute their point of intersection.
     * <p>
     * Both lines are unbounded, so the point of intersection need not lie between the two points
     * given for either line. Parallel lines have no single point of intersection, and neither do
     * coincident lines nor a degenerate line whose two given points are equal.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param ps1x the x coordinate of the first point on the first line
     * @param ps1y the y coordinate of the first point on the first line
     * @param pe1x the x coordinate of the second point on the first line
     * @param pe1y the y coordinate of the second point on the first line
     * @param ps2x the x coordinate of the first point on the second line
     * @param ps2y the y coordinate of the first point on the second line
     * @param pe2x the x coordinate of the second point on the second line
     * @param pe2y the y coordinate of the second point on the second line
     * @return a {@link FloatHit2} whose {@link FloatHit2#hit() hit()} is <code>true</code> iff the
     *        two lines intersect in exactly one point, and whose components then hold the point of
     *        intersection of the two lines
     */
    public static FloatHit2 intersectLineLine(float ps1x, float ps1y, float pe1x, float pe1y, float ps2x, float ps2y, float pe2x, float pe2y) {
        float _t0 = pe1y - ps1y;
        float _t1 = ps2x - pe2x;
        float _t2 = pe2y - ps2y;
        float _t3 = ps1x - pe1x;
        float _t8 = Math.fma(ps1x, _t0, ps1y * _t3);
        float _t9 = Math.fma(ps2x, _t2, ps2y * _t1);
        float _t10 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t10_inv = 1.0f / _t10;
        if (_t10 != 0.0f) return new FloatHit2(1, Math.fma(_t8, _t1, -(_t9 * _t3)) * _t10_inv, Math.fma(_t9, _t0, -(_t8 * _t2)) * _t10_inv);
        return FloatHit2.MISS;
    }

    /** Private tail of {@code intersectLineSegmentAabb}; reached only through it. */
    private static FloatHit2 intersectLineSegmentAabb_s37a3782f_tail(float _t18, float _t26, float _t21, float _t27, float _t20, float _t22, float _t19, float _t23) {
        float _t30 = _t18 > _t26 ? _t18 : _t26 != _t26 ? _t18 : _t26;
        float _t31 = _t21 < _t27 ? _t21 : _t27 != _t27 ? _t21 : _t27;
        float _t32 = _t31 > 1.0f ? 1.0f : 0.0f;
        if (!(_t20 > _t22) && (!(_t19 > _t23) && (!(_t20 > _t21) && (!(_t18 > _t23) && ((_t30 <= _t31) && ((_t30 <= 1.0f) && (_t31 >= 0.0f))))))) {
            if ((_t30 >= 0.0f ? _t32 : 0.0f) != 0) return new FloatHit2(1, _t30, _t30);
            if ((_t30 < 0.0f) && (_t31 <= 1.0f)) return new FloatHit2(1, _t31, _t31);
            if ((_t30 < 0.0f ? _t32 : 0.0f) != 0) return new FloatHit2(3, _t30, _t31);
            return new FloatHit2(2, _t30, _t31);
        }
        return FloatHit2.MISS;
    }

    /**
     * Determine whether the undirected line segment with the given end points intersects the
     * axis-aligned box given as its minimum corner and maximum corner, and determine the parameter
     * <i>t</i> in the equation <i>p(t) = p0 + t * (p1 - p0)</i> at the near and far point of
     * intersection.
     * <p>
     * The segment is the range <i>t</i> in <code>[0, 1]</code> of that equation, and the acceptance
     * test is non-strict, so a segment that only grazes the box - entering and leaving it at the
     * same <i>t</i> - still intersects it. An axis along which the segment does not move at all
     * produces infinite or NaN slab bounds that the clamping absorbs, and that is what lets a
     * segment lying on a side or an edge of the box intersect it. When exactly one end point lies
     * inside the box, so that there is a single crossing, both computed parameters collapse onto
     * that crossing.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust
     * Ray-Box Intersection</a>
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component.
     *
     * @param p0X the x coordinate of the line segment's first end point
     * @param p0Y the y coordinate of the line segment's first end point
     * @param p0Z the z coordinate of the line segment's first end point
     * @param p1X the x coordinate of the line segment's second end point
     * @param p1Y the y coordinate of the line segment's second end point
     * @param p1Z the z coordinate of the line segment's second end point
     * @param minX the x coordinate of the minimum corner of the axis-aligned box
     * @param minY the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ the z coordinate of the maximum corner of the axis-aligned box
     * @return a {@link FloatHit2} whose {@code code()} is <code>ONE_INTERSECTION</code>,
     *        <code>TWO_INTERSECTION</code> or <code>INSIDE</code> and whose components then hold
     *        the near and far parameter, or which is {@code MISS} when the line segment lies
     *        completely outside of the axis-aligned box
     */
    public static FloatHit2 intersectLineSegmentAabb(float p0X, float p0Y, float p0Z, float p1X, float p1Y, float p1Z, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _t0_inv = 1.0f / (p1Z - p0Z);
        float _t3_inv = 1.0f / (p1Y - p0Y);
        float _t6_inv = 1.0f / (p1X - p0X);
        float _t12 = (minZ - p0Z) * _t0_inv;
        float _t13 = (maxZ - p0Z) * _t0_inv;
        float _t14 = (minY - p0Y) * _t3_inv;
        float _t15 = (maxY - p0Y) * _t3_inv;
        float _t16 = (minX - p0X) * _t6_inv;
        float _t17 = (maxX - p0X) * _t6_inv;
        float _t18, _t21;
        if (_t0_inv >= 0.0f) {
            _t18 = _t12;
            _t21 = _t13;
        } else {
            _t18 = _t13;
            _t21 = _t12;
        }
        float _t19, _t22;
        if (_t3_inv >= 0.0f) {
            _t19 = _t14;
            _t22 = _t15;
        } else {
            _t19 = _t15;
            _t22 = _t14;
        }
        float _t20, _t23;
        if (_t6_inv >= 0.0f) {
            _t20 = _t16;
            _t23 = _t17;
        } else {
            _t20 = _t17;
            _t23 = _t16;
        }
        return intersectLineSegmentAabb_s37a3782f_tail(_t18, _t19 > _t20 ? _t19 : _t20 != _t20 ? _t19 : _t20, _t21, _t22 < _t23 ? _t22 : _t23 != _t23 ? _t22 : _t23, _t20, _t22, _t19, _t23);
    }

    /**
     * Determine whether the undirected line segment with the given end points intersects the
     * axis-aligned box given as its minimum corner and maximum corner, and determine the parameter
     * <i>t</i> in the equation <i>p(t) = p0 + t * (p1 - p0)</i> at the near and far point of
     * intersection.
     * <p>
     * The segment is the range <i>t</i> in <code>[0, 1]</code> of that equation, and the acceptance
     * test is non-strict, so a segment that only grazes the box - entering and leaving it at the
     * same <i>t</i> - still intersects it. An axis along which the segment does not move at all
     * produces infinite or NaN slab bounds that the clamping absorbs, and that is what lets a
     * segment lying on a side or an edge of the box intersect it. When exactly one end point lies
     * inside the box, so that there is a single crossing, both computed parameters collapse onto
     * that crossing.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust
     * Ray-Box Intersection</a>
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param p0 the line segment's first end point
     * @param p1 the line segment's second end point
     * @param min the minimum corner of the axis-aligned box
     * @param max the maximum corner of the axis-aligned box
     * @return a {@link FloatHit2} whose {@code code()} is <code>ONE_INTERSECTION</code>,
     *        <code>TWO_INTERSECTION</code> or <code>INSIDE</code> and whose components then hold
     *        the near and far parameter, or which is {@code MISS} when the line segment lies
     *        completely outside of the axis-aligned box
     */
    public static FloatHit2 intersectLineSegmentAabb(Float3 p0, Float3 p1, Float3 min, Float3 max) {
        return intersectLineSegmentAabb(p0.x(), p0.y(), p0.z(), p1.x(), p1.y(), p1.z(), min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
    }

    /** Private tail of {@code intersectLineSegmentAar}; reached only through it. */
    private static FloatHit2 intersectLineSegmentAar_s40782fc4_tail(float _t19, float _t18, float _t13, float _t14, float _t12, float _t15) {
        float _t20 = _t19 > 1.0f ? 1.0f : 0.0f;
        if (!(_t13 > _t14) && (!(_t12 > _t15) && ((_t18 <= _t19) && ((_t18 <= 1.0f) && (_t19 >= 0.0f))))) {
            if ((_t18 >= 0.0f ? _t20 : 0.0f) != 0) return new FloatHit2(1, _t18, _t18);
            if ((_t18 < 0.0f) && (_t19 <= 1.0f)) return new FloatHit2(1, _t19, _t19);
            if ((_t18 < 0.0f ? _t20 : 0.0f) != 0) return new FloatHit2(3, _t18, _t19);
            return new FloatHit2(2, _t18, _t19);
        }
        return FloatHit2.MISS;
    }

    /**
     * Determine whether the undirected line segment with the two given end points intersects the
     * axis-aligned rectangle given as its minimum and maximum corner, and classify how, computing
     * the values of the parameter <i>t</i> in the equation <i>p(t) = p0 + t * (p1 - p0)</i> at
     * which the line enters and leaves the rectangle.
     * <p>
     * A segment with either end point inside the rectangle is detected as well. The two parameters
     * are those of the infinite line through both end points, so for a segment lying completely
     * inside the rectangle they fall outside of <code>[0, 1]</code>. When exactly one end point
     * lies inside there is a single crossing, and both parameters are then that one value.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust
     * Ray-Box Intersection</a>
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param p0X the x coordinate of the line segment's first end point
     * @param p0Y the y coordinate of the line segment's first end point
     * @param p1X the x coordinate of the line segment's second end point
     * @param p1Y the y coordinate of the line segment's second end point
     * @param minX the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY the y coordinate of the maximum corner of the axis-aligned rectangle
     * @return a {@link FloatHit2} whose {@code code()} is <code>ONE_INTERSECTION</code>,
     *        <code>TWO_INTERSECTION</code> or <code>INSIDE</code>, or <code>0</code> when the line
     *        segment does not intersect the axis-aligned rectangle, and whose components hold the
     *        near and far values of the parameter <i>t</i>
     */
    public static FloatHit2 intersectLineSegmentAar(float p0X, float p0Y, float p1X, float p1Y, float minX, float minY, float maxX, float maxY) {
        float _t0_inv = 1.0f / (p1Y - p0Y);
        float _t3_inv = 1.0f / (p1X - p0X);
        float _t8 = (minY - p0Y) * _t0_inv;
        float _t9 = (maxY - p0Y) * _t0_inv;
        float _t10 = (minX - p0X) * _t3_inv;
        float _t11 = (maxX - p0X) * _t3_inv;
        float _t12, _t14;
        if (_t0_inv >= 0.0f) {
            _t12 = _t8;
            _t14 = _t9;
        } else {
            _t12 = _t9;
            _t14 = _t8;
        }
        float _t13, _t15;
        if (_t3_inv >= 0.0f) {
            _t13 = _t10;
            _t15 = _t11;
        } else {
            _t13 = _t11;
            _t15 = _t10;
        }
        return intersectLineSegmentAar_s40782fc4_tail(_t14 < _t15 ? _t14 : _t15 != _t15 ? _t14 : _t15, _t12 > _t13 ? _t12 : _t13 != _t13 ? _t12 : _t13, _t13, _t14, _t12, _t15);
    }

    /**
     * Determine whether the undirected line segment with the two given end points intersects the
     * axis-aligned rectangle given as its minimum and maximum corner, and classify how, computing
     * the values of the parameter <i>t</i> in the equation <i>p(t) = p0 + t * (p1 - p0)</i> at
     * which the line enters and leaves the rectangle.
     * <p>
     * A segment with either end point inside the rectangle is detected as well. The two parameters
     * are those of the infinite line through both end points, so for a segment lying completely
     * inside the rectangle they fall outside of <code>[0, 1]</code>. When exactly one end point
     * lies inside there is a single crossing, and both parameters are then that one value.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust
     * Ray-Box Intersection</a>
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param p0 the line segment's first end point
     * @param p1 the line segment's second end point
     * @param min the minimum corner of the axis-aligned rectangle
     * @param max the maximum corner of the axis-aligned rectangle
     * @return a {@link FloatHit2} whose {@code code()} is <code>ONE_INTERSECTION</code>,
     *        <code>TWO_INTERSECTION</code> or <code>INSIDE</code>, or <code>0</code> when the line
     *        segment does not intersect the axis-aligned rectangle, and whose components hold the
     *        near and far values of the parameter <i>t</i>
     */
    public static FloatHit2 intersectLineSegmentAar(Float2 p0, Float2 p1, Float2 min, Float2 max) {
        return intersectLineSegmentAar(p0.x(), p0.y(), p1.x(), p1.y(), min.x(), min.y(), max.x(), max.y());
    }

    /**
     * Determine whether the line segment with the two given end points intersects the plane given
     * as the general plane equation <i>a*x + b*y + c*z + d = 0</i>, and determine the point of
     * intersection.
     * <p>
     * The segment is bounded: an intersection is only reported for a parameter <i>t</i> in
     * <code>[0, 1]</code> along the segment from its first to its second end point, both end points
     * included. The plane does not have to be given with a unit-length normal
     * <code>(a, b, c)</code> - <i>t</i> is a ratio of two values of the plane equation, so scaling
     * all four coefficients <code>(a, b, c, d)</code> by the same factor leaves it unchanged. A
     * segment parallel to the plane is a miss, including one that lies entirely within the plane.
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero.
     *
     * @param p0X the x coordinate of the line segment's first end point
     * @param p0Y the y coordinate of the line segment's first end point
     * @param p0Z the z coordinate of the line segment's first end point
     * @param p1X the x coordinate of the line segment's second end point
     * @param p1Y the y coordinate of the line segment's second end point
     * @param p1Z the z coordinate of the line segment's second end point
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @return a {@link FloatHit3} whose {@link FloatHit3#hit() hit()} is <code>true</code> iff the
     *        line segment intersects the plane, and whose components then hold the point at which
     *        the line segment intersects the plane
     */
    public static FloatHit3 intersectLineSegmentPlane(float p0X, float p0Y, float p0Z, float p1X, float p1Y, float p1Z, float a, float b, float c, float d) {
        float _t0 = p1Z - p0Z;
        float _t1 = p1X - p0X;
        float _t2 = p1Y - p0Y;
        float _sp0 = Math.fma(a, p0X, Math.fma(b, p0Y, Math.fma(c, p0Z, d))) / Math.fma(c, _t0, Math.fma(a, _t1, b * _t2));
        float _t10 = -_sp0;
        if ((_t10 >= 0.0f) && (_t10 <= 1.0f)) return new FloatHit3(1, p0X - _sp0 * _t1, p0Y - _sp0 * _t2, p0Z - _sp0 * _t0);
        return FloatHit3.MISS;
    }

    /**
     * Determine whether the line segment with the two given end points intersects the plane given
     * as the general plane equation <i>a*x + b*y + c*z + d = 0</i>, and determine the point of
     * intersection.
     * <p>
     * The segment is bounded: an intersection is only reported for a parameter <i>t</i> in
     * <code>[0, 1]</code> along the segment from its first to its second end point, both end points
     * included. The plane does not have to be given with a unit-length normal
     * <code>(a, b, c)</code> - <i>t</i> is a ratio of two values of the plane equation, so scaling
     * all four coefficients <code>(a, b, c, d)</code> by the same factor leaves it unchanged. A
     * segment parallel to the plane is a miss, including one that lies entirely within the plane.
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero.
     *
     * @param p0 the line segment's first end point
     * @param p1 the line segment's second end point
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @return a {@link FloatHit3} whose {@link FloatHit3#hit() hit()} is <code>true</code> iff the
     *        line segment intersects the plane, and whose components then hold the point at which
     *        the line segment intersects the plane
     */
    public static FloatHit3 intersectLineSegmentPlane(Float3 p0, Float3 p1, float a, float b, float c, float d) {
        return intersectLineSegmentPlane(p0.x(), p0.y(), p0.z(), p1.x(), p1.y(), p1.z(), a, b, c, d);
    }

    /** Private tail of {@code intersectLineSegmentTriangle}; reached only through it. */
    private static FloatHit3 intersectLineSegmentTriangle_s77083b57_tail(float _t38, float _t9, float _t24, float _t10, float _t25, float _t11, float _t26, float _t7, float _t27, float _t1, float _t28, float _t3, float _t29, float epsilon, float _t6, float _t4, float _t2, float p0X, float p0Y, float p0Z) {
        float _t38_inv = 1.0f / _t38;
        float _t39 = Math.fma(_t9, _t24, Math.fma(_t10, _t25, _t11 * _t26));
        float _t40 = Math.fma(_t7, _t27, Math.fma(_t1, _t28, _t3 * _t29));
        float _t43 = _t39 * _t38_inv;
        float _t52 = Math.abs(_t38) >= epsilon ? _t43 >= 0.0f ? _t43 <= 1.0f ? _t40 * _t38_inv >= 0.0f ? (_t39 + _t40) * _t38_inv <= 1.0f ? Math.fma(_t6, _t27, Math.fma(_t4, _t28, _t2 * _t29)) * _t38_inv : -1.0f : -1.0f : -1.0f : -1.0f : -1.0f;
        if ((_t52 >= 0.0f) && (_t52 <= 1.0f)) return new FloatHit3(1, Math.fma(_t1, _t52, p0X), Math.fma(_t3, _t52, p0Y), Math.fma(_t7, _t52, p0Z));
        return FloatHit3.MISS;
    }

    /**
     * Determine whether the line segment with the given two end points intersects the triangle
     * consisting of the three given vertices, regardless of the winding order of the triangle or
     * the direction of the line segment between its two end points, and compute the point of
     * intersection.
     * <p>
     * The bounds are inclusive: an intersection exactly at an end point of the segment, or on an
     * edge or at a vertex of the triangle, counts as a hit.
     * <p>
     * The intersection is rejected when the absolute value of the Moller-Trumbore determinant falls
     * below {@code epsilon}, which is the case for a segment that is (nearly) parallel to the
     * triangle's plane; a segment lying in that plane is therefore never reported. That determinant
     * scales with the length of the segment and with the size of the triangle, so {@code epsilon}
     * is not a scale-free threshold.
     * <p>
     * Reference: <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage
     * Ray/Triangle Intersection</a>
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param p0X the x coordinate of the line segment's first end point
     * @param p0Y the y coordinate of the line segment's first end point
     * @param p0Z the z coordinate of the line segment's first end point
     * @param p1X the x coordinate of the line segment's second end point
     * @param p1Y the y coordinate of the line segment's second end point
     * @param p1Z the z coordinate of the line segment's second end point
     * @param v0X the x coordinate of the first vertex of the triangle
     * @param v0Y the y coordinate of the first vertex of the triangle
     * @param v0Z the z coordinate of the first vertex of the triangle
     * @param v1X the x coordinate of the second vertex of the triangle
     * @param v1Y the y coordinate of the second vertex of the triangle
     * @param v1Z the z coordinate of the second vertex of the triangle
     * @param v2X the x coordinate of the third vertex of the triangle
     * @param v2Y the y coordinate of the third vertex of the triangle
     * @param v2Z the z coordinate of the third vertex of the triangle
     * @param epsilon a small epsilon when testing line segments that are almost parallel to the
     *        triangle
     * @return a {@link FloatHit3} whose {@link FloatHit3#hit() hit()} is <code>true</code> iff the
     *        line segment intersects the triangle, and whose components then hold the point of
     *        intersection
     */
    public static FloatHit3 intersectLineSegmentTriangle(float p0X, float p0Y, float p0Z, float p1X, float p1Y, float p1Z, float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z, float epsilon) {
        float _t0 = v1Z - v0Z;
        float _t1 = p1X - p0X;
        float _t2 = v2Y - v0Y;
        float _t3 = p1Y - p0Y;
        float _t4 = v2X - v0X;
        float _t5 = v1X - v0X;
        float _t6 = v2Z - v0Z;
        float _t7 = p1Z - p0Z;
        float _t8 = v1Y - v0Y;
        float _t9 = p0Z - v0Z;
        float _t10 = p0X - v0X;
        float _t11 = p0Y - v0Y;
        float _t24 = Math.fma(_t1, _t2, -(_t3 * _t4));
        float _t25 = Math.fma(_t3, _t6, -(_t7 * _t2));
        float _t26 = Math.fma(_t7, _t4, -(_t1 * _t6));
        return intersectLineSegmentTriangle_s77083b57_tail(Math.fma(_t0, _t24, Math.fma(_t5, _t25, _t8 * _t26)), _t9, _t24, _t10, _t25, _t11, _t26, _t7, Math.fma(_t10, _t8, -(_t11 * _t5)), _t1, Math.fma(_t11, _t0, -(_t9 * _t8)), _t3, Math.fma(_t9, _t5, -(_t10 * _t0)), epsilon, _t6, _t4, _t2, p0X, p0Y, p0Z);
    }

    /**
     * Determine whether the line segment with the given two end points intersects the triangle
     * consisting of the three given vertices, regardless of the winding order of the triangle or
     * the direction of the line segment between its two end points, and compute the point of
     * intersection.
     * <p>
     * The bounds are inclusive: an intersection exactly at an end point of the segment, or on an
     * edge or at a vertex of the triangle, counts as a hit.
     * <p>
     * The intersection is rejected when the absolute value of the Moller-Trumbore determinant falls
     * below {@code epsilon}, which is the case for a segment that is (nearly) parallel to the
     * triangle's plane; a segment lying in that plane is therefore never reported. That determinant
     * scales with the length of the segment and with the size of the triangle, so {@code epsilon}
     * is not a scale-free threshold.
     * <p>
     * Reference: <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage
     * Ray/Triangle Intersection</a>
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param p0 the line segment's first end point
     * @param p1 the line segment's second end point
     * @param v0 the first vertex of the triangle
     * @param v1 the second vertex of the triangle
     * @param v2 the third vertex of the triangle
     * @param epsilon a small epsilon when testing line segments that are almost parallel to the
     *        triangle
     * @return a {@link FloatHit3} whose {@link FloatHit3#hit() hit()} is <code>true</code> iff the
     *        line segment intersects the triangle, and whose components then hold the point of
     *        intersection
     */
    public static FloatHit3 intersectLineSegmentTriangle(Float3 p0, Float3 p1, Float3 v0, Float3 v1, Float3 v2, float epsilon) {
        return intersectLineSegmentTriangle(p0.x(), p0.y(), p0.z(), p1.x(), p1.y(), p1.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i>
     * intersects the sphere with the given center and radius, and determine the circle in which the
     * two intersect.
     * <p>
     * The plane normal <code>(a, b, c)</code> need not be unit length - the kernel divides by its
     * length itself - but it must not be the zero vector. The sphere radius is passed unsquared and
     * is expected to be non-negative.
     * <p>
     * The circle of intersection is centered at the sphere center projected onto the plane, and its
     * radius is the square root of <i>radius * radius - dist * dist</i>, where <i>dist</i> is the
     * signed distance from the sphere center to the plane. Contact counts: a plane touching the
     * sphere tangentially intersects it, in a circle of radius <code>0</code>.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @param centerX the x coordinate of the sphere's center
     * @param centerY the y coordinate of the sphere's center
     * @param centerZ the z coordinate of the sphere's center
     * @param radius the radius of the sphere
     * @return a {@link FloatHit4} whose {@link FloatHit4#hit() hit()} is <code>true</code> iff the
     *        plane intersects the sphere, and whose components then hold the center of the circle
     *        of intersection in the <code>(x, y, z)</code> components and the radius of that circle
     *        in the w component
     */
    public static FloatHit4 intersectPlaneSphere(float a, float b, float c, float d, float centerX, float centerY, float centerZ, float radius) {
        float _t6 = (1.0f / (float) Math.sqrt(Math.fma(c, c, Math.fma(a, a, b * b))));
        float _t8 = Math.fma(a, centerX, Math.fma(b, centerY, Math.fma(c, centerZ, d))) * _t6;
        if ((-radius <= _t8) && (_t8 <= radius)) return new FloatHit4(1, Math.fma(-(a * _t8), _t6, centerX), Math.fma(-(b * _t8), _t6, centerY), Math.fma(-(c * _t8), _t6, centerZ), (float) Math.sqrt((radius + _t8) * (radius - _t8)));
        return FloatHit4.MISS;
    }

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i>
     * intersects the sphere with the given center and radius, and determine the circle in which the
     * two intersect.
     * <p>
     * The plane normal <code>(a, b, c)</code> need not be unit length - the kernel divides by its
     * length itself - but it must not be the zero vector. The sphere radius is passed unsquared and
     * is expected to be non-negative.
     * <p>
     * The circle of intersection is centered at the sphere center projected onto the plane, and its
     * radius is the square root of <i>radius * radius - dist * dist</i>, where <i>dist</i> is the
     * signed distance from the sphere center to the plane. Contact counts: a plane touching the
     * sphere tangentially intersects it, in a circle of radius <code>0</code>.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @param center the sphere's center
     * @param radius the radius of the sphere
     * @return a {@link FloatHit4} whose {@link FloatHit4#hit() hit()} is <code>true</code> iff the
     *        plane intersects the sphere, and whose components then hold the center of the circle
     *        of intersection in the <code>(x, y, z)</code> components and the radius of that circle
     *        in the w component
     */
    public static FloatHit4 intersectPlaneSphere(float a, float b, float c, float d, Float3 center, float radius) {
        return intersectPlaneSphere(a, b, c, d, center.x(), center.y(), center.z(), radius);
    }

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i>
     * intersects the sphere with the given center and <code>radius</code> moving with the given
     * velocity, and compute the point and the time of intersection.
     * <p>
     * The normal <code>(a, b, c)</code> of the plane equation needs to be normalized.
     * <p>
     * Two cases count as an intersection. If the sphere already touches or overlaps the plane at
     * <i>t = 0</i>, the point of intersection is the sphere's center and the time is
     * <code>0</code>. Otherwise the sphere must be moving towards the plane, and the point of
     * intersection is the point on the plane which the sphere first touches, at a positive time.
     * That time is measured in units of the velocity and is not bounded by <code>1</code> - the
     * sphere is treated as moving along the velocity indefinitely, so a sphere that only reaches
     * the plane long after <i>t = 1</i> still counts as intersecting.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.5.3 "Intersecting Moving Sphere
     * Against Plane"
     * <p>
     * Valid input: {@code (a, b, c)} must have unit length; {@code radius} must not be negative.
     *
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @param centerX the x coordinate of the sphere's center at <i>t</i> = 0
     * @param centerY the y coordinate of the sphere's center at <i>t</i> = 0
     * @param centerZ the z coordinate of the sphere's center at <i>t</i> = 0
     * @param radius the sphere's radius
     * @param vX the x coordinate of the sphere's velocity
     * @param vY the y coordinate of the sphere's velocity
     * @param vZ the z coordinate of the sphere's velocity
     * @return a {@link FloatHit4} whose {@link FloatHit4#hit() hit()} is <code>true</code> iff the
     *        moving sphere intersects the plane, and whose components then hold the point of
     *        intersection in the <code>(x, y, z)</code> components and the time of intersection in
     *        the w component
     */
    public static FloatHit4 intersectPlaneSweptSphere(float a, float b, float c, float d, float centerX, float centerY, float centerZ, float radius, float vX, float vY, float vZ) {
        float _t4 = Math.fma(c, vZ, Math.fma(a, vX, b * vY));
        float _t5 = Math.fma(a, centerX, Math.fma(b, centerY, Math.fma(c, centerZ, d)));
        float _t6 = Math.abs(_t5);
        float _t7 = _t5 > 0.0f ? radius : -radius;
        float _sp0 = Math.fma(-a, centerX, Math.fma(-b, centerY, Math.fma(-c, centerZ, _t7 - d))) / _t4;
        if ((_t6 <= radius) || (_t5 * _t4 < 0.0f)) return new FloatHit4(1, _t6 <= radius ? centerX : Math.fma(-a, _t7, centerX + vX * _sp0), _t6 <= radius ? centerY : Math.fma(-b, _t7, centerY + vY * _sp0), _t6 <= radius ? centerZ : Math.fma(-c, _t7, centerZ + vZ * _sp0), _t6 <= radius ? 0.0f : _sp0);
        return FloatHit4.MISS;
    }

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i>
     * intersects the sphere with the given center and <code>radius</code> moving with the given
     * velocity, and compute the point and the time of intersection.
     * <p>
     * The normal <code>(a, b, c)</code> of the plane equation needs to be normalized.
     * <p>
     * Two cases count as an intersection. If the sphere already touches or overlaps the plane at
     * <i>t = 0</i>, the point of intersection is the sphere's center and the time is
     * <code>0</code>. Otherwise the sphere must be moving towards the plane, and the point of
     * intersection is the point on the plane which the sphere first touches, at a positive time.
     * That time is measured in units of the velocity and is not bounded by <code>1</code> - the
     * sphere is treated as moving along the velocity indefinitely, so a sphere that only reaches
     * the plane long after <i>t = 1</i> still counts as intersecting.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.5.3 "Intersecting Moving Sphere
     * Against Plane"
     * <p>
     * Valid input: {@code (a, b, c)} must have unit length; {@code radius} must not be negative.
     *
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @param center the sphere's center at <i>t</i> = 0
     * @param radius the sphere's radius
     * @param v the sphere's velocity
     * @return a {@link FloatHit4} whose {@link FloatHit4#hit() hit()} is <code>true</code> iff the
     *        moving sphere intersects the plane, and whose components then hold the point of
     *        intersection in the <code>(x, y, z)</code> components and the time of intersection in
     *        the w component
     */
    public static FloatHit4 intersectPlaneSweptSphere(float a, float b, float c, float d, Float3 center, float radius, Float3 v) {
        return intersectPlaneSweptSphere(a, b, c, d, center.x(), center.y(), center.z(), radius, v.x(), v.y(), v.z());
    }

    /** Private tail of {@code intersectRayAabb}; reached only through it. */
    private static FloatHit2 intersectRayAabb_s60b2433f_tail(float _t17, float _t15, float _t16, float _t18, float _t20, float _t19) {
        float _t23 = _t17 > _t15 ? _t17 : _t15 != _t15 ? _t17 : _t15;
        float _t24 = _t16 < _t18 ? _t16 : _t18 != _t18 ? _t16 : _t18;
        float _t27 = _t20 > _t23 ? _t20 : _t23 != _t23 ? _t20 : _t23;
        float _t28 = _t19 < _t24 ? _t19 : _t24 != _t24 ? _t19 : _t24;
        if (!(_t15 > _t16) && (!(_t17 > _t18) && (!(_t15 > _t19) && (!(_t20 > _t18) && ((_t27 < _t28) && (_t28 >= 0.0f)))))) return new FloatHit2(1, _t27, _t28);
        return FloatHit2.MISS;
    }

    /**
     * Determine whether the given ray with the given origin and direction intersects the
     * axis-aligned box given as its minimum corner and maximum corner, and compute the values of
     * the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the near and far
     * point of intersection.
     * <p>
     * The direction need not be of unit length; <i>t</i> is measured in units of {@code dir}. A ray
     * whose origin lies inside the axis-aligned box counts as intersecting it, and its near
     * parameter is then negative. A box lying entirely behind the ray's origin does not count as an
     * intersection: the far parameter is required to be non-negative.
     * <p>
     * Direction components that are exactly zero are supported. The ray is then parallel to the
     * corresponding pair of slab planes and is rejected only if its origin lies outside that slab,
     * so a ray running inside a face plane of the box does intersect it.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust
     * Ray-Box Intersection</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must be non-zero; {@code (minX, minY, minZ)} must not
     * exceed {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param originZ the z coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's direction
     * @param dirY the y coordinate of the ray's direction
     * @param dirZ the z coordinate of the ray's direction
     * @param minX the x coordinate of the minimum corner of the axis-aligned box
     * @param minY the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ the z coordinate of the maximum corner of the axis-aligned box
     * @return a {@link FloatHit2} whose {@link FloatHit2#hit() hit()} is <code>true</code> iff the
     *        ray intersects the axis-aligned box, and whose components then hold the values of the
     *        parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and
     *        far intersection
     */
    public static FloatHit2 intersectRayAabb(float originX, float originY, float originZ, float dirX, float dirY, float dirZ, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _rcp0 = 1.0f / dirX;
        float _rcp1 = 1.0f / dirY;
        float _rcp2 = 1.0f / dirZ;
        float _t9 = (minX - originX) * _rcp0;
        float _t10 = (maxX - originX) * _rcp0;
        float _t11 = (maxY - originY) * _rcp1;
        float _t12 = (minY - originY) * _rcp1;
        float _t13 = (maxZ - originZ) * _rcp2;
        float _t14 = (minZ - originZ) * _rcp2;
        float _t15, _t18;
        if (_rcp0 >= 0.0f) {
            _t15 = _t9;
            _t18 = _t10;
        } else {
            _t15 = _t10;
            _t18 = _t9;
        }
        float _t16, _t17;
        if (_rcp1 >= 0.0f) {
            _t16 = _t11;
            _t17 = _t12;
        } else {
            _t16 = _t12;
            _t17 = _t11;
        }
        float _t19, _t20;
        if (_rcp2 >= 0.0f) {
            _t19 = _t13;
            _t20 = _t14;
        } else {
            _t19 = _t14;
            _t20 = _t13;
        }
        return intersectRayAabb_s60b2433f_tail(_t17, _t15, _t16, _t18, _t20, _t19);
    }

    /**
     * Determine whether the given ray with the given origin and direction intersects the
     * axis-aligned box given as its minimum corner and maximum corner, and compute the values of
     * the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the near and far
     * point of intersection.
     * <p>
     * The direction need not be of unit length; <i>t</i> is measured in units of {@code dir}. A ray
     * whose origin lies inside the axis-aligned box counts as intersecting it, and its near
     * parameter is then negative. A box lying entirely behind the ray's origin does not count as an
     * intersection: the far parameter is required to be non-negative.
     * <p>
     * Direction components that are exactly zero are supported. The ray is then parallel to the
     * corresponding pair of slab planes and is rejected only if its origin lies outside that slab,
     * so a ray running inside a face plane of the box does intersect it.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust
     * Ray-Box Intersection</a>
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code min} must not exceed {@code max} in any
     * component.
     *
     * @param origin the ray's origin
     * @param dir the ray's direction
     * @param min the minimum corner of the axis-aligned box
     * @param max the maximum corner of the axis-aligned box
     * @return a {@link FloatHit2} whose {@link FloatHit2#hit() hit()} is <code>true</code> iff the
     *        ray intersects the axis-aligned box, and whose components then hold the values of the
     *        parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and
     *        far intersection
     */
    public static FloatHit2 intersectRayAabb(Float3 origin, Float3 dir, Float3 min, Float3 max) {
        return intersectRayAabb(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
    }

    /**
     * Determine whether the ray with the given origin and normalized direction intersects the
     * circle with the given center and square radius, and compute the values of the parameter
     * <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far point of
     * intersection.
     * <p>
     * The direction must be normalized: the kernel projects the vector from the origin to the
     * center onto it, and takes the 2D cross product of the direction with that vector as the
     * perpendicular distance from the center to the ray, which only holds for a unit direction, and
     * <i>t</i> is then a distance along the ray. The radius is passed squared.
     * <p>
     * An intersection is reported for a ray whose origin lies inside the circle - the near value is
     * negative in that case. A ray that only grazes the circle tangentially does not count, and
     * neither does a circle lying entirely behind the origin.
     * <p>
     * Reference: <a
     * href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code (dirX, dirY)} must have unit length; {@code radiusSquared} must not be
     * negative.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's normalized direction
     * @param dirY the y coordinate of the ray's normalized direction
     * @param centerX the x coordinate of the circle's center
     * @param centerY the y coordinate of the circle's center
     * @param radiusSquared the square of the circle's radius
     * @return a {@link FloatHit2} whose {@link FloatHit2#hit() hit()} is <code>true</code> iff the
     *        ray intersects the circle, and whose components then hold the values of the parameter
     *        <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far
     *        intersection
     */
    public static FloatHit2 intersectRayCircle(float originX, float originY, float dirX, float dirY, float centerX, float centerY, float radiusSquared) {
        float _t0 = centerX - originX;
        float _t1 = centerY - originY;
        float _t4 = Math.fma(dirX, _t1, -(dirY * _t0));
        float _t7 = (float) Math.sqrt(Math.fma(-_t4, _t4, radiusSquared));
        float _t11 = Math.fma(dirX, _t0, Math.fma(dirY, _t1, _t7));
        float _t12 = Math.fma(dirX, _t0, Math.fma(dirY, _t1, -_t7));
        if ((_t12 < _t11) && (_t11 >= 0.0f)) return new FloatHit2(1, _t12, _t11);
        return FloatHit2.MISS;
    }

    /**
     * Determine whether the ray with the given origin and normalized direction intersects the
     * circle with the given center and square radius, and compute the values of the parameter
     * <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far point of
     * intersection.
     * <p>
     * The direction must be normalized: the kernel projects the vector from the origin to the
     * center onto it, and takes the 2D cross product of the direction with that vector as the
     * perpendicular distance from the center to the ray, which only holds for a unit direction, and
     * <i>t</i> is then a distance along the ray. The radius is passed squared.
     * <p>
     * An intersection is reported for a ray whose origin lies inside the circle - the near value is
     * negative in that case. A ray that only grazes the circle tangentially does not count, and
     * neither does a circle lying entirely behind the origin.
     * <p>
     * Reference: <a
     * href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code dir} must have unit length; {@code radiusSquared} must not be negative.
     *
     * @param origin the ray's origin
     * @param dir the ray's normalized direction
     * @param center the circle's center
     * @param radiusSquared the square of the circle's radius
     * @return a {@link FloatHit2} whose {@link FloatHit2#hit() hit()} is <code>true</code> iff the
     *        ray intersects the circle, and whose components then hold the values of the parameter
     *        <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far
     *        intersection
     */
    public static FloatHit2 intersectRayCircle(Float2 origin, Float2 dir, Float2 center, float radiusSquared) {
        return intersectRayCircle(origin.x(), origin.y(), dir.x(), dir.y(), center.x(), center.y(), radiusSquared);
    }

    /**
     * Test whether the ray with the given origin and direction intersects the line containing the
     * given point and having the given normal, and determine the value of the parameter <i>t</i> in
     * the ray equation <i>p(t) = origin + t * dir</i> of the intersection point.
     * <p>
     * Only a hit on the front side of the line is reported: the ray must start on the side its
     * {@code normal} points to and travel towards the line. This method returns <code>-1.0</code>
     * if the ray does not intersect the line, because it is either (nearly) parallel to the line,
     * or its direction points away from the line, or the ray's origin is on the <i>negative</i>
     * side of the line (i.e. the line's normal points away from the ray's origin).
     * <p>
     * Neither {@code dir} nor {@code normal} needs to be of unit length; <i>t</i> is measured in
     * units of {@code dir}. The near-parallel rejection compares the dot product of {@code normal}
     * and {@code dir} against <code>-epsilon</code>, so {@code epsilon} is a threshold on a
     * quantity that scales with the lengths of both, not a scale-free angle.
     * <p>
     * Valid input: {@code (dirX, dirY)} must be non-zero; {@code (normalX, normalY)} must be
     * non-zero; {@code epsilon} must not be negative.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's direction
     * @param dirY the y coordinate of the ray's direction
     * @param pointX the x coordinate of a point on the line
     * @param pointY the y coordinate of a point on the line
     * @param normalX the x coordinate of the line's normal
     * @param normalY the y coordinate of the line's normal
     * @param epsilon a small positive epsilon for rejecting rays that are nearly parallel to the
     *        line
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> of the intersection point, if the ray intersects the line; <code>-1.0</code>
     *        otherwise
     */
    public static float intersectRayLine(float originX, float originY, float dirX, float dirY, float pointX, float pointY, float normalX, float normalY, float epsilon) {
        float _t4 = Math.fma(dirX, normalX, dirY * normalY);
        float _t6 = Math.fma(normalX, pointX - originX, normalY * (pointY - originY)) / _t4;
        return _t4 < -epsilon ? _t6 >= 0.0f ? _t6 : -1.0f : -1.0f;
    }

    /**
     * Test whether the ray with the given origin and direction intersects the line containing the
     * given point and having the given normal, and determine the value of the parameter <i>t</i> in
     * the ray equation <i>p(t) = origin + t * dir</i> of the intersection point.
     * <p>
     * Only a hit on the front side of the line is reported: the ray must start on the side its
     * {@code normal} points to and travel towards the line. This method returns <code>-1.0</code>
     * if the ray does not intersect the line, because it is either (nearly) parallel to the line,
     * or its direction points away from the line, or the ray's origin is on the <i>negative</i>
     * side of the line (i.e. the line's normal points away from the ray's origin).
     * <p>
     * Neither {@code dir} nor {@code normal} needs to be of unit length; <i>t</i> is measured in
     * units of {@code dir}. The near-parallel rejection compares the dot product of {@code normal}
     * and {@code dir} against <code>-epsilon</code>, so {@code epsilon} is a threshold on a
     * quantity that scales with the lengths of both, not a scale-free angle.
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code normal} must be non-zero; {@code epsilon}
     * must not be negative.
     *
     * @param origin the ray's origin
     * @param dir the ray's direction
     * @param point a point on the line
     * @param normal the line's normal
     * @param epsilon a small positive epsilon for rejecting rays that are nearly parallel to the
     *        line
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> of the intersection point, if the ray intersects the line; <code>-1.0</code>
     *        otherwise
     */
    public static float intersectRayLine(Float2 origin, Float2 dir, Float2 point, Float2 normal, float epsilon) {
        return intersectRayLine(origin.x(), origin.y(), dir.x(), dir.y(), point.x(), point.y(), normal.x(), normal.y(), epsilon);
    }

    /**
     * Determine whether the ray with the given origin and direction intersects the undirected line
     * segment given by its two end points, and determine the value of the parameter <i>t</i> in the
     * ray equation <i>p(t) = origin + t * dir</i> of the intersection point.
     * <p>
     * Only the forward half of the ray counts: the intersection must have <i>t</i> greater than or
     * equal to <code>0</code>, and its parameter along the segment must lie in <code>[0, 1]</code>,
     * with both end points included. The direction does not have to be of unit length - <i>t</i> is
     * measured in multiples of it. A ray parallel to the segment, including a collinear one, is a
     * miss.
     * <p>
     * Valid input: {@code (dirX, dirY)} must be non-zero.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's direction
     * @param dirY the y coordinate of the ray's direction
     * @param aX the x coordinate of the line segment's first end point
     * @param aY the y coordinate of the line segment's first end point
     * @param bX the x coordinate of the line segment's second end point
     * @param bY the y coordinate of the line segment's second end point
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> of the intersection point, if the ray intersects the line segment;
     *        <code>-1.0</code> otherwise
     */
    public static float intersectRayLineSegment(float originX, float originY, float dirX, float dirY, float aX, float aY, float bX, float bY) {
        float _t0 = bX - aX;
        float _t1 = originY - aY;
        float _t2 = bY - aY;
        float _t3 = originX - aX;
        float _t10_inv = 1.0f / Math.fma(dirX, _t2, -(dirY * _t0));
        float _t13 = Math.fma(dirX, _t1, -(dirY * _t3)) * _t10_inv;
        float _t14 = Math.fma(_t0, _t1, -(_t2 * _t3)) * _t10_inv;
        return _t14 >= 0.0f ? _t13 >= 0.0f ? _t13 <= 1.0f ? _t14 : -1.0f : -1.0f : -1.0f;
    }

    /**
     * Determine whether the ray with the given origin and direction intersects the undirected line
     * segment given by its two end points, and determine the value of the parameter <i>t</i> in the
     * ray equation <i>p(t) = origin + t * dir</i> of the intersection point.
     * <p>
     * Only the forward half of the ray counts: the intersection must have <i>t</i> greater than or
     * equal to <code>0</code>, and its parameter along the segment must lie in <code>[0, 1]</code>,
     * with both end points included. The direction does not have to be of unit length - <i>t</i> is
     * measured in multiples of it. A ray parallel to the segment, including a collinear one, is a
     * miss.
     * <p>
     * Valid input: {@code dir} must be non-zero.
     *
     * @param origin the ray's origin
     * @param dir the ray's direction
     * @param a the line segment's first end point
     * @param b the line segment's second end point
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> of the intersection point, if the ray intersects the line segment;
     *        <code>-1.0</code> otherwise
     */
    public static float intersectRayLineSegment(Float2 origin, Float2 dir, Float2 a, Float2 b) {
        return intersectRayLineSegment(origin.x(), origin.y(), dir.x(), dir.y(), a.x(), a.y(), b.x(), b.y());
    }

    /**
     * Determine the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     * dir</i> at which the given ray intersects the plane containing the given point and having the
     * given normal.
     * <p>
     * Only a hit on the plane's front is found: the dot product of the plane's normal and the ray's
     * direction must be less than {@code -epsilon}, and the hit must lie on the forward part of the
     * ray. There is thus no intersection when the ray is parallel or nearly parallel to the plane,
     * when its direction points along the plane's normal, or when the ray's origin lies on the
     * negative side of the plane, that is the side the normal points away from.
     * <p>
     * The plane's normal need not be unit length, since <i>t</i> is unaffected by its scale. The
     * near-parallel rejection compares {@code epsilon} against the dot product of the normal and
     * the direction, so the effective angular threshold depends on the length of both. <i>t</i> is
     * measured in units of the direction's length, and equals the distance from the origin to the
     * plane only for a normalized direction.
     * <p>
     * Reference: <a
     * href="https://www.siggraph.org/education/materials/HyperGraph/raytrace/rayplane_intersection.htm">https://www.siggraph.org/</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must be non-zero; {@code (normalX, normalY, normalZ)}
     * must be non-zero; {@code epsilon} must not be negative.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param originZ the z coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's direction
     * @param dirY the y coordinate of the ray's direction
     * @param dirZ the z coordinate of the ray's direction
     * @param pointX the x coordinate of a point on the plane
     * @param pointY the y coordinate of a point on the plane
     * @param pointZ the z coordinate of a point on the plane
     * @param normalX the x coordinate of the plane's normal
     * @param normalY the y coordinate of the plane's normal
     * @param normalZ the z coordinate of the plane's normal
     * @param epsilon some small epsilon for when the ray is parallel to the plane
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> of the intersection point, if the ray intersects the plane; <code>-1</code>
     *        otherwise
     */
    public static float intersectRayPlane(float originX, float originY, float originZ, float dirX, float dirY, float dirZ, float pointX, float pointY, float pointZ, float normalX, float normalY, float normalZ, float epsilon) {
        float _t6 = Math.fma(dirZ, normalZ, Math.fma(dirX, normalX, dirY * normalY));
        float _t9 = Math.fma(normalZ, pointZ - originZ, Math.fma(normalX, pointX - originX, normalY * (pointY - originY))) / _t6;
        return _t6 < -epsilon ? _t9 >= 0.0f ? _t9 : -1.0f : -1.0f;
    }

    /**
     * Determine the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     * dir</i> at which the given ray intersects the plane containing the given point and having the
     * given normal.
     * <p>
     * Only a hit on the plane's front is found: the dot product of the plane's normal and the ray's
     * direction must be less than {@code -epsilon}, and the hit must lie on the forward part of the
     * ray. There is thus no intersection when the ray is parallel or nearly parallel to the plane,
     * when its direction points along the plane's normal, or when the ray's origin lies on the
     * negative side of the plane, that is the side the normal points away from.
     * <p>
     * The plane's normal need not be unit length, since <i>t</i> is unaffected by its scale. The
     * near-parallel rejection compares {@code epsilon} against the dot product of the normal and
     * the direction, so the effective angular threshold depends on the length of both. <i>t</i> is
     * measured in units of the direction's length, and equals the distance from the origin to the
     * plane only for a normalized direction.
     * <p>
     * Reference: <a
     * href="https://www.siggraph.org/education/materials/HyperGraph/raytrace/rayplane_intersection.htm">https://www.siggraph.org/</a>
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code normal} must be non-zero; {@code epsilon}
     * must not be negative.
     *
     * @param origin the ray's origin
     * @param dir the ray's direction
     * @param point a point on the plane
     * @param normal the plane's normal
     * @param epsilon some small epsilon for when the ray is parallel to the plane
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> of the intersection point, if the ray intersects the plane; <code>-1</code>
     *        otherwise
     */
    public static float intersectRayPlane(Float3 origin, Float3 dir, Float3 point, Float3 normal, float epsilon) {
        return intersectRayPlane(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), point.x(), point.y(), point.z(), normal.x(), normal.y(), normal.z(), epsilon);
    }

    /**
     * Test whether the ray with the given origin and normalized direction intersects the sphere
     * with the given center and the given squared radius, and determine the parameter <i>t</i> in
     * the ray equation <i>p(t) = origin + t * dir</i> at the near and far intersection.
     * <p>
     * The direction must be normalized: the near and far parameters are computed as distances along
     * the ray, and the squared length of the origin-to-center vector's component orthogonal to the
     * direction is taken as the squared distance from the center to the ray's line. The radius is
     * passed already squared.
     * <p>
     * A ray whose origin lies inside the sphere intersects it, and its near parameter is then
     * negative; a sphere lying entirely behind the origin does not intersect. A ray that only
     * touches the sphere tangentially, where the near and far parameter coincide, is not counted as
     * an intersection either.
     * <p>
     * Reference: <a
     * href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must have unit length; {@code radiusSquared} must not
     * be negative.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param originZ the z coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's normalized direction
     * @param dirY the y coordinate of the ray's normalized direction
     * @param dirZ the z coordinate of the ray's normalized direction
     * @param centerX the x coordinate of the sphere's center
     * @param centerY the y coordinate of the sphere's center
     * @param centerZ the z coordinate of the sphere's center
     * @param radiusSquared the square of the sphere's radius
     * @return a {@link FloatHit2} whose {@link FloatHit2#hit() hit()} is <code>true</code> iff the
     *        ray intersects the sphere, and whose components then hold the values of the parameter
     *        <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far
     *        intersection
     */
    public static FloatHit2 intersectRaySphere(float originX, float originY, float originZ, float dirX, float dirY, float dirZ, float centerX, float centerY, float centerZ, float radiusSquared) {
        float _t0 = centerX - originX;
        float _t1 = centerY - originY;
        float _t2 = centerZ - originZ;
        float _t5 = Math.fma(dirZ, _t2, Math.fma(dirX, _t0, dirY * _t1));
        float _t6 = Math.fma(-dirX, _t5, _t0);
        float _t7 = Math.fma(-dirY, _t5, _t1);
        float _t8 = Math.fma(-dirZ, _t5, _t2);
        float _t15 = (float) Math.sqrt(Math.fma(-_t6, _t6, Math.fma(-_t7, _t7, Math.fma(-_t8, _t8, radiusSquared))));
        float _t21 = Math.fma(dirX, _t0, Math.fma(dirY, _t1, Math.fma(dirZ, _t2, _t15)));
        float _t22 = Math.fma(dirX, _t0, Math.fma(dirY, _t1, Math.fma(dirZ, _t2, -_t15)));
        if ((_t22 < _t21) && (_t21 >= 0.0f)) return new FloatHit2(1, _t22, _t21);
        return FloatHit2.MISS;
    }

    /**
     * Test whether the ray with the given origin and normalized direction intersects the sphere
     * with the given center and the given squared radius, and determine the parameter <i>t</i> in
     * the ray equation <i>p(t) = origin + t * dir</i> at the near and far intersection.
     * <p>
     * The direction must be normalized: the near and far parameters are computed as distances along
     * the ray, and the squared length of the origin-to-center vector's component orthogonal to the
     * direction is taken as the squared distance from the center to the ray's line. The radius is
     * passed already squared.
     * <p>
     * A ray whose origin lies inside the sphere intersects it, and its near parameter is then
     * negative; a sphere lying entirely behind the origin does not intersect. A ray that only
     * touches the sphere tangentially, where the near and far parameter coincide, is not counted as
     * an intersection either.
     * <p>
     * Reference: <a
     * href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code dir} must have unit length; {@code radiusSquared} must not be negative.
     *
     * @param origin the ray's origin
     * @param dir the ray's normalized direction
     * @param center the sphere's center
     * @param radiusSquared the square of the sphere's radius
     * @return a {@link FloatHit2} whose {@link FloatHit2#hit() hit()} is <code>true</code> iff the
     *        ray intersects the sphere, and whose components then hold the values of the parameter
     *        <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far
     *        intersection
     */
    public static FloatHit2 intersectRaySphere(Float3 origin, Float3 dir, Float3 center, float radiusSquared) {
        return intersectRaySphere(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), center.x(), center.y(), center.z(), radiusSquared);
    }

    /** Private tail of {@code intersectRayTriangle}; reached only through it. */
    private static float intersectRayTriangle_s25803267_tail(float _t0, float _t21, float _t3, float _t22, float _t5, float _t23, float _t6, float _t7, float _t8, float dirZ, float _t26, float dirX, float _t27, float dirY, float _t28, float epsilon, float _t4, float _t2, float _t1) {
        float _t33 = Math.fma(_t0, _t21, Math.fma(_t3, _t22, _t5 * _t23));
        float _t33_inv = 1.0f / _t33;
        float _t34 = Math.fma(_t6, _t21, Math.fma(_t7, _t22, _t8 * _t23));
        float _t35 = Math.fma(dirZ, _t26, Math.fma(dirX, _t27, dirY * _t28));
        float _t36 = _t34 * _t33_inv;
        return Math.abs(_t33) >= epsilon ? _t36 >= 0.0f ? _t36 <= 1.0f ? _t35 * _t33_inv >= 0.0f ? (_t35 + _t34) * _t33_inv <= 1.0f ? Math.fma(_t4, _t26, Math.fma(_t2, _t27, _t1 * _t28)) * _t33_inv : -1.0f : -1.0f : -1.0f : -1.0f : -1.0f;
    }

    /**
     * Determine the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     * dir</i> at which the given ray intersects the triangle formed by the three given vertices.
     * <p>
     * This is an implementation of the <a
     * href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage Ray/Triangle
     * Intersection</a> method.
     * <p>
     * This test does not take the winding order of the triangle into account, so a front-facing
     * triangle is intersected just like a back-facing one. Use {@code intersectRayTriangleFront}
     * for the backface-culling variant.
     * <p>
     * The intersection is computed against the whole line through the origin along the direction,
     * not only against its forward part: when the triangle lies behind the origin, <i>t</i> comes
     * out negative. <i>t</i> is measured in units of the direction's length.
     * <p>
     * Rays nearly parallel to the triangle's plane are rejected by comparing {@code epsilon}
     * against the magnitude of the determinant, which scales with the length of the direction and
     * with the area of the triangle, so {@code epsilon} is not a pure angular threshold.
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must be non-zero; {@code epsilon} must not be
     * negative.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param originZ the z coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's direction
     * @param dirY the y coordinate of the ray's direction
     * @param dirZ the z coordinate of the ray's direction
     * @param v0X the x coordinate of the first vertex of the triangle
     * @param v0Y the y coordinate of the first vertex of the triangle
     * @param v0Z the z coordinate of the first vertex of the triangle
     * @param v1X the x coordinate of the second vertex of the triangle
     * @param v1Y the y coordinate of the second vertex of the triangle
     * @param v1Z the z coordinate of the second vertex of the triangle
     * @param v2X the x coordinate of the third vertex of the triangle
     * @param v2Y the y coordinate of the third vertex of the triangle
     * @param v2Z the z coordinate of the third vertex of the triangle
     * @param epsilon a small epsilon for rejecting rays that are almost parallel to the triangle's
     *        plane
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> of the point of intersection, if the line along the ray meets the triangle -
     *        negative when the triangle lies behind the ray's origin; <code>-1</code> otherwise,
     *        which is also the value produced by a hit exactly one direction length behind the
     *        origin
     */
    public static float intersectRayTriangle(float originX, float originY, float originZ, float dirX, float dirY, float dirZ, float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z, float epsilon) {
        float _t0 = v1Z - v0Z;
        float _t1 = v2Y - v0Y;
        float _t2 = v2X - v0X;
        float _t3 = v1X - v0X;
        float _t4 = v2Z - v0Z;
        float _t5 = v1Y - v0Y;
        float _t6 = originZ - v0Z;
        float _t7 = originX - v0X;
        float _t8 = originY - v0Y;
        return intersectRayTriangle_s25803267_tail(_t0, Math.fma(dirX, _t1, -(dirY * _t2)), _t3, Math.fma(dirY, _t4, -(dirZ * _t1)), _t5, Math.fma(dirZ, _t2, -(dirX * _t4)), _t6, _t7, _t8, dirZ, Math.fma(_t7, _t5, -(_t8 * _t3)), dirX, Math.fma(_t8, _t0, -(_t6 * _t5)), dirY, Math.fma(_t6, _t3, -(_t7 * _t0)), epsilon, _t4, _t2, _t1);
    }

    /**
     * Determine the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     * dir</i> at which the given ray intersects the triangle formed by the three given vertices.
     * <p>
     * This is an implementation of the <a
     * href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage Ray/Triangle
     * Intersection</a> method.
     * <p>
     * This test does not take the winding order of the triangle into account, so a front-facing
     * triangle is intersected just like a back-facing one. Use {@code intersectRayTriangleFront}
     * for the backface-culling variant.
     * <p>
     * The intersection is computed against the whole line through the origin along the direction,
     * not only against its forward part: when the triangle lies behind the origin, <i>t</i> comes
     * out negative. <i>t</i> is measured in units of the direction's length.
     * <p>
     * Rays nearly parallel to the triangle's plane are rejected by comparing {@code epsilon}
     * against the magnitude of the determinant, which scales with the length of the direction and
     * with the area of the triangle, so {@code epsilon} is not a pure angular threshold.
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code epsilon} must not be negative.
     *
     * @param origin the ray's origin
     * @param dir the ray's direction
     * @param v0 the first vertex of the triangle
     * @param v1 the second vertex of the triangle
     * @param v2 the third vertex of the triangle
     * @param epsilon a small epsilon for rejecting rays that are almost parallel to the triangle's
     *        plane
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> of the point of intersection, if the line along the ray meets the triangle -
     *        negative when the triangle lies behind the ray's origin; <code>-1</code> otherwise,
     *        which is also the value produced by a hit exactly one direction length behind the
     *        origin
     */
    public static float intersectRayTriangle(Float3 origin, Float3 dir, Float3 v0, Float3 v1, Float3 v2, float epsilon) {
        return intersectRayTriangle(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /** Private tail of {@code intersectRayTriangleFront}; reached only through it. */
    private static float intersectRayTriangleFront_s25803267_tail(float _t6, float _t3, float _t7, float _t0, float _t21, float _t22, float _t5, float _t23, float _t8, float dirZ, float _t26, float dirX, float _t27, float dirY, float epsilon, float _t4, float _t2, float _t1) {
        float _t28 = Math.fma(_t6, _t3, -(_t7 * _t0));
        float _t33 = Math.fma(_t0, _t21, Math.fma(_t3, _t22, _t5 * _t23));
        float _t34 = Math.fma(_t6, _t21, Math.fma(_t7, _t22, _t8 * _t23));
        float _t35 = Math.fma(dirZ, _t26, Math.fma(dirX, _t27, dirY * _t28));
        return _t33 > epsilon ? _t34 >= 0.0f ? _t34 <= _t33 ? _t35 >= 0.0f ? _t35 + _t34 <= _t33 ? Math.fma(_t4, _t26, Math.fma(_t2, _t27, _t1 * _t28)) / _t33 : -1.0f : -1.0f : -1.0f : -1.0f : -1.0f;
    }

    /**
     * Determine whether the ray with the given origin and direction intersects the frontface of the
     * triangle consisting of the three given vertices, and determine the value of the parameter
     * <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the point of intersection.
     * <p>
     * This is an implementation of the <a
     * href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage Ray/Triangle
     * Intersection</a> method.
     * <p>
     * This method implements backface culling: a triangle in clockwise winding order assuming a
     * <i>right-handed</i> coordinate system when seen along the ray's direction is a miss, even
     * when the ray does meet it. This is in compliance with how OpenGL handles backface culling
     * with default frontface/backface settings. The cull and the near-parallel rejection are one
     * and the same test, namely that the determinant of the intersection system is greater than
     * <code>epsilon</code>. That determinant scales with the length of the direction and with the
     * size of the triangle, so <code>epsilon</code> is not a distance and has to be chosen for the
     * scale at hand.
     * <p>
     * The whole line through the origin is intersected, not just the forward half of the ray: a
     * triangle behind the origin yields a negative <i>t</i> rather than a miss. Callers that need a
     * forward-only hit have to check the sign themselves, or use {@code testRayTriangleFront},
     * which additionally requires <i>t</i> to be at least <code>epsilon</code>. The direction does
     * not have to be of unit length - <i>t</i> is measured in multiples of it.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param originZ the z coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's direction
     * @param dirY the y coordinate of the ray's direction
     * @param dirZ the z coordinate of the ray's direction
     * @param v0X the x coordinate of the first vertex of the triangle
     * @param v0Y the y coordinate of the first vertex of the triangle
     * @param v0Z the z coordinate of the first vertex of the triangle
     * @param v1X the x coordinate of the second vertex of the triangle
     * @param v1Y the y coordinate of the second vertex of the triangle
     * @param v1Z the z coordinate of the second vertex of the triangle
     * @param v2X the x coordinate of the third vertex of the triangle
     * @param v2Y the y coordinate of the third vertex of the triangle
     * @param v2Z the z coordinate of the third vertex of the triangle
     * @param epsilon a small epsilon that the determinant of the intersection system must exceed,
     *        rejecting rays that are almost parallel to the triangle
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> of the point of intersection if the ray intersects the frontface of the
     *        triangle; <code>-1.0</code> otherwise
     */
    public static float intersectRayTriangleFront(float originX, float originY, float originZ, float dirX, float dirY, float dirZ, float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z, float epsilon) {
        float _t0 = v1Z - v0Z;
        float _t1 = v2Y - v0Y;
        float _t2 = v2X - v0X;
        float _t3 = v1X - v0X;
        float _t4 = v2Z - v0Z;
        float _t5 = v1Y - v0Y;
        float _t6 = originZ - v0Z;
        float _t7 = originX - v0X;
        float _t8 = originY - v0Y;
        return intersectRayTriangleFront_s25803267_tail(_t6, _t3, _t7, _t0, Math.fma(dirX, _t1, -(dirY * _t2)), Math.fma(dirY, _t4, -(dirZ * _t1)), _t5, Math.fma(dirZ, _t2, -(dirX * _t4)), _t8, dirZ, Math.fma(_t7, _t5, -(_t8 * _t3)), dirX, Math.fma(_t8, _t0, -(_t6 * _t5)), dirY, epsilon, _t4, _t2, _t1);
    }

    /**
     * Determine whether the ray with the given origin and direction intersects the frontface of the
     * triangle consisting of the three given vertices, and determine the value of the parameter
     * <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the point of intersection.
     * <p>
     * This is an implementation of the <a
     * href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage Ray/Triangle
     * Intersection</a> method.
     * <p>
     * This method implements backface culling: a triangle in clockwise winding order assuming a
     * <i>right-handed</i> coordinate system when seen along the ray's direction is a miss, even
     * when the ray does meet it. This is in compliance with how OpenGL handles backface culling
     * with default frontface/backface settings. The cull and the near-parallel rejection are one
     * and the same test, namely that the determinant of the intersection system is greater than
     * <code>epsilon</code>. That determinant scales with the length of the direction and with the
     * size of the triangle, so <code>epsilon</code> is not a distance and has to be chosen for the
     * scale at hand.
     * <p>
     * The whole line through the origin is intersected, not just the forward half of the ray: a
     * triangle behind the origin yields a negative <i>t</i> rather than a miss. Callers that need a
     * forward-only hit have to check the sign themselves, or use {@code testRayTriangleFront},
     * which additionally requires <i>t</i> to be at least <code>epsilon</code>. The direction does
     * not have to be of unit length - <i>t</i> is measured in multiples of it.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param origin the ray's origin
     * @param dir the ray's direction
     * @param v0 the first vertex of the triangle
     * @param v1 the second vertex of the triangle
     * @param v2 the third vertex of the triangle
     * @param epsilon a small epsilon that the determinant of the intersection system must exceed,
     *        rejecting rays that are almost parallel to the triangle
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> of the point of intersection if the ray intersects the frontface of the
     *        triangle; <code>-1.0</code> otherwise
     */
    public static float intersectRayTriangleFront(Float3 origin, Float3 dir, Float3 v0, Float3 v1, Float3 v2, float epsilon) {
        return intersectRayTriangleFront(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /**
     * Test whether the one sphere with the given center and square radius intersects the other
     * sphere with the given center and square radius, and determine the circle in which they
     * intersect.
     * <p>
     * Both radii are passed already squared, and the computation stays in squared distances up to
     * the final square root that turns the squared circle radius into a radius. Two spheres that
     * only touch give a circle of radius <code>0</code>, and one sphere contained in the other
     * without touching it is not an intersection. Two spheres whose centers coincide are never
     * reported as intersecting, not even when they are identical, because their intersection is not
     * a circle.
     * <p>
     * The normal vector of the circle of intersection can simply be obtained by subtracting the
     * center of either sphere from the other.
     * <p>
     * Reference: <a
     * href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     *
     * @param aX the x coordinate of the first sphere's center
     * @param aY the y coordinate of the first sphere's center
     * @param aZ the z coordinate of the first sphere's center
     * @param radiusSquaredA the square of the first sphere's radius
     * @param bX the x coordinate of the second sphere's center
     * @param bY the y coordinate of the second sphere's center
     * @param bZ the z coordinate of the second sphere's center
     * @param radiusSquaredB the square of the second sphere's radius
     * @return a {@link FloatHit4} whose {@link FloatHit4#hit() hit()} is <code>true</code> iff both
     *        spheres intersect in a circle, and whose components then hold the center of the circle
     *        of intersection in its <code>(x, y, z)</code> components and the radius of that circle
     *        in its w component
     */
    public static FloatHit4 intersectSphereSphere(float aX, float aY, float aZ, float radiusSquaredA, float bX, float bY, float bZ, float radiusSquaredB) {
        float _t0 = bZ - aZ;
        float _t1 = bX - aX;
        float _t2 = bY - aY;
        float _t3 = radiusSquaredB < radiusSquaredA ? radiusSquaredB : radiusSquaredA;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t13 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t0, _t0, _t3 - (radiusSquaredB < radiusSquaredA ? radiusSquaredA : radiusSquaredB))));
        float _sp0 = _t13 / (_t9 + _t9);
        float _t16 = _t3 - _t13 * _t13 / (4.0f * _t9);
        if ((_t9 != 0.0f) && (_t16 >= 0.0f)) return new FloatHit4(1, _sp0 * (radiusSquaredB < radiusSquaredA ? aX - bX : _t1) + (radiusSquaredB < radiusSquaredA ? bX : aX), _sp0 * (radiusSquaredB < radiusSquaredA ? aY - bY : _t2) + (radiusSquaredB < radiusSquaredA ? bY : aY), _sp0 * (radiusSquaredB < radiusSquaredA ? aZ - bZ : _t0) + (radiusSquaredB < radiusSquaredA ? bZ : aZ), (float) Math.sqrt(_t16));
        return FloatHit4.MISS;
    }

    /**
     * Test whether the one sphere with the given center and square radius intersects the other
     * sphere with the given center and square radius, and determine the circle in which they
     * intersect.
     * <p>
     * Both radii are passed already squared, and the computation stays in squared distances up to
     * the final square root that turns the squared circle radius into a radius. Two spheres that
     * only touch give a circle of radius <code>0</code>, and one sphere contained in the other
     * without touching it is not an intersection. Two spheres whose centers coincide are never
     * reported as intersecting, not even when they are identical, because their intersection is not
     * a circle.
     * <p>
     * The normal vector of the circle of intersection can simply be obtained by subtracting the
     * center of either sphere from the other.
     * <p>
     * Reference: <a
     * href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     *
     * @param a the first sphere's center
     * @param radiusSquaredA the square of the first sphere's radius
     * @param b the second sphere's center
     * @param radiusSquaredB the square of the second sphere's radius
     * @return a {@link FloatHit4} whose {@link FloatHit4#hit() hit()} is <code>true</code> iff both
     *        spheres intersect in a circle, and whose components then hold the center of the circle
     *        of intersection in its <code>(x, y, z)</code> components and the radius of that circle
     *        in its w component
     */
    public static FloatHit4 intersectSphereSphere(Float3 a, float radiusSquaredA, Float3 b, float radiusSquaredB) {
        return intersectSphereSphere(a.x(), a.y(), a.z(), radiusSquaredA, b.x(), b.y(), b.z(), radiusSquaredB);
    }

    /**
     * Test whether the axis-aligned box with minimum corner <code>(minXA, minYA, minZA)</code> and
     * maximum corner <code>(maxXA, maxYA, maxZA)</code> intersects the axis-aligned box with
     * minimum corner <code>(minXB, minYB, minZB)</code> and maximum corner
     * <code>(maxXB, maxYB, maxZB)</code>.
     * <p>
     * The test is the overlap of the two boxes on each of the three coordinate axes, and it is
     * inclusive: boxes that only touch on a face, an edge or a corner count as intersecting. Each
     * box is expected to have its minimum corner component-wise less than or equal to its maximum
     * corner.
     * <p>
     * Valid input: {@code (minXA, minYA, minZA)} must not exceed {@code (maxXA, maxYA, maxZA)} in
     * any component; {@code (minXB, minYB, minZB)} must not exceed {@code (maxXB, maxYB, maxZB)} in
     * any component.
     *
     * @param minXA the x coordinate of the minimum corner of the first axis-aligned box
     * @param minYA the y coordinate of the minimum corner of the first axis-aligned box
     * @param minZA the z coordinate of the minimum corner of the first axis-aligned box
     * @param maxXA the x coordinate of the maximum corner of the first axis-aligned box
     * @param maxYA the y coordinate of the maximum corner of the first axis-aligned box
     * @param maxZA the z coordinate of the maximum corner of the first axis-aligned box
     * @param minXB the x coordinate of the minimum corner of the second axis-aligned box
     * @param minYB the y coordinate of the minimum corner of the second axis-aligned box
     * @param minZB the z coordinate of the minimum corner of the second axis-aligned box
     * @param maxXB the x coordinate of the maximum corner of the second axis-aligned box
     * @param maxYB the y coordinate of the maximum corner of the second axis-aligned box
     * @param maxZB the z coordinate of the maximum corner of the second axis-aligned box
     * @return <code>true</code> iff both axis-aligned boxes intersect
     */
    public static boolean testAabbAabb(float minXA, float minYA, float minZA, float maxXA, float maxYA, float maxZA, float minXB, float minYB, float minZB, float maxXB, float maxYB, float maxZB) {
        if (!(maxXA >= minXB)) return false;
        if (!(maxYA >= minYB)) return false;
        if (!(maxZA >= minZB)) return false;
        if (!(minXA <= maxXB)) return false;
        if (!(minYA <= maxYB)) return false;
        return minZA <= maxZB;
    }

    /**
     * Test whether the inner axis-aligned box given as its minimum corner
     * <code>(iMinX, iMinY, iMinZ)</code> and maximum corner <code>(iMaxX, iMaxY, iMaxZ)</code> lies
     * completely inside the outer axis-aligned box given as its minimum corner
     * <code>(oMinX, oMinY, oMinZ)</code> and maximum corner <code>(oMaxX, oMaxY, oMaxZ)</code>.
     * <p>
     * Containment is inclusive: an inner box that touches a side of the outer box from within still
     * counts as contained. Both boxes are assumed to be given with each minimum coordinate less
     * than or equal to the corresponding maximum coordinate. The test is two inclusive comparisons
     * per axis on the corners exactly as given, so an inner box handed over inverted - with a
     * minimum coordinate above the corresponding maximum - is reported as contained.
     * <p>
     * Valid input: {@code (oMinX, oMinY, oMinZ)} must not exceed {@code (oMaxX, oMaxY, oMaxZ)} in
     * any component; {@code (iMinX, iMinY, iMinZ)} must not exceed {@code (iMaxX, iMaxY, iMaxZ)} in
     * any component.
     *
     * @param oMinX the x coordinate of the minimum corner of the outer axis-aligned box
     * @param oMinY the y coordinate of the minimum corner of the outer axis-aligned box
     * @param oMinZ the z coordinate of the minimum corner of the outer axis-aligned box
     * @param oMaxX the x coordinate of the maximum corner of the outer axis-aligned box
     * @param oMaxY the y coordinate of the maximum corner of the outer axis-aligned box
     * @param oMaxZ the z coordinate of the maximum corner of the outer axis-aligned box
     * @param iMinX the x coordinate of the minimum corner of the inner axis-aligned box
     * @param iMinY the y coordinate of the minimum corner of the inner axis-aligned box
     * @param iMinZ the z coordinate of the minimum corner of the inner axis-aligned box
     * @param iMaxX the x coordinate of the maximum corner of the inner axis-aligned box
     * @param iMaxY the y coordinate of the maximum corner of the inner axis-aligned box
     * @param iMaxZ the z coordinate of the maximum corner of the inner axis-aligned box
     * @return <code>true</code> iff the inner axis-aligned box lies completely inside the outer
     *        axis-aligned box; <code>false</code> otherwise
     */
    public static boolean testAabbAabbContains(float oMinX, float oMinY, float oMinZ, float oMaxX, float oMaxY, float oMaxZ, float iMinX, float iMinY, float iMinZ, float iMaxX, float iMaxY, float iMaxZ) {
        if (!(iMinX >= oMinX)) return false;
        if (!(iMaxX <= oMaxX)) return false;
        if (!(iMinY >= oMinY)) return false;
        if (!(iMaxY <= oMaxY)) return false;
        if (!(iMinZ >= oMinZ)) return false;
        return iMaxZ <= oMaxZ;
    }

    /**
     * Test whether the axis-aligned box given as its minimum and maximum corner intersects the
     * plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i>.
     * <p>
     * Only the two corners of the box that are extreme along the plane normal are tested, and the
     * comparison is inclusive, so a box that merely touches the plane counts as intersecting. The
     * normal <code>(a, b, c)</code> does not need to be normalized - only the signs of the two
     * corner distances are used.
     * <p>
     * Reference: <a
     * href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a>
     * ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component; {@code (a, b, c)} must be non-zero.
     *
     * @param minX the x coordinate of the minimum corner of the axis-aligned box
     * @param minY the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ the z coordinate of the maximum corner of the axis-aligned box
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @return <code>true</code> iff the axis-aligned box intersects the plane; <code>false</code>
     *        otherwise
     */
    public static boolean testAabbPlane(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, float a, float b, float c, float d) {
        if (!(Math.fma(a, a > 0.0f ? minX : maxX, Math.fma(b, b > 0.0f ? minY : maxY, Math.fma(c, c > 0.0f ? minZ : maxZ, d))) <= 0.0f)) return false;
        return Math.fma(a, a > 0.0f ? maxX : minX, Math.fma(b, b > 0.0f ? maxY : minY, Math.fma(c, c > 0.0f ? maxZ : minZ, d))) >= 0.0f;
    }

    /**
     * Test whether the axis-aligned box given as its minimum and maximum corner intersects the
     * plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i>.
     * <p>
     * Only the two corners of the box that are extreme along the plane normal are tested, and the
     * comparison is inclusive, so a box that merely touches the plane counts as intersecting. The
     * normal <code>(a, b, c)</code> does not need to be normalized - only the signs of the two
     * corner distances are used.
     * <p>
     * Reference: <a
     * href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a>
     * ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code (a, b, c)} must
     * be non-zero.
     *
     * @param min the minimum corner of the axis-aligned box
     * @param max the maximum corner of the axis-aligned box
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @return <code>true</code> iff the axis-aligned box intersects the plane; <code>false</code>
     *        otherwise
     */
    public static boolean testAabbPlane(Float3 min, Float3 max, float a, float b, float c, float d) {
        return testAabbPlane(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), a, b, c, d);
    }

    /**
     * Test whether the axis-aligned box with the given minimum and maximum corner intersects the
     * sphere with the given center and the given square radius.
     * <p>
     * The radius is passed already squared. Per axis the test takes the squared amount by which the
     * sphere's center lies outside the box's extent on that axis - zero while it lies within - and
     * compares the sum of the three, which is the squared distance from the center to the closest
     * point of the box, with the square radius. No square root is taken, and a sphere that only
     * touches the box counts as intersecting. The box is assumed to be given with each minimum
     * coordinate less than or equal to the corresponding maximum coordinate.
     * <p>
     * Reference: <a
     * href="http://stackoverflow.com/questions/4578967/cube-sphere-intersection-test#answer-4579069">http://stackoverflow.com</a>
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component; {@code radiusSquared} must not be negative.
     *
     * @param minX the x coordinate of the minimum corner of the axis-aligned box
     * @param minY the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ the z coordinate of the maximum corner of the axis-aligned box
     * @param centerX the x coordinate of the sphere's center
     * @param centerY the y coordinate of the sphere's center
     * @param centerZ the z coordinate of the sphere's center
     * @param radiusSquared the square of the sphere's radius
     * @return <code>true</code> iff the axis-aligned box intersects the sphere; <code>false</code>
     *        otherwise
     */
    public static boolean testAabbSphere(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, float centerX, float centerY, float centerZ, float radiusSquared) {
        float _t0 = centerX - maxX;
        float _t1 = centerX - minX;
        float _t2 = centerY - maxY;
        float _t3 = centerY - minY;
        float _t4 = centerZ - maxZ;
        float _t5 = centerZ - minZ;
        return radiusSquared - (centerX >= minX ? centerX <= maxX ? 0.0f : _t0 * _t0 : _t1 * _t1) - (centerY >= minY ? centerY <= maxY ? 0.0f : _t2 * _t2 : _t3 * _t3) - (centerZ >= minZ ? centerZ <= maxZ ? 0.0f : _t4 * _t4 : _t5 * _t5) >= 0.0f;
    }

    /**
     * Test whether the axis-aligned box with the given minimum and maximum corner intersects the
     * sphere with the given center and the given square radius.
     * <p>
     * The radius is passed already squared. Per axis the test takes the squared amount by which the
     * sphere's center lies outside the box's extent on that axis - zero while it lies within - and
     * compares the sum of the three, which is the squared distance from the center to the closest
     * point of the box, with the square radius. No square root is taken, and a sphere that only
     * touches the box counts as intersecting. The box is assumed to be given with each minimum
     * coordinate less than or equal to the corresponding maximum coordinate.
     * <p>
     * Reference: <a
     * href="http://stackoverflow.com/questions/4578967/cube-sphere-intersection-test#answer-4579069">http://stackoverflow.com</a>
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code radiusSquared}
     * must not be negative.
     *
     * @param min the minimum corner of the axis-aligned box
     * @param max the maximum corner of the axis-aligned box
     * @param center the sphere's center
     * @param radiusSquared the square of the sphere's radius
     * @return <code>true</code> iff the axis-aligned box intersects the sphere; <code>false</code>
     *        otherwise
     */
    public static boolean testAabbSphere(Float3 min, Float3 max, Float3 center, float radiusSquared) {
        return testAabbSphere(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), center.x(), center.y(), center.z(), radiusSquared);
    }

    /**
     * Test whether the axis-aligned rectangle with minimum corner <code>(minXA, minYA)</code> and
     * maximum corner <code>(maxXA, maxYA)</code> intersects the axis-aligned rectangle with minimum
     * corner <code>(minXB, minYB)</code> and maximum corner <code>(maxXB, maxYB)</code>.
     * <p>
     * The overlap test is inclusive on both rectangles: two rectangles that only touch along an
     * edge or in a single corner count as intersecting. Each rectangle's minimum corner must be
     * componentwise less than or equal to its maximum corner: a rectangle's own two corners are
     * never compared against each other, so for a rectangle given the other way round the result is
     * unspecified rather than empty.
     * <p>
     * Valid input: {@code (minXA, minYA)} must not exceed {@code (maxXA, maxYA)} in any component;
     * {@code (minXB, minYB)} must not exceed {@code (maxXB, maxYB)} in any component.
     *
     * @param minXA the x coordinate of the minimum corner of the first axis-aligned rectangle
     * @param minYA the y coordinate of the minimum corner of the first axis-aligned rectangle
     * @param maxXA the x coordinate of the maximum corner of the first axis-aligned rectangle
     * @param maxYA the y coordinate of the maximum corner of the first axis-aligned rectangle
     * @param minXB the x coordinate of the minimum corner of the second axis-aligned rectangle
     * @param minYB the y coordinate of the minimum corner of the second axis-aligned rectangle
     * @param maxXB the x coordinate of the maximum corner of the second axis-aligned rectangle
     * @param maxYB the y coordinate of the maximum corner of the second axis-aligned rectangle
     * @return <code>true</code> iff both axis-aligned rectangles intersect
     */
    public static boolean testAarAar(float minXA, float minYA, float maxXA, float maxYA, float minXB, float minYB, float maxXB, float maxYB) {
        if (!(maxXA >= minXB)) return false;
        if (!(maxYA >= minYB)) return false;
        if (!(minXA <= maxXB)) return false;
        return minYA <= maxYB;
    }

    /**
     * Test whether the axis-aligned rectangle with the given minimum corner and maximum corner
     * intersects the circle with the given center and the square radius {@code radiusSquared}.
     * <p>
     * The radius is passed squared. The squared distance from the circle's center to the point of
     * the rectangle nearest to it is compared against {@code radiusSquared}, so a circle that
     * merely touches the rectangle counts as intersecting, and so does either shape lying
     * completely inside the other.
     * <p>
     * Reference: <a
     * href="http://stackoverflow.com/questions/4578967/cube-sphere-intersection-test#answer-4579069">http://stackoverflow.com</a>
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component;
     * {@code radiusSquared} must not be negative.
     *
     * @param minX the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY the y coordinate of the maximum corner of the axis-aligned rectangle
     * @param centerX the x coordinate of the circle's center
     * @param centerY the y coordinate of the circle's center
     * @param radiusSquared the square of the circle's radius
     * @return <code>true</code> iff the axis-aligned rectangle intersects the circle;
     *        <code>false</code> otherwise
     */
    public static boolean testAarCircle(float minX, float minY, float maxX, float maxY, float centerX, float centerY, float radiusSquared) {
        float _t0 = centerX - maxX;
        float _t1 = centerX - minX;
        float _t2 = centerY - maxY;
        float _t3 = centerY - minY;
        return radiusSquared - (centerX >= minX ? centerX <= maxX ? 0.0f : _t0 * _t0 : _t1 * _t1) - (centerY >= minY ? centerY <= maxY ? 0.0f : _t2 * _t2 : _t3 * _t3) >= 0.0f;
    }

    /**
     * Test whether the axis-aligned rectangle with the given minimum corner and maximum corner
     * intersects the circle with the given center and the square radius {@code radiusSquared}.
     * <p>
     * The radius is passed squared. The squared distance from the circle's center to the point of
     * the rectangle nearest to it is compared against {@code radiusSquared}, so a circle that
     * merely touches the rectangle counts as intersecting, and so does either shape lying
     * completely inside the other.
     * <p>
     * Reference: <a
     * href="http://stackoverflow.com/questions/4578967/cube-sphere-intersection-test#answer-4579069">http://stackoverflow.com</a>
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code radiusSquared}
     * must not be negative.
     *
     * @param min the minimum corner of the axis-aligned rectangle
     * @param max the maximum corner of the axis-aligned rectangle
     * @param center the circle's center
     * @param radiusSquared the square of the circle's radius
     * @return <code>true</code> iff the axis-aligned rectangle intersects the circle;
     *        <code>false</code> otherwise
     */
    public static boolean testAarCircle(Float2 min, Float2 max, Float2 center, float radiusSquared) {
        return testAarCircle(min.x(), min.y(), max.x(), max.y(), center.x(), center.y(), radiusSquared);
    }

    /**
     * Test whether the axis-aligned rectangle with the given minimum corner and maximum corner
     * intersects the line with the general equation <i>a*x + b*y + c = 0</i>.
     * <p>
     * The line's normal <code>(a, b)</code> need not be normalized: only the signs of the line
     * equation evaluated at the rectangle's two extreme corners are compared against zero, and
     * those are unaffected by scaling <code>(a, b, c)</code>. A rectangle that merely touches the
     * line, with a corner exactly on it, counts as intersecting.
     * <p>
     * Reference: <a
     * href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a>
     * ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component;
     * {@code (a, b)} must be non-zero.
     *
     * @param minX the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY the y coordinate of the maximum corner of the axis-aligned rectangle
     * @param a the x factor in the line equation
     * @param b the y factor in the line equation
     * @param c the constant in the line equation
     * @return <code>true</code> iff the axis-aligned rectangle intersects the line;
     *        <code>false</code> otherwise
     */
    public static boolean testAarLine(float minX, float minY, float maxX, float maxY, float a, float b, float c) {
        if (!(Math.fma(a, a > 0.0f ? minX : maxX, Math.fma(b, b > 0.0f ? minY : maxY, c)) <= 0.0f)) return false;
        return Math.fma(a, a > 0.0f ? maxX : minX, Math.fma(b, b > 0.0f ? maxY : minY, c)) >= 0.0f;
    }

    /**
     * Test whether the axis-aligned rectangle with the given minimum corner and maximum corner
     * intersects the line with the general equation <i>a*x + b*y + c = 0</i>.
     * <p>
     * The line's normal <code>(a, b)</code> need not be normalized: only the signs of the line
     * equation evaluated at the rectangle's two extreme corners are compared against zero, and
     * those are unaffected by scaling <code>(a, b, c)</code>. A rectangle that merely touches the
     * line, with a corner exactly on it, counts as intersecting.
     * <p>
     * Reference: <a
     * href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a>
     * ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code (a, b)} must be
     * non-zero.
     *
     * @param min the minimum corner of the axis-aligned rectangle
     * @param max the maximum corner of the axis-aligned rectangle
     * @param a the x factor in the line equation
     * @param b the y factor in the line equation
     * @param c the constant in the line equation
     * @return <code>true</code> iff the axis-aligned rectangle intersects the line;
     *        <code>false</code> otherwise
     */
    public static boolean testAarLine(Float2 min, Float2 max, float a, float b, float c) {
        return testAarLine(min.x(), min.y(), max.x(), max.y(), a, b, c);
    }

    /**
     * Test whether the axis-aligned rectangle given as its minimum and maximum corner intersects
     * the line that goes through the two points <code>(x0, y0)</code> and <code>(x1, y1)</code>.
     * <p>
     * The line is the infinite line through both points, not the line segment between them. The two
     * points must be distinct: for two equal points the implied line equation degenerates to <i>0 =
     * 0</i> and every rectangle is then reported as intersecting.
     * <p>
     * Reference: <a
     * href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a>
     * ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component;
     * {@code (x0, y0)} and {@code (x1, y1)} must differ.
     *
     * @param minX the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY the y coordinate of the maximum corner of the axis-aligned rectangle
     * @param x0 the x coordinate of the first point on the line
     * @param y0 the y coordinate of the first point on the line
     * @param x1 the x coordinate of the second point on the line
     * @param y1 the y coordinate of the second point on the line
     * @return <code>true</code> iff the axis-aligned rectangle intersects the line;
     *        <code>false</code> otherwise
     */
    public static boolean testAarLine(float minX, float minY, float maxX, float maxY, float x0, float y0, float x1, float y1) {
        float _t0 = x1 - x0;
        float _t1 = y0 - y1;
        float _t4 = Math.fma(-x0, _t1, -(y0 * _t0));
        if (!(Math.fma(_t0, _t0 > 0.0f ? minY : maxY, Math.fma(_t1, _t1 > 0.0f ? minX : maxX, _t4)) <= 0.0f)) return false;
        return Math.fma(_t0, _t0 > 0.0f ? maxY : minY, Math.fma(_t1, _t1 > 0.0f ? maxX : minX, _t4)) >= 0.0f;
    }

    /**
     * Test whether the axis-aligned rectangle given as its minimum and maximum corner intersects
     * the line that goes through the two points <code>(x0, y0)</code> and <code>(x1, y1)</code>.
     * <p>
     * The line is the infinite line through both points, not the line segment between them. The two
     * points must be distinct: for two equal points the implied line equation degenerates to <i>0 =
     * 0</i> and every rectangle is then reported as intersecting.
     * <p>
     * Reference: <a
     * href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a>
     * ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code (x0, y0)} and
     * {@code (x1, y1)} must differ.
     *
     * @param min the minimum corner of the axis-aligned rectangle
     * @param max the maximum corner of the axis-aligned rectangle
     * @param x0 the x coordinate of the first point on the line
     * @param y0 the y coordinate of the first point on the line
     * @param x1 the x coordinate of the second point on the line
     * @param y1 the y coordinate of the second point on the line
     * @return <code>true</code> iff the axis-aligned rectangle intersects the line;
     *        <code>false</code> otherwise
     */
    public static boolean testAarLine(Float2 min, Float2 max, float x0, float y0, float x1, float y1) {
        return testAarLine(min.x(), min.y(), max.x(), max.y(), x0, y0, x1, y1);
    }

    /**
     * Test whether two circles, given by their centers and their radii, intersect.
     * <p>
     * The radii are passed unsquared: the test compares the squared distance between the centers
     * against the square of the sum of the two radii. It is inclusive, so circles that touch in a
     * single point count as intersecting, and an intersection is also reported when one circle
     * contains the other.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/275514/two-circles-overlap">http://math.stackexchange.com/</a>
     * <p>
     * Valid input: {@code rA} must not be negative; {@code rB} must not be negative.
     *
     * @param aX the x coordinate of the first circle's center
     * @param aY the y coordinate of the first circle's center
     * @param rA the radius of the first circle
     * @param bX the x coordinate of the second circle's center
     * @param bY the y coordinate of the second circle's center
     * @param rB the radius of the second circle
     * @return <code>true</code> iff both circles intersect
     */
    public static boolean testCircleCircle(float aX, float aY, float rA, float bX, float bY, float rB) {
        float _t0 = aX - bX;
        float _t1 = aY - bY;
        float _t2 = rA + rB;
        return Math.fma(_t0, _t0, _t1 * _t1) <= _t2 * _t2;
    }

    /**
     * Test whether two circles, given by their centers and their squared radii, intersect.
     * <p>
     * Unlike the form taking the center coordinates, this form takes the <em>squared</em> radii, as
     * JOML 1 did. The test is inclusive, so circles that touch in a single point count as
     * intersecting, and an intersection is also reported when one circle contains the other.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/275514/two-circles-overlap">http://math.stackexchange.com/</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     *
     * @param centerA the first circle's center
     * @param radiusSquaredA the square of the first circle's radius
     * @param centerB the second circle's center
     * @param radiusSquaredB the square of the second circle's radius
     * @return <code>true</code> iff both circles intersect
     */
    public static boolean testCircleCircle(Float2 centerA, float radiusSquaredA, Float2 centerB, float radiusSquaredB) {
        return testCircleCircle(centerA.x(), centerA.y(), Math.sqrt(radiusSquaredA), centerB.x(), centerB.y(), Math.sqrt(radiusSquaredB));
    }

    /** Private tail of {@code testCircleTriangle}; reached only through it. */
    private static boolean testCircleTriangle_s620d0a93_tail(float _t26, float _t27, float _t31, float radiusSquared, float _t41, float _t24, float _t25, float _t30, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t32) {
        float _t44 = _t26 >= 0.0f ? _t26 <= _t27 ? _t31 * _t31 <= radiusSquared * _t27 ? 1.0f : _t41 : _t41 : _t41;
        float _t47 = _t24 >= 0.0f ? _t24 <= _t25 ? _t30 * _t30 <= radiusSquared * _t25 ? 1.0f : _t44 : _t44 : _t44;
        return (Math.fma(_t0, _t0, Math.fma(_t1, _t1, -radiusSquared)) <= 0.0f ? 1.0f : Math.fma(_t2, _t2, Math.fma(_t3, _t3, -radiusSquared)) <= 0.0f ? 1.0f : Math.fma(_t4, _t4, Math.fma(_t5, _t5, -radiusSquared)) <= 0.0f ? 1.0f : _t30 >= 0.0f ? _t31 >= 0.0f ? _t32 >= 0.0f ? 1.0f : _t47 : _t47 : _t47) != 0;
    }

    /**
     * Test whether the circle with the given center and the given square radius intersects the
     * triangle with the three given vertices.
     * <p>
     * The vertices of the triangle must be specified in counter-clockwise order - the stage that
     * detects a circle center lying inside the triangle relies on that winding. The radius is
     * passed already squared and every stage compares squared quantities, so no square root is
     * taken. A circle that only touches a vertex or an edge counts as intersecting.
     * <p>
     * Reference: <a href="http://www.phatcode.net/articles.php?id=459">http://www.phatcode.net/</a>
     * <p>
     * Valid input: {@code radiusSquared} must not be negative; {@code (v0X, v0Y)},
     * {@code (v1X, v1Y)} and {@code (v2X, v2Y)} must be in counter-clockwise order.
     *
     * @param centerX the x coordinate of the circle's center
     * @param centerY the y coordinate of the circle's center
     * @param radiusSquared the square radius of the circle
     * @param v0X the x coordinate of the first vertex of the triangle
     * @param v0Y the y coordinate of the first vertex of the triangle
     * @param v1X the x coordinate of the second vertex of the triangle
     * @param v1Y the y coordinate of the second vertex of the triangle
     * @param v2X the x coordinate of the third vertex of the triangle
     * @param v2Y the y coordinate of the third vertex of the triangle
     * @return <code>true</code> iff the circle intersects the triangle; <code>false</code>
     *        otherwise
     */
    public static boolean testCircleTriangle(float centerX, float centerY, float radiusSquared, float v0X, float v0Y, float v1X, float v1Y, float v2X, float v2Y) {
        float _t0 = centerX - v0X;
        float _t1 = centerY - v0Y;
        float _t2 = centerX - v1X;
        float _t3 = centerY - v1Y;
        float _t4 = centerX - v2X;
        float _t5 = centerY - v2Y;
        float _t6 = v1X - v0X;
        float _t7 = v1Y - v0Y;
        float _t8 = v2X - v1X;
        float _t9 = v2Y - v1Y;
        float _t10 = v0X - v2X;
        float _t11 = v0Y - v2Y;
        float _t28 = Math.fma(_t4, _t10, _t5 * _t11);
        float _t29 = Math.fma(_t10, _t10, _t11 * _t11);
        float _t32 = Math.fma(_t5, _t10, -(_t4 * _t11));
        return testCircleTriangle_s620d0a93_tail(Math.fma(_t2, _t8, _t3 * _t9), Math.fma(_t8, _t8, _t9 * _t9), Math.fma(_t3, _t8, -(_t2 * _t9)), radiusSquared, _t28 >= 0.0f ? _t28 <= _t29 ? _t32 * _t32 <= radiusSquared * _t29 ? 1.0f : 0.0f : 0.0f : 0.0f, Math.fma(_t0, _t6, _t1 * _t7), Math.fma(_t6, _t6, _t7 * _t7), Math.fma(_t1, _t6, -(_t0 * _t7)), _t0, _t1, _t2, _t3, _t4, _t5, _t32);
    }

    /**
     * Test whether the circle with the given center and the given square radius intersects the
     * triangle with the three given vertices.
     * <p>
     * The vertices of the triangle must be specified in counter-clockwise order - the stage that
     * detects a circle center lying inside the triangle relies on that winding. The radius is
     * passed already squared and every stage compares squared quantities, so no square root is
     * taken. A circle that only touches a vertex or an edge counts as intersecting.
     * <p>
     * Reference: <a href="http://www.phatcode.net/articles.php?id=459">http://www.phatcode.net/</a>
     * <p>
     * Valid input: {@code radiusSquared} must not be negative; {@code v0}, {@code v1} and
     * {@code v2} must be in counter-clockwise order.
     *
     * @param center the circle's center
     * @param radiusSquared the square radius of the circle
     * @param v0 the first vertex of the triangle
     * @param v1 the second vertex of the triangle
     * @param v2 the third vertex of the triangle
     * @return <code>true</code> iff the circle intersects the triangle; <code>false</code>
     *        otherwise
     */
    public static boolean testCircleTriangle(Float2 center, float radiusSquared, Float2 v0, Float2 v1, Float2 v2) {
        return testCircleTriangle(center.x(), center.y(), radiusSquared, v0.x(), v0.y(), v1.x(), v1.y(), v2.x(), v2.y());
    }

    /**
     * Test whether the line with the general line equation <i>a*x + b*y + c = 0</i> intersects the
     * circle with the given center and the given radius.
     * <p>
     * The test compares the signed distance from the circle's center to the line against
     * <code>-radius</code> and <code>+radius</code>, both inclusive, so a line that only touches
     * the circle counts as intersecting. The line coefficients need not be normalized, since that
     * distance is divided by the length of <code>(a, b)</code>, but <code>a</code> and
     * <code>b</code> must not both be zero. The radius is expected to be non-negative and its
     * magnitude is never taken: with a negative <code>radius</code> the two comparisons cannot both
     * hold, so no intersection is ever reported.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a the x factor in the line equation
     * @param b the y factor in the line equation
     * @param c the constant in the line equation
     * @param centerX the x coordinate of the circle's center
     * @param centerY the y coordinate of the circle's center
     * @param radius the radius of the circle
     * @return <code>true</code> iff the line intersects or touches the circle
     */
    public static boolean testLineCircle(float a, float b, float c, float centerX, float centerY, float radius) {
        float _t5 = Math.fma(a, centerX, Math.fma(b, centerY, c)) * (1.0f / (float) Math.sqrt(Math.fma(a, a, b * b)));
        if (!(-radius <= _t5)) return false;
        return _t5 <= radius;
    }

    /**
     * Test whether the line with the general line equation <i>a*x + b*y + c = 0</i> intersects the
     * circle with the given center and the given radius.
     * <p>
     * The test compares the signed distance from the circle's center to the line against
     * <code>-radius</code> and <code>+radius</code>, both inclusive, so a line that only touches
     * the circle counts as intersecting. The line coefficients need not be normalized, since that
     * distance is divided by the length of <code>(a, b)</code>, but <code>a</code> and
     * <code>b</code> must not both be zero. The radius is expected to be non-negative and its
     * magnitude is never taken: with a negative <code>radius</code> the two comparisons cannot both
     * hold, so no intersection is ever reported.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a the x factor in the line equation
     * @param b the y factor in the line equation
     * @param c the constant in the line equation
     * @param center the circle's center
     * @param radius the radius of the circle
     * @return <code>true</code> iff the line intersects or touches the circle
     */
    public static boolean testLineCircle(float a, float b, float c, Float2 center, float radius) {
        return testLineCircle(a, b, c, center.x(), center.y(), radius);
    }

    /**
     * Test whether the line segment with the given two end points intersects the given sphere with
     * the given center and square radius.
     * <p>
     * The segment's parameter is clamped to <code>[0, 1]</code>, so the square distance from the
     * sphere's center to the closest point on the segment - not on the infinite line through it -
     * is what gets compared against {@code radiusSquared}. The comparison is inclusive, so a
     * segment lying entirely inside the sphere counts as intersecting, as does one that only
     * touches the sphere's surface. The radius is supplied already squared. A segment whose two end
     * points coincide is the point they share.
     * <p>
     * Reference: <a
     * href="http://paulbourke.net/geometry/circlesphere/index.html#linesphere">http://paulbourke.net/</a>
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     *
     * @param p0X the x coordinate of the line segment's first end point
     * @param p0Y the y coordinate of the line segment's first end point
     * @param p0Z the z coordinate of the line segment's first end point
     * @param p1X the x coordinate of the line segment's second end point
     * @param p1Y the y coordinate of the line segment's second end point
     * @param p1Z the z coordinate of the line segment's second end point
     * @param centerX the x coordinate of the sphere's center
     * @param centerY the y coordinate of the sphere's center
     * @param centerZ the z coordinate of the sphere's center
     * @param radiusSquared the square of the sphere's radius
     * @return <code>true</code> iff the line segment intersects the sphere
     */
    public static boolean testLineSegmentSphere(float p0X, float p0Y, float p0Z, float p1X, float p1Y, float p1Z, float centerX, float centerY, float centerZ, float radiusSquared) {
        float _t0 = p1Z - p0Z;
        float _t3 = p1X - p0X;
        float _t5 = p1Y - p0Y;
        float _t17 = java.lang.Math.clamp(Math.fma(centerZ - p0Z, _t0, Math.fma(centerX - p0X, _t3, (centerY - p0Y) * _t5)) / Math.max(Math.fma(_t0, _t0, Math.fma(_t3, _t3, _t5 * _t5)), 1.4E-45f), 0.0f, 1.0f);
        float _t18 = Math.fma(_t0, _t17, p0Z - centerZ);
        float _t19 = Math.fma(_t3, _t17, p0X - centerX);
        float _t20 = Math.fma(_t5, _t17, p0Y - centerY);
        return Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)) <= radiusSquared;
    }

    /**
     * Test whether the line segment with the given two end points intersects the given sphere with
     * the given center and square radius.
     * <p>
     * The segment's parameter is clamped to <code>[0, 1]</code>, so the square distance from the
     * sphere's center to the closest point on the segment - not on the infinite line through it -
     * is what gets compared against {@code radiusSquared}. The comparison is inclusive, so a
     * segment lying entirely inside the sphere counts as intersecting, as does one that only
     * touches the sphere's surface. The radius is supplied already squared. A segment whose two end
     * points coincide is the point they share.
     * <p>
     * Reference: <a
     * href="http://paulbourke.net/geometry/circlesphere/index.html#linesphere">http://paulbourke.net/</a>
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     *
     * @param p0 the line segment's first end point
     * @param p1 the line segment's second end point
     * @param center the sphere's center
     * @param radiusSquared the square of the sphere's radius
     * @return <code>true</code> iff the line segment intersects the sphere
     */
    public static boolean testLineSegmentSphere(Float3 p0, Float3 p1, Float3 center, float radiusSquared) {
        return testLineSegmentSphere(p0.x(), p0.y(), p0.z(), p1.x(), p1.y(), p1.z(), center.x(), center.y(), center.z(), radiusSquared);
    }

    /** Private tail of {@code testLineSegmentTriangle}; reached only through it. */
    private static boolean testLineSegmentTriangle_s77083b57_tail(float _t0, float _t24, float _t5, float _t25, float _t8, float _t26, float _t9, float _t10, float _t11, float _t7, float _t27, float _t1, float _t28, float _t3, float _t29, float epsilon, float _t6, float _t4, float _t2) {
        float _t38 = Math.fma(_t0, _t24, Math.fma(_t5, _t25, _t8 * _t26));
        float _t38_inv = 1.0f / _t38;
        float _t39 = Math.fma(_t9, _t24, Math.fma(_t10, _t25, _t11 * _t26));
        float _t40 = Math.fma(_t7, _t27, Math.fma(_t1, _t28, _t3 * _t29));
        float _t43 = _t39 * _t38_inv;
        float _t52 = Math.abs(_t38) >= epsilon ? _t43 >= 0.0f ? _t43 <= 1.0f ? _t40 * _t38_inv >= 0.0f ? (_t39 + _t40) * _t38_inv <= 1.0f ? Math.fma(_t6, _t27, Math.fma(_t4, _t28, _t2 * _t29)) * _t38_inv : -1.0f : -1.0f : -1.0f : -1.0f : -1.0f;
        if (!(_t52 >= 0.0f)) return false;
        return _t52 <= 1.0f;
    }

    /**
     * Test whether the line segment with the given two end points intersects the triangle
     * consisting of the three given vertices, regardless of the winding order of the triangle or
     * the direction of the line segment between its two end points.
     * <p>
     * The bounds are inclusive: an intersection exactly at an end point of the segment, or on an
     * edge or at a vertex of the triangle, counts as a hit.
     * <p>
     * The intersection is rejected when the absolute value of the Moller-Trumbore determinant falls
     * below {@code epsilon}, which is the case for a segment that is (nearly) parallel to the
     * triangle's plane; a segment lying in that plane is therefore never reported. That determinant
     * scales with the length of the segment and with the size of the triangle, so {@code epsilon}
     * is not a scale-free threshold.
     * <p>
     * Reference: <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage
     * Ray/Triangle Intersection</a>
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param p0X the x coordinate of the line segment's first end point
     * @param p0Y the y coordinate of the line segment's first end point
     * @param p0Z the z coordinate of the line segment's first end point
     * @param p1X the x coordinate of the line segment's second end point
     * @param p1Y the y coordinate of the line segment's second end point
     * @param p1Z the z coordinate of the line segment's second end point
     * @param v0X the x coordinate of the first vertex of the triangle
     * @param v0Y the y coordinate of the first vertex of the triangle
     * @param v0Z the z coordinate of the first vertex of the triangle
     * @param v1X the x coordinate of the second vertex of the triangle
     * @param v1Y the y coordinate of the second vertex of the triangle
     * @param v1Z the z coordinate of the second vertex of the triangle
     * @param v2X the x coordinate of the third vertex of the triangle
     * @param v2Y the y coordinate of the third vertex of the triangle
     * @param v2Z the z coordinate of the third vertex of the triangle
     * @param epsilon a small epsilon when testing line segments that are almost parallel to the
     *        triangle
     * @return <code>true</code> iff the line segment intersects the triangle
     */
    public static boolean testLineSegmentTriangle(float p0X, float p0Y, float p0Z, float p1X, float p1Y, float p1Z, float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z, float epsilon) {
        float _t0 = v1Z - v0Z;
        float _t1 = p1X - p0X;
        float _t2 = v2Y - v0Y;
        float _t3 = p1Y - p0Y;
        float _t4 = v2X - v0X;
        float _t5 = v1X - v0X;
        float _t6 = v2Z - v0Z;
        float _t7 = p1Z - p0Z;
        float _t8 = v1Y - v0Y;
        float _t9 = p0Z - v0Z;
        float _t10 = p0X - v0X;
        float _t11 = p0Y - v0Y;
        return testLineSegmentTriangle_s77083b57_tail(_t0, Math.fma(_t1, _t2, -(_t3 * _t4)), _t5, Math.fma(_t3, _t6, -(_t7 * _t2)), _t8, Math.fma(_t7, _t4, -(_t1 * _t6)), _t9, _t10, _t11, _t7, Math.fma(_t10, _t8, -(_t11 * _t5)), _t1, Math.fma(_t11, _t0, -(_t9 * _t8)), _t3, Math.fma(_t9, _t5, -(_t10 * _t0)), epsilon, _t6, _t4, _t2);
    }

    /**
     * Test whether the line segment with the given two end points intersects the triangle
     * consisting of the three given vertices, regardless of the winding order of the triangle or
     * the direction of the line segment between its two end points.
     * <p>
     * The bounds are inclusive: an intersection exactly at an end point of the segment, or on an
     * edge or at a vertex of the triangle, counts as a hit.
     * <p>
     * The intersection is rejected when the absolute value of the Moller-Trumbore determinant falls
     * below {@code epsilon}, which is the case for a segment that is (nearly) parallel to the
     * triangle's plane; a segment lying in that plane is therefore never reported. That determinant
     * scales with the length of the segment and with the size of the triangle, so {@code epsilon}
     * is not a scale-free threshold.
     * <p>
     * Reference: <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage
     * Ray/Triangle Intersection</a>
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param p0 the line segment's first end point
     * @param p1 the line segment's second end point
     * @param v0 the first vertex of the triangle
     * @param v1 the second vertex of the triangle
     * @param v2 the third vertex of the triangle
     * @param epsilon a small epsilon when testing line segments that are almost parallel to the
     *        triangle
     * @return <code>true</code> iff the line segment intersects the triangle
     */
    public static boolean testLineSegmentTriangle(Float3 p0, Float3 p1, Float3 v0, Float3 v1, Float3 v2, float epsilon) {
        return testLineSegmentTriangle(p0.x(), p0.y(), p0.z(), p1.x(), p1.y(), p1.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /** Private tail of {@code testMovingAabbAabb}; reached only through it. */
    private static boolean testMovingAabbAabb_s2a985578_tail(float _t17, float _t15, float _t16, float _t18, float _t20, float _t19) {
        float _t23 = _t17 > _t15 ? _t17 : _t15 != _t15 ? _t17 : _t15;
        float _t24 = _t16 < _t18 ? _t16 : _t18 != _t18 ? _t16 : _t18;
        float _t27 = _t20 > _t23 ? _t20 : _t23 != _t23 ? _t20 : _t23;
        float _t28 = _t19 < _t24 ? _t19 : _t24 != _t24 ? _t19 : _t24;
        if (_t15 > _t16) return false;
        if (_t17 > _t18) return false;
        if (_t15 > _t19) return false;
        if (_t20 > _t18) return false;
        if (!(_t27 < _t28)) return false;
        if (!(_t28 >= 0.0f)) return false;
        return _t27 <= 1.0f;
    }

    /**
     * Test whether the moving axis-aligned box with corners <code>minA</code> and
     * <code>maxA</code>, translated by <code>velocity</code> over the step <i>t</i> in
     * <code>[0, 1]</code>, intersects the static axis-aligned box with corners <code>minB</code>
     * and <code>maxB</code>.
     * <p>
     * This is the slab test of the relative-motion ray against the Minkowski-expanded box and
     * follows the conventions of {@code testRayAabb}: the entry/exit comparison is strict, so a
     * sweep that only grazes - one whose entry and exit instant coincide - is not counted, while
     * contact lasting over a range of <i>t</i> is. An axis along which <code>velocity</code> is
     * zero and the boxes exactly abut yields the NaN that the slab clamping is written to tolerate,
     * and that axis is then dropped from the test. Boxes that already overlap at <i>t</i> = 0 are
     * reported as intersecting.
     * <p>
     * Valid input: {@code (minAX, minAY, minAZ)} must not exceed {@code (maxAX, maxAY, maxAZ)} in
     * any component; {@code (minBX, minBY, minBZ)} must not exceed {@code (maxBX, maxBY, maxBZ)} in
     * any component.
     *
     * @param minAX the x coordinate of the minimum corner of the moving axis-aligned box
     * @param minAY the y coordinate of the minimum corner of the moving axis-aligned box
     * @param minAZ the z coordinate of the minimum corner of the moving axis-aligned box
     * @param maxAX the x coordinate of the maximum corner of the moving axis-aligned box
     * @param maxAY the y coordinate of the maximum corner of the moving axis-aligned box
     * @param maxAZ the z coordinate of the maximum corner of the moving axis-aligned box
     * @param velocityX the x coordinate of the translation applied to the moving box over the step
     * @param velocityY the y coordinate of the translation applied to the moving box over the step
     * @param velocityZ the z coordinate of the translation applied to the moving box over the step
     * @param minBX the x coordinate of the minimum corner of the static axis-aligned box
     * @param minBY the y coordinate of the minimum corner of the static axis-aligned box
     * @param minBZ the z coordinate of the minimum corner of the static axis-aligned box
     * @param maxBX the x coordinate of the maximum corner of the static axis-aligned box
     * @param maxBY the y coordinate of the maximum corner of the static axis-aligned box
     * @param maxBZ the z coordinate of the maximum corner of the static axis-aligned box
     * @return <code>true</code> iff the moving box meets the static box at some <i>t</i> in
     *        <code>[0, 1]</code>
     */
    public static boolean testMovingAabbAabb(float minAX, float minAY, float minAZ, float maxAX, float maxAY, float maxAZ, float velocityX, float velocityY, float velocityZ, float minBX, float minBY, float minBZ, float maxBX, float maxBY, float maxBZ) {
        float _rcp0 = 1.0f / velocityX;
        float _rcp1 = 1.0f / velocityY;
        float _rcp2 = 1.0f / velocityZ;
        float _t9 = (minBX - maxAX) * _rcp0;
        float _t10 = (maxBX - minAX) * _rcp0;
        float _t11 = (maxBY - minAY) * _rcp1;
        float _t12 = (minBY - maxAY) * _rcp1;
        float _t13 = (maxBZ - minAZ) * _rcp2;
        float _t14 = (minBZ - maxAZ) * _rcp2;
        float _t15, _t18;
        if (_rcp0 >= 0.0f) {
            _t15 = _t9;
            _t18 = _t10;
        } else {
            _t15 = _t10;
            _t18 = _t9;
        }
        float _t16, _t17;
        if (_rcp1 >= 0.0f) {
            _t16 = _t11;
            _t17 = _t12;
        } else {
            _t16 = _t12;
            _t17 = _t11;
        }
        float _t19, _t20;
        if (_rcp2 >= 0.0f) {
            _t19 = _t13;
            _t20 = _t14;
        } else {
            _t19 = _t14;
            _t20 = _t13;
        }
        return testMovingAabbAabb_s2a985578_tail(_t17, _t15, _t16, _t18, _t20, _t19);
    }

    /**
     * Test whether the moving axis-aligned box with corners <code>minA</code> and
     * <code>maxA</code>, translated by <code>velocity</code> over the step <i>t</i> in
     * <code>[0, 1]</code>, intersects the static axis-aligned box with corners <code>minB</code>
     * and <code>maxB</code>.
     * <p>
     * This is the slab test of the relative-motion ray against the Minkowski-expanded box and
     * follows the conventions of {@code testRayAabb}: the entry/exit comparison is strict, so a
     * sweep that only grazes - one whose entry and exit instant coincide - is not counted, while
     * contact lasting over a range of <i>t</i> is. An axis along which <code>velocity</code> is
     * zero and the boxes exactly abut yields the NaN that the slab clamping is written to tolerate,
     * and that axis is then dropped from the test. Boxes that already overlap at <i>t</i> = 0 are
     * reported as intersecting.
     * <p>
     * Valid input: {@code minA} must not exceed {@code maxA} in any component; {@code minB} must
     * not exceed {@code maxB} in any component.
     *
     * @param minA the minimum corner of the moving axis-aligned box
     * @param maxA the maximum corner of the moving axis-aligned box
     * @param velocity the translation applied to the moving box over the step
     * @param minB the minimum corner of the static axis-aligned box
     * @param maxB the maximum corner of the static axis-aligned box
     * @return <code>true</code> iff the moving box meets the static box at some <i>t</i> in
     *        <code>[0, 1]</code>
     */
    public static boolean testMovingAabbAabb(Float3 minA, Float3 maxA, Float3 velocity, Float3 minB, Float3 maxB) {
        return testMovingAabbAabb(minA.x(), minA.y(), minA.z(), maxA.x(), maxA.y(), maxA.z(), velocity.x(), velocity.y(), velocity.z(), minB.x(), minB.y(), minB.z(), maxB.x(), maxB.y(), maxB.z());
    }

    /**
     * Test whether the circle with the given center <code>a</code> and radius <code>aR</code>,
     * whose center travels the distance vector <code>ma</code>, intersects the static circle with
     * the given center <code>b</code> and radius <code>bR</code>.
     * <p>
     * The whole move is considered, that is the moving circle's center travels from <code>a</code>
     * to <code>a + ma</code>, and touching counts as an intersection at both ends of the test:
     * circles that already touch or overlap before the move report an intersection whatever the
     * direction of <code>ma</code>, and so does a contact reached exactly at the end of the move. A
     * zero-length <code>ma</code> therefore degenerates into the plain circle-circle overlap test.
     * <p>
     * Note that the case of two moving circles can always be reduced to this case by expressing the
     * moved distance of one of the circles relative to the other.
     * <p>
     * Reference: <a
     * href="https://www.gamasutra.com/view/feature/131424/pool_hall_lessons_fast_accurate_.php?page=2">https://www.gamasutra.com</a>
     * <p>
     * Valid input: {@code aR} must not be negative; {@code bR} must not be negative.
     *
     * @param aX the x coordinate of the moving circle's center
     * @param aY the y coordinate of the moving circle's center
     * @param maX the x coordinate of the distance vector the moving circle travels
     * @param maY the y coordinate of the distance vector the moving circle travels
     * @param aR the radius of the moving circle
     * @param bX the x coordinate of the static circle's center
     * @param bY the y coordinate of the static circle's center
     * @param bR the radius of the static circle
     * @return <code>true</code> iff the moving circle touches or overlaps the static circle at some
     *        point of its move
     */
    public static boolean testMovingCircleCircle(float aX, float aY, float maX, float maY, float aR, float bX, float bY, float bR) {
        float _t0 = aX - bX;
        float _t1 = aY - bY;
        float _t2 = aR + bR;
        float _t4 = bX - aX;
        float _t5 = bY - aY;
        float _t8 = (float) Math.sqrt(Math.fma(maX, maX, maY * maY));
        float _t9 = 1.0f / _t8;
        float _t10 = maX * _t9;
        float _t11 = maY * _t9;
        float _t16 = (float) Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1)) - _t2;
        float _t17 = Math.fma(_t5, _t10, -(_t4 * _t11));
        float _t18 = _t17 * _t17;
        float _t20 = Math.fma(_t2, _t2, -_t18);
        return (_t16 <= 0.0f) || (!(_t8 < _t16) && (!(Math.fma(_t4, _t10, _t5 * _t11) <= 0.0f) && (!(_t18 >= _t2 * _t2) && (!(_t20 < 0.0f) && (!(_t8 < Math.fma(_t4, _t10, Math.fma(_t5, _t11, -(float) Math.sqrt(_t20)))))))));
    }

    /**
     * Test whether the circle with the given center <code>a</code> and radius <code>aR</code>,
     * whose center travels the distance vector <code>ma</code>, intersects the static circle with
     * the given center <code>b</code> and radius <code>bR</code>.
     * <p>
     * The whole move is considered, that is the moving circle's center travels from <code>a</code>
     * to <code>a + ma</code>, and touching counts as an intersection at both ends of the test:
     * circles that already touch or overlap before the move report an intersection whatever the
     * direction of <code>ma</code>, and so does a contact reached exactly at the end of the move. A
     * zero-length <code>ma</code> therefore degenerates into the plain circle-circle overlap test.
     * <p>
     * Note that the case of two moving circles can always be reduced to this case by expressing the
     * moved distance of one of the circles relative to the other.
     * <p>
     * Reference: <a
     * href="https://www.gamasutra.com/view/feature/131424/pool_hall_lessons_fast_accurate_.php?page=2">https://www.gamasutra.com</a>
     * <p>
     * Valid input: {@code aR} must not be negative; {@code bR} must not be negative.
     *
     * @param a the moving circle's center
     * @param ma the distance vector the moving circle travels
     * @param aR the radius of the moving circle
     * @param b the static circle's center
     * @param bR the radius of the static circle
     * @return <code>true</code> iff the moving circle touches or overlaps the static circle at some
     *        point of its move
     */
    public static boolean testMovingCircleCircle(Float2 a, Float2 ma, float aR, Float2 b, float bR) {
        return testMovingCircleCircle(a.x(), a.y(), ma.x(), ma.y(), aR, b.x(), b.y(), bR);
    }

    /**
     * Test whether two oriented boxes, given via their center position, orientation and half-size,
     * intersect.
     * <p>
     * The orientation of a box is given as three unit vectors spanning the local orthonormal basis
     * of the box. They must be normalized and mutually orthogonal: the kernel projects onto them
     * with plain dot products and never normalizes. The size is given as the half-size along each
     * of those unit vectors.
     * <p>
     * The test is the Separating Axis Theorem over all fifteen candidate axes - the three face
     * normals of each box plus the nine cross products of their edge directions - and it is
     * inclusive, so boxes that merely touch count as intersecting. A small epsilon is added to the
     * absolute terms of the relative rotation so that a pair of (near) parallel edges cannot
     * degenerate the cross-product axes. That epsilon only widens the projected radii, which makes
     * the test conservative: boxes separated by a hair can still be reported as intersecting, but a
     * real overlap is never missed.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 4.4.1 "OBB-OBB Intersection"
     * <p>
     * Valid input: {@code (b0uXX, b0uXY, b0uXZ)}, {@code (b0uYX, b0uYY, b0uYZ)} and
     * {@code (b0uZX, b0uZY, b0uZZ)} must be orthonormal; each component of
     * {@code (b0hsX, b0hsY, b0hsZ)} must not be negative; {@code (b1uXX, b1uXY, b1uXZ)},
     * {@code (b1uYX, b1uYY, b1uYZ)} and {@code (b1uZX, b1uZY, b1uZZ)} must be orthonormal; each
     * component of {@code (b1hsX, b1hsY, b1hsZ)} must not be negative.
     *
     * @param b0cX the x coordinate of the center of the first box
     * @param b0cY the y coordinate of the center of the first box
     * @param b0cZ the z coordinate of the center of the first box
     * @param b0uXX the x coordinate of the local X unit vector of the first box
     * @param b0uXY the y coordinate of the local X unit vector of the first box
     * @param b0uXZ the z coordinate of the local X unit vector of the first box
     * @param b0uYX the x coordinate of the local Y unit vector of the first box
     * @param b0uYY the y coordinate of the local Y unit vector of the first box
     * @param b0uYZ the z coordinate of the local Y unit vector of the first box
     * @param b0uZX the x coordinate of the local Z unit vector of the first box
     * @param b0uZY the y coordinate of the local Z unit vector of the first box
     * @param b0uZZ the z coordinate of the local Z unit vector of the first box
     * @param b0hsX the x coordinate of the half-size of the first box
     * @param b0hsY the y coordinate of the half-size of the first box
     * @param b0hsZ the z coordinate of the half-size of the first box
     * @param b1cX the x coordinate of the center of the second box
     * @param b1cY the y coordinate of the center of the second box
     * @param b1cZ the z coordinate of the center of the second box
     * @param b1uXX the x coordinate of the local X unit vector of the second box
     * @param b1uXY the y coordinate of the local X unit vector of the second box
     * @param b1uXZ the z coordinate of the local X unit vector of the second box
     * @param b1uYX the x coordinate of the local Y unit vector of the second box
     * @param b1uYY the y coordinate of the local Y unit vector of the second box
     * @param b1uYZ the z coordinate of the local Y unit vector of the second box
     * @param b1uZX the x coordinate of the local Z unit vector of the second box
     * @param b1uZY the y coordinate of the local Z unit vector of the second box
     * @param b1uZZ the z coordinate of the local Z unit vector of the second box
     * @param b1hsX the x coordinate of the half-size of the second box
     * @param b1hsY the y coordinate of the half-size of the second box
     * @param b1hsZ the z coordinate of the half-size of the second box
     * @return <code>true</code> iff both boxes intersect
     */
    public static boolean testObOb(float b0cX, float b0cY, float b0cZ, float b0uXX, float b0uXY, float b0uXZ, float b0uYX, float b0uYY, float b0uYZ, float b0uZX, float b0uZY, float b0uZZ, float b0hsX, float b0hsY, float b0hsZ, float b1cX, float b1cY, float b1cZ, float b1uXX, float b1uXY, float b1uXZ, float b1uYX, float b1uYY, float b1uYZ, float b1uZX, float b1uZY, float b1uZZ, float b1hsX, float b1hsY, float b1hsZ) {
        float _t0 = b1cZ - b0cZ;
        float _t1 = b1cX - b0cX;
        float _t2 = b1cY - b0cY;
        float _t24 = Math.fma(b0uXZ, b1uXZ, Math.fma(b0uXX, b1uXX, b0uXY * b1uXY));
        float _t25 = Math.fma(b0uXZ, b1uYZ, Math.fma(b0uXX, b1uYX, b0uXY * b1uYY));
        float _t26 = Math.fma(b0uXZ, b1uZZ, Math.fma(b0uXX, b1uZX, b0uXY * b1uZY));
        float _t27 = Math.fma(b0uYZ, b1uXZ, Math.fma(b0uYX, b1uXX, b0uYY * b1uXY));
        float _t28 = Math.fma(b0uYZ, b1uYZ, Math.fma(b0uYX, b1uYX, b0uYY * b1uYY));
        float _t29 = Math.fma(b0uYZ, b1uZZ, Math.fma(b0uYX, b1uZX, b0uYY * b1uZY));
        float _t30 = Math.fma(b0uZZ, b1uXZ, Math.fma(b0uZX, b1uXX, b0uZY * b1uXY));
        float _t31 = Math.fma(b0uZZ, b1uYZ, Math.fma(b0uZX, b1uYX, b0uZY * b1uYY));
        float _t32 = Math.fma(b0uZZ, b1uZZ, Math.fma(b0uZX, b1uZX, b0uZY * b1uZY));
        float _t45 = Math.abs(_t24) + 1.0E-5f;
        float _t46 = Math.abs(_t25) + 1.0E-5f;
        float _t47 = Math.abs(_t26) + 1.0E-5f;
        float _t48 = Math.abs(_t27) + 1.0E-5f;
        float _t49 = Math.abs(_t28) + 1.0E-5f;
        float _t50 = Math.abs(_t29) + 1.0E-5f;
        float _t51 = Math.abs(_t30) + 1.0E-5f;
        float _t52 = Math.abs(_t31) + 1.0E-5f;
        float _t53 = Math.abs(_t32) + 1.0E-5f;
        float _t54 = Math.fma(b0uXZ, _t0, Math.fma(b0uXX, _t1, b0uXY * _t2));
        float _t55 = Math.fma(b0uYZ, _t0, Math.fma(b0uYX, _t1, b0uYY * _t2));
        float _t56 = Math.fma(b0uZZ, _t0, Math.fma(b0uZX, _t1, b0uZY * _t2));
        if (!(Math.abs(_t54) <= Math.fma(b1hsX, _t45, Math.fma(b1hsY, _t46, Math.fma(b1hsZ, _t47, b0hsX))))) return false;
        if (!(Math.abs(_t55) <= Math.fma(b1hsX, _t48, Math.fma(b1hsY, _t49, Math.fma(b1hsZ, _t50, b0hsY))))) return false;
        if (!(Math.abs(_t56) <= Math.fma(b1hsX, _t51, Math.fma(b1hsY, _t52, Math.fma(b1hsZ, _t53, b0hsZ))))) return false;
        if (!(Math.abs(Math.fma(_t30, _t56, Math.fma(_t24, _t54, _t27 * _t55))) <= Math.fma(b0hsX, _t45, Math.fma(b0hsY, _t48, Math.fma(b0hsZ, _t51, b1hsX))))) return false;
        if (!(Math.abs(Math.fma(_t31, _t56, Math.fma(_t25, _t54, _t28 * _t55))) <= Math.fma(b0hsX, _t46, Math.fma(b0hsY, _t49, Math.fma(b0hsZ, _t52, b1hsY))))) return false;
        if (!(Math.abs(Math.fma(_t32, _t56, Math.fma(_t26, _t54, _t29 * _t55))) <= Math.fma(b0hsX, _t47, Math.fma(b0hsY, _t50, Math.fma(b0hsZ, _t53, b1hsZ))))) return false;
        if (!(Math.abs(Math.fma(_t27, _t56, -(_t55 * _t30))) <= Math.fma(b0hsY, _t51, b0hsZ * _t48) + Math.fma(b1hsY, _t47, b1hsZ * _t46))) return false;
        if (!(Math.abs(Math.fma(_t28, _t56, -(_t55 * _t31))) <= Math.fma(b0hsY, _t52, b0hsZ * _t49) + Math.fma(b1hsX, _t47, b1hsZ * _t45))) return false;
        if (!(Math.abs(Math.fma(_t29, _t56, -(_t55 * _t32))) <= Math.fma(b0hsY, _t53, b0hsZ * _t50) + Math.fma(b1hsX, _t46, b1hsY * _t45))) return false;
        if (!(Math.abs(Math.fma(_t54, _t30, -(_t24 * _t56))) <= Math.fma(b0hsX, _t51, b0hsZ * _t45) + Math.fma(b1hsY, _t50, b1hsZ * _t49))) return false;
        if (!(Math.abs(Math.fma(_t54, _t31, -(_t25 * _t56))) <= Math.fma(b0hsX, _t52, b0hsZ * _t46) + Math.fma(b1hsX, _t50, b1hsZ * _t48))) return false;
        if (!(Math.abs(Math.fma(_t54, _t32, -(_t26 * _t56))) <= Math.fma(b0hsX, _t53, b0hsZ * _t47) + Math.fma(b1hsX, _t49, b1hsY * _t48))) return false;
        if (!(Math.abs(Math.fma(_t24, _t55, -(_t54 * _t27))) <= Math.fma(b0hsX, _t48, b0hsY * _t45) + Math.fma(b1hsY, _t53, b1hsZ * _t52))) return false;
        if (!(Math.abs(Math.fma(_t25, _t55, -(_t54 * _t28))) <= Math.fma(b0hsX, _t49, b0hsY * _t46) + Math.fma(b1hsX, _t53, b1hsZ * _t51))) return false;
        return Math.abs(Math.fma(_t26, _t55, -(_t54 * _t29))) <= Math.fma(b0hsX, _t50, b0hsY * _t47) + Math.fma(b1hsX, _t52, b1hsY * _t51);
    }

    /**
     * Test whether two oriented boxes, given via their center position, orientation and half-size,
     * intersect.
     * <p>
     * The orientation of a box is given as three unit vectors spanning the local orthonormal basis
     * of the box. They must be normalized and mutually orthogonal: the kernel projects onto them
     * with plain dot products and never normalizes. The size is given as the half-size along each
     * of those unit vectors.
     * <p>
     * The test is the Separating Axis Theorem over all fifteen candidate axes - the three face
     * normals of each box plus the nine cross products of their edge directions - and it is
     * inclusive, so boxes that merely touch count as intersecting. A small epsilon is added to the
     * absolute terms of the relative rotation so that a pair of (near) parallel edges cannot
     * degenerate the cross-product axes. That epsilon only widens the projected radii, which makes
     * the test conservative: boxes separated by a hair can still be reported as intersecting, but a
     * real overlap is never missed.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 4.4.1 "OBB-OBB Intersection"
     * <p>
     * Valid input: {@code b0uX}, {@code b0uY} and {@code b0uZ} must be orthonormal; each component
     * of {@code b0hs} must not be negative; {@code b1uX}, {@code b1uY} and {@code b1uZ} must be
     * orthonormal; each component of {@code b1hs} must not be negative.
     *
     * @param b0c the center of the first box
     * @param b0uX the local X unit vector of the first box
     * @param b0uY the local Y unit vector of the first box
     * @param b0uZ the local Z unit vector of the first box
     * @param b0hs the half-size of the first box
     * @param b1c the center of the second box
     * @param b1uX the local X unit vector of the second box
     * @param b1uY the local Y unit vector of the second box
     * @param b1uZ the local Z unit vector of the second box
     * @param b1hs the half-size of the second box
     * @return <code>true</code> iff both boxes intersect
     */
    public static boolean testObOb(Float3 b0c, Float3 b0uX, Float3 b0uY, Float3 b0uZ, Float3 b0hs, Float3 b1c, Float3 b1uX, Float3 b1uY, Float3 b1uZ, Float3 b1hs) {
        return testObOb(b0c.x(), b0c.y(), b0c.z(), b0uX.x(), b0uX.y(), b0uX.z(), b0uY.x(), b0uY.y(), b0uY.z(), b0uZ.x(), b0uZ.y(), b0uZ.z(), b0hs.x(), b0hs.y(), b0hs.z(), b1c.x(), b1c.y(), b1c.z(), b1uX.x(), b1uX.y(), b1uX.z(), b1uY.x(), b1uY.y(), b1uY.z(), b1uZ.x(), b1uZ.y(), b1uZ.z(), b1hs.x(), b1hs.y(), b1hs.z());
    }

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i>
     * intersects the sphere with the given center and <code>radius</code>.
     * <p>
     * The normal <code>(a, b, c)</code> of the plane equation does not need to be normalized - the
     * plane equation is evaluated at the sphere's center and divided by the length of
     * <code>(a, b, c)</code>, so the value compared against <code>radius</code> is a true distance.
     * A sphere that merely touches the plane counts as intersecting.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @param centerX the x coordinate of the sphere's center
     * @param centerY the y coordinate of the sphere's center
     * @param centerZ the z coordinate of the sphere's center
     * @param radius the radius of the sphere
     * @return <code>true</code> iff the plane intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testPlaneSphere(float a, float b, float c, float d, float centerX, float centerY, float centerZ, float radius) {
        float _t7 = Math.fma(a, centerX, Math.fma(b, centerY, Math.fma(c, centerZ, d))) * (1.0f / (float) Math.sqrt(Math.fma(c, c, Math.fma(a, a, b * b))));
        if (!(-radius <= _t7)) return false;
        return _t7 <= radius;
    }

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i>
     * intersects the sphere with the given center and <code>radius</code>.
     * <p>
     * The normal <code>(a, b, c)</code> of the plane equation does not need to be normalized - the
     * plane equation is evaluated at the sphere's center and divided by the length of
     * <code>(a, b, c)</code>, so the value compared against <code>radius</code> is a true distance.
     * A sphere that merely touches the plane counts as intersecting.
     * <p>
     * Reference: <a
     * href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @param center the sphere's center
     * @param radius the radius of the sphere
     * @return <code>true</code> iff the plane intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testPlaneSphere(float a, float b, float c, float d, Float3 center, float radius) {
        return testPlaneSphere(a, b, c, d, center.x(), center.y(), center.z(), radius);
    }

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i>
     * intersects the sphere of radius <code>r</code> whose center moves along the straight line
     * from the given start position to the given end position.
     * <p>
     * The normal <code>(a, b, c)</code> of the plane equation needs to be normalized: the distances
     * of the two center positions from the plane are compared against <code>r</code> directly. A
     * sphere that merely touches the plane counts as intersecting.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.5.3 "Intersecting Moving Sphere
     * Against Plane"
     * <p>
     * Valid input: {@code (a, b, c)} must have unit length; {@code r} must not be negative.
     *
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @param t0X the x coordinate of the start position of the sphere's center
     * @param t0Y the y coordinate of the start position of the sphere's center
     * @param t0Z the z coordinate of the start position of the sphere's center
     * @param r the sphere's radius
     * @param t1X the x coordinate of the end position of the sphere's center
     * @param t1Y the y coordinate of the end position of the sphere's center
     * @param t1Z the z coordinate of the end position of the sphere's center
     * @return <code>true</code> iff the sphere touches or crosses the plane at some point of the
     *        sweep; <code>false</code> otherwise
     */
    public static boolean testPlaneSweptSphere(float a, float b, float c, float d, float t0X, float t0Y, float t0Z, float r, float t1X, float t1Y, float t1Z) {
        float _t4 = Math.fma(a, t0X, Math.fma(b, t0Y, Math.fma(c, t0Z, d)));
        float _t5 = Math.fma(a, t1X, Math.fma(b, t1Y, Math.fma(c, t1Z, d)));
        if (_t4 * _t5 < 0.0f) return true;
        if (Math.abs(_t4) <= r) return true;
        return Math.abs(_t5) <= r;
    }

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i>
     * intersects the sphere of radius <code>r</code> whose center moves along the straight line
     * from the given start position to the given end position.
     * <p>
     * The normal <code>(a, b, c)</code> of the plane equation needs to be normalized: the distances
     * of the two center positions from the plane are compared against <code>r</code> directly. A
     * sphere that merely touches the plane counts as intersecting.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.5.3 "Intersecting Moving Sphere
     * Against Plane"
     * <p>
     * Valid input: {@code (a, b, c)} must have unit length; {@code r} must not be negative.
     *
     * @param a the x factor in the plane equation
     * @param b the y factor in the plane equation
     * @param c the z factor in the plane equation
     * @param d the constant in the plane equation
     * @param t0 the start position of the sphere's center
     * @param r the sphere's radius
     * @param t1 the end position of the sphere's center
     * @return <code>true</code> iff the sphere touches or crosses the plane at some point of the
     *        sweep; <code>false</code> otherwise
     */
    public static boolean testPlaneSweptSphere(float a, float b, float c, float d, Float3 t0, float r, Float3 t1) {
        return testPlaneSweptSphere(a, b, c, d, t0.x(), t0.y(), t0.z(), r, t1.x(), t1.y(), t1.z());
    }

    /**
     * Test whether the given point lies inside of the axis-aligned box given as its minimum and
     * maximum corner.
     * <p>
     * The test is inclusive on all six sides, so a point on the boundary of the box counts as
     * contained.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component.
     *
     * @param pX the x coordinate of the point to test
     * @param pY the y coordinate of the point to test
     * @param pZ the z coordinate of the point to test
     * @param minX the x coordinate of the minimum corner of the axis-aligned box
     * @param minY the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ the z coordinate of the maximum corner of the axis-aligned box
     * @return <code>true</code> iff the point lies inside of or on the boundary of the axis-aligned
     *        box; <code>false</code> otherwise
     */
    public static boolean testPointAabb(float pX, float pY, float pZ, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (!(pX >= minX)) return false;
        if (!(pX <= maxX)) return false;
        if (!(pY >= minY)) return false;
        if (!(pY <= maxY)) return false;
        if (!(pZ >= minZ)) return false;
        return pZ <= maxZ;
    }

    /**
     * Test whether the given point lies inside of the axis-aligned box given as its minimum and
     * maximum corner.
     * <p>
     * The test is inclusive on all six sides, so a point on the boundary of the box counts as
     * contained.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param p the point to test
     * @param min the minimum corner of the axis-aligned box
     * @param max the maximum corner of the axis-aligned box
     * @return <code>true</code> iff the point lies inside of or on the boundary of the axis-aligned
     *        box; <code>false</code> otherwise
     */
    public static boolean testPointAabb(Float3 p, Float3 min, Float3 max) {
        return testPointAabb(p.x(), p.y(), p.z(), min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
    }

    /**
     * Test whether the given point lies inside of, or on the boundary of, the axis-aligned
     * rectangle with the given minimum corner and maximum corner.
     * <p>
     * All four comparisons are inclusive, so a point on an edge or a corner of the rectangle is
     * contained.
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param pX the x coordinate of the point to test
     * @param pY the y coordinate of the point to test
     * @param minX the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY the y coordinate of the maximum corner of the axis-aligned rectangle
     * @return <code>true</code> iff the point lies inside of, or on the boundary of, the
     *        axis-aligned rectangle
     */
    public static boolean testPointAar(float pX, float pY, float minX, float minY, float maxX, float maxY) {
        if (!(pX >= minX)) return false;
        if (!(pY >= minY)) return false;
        if (!(pX <= maxX)) return false;
        return pY <= maxY;
    }

    /**
     * Test whether the given point lies inside of, or on the boundary of, the axis-aligned
     * rectangle with the given minimum corner and maximum corner.
     * <p>
     * All four comparisons are inclusive, so a point on an edge or a corner of the rectangle is
     * contained.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param p the point to test
     * @param min the minimum corner of the axis-aligned rectangle
     * @param max the maximum corner of the axis-aligned rectangle
     * @return <code>true</code> iff the point lies inside of, or on the boundary of, the
     *        axis-aligned rectangle
     */
    public static boolean testPointAar(Float2 p, Float2 min, Float2 max) {
        return testPointAar(p.x(), p.y(), min.x(), min.y(), max.x(), max.y());
    }

    /**
     * Test whether the given point lies inside the circle with the given center and the given
     * square radius.
     * <p>
     * The radius is passed already squared and the test compares squared distances, so no square
     * root is taken. The comparison is inclusive: a point exactly on the circle counts as inside.
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     *
     * @param pX the x coordinate of the point to test
     * @param pY the y coordinate of the point to test
     * @param centerX the x coordinate of the circle's center
     * @param centerY the y coordinate of the circle's center
     * @param radiusSquared the square radius of the circle
     * @return <code>true</code> iff the point lies inside the circle; <code>false</code> otherwise
     */
    public static boolean testPointCircle(float pX, float pY, float centerX, float centerY, float radiusSquared) {
        float _t0 = pX - centerX;
        float _t1 = pY - centerY;
        return Math.fma(_t0, _t0, _t1 * _t1) <= radiusSquared;
    }

    /**
     * Test whether the given point lies inside the circle with the given center and the given
     * square radius.
     * <p>
     * The radius is passed already squared and the test compares squared distances, so no square
     * root is taken. The comparison is inclusive: a point exactly on the circle counts as inside.
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     *
     * @param p the point to test
     * @param center the circle's center
     * @param radiusSquared the square radius of the circle
     * @return <code>true</code> iff the point lies inside the circle; <code>false</code> otherwise
     */
    public static boolean testPointCircle(Float2 p, Float2 center, float radiusSquared) {
        return testPointCircle(p.x(), p.y(), center.x(), center.y(), radiusSquared);
    }

    /** Private tail of {@code testPointInTriangle}; reached only through it. */
    private static boolean testPointInTriangle_s6d911777_tail(float _t7, float _t23, float _t8, float _t25, float _t24, float _t6, float _t32, float _t5, float _t33, float _t0, float _t2, float _t4, float _t3, float _t1, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        float _t34 = Math.fma(_t7, _t23, -(_t8 * _t25));
        float _t35 = Math.fma(_t8, _t24, -(_t6 * _t23));
        if (!(Math.fma(_t23, _t23, Math.fma(_t24, _t24, _t25 * _t25)) > _t32 * _t32 * 9.094947E-13f)) return false;
        if (!(Math.fma(_t5, _t33, Math.fma(_t0, _t34, _t2 * _t35)) >= 0.0f)) return false;
        if (!(-Math.fma(_t4, _t33, Math.fma(_t3, _t34, _t1 * _t35)) >= 0.0f)) return false;
        return Math.fma(Math.fma(_t9, _t10, -(_t11 * _t12)), _t23, Math.fma(Math.fma(_t11, _t13, -(_t14 * _t10)), _t25, Math.fma(_t14, _t12, -(_t9 * _t13)) * _t24)) >= 0.0f;
    }

    /**
     * Test whether the projection of the given point onto the plane of the triangle formed by the
     * three given vertices lies inside that triangle.
     * <p>
     * The point need not lie in the triangle's plane: the test compares the signed areas of the
     * three sub-triangles the point forms with the triangle's edges against the triangle's normal,
     * which see only its in-plane part, so it decides for the point's orthogonal projection onto
     * that plane. The areas are formed from cross products, which keeps the verdict accurate for
     * thin triangles. The test is inclusive, so a projection landing on an edge or on a vertex
     * counts as inside, and it is symmetric in the vertices, so the winding order does not matter.
     * A degenerate triangle - collinear or coincident vertices, or an angle between its first two
     * edges below about <code>1e-6</code> rad (<code>float</code>) / <code>2e-15</code> rad
     * (<code>double</code>) - contains no point.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the x coordinate of the point to test
     * @param pY the y coordinate of the point to test
     * @param pZ the z coordinate of the point to test
     * @param v0X the x coordinate of the first vertex
     * @param v0Y the y coordinate of the first vertex
     * @param v0Z the z coordinate of the first vertex
     * @param v1X the x coordinate of the second vertex
     * @param v1Y the y coordinate of the second vertex
     * @param v1Z the z coordinate of the second vertex
     * @param v2X the x coordinate of the third vertex
     * @param v2Y the y coordinate of the third vertex
     * @param v2Z the z coordinate of the third vertex
     * @return <code>true</code> iff the projection of the given point lies inside the given
     *        triangle
     */
    public static boolean testPointInTriangle(float pX, float pY, float pZ, float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z) {
        float _t0 = v1X - v0X;
        float _t1 = v2Y - v0Y;
        float _t2 = v1Y - v0Y;
        float _t3 = v2X - v0X;
        float _t4 = v2Z - v0Z;
        float _t5 = v1Z - v0Z;
        float _t6 = pX - v0X;
        float _t7 = pY - v0Y;
        float _t24 = Math.fma(_t2, _t4, -(_t5 * _t1));
        float _t25 = Math.fma(_t5, _t3, -(_t0 * _t4));
        return testPointInTriangle_s6d911777_tail(_t7, Math.fma(_t0, _t1, -(_t2 * _t3)), pZ - v0Z, _t25, _t24, _t6, Math.fma(_t5, _t4, Math.fma(_t0, _t3, _t2 * _t1)), _t5, Math.fma(_t6, _t25, -(_t7 * _t24)), _t0, _t2, _t4, _t3, _t1, pY - v1Y, v2X - v1X, pX - v1X, v2Y - v1Y, v2Z - v1Z, pZ - v1Z);
    }

    /**
     * Test whether the projection of the given point onto the plane of the triangle formed by the
     * three given vertices lies inside that triangle.
     * <p>
     * The point need not lie in the triangle's plane: the test compares the signed areas of the
     * three sub-triangles the point forms with the triangle's edges against the triangle's normal,
     * which see only its in-plane part, so it decides for the point's orthogonal projection onto
     * that plane. The areas are formed from cross products, which keeps the verdict accurate for
     * thin triangles. The test is inclusive, so a projection landing on an edge or on a vertex
     * counts as inside, and it is symmetric in the vertices, so the winding order does not matter.
     * A degenerate triangle - collinear or coincident vertices, or an angle between its first two
     * edges below about <code>1e-6</code> rad (<code>float</code>) / <code>2e-15</code> rad
     * (<code>double</code>) - contains no point.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @param v0 the first vertex
     * @param v1 the second vertex
     * @param v2 the third vertex
     * @return <code>true</code> iff the projection of the given point lies inside the given
     *        triangle
     */
    public static boolean testPointInTriangle(Float3 p, Float3 v0, Float3 v1, Float3 v2) {
        return testPointInTriangle(p.x(), p.y(), p.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z());
    }

    /**
     * Test whether the given point lies inside of, or on the surface of, the sphere with the given
     * center and the given squared radius.
     * <p>
     * The radius is passed already squared: the test compares the squared distance between the
     * point and the center against it, so no square root is taken and a negative
     * <code>radiusSquared</code> never contains any point.
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     *
     * @param pX the x coordinate of the point to test
     * @param pY the y coordinate of the point to test
     * @param pZ the z coordinate of the point to test
     * @param centerX the x coordinate of the sphere's center
     * @param centerY the y coordinate of the sphere's center
     * @param centerZ the z coordinate of the sphere's center
     * @param radiusSquared the square of the sphere's radius
     * @return <code>true</code> iff the point lies inside of, or on the surface of, the sphere
     */
    public static boolean testPointSphere(float pX, float pY, float pZ, float centerX, float centerY, float centerZ, float radiusSquared) {
        float _t0 = pZ - centerZ;
        float _t1 = pX - centerX;
        float _t2 = pY - centerY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)) <= radiusSquared;
    }

    /**
     * Test whether the given point lies inside of, or on the surface of, the sphere with the given
     * center and the given squared radius.
     * <p>
     * The radius is passed already squared: the test compares the squared distance between the
     * point and the center against it, so no square root is taken and a negative
     * <code>radiusSquared</code> never contains any point.
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     *
     * @param p the point to test
     * @param center the sphere's center
     * @param radiusSquared the square of the sphere's radius
     * @return <code>true</code> iff the point lies inside of, or on the surface of, the sphere
     */
    public static boolean testPointSphere(Float3 p, Float3 center, float radiusSquared) {
        return testPointSphere(p.x(), p.y(), p.z(), center.x(), center.y(), center.z(), radiusSquared);
    }

    /**
     * Test whether the given point lies inside the triangle with the three given vertices.
     * <p>
     * The winding order does not matter for a point strictly inside the triangle: the point is
     * inside when the three edge determinants share a sign. The boundary is not symmetric, though,
     * because one branch of the test admits zero and the other does not. A point lying exactly on
     * an edge or exactly on a vertex is inside only when the vertices are wound so that interior
     * points give positive determinants, which is counter-clockwise when x points right and y
     * points up; with the opposite winding a point exactly on the boundary is outside.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the x coordinate of the point to test
     * @param pY the y coordinate of the point to test
     * @param v0X the x coordinate of the first vertex of the triangle
     * @param v0Y the y coordinate of the first vertex of the triangle
     * @param v1X the x coordinate of the second vertex of the triangle
     * @param v1Y the y coordinate of the second vertex of the triangle
     * @param v2X the x coordinate of the third vertex of the triangle
     * @param v2Y the y coordinate of the third vertex of the triangle
     * @return <code>true</code> iff the point lies inside the triangle; <code>false</code>
     *        otherwise
     */
    public static boolean testPointTriangle(float pX, float pY, float v0X, float v0Y, float v1X, float v1Y, float v2X, float v2Y) {
        float _t18 = Math.fma(pX - v1X, v0Y - v1Y, -((pY - v1Y) * (v0X - v1X)));
        float _t19 = Math.fma(pX - v2X, v1Y - v2Y, -((pY - v2Y) * (v1X - v2X)));
        float _t20 = Math.fma(pX - v0X, v2Y - v0Y, -((pY - v0Y) * (v2X - v0X)));
        float _t23 = _t18 >= 0.0f ? _t19 >= 0.0f ? _t20 >= 0.0f ? 1.0f : 0.0f : 0.0f : 0.0f;
        return (_t18 < 0.0f ? _t19 < 0.0f ? _t20 < 0.0f ? 1.0f : _t23 : _t23 : _t23) != 0;
    }

    /**
     * Test whether the given point lies inside the triangle with the three given vertices.
     * <p>
     * The winding order does not matter for a point strictly inside the triangle: the point is
     * inside when the three edge determinants share a sign. The boundary is not symmetric, though,
     * because one branch of the test admits zero and the other does not. A point lying exactly on
     * an edge or exactly on a vertex is inside only when the vertices are wound so that interior
     * points give positive determinants, which is counter-clockwise when x points right and y
     * points up; with the opposite winding a point exactly on the boundary is outside.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @param v0 the first vertex of the triangle
     * @param v1 the second vertex of the triangle
     * @param v2 the third vertex of the triangle
     * @return <code>true</code> iff the point lies inside the triangle; <code>false</code>
     *        otherwise
     */
    public static boolean testPointTriangle(Float2 p, Float2 v0, Float2 v1, Float2 v2) {
        return testPointTriangle(p.x(), p.y(), v0.x(), v0.y(), v1.x(), v1.y(), v2.x(), v2.y());
    }

    /** Private tail of {@code testRayAabb}; reached only through it. */
    private static boolean testRayAabb_s60b2433f_tail(float _t17, float _t15, float _t16, float _t18, float _t19, float _t20) {
        float _t23 = _t17 > _t15 ? _t17 : _t15 != _t15 ? _t17 : _t15;
        float _t24 = _t16 < _t18 ? _t16 : _t18 != _t18 ? _t16 : _t18;
        float _t26 = _t19 < _t24 ? _t19 : _t24 != _t24 ? _t19 : _t24;
        if (_t15 > _t16) return false;
        if (_t17 > _t18) return false;
        if (_t15 > _t19) return false;
        if (_t20 > _t18) return false;
        if (!((_t20 > _t23 ? _t20 : _t23 != _t23 ? _t20 : _t23) < _t26)) return false;
        return _t26 >= 0.0f;
    }

    /**
     * Test whether the ray with the given origin and direction intersects the axis-aligned box
     * given as its minimum and maximum corner.
     * <p>
     * The ray's direction does not need to be normalized - scaling it by a positive factor scales
     * all slab parameters alike and leaves the outcome unchanged. Only intersections at or in front
     * of the origin count, so this method returns <code>true</code> for a ray whose origin lies
     * inside the axis-aligned box, and <code>false</code> for a box that lies completely behind the
     * origin.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust
     * Ray-Box Intersection</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must be non-zero; {@code (minX, minY, minZ)} must not
     * exceed {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param originZ the z coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's direction
     * @param dirY the y coordinate of the ray's direction
     * @param dirZ the z coordinate of the ray's direction
     * @param minX the x coordinate of the minimum corner of the axis-aligned box
     * @param minY the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ the z coordinate of the maximum corner of the axis-aligned box
     * @return <code>true</code> iff the ray intersects the axis-aligned box; <code>false</code>
     *        otherwise
     */
    public static boolean testRayAabb(float originX, float originY, float originZ, float dirX, float dirY, float dirZ, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _rcp0 = 1.0f / dirX;
        float _rcp1 = 1.0f / dirY;
        float _rcp2 = 1.0f / dirZ;
        float _t9 = (minX - originX) * _rcp0;
        float _t10 = (maxX - originX) * _rcp0;
        float _t11 = (maxY - originY) * _rcp1;
        float _t12 = (minY - originY) * _rcp1;
        float _t13 = (maxZ - originZ) * _rcp2;
        float _t14 = (minZ - originZ) * _rcp2;
        float _t15, _t18;
        if (_rcp0 >= 0.0f) {
            _t15 = _t9;
            _t18 = _t10;
        } else {
            _t15 = _t10;
            _t18 = _t9;
        }
        float _t16, _t17;
        if (_rcp1 >= 0.0f) {
            _t16 = _t11;
            _t17 = _t12;
        } else {
            _t16 = _t12;
            _t17 = _t11;
        }
        float _t19, _t20;
        if (_rcp2 >= 0.0f) {
            _t19 = _t13;
            _t20 = _t14;
        } else {
            _t19 = _t14;
            _t20 = _t13;
        }
        return testRayAabb_s60b2433f_tail(_t17, _t15, _t16, _t18, _t19, _t20);
    }

    /**
     * Test whether the ray with the given origin and direction intersects the axis-aligned box
     * given as its minimum and maximum corner.
     * <p>
     * The ray's direction does not need to be normalized - scaling it by a positive factor scales
     * all slab parameters alike and leaves the outcome unchanged. Only intersections at or in front
     * of the origin count, so this method returns <code>true</code> for a ray whose origin lies
     * inside the axis-aligned box, and <code>false</code> for a box that lies completely behind the
     * origin.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust
     * Ray-Box Intersection</a>
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code min} must not exceed {@code max} in any
     * component.
     *
     * @param origin the ray's origin
     * @param dir the ray's direction
     * @param min the minimum corner of the axis-aligned box
     * @param max the maximum corner of the axis-aligned box
     * @return <code>true</code> iff the ray intersects the axis-aligned box; <code>false</code>
     *        otherwise
     */
    public static boolean testRayAabb(Float3 origin, Float3 dir, Float3 min, Float3 max) {
        return testRayAabb(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
    }

    /**
     * Test whether the ray with the given origin and direction intersects the axis-aligned
     * rectangle given as its minimum corner and maximum corner.
     * <p>
     * A ray whose origin lies inside the axis-aligned rectangle counts as intersecting it. Only the
     * forward part of the ray is considered, so a rectangle lying entirely behind the origin is not
     * intersected, and the two slab intervals must overlap strictly: a ray that passes by the
     * rectangle touching one of its corners in a single point is not an intersection.
     * <p>
     * A zero component in the direction is handled: the ray then runs parallel to one of the
     * rectangle's axes and can intersect it only when its origin already lies within the
     * rectangle's extent along that axis, in which case the remaining slab decides.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust
     * Ray-Box Intersection</a>
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's direction
     * @param dirY the y coordinate of the ray's direction
     * @param minX the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY the y coordinate of the maximum corner of the axis-aligned rectangle
     * @return <code>true</code> iff the given ray intersects the axis-aligned rectangle;
     *        <code>false</code> otherwise
     */
    public static boolean testRayAar(float originX, float originY, float dirX, float dirY, float minX, float minY, float maxX, float maxY) {
        float _rcp0 = 1.0f / dirX;
        float _rcp1 = 1.0f / dirY;
        float _t6 = (minX - originX) * _rcp0;
        float _t7 = (maxX - originX) * _rcp0;
        float _t8 = (maxY - originY) * _rcp1;
        float _t9 = (minY - originY) * _rcp1;
        float _t10, _t13;
        if (_rcp0 >= 0.0f) {
            _t10 = _t6;
            _t13 = _t7;
        } else {
            _t10 = _t7;
            _t13 = _t6;
        }
        float _t11, _t12;
        if (_rcp1 >= 0.0f) {
            _t11 = _t8;
            _t12 = _t9;
        } else {
            _t11 = _t9;
            _t12 = _t8;
        }
        float _t15 = _t11 < _t13 ? _t11 : _t13 != _t13 ? _t11 : _t13;
        if (_t10 > _t11) return false;
        if (_t12 > _t13) return false;
        if (!((_t12 > _t10 ? _t12 : _t10 != _t10 ? _t12 : _t10) < _t15)) return false;
        return _t15 >= 0.0f;
    }

    /**
     * Test whether the ray with the given origin and direction intersects the axis-aligned
     * rectangle given as its minimum corner and maximum corner.
     * <p>
     * A ray whose origin lies inside the axis-aligned rectangle counts as intersecting it. Only the
     * forward part of the ray is considered, so a rectangle lying entirely behind the origin is not
     * intersected, and the two slab intervals must overlap strictly: a ray that passes by the
     * rectangle touching one of its corners in a single point is not an intersection.
     * <p>
     * A zero component in the direction is handled: the ray then runs parallel to one of the
     * rectangle's axes and can intersect it only when its origin already lies within the
     * rectangle's extent along that axis, in which case the remaining slab decides.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust
     * Ray-Box Intersection</a>
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param origin the ray's origin
     * @param dir the ray's direction
     * @param min the minimum corner of the axis-aligned rectangle
     * @param max the maximum corner of the axis-aligned rectangle
     * @return <code>true</code> iff the given ray intersects the axis-aligned rectangle;
     *        <code>false</code> otherwise
     */
    public static boolean testRayAar(Float2 origin, Float2 dir, Float2 min, Float2 max) {
        return testRayAar(origin.x(), origin.y(), dir.x(), dir.y(), min.x(), min.y(), max.x(), max.y());
    }

    /**
     * Test whether the ray with the given origin and normalized direction intersects the circle
     * with the given center and square radius.
     * <p>
     * The direction must be normalized: the kernel projects the vector from the origin to the
     * center onto it, and takes the 2D cross product of the direction with that vector as the
     * perpendicular distance from the center to the ray, which only holds for a unit direction. The
     * radius is passed squared.
     * <p>
     * An intersection is reported for a ray whose origin lies inside the circle. A ray that only
     * grazes the circle tangentially does not count, and neither does a circle lying entirely
     * behind the origin. The decision agrees with {@code intersectRayCircle}.
     * <p>
     * Reference: <a
     * href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code (dirX, dirY)} must have unit length; {@code radiusSquared} must not be
     * negative.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's normalized direction
     * @param dirY the y coordinate of the ray's normalized direction
     * @param centerX the x coordinate of the circle's center
     * @param centerY the y coordinate of the circle's center
     * @param radiusSquared the square of the circle's radius
     * @return <code>true</code> iff the ray intersects the circle
     */
    public static boolean testRayCircle(float originX, float originY, float dirX, float dirY, float centerX, float centerY, float radiusSquared) {
        float _t0 = centerY - originY;
        float _t1 = centerX - originX;
        float _t4 = Math.fma(dirX, _t0, -(dirY * _t1));
        if (!(_t4 * _t4 < radiusSquared)) return false;
        return Math.fma(dirX, _t1, Math.fma(dirY, _t0, (float) Math.sqrt(Math.fma(-_t4, _t4, radiusSquared)))) >= 0.0f;
    }

    /**
     * Test whether the ray with the given origin and normalized direction intersects the circle
     * with the given center and square radius.
     * <p>
     * The direction must be normalized: the kernel projects the vector from the origin to the
     * center onto it, and takes the 2D cross product of the direction with that vector as the
     * perpendicular distance from the center to the ray, which only holds for a unit direction. The
     * radius is passed squared.
     * <p>
     * An intersection is reported for a ray whose origin lies inside the circle. A ray that only
     * grazes the circle tangentially does not count, and neither does a circle lying entirely
     * behind the origin. The decision agrees with {@code intersectRayCircle}.
     * <p>
     * Reference: <a
     * href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code dir} must have unit length; {@code radiusSquared} must not be negative.
     *
     * @param origin the ray's origin
     * @param dir the ray's normalized direction
     * @param center the circle's center
     * @param radiusSquared the square of the circle's radius
     * @return <code>true</code> iff the ray intersects the circle
     */
    public static boolean testRayCircle(Float2 origin, Float2 dir, Float2 center, float radiusSquared) {
        return testRayCircle(origin.x(), origin.y(), dir.x(), dir.y(), center.x(), center.y(), radiusSquared);
    }

    /**
     * Test whether the given ray with the given origin and normalized direction intersects the
     * given sphere with the given center and square radius.
     * <p>
     * The ray's direction must be of unit length: the vector from the ray's origin to the sphere's
     * center is projected onto it without normalizing, and the squared length of its component
     * orthogonal to the direction is the squared distance from the center to the ray's line. The
     * radius is supplied already squared.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the sphere, and
     * <code>false</code> when the whole sphere lies behind the ray's origin. The test is strict, so
     * a ray that only touches the sphere tangentially does not count as an intersection.
     * <p>
     * Reference: <a
     * href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must have unit length; {@code radiusSquared} must not
     * be negative.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param originZ the z coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's normalized direction
     * @param dirY the y coordinate of the ray's normalized direction
     * @param dirZ the z coordinate of the ray's normalized direction
     * @param centerX the x coordinate of the sphere's center
     * @param centerY the y coordinate of the sphere's center
     * @param centerZ the z coordinate of the sphere's center
     * @param radiusSquared the square of the sphere's radius
     * @return <code>true</code> iff the ray intersects the sphere
     */
    public static boolean testRaySphere(float originX, float originY, float originZ, float dirX, float dirY, float dirZ, float centerX, float centerY, float centerZ, float radiusSquared) {
        float _t0 = centerZ - originZ;
        float _t1 = centerX - originX;
        float _t2 = centerY - originY;
        float _t5 = Math.fma(dirZ, _t0, Math.fma(dirX, _t1, dirY * _t2));
        float _t6 = Math.fma(-dirZ, _t5, _t0);
        float _t7 = Math.fma(-dirX, _t5, _t1);
        float _t8 = Math.fma(-dirY, _t5, _t2);
        if (!(Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8)) < radiusSquared)) return false;
        return Math.fma(dirX, _t1, Math.fma(dirY, _t2, Math.fma(dirZ, _t0, (float) Math.sqrt(Math.fma(-_t7, _t7, Math.fma(-_t8, _t8, Math.fma(-_t6, _t6, radiusSquared))))))) >= 0.0f;
    }

    /**
     * Test whether the given ray with the given origin and normalized direction intersects the
     * given sphere with the given center and square radius.
     * <p>
     * The ray's direction must be of unit length: the vector from the ray's origin to the sphere's
     * center is projected onto it without normalizing, and the squared length of its component
     * orthogonal to the direction is the squared distance from the center to the ray's line. The
     * radius is supplied already squared.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the sphere, and
     * <code>false</code> when the whole sphere lies behind the ray's origin. The test is strict, so
     * a ray that only touches the sphere tangentially does not count as an intersection.
     * <p>
     * Reference: <a
     * href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code dir} must have unit length; {@code radiusSquared} must not be negative.
     *
     * @param origin the ray's origin
     * @param dir the ray's normalized direction
     * @param center the sphere's center
     * @param radiusSquared the square of the sphere's radius
     * @return <code>true</code> iff the ray intersects the sphere
     */
    public static boolean testRaySphere(Float3 origin, Float3 dir, Float3 center, float radiusSquared) {
        return testRaySphere(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), center.x(), center.y(), center.z(), radiusSquared);
    }

    /** Private tail of {@code testRayTriangle}; reached only through it. */
    private static boolean testRayTriangle_s25803267_tail(float _t0, float _t21, float _t3, float _t22, float _t5, float _t23, float _t6, float _t7, float _t8, float dirZ, float _t26, float dirX, float _t27, float dirY, float _t28, float epsilon, float _t4, float _t2, float _t1) {
        float _t33 = Math.fma(_t0, _t21, Math.fma(_t3, _t22, _t5 * _t23));
        float _t33_inv = 1.0f / _t33;
        float _t34 = Math.fma(_t6, _t21, Math.fma(_t7, _t22, _t8 * _t23));
        float _t35 = Math.fma(dirZ, _t26, Math.fma(dirX, _t27, dirY * _t28));
        float _t36 = _t34 * _t33_inv;
        if (!(Math.abs(_t33) >= epsilon)) return false;
        if (!(_t36 >= 0.0f)) return false;
        if (!(_t36 <= 1.0f)) return false;
        if (!(_t35 * _t33_inv >= 0.0f)) return false;
        if (!((_t35 + _t34) * _t33_inv <= 1.0f)) return false;
        return Math.fma(_t4, _t26, Math.fma(_t2, _t27, _t1 * _t28)) * _t33_inv >= epsilon;
    }

    /**
     * Test whether the ray with the given origin and direction intersects the triangle formed by
     * the three given vertices.
     * <p>
     * This is an implementation of the <a
     * href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage Ray/Triangle
     * Intersection</a> method.
     * <p>
     * The winding order of the triangle is not taken into account, so a ray hits a front-facing
     * triangle exactly as it hits a back-facing one; use {@code testRayTriangleFront} to cull back
     * faces. Only intersections at a parameter <i>t</i> of at least {@code epsilon} in the ray
     * equation <i>p(t) = origin + t * dir</i> count, so a triangle behind the origin - or too close
     * in front of it - is missed.
     * <p>
     * The direction need not be normalized. Its length sets the unit of <i>t</i> and scales the
     * determinant, and thereby shifts what {@code epsilon} means for both of its roles.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param originZ the z coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's direction
     * @param dirY the y coordinate of the ray's direction
     * @param dirZ the z coordinate of the ray's direction
     * @param v0X the x coordinate of the first vertex
     * @param v0Y the y coordinate of the first vertex
     * @param v0Z the z coordinate of the first vertex
     * @param v1X the x coordinate of the second vertex
     * @param v1Y the y coordinate of the second vertex
     * @param v1Z the z coordinate of the second vertex
     * @param v2X the x coordinate of the third vertex
     * @param v2Y the y coordinate of the third vertex
     * @param v2Z the z coordinate of the third vertex
     * @param epsilon a small positive tolerance, used twice: the ray counts as parallel to the
     *        triangle's plane when the absolute determinant falls below it, and it is also the
     *        smallest value of <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> accepted
     *        as a hit
     * @return <code>true</code> iff the ray intersects the triangle, front face or back face
     */
    public static boolean testRayTriangle(float originX, float originY, float originZ, float dirX, float dirY, float dirZ, float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z, float epsilon) {
        float _t0 = v1Z - v0Z;
        float _t1 = v2Y - v0Y;
        float _t2 = v2X - v0X;
        float _t3 = v1X - v0X;
        float _t4 = v2Z - v0Z;
        float _t5 = v1Y - v0Y;
        float _t6 = originZ - v0Z;
        float _t7 = originX - v0X;
        float _t8 = originY - v0Y;
        return testRayTriangle_s25803267_tail(_t0, Math.fma(dirX, _t1, -(dirY * _t2)), _t3, Math.fma(dirY, _t4, -(dirZ * _t1)), _t5, Math.fma(dirZ, _t2, -(dirX * _t4)), _t6, _t7, _t8, dirZ, Math.fma(_t7, _t5, -(_t8 * _t3)), dirX, Math.fma(_t8, _t0, -(_t6 * _t5)), dirY, Math.fma(_t6, _t3, -(_t7 * _t0)), epsilon, _t4, _t2, _t1);
    }

    /**
     * Test whether the ray with the given origin and direction intersects the triangle formed by
     * the three given vertices.
     * <p>
     * This is an implementation of the <a
     * href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage Ray/Triangle
     * Intersection</a> method.
     * <p>
     * The winding order of the triangle is not taken into account, so a ray hits a front-facing
     * triangle exactly as it hits a back-facing one; use {@code testRayTriangleFront} to cull back
     * faces. Only intersections at a parameter <i>t</i> of at least {@code epsilon} in the ray
     * equation <i>p(t) = origin + t * dir</i> count, so a triangle behind the origin - or too close
     * in front of it - is missed.
     * <p>
     * The direction need not be normalized. Its length sets the unit of <i>t</i> and scales the
     * determinant, and thereby shifts what {@code epsilon} means for both of its roles.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param origin the ray's origin
     * @param dir the ray's direction
     * @param v0 the first vertex
     * @param v1 the second vertex
     * @param v2 the third vertex
     * @param epsilon a small positive tolerance, used twice: the ray counts as parallel to the
     *        triangle's plane when the absolute determinant falls below it, and it is also the
     *        smallest value of <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> accepted
     *        as a hit
     * @return <code>true</code> iff the ray intersects the triangle, front face or back face
     */
    public static boolean testRayTriangle(Float3 origin, Float3 dir, Float3 v0, Float3 v1, Float3 v2, float epsilon) {
        return testRayTriangle(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /** Private tail of {@code testRayTriangleFront}; reached only through it. */
    private static boolean testRayTriangleFront_s25803267_tail(float _t0, float _t21, float _t3, float _t22, float _t5, float _t23, float _t6, float _t7, float _t8, float dirZ, float _t26, float dirX, float _t27, float dirY, float _t28, float epsilon, float _t4, float _t2, float _t1) {
        float _t33 = Math.fma(_t0, _t21, Math.fma(_t3, _t22, _t5 * _t23));
        float _t34 = Math.fma(_t6, _t21, Math.fma(_t7, _t22, _t8 * _t23));
        float _t35 = Math.fma(dirZ, _t26, Math.fma(dirX, _t27, dirY * _t28));
        if (!(_t33 >= epsilon)) return false;
        if (!(_t34 >= 0.0f)) return false;
        if (!(_t34 <= _t33)) return false;
        if (!(_t35 >= 0.0f)) return false;
        if (!(_t35 + _t34 <= _t33)) return false;
        return Math.fma(_t4, _t26, Math.fma(_t2, _t27, _t1 * _t28)) / _t33 >= epsilon;
    }

    /**
     * Test whether the ray with the given origin and the given direction intersects the frontface
     * of the triangle consisting of the three vertices <code>v0</code>, <code>v1</code> and
     * <code>v2</code>.
     * <p>
     * This is an implementation of the <a
     * href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage Ray/Triangle
     * Intersection</a> method.
     * <p>
     * This test implements backface culling, that is, it will return <code>false</code> when the
     * triangle is in clockwise winding order assuming a <i>right-handed</i> coordinate system when
     * seen along the ray's direction, even if the ray intersects the triangle. This is in
     * compliance with how OpenGL handles backface culling with default frontface/backface settings.
     * <p>
     * The given <code>epsilon</code> is used twice: the determinant must be at least
     * <code>epsilon</code>, which rejects rays that are parallel to or hit the back of the
     * triangle, and the ray parameter <i>t</i> must be at least <code>epsilon</code>, which rejects
     * intersections behind the ray's origin, at it, and closer to it than <code>epsilon</code>. The
     * direction does not need to be normalized, but neither threshold is scale-free: the
     * determinant grows with the direction's length and <i>t</i> is measured in units of that
     * length, so the same <code>epsilon</code> means different things for differently scaled
     * directions.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param originX the x coordinate of the ray's origin
     * @param originY the y coordinate of the ray's origin
     * @param originZ the z coordinate of the ray's origin
     * @param dirX the x coordinate of the ray's direction
     * @param dirY the y coordinate of the ray's direction
     * @param dirZ the z coordinate of the ray's direction
     * @param v0X the x coordinate of the position of the first vertex
     * @param v0Y the y coordinate of the position of the first vertex
     * @param v0Z the z coordinate of the position of the first vertex
     * @param v1X the x coordinate of the position of the second vertex
     * @param v1Y the y coordinate of the position of the second vertex
     * @param v1Z the z coordinate of the position of the second vertex
     * @param v2X the x coordinate of the position of the third vertex
     * @param v2Y the y coordinate of the position of the third vertex
     * @param v2Z the z coordinate of the position of the third vertex
     * @param epsilon a small epsilon, used both as the smallest accepted determinant and as the
     *        smallest accepted value of the ray parameter <i>t</i>
     * @return <code>true</code> iff the ray intersects the frontface of the triangle
     */
    public static boolean testRayTriangleFront(float originX, float originY, float originZ, float dirX, float dirY, float dirZ, float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z, float epsilon) {
        float _t0 = v1Z - v0Z;
        float _t1 = v2Y - v0Y;
        float _t2 = v2X - v0X;
        float _t3 = v1X - v0X;
        float _t4 = v2Z - v0Z;
        float _t5 = v1Y - v0Y;
        float _t6 = originZ - v0Z;
        float _t7 = originX - v0X;
        float _t8 = originY - v0Y;
        return testRayTriangleFront_s25803267_tail(_t0, Math.fma(dirX, _t1, -(dirY * _t2)), _t3, Math.fma(dirY, _t4, -(dirZ * _t1)), _t5, Math.fma(dirZ, _t2, -(dirX * _t4)), _t6, _t7, _t8, dirZ, Math.fma(_t7, _t5, -(_t8 * _t3)), dirX, Math.fma(_t8, _t0, -(_t6 * _t5)), dirY, Math.fma(_t6, _t3, -(_t7 * _t0)), epsilon, _t4, _t2, _t1);
    }

    /**
     * Test whether the ray with the given origin and the given direction intersects the frontface
     * of the triangle consisting of the three vertices <code>v0</code>, <code>v1</code> and
     * <code>v2</code>.
     * <p>
     * This is an implementation of the <a
     * href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">Fast, Minimum Storage Ray/Triangle
     * Intersection</a> method.
     * <p>
     * This test implements backface culling, that is, it will return <code>false</code> when the
     * triangle is in clockwise winding order assuming a <i>right-handed</i> coordinate system when
     * seen along the ray's direction, even if the ray intersects the triangle. This is in
     * compliance with how OpenGL handles backface culling with default frontface/backface settings.
     * <p>
     * The given <code>epsilon</code> is used twice: the determinant must be at least
     * <code>epsilon</code>, which rejects rays that are parallel to or hit the back of the
     * triangle, and the ray parameter <i>t</i> must be at least <code>epsilon</code>, which rejects
     * intersections behind the ray's origin, at it, and closer to it than <code>epsilon</code>. The
     * direction does not need to be normalized, but neither threshold is scale-free: the
     * determinant grows with the direction's length and <i>t</i> is measured in units of that
     * length, so the same <code>epsilon</code> means different things for differently scaled
     * directions.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param origin the ray's origin
     * @param dir the ray's direction
     * @param v0 the position of the first vertex
     * @param v1 the position of the second vertex
     * @param v2 the position of the third vertex
     * @param epsilon a small epsilon, used both as the smallest accepted determinant and as the
     *        smallest accepted value of the ray parameter <i>t</i>
     * @return <code>true</code> iff the ray intersects the frontface of the triangle
     */
    public static boolean testRayTriangleFront(Float3 origin, Float3 dir, Float3 v0, Float3 v1, Float3 v2, float epsilon) {
        return testRayTriangleFront(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /**
     * Test whether two spheres, given by their centers and the squares of their radii, intersect.
     * <p>
     * Both radii are passed squared and the whole test is carried out in squared quantities, so no
     * square root is taken. It is exact and inclusive: spheres that touch in a single point count
     * as intersecting, and an intersection is also reported when one sphere completely contains the
     * other.
     * <p>
     * Reference: <a
     * href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     *
     * @param aX the x coordinate of the first sphere's center
     * @param aY the y coordinate of the first sphere's center
     * @param aZ the z coordinate of the first sphere's center
     * @param radiusSquaredA the square of the first sphere's radius
     * @param bX the x coordinate of the second sphere's center
     * @param bY the y coordinate of the second sphere's center
     * @param bZ the z coordinate of the second sphere's center
     * @param radiusSquaredB the square of the second sphere's radius
     * @return <code>true</code> iff both spheres intersect
     */
    public static boolean testSphereSphere(float aX, float aY, float aZ, float radiusSquaredA, float bX, float bY, float bZ, float radiusSquaredB) {
        float _t0 = bX - aX;
        float _t1 = bY - aY;
        float _t2 = bZ - aZ;
        float _t6 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, -radiusSquaredA - radiusSquaredB)));
        if (_t6 <= 0.0f) return true;
        return _t6 * _t6 <= 4.0f * radiusSquaredA * radiusSquaredB;
    }

    /**
     * Test whether two spheres, given by their centers and the squares of their radii, intersect.
     * <p>
     * Both radii are passed squared and the whole test is carried out in squared quantities, so no
     * square root is taken. It is exact and inclusive: spheres that touch in a single point count
     * as intersecting, and an intersection is also reported when one sphere completely contains the
     * other.
     * <p>
     * Reference: <a
     * href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     *
     * @param a the first sphere's center
     * @param radiusSquaredA the square of the first sphere's radius
     * @param b the second sphere's center
     * @param radiusSquaredB the square of the second sphere's radius
     * @return <code>true</code> iff both spheres intersect
     */
    public static boolean testSphereSphere(Float3 a, float radiusSquaredA, Float3 b, float radiusSquaredB) {
        return testSphereSphere(a.x(), a.y(), a.z(), radiusSquaredA, b.x(), b.y(), b.z(), radiusSquaredB);
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
