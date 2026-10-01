// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatRigidRawOpsApi implements FloatRigidRawOps {
    public FloatRigid storeUnsafe(FloatRigidImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 28L));
        return self;
    }
    public FloatRigid loadUnsafe(FloatRigidImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 28L));
        return self;
    }
    public FloatRigid storeDoubleUnsafe(FloatRigidImpl self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 56L));
        return self;
    }
    public FloatRigid loadDoubleUnsafe(FloatRigidImpl self, long address) {
        self.loadDouble(0L, virtualMemory().asSlice(address, 56L));
        return self;
    }
}
