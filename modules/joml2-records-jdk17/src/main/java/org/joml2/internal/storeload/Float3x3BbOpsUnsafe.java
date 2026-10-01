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

public final class Float3x3BbOpsUnsafe extends Float3x3BbOps {

    private static final Float3x3BbOpsApi API = new Float3x3BbOpsApi();
    private static final Float3x3RawOpsUnsafe RAW = new Float3x3RawOpsUnsafe();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeCMAbsolute(Float3x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m10());
        U.putFloat(address + 8L, self.m20());
        U.putFloat(address + 12L, self.m01());
        U.putFloat(address + 16L, self.m11());
        U.putFloat(address + 20L, self.m21());
        U.putFloat(address + 24L, self.m02());
        U.putFloat(address + 28L, self.m12());
        U.putFloat(address + 32L, self.m22());
        return buf;
    }
    public Float3x3 loadCMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = U.getFloat(address);
        float _c3 = U.getFloat(address + 4L);
        float _c6 = U.getFloat(address + 8L);
        float _c1 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c7 = U.getFloat(address + 20L);
        float _c2 = U.getFloat(address + 24L);
        float _c5 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeCMAbsolute(Float3x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m10());
        U.putFloat(address + 8L, self.m20());
        U.putFloat(address + 12L, self.m01());
        U.putFloat(address + 16L, self.m11());
        U.putFloat(address + 20L, self.m21());
        U.putFloat(address + 24L, self.m02());
        U.putFloat(address + 28L, self.m12());
        U.putFloat(address + 32L, self.m22());
        return buf;
    }
    public Float3x3 loadCMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = U.getFloat(address);
        float _c3 = U.getFloat(address + 4L);
        float _c6 = U.getFloat(address + 8L);
        float _c1 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c7 = U.getFloat(address + 20L);
        float _c2 = U.getFloat(address + 24L);
        float _c5 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public DoubleBuffer storeCMAbsolute(Float3x3 self, int index, DoubleBuffer buf) {
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
        return buf;
    }
    public Float3x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) U.getDouble(address);
        float _c3 = (float) U.getDouble(address + 8L);
        float _c6 = (float) U.getDouble(address + 16L);
        float _c1 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c7 = (float) U.getDouble(address + 40L);
        float _c2 = (float) U.getDouble(address + 48L);
        float _c5 = (float) U.getDouble(address + 56L);
        float _c8 = (float) U.getDouble(address + 64L);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf);
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
        return buf;
    }
    public Float3x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) U.getDouble(address);
        float _c3 = (float) U.getDouble(address + 8L);
        float _c6 = (float) U.getDouble(address + 16L);
        float _c1 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c7 = (float) U.getDouble(address + 40L);
        float _c2 = (float) U.getDouble(address + 48L);
        float _c5 = (float) U.getDouble(address + 56L);
        float _c8 = (float) U.getDouble(address + 64L);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public FloatBuffer storeRMAbsolute(Float3x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m01());
        U.putFloat(address + 8L, self.m02());
        U.putFloat(address + 12L, self.m10());
        U.putFloat(address + 16L, self.m11());
        U.putFloat(address + 20L, self.m12());
        U.putFloat(address + 24L, self.m20());
        U.putFloat(address + 28L, self.m21());
        U.putFloat(address + 32L, self.m22());
        return buf;
    }
    public Float3x3 loadRMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
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
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeRMAbsolute(Float3x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m01());
        U.putFloat(address + 8L, self.m02());
        U.putFloat(address + 12L, self.m10());
        U.putFloat(address + 16L, self.m11());
        U.putFloat(address + 20L, self.m12());
        U.putFloat(address + 24L, self.m20());
        U.putFloat(address + 28L, self.m21());
        U.putFloat(address + 32L, self.m22());
        return buf;
    }
    public Float3x3 loadRMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
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
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public DoubleBuffer storeRMAbsolute(Float3x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m02());
        U.putDouble(address + 24L, self.m10());
        U.putDouble(address + 32L, self.m11());
        U.putDouble(address + 40L, self.m12());
        U.putDouble(address + 48L, self.m20());
        U.putDouble(address + 56L, self.m21());
        U.putDouble(address + 64L, self.m22());
        return buf;
    }
    public Float3x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
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
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m02());
        U.putDouble(address + 24L, self.m10());
        U.putDouble(address + 32L, self.m11());
        U.putDouble(address + 40L, self.m12());
        U.putDouble(address + 48L, self.m20());
        U.putDouble(address + 56L, self.m21());
        U.putDouble(address + 64L, self.m22());
        return buf;
    }
    public Float3x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(index, buf);
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
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public FloatBuffer storeCMAbsolute(Float3x3 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m10());
        U.putFloat(address + 8, self.m20());
        U.putFloat(_p1, self.m01());
        U.putFloat(_p1 + 4, self.m11());
        U.putFloat(_p1 + 8, self.m21());
        U.putFloat(_p2, self.m02());
        U.putFloat(_p2 + 4, self.m12());
        U.putFloat(_p2 + 8, self.m22());
        return buf;
    }
    public Float3x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = U.getFloat(address);
        float _c3 = U.getFloat(address + 4);
        float _c6 = U.getFloat(address + 8);
        float _c1 = U.getFloat(_p1);
        float _c4 = U.getFloat(_p1 + 4);
        float _c7 = U.getFloat(_p1 + 8);
        float _c2 = U.getFloat(_p2);
        float _c5 = U.getFloat(_p2 + 4);
        float _c8 = U.getFloat(_p2 + 8);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeCMAbsolute(Float3x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m10());
        U.putFloat(address + 8, self.m20());
        U.putFloat(_p1, self.m01());
        U.putFloat(_p1 + 4, self.m11());
        U.putFloat(_p1 + 8, self.m21());
        U.putFloat(_p2, self.m02());
        U.putFloat(_p2 + 4, self.m12());
        U.putFloat(_p2 + 8, self.m22());
        return buf;
    }
    public Float3x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = U.getFloat(address);
        float _c3 = U.getFloat(address + 4);
        float _c6 = U.getFloat(address + 8);
        float _c1 = U.getFloat(_p1);
        float _c4 = U.getFloat(_p1 + 4);
        float _c7 = U.getFloat(_p1 + 8);
        float _c2 = U.getFloat(_p2);
        float _c5 = U.getFloat(_p2 + 4);
        float _c8 = U.getFloat(_p2 + 8);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public DoubleBuffer storeCMAbsolute(Float3x3 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m10());
        U.putDouble(address + 16, self.m20());
        U.putDouble(_p1, self.m01());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m21());
        U.putDouble(_p2, self.m02());
        U.putDouble(_p2 + 8, self.m12());
        U.putDouble(_p2 + 16, self.m22());
        return buf;
    }
    public Float3x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c3 = (float) U.getDouble(address + 8);
        float _c6 = (float) U.getDouble(address + 16);
        float _c1 = (float) U.getDouble(_p1);
        float _c4 = (float) U.getDouble(_p1 + 8);
        float _c7 = (float) U.getDouble(_p1 + 16);
        float _c2 = (float) U.getDouble(_p2);
        float _c5 = (float) U.getDouble(_p2 + 8);
        float _c8 = (float) U.getDouble(_p2 + 16);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m10());
        U.putDouble(address + 16, self.m20());
        U.putDouble(_p1, self.m01());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m21());
        U.putDouble(_p2, self.m02());
        U.putDouble(_p2 + 8, self.m12());
        U.putDouble(_p2 + 16, self.m22());
        return buf;
    }
    public Float3x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c3 = (float) U.getDouble(address + 8);
        float _c6 = (float) U.getDouble(address + 16);
        float _c1 = (float) U.getDouble(_p1);
        float _c4 = (float) U.getDouble(_p1 + 8);
        float _c7 = (float) U.getDouble(_p1 + 16);
        float _c2 = (float) U.getDouble(_p2);
        float _c5 = (float) U.getDouble(_p2 + 8);
        float _c8 = (float) U.getDouble(_p2 + 16);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public FloatBuffer storeRMAbsolute(Float3x3 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m01());
        U.putFloat(address + 8, self.m02());
        U.putFloat(_p1, self.m10());
        U.putFloat(_p1 + 4, self.m11());
        U.putFloat(_p1 + 8, self.m12());
        U.putFloat(_p2, self.m20());
        U.putFloat(_p2 + 4, self.m21());
        U.putFloat(_p2 + 8, self.m22());
        return buf;
    }
    public Float3x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4);
        float _c2 = U.getFloat(address + 8);
        float _c3 = U.getFloat(_p1);
        float _c4 = U.getFloat(_p1 + 4);
        float _c5 = U.getFloat(_p1 + 8);
        float _c6 = U.getFloat(_p2);
        float _c7 = U.getFloat(_p2 + 4);
        float _c8 = U.getFloat(_p2 + 8);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeRMAbsolute(Float3x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m01());
        U.putFloat(address + 8, self.m02());
        U.putFloat(_p1, self.m10());
        U.putFloat(_p1 + 4, self.m11());
        U.putFloat(_p1 + 8, self.m12());
        U.putFloat(_p2, self.m20());
        U.putFloat(_p2 + 4, self.m21());
        U.putFloat(_p2 + 8, self.m22());
        return buf;
    }
    public Float3x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4);
        float _c2 = U.getFloat(address + 8);
        float _c3 = U.getFloat(_p1);
        float _c4 = U.getFloat(_p1 + 4);
        float _c5 = U.getFloat(_p1 + 8);
        float _c6 = U.getFloat(_p2);
        float _c7 = U.getFloat(_p2 + 4);
        float _c8 = U.getFloat(_p2 + 8);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public DoubleBuffer storeRMAbsolute(Float3x3 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m01());
        U.putDouble(address + 16, self.m02());
        U.putDouble(_p1, self.m10());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m12());
        U.putDouble(_p2, self.m20());
        U.putDouble(_p2 + 8, self.m21());
        U.putDouble(_p2 + 16, self.m22());
        return buf;
    }
    public Float3x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8);
        float _c2 = (float) U.getDouble(address + 16);
        float _c3 = (float) U.getDouble(_p1);
        float _c4 = (float) U.getDouble(_p1 + 8);
        float _c5 = (float) U.getDouble(_p1 + 16);
        float _c6 = (float) U.getDouble(_p2);
        float _c7 = (float) U.getDouble(_p2 + 8);
        float _c8 = (float) U.getDouble(_p2 + 16);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m01());
        U.putDouble(address + 16, self.m02());
        U.putDouble(_p1, self.m10());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m12());
        U.putDouble(_p2, self.m20());
        U.putDouble(_p2 + 8, self.m21());
        U.putDouble(_p2 + 16, self.m22());
        return buf;
    }
    public Float3x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8);
        float _c2 = (float) U.getDouble(address + 16);
        float _c3 = (float) U.getDouble(_p1);
        float _c4 = (float) U.getDouble(_p1 + 8);
        float _c5 = (float) U.getDouble(_p1 + 16);
        float _c6 = (float) U.getDouble(_p2);
        float _c7 = (float) U.getDouble(_p2 + 8);
        float _c8 = (float) U.getDouble(_p2 + 16);
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public FloatBuffer storeCM4x4Absolute(Float3x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m10());
        U.putFloat(address + 8L, self.m20());
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m01());
        U.putFloat(address + 20L, self.m11());
        U.putFloat(address + 24L, self.m21());
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.m02());
        U.putFloat(address + 36L, self.m12());
        U.putFloat(address + 40L, self.m22());
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM4x4Absolute(Float3x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m10());
        U.putFloat(address + 8L, self.m20());
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m01());
        U.putFloat(address + 20L, self.m11());
        U.putFloat(address + 24L, self.m21());
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.m02());
        U.putFloat(address + 36L, self.m12());
        U.putFloat(address + 40L, self.m22());
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeCM4x4Absolute(Float3x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4DoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public ByteBuffer storeCM4x4DoubleAbsolute(Float3x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4DoubleAbsolute(self, index, buf);
        RAW.storeCM4x4DoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public FloatBuffer storeRM4x4Absolute(Float3x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m01());
        U.putFloat(address + 8L, self.m02());
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m10());
        U.putFloat(address + 20L, self.m11());
        U.putFloat(address + 24L, self.m12());
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.m20());
        U.putFloat(address + 36L, self.m21());
        U.putFloat(address + 40L, self.m22());
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM4x4Absolute(Float3x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4L, self.m01());
        U.putFloat(address + 8L, self.m02());
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m10());
        U.putFloat(address + 20L, self.m11());
        U.putFloat(address + 24L, self.m12());
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.m20());
        U.putFloat(address + 36L, self.m21());
        U.putFloat(address + 40L, self.m22());
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeRM4x4Absolute(Float3x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4DoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public ByteBuffer storeRM4x4DoubleAbsolute(Float3x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4DoubleAbsolute(self, index, buf);
        RAW.storeRM4x4DoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
}
