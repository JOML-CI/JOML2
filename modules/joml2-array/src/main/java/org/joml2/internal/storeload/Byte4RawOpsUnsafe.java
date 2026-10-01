// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte4RawOpsUnsafe implements Byte4RawOps {
    public Byte4 storeUnsafe(Byte4Impl self, long address) {
        U.putByte(address + 0L, self.data[0]);
        U.putByte(address + 1L, self.data[1]);
        U.putByte(address + 2L, self.data[2]);
        U.putByte(address + 3L, self.data[3]);
        return self;
    }
    public Byte4 loadUnsafe(Byte4Impl self, long address) {
        self.data[0] = U.getByte(address + 0L);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        self.data[3] = U.getByte(address + 3L);
        return self;
    }
    public Byte4 storeShortUnsafe(Byte4Impl self, long address) {
        U.putShort(address + 0L, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        U.putShort(address + 6L, self.data[3]);
        return self;
    }
    public Byte4 loadShortUnsafe(Byte4Impl self, long address) {
        self.data[0] = (byte) U.getShort(address + 0L);
        self.data[1] = (byte) U.getShort(address + 2L);
        self.data[2] = (byte) U.getShort(address + 4L);
        self.data[3] = (byte) U.getShort(address + 6L);
        return self;
    }
}
