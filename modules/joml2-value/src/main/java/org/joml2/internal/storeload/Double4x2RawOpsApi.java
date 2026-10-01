// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double4x2RawOpsApi implements Double4x2RawOps {
    public Double4x2 storeCMUnsafe(Double4x2 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double4x2 loadCMUnsafe(long address) {
        return Double4x2.loadCM(0L, virtualMemory().asSlice(address, 64L));
    }
    public Double4x2 storeCMFloatUnsafe(Double4x2 self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double4x2 loadCMFloatUnsafe(long address) {
        return Double4x2.loadCMFloat(0L, virtualMemory().asSlice(address, 32L));
    }
    public Double4x2 storeRMUnsafe(Double4x2 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double4x2 loadRMUnsafe(long address) {
        return Double4x2.loadRM(0L, virtualMemory().asSlice(address, 64L));
    }
    public Double4x2 storeRMFloatUnsafe(Double4x2 self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double4x2 loadRMFloatUnsafe(long address) {
        return Double4x2.loadRMFloat(0L, virtualMemory().asSlice(address, 32L));
    }
    public Double4x2 storeCMUnsafe(Double4x2 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, (long) 8 * ((1) * stride + 4)), stride);
        return self;
    }
    public Double4x2 loadCMUnsafe(long address, int stride) {
        return Double4x2.loadCM(0L, virtualMemory().asSlice(address, (long) 8 * ((1) * stride + 4)), stride);
    }
    public Double4x2 storeCMFloatUnsafe(Double4x2 self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((1) * stride + 4)), stride);
        return self;
    }
    public Double4x2 loadCMFloatUnsafe(long address, int stride) {
        return Double4x2.loadCMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((1) * stride + 4)), stride);
    }
    public Double4x2 storeRMUnsafe(Double4x2 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, (long) 8 * ((3) * stride + 2)), stride);
        return self;
    }
    public Double4x2 loadRMUnsafe(long address, int stride) {
        return Double4x2.loadRM(0L, virtualMemory().asSlice(address, (long) 8 * ((3) * stride + 2)), stride);
    }
    public Double4x2 storeRMFloatUnsafe(Double4x2 self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((3) * stride + 2)), stride);
        return self;
    }
    public Double4x2 loadRMFloatUnsafe(long address, int stride) {
        return Double4x2.loadRMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((3) * stride + 2)), stride);
    }
}
