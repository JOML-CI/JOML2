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

public final class IntRectBbOpsUnsafe extends IntRectBbOps {

    private static final IntRectBbOpsApi API = new IntRectBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public IntBuffer storeAbsolute(IntRectImpl self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putInt(address, self.minX);
        U.putInt(address + 4L, self.minY);
        U.putInt(address + 8L, self.maxX);
        U.putInt(address + 12L, self.maxY);
        return buf;
    }
    public IntRect loadAbsolute(IntRectImpl self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.minX = U.getInt(address);
        self.minY = U.getInt(address + 4L);
        self.maxX = U.getInt(address + 8L);
        self.maxY = U.getInt(address + 12L);
        return self;
    }
    public ByteBuffer storeAbsolute(IntRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putInt(address, self.minX);
        U.putInt(address + 4L, self.minY);
        U.putInt(address + 8L, self.maxX);
        U.putInt(address + 12L, self.maxY);
        return buf;
    }
    public IntRect loadAbsolute(IntRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.minX = U.getInt(address);
        self.minY = U.getInt(address + 4L);
        self.maxX = U.getInt(address + 8L);
        self.maxY = U.getInt(address + 12L);
        return self;
    }
    public LongBuffer storeAbsolute(IntRectImpl self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putLong(address, self.minX);
        U.putLong(address + 8L, self.minY);
        U.putLong(address + 16L, self.maxX);
        U.putLong(address + 24L, self.maxY);
        return buf;
    }
    public IntRect loadAbsolute(IntRectImpl self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.minX = (int) U.getLong(address);
        self.minY = (int) U.getLong(address + 8L);
        self.maxX = (int) U.getLong(address + 16L);
        self.maxY = (int) U.getLong(address + 24L);
        return self;
    }
    public ByteBuffer storeLongAbsolute(IntRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeLongAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putLong(address, self.minX);
        U.putLong(address + 8L, self.minY);
        U.putLong(address + 16L, self.maxX);
        U.putLong(address + 24L, self.maxY);
        return buf;
    }
    public IntRect loadLongAbsolute(IntRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadLongAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.minX = (int) U.getLong(address);
        self.minY = (int) U.getLong(address + 8L);
        self.maxX = (int) U.getLong(address + 16L);
        self.maxY = (int) U.getLong(address + 24L);
        return self;
    }
}
