// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Double2.
package org.joml2.kotlin

import org.joml2.*

/** [Double2.add] as the `+` operator. */
inline operator fun Double2.plus(other: Double2): Double2 = add(other)
/** [Double2.div] as the `/` operator. */
inline operator fun Double2.div(scalar: Double): Double2 = div(scalar)
/** [Double2.div] as the `/` operator. */
inline operator fun Double2.div(other: Double2): Double2 = div(other)
/** [Double2.mul] as the `*` operator. */
inline operator fun Double2.times(scalar: Double): Double2 = mul(scalar)
/** [Double2.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: Double2): Double2 = v.mul(this)
/** [Double2.mul] as the `*` operator. */
inline operator fun Double2.times(other: Double2): Double2 = mul(other)
/** [Double2.negate] as the unary `-` operator. */
inline operator fun Double2.unaryMinus(): Double2 = negate()
/** [Double2.sub] as the `-` operator. */
inline operator fun Double2.minus(other: Double2): Double2 = sub(other)
/** [Double2.angleBetween] as an infix function, so `a angleBetween b` parses. */
inline infix fun Double2.angleBetween(other: Double2): Double = angleBetween(other)
/** [Double2.cross] as an infix function, so `a cross b` parses. */
inline infix fun Double2.cross(other: Double2): Double = cross(other)
/** [Double2.distance] as an infix function, so `a distance b` parses. */
inline infix fun Double2.distance(other: Double2): Double = distance(other)
/** [Double2.dot] as an infix function, so `a dot b` parses. */
inline infix fun Double2.dot(other: Double2): Double = dot(other)
/** [Double2.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Double2.mod(y: Double): Double2 = mod(y)
/** [Double2.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Double2.mod(y: Double2): Double2 = mod(y)
/** [Double2.rotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double2.rotate(angle: Angle): Double2 = rotate(angle.radians)
/** [Double2.rotateAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double2.rotateAround(angle: Angle, pivot: Double2): Double2 = rotateAround(angle.radians, pivot)
/** Component 1 of this Double2 (`x()`), for destructuring declarations. */
inline operator fun Double2.component1(): Double = x()
/** Component 2 of this Double2 (`y()`), for destructuring declarations. */
inline operator fun Double2.component2(): Double = y()
/** A copy of this Double2 with the named components replaced; every component left out keeps its current value. */
inline fun Double2.copy(x: Double = x(), y: Double = y()): Double2 = Double2(x, y)
/** The components of this Double2 in a new `DoubleArray`, in storage order. */
inline fun Double2.toDoubleArray(): DoubleArray = store(DoubleArray(2))
/** A Double2 read from this array in storage order, starting at index 0. */
inline fun DoubleArray.toDouble2(): Double2 = Double2.load(this)
/** The component of this Double2 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 1 index (`v[a] = b`) resolves to the Java 2-argument value factory `Double2.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Double2.get(index: Int): Double = when (index) {
    0 -> x()
    1 -> y()
    else -> throw IndexOutOfBoundsException("Double2 index: $index")
}
