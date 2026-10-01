// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Short3.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Short3.add] as the `+` operator. */
inline operator fun Short3.plus(other: Short3): Short3 = add(other)
/** [Short3.div] as the `/` operator. */
inline operator fun Short3.div(scalar: Short): Short3 = div(scalar)
/** [Short3.div] as the `/` operator. */
inline operator fun Short3.div(other: Short3): Short3 = div(other)
/** [Short3.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Short3.mod(scalar: Short): Short3 = floorMod(scalar)
/** [Short3.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Short3.mod(other: Short3): Short3 = floorMod(other)
/** [Short3.mul] as the `*` operator. */
inline operator fun Short3.times(scalar: Short): Short3 = mul(scalar)
/** [Short3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Short.times(v: Short3): Short3 = v.mul(this)
/** [Short3.mul] as the `*` operator. */
inline operator fun Short3.times(other: Short3): Short3 = mul(other)
/** [Short3.negate] as the unary `-` operator. */
inline operator fun Short3.unaryMinus(): Short3 = negate()
/** [Short3.rem] as the `%` operator. */
inline operator fun Short3.rem(scalar: Short): Short3 = rem(scalar)
/** [Short3.rem] as the `%` operator. */
inline operator fun Short3.rem(other: Short3): Short3 = rem(other)
/** [Short3.sub] as the `-` operator. */
inline operator fun Short3.minus(other: Short3): Short3 = sub(other)
/** [Short3.cross] as an infix function, so `a cross b` parses. */
inline infix fun Short3.cross(other: Short3): Short3 = cross(other)
/** [Short3.dot] as an infix function, so `a dot b` parses. */
inline infix fun Short3.dot(other: Short3): Long = dot(other)
/** Component 1 of this Short3 (`x()`), for destructuring declarations. */
inline operator fun Short3.component1(): Short = x()
/** Component 2 of this Short3 (`y()`), for destructuring declarations. */
inline operator fun Short3.component2(): Short = y()
/** Component 3 of this Short3 (`z()`), for destructuring declarations. */
inline operator fun Short3.component3(): Short = z()
/** A copy of this Short3 with the named components replaced; every component left out keeps its current value. */
inline fun Short3.copy(x: Short = x(), y: Short = y(), z: Short = z()): Short3 = Short3(x, y, z)
/** The components of this Short3 in a new `ShortArray`, in storage order. */
inline fun Short3.toShortArray(): ShortArray = store(ShortArray(3))
/** A Short3 read from this array in storage order, starting at index 0. */
inline fun ShortArray.toShort3(): Short3 = Short3.load(this)
/** The component of this Short3 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 2 indices (`v[a, b] = c`) resolves to the Java 3-argument value factory `Short3.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Short3.get(index: Int): Short = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    else -> throw IndexOutOfBoundsException("Short3 index: $index")
}
