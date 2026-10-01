// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Short2.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Short2.add] as the `+` operator. */
inline operator fun Short2.plus(other: Short2): Short2 = add(other)
/** [Short2.div] as the `/` operator. */
inline operator fun Short2.div(scalar: Short): Short2 = div(scalar)
/** [Short2.div] as the `/` operator. */
inline operator fun Short2.div(other: Short2): Short2 = div(other)
/** [Short2.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Short2.mod(scalar: Short): Short2 = floorMod(scalar)
/** [Short2.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Short2.mod(other: Short2): Short2 = floorMod(other)
/** [Short2.mul] as the `*` operator. */
inline operator fun Short2.times(scalar: Short): Short2 = mul(scalar)
/** [Short2.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Short.times(v: Short2): Short2 = v.mul(this)
/** [Short2.mul] as the `*` operator. */
inline operator fun Short2.times(other: Short2): Short2 = mul(other)
/** [Short2.negate] as the unary `-` operator. */
inline operator fun Short2.unaryMinus(): Short2 = negate()
/** [Short2.rem] as the `%` operator. */
inline operator fun Short2.rem(scalar: Short): Short2 = rem(scalar)
/** [Short2.rem] as the `%` operator. */
inline operator fun Short2.rem(other: Short2): Short2 = rem(other)
/** [Short2.sub] as the `-` operator. */
inline operator fun Short2.minus(other: Short2): Short2 = sub(other)
/** [Short2.cross] as an infix function, so `a cross b` parses. */
inline infix fun Short2.cross(other: Short2): Long = cross(other)
/** [Short2.dot] as an infix function, so `a dot b` parses. */
inline infix fun Short2.dot(other: Short2): Long = dot(other)
/** Component 1 of this Short2 (`x()`), for destructuring declarations. */
inline operator fun Short2.component1(): Short = x()
/** Component 2 of this Short2 (`y()`), for destructuring declarations. */
inline operator fun Short2.component2(): Short = y()
/** A copy of this Short2 with the named components replaced; every component left out keeps its current value. */
inline fun Short2.copy(x: Short = x(), y: Short = y()): Short2 = Short2(x, y)
/** The components of this Short2 in a new `ShortArray`, in storage order. */
inline fun Short2.toShortArray(): ShortArray = store(ShortArray(2))
/** A Short2 read from this array in storage order, starting at index 0. */
inline fun ShortArray.toShort2(): Short2 = Short2.load(this)
/** The component of this Short2 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 1 index (`v[a] = b`) resolves to the Java 2-argument value factory `Short2.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Short2.get(index: Int): Short = when (index) {
    0 -> x()
    1 -> y()
    else -> throw IndexOutOfBoundsException("Short2 index: $index")
}
