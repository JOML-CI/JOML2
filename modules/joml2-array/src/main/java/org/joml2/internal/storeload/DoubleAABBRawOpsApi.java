// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleAABBRawOpsApi extends DoubleAABBRawOps {
    public DoubleAABB storeUnsafe(DoubleAABBImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public DoubleAABB loadUnsafe(DoubleAABBImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public DoubleAABB storeFloatUnsafe(DoubleAABBImpl self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public DoubleAABB loadFloatUnsafe(DoubleAABBImpl self, long address) {
        self.loadFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
}
