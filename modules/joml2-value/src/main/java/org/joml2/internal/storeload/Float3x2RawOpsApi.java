// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float3x2RawOpsApi extends Float3x2RawOps {
    public Float3x2 storeCMUnsafe(Float3x2 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Float3x2 loadCMUnsafe(long address) {
        return Float3x2.loadCM(0L, virtualMemory().asSlice(address, 24L));
    }
    public Float3x2 storeCMDoubleUnsafe(Float3x2 self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float3x2 loadCMDoubleUnsafe(long address) {
        return Float3x2.loadCMDouble(0L, virtualMemory().asSlice(address, 48L));
    }
    public Float3x2 storeRMUnsafe(Float3x2 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Float3x2 loadRMUnsafe(long address) {
        return Float3x2.loadRM(0L, virtualMemory().asSlice(address, 24L));
    }
    public Float3x2 storeRMDoubleUnsafe(Float3x2 self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float3x2 loadRMDoubleUnsafe(long address) {
        return Float3x2.loadRMDouble(0L, virtualMemory().asSlice(address, 48L));
    }
    public Float3x2 storeCMUnsafe(Float3x2 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
        return self;
    }
    public Float3x2 loadCMUnsafe(long address, int stride) {
        return Float3x2.loadCM(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
    }
    public Float3x2 storeCMDoubleUnsafe(Float3x2 self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
        return self;
    }
    public Float3x2 loadCMDoubleUnsafe(long address, int stride) {
        return Float3x2.loadCMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
    }
    public Float3x2 storeRMUnsafe(Float3x2 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
        return self;
    }
    public Float3x2 loadRMUnsafe(long address, int stride) {
        return Float3x2.loadRM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
    }
    public Float3x2 storeRMDoubleUnsafe(Float3x2 self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
        return self;
    }
    public Float3x2 loadRMDoubleUnsafe(long address, int stride) {
        return Float3x2.loadRMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
    }
}
