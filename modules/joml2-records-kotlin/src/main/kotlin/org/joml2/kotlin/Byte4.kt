// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Byte4.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Byte4.add] as the `+` operator. */
inline operator fun Byte4.plus(other: Byte4): Byte4 = add(other)
/** [Byte4.div] as the `/` operator. */
inline operator fun Byte4.div(scalar: Byte): Byte4 = div(scalar)
/** [Byte4.div] as the `/` operator. */
inline operator fun Byte4.div(other: Byte4): Byte4 = div(other)
/** [Byte4.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Byte4.mod(scalar: Byte): Byte4 = floorMod(scalar)
/** [Byte4.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Byte4.mod(other: Byte4): Byte4 = floorMod(other)
/** [Byte4.mul] as the `*` operator. */
inline operator fun Byte4.times(scalar: Byte): Byte4 = mul(scalar)
/** [Byte4.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Byte.times(v: Byte4): Byte4 = v.mul(this)
/** [Byte4.mul] as the `*` operator. */
inline operator fun Byte4.times(other: Byte4): Byte4 = mul(other)
/** [Byte4.negate] as the unary `-` operator. */
inline operator fun Byte4.unaryMinus(): Byte4 = negate()
/** [Byte4.rem] as the `%` operator. */
inline operator fun Byte4.rem(scalar: Byte): Byte4 = rem(scalar)
/** [Byte4.rem] as the `%` operator. */
inline operator fun Byte4.rem(other: Byte4): Byte4 = rem(other)
/** [Byte4.sub] as the `-` operator. */
inline operator fun Byte4.minus(other: Byte4): Byte4 = sub(other)
/** [Byte4.dot] as an infix function, so `a dot b` parses. */
inline infix fun Byte4.dot(other: Byte4): Int = dot(other)
/** Component 1 of this Byte4 (`x()`), for destructuring declarations. */
inline operator fun Byte4.component1(): Byte = x()
/** Component 2 of this Byte4 (`y()`), for destructuring declarations. */
inline operator fun Byte4.component2(): Byte = y()
/** Component 3 of this Byte4 (`z()`), for destructuring declarations. */
inline operator fun Byte4.component3(): Byte = z()
/** Component 4 of this Byte4 (`w()`), for destructuring declarations. */
inline operator fun Byte4.component4(): Byte = w()
/** A copy of this Byte4 with the named components replaced; every component left out keeps its current value. */
inline fun Byte4.copy(x: Byte = x(), y: Byte = y(), z: Byte = z(), w: Byte = w()): Byte4 = Byte4(x, y, z, w)
/** The components of this Byte4 in a new `ByteArray`, in storage order. */
inline fun Byte4.toByteArray(): ByteArray = store(ByteArray(4))
/** A Byte4 read from this array in storage order, starting at index 0. */
inline fun ByteArray.toByte4(): Byte4 = Byte4.load(this)
/** The component of this Byte4 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 3 indices (`v[a, b, ...] = x`) resolves to the Java 4-argument value factory `Byte4.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Byte4.get(index: Int): Byte = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    3 -> w()
    else -> throw IndexOutOfBoundsException("Byte4 index: $index")
}
