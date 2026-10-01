// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

public final class Short2RawOpsApi extends Short2RawOps {
    public Short2 storeUnsafe(Short2 self, long address) {
        self.store(0L, virtualMemory().asSlice(address, 4L));
        return self;
    }
    public Short2 loadUnsafe(long address) {
        return Short2.load(0L, virtualMemory().asSlice(address, 4L));
    }
    public Short2 storeByteUnsafe(Short2 self, long address) {
        self.storeByte(0L, virtualMemory().asSlice(address, 2L));
        return self;
    }
    public Short2 loadByteUnsafe(long address) {
        return Short2.loadByte(0L, virtualMemory().asSlice(address, 2L));
    }
}
