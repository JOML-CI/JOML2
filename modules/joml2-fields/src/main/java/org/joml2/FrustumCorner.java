// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

/**
 * One of the eight corners of a projection or combined view-projection matrix's
 * frustum.
 *
 * <p>Passed to {@code frustumCorner} to name the corner to reconstruct:</p>
 *
 * <pre>{@code
 * Float3 nearBottomLeft = viewProj.frustumCorner(FrustumCorner.NXNYNZ, dest);
 * }</pre>
 *
 * <p>Each constant names the corner by the sign its three coordinates take in
 * clip space, so {@link #NXNYNZ} is the bottom-left corner of the near plane and
 * {@link #PXPYPZ} the top-right corner of the far one.</p>
 */
public enum FrustumCorner {
    /**
     * The bottom-left corner of the near plane.
     */
    NXNYNZ,

    /**
     * The bottom-right corner of the near plane.
     */
    PXNYNZ,

    /**
     * The top-right corner of the near plane.
     */
    PXPYNZ,

    /**
     * The top-left corner of the near plane.
     */
    NXPYNZ,

    /**
     * The bottom-left corner of the far plane.
     */
    NXNYPZ,

    /**
     * The bottom-right corner of the far plane.
     */
    PXNYPZ,

    /**
     * The top-right corner of the far plane.
     */
    PXPYPZ,

    /**
     * The top-left corner of the far plane.
     */
    NXPYPZ
}
