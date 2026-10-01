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

    public FloatBuffer storeAbsolute(FloatRigid self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.tX());
        U.putFloat(address + 4L, self.tY());
        U.putFloat(address + 8L, self.tZ());
        U.putFloat(address + 12L, self.rX());
        U.putFloat(address + 16L, self.rY());
        U.putFloat(address + 20L, self.rZ());
        U.putFloat(address + 24L, self.rW());
        return buf;
    }
    public FloatRigid loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
    public ByteBuffer storeAbsolute(FloatRigid self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.tX());
        U.putFloat(address + 4L, self.tY());
        U.putFloat(address + 8L, self.tZ());
        U.putFloat(address + 12L, self.rX());
        U.putFloat(address + 16L, self.rY());
        U.putFloat(address + 20L, self.rZ());
        U.putFloat(address + 24L, self.rW());
        return buf;
    }
    public FloatRigid loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
    public DoubleBuffer storeAbsolute(FloatRigid self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.tX());
        U.putDouble(address + 8L, self.tY());
        U.putDouble(address + 16L, self.tZ());
        U.putDouble(address + 24L, self.rX());
        U.putDouble(address + 32L, self.rY());
        U.putDouble(address + 40L, self.rZ());
        U.putDouble(address + 48L, self.rW());
        return buf;
    }
    public FloatRigid loadAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
    public ByteBuffer storeDoubleAbsolute(FloatRigid self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.tX());
        U.putDouble(address + 8L, self.tY());
        U.putDouble(address + 16L, self.tZ());
        U.putDouble(address + 24L, self.rX());
        U.putDouble(address + 32L, self.rY());
        U.putDouble(address + 40L, self.rZ());
        U.putDouble(address + 48L, self.rW());
        return buf;
    }
    public FloatRigid loadDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
}
