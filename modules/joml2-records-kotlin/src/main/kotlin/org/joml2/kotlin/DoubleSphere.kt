// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoubleSphere.
package org.joml2.kotlin

import org.joml2.*

/** [DoubleSphere.transform] as the `*` operator. */
inline operator fun DoubleSphere.times(m: Double3x4): DoubleSphere = transform(m)
/** [DoubleSphere.transform] as the `*` operator. */
inline operator fun DoubleSphere.times(m: Double4x4): DoubleSphere = transform(m)
/** [DoubleSphere.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun DoubleSphere.contains(p: Double3): Boolean = containsPoint(p)
/** [DoubleSphere.intersectsSphere] as the `overlaps` infix function. */
inline infix fun DoubleSphere.overlaps(o: DoubleSphere): Boolean = intersectsSphere(o)
/** [DoubleSphere.intersectsAABB] as the `overlaps` infix function. */
inline infix fun DoubleSphere.overlaps(a: DoubleAABB): Boolean = intersectsAABB(a)
/** [DoubleSphere.intersectsPlane] as the `overlaps` infix function. */
inline infix fun DoubleSphere.overlaps(plane: DoublePlane): Boolean = intersectsPlane(plane)
/** [DoubleSphere.intersectsRay] as the `overlaps` infix function. */
inline infix fun DoubleSphere.overlaps(ray: DoubleRay): Boolean = intersectsRay(ray)
/** Component 1 of this DoubleSphere for destructuring declarations: the center of the sphere (`x()`, `y()`, `z()`). */
inline operator fun DoubleSphere.component1(): Double3 = Double3(x(), y(), z())
/** Component 2 of this DoubleSphere for destructuring declarations: the radius of the sphere (`r()`). */
inline operator fun DoubleSphere.component2(): Double = r()
/** A copy of this DoubleSphere with the named parts replaced; every part left out keeps its current value. */
inline fun DoubleSphere.copy(center: Double3 = Double3(x(), y(), z()), radius: Double = r()): DoubleSphere = DoubleSphere(center, radius)
