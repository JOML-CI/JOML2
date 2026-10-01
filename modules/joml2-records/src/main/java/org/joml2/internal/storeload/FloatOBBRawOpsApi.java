// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatOBBRawOpsApi implements FloatOBBRawOps {
    public FloatOBB storeUnsafe(FloatOBB self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 60L));
        return self;
    }
    public FloatOBB loadUnsafe(long address) {
        return FloatOBB.load(0L, virtualMemory().asSlice(address, 60L));
    }
    public FloatOBB storeDoubleUnsafe(FloatOBB self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 120L));
        return self;
    }
    public FloatOBB loadDoubleUnsafe(long address) {
        return FloatOBB.loadDouble(0L, virtualMemory().asSlice(address, 120L));
    }
}
