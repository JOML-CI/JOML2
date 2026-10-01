// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
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

    public DoubleBuffer storeAbsolute(DoubleRect self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.minX());
        U.putDouble(address + 8L, self.minY());
        U.putDouble(address + 16L, self.maxX());
        U.putDouble(address + 24L, self.maxY());
        return buf;
    }
    public DoubleRect loadAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(DoubleRect self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.minX());
        U.putDouble(address + 8L, self.minY());
        U.putDouble(address + 16L, self.maxX());
        U.putDouble(address + 24L, self.maxY());
        return buf;
    }
    public DoubleRect loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }
    public FloatBuffer storeAbsolute(DoubleRect self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.minX());
        U.putFloat(address + 4L, (float) self.minY());
        U.putFloat(address + 8L, (float) self.maxX());
        U.putFloat(address + 12L, (float) self.maxY());
        return buf;
    }
    public DoubleRect loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeFloatAbsolute(DoubleRect self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.minX());
        U.putFloat(address + 4L, (float) self.minY());
        U.putFloat(address + 8L, (float) self.maxX());
        U.putFloat(address + 12L, (float) self.maxY());
        return buf;
    }
    public DoubleRect loadFloatAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadFloatAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }
}
