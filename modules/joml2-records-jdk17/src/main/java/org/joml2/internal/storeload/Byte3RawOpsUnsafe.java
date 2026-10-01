// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte3RawOpsUnsafe extends Byte3RawOps {
    public Byte3 storeUnsafe(Byte3 self, long address) {
        U.putByte(address, self.x());
        U.putByte(address + 1L, self.y());
        U.putByte(address + 2L, self.z());
        return self;
    }

    public Byte3 storeHeap(Byte3 self, byte[] bytes, long address) {
        U.putByte(bytes, address, self.x());
        U.putByte(bytes, address + 1L, self.y());
        U.putByte(bytes, address + 2L, self.z());
        return self;
    }

    public Byte3 loadUnsafe(long address) {
        byte _c0 = U.getByte(address);
        byte _c1 = U.getByte(address + 1L);
        byte _c2 = U.getByte(address + 2L);
        return new Byte3(_c0, _c1, _c2);
    }

    public Byte3 loadHeap(byte[] bytes, long address) {
        byte _c0 = U.getByte(bytes, address);
        byte _c1 = U.getByte(bytes, address + 1L);
        byte _c2 = U.getByte(bytes, address + 2L);
        return new Byte3(_c0, _c1, _c2);
    }

    public Byte3 storeShortUnsafe(Byte3 self, long address) {
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        return self;
    }

    public Byte3 storeShortHeap(Byte3 self, byte[] bytes, long address) {
        U.putShort(bytes, address, self.x());
        U.putShort(bytes, address + 2L, self.y());
        U.putShort(bytes, address + 4L, self.z());
        return self;
    }

    public Byte3 loadShortUnsafe(long address) {
        byte _c0 = (byte) U.getShort(address);
        byte _c1 = (byte) U.getShort(address + 2L);
        byte _c2 = (byte) U.getShort(address + 4L);
        return new Byte3(_c0, _c1, _c2);
    }

    public Byte3 loadShortHeap(byte[] bytes, long address) {
        byte _c0 = (byte) U.getShort(bytes, address);
        byte _c1 = (byte) U.getShort(bytes, address + 2L);
        byte _c2 = (byte) U.getShort(bytes, address + 4L);
        return new Byte3(_c0, _c1, _c2);
    }
}
