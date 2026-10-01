// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double3x2RawOpsApi extends Double3x2RawOps {
    public Double3x2 storeCMUnsafe(Double3x2 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double3x2 loadCMUnsafe(long address) {
        return Double3x2.loadCM(0L, virtualMemory().asSlice(address, 48L));
    }
    public Double3x2 storeCMFloatUnsafe(Double3x2 self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double3x2 loadCMFloatUnsafe(long address) {
        return Double3x2.loadCMFloat(0L, virtualMemory().asSlice(address, 24L));
    }
    public Double3x2 storeRMUnsafe(Double3x2 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double3x2 loadRMUnsafe(long address) {
        return Double3x2.loadRM(0L, virtualMemory().asSlice(address, 48L));
    }
    public Double3x2 storeRMFloatUnsafe(Double3x2 self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double3x2 loadRMFloatUnsafe(long address) {
        return Double3x2.loadRMFloat(0L, virtualMemory().asSlice(address, 24L));
    }
    public Double3x2 storeCMUnsafe(Double3x2 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
        return self;
    }
    public Double3x2 loadCMUnsafe(long address, int stride) {
        return Double3x2.loadCM(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
    }
    public Double3x2 storeCMFloatUnsafe(Double3x2 self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
        return self;
    }
    public Double3x2 loadCMFloatUnsafe(long address, int stride) {
        return Double3x2.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
    }
    public Double3x2 storeRMUnsafe(Double3x2 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
        return self;
    }
    public Double3x2 loadRMUnsafe(long address, int stride) {
        return Double3x2.loadRM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
    }
    public Double3x2 storeRMFloatUnsafe(Double3x2 self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
        return self;
    }
    public Double3x2 loadRMFloatUnsafe(long address, int stride) {
        return Double3x2.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
    }
}
