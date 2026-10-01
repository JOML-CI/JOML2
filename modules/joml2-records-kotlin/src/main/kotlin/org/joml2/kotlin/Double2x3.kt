// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Double2x3.
package org.joml2.kotlin

import org.joml2.*

/** [Double2x3.add] as the `+` operator. */
inline operator fun Double2x3.plus(other: Double2x3): Double2x3 = add(other)
/** [Double2x3.mul] as the `*` operator. */
inline operator fun Double2x3.times(scalar: Double): Double2x3 = mul(scalar)
/** [Double2x3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: Double2x3): Double2x3 = v.mul(this)
/** [Double2x3.negate] as the unary `-` operator. */
inline operator fun Double2x3.unaryMinus(): Double2x3 = negate()
/** [Double2x3.sub] as the `-` operator. */
inline operator fun Double2x3.minus(other: Double2x3): Double2x3 = sub(other)
/** [Double2x3.mul] as the `*` operator. */
inline operator fun Double2x3.times(right: Double2x3): Double2x3 = mul(right)
/** [Double2x3.mul] as the `*` operator. */
inline operator fun Double2x3.times(right: Double2x2): Double2x3 = mul(right)
/** [Double2x3.mul] as the `*` operator. */
inline operator fun Double2x3.times(right: Double3x3): Double3x3 = mul(right)
/** [Double2x3.preRotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double2x3.preRotate(angle: Angle): Double2x3 = preRotate(angle.radians)
/** [Double2x3.preRotateAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double2x3.preRotateAround(angle: Angle, pivot: Double2): Double2x3 = preRotateAround(angle.radians, pivot)
/** [Double2x3.rotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double2x3.rotate(angle: Angle): Double2x3 = rotate(angle.radians)
/** [Double2x3.rotateAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double2x3.rotateAround(angle: Angle, pivot: Double2): Double2x3 = rotateAround(angle.radians, pivot)
/** [Double2x3.mul] as the `*` operator. */
inline operator fun Double2x3.times(v: Double3): Double2 = mul(v)
/** The element of this Double2x3 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Double2x3.get(row: Int, column: Int): Double {
    if (row < 0 || row >= 2 || column < 0 || column >= 3)
        throw IndexOutOfBoundsException("Double2x3 indices: $row, $column")
    return when (row * 3 + column) {
        0 -> m00()
        1 -> m01()
        2 -> m02()
        3 -> m10()
        4 -> m11()
        5 -> m12()
        else -> throw IndexOutOfBoundsException("Double2x3 indices: $row, $column")
    }
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object Double2x3Factory {
    /** [Double2x3.makeRotation] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotation(angle: Angle): Double2x3 = Double2x3.makeRotation(angle.radians)
}
