// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double2x3RawOpsApi extends Double2x3RawOps {
    public Double2x3 storeCMUnsafe(Double2x3Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double2x3 loadCMUnsafe(Double2x3Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double2x3 storeCMFloatUnsafe(Double2x3Impl self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double2x3 loadCMFloatUnsafe(Double2x3Impl self, long address) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double2x3 storeRMUnsafe(Double2x3Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double2x3 loadRMUnsafe(Double2x3Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double2x3 storeRMFloatUnsafe(Double2x3Impl self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double2x3 loadRMFloatUnsafe(Double2x3Impl self, long address) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double2x3 storeCMUnsafe(Double2x3Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
        return self;
    }
    public Double2x3 loadCMUnsafe(Double2x3Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
        return self;
    }
    public Double2x3 storeCMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
        return self;
    }
    public Double2x3 loadCMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
        return self;
    }
    public Double2x3 storeRMUnsafe(Double2x3Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
        return self;
    }
    public Double2x3 loadRMUnsafe(Double2x3Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
        return self;
    }
    public Double2x3 storeRMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
        return self;
    }
    public Double2x3 loadRMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
        return self;
    }
    public Double2x3 storeCM3x3Unsafe(Double2x3Impl self, long address) {
        self.storeCM3x3(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double2x3 storeCM3x3FloatUnsafe(Double2x3Impl self, long address) {
        self.storeCM3x3Float(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double2x3 storeRM3x3Unsafe(Double2x3Impl self, long address) {
        self.storeRM3x3(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double2x3 storeRM3x3FloatUnsafe(Double2x3Impl self, long address) {
        self.storeRM3x3Float(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double2x3 storeCM4x4Unsafe(Double2x3Impl self, long address) {
        self.storeCM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double2x3 storeCM4x4FloatUnsafe(Double2x3Impl self, long address) {
        self.storeCM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double2x3 storeRM4x4Unsafe(Double2x3Impl self, long address) {
        self.storeRM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double2x3 storeRM4x4FloatUnsafe(Double2x3Impl self, long address) {
        self.storeRM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
}
