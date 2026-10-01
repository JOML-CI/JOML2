// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatDualQuatRawOpsApi extends FloatDualQuatRawOps {
    public FloatDualQuat storeUnsafe(FloatDualQuat self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public FloatDualQuat loadUnsafe(long address) {
        return FloatDualQuat.load(0L, virtualMemory().asSlice(address, 32L));
    }
    public FloatDualQuat storeDoubleUnsafe(FloatDualQuat self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 64L));
        return self;
    }
    public FloatDualQuat loadDoubleUnsafe(long address) {
        return FloatDualQuat.loadDouble(0L, virtualMemory().asSlice(address, 64L));
    }
}
