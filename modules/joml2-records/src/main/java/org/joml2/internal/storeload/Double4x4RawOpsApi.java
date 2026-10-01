// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double4x4RawOpsApi extends Double4x4RawOps {
    public Double4x4 storeCMUnsafe(Double4x4 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double4x4 loadCMUnsafe(long address) {
        return Double4x4.loadCM(0L, virtualMemory().asSlice(address, 128L));
    }
    public Double4x4 storeCMFloatUnsafe(Double4x4 self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double4x4 loadCMFloatUnsafe(long address) {
        return Double4x4.loadCMFloat(0L, virtualMemory().asSlice(address, 64L));
    }
    public Double4x4 storeRMUnsafe(Double4x4 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double4x4 loadRMUnsafe(long address) {
        return Double4x4.loadRM(0L, virtualMemory().asSlice(address, 128L));
    }
    public Double4x4 storeRMFloatUnsafe(Double4x4 self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double4x4 loadRMFloatUnsafe(long address) {
        return Double4x4.loadRMFloat(0L, virtualMemory().asSlice(address, 64L));
    }
    public Double4x4 storeCMUnsafe(Double4x4 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 4)), stride);
        return self;
    }
    public Double4x4 loadCMUnsafe(long address, int stride) {
        return Double4x4.loadCM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 4)), stride);
    }
    public Double4x4 storeCMFloatUnsafe(Double4x4 self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 4)), stride);
        return self;
    }
    public Double4x4 loadCMFloatUnsafe(long address, int stride) {
        return Double4x4.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 4)), stride);
    }
    public Double4x4 storeRMUnsafe(Double4x4 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 4)), stride);
        return self;
    }
    public Double4x4 loadRMUnsafe(long address, int stride) {
        return Double4x4.loadRM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 4)), stride);
    }
    public Double4x4 storeRMFloatUnsafe(Double4x4 self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 4)), stride);
        return self;
    }
    public Double4x4 loadRMFloatUnsafe(long address, int stride) {
        return Double4x4.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 4)), stride);
    }
}
