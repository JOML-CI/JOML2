// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short4BbOpsUnsafe extends Short4BbOps {

    private static final Short4BbOpsApi API = new Short4BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public ShortBuffer storeAbsolute(Short4Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 2L;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        U.putShort(address + 4L, self.z);
        U.putShort(address + 6L, self.w);
        return buf;
    }
    public Short4 loadAbsolute(Short4Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 2L;
        self.x = U.getShort(address);
        self.y = U.getShort(address + 2L);
        self.z = U.getShort(address + 4L);
        self.w = U.getShort(address + 6L);
        return self;
    }
    public ByteBuffer storeAbsolute(Short4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        U.putShort(address + 4L, self.z);
        U.putShort(address + 6L, self.w);
        return buf;
    }
    public Short4 loadAbsolute(Short4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = U.getShort(address);
        self.y = U.getShort(address + 2L);
        self.z = U.getShort(address + 4L);
        self.w = U.getShort(address + 6L);
        return self;
    }
    public ByteBuffer storeByteAbsolute(Short4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeByteAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putByte(address, (byte) self.x);
        U.putByte(address + 1L, (byte) self.y);
        U.putByte(address + 2L, (byte) self.z);
        U.putByte(address + 3L, (byte) self.w);
        return buf;
    }
    public Short4 loadByteAbsolute(Short4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadByteAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = U.getByte(address);
        self.y = U.getByte(address + 1L);
        self.z = U.getByte(address + 2L);
        self.w = U.getByte(address + 3L);
        return self;
    }
}
