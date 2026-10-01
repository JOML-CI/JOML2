// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.types.Double4x4Impl;

/**
 * Fixed-size {@link Double4x4} stack - port of JOML 1's
 * {@code Matrix*Stack} for emulating the legacy OpenGL push/pop matrix
 * stack ({@code glPushMatrix} / {@code glPopMatrix}).
 *
 * <p>The current/top matrix IS this object - all inherited
 * {@link Double4x4} operations apply to it directly. Calling
 * {@link #pushMatrix()} snapshots the current state into an internal
 * {@code Double4x4[]} array; {@link #popMatrix()} restores from it.</p>
 *
 * <p>This assumes the default in-place mode: with {@code joml.returnNew} enabled,
 * the inherited self-form operations return fresh instances instead of mutating
 * this stack top, which defeats the push/pop workflow.</p>
 *
 * <p>Only the top matrix takes part in {@code equals}, {@code hashCode} and
 * {@code set}: two stacks compare equal when their current matrices do, whatever
 * their depths and saved snapshots (a stack is also equal to a plain
 * {@link Double4x4} holding the same elements), {@code hashCode} hashes the top
 * alone, and {@code set(otherStack)} copies only the other stack's top matrix - its
 * saved snapshots and stack pointer are not copied.</p>
 */
public class Double4x4Stack extends Double4x4Impl {

    /** Internal stack of saved matrices. Length is {@code stackSize - 1};
     *  the inherited {@code Double4x4} occupies the implicit top slot. */
    private final Double4x4[] mats;

    /** Index of the next free slot in {@link #mats}. {@code 0} == bottom of stack. */
    private int curr;

    /**
     * Create a new {@link Double4x4Stack} of the given size.
     * The stack pointer starts at zero and the current matrix is the identity.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param stackSize total stack depth, including the implicit top slot ({@code this});
     *                  must be {@code >= 1}
     * @throws IllegalArgumentException if {@code stackSize < 1}
     */
    public Double4x4Stack(int stackSize) {
        if (stackSize < 1)
            throw new IllegalArgumentException("stackSize must be >= 1");
        mats = new Double4x4[stackSize - 1];
        // Eager allocation keeps runtime push/pop allocation-free.
        for (int i = 0; i < mats.length; i++)
            mats[i] = Joml.double4x4();
    }

    /**
     * Increment the stack pointer by one and copy the current matrix into the
     * next saved slot (below the working top), leaving {@code this} as a working
     * copy that can be modified without affecting the saved snapshot.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     * @throws IllegalStateException if the stack is full
     */
    public Double4x4Stack pushMatrix() {
        if (curr == mats.length)
            throw new IllegalStateException("max stack size of " + (curr + 1) + " reached");
        mats[curr++].set(this);
        return this;
    }

    /**
     * Decrement the stack pointer by one and restore the current matrix from the
     * slot directly below the top.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     * @throws IllegalStateException if the stack is already at the bottom
     */
    public Double4x4Stack popMatrix() {
        if (curr == 0)
            throw new IllegalStateException("already at the bottom of the stack");
        set(mats[--curr]);
        return this;
    }

    /**
     * Set the stack pointer to zero and reset the current matrix to identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    public Double4x4Stack clear() {
        curr = 0;
        makeIdentity();
        return this;
    }
}
