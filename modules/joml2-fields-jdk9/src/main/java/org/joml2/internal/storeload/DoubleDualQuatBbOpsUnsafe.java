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

public final class DoubleDualQuatBbOpsUnsafe extends DoubleDualQuatBbOps {

    private static final DoubleDualQuatBbOpsApi API = new DoubleDualQuatBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DoubleBuffer storeAbsolute(DoubleDualQuatImpl self, int index, DoubleBuffer buf) {
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
    public DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.rX = U.getDouble(address);
        self.rY = U.getDouble(address + 8L);
        self.rZ = U.getDouble(address + 16L);
        self.rW = U.getDouble(address + 24L);
        self.dX = U.getDouble(address + 32L);
        self.dY = U.getDouble(address + 40L);
        self.dZ = U.getDouble(address + 48L);
        self.dW = U.getDouble(address + 56L);
        return self;
    }
    public ByteBuffer storeAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
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
    public DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.rX = U.getDouble(address);
        self.rY = U.getDouble(address + 8L);
        self.rZ = U.getDouble(address + 16L);
        self.rW = U.getDouble(address + 24L);
        self.dX = U.getDouble(address + 32L);
        self.dY = U.getDouble(address + 40L);
        self.dZ = U.getDouble(address + 48L);
        self.dW = U.getDouble(address + 56L);
        return self;
    }
    public FloatBuffer storeAbsolute(DoubleDualQuatImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.rX);
        U.putFloat(address + 4L, (float) self.rY);
        U.putFloat(address + 8L, (float) self.rZ);
        U.putFloat(address + 12L, (float) self.rW);
        U.putFloat(address + 16L, (float) self.dX);
        U.putFloat(address + 20L, (float) self.dY);
        U.putFloat(address + 24L, (float) self.dZ);
        U.putFloat(address + 28L, (float) self.dW);
        return buf;
    }
    public DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, FloatBuffer buf) {
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
    public ByteBuffer storeFloatAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.rX);
        U.putFloat(address + 4L, (float) self.rY);
        U.putFloat(address + 8L, (float) self.rZ);
        U.putFloat(address + 12L, (float) self.rW);
        U.putFloat(address + 16L, (float) self.dX);
        U.putFloat(address + 20L, (float) self.dY);
        U.putFloat(address + 24L, (float) self.dZ);
        U.putFloat(address + 28L, (float) self.dW);
        return buf;
    }
    public DoubleDualQuat loadFloatAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadFloatAbsolute(self, index, buf);
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
}
