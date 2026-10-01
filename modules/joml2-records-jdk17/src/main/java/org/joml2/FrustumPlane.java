// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

/**
 * One of the six clip planes of a projection or combined view-projection matrix.
 *
 * <p>Passed to {@code frustumPlane} to name the plane to extract:</p>
 *
 * <pre>{@code
 * Float4 near = viewProj.frustumPlane(FrustumPlane.NZ, dest);
 * }</pre>
 *
 * <p>Each constant is named after the clip-space face it bounds - {@link #NX} is
 * the left plane, {@link #PZ} the far one - and the declaration order matches
 * the {@code PLANE_MASK_*} bits of {@code Frustum}.</p>
 */
public enum FrustumPlane {
    /**
     * The left plane, bounding the frustum on the &minus;X side of clip space.
     */
    NX,

    /**
     * The right plane, bounding the frustum on the +X side of clip space.
     */
    PX,

    /**
     * The bottom plane, bounding the frustum on the &minus;Y side of clip space.
     */
    NY,

    /**
     * The top plane, bounding the frustum on the +Y side of clip space.
     */
    PY,

    /**
     * The near plane, bounding the frustum at the near end of the depth range.
     */
    NZ,

    /**
     * The far plane, bounding the frustum at the far end of the depth range.
     */
    PZ
}
