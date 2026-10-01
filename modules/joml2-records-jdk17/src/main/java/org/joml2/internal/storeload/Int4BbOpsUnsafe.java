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

public final class Int4BbOpsUnsafe extends Int4BbOps {
    private static final Int4BbOpsApi API = new Int4BbOpsApi();

    public IntBuffer storeAbsolute(Int4 self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        U.putInt(address, self.x());
        U.putInt(address + 4L, self.y());
        U.putInt(address + 8L, self.z());
        U.putInt(address + 12L, self.w());
        return buf;
    }

    public Int4 loadAbsolute(int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        int _c0 = _U.getInt(address);
        int _c1 = _U.getInt(address + 4L);
        int _c2 = _U.getInt(address + 8L);
        int _c3 = _U.getInt(address + 12L);
        return new Int4(_c0, _c1, _c2, _c3);
    }

    public ByteBuffer storeAbsolute(Int4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putInt(address, self.x());
        U.putInt(address + 4L, self.y());
        U.putInt(address + 8L, self.z());
        U.putInt(address + 12L, self.w());
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(Int4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putInt(bytes, address, self.x());
        U.putInt(bytes, address + 4L, self.y());
        U.putInt(bytes, address + 8L, self.z());
        U.putInt(bytes, address + 12L, self.w());
        return buf;
    }

    public Int4 loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        int _c0 = _U.getInt(address);
        int _c1 = _U.getInt(address + 4L);
        int _c2 = _U.getInt(address + 8L);
        int _c3 = _U.getInt(address + 12L);
        return new Int4(_c0, _c1, _c2, _c3);
    }

    private static Int4 loadAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        int _c0 = _U.getInt(bytes, address);
        int _c1 = _U.getInt(bytes, address + 4L);
        int _c2 = _U.getInt(bytes, address + 8L);
        int _c3 = _U.getInt(bytes, address + 12L);
        return new Int4(_c0, _c1, _c2, _c3);
    }

    public LongBuffer storeAbsolute(Int4 self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return buf;
    }

    public Int4 loadAbsolute(int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        int _c0 = (int) _U.getLong(address);
        int _c1 = (int) _U.getLong(address + 8L);
        int _c2 = (int) _U.getLong(address + 16L);
        int _c3 = (int) _U.getLong(address + 24L);
        return new Int4(_c0, _c1, _c2, _c3);
    }

    public ByteBuffer storeLongAbsolute(Int4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeLongAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return buf;
    }

    private static ByteBuffer storeLongAbsoluteElse(Int4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeLongAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putLong(bytes, address, self.x());
        U.putLong(bytes, address + 8L, self.y());
        U.putLong(bytes, address + 16L, self.z());
        U.putLong(bytes, address + 24L, self.w());
        return buf;
    }

    public Int4 loadLongAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadLongAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        int _c0 = (int) _U.getLong(address);
        int _c1 = (int) _U.getLong(address + 8L);
        int _c2 = (int) _U.getLong(address + 16L);
        int _c3 = (int) _U.getLong(address + 24L);
        return new Int4(_c0, _c1, _c2, _c3);
    }

    private static Int4 loadLongAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadLongAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        int _c0 = (int) _U.getLong(bytes, address);
        int _c1 = (int) _U.getLong(bytes, address + 8L);
        int _c2 = (int) _U.getLong(bytes, address + 16L);
        int _c3 = (int) _U.getLong(bytes, address + 24L);
        return new Int4(_c0, _c1, _c2, _c3);
    }
}
