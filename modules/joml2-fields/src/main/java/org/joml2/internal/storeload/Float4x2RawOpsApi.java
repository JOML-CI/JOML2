// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float4x2RawOpsApi implements Float4x2RawOps {
    public Float4x2 storeCMUnsafe(Float4x2Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Float4x2 loadCMUnsafe(Float4x2Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Float4x2 storeCMDoubleUnsafe(Float4x2Impl self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float4x2 loadCMDoubleUnsafe(Float4x2Impl self, long address) {
        self.loadCMDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float4x2 storeRMUnsafe(Float4x2Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Float4x2 loadRMUnsafe(Float4x2Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Float4x2 storeRMDoubleUnsafe(Float4x2Impl self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float4x2 loadRMDoubleUnsafe(Float4x2Impl self, long address) {
        self.loadRMDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float4x2 storeCMUnsafe(Float4x2Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, (long) 4 * ((1) * stride + 4)), stride);
        return self;
    }
    public Float4x2 loadCMUnsafe(Float4x2Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, (long) 4 * ((1) * stride + 4)), stride);
        return self;
    }
    public Float4x2 storeCMDoubleUnsafe(Float4x2Impl self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((1) * stride + 4)), stride);
        return self;
    }
    public Float4x2 loadCMDoubleUnsafe(Float4x2Impl self, long address, int stride) {
        self.loadCMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((1) * stride + 4)), stride);
        return self;
    }
    public Float4x2 storeRMUnsafe(Float4x2Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, (long) 4 * ((3) * stride + 2)), stride);
        return self;
    }
    public Float4x2 loadRMUnsafe(Float4x2Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, (long) 4 * ((3) * stride + 2)), stride);
        return self;
    }
    public Float4x2 storeRMDoubleUnsafe(Float4x2Impl self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((3) * stride + 2)), stride);
        return self;
    }
    public Float4x2 loadRMDoubleUnsafe(Float4x2Impl self, long address, int stride) {
        self.loadRMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((3) * stride + 2)), stride);
        return self;
    }
}
