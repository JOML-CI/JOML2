// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

public class Intersectiond {

    /**
     * Return value of
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, double, double, double, double, Double3)},
     * {@link #findClosestPointOnTriangle(Double3R, Double3R, Double3R, Double3R, Double3)},
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #findClosestPointOnTriangle(Double2R, Double2R, Double2R, Double2R, Double2)} or
     * {@link #intersectSweptSphereTriangle}
     * to signal that the closest point is the first vertex of the triangle.
     */
    public static final int POINT_ON_TRIANGLE_VERTEX_0 = 1;
    /**
     * Return value of
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, double, double, double, double, Double3)},
     * {@link #findClosestPointOnTriangle(Double3R, Double3R, Double3R, Double3R, Double3)},
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #findClosestPointOnTriangle(Double2R, Double2R, Double2R, Double2R, Double2)} or
     * {@link #intersectSweptSphereTriangle}
     * to signal that the closest point is the second vertex of the triangle.
     */
    public static final int POINT_ON_TRIANGLE_VERTEX_1 = 2;
    /**
     * Return value of
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, double, double, double, double, Double3)},
     * {@link #findClosestPointOnTriangle(Double3R, Double3R, Double3R, Double3R, Double3)},
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #findClosestPointOnTriangle(Double2R, Double2R, Double2R, Double2R, Double2)} or
     * {@link #intersectSweptSphereTriangle}
     * to signal that the closest point is the third vertex of the triangle.
     */
    public static final int POINT_ON_TRIANGLE_VERTEX_2 = 3;

    /**
     * Return value of
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, double, double, double, double, Double3)},
     * {@link #findClosestPointOnTriangle(Double3R, Double3R, Double3R, Double3R, Double3)},
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #findClosestPointOnTriangle(Double2R, Double2R, Double2R, Double2R, Double2)} or
     * {@link #intersectSweptSphereTriangle}
     * to signal that the closest point lies on the edge between the first and second vertex of the triangle.
     */
    public static final int POINT_ON_TRIANGLE_EDGE_01 = 4;
    /**
     * Return value of
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, double, double, double, double, Double3)},
     * {@link #findClosestPointOnTriangle(Double3R, Double3R, Double3R, Double3R, Double3)},
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #findClosestPointOnTriangle(Double2R, Double2R, Double2R, Double2R, Double2)} or
     * {@link #intersectSweptSphereTriangle}
     * to signal that the closest point lies on the edge between the second and third vertex of the triangle.
     */
    public static final int POINT_ON_TRIANGLE_EDGE_12 = 5;
    /**
     * Return value of
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, double, double, double, double, Double3)},
     * {@link #findClosestPointOnTriangle(Double3R, Double3R, Double3R, Double3R, Double3)},
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #findClosestPointOnTriangle(Double2R, Double2R, Double2R, Double2R, Double2)} or
     * {@link #intersectSweptSphereTriangle}
     * to signal that the closest point lies on the edge between the third and first vertex of the triangle.
     */
    public static final int POINT_ON_TRIANGLE_EDGE_20 = 6;

    /**
     * Return value of
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, double, double, double, double, Double3)},
     * {@link #findClosestPointOnTriangle(Double3R, Double3R, Double3R, Double3R, Double3)},
     * {@link #findClosestPointOnTriangle(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #findClosestPointOnTriangle(Double2R, Double2R, Double2R, Double2R, Double2)} or 
     * {@link #intersectSweptSphereTriangle}
     * to signal that the closest point lies on the face of the triangle.
     */
    public static final int POINT_ON_TRIANGLE_FACE = 7;

    /**
     * Return value of {@link #intersectRayAar(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectRayAar(Double2R, Double2R, Double2R, Double2R, Double2)}
     * to indicate that the ray intersects the side of the axis-aligned rectangle with the minimum x coordinate.
     */
    public static final int AAR_SIDE_MINX = 0;
    /**
     * Return value of {@link #intersectRayAar(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectRayAar(Double2R, Double2R, Double2R, Double2R, Double2)}
     * to indicate that the ray intersects the side of the axis-aligned rectangle with the minimum y coordinate.
     */
    public static final int AAR_SIDE_MINY = 1;
    /**
     * Return value of {@link #intersectRayAar(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectRayAar(Double2R, Double2R, Double2R, Double2R, Double2)}
     * to indicate that the ray intersects the side of the axis-aligned rectangle with the maximum x coordinate.
     */
    public static final int AAR_SIDE_MAXX = 2;
    /**
     * Return value of {@link #intersectRayAar(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectRayAar(Double2R, Double2R, Double2R, Double2R, Double2)}
     * to indicate that the ray intersects the side of the axis-aligned rectangle with the maximum y coordinate.
     */
    public static final int AAR_SIDE_MAXY = 3;

    /**
     * Return value of {@link #intersectLineSegmentAabb(double, double, double, double, double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectLineSegmentAabb(Double3R, Double3R, Double3R, Double3R, Double2)} to indicate that the line segment does not intersect the axis-aligned box;
     * or return value of {@link #intersectLineSegmentAar(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectLineSegmentAar(Double2R, Double2R, Double2R, Double2R, Double2)} to indicate that the line segment does not intersect the axis-aligned rectangle.
     */
    public static final int OUTSIDE = -1;
    /**
     * Return value of {@link #intersectLineSegmentAabb(double, double, double, double, double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectLineSegmentAabb(Double3R, Double3R, Double3R, Double3R, Double2)} to indicate that one end point of the line segment lies inside of the axis-aligned box;
     * or return value of {@link #intersectLineSegmentAar(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectLineSegmentAar(Double2R, Double2R, Double2R, Double2R, Double2)} to indicate that one end point of the line segment lies inside of the axis-aligned rectangle.
     */
    public static final int ONE_INTERSECTION = 1;
    /**
     * Return value of {@link #intersectLineSegmentAabb(double, double, double, double, double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectLineSegmentAabb(Double3R, Double3R, Double3R, Double3R, Double2)} to indicate that the line segment intersects two sides of the axis-aligned box
     * or lies on an edge or a side of the box;
     * or return value of {@link #intersectLineSegmentAar(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectLineSegmentAar(Double2R, Double2R, Double2R, Double2R, Double2)} to indicate that the line segment intersects two edges of the axis-aligned rectangle
     * or lies on an edge of the rectangle.
     */
    public static final int TWO_INTERSECTION = 2;
    /**
     * Return value of {@link #intersectLineSegmentAabb(double, double, double, double, double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectLineSegmentAabb(Double3R, Double3R, Double3R, Double3R, Double2)} to indicate that the line segment lies completely inside of the axis-aligned box;
     * or return value of {@link #intersectLineSegmentAar(double, double, double, double, double, double, double, double, Double2)} and
     * {@link #intersectLineSegmentAar(Double2R, Double2R, Double2R, Double2R, Double2)} to indicate that the line segment lies completely inside of the axis-aligned rectangle.
     */
    public static final int INSIDE = 3;

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i> intersects the sphere with center
     * <code>(centerX, centerY, centerZ)</code> and <code>radius</code>.
     * <p>
     * Reference: <a href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a
     *          the x factor in the plane equation
     * @param b
     *          the y factor in the plane equation
     * @param c
     *          the z factor in the plane equation
     * @param d
     *          the constant in the plane equation
     * @param centerX
     *          the x coordinate of the sphere's center
     * @param centerY
     *          the y coordinate of the sphere's center
     * @param centerZ
     *          the z coordinate of the sphere's center
     * @param radius
     *          the radius of the sphere
     * @return <code>true</code> iff the plane intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testPlaneSphere(
            double a, double b, double c, double d,
            double centerX, double centerY, double centerZ, double radius) {
        return IntersectionGend.testPlaneSphere(a, b, c, d, centerX, centerY, centerZ, radius);
    }


    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i> intersects the sphere with center
     * <code>(centerX, centerY, centerZ)</code> and <code>radius</code>, and store the center of the circle of
     * intersection in the <code>(x, y, z)</code> components of the supplied vector and the radius of that circle in the w component.
     * <p>
     * Reference: <a href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a
     *          the x factor in the plane equation
     * @param b
     *          the y factor in the plane equation
     * @param c
     *          the z factor in the plane equation
     * @param d
     *          the constant in the plane equation
     * @param centerX
     *          the x coordinate of the sphere's center
     * @param centerY
     *          the y coordinate of the sphere's center
     * @param centerZ
     *          the z coordinate of the sphere's center
     * @param radius
     *          the radius of the sphere
     * @param intersectionCenterAndRadius
     *          will hold the center of the circle of intersection in the <code>(x, y, z)</code> components and the radius in the w component
     * @return <code>true</code> iff the plane intersects the sphere; <code>false</code> otherwise
     */
    public static boolean intersectPlaneSphere(
            double a, double b, double c, double d,
            double centerX, double centerY, double centerZ, double radius,
            Double4 intersectionCenterAndRadius) {
        return IntersectionGend.intersectPlaneSphere(a, b, c, d, centerX, centerY, centerZ, radius, intersectionCenterAndRadius);
    }

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i> intersects the moving sphere with center
     * <code>(cX, cY, cZ)</code>, <code>radius</code> and velocity <code>(vX, vY, vZ)</code>, and store the point of intersection
     * in the <code>(x, y, z)</code> components of the supplied vector and the time of intersection in the w component.
     * <p>
     * The normal vector <code>(a, b, c)</code> of the plane equation needs to be normalized.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.5.3 "Intersecting Moving Sphere Against Plane"
     * <p>
     * Valid input: {@code (a, b, c)} must have unit length; {@code radius} must not be negative.
     * 
     * @param a
     *          the x factor in the plane equation
     * @param b
     *          the y factor in the plane equation
     * @param c
     *          the z factor in the plane equation
     * @param d
     *          the constant in the plane equation
     * @param cX
     *          the x coordinate of the center position of the sphere at t=0
     * @param cY
     *          the y coordinate of the center position of the sphere at t=0
     * @param cZ
     *          the z coordinate of the center position of the sphere at t=0
     * @param radius
     *          the sphere's radius
     * @param vX
     *          the x component of the velocity of the sphere
     * @param vY
     *          the y component of the velocity of the sphere
     * @param vZ
     *          the z component of the velocity of the sphere
     * @param pointAndTime
     *          will hold the point and time of intersection (if any)
     * @return <code>true</code> iff the sphere intersects the plane; <code>false</code> otherwise
     */
    public static boolean intersectPlaneSweptSphere(
            double a, double b, double c, double d,
            double cX, double cY, double cZ, double radius,
            double vX, double vY, double vZ,
            Double4 pointAndTime) {
        // Compute distance of sphere center to plane
        return IntersectionGend.intersectPlaneSweptSphere(a, b, c, d, cX, cY, cZ, radius, vX, vY, vZ, pointAndTime);
    }

    /**
     * Test whether the plane with the general plane equation <i>a*x + b*y + c*z + d = 0</i> intersects the sphere moving from center
     * position <code>(t0X, t0Y, t0Z)</code> to <code>(t1X, t1Y, t1Z)</code> and having the given <code>radius</code>.
     * <p>
     * The normal vector <code>(a, b, c)</code> of the plane equation needs to be normalized.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.5.3 "Intersecting Moving Sphere Against Plane"
     * <p>
     * Valid input: {@code (a, b, c)} must have unit length; {@code r} must not be negative.
     * 
     * @param a
     *          the x factor in the plane equation
     * @param b
     *          the y factor in the plane equation
     * @param c
     *          the z factor in the plane equation
     * @param d
     *          the constant in the plane equation
     * @param t0X
     *          the x coordinate of the start position of the sphere
     * @param t0Y
     *          the y coordinate of the start position of the sphere
     * @param t0Z
     *          the z coordinate of the start position of the sphere
     * @param r
     *          the sphere's radius
     * @param t1X
     *          the x coordinate of the end position of the sphere
     * @param t1Y
     *          the y coordinate of the end position of the sphere
     * @param t1Z
     *          the z coordinate of the end position of the sphere
     * @return <code>true</code> if the sphere intersects the plane; <code>false</code> otherwise
     */
    public static boolean testPlaneSweptSphere(
            double a, double b, double c, double d,
            double t0X, double t0Y, double t0Z, double r,
            double t1X, double t1Y, double t1Z) {
        return IntersectionGend.testPlaneSweptSphere(a, b, c, d, t0X, t0Y, t0Z, r, t1X, t1Y, t1Z);
    }

    /**
     * Test whether the axis-aligned box with minimum corner <code>(minX, minY, minZ)</code> and maximum corner <code>(maxX, maxY, maxZ)</code>
     * intersects the plane with the general equation <i>a*x + b*y + c*z + d = 0</i>.
     * <p>
     * Reference: <a href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a> ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component; {@code (a, b, c)} must be non-zero.
     * 
     * @param minX
     *          the x coordinate of the minimum corner of the axis-aligned box
     * @param minY
     *          the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ
     *          the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX
     *          the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY
     *          the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ
     *          the z coordinate of the maximum corner of the axis-aligned box
     * @param a
     *          the x factor in the plane equation
     * @param b
     *          the y factor in the plane equation
     * @param c
     *          the z factor in the plane equation
     * @param d
     *          the constant in the plane equation
     * @return <code>true</code> iff the axis-aligned box intersects the plane; <code>false</code> otherwise
     */
    public static boolean testAabbPlane(
            double minX, double minY, double minZ,
            double maxX, double maxY, double maxZ,
            double a, double b, double c, double d) {
        return IntersectionGend.testAabbPlane(minX, minY, minZ, maxX, maxY, maxZ, a, b, c, d);
    }


    /**
     * Test whether the axis-aligned box with minimum corner <code>min</code> and maximum corner <code>max</code>
     * intersects the plane with the general equation <i>a*x + b*y + c*z + d = 0</i>.
     * <p>
     * Reference: <a href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a> ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code (a, b, c)} must
     * be non-zero.
     * 
     * @param min
     *          the minimum corner of the axis-aligned box
     * @param max
     *          the maximum corner of the axis-aligned box
     * @param a
     *          the x factor in the plane equation
     * @param b
     *          the y factor in the plane equation
     * @param c
     *          the z factor in the plane equation
     * @param d
     *          the constant in the plane equation
     * @return <code>true</code> iff the axis-aligned box intersects the plane; <code>false</code> otherwise
     */
    public static boolean testAabbPlane(Double3R min, Double3R max, double a, double b, double c, double d) {
        return testAabbPlane(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), a, b, c, d);
    }

    /**
     * Test whether the axis-aligned box with minimum corner <code>(minXA, minYA, minZA)</code> and maximum corner <code>(maxXA, maxYA, maxZA)</code>
     * intersects the axis-aligned box with minimum corner <code>(minXB, minYB, minZB)</code> and maximum corner <code>(maxXB, maxYB, maxZB)</code>.
     * <p>
     * Valid input: {@code (minXA, minYA, minZA)} must not exceed {@code (maxXA, maxYA, maxZA)} in
     * any component; {@code (minXB, minYB, minZB)} must not exceed {@code (maxXB, maxYB, maxZB)} in
     * any component.
     * 
     * @param minXA
     *              the x coordinate of the minimum corner of the first axis-aligned box
     * @param minYA
     *              the y coordinate of the minimum corner of the first axis-aligned box
     * @param minZA
     *              the z coordinate of the minimum corner of the first axis-aligned box
     * @param maxXA
     *              the x coordinate of the maximum corner of the first axis-aligned box
     * @param maxYA
     *              the y coordinate of the maximum corner of the first axis-aligned box
     * @param maxZA
     *              the z coordinate of the maximum corner of the first axis-aligned box
     * @param minXB
     *              the x coordinate of the minimum corner of the second axis-aligned box
     * @param minYB
     *              the y coordinate of the minimum corner of the second axis-aligned box
     * @param minZB
     *              the z coordinate of the minimum corner of the second axis-aligned box
     * @param maxXB
     *              the x coordinate of the maximum corner of the second axis-aligned box
     * @param maxYB
     *              the y coordinate of the maximum corner of the second axis-aligned box
     * @param maxZB
     *              the z coordinate of the maximum corner of the second axis-aligned box
     * @return <code>true</code> iff both axis-aligned boxes intersect; <code>false</code> otherwise
     */
    public static boolean testAabbAabb(
            double minXA, double minYA, double minZA,
            double maxXA, double maxYA, double maxZA,
            double minXB, double minYB, double minZB,
            double maxXB, double maxYB, double maxZB) {
        return IntersectionGend.testAabbAabb(minXA, minYA, minZA, maxXA, maxYA, maxZA,
                minXB, minYB, minZB, maxXB, maxYB, maxZB);
    }

    /**
     * Test whether the axis-aligned box with minimum corner <code>minA</code> and maximum corner <code>maxA</code>
     * intersects the axis-aligned box with minimum corner <code>minB</code> and maximum corner <code>maxB</code>.
     * <p>
     * Valid input: {@code minA} must not exceed {@code maxA} in any component; {@code minB} must
     * not exceed {@code maxB} in any component.
     * 
     * @param minA
     *              the minimum corner of the first axis-aligned box
     * @param maxA
     *              the maximum corner of the first axis-aligned box
     * @param minB
     *              the minimum corner of the second axis-aligned box
     * @param maxB
     *              the maximum corner of the second axis-aligned box
     * @return <code>true</code> iff both axis-aligned boxes intersect; <code>false</code> otherwise
     */
    public static boolean testAabbAabb(Double3R minA, Double3R maxA, Double3R minB, Double3R maxB) {
        return testAabbAabb(minA.x(), minA.y(), minA.z(), maxA.x(), maxA.y(), maxA.z(), minB.x(), minB.y(), minB.z(), maxB.x(), maxB.y(), maxB.z());
    }

    /**
     * Test whether the two axis-aligned boxes intersect.
     * <p>
     * Valid input: the minimum corner of {@code aabb1} must not exceed the maximum corner of {@code
     * aabb1} in any component; the minimum corner of {@code aabb2} must not exceed the maximum
     * corner of {@code aabb2} in any component.
     * 
     * @param aabb1
     *              the first AABB
     * @param aabb2
     *              the second AABB
     * @return <code>true</code> iff both axis-aligned boxes intersect; <code>false</code> otherwise
     */
    public static boolean testAabbAabb(DoubleAABBR aabb1, DoubleAABBR aabb2) {
        return testAabbAabb(aabb1.minX(), aabb1.minY(), aabb1.minZ(), aabb1.maxX(), aabb1.maxY(), aabb1.maxZ(), aabb2.minX(), aabb2.minY(), aabb2.minZ(), aabb2.maxX(), aabb2.maxY(), aabb2.maxZ());
    }

    /**
     * Test whether the moving axis-aligned box <code>A</code>, translated by <code>(velocityX, velocityY, velocityZ)</code>
     * over the step <i>t</i> in <code>[0, 1]</code>, intersects the static axis-aligned box <code>B</code>.
     * <p>
     * This is the slab test of the relative-motion ray against the Minkowski-expanded box and follows the
     * conventions of {@link #testRayAabb(double, double, double, double, double, double, double, double, double, double, double, double) testRayAabb}
     * (in particular, grazing contact is not counted).
     * <p>
     * Valid input: {@code (minXA, minYA, minZA)} must not exceed {@code (maxXA, maxYA, maxZA)} in
     * any component; {@code (minXB, minYB, minZB)} must not exceed {@code (maxXB, maxYB, maxZB)} in
     * any component.
     *
     * @param minXA
     *              the x coordinate of the minimum corner of the moving axis-aligned box
     * @param minYA
     *              the y coordinate of the minimum corner of the moving axis-aligned box
     * @param minZA
     *              the z coordinate of the minimum corner of the moving axis-aligned box
     * @param maxXA
     *              the x coordinate of the maximum corner of the moving axis-aligned box
     * @param maxYA
     *              the y coordinate of the maximum corner of the moving axis-aligned box
     * @param maxZA
     *              the z coordinate of the maximum corner of the moving axis-aligned box
     * @param velocityX
     *              the x component of the translation applied over the step
     * @param velocityY
     *              the y component of the translation applied over the step
     * @param velocityZ
     *              the z component of the translation applied over the step
     * @param minXB
     *              the x coordinate of the minimum corner of the static axis-aligned box
     * @param minYB
     *              the y coordinate of the minimum corner of the static axis-aligned box
     * @param minZB
     *              the z coordinate of the minimum corner of the static axis-aligned box
     * @param maxXB
     *              the x coordinate of the maximum corner of the static axis-aligned box
     * @param maxYB
     *              the y coordinate of the maximum corner of the static axis-aligned box
     * @param maxZB
     *              the z coordinate of the maximum corner of the static axis-aligned box
     * @return <code>true</code> iff the moving box meets the static box within the step; <code>false</code> otherwise
     */
    public static boolean testMovingAabbAabb(
            double minXA, double minYA, double minZA,
            double maxXA, double maxYA, double maxZA,
            double velocityX, double velocityY, double velocityZ,
            double minXB, double minYB, double minZB,
            double maxXB, double maxYB, double maxZB) {
        return IntersectionGend.testMovingAabbAabb(minXA, minYA, minZA, maxXA, maxYA, maxZA,
                velocityX, velocityY, velocityZ, minXB, minYB, minZB, maxXB, maxYB, maxZB);
    }

    /**
     * Test whether the axis-aligned box with corners <code>minA</code>/<code>maxA</code>, translated by
     * <code>velocity</code> over the step <i>t</i> in <code>[0, 1]</code>, intersects the static
     * axis-aligned box with corners <code>minB</code>/<code>maxB</code>.
     * <p>
     * Valid input: {@code minA} must not exceed {@code maxA} in any component; {@code minB} must
     * not exceed {@code maxB} in any component.
     *
     * @param minA
     *              the minimum corner of the moving axis-aligned box
     * @param maxA
     *              the maximum corner of the moving axis-aligned box
     * @param velocity
     *              the translation applied to the moving box over the step
     * @param minB
     *              the minimum corner of the static axis-aligned box
     * @param maxB
     *              the maximum corner of the static axis-aligned box
     * @return <code>true</code> iff the moving box meets the static box within the step; <code>false</code> otherwise
     */
    public static boolean testMovingAabbAabb(Double3R minA, Double3R maxA, Double3R velocity, Double3R minB, Double3R maxB) {
        return testMovingAabbAabb(minA.x(), minA.y(), minA.z(), maxA.x(), maxA.y(), maxA.z(),
                velocity.x(), velocity.y(), velocity.z(), minB.x(), minB.y(), minB.z(), maxB.x(), maxB.y(), maxB.z());
    }

    /**
     * Test whether the moving axis-aligned box <code>a</code>, translated by <code>velocity</code>
     * over the step <i>t</i> in <code>[0, 1]</code>, intersects the static axis-aligned box <code>b</code>.
     * <p>
     * Valid input: the minimum corner of {@code a} must not exceed the maximum corner of {@code a}
     * in any component; the minimum corner of {@code b} must not exceed the maximum corner of
     * {@code b} in any component.
     *
     * @param a
     *              the moving AABB
     * @param velocity
     *              the translation applied to <code>a</code> over the step
     * @param b
     *              the static AABB
     * @return <code>true</code> iff the moving box meets the static box within the step; <code>false</code> otherwise
     */
    public static boolean testMovingAabbAabb(DoubleAABBR a, Double3R velocity, DoubleAABBR b) {
        return testMovingAabbAabb(a.minX(), a.minY(), a.minZ(), a.maxX(), a.maxY(), a.maxZ(),
                velocity.x(), velocity.y(), velocity.z(), b.minX(), b.minY(), b.minZ(), b.maxX(), b.maxY(), b.maxZ());
    }

    /**
     * Test whether two oriented boxes given via their center position, orientation and half-size, intersect.
     * <p>
     * The orientation of a box is given as three unit vectors spanning the local orthonormal basis of the box.
     * <p>
     * The size is given as the half-size along each of the unit vectors defining the orthonormal basis.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 4.4.1 "OBB-OBB Intersection"
     * <p>
     * Valid input: {@code b0uX}, {@code b0uY} and {@code b0uZ} must be orthonormal; each component
     * of {@code b0hs} must not be negative; {@code b1uX}, {@code b1uY} and {@code b1uZ} must be
     * orthonormal; each component of {@code b1hs} must not be negative.
     * 
     * @param b0c
     *          the center of the first box
     * @param b0uX
     *          the local X unit vector of the first box
     * @param b0uY
     *          the local Y unit vector of the first box
     * @param b0uZ
     *          the local Z unit vector of the first box
     * @param b0hs
     *          the half-size of the first box
     * @param b1c
     *          the center of the second box
     * @param b1uX
     *          the local X unit vector of the second box
     * @param b1uY
     *          the local Y unit vector of the second box
     * @param b1uZ
     *          the local Z unit vector of the second box
     * @param b1hs
     *          the half-size of the second box
     * @return <code>true</code> if both boxes intersect; <code>false</code> otherwise
     */
    public static boolean testObOb(
            Double3R b0c, Double3R b0uX, Double3R b0uY, Double3R b0uZ, Double3R b0hs,
            Double3R b1c, Double3R b1uX, Double3R b1uY, Double3R b1uZ, Double3R b1hs) {
        return testObOb(
                b0c.x(), b0c.y(), b0c.z(), b0uX.x(), b0uX.y(), b0uX.z(), b0uY.x(), b0uY.y(), b0uY.z(), b0uZ.x(), b0uZ.y(), b0uZ.z(), b0hs.x(), b0hs.y(), b0hs.z(),
                b1c.x(), b1c.y(), b1c.z(), b1uX.x(), b1uX.y(), b1uX.z(), b1uY.x(), b1uY.y(), b1uY.z(), b1uZ.x(), b1uZ.y(), b1uZ.z(), b1hs.x(), b1hs.y(), b1hs.z());
    }

    /**
     * Test whether two oriented boxes given via their center position, orientation and half-size, intersect.
     * <p>
     * The orientation of a box is given as three unit vectors spanning the local orthonormal basis of the box.
     * <p>
     * The size is given as the half-size along each of the unit vectors defining the orthonormal basis.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 4.4.1 "OBB-OBB Intersection"
     * <p>
     * Valid input: {@code (b0uXx, b0uXy, b0uXz)}, {@code (b0uYx, b0uYy, b0uYz)} and {@code (b0uZx,
     * b0uZy, b0uZz)} must be orthonormal; each component of {@code (b0hsX, b0hsY, b0hsZ)} must not
     * be negative; {@code (b1uXx, b1uXy, b1uXz)}, {@code (b1uYx, b1uYy, b1uYz)} and {@code (b1uZx,
     * b1uZy, b1uZz)} must be orthonormal; each component of {@code (b1hsX, b1hsY, b1hsZ)} must not
     * be negative.
     * 
     * @param b0cX
     *          the x coordinate of the center of the first box
     * @param b0cY
     *          the y coordinate of the center of the first box
     * @param b0cZ
     *          the z coordinate of the center of the first box
     * @param b0uXx
     *          the x coordinate of the local X unit vector of the first box
     * @param b0uXy
     *          the y coordinate of the local X unit vector of the first box
     * @param b0uXz
     *          the z coordinate of the local X unit vector of the first box
     * @param b0uYx
     *          the x coordinate of the local Y unit vector of the first box
     * @param b0uYy
     *          the y coordinate of the local Y unit vector of the first box
     * @param b0uYz
     *          the z coordinate of the local Y unit vector of the first box
     * @param b0uZx
     *          the x coordinate of the local Z unit vector of the first box
     * @param b0uZy
     *          the y coordinate of the local Z unit vector of the first box
     * @param b0uZz
     *          the z coordinate of the local Z unit vector of the first box
     * @param b0hsX
     *          the half-size of the first box along its local X axis
     * @param b0hsY
     *          the half-size of the first box along its local Y axis
     * @param b0hsZ
     *          the half-size of the first box along its local Z axis
     * @param b1cX
     *          the x coordinate of the center of the second box
     * @param b1cY
     *          the y coordinate of the center of the second box
     * @param b1cZ
     *          the z coordinate of the center of the second box
     * @param b1uXx
     *          the x coordinate of the local X unit vector of the second box
     * @param b1uXy
     *          the y coordinate of the local X unit vector of the second box
     * @param b1uXz
     *          the z coordinate of the local X unit vector of the second box
     * @param b1uYx
     *          the x coordinate of the local Y unit vector of the second box
     * @param b1uYy
     *          the y coordinate of the local Y unit vector of the second box
     * @param b1uYz
     *          the z coordinate of the local Y unit vector of the second box
     * @param b1uZx
     *          the x coordinate of the local Z unit vector of the second box
     * @param b1uZy
     *          the y coordinate of the local Z unit vector of the second box
     * @param b1uZz
     *          the z coordinate of the local Z unit vector of the second box
     * @param b1hsX
     *          the half-size of the second box along its local X axis
     * @param b1hsY
     *          the half-size of the second box along its local Y axis
     * @param b1hsZ
     *          the half-size of the second box along its local Z axis
     * @return <code>true</code> if both boxes intersect; <code>false</code> otherwise
     */
    public static boolean testObOb(
            double b0cX, double b0cY, double b0cZ, double b0uXx, double b0uXy, double b0uXz, double b0uYx, double b0uYy, double b0uYz, double b0uZx, double b0uZy, double b0uZz, double b0hsX, double b0hsY, double b0hsZ,
            double b1cX, double b1cY, double b1cZ, double b1uXx, double b1uXy, double b1uXz, double b1uYx, double b1uYy, double b1uYz, double b1uZx, double b1uZy, double b1uZz, double b1hsX, double b1hsY, double b1hsZ) {
        return IntersectionGend.testObOb(b0cX, b0cY, b0cZ, b0uXx, b0uXy, b0uXz, b0uYx, b0uYy, b0uYz, b0uZx, b0uZy, b0uZz, b0hsX, b0hsY, b0hsZ, b1cX, b1cY, b1cZ, b1uXx, b1uXy, b1uXz, b1uYx, b1uYy, b1uYz, b1uZx, b1uZy, b1uZz, b1hsX, b1hsY, b1hsZ);
    }

    /**
     * Test whether the one sphere with center <code>(aX, aY, aZ)</code> and square radius <code>radiusSquaredA</code> intersects the other
     * sphere with center <code>(bX, bY, bZ)</code> and square radius <code>radiusSquaredB</code>, and store the center of the circle of
     * intersection in the <code>(x, y, z)</code> components of the supplied vector and the radius of that circle in the w component.
     * <p>
     * Two spheres that only touch give a circle of radius <code>0</code>. One sphere contained in the other without touching it, and two
     * spheres with the same center, are not reported as intersecting, because their intersection is not a circle.
     * <p>
     * The normal vector of the circle of intersection can simply be obtained by subtracting the center of either sphere from the other.
     * <p>
     * Reference: <a href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     * 
     * @param aX
     *              the x coordinate of the first sphere's center
     * @param aY
     *              the y coordinate of the first sphere's center
     * @param aZ
     *              the z coordinate of the first sphere's center
     * @param radiusSquaredA
     *              the square of the first sphere's radius
     * @param bX
     *              the x coordinate of the second sphere's center
     * @param bY
     *              the y coordinate of the second sphere's center
     * @param bZ
     *              the z coordinate of the second sphere's center
     * @param radiusSquaredB
     *              the square of the second sphere's radius
     * @param centerAndRadiusOfIntersectionCircle
     *              will hold the center of the circle of intersection in the <code>(x, y, z)</code> components and the radius in the w component
     * @return <code>true</code> iff both spheres intersect; <code>false</code> otherwise
     */
    public static boolean intersectSphereSphere(
            double aX, double aY, double aZ, double radiusSquaredA,
            double bX, double bY, double bZ, double radiusSquaredB,
            Double4 centerAndRadiusOfIntersectionCircle) {
        return IntersectionGend.intersectSphereSphere(aX, aY, aZ, radiusSquaredA, bX, bY, bZ, radiusSquaredB, centerAndRadiusOfIntersectionCircle);
    }

    /**
     * Test whether the one sphere with center <code>centerA</code> and square radius <code>radiusSquaredA</code> intersects the other
     * sphere with center <code>centerB</code> and square radius <code>radiusSquaredB</code>, and store the center of the circle of
     * intersection in the <code>(x, y, z)</code> components of the supplied vector and the radius of that circle in the w component.
     * <p>
     * Two spheres that only touch give a circle of radius <code>0</code>. One sphere contained in the other without touching it, and two
     * spheres with the same center, are not reported as intersecting, because their intersection is not a circle.
     * <p>
     * The normal vector of the circle of intersection can simply be obtained by subtracting the center of either sphere from the other.
     * <p>
     * Reference: <a href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     * 
     * @param centerA
     *              the first sphere's center
     * @param radiusSquaredA
     *              the square of the first sphere's radius
     * @param centerB
     *              the second sphere's center
     * @param radiusSquaredB
     *              the square of the second sphere's radius
     * @param centerAndRadiusOfIntersectionCircle
     *              will hold the center of the circle of intersection in the <code>(x, y, z)</code> components and the radius in the w component
     * @return <code>true</code> iff both spheres intersect; <code>false</code> otherwise
     */
    public static boolean intersectSphereSphere(Double3R centerA, double radiusSquaredA, Double3R centerB, double radiusSquaredB, Double4 centerAndRadiusOfIntersectionCircle) {
        return intersectSphereSphere(centerA.x(), centerA.y(), centerA.z(), radiusSquaredA, centerB.x(), centerB.y(), centerB.z(), radiusSquaredB, centerAndRadiusOfIntersectionCircle);
    }

    /**
     * Test whether the one sphere intersects the other sphere, and store the center of the circle of
     * intersection in the <code>(x, y, z)</code> components of the supplied vector and the radius of that circle in the w component.
     * <p>
     * Two spheres that only touch give a circle of radius <code>0</code>. One sphere contained in the other without touching it, and two
     * spheres with the same center, are not reported as intersecting, because their intersection is not a circle.
     * <p>
     * The normal vector of the circle of intersection can simply be obtained by subtracting the center of either sphere from the other.
     * <p>
     * Reference: <a href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: the radius of {@code sphereA} must not be negative; the radius of {@code sphereB} must not be negative.
     * 
     * @param sphereA
     *              the first sphere
     * @param sphereB
     *              the second sphere
     * @param centerAndRadiusOfIntersectionCircle
     *              will hold the center of the circle of intersection in the <code>(x, y, z)</code> components and the radius in the w component
     * @return <code>true</code> iff both spheres intersect; <code>false</code> otherwise
     */
    public static boolean intersectSphereSphere(DoubleSphereR sphereA, DoubleSphereR sphereB, Double4 centerAndRadiusOfIntersectionCircle) {
        return intersectSphereSphere(sphereA.x(), sphereA.y(), sphereA.z(), sphereA.r()*sphereA.r(), sphereB.x(), sphereB.y(), sphereB.z(), sphereB.r()*sphereB.r(), centerAndRadiusOfIntersectionCircle);
    }

    /**
     * Test whether the given sphere with center <code>(sX, sY, sZ)</code> intersects the triangle given by its three vertices, and if they intersect
     * store the point of intersection into <code>result</code>.
     * <p>
     * This method also returns whether the point of intersection is on one of the triangle's vertices, edges or on the face.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.2.7 "Testing Sphere Against Triangle"
     * <p>
     * Valid input: {@code sR} must not be negative.
     * 
     * @param sX
     *          the x coordinate of the sphere's center
     * @param sY
     *          the y coordinate of the sphere's center
     * @param sZ
     *          the z coordinate of the sphere's center
     * @param sR
     *          the sphere's radius
     * @param v0X
     *          the x coordinate of the first vertex of the triangle
     * @param v0Y
     *          the y coordinate of the first vertex of the triangle
     * @param v0Z
     *          the z coordinate of the first vertex of the triangle
     * @param v1X
     *          the x coordinate of the second vertex of the triangle
     * @param v1Y
     *          the y coordinate of the second vertex of the triangle
     * @param v1Z
     *          the z coordinate of the second vertex of the triangle
     * @param v2X
     *          the x coordinate of the third vertex of the triangle
     * @param v2Y
     *          the y coordinate of the third vertex of the triangle
     * @param v2Z
     *          the z coordinate of the third vertex of the triangle
     * @param result
     *          will hold the point of intersection
     * @return one of {@link #POINT_ON_TRIANGLE_VERTEX_0}, {@link #POINT_ON_TRIANGLE_VERTEX_1}, {@link #POINT_ON_TRIANGLE_VERTEX_2},
     *                {@link #POINT_ON_TRIANGLE_EDGE_01}, {@link #POINT_ON_TRIANGLE_EDGE_12}, {@link #POINT_ON_TRIANGLE_EDGE_20} or
     *                {@link #POINT_ON_TRIANGLE_FACE} or <code>0</code>
     */
    public static int intersectSphereTriangle(
            double sX, double sY, double sZ, double sR,
            double v0X, double v0Y, double v0Z,
            double v1X, double v1Y, double v1Z,
            double v2X, double v2Y, double v2Z,
            Double3 result) {
        int closest = findClosestPointOnTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, sX, sY, sZ, result);
        double vX = result.x() - sX, vY = result.y() - sY, vZ = result.z() - sZ;
        double dot = vX * vX + vY * vY + vZ * vZ;
        if (dot <= sR * sR) {
            return closest;
        }
        return 0;
    }

    /**
     * Test whether the one sphere with center <code>(aX, aY, aZ)</code> and square radius <code>radiusSquaredA</code> intersects the other
     * sphere with center <code>(bX, bY, bZ)</code> and square radius <code>radiusSquaredB</code>.
     * <p>
     * Reference: <a href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     * 
     * @param aX
     *              the x coordinate of the first sphere's center
     * @param aY
     *              the y coordinate of the first sphere's center
     * @param aZ
     *              the z coordinate of the first sphere's center
     * @param radiusSquaredA
     *              the square of the first sphere's radius
     * @param bX
     *              the x coordinate of the second sphere's center
     * @param bY
     *              the y coordinate of the second sphere's center
     * @param bZ
     *              the z coordinate of the second sphere's center
     * @param radiusSquaredB
     *              the square of the second sphere's radius
     * @return <code>true</code> iff both spheres intersect; <code>false</code> otherwise
     */
    public static boolean testSphereSphere(
            double aX, double aY, double aZ, double radiusSquaredA,
            double bX, double bY, double bZ, double radiusSquaredB) {
        return IntersectionGend.testSphereSphere(aX, aY, aZ, radiusSquaredA, bX, bY, bZ, radiusSquaredB);
    }

    /**
     * Test whether the one sphere with center <code>centerA</code> and square radius <code>radiusSquaredA</code> intersects the other
     * sphere with center <code>centerB</code> and square radius <code>radiusSquaredB</code>.
     * <p>
     * Reference: <a href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     * 
     * @param centerA
     *              the first sphere's center
     * @param radiusSquaredA
     *              the square of the first sphere's radius
     * @param centerB
     *              the second sphere's center
     * @param radiusSquaredB
     *              the square of the second sphere's radius
     * @return <code>true</code> iff both spheres intersect; <code>false</code> otherwise
     */
    public static boolean testSphereSphere(Double3R centerA, double radiusSquaredA, Double3R centerB, double radiusSquaredB) {
        return testSphereSphere(centerA.x(), centerA.y(), centerA.z(), radiusSquaredA, centerB.x(), centerB.y(), centerB.z(), radiusSquaredB);
    }

    /**
     * Determine the signed distance of the given point <code>(pointX, pointY, pointZ)</code> to the plane specified via its general plane equation
     * <i>a*x + b*y + c*z + d = 0</i>.
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero.
     * 
     * @param pointX
     *              the x coordinate of the point
     * @param pointY
     *              the y coordinate of the point
     * @param pointZ
     *              the z coordinate of the point
     * @param a
     *              the x factor in the plane equation
     * @param b
     *              the y factor in the plane equation
     * @param c
     *              the z factor in the plane equation
     * @param d
     *              the constant in the plane equation
     * @return the signed distance between the point and the plane
     */
    public static double distancePointPlane(double pointX, double pointY, double pointZ, double a, double b, double c, double d) {
        return IntersectionGend.distancePointPlane(pointX, pointY, pointZ, a, b, c, d);
    }

    /**
     * Determine the signed distance of the given point <code>(pointX, pointY, pointZ)</code> to the plane of the triangle specified by its three points
     * <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code> and <code>(v2X, v2Y, v2Z)</code>.
     * <p>
     * If the point lies on the front-facing side of the triangle's plane, that is, if the triangle has counter-clockwise winding order
     * as seen from the point, then this method returns a positive number.
     * <p>
     * Valid input: {@code (v0X, v0Y, v0Z)}, {@code (v1X, v1Y, v1Z)} and {@code (v2X, v2Y, v2Z)}
     * must not be collinear.
     * 
     * @param pointX
     *          the x coordinate of the point
     * @param pointY
     *          the y coordinate of the point
     * @param pointZ
     *          the z coordinate of the point
     * @param v0X
     *          the x coordinate of the first vertex of the triangle
     * @param v0Y
     *          the y coordinate of the first vertex of the triangle
     * @param v0Z
     *          the z coordinate of the first vertex of the triangle
     * @param v1X
     *          the x coordinate of the second vertex of the triangle
     * @param v1Y
     *          the y coordinate of the second vertex of the triangle
     * @param v1Z
     *          the z coordinate of the second vertex of the triangle
     * @param v2X
     *          the x coordinate of the third vertex of the triangle
     * @param v2Y
     *          the y coordinate of the third vertex of the triangle
     * @param v2Z
     *          the z coordinate of the third vertex of the triangle
     * @return the signed distance between the point and the plane of the triangle
     */
    public static double distancePointPlane(double pointX, double pointY, double pointZ,
            double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z) {
        return IntersectionGend.distancePointPlane(pointX, pointY, pointZ,
                v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * Test whether the ray with given origin <code>(originX, originY, originZ)</code> and direction <code>(dirX, dirY, dirZ)</code> intersects the plane
     * containing the given point <code>(pointX, pointY, pointZ)</code> and having the normal <code>(normalX, normalY, normalZ)</code>, and return the
     * value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point.
     * <p>
     * This method returns <code>-1.0</code> if the ray does not intersect the plane, because it is either parallel to the plane or its direction points
     * away from the plane or the ray's origin is on the <i>negative</i> side of the plane (i.e. the plane's normal points away from the ray's origin).
     * <p>
     * Reference: <a href="https://www.siggraph.org/education/materials/HyperGraph/raytrace/rayplane_intersection.htm">https://www.siggraph.org/</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must be non-zero; {@code (normalX, normalY, normalZ)}
     * must be non-zero; {@code epsilon} must not be negative.
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param originZ
     *              the z coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param dirZ
     *              the z coordinate of the ray's direction
     * @param pointX
     *              the x coordinate of a point on the plane
     * @param pointY
     *              the y coordinate of a point on the plane
     * @param pointZ
     *              the z coordinate of a point on the plane
     * @param normalX
     *              the x coordinate of the plane's normal
     * @param normalY
     *              the y coordinate of the plane's normal
     * @param normalZ
     *              the z coordinate of the plane's normal
     * @param epsilon
     *              some small epsilon for when the ray is parallel to the plane
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point, if the ray
     *         intersects the plane; <code>-1.0</code> otherwise
     */
    public static double intersectRayPlane(double originX, double originY, double originZ, double dirX, double dirY, double dirZ,
            double pointX, double pointY, double pointZ, double normalX, double normalY, double normalZ, double epsilon) {
        return IntersectionGend.intersectRayPlane(originX, originY, originZ, dirX, dirY, dirZ,
                pointX, pointY, pointZ, normalX, normalY, normalZ, epsilon);
    }

    /**
     * Test whether the ray with given <code>origin</code> and direction <code>dir</code> intersects the plane
     * containing the given <code>point</code> and having the given <code>normal</code>, and return the
     * value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point.
     * <p>
     * This method returns <code>-1.0</code> if the ray does not intersect the plane, because it is either parallel to the plane or its direction points
     * away from the plane or the ray's origin is on the <i>negative</i> side of the plane (i.e. the plane's normal points away from the ray's origin).
     * <p>
     * Reference: <a href="https://www.siggraph.org/education/materials/HyperGraph/raytrace/rayplane_intersection.htm">https://www.siggraph.org/</a>
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code normal} must be non-zero; {@code epsilon}
     * must not be negative.
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param point
     *              a point on the plane
     * @param normal
     *              the plane's normal
     * @param epsilon
     *              some small epsilon for when the ray is parallel to the plane
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point, if the ray
     *         intersects the plane; <code>-1.0</code> otherwise
     */
    public static double intersectRayPlane(Double3R origin, Double3R dir, Double3R point, Double3R normal, double epsilon) {
        return intersectRayPlane(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), point.x(), point.y(), point.z(), normal.x(), normal.y(), normal.z(), epsilon);
    }


    /**
     * Test whether the ray with given origin <code>(originX, originY, originZ)</code> and direction <code>(dirX, dirY, dirZ)</code> intersects the plane
     * given as the general plane equation <i>a*x + b*y + c*z + d = 0</i>, and return the
     * value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point.
     * <p>
     * This method returns <code>-1.0</code> if the ray does not intersect the plane, because it is either parallel to the plane or its direction points
     * away from the plane or the ray's origin is on the <i>negative</i> side of the plane (i.e. the plane's normal points away from the ray's origin).
     * <p>
     * Reference: <a href="https://www.siggraph.org/education/materials/HyperGraph/raytrace/rayplane_intersection.htm">https://www.siggraph.org/</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must be non-zero; {@code (a, b, c)} must be non-zero; {@code epsilon} must not be negative.
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param originZ
     *              the z coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param dirZ
     *              the z coordinate of the ray's direction
     * @param a
     *              the x factor in the plane equation
     * @param b
     *              the y factor in the plane equation
     * @param c
     *              the z factor in the plane equation
     * @param d
     *              the constant in the plane equation
     * @param epsilon
     *              some small epsilon for when the ray is parallel to the plane
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point, if the ray
     *         intersects the plane; <code>-1.0</code> otherwise
     */
    public static double intersectRayPlane(double originX, double originY, double originZ, double dirX, double dirY, double dirZ,
            double a, double b, double c, double d, double epsilon) {
        double denom = a * dirX + b * dirY + c * dirZ;
        // Front-facing only, like the point/normal sibling: the direction must run
        // against the plane's normal by more than epsilon. The old `denom < epsilon`
        // accepted the whole (near-)parallel band [-epsilon, epsilon) and returned
        // a huge or infinite t for it.
        if (denom < -epsilon) {
            double t = -(a * originX + b * originY + c * originZ + d) / denom;
            if (t >= 0.0)
                return t;
        }
        return -1.0;
    }

    /**
     * Test whether the axis-aligned box with minimum corner <code>(minX, minY, minZ)</code> and maximum corner <code>(maxX, maxY, maxZ)</code>
     * intersects the sphere with the given center <code>(centerX, centerY, centerZ)</code> and square radius <code>radiusSquared</code>.
     * <p>
     * Reference: <a href="http://stackoverflow.com/questions/4578967/cube-sphere-intersection-test#answer-4579069">http://stackoverflow.com</a>
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component; {@code radiusSquared} must not be negative.
     * 
     * @param minX
     *          the x coordinate of the minimum corner of the axis-aligned box
     * @param minY
     *          the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ
     *          the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX
     *          the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY
     *          the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ
     *          the z coordinate of the maximum corner of the axis-aligned box
     * @param centerX
     *          the x coordinate of the sphere's center
     * @param centerY
     *          the y coordinate of the sphere's center
     * @param centerZ
     *          the z coordinate of the sphere's center
     * @param radiusSquared
     *          the square of the sphere's radius
     * @return <code>true</code> iff the axis-aligned box intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testAabbSphere(
            double minX, double minY, double minZ,
            double maxX, double maxY, double maxZ,
            double centerX, double centerY, double centerZ, double radiusSquared) {
        return IntersectionGend.testAabbSphere(minX, minY, minZ, maxX, maxY, maxZ, centerX, centerY, centerZ, radiusSquared);
    }

    /**
     * Test whether the axis-aligned box with minimum corner <code>min</code> and maximum corner <code>max</code>
     * intersects the sphere with the given <code>center</code> and square radius <code>radiusSquared</code>.
     * <p>
     * Reference: <a href="http://stackoverflow.com/questions/4578967/cube-sphere-intersection-test#answer-4579069">http://stackoverflow.com</a>
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code radiusSquared}
     * must not be negative.
     * 
     * @param min
     *          the minimum corner of the axis-aligned box
     * @param max
     *          the maximum corner of the axis-aligned box
     * @param center
     *          the sphere's center
     * @param radiusSquared
     *          the square of the sphere's radius
     * @return <code>true</code> iff the axis-aligned box intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testAabbSphere(Double3R min, Double3R max, Double3R center, double radiusSquared) {
        return testAabbSphere(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), center.x(), center.y(), center.z(), radiusSquared);
    }

    /**
     * Test whether the given axis-aligned box intersects the given sphere.
     * <p>
     * Reference: <a href="http://stackoverflow.com/questions/4578967/cube-sphere-intersection-test#answer-4579069">http://stackoverflow.com</a>
     * <p>
     * Valid input: the minimum corner of {@code aabb} must not exceed the maximum corner of {@code
     * aabb} in any component.
     * 
     * @param aabb
     *          the AABB
     * @param sphere
     *          the sphere
     * @return <code>true</code> iff the axis-aligned box intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testAabbSphere(DoubleAABBR aabb, DoubleSphereR sphere) {
        return testAabbSphere(aabb.minX(), aabb.minY(), aabb.minZ(), aabb.maxX(), aabb.maxY(), aabb.maxZ(), sphere.x(), sphere.y(), sphere.z(), sphere.r()*sphere.r());
    }

    /**
     * Find the point on the given plane which is closest to the specified point <code>(pX, pY, pZ)</code> and store the result in <code>result</code>.
     * <p>
     * Valid input: {@code (nX, nY, nZ)} must have unit length.
     * 
     * @param aX
     *          the x coordinate of one point on the plane
     * @param aY
     *          the y coordinate of one point on the plane
     * @param aZ
     *          the z coordinate of one point on the plane
     * @param nX
     *          the x coordinate of the unit normal of the plane
     * @param nY
     *          the y coordinate of the unit normal of the plane
     * @param nZ
     *          the z coordinate of the unit normal of the plane
     * @param pX
     *          the x coordinate of the point
     * @param pY
     *          the y coordinate of the point
     * @param pZ
     *          the z coordinate of the point
     * @param result
     *          will hold the result
     * @return result
     */
    public static Double3 findClosestPointOnPlane(double aX, double aY, double aZ, double nX, double nY, double nZ, double pX, double pY, double pZ, Double3 result) {
        return IntersectionGend.findClosestPointOnPlane(aX, aY, aZ, nX, nY, nZ, pX, pY, pZ, result);
    }

    /**
     * Find the point on the given line segment which is closest to the specified point <code>(pX, pY, pZ)</code>, and store the result in <code>result</code>.
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param aX
     *          the x coordinate of the first end point of the line segment
     * @param aY
     *          the y coordinate of the first end point of the line segment
     * @param aZ
     *          the z coordinate of the first end point of the line segment
     * @param bX
     *          the x coordinate of the second end point of the line segment
     * @param bY
     *          the y coordinate of the second end point of the line segment
     * @param bZ
     *          the z coordinate of the second end point of the line segment
     * @param pX
     *          the x coordinate of the point
     * @param pY
     *          the y coordinate of the point
     * @param pZ
     *          the z coordinate of the point
     * @param result
     *          will hold the result
     * @return result
     */
    public static Double3 findClosestPointOnLineSegment(double aX, double aY, double aZ, double bX, double bY, double bZ, double pX, double pY, double pZ, Double3 result) {
        return IntersectionGend.findClosestPointOnLineSegment(aX, aY, aZ, bX, bY, bZ, pX, pY, pZ, result);
    }

    /**
     * Find the closest points on the two line segments, store the point on the first line segment in <code>resultA</code> and 
     * the point on the second line segment in <code>resultB</code>, and return the square distance between both points.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.9 "Closest Points of Two Line Segments"
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param a0X
     *          the x coordinate of the first line segment's first end point
     * @param a0Y
     *          the y coordinate of the first line segment's first end point
     * @param a0Z
     *          the z coordinate of the first line segment's first end point
     * @param a1X
     *          the x coordinate of the first line segment's second end point
     * @param a1Y
     *          the y coordinate of the first line segment's second end point
     * @param a1Z
     *          the z coordinate of the first line segment's second end point
     * @param b0X
     *          the x coordinate of the second line segment's first end point
     * @param b0Y
     *          the y coordinate of the second line segment's first end point
     * @param b0Z
     *          the z coordinate of the second line segment's first end point
     * @param b1X
     *          the x coordinate of the second line segment's second end point
     * @param b1Y
     *          the y coordinate of the second line segment's second end point
     * @param b1Z
     *          the z coordinate of the second line segment's second end point
     * @param resultA
     *          will hold the point on the first line segment
     * @param resultB
     *          will hold the point on the second line segment
     * @return the square distance between the two closest points
     */
    public static double findClosestPointsLineSegments(
            double a0X, double a0Y, double a0Z, double a1X, double a1Y, double a1Z,
            double b0X, double b0Y, double b0Z, double b1X, double b1Y, double b1Z,
            Double3 resultA, Double3 resultB) {
        double d1x = a1X - a0X, d1y = a1Y - a0Y, d1z = a1Z - a0Z;
        double d2x = b1X - b0X, d2y = b1Y - b0Y, d2z = b1Z - b0Z;
        double rX = a0X - b0X, rY = a0Y - b0Y, rZ = a0Z - b0Z;
        double a = d1x * d1x + d1y * d1y + d1z * d1z;
        double e = d2x * d2x + d2y * d2y + d2z * d2z;
        double f = d2x * rX + d2y * rY + d2z * rZ;
        // Only a segment of exactly zero length is a point: the divisions by a and e
        // below are clamped to [0, 1], so however short a segment is, its closest
        // point stays on it. A threshold relative to the longer segment (1e-5 of its
        // length) moved the closest point by up to the whole short segment - 1.005
        // instead of 0.1 for a unit segment beside one 120000 long.
        double s, t;
        if (a == 0 && e == 0) {
            // Both segments degenerate into points
            resultA.set(a0X, a0Y, a0Z);
            resultB.set(b0X, b0Y, b0Z);
            return rX * rX + rY * rY + rZ * rZ;
        }
        if (a == 0) {
            // First segment degenerates into a point
            s = 0.0;
            t = f / e;
            t = Math.min(Math.max(t, 0.0), 1.0);
        } else {
            double c = d1x * rX + d1y * rY + d1z * rZ;
            if (e == 0) {
                // Second segment degenerates into a point
                t = 0.0;
                s = Math.min(Math.max(-c / a, 0.0), 1.0);
            } else {
                // The general nondegenerate case starts here
                double b = d1x * d2x + d1y * d2y + d1z * d2z;
                double denom = a * e - b * b;
                // If segments not parallel, compute closest point on L1 to L2 and
                // clamp to segment S1. Else pick arbitrary s (here 0)
                if (denom != 0.0)
                    s = Math.min(Math.max((b*f - c*e) / denom, 0.0), 1.0);
                else
                    s = 0.0;
                // Compute point on L2 closest to S1(s) using
                // t = Dot((P1 + D1*s) - P2,D2) / Dot(D2,D2) = (b*s + f) / e
                t = (b * s + f) / e;
                // If t in [0,1] done. Else clamp t, recompute s for the new value
                // of t using s = Dot((P2 + D2*t) - P1,D1) / Dot(D1,D1)= (t*b - c) / a
                // and clamp s to [0, 1]
                if (t < 0.0) {
                    t = 0.0;
                    s = Math.min(Math.max(-c / a, 0.0), 1.0);
                } else if (t > 1.0) {
                    t = 1.0;
                    s = Math.min(Math.max((b - c) / a, 0.0), 1.0);
                }
            }
        }
        resultA.set(a0X + d1x * s, a0Y + d1y * s, a0Z + d1z * s);
        resultB.set(b0X + d2x * t, b0Y + d2y * t, b0Z + d2z * t);
        double dX = resultA.x() - resultB.x(), dY = resultA.y() - resultB.y(), dZ = resultA.z() - resultB.z();
        return dX*dX + dY*dY + dZ*dZ;
    }

    /**
     * Find the closest points on a line segment and a triangle.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.10 "Closest Points of a Line Segment and a Triangle"
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param aX
     *          the x coordinate of the line segment's first end point
     * @param aY
     *          the y coordinate of the line segment's first end point
     * @param aZ
     *          the z coordinate of the line segment's first end point
     * @param bX
     *          the x coordinate of the line segment's second end point
     * @param bY
     *          the y coordinate of the line segment's second end point
     * @param bZ
     *          the z coordinate of the line segment's second end point
     * @param v0X
     *          the x coordinate of the triangle's first vertex
     * @param v0Y
     *          the y coordinate of the triangle's first vertex
     * @param v0Z
     *          the z coordinate of the triangle's first vertex
     * @param v1X
     *          the x coordinate of the triangle's second vertex
     * @param v1Y
     *          the y coordinate of the triangle's second vertex
     * @param v1Z
     *          the z coordinate of the triangle's second vertex
     * @param v2X
     *          the x coordinate of the triangle's third vertex
     * @param v2Y
     *          the y coordinate of the triangle's third vertex
     * @param v2Z
     *          the z coordinate of the triangle's third vertex
     * @param lineSegmentResult
     *          will hold the closest point on the line segment
     * @param triangleResult
     *          will hold the closest point on the triangle
     * @return the square distance of the closest points
     */
    public static double findClosestPointsLineSegmentTriangle(
            double aX, double aY, double aZ, double bX, double bY, double bZ,
            double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z,
            Double3 lineSegmentResult, Double3 triangleResult) {
        double min, d;
        double minlsX, minlsY, minlsZ, mintX, mintY, mintZ;
        // The segment may pass straight THROUGH the triangle, in which case the
        // distance is zero and both closest points are the crossing point. None
        // of the candidates below can see that: the three edge tests measure to
        // the boundary, and the two endpoint tests only fire when an endpoint
        // projects inside the face. With the segment crossing the interior and
        // both endpoints projecting outside, every candidate is strictly
        // positive and the smallest of them would be returned instead of 0.
        double dirX = bX - aX, dirY = bY - aY, dirZ = bZ - aZ;
        // The Moller-Trumbore determinant scales with |dir|*|v1-v0|*|v2-v0|, so the
        // parallel-ray epsilon must scale the same way: with a fixed 1E-6 a
        // millimetre-sized segment and triangle (determinant ~1e-9) always counted
        // as parallel and the crossing was never seen.
        double e1X = v1X - v0X, e1Y = v1Y - v0Y, e1Z = v1Z - v0Z;
        double e2X = v2X - v0X, e2Y = v2Y - v0Y, e2Z = v2Z - v0Z;
        double detScale = Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ)
                * Math.sqrt(e1X * e1X + e1Y * e1Y + e1Z * e1Z)
                * Math.sqrt(e2X * e2X + e2Y * e2Y + e2Z * e2Z);
        double tHit = intersectRayTriangle(aX, aY, aZ, dirX, dirY, dirZ,
                v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, 1E-9 * detScale);
        if (tHit >= 0.0 && tHit <= 1.0) {
            double hX = aX + tHit * dirX, hY = aY + tHit * dirY, hZ = aZ + tHit * dirZ;
            lineSegmentResult.set(hX, hY, hZ);
            triangleResult.set(hX, hY, hZ);
            return 0.0;
        }
        // AB -> V0V1
        d = findClosestPointsLineSegments(aX, aY, aZ, bX, bY, bZ, v0X, v0Y, v0Z, v1X, v1Y, v1Z, lineSegmentResult, triangleResult);
        min = d;
        minlsX = lineSegmentResult.x(); minlsY = lineSegmentResult.y(); minlsZ = lineSegmentResult.z();
        mintX = triangleResult.x(); mintY = triangleResult.y(); mintZ = triangleResult.z();
        // AB -> V1V2
        d = findClosestPointsLineSegments(aX, aY, aZ, bX, bY, bZ, v1X, v1Y, v1Z, v2X, v2Y, v2Z, lineSegmentResult, triangleResult);
        if (d < min) {
            min = d;
            minlsX = lineSegmentResult.x(); minlsY = lineSegmentResult.y(); minlsZ = lineSegmentResult.z();
            mintX = triangleResult.x(); mintY = triangleResult.y(); mintZ = triangleResult.z();
        }
        // AB -> V2V0
        d = findClosestPointsLineSegments(aX, aY, aZ, bX, bY, bZ, v2X, v2Y, v2Z, v0X, v0Y, v0Z, lineSegmentResult, triangleResult);
        if (d < min) {
            min = d;
            minlsX = lineSegmentResult.x(); minlsY = lineSegmentResult.y(); minlsZ = lineSegmentResult.z();
            mintX = triangleResult.x(); mintY = triangleResult.y(); mintZ = triangleResult.z();
        }
        // segment endpoint A and plane of triangle (when A projects inside V0V1V2)
        boolean computed = false;
        double a = Double.NaN, b = Double.NaN, c = Double.NaN, nd = Double.NaN;
        if (testPointInTriangle(aX, aY, aZ, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z)) {
            double v1Y0Y = v1Y - v0Y;
            double v2Z0Z = v2Z - v0Z;
            double v2Y0Y = v2Y - v0Y;
            double v1Z0Z = v1Z - v0Z;
            double v2X0X = v2X - v0X;
            double v1X0X = v1X - v0X;
            a = v1Y0Y * v2Z0Z - v2Y0Y * v1Z0Z;
            b = v1Z0Z * v2X0X - v2Z0Z * v1X0X;
            c = v1X0X * v2Y0Y - v2X0X * v1Y0Y;
            computed = true;
            double invLen = (1.0 / Math.sqrt(a*a + b*b + c*c));
            a *= invLen; b *= invLen; c *= invLen;
            nd = -(a * v0X + b * v0Y + c * v0Z);
            d = (a * aX + b * aY + c * aZ + nd);
            double l = d;
            d *= d;
            if (d < min) {
                min = d;
                minlsX = aX; minlsY = aY; minlsZ = aZ;
                mintX = aX - a*l; mintY = aY - b*l; mintZ = aZ - c*l;
            }
        }
        // segment endpoint B and plane of triangle (when B projects inside V0V1V2)
        if (testPointInTriangle(bX, bY, bZ, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z)) {
            if (!computed) {
                double v1Y0Y = v1Y - v0Y;
                double v2Z0Z = v2Z - v0Z;
                double v2Y0Y = v2Y - v0Y;
                double v1Z0Z = v1Z - v0Z;
                double v2X0X = v2X - v0X;
                double v1X0X = v1X - v0X;
                a = v1Y0Y * v2Z0Z - v2Y0Y * v1Z0Z;
                b = v1Z0Z * v2X0X - v2Z0Z * v1X0X;
                c = v1X0X * v2Y0Y - v2X0X * v1Y0Y;
                double invLen = (1.0 / Math.sqrt(a*a + b*b + c*c));
                a *= invLen; b *= invLen; c *= invLen;
                nd = -(a * v0X + b * v0Y + c * v0Z);
            }
            d = (a * bX + b * bY + c * bZ + nd);
            double l = d;
            d *= d;
            if (d < min) {
                min = d;
                minlsX = bX; minlsY = bY; minlsZ = bZ;
                mintX = bX - a*l; mintY = bY - b*l; mintZ = bZ - c*l;
            }
        }
        lineSegmentResult.set(minlsX, minlsY, minlsZ);
        triangleResult.set(mintX, mintY, mintZ);
        return min;
    }

    /**
     * Determine the closest point on the triangle with the given vertices <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code>, <code>(v2X, v2Y, v2Z)</code>
     * between that triangle and the given point <code>(pX, pY, pZ)</code> and store that point into the given <code>result</code>.
     * <p>
     * Additionally, this method returns whether the closest point is a vertex ({@link #POINT_ON_TRIANGLE_VERTEX_0}, {@link #POINT_ON_TRIANGLE_VERTEX_1}, {@link #POINT_ON_TRIANGLE_VERTEX_2})
     * of the triangle, lies on an edge ({@link #POINT_ON_TRIANGLE_EDGE_01}, {@link #POINT_ON_TRIANGLE_EDGE_12}, {@link #POINT_ON_TRIANGLE_EDGE_20})
     * or on the {@link #POINT_ON_TRIANGLE_FACE face} of the triangle.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.5 "Closest Point on Triangle to Point"
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param v0X
     *          the x coordinate of the first vertex of the triangle
     * @param v0Y
     *          the y coordinate of the first vertex of the triangle
     * @param v0Z
     *          the z coordinate of the first vertex of the triangle
     * @param v1X
     *          the x coordinate of the second vertex of the triangle
     * @param v1Y
     *          the y coordinate of the second vertex of the triangle
     * @param v1Z
     *          the z coordinate of the second vertex of the triangle
     * @param v2X
     *          the x coordinate of the third vertex of the triangle
     * @param v2Y
     *          the y coordinate of the third vertex of the triangle
     * @param v2Z
     *          the z coordinate of the third vertex of the triangle
     * @param pX
     *          the x coordinate of the point
     * @param pY
     *          the y coordinate of the point
     * @param pZ
     *          the z coordinate of the point
     * @param result
     *          will hold the closest point
     * @return one of {@link #POINT_ON_TRIANGLE_VERTEX_0}, {@link #POINT_ON_TRIANGLE_VERTEX_1}, {@link #POINT_ON_TRIANGLE_VERTEX_2},
     *                {@link #POINT_ON_TRIANGLE_EDGE_01}, {@link #POINT_ON_TRIANGLE_EDGE_12}, {@link #POINT_ON_TRIANGLE_EDGE_20} or
     *                {@link #POINT_ON_TRIANGLE_FACE}
     */
    public static int findClosestPointOnTriangle(
            double v0X, double v0Y, double v0Z,
            double v1X, double v1Y, double v1Z,
            double v2X, double v2Y, double v2Z,
            double pX, double pY, double pZ,
            Double3 result) {
        return IntersectionGend.findClosestPointOnTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, pX, pY, pZ, result);
    }

    /**
     * Determine the closest point on the triangle with the vertices <code>v0</code>, <code>v1</code>, <code>v2</code>
     * between that triangle and the given point <code>p</code> and store that point into the given <code>result</code>.
     * <p>
     * Additionally, this method returns whether the closest point is a vertex ({@link #POINT_ON_TRIANGLE_VERTEX_0}, {@link #POINT_ON_TRIANGLE_VERTEX_1}, {@link #POINT_ON_TRIANGLE_VERTEX_2})
     * of the triangle, lies on an edge ({@link #POINT_ON_TRIANGLE_EDGE_01}, {@link #POINT_ON_TRIANGLE_EDGE_12}, {@link #POINT_ON_TRIANGLE_EDGE_20})
     * or on the {@link #POINT_ON_TRIANGLE_FACE face} of the triangle.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.5 "Closest Point on Triangle to Point"
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param v0
     *          the first vertex of the triangle
     * @param v1
     *          the second vertex of the triangle
     * @param v2
     *          the third vertex of the triangle
     * @param p
     *          the point
     * @param result
     *          will hold the closest point
     * @return one of {@link #POINT_ON_TRIANGLE_VERTEX_0}, {@link #POINT_ON_TRIANGLE_VERTEX_1}, {@link #POINT_ON_TRIANGLE_VERTEX_2},
     *                {@link #POINT_ON_TRIANGLE_EDGE_01}, {@link #POINT_ON_TRIANGLE_EDGE_12}, {@link #POINT_ON_TRIANGLE_EDGE_20} or
     *                {@link #POINT_ON_TRIANGLE_FACE}
     */
    public static int findClosestPointOnTriangle(Double3R v0, Double3R v1, Double3R v2, Double3R p, Double3 result) {
        return findClosestPointOnTriangle(v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), p.x(), p.y(), p.z(), result);
    }

    /**
     * Find the point on a given rectangle, specified via three of its corners, which is closest to the specified point
     * <code>(pX, pY, pZ)</code> and store the result into <code>res</code>.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.4.2 "Closest Point on 3D Rectangle to Point"
     * <p>
     * Valid input: the edges from {@code (aX, aY, aZ)} to {@code (bX, bY, bZ)} and to {@code (cX,
     * cY, cZ)} must be perpendicular.
     * 
     * @param aX
     *          the x coordinate of the first corner point of the rectangle
     * @param aY
     *          the y coordinate of the first corner point of the rectangle
     * @param aZ
     *          the z coordinate of the first corner point of the rectangle
     * @param bX
     *          the x coordinate of the second corner point of the rectangle 
     * @param bY
     *          the y coordinate of the second corner point of the rectangle
     * @param bZ
     *          the z coordinate of the second corner point of the rectangle
     * @param cX
     *          the x coordinate of the third corner point of the rectangle
     * @param cY
     *          the y coordinate of the third corner point of the rectangle
     * @param cZ
     *          the z coordinate of the third corner point of the rectangle
     * @param pX
     *          the x coordinate of the point
     * @param pY
     *          the y coordinate of the point
     * @param pZ
     *          the z coordinate of the point
     * @param res
     *          will hold the result
     * @return res
     */
    public static Double3 findClosestPointOnRectangle(
            double aX, double aY, double aZ,
            double bX, double bY, double bZ,
            double cX, double cY, double cZ,
            double pX, double pY, double pZ, Double3 res) {
        return IntersectionGend.findClosestPointOnRectangle(aX, aY, aZ, bX, bY, bZ, cX, cY, cZ, pX, pY, pZ, res);
    }

    /**
     * Determine the point of intersection between a sphere with the given center <code>(centerX, centerY, centerZ)</code> and <code>radius</code> moving
     * with the given velocity <code>(velX, velY, velZ)</code> and the triangle specified via its three vertices <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code>, <code>(v2X, v2Y, v2Z)</code>.
     * <p>
     * Contact with the triangle's face is detected from either side of the triangle, so the winding order of the vertices does not matter.
     * <p>
     * An intersection is only considered if the time of intersection is smaller than the given <code>maxT</code> value.
     * <p>
     * A sphere that already touches the triangle at <i>t</i> = 0 is reported as colliding at <i>t</i> = 0, whichever way it moves: the point is the
     * triangle's point closest to the sphere's center, and the result the code of the face, edge or vertex that point lies on.
     * <p>
     * A sphere moving (almost) parallel to the triangle's plane, i.e. with <code>|velocity &middot; normal| &lt; epsilon</code> for the unit normal,
     * is otherwise reported as not intersecting even when its path would clip an edge or a vertex: the edge and vertex sweeps are only run for a
     * sphere that crosses the plane.
     * <p>
     * Reference: <a href="http://www.peroxide.dk/papers/collision/collision.pdf">Improved Collision detection and Response</a>
     * <p>
     * Valid input: {@code radius} must not be negative; {@code epsilon} must not be negative.
     * 
     * @param centerX
     *              the x coordinate of the sphere's center
     * @param centerY
     *              the y coordinate of the sphere's center
     * @param centerZ
     *              the z coordinate of the sphere's center
     * @param radius
     *              the radius of the sphere
     * @param velX
     *              the x component of the velocity of the sphere
     * @param velY
     *              the y component of the velocity of the sphere
     * @param velZ
     *              the z component of the velocity of the sphere
     * @param v0X
     *              the x coordinate of the first triangle vertex
     * @param v0Y
     *              the y coordinate of the first triangle vertex
     * @param v0Z
     *              the z coordinate of the first triangle vertex
     * @param v1X
     *              the x coordinate of the second triangle vertex
     * @param v1Y
     *              the y coordinate of the second triangle vertex
     * @param v1Z
     *              the z coordinate of the second triangle vertex
     * @param v2X
     *              the x coordinate of the third triangle vertex
     * @param v2Y
     *              the y coordinate of the third triangle vertex
     * @param v2Z
     *              the z coordinate of the third triangle vertex
     * @param epsilon
     *              a small epsilon when testing spheres that move almost parallel to the triangle
     * @param maxT
     *              the maximum intersection time
     * @param pointAndTime
     *              iff the moving sphere and the triangle intersect, this will hold the point of intersection in the <code>(x, y, z)</code> components
     *              and the time of intersection in the <code>w</code> component
     * @return {@link #POINT_ON_TRIANGLE_FACE} if the intersection point lies on the triangle's face,
     *         or {@link #POINT_ON_TRIANGLE_VERTEX_0}, {@link #POINT_ON_TRIANGLE_VERTEX_1} or {@link #POINT_ON_TRIANGLE_VERTEX_2} if the intersection point is a vertex,
     *         or {@link #POINT_ON_TRIANGLE_EDGE_01}, {@link #POINT_ON_TRIANGLE_EDGE_12} or {@link #POINT_ON_TRIANGLE_EDGE_20} if the intersection point lies on an edge;
     *         or <code>0</code> if no intersection
     */
    public static int intersectSweptSphereTriangle(
            double centerX, double centerY, double centerZ, double radius, double velX, double velY, double velZ,
            double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z,
            double epsilon, double maxT, Double4 pointAndTime) {
        double v10X = v1X - v0X;
        double v10Y = v1Y - v0Y;
        double v10Z = v1Z - v0Z;
        double v20X = v2X - v0X;
        double v20Y = v2Y - v0Y;
        double v20Z = v2Z - v0Z;
        // build triangle plane
        double a = v10Y * v20Z - v20Y * v10Z;
        double b = v10Z * v20X - v20Z * v10X;
        double c = v10X * v20Y - v20X * v10Y;
        double d = -(a * v0X + b * v0Y + c * v0Z);
        double invLen = (1.0 / Math.sqrt(a * a + b * b + c * c));
        double signedDist = (a * centerX + b * centerY + c * centerZ + d) * invLen;
        // A sphere that already touches the triangle at t = 0 collides at t = 0, at the
        // triangle's closest point - whichever way it moves. The sweeps below assume a sphere
        // that starts clear of the triangle: for one that overlaps it they found no face
        // contact (the plane is entered before t = 0) and, for a vertex or an edge, the time
        // the contact ENDS (the lowest positive root of an already negative distance). Only a
        // sphere reaching the plane can touch the triangle, which keeps this off the common path.
        // (Written as !(> radius): a zero-area triangle has no plane, signedDist is NaN, and the
        // closest-point test below is then the only check that can see the overlap.)
        if (maxT >= 0.0 && !(Math.abs(signedDist) > radius)) {
            Double3 closest = Joml.double3();
            int feature = findClosestPointOnTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z,
                    centerX, centerY, centerZ, closest);
            double dx = closest.x() - centerX, dy = closest.y() - centerY, dz = closest.z() - centerZ;
            if (dx * dx + dy * dy + dz * dz <= radius * radius) {
                pointAndTime.set(closest.x(), closest.y(), closest.z(), 0.0);
                return feature;
            }
        }
        double dot = (a * velX + b * velY + c * velZ) * invLen;
        if (dot < epsilon && dot > -epsilon)
            return 0;
        double pt0 = (radius - signedDist) / dot;
        double pt1 = (-radius - signedDist) / dot;
        // The sphere overlaps the triangle's plane exactly while t lies between
        // pt0 and pt1 (which is the earlier of the two depends on the sign of
        // `dot`), so no contact of any kind - face, edge or vertex - can occur
        // outside that window. A window entirely outside [0, maxT] means no
        // collision. The `tExit < 0` half also stops the face branch below from
        // reporting a collision that has already happened: without it, a sphere
        // receding from a triangle behind it returned POINT_ON_TRIANGLE_FACE at
        // a negative time.
        double tEnter = Math.min(pt0, pt1), tExit = Math.max(pt0, pt1);
        if (tEnter > maxT || tExit < 0.0)
            return 0;
        // The plane is reached first by the point of the sphere that faces it:
        // centre - radius*n when the centre is on the normal's side, centre +
        // radius*n when it is behind. That point meets the plane at tEnter, the
        // earlier of pt0/pt1, whichever side the sphere comes from - the old
        // `pt0` was that moment only for a sphere in front of the triangle; from
        // behind it named the exit of the trailing point, so a back-side
        // approach missed the face and fell through to a later edge contact.
        double lead = signedDist >= 0.0 ? -radius : radius;
        double p0X = centerX + lead * a * invLen + velX * tEnter;
        double p0Y = centerY + lead * b * invLen + velY * tEnter;
        double p0Z = centerZ + lead * c * invLen + velZ * tEnter;
        // tEnter is a candidate only while it is inside the requested time window
        // (a sphere overlapping the plane but not the triangle has tEnter < 0: it can only
        // reach the triangle through an edge or a vertex).
        boolean insideTriangle = tEnter >= 0.0 && tEnter <= maxT
                && testPointInTriangle(p0X, p0Y, p0Z, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z);
        if (insideTriangle) {
            pointAndTime.set(p0X, p0Y, p0Z, tEnter);
            return POINT_ON_TRIANGLE_FACE;
        }
        int isect = 0;
        double t0 = maxT;
        double A = velX * velX + velY * velY + velZ * velZ;
        double radius2 = radius * radius;
        // test against v0
        double centerV0X = centerX - v0X;
        double centerV0Y = centerY - v0Y;
        double centerV0Z = centerZ - v0Z;
        double B0 = 2.0 * (velX * centerV0X + velY * centerV0Y + velZ * centerV0Z);
        double C0 = centerV0X * centerV0X + centerV0Y * centerV0Y + centerV0Z * centerV0Z - radius2;
        double root0 = computeLowestRoot(A, B0, C0, t0);
        if (root0 < t0) {
            pointAndTime.set(v0X, v0Y, v0Z, root0);
            t0 = root0;
            isect = POINT_ON_TRIANGLE_VERTEX_0;
        }
        // test against v1
        double centerV1X = centerX - v1X;
        double centerV1Y = centerY - v1Y;
        double centerV1Z = centerZ - v1Z;
        double centerV1Len = centerV1X * centerV1X + centerV1Y * centerV1Y + centerV1Z * centerV1Z;
        double B1 = 2.0 * (velX * centerV1X + velY * centerV1Y + velZ * centerV1Z);
        double C1 = centerV1Len - radius2;
        double root1 = computeLowestRoot(A, B1, C1, t0);
        if (root1 < t0) {
            pointAndTime.set(v1X, v1Y, v1Z, root1);
            t0 = root1;
            isect = POINT_ON_TRIANGLE_VERTEX_1;
        }
        // test against v2
        double centerV2X = centerX - v2X;
        double centerV2Y = centerY - v2Y;
        double centerV2Z = centerZ - v2Z;
        double B2 = 2.0 * (velX * centerV2X + velY * centerV2Y + velZ * centerV2Z);
        double C2 = centerV2X * centerV2X + centerV2Y * centerV2Y + centerV2Z * centerV2Z - radius2;
        double root2 = computeLowestRoot(A, B2, C2, t0);
        if (root2 < t0) {
            pointAndTime.set(v2X, v2Y, v2Z, root2);
            t0 = root2;
            isect = POINT_ON_TRIANGLE_VERTEX_2;
        }
        double velLen = velX * velX + velY * velY + velZ * velZ;
        // test against edge10
        double len10 = v10X * v10X + v10Y * v10Y + v10Z * v10Z;
        double baseTo0Len = centerV0X * centerV0X + centerV0Y * centerV0Y + centerV0Z * centerV0Z;
        double v10Vel = (v10X * velX + v10Y * velY + v10Z * velZ);
        double A10 = len10 * -velLen + v10Vel * v10Vel;
        double v10BaseTo0 = v10X * -centerV0X + v10Y * -centerV0Y + v10Z * -centerV0Z;
        double velBaseTo0 = velX * -centerV0X + velY * -centerV0Y + velZ * -centerV0Z;
        double B10 = len10 * 2 * velBaseTo0 - 2 * v10Vel * v10BaseTo0;
        double C10 = len10 * (radius2 - baseTo0Len) + v10BaseTo0 * v10BaseTo0;
        double root10 = computeLowestRoot(A10, B10, C10, t0);
        double f10 = (v10Vel * root10 - v10BaseTo0) / len10;
        if (f10 >= 0.0 && f10 <= 1.0 && root10 < t0) {
            pointAndTime.set(v0X + f10 * v10X, v0Y + f10 * v10Y, v0Z + f10 * v10Z, root10);
            t0 = root10;
            isect = POINT_ON_TRIANGLE_EDGE_01;
        }
        // test against edge20
        double len20 = v20X * v20X + v20Y * v20Y + v20Z * v20Z;
        double v20Vel = (v20X * velX + v20Y * velY + v20Z * velZ);
        double A20 = len20 * -velLen + v20Vel * v20Vel;
        double v20BaseTo0 = v20X * -centerV0X + v20Y * -centerV0Y + v20Z * -centerV0Z;
        double B20 = len20 * 2 * velBaseTo0 - 2 * v20Vel * v20BaseTo0;
        double C20 = len20 * (radius2 - baseTo0Len) + v20BaseTo0 * v20BaseTo0;
        double root20 = computeLowestRoot(A20, B20, C20, t0);
        double f20 = (v20Vel * root20 - v20BaseTo0) / len20;
        if (f20 >= 0.0 && f20 <= 1.0 && root20 < t0) {
            pointAndTime.set(v0X + f20 * v20X, v0Y + f20 * v20Y, v0Z + f20 * v20Z, root20);
            t0 = root20;
            isect = POINT_ON_TRIANGLE_EDGE_20;
        }
        // test against edge21
        double v21X = v2X - v1X;
        double v21Y = v2Y - v1Y;
        double v21Z = v2Z - v1Z;
        double len21 = v21X * v21X + v21Y * v21Y + v21Z * v21Z;
        double baseTo1Len = centerV1Len;
        double v21Vel = (v21X * velX + v21Y * velY + v21Z * velZ);
        double A21 = len21 * -velLen + v21Vel * v21Vel;
        double v21BaseTo1 = v21X * -centerV1X + v21Y * -centerV1Y + v21Z * -centerV1Z;
        double velBaseTo1 = velX * -centerV1X + velY * -centerV1Y + velZ * -centerV1Z;
        double B21 = len21 * 2 * velBaseTo1 - 2 * v21Vel * v21BaseTo1;
        double C21 = len21 * (radius2 - baseTo1Len) + v21BaseTo1 * v21BaseTo1;
        double root21 = computeLowestRoot(A21, B21, C21, t0);
        double f21 = (v21Vel * root21 - v21BaseTo1) / len21;
        if (f21 >= 0.0 && f21 <= 1.0 && root21 < t0) {
            pointAndTime.set(v1X + f21 * v21X, v1Y + f21 * v21Y, v1Z + f21 * v21Z, root21);
            t0 = root21;
            isect = POINT_ON_TRIANGLE_EDGE_12;
        }
        return isect;
    }

    /**
     * Compute the lowest root for <code>t</code> in the quadratic equation <code>a*t*t + b*t + c = 0</code>.
     * <p>
     * This is a helper method for {@link #intersectSweptSphereTriangle}
     * 
     * @param a
     *              the quadratic factor
     * @param b
     *              the linear factor
     * @param c
     *              the constant
     * @param maxR
     *              the maximum expected root
     * @return the lowest of the two roots of the quadratic equation; or {@link Double#POSITIVE_INFINITY}
     */
    private static double computeLowestRoot(double a, double b, double c, double maxR) {
        return IntersectionGend.computeLowestRoot(a, b, c, maxR);
    }

    /**
     * Test whether the projection of the given point <code>(pX, pY, pZ)</code> lies inside of the triangle defined by the three vertices
     * <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code> and <code>(v2X, v2Y, v2Z)</code>.
     * <p>
     * Reference: <a href="http://www.peroxide.dk/papers/collision/collision.pdf">Improved Collision detection and Response</a>
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param pX
     *              the x coordinate of the point to test
     * @param pY
     *              the y coordinate of the point to test
     * @param pZ
     *              the z coordinate of the point to test
     * @param v0X
     *              the x coordinate of the first vertex
     * @param v0Y
     *              the y coordinate of the first vertex
     * @param v0Z
     *              the z coordinate of the first vertex
     * @param v1X
     *              the x coordinate of the second vertex
     * @param v1Y
     *              the y coordinate of the second vertex
     * @param v1Z
     *              the z coordinate of the second vertex
     * @param v2X
     *              the x coordinate of the third vertex
     * @param v2Y
     *              the y coordinate of the third vertex
     * @param v2Z
     *              the z coordinate of the third vertex
     * @return <code>true</code> if the projection of the given point lies inside of the given triangle; <code>false</code> otherwise
     */
    public static boolean testPointInTriangle(double pX, double pY, double pZ, double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z) {
        return IntersectionGend.testPointInTriangle(pX, pY, pZ, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * Test whether the given ray with the origin <code>(originX, originY, originZ)</code> and normalized direction <code>(dirX, dirY, dirZ)</code>
     * intersects the given sphere with center <code>(centerX, centerY, centerZ)</code> and square radius <code>radiusSquared</code>,
     * and store the values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> for both points (near
     * and far) of intersections into the given <code>result</code> vector.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the sphere.
     * <p>
     * Reference: <a href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must have unit length; {@code radiusSquared} must not
     * be negative.
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param originZ
     *              the z coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's normalized direction
     * @param dirY
     *              the y coordinate of the ray's normalized direction
     * @param dirZ
     *              the z coordinate of the ray's normalized direction
     * @param centerX
     *              the x coordinate of the sphere's center
     * @param centerY
     *              the y coordinate of the sphere's center
     * @param centerZ
     *              the z coordinate of the sphere's center
     * @param radiusSquared
     *              the sphere radius squared
     * @param result
     *              a vector that will contain the values of the parameter <i>t</i> in the ray equation
     *              <i>p(t) = origin + t * dir</i> for both points (near, far) of intersections with the sphere
     * @return <code>true</code> if the ray intersects the sphere; <code>false</code> otherwise
     */
    public static boolean intersectRaySphere(double originX, double originY, double originZ, double dirX, double dirY, double dirZ,
            double centerX, double centerY, double centerZ, double radiusSquared, Double2 result) {
        return IntersectionGend.intersectRaySphere(originX, originY, originZ, dirX, dirY, dirZ, centerX, centerY, centerZ, radiusSquared, result);
    }

    /**
     * Test whether the ray with the given <code>origin</code> and normalized direction <code>dir</code>
     * intersects the sphere with the given <code>center</code> and square radius <code>radiusSquared</code>,
     * and store the values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> for both points (near
     * and far) of intersections into the given <code>result</code> vector.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the sphere.
     * <p>
     * Reference: <a href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code dir} must have unit length; {@code radiusSquared} must not be negative.
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's normalized direction
     * @param center
     *              the sphere's center
     * @param radiusSquared
     *              the sphere radius squared
     * @param result
     *              a vector that will contain the values of the parameter <i>t</i> in the ray equation
     *              <i>p(t) = origin + t * dir</i> for both points (near, far) of intersections with the sphere
     * @return <code>true</code> if the ray intersects the sphere; <code>false</code> otherwise
     */
    public static boolean intersectRaySphere(Double3R origin, Double3R dir, Double3R center, double radiusSquared, Double2 result) {
        return intersectRaySphere(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), center.x(), center.y(), center.z(), radiusSquared, result);
    }

    /**
     * Test whether the given ray intersects the given sphere,
     * and store the values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> for both points (near
     * and far) of intersections into the given <code>result</code> vector.
     * <p>
     * The ray's direction must be normalized.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the sphere.
     * <p>
     * Reference: <a href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: the direction of {@code ray} must have unit length.
     * 
     * @param ray
     *              the ray (its direction must be normalized)
     * @param sphere
     *              the sphere
     * @param result
     *              a vector that will contain the values of the parameter <i>t</i> in the ray equation
     *              <i>p(t) = origin + t * dir</i> for both points (near, far) of intersections with the sphere
     * @return <code>true</code> if the ray intersects the sphere; <code>false</code> otherwise
     */
    public static boolean intersectRaySphere(DoubleRayR ray, DoubleSphereR sphere, Double2 result) {
        return intersectRaySphere(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), sphere.x(), sphere.y(), sphere.z(), sphere.r()*sphere.r(), result);
    }

    /**
     * Test whether the given ray with the origin <code>(originX, originY, originZ)</code> and normalized direction <code>(dirX, dirY, dirZ)</code>
     * intersects the given sphere with center <code>(centerX, centerY, centerZ)</code> and square radius <code>radiusSquared</code>.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the sphere.
     * <p>
     * Reference: <a href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must have unit length; {@code radiusSquared} must not
     * be negative.
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param originZ
     *              the z coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's normalized direction
     * @param dirY
     *              the y coordinate of the ray's normalized direction
     * @param dirZ
     *              the z coordinate of the ray's normalized direction
     * @param centerX
     *              the x coordinate of the sphere's center
     * @param centerY
     *              the y coordinate of the sphere's center
     * @param centerZ
     *              the z coordinate of the sphere's center
     * @param radiusSquared
     *              the sphere radius squared
     * @return <code>true</code> if the ray intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testRaySphere(double originX, double originY, double originZ, double dirX, double dirY, double dirZ,
            double centerX, double centerY, double centerZ, double radiusSquared) {
        return IntersectionGend.testRaySphere(originX, originY, originZ, dirX, dirY, dirZ,
                centerX, centerY, centerZ, radiusSquared);
    }

    /**
     * Test whether the ray with the given <code>origin</code> and normalized direction <code>dir</code>
     * intersects the sphere with the given <code>center</code> and square radius.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the sphere.
     * <p>
     * Reference: <a href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code dir} must have unit length; {@code radiusSquared} must not be negative.
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's normalized direction
     * @param center
     *              the sphere's center
     * @param radiusSquared
     *              the sphere radius squared
     * @return <code>true</code> if the ray intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testRaySphere(Double3R origin, Double3R dir, Double3R center, double radiusSquared) {
        return testRaySphere(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), center.x(), center.y(), center.z(), radiusSquared);
    }

    /**
     * Test whether the given ray intersects the given sphere.
     * <p>
     * The ray's direction must be normalized.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the sphere.
     * <p>
     * Reference: <a href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: the direction of {@code ray} must have unit length.
     * 
     * @param ray
     *              the ray (its direction must be normalized)
     * @param sphere
     *              the sphere
     * @return <code>true</code> if the ray intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testRaySphere(DoubleRayR ray, DoubleSphereR sphere) {
        return testRaySphere(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), sphere.x(), sphere.y(), sphere.z(), sphere.r()*sphere.r());
    }

    /**
     * Test whether the line segment with the end points <code>(p0X, p0Y, p0Z)</code> and <code>(p1X, p1Y, p1Z)</code>
     * intersects the given sphere with center <code>(centerX, centerY, centerZ)</code> and square radius <code>radiusSquared</code>.
     * <p>
     * Reference: <a href="http://paulbourke.net/geometry/circlesphere/index.html#linesphere">http://paulbourke.net/</a>
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     * 
     * @param p0X
     *              the x coordinate of the line segment's first end point
     * @param p0Y
     *              the y coordinate of the line segment's first end point
     * @param p0Z
     *              the z coordinate of the line segment's first end point
     * @param p1X
     *              the x coordinate of the line segment's second end point
     * @param p1Y
     *              the y coordinate of the line segment's second end point
     * @param p1Z
     *              the z coordinate of the line segment's second end point
     * @param centerX
     *              the x coordinate of the sphere's center
     * @param centerY
     *              the y coordinate of the sphere's center
     * @param centerZ
     *              the z coordinate of the sphere's center
     * @param radiusSquared
     *              the sphere radius squared
     * @return <code>true</code> if the line segment intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testLineSegmentSphere(double p0X, double p0Y, double p0Z, double p1X, double p1Y, double p1Z,
            double centerX, double centerY, double centerZ, double radiusSquared) {
        return IntersectionGend.testLineSegmentSphere(p0X, p0Y, p0Z, p1X, p1Y, p1Z, centerX, centerY, centerZ, radiusSquared);
    }

    /**
     * Test whether the line segment with the end points <code>p0</code> and <code>p1</code>
     * intersects the given sphere with center <code>center</code> and square radius <code>radiusSquared</code>.
     * <p>
     * Reference: <a href="http://paulbourke.net/geometry/circlesphere/index.html#linesphere">http://paulbourke.net/</a>
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     * 
     * @param p0
     *              the line segment's first end point
     * @param p1
     *              the line segment's second end point
     * @param center
     *              the sphere's center
     * @param radiusSquared
     *              the sphere radius squared
     * @return <code>true</code> if the line segment intersects the sphere; <code>false</code> otherwise
     */
    public static boolean testLineSegmentSphere(Double3R p0, Double3R p1, Double3R center, double radiusSquared) {
        return testLineSegmentSphere(p0.x(), p0.y(), p0.z(), p1.x(), p1.y(), p1.z(), center.x(), center.y(), center.z(), radiusSquared);
    }

    /**
     * Test whether the given ray with the origin <code>(originX, originY, originZ)</code> and direction <code>(dirX, dirY, dirZ)</code>
     * intersects the axis-aligned box given as its minimum corner <code>(minX, minY, minZ)</code> and maximum corner <code>(maxX, maxY, maxZ)</code>,
     * and return the values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the near and far point of intersection.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the axis-aligned box.
     * <p>
     * If many boxes need to be tested against the same ray, then hoisting the reciprocal of the ray direction out of the loop is likely more efficient.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must be non-zero; {@code (minX, minY, minZ)} must not
     * exceed {@code (maxX, maxY, maxZ)} in any component.
     * 
     * @see #intersectRayAabb(Double3R, Double3R, Double3R, Double3R, Double2)
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param originZ
     *              the z coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param dirZ
     *              the z coordinate of the ray's direction
     * @param minX
     *              the x coordinate of the minimum corner of the axis-aligned box
     * @param minY
     *              the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ
     *              the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX
     *              the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY
     *              the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ
     *              the z coordinate of the maximum corner of the axis-aligned box
     * @param result
     *              a vector which will hold the resulting values of the parameter
     *              <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the near and far point of intersection
     *              iff the ray intersects the axis-aligned box
     * @return <code>true</code> if the given ray intersects the axis-aligned box; <code>false</code> otherwise
     */
    public static boolean intersectRayAabb(double originX, double originY, double originZ, double dirX, double dirY, double dirZ,
            double minX, double minY, double minZ, double maxX, double maxY, double maxZ, Double2 result) {
        return IntersectionGend.intersectRayAabb(originX, originY, originZ, dirX, dirY, dirZ, minX, minY, minZ, maxX, maxY, maxZ, result);
    }

    /**
     * Test whether the ray with the given <code>origin</code> and direction <code>dir</code>
     * intersects the axis-aligned box specified as its minimum corner <code>min</code> and maximum corner <code>max</code>,
     * and return the values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the near and far point of intersection..
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the axis-aligned box.
     * <p>
     * If many boxes need to be tested against the same ray, then hoisting the reciprocal of the ray direction out of the loop is likely more efficient.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code min} must not exceed {@code max} in any
     * component.
     * 
     * @see #intersectRayAabb(double, double, double, double, double, double, double, double, double, double, double, double, Double2)
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param min
     *              the minimum corner of the axis-aligned box
     * @param max
     *              the maximum corner of the axis-aligned box
     * @param result
     *              a vector which will hold the resulting values of the parameter
     *              <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the near and far point of intersection
     *              iff the ray intersects the axis-aligned box
     * @return <code>true</code> if the given ray intersects the axis-aligned box; <code>false</code> otherwise
     */
    public static boolean intersectRayAabb(Double3R origin, Double3R dir, Double3R min, Double3R max, Double2 result) {
        return intersectRayAabb(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), result);
    }

    /**
     * Test whether the given ray intersects given the axis-aligned box
     * and return the values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the near and far point of intersection..
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the axis-aligned box.
     * <p>
     * If many boxes need to be tested against the same ray, then hoisting the reciprocal of the ray direction out of the loop is likely more efficient.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: the direction of {@code ray} must be non-zero; the minimum corner of {@code
     * aabb} must not exceed the maximum corner of {@code aabb} in any component.
     * 
     * @see #intersectRayAabb(double, double, double, double, double, double, double, double, double, double, double, double, Double2)
     * 
     * @param ray
     *              the ray
     * @param aabb
     *              the AABB
     * @param result
     *              a vector which will hold the resulting values of the parameter
     *              <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the near and far point of intersection
     *              iff the ray intersects the axis-aligned box
     * @return <code>true</code> if the given ray intersects the axis-aligned box; <code>false</code> otherwise
     */
    public static boolean intersectRayAabb(DoubleRayR ray, DoubleAABBR aabb, Double2 result) {
        return intersectRayAabb(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), aabb.minX(), aabb.minY(), aabb.minZ(), aabb.maxX(), aabb.maxY(), aabb.maxZ(), result);
    }

    /**
     * Determine whether the undirected line segment with the end points <code>(p0X, p0Y, p0Z)</code> and <code>(p1X, p1Y, p1Z)</code>
     * intersects the axis-aligned box given as its minimum corner <code>(minX, minY, minZ)</code> and maximum corner <code>(maxX, maxY, maxZ)</code>,
     * and return the values of the parameter <i>t</i> in the ray equation <i>p(t) = p0 + t * (p1 - p0)</i> of the near and far point of intersection.
     * <p>
     * This method returns {@link #ONE_INTERSECTION} for a line segment with exactly one end point inside the axis-aligned box.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component.
     * 
     * @see #intersectLineSegmentAabb(Double3R, Double3R, Double3R, Double3R, Double2)
     * 
     * @param p0X
     *              the x coordinate of the line segment's first end point
     * @param p0Y
     *              the y coordinate of the line segment's first end point
     * @param p0Z
     *              the z coordinate of the line segment's first end point
     * @param p1X
     *              the x coordinate of the line segment's second end point
     * @param p1Y
     *              the y coordinate of the line segment's second end point
     * @param p1Z
     *              the z coordinate of the line segment's second end point
     * @param minX
     *              the x coordinate of the minimum corner of the axis-aligned box
     * @param minY
     *              the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ
     *              the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX
     *              the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY
     *              the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ
     *              the z coordinate of the maximum corner of the axis-aligned box
     * @param result
     *              a vector which will hold the resulting values of the parameter
     *              <i>t</i> in the ray equation <i>p(t) = p0 + t * (p1 - p0)</i> of the near and far point of intersection
     *              iff the line segment intersects the axis-aligned box
     * @return {@link #INSIDE} if the line segment lies completely inside of the axis-aligned box; or
     *         {@link #OUTSIDE} if the line segment lies completely outside of the axis-aligned box; or
     *         {@link #ONE_INTERSECTION} if one of the end points of the line segment lies inside of the axis-aligned box; or
     *         {@link #TWO_INTERSECTION} if the line segment intersects two sides of the axis-aligned box
     *         or lies on an edge or a side of the box
     */
    public static int intersectLineSegmentAabb(double p0X, double p0Y, double p0Z, double p1X, double p1Y, double p1Z,
            double minX, double minY, double minZ, double maxX, double maxY, double maxZ, Double2 result) {
        return IntersectionGend.intersectLineSegmentAabb(p0X, p0Y, p0Z, p1X, p1Y, p1Z,
                minX, minY, minZ, maxX, maxY, maxZ, result);
    }

    /**
     * Determine whether the undirected line segment with the end points <code>p0</code> and <code>p1</code>
     * intersects the axis-aligned box given as its minimum corner <code>min</code> and maximum corner <code>max</code>,
     * and return the values of the parameter <i>t</i> in the ray equation <i>p(t) = p0 + t * (p1 - p0)</i> of the near and far point of intersection.
     * <p>
     * This method returns {@link #ONE_INTERSECTION} for a line segment with exactly one end point inside the axis-aligned box.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     * 
     * @see #intersectLineSegmentAabb(Double3R, Double3R, Double3R, Double3R, Double2)
     * 
     * @param p0
     *              the line segment's first end point
     * @param p1
     *              the line segment's second end point
     * @param min
     *              the minimum corner of the axis-aligned box
     * @param max
     *              the maximum corner of the axis-aligned box
     * @param result
     *              a vector which will hold the resulting values of the parameter
     *              <i>t</i> in the ray equation <i>p(t) = p0 + t * (p1 - p0)</i> of the near and far point of intersection
     *              iff the line segment intersects the axis-aligned box
     * @return {@link #INSIDE} if the line segment lies completely inside of the axis-aligned box; or
     *         {@link #OUTSIDE} if the line segment lies completely outside of the axis-aligned box; or
     *         {@link #ONE_INTERSECTION} if one of the end points of the line segment lies inside of the axis-aligned box; or
     *         {@link #TWO_INTERSECTION} if the line segment intersects two sides of the axis-aligned box
     *         or lies on an edge or a side of the box
     */
    public static int intersectLineSegmentAabb(Double3R p0, Double3R p1, Double3R min, Double3R max, Double2 result) {
        return intersectLineSegmentAabb(p0.x(), p0.y(), p0.z(), p1.x(), p1.y(), p1.z(), min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), result);
    }


    /**
     * Test whether the given ray with the origin <code>(originX, originY, originZ)</code> and direction <code>(dirX, dirY, dirZ)</code>
     * intersects the axis-aligned box given as its minimum corner <code>(minX, minY, minZ)</code> and maximum corner <code>(maxX, maxY, maxZ)</code>.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the axis-aligned box.
     * <p>
     * If many boxes need to be tested against the same ray, then hoisting the reciprocal of the ray direction out of the loop is likely more efficient.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must be non-zero; {@code (minX, minY, minZ)} must not
     * exceed {@code (maxX, maxY, maxZ)} in any component.
     * 
     * @see #testRayAabb(Double3R, Double3R, Double3R, Double3R)
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param originZ
     *              the z coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param dirZ
     *              the z coordinate of the ray's direction
     * @param minX
     *              the x coordinate of the minimum corner of the axis-aligned box
     * @param minY
     *              the y coordinate of the minimum corner of the axis-aligned box
     * @param minZ
     *              the z coordinate of the minimum corner of the axis-aligned box
     * @param maxX
     *              the x coordinate of the maximum corner of the axis-aligned box
     * @param maxY
     *              the y coordinate of the maximum corner of the axis-aligned box
     * @param maxZ
     *              the z coordinate of the maximum corner of the axis-aligned box
     * @return <code>true</code> if the given ray intersects the axis-aligned box; <code>false</code> otherwise
     */
    public static boolean testRayAabb(double originX, double originY, double originZ, double dirX, double dirY, double dirZ,
            double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return IntersectionGend.testRayAabb(originX, originY, originZ, dirX, dirY, dirZ, minX, minY, minZ, maxX, maxY, maxZ);
    }

    /**
     * Test whether the ray with the given <code>origin</code> and direction <code>dir</code>
     * intersects the axis-aligned box specified as its minimum corner <code>min</code> and maximum corner <code>max</code>.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the axis-aligned box.
     * <p>
     * If many boxes need to be tested against the same ray, then hoisting the reciprocal of the ray direction out of the loop is likely more efficient.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code min} must not exceed {@code max} in any
     * component.
     *  
     * @see #testRayAabb(double, double, double, double, double, double, double, double, double, double, double, double)
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param min
     *              the minimum corner of the axis-aligned box
     * @param max
     *              the maximum corner of the axis-aligned box
     * @return <code>true</code> if the given ray intersects the axis-aligned box; <code>false</code> otherwise
     */
    public static boolean testRayAabb(Double3R origin, Double3R dir, Double3R min, Double3R max) {
        return testRayAabb(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
    }

    /**
     * Test whether the given ray intersects the given axis-aligned box.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the axis-aligned box.
     * <p>
     * If many boxes need to be tested against the same ray, then hoisting the reciprocal of the ray direction out of the loop is likely more efficient.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: the direction of {@code ray} must be non-zero; the minimum corner of {@code
     * aabb} must not exceed the maximum corner of {@code aabb} in any component.
     *  
     * @see #testRayAabb(double, double, double, double, double, double, double, double, double, double, double, double)
     * 
     * @param ray
     *              the ray
     * @param aabb
     *              the AABB
     * @return <code>true</code> if the given ray intersects the axis-aligned box; <code>false</code> otherwise
     */
    public static boolean testRayAabb(DoubleRayR ray, DoubleAABBR aabb) {
        return testRayAabb(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), aabb.minX(), aabb.minY(), aabb.minZ(), aabb.maxX(), aabb.maxY(), aabb.maxZ());
    }

    /**
     * Test whether the given ray with the origin <code>(originX, originY, originZ)</code> and direction <code>(dirX, dirY, dirZ)</code>
     * intersects the frontface of the triangle consisting of the three vertices <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code> and <code>(v2X, v2Y, v2Z)</code>.
     * <p>
     * This is an implementation of the <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a> method.
     * <p>
     * This test implements backface culling, that is, it will return <code>false</code> when the triangle is in clockwise
     * winding order assuming a <i>right-handed</i> coordinate system when seen along the ray's direction, even if the ray intersects the triangle.
     * This is in compliance with how OpenGL handles backface culling with default frontface/backface settings.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     * 
     * @see #testRayTriangleFront(Double3R, Double3R, Double3R, Double3R, Double3R, double)
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param originZ
     *              the z coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param dirZ
     *              the z coordinate of the ray's direction
     * @param v0X
     *              the x coordinate of the first vertex
     * @param v0Y
     *              the y coordinate of the first vertex
     * @param v0Z
     *              the z coordinate of the first vertex
     * @param v1X
     *              the x coordinate of the second vertex
     * @param v1Y
     *              the y coordinate of the second vertex
     * @param v1Z
     *              the z coordinate of the second vertex
     * @param v2X
     *              the x coordinate of the third vertex
     * @param v2Y
     *              the y coordinate of the third vertex
     * @param v2Z
     *              the z coordinate of the third vertex
     * @param epsilon
     *              a small epsilon, used both as the smallest accepted determinant of the intersection system and as the smallest accepted value of the ray parameter <i>t</i>. The determinant is not normalized: it scales with the length of the direction and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @return <code>true</code> if the given ray intersects the frontface of the triangle; <code>false</code> otherwise
     */
    public static boolean testRayTriangleFront(double originX, double originY, double originZ, double dirX, double dirY, double dirZ,
            double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z,
            double epsilon) {
        return IntersectionGend.testRayTriangleFront(originX, originY, originZ, dirX, dirY, dirZ, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, epsilon);
    }

    /**
     * Test whether the ray with the given <code>origin</code> and the given <code>dir</code> intersects the frontface of the triangle consisting of the three vertices
     * <code>v0</code>, <code>v1</code> and <code>v2</code>.
     * <p>
     * This is an implementation of the <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a> method.
     * <p>
     * This test implements backface culling, that is, it will return <code>false</code> when the triangle is in clockwise
     * winding order assuming a <i>right-handed</i> coordinate system when seen along the ray's direction, even if the ray intersects the triangle.
     * This is in compliance with how OpenGL handles backface culling with default frontface/backface settings.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     * 
     * @see #testRayTriangleFront(double, double, double, double, double, double, double, double, double, double, double, double, double, double, double, double)
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param v0
     *              the position of the first vertex
     * @param v1
     *              the position of the second vertex
     * @param v2
     *              the position of the third vertex
     * @param epsilon
     *              a small epsilon, used both as the smallest accepted determinant of the intersection system and as the smallest accepted value of the ray parameter <i>t</i>. The determinant is not normalized: it scales with the length of the direction and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @return <code>true</code> if the given ray intersects the frontface of the triangle; <code>false</code> otherwise
     */
    public static boolean testRayTriangleFront(Double3R origin, Double3R dir, Double3R v0, Double3R v1, Double3R v2, double epsilon) {
        return testRayTriangleFront(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /**
     * Test whether the given ray with the origin <code>(originX, originY, originZ)</code> and direction <code>(dirX, dirY, dirZ)</code>
     * intersects the triangle consisting of the three vertices <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code> and <code>(v2X, v2Y, v2Z)</code>.
     * <p>
     * This is an implementation of the <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a> method.
     * <p>
     * This test does not take into account the winding order of the triangle, so a ray will intersect a front-facing triangle as well as a back-facing triangle.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     * 
     * @see #testRayTriangle(Double3R, Double3R, Double3R, Double3R, Double3R, double)
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param originZ
     *              the z coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param dirZ
     *              the z coordinate of the ray's direction
     * @param v0X
     *              the x coordinate of the first vertex
     * @param v0Y
     *              the y coordinate of the first vertex
     * @param v0Z
     *              the z coordinate of the first vertex
     * @param v1X
     *              the x coordinate of the second vertex
     * @param v1Y
     *              the y coordinate of the second vertex
     * @param v1Z
     *              the z coordinate of the second vertex
     * @param v2X
     *              the x coordinate of the third vertex
     * @param v2Y
     *              the y coordinate of the third vertex
     * @param v2Z
     *              the z coordinate of the third vertex
     * @param epsilon
     *              a small positive tolerance, used twice: the ray counts as parallel to the triangle's plane when the absolute determinant of the intersection system falls below it, and it is also the smallest value of the ray parameter <i>t</i> accepted as a hit. The determinant is not normalized: it scales with the length of the direction and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @return <code>true</code> if the given ray intersects the triangle; <code>false</code> otherwise
     */
    public static boolean testRayTriangle(double originX, double originY, double originZ, double dirX, double dirY, double dirZ,
            double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z,
            double epsilon) {
        return IntersectionGend.testRayTriangle(originX, originY, originZ, dirX, dirY, dirZ, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, epsilon);
    }

    /**
     * Test whether the ray with the given <code>origin</code> and the given <code>dir</code> intersects the triangle consisting of the three vertices
     * <code>v0</code>, <code>v1</code> and <code>v2</code>.
     * <p>
     * This is an implementation of the <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a> method.
     * <p>
     * This test does not take into account the winding order of the triangle, so a ray will intersect a front-facing triangle as well as a back-facing triangle.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     * 
     * @see #testRayTriangle(double, double, double, double, double, double, double, double, double, double, double, double, double, double, double, double)
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param v0
     *              the position of the first vertex
     * @param v1
     *              the position of the second vertex
     * @param v2
     *              the position of the third vertex
     * @param epsilon
     *              a small positive tolerance, used twice: the ray counts as parallel to the triangle's plane when the absolute determinant of the intersection system falls below it, and it is also the smallest value of the ray parameter <i>t</i> accepted as a hit. The determinant is not normalized: it scales with the length of the direction and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @return <code>true</code> if the given ray intersects the triangle; <code>false</code> otherwise
     */
    public static boolean testRayTriangle(Double3R origin, Double3R dir, Double3R v0, Double3R v1, Double3R v2, double epsilon) {
        return testRayTriangle(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /**
     * Determine whether the given ray with the origin <code>(originX, originY, originZ)</code> and direction <code>(dirX, dirY, dirZ)</code>
     * intersects the frontface of the triangle consisting of the three vertices <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code> and <code>(v2X, v2Y, v2Z)</code>
     * and return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the point of intersection.
     * <p>
     * This is an implementation of the <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a> method.
     * <p>
     * This test implements backface culling, that is, it will return <code>-1.0</code> when the triangle is in clockwise
     * winding order assuming a <i>right-handed</i> coordinate system when seen along the ray's direction, even if the ray intersects the triangle.
     * This is in compliance with how OpenGL handles backface culling with default frontface/backface settings.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     * 
     * @see #testRayTriangleFront(Double3R, Double3R, Double3R, Double3R, Double3R, double)
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param originZ
     *              the z coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param dirZ
     *              the z coordinate of the ray's direction
     * @param v0X
     *              the x coordinate of the first vertex
     * @param v0Y
     *              the y coordinate of the first vertex
     * @param v0Z
     *              the z coordinate of the first vertex
     * @param v1X
     *              the x coordinate of the second vertex
     * @param v1Y
     *              the y coordinate of the second vertex
     * @param v1Z
     *              the z coordinate of the second vertex
     * @param v2X
     *              the x coordinate of the third vertex
     * @param v2Y
     *              the y coordinate of the third vertex
     * @param v2Z
     *              the z coordinate of the third vertex
     * @param epsilon
     *              a small epsilon that the determinant of the intersection system must exceed, rejecting rays that are almost parallel to the triangle. The determinant is not normalized: it scales with the length of the direction and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the point of intersection
     *         if the ray intersects the frontface of the triangle; <code>-1.0</code> otherwise
     */
    public static double intersectRayTriangleFront(double originX, double originY, double originZ, double dirX, double dirY, double dirZ,
            double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z,
            double epsilon) {
        return IntersectionGend.intersectRayTriangleFront(originX, originY, originZ, dirX, dirY, dirZ, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, epsilon);
    }

    /**
     * Determine whether the ray with the given <code>origin</code> and the given <code>dir</code> intersects the frontface of the triangle consisting of the three vertices
     * <code>v0</code>, <code>v1</code> and <code>v2</code> and return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the point of intersection.
     * <p>
     * This is an implementation of the <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a> method.
     * <p>
     * This test implements backface culling, that is, it will return <code>-1.0</code> when the triangle is in clockwise
     * winding order assuming a <i>right-handed</i> coordinate system when seen along the ray's direction, even if the ray intersects the triangle.
     * This is in compliance with how OpenGL handles backface culling with default frontface/backface settings.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     * 
     * @see #intersectRayTriangleFront(double, double, double, double, double, double, double, double, double, double, double, double, double, double, double, double)
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param v0
     *              the position of the first vertex
     * @param v1
     *              the position of the second vertex
     * @param v2
     *              the position of the third vertex
     * @param epsilon
     *              a small epsilon that the determinant of the intersection system must exceed, rejecting rays that are almost parallel to the triangle. The determinant is not normalized: it scales with the length of the direction and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the point of intersection
     *         if the ray intersects the frontface of the triangle; <code>-1.0</code> otherwise
     */
    public static double intersectRayTriangleFront(Double3R origin, Double3R dir, Double3R v0, Double3R v1, Double3R v2, double epsilon) {
        return intersectRayTriangleFront(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /**
     * Determine whether the given ray with the origin <code>(originX, originY, originZ)</code> and direction <code>(dirX, dirY, dirZ)</code>
     * intersects the triangle consisting of the three vertices <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code> and <code>(v2X, v2Y, v2Z)</code>
     * and return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the point of intersection.
     * <p>
     * This is an implementation of the <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a> method.
     * <p>
     * This test does not take into account the winding order of the triangle, so a ray will intersect a front-facing triangle as well as a back-facing triangle.
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} must be non-zero; {@code epsilon} must not be
     * negative.
     * 
     * @see #testRayTriangle(Double3R, Double3R, Double3R, Double3R, Double3R, double)
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param originZ
     *              the z coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param dirZ
     *              the z coordinate of the ray's direction
     * @param v0X
     *              the x coordinate of the first vertex
     * @param v0Y
     *              the y coordinate of the first vertex
     * @param v0Z
     *              the z coordinate of the first vertex
     * @param v1X
     *              the x coordinate of the second vertex
     * @param v1Y
     *              the y coordinate of the second vertex
     * @param v1Z
     *              the z coordinate of the second vertex
     * @param v2X
     *              the x coordinate of the third vertex
     * @param v2Y
     *              the y coordinate of the third vertex
     * @param v2Z
     *              the z coordinate of the third vertex
     * @param epsilon
     *              a small epsilon that the absolute determinant of the intersection system must reach, rejecting rays that are almost parallel to the triangle. The determinant is not normalized: it scales with the length of the direction and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the point of intersection
     *         if the ray intersects the triangle; <code>-1.0</code> otherwise
     */
    public static double intersectRayTriangle(double originX, double originY, double originZ, double dirX, double dirY, double dirZ,
            double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z,
            double epsilon) {
        return IntersectionGend.intersectRayTriangle(originX, originY, originZ, dirX, dirY, dirZ, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, epsilon);
    }

    /**
     * Determine whether the ray with the given <code>origin</code> and the given <code>dir</code> intersects the triangle consisting of the three vertices
     * <code>v0</code>, <code>v1</code> and <code>v2</code> and return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the point of intersection.
     * <p>
     * This is an implementation of the <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a> method.
     * <p>
     * This test does not take into account the winding order of the triangle, so a ray will intersect a front-facing triangle as well as a back-facing triangle.
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code epsilon} must not be negative.
     * 
     * @see #intersectRayTriangle(double, double, double, double, double, double, double, double, double, double, double, double, double, double, double, double)
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param v0
     *              the position of the first vertex
     * @param v1
     *              the position of the second vertex
     * @param v2
     *              the position of the third vertex
     * @param epsilon
     *              a small epsilon that the absolute determinant of the intersection system must reach, rejecting rays that are almost parallel to the triangle. The determinant is not normalized: it scales with the length of the direction and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the point of intersection
     *         if the ray intersects the triangle; <code>-1.0</code> otherwise
     */
    public static double intersectRayTriangle(Double3R origin, Double3R dir, Double3R v0, Double3R v1, Double3R v2, double epsilon) {
        return intersectRayTriangle(origin.x(), origin.y(), origin.z(), dir.x(), dir.y(), dir.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /**
     * Test whether the line segment with the end points <code>(p0X, p0Y, p0Z)</code> and <code>(p1X, p1Y, p1Z)</code>
     * intersects the triangle consisting of the three vertices <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code> and <code>(v2X, v2Y, v2Z)</code>,
     * regardless of the winding order of the triangle or the direction of the line segment between its two end points.
     * <p>
     * Reference: <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a>
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     * 
     * @see #testLineSegmentTriangle(Double3R, Double3R, Double3R, Double3R, Double3R, double)
     * 
     * @param p0X
     *              the x coordinate of the line segment's first end point
     * @param p0Y
     *              the y coordinate of the line segment's first end point
     * @param p0Z
     *              the z coordinate of the line segment's first end point
     * @param p1X
     *              the x coordinate of the line segment's second end point
     * @param p1Y
     *              the y coordinate of the line segment's second end point
     * @param p1Z
     *              the z coordinate of the line segment's second end point
     * @param v0X
     *              the x coordinate of the first vertex
     * @param v0Y
     *              the y coordinate of the first vertex
     * @param v0Z
     *              the z coordinate of the first vertex
     * @param v1X
     *              the x coordinate of the second vertex
     * @param v1Y
     *              the y coordinate of the second vertex
     * @param v1Z
     *              the z coordinate of the second vertex
     * @param v2X
     *              the x coordinate of the third vertex
     * @param v2Y
     *              the y coordinate of the third vertex
     * @param v2Z
     *              the z coordinate of the third vertex
     * @param epsilon
     *              a small epsilon that the absolute determinant of the intersection system must reach, rejecting line segments that are almost parallel to the triangle. The determinant is not normalized: it scales with the length of the segment and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @return <code>true</code> if the given line segment intersects the triangle; <code>false</code> otherwise
     */
    public static boolean testLineSegmentTriangle(double p0X, double p0Y, double p0Z, double p1X, double p1Y, double p1Z,
            double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z,
            double epsilon) {
        return IntersectionGend.testLineSegmentTriangle(p0X, p0Y, p0Z, p1X, p1Y, p1Z, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, epsilon);
    }

    /**
     * Test whether the line segment with the end points <code>p0</code> and <code>p1</code>
     * intersects the triangle consisting of the three vertices <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code> and <code>(v2X, v2Y, v2Z)</code>,
     * regardless of the winding order of the triangle or the direction of the line segment between its two end points.
     * <p>
     * Reference: <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a>
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     * 
     * @see #testLineSegmentTriangle(double, double, double, double, double, double, double, double, double, double, double, double, double, double, double, double)
     * 
     * @param p0
     *              the line segment's first end point
     * @param p1
     *              the line segment's second end point
     * @param v0
     *              the position of the first vertex
     * @param v1
     *              the position of the second vertex
     * @param v2
     *              the position of the third vertex
     * @param epsilon
     *              a small epsilon that the absolute determinant of the intersection system must reach, rejecting line segments that are almost parallel to the triangle. The determinant is not normalized: it scales with the length of the segment and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @return <code>true</code> if the given line segment intersects the triangle; <code>false</code> otherwise
     */
    public static boolean testLineSegmentTriangle(Double3R p0, Double3R p1, Double3R v0, Double3R v1, Double3R v2, double epsilon) {
        return testLineSegmentTriangle(p0.x(), p0.y(), p0.z(), p1.x(), p1.y(), p1.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon);
    }

    /**
     * Determine whether the line segment with the end points <code>(p0X, p0Y, p0Z)</code> and <code>(p1X, p1Y, p1Z)</code>
     * intersects the triangle consisting of the three vertices <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code> and <code>(v2X, v2Y, v2Z)</code>,
     * regardless of the winding order of the triangle or the direction of the line segment between its two end points,
     * and return the point of intersection.
     * <p>
     * Reference: <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a>
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     * 
     * @see #intersectLineSegmentTriangle(Double3R, Double3R, Double3R, Double3R, Double3R, double, Double3)
     * 
     * @param p0X
     *              the x coordinate of the line segment's first end point
     * @param p0Y
     *              the y coordinate of the line segment's first end point
     * @param p0Z
     *              the z coordinate of the line segment's first end point
     * @param p1X
     *              the x coordinate of the line segment's second end point
     * @param p1Y
     *              the y coordinate of the line segment's second end point
     * @param p1Z
     *              the z coordinate of the line segment's second end point
     * @param v0X
     *              the x coordinate of the first vertex
     * @param v0Y
     *              the y coordinate of the first vertex
     * @param v0Z
     *              the z coordinate of the first vertex
     * @param v1X
     *              the x coordinate of the second vertex
     * @param v1Y
     *              the y coordinate of the second vertex
     * @param v1Z
     *              the z coordinate of the second vertex
     * @param v2X
     *              the x coordinate of the third vertex
     * @param v2Y
     *              the y coordinate of the third vertex
     * @param v2Z
     *              the z coordinate of the third vertex
     * @param epsilon
     *              a small epsilon that the absolute determinant of the intersection system must reach, rejecting line segments that are almost parallel to the triangle. The determinant is not normalized: it scales with the length of the segment and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @param intersectionPoint
     *              the point of intersection
     * @return <code>true</code> if the given line segment intersects the triangle; <code>false</code> otherwise
     */
    public static boolean intersectLineSegmentTriangle(double p0X, double p0Y, double p0Z, double p1X, double p1Y, double p1Z,
            double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z,
            double epsilon, Double3 intersectionPoint) {
        return IntersectionGend.intersectLineSegmentTriangle(p0X, p0Y, p0Z, p1X, p1Y, p1Z, v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z, epsilon, intersectionPoint);
    }

    /**
     * Determine whether the line segment with the end points <code>p0</code> and <code>p1</code>
     * intersects the triangle consisting of the three vertices <code>(v0X, v0Y, v0Z)</code>, <code>(v1X, v1Y, v1Z)</code> and <code>(v2X, v2Y, v2Z)</code>,
     * regardless of the winding order of the triangle or the direction of the line segment between its two end points,
     * and return the point of intersection.
     * <p>
     * Reference: <a href="http://www.graphics.cornell.edu/pubs/1997/MT97.pdf">
     * Fast, Minimum Storage Ray/Triangle Intersection</a>
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     * 
     * @see #intersectLineSegmentTriangle(double, double, double, double, double, double, double, double, double, double, double, double, double, double, double, double, Double3)
     * 
     * @param p0
     *              the line segment's first end point
     * @param p1
     *              the line segment's second end point
     * @param v0
     *              the position of the first vertex
     * @param v1
     *              the position of the second vertex
     * @param v2
     *              the position of the third vertex
     * @param epsilon
     *              a small epsilon that the absolute determinant of the intersection system must reach, rejecting line segments that are almost parallel to the triangle. The determinant is not normalized: it scales with the length of the segment and with the size of the triangle, so choose <code>epsilon</code> relative to both
     * @param intersectionPoint
     *              the point of intersection
     * @return <code>true</code> if the given line segment intersects the triangle; <code>false</code> otherwise
     */
    public static boolean intersectLineSegmentTriangle(Double3R p0, Double3R p1, Double3R v0, Double3R v1, Double3R v2, double epsilon, Double3 intersectionPoint) {
        return intersectLineSegmentTriangle(p0.x(), p0.y(), p0.z(), p1.x(), p1.y(), p1.z(), v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), epsilon, intersectionPoint);
    }

    /**
     * Determine whether the line segment with the end points <code>(p0X, p0Y, p0Z)</code> and <code>(p1X, p1Y, p1Z)</code>
     * intersects the plane given as the general plane equation <i>a*x + b*y + c*z + d = 0</i>,
     * and return the point of intersection.
     * <p>
     * Valid input: {@code (a, b, c)} must be non-zero.
     * 
     * @param p0X
     *              the x coordinate of the line segment's first end point
     * @param p0Y
     *              the y coordinate of the line segment's first end point
     * @param p0Z
     *              the z coordinate of the line segment's first end point
     * @param p1X
     *              the x coordinate of the line segment's second end point
     * @param p1Y
     *              the y coordinate of the line segment's second end point
     * @param p1Z
     *              the z coordinate of the line segment's second end point
     * @param a
     *              the x factor in the plane equation
     * @param b
     *              the y factor in the plane equation
     * @param c
     *              the z factor in the plane equation
     * @param d
     *              the constant in the plane equation
     * @param intersectionPoint
     *              the point of intersection
     * @return <code>true</code> if the given line segment intersects the plane; <code>false</code> otherwise
     */
    public static boolean intersectLineSegmentPlane(double p0X, double p0Y, double p0Z, double p1X, double p1Y, double p1Z,
            double a, double b, double c, double d, Double3 intersectionPoint) {
        return IntersectionGend.intersectLineSegmentPlane(p0X, p0Y, p0Z, p1X, p1Y, p1Z, a, b, c, d, intersectionPoint);
    }

    /**
     * Test whether the line with the general line equation <i>a*x + b*y + c = 0</i> intersects the circle with center
     * <code>(centerX, centerY)</code> and <code>radius</code>.
     * <p>
     * Reference: <a href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a
     *          the x factor in the line equation
     * @param b
     *          the y factor in the line equation
     * @param c
     *          the constant in the line equation
     * @param centerX
     *          the x coordinate of the circle's center
     * @param centerY
     *          the y coordinate of the circle's center
     * @param radius
     *          the radius of the circle
     * @return <code>true</code> iff the line intersects the circle; <code>false</code> otherwise
     */
    public static boolean testLineCircle(double a, double b, double c, double centerX, double centerY, double radius) {
        return IntersectionGend.testLineCircle(a, b, c, centerX, centerY, radius);
    }

    /**
     * Test whether the line with the general line equation <i>a*x + b*y + c = 0</i> intersects the circle with center
     * <code>(centerX, centerY)</code> and <code>radius</code>, and store the center of the line segment of
     * intersection in the <code>(x, y)</code> components of the supplied vector and the half-length of that line segment in the z component.
     * <p>
     * Reference: <a href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (a, b)} must be non-zero; {@code radius} must not be negative.
     *
     * @param a
     *          the x factor in the line equation
     * @param b
     *          the y factor in the line equation
     * @param c
     *          the constant in the line equation
     * @param centerX
     *          the x coordinate of the circle's center
     * @param centerY
     *          the y coordinate of the circle's center
     * @param radius
     *          the radius of the circle
     * @param intersectionCenterAndHL
     *          will hold the center of the line segment of intersection in the <code>(x, y)</code> components and the half-length in the z component
     * @return <code>true</code> iff the line intersects the circle; <code>false</code> otherwise
     */
    public static boolean intersectLineCircle(double a, double b, double c, double centerX, double centerY, double radius, Double3 intersectionCenterAndHL) {
        return IntersectionGend.intersectLineCircle(a, b, c, centerX, centerY, radius, intersectionCenterAndHL);
    }

    /**
     * Test whether the line defined by the two points <code>(x0, y0)</code> and <code>(x1, y1)</code> intersects the circle with center
     * <code>(centerX, centerY)</code> and <code>radius</code>, and store the center of the line segment of
     * intersection in the <code>(x, y)</code> components of the supplied vector and the half-length of that line segment in the z component.
     * <p>
     * Reference: <a href="http://math.stackexchange.com/questions/943383/determine-circle-of-intersection-of-plane-and-sphere">http://math.stackexchange.com</a>
     * <p>
     * Valid input: {@code (x0, y0)} and {@code (x1, y1)} must differ; {@code radius} must not be
     * negative.
     *
     * @param x0
     *          the x coordinate of the first point on the line
     * @param y0
     *          the y coordinate of the first point on the line
     * @param x1
     *          the x coordinate of the second point on the line
     * @param y1
     *          the y coordinate of the second point on the line
     * @param centerX
     *          the x coordinate of the circle's center
     * @param centerY
     *          the y coordinate of the circle's center
     * @param radius
     *          the radius of the circle
     * @param intersectionCenterAndHL
     *          will hold the center of the line segment of intersection in the <code>(x, y)</code> components and the half-length in the z component
     * @return <code>true</code> iff the line intersects the circle; <code>false</code> otherwise
     */
    public static boolean intersectLineCircle(double x0, double y0, double x1, double y1, double centerX, double centerY, double radius, Double3 intersectionCenterAndHL) {
        return IntersectionGend.intersectLineCircle(x0, y0, x1, y1, centerX, centerY, radius, intersectionCenterAndHL);
    }

    /**
     * Test whether the axis-aligned rectangle with minimum corner <code>(minX, minY)</code> and maximum corner <code>(maxX, maxY)</code>
     * intersects the line with the general equation <i>a*x + b*y + c = 0</i>.
     * <p>
     * Reference: <a href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a> ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component;
     * {@code (a, b)} must be non-zero.
     * 
     * @param minX
     *          the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY
     *          the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX
     *          the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY
     *          the y coordinate of the maximum corner of the axis-aligned rectangle
     * @param a
     *          the x factor in the line equation
     * @param b
     *          the y factor in the line equation
     * @param c
     *          the constant in the plane equation
     * @return <code>true</code> iff the axis-aligned rectangle intersects the line; <code>false</code> otherwise
     */
    public static boolean testAarLine(double minX, double minY, double maxX, double maxY, double a, double b, double c) {
        return IntersectionGend.testAarLine(minX, minY, maxX, maxY, a, b, c);
    }

    /**
     * Test whether the axis-aligned rectangle with minimum corner <code>min</code> and maximum corner <code>max</code>
     * intersects the line with the general equation <i>a*x + b*y + c = 0</i>.
     * <p>
     * Reference: <a href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a> ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code (a, b)} must be
     * non-zero.
     * 
     * @param min
     *          the minimum corner of the axis-aligned rectangle
     * @param max
     *          the maximum corner of the axis-aligned rectangle
     * @param a
     *          the x factor in the line equation
     * @param b
     *          the y factor in the line equation
     * @param c
     *          the constant in the line equation
     * @return <code>true</code> iff the axis-aligned rectangle intersects the line; <code>false</code> otherwise
     */
    public static boolean testAarLine(Double2R min, Double2R max, double a, double b, double c) {
        return testAarLine(min.x(), min.y(), max.x(), max.y(), a, b, c);
    }

    /**
     * Test whether the axis-aligned rectangle with minimum corner <code>(minX, minY)</code> and maximum corner <code>(maxX, maxY)</code>
     * intersects the line defined by the two points <code>(x0, y0)</code> and <code>(x1, y1)</code>.
     * <p>
     * Reference: <a href="http://www.lighthouse3d.com/tutorials/view-frustum-culling/geometric-approach-testing-boxes-ii/">http://www.lighthouse3d.com</a> ("Geometric Approach - Testing Boxes II")
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component;
     * {@code (x0, y0)} and {@code (x1, y1)} must differ.
     * 
     * @param minX
     *          the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY
     *          the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX
     *          the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY
     *          the y coordinate of the maximum corner of the axis-aligned rectangle
     * @param x0
     *          the x coordinate of the first point on the line
     * @param y0
     *          the y coordinate of the first point on the line
     * @param x1
     *          the x coordinate of the second point on the line
     * @param y1
     *          the y coordinate of the second point on the line
     * @return <code>true</code> iff the axis-aligned rectangle intersects the line; <code>false</code> otherwise
     */
    public static boolean testAarLine(double minX, double minY, double maxX, double maxY, double x0, double y0, double x1, double y1) {
        return IntersectionGend.testAarLine(minX, minY, maxX, maxY, x0, y0, x1, y1);
    }

    /**
     * Test whether the axis-aligned rectangle with minimum corner <code>(minXA, minYA)</code> and maximum corner <code>(maxXA, maxYA)</code>
     * intersects the axis-aligned rectangle with minimum corner <code>(minXB, minYB)</code> and maximum corner <code>(maxXB, maxYB)</code>.
     * <p>
     * Valid input: {@code (minXA, minYA)} must not exceed {@code (maxXA, maxYA)} in any component;
     * {@code (minXB, minYB)} must not exceed {@code (maxXB, maxYB)} in any component.
     * 
     * @param minXA
     *              the x coordinate of the minimum corner of the first axis-aligned rectangle
     * @param minYA
     *              the y coordinate of the minimum corner of the first axis-aligned rectangle
     * @param maxXA
     *              the x coordinate of the maximum corner of the first axis-aligned rectangle
     * @param maxYA
     *              the y coordinate of the maximum corner of the first axis-aligned rectangle
     * @param minXB
     *              the x coordinate of the minimum corner of the second axis-aligned rectangle
     * @param minYB
     *              the y coordinate of the minimum corner of the second axis-aligned rectangle
     * @param maxXB
     *              the x coordinate of the maximum corner of the second axis-aligned rectangle
     * @param maxYB
     *              the y coordinate of the maximum corner of the second axis-aligned rectangle
     * @return <code>true</code> iff both axis-aligned rectangles intersect; <code>false</code> otherwise
     */
    public static boolean testAarAar(double minXA, double minYA, double maxXA, double maxYA, double minXB, double minYB, double maxXB, double maxYB) {
        return IntersectionGend.testAarAar(minXA, minYA, maxXA, maxYA, minXB, minYB, maxXB, maxYB);
    }

    /**
     * Test whether the axis-aligned rectangle with minimum corner <code>minA</code> and maximum corner <code>maxA</code>
     * intersects the axis-aligned rectangle with minimum corner <code>minB</code> and maximum corner <code>maxB</code>.
     * <p>
     * Valid input: {@code minA} must not exceed {@code maxA} in any component; {@code minB} must
     * not exceed {@code maxB} in any component.
     * 
     * @param minA
     *              the minimum corner of the first axis-aligned rectangle
     * @param maxA
     *              the maximum corner of the first axis-aligned rectangle
     * @param minB
     *              the minimum corner of the second axis-aligned rectangle
     * @param maxB
     *              the maximum corner of the second axis-aligned rectangle
     * @return <code>true</code> iff both axis-aligned rectangles intersect; <code>false</code> otherwise
     */
    public static boolean testAarAar(Double2R minA, Double2R maxA, Double2R minB, Double2R maxB) {
        return testAarAar(minA.x(), minA.y(), maxA.x(), maxA.y(), minB.x(), minB.y(), maxB.x(), maxB.y());
    }

    /**
     * Test whether a given circle with center <code>(aX, aY)</code> and radius <code>aR</code> and travelled distance vector <code>(maX, maY)</code>
     * intersects a given static circle with center <code>(bX, bY)</code> and radius <code>bR</code>.
     * <p>
     * Note that the case of two moving circles can always be reduced to this case by expressing the moved distance of one of the circles relative
     * to the other.
     * <p>
     * Reference: <a href="https://www.gamasutra.com/view/feature/131424/pool_hall_lessons_fast_accurate_.php?page=2">https://www.gamasutra.com</a>
     * <p>
     * Valid input: {@code aR} must not be negative; {@code bR} must not be negative.
     * 
     * @param aX
     *              the x coordinate of the first circle's center
     * @param aY
     *              the y coordinate of the first circle's center
     * @param maX
     *              the x coordinate of the first circle's travelled distance vector
     * @param maY
     *              the y coordinate of the first circle's travelled distance vector
     * @param aR
     *              the radius of the first circle
     * @param bX
     *              the x coordinate of the second circle's center
     * @param bY
     *              the y coordinate of the second circle's center
     * @param bR
     *              the radius of the second circle
     * @return <code>true</code> if both circle intersect; <code>false</code> otherwise
     */
    public static boolean testMovingCircleCircle(double aX, double aY, double maX, double maY, double aR, double bX, double bY, double bR) {
        return IntersectionGend.testMovingCircleCircle(aX, aY, maX, maY, aR, bX, bY, bR);
    }

    /**
     * Test whether a given circle with center <code>centerA</code> and radius <code>aR</code> and travelled distance vector <code>moveA</code>
     * intersects a given static circle with center <code>centerB</code> and radius <code>bR</code>.
     * <p>
     * Note that the case of two moving circles can always be reduced to this case by expressing the moved distance of one of the circles relative
     * to the other.
     * <p>
     * Reference: <a href="https://www.gamasutra.com/view/feature/131424/pool_hall_lessons_fast_accurate_.php?page=2">https://www.gamasutra.com</a>
     * <p>
     * Valid input: {@code aR} must not be negative; {@code bR} must not be negative.
     * 
     * @param centerA
     *              the coordinates of the first circle's center
     * @param moveA
     *              the coordinates of the first circle's travelled distance vector
     * @param aR
     *              the radius of the first circle
     * @param centerB
     *              the coordinates of the second circle's center
     * @param bR
     *              the radius of the second circle
     * @return <code>true</code> if both circle intersect; <code>false</code> otherwise
     */
    public static boolean testMovingCircleCircle(Double2R centerA, Double2R moveA, double aR, Double2R centerB, double bR) {
        return testMovingCircleCircle(centerA.x(), centerA.y(), moveA.x(), moveA.y(), aR, centerB.x(), centerB.y(), bR);
    }

    /**
     * Test whether the one circle with center <code>(aX, aY)</code> and square radius <code>radiusSquaredA</code> intersects the other
     * circle with center <code>(bX, bY)</code> and square radius <code>radiusSquaredB</code>, and store the center of the line segment of
     * intersection in the <code>(x, y)</code> components of the supplied vector and the half-length of that line segment in the z component.
     * <p>
     * This method returns <code>false</code> when one circle contains the other circle.
     * <p>
     * Reference: <a href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     * 
     * @param aX
     *              the x coordinate of the first circle's center
     * @param aY
     *              the y coordinate of the first circle's center
     * @param radiusSquaredA
     *              the square of the first circle's radius
     * @param bX
     *              the x coordinate of the second circle's center
     * @param bY
     *              the y coordinate of the second circle's center
     * @param radiusSquaredB
     *              the square of the second circle's radius
     * @param intersectionCenterAndHL
     *              will hold the center of the line segment of intersection in the <code>(x, y)</code> components and its half-length in the z component
     * @return <code>true</code> iff both circles intersect; <code>false</code> otherwise
     */
    public static boolean intersectCircleCircle(double aX, double aY, double radiusSquaredA, double bX, double bY, double radiusSquaredB, Double3 intersectionCenterAndHL) {
        return IntersectionGend.intersectCircleCircle(aX, aY, radiusSquaredA, bX, bY, radiusSquaredB, intersectionCenterAndHL);
    }

    /**
     * Test whether the one circle with center <code>centerA</code> and square radius <code>radiusSquaredA</code> intersects the other
     * circle with center <code>centerB</code> and square radius <code>radiusSquaredB</code>, and store the center of the line segment of
     * intersection in the <code>(x, y)</code> components of the supplied vector and the half-length of that line segment in the z component.
     * <p>
     * This method returns <code>false</code> when one circle contains the other circle.
     * <p>
     * Reference: <a href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be
     * negative.
     * 
     * @param centerA
     *              the first circle's center
     * @param radiusSquaredA
     *              the square of the first circle's radius
     * @param centerB
     *              the second circle's center
     * @param radiusSquaredB
     *              the square of the second circle's radius
     * @param intersectionCenterAndHL
     *              will hold the center of the line segment of intersection in the <code>(x, y)</code> components and the half-length in the z component
     * @return <code>true</code> iff both circles intersect; <code>false</code> otherwise
     */
    public static boolean intersectCircleCircle(Double2R centerA, double radiusSquaredA, Double2R centerB, double radiusSquaredB, Double3 intersectionCenterAndHL) {
        return intersectCircleCircle(centerA.x(), centerA.y(), radiusSquaredA, centerB.x(), centerB.y(), radiusSquaredB, intersectionCenterAndHL);
    }

    /**
     * Test whether the one circle with center <code>(aX, aY)</code> and radius <code>rA</code> intersects the other circle with center <code>(bX, bY)</code> and radius <code>rB</code>.
     * <p>
     * This method returns <code>true</code> when one circle contains the other circle.
     * <p>
     * Reference: <a href="http://math.stackexchange.com/questions/275514/two-circles-overlap">http://math.stackexchange.com/</a>
     * <p>
     * Valid input: {@code rA} must not be negative; {@code rB} must not be negative.
     * 
     * @param aX
     *              the x coordinate of the first circle's center
     * @param aY
     *              the y coordinate of the first circle's center
     * @param rA
     *              the radius of the first circle
     * @param bX
     *              the x coordinate of the second circle's center
     * @param bY
     *              the y coordinate of the second circle's center
     * @param rB
     *              the radius of the second circle
     * @return <code>true</code> iff both circles intersect; <code>false</code> otherwise
     */
    public static boolean testCircleCircle(double aX, double aY, double rA, double bX, double bY, double rB) {
        return IntersectionGend.testCircleCircle(aX, aY, rA, bX, bY, rB);
    }

    /**
     * Test whether the one circle with center <code>centerA</code> and square radius <code>radiusSquaredA</code> intersects the other
     * circle with center <code>centerB</code> and square radius <code>radiusSquaredB</code>.
     * <p>
     * This method returns <code>true</code> when one circle contains the other circle.
     * <p>
     * Reference: <a href="http://gamedev.stackexchange.com/questions/75756/sphere-sphere-intersection-and-circle-sphere-intersection">http://gamedev.stackexchange.com</a>
     * <p>
     * Valid input: {@code radiusSquaredA} must not be negative; {@code radiusSquaredB} must not be negative.
     * 
     * @param centerA
     *              the first circle's center
     * @param radiusSquaredA
     *              the square of the first circle's radius
     * @param centerB
     *              the second circle's center
     * @param radiusSquaredB
     *              the square of the second circle's radius
     * @return <code>true</code> iff both circles intersect; <code>false</code> otherwise
     */
    public static boolean testCircleCircle(Double2R centerA, double radiusSquaredA, Double2R centerB, double radiusSquaredB) {
        // The scalar kernel takes plain radii; forwarding the squared values
        // unchanged reported disjoint circles as intersecting.
        return testCircleCircle(centerA.x(), centerA.y(), Math.sqrt(radiusSquaredA), centerB.x(), centerB.y(), Math.sqrt(radiusSquaredB));
    }

    /**
     * Determine the signed distance of the given point <code>(pointX, pointY)</code> to the line specified via its general plane equation
     * <i>a*x + b*y + c = 0</i>.
     * <p>
     * Reference: <a href="http://mathworld.wolfram.com/Point-LineDistance2-Dimensional.html">http://mathworld.wolfram.com</a>
     * <p>
     * Valid input: {@code (a, b)} must be non-zero.
     * 
     * @param pointX
     *              the x coordinate of the point
     * @param pointY
     *              the y coordinate of the point
     * @param a
     *              the x factor in the plane equation
     * @param b
     *              the y factor in the plane equation
     * @param c
     *              the constant in the plane equation
     * @return the distance between the point and the line
     */
    public static double distancePointLine(double pointX, double pointY, double a, double b, double c) {
        return IntersectionGend.distancePointLine(pointX, pointY, a, b, c);
    }

    /**
     * Determine the signed distance of the given point <code>(pointX, pointY)</code> to the line defined by the two points <code>(x0, y0)</code> and <code>(x1, y1)</code>.
     * <p>
     * The distance is negative for a point on the left of the line's direction <code>(x1 - x0, y1 - y0)</code> and positive for a point on its right.
     * <p>
     * Reference: <a href="http://mathworld.wolfram.com/Point-LineDistance2-Dimensional.html">http://mathworld.wolfram.com</a>
     * <p>
     * Valid input: {@code (x0, y0)} and {@code (x1, y1)} must differ.
     * 
     * @param pointX
     *              the x coordinate of the point
     * @param pointY
     *              the y coordinate of the point
     * @param x0
     *              the x coordinate of the first point on the line
     * @param y0
     *              the y coordinate of the first point on the line
     * @param x1
     *              the x coordinate of the second point on the line
     * @param y1
     *              the y coordinate of the second point on the line
     * @return the distance between the point and the line
     */
    public static double distancePointLine(double pointX, double pointY, double x0, double y0, double x1, double y1) {
        return IntersectionGend.distancePointLine(pointX, pointY, x0, y0, x1, y1);
    }

    /**
     * Compute the distance of the given point <code>(pX, pY, pZ)</code> to the line defined by the two points <code>(x0, y0, z0)</code> and <code>(x1, y1, z1)</code>.
     * <p>
     * Reference: <a href="http://mathworld.wolfram.com/Point-LineDistance3-Dimensional.html">http://mathworld.wolfram.com</a>
     * <p>
     * Valid input: {@code (x0, y0, z0)} and {@code (x1, y1, z1)} must differ.
     * 
     * @param pX
     *          the x coordinate of the point
     * @param pY
     *          the y coordinate of the point
     * @param pZ
     *          the z coordinate of the point
     * @param x0
     *          the x coordinate of the first point on the line
     * @param y0
     *          the y coordinate of the first point on the line
     * @param z0
     *          the z coordinate of the first point on the line
     * @param x1
     *          the x coordinate of the second point on the line
     * @param y1
     *          the y coordinate of the second point on the line
     * @param z1
     *          the z coordinate of the second point on the line
     * @return the distance between the point and the line
     */
    public static double distancePointLine(double pX, double pY, double pZ,
            double x0, double y0, double z0, double x1, double y1, double z1) {
        return IntersectionGend.distancePointLine(pX, pY, pZ, x0, y0, z0, x1, y1, z1);
    }

    /**
     * Test whether the ray with given origin <code>(originX, originY)</code> and direction <code>(dirX, dirY)</code> intersects the line
     * containing the given point <code>(pointX, pointY)</code> and having the normal <code>(normalX, normalY)</code>, and return the
     * value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point.
     * <p>
     * This method returns <code>-1.0</code> if the ray does not intersect the line, because it is either parallel to the line or its direction points
     * away from the line or the ray's origin is on the <i>negative</i> side of the line (i.e. the line's normal points away from the ray's origin).
     * <p>
     * Valid input: {@code (dirX, dirY)} must be non-zero; {@code (normalX, normalY)} must be
     * non-zero; {@code epsilon} must not be negative.
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param pointX
     *              the x coordinate of a point on the line
     * @param pointY
     *              the y coordinate of a point on the line
     * @param normalX
     *              the x coordinate of the line's normal
     * @param normalY
     *              the y coordinate of the line's normal
     * @param epsilon
     *              some small epsilon for when the ray is parallel to the line
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point, if the ray
     *         intersects the line; <code>-1.0</code> otherwise
     */
    public static double intersectRayLine(double originX, double originY, double dirX, double dirY, double pointX, double pointY, double normalX, double normalY, double epsilon) {
        return IntersectionGend.intersectRayLine(originX, originY, dirX, dirY, pointX, pointY, normalX, normalY, epsilon);
    }

    /**
     * Test whether the ray with given <code>origin</code> and direction <code>dir</code> intersects the line
     * containing the given <code>point</code> and having the given <code>normal</code>, and return the
     * value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point.
     * <p>
     * This method returns <code>-1.0</code> if the ray does not intersect the line, because it is either parallel to the line or its direction points
     * away from the line or the ray's origin is on the <i>negative</i> side of the line (i.e. the line's normal points away from the ray's origin).
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code normal} must be non-zero; {@code epsilon}
     * must not be negative.
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param point
     *              a point on the line
     * @param normal
     *              the line's normal
     * @param epsilon
     *              some small epsilon for when the ray is parallel to the line
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point, if the ray
     *         intersects the line; <code>-1.0</code> otherwise
     */
    public static double intersectRayLine(Double2R origin, Double2R dir, Double2R point, Double2R normal, double epsilon) {
        return intersectRayLine(origin.x(), origin.y(), dir.x(), dir.y(), point.x(), point.y(), normal.x(), normal.y(), epsilon);
    }

    /**
     * Determine whether the ray with given origin <code>(originX, originY)</code> and direction <code>(dirX, dirY)</code> intersects the undirected line segment
     * given by the two end points <code>(aX, aY)</code> and <code>(bX, bY)</code>, and return the value of the parameter <i>t</i> in the ray equation
     * <i>p(t) = origin + t * dir</i> of the intersection point, if any.
     * <p>
     * This method returns <code>-1.0</code> if the ray does not intersect the line segment.
     * <p>
     * Valid input: {@code (dirX, dirY)} must be non-zero.
     * 
     * @see #intersectRayLineSegment(Double2R, Double2R, Double2R, Double2R)
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param aX
     *              the x coordinate of the line segment's first end point
     * @param aY
     *              the y coordinate of the line segment's first end point
     * @param bX
     *              the x coordinate of the line segment's second end point
     * @param bY
     *              the y coordinate of the line segment's second end point
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point, if the ray
     *         intersects the line segment; <code>-1.0</code> otherwise
     */
    public static double intersectRayLineSegment(double originX, double originY, double dirX, double dirY, double aX, double aY, double bX, double bY) {
        return IntersectionGend.intersectRayLineSegment(originX, originY, dirX, dirY, aX, aY, bX, bY);
    }

    /**
     * Determine whether the ray with given <code>origin</code> and direction <code>dir</code> intersects the undirected line segment
     * given by the two end points <code>a</code> and <code>b</code>, and return the value of the parameter <i>t</i> in the ray equation
     * <i>p(t) = origin + t * dir</i> of the intersection point, if any.
     * <p>
     * This method returns <code>-1.0</code> if the ray does not intersect the line segment.
     * <p>
     * Valid input: {@code dir} must be non-zero.
     * 
     * @see #intersectRayLineSegment(double, double, double, double, double, double, double, double)
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param a
     *              the line segment's first end point
     * @param b
     *              the line segment's second end point
     * @return the value of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the intersection point, if the ray
     *         intersects the line segment; <code>-1.0</code> otherwise
     */
    public static double intersectRayLineSegment(Double2R origin, Double2R dir, Double2R a, Double2R b) {
        return intersectRayLineSegment(origin.x(), origin.y(), dir.x(), dir.y(), a.x(), a.y(), b.x(), b.y());
    }

    /**
     * Test whether the axis-aligned rectangle with minimum corner <code>(minX, minY)</code> and maximum corner <code>(maxX, maxY)</code>
     * intersects the circle with the given center <code>(centerX, centerY)</code> and square radius <code>radiusSquared</code>.
     * <p>
     * Reference: <a href="http://stackoverflow.com/questions/4578967/cube-sphere-intersection-test#answer-4579069">http://stackoverflow.com</a>
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component;
     * {@code radiusSquared} must not be negative.
     * 
     * @param minX
     *          the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY
     *          the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX
     *          the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY
     *          the y coordinate of the maximum corner of the axis-aligned rectangle
     * @param centerX
     *          the x coordinate of the circle's center
     * @param centerY
     *          the y coordinate of the circle's center
     * @param radiusSquared
     *          the square of the circle's radius
     * @return <code>true</code> iff the axis-aligned rectangle intersects the circle; <code>false</code> otherwise
     */
    public static boolean testAarCircle(double minX, double minY, double maxX, double maxY, double centerX, double centerY, double radiusSquared) {
        return IntersectionGend.testAarCircle(minX, minY, maxX, maxY, centerX, centerY, radiusSquared);
    }

    /**
     * Test whether the axis-aligned rectangle with minimum corner <code>min</code> and maximum corner <code>max</code>
     * intersects the circle with the given <code>center</code> and square radius <code>radiusSquared</code>.
     * <p>
     * Reference: <a href="http://stackoverflow.com/questions/4578967/cube-sphere-intersection-test#answer-4579069">http://stackoverflow.com</a>
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code radiusSquared}
     * must not be negative.
     * 
     * @param min
     *          the minimum corner of the axis-aligned rectangle
     * @param max
     *          the maximum corner of the axis-aligned rectangle
     * @param center
     *          the circle's center
     * @param radiusSquared
     *          the square of the circle's radius
     * @return <code>true</code> iff the axis-aligned rectangle intersects the circle; <code>false</code> otherwise
     */
    public static boolean testAarCircle(Double2R min, Double2R max, Double2R center, double radiusSquared) {
        return testAarCircle(min.x(), min.y(), max.x(), max.y(), center.x(), center.y(), radiusSquared);
    }

    /**
     * Determine the closest point on the triangle with the given vertices <code>(v0X, v0Y)</code>, <code>(v1X, v1Y)</code>, <code>(v2X, v2Y)</code>
     * between that triangle and the given point <code>(pX, pY)</code> and store that point into the given <code>result</code>.
     * <p>
     * Additionally, this method returns whether the closest point is a vertex ({@link #POINT_ON_TRIANGLE_VERTEX_0}, {@link #POINT_ON_TRIANGLE_VERTEX_1}, {@link #POINT_ON_TRIANGLE_VERTEX_2})
     * of the triangle, lies on an edge ({@link #POINT_ON_TRIANGLE_EDGE_01}, {@link #POINT_ON_TRIANGLE_EDGE_12}, {@link #POINT_ON_TRIANGLE_EDGE_20})
     * or on the {@link #POINT_ON_TRIANGLE_FACE face} of the triangle.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.5 "Closest Point on Triangle to Point"
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param v0X
     *          the x coordinate of the first vertex of the triangle
     * @param v0Y
     *          the y coordinate of the first vertex of the triangle
     * @param v1X
     *          the x coordinate of the second vertex of the triangle
     * @param v1Y
     *          the y coordinate of the second vertex of the triangle
     * @param v2X
     *          the x coordinate of the third vertex of the triangle
     * @param v2Y
     *          the y coordinate of the third vertex of the triangle
     * @param pX
     *          the x coordinate of the point
     * @param pY
     *          the y coordinate of the point
     * @param result
     *          will hold the closest point
     * @return one of {@link #POINT_ON_TRIANGLE_VERTEX_0}, {@link #POINT_ON_TRIANGLE_VERTEX_1}, {@link #POINT_ON_TRIANGLE_VERTEX_2},
     *                {@link #POINT_ON_TRIANGLE_EDGE_01}, {@link #POINT_ON_TRIANGLE_EDGE_12}, {@link #POINT_ON_TRIANGLE_EDGE_20} or
     *                {@link #POINT_ON_TRIANGLE_FACE}
     */
    public static int findClosestPointOnTriangle(double v0X, double v0Y, double v1X, double v1Y, double v2X, double v2Y, double pX, double pY, Double2 result) {
        return IntersectionGend.findClosestPointOnTriangle(v0X, v0Y, v1X, v1Y, v2X, v2Y, pX, pY, result);
    }

    /**
     * Determine the closest point on the triangle with the vertices <code>v0</code>, <code>v1</code>, <code>v2</code>
     * between that triangle and the given point <code>p</code> and store that point into the given <code>result</code>.
     * <p>
     * Additionally, this method returns whether the closest point is a vertex ({@link #POINT_ON_TRIANGLE_VERTEX_0}, {@link #POINT_ON_TRIANGLE_VERTEX_1}, {@link #POINT_ON_TRIANGLE_VERTEX_2})
     * of the triangle, lies on an edge ({@link #POINT_ON_TRIANGLE_EDGE_01}, {@link #POINT_ON_TRIANGLE_EDGE_12}, {@link #POINT_ON_TRIANGLE_EDGE_20})
     * or on the {@link #POINT_ON_TRIANGLE_FACE face} of the triangle.
     * <p>
     * Reference: Book "Real-Time Collision Detection" chapter 5.1.5 "Closest Point on Triangle to Point"
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param v0
     *          the first vertex of the triangle
     * @param v1
     *          the second vertex of the triangle
     * @param v2
     *          the third vertex of the triangle
     * @param p
     *          the point
     * @param result
     *          will hold the closest point
     * @return one of {@link #POINT_ON_TRIANGLE_VERTEX_0}, {@link #POINT_ON_TRIANGLE_VERTEX_1}, {@link #POINT_ON_TRIANGLE_VERTEX_2},
     *                {@link #POINT_ON_TRIANGLE_EDGE_01}, {@link #POINT_ON_TRIANGLE_EDGE_12}, {@link #POINT_ON_TRIANGLE_EDGE_20} or
     *                {@link #POINT_ON_TRIANGLE_FACE}
     */
    public static int findClosestPointOnTriangle(Double2R v0, Double2R v1, Double2R v2, Double2R p, Double2 result) {
        return findClosestPointOnTriangle(v0.x(), v0.y(), v1.x(), v1.y(), v2.x(), v2.y(), p.x(), p.y(), result);
    }

    /**
     * Test whether the given ray with the origin <code>(originX, originY)</code> and normalized direction <code>(dirX, dirY)</code>
     * intersects the given circle with center <code>(centerX, centerY)</code> and square radius <code>radiusSquared</code>,
     * and store the values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> for both points (near
     * and far) of intersections into the given <code>result</code> vector.
     * <p>
     * The ray's direction must be normalized.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the circle.
     * <p>
     * Reference: <a href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code (dirX, dirY)} must have unit length; {@code radiusSquared} must not be
     * negative.
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's normalized direction
     * @param dirY
     *              the y coordinate of the ray's normalized direction
     * @param centerX
     *              the x coordinate of the circle's center
     * @param centerY
     *              the y coordinate of the circle's center
     * @param radiusSquared
     *              the circle radius squared
     * @param result
     *              a vector that will contain the values of the parameter <i>t</i> in the ray equation
     *              <i>p(t) = origin + t * dir</i> for both points (near, far) of intersections with the circle
     * @return <code>true</code> if the ray intersects the circle; <code>false</code> otherwise
     */
    public static boolean intersectRayCircle(double originX, double originY, double dirX, double dirY, 
            double centerX, double centerY, double radiusSquared, Double2 result) {
        return IntersectionGend.intersectRayCircle(originX, originY, dirX, dirY, centerX, centerY, radiusSquared, result);
    }

    /**
     * Test whether the ray with the given <code>origin</code> and normalized direction <code>dir</code>
     * intersects the circle with the given <code>center</code> and square radius <code>radiusSquared</code>,
     * and store the values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> for both points (near
     * and far) of intersections into the given <code>result</code> vector.
     * <p>
     * The ray's direction must be normalized.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the circle.
     * <p>
     * Reference: <a href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code dir} must have unit length; {@code radiusSquared} must not be negative.
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's normalized direction
     * @param center
     *              the circle's center
     * @param radiusSquared
     *              the circle radius squared
     * @param result
     *              a vector that will contain the values of the parameter <i>t</i> in the ray equation
     *              <i>p(t) = origin + t * dir</i> for both points (near, far) of intersections with the circle
     * @return <code>true</code> if the ray intersects the circle; <code>false</code> otherwise
     */
    public static boolean intersectRayCircle(Double2R origin, Double2R dir, Double2R center, double radiusSquared, Double2 result) {
        return intersectRayCircle(origin.x(), origin.y(), dir.x(), dir.y(), center.x(), center.y(), radiusSquared, result);
    }

    /**
     * Test whether the given ray with the origin <code>(originX, originY)</code> and normalized direction <code>(dirX, dirY)</code>
     * intersects the given circle with center <code>(centerX, centerY)</code> and square radius <code>radiusSquared</code>.
     * <p>
     * The ray's direction must be normalized.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the circle.
     * <p>
     * Reference: <a href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code (dirX, dirY)} must have unit length; {@code radiusSquared} must not be
     * negative.
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's normalized direction
     * @param dirY
     *              the y coordinate of the ray's normalized direction
     * @param centerX
     *              the x coordinate of the circle's center
     * @param centerY
     *              the y coordinate of the circle's center
     * @param radiusSquared
     *              the circle radius squared
     * @return <code>true</code> if the ray intersects the circle; <code>false</code> otherwise
     */
    public static boolean testRayCircle(double originX, double originY, double dirX, double dirY, 
            double centerX, double centerY, double radiusSquared) {
        return IntersectionGend.testRayCircle(originX, originY, dirX, dirY, centerX, centerY, radiusSquared);
    }

    /**
     * Test whether the ray with the given <code>origin</code> and normalized direction <code>dir</code>
     * intersects the circle with the given <code>center</code> and square radius.
     * <p>
     * The ray's direction must be normalized.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the circle.
     * <p>
     * Reference: <a href="http://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection">http://www.scratchapixel.com/</a>
     * <p>
     * Valid input: {@code dir} must have unit length; {@code radiusSquared} must not be negative.
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's normalized direction
     * @param center
     *              the circle's center
     * @param radiusSquared
     *              the circle radius squared
     * @return <code>true</code> if the ray intersects the circle; <code>false</code> otherwise
     */
    public static boolean testRayCircle(Double2R origin, Double2R dir, Double2R center, double radiusSquared) {
        return testRayCircle(origin.x(), origin.y(), dir.x(), dir.y(), center.x(), center.y(), radiusSquared);
    }

    /**
     * Determine whether the given ray with the origin <code>(originX, originY)</code> and direction <code>(dirX, dirY)</code>
     * intersects the axis-aligned rectangle given as its minimum corner <code>(minX, minY)</code> and maximum corner <code>(maxX, maxY)</code>,
     * and return the values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the near and far point of intersection
     * as well as the side of the axis-aligned rectangle the ray intersects.
     * <p>
     * This method also detects an intersection for a ray whose origin lies inside the axis-aligned rectangle.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code (dirX, dirY)} must be non-zero; {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     * 
     * @see #intersectRayAar(Double2R, Double2R, Double2R, Double2R, Double2)
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param minX
     *              the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY
     *              the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX
     *              the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY
     *              the y coordinate of the maximum corner of the axis-aligned rectangle
     * @param result
     *              a vector which will hold the values of the parameter <i>t</i> in the ray equation
     *              <i>p(t) = origin + t * dir</i> of the near and far point of intersection
     * @return the side on which the near intersection occurred, or the side through which the ray exits (at <code>result.y</code>)
     *         when the ray's origin lies inside the rectangle, as one of
     *         {@link #AAR_SIDE_MINX}, {@link #AAR_SIDE_MINY}, {@link #AAR_SIDE_MAXX} or {@link #AAR_SIDE_MAXY};
     *         or <code>-1</code> if the ray does not intersect the axis-aligned rectangle;
     */
    public static int intersectRayAar(double originX, double originY, double dirX, double dirY, 
            double minX, double minY, double maxX, double maxY, Double2 result) {
        double invDirX = 1.0 / dirX, invDirY = 1.0 / dirY;
        double tNear, tFar, tymin, tymax;
        if (invDirX >= 0.0) {
            tNear = (minX - originX) * invDirX;
            tFar = (maxX - originX) * invDirX;
        } else {
            tNear = (maxX - originX) * invDirX;
            tFar = (minX - originX) * invDirX;
        }
        if (invDirY >= 0.0) {
            tymin = (minY - originY) * invDirY;
            tymax = (maxY - originY) * invDirY;
        } else {
            tymin = (maxY - originY) * invDirY;
            tymax = (minY - originY) * invDirY;
        }
        if (tNear > tymax || tymin > tFar)
            return OUTSIDE;
        tNear = tymin > tNear || Double.isNaN(tNear) ? tymin : tNear;
        tFar = tymax < tFar || Double.isNaN(tFar) ? tymax : tFar;
        int side = -1; // no intersection side
        if (tNear < tFar && tFar >= 0.0) {
            // A ray starting inside the rectangle (tNear < 0) never crosses the
            // near side; the side it actually passes through is the far one.
            double tSide = tNear >= 0.0 ? tNear : tFar;
            double px = originX + tSide * dirX;
            double py = originY + tSide * dirY;
            result.set(tNear, tFar);
            double daX = Math.abs(px - minX);
            double daY = Math.abs(py - minY);
            double dbX = Math.abs(px - maxX);
            double dbY = Math.abs(py - maxY);
            side = 0; // min x coordinate
            double min = daX;
            if (daY < min) {
                min = daY;
                side = 1; // min y coordinate
            }
            if (dbX < min) {
                min = dbX;
                side = 2; // max xcoordinate
            }
            if (dbY < min)
                side = 3; // max y coordinate
        }
        return side;
    }

    /**
     * Determine whether the given ray with the given <code>origin</code> and direction <code>dir</code>
     * intersects the axis-aligned rectangle given as its minimum corner <code>min</code> and maximum corner <code>max</code>,
     * and return the values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> of the near and far point of intersection
     * as well as the side of the axis-aligned rectangle the ray intersects.
     * <p>
     * This method also detects an intersection for a ray whose origin lies inside the axis-aligned rectangle.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code dir} must be non-zero; {@code min} must not exceed {@code max} in any component.
     * 
     * @see #intersectRayAar(double, double, double, double, double, double, double, double, Double2)
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param min
     *              the minimum corner of the axis-aligned rectangle
     * @param max
     *              the maximum corner of the axis-aligned rectangle
     * @param result
     *              a vector which will hold the values of the parameter <i>t</i> in the ray equation
     *              <i>p(t) = origin + t * dir</i> of the near and far point of intersection
     * @return the side on which the near intersection occurred, or the side through which the ray exits (at <code>result.y</code>)
     *         when the ray's origin lies inside the rectangle, as one of
     *         {@link #AAR_SIDE_MINX}, {@link #AAR_SIDE_MINY}, {@link #AAR_SIDE_MAXX} or {@link #AAR_SIDE_MAXY};
     *         or <code>-1</code> if the ray does not intersect the axis-aligned rectangle;
     */
    public static int intersectRayAar(Double2R origin, Double2R dir, Double2R min, Double2R max, Double2 result) {
        return intersectRayAar(origin.x(), origin.y(), dir.x(), dir.y(), min.x(), min.y(), max.x(), max.y(), result);
    }

    /**
     * Determine whether the undirected line segment with the end points <code>(p0X, p0Y)</code> and <code>(p1X, p1Y)</code>
     * intersects the axis-aligned rectangle given as its minimum corner <code>(minX, minY)</code> and maximum corner <code>(maxX, maxY)</code>,
     * and store the values of the parameter <i>t</i> in the ray equation <i>p(t) = p0 + t * (p1 - p0)</i> of the near and far point of intersection
     * into <code>result</code>.
     * <p>
     * This method also detects an intersection of a line segment whose either end point lies inside the axis-aligned rectangle.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @see #intersectLineSegmentAar(Double2R, Double2R, Double2R, Double2R, Double2)
     * 
     * @param p0X
     *              the x coordinate of the line segment's first end point
     * @param p0Y
     *              the y coordinate of the line segment's first end point
     * @param p1X
     *              the x coordinate of the line segment's second end point
     * @param p1Y
     *              the y coordinate of the line segment's second end point
     * @param minX
     *              the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY
     *              the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX
     *              the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY
     *              the y coordinate of the maximum corner of the axis-aligned rectangle
     * @param result
     *              a vector which will hold the values of the parameter <i>t</i> in the ray equation
     *              <i>p(t) = p0 + t * (p1 - p0)</i> of the near and far point of intersection
     * @return {@link #INSIDE} if the line segment lies completely inside of the axis-aligned rectangle; or
     *         {@link #OUTSIDE} if the line segment lies completely outside of the axis-aligned rectangle; or
     *         {@link #ONE_INTERSECTION} if one of the end points of the line segment lies inside of the axis-aligned rectangle; or
     *         {@link #TWO_INTERSECTION} if the line segment intersects two edges of the axis-aligned rectangle or lies on one edge of the rectangle
     */
    public static int intersectLineSegmentAar(double p0X, double p0Y, double p1X, double p1Y,
            double minX, double minY, double maxX, double maxY, Double2 result) {
        return IntersectionGend.intersectLineSegmentAar(p0X, p0Y, p1X, p1Y, minX, minY, maxX, maxY, result);
    }

    /**
     * Determine whether the undirected line segment with the end points <code>p0</code> and <code>p1</code>
     * intersects the axis-aligned rectangle given as its minimum corner <code>min</code> and maximum corner <code>max</code>,
     * and store the values of the parameter <i>t</i> in the ray equation <i>p(t) = p0 + t * (p1 - p0)</i> of the near and far point of intersection
     * into <code>result</code>.
     * <p>
     * This method also detects an intersection of a line segment whose either end point lies inside the axis-aligned rectangle.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @see #intersectLineSegmentAar(double, double, double, double, double, double, double, double, Double2)
     * 
     * @param p0
     *              the line segment's first end point
     * @param p1
     *              the line segment's second end point
     * @param min
     *              the minimum corner of the axis-aligned rectangle
     * @param max
     *              the maximum corner of the axis-aligned rectangle
     * @param result
     *              a vector which will hold the values of the parameter <i>t</i> in the ray equation
     *              <i>p(t) = p0 + t * (p1 - p0)</i> of the near and far point of intersection
     * @return {@link #INSIDE} if the line segment lies completely inside of the axis-aligned rectangle; or
     *         {@link #OUTSIDE} if the line segment lies completely outside of the axis-aligned rectangle; or
     *         {@link #ONE_INTERSECTION} if one of the end points of the line segment lies inside of the axis-aligned rectangle; or
     *         {@link #TWO_INTERSECTION} if the line segment intersects two edges of the axis-aligned rectangle
     */
    public static int intersectLineSegmentAar(Double2R p0, Double2R p1, Double2R min, Double2R max, Double2 result) {
        return intersectLineSegmentAar(p0.x(), p0.y(), p1.x(), p1.y(), min.x(), min.y(), max.x(), max.y(), result);
    }

    /**
     * Test whether the given ray with the origin <code>(originX, originY)</code> and direction <code>(dirX, dirY)</code>
     * intersects the given axis-aligned rectangle given as its minimum corner <code>(minX, minY)</code> and maximum corner <code>(maxX, maxY)</code>.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the axis-aligned rectangle.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     * 
     * @see #testRayAar(Double2R, Double2R, Double2R, Double2R)
     * 
     * @param originX
     *              the x coordinate of the ray's origin
     * @param originY
     *              the y coordinate of the ray's origin
     * @param dirX
     *              the x coordinate of the ray's direction
     * @param dirY
     *              the y coordinate of the ray's direction
     * @param minX
     *          the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY
     *          the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX
     *          the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY
     *          the y coordinate of the maximum corner of the axis-aligned rectangle
     * @return <code>true</code> if the given ray intersects the axis-aligned rectangle; <code>false</code> otherwise
     */
    public static boolean testRayAar(double originX, double originY, double dirX, double dirY, double minX, double minY, double maxX, double maxY) {
        return IntersectionGend.testRayAar(originX, originY, dirX, dirY, minX, minY, maxX, maxY);
    }

    /**
     * Test whether the ray with the given <code>origin</code> and direction <code>dir</code>
     * intersects the given axis-aligned rectangle specified as its minimum corner <code>min</code> and maximum corner <code>max</code>.
     * <p>
     * This method returns <code>true</code> for a ray whose origin lies inside the axis-aligned rectangle.
     * <p>
     * Reference: <a href="https://dl.acm.org/citation.cfm?id=1198748">An Efficient and Robust Ray–Box Intersection</a>
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     * 
     * @see #testRayAar(double, double, double, double, double, double, double, double)
     * 
     * @param origin
     *              the ray's origin
     * @param dir
     *              the ray's direction
     * @param min
     *              the minimum corner of the axis-aligned rectangle
     * @param max
     *              the maximum corner of the axis-aligned rectangle
     * @return <code>true</code> if the given ray intersects the axis-aligned rectangle; <code>false</code> otherwise
     */
    public static boolean testRayAar(Double2R origin, Double2R dir, Double2R min, Double2R max) {
        return testRayAar(origin.x(), origin.y(), dir.x(), dir.y(), min.x(), min.y(), max.x(), max.y());
    }

    /**
     * Test whether the given point <code>(pX, pY)</code> lies inside the triangle with the vertices <code>(v0X, v0Y)</code>, <code>(v1X, v1Y)</code>, <code>(v2X, v2Y)</code>.
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param pX
     *          the x coordinate of the point
     * @param pY
     *          the y coordinate of the point
     * @param v0X
     *          the x coordinate of the first vertex of the triangle
     * @param v0Y
     *          the y coordinate of the first vertex of the triangle
     * @param v1X
     *          the x coordinate of the second vertex of the triangle
     * @param v1Y
     *          the y coordinate of the second vertex of the triangle
     * @param v2X
     *          the x coordinate of the third vertex of the triangle
     * @param v2Y
     *          the y coordinate of the third vertex of the triangle
     * @return <code>true</code> iff the point lies inside the triangle; <code>false</code> otherwise
     */
    public static boolean testPointTriangle(double pX, double pY, double v0X, double v0Y, double v1X, double v1Y, double v2X, double v2Y) {
        return IntersectionGend.testPointTriangle(pX, pY, v0X, v0Y, v1X, v1Y, v2X, v2Y);
    }

    /**
     * Test whether the given <code>point</code> lies inside the triangle with the vertices <code>v0</code>, <code>v1</code>, <code>v2</code>.
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param v0
     *          the first vertex of the triangle
     * @param v1
     *          the second vertex of the triangle
     * @param v2
     *          the third vertex of the triangle
     * @param point
     *          the point
     * @return <code>true</code> iff the point lies inside the triangle; <code>false</code> otherwise
     */
    public static boolean testPointTriangle(Double2R point, Double2R v0, Double2R v1, Double2R v2) {
        return testPointTriangle(point.x(), point.y(), v0.x(), v0.y(), v1.x(), v1.y(), v2.x(), v2.y());
    }

    /**
     * Test whether the given point <code>(pX, pY)</code> lies inside the axis-aligned rectangle with the minimum corner <code>(minX, minY)</code>
     * and maximum corner <code>(maxX, maxY)</code>.
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     * 
     * @param pX
     *          the x coordinate of the point
     * @param pY
     *          the y coordinate of the point
     * @param minX
     *          the x coordinate of the minimum corner of the axis-aligned rectangle
     * @param minY
     *          the y coordinate of the minimum corner of the axis-aligned rectangle
     * @param maxX
     *          the x coordinate of the maximum corner of the axis-aligned rectangle
     * @param maxY
     *          the y coordinate of the maximum corner of the axis-aligned rectangle
     * @return <code>true</code> iff the point lies inside the axis-aligned rectangle; <code>false</code> otherwise
     */
    public static boolean testPointAar(double pX, double pY, double minX, double minY, double maxX, double maxY) {
        return IntersectionGend.testPointAar(pX, pY, minX, minY, maxX, maxY);
    }

    /**
     * Test whether the point <code>(pX, pY)</code> lies inside the circle with center <code>(centerX, centerY)</code> and square radius <code>radiusSquared</code>.
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     * 
     * @param pX
     *          the x coordinate of the point
     * @param pY
     *          the y coordinate of the point
     * @param centerX
     *          the x coordinate of the circle's center
     * @param centerY
     *          the y coordinate of the circle's center
     * @param radiusSquared
     *          the square radius of the circle
     * @return <code>true</code> iff the point lies inside the circle; <code>false</code> otherwise
     */
    public static boolean testPointCircle(double pX, double pY, double centerX, double centerY, double radiusSquared) {
        return IntersectionGend.testPointCircle(pX, pY, centerX, centerY, radiusSquared);
    }

    /**
     * Test whether the circle with center <code>(centerX, centerY)</code> and square radius <code>radiusSquared</code> intersects the triangle with counter-clockwise vertices
     * <code>(v0X, v0Y)</code>, <code>(v1X, v1Y)</code>, <code>(v2X, v2Y)</code>.
     * <p>
     * The vertices of the triangle must be specified in counter-clockwise order.
     * <p>
     * Reference: <a href="http://www.phatcode.net/articles.php?id=459">http://www.phatcode.net/</a>
     * <p>
     * Valid input: {@code radiusSquared} must not be negative; {@code (v0X, v0Y)}, {@code (v1X,
     * v1Y)} and {@code (v2X, v2Y)} must be in counter-clockwise order.
     * 
     * @param centerX
     *          the x coordinate of the circle's center
     * @param centerY
     *          the y coordinate of the circle's center
     * @param radiusSquared
     *          the square radius of the circle
     * @param v0X
     *          the x coordinate of the first vertex of the triangle
     * @param v0Y
     *          the y coordinate of the first vertex of the triangle
     * @param v1X
     *          the x coordinate of the second vertex of the triangle
     * @param v1Y
     *          the y coordinate of the second vertex of the triangle
     * @param v2X
     *          the x coordinate of the third vertex of the triangle
     * @param v2Y
     *          the y coordinate of the third vertex of the triangle
     * @return <code>true</code> iff the circle intersects the triangle; <code>false</code> otherwise
     */
    public static boolean testCircleTriangle(double centerX, double centerY, double radiusSquared, double v0X, double v0Y, double v1X, double v1Y, double v2X, double v2Y) {
        return IntersectionGend.testCircleTriangle(centerX, centerY, radiusSquared, v0X, v0Y, v1X, v1Y, v2X, v2Y);
    }

    /**
     * Test whether the circle with given <code>center</code> and square radius <code>radiusSquared</code> intersects the triangle with counter-clockwise vertices
     * <code>v0</code>, <code>v1</code>, <code>v2</code>.
     * <p>
     * The vertices of the triangle must be specified in counter-clockwise order.
     * <p>
     * Reference: <a href="http://www.phatcode.net/articles.php?id=459">http://www.phatcode.net/</a>
     * <p>
     * Valid input: {@code radiusSquared} must not be negative; {@code v0}, {@code v1} and {@code
     * v2} must be in counter-clockwise order.
     * 
     * @param center
     *          the circle's center
     * @param radiusSquared
     *          the square radius of the circle
     * @param v0
     *          the first vertex of the triangle
     * @param v1
     *          the second vertex of the triangle
     * @param v2
     *          the third vertex of the triangle
     * @return <code>true</code> iff the circle intersects the triangle; <code>false</code> otherwise
     */
    public static boolean testCircleTriangle(Double2R center, double radiusSquared, Double2R v0, Double2R v1, Double2R v2) {
        return testCircleTriangle(center.x(), center.y(), radiusSquared, v0.x(), v0.y(), v1.x(), v1.y(), v2.x(), v2.y());
    }

    /**
     * Determine whether the polygon specified by the given sequence of <code>(x, y)</code> coordinate pairs intersects with the ray
     * with given origin <code>(originX, originY)</code> and direction <code>(dirX, dirY)</code>, and store the point of intersection
     * into the given vector <code>p</code>.
     * <p>
     * If the polygon intersects the ray, this method returns the index of the polygon edge intersecting the ray, that is, the index of the 
     * first vertex of the directed line segment. The second vertex is always that index + 1, modulus the number of polygon vertices.
     * <p>
     * Valid input: {@code (dirX, dirY)} must be non-zero.
     * 
     * @param verticesXY
     *          the sequence of <code>(x, y)</code> coordinate pairs of all vertices of the polygon
     * @param originX
     *          the x coordinate of the ray's origin
     * @param originY
     *          the y coordinate of the ray's origin
     * @param dirX
     *          the x coordinate of the ray's direction
     * @param dirY
     *          the y coordinate of the ray's direction
     * @param p
     *          will hold the point of intersection
     * @return the index of the first vertex of the polygon edge that intersects the ray; or <code>-1</code> if the ray does not intersect the polygon
     */
    public static int intersectPolygonRay(double[] verticesXY, double originX, double originY, double dirX, double dirY, Double2 p) {
        double nearestT = Double.POSITIVE_INFINITY;
        int count = verticesXY.length >> 1;
        int edgeIndex = -1;
        if (count == 0) return -1; // no vertices, no edge to hit
        double aX = verticesXY[(count-1)<<1], aY = verticesXY[((count-1)<<1) + 1];
        for (int i = 0; i < count; i++) {
            double bX = verticesXY[i << 1], bY = verticesXY[(i << 1) + 1];
            double doaX = originX - aX, doaY = originY - aY;
            double dbaX = bX - aX, dbaY = bY - aY;
            double invDbaDir = 1.0 / (dbaY * dirX - dbaX * dirY);
            double t = (dbaX * doaY - dbaY * doaX) * invDbaDir;
            if (t >= 0.0 && t < nearestT) {
                double t2 = (doaY * dirX - doaX * dirY) * invDbaDir;
                if (t2 >= 0.0 && t2 <= 1.0) {
                    edgeIndex = (i - 1 + count) % count;
                    nearestT = t;
                    p.set(originX + t * dirX, originY + t * dirY);
                }
            }
            aX = bX;
            aY = bY;
        }
        return edgeIndex;
    }

    /**
     * Determine whether the polygon specified by the given sequence of <code>vertices</code> intersects with the ray
     * with given origin <code>(originX, originY)</code> and direction <code>(dirX, dirY)</code>, and store the point of intersection
     * into the given vector <code>p</code>.
     * <p>
     * If the polygon intersects the ray, this method returns the index of the polygon edge intersecting the ray, that is, the index of the 
     * first vertex of the directed line segment. The second vertex is always that index + 1, modulus the number of polygon vertices.
     * <p>
     * Valid input: {@code (dirX, dirY)} must be non-zero.
     * 
     * @param vertices
     *          the sequence of <code>(x, y)</code> coordinate pairs of all vertices of the polygon
     * @param originX
     *          the x coordinate of the ray's origin
     * @param originY
     *          the y coordinate of the ray's origin
     * @param dirX
     *          the x coordinate of the ray's direction
     * @param dirY
     *          the y coordinate of the ray's direction
     * @param p
     *          will hold the point of intersection
     * @return the index of the first vertex of the polygon edge that intersects the ray; or <code>-1</code> if the ray does not intersect the polygon
     */
    public static int intersectPolygonRay(Double2R[] vertices, double originX, double originY, double dirX, double dirY, Double2 p) {
        double nearestT = Double.POSITIVE_INFINITY;
        int count = vertices.length;
        int edgeIndex = -1;
        if (count == 0) return -1; // no vertices, no edge to hit
        double aX = vertices[count-1].x(), aY = vertices[count-1].y();
        for (int i = 0; i < count; i++) {
            Double2R b = vertices[i];
            double bX = b.x(), bY = b.y();
            double doaX = originX - aX, doaY = originY - aY;
            double dbaX = bX - aX, dbaY = bY - aY;
            double invDbaDir = 1.0 / (dbaY * dirX - dbaX * dirY);
            double t = (dbaX * doaY - dbaY * doaX) * invDbaDir;
            if (t >= 0.0 && t < nearestT) {
                double t2 = (doaY * dirX - doaX * dirY) * invDbaDir;
                if (t2 >= 0.0 && t2 <= 1.0) {
                    edgeIndex = (i - 1 + count) % count;
                    nearestT = t;
                    p.set(originX + t * dirX, originY + t * dirY);
                }
            }
            aX = bX;
            aY = bY;
        }
        return edgeIndex;
    }

    /**
     * Determine whether the two lines, specified via two points lying on each line, intersect each other, and store the point of intersection
     * into the given vector <code>p</code>.
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param ps1x
     *          the x coordinate of the first point on the first line
     * @param ps1y
     *          the y coordinate of the first point on the first line
     * @param pe1x
     *          the x coordinate of the second point on the first line
     * @param pe1y
     *          the y coordinate of the second point on the first line
     * @param ps2x
     *          the x coordinate of the first point on the second line
     * @param ps2y
     *          the y coordinate of the first point on the second line
     * @param pe2x
     *          the x coordinate of the second point on the second line
     * @param pe2y
     *          the y coordinate of the second point on the second line
     * @param p
     *          will hold the point of intersection
     * @return <code>true</code> iff the two lines intersect; <code>false</code> otherwise
     */
    public static boolean intersectLineLine(double ps1x, double ps1y, double pe1x, double pe1y, double ps2x, double ps2y, double pe2x, double pe2y, Double2 p) {
        return IntersectionGend.intersectLineLine(ps1x, ps1y, pe1x, pe1y, ps2x, ps2y, pe2x, pe2y, p);
    }

    private static boolean separatingAxis(Double2R[] v1s, Double2R[] v2s, double aX, double aY) {
        double minA = Double.POSITIVE_INFINITY, maxA = Double.NEGATIVE_INFINITY;
        double minB = Double.POSITIVE_INFINITY, maxB = Double.NEGATIVE_INFINITY;
        int maxLen = Math.max(v1s.length, v2s.length);
        /* Project both polygons on axis */
        for (int k = 0; k < maxLen; k++) {
            if (k < v1s.length) {
                Double2R v1 = v1s[k];
                double d = v1.x() * aX + v1.y() * aY;
                if (d < minA) minA = d;
                if (d > maxA) maxA = d;
            }
            if (k < v2s.length) {
                Double2R v2 = v2s[k];
                double d = v2.x() * aX + v2.y() * aY;
                if (d < minB) minB = d;
                if (d > maxB) maxB = d;
            }
            /* Early-out if overlap found */
            if (minA <= maxB && minB <= maxA) {
                return false;
            }
        }
        return true;
    }

    /**
     * Test if the two polygons, given via their vertices, intersect.
     * <p>
     * Both polygons must be convex: this is a separating-axis test over the polygons' edge normals.
     * It never misses a real intersection, but for a concave polygon it can report one that does not
     * exist, because a separating line need not be parallel to an edge of a concave polygon.
     * <p>
     * Polygons without area are tested exactly too: a single point, a segment (two vertices) or
     * collinear vertices. An empty array intersects nothing.
     * <p>
     * Valid input: the default range of the package documentation.
     * 
     * @param v1s
     *          the vertices of the first polygon
     * @param v2s
     *          the vertices of the second polygon
     * @return <code>true</code> if the polygons intersect; <code>false</code> otherwise
     */
    public static boolean testPolygonPolygon(Double2R[] v1s, Double2R[] v2s) {
        /* A polygon without vertices intersects nothing */
        if (v1s.length == 0 || v2s.length == 0)
            return false;
        /* Try to find a separating axis using the first polygon's edges */
        for (int i = 0, j = v1s.length - 1; i < v1s.length; j = i, i++) {
            Double2R s = v1s[i], t = v1s[j];
            if (separatingAxis(v1s, v2s, s.y() - t.y(), t.x() - s.x()))
                return false;
        }
        /* Try to find a separating axis using the second polygon's edges */
        for (int i = 0, j = v2s.length - 1; i < v2s.length; j = i, i++) {
            Double2R s = v2s[i], t = v2s[j];
            if (separatingAxis(v1s, v2s, s.y() - t.y(), t.x() - s.x()))
                return false;
        }
        /*
         * A polygon without area - a point, a segment, collinear vertices - has no edge normal
         * across its own extent (a zero-length edge gives the zero axis, on which everything
         * overlaps): its edge directions separate it along its line too, and two such polygons
         * also by the axis joining them (two distinct points).
         */
        boolean flat1 = isFlat(v1s), flat2 = isFlat(v2s);
        if (flat1)
            for (int i = 0, j = v1s.length - 1; i < v1s.length; j = i, i++) {
                Double2R s = v1s[i], t = v1s[j];
                if (separatingAxis(v1s, v2s, t.x() - s.x(), t.y() - s.y()))
                    return false;
            }
        if (flat2)
            for (int i = 0, j = v2s.length - 1; i < v2s.length; j = i, i++) {
                Double2R s = v2s[i], t = v2s[j];
                if (separatingAxis(v1s, v2s, t.x() - s.x(), t.y() - s.y()))
                    return false;
            }
        if (flat1 && flat2) {
            Double2R p = v1s[0], q = v2s[0];
            if (separatingAxis(v1s, v2s, q.x() - p.x(), q.y() - p.y()))
                return false;
        }
        return true;
    }

    /** Whether the polygon's signed area (shoelace sum) is exactly zero: a point, a segment or collinear vertices. */
    private static boolean isFlat(Double2R[] vs) {
        double area2 = 0;
        for (int i = 0, j = vs.length - 1; i < vs.length; j = i, i++)
            area2 += vs[j].x() * vs[i].y() - vs[i].x() * vs[j].y();
        return area2 == 0;
    }

    // ──────────────────────────────────────────────────────────────────────
    //  3-D point-in-volume and AABB containment tests.
    //
    //  Not part of JOML 1's Intersectiond surface (which has only 2-D
    //  testPointAar / testPointCircle), but the natural 3-D analogs.
    // ──────────────────────────────────────────────────────────────────────

    /**
     * Test whether the point {@code (pX, pY, pZ)} lies inside (or on the boundary
     * of) the axis-aligned box defined by {@code (minX..maxX) × (minY..maxY) ×
     * (minZ..maxZ)}.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component.
     *
     * @return {@code true} iff the point is contained in the AABB
     */
    public static boolean testPointAabb(double pX, double pY, double pZ,
            double minX, double minY, double minZ,
            double maxX, double maxY, double maxZ) {
        return IntersectionGend.testPointAabb(pX, pY, pZ, minX, minY, minZ, maxX, maxY, maxZ);
    }

    /**
     * Vec3R-overload of {@link #testPointAabb(double, double, double, double, double, double, double, double, double)}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     */
    public static boolean testPointAabb(Double3R point, Double3R min, Double3R max) {
        return testPointAabb(point.x(), point.y(), point.z(),
                min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
    }

    /**
     * Convenience: point against an {@link DoubleAABB}.
     * <p>
     * Valid input: the minimum corner of {@code aabb} must not exceed the maximum corner of {@code
     * aabb} in any component.
     */
    public static boolean testPointAabb(Double3R point, DoubleAABBR aabb) {
        return testPointAabb(point.x(), point.y(), point.z(),
                aabb.minX(), aabb.minY(), aabb.minZ(), aabb.maxX(), aabb.maxY(), aabb.maxZ());
    }

    /**
     * Test whether the point {@code (pX, pY, pZ)} lies inside (or on the surface
     * of) the sphere with center {@code (centerX, centerY, centerZ)} and
     * <em>squared</em> radius {@code radiusSquared}.
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     *
     * @return {@code true} iff the point is contained in the sphere
     */
    public static boolean testPointSphere(double pX, double pY, double pZ,
            double centerX, double centerY, double centerZ, double radiusSquared) {
        return IntersectionGend.testPointSphere(pX, pY, pZ, centerX, centerY, centerZ, radiusSquared);
    }

    /**
     * Vec3R-overload of {@link #testPointSphere(double, double, double, double, double, double, double)}.
     * <p>
     * Valid input: {@code radiusSquared} must not be negative.
     */
    public static boolean testPointSphere(Double3R point, Double3R center, double radiusSquared) {
        return testPointSphere(point.x(), point.y(), point.z(),
                center.x(), center.y(), center.z(), radiusSquared);
    }

    /**
     * Convenience: point against a {@link DoubleSphere}.
     * <p>
     * Valid input: the radius of {@code sphere} must not be negative.
     */
    public static boolean testPointSphere(Double3R point, DoubleSphereR sphere) {
        return testPointSphere(point.x(), point.y(), point.z(),
                sphere.x(), sphere.y(), sphere.z(), sphere.r() * sphere.r());
    }

    /**
     * Test whether the inner axis-aligned box given as its minimum corner <code>(iMinX, iMinY, iMinZ)</code> and maximum corner <code>(iMaxX, iMaxY, iMaxZ)</code>
     * lies completely inside the outer axis-aligned box given as its minimum corner <code>(oMinX, oMinY, oMinZ)</code> and maximum corner <code>(oMaxX, oMaxY, oMaxZ)</code>.
     * <p>
     * Containment is inclusive, so this method returns <code>true</code> for an inner box that touches a side of the outer box from within.
     * <p>
     * Valid input: {@code (oMinX, oMinY, oMinZ)} must not exceed {@code (oMaxX, oMaxY, oMaxZ)} in
     * any component; {@code (iMinX, iMinY, iMinZ)} must not exceed {@code (iMaxX, iMaxY, iMaxZ)} in
     * any component.
     *
     * @see #testAabbAabbContains(Double3R, Double3R, Double3R, Double3R)
     *
     * @param oMinX
     *              the x coordinate of the minimum corner of the outer axis-aligned box
     * @param oMinY
     *              the y coordinate of the minimum corner of the outer axis-aligned box
     * @param oMinZ
     *              the z coordinate of the minimum corner of the outer axis-aligned box
     * @param oMaxX
     *              the x coordinate of the maximum corner of the outer axis-aligned box
     * @param oMaxY
     *              the y coordinate of the maximum corner of the outer axis-aligned box
     * @param oMaxZ
     *              the z coordinate of the maximum corner of the outer axis-aligned box
     * @param iMinX
     *              the x coordinate of the minimum corner of the inner axis-aligned box
     * @param iMinY
     *              the y coordinate of the minimum corner of the inner axis-aligned box
     * @param iMinZ
     *              the z coordinate of the minimum corner of the inner axis-aligned box
     * @param iMaxX
     *              the x coordinate of the maximum corner of the inner axis-aligned box
     * @param iMaxY
     *              the y coordinate of the maximum corner of the inner axis-aligned box
     * @param iMaxZ
     *              the z coordinate of the maximum corner of the inner axis-aligned box
     * @return <code>true</code> if the inner axis-aligned box is completely contained in the outer axis-aligned box; <code>false</code> otherwise
     */
    public static boolean testAabbAabbContains(
            double oMinX, double oMinY, double oMinZ, double oMaxX, double oMaxY, double oMaxZ,
            double iMinX, double iMinY, double iMinZ, double iMaxX, double iMaxY, double iMaxZ) {
        return IntersectionGend.testAabbAabbContains(oMinX, oMinY, oMinZ, oMaxX, oMaxY, oMaxZ,
                iMinX, iMinY, iMinZ, iMaxX, iMaxY, iMaxZ);
    }

    /**
     * Vec3R-overload of {@link #testAabbAabbContains(double, double, double, double, double, double, double, double, double, double, double, double)}.
     * <p>
     * Valid input: {@code oMin} must not exceed {@code oMax} in any component; {@code iMin} must
     * not exceed {@code iMax} in any component.
     */
    public static boolean testAabbAabbContains(Double3R oMin, Double3R oMax, Double3R iMin, Double3R iMax) {
        return testAabbAabbContains(
                oMin.x(), oMin.y(), oMin.z(), oMax.x(), oMax.y(), oMax.z(),
                iMin.x(), iMin.y(), iMin.z(), iMax.x(), iMax.y(), iMax.z());
    }

    /**
     * Convenience: AABB-contains-AABB at the {@link DoubleAABB} level.
     * <p>
     * Valid input: the minimum corner of {@code outer} must not exceed the maximum corner of {@code
     * outer} in any component; the minimum corner of {@code inner} must not exceed the maximum
     * corner of {@code inner} in any component.
     */
    public static boolean testAabbAabbContains(DoubleAABBR outer, DoubleAABBR inner) {
        return testAabbAabbContains(
                outer.minX(), outer.minY(), outer.minZ(), outer.maxX(), outer.maxY(), outer.maxZ(),
                inner.minX(), inner.minY(), inner.minZ(), inner.maxX(), inner.maxY(), inner.maxZ());
    }

}
