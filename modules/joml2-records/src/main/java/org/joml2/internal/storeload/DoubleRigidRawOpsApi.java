// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleRigidRawOpsApi extends DoubleRigidRawOps {
    public DoubleRigid storeUnsafe(DoubleRigid self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 56L));
        return self;
    }
    public DoubleRigid loadUnsafe(long address) {
        return DoubleRigid.load(0L, virtualMemory().asSlice(address, 56L));
    }
    public DoubleRigid storeFloatUnsafe(DoubleRigid self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 28L));
        return self;
    }
    public DoubleRigid loadFloatUnsafe(long address) {
        return DoubleRigid.loadFloat(0L, virtualMemory().asSlice(address, 28L));
    }
}
