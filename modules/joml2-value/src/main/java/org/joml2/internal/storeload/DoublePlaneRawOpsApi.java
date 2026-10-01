// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoublePlaneRawOpsApi implements DoublePlaneRawOps {
    public DoublePlane storeUnsafe(DoublePlane self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoublePlane loadUnsafe(long address) {
        return DoublePlane.load(0L, virtualMemory().asSlice(address, 32L));
    }
    public DoublePlane storeFloatUnsafe(DoublePlane self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public DoublePlane loadFloatUnsafe(long address) {
        return DoublePlane.loadFloat(0L, virtualMemory().asSlice(address, 16L));
    }
}
