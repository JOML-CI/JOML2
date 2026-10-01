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

public final class Short2BbOpsUnsafe extends Short2BbOps {
    private static final Short2BbOpsApi API = new Short2BbOpsApi();

    public ShortBuffer storeAbsolute(Short2Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        return buf;
    }

    public Short2 loadAbsolute(Short2Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 2L;
        self.x = U.getShort(address);
        self.y = U.getShort(address + 2L);
        return self;
    }

    public ByteBuffer storeAbsolute(Short2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(address, self.x);
        U.putShort(address + 2L, self.y);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(Short2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putShort(bytes, address, self.x);
        U.putShort(bytes, address + 2L, self.y);
        return buf;
    }

    public Short2 loadAbsolute(Short2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getShort(address);
        self.y = U.getShort(address + 2L);
        return self;
    }

    private static Short2 loadAbsoluteElse(Short2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getShort(bytes, address);
        self.y = U.getShort(bytes, address + 2L);
        return self;
    }

    public ByteBuffer storeByteAbsolute(Short2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeByteAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(address, (byte) self.x);
        U.putByte(address + 1L, (byte) self.y);
        return buf;
    }

    private static ByteBuffer storeByteAbsoluteElse(Short2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeByteAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putByte(bytes, address, (byte) self.x);
        U.putByte(bytes, address + 1L, (byte) self.y);
        return buf;
    }

    public Short2 loadByteAbsolute(Short2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadByteAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getByte(address);
        self.y = U.getByte(address + 1L);
        return self;
    }

    private static Short2 loadByteAbsoluteElse(Short2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadByteAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getByte(bytes, address);
        self.y = U.getByte(bytes, address + 1L);
        return self;
    }
}
