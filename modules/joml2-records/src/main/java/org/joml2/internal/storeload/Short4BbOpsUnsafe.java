// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
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

    public ShortBuffer storeAbsolute(Short4 self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 2L;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        U.putShort(address + 6L, self.w());
        return buf;
    }
    public Short4 loadAbsolute(int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 2L;
        short _c0 = U.getShort(address);
        short _c1 = U.getShort(address + 2L);
        short _c2 = U.getShort(address + 4L);
        short _c3 = U.getShort(address + 6L);
        return new Short4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(Short4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        U.putShort(address + 6L, self.w());
        return buf;
    }
    public Short4 loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        short _c0 = U.getShort(address);
        short _c1 = U.getShort(address + 2L);
        short _c2 = U.getShort(address + 4L);
        short _c3 = U.getShort(address + 6L);
        return new Short4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeByteAbsolute(Short4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeByteAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putByte(address, (byte) self.x());
        U.putByte(address + 1L, (byte) self.y());
        U.putByte(address + 2L, (byte) self.z());
        U.putByte(address + 3L, (byte) self.w());
        return buf;
    }
    public Short4 loadByteAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadByteAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        short _c0 = U.getByte(address);
        short _c1 = U.getByte(address + 1L);
        short _c2 = U.getByte(address + 2L);
        short _c3 = U.getByte(address + 3L);
        return new Short4(_c0, _c1, _c2, _c3);
    }
}
