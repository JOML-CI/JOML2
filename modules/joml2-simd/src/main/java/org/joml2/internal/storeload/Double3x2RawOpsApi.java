// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double3x2RawOpsApi extends Double3x2RawOps {
    public Double3x2 storeCMUnsafe(Double3x2Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double3x2 loadCMUnsafe(Double3x2Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double3x2 storeCMFloatUnsafe(Double3x2Impl self, long address) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double3x2 loadCMFloatUnsafe(Double3x2Impl self, long address) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double3x2 storeRMUnsafe(Double3x2Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double3x2 loadRMUnsafe(Double3x2Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Double3x2 storeRMFloatUnsafe(Double3x2Impl self, long address) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double3x2 loadRMFloatUnsafe(Double3x2Impl self, long address) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double3x2 storeCMUnsafe(Double3x2Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
        return self;
    }
    public Double3x2 loadCMUnsafe(Double3x2Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
        return self;
    }
    public Double3x2 storeCMFloatUnsafe(Double3x2Impl self, long address, int stride) {
        self.storeCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
        return self;
    }
    public Double3x2 loadCMFloatUnsafe(Double3x2Impl self, long address, int stride) {
        self.loadCMFloat(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
        return self;
    }
    public Double3x2 storeRMUnsafe(Double3x2Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
        return self;
    }
    public Double3x2 loadRMUnsafe(Double3x2Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
        return self;
    }
    public Double3x2 storeRMFloatUnsafe(Double3x2Impl self, long address, int stride) {
        self.storeRMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
        return self;
    }
    public Double3x2 loadRMFloatUnsafe(Double3x2Impl self, long address, int stride) {
        self.loadRMFloat(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
        return self;
    }
}
