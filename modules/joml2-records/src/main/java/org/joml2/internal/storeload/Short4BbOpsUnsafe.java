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

public final class Short4BbOpsUnsafe extends Short4BbOps {
    private static final Short4BbOpsApi API = new Short4BbOpsApi();

    public ShortBuffer storeAbsolute(Short4 self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        U.putShort(address + 6L, self.w());
        return buf;
    }

    public Short4 loadAbsolute(int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        short _c0 = _U.getShort(address);
        short _c1 = _U.getShort(address + 2L);
        short _c2 = _U.getShort(address + 4L);
        short _c3 = _U.getShort(address + 6L);
        return new Short4(_c0, _c1, _c2, _c3);
    }

    public ByteBuffer storeAbsolute(Short4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(address, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        U.putShort(address + 6L, self.w());
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(Short4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(bytes, address, self.x());
        U.putShort(bytes, address + 2L, self.y());
        U.putShort(bytes, address + 4L, self.z());
        U.putShort(bytes, address + 6L, self.w());
        return buf;
    }

    public Short4 loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        short _c0 = _U.getShort(address);
        short _c1 = _U.getShort(address + 2L);
        short _c2 = _U.getShort(address + 4L);
        short _c3 = _U.getShort(address + 6L);
        return new Short4(_c0, _c1, _c2, _c3);
    }

    private static Short4 loadAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        short _c0 = _U.getShort(bytes, address);
        short _c1 = _U.getShort(bytes, address + 2L);
        short _c2 = _U.getShort(bytes, address + 4L);
        short _c3 = _U.getShort(bytes, address + 6L);
        return new Short4(_c0, _c1, _c2, _c3);
    }

    public ByteBuffer storeByteAbsolute(Short4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeByteAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(address, (byte) self.x());
        U.putByte(address + 1L, (byte) self.y());
        U.putByte(address + 2L, (byte) self.z());
        U.putByte(address + 3L, (byte) self.w());
        return buf;
    }

    private static ByteBuffer storeByteAbsoluteElse(Short4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeByteAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(bytes, address, (byte) self.x());
        U.putByte(bytes, address + 1L, (byte) self.y());
        U.putByte(bytes, address + 2L, (byte) self.z());
        U.putByte(bytes, address + 3L, (byte) self.w());
        return buf;
    }

    public Short4 loadByteAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadByteAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        short _c0 = _U.getByte(address);
        short _c1 = _U.getByte(address + 1L);
        short _c2 = _U.getByte(address + 2L);
        short _c3 = _U.getByte(address + 3L);
        return new Short4(_c0, _c1, _c2, _c3);
    }

    private static Short4 loadByteAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadByteAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        short _c0 = _U.getByte(bytes, address);
        short _c1 = _U.getByte(bytes, address + 1L);
        short _c2 = _U.getByte(bytes, address + 2L);
        short _c3 = _U.getByte(bytes, address + 3L);
        return new Short4(_c0, _c1, _c2, _c3);
    }
}
