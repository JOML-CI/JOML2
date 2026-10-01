// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoubleOBB.
package org.joml2.kotlin

import org.joml2.*

/** [DoubleOBB.transform] as the `*` operator. */
inline operator fun DoubleOBB.times(m: Double3x4): DoubleOBB = transform(m)
/** [DoubleOBB.transform] as the `*` operator. */
inline operator fun DoubleOBB.times(m: Double4x4): DoubleOBB = transform(m)
/** [DoubleOBB.containsPoint] as the `in` operator, so `p in shape` reads as containment. */
inline operator fun DoubleOBB.contains(p: Double3): Boolean = containsPoint(p)
/** [DoubleOBB.intersectsOBB] as the `overlaps` infix function. */
inline infix fun DoubleOBB.overlaps(o: DoubleOBB): Boolean = intersectsOBB(o)
/** Component 1 of this DoubleOBB for destructuring declarations: the center of the box (`cX()`, `cY()`, `cZ()`). */
inline operator fun DoubleOBB.component1(): Double3 = Double3(cX(), cY(), cZ())
/** Component 2 of this DoubleOBB for destructuring declarations: the local x axis of the box, taken as given (expected to be unit length and orthogonal to the other two axes) (`uXx()`, `uXy()`, `uXz()`). */
inline operator fun DoubleOBB.component2(): Double3 = Double3(uXx(), uXy(), uXz())
/** Component 3 of this DoubleOBB for destructuring declarations: the local y axis of the box, taken as given (expected to be unit length and orthogonal to the other two axes) (`uYx()`, `uYy()`, `uYz()`). */
inline operator fun DoubleOBB.component3(): Double3 = Double3(uYx(), uYy(), uYz())
/** Component 4 of this DoubleOBB for destructuring declarations: the local z axis of the box, taken as given (expected to be unit length and orthogonal to the other two axes) (`uZx()`, `uZy()`, `uZz()`). */
inline operator fun DoubleOBB.component4(): Double3 = Double3(uZx(), uZy(), uZz())
/** Component 5 of this DoubleOBB for destructuring declarations: the half extent of the box along each local axis (`hsX()`, `hsY()`, `hsZ()`). */
inline operator fun DoubleOBB.component5(): Double3 = Double3(hsX(), hsY(), hsZ())
/** A copy of this DoubleOBB with the named parts replaced; every part left out keeps its current value. */
inline fun DoubleOBB.copy(center: Double3 = Double3(cX(), cY(), cZ()), axisX: Double3 = Double3(uXx(), uXy(), uXz()), axisY: Double3 = Double3(uYx(), uYy(), uYz()), axisZ: Double3 = Double3(uZx(), uZy(), uZz()), halfSize: Double3 = Double3(hsX(), hsY(), hsZ())): DoubleOBB = DoubleOBB(center, axisX, axisY, axisZ, halfSize)
