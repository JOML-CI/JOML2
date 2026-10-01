// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double3RawOpsApi extends Double3RawOps {
    public Double3 storeUnsafe(Double3 self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double3 loadUnsafe(long address) {
        return Double3.load(0L, virtualMemory().asSlice(address, 24L));
    }
    public Double3 storeFloatUnsafe(Double3 self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 12L));
        return self;
    }
    public Double3 loadFloatUnsafe(long address) {
        return Double3.loadFloat(0L, virtualMemory().asSlice(address, 12L));
    }
}
