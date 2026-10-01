// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatPlaneRawOpsApi extends FloatPlaneRawOps {
    public FloatPlane storeUnsafe(FloatPlane self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public FloatPlane loadUnsafe(long address) {
        return FloatPlane.load(0L, virtualMemory().asSlice(address, 16L));
    }
    public FloatPlane storeDoubleUnsafe(FloatPlane self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public FloatPlane loadDoubleUnsafe(long address) {
        return FloatPlane.loadDouble(0L, virtualMemory().asSlice(address, 32L));
    }
}
