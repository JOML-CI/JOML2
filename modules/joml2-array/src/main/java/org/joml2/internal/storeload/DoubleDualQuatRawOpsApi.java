// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleDualQuatRawOpsApi extends DoubleDualQuatRawOps {
    public DoubleDualQuat storeUnsafe(DoubleDualQuatImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public DoubleDualQuat loadUnsafe(DoubleDualQuatImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public DoubleDualQuat storeFloatUnsafe(DoubleDualQuatImpl self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoubleDualQuat loadFloatUnsafe(DoubleDualQuatImpl self, long address) {
        self.loadFloat(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
}
