// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte2RawOpsUnsafe extends Byte2RawOps {
    public Byte2 storeUnsafe(Byte2Impl self, long address) {
        U.putByte(address, self.x);
        U.putByte(address + 1L, self.y);
        return self;
    }

    public Byte2 storeHeap(Byte2Impl self, byte[] bytes, long address) {
        U.putByte(bytes, address, self.x);
        U.putByte(bytes, address + 1L, self.y);
        return self;
    }

    public Byte2 loadUnsafe(Byte2Impl self, long address) {
        self.x = U.getByte(address);
        self.y = U.getByte(address + 1L);
        return self;
    }

    public Byte2 loadHeap(Byte2Impl self, byte[] bytes, long address) {
        self.x = U.getByte(bytes, address);
        self.y = U.getByte(bytes, address + 1L);
        return self;
    }

    public Byte2 storeShortUnsafe(Byte2Impl self, long address) {
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        return self;
    }

    public Byte2 storeShortHeap(Byte2Impl self, byte[] bytes, long address) {
        U.putShort(bytes, address, self.x);
        U.putShort(bytes, address + 2L, self.y);
        return self;
    }

    public Byte2 loadShortUnsafe(Byte2Impl self, long address) {
        self.x = (byte) U.getShort(address);
        self.y = (byte) U.getShort(address + 2L);
        return self;
    }

    public Byte2 loadShortHeap(Byte2Impl self, byte[] bytes, long address) {
        self.x = (byte) U.getShort(bytes, address);
        self.y = (byte) U.getShort(bytes, address + 2L);
        return self;
    }
}
