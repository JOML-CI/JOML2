// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Byte2RawOpsApi implements Byte2RawOps {
    public Byte2 storeUnsafe(Byte2 self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 2L));
        return self;
    }
    public Byte2 loadUnsafe(long address) {
        return Byte2.load(0L, virtualMemory().asSlice(address, 2L));
    }
    public Byte2 storeShortUnsafe(Byte2 self, long address) {
        self.storeShort(0L, virtualMemory().asSlice(address, 4L));
        return self;
    }
    public Byte2 loadShortUnsafe(long address) {
        return Byte2.loadShort(0L, virtualMemory().asSlice(address, 4L));
    }
}
