// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatQuatRawOpsApi implements FloatQuatRawOps {
    public FloatQuat storeUnsafe(FloatQuatImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public FloatQuat loadUnsafe(FloatQuatImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public FloatQuat storeDoubleUnsafe(FloatQuatImpl self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public FloatQuat loadDoubleUnsafe(FloatQuatImpl self, long address) {
        self.loadDouble(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
}
