// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short4RawOpsUnsafe extends Short4RawOps {
    public Short4 storeUnsafe(Short4Impl self, long address) {
        U.putShort(address, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        U.putShort(address + 6L, self.data[3]);
        return self;
    }

    public Short4 storeHeap(Short4Impl self, byte[] bytes, long address) {
        U.putShort(bytes, address, self.data[0]);
        U.putShort(bytes, address + 2L, self.data[1]);
        U.putShort(bytes, address + 4L, self.data[2]);
        U.putShort(bytes, address + 6L, self.data[3]);
        return self;
    }

    public Short4 loadUnsafe(Short4Impl self, long address) {
        self.data[0] = U.getShort(address);
        self.data[1] = U.getShort(address + 2L);
        self.data[2] = U.getShort(address + 4L);
        self.data[3] = U.getShort(address + 6L);
        return self;
    }

    public Short4 loadHeap(Short4Impl self, byte[] bytes, long address) {
        self.data[0] = U.getShort(bytes, address);
        self.data[1] = U.getShort(bytes, address + 2L);
        self.data[2] = U.getShort(bytes, address + 4L);
        self.data[3] = U.getShort(bytes, address + 6L);
        return self;
    }

    public Short4 storeByteUnsafe(Short4Impl self, long address) {
        U.putByte(address, (byte) self.data[0]);
        U.putByte(address + 1L, (byte) self.data[1]);
        U.putByte(address + 2L, (byte) self.data[2]);
        U.putByte(address + 3L, (byte) self.data[3]);
        return self;
    }

    public Short4 storeByteHeap(Short4Impl self, byte[] bytes, long address) {
        U.putByte(bytes, address, (byte) self.data[0]);
        U.putByte(bytes, address + 1L, (byte) self.data[1]);
        U.putByte(bytes, address + 2L, (byte) self.data[2]);
        U.putByte(bytes, address + 3L, (byte) self.data[3]);
        return self;
    }

    public Short4 loadByteUnsafe(Short4Impl self, long address) {
        self.data[0] = U.getByte(address);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        self.data[3] = U.getByte(address + 3L);
        return self;
    }

    public Short4 loadByteHeap(Short4Impl self, byte[] bytes, long address) {
        self.data[0] = U.getByte(bytes, address);
        self.data[1] = U.getByte(bytes, address + 1L);
        self.data[2] = U.getByte(bytes, address + 2L);
        self.data[3] = U.getByte(bytes, address + 3L);
        return self;
    }
}
