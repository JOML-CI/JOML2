// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleRigidRawOpsApi extends DoubleRigidRawOps {
    public DoubleRigid storeUnsafe(DoubleRigidImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 56L));
        return self;
    }
    public DoubleRigid loadUnsafe(DoubleRigidImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 56L));
        return self;
    }
    public DoubleRigid storeFloatUnsafe(DoubleRigidImpl self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 28L));
        return self;
    }
    public DoubleRigid loadFloatUnsafe(DoubleRigidImpl self, long address) {
        self.loadFloat(0L, virtualMemory().asSlice(address, 28L));
        return self;
    }
}
