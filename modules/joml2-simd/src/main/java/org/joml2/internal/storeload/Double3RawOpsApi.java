// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Double3RawOpsApi implements Double3RawOps {
    public Double3 storeUnsafe(Double3Impl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double3 loadUnsafe(Double3Impl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Double3 storeFloatUnsafe(Double3Impl self, long address) {
        self.storeFloat(0L, virtualMemory().asSlice(address, 12L));
        return self;
    }
    public Double3 loadFloatUnsafe(Double3Impl self, long address) {
        self.loadFloat(0L, virtualMemory().asSlice(address, 12L));
        return self;
    }
}
