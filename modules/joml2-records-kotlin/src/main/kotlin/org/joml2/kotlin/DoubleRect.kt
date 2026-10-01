// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoubleRect.
package org.joml2.kotlin

import org.joml2.*

/** [DoubleRect.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun DoubleRect.contains(p: Double2): Boolean = containsPoint(p)
/** [DoubleRect.containsRect] as the `in` operator, so `o in shape` reads as containment. */
inline operator fun DoubleRect.contains(o: DoubleRect): Boolean = containsRect(o)
/** [DoubleRect.intersectsRect] as the `overlaps` infix function. */
inline infix fun DoubleRect.overlaps(o: DoubleRect): Boolean = intersectsRect(o)
/** Component 1 of this DoubleRect for destructuring declarations: the minimum corner of the rectangle (`minX()`, `minY()`). */
inline operator fun DoubleRect.component1(): Double2 = Double2(minX(), minY())
/** Component 2 of this DoubleRect for destructuring declarations: the maximum corner of the rectangle (`maxX()`, `maxY()`). */
inline operator fun DoubleRect.component2(): Double2 = Double2(maxX(), maxY())
/** A copy of this DoubleRect with the named parts replaced; every part left out keeps its current value. */
inline fun DoubleRect.copy(min: Double2 = Double2(minX(), minY()), max: Double2 = Double2(maxX(), maxY())): DoubleRect = DoubleRect(min, max)
