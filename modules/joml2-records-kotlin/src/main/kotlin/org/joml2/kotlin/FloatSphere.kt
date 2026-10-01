// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatSphere.
package org.joml2.kotlin

import org.joml2.*

/** [FloatSphere.transform] as the `*` operator. */
inline operator fun FloatSphere.times(m: Float3x4): FloatSphere = transform(m)
/** [FloatSphere.transform] as the `*` operator. */
inline operator fun FloatSphere.times(m: Float4x4): FloatSphere = transform(m)
/** [FloatSphere.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun FloatSphere.contains(p: Float3): Boolean = containsPoint(p)
/** [FloatSphere.intersectsSphere] as the `overlaps` infix function. */
inline infix fun FloatSphere.overlaps(o: FloatSphere): Boolean = intersectsSphere(o)
/** [FloatSphere.intersectsAABB] as the `overlaps` infix function. */
inline infix fun FloatSphere.overlaps(a: FloatAABB): Boolean = intersectsAABB(a)
/** [FloatSphere.intersectsPlane] as the `overlaps` infix function. */
inline infix fun FloatSphere.overlaps(plane: FloatPlane): Boolean = intersectsPlane(plane)
/** [FloatSphere.intersectsRay] as the `overlaps` infix function. */
inline infix fun FloatSphere.overlaps(ray: FloatRay): Boolean = intersectsRay(ray)
/** Component 1 of this FloatSphere for destructuring declarations: the center of the sphere (`x()`, `y()`, `z()`). */
inline operator fun FloatSphere.component1(): Float3 = Float3(x(), y(), z())
/** Component 2 of this FloatSphere for destructuring declarations: the radius of the sphere (`r()`). */
inline operator fun FloatSphere.component2(): Float = r()
/** A copy of this FloatSphere with the named parts replaced; every part left out keeps its current value. */
inline fun FloatSphere.copy(center: Float3 = Float3(x(), y(), z()), radius: Float = r()): FloatSphere = FloatSphere(center, radius)
