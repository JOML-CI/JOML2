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

public final class DoubleDualQuatBbOpsUnsafe extends DoubleDualQuatBbOps {
    private static final DoubleDualQuatBbOpsApi API = new DoubleDualQuatBbOpsApi();

    public DoubleBuffer storeAbsolute(DoubleDualQuatImpl self, int index, DoubleBuffer buf) {
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

    public DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.rX = _U.getDouble(address);
        self.rY = _U.getDouble(address + 8L);
        self.rZ = _U.getDouble(address + 16L);
        self.rW = _U.getDouble(address + 24L);
        self.dX = _U.getDouble(address + 32L);
        self.dY = _U.getDouble(address + 40L);
        self.dZ = _U.getDouble(address + 48L);
        self.dW = _U.getDouble(address + 56L);
        return self;
    }

    public ByteBuffer storeAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
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

    private static ByteBuffer storeAbsoluteElse(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
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

    public DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.rX = _U.getDouble(address);
        self.rY = _U.getDouble(address + 8L);
        self.rZ = _U.getDouble(address + 16L);
        self.rW = _U.getDouble(address + 24L);
        self.dX = _U.getDouble(address + 32L);
        self.dY = _U.getDouble(address + 40L);
        self.dZ = _U.getDouble(address + 48L);
        self.dW = _U.getDouble(address + 56L);
        return self;
    }

    private static DoubleDualQuat loadAbsoluteElse(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.rX = _U.getDouble(bytes, address);
        self.rY = _U.getDouble(bytes, address + 8L);
        self.rZ = _U.getDouble(bytes, address + 16L);
        self.rW = _U.getDouble(bytes, address + 24L);
        self.dX = _U.getDouble(bytes, address + 32L);
        self.dY = _U.getDouble(bytes, address + 40L);
        self.dZ = _U.getDouble(bytes, address + 48L);
        self.dW = _U.getDouble(bytes, address + 56L);
        return self;
    }

    public FloatBuffer storeAbsolute(DoubleDualQuatImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, (float) self.rX);
        _U.putFloat(address + 4L, (float) self.rY);
        _U.putFloat(address + 8L, (float) self.rZ);
        _U.putFloat(address + 12L, (float) self.rW);
        _U.putFloat(address + 16L, (float) self.dX);
        _U.putFloat(address + 20L, (float) self.dY);
        _U.putFloat(address + 24L, (float) self.dZ);
        _U.putFloat(address + 28L, (float) self.dW);
        return buf;
    }

    public DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, FloatBuffer buf) {
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

    public ByteBuffer storeFloatAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, (float) self.rX);
        _U.putFloat(address + 4L, (float) self.rY);
        _U.putFloat(address + 8L, (float) self.rZ);
        _U.putFloat(address + 12L, (float) self.rW);
        _U.putFloat(address + 16L, (float) self.dX);
        _U.putFloat(address + 20L, (float) self.dY);
        _U.putFloat(address + 24L, (float) self.dZ);
        _U.putFloat(address + 28L, (float) self.dW);
        return buf;
    }

    private static ByteBuffer storeFloatAbsoluteElse(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, (float) self.rX);
        _U.putFloat(bytes, address + 4L, (float) self.rY);
        _U.putFloat(bytes, address + 8L, (float) self.rZ);
        _U.putFloat(bytes, address + 12L, (float) self.rW);
        _U.putFloat(bytes, address + 16L, (float) self.dX);
        _U.putFloat(bytes, address + 20L, (float) self.dY);
        _U.putFloat(bytes, address + 24L, (float) self.dZ);
        _U.putFloat(bytes, address + 28L, (float) self.dW);
        return buf;
    }

    public DoubleDualQuat loadFloatAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(self, index, buf);
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

    private static DoubleDualQuat loadFloatAbsoluteElse(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadFloatAbsolute(self, index, buf);
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
}
