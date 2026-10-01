// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

/**
 * Handedness of the coordinate system a projection or view matrix maps into.
 *
 * <p>Passed to the projection / view builders - {@code perspective},
 * {@code frustum}, {@code ortho}, {@code ortho2D}, {@code lookAt} and their
 * clip-space variants - to select the sign conventions of the result:</p>
 *
 * <pre>{@code
 * m.perspective(fovy, 16f / 9f, 0.1f, 100f, Handedness.RIGHT_HANDED);
 * }</pre>
 *
 * <p>The generated methods hold one fully constant-folded, branch-free body per
 * constant and select between them with a single {@code switch}, so passing a
 * constant costs nothing beyond a predictable branch.</p>
 */
public enum Handedness {
    /**
     * Left-handed: +Z points away from the viewer, into the screen.
     */
    LEFT_HANDED,

    /**
     * Right-handed: +Z points toward the viewer, out of the screen. The OpenGL convention.
     */
    RIGHT_HANDED
}
