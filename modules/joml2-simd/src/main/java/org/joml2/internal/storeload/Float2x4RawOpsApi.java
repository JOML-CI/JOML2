// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float2x4RawOpsApi extends Float2x4RawOps {
    public Float2x4 storeCMUnsafe(Float2x4Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Float2x4 loadCMUnsafe(Float2x4Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Float2x4 storeCMDoubleUnsafe(Float2x4Impl self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float2x4 loadCMDoubleUnsafe(Float2x4Impl self, long address) {
        self.loadCMDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float2x4 storeRMUnsafe(Float2x4Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Float2x4 loadRMUnsafe(Float2x4Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Float2x4 storeRMDoubleUnsafe(Float2x4Impl self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float2x4 loadRMDoubleUnsafe(Float2x4Impl self, long address) {
        self.loadRMDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float2x4 storeCMUnsafe(Float2x4Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 2)), stride);
        return self;
    }
    public Float2x4 loadCMUnsafe(Float2x4Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 2)), stride);
        return self;
    }
    public Float2x4 storeCMDoubleUnsafe(Float2x4Impl self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 2)), stride);
        return self;
    }
    public Float2x4 loadCMDoubleUnsafe(Float2x4Impl self, long address, int stride) {
        self.loadCMDouble(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 2)), stride);
        return self;
    }
    public Float2x4 storeRMUnsafe(Float2x4Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 4L * (stride + 4)), stride);
        return self;
    }
    public Float2x4 loadRMUnsafe(Float2x4Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, 4L * (stride + 4)), stride);
        return self;
    }
    public Float2x4 storeRMDoubleUnsafe(Float2x4Impl self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 4)), stride);
        return self;
    }
    public Float2x4 loadRMDoubleUnsafe(Float2x4Impl self, long address, int stride) {
        self.loadRMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 4)), stride);
        return self;
    }
}
