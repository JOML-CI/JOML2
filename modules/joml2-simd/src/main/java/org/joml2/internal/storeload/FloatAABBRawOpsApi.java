// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatAABBRawOpsApi extends FloatAABBRawOps {
    public FloatAABB storeUnsafe(FloatAABBImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public FloatAABB loadUnsafe(FloatAABBImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 24L));
        return self;
    }
    public FloatAABB storeDoubleUnsafe(FloatAABBImpl self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
    public FloatAABB loadDoubleUnsafe(FloatAABBImpl self, long address) {
        self.loadDouble(0L, virtualMemory().asSlice(address, 48L));
        return self;
    }
}
