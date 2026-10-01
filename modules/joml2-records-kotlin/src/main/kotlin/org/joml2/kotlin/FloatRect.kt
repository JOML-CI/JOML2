// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatRect.
package org.joml2.kotlin

import org.joml2.*

/** [FloatRect.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun FloatRect.contains(p: Float2): Boolean = containsPoint(p)
/** [FloatRect.containsRect] as the `in` operator, so `o in shape` reads as containment. */
inline operator fun FloatRect.contains(o: FloatRect): Boolean = containsRect(o)
/** [FloatRect.intersectsRect] as the `overlaps` infix function. */
inline infix fun FloatRect.overlaps(o: FloatRect): Boolean = intersectsRect(o)
/** Component 1 of this FloatRect for destructuring declarations: the minimum corner of the rectangle (`minX()`, `minY()`). */
inline operator fun FloatRect.component1(): Float2 = Float2(minX(), minY())
/** Component 2 of this FloatRect for destructuring declarations: the maximum corner of the rectangle (`maxX()`, `maxY()`). */
inline operator fun FloatRect.component2(): Float2 = Float2(maxX(), maxY())
/** A copy of this FloatRect with the named parts replaced; every part left out keeps its current value. */
inline fun FloatRect.copy(min: Float2 = Float2(minX(), minY()), max: Float2 = Float2(maxX(), maxY())): FloatRect = FloatRect(min, max)
