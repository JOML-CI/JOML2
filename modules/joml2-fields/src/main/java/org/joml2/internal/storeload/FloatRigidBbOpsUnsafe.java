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

public final class FloatRigidBbOpsUnsafe extends FloatRigidBbOps {

    private static final FloatRigidBbOpsApi API = new FloatRigidBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeAbsolute(FloatRigidImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.tX);
        U.putFloat(address + 4L, self.tY);
        U.putFloat(address + 8L, self.tZ);
        U.putFloat(address + 12L, self.rX);
        U.putFloat(address + 16L, self.rY);
        U.putFloat(address + 20L, self.rZ);
        U.putFloat(address + 24L, self.rW);
        return buf;
    }
    public FloatRigid loadAbsolute(FloatRigidImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.tX = U.getFloat(address);
        self.tY = U.getFloat(address + 4L);
        self.tZ = U.getFloat(address + 8L);
        self.rX = U.getFloat(address + 12L);
        self.rY = U.getFloat(address + 16L);
        self.rZ = U.getFloat(address + 20L);
        self.rW = U.getFloat(address + 24L);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.tX);
        U.putFloat(address + 4L, self.tY);
        U.putFloat(address + 8L, self.tZ);
        U.putFloat(address + 12L, self.rX);
        U.putFloat(address + 16L, self.rY);
        U.putFloat(address + 20L, self.rZ);
        U.putFloat(address + 24L, self.rW);
        return buf;
    }
    public FloatRigid loadAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.tX = U.getFloat(address);
        self.tY = U.getFloat(address + 4L);
        self.tZ = U.getFloat(address + 8L);
        self.rX = U.getFloat(address + 12L);
        self.rY = U.getFloat(address + 16L);
        self.rZ = U.getFloat(address + 20L);
        self.rW = U.getFloat(address + 24L);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatRigidImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.tX);
        U.putDouble(address + 8L, self.tY);
        U.putDouble(address + 16L, self.tZ);
        U.putDouble(address + 24L, self.rX);
        U.putDouble(address + 32L, self.rY);
        U.putDouble(address + 40L, self.rZ);
        U.putDouble(address + 48L, self.rW);
        return buf;
    }
    public FloatRigid loadAbsolute(FloatRigidImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.tX = (float) U.getDouble(address);
        self.tY = (float) U.getDouble(address + 8L);
        self.tZ = (float) U.getDouble(address + 16L);
        self.rX = (float) U.getDouble(address + 24L);
        self.rY = (float) U.getDouble(address + 32L);
        self.rZ = (float) U.getDouble(address + 40L);
        self.rW = (float) U.getDouble(address + 48L);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.tX);
        U.putDouble(address + 8L, self.tY);
        U.putDouble(address + 16L, self.tZ);
        U.putDouble(address + 24L, self.rX);
        U.putDouble(address + 32L, self.rY);
        U.putDouble(address + 40L, self.rZ);
        U.putDouble(address + 48L, self.rW);
        return buf;
    }
    public FloatRigid loadDoubleAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.tX = (float) U.getDouble(address);
        self.tY = (float) U.getDouble(address + 8L);
        self.tZ = (float) U.getDouble(address + 16L);
        self.rX = (float) U.getDouble(address + 24L);
        self.rY = (float) U.getDouble(address + 32L);
        self.rZ = (float) U.getDouble(address + 40L);
        self.rW = (float) U.getDouble(address + 48L);
        return self;
    }
}
