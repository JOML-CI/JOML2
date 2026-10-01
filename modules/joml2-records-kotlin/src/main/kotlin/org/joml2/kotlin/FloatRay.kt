// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatRay.
package org.joml2.kotlin

import org.joml2.*

/** [FloatRay.transform] as the `*` operator. */
inline operator fun FloatRay.times(m: Float3x4): FloatRay = transform(m)
/** [FloatRay.transform] as the `*` operator. */
inline operator fun FloatRay.times(m: Float4x4): FloatRay = transform(m)
/** [FloatRay.at] as the indexing operator: `ray[t]` is the point at parameter `t` along the ray. */
inline operator fun FloatRay.get(t: Float): Float3 = at(t)
/** Component 1 of this FloatRay for destructuring declarations: the origin of the ray (`oX()`, `oY()`, `oZ()`). */
inline operator fun FloatRay.component1(): Float3 = Float3(oX(), oY(), oZ())
/** Component 2 of this FloatRay for destructuring declarations: the direction of the ray, taken as given (not normalized) (`dX()`, `dY()`, `dZ()`). */
inline operator fun FloatRay.component2(): Float3 = Float3(dX(), dY(), dZ())
/** A copy of this FloatRay with the named parts replaced; every part left out keeps its current value. */
inline fun FloatRay.copy(origin: Float3 = Float3(oX(), oY(), oZ()), direction: Float3 = Float3(dX(), dY(), dZ())): FloatRay = FloatRay(origin, direction)
