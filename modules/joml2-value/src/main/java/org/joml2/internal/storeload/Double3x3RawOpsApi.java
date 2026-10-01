// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double3x3RawOpsApi extends Double3x3RawOps {
    public Double3x3 storeCMUnsafe(Double3x3 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double3x3 loadCMUnsafe(long address) {
        return Double3x3.loadCM(0L, virtualMemory().asSlice(address, 72L));
    }
    public Double3x3 storeCMFloatUnsafe(Double3x3 self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double3x3 loadCMFloatUnsafe(long address) {
        return Double3x3.loadCMFloat(0L, virtualMemory().asSlice(address, 36L));
    }
    public Double3x3 storeRMUnsafe(Double3x3 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double3x3 loadRMUnsafe(long address) {
        return Double3x3.loadRM(0L, virtualMemory().asSlice(address, 72L));
    }
    public Double3x3 storeRMFloatUnsafe(Double3x3 self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double3x3 loadRMFloatUnsafe(long address) {
        return Double3x3.loadRMFloat(0L, virtualMemory().asSlice(address, 36L));
    }
    public Double3x3 storeCMUnsafe(Double3x3 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 3)), stride);
        return self;
    }
    public Double3x3 loadCMUnsafe(long address, int stride) {
        return Double3x3.loadCM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 3)), stride);
    }
    public Double3x3 storeCMFloatUnsafe(Double3x3 self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 3)), stride);
        return self;
    }
    public Double3x3 loadCMFloatUnsafe(long address, int stride) {
        return Double3x3.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 3)), stride);
    }
    public Double3x3 storeRMUnsafe(Double3x3 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 3)), stride);
        return self;
    }
    public Double3x3 loadRMUnsafe(long address, int stride) {
        return Double3x3.loadRM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 3)), stride);
    }
    public Double3x3 storeRMFloatUnsafe(Double3x3 self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 3)), stride);
        return self;
    }
    public Double3x3 loadRMFloatUnsafe(long address, int stride) {
        return Double3x3.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 3)), stride);
    }
    public Double3x3 storeCM4x4Unsafe(Double3x3 self, long address) {
        self.storeCM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double3x3 storeCM4x4FloatUnsafe(Double3x3 self, long address) {
        self.storeCM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double3x3 storeRM4x4Unsafe(Double3x3 self, long address) {
        self.storeRM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double3x3 storeRM4x4FloatUnsafe(Double3x3 self, long address) {
        self.storeRM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
}
