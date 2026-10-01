// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float2RawOpsApi implements Float2RawOps {
    public Float2 storeUnsafe(Float2 self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 8L));
        return self;
    }
    public Float2 loadUnsafe(long address) {
        return Float2.load(0L, virtualMemory().asSlice(address, 8L));
    }
    public Float2 storeDoubleUnsafe(Float2 self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Float2 loadDoubleUnsafe(long address) {
        return Float2.loadDouble(0L, virtualMemory().asSlice(address, 16L));
    }
}
