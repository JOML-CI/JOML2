// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Byte3RawOpsApi implements Byte3RawOps {
    public Byte3 storeUnsafe(Byte3Impl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 3L));
        return self;
    }
    public Byte3 loadUnsafe(Byte3Impl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 3L));
        return self;
    }
    public Byte3 storeShortUnsafe(Byte3Impl self, long address) {
        self.storeShort(0L, virtualMemory().asSlice(address, 6L));
        return self;
    }
    public Byte3 loadShortUnsafe(Byte3Impl self, long address) {
        self.loadShort(0L, virtualMemory().asSlice(address, 6L));
        return self;
    }
}
