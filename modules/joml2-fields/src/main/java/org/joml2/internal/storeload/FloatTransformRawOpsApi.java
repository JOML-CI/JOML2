// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class FloatTransformRawOpsApi extends FloatTransformRawOps {
    public FloatTransform storeUnsafe(FloatTransformImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 40L));
        return self;
    }
    public FloatTransform loadUnsafe(FloatTransformImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 40L));
        return self;
    }
    public FloatTransform storeDoubleUnsafe(FloatTransformImpl self, long address) {
        self.storeDouble(0L, virtualMemory().asSlice(address, 80L));
        return self;
    }
    public FloatTransform loadDoubleUnsafe(FloatTransformImpl self, long address) {
        self.loadDouble(0L, virtualMemory().asSlice(address, 80L));
        return self;
    }
}
