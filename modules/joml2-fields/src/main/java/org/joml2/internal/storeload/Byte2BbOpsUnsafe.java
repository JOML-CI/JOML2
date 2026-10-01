// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte2BbOpsUnsafe extends Byte2BbOps {
    private static final Byte2BbOpsApi API = new Byte2BbOpsApi();

    public ByteBuffer storeAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(address, self.x);
        U.putByte(address + 1L, self.y);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(Byte2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(bytes, address, self.x);
        U.putByte(bytes, address + 1L, self.y);
        return buf;
    }

    public Byte2 loadAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getByte(address);
        self.y = U.getByte(address + 1L);
        return self;
    }

    private static Byte2 loadAbsoluteElse(Byte2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getByte(bytes, address);
        self.y = U.getByte(bytes, address + 1L);
        return self;
    }

    public ShortBuffer storeAbsolute(Byte2Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        return buf;
    }

    public Byte2 loadAbsolute(Byte2Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        self.x = (byte) U.getShort(address);
        self.y = (byte) U.getShort(address + 2L);
        return self;
    }

    public ByteBuffer storeShortAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeShortAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        return buf;
    }

    private static ByteBuffer storeShortAbsoluteElse(Byte2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeShortAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(bytes, address, self.x);
        U.putShort(bytes, address + 2L, self.y);
        return buf;
    }

    public Byte2 loadShortAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadShortAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = (byte) U.getShort(address);
        self.y = (byte) U.getShort(address + 2L);
        return self;
    }

    private static Byte2 loadShortAbsoluteElse(Byte2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadShortAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = (byte) U.getShort(bytes, address);
        self.y = (byte) U.getShort(bytes, address + 2L);
        return self;
    }
}
