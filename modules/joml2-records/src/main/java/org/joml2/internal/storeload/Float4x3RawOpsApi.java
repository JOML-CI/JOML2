// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float4x3RawOpsApi extends Float4x3RawOps {
    public Float4x3 storeCMUnsafe(Float4x3 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float4x3 loadCMUnsafe(long address) {
        return Float4x3.loadCM(0L, virtualMemory().asSlice(address, 48L));
    }
    public Float4x3 storeCMDoubleUnsafe(Float4x3 self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Float4x3 loadCMDoubleUnsafe(long address) {
        return Float4x3.loadCMDouble(0L, virtualMemory().asSlice(address, 96L));
    }
    public Float4x3 storeRMUnsafe(Float4x3 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float4x3 loadRMUnsafe(long address) {
        return Float4x3.loadRM(0L, virtualMemory().asSlice(address, 48L));
    }
    public Float4x3 storeRMDoubleUnsafe(Float4x3 self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Float4x3 loadRMDoubleUnsafe(long address) {
        return Float4x3.loadRMDouble(0L, virtualMemory().asSlice(address, 96L));
    }
    public Float4x3 storeCMUnsafe(Float4x3 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 4)), stride);
        return self;
    }
    public Float4x3 loadCMUnsafe(long address, int stride) {
        return Float4x3.loadCM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 4)), stride);
    }
    public Float4x3 storeCMDoubleUnsafe(Float4x3 self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 4)), stride);
        return self;
    }
    public Float4x3 loadCMDoubleUnsafe(long address, int stride) {
        return Float4x3.loadCMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 4)), stride);
    }
    public Float4x3 storeRMUnsafe(Float4x3 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 3)), stride);
        return self;
    }
    public Float4x3 loadRMUnsafe(long address, int stride) {
        return Float4x3.loadRM(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 3)), stride);
    }
    public Float4x3 storeRMDoubleUnsafe(Float4x3 self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 3)), stride);
        return self;
    }
    public Float4x3 loadRMDoubleUnsafe(long address, int stride) {
        return Float4x3.loadRMDouble(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 3)), stride);
    }
}
