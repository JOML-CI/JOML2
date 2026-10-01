// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatOBBRawOpsApi implements FloatOBBRawOps {
    public FloatOBB storeUnsafe(FloatOBBImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 60L));
        return self;
    }
    public FloatOBB loadUnsafe(FloatOBBImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 60L));
        return self;
    }
    public FloatOBB storeDoubleUnsafe(FloatOBBImpl self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 120L));
        return self;
    }
    public FloatOBB loadDoubleUnsafe(FloatOBBImpl self, long address) {
        self.loadDouble(0L, virtualMemory().asSlice(address, 120L));
        return self;
    }
}
