// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatSphereRawOpsApi implements FloatSphereRawOps {
    public FloatSphere storeUnsafe(FloatSphere self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public FloatSphere loadUnsafe(long address) {
        return FloatSphere.load(0L, virtualMemory().asSlice(address, 16L));
    }
    public FloatSphere storeDoubleUnsafe(FloatSphere self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public FloatSphere loadDoubleUnsafe(long address) {
        return FloatSphere.loadDouble(0L, virtualMemory().asSlice(address, 32L));
    }
}
