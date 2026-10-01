// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte4BbOpsUnsafe extends Byte4BbOps {
    private static final Byte4BbOpsApi API = new Byte4BbOpsApi();

    public ByteBuffer storeAbsolute(Byte4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(address, self.x());
        U.putByte(address + 1L, self.y());
        U.putByte(address + 2L, self.z());
        U.putByte(address + 3L, self.w());
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(Byte4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(bytes, address, self.x());
        U.putByte(bytes, address + 1L, self.y());
        U.putByte(bytes, address + 2L, self.z());
        U.putByte(bytes, address + 3L, self.w());
        return buf;
    }

    public Byte4 loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        byte _c0 = _U.getByte(address);
        byte _c1 = _U.getByte(address + 1L);
        byte _c2 = _U.getByte(address + 2L);
        byte _c3 = _U.getByte(address + 3L);
        return new Byte4(_c0, _c1, _c2, _c3);
    }

    private static Byte4 loadAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        byte _c0 = _U.getByte(bytes, address);
        byte _c1 = _U.getByte(bytes, address + 1L);
        byte _c2 = _U.getByte(bytes, address + 2L);
        byte _c3 = _U.getByte(bytes, address + 3L);
        return new Byte4(_c0, _c1, _c2, _c3);
    }

    public ShortBuffer storeAbsolute(Byte4 self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        U.putShort(address + 6L, self.w());
        return buf;
    }

    public Byte4 loadAbsolute(int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        byte _c0 = (byte) _U.getShort(address);
        byte _c1 = (byte) _U.getShort(address + 2L);
        byte _c2 = (byte) _U.getShort(address + 4L);
        byte _c3 = (byte) _U.getShort(address + 6L);
        return new Byte4(_c0, _c1, _c2, _c3);
    }

    public ByteBuffer storeShortAbsolute(Byte4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeShortAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        U.putShort(address + 6L, self.w());
        return buf;
    }

    private static ByteBuffer storeShortAbsoluteElse(Byte4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeShortAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(bytes, address, self.x());
        U.putShort(bytes, address + 2L, self.y());
        U.putShort(bytes, address + 4L, self.z());
        U.putShort(bytes, address + 6L, self.w());
        return buf;
    }

    public Byte4 loadShortAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadShortAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        byte _c0 = (byte) _U.getShort(address);
        byte _c1 = (byte) _U.getShort(address + 2L);
        byte _c2 = (byte) _U.getShort(address + 4L);
        byte _c3 = (byte) _U.getShort(address + 6L);
        return new Byte4(_c0, _c1, _c2, _c3);
    }

    private static Byte4 loadShortAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadShortAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        byte _c0 = (byte) _U.getShort(bytes, address);
        byte _c1 = (byte) _U.getShort(bytes, address + 2L);
        byte _c2 = (byte) _U.getShort(bytes, address + 4L);
        byte _c3 = (byte) _U.getShort(bytes, address + 6L);
        return new Byte4(_c0, _c1, _c2, _c3);
    }
}
