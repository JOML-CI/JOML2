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

    public DoubleBuffer storeAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf) {
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
    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.oX = U.getDouble(address);
        self.oY = U.getDouble(address + 8L);
        self.oZ = U.getDouble(address + 16L);
        self.dX = U.getDouble(address + 24L);
        self.dY = U.getDouble(address + 32L);
        self.dZ = U.getDouble(address + 40L);
        return self;
    }
    public ByteBuffer storeAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.oX);
        U.putDouble(address + 8L, self.oY);
        U.putDouble(address + 16L, self.oZ);
        U.putDouble(address + 24L, self.dX);
        U.putDouble(address + 32L, self.dY);
        U.putDouble(address + 40L, self.dZ);
        return buf;
    }
    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.oX = U.getDouble(address);
        self.oY = U.getDouble(address + 8L);
        self.oZ = U.getDouble(address + 16L);
        self.dX = U.getDouble(address + 24L);
        self.dY = U.getDouble(address + 32L);
        self.dZ = U.getDouble(address + 40L);
        return self;
    }
    public FloatBuffer storeAbsolute(DoubleRayImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.oX);
        U.putFloat(address + 4L, (float) self.oY);
        U.putFloat(address + 8L, (float) self.oZ);
        U.putFloat(address + 12L, (float) self.dX);
        U.putFloat(address + 16L, (float) self.dY);
        U.putFloat(address + 20L, (float) self.dZ);
        return buf;
    }
    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, FloatBuffer buf) {
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
    public ByteBuffer storeFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.oX);
        U.putFloat(address + 4L, (float) self.oY);
        U.putFloat(address + 8L, (float) self.oZ);
        U.putFloat(address + 12L, (float) self.dX);
        U.putFloat(address + 16L, (float) self.dY);
        U.putFloat(address + 20L, (float) self.dZ);
        return buf;
    }
    public DoubleRay loadFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.oX = U.getFloat(address);
        self.oY = U.getFloat(address + 4L);
        self.oZ = U.getFloat(address + 8L);
        self.dX = U.getFloat(address + 12L);
        self.dY = U.getFloat(address + 16L);
        self.dZ = U.getFloat(address + 20L);
        return self;
    }
}
