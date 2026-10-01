// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleRectRawOpsApi extends DoubleRectRawOps {
    public DoubleRect storeUnsafe(DoubleRect self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoubleRect loadUnsafe(long address) {
        return DoubleRect.load(0L, virtualMemory().asSlice(address, 32L));
    }
    public DoubleRect storeFloatUnsafe(DoubleRect self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public DoubleRect loadFloatUnsafe(long address) {
        return DoubleRect.loadFloat(0L, virtualMemory().asSlice(address, 16L));
    }
}
