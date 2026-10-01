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

public final class IntRectBbOpsUnsafe extends IntRectBbOps {
    private static final IntRectBbOpsApi API = new IntRectBbOpsApi();

    public IntBuffer storeAbsolute(IntRect self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        U.putInt(address, self.minX());
        U.putInt(address + 4L, self.minY());
        U.putInt(address + 8L, self.maxX());
        U.putInt(address + 12L, self.maxY());
        return buf;
    }

    public IntRect loadAbsolute(int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        int _c0 = _U.getInt(address);
        int _c1 = _U.getInt(address + 4L);
        int _c2 = _U.getInt(address + 8L);
        int _c3 = _U.getInt(address + 12L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    public ByteBuffer storeAbsolute(IntRect self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putInt(address, self.minX());
        U.putInt(address + 4L, self.minY());
        U.putInt(address + 8L, self.maxX());
        U.putInt(address + 12L, self.maxY());
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(IntRect self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putInt(bytes, address, self.minX());
        U.putInt(bytes, address + 4L, self.minY());
        U.putInt(bytes, address + 8L, self.maxX());
        U.putInt(bytes, address + 12L, self.maxY());
        return buf;
    }

    public IntRect loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        int _c0 = _U.getInt(address);
        int _c1 = _U.getInt(address + 4L);
        int _c2 = _U.getInt(address + 8L);
        int _c3 = _U.getInt(address + 12L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    private static IntRect loadAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        int _c0 = _U.getInt(bytes, address);
        int _c1 = _U.getInt(bytes, address + 4L);
        int _c2 = _U.getInt(bytes, address + 8L);
        int _c3 = _U.getInt(bytes, address + 12L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    public LongBuffer storeAbsolute(IntRect self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        U.putLong(address, self.minX());
        U.putLong(address + 8L, self.minY());
        U.putLong(address + 16L, self.maxX());
        U.putLong(address + 24L, self.maxY());
        return buf;
    }

    public IntRect loadAbsolute(int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        int _c0 = (int) _U.getLong(address);
        int _c1 = (int) _U.getLong(address + 8L);
        int _c2 = (int) _U.getLong(address + 16L);
        int _c3 = (int) _U.getLong(address + 24L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    public ByteBuffer storeLongAbsolute(IntRect self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeLongAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putLong(address, self.minX());
        U.putLong(address + 8L, self.minY());
        U.putLong(address + 16L, self.maxX());
        U.putLong(address + 24L, self.maxY());
        return buf;
    }

    private static ByteBuffer storeLongAbsoluteElse(IntRect self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeLongAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putLong(bytes, address, self.minX());
        U.putLong(bytes, address + 8L, self.minY());
        U.putLong(bytes, address + 16L, self.maxX());
        U.putLong(bytes, address + 24L, self.maxY());
        return buf;
    }

    public IntRect loadLongAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadLongAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        int _c0 = (int) _U.getLong(address);
        int _c1 = (int) _U.getLong(address + 8L);
        int _c2 = (int) _U.getLong(address + 16L);
        int _c3 = (int) _U.getLong(address + 24L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    private static IntRect loadLongAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadLongAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        int _c0 = (int) _U.getLong(bytes, address);
        int _c1 = (int) _U.getLong(bytes, address + 8L);
        int _c2 = (int) _U.getLong(bytes, address + 16L);
        int _c3 = (int) _U.getLong(bytes, address + 24L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
}
