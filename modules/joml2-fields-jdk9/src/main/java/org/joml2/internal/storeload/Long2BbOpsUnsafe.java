// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long2BbOpsUnsafe extends Long2BbOps {
    private static final Long2BbOpsApi API = new Long2BbOpsApi();

    public LongBuffer storeAbsolute(Long2Impl self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        return buf;
    }

    public Long2 loadAbsolute(Long2Impl self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.x = U.getLong(address);
        self.y = U.getLong(address + 8L);
        return self;
    }

    public ByteBuffer storeAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(Long2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putLong(bytes, address, self.x);
        U.putLong(bytes, address + 8L, self.y);
        return buf;
    }

    public Long2 loadAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getLong(address);
        self.y = U.getLong(address + 8L);
        return self;
    }

    private static Long2 loadAbsoluteElse(Long2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getLong(bytes, address);
        self.y = U.getLong(bytes, address + 8L);
        return self;
    }

    public IntBuffer storeAbsolute(Long2Impl self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        U.putInt(address, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        return buf;
    }

    public Long2 loadAbsolute(Long2Impl self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        return self;
    }

    public ByteBuffer storeIntAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeIntAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putInt(address, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        return buf;
    }

    private static ByteBuffer storeIntAbsoluteElse(Long2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeIntAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putInt(bytes, address, (int) self.x);
        U.putInt(bytes, address + 4L, (int) self.y);
        return buf;
    }

    public Long2 loadIntAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadIntAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        return self;
    }

    private static Long2 loadIntAbsoluteElse(Long2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadIntAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getInt(bytes, address);
        self.y = U.getInt(bytes, address + 4L);
        return self;
    }
}
