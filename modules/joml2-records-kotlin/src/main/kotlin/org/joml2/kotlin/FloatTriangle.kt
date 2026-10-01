// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatTriangle.
package org.joml2.kotlin

import org.joml2.*

/** [FloatTriangle.transform] as the `*` operator. */
inline operator fun FloatTriangle.times(m: Float3x4): FloatTriangle = transform(m)
/** [FloatTriangle.transform] as the `*` operator. */
inline operator fun FloatTriangle.times(m: Float4x4): FloatTriangle = transform(m)
/** [FloatTriangle.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun FloatTriangle.contains(p: Float3): Boolean = containsPoint(p)
/** Component 1 of this FloatTriangle for destructuring declarations: the first vertex (`v0X()`, `v0Y()`, `v0Z()`). */
inline operator fun FloatTriangle.component1(): Float3 = Float3(v0X(), v0Y(), v0Z())
/** Component 2 of this FloatTriangle for destructuring declarations: the second vertex (`v1X()`, `v1Y()`, `v1Z()`). */
inline operator fun FloatTriangle.component2(): Float3 = Float3(v1X(), v1Y(), v1Z())
/** Component 3 of this FloatTriangle for destructuring declarations: the third vertex (`v2X()`, `v2Y()`, `v2Z()`). */
inline operator fun FloatTriangle.component3(): Float3 = Float3(v2X(), v2Y(), v2Z())
/** A copy of this FloatTriangle with the named parts replaced; every part left out keeps its current value. */
inline fun FloatTriangle.copy(v0: Float3 = Float3(v0X(), v0Y(), v0Z()), v1: Float3 = Float3(v1X(), v1Y(), v1Z()), v2: Float3 = Float3(v2X(), v2Y(), v2Z())): FloatTriangle = FloatTriangle(v0, v1, v2)
