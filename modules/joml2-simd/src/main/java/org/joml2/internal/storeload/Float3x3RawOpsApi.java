// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float3x3RawOpsApi implements Float3x3RawOps {
    public Float3x3 storeCMUnsafe(Float3x3Impl self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Float3x3 loadCMUnsafe(Float3x3Impl self, long address) {
        self.loadCM(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Float3x3 storeCMDoubleUnsafe(Float3x3Impl self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Float3x3 loadCMDoubleUnsafe(Float3x3Impl self, long address) {
        self.loadCMDouble(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Float3x3 storeRMUnsafe(Float3x3Impl self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Float3x3 loadRMUnsafe(Float3x3Impl self, long address) {
        self.loadRM(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Float3x3 storeRMDoubleUnsafe(Float3x3Impl self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Float3x3 loadRMDoubleUnsafe(Float3x3Impl self, long address) {
        self.loadRMDouble(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Float3x3 storeCMUnsafe(Float3x3Impl self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, (long) 4 * ((2) * stride + 3)), stride);
        return self;
    }
    public Float3x3 loadCMUnsafe(Float3x3Impl self, long address, int stride) {
        self.loadCM(0L, virtualMemory().asSlice(address, (long) 4 * ((2) * stride + 3)), stride);
        return self;
    }
    public Float3x3 storeCMDoubleUnsafe(Float3x3Impl self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((2) * stride + 3)), stride);
        return self;
    }
    public Float3x3 loadCMDoubleUnsafe(Float3x3Impl self, long address, int stride) {
        self.loadCMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((2) * stride + 3)), stride);
        return self;
    }
    public Float3x3 storeRMUnsafe(Float3x3Impl self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, (long) 4 * ((2) * stride + 3)), stride);
        return self;
    }
    public Float3x3 loadRMUnsafe(Float3x3Impl self, long address, int stride) {
        self.loadRM(0L, virtualMemory().asSlice(address, (long) 4 * ((2) * stride + 3)), stride);
        return self;
    }
    public Float3x3 storeRMDoubleUnsafe(Float3x3Impl self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((2) * stride + 3)), stride);
        return self;
    }
    public Float3x3 loadRMDoubleUnsafe(Float3x3Impl self, long address, int stride) {
        self.loadRMDouble(0L, virtualMemory().asSlice(address, (long) 8 * ((2) * stride + 3)), stride);
        return self;
    }
    public Float3x3 storeCM4x4Unsafe(Float3x3Impl self, long address) {
        self.storeCM4x4(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float3x3 storeCM4x4DoubleUnsafe(Float3x3Impl self, long address) {
        self.storeCM4x4Double(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Float3x3 storeRM4x4Unsafe(Float3x3Impl self, long address) {
        self.storeRM4x4(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float3x3 storeRM4x4DoubleUnsafe(Float3x3Impl self, long address) {
        self.storeRM4x4Double(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
}
