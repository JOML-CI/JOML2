// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleOBBRawOpsApi implements DoubleOBBRawOps {
    public DoubleOBB storeUnsafe(DoubleOBBImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 120L));
        return self;
    }
    public DoubleOBB loadUnsafe(DoubleOBBImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 120L));
        return self;
    }
    public DoubleOBB storeFloatUnsafe(DoubleOBBImpl self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 60L));
        return self;
    }
    public DoubleOBB loadFloatUnsafe(DoubleOBBImpl self, long address) {
        self.loadFloat(0L, virtualMemory().asSlice(address, 60L));
        return self;
    }
}
