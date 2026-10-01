// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double4x2RawOpsApi extends Double4x2RawOps {
    public Double4x2 storeCMUnsafe(Double4x2Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double4x2 loadCMUnsafe(Double4x2Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double4x2 storeCMFloatUnsafe(Double4x2Impl self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double4x2 loadCMFloatUnsafe(Double4x2Impl self, long address) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double4x2 storeRMUnsafe(Double4x2Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double4x2 loadRMUnsafe(Double4x2Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Double4x2 storeRMFloatUnsafe(Double4x2Impl self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double4x2 loadRMFloatUnsafe(Double4x2Impl self, long address) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double4x2 storeCMUnsafe(Double4x2Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 8L * (stride + 4)), stride);
        return self;
    }
    public Double4x2 loadCMUnsafe(Double4x2Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, 8L * (stride + 4)), stride);
        return self;
    }
    public Double4x2 storeCMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 4)), stride);
        return self;
    }
    public Double4x2 loadCMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 4)), stride);
        return self;
    }
    public Double4x2 storeRMUnsafe(Double4x2Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 2)), stride);
        return self;
    }
    public Double4x2 loadRMUnsafe(Double4x2Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 2)), stride);
        return self;
    }
    public Double4x2 storeRMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 2)), stride);
        return self;
    }
    public Double4x2 loadRMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 2)), stride);
        return self;
    }
}
