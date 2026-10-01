// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double3x3RawOpsApi implements Double3x3RawOps {
    public Double3x3 storeCMUnsafe(Double3x3Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double3x3 loadCMUnsafe(Double3x3Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double3x3 storeCMFloatUnsafe(Double3x3Impl self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double3x3 loadCMFloatUnsafe(Double3x3Impl self, long address) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double3x3 storeRMUnsafe(Double3x3Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double3x3 loadRMUnsafe(Double3x3Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Double3x3 storeRMFloatUnsafe(Double3x3Impl self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double3x3 loadRMFloatUnsafe(Double3x3Impl self, long address) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Double3x3 storeCMUnsafe(Double3x3Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, (long) 8 * ((2) * stride + 3)), stride);
        return self;
    }
    public Double3x3 loadCMUnsafe(Double3x3Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, (long) 8 * ((2) * stride + 3)), stride);
        return self;
    }
    public Double3x3 storeCMFloatUnsafe(Double3x3Impl self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((2) * stride + 3)), stride);
        return self;
    }
    public Double3x3 loadCMFloatUnsafe(Double3x3Impl self, long address, int stride) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((2) * stride + 3)), stride);
        return self;
    }
    public Double3x3 storeRMUnsafe(Double3x3Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, (long) 8 * ((2) * stride + 3)), stride);
        return self;
    }
    public Double3x3 loadRMUnsafe(Double3x3Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, (long) 8 * ((2) * stride + 3)), stride);
        return self;
    }
    public Double3x3 storeRMFloatUnsafe(Double3x3Impl self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((2) * stride + 3)), stride);
        return self;
    }
    public Double3x3 loadRMFloatUnsafe(Double3x3Impl self, long address, int stride) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((2) * stride + 3)), stride);
        return self;
    }
    public Double3x3 storeCM4x4Unsafe(Double3x3Impl self, long address) {
        self.storeCM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double3x3 storeCM4x4FloatUnsafe(Double3x3Impl self, long address) {
        self.storeCM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double3x3 storeRM4x4Unsafe(Double3x3Impl self, long address) {
        self.storeRM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double3x3 storeRM4x4FloatUnsafe(Double3x3Impl self, long address) {
        self.storeRM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
}
