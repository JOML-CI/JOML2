// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long4BbOpsUnsafe extends Long4BbOps {
    private static final Long4BbOpsApi API = new Long4BbOpsApi();

    public LongBuffer storeAbsolute(Long4 self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return buf;
    }

    public Long4 loadAbsolute(int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _c0 = _U.getLong(address);
        long _c1 = _U.getLong(address + 8L);
        long _c2 = _U.getLong(address + 16L);
        long _c3 = _U.getLong(address + 24L);
        return new Long4(_c0, _c1, _c2, _c3);
    }

    public ByteBuffer storeAbsolute(Long4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(Long4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putLong(bytes, address, self.x());
        U.putLong(bytes, address + 8L, self.y());
        U.putLong(bytes, address + 16L, self.z());
        U.putLong(bytes, address + 24L, self.w());
        return buf;
    }

    public Long4 loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _c0 = _U.getLong(address);
        long _c1 = _U.getLong(address + 8L);
        long _c2 = _U.getLong(address + 16L);
        long _c3 = _U.getLong(address + 24L);
        return new Long4(_c0, _c1, _c2, _c3);
    }

    private static Long4 loadAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _c0 = _U.getLong(bytes, address);
        long _c1 = _U.getLong(bytes, address + 8L);
        long _c2 = _U.getLong(bytes, address + 16L);
        long _c3 = _U.getLong(bytes, address + 24L);
        return new Long4(_c0, _c1, _c2, _c3);
    }

    public IntBuffer storeAbsolute(Long4 self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        U.putInt(address, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        U.putInt(address + 8L, (int) self.z());
        U.putInt(address + 12L, (int) self.w());
        return buf;
    }

    public Long4 loadAbsolute(int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _c0 = _U.getInt(address);
        long _c1 = _U.getInt(address + 4L);
        long _c2 = _U.getInt(address + 8L);
        long _c3 = _U.getInt(address + 12L);
        return new Long4(_c0, _c1, _c2, _c3);
    }

    public ByteBuffer storeIntAbsolute(Long4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeIntAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putInt(address, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        U.putInt(address + 8L, (int) self.z());
        U.putInt(address + 12L, (int) self.w());
        return buf;
    }

    private static ByteBuffer storeIntAbsoluteElse(Long4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeIntAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putInt(bytes, address, (int) self.x());
        U.putInt(bytes, address + 4L, (int) self.y());
        U.putInt(bytes, address + 8L, (int) self.z());
        U.putInt(bytes, address + 12L, (int) self.w());
        return buf;
    }

    public Long4 loadIntAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadIntAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _c0 = _U.getInt(address);
        long _c1 = _U.getInt(address + 4L);
        long _c2 = _U.getInt(address + 8L);
        long _c3 = _U.getInt(address + 12L);
        return new Long4(_c0, _c1, _c2, _c3);
    }

    private static Long4 loadIntAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadIntAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _c0 = _U.getInt(bytes, address);
        long _c1 = _U.getInt(bytes, address + 4L);
        long _c2 = _U.getInt(bytes, address + 8L);
        long _c3 = _U.getInt(bytes, address + 12L);
        return new Long4(_c0, _c1, _c2, _c3);
    }
}
