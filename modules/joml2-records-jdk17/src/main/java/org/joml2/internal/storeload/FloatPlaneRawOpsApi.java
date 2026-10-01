// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

/**
 * API-backend raw-address kernels of a target without {@code java.lang.foreign.MemorySegment}:
 * every method throws {@link UnsupportedOperationException} - without a segment view there is no
 * portable way to dereference a raw {@code long} address. Select {@code StoreLoadBackend.UNSAFE}
 * to use the raw-address forms on this variant.
 */
public final class FloatPlaneRawOpsApi implements FloatPlaneRawOps {
    public FloatPlane storeUnsafe(FloatPlane self, long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public FloatPlane loadUnsafe(long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public FloatPlane storeDoubleUnsafe(FloatPlane self, long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public FloatPlane loadDoubleUnsafe(long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
}
