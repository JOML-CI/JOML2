// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float3x2RawOpsApi extends Float3x2RawOps {
    public Float3x2 storeCMUnsafe(Float3x2Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Float3x2 loadCMUnsafe(Float3x2Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Float3x2 storeCMDoubleUnsafe(Float3x2Impl self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float3x2 loadCMDoubleUnsafe(Float3x2Impl self, long address) {
        self.loadCMDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float3x2 storeRMUnsafe(Float3x2Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Float3x2 loadRMUnsafe(Float3x2Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Float3x2 storeRMDoubleUnsafe(Float3x2Impl self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float3x2 loadRMDoubleUnsafe(Float3x2Impl self, long address) {
        self.loadRMDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float3x2 storeCMUnsafe(Float3x2Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
        return self;
    }
    public Float3x2 loadCMUnsafe(Float3x2Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
        return self;
    }
    public Float3x2 storeCMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
        return self;
    }
    public Float3x2 loadCMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        self.loadCMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
        return self;
    }
    public Float3x2 storeRMUnsafe(Float3x2Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
        return self;
    }
    public Float3x2 loadRMUnsafe(Float3x2Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
        return self;
    }
    public Float3x2 storeRMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
        return self;
    }
    public Float3x2 loadRMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        self.loadRMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
        return self;
    }
}
