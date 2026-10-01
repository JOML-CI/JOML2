// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int3BbOpsUnsafe extends Int3BbOps {

    private static final Int3BbOpsApi API = new Int3BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public IntBuffer storeAbsolute(Int3Impl self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putInt(address, self.x);
        U.putInt(address + 4L, self.y);
        U.putInt(address + 8L, self.z);
        return buf;
    }
    public Int3 loadAbsolute(Int3Impl self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        self.z = U.getInt(address + 8L);
        return self;
    }
    public ByteBuffer storeAbsolute(Int3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putInt(address, self.x);
        U.putInt(address + 4L, self.y);
        U.putInt(address + 8L, self.z);
        return buf;
    }
    public Int3 loadAbsolute(Int3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        self.z = U.getInt(address + 8L);
        return self;
    }
    public LongBuffer storeAbsolute(Int3Impl self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        U.putLong(address + 16L, self.z);
        return buf;
    }
    public Int3 loadAbsolute(Int3Impl self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.x = (int) U.getLong(address);
        self.y = (int) U.getLong(address + 8L);
        self.z = (int) U.getLong(address + 16L);
        return self;
    }
    public ByteBuffer storeLongAbsolute(Int3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeLongAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        U.putLong(address + 16L, self.z);
        return buf;
    }
    public Int3 loadLongAbsolute(Int3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadLongAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = (int) U.getLong(address);
        self.y = (int) U.getLong(address + 8L);
        self.z = (int) U.getLong(address + 16L);
        return self;
    }
}
