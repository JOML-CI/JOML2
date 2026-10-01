// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatAABBRawOpsApi extends FloatAABBRawOps {
    public FloatAABB storeUnsafe(FloatAABB self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public FloatAABB loadUnsafe(long address) {
        return FloatAABB.load(0L, virtualMemory().asSlice(address, 24L));
    }
    public FloatAABB storeDoubleUnsafe(FloatAABB self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public FloatAABB loadDoubleUnsafe(long address) {
        return FloatAABB.loadDouble(0L, virtualMemory().asSlice(address, 48L));
    }
}
