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

public final class FloatDualQuatBbOpsUnsafe extends FloatDualQuatBbOps {

    private static final FloatDualQuatBbOpsApi API = new FloatDualQuatBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeAbsolute(FloatDualQuatImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.rX);
        U.putFloat(address + 4L, self.rY);
        U.putFloat(address + 8L, self.rZ);
        U.putFloat(address + 12L, self.rW);
        U.putFloat(address + 16L, self.dX);
        U.putFloat(address + 20L, self.dY);
        U.putFloat(address + 24L, self.dZ);
        U.putFloat(address + 28L, self.dW);
        return buf;
    }
    public FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.rX = U.getFloat(address);
        self.rY = U.getFloat(address + 4L);
        self.rZ = U.getFloat(address + 8L);
        self.rW = U.getFloat(address + 12L);
        self.dX = U.getFloat(address + 16L);
        self.dY = U.getFloat(address + 20L);
        self.dZ = U.getFloat(address + 24L);
        self.dW = U.getFloat(address + 28L);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.rX);
        U.putFloat(address + 4L, self.rY);
        U.putFloat(address + 8L, self.rZ);
        U.putFloat(address + 12L, self.rW);
        U.putFloat(address + 16L, self.dX);
        U.putFloat(address + 20L, self.dY);
        U.putFloat(address + 24L, self.dZ);
        U.putFloat(address + 28L, self.dW);
        return buf;
    }
    public FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.rX = U.getFloat(address);
        self.rY = U.getFloat(address + 4L);
        self.rZ = U.getFloat(address + 8L);
        self.rW = U.getFloat(address + 12L);
        self.dX = U.getFloat(address + 16L);
        self.dY = U.getFloat(address + 20L);
        self.dZ = U.getFloat(address + 24L);
        self.dW = U.getFloat(address + 28L);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatDualQuatImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.rX);
        U.putDouble(address + 8L, self.rY);
        U.putDouble(address + 16L, self.rZ);
        U.putDouble(address + 24L, self.rW);
        U.putDouble(address + 32L, self.dX);
        U.putDouble(address + 40L, self.dY);
        U.putDouble(address + 48L, self.dZ);
        U.putDouble(address + 56L, self.dW);
        return buf;
    }
    public FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.rX = (float) U.getDouble(address);
        self.rY = (float) U.getDouble(address + 8L);
        self.rZ = (float) U.getDouble(address + 16L);
        self.rW = (float) U.getDouble(address + 24L);
        self.dX = (float) U.getDouble(address + 32L);
        self.dY = (float) U.getDouble(address + 40L);
        self.dZ = (float) U.getDouble(address + 48L);
        self.dW = (float) U.getDouble(address + 56L);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.rX);
        U.putDouble(address + 8L, self.rY);
        U.putDouble(address + 16L, self.rZ);
        U.putDouble(address + 24L, self.rW);
        U.putDouble(address + 32L, self.dX);
        U.putDouble(address + 40L, self.dY);
        U.putDouble(address + 48L, self.dZ);
        U.putDouble(address + 56L, self.dW);
        return buf;
    }
    public FloatDualQuat loadDoubleAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.rX = (float) U.getDouble(address);
        self.rY = (float) U.getDouble(address + 8L);
        self.rZ = (float) U.getDouble(address + 16L);
        self.rW = (float) U.getDouble(address + 24L);
        self.dX = (float) U.getDouble(address + 32L);
        self.dY = (float) U.getDouble(address + 40L);
        self.dZ = (float) U.getDouble(address + 48L);
        self.dW = (float) U.getDouble(address + 56L);
        return self;
    }
}
