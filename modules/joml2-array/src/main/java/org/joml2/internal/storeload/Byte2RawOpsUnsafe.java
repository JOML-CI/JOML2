// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte2RawOpsUnsafe implements Byte2RawOps {
    public Byte2 storeUnsafe(Byte2Impl self, long address) {
        U.putByte(address + 0L, self.data[0]);
        U.putByte(address + 1L, self.data[1]);
        return self;
    }
    public Byte2 loadUnsafe(Byte2Impl self, long address) {
        self.data[0] = U.getByte(address + 0L);
        self.data[1] = U.getByte(address + 1L);
        return self;
    }
    public Byte2 storeShortUnsafe(Byte2Impl self, long address) {
        U.putShort(address + 0L, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        return self;
    }
    public Byte2 loadShortUnsafe(Byte2Impl self, long address) {
        self.data[0] = (byte) U.getShort(address + 0L);
        self.data[1] = (byte) U.getShort(address + 2L);
        return self;
    }
}
