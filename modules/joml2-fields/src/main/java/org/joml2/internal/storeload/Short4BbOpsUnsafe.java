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

public final class Short4BbOpsUnsafe extends Short4BbOps {
    private static final Short4BbOpsApi API = new Short4BbOpsApi();

    public ShortBuffer storeAbsolute(Short4Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        U.putShort(address + 4L, self.z);
        U.putShort(address + 6L, self.w);
        return buf;
    }

    public Short4 loadAbsolute(Short4Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        self.x = U.getShort(address);
        self.y = U.getShort(address + 2L);
        self.z = U.getShort(address + 4L);
        self.w = U.getShort(address + 6L);
        return self;
    }

    public ByteBuffer storeAbsolute(Short4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        U.putShort(address + 4L, self.z);
        U.putShort(address + 6L, self.w);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(Short4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(bytes, address, self.x);
        U.putShort(bytes, address + 2L, self.y);
        U.putShort(bytes, address + 4L, self.z);
        U.putShort(bytes, address + 6L, self.w);
        return buf;
    }

    public Short4 loadAbsolute(Short4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getShort(address);
        self.y = U.getShort(address + 2L);
        self.z = U.getShort(address + 4L);
        self.w = U.getShort(address + 6L);
        return self;
    }

    private static Short4 loadAbsoluteElse(Short4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getShort(bytes, address);
        self.y = U.getShort(bytes, address + 2L);
        self.z = U.getShort(bytes, address + 4L);
        self.w = U.getShort(bytes, address + 6L);
        return self;
    }

    public ByteBuffer storeByteAbsolute(Short4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeByteAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(address, (byte) self.x);
        U.putByte(address + 1L, (byte) self.y);
        U.putByte(address + 2L, (byte) self.z);
        U.putByte(address + 3L, (byte) self.w);
        return buf;
    }

    private static ByteBuffer storeByteAbsoluteElse(Short4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeByteAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(bytes, address, (byte) self.x);
        U.putByte(bytes, address + 1L, (byte) self.y);
        U.putByte(bytes, address + 2L, (byte) self.z);
        U.putByte(bytes, address + 3L, (byte) self.w);
        return buf;
    }

    public Short4 loadByteAbsolute(Short4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadByteAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getByte(address);
        self.y = U.getByte(address + 1L);
        self.z = U.getByte(address + 2L);
        self.w = U.getByte(address + 3L);
        return self;
    }

    private static Short4 loadByteAbsoluteElse(Short4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadByteAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getByte(bytes, address);
        self.y = U.getByte(bytes, address + 1L);
        self.z = U.getByte(bytes, address + 2L);
        self.w = U.getByte(bytes, address + 3L);
        return self;
    }
}
