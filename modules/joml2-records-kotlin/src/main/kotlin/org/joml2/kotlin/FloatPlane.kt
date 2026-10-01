// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatPlane.
package org.joml2.kotlin

import org.joml2.*

/** [FloatPlane.intersectsSphere] as the `overlaps` infix function. */
inline infix fun FloatPlane.overlaps(sph: FloatSphere): Boolean = intersectsSphere(sph)
/** [FloatPlane.intersectsAABB] as the `overlaps` infix function. */
inline infix fun FloatPlane.overlaps(box: FloatAABB): Boolean = intersectsAABB(box)
/** Component 1 of this FloatPlane for destructuring declarations: the normal `(a, b, c)` of the plane, taken as given (not normalized) (`a()`, `b()`, `c()`). */
inline operator fun FloatPlane.component1(): Float3 = Float3(a(), b(), c())
/** Component 2 of this FloatPlane for destructuring declarations: the `d` coefficient of the plane equation `a*x + b*y + c*z + d = 0`; for a unit normal, the signed distance of the origin to the plane, positive on the side the normal points to (`d()`). */
inline operator fun FloatPlane.component2(): Float = d()
/** A copy of this FloatPlane with the named parts replaced; every part left out keeps its current value. */
inline fun FloatPlane.copy(normal: Float3 = Float3(a(), b(), c()), d: Float = d()): FloatPlane = FloatPlane(normal, d)
