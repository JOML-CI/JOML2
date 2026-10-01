// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Float3.
package org.joml2.kotlin

import org.joml2.*

/** [Float3.add] as the `+` operator. */
inline operator fun Float3.plus(other: Float3): Float3 = add(other)
/** [Float3.div] as the `/` operator. */
inline operator fun Float3.div(scalar: Float): Float3 = div(scalar)
/** [Float3.div] as the `/` operator. */
inline operator fun Float3.div(other: Float3): Float3 = div(other)
/** [Float3.mul] as the `*` operator. */
inline operator fun Float3.times(scalar: Float): Float3 = mul(scalar)
/** [Float3.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: Float3): Float3 = v.mul(this)
/** [Float3.mul] as the `*` operator. */
inline operator fun Float3.times(other: Float3): Float3 = mul(other)
/** [Float3.negate] as the unary `-` operator. */
inline operator fun Float3.unaryMinus(): Float3 = negate()
/** [Float3.sub] as the `-` operator. */
inline operator fun Float3.minus(other: Float3): Float3 = sub(other)
/** [Float3.angleBetween] as an infix function, so `a angleBetween b` parses. */
inline infix fun Float3.angleBetween(other: Float3): Float = angleBetween(other)
/** [Float3.cross] as an infix function, so `a cross b` parses. */
inline infix fun Float3.cross(other: Float3): Float3 = cross(other)
/** [Float3.distance] as an infix function, so `a distance b` parses. */
inline infix fun Float3.distance(other: Float3): Float = distance(other)
/** [Float3.dot] as an infix function, so `a dot b` parses. */
inline infix fun Float3.dot(other: Float3): Float = dot(other)
/** [Float3.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Float3.mod(y: Float): Float3 = mod(y)
/** [Float3.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Float3.mod(y: Float3): Float3 = mod(y)
/** [Float3.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3.rotateAxis(angle: Angle, axis: Float3): Float3 = rotateAxis(angle.radians.toFloat(), axis)
/** [Float3.rotateAxisAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3.rotateAxisAround(angle: Angle, axis: Float3, pivot: Float3): Float3 = rotateAxisAround(angle.radians.toFloat(), axis, pivot)
/** [Float3.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3.rotateX(angle: Angle): Float3 = rotateX(angle.radians.toFloat())
/** [Float3.rotateXAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3.rotateXAround(angle: Angle, pivot: Float3): Float3 = rotateXAround(angle.radians.toFloat(), pivot)
/** [Float3.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3.rotateY(angle: Angle): Float3 = rotateY(angle.radians.toFloat())
/** [Float3.rotateYAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3.rotateYAround(angle: Angle, pivot: Float3): Float3 = rotateYAround(angle.radians.toFloat(), pivot)
/** [Float3.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3.rotateZ(angle: Angle): Float3 = rotateZ(angle.radians.toFloat())
/** [Float3.rotateZAround] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float3.rotateZAround(angle: Angle, pivot: Float3): Float3 = rotateZAround(angle.radians.toFloat(), pivot)
/** Component 1 of this Float3 (`x()`), for destructuring declarations. */
inline operator fun Float3.component1(): Float = x()
/** Component 2 of this Float3 (`y()`), for destructuring declarations. */
inline operator fun Float3.component2(): Float = y()
/** Component 3 of this Float3 (`z()`), for destructuring declarations. */
inline operator fun Float3.component3(): Float = z()
/** A copy of this Float3 with the named components replaced; every component left out keeps its current value. */
inline fun Float3.copy(x: Float = x(), y: Float = y(), z: Float = z()): Float3 = Float3(x, y, z)
/** The components of this Float3 in a new `FloatArray`, in storage order. */
inline fun Float3.toFloatArray(): FloatArray = store(FloatArray(3))
/** A Float3 read from this array in storage order, starting at index 0. */
inline fun FloatArray.toFloat3(): Float3 = Float3.load(this)
/** The component of this Float3 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 2 indices (`v[a, b] = c`) resolves to the Java 3-argument value factory `Float3.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Float3.get(index: Int): Float = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    else -> throw IndexOutOfBoundsException("Float3 index: $index")
}
