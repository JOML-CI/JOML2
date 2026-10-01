// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Byte3RawOpsApi implements Byte3RawOps {
    public Byte3 storeUnsafe(Byte3 self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 3L));
        return self;
    }
    public Byte3 loadUnsafe(long address) {
        return Byte3.load(0L, virtualMemory().asSlice(address, 3L));
    }
    public Byte3 storeShortUnsafe(Byte3 self, long address) {
        self.storeShort(0L, virtualMemory().asSlice(address, 6L));
        return self;
    }
    public Byte3 loadShortUnsafe(long address) {
        return Byte3.loadShort(0L, virtualMemory().asSlice(address, 6L));
    }
}
