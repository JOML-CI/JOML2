// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteBuffer;

/**
 * An oriented bounding box of double-precision {@code double} components.
 * <p>
 * Extends the read-only view {@link DoubleOBBR} with the mutating surface: in-place self-forms,
 * setters and load methods. A self-form operation writes its result to {@code this} and returns it
 * - unless the {@code joml.returnNew} mode is enabled, in which case it leaves {@code this}
 * unchanged and returns a freshly allocated instance.
 * <p>
 * Instances are created through the {@link Joml} factory methods.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns). Only instances
 * of this library's implementation compare equal to each other; the {@code equals} of an oriented
 * bounding box never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface DoubleOBB extends DoubleOBBR {

    /**
     * Set this oriented bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the oriented bounding box to copy
     * @return this
     */
    @Mutated DoubleOBB set(DoubleOBBR v);

    /**
     * Set this oriented bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code cX} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param cY the {@code cY} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param cZ the {@code cZ} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param uXx the {@code uXx} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param uXy the {@code uXy} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param uXz the {@code uXz} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param uYx the {@code uYx} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param uYy the {@code uYy} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param uYz the {@code uYz} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param uZx the {@code uZx} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param uZy the {@code uZy} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param uZz the {@code uZz} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param hsX the {@code hsX} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param hsY the {@code hsY} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @param hsZ the {@code hsZ} component of the oriented bounding box
     *        {@code (cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ)}
     * @return this
     */
    @Mutated DoubleOBB set(double cX, double cY, double cZ, double uXx, double uXy, double uXz, double uYx, double uYy, double uYz, double uZx, double uZy, double uZz, double hsX, double hsY, double hsZ);

    /**
     * Set the local coordinate axes of this oriented bounding box to {@code axisX}, {@code axisY}
     * and {@code axisZ}.
     * <p>
     * Valid input: {@code axisX}, {@code axisY} and {@code axisZ} must be orthonormal.
     *
     * @param axisX the new local X axis
     * @param axisY the new local Y axis
     * @param axisZ the new local Z axis
     * @return this
     */
    @Mutated default DoubleOBB setAxes(Double3R axisX, Double3R axisY, Double3R axisZ) { return setAxes(axisX, axisY, axisZ, this); }

    /**
     * Set the local coordinate axes of this oriented bounding box to ({@code axisXX},
     * {@code axisXY}, {@code axisXZ}), ({@code axisYX}, {@code axisYY}, {@code axisYZ}) and
     * ({@code axisZX}, {@code axisZY}, {@code axisZZ}).
     * <p>
     * Valid input: {@code (axisXX, axisXY, axisXZ)}, {@code (axisYX, axisYY, axisYZ)} and
     * {@code (axisZX, axisZY, axisZZ)} must be orthonormal.
     *
     * @param axisXX the {@code x} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisXY the {@code y} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisXZ the {@code z} component of the vector {@code (axisXX, axisXY, axisXZ)}
     * @param axisYX the {@code x} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisYY the {@code y} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisYZ the {@code z} component of the vector {@code (axisYX, axisYY, axisYZ)}
     * @param axisZX the {@code x} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param axisZY the {@code y} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @param axisZZ the {@code z} component of the vector {@code (axisZX, axisZY, axisZZ)}
     * @return this
     */
    @Mutated default DoubleOBB setAxes(double axisXX, double axisXY, double axisXZ, double axisYX, double axisYY, double axisYZ, double axisZX, double axisZY, double axisZZ) { return setAxes(axisXX, axisXY, axisXZ, axisYX, axisYY, axisYZ, axisZX, axisZY, axisZZ, this); }

    /**
     * Set the center of this oriented bounding box to {@code c}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @return this
     */
    @Mutated default DoubleOBB setCenter(Double3R c) { return setCenter(c, this); }

    /**
     * Set the center of this oriented bounding box to ({@code x}, {@code y}, {@code z}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this
     */
    @Mutated default DoubleOBB setCenter(double x, double y, double z) { return setCenter(x, y, z, this); }

    /**
     * Set the half extents of this oriented bounding box to {@code h}.
     * <p>
     * Valid input: each component of {@code h} must not be negative.
     *
     * @param h the new half extents
     * @return this
     */
    @Mutated default DoubleOBB setHalfSize(Double3R h) { return setHalfSize(h, this); }

    /**
     * Set the half extents of this oriented bounding box to ({@code x}, {@code y}, {@code z}).
     * <p>
     * Valid input: each component of {@code (x, y, z)} must not be negative.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this
     */
    @Mutated default DoubleOBB setHalfSize(double x, double y, double z) { return setHalfSize(x, y, z, this); }

    /**
     * Reset the orientation of this oriented bounding box to identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this
     */
    @Mutated default DoubleOBB setIdentityOrientation() { return setIdentityOrientation(this); }

    /**
     * Set the orientation of this oriented bounding box to {@code q}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the new orientation
     * @return this
     */
    @Mutated default DoubleOBB setOrientation(DoubleQuatR q) { return setOrientation(q, this); }

    /**
     * Set the orientation of this oriented bounding box to ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this
     */
    @Mutated default DoubleOBB setOrientation(double x, double y, double z, double w) { return setOrientation(x, y, z, w, this); }

    /**
     * Convert this oriented bounding box to {@code float} precision, returning the result as a new
     * instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatOBB} holding the result
     */
    default FloatOBB toFloat() { return toFloat(Joml.floatOBB()); }

    /**
     * Set this oriented bounding box to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated DoubleOBB makeIdentity();

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them).
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleOBB transform(Double3x4R m) { return transform(m, Joml.RETURN_NEW ? Joml.doubleOBB() : this); }

    /**
     * Transform this oriented bounding box by {@code m}: the center is transformed as a point and
     * the axes as directions, which are then made orthonormal again (the transformed X axis, the Y
     * axis perpendicular to it, and their cross product); each half-size becomes the transformed
     * box's extent along its new axis, so the result encloses the transformed box - exactly when
     * the matrix keeps the axes perpendicular (a rotation times a scale along them).
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the axes of this oriented bounding box must be orthonormal.
     *
     * @param m the transformation matrix to apply
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleOBB transform(Double4x4R m) { return transform(m, Joml.RETURN_NEW ? Joml.doubleOBB() : this); }

    /**
     * Translate this oriented bounding box by {@code delta}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleOBB translate(Double3R delta) { return translate(delta, Joml.RETURN_NEW ? Joml.doubleOBB() : this); }

    /**
     * Translate this oriented bounding box by ({@code x}, {@code y}, {@code z}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleOBB translate(double x, double y, double z) { return translate(x, y, z, Joml.RETURN_NEW ? Joml.doubleOBB() : this); }

    /**
     * Set this oriented bounding box from its center, its three local axes and its half-size.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the box
     * @param axisX the local x axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param axisY the local y axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param axisZ the local z axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param halfSize the half extent of the box along each local axis
     * @return this
     */
    @Mutated default DoubleOBB set(Double3R center, Double3R axisX, Double3R axisY, Double3R axisZ, Double3R halfSize) { return set(center.x(), center.y(), center.z(), axisX.x(), axisX.y(), axisX.z(), axisY.x(), axisY.y(), axisY.z(), axisZ.x(), axisZ.y(), axisZ.z(), halfSize.x(), halfSize.y(), halfSize.z()); }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default DoubleOBB load(double[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated DoubleOBB load(double[] src, int offset);

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default DoubleOBB load(DoubleBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default DoubleOBB loadAbsolute(DoubleBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
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
     * @param src the source buffer
     * @return this
     */
    @Mutated DoubleOBB loadAbsolute(int index, DoubleBuffer src);

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default DoubleOBB loadRelative(DoubleBuffer src) {
        if (src.remaining() < 15) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 15);
        return this;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default DoubleOBB load(ByteBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default DoubleOBB loadAbsolute(ByteBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated DoubleOBB loadAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default DoubleOBB loadRelative(ByteBuffer src) {
        if (src.remaining() < 120) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 120);
        return this;
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated DoubleOBB loadUnsafe(long address);

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default DoubleOBB load(float[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated DoubleOBB load(float[] src, int offset);

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default DoubleOBB load(FloatBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default DoubleOBB loadAbsolute(FloatBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
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
     * @param src the source buffer
     * @return this
     */
    @Mutated DoubleOBB loadAbsolute(int index, FloatBuffer src);

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default DoubleOBB loadRelative(FloatBuffer src) {
        if (src.remaining() < 15) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 15);
        return this;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default DoubleOBB loadFloat(ByteBuffer src) { return loadFloatAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default DoubleOBB loadFloatAbsolute(ByteBuffer src) { return loadFloatAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated DoubleOBB loadFloatAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default DoubleOBB loadFloatRelative(ByteBuffer src) {
        if (src.remaining() < 60) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadFloatAbsolute(pos, src);
        src.position(pos + 60);
        return this;
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated DoubleOBB loadFloatUnsafe(long address);
}
