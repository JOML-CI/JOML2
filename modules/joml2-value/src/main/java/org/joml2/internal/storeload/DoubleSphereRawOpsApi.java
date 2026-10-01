// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleSphereRawOpsApi extends DoubleSphereRawOps {
    public DoubleSphere storeUnsafe(DoubleSphere self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoubleSphere loadUnsafe(long address) {
        return DoubleSphere.load(0L, virtualMemory().asSlice(address, 32L));
    }
    public DoubleSphere storeFloatUnsafe(DoubleSphere self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public DoubleSphere loadFloatUnsafe(long address) {
        return DoubleSphere.loadFloat(0L, virtualMemory().asSlice(address, 16L));
    }
}
