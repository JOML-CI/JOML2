// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleTriangleBbOpsUnsafe extends DoubleTriangleBbOps {
    private static final DoubleTriangleBbOpsApi API = new DoubleTriangleBbOpsApi();

    public DoubleBuffer storeAbsolute(DoubleTriangle self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.v0X());
        _U.putDouble(address + 8L, self.v0Y());
        _U.putDouble(address + 16L, self.v0Z());
        _U.putDouble(address + 24L, self.v1X());
        _U.putDouble(address + 32L, self.v1Y());
        _U.putDouble(address + 40L, self.v1Z());
        _U.putDouble(address + 48L, self.v2X());
        _U.putDouble(address + 56L, self.v2Y());
        _U.putDouble(address + 64L, self.v2Z());
        return buf;
    }

    public DoubleTriangle loadAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        double _c6 = _U.getDouble(address + 48L);
        double _c7 = _U.getDouble(address + 56L);
        double _c8 = _U.getDouble(address + 64L);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    public ByteBuffer storeAbsolute(DoubleTriangle self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.v0X());
        _U.putDouble(address + 8L, self.v0Y());
        _U.putDouble(address + 16L, self.v0Z());
        _U.putDouble(address + 24L, self.v1X());
        _U.putDouble(address + 32L, self.v1Y());
        _U.putDouble(address + 40L, self.v1Z());
        _U.putDouble(address + 48L, self.v2X());
        _U.putDouble(address + 56L, self.v2Y());
        _U.putDouble(address + 64L, self.v2Z());
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(DoubleTriangle self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.v0X());
        _U.putDouble(bytes, address + 8L, self.v0Y());
        _U.putDouble(bytes, address + 16L, self.v0Z());
        _U.putDouble(bytes, address + 24L, self.v1X());
        _U.putDouble(bytes, address + 32L, self.v1Y());
        _U.putDouble(bytes, address + 40L, self.v1Z());
        _U.putDouble(bytes, address + 48L, self.v2X());
        _U.putDouble(bytes, address + 56L, self.v2Y());
        _U.putDouble(bytes, address + 64L, self.v2Z());
        return buf;
    }

    public DoubleTriangle loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        double _c6 = _U.getDouble(address + 48L);
        double _c7 = _U.getDouble(address + 56L);
        double _c8 = _U.getDouble(address + 64L);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    private static DoubleTriangle loadAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8L);
        double _c2 = _U.getDouble(bytes, address + 16L);
        double _c3 = _U.getDouble(bytes, address + 24L);
        double _c4 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        double _c6 = _U.getDouble(bytes, address + 48L);
        double _c7 = _U.getDouble(bytes, address + 56L);
        double _c8 = _U.getDouble(bytes, address + 64L);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    public FloatBuffer storeAbsolute(DoubleTriangle self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, (float) self.v0X());
        _U.putFloat(address + 4L, (float) self.v0Y());
        _U.putFloat(address + 8L, (float) self.v0Z());
        _U.putFloat(address + 12L, (float) self.v1X());
        _U.putFloat(address + 16L, (float) self.v1Y());
        _U.putFloat(address + 20L, (float) self.v1Z());
        _U.putFloat(address + 24L, (float) self.v2X());
        _U.putFloat(address + 28L, (float) self.v2Y());
        _U.putFloat(address + 32L, (float) self.v2Z());
        return buf;
    }

    public DoubleTriangle loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        double _c6 = _U.getFloat(address + 24L);
        double _c7 = _U.getFloat(address + 28L);
        double _c8 = _U.getFloat(address + 32L);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    public ByteBuffer storeFloatAbsolute(DoubleTriangle self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, (float) self.v0X());
        _U.putFloat(address + 4L, (float) self.v0Y());
        _U.putFloat(address + 8L, (float) self.v0Z());
        _U.putFloat(address + 12L, (float) self.v1X());
        _U.putFloat(address + 16L, (float) self.v1Y());
        _U.putFloat(address + 20L, (float) self.v1Z());
        _U.putFloat(address + 24L, (float) self.v2X());
        _U.putFloat(address + 28L, (float) self.v2Y());
        _U.putFloat(address + 32L, (float) self.v2Z());
        return buf;
    }

    private static ByteBuffer storeFloatAbsoluteElse(DoubleTriangle self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, (float) self.v0X());
        _U.putFloat(bytes, address + 4L, (float) self.v0Y());
        _U.putFloat(bytes, address + 8L, (float) self.v0Z());
        _U.putFloat(bytes, address + 12L, (float) self.v1X());
        _U.putFloat(bytes, address + 16L, (float) self.v1Y());
        _U.putFloat(bytes, address + 20L, (float) self.v1Z());
        _U.putFloat(bytes, address + 24L, (float) self.v2X());
        _U.putFloat(bytes, address + 28L, (float) self.v2Y());
        _U.putFloat(bytes, address + 32L, (float) self.v2Z());
        return buf;
    }

    public DoubleTriangle loadFloatAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        double _c6 = _U.getFloat(address + 24L);
        double _c7 = _U.getFloat(address + 28L);
        double _c8 = _U.getFloat(address + 32L);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    private static DoubleTriangle loadFloatAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadFloatAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4L);
        double _c2 = _U.getFloat(bytes, address + 8L);
        double _c3 = _U.getFloat(bytes, address + 12L);
        double _c4 = _U.getFloat(bytes, address + 16L);
        double _c5 = _U.getFloat(bytes, address + 20L);
        double _c6 = _U.getFloat(bytes, address + 24L);
        double _c7 = _U.getFloat(bytes, address + 28L);
        double _c8 = _U.getFloat(bytes, address + 32L);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
}
