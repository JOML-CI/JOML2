// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatQuat.
package org.joml2.kotlin

import org.joml2.*

/** [FloatQuat.add] as the `+` operator. */
inline operator fun FloatQuat.plus(other: FloatQuat): FloatQuat = add(other)
/** [FloatQuat.mul] as the `*` operator. */
inline operator fun FloatQuat.times(scalar: Float): FloatQuat = mul(scalar)
/** [FloatQuat.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: FloatQuat): FloatQuat = v.mul(this)
/** [FloatQuat.negate] as the unary `-` operator. */
inline operator fun FloatQuat.unaryMinus(): FloatQuat = negate()
/** [FloatQuat.sub] as the `-` operator. */
inline operator fun FloatQuat.minus(other: FloatQuat): FloatQuat = sub(other)
/** [FloatQuat.mul] as the `*` operator. */
inline operator fun FloatQuat.times(other: FloatQuat): FloatQuat = mul(other)
/** [FloatQuat.dot] as an infix function, so `a dot b` parses. */
inline infix fun FloatQuat.dot(other: FloatQuat): Float = dot(other)
/** [FloatQuat.preRotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.preRotateX(angle: Angle): FloatQuat = preRotateX(angle.radians.toFloat())
/** [FloatQuat.preRotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.preRotateY(angle: Angle): FloatQuat = preRotateY(angle.radians.toFloat())
/** [FloatQuat.preRotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.preRotateZ(angle: Angle): FloatQuat = preRotateZ(angle.radians.toFloat())
/** [FloatQuat.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.rotateAxis(angle: Angle, axis: Float3): FloatQuat = rotateAxis(angle.radians.toFloat(), axis)
/** [FloatQuat.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.rotateX(angle: Angle): FloatQuat = rotateX(angle.radians.toFloat())
/** [FloatQuat.rotateXYZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.rotateXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): FloatQuat = rotateXYZ(angleX.radians.toFloat(), angleY.radians.toFloat(), angleZ.radians.toFloat())
/** [FloatQuat.rotateXZY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.rotateXZY(angleX: Angle, angleZ: Angle, angleY: Angle): FloatQuat = rotateXZY(angleX.radians.toFloat(), angleZ.radians.toFloat(), angleY.radians.toFloat())
/** [FloatQuat.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.rotateY(angle: Angle): FloatQuat = rotateY(angle.radians.toFloat())
/** [FloatQuat.rotateYXZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.rotateYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): FloatQuat = rotateYXZ(angleY.radians.toFloat(), angleX.radians.toFloat(), angleZ.radians.toFloat())
/** [FloatQuat.rotateYZX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.rotateYZX(angleY: Angle, angleZ: Angle, angleX: Angle): FloatQuat = rotateYZX(angleY.radians.toFloat(), angleZ.radians.toFloat(), angleX.radians.toFloat())
/** [FloatQuat.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.rotateZ(angle: Angle): FloatQuat = rotateZ(angle.radians.toFloat())
/** [FloatQuat.rotateZXY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.rotateZXY(angleZ: Angle, angleX: Angle, angleY: Angle): FloatQuat = rotateZXY(angleZ.radians.toFloat(), angleX.radians.toFloat(), angleY.radians.toFloat())
/** [FloatQuat.rotateZYX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatQuat.rotateZYX(angleZ: Angle, angleY: Angle, angleX: Angle): FloatQuat = rotateZYX(angleZ.radians.toFloat(), angleY.radians.toFloat(), angleX.radians.toFloat())
/** [FloatQuat.transform] as the `*` operator. */
inline operator fun FloatQuat.times(v: Float3): Float3 = transform(v)
/** Component 1 of this FloatQuat (`x()`), for destructuring declarations. */
inline operator fun FloatQuat.component1(): Float = x()
/** Component 2 of this FloatQuat (`y()`), for destructuring declarations. */
inline operator fun FloatQuat.component2(): Float = y()
/** Component 3 of this FloatQuat (`z()`), for destructuring declarations. */
inline operator fun FloatQuat.component3(): Float = z()
/** Component 4 of this FloatQuat (`w()`), for destructuring declarations. */
inline operator fun FloatQuat.component4(): Float = w()
/** A copy of this FloatQuat with the named components replaced; every component left out keeps its current value. */
inline fun FloatQuat.copy(x: Float = x(), y: Float = y(), z: Float = z(), w: Float = w()): FloatQuat = FloatQuat(x, y, z, w)
/** The components of this FloatQuat in a new `FloatArray`, in storage order. */
inline fun FloatQuat.toFloatArray(): FloatArray = store(FloatArray(4))
/** A FloatQuat read from this array in storage order, starting at index 0. */
inline fun FloatArray.toFloatQuat(): FloatQuat = FloatQuat.load(this)
/** The component of this FloatQuat at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 3 indices (`v[a, b, ...] = x`) resolves to the Java 4-argument value factory `FloatQuat.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun FloatQuat.get(index: Int): Float = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    3 -> w()
    else -> throw IndexOutOfBoundsException("FloatQuat index: $index")
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object FloatQuatFactory {
    /** [FloatQuat.makeRotationAxis] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationAxis(angle: Angle, axis: Float3): FloatQuat = FloatQuat.makeRotationAxis(angle.radians.toFloat(), axis)
    /** [FloatQuat.makeRotationX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationX(angle: Angle): FloatQuat = FloatQuat.makeRotationX(angle.radians.toFloat())
    /** [FloatQuat.makeRotationXYZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): FloatQuat = FloatQuat.makeRotationXYZ(angleX.radians.toFloat(), angleY.radians.toFloat(), angleZ.radians.toFloat())
    /** [FloatQuat.makeRotationXZY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXZY(angleX: Angle, angleZ: Angle, angleY: Angle): FloatQuat = FloatQuat.makeRotationXZY(angleX.radians.toFloat(), angleZ.radians.toFloat(), angleY.radians.toFloat())
    /** [FloatQuat.makeRotationY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationY(angle: Angle): FloatQuat = FloatQuat.makeRotationY(angle.radians.toFloat())
    /** [FloatQuat.makeRotationYXZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): FloatQuat = FloatQuat.makeRotationYXZ(angleY.radians.toFloat(), angleX.radians.toFloat(), angleZ.radians.toFloat())
    /** [FloatQuat.makeRotationYZX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYZX(angleY: Angle, angleZ: Angle, angleX: Angle): FloatQuat = FloatQuat.makeRotationYZX(angleY.radians.toFloat(), angleZ.radians.toFloat(), angleX.radians.toFloat())
    /** [FloatQuat.makeRotationZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZ(angle: Angle): FloatQuat = FloatQuat.makeRotationZ(angle.radians.toFloat())
    /** [FloatQuat.makeRotationZXY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZXY(angleZ: Angle, angleX: Angle, angleY: Angle): FloatQuat = FloatQuat.makeRotationZXY(angleZ.radians.toFloat(), angleX.radians.toFloat(), angleY.radians.toFloat())
    /** [FloatQuat.makeRotationZYX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZYX(angleZ: Angle, angleY: Angle, angleX: Angle): FloatQuat = FloatQuat.makeRotationZYX(angleZ.radians.toFloat(), angleY.radians.toFloat(), angleX.radians.toFloat())
}
