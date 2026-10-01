// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatRigid.
package org.joml2.kotlin

import org.joml2.*

/** [FloatRigid.mul] as the `*` operator. */
inline operator fun FloatRigid.times(other: FloatRigid): FloatRigid = mul(other)
/** [FloatRigid.transform] as the `*` operator. */
inline operator fun FloatRigid.times(v: Float3): Float3 = transform(v)
/** Component 1 of this FloatRigid for destructuring declarations: the translation (`tX()`, `tY()`, `tZ()`). */
inline operator fun FloatRigid.component1(): Float3 = Float3(tX(), tY(), tZ())
/** Component 2 of this FloatRigid for destructuring declarations: the rotation quaternion, taken as given (not normalized) (`rX()`, `rY()`, `rZ()`, `rW()`). */
inline operator fun FloatRigid.component2(): FloatQuat = FloatQuat(rX(), rY(), rZ(), rW())
/** A copy of this FloatRigid with the named parts replaced; every part left out keeps its current value. */
inline fun FloatRigid.copy(translation: Float3 = Float3(tX(), tY(), tZ()), rotation: FloatQuat = FloatQuat(rX(), rY(), rZ(), rW())): FloatRigid = FloatRigid(translation, rotation)
