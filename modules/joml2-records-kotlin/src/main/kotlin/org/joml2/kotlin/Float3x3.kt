// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Float3x3.
package org.joml2.kotlin

import org.joml2.*

/** [Float3x3.add] as the `+` operator. */
inline operator fun Float3x3.plus(other: Float3x3): Float3x3 = add(other)
/** [Float3x3.mul] as the `*` operator. */
inline operator fun Float3x3.times(scalar: Float): Float3x3 = mul(scalar)
/** [Float3x3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: Float3x3): Float3x3 = v.mul(this)
/** [Float3x3.negate] as the unary `-` operator. */
inline operator fun Float3x3.unaryMinus(): Float3x3 = negate()
/** [Float3x3.sub] as the `-` operator. */
inline operator fun Float3x3.minus(other: Float3x3): Float3x3 = sub(other)
/** [Float3x3.mul] as the `*` operator. */
inline operator fun Float3x3.times(right: Float3x3): Float3x3 = mul(right)
/** [Float3x3.mul] as the `*` operator. */
inline operator fun Float3x3.times(right: Float2x2): Float3x3 = mul(right)
/** [Float3x3.mul] as the `*` operator. */
inline operator fun Float3x3.times(right: Float2x3): Float3x3 = mul(right)
/** [Float3x3.preRotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.preRotate(angle: Angle): Float3x3 = preRotate(angle.radians.toFloat())
/** [Float3x3.preRotateAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.preRotateAround(angle: Angle, pivot: Float2): Float3x3 = preRotateAround(angle.radians.toFloat(), pivot)
/** [Float3x3.preRotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.preRotateAxis(angle: Angle, axis: Float3): Float3x3 = preRotateAxis(angle.radians.toFloat(), axis)
/** [Float3x3.preRotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.preRotateX(angle: Angle): Float3x3 = preRotateX(angle.radians.toFloat())
/** [Float3x3.preRotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.preRotateY(angle: Angle): Float3x3 = preRotateY(angle.radians.toFloat())
/** [Float3x3.preRotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.preRotateZ(angle: Angle): Float3x3 = preRotateZ(angle.radians.toFloat())
/** [Float3x3.rotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotate(angle: Angle): Float3x3 = rotate(angle.radians.toFloat())
/** [Float3x3.rotateAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateAround(angle: Angle, pivot: Float2): Float3x3 = rotateAround(angle.radians.toFloat(), pivot)
/** [Float3x3.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateAxis(angle: Angle, axis: Float3): Float3x3 = rotateAxis(angle.radians.toFloat(), axis)
/** [Float3x3.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateX(angle: Angle): Float3x3 = rotateX(angle.radians.toFloat())
/** [Float3x3.rotateXYZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): Float3x3 = rotateXYZ(angleX.radians.toFloat(), angleY.radians.toFloat(), angleZ.radians.toFloat())
/** [Float3x3.rotateXZY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateXZY(angleX: Angle, angleZ: Angle, angleY: Angle): Float3x3 = rotateXZY(angleX.radians.toFloat(), angleZ.radians.toFloat(), angleY.radians.toFloat())
/** [Float3x3.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateY(angle: Angle): Float3x3 = rotateY(angle.radians.toFloat())
/** [Float3x3.rotateYXZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): Float3x3 = rotateYXZ(angleY.radians.toFloat(), angleX.radians.toFloat(), angleZ.radians.toFloat())
/** [Float3x3.rotateYZX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateYZX(angleY: Angle, angleZ: Angle, angleX: Angle): Float3x3 = rotateYZX(angleY.radians.toFloat(), angleZ.radians.toFloat(), angleX.radians.toFloat())
/** [Float3x3.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateZ(angle: Angle): Float3x3 = rotateZ(angle.radians.toFloat())
/** [Float3x3.rotateZXY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateZXY(angleZ: Angle, angleX: Angle, angleY: Angle): Float3x3 = rotateZXY(angleZ.radians.toFloat(), angleX.radians.toFloat(), angleY.radians.toFloat())
/** [Float3x3.rotateZYX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x3.rotateZYX(angleZ: Angle, angleY: Angle, angleX: Angle): Float3x3 = rotateZYX(angleZ.radians.toFloat(), angleY.radians.toFloat(), angleX.radians.toFloat())
/** [Float3x3.mul] as the `*` operator. */
inline operator fun Float3x3.times(v: Float3): Float3 = mul(v)
/** The element of this Float3x3 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Float3x3.get(row: Int, column: Int): Float {
    if (row < 0 || row >= 3 || column < 0 || column >= 3)
        throw IndexOutOfBoundsException("Float3x3 indices: $row, $column")
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
        else -> throw IndexOutOfBoundsException("Float3x3 indices: $row, $column")
    }
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object Float3x3Factory {
    /** [Float3x3.makeRotation] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotation(angle: Angle): Float3x3 = Float3x3.makeRotation(angle.radians.toFloat())
    /** [Float3x3.makeRotationAxis] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationAxis(angle: Angle, axis: Float3): Float3x3 = Float3x3.makeRotationAxis(angle.radians.toFloat(), axis)
    /** [Float3x3.makeRotationX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationX(angle: Angle): Float3x3 = Float3x3.makeRotationX(angle.radians.toFloat())
    /** [Float3x3.makeRotationXYZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): Float3x3 = Float3x3.makeRotationXYZ(angleX.radians.toFloat(), angleY.radians.toFloat(), angleZ.radians.toFloat())
    /** [Float3x3.makeRotationXZY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXZY(angleX: Angle, angleZ: Angle, angleY: Angle): Float3x3 = Float3x3.makeRotationXZY(angleX.radians.toFloat(), angleZ.radians.toFloat(), angleY.radians.toFloat())
    /** [Float3x3.makeRotationY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationY(angle: Angle): Float3x3 = Float3x3.makeRotationY(angle.radians.toFloat())
    /** [Float3x3.makeRotationYXZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): Float3x3 = Float3x3.makeRotationYXZ(angleY.radians.toFloat(), angleX.radians.toFloat(), angleZ.radians.toFloat())
    /** [Float3x3.makeRotationYZX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYZX(angleY: Angle, angleZ: Angle, angleX: Angle): Float3x3 = Float3x3.makeRotationYZX(angleY.radians.toFloat(), angleZ.radians.toFloat(), angleX.radians.toFloat())
    /** [Float3x3.makeRotationZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZ(angle: Angle): Float3x3 = Float3x3.makeRotationZ(angle.radians.toFloat())
    /** [Float3x3.makeRotationZXY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZXY(angleZ: Angle, angleX: Angle, angleY: Angle): Float3x3 = Float3x3.makeRotationZXY(angleZ.radians.toFloat(), angleX.radians.toFloat(), angleY.radians.toFloat())
    /** [Float3x3.makeRotationZYX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZYX(angleZ: Angle, angleY: Angle, angleX: Angle): Float3x3 = Float3x3.makeRotationZYX(angleZ.radians.toFloat(), angleY.radians.toFloat(), angleX.radians.toFloat())
}
