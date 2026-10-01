// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Double3x3.
package org.joml2.kotlin

import org.joml2.*

/** [Double3x3.add] as the `+` operator. */
inline operator fun Double3x3.plus(other: Double3x3): Double3x3 = add(other)
/** [Double3x3.mul] as the `*` operator. */
inline operator fun Double3x3.times(scalar: Double): Double3x3 = mul(scalar)
/** [Double3x3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: Double3x3): Double3x3 = v.mul(this)
/** [Double3x3.negate] as the unary `-` operator. */
inline operator fun Double3x3.unaryMinus(): Double3x3 = negate()
/** [Double3x3.sub] as the `-` operator. */
inline operator fun Double3x3.minus(other: Double3x3): Double3x3 = sub(other)
/** [Double3x3.mul] as the `*` operator. */
inline operator fun Double3x3.times(right: Double3x3): Double3x3 = mul(right)
/** [Double3x3.mul] as the `*` operator. */
inline operator fun Double3x3.times(right: Double2x2): Double3x3 = mul(right)
/** [Double3x3.mul] as the `*` operator. */
inline operator fun Double3x3.times(right: Double2x3): Double3x3 = mul(right)
/** [Double3x3.preRotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.preRotate(angle: Angle): Double3x3 = preRotate(angle.radians)
/** [Double3x3.preRotateAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.preRotateAround(angle: Angle, pivot: Double2): Double3x3 = preRotateAround(angle.radians, pivot)
/** [Double3x3.preRotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.preRotateAxis(angle: Angle, axis: Double3): Double3x3 = preRotateAxis(angle.radians, axis)
/** [Double3x3.preRotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.preRotateX(angle: Angle): Double3x3 = preRotateX(angle.radians)
/** [Double3x3.preRotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.preRotateY(angle: Angle): Double3x3 = preRotateY(angle.radians)
/** [Double3x3.preRotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.preRotateZ(angle: Angle): Double3x3 = preRotateZ(angle.radians)
/** [Double3x3.rotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotate(angle: Angle): Double3x3 = rotate(angle.radians)
/** [Double3x3.rotateAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateAround(angle: Angle, pivot: Double2): Double3x3 = rotateAround(angle.radians, pivot)
/** [Double3x3.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateAxis(angle: Angle, axis: Double3): Double3x3 = rotateAxis(angle.radians, axis)
/** [Double3x3.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateX(angle: Angle): Double3x3 = rotateX(angle.radians)
/** [Double3x3.rotateXYZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): Double3x3 = rotateXYZ(angleX.radians, angleY.radians, angleZ.radians)
/** [Double3x3.rotateXZY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateXZY(angleX: Angle, angleZ: Angle, angleY: Angle): Double3x3 = rotateXZY(angleX.radians, angleZ.radians, angleY.radians)
/** [Double3x3.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateY(angle: Angle): Double3x3 = rotateY(angle.radians)
/** [Double3x3.rotateYXZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): Double3x3 = rotateYXZ(angleY.radians, angleX.radians, angleZ.radians)
/** [Double3x3.rotateYZX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateYZX(angleY: Angle, angleZ: Angle, angleX: Angle): Double3x3 = rotateYZX(angleY.radians, angleZ.radians, angleX.radians)
/** [Double3x3.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateZ(angle: Angle): Double3x3 = rotateZ(angle.radians)
/** [Double3x3.rotateZXY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateZXY(angleZ: Angle, angleX: Angle, angleY: Angle): Double3x3 = rotateZXY(angleZ.radians, angleX.radians, angleY.radians)
/** [Double3x3.rotateZYX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3x3.rotateZYX(angleZ: Angle, angleY: Angle, angleX: Angle): Double3x3 = rotateZYX(angleZ.radians, angleY.radians, angleX.radians)
/** [Double3x3.mul] as the `*` operator. */
inline operator fun Double3x3.times(v: Double3): Double3 = mul(v)
/** The element of this Double3x3 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Double3x3.get(row: Int, column: Int): Double {
    if (row < 0 || row >= 3 || column < 0 || column >= 3)
        throw IndexOutOfBoundsException("Double3x3 indices: $row, $column")
    return when (row * 3 + column) {
        0 -> m00()
        1 -> m01()
        2 -> m02()
        3 -> m10()
        4 -> m11()
        5 -> m12()
        6 -> m20()
        7 -> m21()
        8 -> m22()
        else -> throw IndexOutOfBoundsException("Double3x3 indices: $row, $column")
    }
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object Double3x3Factory {
    /** [Double3x3.makeRotation] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotation(angle: Angle): Double3x3 = Double3x3.makeRotation(angle.radians)
    /** [Double3x3.makeRotationAxis] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationAxis(angle: Angle, axis: Double3): Double3x3 = Double3x3.makeRotationAxis(angle.radians, axis)
    /** [Double3x3.makeRotationX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationX(angle: Angle): Double3x3 = Double3x3.makeRotationX(angle.radians)
    /** [Double3x3.makeRotationXYZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): Double3x3 = Double3x3.makeRotationXYZ(angleX.radians, angleY.radians, angleZ.radians)
    /** [Double3x3.makeRotationXZY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXZY(angleX: Angle, angleZ: Angle, angleY: Angle): Double3x3 = Double3x3.makeRotationXZY(angleX.radians, angleZ.radians, angleY.radians)
    /** [Double3x3.makeRotationY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationY(angle: Angle): Double3x3 = Double3x3.makeRotationY(angle.radians)
    /** [Double3x3.makeRotationYXZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): Double3x3 = Double3x3.makeRotationYXZ(angleY.radians, angleX.radians, angleZ.radians)
    /** [Double3x3.makeRotationYZX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYZX(angleY: Angle, angleZ: Angle, angleX: Angle): Double3x3 = Double3x3.makeRotationYZX(angleY.radians, angleZ.radians, angleX.radians)
    /** [Double3x3.makeRotationZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZ(angle: Angle): Double3x3 = Double3x3.makeRotationZ(angle.radians)
    /** [Double3x3.makeRotationZXY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZXY(angleZ: Angle, angleX: Angle, angleY: Angle): Double3x3 = Double3x3.makeRotationZXY(angleZ.radians, angleX.radians, angleY.radians)
    /** [Double3x3.makeRotationZYX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZYX(angleZ: Angle, angleY: Angle, angleX: Angle): Double3x3 = Double3x3.makeRotationZYX(angleZ.radians, angleY.radians, angleX.radians)
}
