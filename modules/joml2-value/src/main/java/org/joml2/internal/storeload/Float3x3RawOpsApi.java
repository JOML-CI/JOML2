// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float3x3RawOpsApi extends Float3x3RawOps {
    public Float3x3 storeCMUnsafe(Float3x3 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Float3x3 loadCMUnsafe(long address) {
        return Float3x3.loadCM(0L, virtualMemory().asSlice(address, 36L));
    }
    public Float3x3 storeCMDoubleUnsafe(Float3x3 self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Float3x3 loadCMDoubleUnsafe(long address) {
        return Float3x3.loadCMDouble(0L, virtualMemory().asSlice(address, 72L));
    }
    public Float3x3 storeRMUnsafe(Float3x3 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Float3x3 loadRMUnsafe(long address) {
        return Float3x3.loadRM(0L, virtualMemory().asSlice(address, 36L));
    }
    public Float3x3 storeRMDoubleUnsafe(Float3x3 self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Float3x3 loadRMDoubleUnsafe(long address) {
        return Float3x3.loadRMDouble(0L, virtualMemory().asSlice(address, 72L));
    }
    public Float3x3 storeCMUnsafe(Float3x3 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 3)), stride);
        return self;
    }
    public Float3x3 loadCMUnsafe(long address, int stride) {
        return Float3x3.loadCM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 3)), stride);
    }
    public Float3x3 storeCMDoubleUnsafe(Float3x3 self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 3)), stride);
        return self;
    }
    public Float3x3 loadCMDoubleUnsafe(long address, int stride) {
        return Float3x3.loadCMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 3)), stride);
    }
    public Float3x3 storeRMUnsafe(Float3x3 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 3)), stride);
        return self;
    }
    public Float3x3 loadRMUnsafe(long address, int stride) {
        return Float3x3.loadRM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 3)), stride);
    }
    public Float3x3 storeRMDoubleUnsafe(Float3x3 self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 3)), stride);
        return self;
    }
    public Float3x3 loadRMDoubleUnsafe(long address, int stride) {
        return Float3x3.loadRMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 3)), stride);
    }
    public Float3x3 storeCM4x4Unsafe(Float3x3 self, long address) {
        self.storeCM4x4(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float3x3 storeCM4x4DoubleUnsafe(Float3x3 self, long address) {
        self.storeCM4x4Double(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Float3x3 storeRM4x4Unsafe(Float3x3 self, long address) {
        self.storeRM4x4(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float3x3 storeRM4x4DoubleUnsafe(Float3x3 self, long address) {
        self.storeRM4x4Double(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
}
