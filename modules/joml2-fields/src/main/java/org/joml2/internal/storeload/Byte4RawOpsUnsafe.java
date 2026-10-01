// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte4RawOpsUnsafe implements Byte4RawOps {
    public Byte4 storeUnsafe(Byte4Impl self, long address) {
        U.putByte(address + 0L, self.x);
        U.putByte(address + 1L, self.y);
        U.putByte(address + 2L, self.z);
        U.putByte(address + 3L, self.w);
        return self;
    }
    public Byte4 loadUnsafe(Byte4Impl self, long address) {
        self.x = U.getByte(address + 0L);
        self.y = U.getByte(address + 1L);
        self.z = U.getByte(address + 2L);
        self.w = U.getByte(address + 3L);
        return self;
    }
    public Byte4 storeShortUnsafe(Byte4Impl self, long address) {
        U.putShort(address + 0L, self.x);
        U.putShort(address + 2L, self.y);
        U.putShort(address + 4L, self.z);
        U.putShort(address + 6L, self.w);
        return self;
    }
    public Byte4 loadShortUnsafe(Byte4Impl self, long address) {
        self.x = (byte) U.getShort(address + 0L);
        self.y = (byte) U.getShort(address + 2L);
        self.z = (byte) U.getShort(address + 4L);
        self.w = (byte) U.getShort(address + 6L);
        return self;
    }
}
