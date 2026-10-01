// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoubleQuat.
package org.joml2.kotlin

import org.joml2.*

/** [DoubleQuat.add] as the `+` operator. */
inline operator fun DoubleQuat.plus(other: DoubleQuat): DoubleQuat = add(other)
/** [DoubleQuat.mul] as the `*` operator. */
inline operator fun DoubleQuat.times(scalar: Double): DoubleQuat = mul(scalar)
/** [DoubleQuat.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: DoubleQuat): DoubleQuat = v.mul(this)
/** [DoubleQuat.negate] as the unary `-` operator. */
inline operator fun DoubleQuat.unaryMinus(): DoubleQuat = negate()
/** [DoubleQuat.sub] as the `-` operator. */
inline operator fun DoubleQuat.minus(other: DoubleQuat): DoubleQuat = sub(other)
/** [DoubleQuat.mul] as the `*` operator. */
inline operator fun DoubleQuat.times(other: DoubleQuat): DoubleQuat = mul(other)
/** [DoubleQuat.dot] as an infix function, so `a dot b` parses. */
inline infix fun DoubleQuat.dot(other: DoubleQuat): Double = dot(other)
/** [DoubleQuat.preRotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.preRotateX(angle: Angle): DoubleQuat = preRotateX(angle.radians)
/** [DoubleQuat.preRotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.preRotateY(angle: Angle): DoubleQuat = preRotateY(angle.radians)
/** [DoubleQuat.preRotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.preRotateZ(angle: Angle): DoubleQuat = preRotateZ(angle.radians)
/** [DoubleQuat.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.rotateAxis(angle: Angle, axis: Double3): DoubleQuat = rotateAxis(angle.radians, axis)
/** [DoubleQuat.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.rotateX(angle: Angle): DoubleQuat = rotateX(angle.radians)
/** [DoubleQuat.rotateXYZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.rotateXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): DoubleQuat = rotateXYZ(angleX.radians, angleY.radians, angleZ.radians)
/** [DoubleQuat.rotateXZY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.rotateXZY(angleX: Angle, angleZ: Angle, angleY: Angle): DoubleQuat = rotateXZY(angleX.radians, angleZ.radians, angleY.radians)
/** [DoubleQuat.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.rotateY(angle: Angle): DoubleQuat = rotateY(angle.radians)
/** [DoubleQuat.rotateYXZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.rotateYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): DoubleQuat = rotateYXZ(angleY.radians, angleX.radians, angleZ.radians)
/** [DoubleQuat.rotateYZX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.rotateYZX(angleY: Angle, angleZ: Angle, angleX: Angle): DoubleQuat = rotateYZX(angleY.radians, angleZ.radians, angleX.radians)
/** [DoubleQuat.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.rotateZ(angle: Angle): DoubleQuat = rotateZ(angle.radians)
/** [DoubleQuat.rotateZXY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.rotateZXY(angleZ: Angle, angleX: Angle, angleY: Angle): DoubleQuat = rotateZXY(angleZ.radians, angleX.radians, angleY.radians)
/** [DoubleQuat.rotateZYX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun DoubleQuat.rotateZYX(angleZ: Angle, angleY: Angle, angleX: Angle): DoubleQuat = rotateZYX(angleZ.radians, angleY.radians, angleX.radians)
/** [DoubleQuat.transform] as the `*` operator. */
inline operator fun DoubleQuat.times(v: Double3): Double3 = transform(v)
/** Component 1 of this DoubleQuat (`x()`), for destructuring declarations. */
inline operator fun DoubleQuat.component1(): Double = x()
/** Component 2 of this DoubleQuat (`y()`), for destructuring declarations. */
inline operator fun DoubleQuat.component2(): Double = y()
/** Component 3 of this DoubleQuat (`z()`), for destructuring declarations. */
inline operator fun DoubleQuat.component3(): Double = z()
/** Component 4 of this DoubleQuat (`w()`), for destructuring declarations. */
inline operator fun DoubleQuat.component4(): Double = w()
/** A copy of this DoubleQuat with the named components replaced; every component left out keeps its current value. */
inline fun DoubleQuat.copy(x: Double = x(), y: Double = y(), z: Double = z(), w: Double = w()): DoubleQuat = DoubleQuat(x, y, z, w)
/** The components of this DoubleQuat in a new `DoubleArray`, in storage order. */
inline fun DoubleQuat.toDoubleArray(): DoubleArray = store(DoubleArray(4))
/** A DoubleQuat read from this array in storage order, starting at index 0. */
inline fun DoubleArray.toDoubleQuat(): DoubleQuat = DoubleQuat.load(this)
/** The component of this DoubleQuat at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 3 indices (`v[a, b, ...] = x`) resolves to the Java 4-argument value factory `DoubleQuat.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun DoubleQuat.get(index: Int): Double = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    3 -> w()
    else -> throw IndexOutOfBoundsException("DoubleQuat index: $index")
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object DoubleQuatFactory {
    /** [DoubleQuat.makeRotationAxis] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationAxis(angle: Angle, axis: Double3): DoubleQuat = DoubleQuat.makeRotationAxis(angle.radians, axis)
    /** [DoubleQuat.makeRotationX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationX(angle: Angle): DoubleQuat = DoubleQuat.makeRotationX(angle.radians)
    /** [DoubleQuat.makeRotationXYZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): DoubleQuat = DoubleQuat.makeRotationXYZ(angleX.radians, angleY.radians, angleZ.radians)
    /** [DoubleQuat.makeRotationXZY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXZY(angleX: Angle, angleZ: Angle, angleY: Angle): DoubleQuat = DoubleQuat.makeRotationXZY(angleX.radians, angleZ.radians, angleY.radians)
    /** [DoubleQuat.makeRotationY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationY(angle: Angle): DoubleQuat = DoubleQuat.makeRotationY(angle.radians)
    /** [DoubleQuat.makeRotationYXZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): DoubleQuat = DoubleQuat.makeRotationYXZ(angleY.radians, angleX.radians, angleZ.radians)
    /** [DoubleQuat.makeRotationYZX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYZX(angleY: Angle, angleZ: Angle, angleX: Angle): DoubleQuat = DoubleQuat.makeRotationYZX(angleY.radians, angleZ.radians, angleX.radians)
    /** [DoubleQuat.makeRotationZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZ(angle: Angle): DoubleQuat = DoubleQuat.makeRotationZ(angle.radians)
    /** [DoubleQuat.makeRotationZXY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZXY(angleZ: Angle, angleX: Angle, angleY: Angle): DoubleQuat = DoubleQuat.makeRotationZXY(angleZ.radians, angleX.radians, angleY.radians)
    /** [DoubleQuat.makeRotationZYX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZYX(angleZ: Angle, angleY: Angle, angleX: Angle): DoubleQuat = DoubleQuat.makeRotationZYX(angleZ.radians, angleY.radians, angleX.radians)
}
