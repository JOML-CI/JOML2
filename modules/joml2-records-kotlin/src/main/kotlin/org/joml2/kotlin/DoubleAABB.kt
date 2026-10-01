// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoubleAABB.
package org.joml2.kotlin

import org.joml2.*

/** [DoubleAABB.transform] as the `*` operator. */
inline operator fun DoubleAABB.times(m: Double3x4): DoubleAABB = transform(m)
/** [DoubleAABB.transform] as the `*` operator. */
inline operator fun DoubleAABB.times(m: Double4x4): DoubleAABB = transform(m)
/** [DoubleAABB.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun DoubleAABB.contains(p: Double3): Boolean = containsPoint(p)
/** [DoubleAABB.containsAABB] as the `in` operator, so `o in shape` reads as containment. */
inline operator fun DoubleAABB.contains(o: DoubleAABB): Boolean = containsAABB(o)
/** [DoubleAABB.intersectsAABB] as the `overlaps` infix function. */
inline infix fun DoubleAABB.overlaps(o: DoubleAABB): Boolean = intersectsAABB(o)
/** [DoubleAABB.intersectsSphere] as the `overlaps` infix function. */
inline infix fun DoubleAABB.overlaps(sph: DoubleSphere): Boolean = intersectsSphere(sph)
/** [DoubleAABB.intersectsPlane] as the `overlaps` infix function. */
inline infix fun DoubleAABB.overlaps(plane: DoublePlane): Boolean = intersectsPlane(plane)
/** [DoubleAABB.intersectsRay] as the `overlaps` infix function. */
inline infix fun DoubleAABB.overlaps(ray: DoubleRay): Boolean = intersectsRay(ray)
/** Component 1 of this DoubleAABB for destructuring declarations: the minimum corner of the box (`minX()`, `minY()`, `minZ()`). */
inline operator fun DoubleAABB.component1(): Double3 = Double3(minX(), minY(), minZ())
/** Component 2 of this DoubleAABB for destructuring declarations: the maximum corner of the box (`maxX()`, `maxY()`, `maxZ()`). */
inline operator fun DoubleAABB.component2(): Double3 = Double3(maxX(), maxY(), maxZ())
/** A copy of this DoubleAABB with the named parts replaced; every part left out keeps its current value. */
inline fun DoubleAABB.copy(min: Double3 = Double3(minX(), minY(), minZ()), max: Double3 = Double3(maxX(), maxY(), maxZ())): DoubleAABB = DoubleAABB(min, max)
