// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short4RawOpsUnsafe extends Short4RawOps {
    public Short4 storeUnsafe(Short4 self, long address) {
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        U.putShort(address + 6L, self.w());
        return self;
    }

    public Short4 storeHeap(Short4 self, byte[] bytes, long address) {
        U.putShort(bytes, address, self.x());
        U.putShort(bytes, address + 2L, self.y());
        U.putShort(bytes, address + 4L, self.z());
        U.putShort(bytes, address + 6L, self.w());
        return self;
    }

    public Short4 loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        short _c0 = _U.getShort(address);
        short _c1 = _U.getShort(address + 2L);
        short _c2 = _U.getShort(address + 4L);
        short _c3 = _U.getShort(address + 6L);
        return new Short4(_c0, _c1, _c2, _c3);
    }

    public Short4 loadHeap(byte[] bytes, long address) {
        short _c0 = U.getShort(bytes, address);
        short _c1 = U.getShort(bytes, address + 2L);
        short _c2 = U.getShort(bytes, address + 4L);
        short _c3 = U.getShort(bytes, address + 6L);
        return new Short4(_c0, _c1, _c2, _c3);
    }

    public Short4 storeByteUnsafe(Short4 self, long address) {
        U.putByte(address, (byte) self.x());
        U.putByte(address + 1L, (byte) self.y());
        U.putByte(address + 2L, (byte) self.z());
        U.putByte(address + 3L, (byte) self.w());
        return self;
    }

    public Short4 storeByteHeap(Short4 self, byte[] bytes, long address) {
        U.putByte(bytes, address, (byte) self.x());
        U.putByte(bytes, address + 1L, (byte) self.y());
        U.putByte(bytes, address + 2L, (byte) self.z());
        U.putByte(bytes, address + 3L, (byte) self.w());
        return self;
    }

    public Short4 loadByteUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        short _c0 = _U.getByte(address);
        short _c1 = _U.getByte(address + 1L);
        short _c2 = _U.getByte(address + 2L);
        short _c3 = _U.getByte(address + 3L);
        return new Short4(_c0, _c1, _c2, _c3);
    }

    public Short4 loadByteHeap(byte[] bytes, long address) {
        short _c0 = U.getByte(bytes, address);
        short _c1 = U.getByte(bytes, address + 1L);
        short _c2 = U.getByte(bytes, address + 2L);
        short _c3 = U.getByte(bytes, address + 3L);
        return new Short4(_c0, _c1, _c2, _c3);
    }
}
