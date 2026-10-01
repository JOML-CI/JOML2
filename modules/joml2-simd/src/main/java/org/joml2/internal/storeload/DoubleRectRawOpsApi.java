// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class DoubleRectRawOpsApi extends DoubleRectRawOps {
    public DoubleRect storeUnsafe(DoubleRectImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoubleRect loadUnsafe(DoubleRectImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public DoubleRect storeFloatUnsafe(DoubleRectImpl self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public DoubleRect loadFloatUnsafe(DoubleRectImpl self, long address) {
        self.loadFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
}
