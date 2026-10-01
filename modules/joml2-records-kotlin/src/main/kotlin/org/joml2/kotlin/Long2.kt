// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Long2.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Long2.add] as the `+` operator. */
inline operator fun Long2.plus(other: Long2): Long2 = add(other)
/** [Long2.div] as the `/` operator. */
inline operator fun Long2.div(scalar: Long): Long2 = div(scalar)
/** [Long2.div] as the `/` operator. */
inline operator fun Long2.div(other: Long2): Long2 = div(other)
/** [Long2.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Long2.mod(scalar: Long): Long2 = floorMod(scalar)
/** [Long2.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Long2.mod(other: Long2): Long2 = floorMod(other)
/** [Long2.mul] as the `*` operator. */
inline operator fun Long2.times(scalar: Long): Long2 = mul(scalar)
/** [Long2.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Long.times(v: Long2): Long2 = v.mul(this)
/** [Long2.mul] as the `*` operator. */
inline operator fun Long2.times(other: Long2): Long2 = mul(other)
/** [Long2.negate] as the unary `-` operator. */
inline operator fun Long2.unaryMinus(): Long2 = negate()
/** [Long2.rem] as the `%` operator. */
inline operator fun Long2.rem(scalar: Long): Long2 = rem(scalar)
/** [Long2.rem] as the `%` operator. */
inline operator fun Long2.rem(other: Long2): Long2 = rem(other)
/** [Long2.sub] as the `-` operator. */
inline operator fun Long2.minus(other: Long2): Long2 = sub(other)
/** [Long2.cross] as an infix function, so `a cross b` parses. */
inline infix fun Long2.cross(other: Long2): Long = cross(other)
/** [Long2.dot] as an infix function, so `a dot b` parses. */
inline infix fun Long2.dot(other: Long2): Long = dot(other)
/** Component 1 of this Long2 (`x()`), for destructuring declarations. */
inline operator fun Long2.component1(): Long = x()
/** Component 2 of this Long2 (`y()`), for destructuring declarations. */
inline operator fun Long2.component2(): Long = y()
/** A copy of this Long2 with the named components replaced; every component left out keeps its current value. */
inline fun Long2.copy(x: Long = x(), y: Long = y()): Long2 = Long2(x, y)
/** The components of this Long2 in a new `LongArray`, in storage order. */
inline fun Long2.toLongArray(): LongArray = store(LongArray(2))
/** A Long2 read from this array in storage order, starting at index 0. */
inline fun LongArray.toLong2(): Long2 = Long2.load(this)
/** The component of this Long2 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 1 index (`v[a] = b`) resolves to the Java 2-argument value factory `Long2.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Long2.get(index: Int): Long = when (index) {
    0 -> x()
    1 -> y()
    else -> throw IndexOutOfBoundsException("Long2 index: $index")
}
