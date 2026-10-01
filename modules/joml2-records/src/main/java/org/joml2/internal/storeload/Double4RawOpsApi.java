// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double4RawOpsApi implements Double4RawOps {
    public Double4 storeUnsafe(Double4 self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Double4 loadUnsafe(long address) {
        return Double4.load(0L, virtualMemory().asSlice(address, 32L));
    }
    public Double4 storeFloatUnsafe(Double4 self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Double4 loadFloatUnsafe(long address) {
        return Double4.loadFloat(0L, virtualMemory().asSlice(address, 16L));
    }
}
