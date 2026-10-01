// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleDualQuatRawOpsApi extends DoubleDualQuatRawOps {
    public DoubleDualQuat storeUnsafe(DoubleDualQuat self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public DoubleDualQuat loadUnsafe(long address) {
        return DoubleDualQuat.load(0L, virtualMemory().asSlice(address, 64L));
    }
    public DoubleDualQuat storeFloatUnsafe(DoubleDualQuat self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoubleDualQuat loadFloatUnsafe(long address) {
        return DoubleDualQuat.loadFloat(0L, virtualMemory().asSlice(address, 32L));
    }
}
