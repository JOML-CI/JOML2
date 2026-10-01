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

public final class Float2BbOpsUnsafe extends Float2BbOps {

    private static final Float2BbOpsApi API = new Float2BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeAbsolute(Float2 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.x());
        U.putFloat(address + 4L, self.y());
        return buf;
    }
    public Float2 loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        return new Float2(_c0, _c1);
    }
    public ByteBuffer storeAbsolute(Float2 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.x());
        U.putFloat(address + 4L, self.y());
        return buf;
    }
    public Float2 loadAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        return new Float2(_c0, _c1);
    }
    public DoubleBuffer storeAbsolute(Float2 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.x());
        U.putDouble(address + 8L, self.y());
        return buf;
    }
    public Float2 loadAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        return new Float2(_c0, _c1);
    }
    public ByteBuffer storeDoubleAbsolute(Float2 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.x());
        U.putDouble(address + 8L, self.y());
        return buf;
    }
    public Float2 loadDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        return new Float2(_c0, _c1);
    }
}
