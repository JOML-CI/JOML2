// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoubleTransform.
package org.joml2.kotlin

import org.joml2.*

/** [DoubleTransform.mul] as the `*` operator. */
inline operator fun DoubleTransform.times(other: DoubleTransform): DoubleTransform = mul(other)
/** [DoubleTransform.transform] as the `*` operator. */
inline operator fun DoubleTransform.times(v: Double3): Double3 = transform(v)
/** Component 1 of this DoubleTransform for destructuring declarations: the translation (`tX()`, `tY()`, `tZ()`). */
inline operator fun DoubleTransform.component1(): Double3 = Double3(tX(), tY(), tZ())
/** Component 2 of this DoubleTransform for destructuring declarations: the rotation quaternion, taken as given (not normalized) (`rX()`, `rY()`, `rZ()`, `rW()`). */
inline operator fun DoubleTransform.component2(): DoubleQuat = DoubleQuat(rX(), rY(), rZ(), rW())
/** Component 3 of this DoubleTransform for destructuring declarations: the scale along each local axis (`sX()`, `sY()`, `sZ()`). */
inline operator fun DoubleTransform.component3(): Double3 = Double3(sX(), sY(), sZ())
/** A copy of this DoubleTransform with the named parts replaced; every part left out keeps its current value. */
inline fun DoubleTransform.copy(translation: Double3 = Double3(tX(), tY(), tZ()), rotation: DoubleQuat = DoubleQuat(rX(), rY(), rZ(), rW()), scale: Double3 = Double3(sX(), sY(), sZ())): DoubleTransform = DoubleTransform(translation, rotation, scale)
