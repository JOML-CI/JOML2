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

public final class Double3x4BbOpsUnsafe extends Double3x4BbOps {

    private static final Double3x4BbOpsApi API = new Double3x4BbOpsApi();
    private static final Double3x4RawOpsUnsafe RAW = new Double3x4RawOpsUnsafe();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DoubleBuffer storeCMAbsolute(Double3x4 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m10());
        U.putDouble(address + 16L, self.m20());
        U.putDouble(address + 24L, self.m01());
        U.putDouble(address + 32L, self.m11());
        U.putDouble(address + 40L, self.m21());
        U.putDouble(address + 48L, self.m02());
        U.putDouble(address + 56L, self.m12());
        U.putDouble(address + 64L, self.m22());
        U.putDouble(address + 72L, self.m03());
        U.putDouble(address + 80L, self.m13());
        U.putDouble(address + 88L, self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        double _c0 = U.getDouble(address);
        double _c4 = U.getDouble(address + 8L);
        double _c8 = U.getDouble(address + 16L);
        double _c1 = U.getDouble(address + 24L);
        double _c5 = U.getDouble(address + 32L);
        double _c9 = U.getDouble(address + 40L);
        double _c2 = U.getDouble(address + 48L);
        double _c6 = U.getDouble(address + 56L);
        double _c10 = U.getDouble(address + 64L);
        double _c3 = U.getDouble(address + 72L);
        double _c7 = U.getDouble(address + 80L);
        double _c11 = U.getDouble(address + 88L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeCMAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m10());
        U.putDouble(address + 16L, self.m20());
        U.putDouble(address + 24L, self.m01());
        U.putDouble(address + 32L, self.m11());
        U.putDouble(address + 40L, self.m21());
        U.putDouble(address + 48L, self.m02());
        U.putDouble(address + 56L, self.m12());
        U.putDouble(address + 64L, self.m22());
        U.putDouble(address + 72L, self.m03());
        U.putDouble(address + 80L, self.m13());
        U.putDouble(address + 88L, self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getDouble(address);
        double _c4 = U.getDouble(address + 8L);
        double _c8 = U.getDouble(address + 16L);
        double _c1 = U.getDouble(address + 24L);
        double _c5 = U.getDouble(address + 32L);
        double _c9 = U.getDouble(address + 40L);
        double _c2 = U.getDouble(address + 48L);
        double _c6 = U.getDouble(address + 56L);
        double _c10 = U.getDouble(address + 64L);
        double _c3 = U.getDouble(address + 72L);
        double _c7 = U.getDouble(address + 80L);
        double _c11 = U.getDouble(address + 88L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public FloatBuffer storeCMAbsolute(Double3x4 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4L, (float) self.m10());
        U.putFloat(address + 8L, (float) self.m20());
        U.putFloat(address + 12L, (float) self.m01());
        U.putFloat(address + 16L, (float) self.m11());
        U.putFloat(address + 20L, (float) self.m21());
        U.putFloat(address + 24L, (float) self.m02());
        U.putFloat(address + 28L, (float) self.m12());
        U.putFloat(address + 32L, (float) self.m22());
        U.putFloat(address + 36L, (float) self.m03());
        U.putFloat(address + 40L, (float) self.m13());
        U.putFloat(address + 44L, (float) self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        double _c0 = U.getFloat(address);
        double _c4 = U.getFloat(address + 4L);
        double _c8 = U.getFloat(address + 8L);
        double _c1 = U.getFloat(address + 12L);
        double _c5 = U.getFloat(address + 16L);
        double _c9 = U.getFloat(address + 20L);
        double _c2 = U.getFloat(address + 24L);
        double _c6 = U.getFloat(address + 28L);
        double _c10 = U.getFloat(address + 32L);
        double _c3 = U.getFloat(address + 36L);
        double _c7 = U.getFloat(address + 40L);
        double _c11 = U.getFloat(address + 44L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeCMFloatAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4L, (float) self.m10());
        U.putFloat(address + 8L, (float) self.m20());
        U.putFloat(address + 12L, (float) self.m01());
        U.putFloat(address + 16L, (float) self.m11());
        U.putFloat(address + 20L, (float) self.m21());
        U.putFloat(address + 24L, (float) self.m02());
        U.putFloat(address + 28L, (float) self.m12());
        U.putFloat(address + 32L, (float) self.m22());
        U.putFloat(address + 36L, (float) self.m03());
        U.putFloat(address + 40L, (float) self.m13());
        U.putFloat(address + 44L, (float) self.m23());
        return buf;
    }
    public Double3x4 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMFloatAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getFloat(address);
        double _c4 = U.getFloat(address + 4L);
        double _c8 = U.getFloat(address + 8L);
        double _c1 = U.getFloat(address + 12L);
        double _c5 = U.getFloat(address + 16L);
        double _c9 = U.getFloat(address + 20L);
        double _c2 = U.getFloat(address + 24L);
        double _c6 = U.getFloat(address + 28L);
        double _c10 = U.getFloat(address + 32L);
        double _c3 = U.getFloat(address + 36L);
        double _c7 = U.getFloat(address + 40L);
        double _c11 = U.getFloat(address + 44L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public DoubleBuffer storeRMAbsolute(Double3x4 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m02());
        U.putDouble(address + 24L, self.m03());
        U.putDouble(address + 32L, self.m10());
        U.putDouble(address + 40L, self.m11());
        U.putDouble(address + 48L, self.m12());
        U.putDouble(address + 56L, self.m13());
        U.putDouble(address + 64L, self.m20());
        U.putDouble(address + 72L, self.m21());
        U.putDouble(address + 80L, self.m22());
        U.putDouble(address + 88L, self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
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
        double _c9 = U.getDouble(address + 72L);
        double _c10 = U.getDouble(address + 80L);
        double _c11 = U.getDouble(address + 88L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeRMAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m02());
        U.putDouble(address + 24L, self.m03());
        U.putDouble(address + 32L, self.m10());
        U.putDouble(address + 40L, self.m11());
        U.putDouble(address + 48L, self.m12());
        U.putDouble(address + 56L, self.m13());
        U.putDouble(address + 64L, self.m20());
        U.putDouble(address + 72L, self.m21());
        U.putDouble(address + 80L, self.m22());
        U.putDouble(address + 88L, self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
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
        double _c9 = U.getDouble(address + 72L);
        double _c10 = U.getDouble(address + 80L);
        double _c11 = U.getDouble(address + 88L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public FloatBuffer storeRMAbsolute(Double3x4 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4L, (float) self.m01());
        U.putFloat(address + 8L, (float) self.m02());
        U.putFloat(address + 12L, (float) self.m03());
        U.putFloat(address + 16L, (float) self.m10());
        U.putFloat(address + 20L, (float) self.m11());
        U.putFloat(address + 24L, (float) self.m12());
        U.putFloat(address + 28L, (float) self.m13());
        U.putFloat(address + 32L, (float) self.m20());
        U.putFloat(address + 36L, (float) self.m21());
        U.putFloat(address + 40L, (float) self.m22());
        U.putFloat(address + 44L, (float) self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
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
        double _c9 = U.getFloat(address + 36L);
        double _c10 = U.getFloat(address + 40L);
        double _c11 = U.getFloat(address + 44L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeRMFloatAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4L, (float) self.m01());
        U.putFloat(address + 8L, (float) self.m02());
        U.putFloat(address + 12L, (float) self.m03());
        U.putFloat(address + 16L, (float) self.m10());
        U.putFloat(address + 20L, (float) self.m11());
        U.putFloat(address + 24L, (float) self.m12());
        U.putFloat(address + 28L, (float) self.m13());
        U.putFloat(address + 32L, (float) self.m20());
        U.putFloat(address + 36L, (float) self.m21());
        U.putFloat(address + 40L, (float) self.m22());
        U.putFloat(address + 44L, (float) self.m23());
        return buf;
    }
    public Double3x4 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMFloatAbsolute(index, buf);
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
        double _c9 = U.getFloat(address + 36L);
        double _c10 = U.getFloat(address + 40L);
        double _c11 = U.getFloat(address + 44L);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public DoubleBuffer storeCMAbsolute(Double3x4 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m10());
        U.putDouble(address + 16, self.m20());
        U.putDouble(_p1, self.m01());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m21());
        U.putDouble(_p2, self.m02());
        U.putDouble(_p2 + 8, self.m12());
        U.putDouble(_p2 + 16, self.m22());
        U.putDouble(_p3, self.m03());
        U.putDouble(_p3 + 8, self.m13());
        U.putDouble(_p3 + 16, self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        double _c0 = U.getDouble(address);
        double _c4 = U.getDouble(address + 8);
        double _c8 = U.getDouble(address + 16);
        double _c1 = U.getDouble(_p1);
        double _c5 = U.getDouble(_p1 + 8);
        double _c9 = U.getDouble(_p1 + 16);
        double _c2 = U.getDouble(_p2);
        double _c6 = U.getDouble(_p2 + 8);
        double _c10 = U.getDouble(_p2 + 16);
        double _c3 = U.getDouble(_p3);
        double _c7 = U.getDouble(_p3 + 8);
        double _c11 = U.getDouble(_p3 + 16);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeCMAbsolute(Double3x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m10());
        U.putDouble(address + 16, self.m20());
        U.putDouble(_p1, self.m01());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m21());
        U.putDouble(_p2, self.m02());
        U.putDouble(_p2 + 8, self.m12());
        U.putDouble(_p2 + 16, self.m22());
        U.putDouble(_p3, self.m03());
        U.putDouble(_p3 + 8, self.m13());
        U.putDouble(_p3 + 16, self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        double _c0 = U.getDouble(address);
        double _c4 = U.getDouble(address + 8);
        double _c8 = U.getDouble(address + 16);
        double _c1 = U.getDouble(_p1);
        double _c5 = U.getDouble(_p1 + 8);
        double _c9 = U.getDouble(_p1 + 16);
        double _c2 = U.getDouble(_p2);
        double _c6 = U.getDouble(_p2 + 8);
        double _c10 = U.getDouble(_p2 + 16);
        double _c3 = U.getDouble(_p3);
        double _c7 = U.getDouble(_p3 + 8);
        double _c11 = U.getDouble(_p3 + 16);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public FloatBuffer storeCMAbsolute(Double3x4 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4, (float) self.m10());
        U.putFloat(address + 8, (float) self.m20());
        U.putFloat(_p1, (float) self.m01());
        U.putFloat(_p1 + 4, (float) self.m11());
        U.putFloat(_p1 + 8, (float) self.m21());
        U.putFloat(_p2, (float) self.m02());
        U.putFloat(_p2 + 4, (float) self.m12());
        U.putFloat(_p2 + 8, (float) self.m22());
        U.putFloat(_p3, (float) self.m03());
        U.putFloat(_p3 + 4, (float) self.m13());
        U.putFloat(_p3 + 8, (float) self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        double _c0 = U.getFloat(address);
        double _c4 = U.getFloat(address + 4);
        double _c8 = U.getFloat(address + 8);
        double _c1 = U.getFloat(_p1);
        double _c5 = U.getFloat(_p1 + 4);
        double _c9 = U.getFloat(_p1 + 8);
        double _c2 = U.getFloat(_p2);
        double _c6 = U.getFloat(_p2 + 4);
        double _c10 = U.getFloat(_p2 + 8);
        double _c3 = U.getFloat(_p3);
        double _c7 = U.getFloat(_p3 + 4);
        double _c11 = U.getFloat(_p3 + 8);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeCMFloatAbsolute(Double3x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMFloatAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4, (float) self.m10());
        U.putFloat(address + 8, (float) self.m20());
        U.putFloat(_p1, (float) self.m01());
        U.putFloat(_p1 + 4, (float) self.m11());
        U.putFloat(_p1 + 8, (float) self.m21());
        U.putFloat(_p2, (float) self.m02());
        U.putFloat(_p2 + 4, (float) self.m12());
        U.putFloat(_p2 + 8, (float) self.m22());
        U.putFloat(_p3, (float) self.m03());
        U.putFloat(_p3 + 4, (float) self.m13());
        U.putFloat(_p3 + 8, (float) self.m23());
        return buf;
    }
    public Double3x4 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMFloatAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        double _c0 = U.getFloat(address);
        double _c4 = U.getFloat(address + 4);
        double _c8 = U.getFloat(address + 8);
        double _c1 = U.getFloat(_p1);
        double _c5 = U.getFloat(_p1 + 4);
        double _c9 = U.getFloat(_p1 + 8);
        double _c2 = U.getFloat(_p2);
        double _c6 = U.getFloat(_p2 + 4);
        double _c10 = U.getFloat(_p2 + 8);
        double _c3 = U.getFloat(_p3);
        double _c7 = U.getFloat(_p3 + 4);
        double _c11 = U.getFloat(_p3 + 8);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public DoubleBuffer storeRMAbsolute(Double3x4 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m01());
        U.putDouble(address + 16, self.m02());
        U.putDouble(address + 24, self.m03());
        U.putDouble(_p1, self.m10());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m12());
        U.putDouble(_p1 + 24, self.m13());
        U.putDouble(_p2, self.m20());
        U.putDouble(_p2 + 8, self.m21());
        U.putDouble(_p2 + 16, self.m22());
        U.putDouble(_p2 + 24, self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8);
        double _c2 = U.getDouble(address + 16);
        double _c3 = U.getDouble(address + 24);
        double _c4 = U.getDouble(_p1);
        double _c5 = U.getDouble(_p1 + 8);
        double _c6 = U.getDouble(_p1 + 16);
        double _c7 = U.getDouble(_p1 + 24);
        double _c8 = U.getDouble(_p2);
        double _c9 = U.getDouble(_p2 + 8);
        double _c10 = U.getDouble(_p2 + 16);
        double _c11 = U.getDouble(_p2 + 24);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeRMAbsolute(Double3x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m01());
        U.putDouble(address + 16, self.m02());
        U.putDouble(address + 24, self.m03());
        U.putDouble(_p1, self.m10());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m12());
        U.putDouble(_p1 + 24, self.m13());
        U.putDouble(_p2, self.m20());
        U.putDouble(_p2 + 8, self.m21());
        U.putDouble(_p2 + 16, self.m22());
        U.putDouble(_p2 + 24, self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8);
        double _c2 = U.getDouble(address + 16);
        double _c3 = U.getDouble(address + 24);
        double _c4 = U.getDouble(_p1);
        double _c5 = U.getDouble(_p1 + 8);
        double _c6 = U.getDouble(_p1 + 16);
        double _c7 = U.getDouble(_p1 + 24);
        double _c8 = U.getDouble(_p2);
        double _c9 = U.getDouble(_p2 + 8);
        double _c10 = U.getDouble(_p2 + 16);
        double _c11 = U.getDouble(_p2 + 24);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public FloatBuffer storeRMAbsolute(Double3x4 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4, (float) self.m01());
        U.putFloat(address + 8, (float) self.m02());
        U.putFloat(address + 12, (float) self.m03());
        U.putFloat(_p1, (float) self.m10());
        U.putFloat(_p1 + 4, (float) self.m11());
        U.putFloat(_p1 + 8, (float) self.m12());
        U.putFloat(_p1 + 12, (float) self.m13());
        U.putFloat(_p2, (float) self.m20());
        U.putFloat(_p2 + 4, (float) self.m21());
        U.putFloat(_p2 + 8, (float) self.m22());
        U.putFloat(_p2 + 12, (float) self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4);
        double _c2 = U.getFloat(address + 8);
        double _c3 = U.getFloat(address + 12);
        double _c4 = U.getFloat(_p1);
        double _c5 = U.getFloat(_p1 + 4);
        double _c6 = U.getFloat(_p1 + 8);
        double _c7 = U.getFloat(_p1 + 12);
        double _c8 = U.getFloat(_p2);
        double _c9 = U.getFloat(_p2 + 4);
        double _c10 = U.getFloat(_p2 + 8);
        double _c11 = U.getFloat(_p2 + 12);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeRMFloatAbsolute(Double3x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMFloatAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4, (float) self.m01());
        U.putFloat(address + 8, (float) self.m02());
        U.putFloat(address + 12, (float) self.m03());
        U.putFloat(_p1, (float) self.m10());
        U.putFloat(_p1 + 4, (float) self.m11());
        U.putFloat(_p1 + 8, (float) self.m12());
        U.putFloat(_p1 + 12, (float) self.m13());
        U.putFloat(_p2, (float) self.m20());
        U.putFloat(_p2 + 4, (float) self.m21());
        U.putFloat(_p2 + 8, (float) self.m22());
        U.putFloat(_p2 + 12, (float) self.m23());
        return buf;
    }
    public Double3x4 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMFloatAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4);
        double _c2 = U.getFloat(address + 8);
        double _c3 = U.getFloat(address + 12);
        double _c4 = U.getFloat(_p1);
        double _c5 = U.getFloat(_p1 + 4);
        double _c6 = U.getFloat(_p1 + 8);
        double _c7 = U.getFloat(_p1 + 12);
        double _c8 = U.getFloat(_p2);
        double _c9 = U.getFloat(_p2 + 4);
        double _c10 = U.getFloat(_p2 + 8);
        double _c11 = U.getFloat(_p2 + 12);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public DoubleBuffer storeCM4x4Absolute(Double3x4 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4Unsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public ByteBuffer storeCM4x4Absolute(Double3x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4Unsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public FloatBuffer storeCM4x4Absolute(Double3x4 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4FloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public ByteBuffer storeCM4x4FloatAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4FloatAbsolute(self, index, buf);
        RAW.storeCM4x4FloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public DoubleBuffer storeRM4x4Absolute(Double3x4 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4Unsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public ByteBuffer storeRM4x4Absolute(Double3x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4Unsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public FloatBuffer storeRM4x4Absolute(Double3x4 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4FloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public ByteBuffer storeRM4x4FloatAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4FloatAbsolute(self, index, buf);
        RAW.storeRM4x4FloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
}
