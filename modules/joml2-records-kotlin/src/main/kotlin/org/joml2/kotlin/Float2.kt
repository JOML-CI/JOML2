// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Float2.
package org.joml2.kotlin

import org.joml2.*

/** [Float2.add] as the `+` operator. */
inline operator fun Float2.plus(other: Float2): Float2 = add(other)
/** [Float2.div] as the `/` operator. */
inline operator fun Float2.div(scalar: Float): Float2 = div(scalar)
/** [Float2.div] as the `/` operator. */
inline operator fun Float2.div(other: Float2): Float2 = div(other)
/** [Float2.mul] as the `*` operator. */
inline operator fun Float2.times(scalar: Float): Float2 = mul(scalar)
/** [Float2.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: Float2): Float2 = v.mul(this)
/** [Float2.mul] as the `*` operator. */
inline operator fun Float2.times(other: Float2): Float2 = mul(other)
/** [Float2.negate] as the unary `-` operator. */
inline operator fun Float2.unaryMinus(): Float2 = negate()
/** [Float2.sub] as the `-` operator. */
inline operator fun Float2.minus(other: Float2): Float2 = sub(other)
/** [Float2.angleBetween] as an infix function, so `a angleBetween b` parses. */
inline infix fun Float2.angleBetween(other: Float2): Float = angleBetween(other)
/** [Float2.cross] as an infix function, so `a cross b` parses. */
inline infix fun Float2.cross(other: Float2): Float = cross(other)
/** [Float2.distance] as an infix function, so `a distance b` parses. */
inline infix fun Float2.distance(other: Float2): Float = distance(other)
/** [Float2.dot] as an infix function, so `a dot b` parses. */
inline infix fun Float2.dot(other: Float2): Float = dot(other)
/** [Float2.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Float2.mod(y: Float): Float2 = mod(y)
/** [Float2.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Float2.mod(y: Float2): Float2 = mod(y)
/** [Float2.rotate] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float2.rotate(angle: Angle): Float2 = rotate(angle.radians.toFloat())
/** [Float2.rotateAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float2.rotateAround(angle: Angle, pivot: Float2): Float2 = rotateAround(angle.radians.toFloat(), pivot)
/** Component 1 of this Float2 (`x()`), for destructuring declarations. */
inline operator fun Float2.component1(): Float = x()
/** Component 2 of this Float2 (`y()`), for destructuring declarations. */
inline operator fun Float2.component2(): Float = y()
/** A copy of this Float2 with the named components replaced; every component left out keeps its current value. */
inline fun Float2.copy(x: Float = x(), y: Float = y()): Float2 = Float2(x, y)
/** The components of this Float2 in a new `FloatArray`, in storage order. */
inline fun Float2.toFloatArray(): FloatArray = store(FloatArray(2))
/** A Float2 read from this array in storage order, starting at index 0. */
inline fun FloatArray.toFloat2(): Float2 = Float2.load(this)
/** The component of this Float2 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 1 index (`v[a] = b`) resolves to the Java 2-argument value factory `Float2.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Float2.get(index: Int): Float = when (index) {
    0 -> x()
    1 -> y()
    else -> throw IndexOutOfBoundsException("Float2 index: $index")
}
