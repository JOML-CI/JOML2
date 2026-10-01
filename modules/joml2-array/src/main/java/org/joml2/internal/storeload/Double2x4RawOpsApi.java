// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double2x4RawOpsApi extends Double2x4RawOps {
    public Double2x4 storeCMUnsafe(Double2x4Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double2x4 loadCMUnsafe(Double2x4Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double2x4 storeCMFloatUnsafe(Double2x4Impl self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double2x4 loadCMFloatUnsafe(Double2x4Impl self, long address) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double2x4 storeRMUnsafe(Double2x4Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double2x4 loadRMUnsafe(Double2x4Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double2x4 storeRMFloatUnsafe(Double2x4Impl self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double2x4 loadRMFloatUnsafe(Double2x4Impl self, long address) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double2x4 storeCMUnsafe(Double2x4Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 2)), stride);
        return self;
    }
    public Double2x4 loadCMUnsafe(Double2x4Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 2)), stride);
        return self;
    }
    public Double2x4 storeCMFloatUnsafe(Double2x4Impl self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 2)), stride);
        return self;
    }
    public Double2x4 loadCMFloatUnsafe(Double2x4Impl self, long address, int stride) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 2)), stride);
        return self;
    }
    public Double2x4 storeRMUnsafe(Double2x4Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 8L * (stride + 4)), stride);
        return self;
    }
    public Double2x4 loadRMUnsafe(Double2x4Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, 8L * (stride + 4)), stride);
        return self;
    }
    public Double2x4 storeRMFloatUnsafe(Double2x4Impl self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 4)), stride);
        return self;
    }
    public Double2x4 loadRMFloatUnsafe(Double2x4Impl self, long address, int stride) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 4)), stride);
        return self;
    }
}
