// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatAABB.
package org.joml2.kotlin

import org.joml2.*

/** [FloatAABB.transform] as the `*` operator. */
inline operator fun FloatAABB.times(m: Float3x4): FloatAABB = transform(m)
/** [FloatAABB.transform] as the `*` operator. */
inline operator fun FloatAABB.times(m: Float4x4): FloatAABB = transform(m)
/** [FloatAABB.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun FloatAABB.contains(p: Float3): Boolean = containsPoint(p)
/** [FloatAABB.containsAABB] as the `in` operator, so `o in shape` reads as containment. */
inline operator fun FloatAABB.contains(o: FloatAABB): Boolean = containsAABB(o)
/** [FloatAABB.intersectsAABB] as the `overlaps` infix function. */
inline infix fun FloatAABB.overlaps(o: FloatAABB): Boolean = intersectsAABB(o)
/** [FloatAABB.intersectsSphere] as the `overlaps` infix function. */
inline infix fun FloatAABB.overlaps(sph: FloatSphere): Boolean = intersectsSphere(sph)
/** [FloatAABB.intersectsPlane] as the `overlaps` infix function. */
inline infix fun FloatAABB.overlaps(plane: FloatPlane): Boolean = intersectsPlane(plane)
/** [FloatAABB.intersectsRay] as the `overlaps` infix function. */
inline infix fun FloatAABB.overlaps(ray: FloatRay): Boolean = intersectsRay(ray)
/** Component 1 of this FloatAABB for destructuring declarations: the minimum corner of the box (`minX()`, `minY()`, `minZ()`). */
inline operator fun FloatAABB.component1(): Float3 = Float3(minX(), minY(), minZ())
/** Component 2 of this FloatAABB for destructuring declarations: the maximum corner of the box (`maxX()`, `maxY()`, `maxZ()`). */
inline operator fun FloatAABB.component2(): Float3 = Float3(maxX(), maxY(), maxZ())
/** A copy of this FloatAABB with the named parts replaced; every part left out keeps its current value. */
inline fun FloatAABB.copy(min: Float3 = Float3(minX(), minY(), minZ()), max: Float3 = Float3(maxX(), maxY(), maxZ())): FloatAABB = FloatAABB(min, max)
