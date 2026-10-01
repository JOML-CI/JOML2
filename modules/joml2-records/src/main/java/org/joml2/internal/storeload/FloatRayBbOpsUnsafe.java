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

public final class FloatRayBbOpsUnsafe extends FloatRayBbOps {

    private static final FloatRayBbOpsApi API = new FloatRayBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeAbsolute(FloatRay self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.oX());
        U.putFloat(address + 4L, self.oY());
        U.putFloat(address + 8L, self.oZ());
        U.putFloat(address + 12L, self.dX());
        U.putFloat(address + 16L, self.dY());
        U.putFloat(address + 20L, self.dZ());
        return buf;
    }
    public FloatRay loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeAbsolute(FloatRay self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.oX());
        U.putFloat(address + 4L, self.oY());
        U.putFloat(address + 8L, self.oZ());
        U.putFloat(address + 12L, self.dX());
        U.putFloat(address + 16L, self.dY());
        U.putFloat(address + 20L, self.dZ());
        return buf;
    }
    public FloatRay loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public DoubleBuffer storeAbsolute(FloatRay self, int index, DoubleBuffer buf) {
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
    public FloatRay loadAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeDoubleAbsolute(FloatRay self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.oX());
        U.putDouble(address + 8L, self.oY());
        U.putDouble(address + 16L, self.oZ());
        U.putDouble(address + 24L, self.dX());
        U.putDouble(address + 32L, self.dY());
        U.putDouble(address + 40L, self.dZ());
        return buf;
    }
    public FloatRay loadDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
