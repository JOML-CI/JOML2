// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
/**
 * Bring-your-own-storage operations: static, allocation-free counterparts of the
 * {@code org.joml2} math types that operate directly on user-supplied raw storage.
 *
 * <p>Each math type has one {@code Ops} class ({@code Float3Ops} for {@code Float3},
 * {@code Float4x4Ops} for {@code Float4x4}, ...) whose methods take the storage
 * plus an offset instead of a math object. Elements follow the canonical storage
 * order of the corresponding math type.
 *
 * <p>The package ships apart from the math types, in the variant's {@code -byos} artifact
 * ({@code joml2-records-byos} for {@code joml2-records}, and so on): module
 * {@code org.joml2.byos}, which requires the variant's module {@code org.joml2} - the
 * {@code Handedness}, {@code DepthRange}, {@code FrustumPlane} and {@code FrustumCorner}
 * parameters, {@link org.joml2.Joml}'s configuration and the store/load backend are
 * that module's.
 *
 * <p>The meaning of an offset depends on the backing:
 * <ul>
 *   <li>primitive arrays ({@code float[]}, {@code double[]}, ...) and typed NIO buffers
 *   ({@code FloatBuffer}, {@code DoubleBuffer}, ...): an <b>element index</b> ({@code int}),
 *   absolute for buffers (the buffer's position is ignored);</li>
 *   <li>{@code ByteBuffer}: a <b>byte offset</b> ({@code int}), absolute;</li>
 *   <li>{@code java.lang.foreign.MemorySegment} (targets that ship the FFM API): a
 *   <b>byte offset</b> ({@code long}) from the segment's base;</li>
 *   <li>raw {@code long} native addresses: no offset - each address points at the first
 *   element. These overloads are never bounds-checked, whatever the backend.</li>
 * </ul>
 *
 * <p>All buffer parameters of a single call must use the same storage backing;
 * only the {@code copy} methods translate between two backings.
 *
 * <p>With the default {@code unsafe} store/load backend ({@code joml.storeLoadBackend}), direct
 * native-order NIO buffers and native memory segments are accessed through {@code Unsafe}
 * <b>without bounds or liveness checks</b>: an offset outside the storage reads or corrupts
 * arbitrary memory instead of throwing. Heap buffers, read-only buffers, non-native byte
 * orders and the {@code api} backend go through the checked NIO/FFM API.
 *
 * <p>The elements in storage and the scalar arguments follow the valid-input contract of the
 * {@link org.joml2 org.joml2} package, and an operation here accepts what its
 * counterpart on the math type accepts - its <i>Valid input:</i> paragraph applies to the
 * elements the offsets address.
 */
@NullMarked
package org.joml2.ops;

import org.jspecify.annotations.NullMarked;
