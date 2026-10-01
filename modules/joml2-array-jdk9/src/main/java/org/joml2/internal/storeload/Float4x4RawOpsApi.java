// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

/**
 * API-backend raw-address kernels of a target without {@code java.lang.foreign.MemorySegment}:
 * every method throws {@link UnsupportedOperationException} - without a segment view there is no
 * portable way to dereference a raw {@code long} address. Select {@code StoreLoadBackend.UNSAFE}
 * to use the raw-address forms on this variant.
 */
public final class Float4x4RawOpsApi implements Float4x4RawOps {
    public Float4x4 storeCMUnsafe(Float4x4Impl self, long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 loadCMUnsafe(Float4x4Impl self, long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 storeCMDoubleUnsafe(Float4x4Impl self, long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 loadCMDoubleUnsafe(Float4x4Impl self, long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 storeRMUnsafe(Float4x4Impl self, long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 loadRMUnsafe(Float4x4Impl self, long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 storeRMDoubleUnsafe(Float4x4Impl self, long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 loadRMDoubleUnsafe(Float4x4Impl self, long address) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 storeCMUnsafe(Float4x4Impl self, long address, int stride) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 loadCMUnsafe(Float4x4Impl self, long address, int stride) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 storeCMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 loadCMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 storeRMUnsafe(Float4x4Impl self, long address, int stride) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 loadRMUnsafe(Float4x4Impl self, long address, int stride) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 storeRMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
    public Float4x4 loadRMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
    }
}
