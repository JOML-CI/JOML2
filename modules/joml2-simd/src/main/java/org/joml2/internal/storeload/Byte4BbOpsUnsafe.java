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

public final class Byte4BbOpsUnsafe extends Byte4BbOps {
    private static final Byte4BbOpsApi API = new Byte4BbOpsApi();

    public ByteBuffer storeAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(address, self.data[0]);
        U.putByte(address + 1L, self.data[1]);
        U.putByte(address + 2L, self.data[2]);
        U.putByte(address + 3L, self.data[3]);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(Byte4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(bytes, address, self.data[0]);
        U.putByte(bytes, address + 1L, self.data[1]);
        U.putByte(bytes, address + 2L, self.data[2]);
        U.putByte(bytes, address + 3L, self.data[3]);
        return buf;
    }

    public Byte4 loadAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getByte(address);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        self.data[3] = U.getByte(address + 3L);
        return self;
    }

    private static Byte4 loadAbsoluteElse(Byte4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getByte(bytes, address);
        self.data[1] = U.getByte(bytes, address + 1L);
        self.data[2] = U.getByte(bytes, address + 2L);
        self.data[3] = U.getByte(bytes, address + 3L);
        return self;
    }

    public ShortBuffer storeAbsolute(Byte4Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        U.putShort(address, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        U.putShort(address + 6L, self.data[3]);
        return buf;
    }

    public Byte4 loadAbsolute(Byte4Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        self.data[0] = (byte) U.getShort(address);
        self.data[1] = (byte) U.getShort(address + 2L);
        self.data[2] = (byte) U.getShort(address + 4L);
        self.data[3] = (byte) U.getShort(address + 6L);
        return self;
    }

    public ByteBuffer storeShortAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeShortAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(address, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        U.putShort(address + 6L, self.data[3]);
        return buf;
    }

    private static ByteBuffer storeShortAbsoluteElse(Byte4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeShortAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(bytes, address, self.data[0]);
        U.putShort(bytes, address + 2L, self.data[1]);
        U.putShort(bytes, address + 4L, self.data[2]);
        U.putShort(bytes, address + 6L, self.data[3]);
        return buf;
    }

    public Byte4 loadShortAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadShortAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = (byte) U.getShort(address);
        self.data[1] = (byte) U.getShort(address + 2L);
        self.data[2] = (byte) U.getShort(address + 4L);
        self.data[3] = (byte) U.getShort(address + 6L);
        return self;
    }

    private static Byte4 loadShortAbsoluteElse(Byte4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadShortAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = (byte) U.getShort(bytes, address);
        self.data[1] = (byte) U.getShort(bytes, address + 2L);
        self.data[2] = (byte) U.getShort(bytes, address + 4L);
        self.data[3] = (byte) U.getShort(bytes, address + 6L);
        return self;
    }
}
