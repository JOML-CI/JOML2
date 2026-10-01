// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double3x4RawOpsApi extends Double3x4RawOps {
    public Double3x4 storeCMUnsafe(Double3x4Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Double3x4 loadCMUnsafe(Double3x4Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Double3x4 storeCMFloatUnsafe(Double3x4Impl self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double3x4 loadCMFloatUnsafe(Double3x4Impl self, long address) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double3x4 storeRMUnsafe(Double3x4Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Double3x4 loadRMUnsafe(Double3x4Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Double3x4 storeRMFloatUnsafe(Double3x4Impl self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double3x4 loadRMFloatUnsafe(Double3x4Impl self, long address) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double3x4 storeCMUnsafe(Double3x4Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 3)), stride);
        return self;
    }
    public Double3x4 loadCMUnsafe(Double3x4Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 3)), stride);
        return self;
    }
    public Double3x4 storeCMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 3)), stride);
        return self;
    }
    public Double3x4 loadCMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 3)), stride);
        return self;
    }
    public Double3x4 storeRMUnsafe(Double3x4Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 4)), stride);
        return self;
    }
    public Double3x4 loadRMUnsafe(Double3x4Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 4)), stride);
        return self;
    }
    public Double3x4 storeRMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 4)), stride);
        return self;
    }
    public Double3x4 loadRMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 4)), stride);
        return self;
    }
    public Double3x4 storeCM4x4Unsafe(Double3x4Impl self, long address) {
        self.storeCM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double3x4 storeCM4x4FloatUnsafe(Double3x4Impl self, long address) {
        self.storeCM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double3x4 storeRM4x4Unsafe(Double3x4Impl self, long address) {
        self.storeRM4x4(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Double3x4 storeRM4x4FloatUnsafe(Double3x4Impl self, long address) {
        self.storeRM4x4Float(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
}
