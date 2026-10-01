// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Float3x4.
package org.joml2.kotlin

import org.joml2.*

/** [Float3x4.add] as the `+` operator. */
inline operator fun Float3x4.plus(other: Float3x4): Float3x4 = add(other)
/** [Float3x4.mul] as the `*` operator. */
inline operator fun Float3x4.times(scalar: Float): Float3x4 = mul(scalar)
/** [Float3x4.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: Float3x4): Float3x4 = v.mul(this)
/** [Float3x4.negate] as the unary `-` operator. */
inline operator fun Float3x4.unaryMinus(): Float3x4 = negate()
/** [Float3x4.sub] as the `-` operator. */
inline operator fun Float3x4.minus(other: Float3x4): Float3x4 = sub(other)
/** [Float3x4.mul] as the `*` operator. */
inline operator fun Float3x4.times(right: Float3x4): Float3x4 = mul(right)
/** [Float3x4.mul] as the `*` operator. */
inline operator fun Float3x4.times(right: Float2x2): Float3x4 = mul(right)
/** [Float3x4.mul] as the `*` operator. */
inline operator fun Float3x4.times(right: Float2x3): Float3x4 = mul(right)
/** [Float3x4.mul] as the `*` operator. */
inline operator fun Float3x4.times(right: Float3x3): Float3x4 = mul(right)
/** [Float3x4.mul] as the `*` operator. */
inline operator fun Float3x4.times(right: Float4x4): Float4x4 = mul(right)
/** [Float3x4.preRotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.preRotateAxis(angle: Angle, axis: Float3): Float3x4 = preRotateAxis(angle.radians.toFloat(), axis)
/** [Float3x4.preRotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.preRotateX(angle: Angle): Float3x4 = preRotateX(angle.radians.toFloat())
/** [Float3x4.preRotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.preRotateY(angle: Angle): Float3x4 = preRotateY(angle.radians.toFloat())
/** [Float3x4.preRotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.preRotateZ(angle: Angle): Float3x4 = preRotateZ(angle.radians.toFloat())
/** [Float3x4.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.rotateAxis(angle: Angle, axis: Float3): Float3x4 = rotateAxis(angle.radians.toFloat(), axis)
/** [Float3x4.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.rotateX(angle: Angle): Float3x4 = rotateX(angle.radians.toFloat())
/** [Float3x4.rotateXYZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.rotateXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): Float3x4 = rotateXYZ(angleX.radians.toFloat(), angleY.radians.toFloat(), angleZ.radians.toFloat())
/** [Float3x4.rotateXZY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.rotateXZY(angleX: Angle, angleZ: Angle, angleY: Angle): Float3x4 = rotateXZY(angleX.radians.toFloat(), angleZ.radians.toFloat(), angleY.radians.toFloat())
/** [Float3x4.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.rotateY(angle: Angle): Float3x4 = rotateY(angle.radians.toFloat())
/** [Float3x4.rotateYXZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.rotateYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): Float3x4 = rotateYXZ(angleY.radians.toFloat(), angleX.radians.toFloat(), angleZ.radians.toFloat())
/** [Float3x4.rotateYZX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.rotateYZX(angleY: Angle, angleZ: Angle, angleX: Angle): Float3x4 = rotateYZX(angleY.radians.toFloat(), angleZ.radians.toFloat(), angleX.radians.toFloat())
/** [Float3x4.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.rotateZ(angle: Angle): Float3x4 = rotateZ(angle.radians.toFloat())
/** [Float3x4.rotateZXY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.rotateZXY(angleZ: Angle, angleX: Angle, angleY: Angle): Float3x4 = rotateZXY(angleZ.radians.toFloat(), angleX.radians.toFloat(), angleY.radians.toFloat())
/** [Float3x4.rotateZYX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3x4.rotateZYX(angleZ: Angle, angleY: Angle, angleX: Angle): Float3x4 = rotateZYX(angleZ.radians.toFloat(), angleY.radians.toFloat(), angleX.radians.toFloat())
/** [Float3x4.mul] as the `*` operator. */
inline operator fun Float3x4.times(v: Float4): Float3 = mul(v)
/** The element of this Float3x4 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Float3x4.get(row: Int, column: Int): Float {
    if (row < 0 || row >= 3 || column < 0 || column >= 4)
        throw IndexOutOfBoundsException("Float3x4 indices: $row, $column")
    return when (row * 4 + column) {
        0 -> m00()
        1 -> m01()
        2 -> m02()
        3 -> m03()
        4 -> m10()
        5 -> m11()
        6 -> m12()
        7 -> m13()
        8 -> m20()
        9 -> m21()
        10 -> m22()
        11 -> m23()
        else -> throw IndexOutOfBoundsException("Float3x4 indices: $row, $column")
    }
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object Float3x4Factory {
    /** [Float3x4.makeRotationAxis] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationAxis(angle: Angle, axis: Float3): Float3x4 = Float3x4.makeRotationAxis(angle.radians.toFloat(), axis)
    /** [Float3x4.makeRotationX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationX(angle: Angle): Float3x4 = Float3x4.makeRotationX(angle.radians.toFloat())
    /** [Float3x4.makeRotationXYZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): Float3x4 = Float3x4.makeRotationXYZ(angleX.radians.toFloat(), angleY.radians.toFloat(), angleZ.radians.toFloat())
    /** [Float3x4.makeRotationXZY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXZY(angleX: Angle, angleZ: Angle, angleY: Angle): Float3x4 = Float3x4.makeRotationXZY(angleX.radians.toFloat(), angleZ.radians.toFloat(), angleY.radians.toFloat())
    /** [Float3x4.makeRotationY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationY(angle: Angle): Float3x4 = Float3x4.makeRotationY(angle.radians.toFloat())
    /** [Float3x4.makeRotationYXZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): Float3x4 = Float3x4.makeRotationYXZ(angleY.radians.toFloat(), angleX.radians.toFloat(), angleZ.radians.toFloat())
    /** [Float3x4.makeRotationYZX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYZX(angleY: Angle, angleZ: Angle, angleX: Angle): Float3x4 = Float3x4.makeRotationYZX(angleY.radians.toFloat(), angleZ.radians.toFloat(), angleX.radians.toFloat())
    /** [Float3x4.makeRotationZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZ(angle: Angle): Float3x4 = Float3x4.makeRotationZ(angle.radians.toFloat())
    /** [Float3x4.makeRotationZXY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZXY(angleZ: Angle, angleX: Angle, angleY: Angle): Float3x4 = Float3x4.makeRotationZXY(angleZ.radians.toFloat(), angleX.radians.toFloat(), angleY.radians.toFloat())
    /** [Float3x4.makeRotationZYX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZYX(angleZ: Angle, angleY: Angle, angleX: Angle): Float3x4 = Float3x4.makeRotationZYX(angleZ.radians.toFloat(), angleY.radians.toFloat(), angleX.radians.toFloat())
}
