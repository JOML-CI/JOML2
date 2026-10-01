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

public final class DoublePlaneBbOpsUnsafe extends DoublePlaneBbOps {

    private static final DoublePlaneBbOpsApi API = new DoublePlaneBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DoubleBuffer storeAbsolute(DoublePlane self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.a());
        U.putDouble(address + 8L, self.b());
        U.putDouble(address + 16L, self.c());
        U.putDouble(address + 24L, self.d());
        return buf;
    }
    public DoublePlane loadAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(DoublePlane self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.a());
        U.putDouble(address + 8L, self.b());
        U.putDouble(address + 16L, self.c());
        U.putDouble(address + 24L, self.d());
        return buf;
    }
    public DoublePlane loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
    public FloatBuffer storeAbsolute(DoublePlane self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.a());
        U.putFloat(address + 4L, (float) self.b());
        U.putFloat(address + 8L, (float) self.c());
        U.putFloat(address + 12L, (float) self.d());
        return buf;
    }
    public DoublePlane loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeFloatAbsolute(DoublePlane self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.a());
        U.putFloat(address + 4L, (float) self.b());
        U.putFloat(address + 8L, (float) self.c());
        U.putFloat(address + 12L, (float) self.d());
        return buf;
    }
    public DoublePlane loadFloatAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadFloatAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
}
