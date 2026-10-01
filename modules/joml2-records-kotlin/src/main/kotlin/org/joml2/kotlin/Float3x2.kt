// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Float3x2.
package org.joml2.kotlin

import org.joml2.*

/** [Float3x2.add] as the `+` operator. */
inline operator fun Float3x2.plus(other: Float3x2): Float3x2 = add(other)
/** [Float3x2.mul] as the `*` operator. */
inline operator fun Float3x2.times(scalar: Float): Float3x2 = mul(scalar)
/** [Float3x2.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: Float3x2): Float3x2 = v.mul(this)
/** [Float3x2.negate] as the unary `-` operator. */
inline operator fun Float3x2.unaryMinus(): Float3x2 = negate()
/** [Float3x2.sub] as the `-` operator. */
inline operator fun Float3x2.minus(other: Float3x2): Float3x2 = sub(other)
/** [Float3x2.mul] as the `*` operator. */
inline operator fun Float3x2.times(right: Float2x2): Float3x2 = mul(right)
/** [Float3x2.mul] as the `*` operator. */
inline operator fun Float3x2.times(v: Float2): Float3 = mul(v)
/** The element of this Float3x2 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Float3x2.get(row: Int, column: Int): Float {
    if (row < 0 || row >= 3 || column < 0 || column >= 2)
        throw IndexOutOfBoundsException("Float3x2 indices: $row, $column")
    return when (row * 2 + column) {
        0 -> m00()
        1 -> m01()
        2 -> m10()
        3 -> m11()
        4 -> m20()
        5 -> m21()
        else -> throw IndexOutOfBoundsException("Float3x2 indices: $row, $column")
    }
}
