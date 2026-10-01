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

public final class DoubleTriangleBbOpsUnsafe extends DoubleTriangleBbOps {
    private static final DoubleTriangleBbOpsApi API = new DoubleTriangleBbOpsApi();

    public DoubleBuffer storeAbsolute(DoubleTriangleImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.v0X);
        _U.putDouble(address + 8L, self.v0Y);
        _U.putDouble(address + 16L, self.v0Z);
        _U.putDouble(address + 24L, self.v1X);
        _U.putDouble(address + 32L, self.v1Y);
        _U.putDouble(address + 40L, self.v1Z);
        _U.putDouble(address + 48L, self.v2X);
        _U.putDouble(address + 56L, self.v2Y);
        _U.putDouble(address + 64L, self.v2Z);
        return buf;
    }

    public DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.v0X = _U.getDouble(address);
        self.v0Y = _U.getDouble(address + 8L);
        self.v0Z = _U.getDouble(address + 16L);
        self.v1X = _U.getDouble(address + 24L);
        self.v1Y = _U.getDouble(address + 32L);
        self.v1Z = _U.getDouble(address + 40L);
        self.v2X = _U.getDouble(address + 48L);
        self.v2Y = _U.getDouble(address + 56L);
        self.v2Z = _U.getDouble(address + 64L);
        return self;
    }

    public ByteBuffer storeAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.v0X);
        _U.putDouble(address + 8L, self.v0Y);
        _U.putDouble(address + 16L, self.v0Z);
        _U.putDouble(address + 24L, self.v1X);
        _U.putDouble(address + 32L, self.v1Y);
        _U.putDouble(address + 40L, self.v1Z);
        _U.putDouble(address + 48L, self.v2X);
        _U.putDouble(address + 56L, self.v2Y);
        _U.putDouble(address + 64L, self.v2Z);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.v0X);
        _U.putDouble(bytes, address + 8L, self.v0Y);
        _U.putDouble(bytes, address + 16L, self.v0Z);
        _U.putDouble(bytes, address + 24L, self.v1X);
        _U.putDouble(bytes, address + 32L, self.v1Y);
        _U.putDouble(bytes, address + 40L, self.v1Z);
        _U.putDouble(bytes, address + 48L, self.v2X);
        _U.putDouble(bytes, address + 56L, self.v2Y);
        _U.putDouble(bytes, address + 64L, self.v2Z);
        return buf;
    }

    public DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.v0X = _U.getDouble(address);
        self.v0Y = _U.getDouble(address + 8L);
        self.v0Z = _U.getDouble(address + 16L);
        self.v1X = _U.getDouble(address + 24L);
        self.v1Y = _U.getDouble(address + 32L);
        self.v1Z = _U.getDouble(address + 40L);
        self.v2X = _U.getDouble(address + 48L);
        self.v2Y = _U.getDouble(address + 56L);
        self.v2Z = _U.getDouble(address + 64L);
        return self;
    }

    private static DoubleTriangle loadAbsoluteElse(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.v0X = _U.getDouble(bytes, address);
        self.v0Y = _U.getDouble(bytes, address + 8L);
        self.v0Z = _U.getDouble(bytes, address + 16L);
        self.v1X = _U.getDouble(bytes, address + 24L);
        self.v1Y = _U.getDouble(bytes, address + 32L);
        self.v1Z = _U.getDouble(bytes, address + 40L);
        self.v2X = _U.getDouble(bytes, address + 48L);
        self.v2Y = _U.getDouble(bytes, address + 56L);
        self.v2Z = _U.getDouble(bytes, address + 64L);
        return self;
    }

    public FloatBuffer storeAbsolute(DoubleTriangleImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, (float) self.v0X);
        _U.putFloat(address + 4L, (float) self.v0Y);
        _U.putFloat(address + 8L, (float) self.v0Z);
        _U.putFloat(address + 12L, (float) self.v1X);
        _U.putFloat(address + 16L, (float) self.v1Y);
        _U.putFloat(address + 20L, (float) self.v1Z);
        _U.putFloat(address + 24L, (float) self.v2X);
        _U.putFloat(address + 28L, (float) self.v2Y);
        _U.putFloat(address + 32L, (float) self.v2Z);
        return buf;
    }

    public DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.v0X = _U.getFloat(address);
        self.v0Y = _U.getFloat(address + 4L);
        self.v0Z = _U.getFloat(address + 8L);
        self.v1X = _U.getFloat(address + 12L);
        self.v1Y = _U.getFloat(address + 16L);
        self.v1Z = _U.getFloat(address + 20L);
        self.v2X = _U.getFloat(address + 24L);
        self.v2Y = _U.getFloat(address + 28L);
        self.v2Z = _U.getFloat(address + 32L);
        return self;
    }

    public ByteBuffer storeFloatAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, (float) self.v0X);
        _U.putFloat(address + 4L, (float) self.v0Y);
        _U.putFloat(address + 8L, (float) self.v0Z);
        _U.putFloat(address + 12L, (float) self.v1X);
        _U.putFloat(address + 16L, (float) self.v1Y);
        _U.putFloat(address + 20L, (float) self.v1Z);
        _U.putFloat(address + 24L, (float) self.v2X);
        _U.putFloat(address + 28L, (float) self.v2Y);
        _U.putFloat(address + 32L, (float) self.v2Z);
        return buf;
    }

    private static ByteBuffer storeFloatAbsoluteElse(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, (float) self.v0X);
        _U.putFloat(bytes, address + 4L, (float) self.v0Y);
        _U.putFloat(bytes, address + 8L, (float) self.v0Z);
        _U.putFloat(bytes, address + 12L, (float) self.v1X);
        _U.putFloat(bytes, address + 16L, (float) self.v1Y);
        _U.putFloat(bytes, address + 20L, (float) self.v1Z);
        _U.putFloat(bytes, address + 24L, (float) self.v2X);
        _U.putFloat(bytes, address + 28L, (float) self.v2Y);
        _U.putFloat(bytes, address + 32L, (float) self.v2Z);
        return buf;
    }

    public DoubleTriangle loadFloatAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.v0X = _U.getFloat(address);
        self.v0Y = _U.getFloat(address + 4L);
        self.v0Z = _U.getFloat(address + 8L);
        self.v1X = _U.getFloat(address + 12L);
        self.v1Y = _U.getFloat(address + 16L);
        self.v1Z = _U.getFloat(address + 20L);
        self.v2X = _U.getFloat(address + 24L);
        self.v2Y = _U.getFloat(address + 28L);
        self.v2Z = _U.getFloat(address + 32L);
        return self;
    }

    private static DoubleTriangle loadFloatAbsoluteElse(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.v0X = _U.getFloat(bytes, address);
        self.v0Y = _U.getFloat(bytes, address + 4L);
        self.v0Z = _U.getFloat(bytes, address + 8L);
        self.v1X = _U.getFloat(bytes, address + 12L);
        self.v1Y = _U.getFloat(bytes, address + 16L);
        self.v1Z = _U.getFloat(bytes, address + 20L);
        self.v2X = _U.getFloat(bytes, address + 24L);
        self.v2Y = _U.getFloat(bytes, address + 28L);
        self.v2Z = _U.getFloat(bytes, address + 32L);
        return self;
    }
}
