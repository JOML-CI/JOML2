// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
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

    public FloatBuffer storeAbsolute(FloatTriangle self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.v0X());
        U.putFloat(address + 4L, self.v0Y());
        U.putFloat(address + 8L, self.v0Z());
        U.putFloat(address + 12L, self.v1X());
        U.putFloat(address + 16L, self.v1Y());
        U.putFloat(address + 20L, self.v1Z());
        U.putFloat(address + 24L, self.v2X());
        U.putFloat(address + 28L, self.v2Y());
        U.putFloat(address + 32L, self.v2Z());
        return buf;
    }
    public FloatTriangle loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeAbsolute(FloatTriangle self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.v0X());
        U.putFloat(address + 4L, self.v0Y());
        U.putFloat(address + 8L, self.v0Z());
        U.putFloat(address + 12L, self.v1X());
        U.putFloat(address + 16L, self.v1Y());
        U.putFloat(address + 20L, self.v1Z());
        U.putFloat(address + 24L, self.v2X());
        U.putFloat(address + 28L, self.v2Y());
        U.putFloat(address + 32L, self.v2Z());
        return buf;
    }
    public FloatTriangle loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public DoubleBuffer storeAbsolute(FloatTriangle self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.v0X());
        U.putDouble(address + 8L, self.v0Y());
        U.putDouble(address + 16L, self.v0Z());
        U.putDouble(address + 24L, self.v1X());
        U.putDouble(address + 32L, self.v1Y());
        U.putDouble(address + 40L, self.v1Z());
        U.putDouble(address + 48L, self.v2X());
        U.putDouble(address + 56L, self.v2Y());
        U.putDouble(address + 64L, self.v2Z());
        return buf;
    }
    public FloatTriangle loadAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        float _c8 = (float) U.getDouble(address + 64L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeDoubleAbsolute(FloatTriangle self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.v0X());
        U.putDouble(address + 8L, self.v0Y());
        U.putDouble(address + 16L, self.v0Z());
        U.putDouble(address + 24L, self.v1X());
        U.putDouble(address + 32L, self.v1Y());
        U.putDouble(address + 40L, self.v1Z());
        U.putDouble(address + 48L, self.v2X());
        U.putDouble(address + 56L, self.v2Y());
        U.putDouble(address + 64L, self.v2Z());
        return buf;
    }
    public FloatTriangle loadDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        float _c8 = (float) U.getDouble(address + 64L);
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
}
