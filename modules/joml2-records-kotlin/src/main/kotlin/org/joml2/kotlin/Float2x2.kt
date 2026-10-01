// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Float2x2.
package org.joml2.kotlin

import org.joml2.*

/** [Float2x2.add] as the `+` operator. */
inline operator fun Float2x2.plus(other: Float2x2): Float2x2 = add(other)
/** [Float2x2.mul] as the `*` operator. */
inline operator fun Float2x2.times(scalar: Float): Float2x2 = mul(scalar)
/** [Float2x2.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: Float2x2): Float2x2 = v.mul(this)
/** [Float2x2.negate] as the unary `-` operator. */
inline operator fun Float2x2.unaryMinus(): Float2x2 = negate()
/** [Float2x2.sub] as the `-` operator. */
inline operator fun Float2x2.minus(other: Float2x2): Float2x2 = sub(other)
/** [Float2x2.mul] as the `*` operator. */
inline operator fun Float2x2.times(right: Float2x2): Float2x2 = mul(right)
/** [Float2x2.preRotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float2x2.preRotate(angle: Angle): Float2x2 = preRotate(angle.radians.toFloat())
/** [Float2x2.rotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float2x2.rotate(angle: Angle): Float2x2 = rotate(angle.radians.toFloat())
/** [Float2x2.mul] as the `*` operator. */
inline operator fun Float2x2.times(v: Float2): Float2 = mul(v)
/** The element of this Float2x2 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Float2x2.get(row: Int, column: Int): Float {
    if (row < 0 || row >= 2 || column < 0 || column >= 2)
        throw IndexOutOfBoundsException("Float2x2 indices: $row, $column")
    return when (row * 2 + column) {
        0 -> m00()
        1 -> m01()
        2 -> m10()
        3 -> m11()
        else -> throw IndexOutOfBoundsException("Float2x2 indices: $row, $column")
    }
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object Float2x2Factory {
    /** [Float2x2.makeRotation] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotation(angle: Angle): Float2x2 = Float2x2.makeRotation(angle.radians.toFloat())
}
