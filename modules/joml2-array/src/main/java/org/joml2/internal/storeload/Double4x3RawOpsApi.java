// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double4x3RawOpsApi implements Double4x3RawOps {
    public Double4x3 storeCMUnsafe(Double4x3Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Double4x3 loadCMUnsafe(Double4x3Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Double4x3 storeCMFloatUnsafe(Double4x3Impl self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double4x3 loadCMFloatUnsafe(Double4x3Impl self, long address) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double4x3 storeRMUnsafe(Double4x3Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Double4x3 loadRMUnsafe(Double4x3Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Double4x3 storeRMFloatUnsafe(Double4x3Impl self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double4x3 loadRMFloatUnsafe(Double4x3Impl self, long address) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double4x3 storeCMUnsafe(Double4x3Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, (long) 8 * ((2) * stride + 4)), stride);
        return self;
    }
    public Double4x3 loadCMUnsafe(Double4x3Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, (long) 8 * ((2) * stride + 4)), stride);
        return self;
    }
    public Double4x3 storeCMFloatUnsafe(Double4x3Impl self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((2) * stride + 4)), stride);
        return self;
    }
    public Double4x3 loadCMFloatUnsafe(Double4x3Impl self, long address, int stride) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((2) * stride + 4)), stride);
        return self;
    }
    public Double4x3 storeRMUnsafe(Double4x3Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, (long) 8 * ((3) * stride + 3)), stride);
        return self;
    }
    public Double4x3 loadRMUnsafe(Double4x3Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, (long) 8 * ((3) * stride + 3)), stride);
        return self;
    }
    public Double4x3 storeRMFloatUnsafe(Double4x3Impl self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((3) * stride + 3)), stride);
        return self;
    }
    public Double4x3 loadRMFloatUnsafe(Double4x3Impl self, long address, int stride) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, (long) 4 * ((3) * stride + 3)), stride);
        return self;
    }
}
