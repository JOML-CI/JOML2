// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Long4.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Long4.add] as the `+` operator. */
inline operator fun Long4.plus(other: Long4): Long4 = add(other)
/** [Long4.div] as the `/` operator. */
inline operator fun Long4.div(scalar: Long): Long4 = div(scalar)
/** [Long4.div] as the `/` operator. */
inline operator fun Long4.div(other: Long4): Long4 = div(other)
/** [Long4.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Long4.mod(scalar: Long): Long4 = floorMod(scalar)
/** [Long4.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Long4.mod(other: Long4): Long4 = floorMod(other)
/** [Long4.mul] as the `*` operator. */
inline operator fun Long4.times(scalar: Long): Long4 = mul(scalar)
/** [Long4.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Long.times(v: Long4): Long4 = v.mul(this)
/** [Long4.mul] as the `*` operator. */
inline operator fun Long4.times(other: Long4): Long4 = mul(other)
/** [Long4.negate] as the unary `-` operator. */
inline operator fun Long4.unaryMinus(): Long4 = negate()
/** [Long4.rem] as the `%` operator. */
inline operator fun Long4.rem(scalar: Long): Long4 = rem(scalar)
/** [Long4.rem] as the `%` operator. */
inline operator fun Long4.rem(other: Long4): Long4 = rem(other)
/** [Long4.sub] as the `-` operator. */
inline operator fun Long4.minus(other: Long4): Long4 = sub(other)
/** [Long4.dot] as an infix function, so `a dot b` parses. */
inline infix fun Long4.dot(other: Long4): Long = dot(other)
/** Component 1 of this Long4 (`x()`), for destructuring declarations. */
inline operator fun Long4.component1(): Long = x()
/** Component 2 of this Long4 (`y()`), for destructuring declarations. */
inline operator fun Long4.component2(): Long = y()
/** Component 3 of this Long4 (`z()`), for destructuring declarations. */
inline operator fun Long4.component3(): Long = z()
/** Component 4 of this Long4 (`w()`), for destructuring declarations. */
inline operator fun Long4.component4(): Long = w()
/** A copy of this Long4 with the named components replaced; every component left out keeps its current value. */
inline fun Long4.copy(x: Long = x(), y: Long = y(), z: Long = z(), w: Long = w()): Long4 = Long4(x, y, z, w)
/** The components of this Long4 in a new `LongArray`, in storage order. */
inline fun Long4.toLongArray(): LongArray = store(LongArray(4))
/** A Long4 read from this array in storage order, starting at index 0. */
inline fun LongArray.toLong4(): Long4 = Long4.load(this)
/** The component of this Long4 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 3 indices (`v[a, b, ...] = x`) resolves to the Java 4-argument value factory `Long4.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Long4.get(index: Int): Long = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    3 -> w()
    else -> throw IndexOutOfBoundsException("Long4 index: $index")
}
