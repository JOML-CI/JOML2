// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte2RawOpsUnsafe implements Byte2RawOps {
    public Byte2 storeUnsafe(Byte2 self, long address) {
        U.putByte(address + 0L, self.x());
        U.putByte(address + 1L, self.y());
        return self;
    }
    public Byte2 loadUnsafe(long address) {
        byte _c0 = U.getByte(address + 0L);
        byte _c1 = U.getByte(address + 1L);
        return new Byte2(_c0, _c1);
    }
    public Byte2 storeShortUnsafe(Byte2 self, long address) {
        U.putShort(address + 0L, self.x());
        U.putShort(address + 2L, self.y());
        return self;
    }
    public Byte2 loadShortUnsafe(long address) {
        byte _c0 = (byte) U.getShort(address + 0L);
        byte _c1 = (byte) U.getShort(address + 2L);
        return new Byte2(_c0, _c1);
    }
}
