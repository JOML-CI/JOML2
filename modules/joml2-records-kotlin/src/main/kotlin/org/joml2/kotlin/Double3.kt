// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Double3.
package org.joml2.kotlin

import org.joml2.*

/** [Double3.add] as the `+` operator. */
inline operator fun Double3.plus(other: Double3): Double3 = add(other)
/** [Double3.div] as the `/` operator. */
inline operator fun Double3.div(scalar: Double): Double3 = div(scalar)
/** [Double3.div] as the `/` operator. */
inline operator fun Double3.div(other: Double3): Double3 = div(other)
/** [Double3.mul] as the `*` operator. */
inline operator fun Double3.times(scalar: Double): Double3 = mul(scalar)
/** [Double3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: Double3): Double3 = v.mul(this)
/** [Double3.mul] as the `*` operator. */
inline operator fun Double3.times(other: Double3): Double3 = mul(other)
/** [Double3.negate] as the unary `-` operator. */
inline operator fun Double3.unaryMinus(): Double3 = negate()
/** [Double3.sub] as the `-` operator. */
inline operator fun Double3.minus(other: Double3): Double3 = sub(other)
/** [Double3.angleBetween] as an infix function, so `a angleBetween b` parses. */
inline infix fun Double3.angleBetween(other: Double3): Double = angleBetween(other)
/** [Double3.cross] as an infix function, so `a cross b` parses. */
inline infix fun Double3.cross(other: Double3): Double3 = cross(other)
/** [Double3.distance] as an infix function, so `a distance b` parses. */
inline infix fun Double3.distance(other: Double3): Double = distance(other)
/** [Double3.dot] as an infix function, so `a dot b` parses. */
inline infix fun Double3.dot(other: Double3): Double = dot(other)
/** [Double3.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Double3.mod(y: Double): Double3 = mod(y)
/** [Double3.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Double3.mod(y: Double3): Double3 = mod(y)
/** [Double3.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3.rotateAxis(angle: Angle, axis: Double3): Double3 = rotateAxis(angle.radians, axis)
/** [Double3.rotateAxisAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3.rotateAxisAround(angle: Angle, axis: Double3, pivot: Double3): Double3 = rotateAxisAround(angle.radians, axis, pivot)
/** [Double3.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3.rotateX(angle: Angle): Double3 = rotateX(angle.radians)
/** [Double3.rotateXAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3.rotateXAround(angle: Angle, pivot: Double3): Double3 = rotateXAround(angle.radians, pivot)
/** [Double3.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3.rotateY(angle: Angle): Double3 = rotateY(angle.radians)
/** [Double3.rotateYAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3.rotateYAround(angle: Angle, pivot: Double3): Double3 = rotateYAround(angle.radians, pivot)
/** [Double3.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3.rotateZ(angle: Angle): Double3 = rotateZ(angle.radians)
/** [Double3.rotateZAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double3.rotateZAround(angle: Angle, pivot: Double3): Double3 = rotateZAround(angle.radians, pivot)
/** Component 1 of this Double3 (`x()`), for destructuring declarations. */
inline operator fun Double3.component1(): Double = x()
/** Component 2 of this Double3 (`y()`), for destructuring declarations. */
inline operator fun Double3.component2(): Double = y()
/** Component 3 of this Double3 (`z()`), for destructuring declarations. */
inline operator fun Double3.component3(): Double = z()
/** A copy of this Double3 with the named components replaced; every component left out keeps its current value. */
inline fun Double3.copy(x: Double = x(), y: Double = y(), z: Double = z()): Double3 = Double3(x, y, z)
/** The components of this Double3 in a new `DoubleArray`, in storage order. */
inline fun Double3.toDoubleArray(): DoubleArray = store(DoubleArray(3))
/** A Double3 read from this array in storage order, starting at index 0. */
inline fun DoubleArray.toDouble3(): Double3 = Double3.load(this)
/** The component of this Double3 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 2 indices (`v[a, b] = c`) resolves to the Java 3-argument value factory `Double3.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Double3.get(index: Int): Double = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    else -> throw IndexOutOfBoundsException("Double3 index: $index")
}
