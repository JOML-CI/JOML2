// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double2x2RawOpsApi extends Double2x2RawOps {
    public Double2x2 storeCMUnsafe(Double2x2 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double2x2 loadCMUnsafe(long address) {
        return Double2x2.loadCM(0L, virtualMemory().asSlice(address, 32L));
    }
    public Double2x2 storeCMFloatUnsafe(Double2x2 self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Double2x2 loadCMFloatUnsafe(long address) {
        return Double2x2.loadCMFloat(0L, virtualMemory().asSlice(address, 16L));
    }
    public Double2x2 storeRMUnsafe(Double2x2 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double2x2 loadRMUnsafe(long address) {
        return Double2x2.loadRM(0L, virtualMemory().asSlice(address, 32L));
    }
    public Double2x2 storeRMFloatUnsafe(Double2x2 self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Double2x2 loadRMFloatUnsafe(long address) {
        return Double2x2.loadRMFloat(0L, virtualMemory().asSlice(address, 16L));
    }
    public Double2x2 storeCMUnsafe(Double2x2 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 8L * (stride + 2)), stride);
        return self;
    }
    public Double2x2 loadCMUnsafe(long address, int stride) {
        return Double2x2.loadCM(0L, virtualMemory().asSlice(address, 8L * (stride + 2)), stride);
    }
    public Double2x2 storeCMFloatUnsafe(Double2x2 self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 2)), stride);
        return self;
    }
    public Double2x2 loadCMFloatUnsafe(long address, int stride) {
        return Double2x2.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 2)), stride);
    }
    public Double2x2 storeRMUnsafe(Double2x2 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 8L * (stride + 2)), stride);
        return self;
    }
    public Double2x2 loadRMUnsafe(long address, int stride) {
        return Double2x2.loadRM(0L, virtualMemory().asSlice(address, 8L * (stride + 2)), stride);
    }
    public Double2x2 storeRMFloatUnsafe(Double2x2 self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 2)), stride);
        return self;
    }
    public Double2x2 loadRMFloatUnsafe(long address, int stride) {
        return Double2x2.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 2)), stride);
    }
    public Double2x2 storeCM3x3Unsafe(Double2x2 self, long address) {
        self.storeCM3x3(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double2x2 storeCM3x3FloatUnsafe(Double2x2 self, long address) {
        self.storeCM3x3Float(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double2x2 storeRM3x3Unsafe(Double2x2 self, long address) {
        self.storeRM3x3(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double2x2 storeRM3x3FloatUnsafe(Double2x2 self, long address) {
        self.storeRM3x3Float(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double2x2 storeCM4x4Unsafe(Double2x2 self, long address) {
        self.storeCM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double2x2 storeCM4x4FloatUnsafe(Double2x2 self, long address) {
        self.storeCM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double2x2 storeRM4x4Unsafe(Double2x2 self, long address) {
        self.storeRM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double2x2 storeRM4x4FloatUnsafe(Double2x2 self, long address) {
        self.storeRM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
}
