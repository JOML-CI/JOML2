// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRigidBbOpsUnsafe extends FloatRigidBbOps {
    private static final FloatRigidBbOpsApi API = new FloatRigidBbOpsApi();

    public FloatBuffer storeAbsolute(FloatRigidImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.tX);
        _U.putFloat(address + 4L, self.tY);
        _U.putFloat(address + 8L, self.tZ);
        _U.putFloat(address + 12L, self.rX);
        _U.putFloat(address + 16L, self.rY);
        _U.putFloat(address + 20L, self.rZ);
        _U.putFloat(address + 24L, self.rW);
        return buf;
    }

    public FloatRigid loadAbsolute(FloatRigidImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.tX = _U.getFloat(address);
        self.tY = _U.getFloat(address + 4L);
        self.tZ = _U.getFloat(address + 8L);
        self.rX = _U.getFloat(address + 12L);
        self.rY = _U.getFloat(address + 16L);
        self.rZ = _U.getFloat(address + 20L);
        self.rW = _U.getFloat(address + 24L);
        return self;
    }

    public ByteBuffer storeAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.tX);
        _U.putFloat(address + 4L, self.tY);
        _U.putFloat(address + 8L, self.tZ);
        _U.putFloat(address + 12L, self.rX);
        _U.putFloat(address + 16L, self.rY);
        _U.putFloat(address + 20L, self.rZ);
        _U.putFloat(address + 24L, self.rW);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(FloatRigidImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.tX);
        _U.putFloat(bytes, address + 4L, self.tY);
        _U.putFloat(bytes, address + 8L, self.tZ);
        _U.putFloat(bytes, address + 12L, self.rX);
        _U.putFloat(bytes, address + 16L, self.rY);
        _U.putFloat(bytes, address + 20L, self.rZ);
        _U.putFloat(bytes, address + 24L, self.rW);
        return buf;
    }

    public FloatRigid loadAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.tX = _U.getFloat(address);
        self.tY = _U.getFloat(address + 4L);
        self.tZ = _U.getFloat(address + 8L);
        self.rX = _U.getFloat(address + 12L);
        self.rY = _U.getFloat(address + 16L);
        self.rZ = _U.getFloat(address + 20L);
        self.rW = _U.getFloat(address + 24L);
        return self;
    }

    private static FloatRigid loadAbsoluteElse(FloatRigidImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.tX = _U.getFloat(bytes, address);
        self.tY = _U.getFloat(bytes, address + 4L);
        self.tZ = _U.getFloat(bytes, address + 8L);
        self.rX = _U.getFloat(bytes, address + 12L);
        self.rY = _U.getFloat(bytes, address + 16L);
        self.rZ = _U.getFloat(bytes, address + 20L);
        self.rW = _U.getFloat(bytes, address + 24L);
        return self;
    }

    public DoubleBuffer storeAbsolute(FloatRigidImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.tX);
        _U.putDouble(address + 8L, self.tY);
        _U.putDouble(address + 16L, self.tZ);
        _U.putDouble(address + 24L, self.rX);
        _U.putDouble(address + 32L, self.rY);
        _U.putDouble(address + 40L, self.rZ);
        _U.putDouble(address + 48L, self.rW);
        return buf;
    }

    public FloatRigid loadAbsolute(FloatRigidImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.tX = (float) _U.getDouble(address);
        self.tY = (float) _U.getDouble(address + 8L);
        self.tZ = (float) _U.getDouble(address + 16L);
        self.rX = (float) _U.getDouble(address + 24L);
        self.rY = (float) _U.getDouble(address + 32L);
        self.rZ = (float) _U.getDouble(address + 40L);
        self.rW = (float) _U.getDouble(address + 48L);
        return self;
    }

    public ByteBuffer storeDoubleAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.tX);
        _U.putDouble(address + 8L, self.tY);
        _U.putDouble(address + 16L, self.tZ);
        _U.putDouble(address + 24L, self.rX);
        _U.putDouble(address + 32L, self.rY);
        _U.putDouble(address + 40L, self.rZ);
        _U.putDouble(address + 48L, self.rW);
        return buf;
    }

    private static ByteBuffer storeDoubleAbsoluteElse(FloatRigidImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.tX);
        _U.putDouble(bytes, address + 8L, self.tY);
        _U.putDouble(bytes, address + 16L, self.tZ);
        _U.putDouble(bytes, address + 24L, self.rX);
        _U.putDouble(bytes, address + 32L, self.rY);
        _U.putDouble(bytes, address + 40L, self.rZ);
        _U.putDouble(bytes, address + 48L, self.rW);
        return buf;
    }

    public FloatRigid loadDoubleAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.tX = (float) _U.getDouble(address);
        self.tY = (float) _U.getDouble(address + 8L);
        self.tZ = (float) _U.getDouble(address + 16L);
        self.rX = (float) _U.getDouble(address + 24L);
        self.rY = (float) _U.getDouble(address + 32L);
        self.rZ = (float) _U.getDouble(address + 40L);
        self.rW = (float) _U.getDouble(address + 48L);
        return self;
    }

    private static FloatRigid loadDoubleAbsoluteElse(FloatRigidImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.tX = (float) _U.getDouble(bytes, address);
        self.tY = (float) _U.getDouble(bytes, address + 8L);
        self.tZ = (float) _U.getDouble(bytes, address + 16L);
        self.rX = (float) _U.getDouble(bytes, address + 24L);
        self.rY = (float) _U.getDouble(bytes, address + 32L);
        self.rZ = (float) _U.getDouble(bytes, address + 40L);
        self.rW = (float) _U.getDouble(bytes, address + 48L);
        return self;
    }
}
