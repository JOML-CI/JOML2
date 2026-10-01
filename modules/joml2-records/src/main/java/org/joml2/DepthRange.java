// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

/**
 * The clip-space depth range a projection maps the view frustum onto.
 *
 * <p>Passed to the projection builders - {@code perspective}, {@code frustum},
 * {@code ortho}, {@code orthoCrop} and friends - alongside {@link Handedness},
 * replacing the old {@code …ZO} method-name suffixes:</p>
 *
 * <pre>{@code
 * // Vulkan-style [0,1] depth, left-handed:
 * m.perspective(fovy, 16f / 9f, 0.1f, 100f, Handedness.LEFT_HANDED, DepthRange.ZERO_TO_ONE);
 * // OpenGL-style [-1,1] depth, right-handed (the defaults):
 * m.perspective(fovy, 16f / 9f, 0.1f, 100f);
 * }</pre>
 *
 * <p>The generated methods hold one fully constant-folded, branch-free body per
 * constant and select between them with a single {@code switch}, so passing a
 * constant costs nothing beyond a predictable branch.</p>
 */
public enum DepthRange {
    /**
     * Depth maps to [-1, 1]: near &rarr; -1, far &rarr; +1. The OpenGL default.
     */
    NEGATIVE_ONE_TO_ONE,

    /**
     * Depth maps to [0, 1]: near &rarr; 0, far &rarr; 1. The Vulkan / Direct3D convention.
     */
    ZERO_TO_ONE
}
