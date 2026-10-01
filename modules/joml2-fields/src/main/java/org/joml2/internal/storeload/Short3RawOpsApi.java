// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Short3RawOpsApi extends Short3RawOps {
    public Short3 storeUnsafe(Short3Impl self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 6L));
        return self;
    }
    public Short3 loadUnsafe(Short3Impl self, long address) {
        self.load(0L, virtualMemory().asSlice(address, 6L));
        return self;
    }
    public Short3 storeByteUnsafe(Short3Impl self, long address) {
        self.storeByte(0L, virtualMemory().asSlice(address, 3L));
        return self;
    }
    public Short3 loadByteUnsafe(Short3Impl self, long address) {
        self.loadByte(0L, virtualMemory().asSlice(address, 3L));
        return self;
    }
}
