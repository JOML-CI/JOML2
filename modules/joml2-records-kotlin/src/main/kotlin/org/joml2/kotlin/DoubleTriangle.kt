// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoubleTriangle.
package org.joml2.kotlin

import org.joml2.*

/** [DoubleTriangle.transform] as the `*` operator. */
inline operator fun DoubleTriangle.times(m: Double3x4): DoubleTriangle = transform(m)
/** [DoubleTriangle.transform] as the `*` operator. */
inline operator fun DoubleTriangle.times(m: Double4x4): DoubleTriangle = transform(m)
/** [DoubleTriangle.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun DoubleTriangle.contains(p: Double3): Boolean = containsPoint(p)
/** Component 1 of this DoubleTriangle for destructuring declarations: the first vertex (`v0X()`, `v0Y()`, `v0Z()`). */
inline operator fun DoubleTriangle.component1(): Double3 = Double3(v0X(), v0Y(), v0Z())
/** Component 2 of this DoubleTriangle for destructuring declarations: the second vertex (`v1X()`, `v1Y()`, `v1Z()`). */
inline operator fun DoubleTriangle.component2(): Double3 = Double3(v1X(), v1Y(), v1Z())
/** Component 3 of this DoubleTriangle for destructuring declarations: the third vertex (`v2X()`, `v2Y()`, `v2Z()`). */
inline operator fun DoubleTriangle.component3(): Double3 = Double3(v2X(), v2Y(), v2Z())
/** A copy of this DoubleTriangle with the named parts replaced; every part left out keeps its current value. */
inline fun DoubleTriangle.copy(v0: Double3 = Double3(v0X(), v0Y(), v0Z()), v1: Double3 = Double3(v1X(), v1Y(), v1Z()), v2: Double3 = Double3(v2X(), v2Y(), v2Z())): DoubleTriangle = DoubleTriangle(v0, v1, v2)
