// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleRayRawOpsApi extends DoubleRayRawOps {
    public DoubleRay storeUnsafe(DoubleRay self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public DoubleRay loadUnsafe(long address) {
        return DoubleRay.load(0L, virtualMemory().asSlice(address, 48L));
    }
    public DoubleRay storeFloatUnsafe(DoubleRay self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public DoubleRay loadFloatUnsafe(long address) {
        return DoubleRay.loadFloat(0L, virtualMemory().asSlice(address, 24L));
    }
}
