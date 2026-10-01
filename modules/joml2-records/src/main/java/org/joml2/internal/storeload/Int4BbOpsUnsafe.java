// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int4BbOpsUnsafe extends Int4BbOps {

    private static final Int4BbOpsApi API = new Int4BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public IntBuffer storeAbsolute(Int4 self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putInt(address, self.x());
        U.putInt(address + 4L, self.y());
        U.putInt(address + 8L, self.z());
        U.putInt(address + 12L, self.w());
        return buf;
    }
    public Int4 loadAbsolute(int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        int _c0 = U.getInt(address);
        int _c1 = U.getInt(address + 4L);
        int _c2 = U.getInt(address + 8L);
        int _c3 = U.getInt(address + 12L);
        return new Int4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(Int4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putInt(address, self.x());
        U.putInt(address + 4L, self.y());
        U.putInt(address + 8L, self.z());
        U.putInt(address + 12L, self.w());
        return buf;
    }
    public Int4 loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        int _c0 = U.getInt(address);
        int _c1 = U.getInt(address + 4L);
        int _c2 = U.getInt(address + 8L);
        int _c3 = U.getInt(address + 12L);
        return new Int4(_c0, _c1, _c2, _c3);
    }
    public LongBuffer storeAbsolute(Int4 self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return buf;
    }
    public Int4 loadAbsolute(int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        int _c0 = (int) U.getLong(address);
        int _c1 = (int) U.getLong(address + 8L);
        int _c2 = (int) U.getLong(address + 16L);
        int _c3 = (int) U.getLong(address + 24L);
        return new Int4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeLongAbsolute(Int4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeLongAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return buf;
    }
    public Int4 loadLongAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadLongAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        int _c0 = (int) U.getLong(address);
        int _c1 = (int) U.getLong(address + 8L);
        int _c2 = (int) U.getLong(address + 16L);
        int _c3 = (int) U.getLong(address + 24L);
        return new Int4(_c0, _c1, _c2, _c3);
    }
}
