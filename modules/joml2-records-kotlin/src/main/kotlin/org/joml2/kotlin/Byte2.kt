// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Byte2.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Byte2.add] as the `+` operator. */
inline operator fun Byte2.plus(other: Byte2): Byte2 = add(other)
/** [Byte2.div] as the `/` operator. */
inline operator fun Byte2.div(scalar: Byte): Byte2 = div(scalar)
/** [Byte2.div] as the `/` operator. */
inline operator fun Byte2.div(other: Byte2): Byte2 = div(other)
/** [Byte2.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Byte2.mod(scalar: Byte): Byte2 = floorMod(scalar)
/** [Byte2.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Byte2.mod(other: Byte2): Byte2 = floorMod(other)
/** [Byte2.mul] as the `*` operator. */
inline operator fun Byte2.times(scalar: Byte): Byte2 = mul(scalar)
/** [Byte2.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Byte.times(v: Byte2): Byte2 = v.mul(this)
/** [Byte2.mul] as the `*` operator. */
inline operator fun Byte2.times(other: Byte2): Byte2 = mul(other)
/** [Byte2.negate] as the unary `-` operator. */
inline operator fun Byte2.unaryMinus(): Byte2 = negate()
/** [Byte2.rem] as the `%` operator. */
inline operator fun Byte2.rem(scalar: Byte): Byte2 = rem(scalar)
/** [Byte2.rem] as the `%` operator. */
inline operator fun Byte2.rem(other: Byte2): Byte2 = rem(other)
/** [Byte2.sub] as the `-` operator. */
inline operator fun Byte2.minus(other: Byte2): Byte2 = sub(other)
/** [Byte2.cross] as an infix function, so `a cross b` parses. */
inline infix fun Byte2.cross(other: Byte2): Int = cross(other)
/** [Byte2.dot] as an infix function, so `a dot b` parses. */
inline infix fun Byte2.dot(other: Byte2): Int = dot(other)
/** Component 1 of this Byte2 (`x()`), for destructuring declarations. */
inline operator fun Byte2.component1(): Byte = x()
/** Component 2 of this Byte2 (`y()`), for destructuring declarations. */
inline operator fun Byte2.component2(): Byte = y()
/** A copy of this Byte2 with the named components replaced; every component left out keeps its current value. */
inline fun Byte2.copy(x: Byte = x(), y: Byte = y()): Byte2 = Byte2(x, y)
/** The components of this Byte2 in a new `ByteArray`, in storage order. */
inline fun Byte2.toByteArray(): ByteArray = store(ByteArray(2))
/** A Byte2 read from this array in storage order, starting at index 0. */
inline fun ByteArray.toByte2(): Byte2 = Byte2.load(this)
/** The component of this Byte2 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 1 index (`v[a] = b`) resolves to the Java 2-argument value factory `Byte2.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Byte2.get(index: Int): Byte = when (index) {
    0 -> x()
    1 -> y()
    else -> throw IndexOutOfBoundsException("Byte2 index: $index")
}
