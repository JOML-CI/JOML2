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

public final class DoubleRayBbOpsUnsafe extends DoubleRayBbOps {
    private static final DoubleRayBbOpsApi API = new DoubleRayBbOpsApi();

    public DoubleBuffer storeAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.oX);
        _U.putDouble(address + 8L, self.oY);
        _U.putDouble(address + 16L, self.oZ);
        _U.putDouble(address + 24L, self.dX);
        _U.putDouble(address + 32L, self.dY);
        _U.putDouble(address + 40L, self.dZ);
        return buf;
    }

    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.oX = _U.getDouble(address);
        self.oY = _U.getDouble(address + 8L);
        self.oZ = _U.getDouble(address + 16L);
        self.dX = _U.getDouble(address + 24L);
        self.dY = _U.getDouble(address + 32L);
        self.dZ = _U.getDouble(address + 40L);
        return self;
    }

    public ByteBuffer storeAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.oX);
        _U.putDouble(address + 8L, self.oY);
        _U.putDouble(address + 16L, self.oZ);
        _U.putDouble(address + 24L, self.dX);
        _U.putDouble(address + 32L, self.dY);
        _U.putDouble(address + 40L, self.dZ);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(DoubleRayImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.oX);
        _U.putDouble(bytes, address + 8L, self.oY);
        _U.putDouble(bytes, address + 16L, self.oZ);
        _U.putDouble(bytes, address + 24L, self.dX);
        _U.putDouble(bytes, address + 32L, self.dY);
        _U.putDouble(bytes, address + 40L, self.dZ);
        return buf;
    }

    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.oX = _U.getDouble(address);
        self.oY = _U.getDouble(address + 8L);
        self.oZ = _U.getDouble(address + 16L);
        self.dX = _U.getDouble(address + 24L);
        self.dY = _U.getDouble(address + 32L);
        self.dZ = _U.getDouble(address + 40L);
        return self;
    }

    private static DoubleRay loadAbsoluteElse(DoubleRayImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.oX = _U.getDouble(bytes, address);
        self.oY = _U.getDouble(bytes, address + 8L);
        self.oZ = _U.getDouble(bytes, address + 16L);
        self.dX = _U.getDouble(bytes, address + 24L);
        self.dY = _U.getDouble(bytes, address + 32L);
        self.dZ = _U.getDouble(bytes, address + 40L);
        return self;
    }

    public FloatBuffer storeAbsolute(DoubleRayImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, (float) self.oX);
        _U.putFloat(address + 4L, (float) self.oY);
        _U.putFloat(address + 8L, (float) self.oZ);
        _U.putFloat(address + 12L, (float) self.dX);
        _U.putFloat(address + 16L, (float) self.dY);
        _U.putFloat(address + 20L, (float) self.dZ);
        return buf;
    }

    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.oX = _U.getFloat(address);
        self.oY = _U.getFloat(address + 4L);
        self.oZ = _U.getFloat(address + 8L);
        self.dX = _U.getFloat(address + 12L);
        self.dY = _U.getFloat(address + 16L);
        self.dZ = _U.getFloat(address + 20L);
        return self;
    }

    public ByteBuffer storeFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, (float) self.oX);
        _U.putFloat(address + 4L, (float) self.oY);
        _U.putFloat(address + 8L, (float) self.oZ);
        _U.putFloat(address + 12L, (float) self.dX);
        _U.putFloat(address + 16L, (float) self.dY);
        _U.putFloat(address + 20L, (float) self.dZ);
        return buf;
    }

    private static ByteBuffer storeFloatAbsoluteElse(DoubleRayImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, (float) self.oX);
        _U.putFloat(bytes, address + 4L, (float) self.oY);
        _U.putFloat(bytes, address + 8L, (float) self.oZ);
        _U.putFloat(bytes, address + 12L, (float) self.dX);
        _U.putFloat(bytes, address + 16L, (float) self.dY);
        _U.putFloat(bytes, address + 20L, (float) self.dZ);
        return buf;
    }

    public DoubleRay loadFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.oX = _U.getFloat(address);
        self.oY = _U.getFloat(address + 4L);
        self.oZ = _U.getFloat(address + 8L);
        self.dX = _U.getFloat(address + 12L);
        self.dY = _U.getFloat(address + 16L);
        self.dZ = _U.getFloat(address + 20L);
        return self;
    }

    private static DoubleRay loadFloatAbsoluteElse(DoubleRayImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.oX = _U.getFloat(bytes, address);
        self.oY = _U.getFloat(bytes, address + 4L);
        self.oZ = _U.getFloat(bytes, address + 8L);
        self.dX = _U.getFloat(bytes, address + 12L);
        self.dY = _U.getFloat(bytes, address + 16L);
        self.dZ = _U.getFloat(bytes, address + 20L);
        return self;
    }
}
