// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short2RawOpsUnsafe implements Short2RawOps {
    public Short2 storeUnsafe(Short2Impl self, long address) {
        U.putShort(address + 0L, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        return self;
    }
    public Short2 loadUnsafe(Short2Impl self, long address) {
        self.data[0] = U.getShort(address + 0L);
        self.data[1] = U.getShort(address + 2L);
        return self;
    }
    public Short2 storeByteUnsafe(Short2Impl self, long address) {
        U.putByte(address + 0L, (byte) self.data[0]);
        U.putByte(address + 1L, (byte) self.data[1]);
        return self;
    }
    public Short2 loadByteUnsafe(Short2Impl self, long address) {
        self.data[0] = U.getByte(address + 0L);
        self.data[1] = U.getByte(address + 1L);
        return self;
    }
}
