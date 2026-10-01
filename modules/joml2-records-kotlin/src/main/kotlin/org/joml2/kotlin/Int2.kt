// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Int2.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Int2.add] as the `+` operator. */
inline operator fun Int2.plus(other: Int2): Int2 = add(other)
/** [Int2.div] as the `/` operator. */
inline operator fun Int2.div(scalar: Int): Int2 = div(scalar)
/** [Int2.div] as the `/` operator. */
inline operator fun Int2.div(other: Int2): Int2 = div(other)
/** [Int2.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Int2.mod(scalar: Int): Int2 = floorMod(scalar)
/** [Int2.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Int2.mod(other: Int2): Int2 = floorMod(other)
/** [Int2.mul] as the `*` operator. */
inline operator fun Int2.times(scalar: Int): Int2 = mul(scalar)
/** [Int2.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Int.times(v: Int2): Int2 = v.mul(this)
/** [Int2.mul] as the `*` operator. */
inline operator fun Int2.times(other: Int2): Int2 = mul(other)
/** [Int2.negate] as the unary `-` operator. */
inline operator fun Int2.unaryMinus(): Int2 = negate()
/** [Int2.rem] as the `%` operator. */
inline operator fun Int2.rem(scalar: Int): Int2 = rem(scalar)
/** [Int2.rem] as the `%` operator. */
inline operator fun Int2.rem(other: Int2): Int2 = rem(other)
/** [Int2.sub] as the `-` operator. */
inline operator fun Int2.minus(other: Int2): Int2 = sub(other)
/** [Int2.cross] as an infix function, so `a cross b` parses. */
inline infix fun Int2.cross(other: Int2): Long = cross(other)
/** [Int2.dot] as an infix function, so `a dot b` parses. */
inline infix fun Int2.dot(other: Int2): Long = dot(other)
/** Component 1 of this Int2 (`x()`), for destructuring declarations. */
inline operator fun Int2.component1(): Int = x()
/** Component 2 of this Int2 (`y()`), for destructuring declarations. */
inline operator fun Int2.component2(): Int = y()
/** A copy of this Int2 with the named components replaced; every component left out keeps its current value. */
inline fun Int2.copy(x: Int = x(), y: Int = y()): Int2 = Int2(x, y)
/** The components of this Int2 in a new `IntArray`, in storage order. */
inline fun Int2.toIntArray(): IntArray = store(IntArray(2))
/** A Int2 read from this array in storage order, starting at index 0. */
inline fun IntArray.toInt2(): Int2 = Int2.load(this)
/** The component of this Int2 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 1 index (`v[a] = b`, or `v[a] += b`) resolves to the Java 2-argument value factory `Int2.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Int2.get(index: Int): Int = when (index) {
    0 -> x()
    1 -> y()
    else -> throw IndexOutOfBoundsException("Int2 index: $index")
}
