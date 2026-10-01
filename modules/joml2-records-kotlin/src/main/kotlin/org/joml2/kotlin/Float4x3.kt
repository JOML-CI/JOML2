// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Float4x3.
package org.joml2.kotlin

import org.joml2.*

/** [Float4x3.add] as the `+` operator. */
inline operator fun Float4x3.plus(other: Float4x3): Float4x3 = add(other)
/** [Float4x3.mul] as the `*` operator. */
inline operator fun Float4x3.times(scalar: Float): Float4x3 = mul(scalar)
/** [Float4x3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: Float4x3): Float4x3 = v.mul(this)
/** [Float4x3.negate] as the unary `-` operator. */
inline operator fun Float4x3.unaryMinus(): Float4x3 = negate()
/** [Float4x3.sub] as the `-` operator. */
inline operator fun Float4x3.minus(other: Float4x3): Float4x3 = sub(other)
/** [Float4x3.mul] as the `*` operator. */
inline operator fun Float4x3.times(right: Float3x3): Float4x3 = mul(right)
/** [Float4x3.mul] as the `*` operator. */
inline operator fun Float4x3.times(v: Float3): Float4 = mul(v)
/** The element of this Float4x3 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Float4x3.get(row: Int, column: Int): Float {
    if (row < 0 || row >= 4 || column < 0 || column >= 3)
        throw IndexOutOfBoundsException("Float4x3 indices: $row, $column")
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
        9 -> m30()
        10 -> m31()
        11 -> m32()
        else -> throw IndexOutOfBoundsException("Float4x3 indices: $row, $column")
    }
}
