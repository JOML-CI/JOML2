// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float2x4RawOpsApi extends Float2x4RawOps {
    public Float2x4 storeCMUnsafe(Float2x4 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Float2x4 loadCMUnsafe(long address) {
        return Float2x4.loadCM(0L, virtualMemory().asSlice(address, 32L));
    }
    public Float2x4 storeCMDoubleUnsafe(Float2x4 self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float2x4 loadCMDoubleUnsafe(long address) {
        return Float2x4.loadCMDouble(0L, virtualMemory().asSlice(address, 64L));
    }
    public Float2x4 storeRMUnsafe(Float2x4 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Float2x4 loadRMUnsafe(long address) {
        return Float2x4.loadRM(0L, virtualMemory().asSlice(address, 32L));
    }
    public Float2x4 storeRMDoubleUnsafe(Float2x4 self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float2x4 loadRMDoubleUnsafe(long address) {
        return Float2x4.loadRMDouble(0L, virtualMemory().asSlice(address, 64L));
    }
    public Float2x4 storeCMUnsafe(Float2x4 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 2)), stride);
        return self;
    }
    public Float2x4 loadCMUnsafe(long address, int stride) {
        return Float2x4.loadCM(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 2)), stride);
    }
    public Float2x4 storeCMDoubleUnsafe(Float2x4 self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 2)), stride);
        return self;
    }
    public Float2x4 loadCMDoubleUnsafe(long address, int stride) {
        return Float2x4.loadCMDouble(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 2)), stride);
    }
    public Float2x4 storeRMUnsafe(Float2x4 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 4L * (stride + 4)), stride);
        return self;
    }
    public Float2x4 loadRMUnsafe(long address, int stride) {
        return Float2x4.loadRM(0L, virtualMemory().asSlice(address, 4L * (stride + 4)), stride);
    }
    public Float2x4 storeRMDoubleUnsafe(Float2x4 self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 4)), stride);
        return self;
    }
    public Float2x4 loadRMDoubleUnsafe(long address, int stride) {
        return Float2x4.loadRMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 4)), stride);
    }
}
