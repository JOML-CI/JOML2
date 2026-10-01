// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Float2x3.
package org.joml2.kotlin

import org.joml2.*

/** [Float2x3.add] as the `+` operator. */
inline operator fun Float2x3.plus(other: Float2x3): Float2x3 = add(other)
/** [Float2x3.mul] as the `*` operator. */
inline operator fun Float2x3.times(scalar: Float): Float2x3 = mul(scalar)
/** [Float2x3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: Float2x3): Float2x3 = v.mul(this)
/** [Float2x3.negate] as the unary `-` operator. */
inline operator fun Float2x3.unaryMinus(): Float2x3 = negate()
/** [Float2x3.sub] as the `-` operator. */
inline operator fun Float2x3.minus(other: Float2x3): Float2x3 = sub(other)
/** [Float2x3.mul] as the `*` operator. */
inline operator fun Float2x3.times(right: Float2x3): Float2x3 = mul(right)
/** [Float2x3.mul] as the `*` operator. */
inline operator fun Float2x3.times(right: Float2x2): Float2x3 = mul(right)
/** [Float2x3.mul] as the `*` operator. */
inline operator fun Float2x3.times(right: Float3x3): Float3x3 = mul(right)
/** [Float2x3.preRotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float2x3.preRotate(angle: Angle): Float2x3 = preRotate(angle.radians.toFloat())
/** [Float2x3.preRotateAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float2x3.preRotateAround(angle: Angle, pivot: Float2): Float2x3 = preRotateAround(angle.radians.toFloat(), pivot)
/** [Float2x3.rotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float2x3.rotate(angle: Angle): Float2x3 = rotate(angle.radians.toFloat())
/** [Float2x3.rotateAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float2x3.rotateAround(angle: Angle, pivot: Float2): Float2x3 = rotateAround(angle.radians.toFloat(), pivot)
/** [Float2x3.mul] as the `*` operator. */
inline operator fun Float2x3.times(v: Float3): Float2 = mul(v)
/** The element of this Float2x3 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Float2x3.get(row: Int, column: Int): Float {
    if (row < 0 || row >= 2 || column < 0 || column >= 3)
        throw IndexOutOfBoundsException("Float2x3 indices: $row, $column")
    return when (row * 3 + column) {
        0 -> m00()
        1 -> m01()
        2 -> m02()
        3 -> m10()
        4 -> m11()
        5 -> m12()
        else -> throw IndexOutOfBoundsException("Float2x3 indices: $row, $column")
    }
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object Float2x3Factory {
    /** [Float2x3.makeRotation] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotation(angle: Angle): Float2x3 = Float2x3.makeRotation(angle.radians.toFloat())
}
