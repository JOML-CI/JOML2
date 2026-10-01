// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoubleDualQuat.
package org.joml2.kotlin

import org.joml2.*

/** [DoubleDualQuat.add] as the `+` operator. */
inline operator fun DoubleDualQuat.plus(other: DoubleDualQuat): DoubleDualQuat = add(other)
/** [DoubleDualQuat.mul] as the `*` operator. */
inline operator fun DoubleDualQuat.times(scalar: Double): DoubleDualQuat = mul(scalar)
/** [DoubleDualQuat.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: DoubleDualQuat): DoubleDualQuat = v.mul(this)
/** [DoubleDualQuat.negate] as the unary `-` operator. */
inline operator fun DoubleDualQuat.unaryMinus(): DoubleDualQuat = negate()
/** [DoubleDualQuat.sub] as the `-` operator. */
inline operator fun DoubleDualQuat.minus(other: DoubleDualQuat): DoubleDualQuat = sub(other)
/** [DoubleDualQuat.mul] as the `*` operator. */
inline operator fun DoubleDualQuat.times(other: DoubleDualQuat): DoubleDualQuat = mul(other)
/** [DoubleDualQuat.dot] as an infix function, so `a dot b` parses. */
inline infix fun DoubleDualQuat.dot(other: DoubleDualQuat): Double = dot(other)
/** [DoubleDualQuat.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleDualQuat.rotateAxis(angle: Angle, axis: Double3): DoubleDualQuat = rotateAxis(angle.radians, axis)
/** [DoubleDualQuat.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleDualQuat.rotateX(angle: Angle): DoubleDualQuat = rotateX(angle.radians)
/** [DoubleDualQuat.rotateXYZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleDualQuat.rotateXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): DoubleDualQuat = rotateXYZ(angleX.radians, angleY.radians, angleZ.radians)
/** [DoubleDualQuat.rotateXZY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleDualQuat.rotateXZY(angleX: Angle, angleZ: Angle, angleY: Angle): DoubleDualQuat = rotateXZY(angleX.radians, angleZ.radians, angleY.radians)
/** [DoubleDualQuat.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleDualQuat.rotateY(angle: Angle): DoubleDualQuat = rotateY(angle.radians)
/** [DoubleDualQuat.rotateYXZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleDualQuat.rotateYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): DoubleDualQuat = rotateYXZ(angleY.radians, angleX.radians, angleZ.radians)
/** [DoubleDualQuat.rotateYZX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleDualQuat.rotateYZX(angleY: Angle, angleZ: Angle, angleX: Angle): DoubleDualQuat = rotateYZX(angleY.radians, angleZ.radians, angleX.radians)
/** [DoubleDualQuat.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleDualQuat.rotateZ(angle: Angle): DoubleDualQuat = rotateZ(angle.radians)
/** [DoubleDualQuat.rotateZXY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleDualQuat.rotateZXY(angleZ: Angle, angleX: Angle, angleY: Angle): DoubleDualQuat = rotateZXY(angleZ.radians, angleX.radians, angleY.radians)
/** [DoubleDualQuat.rotateZYX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleDualQuat.rotateZYX(angleZ: Angle, angleY: Angle, angleX: Angle): DoubleDualQuat = rotateZYX(angleZ.radians, angleY.radians, angleX.radians)
/** [DoubleDualQuat.transform] as the `*` operator. */
inline operator fun DoubleDualQuat.times(p: Double3): Double3 = transform(p)
/** Component 1 of this DoubleDualQuat (`rX()`), for destructuring declarations. */
inline operator fun DoubleDualQuat.component1(): Double = rX()
/** Component 2 of this DoubleDualQuat (`rY()`), for destructuring declarations. */
inline operator fun DoubleDualQuat.component2(): Double = rY()
/** Component 3 of this DoubleDualQuat (`rZ()`), for destructuring declarations. */
inline operator fun DoubleDualQuat.component3(): Double = rZ()
/** Component 4 of this DoubleDualQuat (`rW()`), for destructuring declarations. */
inline operator fun DoubleDualQuat.component4(): Double = rW()
/** Component 5 of this DoubleDualQuat (`dX()`), for destructuring declarations. */
inline operator fun DoubleDualQuat.component5(): Double = dX()
/** Component 6 of this DoubleDualQuat (`dY()`), for destructuring declarations. */
inline operator fun DoubleDualQuat.component6(): Double = dY()
/** Component 7 of this DoubleDualQuat (`dZ()`), for destructuring declarations. */
inline operator fun DoubleDualQuat.component7(): Double = dZ()
/** Component 8 of this DoubleDualQuat (`dW()`), for destructuring declarations. */
inline operator fun DoubleDualQuat.component8(): Double = dW()
/** A copy of this DoubleDualQuat with the named components replaced; every component left out keeps its current value. */
inline fun DoubleDualQuat.copy(rX: Double = rX(), rY: Double = rY(), rZ: Double = rZ(), rW: Double = rW(), dX: Double = dX(), dY: Double = dY(), dZ: Double = dZ(), dW: Double = dW()): DoubleDualQuat = DoubleDualQuat(rX, rY, rZ, rW, dX, dY, dZ, dW)
/** The components of this DoubleDualQuat in a new `DoubleArray`, in storage order. */
inline fun DoubleDualQuat.toDoubleArray(): DoubleArray = store(DoubleArray(8))
/** A DoubleDualQuat read from this array in storage order, starting at index 0. */
inline fun DoubleArray.toDoubleDualQuat(): DoubleDualQuat = DoubleDualQuat.load(this)
/** The component of this DoubleDualQuat at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 7 indices (`v[a, b, ...] = x`) resolves to the Java 8-argument value factory `DoubleDualQuat.set(...)`, whose result is discarded (and `v[q] = t` to `set(Quat, Vec3)` likewise) - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun DoubleDualQuat.get(index: Int): Double = when (index) {
    0 -> rX()
    1 -> rY()
    2 -> rZ()
    3 -> rW()
    4 -> dX()
    5 -> dY()
    6 -> dZ()
    7 -> dW()
    else -> throw IndexOutOfBoundsException("DoubleDualQuat index: $index")
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object DoubleDualQuatFactory {
    /** [DoubleDualQuat.makeFromAxisAngle] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeFromAxisAngle(axis: Double3, angle: Angle, translation: Double3): DoubleDualQuat = DoubleDualQuat.makeFromAxisAngle(axis, angle.radians, translation)
    /** [DoubleDualQuat.makeRotationAxis] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationAxis(angle: Angle, axis: Double3): DoubleDualQuat = DoubleDualQuat.makeRotationAxis(angle.radians, axis)
    /** [DoubleDualQuat.makeRotationX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationX(angle: Angle): DoubleDualQuat = DoubleDualQuat.makeRotationX(angle.radians)
    /** [DoubleDualQuat.makeRotationXYZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): DoubleDualQuat = DoubleDualQuat.makeRotationXYZ(angleX.radians, angleY.radians, angleZ.radians)
    /** [DoubleDualQuat.makeRotationXZY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXZY(angleX: Angle, angleZ: Angle, angleY: Angle): DoubleDualQuat = DoubleDualQuat.makeRotationXZY(angleX.radians, angleZ.radians, angleY.radians)
    /** [DoubleDualQuat.makeRotationY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationY(angle: Angle): DoubleDualQuat = DoubleDualQuat.makeRotationY(angle.radians)
    /** [DoubleDualQuat.makeRotationYXZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): DoubleDualQuat = DoubleDualQuat.makeRotationYXZ(angleY.radians, angleX.radians, angleZ.radians)
    /** [DoubleDualQuat.makeRotationYZX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYZX(angleY: Angle, angleZ: Angle, angleX: Angle): DoubleDualQuat = DoubleDualQuat.makeRotationYZX(angleY.radians, angleZ.radians, angleX.radians)
    /** [DoubleDualQuat.makeRotationZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZ(angle: Angle): DoubleDualQuat = DoubleDualQuat.makeRotationZ(angle.radians)
    /** [DoubleDualQuat.makeRotationZXY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZXY(angleZ: Angle, angleX: Angle, angleY: Angle): DoubleDualQuat = DoubleDualQuat.makeRotationZXY(angleZ.radians, angleX.radians, angleY.radians)
    /** [DoubleDualQuat.makeRotationZYX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZYX(angleZ: Angle, angleY: Angle, angleX: Angle): DoubleDualQuat = DoubleDualQuat.makeRotationZYX(angleZ.radians, angleY.radians, angleX.radians)
}
