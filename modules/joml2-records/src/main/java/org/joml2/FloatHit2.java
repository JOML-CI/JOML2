// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

/**
 * The result of an intersection kernel that yields both a classification and a
 * {@link Float2}: {@link #code()} says which case the result falls in
 * (<code>0</code> = no intersection) and the components carry the result itself.
 * <p>
 * A boolean-valued kernel only ever reports <code>0</code> or <code>1</code> - prefer
 * {@link #hit()} there.
 * The classifying kernels use two families that share the values 1-3, so read {@link #code()}
 * against the kernel you called: the region kernels report a <code>POINT_ON_TRIANGLE_*</code>
 * constant, the line-segment kernels a <code>ONE_INTERSECTION</code>/<code>TWO_INTERSECTION</code>/<code>INSIDE</code>
 * constant.
 *
 * @param code the case this result falls in, or <code>0</code> for no intersection
 * @param x the {@code x} component of the result
 * @param y the {@code y} component of the result
 */
public record FloatHit2(int code, float x, float y) {

    /** No intersection: {@link #code()} <code>0</code> and all components <code>0</code>. */
    public static final FloatHit2 MISS = new FloatHit2(0, 0, 0);

    /**
     * Whether the kernel found an intersection, i.e. whether {@link #code()} is non-zero.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return <code>true</code> iff there is an intersection
     */
    public boolean hit() {
        return code != 0;
    }

    /** {@return whether any result component is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(x) || Float.isNaN(y);
    }

    /**
     * The result components as a vector.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return a {@link Float2} holding the result components
     */
    public Float2 value() {
        return Joml.float2(x, y);
    }
}
