// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Float2x4.
package org.joml2.kotlin

import org.joml2.*

/** [Float2x4.add] as the `+` operator. */
inline operator fun Float2x4.plus(other: Float2x4): Float2x4 = add(other)
/** [Float2x4.mul] as the `*` operator. */
inline operator fun Float2x4.times(scalar: Float): Float2x4 = mul(scalar)
/** [Float2x4.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: Float2x4): Float2x4 = v.mul(this)
/** [Float2x4.negate] as the unary `-` operator. */
inline operator fun Float2x4.unaryMinus(): Float2x4 = negate()
/** [Float2x4.sub] as the `-` operator. */
inline operator fun Float2x4.minus(other: Float2x4): Float2x4 = sub(other)
/** [Float2x4.mul] as the `*` operator. */
inline operator fun Float2x4.times(right: Float2x4): Float2x4 = mul(right)
/** [Float2x4.mul] as the `*` operator. */
inline operator fun Float2x4.times(v: Float4): Float2 = mul(v)
/** The element of this Float2x4 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Float2x4.get(row: Int, column: Int): Float {
    if (row < 0 || row >= 2 || column < 0 || column >= 4)
        throw IndexOutOfBoundsException("Float2x4 indices: $row, $column")
    return when (row * 4 + column) {
        0 -> m00()
        1 -> m01()
        2 -> m02()
        3 -> m03()
        4 -> m10()
        5 -> m11()
        6 -> m12()
        7 -> m13()
        else -> throw IndexOutOfBoundsException("Float2x4 indices: $row, $column")
    }
}
