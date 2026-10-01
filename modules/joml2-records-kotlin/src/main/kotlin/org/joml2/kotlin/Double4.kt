// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Double4.
package org.joml2.kotlin

import org.joml2.*

/** [Double4.add] as the `+` operator. */
inline operator fun Double4.plus(other: Double4): Double4 = add(other)
/** [Double4.div] as the `/` operator. */
inline operator fun Double4.div(scalar: Double): Double4 = div(scalar)
/** [Double4.div] as the `/` operator. */
inline operator fun Double4.div(other: Double4): Double4 = div(other)
/** [Double4.mul] as the `*` operator. */
inline operator fun Double4.times(scalar: Double): Double4 = mul(scalar)
/** [Double4.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: Double4): Double4 = v.mul(this)
/** [Double4.mul] as the `*` operator. */
inline operator fun Double4.times(other: Double4): Double4 = mul(other)
/** [Double4.negate] as the unary `-` operator. */
inline operator fun Double4.unaryMinus(): Double4 = negate()
/** [Double4.sub] as the `-` operator. */
inline operator fun Double4.minus(other: Double4): Double4 = sub(other)
/** [Double4.angleBetween] as an infix function, so `a angleBetween b` parses. */
inline infix fun Double4.angleBetween(other: Double4): Double = angleBetween(other)
/** [Double4.distance] as an infix function, so `a distance b` parses. */
inline infix fun Double4.distance(other: Double4): Double = distance(other)
/** [Double4.dot] as an infix function, so `a dot b` parses. */
inline infix fun Double4.dot(other: Double4): Double = dot(other)
/** [Double4.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Double4.mod(y: Double): Double4 = mod(y)
/** [Double4.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Double4.mod(y: Double4): Double4 = mod(y)
/** [Double4.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4.rotateAxis(angle: Angle, axis: Double3): Double4 = rotateAxis(angle.radians, axis)
/** [Double4.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4.rotateX(angle: Angle): Double4 = rotateX(angle.radians)
/** [Double4.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4.rotateY(angle: Angle): Double4 = rotateY(angle.radians)
/** [Double4.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4.rotateZ(angle: Angle): Double4 = rotateZ(angle.radians)
/** Component 1 of this Double4 (`x()`), for destructuring declarations. */
inline operator fun Double4.component1(): Double = x()
/** Component 2 of this Double4 (`y()`), for destructuring declarations. */
inline operator fun Double4.component2(): Double = y()
/** Component 3 of this Double4 (`z()`), for destructuring declarations. */
inline operator fun Double4.component3(): Double = z()
/** Component 4 of this Double4 (`w()`), for destructuring declarations. */
inline operator fun Double4.component4(): Double = w()
/** A copy of this Double4 with the named components replaced; every component left out keeps its current value. */
inline fun Double4.copy(x: Double = x(), y: Double = y(), z: Double = z(), w: Double = w()): Double4 = Double4(x, y, z, w)
/** The components of this Double4 in a new `DoubleArray`, in storage order. */
inline fun Double4.toDoubleArray(): DoubleArray = store(DoubleArray(4))
/** A Double4 read from this array in storage order, starting at index 0. */
inline fun DoubleArray.toDouble4(): Double4 = Double4.load(this)
/** The component of this Double4 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 3 indices (`v[a, b, ...] = x`) resolves to the Java 4-argument value factory `Double4.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Double4.get(index: Int): Double = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    3 -> w()
    else -> throw IndexOutOfBoundsException("Double4 index: $index")
}
