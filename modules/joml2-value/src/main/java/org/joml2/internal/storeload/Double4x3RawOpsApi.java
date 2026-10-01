// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double4x3RawOpsApi extends Double4x3RawOps {
    public Double4x3 storeCMUnsafe(Double4x3 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Double4x3 loadCMUnsafe(long address) {
        return Double4x3.loadCM(0L, virtualMemory().asSlice(address, 96L));
    }
    public Double4x3 storeCMFloatUnsafe(Double4x3 self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double4x3 loadCMFloatUnsafe(long address) {
        return Double4x3.loadCMFloat(0L, virtualMemory().asSlice(address, 48L));
    }
    public Double4x3 storeRMUnsafe(Double4x3 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Double4x3 loadRMUnsafe(long address) {
        return Double4x3.loadRM(0L, virtualMemory().asSlice(address, 96L));
    }
    public Double4x3 storeRMFloatUnsafe(Double4x3 self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double4x3 loadRMFloatUnsafe(long address) {
        return Double4x3.loadRMFloat(0L, virtualMemory().asSlice(address, 48L));
    }
    public Double4x3 storeCMUnsafe(Double4x3 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 4)), stride);
        return self;
    }
    public Double4x3 loadCMUnsafe(long address, int stride) {
        return Double4x3.loadCM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 4)), stride);
    }
    public Double4x3 storeCMFloatUnsafe(Double4x3 self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 4)), stride);
        return self;
    }
    public Double4x3 loadCMFloatUnsafe(long address, int stride) {
        return Double4x3.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 4)), stride);
    }
    public Double4x3 storeRMUnsafe(Double4x3 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 3)), stride);
        return self;
    }
    public Double4x3 loadRMUnsafe(long address, int stride) {
        return Double4x3.loadRM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 3)), stride);
    }
    public Double4x3 storeRMFloatUnsafe(Double4x3 self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 3)), stride);
        return self;
    }
    public Double4x3 loadRMFloatUnsafe(long address, int stride) {
        return Double4x3.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 3)), stride);
    }
}
