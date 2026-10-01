// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float4x4RawOpsApi implements Float4x4RawOps {
    public Float4x4 storeCMUnsafe(Float4x4 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float4x4 loadCMUnsafe(long address) {
        return Float4x4.loadCM(0L, virtualMemory().asSlice(address, 64L));
    }
    public Float4x4 storeCMDoubleUnsafe(Float4x4 self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Float4x4 loadCMDoubleUnsafe(long address) {
        return Float4x4.loadCMDouble(0L, virtualMemory().asSlice(address, 128L));
    }
    public Float4x4 storeRMUnsafe(Float4x4 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float4x4 loadRMUnsafe(long address) {
        return Float4x4.loadRM(0L, virtualMemory().asSlice(address, 64L));
    }
    public Float4x4 storeRMDoubleUnsafe(Float4x4 self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Float4x4 loadRMDoubleUnsafe(long address) {
        return Float4x4.loadRMDouble(0L, virtualMemory().asSlice(address, 128L));
    }
    public Float4x4 storeCMUnsafe(Float4x4 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, (long) 4 * ((3) * stride + 4)), stride);
        return self;
    }
    public Float4x4 loadCMUnsafe(long address, int stride) {
        return Float4x4.loadCM(0L, virtualMemory().asSlice(address, (long) 4 * ((3) * stride + 4)), stride);
    }
    public Float4x4 storeCMDoubleUnsafe(Float4x4 self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((3) * stride + 4)), stride);
        return self;
    }
    public Float4x4 loadCMDoubleUnsafe(long address, int stride) {
        return Float4x4.loadCMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((3) * stride + 4)), stride);
    }
    public Float4x4 storeRMUnsafe(Float4x4 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, (long) 4 * ((3) * stride + 4)), stride);
        return self;
    }
    public Float4x4 loadRMUnsafe(long address, int stride) {
        return Float4x4.loadRM(0L, virtualMemory().asSlice(address, (long) 4 * ((3) * stride + 4)), stride);
    }
    public Float4x4 storeRMDoubleUnsafe(Float4x4 self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((3) * stride + 4)), stride);
        return self;
    }
    public Float4x4 loadRMDoubleUnsafe(long address, int stride) {
        return Float4x4.loadRMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((3) * stride + 4)), stride);
    }
}
