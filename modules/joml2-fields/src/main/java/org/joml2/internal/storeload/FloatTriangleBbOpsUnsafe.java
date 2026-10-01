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

public final class FloatTriangleBbOpsUnsafe extends FloatTriangleBbOps {
    private static final FloatTriangleBbOpsApi API = new FloatTriangleBbOpsApi();

    public FloatBuffer storeAbsolute(FloatTriangleImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.v0X);
        _U.putFloat(address + 4L, self.v0Y);
        _U.putFloat(address + 8L, self.v0Z);
        _U.putFloat(address + 12L, self.v1X);
        _U.putFloat(address + 16L, self.v1Y);
        _U.putFloat(address + 20L, self.v1Z);
        _U.putFloat(address + 24L, self.v2X);
        _U.putFloat(address + 28L, self.v2Y);
        _U.putFloat(address + 32L, self.v2Z);
        return buf;
    }

    public FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, FloatBuffer buf) {
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

    public ByteBuffer storeAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.v0X);
        _U.putFloat(address + 4L, self.v0Y);
        _U.putFloat(address + 8L, self.v0Z);
        _U.putFloat(address + 12L, self.v1X);
        _U.putFloat(address + 16L, self.v1Y);
        _U.putFloat(address + 20L, self.v1Z);
        _U.putFloat(address + 24L, self.v2X);
        _U.putFloat(address + 28L, self.v2Y);
        _U.putFloat(address + 32L, self.v2Z);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(FloatTriangleImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.v0X);
        _U.putFloat(bytes, address + 4L, self.v0Y);
        _U.putFloat(bytes, address + 8L, self.v0Z);
        _U.putFloat(bytes, address + 12L, self.v1X);
        _U.putFloat(bytes, address + 16L, self.v1Y);
        _U.putFloat(bytes, address + 20L, self.v1Z);
        _U.putFloat(bytes, address + 24L, self.v2X);
        _U.putFloat(bytes, address + 28L, self.v2Y);
        _U.putFloat(bytes, address + 32L, self.v2Z);
        return buf;
    }

    public FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
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

    private static FloatTriangle loadAbsoluteElse(FloatTriangleImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
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

    public DoubleBuffer storeAbsolute(FloatTriangleImpl self, int index, DoubleBuffer buf) {
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

    public FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.v0X = (float) _U.getDouble(address);
        self.v0Y = (float) _U.getDouble(address + 8L);
        self.v0Z = (float) _U.getDouble(address + 16L);
        self.v1X = (float) _U.getDouble(address + 24L);
        self.v1Y = (float) _U.getDouble(address + 32L);
        self.v1Z = (float) _U.getDouble(address + 40L);
        self.v2X = (float) _U.getDouble(address + 48L);
        self.v2Y = (float) _U.getDouble(address + 56L);
        self.v2Z = (float) _U.getDouble(address + 64L);
        return self;
    }

    public ByteBuffer storeDoubleAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeDoubleAbsoluteElse(self, index, buf);
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

    private static ByteBuffer storeDoubleAbsoluteElse(FloatTriangleImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeDoubleAbsolute(self, index, buf);
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

    public FloatTriangle loadDoubleAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.v0X = (float) _U.getDouble(address);
        self.v0Y = (float) _U.getDouble(address + 8L);
        self.v0Z = (float) _U.getDouble(address + 16L);
        self.v1X = (float) _U.getDouble(address + 24L);
        self.v1Y = (float) _U.getDouble(address + 32L);
        self.v1Z = (float) _U.getDouble(address + 40L);
        self.v2X = (float) _U.getDouble(address + 48L);
        self.v2Y = (float) _U.getDouble(address + 56L);
        self.v2Z = (float) _U.getDouble(address + 64L);
        return self;
    }

    private static FloatTriangle loadDoubleAbsoluteElse(FloatTriangleImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.v0X = (float) _U.getDouble(bytes, address);
        self.v0Y = (float) _U.getDouble(bytes, address + 8L);
        self.v0Z = (float) _U.getDouble(bytes, address + 16L);
        self.v1X = (float) _U.getDouble(bytes, address + 24L);
        self.v1Y = (float) _U.getDouble(bytes, address + 32L);
        self.v1Z = (float) _U.getDouble(bytes, address + 40L);
        self.v2X = (float) _U.getDouble(bytes, address + 48L);
        self.v2Y = (float) _U.getDouble(bytes, address + 56L);
        self.v2Z = (float) _U.getDouble(bytes, address + 64L);
        return self;
    }
}
