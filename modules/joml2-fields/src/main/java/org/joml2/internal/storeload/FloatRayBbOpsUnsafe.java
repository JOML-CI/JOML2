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

    public FloatBuffer storeAbsolute(FloatRayImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.oX);
        U.putFloat(address + 4L, self.oY);
        U.putFloat(address + 8L, self.oZ);
        U.putFloat(address + 12L, self.dX);
        U.putFloat(address + 16L, self.dY);
        U.putFloat(address + 20L, self.dZ);
        return buf;
    }
    public FloatRay loadAbsolute(FloatRayImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.oX = U.getFloat(address);
        self.oY = U.getFloat(address + 4L);
        self.oZ = U.getFloat(address + 8L);
        self.dX = U.getFloat(address + 12L);
        self.dY = U.getFloat(address + 16L);
        self.dZ = U.getFloat(address + 20L);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.oX);
        U.putFloat(address + 4L, self.oY);
        U.putFloat(address + 8L, self.oZ);
        U.putFloat(address + 12L, self.dX);
        U.putFloat(address + 16L, self.dY);
        U.putFloat(address + 20L, self.dZ);
        return buf;
    }
    public FloatRay loadAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.oX = U.getFloat(address);
        self.oY = U.getFloat(address + 4L);
        self.oZ = U.getFloat(address + 8L);
        self.dX = U.getFloat(address + 12L);
        self.dY = U.getFloat(address + 16L);
        self.dZ = U.getFloat(address + 20L);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatRayImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.oX);
        U.putDouble(address + 8L, self.oY);
        U.putDouble(address + 16L, self.oZ);
        U.putDouble(address + 24L, self.dX);
        U.putDouble(address + 32L, self.dY);
        U.putDouble(address + 40L, self.dZ);
        return buf;
    }
    public FloatRay loadAbsolute(FloatRayImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.oX = (float) U.getDouble(address);
        self.oY = (float) U.getDouble(address + 8L);
        self.oZ = (float) U.getDouble(address + 16L);
        self.dX = (float) U.getDouble(address + 24L);
        self.dY = (float) U.getDouble(address + 32L);
        self.dZ = (float) U.getDouble(address + 40L);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.oX);
        U.putDouble(address + 8L, self.oY);
        U.putDouble(address + 16L, self.oZ);
        U.putDouble(address + 24L, self.dX);
        U.putDouble(address + 32L, self.dY);
        U.putDouble(address + 40L, self.dZ);
        return buf;
    }
    public FloatRay loadDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.oX = (float) U.getDouble(address);
        self.oY = (float) U.getDouble(address + 8L);
        self.oZ = (float) U.getDouble(address + 16L);
        self.dX = (float) U.getDouble(address + 24L);
        self.dY = (float) U.getDouble(address + 32L);
        self.dZ = (float) U.getDouble(address + 40L);
        return self;
    }
}
