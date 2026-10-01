// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoubleRay.
package org.joml2.kotlin

import org.joml2.*

/** [DoubleRay.transform] as the `*` operator. */
inline operator fun DoubleRay.times(m: Double3x4): DoubleRay = transform(m)
/** [DoubleRay.transform] as the `*` operator. */
inline operator fun DoubleRay.times(m: Double4x4): DoubleRay = transform(m)
/** [DoubleRay.at] as the indexing operator: `ray[t]` is the point at parameter `t` along the ray. */
inline operator fun DoubleRay.get(t: Double): Double3 = at(t)
/** Component 1 of this DoubleRay for destructuring declarations: the origin of the ray (`oX()`, `oY()`, `oZ()`). */
inline operator fun DoubleRay.component1(): Double3 = Double3(oX(), oY(), oZ())
/** Component 2 of this DoubleRay for destructuring declarations: the direction of the ray, taken as given (not normalized) (`dX()`, `dY()`, `dZ()`). */
inline operator fun DoubleRay.component2(): Double3 = Double3(dX(), dY(), dZ())
/** A copy of this DoubleRay with the named parts replaced; every part left out keeps its current value. */
inline fun DoubleRay.copy(origin: Double3 = Double3(oX(), oY(), oZ()), direction: Double3 = Double3(dX(), dY(), dZ())): DoubleRay = DoubleRay(origin, direction)
