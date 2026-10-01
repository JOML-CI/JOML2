// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Double4x2.
package org.joml2.kotlin

import org.joml2.*

/** [Double4x2.add] as the `+` operator. */
inline operator fun Double4x2.plus(other: Double4x2): Double4x2 = add(other)
/** [Double4x2.mul] as the `*` operator. */
inline operator fun Double4x2.times(scalar: Double): Double4x2 = mul(scalar)
/** [Double4x2.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: Double4x2): Double4x2 = v.mul(this)
/** [Double4x2.negate] as the unary `-` operator. */
inline operator fun Double4x2.unaryMinus(): Double4x2 = negate()
/** [Double4x2.sub] as the `-` operator. */
inline operator fun Double4x2.minus(other: Double4x2): Double4x2 = sub(other)
/** [Double4x2.mul] as the `*` operator. */
inline operator fun Double4x2.times(right: Double2x2): Double4x2 = mul(right)
/** [Double4x2.mul] as the `*` operator. */
inline operator fun Double4x2.times(v: Double2): Double4 = mul(v)
/** The element of this Double4x2 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Double4x2.get(row: Int, column: Int): Double {
    if (row < 0 || row >= 4 || column < 0 || column >= 2)
        throw IndexOutOfBoundsException("Double4x2 indices: $row, $column")
    return when (row * 2 + column) {
        0 -> m00()
        1 -> m01()
        2 -> m10()
        3 -> m11()
        4 -> m20()
        5 -> m21()
        6 -> m30()
        7 -> m31()
        else -> throw IndexOutOfBoundsException("Double4x2 indices: $row, $column")
    }
}
