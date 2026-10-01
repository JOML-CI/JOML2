// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for DoubleRigid.
package org.joml2.kotlin

import org.joml2.*

/** [DoubleRigid.mul] as the `*` operator. */
inline operator fun DoubleRigid.times(other: DoubleRigid): DoubleRigid = mul(other)
/** [DoubleRigid.transform] as the `*` operator. */
inline operator fun DoubleRigid.times(v: Double3): Double3 = transform(v)
/** Component 1 of this DoubleRigid for destructuring declarations: the translation (`tX()`, `tY()`, `tZ()`). */
inline operator fun DoubleRigid.component1(): Double3 = Double3(tX(), tY(), tZ())
/** Component 2 of this DoubleRigid for destructuring declarations: the rotation quaternion, taken as given (not normalized) (`rX()`, `rY()`, `rZ()`, `rW()`). */
inline operator fun DoubleRigid.component2(): DoubleQuat = DoubleQuat(rX(), rY(), rZ(), rW())
/** A copy of this DoubleRigid with the named parts replaced; every part left out keeps its current value. */
inline fun DoubleRigid.copy(translation: Double3 = Double3(tX(), tY(), tZ()), rotation: DoubleQuat = DoubleQuat(rX(), rY(), rZ(), rW())): DoubleRigid = DoubleRigid(translation, rotation)
