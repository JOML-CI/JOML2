// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Float3RawOpsApi extends Float3RawOps {
    public Float3 storeUnsafe(Float3Impl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 12L));
        return self;
    }
    public Float3 loadUnsafe(Float3Impl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 12L));
        return self;
    }
    public Float3 storeDoubleUnsafe(Float3Impl self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public Float3 loadDoubleUnsafe(Float3Impl self, long address) {
        self.loadDouble(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
}
