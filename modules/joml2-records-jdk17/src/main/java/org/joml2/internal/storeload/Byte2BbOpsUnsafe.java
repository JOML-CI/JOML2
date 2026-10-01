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

public final class Byte2BbOpsUnsafe extends Byte2BbOps {

    private static final Byte2BbOpsApi API = new Byte2BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public ByteBuffer storeAbsolute(Byte2 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putByte(address, self.x());
        U.putByte(address + 1L, self.y());
        return buf;
    }
    public Byte2 loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        byte _c0 = U.getByte(address);
        byte _c1 = U.getByte(address + 1L);
        return new Byte2(_c0, _c1);
    }
    public ShortBuffer storeAbsolute(Byte2 self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 2L;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        return buf;
    }
    public Byte2 loadAbsolute(int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 2L;
        byte _c0 = (byte) U.getShort(address);
        byte _c1 = (byte) U.getShort(address + 2L);
        return new Byte2(_c0, _c1);
    }
    public ByteBuffer storeShortAbsolute(Byte2 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeShortAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        return buf;
    }
    public Byte2 loadShortAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadShortAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        byte _c0 = (byte) U.getShort(address);
        byte _c1 = (byte) U.getShort(address + 2L);
        return new Byte2(_c0, _c1);
    }
}
