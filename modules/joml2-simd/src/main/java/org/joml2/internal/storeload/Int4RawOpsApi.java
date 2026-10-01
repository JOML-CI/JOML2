// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Int4RawOpsApi extends Int4RawOps {
    public Int4 storeUnsafe(Int4Impl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Int4 loadUnsafe(Int4Impl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public Int4 storeLongUnsafe(Int4Impl self, long address) {
        self.storeLong(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public Int4 loadLongUnsafe(Int4Impl self, long address) {
        self.loadLong(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
}
