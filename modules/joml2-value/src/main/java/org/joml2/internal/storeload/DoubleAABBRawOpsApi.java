// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleAABBRawOpsApi extends DoubleAABBRawOps {
    public DoubleAABB storeUnsafe(DoubleAABB self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public DoubleAABB loadUnsafe(long address) {
        return DoubleAABB.load(0L, virtualMemory().asSlice(address, 48L));
    }
    public DoubleAABB storeFloatUnsafe(DoubleAABB self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public DoubleAABB loadFloatUnsafe(long address) {
        return DoubleAABB.loadFloat(0L, virtualMemory().asSlice(address, 24L));
    }
}
