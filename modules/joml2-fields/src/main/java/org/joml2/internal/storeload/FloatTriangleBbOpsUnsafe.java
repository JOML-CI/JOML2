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

public final class FloatTriangleBbOpsUnsafe extends FloatTriangleBbOps {

    private static final FloatTriangleBbOpsApi API = new FloatTriangleBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeAbsolute(FloatTriangleImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.v0X);
        U.putFloat(address + 4L, self.v0Y);
        U.putFloat(address + 8L, self.v0Z);
        U.putFloat(address + 12L, self.v1X);
        U.putFloat(address + 16L, self.v1Y);
        U.putFloat(address + 20L, self.v1Z);
        U.putFloat(address + 24L, self.v2X);
        U.putFloat(address + 28L, self.v2Y);
        U.putFloat(address + 32L, self.v2Z);
        return buf;
    }
    public FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.v0X = U.getFloat(address);
        self.v0Y = U.getFloat(address + 4L);
        self.v0Z = U.getFloat(address + 8L);
        self.v1X = U.getFloat(address + 12L);
        self.v1Y = U.getFloat(address + 16L);
        self.v1Z = U.getFloat(address + 20L);
        self.v2X = U.getFloat(address + 24L);
        self.v2Y = U.getFloat(address + 28L);
        self.v2Z = U.getFloat(address + 32L);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.v0X);
        U.putFloat(address + 4L, self.v0Y);
        U.putFloat(address + 8L, self.v0Z);
        U.putFloat(address + 12L, self.v1X);
        U.putFloat(address + 16L, self.v1Y);
        U.putFloat(address + 20L, self.v1Z);
        U.putFloat(address + 24L, self.v2X);
        U.putFloat(address + 28L, self.v2Y);
        U.putFloat(address + 32L, self.v2Z);
        return buf;
    }
    public FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.v0X = U.getFloat(address);
        self.v0Y = U.getFloat(address + 4L);
        self.v0Z = U.getFloat(address + 8L);
        self.v1X = U.getFloat(address + 12L);
        self.v1Y = U.getFloat(address + 16L);
        self.v1Z = U.getFloat(address + 20L);
        self.v2X = U.getFloat(address + 24L);
        self.v2Y = U.getFloat(address + 28L);
        self.v2Z = U.getFloat(address + 32L);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatTriangleImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.v0X);
        U.putDouble(address + 8L, self.v0Y);
        U.putDouble(address + 16L, self.v0Z);
        U.putDouble(address + 24L, self.v1X);
        U.putDouble(address + 32L, self.v1Y);
        U.putDouble(address + 40L, self.v1Z);
        U.putDouble(address + 48L, self.v2X);
        U.putDouble(address + 56L, self.v2Y);
        U.putDouble(address + 64L, self.v2Z);
        return buf;
    }
    public FloatTriangle loadAbsolute(FloatTriangleImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.v0X = (float) U.getDouble(address);
        self.v0Y = (float) U.getDouble(address + 8L);
        self.v0Z = (float) U.getDouble(address + 16L);
        self.v1X = (float) U.getDouble(address + 24L);
        self.v1Y = (float) U.getDouble(address + 32L);
        self.v1Z = (float) U.getDouble(address + 40L);
        self.v2X = (float) U.getDouble(address + 48L);
        self.v2Y = (float) U.getDouble(address + 56L);
        self.v2Z = (float) U.getDouble(address + 64L);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.v0X);
        U.putDouble(address + 8L, self.v0Y);
        U.putDouble(address + 16L, self.v0Z);
        U.putDouble(address + 24L, self.v1X);
        U.putDouble(address + 32L, self.v1Y);
        U.putDouble(address + 40L, self.v1Z);
        U.putDouble(address + 48L, self.v2X);
        U.putDouble(address + 56L, self.v2Y);
        U.putDouble(address + 64L, self.v2Z);
        return buf;
    }
    public FloatTriangle loadDoubleAbsolute(FloatTriangleImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.v0X = (float) U.getDouble(address);
        self.v0Y = (float) U.getDouble(address + 8L);
        self.v0Z = (float) U.getDouble(address + 16L);
        self.v1X = (float) U.getDouble(address + 24L);
        self.v1Y = (float) U.getDouble(address + 32L);
        self.v1Z = (float) U.getDouble(address + 40L);
        self.v2X = (float) U.getDouble(address + 48L);
        self.v2Y = (float) U.getDouble(address + 56L);
        self.v2Z = (float) U.getDouble(address + 64L);
        return self;
    }
}
