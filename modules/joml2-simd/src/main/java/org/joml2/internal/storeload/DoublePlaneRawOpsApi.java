// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoublePlaneRawOpsApi extends DoublePlaneRawOps {
    public DoublePlane storeUnsafe(DoublePlaneImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoublePlane loadUnsafe(DoublePlaneImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoublePlane storeFloatUnsafe(DoublePlaneImpl self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public DoublePlane loadFloatUnsafe(DoublePlaneImpl self, long address) {
        self.loadFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
}
