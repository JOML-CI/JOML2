// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

/**
 * The result of an intersection kernel that yields both a classification and a
 * {@link Float3}: {@link #code()} says which case the result falls in
 * (<code>0</code> = no intersection) and the components carry the result itself.
 * <p>
 * A boolean-valued kernel only ever reports <code>0</code> or <code>1</code> - prefer
 * {@link #hit()} there.
 * The region-classifying kernels report the matching
 * <code>POINT_ON_TRIANGLE_*</code> constant.
 *
 * @param code the case this result falls in, or <code>0</code> for no intersection
 * @param x the {@code x} component of the result
 * @param y the {@code y} component of the result
 * @param z the {@code z} component of the result
 */
public record FloatHit3(int code, float x, float y, float z) {

    /** No intersection: {@link #code()} <code>0</code> and all components <code>0</code>. */
    public static final FloatHit3 MISS = new FloatHit3(0, 0, 0, 0);

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
        return Float.isNaN(x) || Float.isNaN(y) || Float.isNaN(z);
    }

    /**
     * The result components as a vector.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return a {@link Float3} holding the result components
     */
    public Float3 value() {
        return Joml.float3(x, y, z);
    }
}
