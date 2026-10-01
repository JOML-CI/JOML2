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

public final class DoubleTriangleBbOpsUnsafe extends DoubleTriangleBbOps {

    private static final DoubleTriangleBbOpsApi API = new DoubleTriangleBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DoubleBuffer storeAbsolute(DoubleTriangle self, int index, DoubleBuffer buf) {
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
    public DoubleTriangle loadAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        double _c6 = U.getDouble(address + 48L);
        double _c7 = U.getDouble(address + 56L);
        double _c8 = U.getDouble(address + 64L);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeAbsolute(DoubleTriangle self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
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
    public DoubleTriangle loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        double _c6 = U.getDouble(address + 48L);
        double _c7 = U.getDouble(address + 56L);
        double _c8 = U.getDouble(address + 64L);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public FloatBuffer storeAbsolute(DoubleTriangle self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.v0X());
        U.putFloat(address + 4L, (float) self.v0Y());
        U.putFloat(address + 8L, (float) self.v0Z());
        U.putFloat(address + 12L, (float) self.v1X());
        U.putFloat(address + 16L, (float) self.v1Y());
        U.putFloat(address + 20L, (float) self.v1Z());
        U.putFloat(address + 24L, (float) self.v2X());
        U.putFloat(address + 28L, (float) self.v2Y());
        U.putFloat(address + 32L, (float) self.v2Z());
        return buf;
    }
    public DoubleTriangle loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        double _c6 = U.getFloat(address + 24L);
        double _c7 = U.getFloat(address + 28L);
        double _c8 = U.getFloat(address + 32L);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeFloatAbsolute(DoubleTriangle self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.v0X());
        U.putFloat(address + 4L, (float) self.v0Y());
        U.putFloat(address + 8L, (float) self.v0Z());
        U.putFloat(address + 12L, (float) self.v1X());
        U.putFloat(address + 16L, (float) self.v1Y());
        U.putFloat(address + 20L, (float) self.v1Z());
        U.putFloat(address + 24L, (float) self.v2X());
        U.putFloat(address + 28L, (float) self.v2Y());
        U.putFloat(address + 32L, (float) self.v2Z());
        return buf;
    }
    public DoubleTriangle loadFloatAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadFloatAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        double _c6 = U.getFloat(address + 24L);
        double _c7 = U.getFloat(address + 28L);
        double _c8 = U.getFloat(address + 32L);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
}
