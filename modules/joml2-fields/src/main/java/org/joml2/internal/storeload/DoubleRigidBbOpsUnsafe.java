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

public final class DoubleRigidBbOpsUnsafe extends DoubleRigidBbOps {
    private static final DoubleRigidBbOpsApi API = new DoubleRigidBbOpsApi();

    public DoubleBuffer storeAbsolute(DoubleRigidImpl self, int index, DoubleBuffer buf) {
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

    public DoubleRigid loadAbsolute(DoubleRigidImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.tX = _U.getDouble(address);
        self.tY = _U.getDouble(address + 8L);
        self.tZ = _U.getDouble(address + 16L);
        self.rX = _U.getDouble(address + 24L);
        self.rY = _U.getDouble(address + 32L);
        self.rZ = _U.getDouble(address + 40L);
        self.rW = _U.getDouble(address + 48L);
        return self;
    }

    public ByteBuffer storeAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
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

    private static ByteBuffer storeAbsoluteElse(DoubleRigidImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
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

    public DoubleRigid loadAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.tX = _U.getDouble(address);
        self.tY = _U.getDouble(address + 8L);
        self.tZ = _U.getDouble(address + 16L);
        self.rX = _U.getDouble(address + 24L);
        self.rY = _U.getDouble(address + 32L);
        self.rZ = _U.getDouble(address + 40L);
        self.rW = _U.getDouble(address + 48L);
        return self;
    }

    private static DoubleRigid loadAbsoluteElse(DoubleRigidImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.tX = _U.getDouble(bytes, address);
        self.tY = _U.getDouble(bytes, address + 8L);
        self.tZ = _U.getDouble(bytes, address + 16L);
        self.rX = _U.getDouble(bytes, address + 24L);
        self.rY = _U.getDouble(bytes, address + 32L);
        self.rZ = _U.getDouble(bytes, address + 40L);
        self.rW = _U.getDouble(bytes, address + 48L);
        return self;
    }

    public FloatBuffer storeAbsolute(DoubleRigidImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, (float) self.tX);
        _U.putFloat(address + 4L, (float) self.tY);
        _U.putFloat(address + 8L, (float) self.tZ);
        _U.putFloat(address + 12L, (float) self.rX);
        _U.putFloat(address + 16L, (float) self.rY);
        _U.putFloat(address + 20L, (float) self.rZ);
        _U.putFloat(address + 24L, (float) self.rW);
        return buf;
    }

    public DoubleRigid loadAbsolute(DoubleRigidImpl self, int index, FloatBuffer buf) {
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

    public ByteBuffer storeFloatAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, (float) self.tX);
        _U.putFloat(address + 4L, (float) self.tY);
        _U.putFloat(address + 8L, (float) self.tZ);
        _U.putFloat(address + 12L, (float) self.rX);
        _U.putFloat(address + 16L, (float) self.rY);
        _U.putFloat(address + 20L, (float) self.rZ);
        _U.putFloat(address + 24L, (float) self.rW);
        return buf;
    }

    private static ByteBuffer storeFloatAbsoluteElse(DoubleRigidImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, (float) self.tX);
        _U.putFloat(bytes, address + 4L, (float) self.tY);
        _U.putFloat(bytes, address + 8L, (float) self.tZ);
        _U.putFloat(bytes, address + 12L, (float) self.rX);
        _U.putFloat(bytes, address + 16L, (float) self.rY);
        _U.putFloat(bytes, address + 20L, (float) self.rZ);
        _U.putFloat(bytes, address + 24L, (float) self.rW);
        return buf;
    }

    public DoubleRigid loadFloatAbsolute(DoubleRigidImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(self, index, buf);
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

    private static DoubleRigid loadFloatAbsoluteElse(DoubleRigidImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadFloatAbsolute(self, index, buf);
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
}
