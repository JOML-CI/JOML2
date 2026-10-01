// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRectBbOpsUnsafe extends DoubleRectBbOps {

    private static final DoubleRectBbOpsApi API = new DoubleRectBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DoubleBuffer storeAbsolute(DoubleRectImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.maxX);
        U.putDouble(address + 24L, self.maxY);
        return buf;
    }
    public DoubleRect loadAbsolute(DoubleRectImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.minX = U.getDouble(address);
        self.minY = U.getDouble(address + 8L);
        self.maxX = U.getDouble(address + 16L);
        self.maxY = U.getDouble(address + 24L);
        return self;
    }
    public ByteBuffer storeAbsolute(DoubleRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.maxX);
        U.putDouble(address + 24L, self.maxY);
        return buf;
    }
    public DoubleRect loadAbsolute(DoubleRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.minX = U.getDouble(address);
        self.minY = U.getDouble(address + 8L);
        self.maxX = U.getDouble(address + 16L);
        self.maxY = U.getDouble(address + 24L);
        return self;
    }
    public FloatBuffer storeAbsolute(DoubleRectImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.minX);
        U.putFloat(address + 4L, (float) self.minY);
        U.putFloat(address + 8L, (float) self.maxX);
        U.putFloat(address + 12L, (float) self.maxY);
        return buf;
    }
    public DoubleRect loadAbsolute(DoubleRectImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.maxX = U.getFloat(address + 8L);
        self.maxY = U.getFloat(address + 12L);
        return self;
    }
    public ByteBuffer storeFloatAbsolute(DoubleRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.minX);
        U.putFloat(address + 4L, (float) self.minY);
        U.putFloat(address + 8L, (float) self.maxX);
        U.putFloat(address + 12L, (float) self.maxY);
        return buf;
    }
    public DoubleRect loadFloatAbsolute(DoubleRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.maxX = U.getFloat(address + 8L);
        self.maxY = U.getFloat(address + 12L);
        return self;
    }
}
