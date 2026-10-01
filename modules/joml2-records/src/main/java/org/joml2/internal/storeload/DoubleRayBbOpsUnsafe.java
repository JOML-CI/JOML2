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

public final class DoubleRayBbOpsUnsafe extends DoubleRayBbOps {

    private static final DoubleRayBbOpsApi API = new DoubleRayBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DoubleBuffer storeAbsolute(DoubleRay self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.oX());
        U.putDouble(address + 8L, self.oY());
        U.putDouble(address + 16L, self.oZ());
        U.putDouble(address + 24L, self.dX());
        U.putDouble(address + 32L, self.dY());
        U.putDouble(address + 40L, self.dZ());
        return buf;
    }
    public DoubleRay loadAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeAbsolute(DoubleRay self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.oX());
        U.putDouble(address + 8L, self.oY());
        U.putDouble(address + 16L, self.oZ());
        U.putDouble(address + 24L, self.dX());
        U.putDouble(address + 32L, self.dY());
        U.putDouble(address + 40L, self.dZ());
        return buf;
    }
    public DoubleRay loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public FloatBuffer storeAbsolute(DoubleRay self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.oX());
        U.putFloat(address + 4L, (float) self.oY());
        U.putFloat(address + 8L, (float) self.oZ());
        U.putFloat(address + 12L, (float) self.dX());
        U.putFloat(address + 16L, (float) self.dY());
        U.putFloat(address + 20L, (float) self.dZ());
        return buf;
    }
    public DoubleRay loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeFloatAbsolute(DoubleRay self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.oX());
        U.putFloat(address + 4L, (float) self.oY());
        U.putFloat(address + 8L, (float) self.oZ());
        U.putFloat(address + 12L, (float) self.dX());
        U.putFloat(address + 16L, (float) self.dY());
        U.putFloat(address + 20L, (float) self.dZ());
        return buf;
    }
    public DoubleRay loadFloatAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadFloatAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
