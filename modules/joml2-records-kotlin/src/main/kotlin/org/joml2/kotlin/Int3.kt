// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Int3.
// Note: the record's Java `not()` doubles as Kotlin's unary `!` operator on this
// type, and it is the BITWISE complement (`!v` == `v.not()`), not a logical negation.
package org.joml2.kotlin

import org.joml2.*

/** [Int3.add] as the `+` operator. */
inline operator fun Int3.plus(other: Int3): Int3 = add(other)
/** [Int3.div] as the `/` operator. */
inline operator fun Int3.div(scalar: Int): Int3 = div(scalar)
/** [Int3.div] as the `/` operator. */
inline operator fun Int3.div(other: Int3): Int3 = div(other)
/** [Int3.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Int3.mod(scalar: Int): Int3 = floorMod(scalar)
/** [Int3.floorMod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Int3.mod(other: Int3): Int3 = floorMod(other)
/** [Int3.mul] as the `*` operator. */
inline operator fun Int3.times(scalar: Int): Int3 = mul(scalar)
/** [Int3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Int.times(v: Int3): Int3 = v.mul(this)
/** [Int3.mul] as the `*` operator. */
inline operator fun Int3.times(other: Int3): Int3 = mul(other)
/** [Int3.negate] as the unary `-` operator. */
inline operator fun Int3.unaryMinus(): Int3 = negate()
/** [Int3.rem] as the `%` operator. */
inline operator fun Int3.rem(scalar: Int): Int3 = rem(scalar)
/** [Int3.rem] as the `%` operator. */
inline operator fun Int3.rem(other: Int3): Int3 = rem(other)
/** [Int3.sub] as the `-` operator. */
inline operator fun Int3.minus(other: Int3): Int3 = sub(other)
/** [Int3.cross] as an infix function, so `a cross b` parses. */
inline infix fun Int3.cross(other: Int3): Int3 = cross(other)
/** [Int3.dot] as an infix function, so `a dot b` parses. */
inline infix fun Int3.dot(other: Int3): Long = dot(other)
/** Component 1 of this Int3 (`x()`), for destructuring declarations. */
inline operator fun Int3.component1(): Int = x()
/** Component 2 of this Int3 (`y()`), for destructuring declarations. */
inline operator fun Int3.component2(): Int = y()
/** Component 3 of this Int3 (`z()`), for destructuring declarations. */
inline operator fun Int3.component3(): Int = z()
/** A copy of this Int3 with the named components replaced; every component left out keeps its current value. */
inline fun Int3.copy(x: Int = x(), y: Int = y(), z: Int = z()): Int3 = Int3(x, y, z)
/** The components of this Int3 in a new `IntArray`, in storage order. */
inline fun Int3.toIntArray(): IntArray = store(IntArray(3))
/** A Int3 read from this array in storage order, starting at index 0. */
inline fun IntArray.toInt3(): Int3 = Int3.load(this)
/** The component of this Int3 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 2 indices (`v[a, b] = c`) resolves to the Java 3-argument value factory `Int3.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Int3.get(index: Int): Int = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    else -> throw IndexOutOfBoundsException("Int3 index: $index")
}
