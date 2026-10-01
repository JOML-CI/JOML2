// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleOBBRawOpsApi implements DoubleOBBRawOps {
    public DoubleOBB storeUnsafe(DoubleOBB self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 120L));
        return self;
    }
    public DoubleOBB loadUnsafe(long address) {
        return DoubleOBB.load(0L, virtualMemory().asSlice(address, 120L));
    }
    public DoubleOBB storeFloatUnsafe(DoubleOBB self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 60L));
        return self;
    }
    public DoubleOBB loadFloatUnsafe(long address) {
        return DoubleOBB.loadFloat(0L, virtualMemory().asSlice(address, 60L));
    }
}
