// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for FloatTransform.
package org.joml2.kotlin

import org.joml2.*

/** [FloatTransform.mul] as the `*` operator. */
inline operator fun FloatTransform.times(other: FloatTransform): FloatTransform = mul(other)
/** [FloatTransform.transform] as the `*` operator. */
inline operator fun FloatTransform.times(v: Float3): Float3 = transform(v)
/** Component 1 of this FloatTransform for destructuring declarations: the translation (`tX()`, `tY()`, `tZ()`). */
inline operator fun FloatTransform.component1(): Float3 = Float3(tX(), tY(), tZ())
/** Component 2 of this FloatTransform for destructuring declarations: the rotation quaternion, taken as given (not normalized) (`rX()`, `rY()`, `rZ()`, `rW()`). */
inline operator fun FloatTransform.component2(): FloatQuat = FloatQuat(rX(), rY(), rZ(), rW())
/** Component 3 of this FloatTransform for destructuring declarations: the scale along each local axis (`sX()`, `sY()`, `sZ()`). */
inline operator fun FloatTransform.component3(): Float3 = Float3(sX(), sY(), sZ())
/** A copy of this FloatTransform with the named parts replaced; every part left out keeps its current value. */
inline fun FloatTransform.copy(translation: Float3 = Float3(tX(), tY(), tZ()), rotation: FloatQuat = FloatQuat(rX(), rY(), rZ(), rW()), scale: Float3 = Float3(sX(), sY(), sZ())): FloatTransform = FloatTransform(translation, rotation, scale)
