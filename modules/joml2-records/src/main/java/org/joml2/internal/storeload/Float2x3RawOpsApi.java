// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float2x3RawOpsApi extends Float2x3RawOps {
    public Float2x3 storeCMUnsafe(Float2x3 self, long address) {
        self.storeCM(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Float2x3 loadCMUnsafe(long address) {
        return Float2x3.loadCM(0L, virtualMemory().asSlice(address, 24L));
    }
    public Float2x3 storeCMDoubleUnsafe(Float2x3 self, long address) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float2x3 loadCMDoubleUnsafe(long address) {
        return Float2x3.loadCMDouble(0L, virtualMemory().asSlice(address, 48L));
    }
    public Float2x3 storeRMUnsafe(Float2x3 self, long address) {
        self.storeRM(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Float2x3 loadRMUnsafe(long address) {
        return Float2x3.loadRM(0L, virtualMemory().asSlice(address, 24L));
    }
    public Float2x3 storeRMDoubleUnsafe(Float2x3 self, long address) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public Float2x3 loadRMDoubleUnsafe(long address) {
        return Float2x3.loadRMDouble(0L, virtualMemory().asSlice(address, 48L));
    }
    public Float2x3 storeCMUnsafe(Float2x3 self, long address, int stride) {
        self.storeCM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
        return self;
    }
    public Float2x3 loadCMUnsafe(long address, int stride) {
        return Float2x3.loadCM(0L, virtualMemory().asSlice(address, 4L * (2 * stride + 2)), stride);
    }
    public Float2x3 storeCMDoubleUnsafe(Float2x3 self, long address, int stride) {
        self.storeCMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
        return self;
    }
    public Float2x3 loadCMDoubleUnsafe(long address, int stride) {
        return Float2x3.loadCMDouble(0L, virtualMemory().asSlice(address, 8L * (2 * stride + 2)), stride);
    }
    public Float2x3 storeRMUnsafe(Float2x3 self, long address, int stride) {
        self.storeRM(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
        return self;
    }
    public Float2x3 loadRMUnsafe(long address, int stride) {
        return Float2x3.loadRM(0L, virtualMemory().asSlice(address, 4L * (stride + 3)), stride);
    }
    public Float2x3 storeRMDoubleUnsafe(Float2x3 self, long address, int stride) {
        self.storeRMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
        return self;
    }
    public Float2x3 loadRMDoubleUnsafe(long address, int stride) {
        return Float2x3.loadRMDouble(0L, virtualMemory().asSlice(address, 8L * (stride + 3)), stride);
    }
    public Float2x3 storeCM3x3Unsafe(Float2x3 self, long address) {
        self.storeCM3x3(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Float2x3 storeCM3x3DoubleUnsafe(Float2x3 self, long address) {
        self.storeCM3x3Double(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Float2x3 storeRM3x3Unsafe(Float2x3 self, long address) {
        self.storeRM3x3(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public Float2x3 storeRM3x3DoubleUnsafe(Float2x3 self, long address) {
        self.storeRM3x3Double(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public Float2x3 storeCM4x4Unsafe(Float2x3 self, long address) {
        self.storeCM4x4(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float2x3 storeCM4x4DoubleUnsafe(Float2x3 self, long address) {
        self.storeCM4x4Double(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
    public Float2x3 storeRM4x4Unsafe(Float2x3 self, long address) {
        self.storeRM4x4(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public Float2x3 storeRM4x4DoubleUnsafe(Float2x3 self, long address) {
        self.storeRM4x4Double(0L, virtualMemory().asSlice(address, 128L));
        return self;
    }
}
