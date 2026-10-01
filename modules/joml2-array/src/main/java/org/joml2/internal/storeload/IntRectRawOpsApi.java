// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class IntRectRawOpsApi extends IntRectRawOps {
    public IntRect storeUnsafe(IntRectImpl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public IntRect loadUnsafe(IntRectImpl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 16L));
        return self;
    }
    public IntRect storeLongUnsafe(IntRectImpl self, long address) {
        self.storeLong(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
    public IntRect loadLongUnsafe(IntRectImpl self, long address) {
        self.loadLong(0L, virtualMemory().asSlice(address, 32L));
        return self;
    }
}
