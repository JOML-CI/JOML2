// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Byte3.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Byte3.add] as the `+` operator. */
inline operator fun Byte3.plus(other: Byte3): Byte3 = add(other)
/** [Byte3.div] as the `/` operator. */
inline operator fun Byte3.div(scalar: Byte): Byte3 = div(scalar)
/** [Byte3.div] as the `/` operator. */
inline operator fun Byte3.div(other: Byte3): Byte3 = div(other)
/** [Byte3.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Byte3.mod(scalar: Byte): Byte3 = floorMod(scalar)
/** [Byte3.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Byte3.mod(other: Byte3): Byte3 = floorMod(other)
/** [Byte3.mul] as the `*` operator. */
inline operator fun Byte3.times(scalar: Byte): Byte3 = mul(scalar)
/** [Byte3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Byte.times(v: Byte3): Byte3 = v.mul(this)
/** [Byte3.mul] as the `*` operator. */
inline operator fun Byte3.times(other: Byte3): Byte3 = mul(other)
/** [Byte3.negate] as the unary `-` operator. */
inline operator fun Byte3.unaryMinus(): Byte3 = negate()
/** [Byte3.rem] as the `%` operator. */
inline operator fun Byte3.rem(scalar: Byte): Byte3 = rem(scalar)
/** [Byte3.rem] as the `%` operator. */
inline operator fun Byte3.rem(other: Byte3): Byte3 = rem(other)
/** [Byte3.sub] as the `-` operator. */
inline operator fun Byte3.minus(other: Byte3): Byte3 = sub(other)
/** [Byte3.cross] as an infix function, so `a cross b` parses. */
inline infix fun Byte3.cross(other: Byte3): Byte3 = cross(other)
/** [Byte3.dot] as an infix function, so `a dot b` parses. */
inline infix fun Byte3.dot(other: Byte3): Int = dot(other)
/** Component 1 of this Byte3 (`x()`), for destructuring declarations. */
inline operator fun Byte3.component1(): Byte = x()
/** Component 2 of this Byte3 (`y()`), for destructuring declarations. */
inline operator fun Byte3.component2(): Byte = y()
/** Component 3 of this Byte3 (`z()`), for destructuring declarations. */
inline operator fun Byte3.component3(): Byte = z()
/** A copy of this Byte3 with the named components replaced; every component left out keeps its current value. */
inline fun Byte3.copy(x: Byte = x(), y: Byte = y(), z: Byte = z()): Byte3 = Byte3(x, y, z)
/** The components of this Byte3 in a new `ByteArray`, in storage order. */
inline fun Byte3.toByteArray(): ByteArray = store(ByteArray(3))
/** A Byte3 read from this array in storage order, starting at index 0. */
inline fun ByteArray.toByte3(): Byte3 = Byte3.load(this)
/** The component of this Byte3 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 2 indices (`v[a, b] = c`) resolves to the Java 3-argument value factory `Byte3.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Byte3.get(index: Int): Byte = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    else -> throw IndexOutOfBoundsException("Byte3 index: $index")
}
