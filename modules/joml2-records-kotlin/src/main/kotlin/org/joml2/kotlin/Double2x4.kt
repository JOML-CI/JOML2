// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Double2x4.
package org.joml2.kotlin

import org.joml2.*

/** [Double2x4.add] as the `+` operator. */
inline operator fun Double2x4.plus(other: Double2x4): Double2x4 = add(other)
/** [Double2x4.mul] as the `*` operator. */
inline operator fun Double2x4.times(scalar: Double): Double2x4 = mul(scalar)
/** [Double2x4.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: Double2x4): Double2x4 = v.mul(this)
/** [Double2x4.negate] as the unary `-` operator. */
inline operator fun Double2x4.unaryMinus(): Double2x4 = negate()
/** [Double2x4.sub] as the `-` operator. */
inline operator fun Double2x4.minus(other: Double2x4): Double2x4 = sub(other)
/** [Double2x4.mul] as the `*` operator. */
inline operator fun Double2x4.times(right: Double2x4): Double2x4 = mul(right)
/** [Double2x4.mul] as the `*` operator. */
inline operator fun Double2x4.times(v: Double4): Double2 = mul(v)
/** The element of this Double2x4 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Double2x4.get(row: Int, column: Int): Double {
    if (row < 0 || row >= 2 || column < 0 || column >= 4)
        throw IndexOutOfBoundsException("Double2x4 indices: $row, $column")
    return when (row * 4 + column) {
        0 -> m00()
        1 -> m01()
        2 -> m02()
        3 -> m03()
        4 -> m10()
        5 -> m11()
        6 -> m12()
        7 -> m13()
        else -> throw IndexOutOfBoundsException("Double2x4 indices: $row, $column")
    }
}
