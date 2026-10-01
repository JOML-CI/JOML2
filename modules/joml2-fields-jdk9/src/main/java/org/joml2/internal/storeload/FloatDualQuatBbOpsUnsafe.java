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

public final class FloatDualQuatBbOpsUnsafe extends FloatDualQuatBbOps {
    private static final FloatDualQuatBbOpsApi API = new FloatDualQuatBbOpsApi();

    public FloatBuffer storeAbsolute(FloatDualQuatImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.rX);
        _U.putFloat(address + 4L, self.rY);
        _U.putFloat(address + 8L, self.rZ);
        _U.putFloat(address + 12L, self.rW);
        _U.putFloat(address + 16L, self.dX);
        _U.putFloat(address + 20L, self.dY);
        _U.putFloat(address + 24L, self.dZ);
        _U.putFloat(address + 28L, self.dW);
        return buf;
    }

    public FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.rX = _U.getFloat(address);
        self.rY = _U.getFloat(address + 4L);
        self.rZ = _U.getFloat(address + 8L);
        self.rW = _U.getFloat(address + 12L);
        self.dX = _U.getFloat(address + 16L);
        self.dY = _U.getFloat(address + 20L);
        self.dZ = _U.getFloat(address + 24L);
        self.dW = _U.getFloat(address + 28L);
        return self;
    }

    public ByteBuffer storeAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.rX);
        _U.putFloat(address + 4L, self.rY);
        _U.putFloat(address + 8L, self.rZ);
        _U.putFloat(address + 12L, self.rW);
        _U.putFloat(address + 16L, self.dX);
        _U.putFloat(address + 20L, self.dY);
        _U.putFloat(address + 24L, self.dZ);
        _U.putFloat(address + 28L, self.dW);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.rX);
        _U.putFloat(bytes, address + 4L, self.rY);
        _U.putFloat(bytes, address + 8L, self.rZ);
        _U.putFloat(bytes, address + 12L, self.rW);
        _U.putFloat(bytes, address + 16L, self.dX);
        _U.putFloat(bytes, address + 20L, self.dY);
        _U.putFloat(bytes, address + 24L, self.dZ);
        _U.putFloat(bytes, address + 28L, self.dW);
        return buf;
    }

    public FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.rX = _U.getFloat(address);
        self.rY = _U.getFloat(address + 4L);
        self.rZ = _U.getFloat(address + 8L);
        self.rW = _U.getFloat(address + 12L);
        self.dX = _U.getFloat(address + 16L);
        self.dY = _U.getFloat(address + 20L);
        self.dZ = _U.getFloat(address + 24L);
        self.dW = _U.getFloat(address + 28L);
        return self;
    }

    private static FloatDualQuat loadAbsoluteElse(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.rX = _U.getFloat(bytes, address);
        self.rY = _U.getFloat(bytes, address + 4L);
        self.rZ = _U.getFloat(bytes, address + 8L);
        self.rW = _U.getFloat(bytes, address + 12L);
        self.dX = _U.getFloat(bytes, address + 16L);
        self.dY = _U.getFloat(bytes, address + 20L);
        self.dZ = _U.getFloat(bytes, address + 24L);
        self.dW = _U.getFloat(bytes, address + 28L);
        return self;
    }

    public DoubleBuffer storeAbsolute(FloatDualQuatImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.rX);
        _U.putDouble(address + 8L, self.rY);
        _U.putDouble(address + 16L, self.rZ);
        _U.putDouble(address + 24L, self.rW);
        _U.putDouble(address + 32L, self.dX);
        _U.putDouble(address + 40L, self.dY);
        _U.putDouble(address + 48L, self.dZ);
        _U.putDouble(address + 56L, self.dW);
        return buf;
    }

    public FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.rX = (float) _U.getDouble(address);
        self.rY = (float) _U.getDouble(address + 8L);
        self.rZ = (float) _U.getDouble(address + 16L);
        self.rW = (float) _U.getDouble(address + 24L);
        self.dX = (float) _U.getDouble(address + 32L);
        self.dY = (float) _U.getDouble(address + 40L);
        self.dZ = (float) _U.getDouble(address + 48L);
        self.dW = (float) _U.getDouble(address + 56L);
        return self;
    }

    public ByteBuffer storeDoubleAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.rX);
        _U.putDouble(address + 8L, self.rY);
        _U.putDouble(address + 16L, self.rZ);
        _U.putDouble(address + 24L, self.rW);
        _U.putDouble(address + 32L, self.dX);
        _U.putDouble(address + 40L, self.dY);
        _U.putDouble(address + 48L, self.dZ);
        _U.putDouble(address + 56L, self.dW);
        return buf;
    }

    private static ByteBuffer storeDoubleAbsoluteElse(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.rX);
        _U.putDouble(bytes, address + 8L, self.rY);
        _U.putDouble(bytes, address + 16L, self.rZ);
        _U.putDouble(bytes, address + 24L, self.rW);
        _U.putDouble(bytes, address + 32L, self.dX);
        _U.putDouble(bytes, address + 40L, self.dY);
        _U.putDouble(bytes, address + 48L, self.dZ);
        _U.putDouble(bytes, address + 56L, self.dW);
        return buf;
    }

    public FloatDualQuat loadDoubleAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.rX = (float) _U.getDouble(address);
        self.rY = (float) _U.getDouble(address + 8L);
        self.rZ = (float) _U.getDouble(address + 16L);
        self.rW = (float) _U.getDouble(address + 24L);
        self.dX = (float) _U.getDouble(address + 32L);
        self.dY = (float) _U.getDouble(address + 40L);
        self.dZ = (float) _U.getDouble(address + 48L);
        self.dW = (float) _U.getDouble(address + 56L);
        return self;
    }

    private static FloatDualQuat loadDoubleAbsoluteElse(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.rX = (float) _U.getDouble(bytes, address);
        self.rY = (float) _U.getDouble(bytes, address + 8L);
        self.rZ = (float) _U.getDouble(bytes, address + 16L);
        self.rW = (float) _U.getDouble(bytes, address + 24L);
        self.dX = (float) _U.getDouble(bytes, address + 32L);
        self.dY = (float) _U.getDouble(bytes, address + 40L);
        self.dZ = (float) _U.getDouble(bytes, address + 48L);
        self.dW = (float) _U.getDouble(bytes, address + 56L);
        return self;
    }
}
