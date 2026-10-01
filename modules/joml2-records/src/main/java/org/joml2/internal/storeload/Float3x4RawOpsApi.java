// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float3x4RawOpsApi extends Float3x4RawOps {
    public Float3x4 storeCMUnsafe(Float3x4 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float3x4 loadCMUnsafe(long address) {
        return Float3x4.loadCM(0L, virtualMemory().asSlice(address, 48L));
    }
    public Float3x4 storeCMDoubleUnsafe(Float3x4 self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Float3x4 loadCMDoubleUnsafe(long address) {
        return Float3x4.loadCMDouble(0L, virtualMemory().asSlice(address, 96L));
    }
    public Float3x4 storeRMUnsafe(Float3x4 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float3x4 loadRMUnsafe(long address) {
        return Float3x4.loadRM(0L, virtualMemory().asSlice(address, 48L));
    }
    public Float3x4 storeRMDoubleUnsafe(Float3x4 self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 96L));
        return self;
    }
    public Float3x4 loadRMDoubleUnsafe(long address) {
        return Float3x4.loadRMDouble(0L, virtualMemory().asSlice(address, 96L));
    }
    public Float3x4 storeCMUnsafe(Float3x4 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 3)), stride);
        return self;
    }
    public Float3x4 loadCMUnsafe(long address, int stride) {
        return Float3x4.loadCM(0L, virtualMemory().asSlice(address, 4L * (3 * stride + 3)), stride);
    }
    public Float3x4 storeCMDoubleUnsafe(Float3x4 self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 3)), stride);
        return self;
    }
    public Float3x4 loadCMDoubleUnsafe(long address, int stride) {
        return Float3x4.loadCMDouble(0L, virtualMemory().asSlice(address, 8L * (3 * stride + 3)), stride);
    }
    public Float3x4 storeRMUnsafe(Float3x4 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 4)), stride);
        return self;
    }
    public Float3x4 loadRMUnsafe(long address, int stride) {
        return Float3x4.loadRM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 4)), stride);
    }
    public Float3x4 storeRMDoubleUnsafe(Float3x4 self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 4)), stride);
        return self;
    }
    public Float3x4 loadRMDoubleUnsafe(long address, int stride) {
        return Float3x4.loadRMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 4)), stride);
    }
    public Float3x4 storeCM4x4Unsafe(Float3x4 self, long address) {
        self.storeCM4x4(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float3x4 storeCM4x4DoubleUnsafe(Float3x4 self, long address) {
        self.storeCM4x4Double(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Float3x4 storeRM4x4Unsafe(Float3x4 self, long address) {
        self.storeRM4x4(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float3x4 storeRM4x4DoubleUnsafe(Float3x4 self, long address) {
        self.storeRM4x4Double(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
}
