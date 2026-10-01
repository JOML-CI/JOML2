// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Double4x3.
package org.joml2.kotlin

import org.joml2.*

/** [Double4x3.add] as the `+` operator. */
inline operator fun Double4x3.plus(other: Double4x3): Double4x3 = add(other)
/** [Double4x3.mul] as the `*` operator. */
inline operator fun Double4x3.times(scalar: Double): Double4x3 = mul(scalar)
/** [Double4x3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: Double4x3): Double4x3 = v.mul(this)
/** [Double4x3.negate] as the unary `-` operator. */
inline operator fun Double4x3.unaryMinus(): Double4x3 = negate()
/** [Double4x3.sub] as the `-` operator. */
inline operator fun Double4x3.minus(other: Double4x3): Double4x3 = sub(other)
/** [Double4x3.mul] as the `*` operator. */
inline operator fun Double4x3.times(right: Double3x3): Double4x3 = mul(right)
/** [Double4x3.mul] as the `*` operator. */
inline operator fun Double4x3.times(v: Double3): Double4 = mul(v)
/** The element of this Double4x3 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Double4x3.get(row: Int, column: Int): Double {
    if (row < 0 || row >= 4 || column < 0 || column >= 3)
        throw IndexOutOfBoundsException("Double4x3 indices: $row, $column")
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
        else -> throw IndexOutOfBoundsException("Double4x3 indices: $row, $column")
    }
}
