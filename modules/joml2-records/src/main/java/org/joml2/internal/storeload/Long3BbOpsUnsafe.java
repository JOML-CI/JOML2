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

public final class Long3BbOpsUnsafe extends Long3BbOps {

    private static final Long3BbOpsApi API = new Long3BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public LongBuffer storeAbsolute(Long3 self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        return buf;
    }
    public Long3 loadAbsolute(int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _c0 = U.getLong(address);
        long _c1 = U.getLong(address + 8L);
        long _c2 = U.getLong(address + 16L);
        return new Long3(_c0, _c1, _c2);
    }
    public ByteBuffer storeAbsolute(Long3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        return buf;
    }
    public Long3 loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _c0 = U.getLong(address);
        long _c1 = U.getLong(address + 8L);
        long _c2 = U.getLong(address + 16L);
        return new Long3(_c0, _c1, _c2);
    }
    public IntBuffer storeAbsolute(Long3 self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putInt(address, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        U.putInt(address + 8L, (int) self.z());
        return buf;
    }
    public Long3 loadAbsolute(int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _c0 = U.getInt(address);
        long _c1 = U.getInt(address + 4L);
        long _c2 = U.getInt(address + 8L);
        return new Long3(_c0, _c1, _c2);
    }
    public ByteBuffer storeIntAbsolute(Long3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeIntAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putInt(address, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        U.putInt(address + 8L, (int) self.z());
        return buf;
    }
    public Long3 loadIntAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadIntAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _c0 = U.getInt(address);
        long _c1 = U.getInt(address + 4L);
        long _c2 = U.getInt(address + 8L);
        return new Long3(_c0, _c1, _c2);
    }
}
