// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Long3RawOpsApi extends Long3RawOps {
    public Long3 storeUnsafe(Long3Impl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Long3 loadUnsafe(Long3Impl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Long3 storeIntUnsafe(Long3Impl self, long address) {
        self.storeInt(0L, virtualMemory().asSlice(address, 12L));
        return self;
    }
    public Long3 loadIntUnsafe(Long3Impl self, long address) {
        self.loadInt(0L, virtualMemory().asSlice(address, 12L));
        return self;
    }
}
