// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE")
@file:JvmSynthetic

// Angle value type + degrees/radians constructors for the JOML 2 Kotlin extensions.
package org.joml2.kotlin

/**
 * A typed angle, stored as radians (double precision). Construct via the
 * [degrees] / [radians] extensions, e.g. `30f.degrees`, `90.degrees`, `1.2.radians`,
 * and pass to the generated rotate / rotation overloads: `matrix.rotateX(30f.degrees)`.
 */
@JvmInline
value class Angle(val radians: Double)

/** This value interpreted as degrees, as an [Angle] (i.e. converted to radians). */
inline val Float.degrees: Angle  get() = Angle(org.joml2.Math.toRadians(this.toDouble()))
inline val Double.degrees: Angle get() = Angle(org.joml2.Math.toRadians(this))
inline val Int.degrees: Angle    get() = Angle(org.joml2.Math.toRadians(this.toDouble()))

/** This value interpreted as radians, as an [Angle] (identity - documents intent). */
inline val Float.radians: Angle  get() = Angle(this.toDouble())
inline val Double.radians: Angle get() = Angle(this)
inline val Int.radians: Angle    get() = Angle(this.toDouble())
