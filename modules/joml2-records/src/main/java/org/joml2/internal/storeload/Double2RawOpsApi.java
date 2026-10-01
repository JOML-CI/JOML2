// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double2RawOpsApi extends Double2RawOps {
    public Double2 storeUnsafe(Double2 self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Double2 loadUnsafe(long address) {
        return Double2.load(0L, virtualMemory().asSlice(address, 16L));
    }
    public Double2 storeFloatUnsafe(Double2 self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 8L));
        return self;
    }
    public Double2 loadFloatUnsafe(long address) {
        return Double2.loadFloat(0L, virtualMemory().asSlice(address, 8L));
    }
}
