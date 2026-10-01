// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatDualQuat.
package org.joml2.kotlin

import org.joml2.*

/** [FloatDualQuat.add] as the `+` operator. */
inline operator fun FloatDualQuat.plus(other: FloatDualQuat): FloatDualQuat = add(other)
/** [FloatDualQuat.mul] as the `*` operator. */
inline operator fun FloatDualQuat.times(scalar: Float): FloatDualQuat = mul(scalar)
/** [FloatDualQuat.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: FloatDualQuat): FloatDualQuat = v.mul(this)
/** [FloatDualQuat.negate] as the unary `-` operator. */
inline operator fun FloatDualQuat.unaryMinus(): FloatDualQuat = negate()
/** [FloatDualQuat.sub] as the `-` operator. */
inline operator fun FloatDualQuat.minus(other: FloatDualQuat): FloatDualQuat = sub(other)
/** [FloatDualQuat.mul] as the `*` operator. */
inline operator fun FloatDualQuat.times(other: FloatDualQuat): FloatDualQuat = mul(other)
/** [FloatDualQuat.dot] as an infix function, so `a dot b` parses. */
inline infix fun FloatDualQuat.dot(other: FloatDualQuat): Float = dot(other)
/** [FloatDualQuat.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatDualQuat.rotateAxis(angle: Angle, axis: Float3): FloatDualQuat = rotateAxis(angle.radians.toFloat(), axis)
/** [FloatDualQuat.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatDualQuat.rotateX(angle: Angle): FloatDualQuat = rotateX(angle.radians.toFloat())
/** [FloatDualQuat.rotateXYZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatDualQuat.rotateXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): FloatDualQuat = rotateXYZ(angleX.radians.toFloat(), angleY.radians.toFloat(), angleZ.radians.toFloat())
/** [FloatDualQuat.rotateXZY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatDualQuat.rotateXZY(angleX: Angle, angleZ: Angle, angleY: Angle): FloatDualQuat = rotateXZY(angleX.radians.toFloat(), angleZ.radians.toFloat(), angleY.radians.toFloat())
/** [FloatDualQuat.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatDualQuat.rotateY(angle: Angle): FloatDualQuat = rotateY(angle.radians.toFloat())
/** [FloatDualQuat.rotateYXZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatDualQuat.rotateYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): FloatDualQuat = rotateYXZ(angleY.radians.toFloat(), angleX.radians.toFloat(), angleZ.radians.toFloat())
/** [FloatDualQuat.rotateYZX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatDualQuat.rotateYZX(angleY: Angle, angleZ: Angle, angleX: Angle): FloatDualQuat = rotateYZX(angleY.radians.toFloat(), angleZ.radians.toFloat(), angleX.radians.toFloat())
/** [FloatDualQuat.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatDualQuat.rotateZ(angle: Angle): FloatDualQuat = rotateZ(angle.radians.toFloat())
/** [FloatDualQuat.rotateZXY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatDualQuat.rotateZXY(angleZ: Angle, angleX: Angle, angleY: Angle): FloatDualQuat = rotateZXY(angleZ.radians.toFloat(), angleX.radians.toFloat(), angleY.radians.toFloat())
/** [FloatDualQuat.rotateZYX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun FloatDualQuat.rotateZYX(angleZ: Angle, angleY: Angle, angleX: Angle): FloatDualQuat = rotateZYX(angleZ.radians.toFloat(), angleY.radians.toFloat(), angleX.radians.toFloat())
/** [FloatDualQuat.transform] as the `*` operator. */
inline operator fun FloatDualQuat.times(p: Float3): Float3 = transform(p)
/** Component 1 of this FloatDualQuat (`rX()`), for destructuring declarations. */
inline operator fun FloatDualQuat.component1(): Float = rX()
/** Component 2 of this FloatDualQuat (`rY()`), for destructuring declarations. */
inline operator fun FloatDualQuat.component2(): Float = rY()
/** Component 3 of this FloatDualQuat (`rZ()`), for destructuring declarations. */
inline operator fun FloatDualQuat.component3(): Float = rZ()
/** Component 4 of this FloatDualQuat (`rW()`), for destructuring declarations. */
inline operator fun FloatDualQuat.component4(): Float = rW()
/** Component 5 of this FloatDualQuat (`dX()`), for destructuring declarations. */
inline operator fun FloatDualQuat.component5(): Float = dX()
/** Component 6 of this FloatDualQuat (`dY()`), for destructuring declarations. */
inline operator fun FloatDualQuat.component6(): Float = dY()
/** Component 7 of this FloatDualQuat (`dZ()`), for destructuring declarations. */
inline operator fun FloatDualQuat.component7(): Float = dZ()
/** Component 8 of this FloatDualQuat (`dW()`), for destructuring declarations. */
inline operator fun FloatDualQuat.component8(): Float = dW()
/** A copy of this FloatDualQuat with the named components replaced; every component left out keeps its current value. */
inline fun FloatDualQuat.copy(rX: Float = rX(), rY: Float = rY(), rZ: Float = rZ(), rW: Float = rW(), dX: Float = dX(), dY: Float = dY(), dZ: Float = dZ(), dW: Float = dW()): FloatDualQuat = FloatDualQuat(rX, rY, rZ, rW, dX, dY, dZ, dW)
/** The components of this FloatDualQuat in a new `FloatArray`, in storage order. */
inline fun FloatDualQuat.toFloatArray(): FloatArray = store(FloatArray(8))
/** A FloatDualQuat read from this array in storage order, starting at index 0. */
inline fun FloatArray.toFloatDualQuat(): FloatDualQuat = FloatDualQuat.load(this)
/** The component of this FloatDualQuat at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 7 indices (`v[a, b, ...] = x`) resolves to the Java 8-argument value factory `FloatDualQuat.set(...)`, whose result is discarded (and `v[q] = t` to `set(Quat, Vec3)` likewise) - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun FloatDualQuat.get(index: Int): Float = when (index) {
    0 -> rX()
    1 -> rY()
    2 -> rZ()
    3 -> rW()
    4 -> dX()
    5 -> dY()
    6 -> dZ()
    7 -> dW()
    else -> throw IndexOutOfBoundsException("FloatDualQuat index: $index")
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object FloatDualQuatFactory {
    /** [FloatDualQuat.makeFromAxisAngle] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeFromAxisAngle(axis: Float3, angle: Angle, translation: Float3): FloatDualQuat = FloatDualQuat.makeFromAxisAngle(axis, angle.radians.toFloat(), translation)
    /** [FloatDualQuat.makeRotationAxis] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationAxis(angle: Angle, axis: Float3): FloatDualQuat = FloatDualQuat.makeRotationAxis(angle.radians.toFloat(), axis)
    /** [FloatDualQuat.makeRotationX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationX(angle: Angle): FloatDualQuat = FloatDualQuat.makeRotationX(angle.radians.toFloat())
    /** [FloatDualQuat.makeRotationXYZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): FloatDualQuat = FloatDualQuat.makeRotationXYZ(angleX.radians.toFloat(), angleY.radians.toFloat(), angleZ.radians.toFloat())
    /** [FloatDualQuat.makeRotationXZY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXZY(angleX: Angle, angleZ: Angle, angleY: Angle): FloatDualQuat = FloatDualQuat.makeRotationXZY(angleX.radians.toFloat(), angleZ.radians.toFloat(), angleY.radians.toFloat())
    /** [FloatDualQuat.makeRotationY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationY(angle: Angle): FloatDualQuat = FloatDualQuat.makeRotationY(angle.radians.toFloat())
    /** [FloatDualQuat.makeRotationYXZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): FloatDualQuat = FloatDualQuat.makeRotationYXZ(angleY.radians.toFloat(), angleX.radians.toFloat(), angleZ.radians.toFloat())
    /** [FloatDualQuat.makeRotationYZX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYZX(angleY: Angle, angleZ: Angle, angleX: Angle): FloatDualQuat = FloatDualQuat.makeRotationYZX(angleY.radians.toFloat(), angleZ.radians.toFloat(), angleX.radians.toFloat())
    /** [FloatDualQuat.makeRotationZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZ(angle: Angle): FloatDualQuat = FloatDualQuat.makeRotationZ(angle.radians.toFloat())
    /** [FloatDualQuat.makeRotationZXY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZXY(angleZ: Angle, angleX: Angle, angleY: Angle): FloatDualQuat = FloatDualQuat.makeRotationZXY(angleZ.radians.toFloat(), angleX.radians.toFloat(), angleY.radians.toFloat())
    /** [FloatDualQuat.makeRotationZYX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZYX(angleZ: Angle, angleY: Angle, angleX: Angle): FloatDualQuat = FloatDualQuat.makeRotationZYX(angleZ.radians.toFloat(), angleY.radians.toFloat(), angleX.radians.toFloat())
}
