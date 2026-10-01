// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Long4RawOpsApi implements Long4RawOps {
    public Long4 storeUnsafe(Long4Impl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Long4 loadUnsafe(Long4Impl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Long4 storeIntUnsafe(Long4Impl self, long address) {
        self.storeInt(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Long4 loadIntUnsafe(Long4Impl self, long address) {
        self.loadInt(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
}
