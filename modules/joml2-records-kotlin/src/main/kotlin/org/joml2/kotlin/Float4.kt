// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Float4.
package org.joml2.kotlin

import org.joml2.*

/** [Float4.add] as the `+` operator. */
inline operator fun Float4.plus(other: Float4): Float4 = add(other)
/** [Float4.div] as the `/` operator. */
inline operator fun Float4.div(scalar: Float): Float4 = div(scalar)
/** [Float4.div] as the `/` operator. */
inline operator fun Float4.div(other: Float4): Float4 = div(other)
/** [Float4.mul] as the `*` operator. */
inline operator fun Float4.times(scalar: Float): Float4 = mul(scalar)
/** [Float4.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Float.times(v: Float4): Float4 = v.mul(this)
/** [Float4.mul] as the `*` operator. */
inline operator fun Float4.times(other: Float4): Float4 = mul(other)
/** [Float4.negate] as the unary `-` operator. */
inline operator fun Float4.unaryMinus(): Float4 = negate()
/** [Float4.sub] as the `-` operator. */
inline operator fun Float4.minus(other: Float4): Float4 = sub(other)
/** [Float4.angleBetween] as an infix function, so `a angleBetween b` parses. */
inline infix fun Float4.angleBetween(other: Float4): Float = angleBetween(other)
/** [Float4.distance] as an infix function, so `a distance b` parses. */
inline infix fun Float4.distance(other: Float4): Float = distance(other)
/** [Float4.dot] as an infix function, so `a dot b` parses. */
inline infix fun Float4.dot(other: Float4): Float = dot(other)
/** [Float4.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Float4.mod(y: Float): Float4 = mod(y)
/** [Float4.mod] as the `mod` infix function, matching the name Kotlin uses for that operation. */
inline infix fun Float4.mod(y: Float4): Float4 = mod(y)
/** [Float4.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float4.rotateAxis(angle: Angle, axis: Float3): Float4 = rotateAxis(angle.radians.toFloat(), axis)
/** [Float4.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float4.rotateX(angle: Angle): Float4 = rotateX(angle.radians.toFloat())
/** [Float4.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float4.rotateY(angle: Angle): Float4 = rotateY(angle.radians.toFloat())
/** [Float4.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Float4.rotateZ(angle: Angle): Float4 = rotateZ(angle.radians.toFloat())
/** Component 1 of this Float4 (`x()`), for destructuring declarations. */
inline operator fun Float4.component1(): Float = x()
/** Component 2 of this Float4 (`y()`), for destructuring declarations. */
inline operator fun Float4.component2(): Float = y()
/** Component 3 of this Float4 (`z()`), for destructuring declarations. */
inline operator fun Float4.component3(): Float = z()
/** Component 4 of this Float4 (`w()`), for destructuring declarations. */
inline operator fun Float4.component4(): Float = w()
/** A copy of this Float4 with the named components replaced; every component left out keeps its current value. */
inline fun Float4.copy(x: Float = x(), y: Float = y(), z: Float = z(), w: Float = w()): Float4 = Float4(x, y, z, w)
/** The components of this Float4 in a new `FloatArray`, in storage order. */
inline fun Float4.toFloatArray(): FloatArray = store(FloatArray(4))
/** A Float4 read from this array in storage order, starting at index 0. */
inline fun FloatArray.toFloat4(): Float4 = Float4.load(this)
/** The component of this Float4 at `index` in storage order, throwing `IndexOutOfBoundsException` when `index` is out of range. Read-only: an indexed ASSIGNMENT with 3 indices (`v[a, b, ...] = x`) resolves to the Java 4-argument value factory `Float4.set(...)`, whose result is discarded - it compiles but is a silent no-op on this immutable record; use `copy(...)` or the `with*` methods instead. */
inline operator fun Float4.get(index: Int): Float = when (index) {
    0 -> x()
    1 -> y()
    2 -> z()
    3 -> w()
    else -> throw IndexOutOfBoundsException("Float4 index: $index")
}
