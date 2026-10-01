// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double2x2RawOpsApi implements Double2x2RawOps {
    public Double2x2 storeCMUnsafe(Double2x2Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double2x2 loadCMUnsafe(Double2x2Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double2x2 storeCMFloatUnsafe(Double2x2Impl self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Double2x2 loadCMFloatUnsafe(Double2x2Impl self, long address) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Double2x2 storeRMUnsafe(Double2x2Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double2x2 loadRMUnsafe(Double2x2Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double2x2 storeRMFloatUnsafe(Double2x2Impl self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Double2x2 loadRMFloatUnsafe(Double2x2Impl self, long address) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Double2x2 storeCMUnsafe(Double2x2Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, (long) 8 * ((1) * stride + 2)), stride);
        return self;
    }
    public Double2x2 loadCMUnsafe(Double2x2Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, (long) 8 * ((1) * stride + 2)), stride);
        return self;
    }
    public Double2x2 storeCMFloatUnsafe(Double2x2Impl self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((1) * stride + 2)), stride);
        return self;
    }
    public Double2x2 loadCMFloatUnsafe(Double2x2Impl self, long address, int stride) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((1) * stride + 2)), stride);
        return self;
    }
    public Double2x2 storeRMUnsafe(Double2x2Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, (long) 8 * ((1) * stride + 2)), stride);
        return self;
    }
    public Double2x2 loadRMUnsafe(Double2x2Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, (long) 8 * ((1) * stride + 2)), stride);
        return self;
    }
    public Double2x2 storeRMFloatUnsafe(Double2x2Impl self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((1) * stride + 2)), stride);
        return self;
    }
    public Double2x2 loadRMFloatUnsafe(Double2x2Impl self, long address, int stride) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((1) * stride + 2)), stride);
        return self;
    }
    public Double2x2 storeCM3x3Unsafe(Double2x2Impl self, long address) {
        self.storeCM3x3(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double2x2 storeCM3x3FloatUnsafe(Double2x2Impl self, long address) {
        self.storeCM3x3Float(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double2x2 storeRM3x3Unsafe(Double2x2Impl self, long address) {
        self.storeRM3x3(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double2x2 storeRM3x3FloatUnsafe(Double2x2Impl self, long address) {
        self.storeRM3x3Float(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double2x2 storeCM4x4Unsafe(Double2x2Impl self, long address) {
        self.storeCM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double2x2 storeCM4x4FloatUnsafe(Double2x2Impl self, long address) {
        self.storeCM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double2x2 storeRM4x4Unsafe(Double2x2Impl self, long address) {
        self.storeRM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double2x2 storeRM4x4FloatUnsafe(Double2x2Impl self, long address) {
        self.storeRM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
}
