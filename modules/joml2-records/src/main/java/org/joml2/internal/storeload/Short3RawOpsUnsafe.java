// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short3RawOpsUnsafe extends Short3RawOps {
    public Short3 storeUnsafe(Short3 self, long address) {
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        return self;
    }

    public Short3 storeHeap(Short3 self, byte[] bytes, long address) {
        U.putShort(bytes, address, self.x());
        U.putShort(bytes, address + 2L, self.y());
        U.putShort(bytes, address + 4L, self.z());
        return self;
    }

    public Short3 loadUnsafe(long address) {
        short _c0 = U.getShort(address);
        short _c1 = U.getShort(address + 2L);
        short _c2 = U.getShort(address + 4L);
        return new Short3(_c0, _c1, _c2);
    }

    public Short3 loadHeap(byte[] bytes, long address) {
        short _c0 = U.getShort(bytes, address);
        short _c1 = U.getShort(bytes, address + 2L);
        short _c2 = U.getShort(bytes, address + 4L);
        return new Short3(_c0, _c1, _c2);
    }

    public Short3 storeByteUnsafe(Short3 self, long address) {
        U.putByte(address, (byte) self.x());
        U.putByte(address + 1L, (byte) self.y());
        U.putByte(address + 2L, (byte) self.z());
        return self;
    }

    public Short3 storeByteHeap(Short3 self, byte[] bytes, long address) {
        U.putByte(bytes, address, (byte) self.x());
        U.putByte(bytes, address + 1L, (byte) self.y());
        U.putByte(bytes, address + 2L, (byte) self.z());
        return self;
    }

    public Short3 loadByteUnsafe(long address) {
        short _c0 = U.getByte(address);
        short _c1 = U.getByte(address + 1L);
        short _c2 = U.getByte(address + 2L);
        return new Short3(_c0, _c1, _c2);
    }

    public Short3 loadByteHeap(byte[] bytes, long address) {
        short _c0 = U.getByte(bytes, address);
        short _c1 = U.getByte(bytes, address + 1L);
        short _c2 = U.getByte(bytes, address + 2L);
        return new Short3(_c0, _c1, _c2);
    }
}
