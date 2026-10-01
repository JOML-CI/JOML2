// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

/**
 * The result of an intersection kernel that yields both a classification and a
 * {@link Double4}: {@link #code()} says which case the result falls in
 * (<code>0</code> = no intersection) and the components carry the result itself.
 * <p>
 * A boolean-valued kernel only ever reports <code>0</code> or <code>1</code> - prefer
 * {@link #hit()} there.
 *
 * @param code the case this result falls in, or <code>0</code> for no intersection
 * @param x the {@code x} component of the result
 * @param y the {@code y} component of the result
 * @param z the {@code z} component of the result
 * @param w the {@code w} component of the result
 */
public record DoubleHit4(int code, double x, double y, double z, double w) {

    /** No intersection: {@link #code()} <code>0</code> and all components <code>0</code>. */
    public static final DoubleHit4 MISS = new DoubleHit4(0, 0, 0, 0, 0);

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
        return Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z) || Double.isNaN(w);
    }

    /**
     * The result components as a vector.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return a {@link Double4} holding the result components
     */
    public Double4 value() {
        return Joml.double4(x, y, z, w);
    }
}
