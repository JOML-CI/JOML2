// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for IntRect.
package org.joml2.kotlin

import org.joml2.*

/** [IntRect.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun IntRect.contains(p: Int2): Boolean = containsPoint(p)
/** [IntRect.containsRect] as the `in` operator, so `o in shape` reads as containment. */
inline operator fun IntRect.contains(o: IntRect): Boolean = containsRect(o)
/** [IntRect.intersectsRect] as the `overlaps` infix function. */
inline infix fun IntRect.overlaps(o: IntRect): Boolean = intersectsRect(o)
/** Component 1 of this IntRect for destructuring declarations: the minimum corner of the rectangle (`minX()`, `minY()`). */
inline operator fun IntRect.component1(): Int2 = Int2(minX(), minY())
/** Component 2 of this IntRect for destructuring declarations: the maximum corner of the rectangle (`maxX()`, `maxY()`). */
inline operator fun IntRect.component2(): Int2 = Int2(maxX(), maxY())
/** A copy of this IntRect with the named parts replaced; every part left out keeps its current value. */
inline fun IntRect.copy(min: Int2 = Int2(minX(), minY()), max: Int2 = Int2(maxX(), maxY())): IntRect = IntRect(min, max)
