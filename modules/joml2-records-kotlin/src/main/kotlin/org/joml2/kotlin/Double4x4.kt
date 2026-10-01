// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")
@file:JvmSynthetic

// Kotlin operator/infix/destructuring/indexing extensions for Double4x4.
package org.joml2.kotlin

import org.joml2.*

/** [Double4x4.add] as the `+` operator. */
inline operator fun Double4x4.plus(other: Double4x4): Double4x4 = add(other)
/** [Double4x4.mul] as the `*` operator. */
inline operator fun Double4x4.times(scalar: Double): Double4x4 = mul(scalar)
/** [Double4x4.mul] with the scalar on the left, so `s * v` reads like `v * s`. */
inline operator fun Double.times(v: Double4x4): Double4x4 = v.mul(this)
/** [Double4x4.negate] as the unary `-` operator. */
inline operator fun Double4x4.unaryMinus(): Double4x4 = negate()
/** [Double4x4.sub] as the `-` operator. */
inline operator fun Double4x4.minus(other: Double4x4): Double4x4 = sub(other)
/** [Double4x4.mul] as the `*` operator. */
inline operator fun Double4x4.times(right: Double4x4): Double4x4 = mul(right)
/** [Double4x4.mul] as the `*` operator. */
inline operator fun Double4x4.times(right: Double2x2): Double4x4 = mul(right)
/** [Double4x4.mul] as the `*` operator. */
inline operator fun Double4x4.times(right: Double2x3): Double4x4 = mul(right)
/** [Double4x4.mul] as the `*` operator. */
inline operator fun Double4x4.times(right: Double3x3): Double4x4 = mul(right)
/** [Double4x4.mul] as the `*` operator. */
inline operator fun Double4x4.times(right: Double3x4): Double4x4 = mul(right)
/** [Double4x4.arcball] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.arcball(radius: Double, center: Double3, angleX: Angle, angleY: Angle): Double4x4 = arcball(radius, center, angleX.radians, angleY.radians)
/** [Double4x4.obliqueCabinet] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.obliqueCabinet(angle: Angle): Double4x4 = obliqueCabinet(angle.radians)
/** [Double4x4.obliqueCavalier] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.obliqueCavalier(angle: Angle): Double4x4 = obliqueCavalier(angle.radians)
/** [Double4x4.obliqueMilitary] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.obliqueMilitary(angle: Angle): Double4x4 = obliqueMilitary(angle.radians)
/** [Double4x4.obliquePlanometric] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.obliquePlanometric(angle: Angle): Double4x4 = obliquePlanometric(angle.radians)
/** [Double4x4.perspective] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspective(fovy: Angle, aspect: Double, near: Double, far: Double, handedness: Handedness, depthRange: DepthRange): Double4x4 = perspective(fovy.radians, aspect, near, far, handedness, depthRange)
/** [Double4x4.perspective] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspective(fovy: Angle, aspect: Double, near: Double, far: Double, depthRange: DepthRange): Double4x4 = perspective(fovy.radians, aspect, near, far, Handedness.RIGHT_HANDED, depthRange)
/** [Double4x4.perspective] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspective(fovy: Angle, aspect: Double, near: Double, far: Double, handedness: Handedness): Double4x4 = perspective(fovy.radians, aspect, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE)
/** [Double4x4.perspective] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspective(fovy: Angle, aspect: Double, near: Double, far: Double): Double4x4 = perspective(fovy.radians, aspect, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE)
/** [Double4x4.perspectiveFovRange] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspectiveFovRange(angleMin: Angle, angleMax: Angle, aspect: Double, near: Double, far: Double, handedness: Handedness, depthRange: DepthRange): Double4x4 = perspectiveFovRange(angleMin.radians, angleMax.radians, aspect, near, far, handedness, depthRange)
/** [Double4x4.perspectiveFovRange] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspectiveFovRange(angleMin: Angle, angleMax: Angle, aspect: Double, near: Double, far: Double, depthRange: DepthRange): Double4x4 = perspectiveFovRange(angleMin.radians, angleMax.radians, aspect, near, far, Handedness.RIGHT_HANDED, depthRange)
/** [Double4x4.perspectiveFovRange] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspectiveFovRange(angleMin: Angle, angleMax: Angle, aspect: Double, near: Double, far: Double, handedness: Handedness): Double4x4 = perspectiveFovRange(angleMin.radians, angleMax.radians, aspect, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE)
/** [Double4x4.perspectiveFovRange] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspectiveFovRange(angleMin: Angle, angleMax: Angle, aspect: Double, near: Double, far: Double): Double4x4 = perspectiveFovRange(angleMin.radians, angleMax.radians, aspect, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE)
/** [Double4x4.perspectiveOffCenterFov] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspectiveOffCenterFov(angleLeft: Angle, angleRight: Angle, angleDown: Angle, angleUp: Angle, near: Double, far: Double, handedness: Handedness, depthRange: DepthRange): Double4x4 = perspectiveOffCenterFov(angleLeft.radians, angleRight.radians, angleDown.radians, angleUp.radians, near, far, handedness, depthRange)
/** [Double4x4.perspectiveOffCenterFov] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspectiveOffCenterFov(angleLeft: Angle, angleRight: Angle, angleDown: Angle, angleUp: Angle, near: Double, far: Double, depthRange: DepthRange): Double4x4 = perspectiveOffCenterFov(angleLeft.radians, angleRight.radians, angleDown.radians, angleUp.radians, near, far, Handedness.RIGHT_HANDED, depthRange)
/** [Double4x4.perspectiveOffCenterFov] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspectiveOffCenterFov(angleLeft: Angle, angleRight: Angle, angleDown: Angle, angleUp: Angle, near: Double, far: Double, handedness: Handedness): Double4x4 = perspectiveOffCenterFov(angleLeft.radians, angleRight.radians, angleDown.radians, angleUp.radians, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE)
/** [Double4x4.perspectiveOffCenterFov] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.perspectiveOffCenterFov(angleLeft: Angle, angleRight: Angle, angleDown: Angle, angleUp: Angle, near: Double, far: Double): Double4x4 = perspectiveOffCenterFov(angleLeft.radians, angleRight.radians, angleDown.radians, angleUp.radians, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE)
/** [Double4x4.preRotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.preRotateAxis(angle: Angle, axis: Double3): Double4x4 = preRotateAxis(angle.radians, axis)
/** [Double4x4.preRotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.preRotateX(angle: Angle): Double4x4 = preRotateX(angle.radians)
/** [Double4x4.preRotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.preRotateY(angle: Angle): Double4x4 = preRotateY(angle.radians)
/** [Double4x4.preRotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.preRotateZ(angle: Angle): Double4x4 = preRotateZ(angle.radians)
/** [Double4x4.rotateAxis] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.rotateAxis(angle: Angle, axis: Double3): Double4x4 = rotateAxis(angle.radians, axis)
/** [Double4x4.rotateX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.rotateX(angle: Angle): Double4x4 = rotateX(angle.radians)
/** [Double4x4.rotateXYZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.rotateXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): Double4x4 = rotateXYZ(angleX.radians, angleY.radians, angleZ.radians)
/** [Double4x4.rotateXZY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.rotateXZY(angleX: Angle, angleZ: Angle, angleY: Angle): Double4x4 = rotateXZY(angleX.radians, angleZ.radians, angleY.radians)
/** [Double4x4.rotateY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.rotateY(angle: Angle): Double4x4 = rotateY(angle.radians)
/** [Double4x4.rotateYXZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.rotateYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): Double4x4 = rotateYXZ(angleY.radians, angleX.radians, angleZ.radians)
/** [Double4x4.rotateYZX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.rotateYZX(angleY: Angle, angleZ: Angle, angleX: Angle): Double4x4 = rotateYZX(angleY.radians, angleZ.radians, angleX.radians)
/** [Double4x4.rotateZ] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.rotateZ(angle: Angle): Double4x4 = rotateZ(angle.radians)
/** [Double4x4.rotateZXY] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.rotateZXY(angleZ: Angle, angleX: Angle, angleY: Angle): Double4x4 = rotateZXY(angleZ.radians, angleX.radians, angleY.radians)
/** [Double4x4.rotateZYX] taking a type-safe [Angle] in place of the angle in radians. */
inline fun Double4x4.rotateZYX(angleZ: Angle, angleY: Angle, angleX: Angle): Double4x4 = rotateZYX(angleZ.radians, angleY.radians, angleX.radians)
/** [Double4x4.mul] as the `*` operator. */
inline operator fun Double4x4.times(v: Double4): Double4 = mul(v)
/** The element of this Double4x4 in the given row and column, throwing `IndexOutOfBoundsException` when either index is out of range. */
inline operator fun Double4x4.get(row: Int, column: Int): Double {
    if (row < 0 || row >= 4 || column < 0 || column >= 4)
        throw IndexOutOfBoundsException("Double4x4 indices: $row, $column")
    return when (row * 4 + column) {
        0 -> m00()
        1 -> m01()
        2 -> m02()
        3 -> m03()
        4 -> m10()
        5 -> m11()
        6 -> m12()
        7 -> m13()
        8 -> m20()
        9 -> m21()
        10 -> m22()
        11 -> m23()
        12 -> m30()
        13 -> m31()
        14 -> m32()
        15 -> m33()
        else -> throw IndexOutOfBoundsException("Double4x4 indices: $row, $column")
    }
}

// Static make*(… : Angle …) factories. Object-scoped because Kotlin can't
// extend a Java class's static scope, and bare top-level funs would collide by
// return type across the shapes/precisions sharing a name.
object Double4x4Factory {
    /** [Double4x4.makeArcball] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeArcball(radius: Double, center: Double3, angleX: Angle, angleY: Angle): Double4x4 = Double4x4.makeArcball(radius, center, angleX.radians, angleY.radians)
    /** [Double4x4.makeObliqueCabinet] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeObliqueCabinet(angle: Angle): Double4x4 = Double4x4.makeObliqueCabinet(angle.radians)
    /** [Double4x4.makeObliqueCavalier] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeObliqueCavalier(angle: Angle): Double4x4 = Double4x4.makeObliqueCavalier(angle.radians)
    /** [Double4x4.makeObliqueMilitary] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeObliqueMilitary(angle: Angle): Double4x4 = Double4x4.makeObliqueMilitary(angle.radians)
    /** [Double4x4.makeObliquePlanometric] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeObliquePlanometric(angle: Angle): Double4x4 = Double4x4.makeObliquePlanometric(angle.radians)
    /** [Double4x4.makePerspective] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspective(fovy: Angle, aspect: Double, near: Double, far: Double, handedness: Handedness, depthRange: DepthRange): Double4x4 = Double4x4.makePerspective(fovy.radians, aspect, near, far, handedness, depthRange)
    /** [Double4x4.makePerspective] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspective(fovy: Angle, aspect: Double, near: Double, far: Double, depthRange: DepthRange): Double4x4 = Double4x4.makePerspective(fovy.radians, aspect, near, far, Handedness.RIGHT_HANDED, depthRange)
    /** [Double4x4.makePerspective] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspective(fovy: Angle, aspect: Double, near: Double, far: Double, handedness: Handedness): Double4x4 = Double4x4.makePerspective(fovy.radians, aspect, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE)
    /** [Double4x4.makePerspective] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspective(fovy: Angle, aspect: Double, near: Double, far: Double): Double4x4 = Double4x4.makePerspective(fovy.radians, aspect, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE)
    /** [Double4x4.makePerspectiveFovRange] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspectiveFovRange(angleMin: Angle, angleMax: Angle, aspect: Double, near: Double, far: Double, handedness: Handedness, depthRange: DepthRange): Double4x4 = Double4x4.makePerspectiveFovRange(angleMin.radians, angleMax.radians, aspect, near, far, handedness, depthRange)
    /** [Double4x4.makePerspectiveFovRange] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspectiveFovRange(angleMin: Angle, angleMax: Angle, aspect: Double, near: Double, far: Double, depthRange: DepthRange): Double4x4 = Double4x4.makePerspectiveFovRange(angleMin.radians, angleMax.radians, aspect, near, far, Handedness.RIGHT_HANDED, depthRange)
    /** [Double4x4.makePerspectiveFovRange] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspectiveFovRange(angleMin: Angle, angleMax: Angle, aspect: Double, near: Double, far: Double, handedness: Handedness): Double4x4 = Double4x4.makePerspectiveFovRange(angleMin.radians, angleMax.radians, aspect, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE)
    /** [Double4x4.makePerspectiveFovRange] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspectiveFovRange(angleMin: Angle, angleMax: Angle, aspect: Double, near: Double, far: Double): Double4x4 = Double4x4.makePerspectiveFovRange(angleMin.radians, angleMax.radians, aspect, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE)
    /** [Double4x4.makePerspectiveOffCenterFov] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspectiveOffCenterFov(angleLeft: Angle, angleRight: Angle, angleDown: Angle, angleUp: Angle, near: Double, far: Double, handedness: Handedness, depthRange: DepthRange): Double4x4 = Double4x4.makePerspectiveOffCenterFov(angleLeft.radians, angleRight.radians, angleDown.radians, angleUp.radians, near, far, handedness, depthRange)
    /** [Double4x4.makePerspectiveOffCenterFov] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspectiveOffCenterFov(angleLeft: Angle, angleRight: Angle, angleDown: Angle, angleUp: Angle, near: Double, far: Double, depthRange: DepthRange): Double4x4 = Double4x4.makePerspectiveOffCenterFov(angleLeft.radians, angleRight.radians, angleDown.radians, angleUp.radians, near, far, Handedness.RIGHT_HANDED, depthRange)
    /** [Double4x4.makePerspectiveOffCenterFov] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspectiveOffCenterFov(angleLeft: Angle, angleRight: Angle, angleDown: Angle, angleUp: Angle, near: Double, far: Double, handedness: Handedness): Double4x4 = Double4x4.makePerspectiveOffCenterFov(angleLeft.radians, angleRight.radians, angleDown.radians, angleUp.radians, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE)
    /** [Double4x4.makePerspectiveOffCenterFov] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makePerspectiveOffCenterFov(angleLeft: Angle, angleRight: Angle, angleDown: Angle, angleUp: Angle, near: Double, far: Double): Double4x4 = Double4x4.makePerspectiveOffCenterFov(angleLeft.radians, angleRight.radians, angleDown.radians, angleUp.radians, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE)
    /** [Double4x4.makeRotationAxis] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationAxis(angle: Angle, axis: Double3): Double4x4 = Double4x4.makeRotationAxis(angle.radians, axis)
    /** [Double4x4.makeRotationX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationX(angle: Angle): Double4x4 = Double4x4.makeRotationX(angle.radians)
    /** [Double4x4.makeRotationXYZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXYZ(angleX: Angle, angleY: Angle, angleZ: Angle): Double4x4 = Double4x4.makeRotationXYZ(angleX.radians, angleY.radians, angleZ.radians)
    /** [Double4x4.makeRotationXZY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationXZY(angleX: Angle, angleZ: Angle, angleY: Angle): Double4x4 = Double4x4.makeRotationXZY(angleX.radians, angleZ.radians, angleY.radians)
    /** [Double4x4.makeRotationY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationY(angle: Angle): Double4x4 = Double4x4.makeRotationY(angle.radians)
    /** [Double4x4.makeRotationYXZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYXZ(angleY: Angle, angleX: Angle, angleZ: Angle): Double4x4 = Double4x4.makeRotationYXZ(angleY.radians, angleX.radians, angleZ.radians)
    /** [Double4x4.makeRotationYZX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationYZX(angleY: Angle, angleZ: Angle, angleX: Angle): Double4x4 = Double4x4.makeRotationYZX(angleY.radians, angleZ.radians, angleX.radians)
    /** [Double4x4.makeRotationZ] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZ(angle: Angle): Double4x4 = Double4x4.makeRotationZ(angle.radians)
    /** [Double4x4.makeRotationZXY] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZXY(angleZ: Angle, angleX: Angle, angleY: Angle): Double4x4 = Double4x4.makeRotationZXY(angleZ.radians, angleX.radians, angleY.radians)
    /** [Double4x4.makeRotationZYX] taking a type-safe [Angle] in place of the angle in radians. */
    inline fun makeRotationZYX(angleZ: Angle, angleY: Angle, angleX: Angle): Double4x4 = Double4x4.makeRotationZYX(angleZ.radians, angleY.radians, angleX.radians)
}
