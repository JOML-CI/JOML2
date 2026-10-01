// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Int4.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Int4.add] as the `+` operator. */
inline operator fun Int4.plus(other: Int4): Int4 = add(other)
/** [Int4.div] as the `/` operator. */
inline operator fun Int4.div(scalar: Int): Int4 = div(scalar)
/** [Int4.div] as the `/` operator. */
inline operator fun Int4.div(other: Int4): Int4 = div(other)
/** [Int4.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Int4.mod(scalar: Int): Int4 = floorMod(scalar)
/** [Int4.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Int4.mod(other: Int4): Int4 = floorMod(other)
/** [Int4.mul] as the `*` operator. */
inline operator fun Int4.times(scalar: Int): Int4 = mul(scalar)
/** [Int4.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Int.times(v: Int4): Int4 = v.mul(this)
/** [Int4.mul] as the `*` operator. */
inline operator fun Int4.times(other: Int4): Int4 = mul(other)
/** [Int4.negate] as the unary `-` operator. */
inline operator fun Int4.unaryMinus(): Int4 = negate()
/** [Int4.rem] as the `%` operator. */
inline operator fun Int4.rem(scalar: Int): Int4 = rem(scalar)
/** [Int4.rem] as the `%` operator. */
inline operator fun Int4.rem(other: Int4): Int4 = rem(other)
/** [Int4.sub] as the `-` operator. */
inline operator fun Int4.minus(other: Int4): Int4 = sub(other)
/** [Int4.dot] as an infix function, so `a dot b` parses. */
inline infix fun Int4.dot(other: Int4): Long = dot(other)
/** Component 1 of this Int4 (`x()`), for destructuring declarations. */
inline operator fun Int4.component1(): Int = x()
/** Component 2 of this Int4 (`y()`), for destructuring declarations. */
inline operator fun Int4.component2(): Int = y()
/** Component 3 of this Int4 (`z()`), for destructuring declarations. */
inline operator fun Int4.component3(): Int = z()
/** Component 4 of this Int4 (`w()`), for destructuring declarations. */
inline operator fun Int4.component4(): Int = w()
/** A copy of this Int4 with the named components replaced; every component left out keeps its current value. */
inline fun Int4.copy(x: Int = x(), y: Int = y(), z: Int = z(), w: Int = w()): Int4 = Int4(x, y, z, w)
/** The components of this Int4 in a new `IntArray`, in storage order. */
inline fun Int4.toIntArray(): IntArray = store(IntArray(4))
/** A Int4 read from this array in storage order, starting at index 0. */
inline fun IntArray.toInt4(): Int4 = Int4.load(this)
/** The component of this Int4 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 3 indices (`v[a, b, ...] = x`) resolves to the Java 4-argument value factory `Int4.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Int4.get(index: Int): Int = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    3 -> w()
    else -> throw IndexOutOfBoundsException("Int4 index: $index")
}
