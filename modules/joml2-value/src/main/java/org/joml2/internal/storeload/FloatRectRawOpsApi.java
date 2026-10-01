// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatRectRawOpsApi extends FloatRectRawOps {
    public FloatRect storeUnsafe(FloatRect self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public FloatRect loadUnsafe(long address) {
        return FloatRect.load(0L, virtualMemory().asSlice(address, 16L));
    }
    public FloatRect storeDoubleUnsafe(FloatRect self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public FloatRect loadDoubleUnsafe(long address) {
        return FloatRect.loadDouble(0L, virtualMemory().asSlice(address, 32L));
    }
}
