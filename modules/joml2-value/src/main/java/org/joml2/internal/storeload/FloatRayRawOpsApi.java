// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatRayRawOpsApi implements FloatRayRawOps {
    public FloatRay storeUnsafe(FloatRay self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public FloatRay loadUnsafe(long address) {
        return FloatRay.load(0L, virtualMemory().asSlice(address, 24L));
    }
    public FloatRay storeDoubleUnsafe(FloatRay self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public FloatRay loadDoubleUnsafe(long address) {
        return FloatRay.loadDouble(0L, virtualMemory().asSlice(address, 48L));
    }
}
