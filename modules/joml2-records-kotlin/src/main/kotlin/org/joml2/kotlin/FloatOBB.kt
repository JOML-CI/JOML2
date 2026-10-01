// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatOBB.
package org.joml2.kotlin

import org.joml2.*

/** [FloatOBB.transform] as the `*` operator. */
inline operator fun FloatOBB.times(m: Float3x4): FloatOBB = transform(m)
/** [FloatOBB.transform] as the `*` operator. */
inline operator fun FloatOBB.times(m: Float4x4): FloatOBB = transform(m)
/** [FloatOBB.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun FloatOBB.contains(p: Float3): Boolean = containsPoint(p)
/** [FloatOBB.intersectsOBB] as the `overlaps` infix function. */
inline infix fun FloatOBB.overlaps(o: FloatOBB): Boolean = intersectsOBB(o)
/** Component 1 of this FloatOBB for destructuring declarations: the center of the box (`cX()`, `cY()`, `cZ()`). */
inline operator fun FloatOBB.component1(): Float3 = Float3(cX(), cY(), cZ())
/** Component 2 of this FloatOBB for destructuring declarations: the local x axis of the box, taken as given (expected to be unit length and orthogonal to the other two axes) (`uXx()`, `uXy()`, `uXz()`). */
inline operator fun FloatOBB.component2(): Float3 = Float3(uXx(), uXy(), uXz())
/** Component 3 of this FloatOBB for destructuring declarations: the local y axis of the box, taken as given (expected to be unit length and orthogonal to the other two axes) (`uYx()`, `uYy()`, `uYz()`). */
inline operator fun FloatOBB.component3(): Float3 = Float3(uYx(), uYy(), uYz())
/** Component 4 of this FloatOBB for destructuring declarations: the local z axis of the box, taken as given (expected to be unit length and orthogonal to the other two axes) (`uZx()`, `uZy()`, `uZz()`). */
inline operator fun FloatOBB.component4(): Float3 = Float3(uZx(), uZy(), uZz())
/** Component 5 of this FloatOBB for destructuring declarations: the half extent of the box along each local axis (`hsX()`, `hsY()`, `hsZ()`). */
inline operator fun FloatOBB.component5(): Float3 = Float3(hsX(), hsY(), hsZ())
/** A copy of this FloatOBB with the named parts replaced; every part left out keeps its current value. */
inline fun FloatOBB.copy(center: Float3 = Float3(cX(), cY(), cZ()), axisX: Float3 = Float3(uXx(), uXy(), uXz()), axisY: Float3 = Float3(uYx(), uYy(), uYz()), axisZ: Float3 = Float3(uZx(), uZy(), uZz()), halfSize: Float3 = Float3(hsX(), hsY(), hsZ())): FloatOBB = FloatOBB(center, axisX, axisY, axisZ, halfSize)
