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

public final class Float3x2BbOpsUnsafe extends Float3x2BbOps {

    private static final Float3x2BbOpsApi API = new Float3x2BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[3]);
        U.putFloat(address + 8L, self.data[1]);
        U.putFloat(address + 12L, self.data[4]);
        U.putFloat(address + 16L, self.data[2]);
        U.putFloat(address + 20L, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4L);
        self.data[1] = U.getFloat(address + 8L);
        self.data[4] = U.getFloat(address + 12L);
        self.data[2] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[3]);
        U.putFloat(address + 8L, self.data[1]);
        U.putFloat(address + 12L, self.data[4]);
        U.putFloat(address + 16L, self.data[2]);
        U.putFloat(address + 20L, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4L);
        self.data[1] = U.getFloat(address + 8L);
        self.data[4] = U.getFloat(address + 12L);
        self.data[2] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[3]);
        U.putDouble(address + 16L, self.data[1]);
        U.putDouble(address + 24L, self.data[4]);
        U.putDouble(address + 32L, self.data[2]);
        U.putDouble(address + 40L, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8L);
        self.data[1] = (float) U.getDouble(address + 16L);
        self.data[4] = (float) U.getDouble(address + 24L);
        self.data[2] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[3]);
        U.putDouble(address + 16L, self.data[1]);
        U.putDouble(address + 24L, self.data[4]);
        U.putDouble(address + 32L, self.data[2]);
        U.putDouble(address + 40L, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8L);
        self.data[1] = (float) U.getDouble(address + 16L);
        self.data[4] = (float) U.getDouble(address + 24L);
        self.data[2] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        return self;
    }
    public FloatBuffer storeCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[1]);
        U.putFloat(address + 8, self.data[2]);
        U.putFloat(_p1, self.data[3]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p1 + 8, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[5] = U.getFloat(_p1 + 8);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[1]);
        U.putFloat(address + 8, self.data[2]);
        U.putFloat(_p1, self.data[3]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p1 + 8, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[5] = U.getFloat(_p1 + 8);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(_p1, self.data[3]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p1 + 16, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8);
        self.data[2] = (float) U.getDouble(address + 16);
        self.data[3] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[5] = (float) U.getDouble(_p1 + 16);
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(_p1, self.data[3]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p1 + 16, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8);
        self.data[2] = (float) U.getDouble(address + 16);
        self.data[3] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[5] = (float) U.getDouble(_p1 + 16);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[3]);
        U.putFloat(_p1, self.data[1]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p2, self.data[2]);
        U.putFloat(_p2 + 4, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4);
        self.data[1] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[2] = U.getFloat(_p2);
        self.data[5] = U.getFloat(_p2 + 4);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[3]);
        U.putFloat(_p1, self.data[1]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p2, self.data[2]);
        U.putFloat(_p2 + 4, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4);
        self.data[1] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[2] = U.getFloat(_p2);
        self.data[5] = U.getFloat(_p2 + 4);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[3]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8);
        self.data[1] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[2] = (float) U.getDouble(_p2);
        self.data[5] = (float) U.getDouble(_p2 + 8);
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[3]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8);
        self.data[1] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[2] = (float) U.getDouble(_p2);
        self.data[5] = (float) U.getDouble(_p2 + 8);
        return self;
    }
}
