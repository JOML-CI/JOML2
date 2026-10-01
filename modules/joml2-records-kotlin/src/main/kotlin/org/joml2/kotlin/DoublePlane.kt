// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoublePlane.
package org.joml2.kotlin

import org.joml2.*

/** [DoublePlane.intersectsSphere] as the `overlaps` infix function. */
inline infix fun DoublePlane.overlaps(sph: DoubleSphere): Boolean = intersectsSphere(sph)
/** [DoublePlane.intersectsAABB] as the `overlaps` infix function. */
inline infix fun DoublePlane.overlaps(box: DoubleAABB): Boolean = intersectsAABB(box)
/** Component 1 of this DoublePlane for destructuring declarations: the normal `(a, b, c)` of the plane, taken as given (not normalized) (`a()`, `b()`, `c()`). */
inline operator fun DoublePlane.component1(): Double3 = Double3(a(), b(), c())
/** Component 2 of this DoublePlane for destructuring declarations: the `d` coefficient of the plane equation `a*x + b*y + c*z + d = 0`; for a unit normal, the signed distance of the origin to the plane, positive on the side the normal points to (`d()`). */
inline operator fun DoublePlane.component2(): Double = d()
/** A copy of this DoublePlane with the named parts replaced; every part left out keeps its current value. */
inline fun DoublePlane.copy(normal: Double3 = Double3(a(), b(), c()), d: Double = d()): DoublePlane = DoublePlane(normal, d)
