// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Long2RawOpsApi implements Long2RawOps {
    public Long2 storeUnsafe(Long2 self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Long2 loadUnsafe(long address) {
        return Long2.load(0L, virtualMemory().asSlice(address, 16L));
    }
    public Long2 storeIntUnsafe(Long2 self, long address) {
        self.storeInt(0L, virtualMemory().asSlice(address, 8L));
        return self;
    }
    public Long2 loadIntUnsafe(long address) {
        return Long2.loadInt(0L, virtualMemory().asSlice(address, 8L));
    }
}
