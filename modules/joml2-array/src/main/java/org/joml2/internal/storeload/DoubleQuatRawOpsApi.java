// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleQuatRawOpsApi implements DoubleQuatRawOps {
    public DoubleQuat storeUnsafe(DoubleQuatImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoubleQuat loadUnsafe(DoubleQuatImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoubleQuat storeFloatUnsafe(DoubleQuatImpl self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public DoubleQuat loadFloatUnsafe(DoubleQuatImpl self, long address) {
        self.loadFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
}
