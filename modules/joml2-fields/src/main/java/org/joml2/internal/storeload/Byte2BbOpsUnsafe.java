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

    public ByteBuffer storeAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putByte(address, self.x);
        U.putByte(address + 1L, self.y);
        return buf;
    }
    public Byte2 loadAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = U.getByte(address);
        self.y = U.getByte(address + 1L);
        return self;
    }
    public ShortBuffer storeAbsolute(Byte2Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 2L;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        return buf;
    }
    public Byte2 loadAbsolute(Byte2Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 2L;
        self.x = (byte) U.getShort(address);
        self.y = (byte) U.getShort(address + 2L);
        return self;
    }
    public ByteBuffer storeShortAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeShortAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        return buf;
    }
    public Byte2 loadShortAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadShortAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = (byte) U.getShort(address);
        self.y = (byte) U.getShort(address + 2L);
        return self;
    }
}
