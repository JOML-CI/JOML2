// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteBuffer;

/**
 * Read-only view of an axis-aligned bounding box of double-precision {@code double} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link DoubleAABB}. APIs that only read an
 * axis-aligned bounding box should accept {@code DoubleAABBR}, so callers can pass mutable
 * instances without exposing them to modification.
 * <p>
 * Arguments of type {@code DoubleAABBR} must be instances created by the library ({@link Joml}
 * factories / the library's own types); the implementations read cached state through the library's
 * own classes, so foreign implementations of the {@code *R} interfaces are not supported as
 * arguments.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns). Only instances
 * of this library's implementation compare equal to each other; the {@code equals} of an
 * axis-aligned bounding box never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface DoubleAABBR {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 48;

    /** {@return a shared all-zero axis-aligned bounding box} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleAABBR ZERO() { return Holder.ZERO; }


    /**
     * Set the maximum corner of this axis-aligned bounding box to {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB setMax(Double3R max, @Mutated DoubleAABB dest);

    /**
     * Set the maximum corner of this axis-aligned bounding box to ({@code x}, {@code y}, {@code z})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB setMax(double x, double y, double z, @Mutated DoubleAABB dest);

    /**
     * Set the minimum corner of this axis-aligned bounding box to {@code min} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB setMin(Double3R min, @Mutated DoubleAABB dest);

    /**
     * Set the minimum corner of this axis-aligned bounding box to ({@code x}, {@code y}, {@code z})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB setMin(double x, double y, double z, @Mutated DoubleAABB dest);

    /**
     * Convert this axis-aligned bounding box to {@code float} precision and store the result in
     * {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatAABB toFloat(@Mutated FloatAABB dest);

    /**
     * Swap the minimum and maximum bounds of this axis-aligned bounding box where necessary so the
     * bounds are valid and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB correctBounds(@Mutated DoubleAABB dest);

    /**
     * Transform this axis-aligned bounding box by {@code m} and set it to the axis-aligned box
     * enclosing the transformed box and store the result in {@code dest}.
     * <p>
     * Valid input: the minimum corner of this axis-aligned bounding box must not exceed the maximum
     * corner of this axis-aligned bounding box in any component.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB transform(Double3x4R m, @Mutated DoubleAABB dest);

    /**
     * Transform this axis-aligned bounding box by {@code m} and set it to the axis-aligned box
     * enclosing the transformed box and store the result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the minimum corner of this axis-aligned bounding box must not exceed the maximum
     * corner of this axis-aligned bounding box in any component.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB transform(Double4x4R m, @Mutated DoubleAABB dest);

    /**
     * Translate this axis-aligned bounding box by {@code delta} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB translate(Double3R delta, @Mutated DoubleAABB dest);

    /**
     * Translate this axis-aligned bounding box by ({@code x}, {@code y}, {@code z}) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB translate(double x, double y, double z, @Mutated DoubleAABB dest);

    /**
     * Set this axis-aligned bounding box to the union of itself and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the axis-aligned bounding box to include in the union
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB union(DoubleAABBR other, @Mutated DoubleAABB dest);

    /**
     * Set this axis-aligned bounding box to the union of itself and ({@code minX}, {@code minY},
     * {@code minZ}, {@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minY the {@code minY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minZ the {@code minZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxX the {@code maxX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxY the {@code maxY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxZ the {@code maxZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, @Mutated DoubleAABB dest);

    /**
     * Grow this axis-aligned bounding box to include the point {@code p} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB union(Double3R p, @Mutated DoubleAABB dest);

    /**
     * Grow this axis-aligned bounding box to include the point ({@code x}, {@code y}, {@code z})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB union(double x, double y, double z, @Mutated DoubleAABB dest);

    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Double3 closestPointToPoint(Double3R p, @Mutated Double3 dest);

    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param x the {@code x} component of the point {@code (x, y, z)} to find the closest point to
     * @param y the {@code y} component of the point {@code (x, y, z)} to find the closest point to
     * @param z the {@code z} component of the point {@code (x, y, z)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Double3 closestPointToPoint(double x, double y, double z, @Mutated Double3 dest);

    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to find the closest point to (also receives the result)
     * @return {@code p}
     */
    default Double3 closestPointToPoint(@Mutated Double3 p) { return closestPointToPoint(p, p); }

    /**
     * Compute the squared distance between this axis-aligned bounding box and the given box, i.e.
     * the squared length of the shortest vector between any two points of the two boxes; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; the minimum corner of {@code other} must not
     * exceed the maximum corner of {@code other} in any component.
     *
     * @param other the box to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given box, i.e.
     *        the squared length of the shortest vector between any two points of the two boxes;
     *        zero when they overlap or touch
     */
    double distanceSquaredToAABB(DoubleAABBR other);

    /**
     * Compute the squared distance between this axis-aligned bounding box and the given box, i.e.
     * the squared length of the shortest vector between any two points of the two boxes; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; {@code (minX, minY, minZ)} must not exceed
     * {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given box, i.e.
     *        the squared length of the shortest vector between any two points of the two boxes;
     *        zero when they overlap or touch
     */
    double distanceSquaredToAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);

    /**
     * Compute the squared distance between this axis-aligned bounding box and the given point, i.e.
     * the squared length of the difference between the point and its per-axis clamp into the box's
     * bounds; zero for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given point, i.e.
     *        the squared length of the difference between the point and its per-axis clamp into the
     *        box's bounds; zero for a point inside or on the box
     */
    double distanceSquaredToPoint(Double3R p);

    /**
     * Compute the squared distance between this axis-aligned bounding box and the given point, i.e.
     * the squared length of the difference between the point and its per-axis clamp into the box's
     * bounds; zero for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param x the {@code x} component of the point {@code (x, y, z)} to measure the distance to
     * @param y the {@code y} component of the point {@code (x, y, z)} to measure the distance to
     * @param z the {@code z} component of the point {@code (x, y, z)} to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given point, i.e.
     *        the squared length of the difference between the point and its per-axis clamp into the
     *        box's bounds; zero for a point inside or on the box
     */
    double distanceSquaredToPoint(double x, double y, double z);

    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphere the sphere to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    double distanceSquaredToSphere(DoubleSphereR sphere);

    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param x the {@code x} component of the sphere {@code (x, y, z, r)} to measure the distance
     *        to
     * @param y the {@code y} component of the sphere {@code (x, y, z, r)} to measure the distance
     *        to
     * @param z the {@code z} component of the sphere {@code (x, y, z, r)} to measure the distance
     *        to
     * @param r the {@code r} component of the sphere {@code (x, y, z, r)} to measure the distance
     *        to
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    double distanceSquaredToSphere(double x, double y, double z, double r);

    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    double distanceSquaredToSphere(Double3R center, double radius);

    /**
     * Compute the distance between this axis-aligned bounding box and the given box, i.e. the
     * length of the shortest vector between any two points of the two boxes; zero when they overlap
     * or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; the minimum corner of {@code other} must not
     * exceed the maximum corner of {@code other} in any component.
     *
     * @param other the box to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given box, i.e. the
     *        length of the shortest vector between any two points of the two boxes; zero when they
     *        overlap or touch
     */
    double distanceToAABB(DoubleAABBR other);

    /**
     * Compute the distance between this axis-aligned bounding box and the given box, i.e. the
     * length of the shortest vector between any two points of the two boxes; zero when they overlap
     * or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; {@code (minX, minY, minZ)} must not exceed
     * {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given box, i.e. the
     *        length of the shortest vector between any two points of the two boxes; zero when they
     *        overlap or touch
     */
    double distanceToAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);

    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    double distanceToPlane(DoublePlaneR plane);

    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code (a, b, c, d)} must be non-zero.
     *
     * @param a the {@code a} component of the plane {@code (a, b, c, d)} to measure the distance to
     * @param b the {@code b} component of the plane {@code (a, b, c, d)} to measure the distance to
     * @param c the {@code c} component of the plane {@code (a, b, c, d)} to measure the distance to
     * @param d the {@code d} component of the plane {@code (a, b, c, d)} to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    double distanceToPlane(double a, double b, double c, double d);

    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    double distanceToPlane(Double4R plane);

    /**
     * Compute the distance between this axis-aligned bounding box and the given point, i.e. the
     * length of the difference between the point and its per-axis clamp into the box's bounds; zero
     * for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given point, i.e. the
     *        length of the difference between the point and its per-axis clamp into the box's
     *        bounds; zero for a point inside or on the box
     */
    double distanceToPoint(Double3R p);

    /**
     * Compute the distance between this axis-aligned bounding box and the given point, i.e. the
     * length of the difference between the point and its per-axis clamp into the box's bounds; zero
     * for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param x the {@code x} component of the point {@code (x, y, z)} to measure the distance to
     * @param y the {@code y} component of the point {@code (x, y, z)} to measure the distance to
     * @param z the {@code z} component of the point {@code (x, y, z)} to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given point, i.e. the
     *        length of the difference between the point and its per-axis clamp into the box's
     *        bounds; zero for a point inside or on the box
     */
    double distanceToPoint(double x, double y, double z);

    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphere the sphere to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    double distanceToSphere(DoubleSphereR sphere);

    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param x the {@code x} component of the sphere {@code (x, y, z, r)} to measure the distance
     *        to
     * @param y the {@code y} component of the sphere {@code (x, y, z, r)} to measure the distance
     *        to
     * @param z the {@code z} component of the sphere {@code (x, y, z, r)} to measure the distance
     *        to
     * @param r the {@code r} component of the sphere {@code (x, y, z, r)} to measure the distance
     *        to
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    double distanceToSphere(double x, double y, double z, double r);

    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    double distanceToSphere(Double3R center, double radius);

    /**
     * Get the center of this axis-aligned bounding box and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getCenter(@Mutated Double3 dest);

    /**
     * Get the maximum corner of this axis-aligned bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getMax(@Mutated Double3 dest);

    /**
     * Get the minimum corner of this axis-aligned bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getMin(@Mutated Double3 dest);

    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this axis-aligned
     * bounding box and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getSize(@Mutated Double3 dest);

    /**
     * Determine whether this axis-aligned bounding box is valid, i.e. no minimum bound exceeds its
     * maximum.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return {@code true} if this axis-aligned bounding box is valid, i.e. no minimum bound
     *        exceeds its maximum, {@code false} otherwise
     */
    boolean isValid();
    /** {@return the value of the {@code minX} component} <p>Valid input: any value, NaN and the infinities included. */
    double minX();
    /** {@return the value of the {@code minY} component} <p>Valid input: any value, NaN and the infinities included. */
    double minY();
    /** {@return the value of the {@code minZ} component} <p>Valid input: any value, NaN and the infinities included. */
    double minZ();
    /** {@return the value of the {@code maxX} component} <p>Valid input: any value, NaN and the infinities included. */
    double maxX();
    /** {@return the value of the {@code maxY} component} <p>Valid input: any value, NaN and the infinities included. */
    double maxY();
    /** {@return the value of the {@code maxZ} component} <p>Valid input: any value, NaN and the infinities included. */
    double maxZ();

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default double[] store(@Mutated double[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    double[] store(@Mutated double[] dest, int offset);

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer store(@Mutated DoubleBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer storeAbsolute(@Mutated DoubleBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer dest);

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default DoubleBuffer storeRelative(@Mutated DoubleBuffer dest) {
        if (dest.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 6);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer store(@Mutated ByteBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeAbsolute(@Mutated ByteBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 48);
        return dest;
    }

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    DoubleAABB storeUnsafe(long address);

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default float[] store(@Mutated float[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    float[] store(@Mutated float[] dest, int offset);

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer store(@Mutated FloatBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer storeAbsolute(@Mutated FloatBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer dest);

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default FloatBuffer storeRelative(@Mutated FloatBuffer dest) {
        if (dest.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 6);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeFloat(@Mutated ByteBuffer dest) { return storeFloatAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeFloatAbsolute(@Mutated ByteBuffer dest) { return storeFloatAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeFloatAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeFloatRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeFloatAbsolute(pos, dest);
        dest.position(pos + 24);
        return dest;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    DoubleAABB storeFloatUnsafe(long address);

    /** {@return whether all components of this axis-aligned bounding box are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    boolean isFinite();

    /** {@return whether any component of this axis-aligned bounding box is NaN} <p>Valid input: any value, NaN and the infinities included. */
    boolean isNaN();

    /**
     * Compare this axis-aligned bounding box component-wise against {@code other}, allowing a difference
     * of at most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the axis-aligned bounding box to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    boolean equalsEpsilon(DoubleAABBR other, double epsilon);

    /**
     * Compare this axis-aligned bounding box with the given object for element-wise equality.
     * <p>
     * Each component is compared bitwise, as by {@code Double.doubleToLongBits}: {@code 0.0} and
     * {@code -0.0} are not equal, and NaN is equal to NaN. Use {@link #equalsEpsilon} for a
     * tolerant comparison.
     * <p>
     * Only instances of this library's implementation compare equal to each other; any other object
     * yields {@code false}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param obj the object to compare with
     * @return {@code true} if {@code obj} is an axis-aligned bounding box of this library with
     *        element-wise equal components, {@code false} otherwise
     */
    boolean equals(@org.jspecify.annotations.Nullable Object obj);

    /**
     * Compute a hash code consistent with {@link #equals}: it is derived from the components via
     * {@code Double.doubleToLongBits} alone.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return the hash code of this axis-aligned bounding box
     */
    int hashCode();

    /**
     * Determine whether this axis-aligned bounding box contains the given point (boundary
     * inclusive). Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @param z the z coordinate of the point
     * @return {@code true} if the given point lies inside or on this axis-aligned bounding box,
     *        {@code false} otherwise
     */
    boolean containsPoint(double x, double y, double z);

    /**
     * Determine whether this axis-aligned bounding box contains the given point (boundary
     * inclusive). Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @return {@code true} if the given point lies inside or on this axis-aligned bounding box,
     *        {@code false} otherwise
     */
    boolean containsPoint(Double3R p);

    /**
     * Determine whether this axis-aligned bounding box contains the given axis-aligned box
     * (boundary inclusive). Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the axis-aligned box to test for containment
     * @return {@code true} if the given box lies entirely inside this axis-aligned bounding box,
     *        boundary included, {@code false} otherwise
     */
    boolean containsAABB(DoubleAABBR o);

    /**
     * Determine whether this axis-aligned bounding box intersects the given axis-aligned box.
     * Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the axis-aligned box to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given axis-aligned box
     *        intersect, {@code false} otherwise
     */
    boolean intersectsAABB(DoubleAABBR o);

    /**
     * Determine whether this axis-aligned bounding box intersects the given axis-aligned box swept
     * by the given velocity. Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the axis-aligned box that moves
     * @param vX the x component of the velocity
     * @param vY the y component of the velocity
     * @param vZ the z component of the velocity
     * @return {@code true} if the moving box meets this axis-aligned bounding box at any point of
     *        the step, {@code false} otherwise
     */
    boolean intersectsSweptAABB(DoubleAABBR other, double vX, double vY, double vZ);

    /**
     * Determine whether this axis-aligned bounding box intersects the given axis-aligned box swept
     * by the given velocity. Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the axis-aligned box that moves
     * @param velocity the velocity the given box moves by over one step
     * @return {@code true} if the moving box meets this axis-aligned bounding box at any point of
     *        the step, {@code false} otherwise
     */
    boolean intersectsSweptAABB(DoubleAABBR other, Double3R velocity);

    /**
     * Determine whether this axis-aligned bounding box intersects the given sphere. Delegates to
     * the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sph the sphere to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given sphere intersect,
     *        {@code false} otherwise
     */
    boolean intersectsSphere(DoubleSphereR sph);

    /**
     * Determine whether this axis-aligned bounding box intersects the given plane. Delegates to the
     * shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param plane the plane to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given plane intersect,
     *        {@code false} otherwise
     */
    boolean intersectsPlane(DoublePlaneR plane);

    /**
     * Determine whether this axis-aligned bounding box intersects the given ray. Delegates to the
     * shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param r the ray to test for intersection
     * @return {@code true} if this axis-aligned bounding box and the given ray intersect,
     *        {@code false} otherwise
     */
    boolean intersectsRay(DoubleRayR r);

    /**
     * Determine whether this axis-aligned bounding box intersects the given ray and, if so, the
     * values of <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i> at the near and far
     * points of intersection. Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param r the ray to intersect
     * @param dest will hold the values of <i>t</i> in the ray equation <i>p(t) = origin + t *
     *        dir</i> at the near and far points of intersection
     * @return {@code true} if the ray intersects this axis-aligned bounding box, {@code false}
     *        otherwise
     */
    boolean intersectRay(DoubleRayR r, @Mutated Double2 dest);

    /** Backs {@code ZERO()}: defers the shared instance's
     *  allocation to first use, avoiding a class-initialization cycle with the
     *  implementation class. Not part of the public API. */
    final class Holder {
        private Holder() {}
        static final DoubleAABBR ZERO = Joml.doubleAABB(0, 0, 0, 0, 0, 0);
    }
}
