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

public final class FloatRayBbOpsUnsafe extends FloatRayBbOps {
    private static final FloatRayBbOpsApi API = new FloatRayBbOpsApi();

    public FloatBuffer storeAbsolute(FloatRayImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.oX);
        _U.putFloat(address + 4L, self.oY);
        _U.putFloat(address + 8L, self.oZ);
        _U.putFloat(address + 12L, self.dX);
        _U.putFloat(address + 16L, self.dY);
        _U.putFloat(address + 20L, self.dZ);
        return buf;
    }

    public FloatRay loadAbsolute(FloatRayImpl self, int index, FloatBuffer buf) {
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

    public ByteBuffer storeAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.oX);
        _U.putFloat(address + 4L, self.oY);
        _U.putFloat(address + 8L, self.oZ);
        _U.putFloat(address + 12L, self.dX);
        _U.putFloat(address + 16L, self.dY);
        _U.putFloat(address + 20L, self.dZ);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(FloatRayImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.oX);
        _U.putFloat(bytes, address + 4L, self.oY);
        _U.putFloat(bytes, address + 8L, self.oZ);
        _U.putFloat(bytes, address + 12L, self.dX);
        _U.putFloat(bytes, address + 16L, self.dY);
        _U.putFloat(bytes, address + 20L, self.dZ);
        return buf;
    }

    public FloatRay loadAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
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

    private static FloatRay loadAbsoluteElse(FloatRayImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
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

    public DoubleBuffer storeAbsolute(FloatRayImpl self, int index, DoubleBuffer buf) {
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

    public FloatRay loadAbsolute(FloatRayImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.oX = (float) _U.getDouble(address);
        self.oY = (float) _U.getDouble(address + 8L);
        self.oZ = (float) _U.getDouble(address + 16L);
        self.dX = (float) _U.getDouble(address + 24L);
        self.dY = (float) _U.getDouble(address + 32L);
        self.dZ = (float) _U.getDouble(address + 40L);
        return self;
    }

    public ByteBuffer storeDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeDoubleAbsoluteElse(self, index, buf);
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

    private static ByteBuffer storeDoubleAbsoluteElse(FloatRayImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeDoubleAbsolute(self, index, buf);
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

    public FloatRay loadDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.oX = (float) _U.getDouble(address);
        self.oY = (float) _U.getDouble(address + 8L);
        self.oZ = (float) _U.getDouble(address + 16L);
        self.dX = (float) _U.getDouble(address + 24L);
        self.dY = (float) _U.getDouble(address + 32L);
        self.dZ = (float) _U.getDouble(address + 40L);
        return self;
    }

    private static FloatRay loadDoubleAbsoluteElse(FloatRayImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.oX = (float) _U.getDouble(bytes, address);
        self.oY = (float) _U.getDouble(bytes, address + 8L);
        self.oZ = (float) _U.getDouble(bytes, address + 16L);
        self.dX = (float) _U.getDouble(bytes, address + 24L);
        self.dY = (float) _U.getDouble(bytes, address + 32L);
        self.dZ = (float) _U.getDouble(bytes, address + 40L);
        return self;
    }
}
