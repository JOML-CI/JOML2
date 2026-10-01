// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Short4.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Short4.add] as the `+` operator. */
inline operator fun Short4.plus(other: Short4): Short4 = add(other)
/** [Short4.div] as the `/` operator. */
inline operator fun Short4.div(scalar: Short): Short4 = div(scalar)
/** [Short4.div] as the `/` operator. */
inline operator fun Short4.div(other: Short4): Short4 = div(other)
/** [Short4.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Short4.mod(scalar: Short): Short4 = floorMod(scalar)
/** [Short4.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Short4.mod(other: Short4): Short4 = floorMod(other)
/** [Short4.mul] as the `*` operator. */
inline operator fun Short4.times(scalar: Short): Short4 = mul(scalar)
/** [Short4.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Short.times(v: Short4): Short4 = v.mul(this)
/** [Short4.mul] as the `*` operator. */
inline operator fun Short4.times(other: Short4): Short4 = mul(other)
/** [Short4.negate] as the unary `-` operator. */
inline operator fun Short4.unaryMinus(): Short4 = negate()
/** [Short4.rem] as the `%` operator. */
inline operator fun Short4.rem(scalar: Short): Short4 = rem(scalar)
/** [Short4.rem] as the `%` operator. */
inline operator fun Short4.rem(other: Short4): Short4 = rem(other)
/** [Short4.sub] as the `-` operator. */
inline operator fun Short4.minus(other: Short4): Short4 = sub(other)
/** [Short4.dot] as an infix function, so `a dot b` parses. */
inline infix fun Short4.dot(other: Short4): Long = dot(other)
/** Component 1 of this Short4 (`x()`), for destructuring declarations. */
inline operator fun Short4.component1(): Short = x()
/** Component 2 of this Short4 (`y()`), for destructuring declarations. */
inline operator fun Short4.component2(): Short = y()
/** Component 3 of this Short4 (`z()`), for destructuring declarations. */
inline operator fun Short4.component3(): Short = z()
/** Component 4 of this Short4 (`w()`), for destructuring declarations. */
inline operator fun Short4.component4(): Short = w()
/** A copy of this Short4 with the named components replaced; every component left out keeps its current value. */
inline fun Short4.copy(x: Short = x(), y: Short = y(), z: Short = z(), w: Short = w()): Short4 = Short4(x, y, z, w)
/** The components of this Short4 in a new `ShortArray`, in storage order. */
inline fun Short4.toShortArray(): ShortArray = store(ShortArray(4))
/** A Short4 read from this array in storage order, starting at index 0. */
inline fun ShortArray.toShort4(): Short4 = Short4.load(this)
/** The component of this Short4 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 3 indices (`v[a, b, ...] = x`) resolves to the Java 4-argument value factory `Short4.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Short4.get(index: Int): Short = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    3 -> w()
    else -> throw IndexOutOfBoundsException("Short4 index: $index")
}
