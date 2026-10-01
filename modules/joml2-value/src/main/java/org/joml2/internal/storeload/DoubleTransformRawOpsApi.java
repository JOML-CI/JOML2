// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleTransformRawOpsApi implements DoubleTransformRawOps {
    public DoubleTransform storeUnsafe(DoubleTransform self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 80L));
        return self;
    }
    public DoubleTransform loadUnsafe(long address) {
        return DoubleTransform.load(0L, virtualMemory().asSlice(address, 80L));
    }
    public DoubleTransform storeFloatUnsafe(DoubleTransform self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 40L));
        return self;
    }
    public DoubleTransform loadFloatUnsafe(long address) {
        return DoubleTransform.loadFloat(0L, virtualMemory().asSlice(address, 40L));
    }
}
