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

public final class Float2x3BbOpsUnsafe extends Float2x3BbOps {
    private static final Float2x3BbOpsApi API = new Float2x3BbOpsApi();
    private static final Float2x3RawOpsUnsafe RAW = new Float2x3RawOpsUnsafe();

    public FloatBuffer storeCMAbsolute(Float2x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, self.m01());
        _U.putFloat(address + 12L, self.m11());
        _U.putFloat(address + 16L, self.m02());
        _U.putFloat(address + 20L, self.m12());
        return buf;
    }

    public Float2x3 loadCMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = _U.getFloat(address);
        float _c3 = _U.getFloat(address + 4L);
        float _c1 = _U.getFloat(address + 8L);
        float _c4 = _U.getFloat(address + 12L);
        float _c2 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public ByteBuffer storeCMAbsolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, self.m01());
        _U.putFloat(address + 12L, self.m11());
        _U.putFloat(address + 16L, self.m02());
        _U.putFloat(address + 20L, self.m12());
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m10());
        _U.putFloat(bytes, address + 8L, self.m01());
        _U.putFloat(bytes, address + 12L, self.m11());
        _U.putFloat(bytes, address + 16L, self.m02());
        _U.putFloat(bytes, address + 20L, self.m12());
        return buf;
    }

    public Float2x3 loadCMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = _U.getFloat(address);
        float _c3 = _U.getFloat(address + 4L);
        float _c1 = _U.getFloat(address + 8L);
        float _c4 = _U.getFloat(address + 12L);
        float _c2 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Float2x3 loadCMAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = _U.getFloat(bytes, address);
        float _c3 = _U.getFloat(bytes, address + 4L);
        float _c1 = _U.getFloat(bytes, address + 8L);
        float _c4 = _U.getFloat(bytes, address + 12L);
        float _c2 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public DoubleBuffer storeCMAbsolute(Float2x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, self.m01());
        _U.putDouble(address + 24L, self.m11());
        _U.putDouble(address + 32L, self.m02());
        _U.putDouble(address + 40L, self.m12());
        return buf;
    }

    public Float2x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) _U.getDouble(address);
        float _c3 = (float) _U.getDouble(address + 8L);
        float _c1 = (float) _U.getDouble(address + 16L);
        float _c4 = (float) _U.getDouble(address + 24L);
        float _c2 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public ByteBuffer storeCMDoubleAbsolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, self.m01());
        _U.putDouble(address + 24L, self.m11());
        _U.putDouble(address + 32L, self.m02());
        _U.putDouble(address + 40L, self.m12());
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m10());
        _U.putDouble(bytes, address + 16L, self.m01());
        _U.putDouble(bytes, address + 24L, self.m11());
        _U.putDouble(bytes, address + 32L, self.m02());
        _U.putDouble(bytes, address + 40L, self.m12());
        return buf;
    }

    public Float2x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) _U.getDouble(address);
        float _c3 = (float) _U.getDouble(address + 8L);
        float _c1 = (float) _U.getDouble(address + 16L);
        float _c4 = (float) _U.getDouble(address + 24L);
        float _c2 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Float2x3 loadCMDoubleAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c3 = (float) _U.getDouble(bytes, address + 8L);
        float _c1 = (float) _U.getDouble(bytes, address + 16L);
        float _c4 = (float) _U.getDouble(bytes, address + 24L);
        float _c2 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public FloatBuffer storeRMAbsolute(Float2x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, self.m02());
        _U.putFloat(address + 12L, self.m10());
        _U.putFloat(address + 16L, self.m11());
        _U.putFloat(address + 20L, self.m12());
        return buf;
    }

    public Float2x3 loadRMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public ByteBuffer storeRMAbsolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, self.m02());
        _U.putFloat(address + 12L, self.m10());
        _U.putFloat(address + 16L, self.m11());
        _U.putFloat(address + 20L, self.m12());
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m01());
        _U.putFloat(bytes, address + 8L, self.m02());
        _U.putFloat(bytes, address + 12L, self.m10());
        _U.putFloat(bytes, address + 16L, self.m11());
        _U.putFloat(bytes, address + 20L, self.m12());
        return buf;
    }

    public Float2x3 loadRMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Float2x3 loadRMAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4L);
        float _c2 = _U.getFloat(bytes, address + 8L);
        float _c3 = _U.getFloat(bytes, address + 12L);
        float _c4 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public DoubleBuffer storeRMAbsolute(Float2x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, self.m02());
        _U.putDouble(address + 24L, self.m10());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, self.m12());
        return buf;
    }

    public Float2x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public ByteBuffer storeRMDoubleAbsolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, self.m02());
        _U.putDouble(address + 24L, self.m10());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, self.m12());
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, self.m02());
        _U.putDouble(bytes, address + 24L, self.m10());
        _U.putDouble(bytes, address + 32L, self.m11());
        _U.putDouble(bytes, address + 40L, self.m12());
        return buf;
    }

    public Float2x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Float2x3 loadRMDoubleAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8L);
        float _c2 = (float) _U.getDouble(bytes, address + 16L);
        float _c3 = (float) _U.getDouble(bytes, address + 24L);
        float _c4 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public FloatBuffer storeCMAbsolute(Float2x3 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m10());
        _U.putFloat(_p1, self.m01());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p2, self.m02());
        _U.putFloat(_p2 + 4, self.m12());
        return buf;
    }

    public Float2x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = _U.getFloat(address);
        float _c3 = _U.getFloat(address + 4);
        float _c1 = _U.getFloat(_p1);
        float _c4 = _U.getFloat(_p1 + 4);
        float _c2 = _U.getFloat(_p2);
        float _c5 = _U.getFloat(_p2 + 4);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public ByteBuffer storeCMAbsolute(Float2x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m10());
        _U.putFloat(_p1, self.m01());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p2, self.m02());
        _U.putFloat(_p2 + 4, self.m12());
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float2x3 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4, self.m10());
        _U.putFloat(bytes, _p1, self.m01());
        _U.putFloat(bytes, _p1 + 4, self.m11());
        _U.putFloat(bytes, _p2, self.m02());
        _U.putFloat(bytes, _p2 + 4, self.m12());
        return buf;
    }

    public Float2x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = _U.getFloat(address);
        float _c3 = _U.getFloat(address + 4);
        float _c1 = _U.getFloat(_p1);
        float _c4 = _U.getFloat(_p1 + 4);
        float _c2 = _U.getFloat(_p2);
        float _c5 = _U.getFloat(_p2 + 4);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Float2x3 loadCMAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = _U.getFloat(bytes, address);
        float _c3 = _U.getFloat(bytes, address + 4);
        float _c1 = _U.getFloat(bytes, _p1);
        float _c4 = _U.getFloat(bytes, _p1 + 4);
        float _c2 = _U.getFloat(bytes, _p2);
        float _c5 = _U.getFloat(bytes, _p2 + 4);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public DoubleBuffer storeCMAbsolute(Float2x3 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m10());
        _U.putDouble(_p1, self.m01());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p2, self.m02());
        _U.putDouble(_p2 + 8, self.m12());
        return buf;
    }

    public Float2x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) _U.getDouble(address);
        float _c3 = (float) _U.getDouble(address + 8);
        float _c1 = (float) _U.getDouble(_p1);
        float _c4 = (float) _U.getDouble(_p1 + 8);
        float _c2 = (float) _U.getDouble(_p2);
        float _c5 = (float) _U.getDouble(_p2 + 8);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public ByteBuffer storeCMDoubleAbsolute(Float2x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m10());
        _U.putDouble(_p1, self.m01());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p2, self.m02());
        _U.putDouble(_p2 + 8, self.m12());
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float2x3 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8, self.m10());
        _U.putDouble(bytes, _p1, self.m01());
        _U.putDouble(bytes, _p1 + 8, self.m11());
        _U.putDouble(bytes, _p2, self.m02());
        _U.putDouble(bytes, _p2 + 8, self.m12());
        return buf;
    }

    public Float2x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) _U.getDouble(address);
        float _c3 = (float) _U.getDouble(address + 8);
        float _c1 = (float) _U.getDouble(_p1);
        float _c4 = (float) _U.getDouble(_p1 + 8);
        float _c2 = (float) _U.getDouble(_p2);
        float _c5 = (float) _U.getDouble(_p2 + 8);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Float2x3 loadCMDoubleAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c3 = (float) _U.getDouble(bytes, address + 8);
        float _c1 = (float) _U.getDouble(bytes, _p1);
        float _c4 = (float) _U.getDouble(bytes, _p1 + 8);
        float _c2 = (float) _U.getDouble(bytes, _p2);
        float _c5 = (float) _U.getDouble(bytes, _p2 + 8);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public FloatBuffer storeRMAbsolute(Float2x3 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m01());
        _U.putFloat(address + 8, self.m02());
        _U.putFloat(_p1, self.m10());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p1 + 8, self.m12());
        return buf;
    }

    public Float2x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4);
        float _c2 = _U.getFloat(address + 8);
        float _c3 = _U.getFloat(_p1);
        float _c4 = _U.getFloat(_p1 + 4);
        float _c5 = _U.getFloat(_p1 + 8);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public ByteBuffer storeRMAbsolute(Float2x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m01());
        _U.putFloat(address + 8, self.m02());
        _U.putFloat(_p1, self.m10());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p1 + 8, self.m12());
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float2x3 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4, self.m01());
        _U.putFloat(bytes, address + 8, self.m02());
        _U.putFloat(bytes, _p1, self.m10());
        _U.putFloat(bytes, _p1 + 4, self.m11());
        _U.putFloat(bytes, _p1 + 8, self.m12());
        return buf;
    }

    public Float2x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4);
        float _c2 = _U.getFloat(address + 8);
        float _c3 = _U.getFloat(_p1);
        float _c4 = _U.getFloat(_p1 + 4);
        float _c5 = _U.getFloat(_p1 + 8);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Float2x3 loadRMAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4);
        float _c2 = _U.getFloat(bytes, address + 8);
        float _c3 = _U.getFloat(bytes, _p1);
        float _c4 = _U.getFloat(bytes, _p1 + 4);
        float _c5 = _U.getFloat(bytes, _p1 + 8);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public DoubleBuffer storeRMAbsolute(Float2x3 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m01());
        _U.putDouble(address + 16, self.m02());
        _U.putDouble(_p1, self.m10());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m12());
        return buf;
    }

    public Float2x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8);
        float _c2 = (float) _U.getDouble(address + 16);
        float _c3 = (float) _U.getDouble(_p1);
        float _c4 = (float) _U.getDouble(_p1 + 8);
        float _c5 = (float) _U.getDouble(_p1 + 16);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public ByteBuffer storeRMDoubleAbsolute(Float2x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m01());
        _U.putDouble(address + 16, self.m02());
        _U.putDouble(_p1, self.m10());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m12());
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float2x3 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8, self.m01());
        _U.putDouble(bytes, address + 16, self.m02());
        _U.putDouble(bytes, _p1, self.m10());
        _U.putDouble(bytes, _p1 + 8, self.m11());
        _U.putDouble(bytes, _p1 + 16, self.m12());
        return buf;
    }

    public Float2x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8);
        float _c2 = (float) _U.getDouble(address + 16);
        float _c3 = (float) _U.getDouble(_p1);
        float _c4 = (float) _U.getDouble(_p1 + 8);
        float _c5 = (float) _U.getDouble(_p1 + 16);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    private static Float2x3 loadRMDoubleAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8);
        float _c2 = (float) _U.getDouble(bytes, address + 16);
        float _c3 = (float) _U.getDouble(bytes, _p1);
        float _c4 = (float) _U.getDouble(bytes, _p1 + 8);
        float _c5 = (float) _U.getDouble(bytes, _p1 + 16);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public FloatBuffer storeCM3x3Absolute(Float2x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, self.m01());
        _U.putFloat(address + 16L, self.m11());
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, self.m02());
        _U.putFloat(address + 28L, self.m12());
        _U.putFloat(address + 32L, 1.0f);
        return buf;
    }

    public ByteBuffer storeCM3x3Absolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM3x3AbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, self.m01());
        _U.putFloat(address + 16L, self.m11());
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, self.m02());
        _U.putFloat(address + 28L, self.m12());
        _U.putFloat(address + 32L, 1.0f);
        return buf;
    }

    private static ByteBuffer storeCM3x3AbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCM3x3Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m10());
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, self.m01());
        _U.putFloat(bytes, address + 16L, self.m11());
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, self.m02());
        _U.putFloat(bytes, address + 28L, self.m12());
        _U.putFloat(bytes, address + 32L, 1.0f);
        return buf;
    }

    public DoubleBuffer storeCM3x3Absolute(Float2x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m01());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, self.m02());
        _U.putDouble(address + 56L, self.m12());
        _U.putDouble(address + 64L, 1.0);
        return buf;
    }

    public ByteBuffer storeCM3x3DoubleAbsolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM3x3DoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m01());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, self.m02());
        _U.putDouble(address + 56L, self.m12());
        _U.putDouble(address + 64L, 1.0);
        return buf;
    }

    private static ByteBuffer storeCM3x3DoubleAbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCM3x3DoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m10());
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m01());
        _U.putDouble(bytes, address + 32L, self.m11());
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, self.m02());
        _U.putDouble(bytes, address + 56L, self.m12());
        _U.putDouble(bytes, address + 64L, 1.0);
        return buf;
    }

    public FloatBuffer storeRM3x3Absolute(Float2x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, self.m02());
        _U.putFloat(address + 12L, self.m10());
        _U.putFloat(address + 16L, self.m11());
        _U.putFloat(address + 20L, self.m12());
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return buf;
    }

    public ByteBuffer storeRM3x3Absolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM3x3AbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, self.m02());
        _U.putFloat(address + 12L, self.m10());
        _U.putFloat(address + 16L, self.m11());
        _U.putFloat(address + 20L, self.m12());
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return buf;
    }

    private static ByteBuffer storeRM3x3AbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRM3x3Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m01());
        _U.putFloat(bytes, address + 8L, self.m02());
        _U.putFloat(bytes, address + 12L, self.m10());
        _U.putFloat(bytes, address + 16L, self.m11());
        _U.putFloat(bytes, address + 20L, self.m12());
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return buf;
    }

    public DoubleBuffer storeRM3x3Absolute(Float2x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, self.m02());
        _U.putDouble(address + 24L, self.m10());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, self.m12());
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return buf;
    }

    public ByteBuffer storeRM3x3DoubleAbsolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM3x3DoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, self.m02());
        _U.putDouble(address + 24L, self.m10());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, self.m12());
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return buf;
    }

    private static ByteBuffer storeRM3x3DoubleAbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRM3x3DoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, self.m02());
        _U.putDouble(bytes, address + 24L, self.m10());
        _U.putDouble(bytes, address + 32L, self.m11());
        _U.putDouble(bytes, address + 40L, self.m12());
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return buf;
    }

    public FloatBuffer storeCM4x4Absolute(Float2x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m01());
        _U.putFloat(address + 20L, self.m11());
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 0.0f);
        _U.putFloat(address + 36L, 0.0f);
        _U.putFloat(address + 40L, 1.0f);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, self.m02());
        _U.putFloat(address + 52L, self.m12());
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return buf;
    }

    public ByteBuffer storeCM4x4Absolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM4x4AbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m01());
        _U.putFloat(address + 20L, self.m11());
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 0.0f);
        _U.putFloat(address + 36L, 0.0f);
        _U.putFloat(address + 40L, 1.0f);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, self.m02());
        _U.putFloat(address + 52L, self.m12());
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return buf;
    }

    private static ByteBuffer storeCM4x4AbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4Heap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public DoubleBuffer storeCM4x4Absolute(Float2x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m01());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 0.0);
        _U.putDouble(address + 72L, 0.0);
        _U.putDouble(address + 80L, 1.0);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, self.m02());
        _U.putDouble(address + 104L, self.m12());
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return buf;
    }

    public ByteBuffer storeCM4x4DoubleAbsolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM4x4DoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m01());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 0.0);
        _U.putDouble(address + 72L, 0.0);
        _U.putDouble(address + 80L, 1.0);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, self.m02());
        _U.putDouble(address + 104L, self.m12());
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return buf;
    }

    private static ByteBuffer storeCM4x4DoubleAbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCM4x4DoubleAbsolute(self, index, buf);
        RAW.storeCM4x4DoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public FloatBuffer storeRM4x4Absolute(Float2x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, self.m02());
        _U.putFloat(address + 16L, self.m10());
        _U.putFloat(address + 20L, self.m11());
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, self.m12());
        _U.putFloat(address + 32L, 0.0f);
        _U.putFloat(address + 36L, 0.0f);
        _U.putFloat(address + 40L, 1.0f);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return buf;
    }

    public ByteBuffer storeRM4x4Absolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM4x4AbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, self.m02());
        _U.putFloat(address + 16L, self.m10());
        _U.putFloat(address + 20L, self.m11());
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, self.m12());
        _U.putFloat(address + 32L, 0.0f);
        _U.putFloat(address + 36L, 0.0f);
        _U.putFloat(address + 40L, 1.0f);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return buf;
    }

    private static ByteBuffer storeRM4x4AbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4Heap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public DoubleBuffer storeRM4x4Absolute(Float2x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m02());
        _U.putDouble(address + 32L, self.m10());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, self.m12());
        _U.putDouble(address + 64L, 0.0);
        _U.putDouble(address + 72L, 0.0);
        _U.putDouble(address + 80L, 1.0);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return buf;
    }

    public ByteBuffer storeRM4x4DoubleAbsolute(Float2x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM4x4DoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m02());
        _U.putDouble(address + 32L, self.m10());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, self.m12());
        _U.putDouble(address + 64L, 0.0);
        _U.putDouble(address + 72L, 0.0);
        _U.putDouble(address + 80L, 1.0);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return buf;
    }

    private static ByteBuffer storeRM4x4DoubleAbsoluteElse(Float2x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRM4x4DoubleAbsolute(self, index, buf);
        RAW.storeRM4x4DoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }
}
