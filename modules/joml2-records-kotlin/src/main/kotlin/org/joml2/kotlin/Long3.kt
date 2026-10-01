// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Long3.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Long3.add] as the `+` operator. */
inline operator fun Long3.plus(other: Long3): Long3 = add(other)
/** [Long3.div] as the `/` operator. */
inline operator fun Long3.div(scalar: Long): Long3 = div(scalar)
/** [Long3.div] as the `/` operator. */
inline operator fun Long3.div(other: Long3): Long3 = div(other)
/** [Long3.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Long3.mod(scalar: Long): Long3 = floorMod(scalar)
/** [Long3.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Long3.mod(other: Long3): Long3 = floorMod(other)
/** [Long3.mul] as the `*` operator. */
inline operator fun Long3.times(scalar: Long): Long3 = mul(scalar)
/** [Long3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Long.times(v: Long3): Long3 = v.mul(this)
/** [Long3.mul] as the `*` operator. */
inline operator fun Long3.times(other: Long3): Long3 = mul(other)
/** [Long3.negate] as the unary `-` operator. */
inline operator fun Long3.unaryMinus(): Long3 = negate()
/** [Long3.rem] as the `%` operator. */
inline operator fun Long3.rem(scalar: Long): Long3 = rem(scalar)
/** [Long3.rem] as the `%` operator. */
inline operator fun Long3.rem(other: Long3): Long3 = rem(other)
/** [Long3.sub] as the `-` operator. */
inline operator fun Long3.minus(other: Long3): Long3 = sub(other)
/** [Long3.cross] as an infix function, so `a cross b` parses. */
inline infix fun Long3.cross(other: Long3): Long3 = cross(other)
/** [Long3.dot] as an infix function, so `a dot b` parses. */
inline infix fun Long3.dot(other: Long3): Long = dot(other)
/** Component 1 of this Long3 (`x()`), for destructuring declarations. */
inline operator fun Long3.component1(): Long = x()
/** Component 2 of this Long3 (`y()`), for destructuring declarations. */
inline operator fun Long3.component2(): Long = y()
/** Component 3 of this Long3 (`z()`), for destructuring declarations. */
inline operator fun Long3.component3(): Long = z()
/** A copy of this Long3 with the named components replaced; every component left out keeps its current value. */
inline fun Long3.copy(x: Long = x(), y: Long = y(), z: Long = z()): Long3 = Long3(x, y, z)
/** The components of this Long3 in a new `LongArray`, in storage order. */
inline fun Long3.toLongArray(): LongArray = store(LongArray(3))
/** A Long3 read from this array in storage order, starting at index 0. */
inline fun LongArray.toLong3(): Long3 = Long3.load(this)
/** The component of this Long3 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 2 indices (`v[a, b] = c`) resolves to the Java 3-argument value factory `Long3.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Long3.get(index: Int): Long = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    else -> throw IndexOutOfBoundsException("Long3 index: $index")
}
