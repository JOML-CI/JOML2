// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatTriangleRawOpsApi implements FloatTriangleRawOps {
    public FloatTriangle storeUnsafe(FloatTriangle self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 36L));
        return self;
    }
    public FloatTriangle loadUnsafe(long address) {
        return FloatTriangle.load(0L, virtualMemory().asSlice(address, 36L));
    }
    public FloatTriangle storeDoubleUnsafe(FloatTriangle self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 72L));
        return self;
    }
    public FloatTriangle loadDoubleUnsafe(long address) {
        return FloatTriangle.loadDouble(0L, virtualMemory().asSlice(address, 72L));
    }
}
