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

public final class Double3x2BbOpsUnsafe extends Double3x2BbOps {
    private static final Double3x2BbOpsApi API = new Double3x2BbOpsApi();

    public DoubleBuffer storeCMAbsolute(Double3x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, self.m01);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m21);
        return buf;
    }

    public Double3x2 loadCMAbsolute(Double3x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.m00 = _U.getDouble(address);
        self.m10 = _U.getDouble(address + 8L);
        self.m20 = _U.getDouble(address + 16L);
        self.m01 = _U.getDouble(address + 24L);
        self.m11 = _U.getDouble(address + 32L);
        self.m21 = _U.getDouble(address + 40L);
        return self;
    }

    public ByteBuffer storeCMAbsolute(Double3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, self.m01);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m21);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, self.m20);
        _U.putDouble(bytes, address + 24L, self.m01);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, self.m21);
        return buf;
    }

    public Double3x2 loadCMAbsolute(Double3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getDouble(address);
        self.m10 = _U.getDouble(address + 8L);
        self.m20 = _U.getDouble(address + 16L);
        self.m01 = _U.getDouble(address + 24L);
        self.m11 = _U.getDouble(address + 32L);
        self.m21 = _U.getDouble(address + 40L);
        return self;
    }

    private static Double3x2 loadCMAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getDouble(bytes, address);
        self.m10 = _U.getDouble(bytes, address + 8L);
        self.m20 = _U.getDouble(bytes, address + 16L);
        self.m01 = _U.getDouble(bytes, address + 24L);
        self.m11 = _U.getDouble(bytes, address + 32L);
        self.m21 = _U.getDouble(bytes, address + 40L);
        return self;
    }

    public FloatBuffer storeCMAbsolute(Double3x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m10);
        _U.putFloat(address + 8L, (float) self.m20);
        _U.putFloat(address + 12L, (float) self.m01);
        _U.putFloat(address + 16L, (float) self.m11);
        _U.putFloat(address + 20L, (float) self.m21);
        return buf;
    }

    public Double3x2 loadCMAbsolute(Double3x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m20 = _U.getFloat(address + 8L);
        self.m01 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m21 = _U.getFloat(address + 20L);
        return self;
    }

    public ByteBuffer storeCMFloatAbsolute(Double3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m10);
        _U.putFloat(address + 8L, (float) self.m20);
        _U.putFloat(address + 12L, (float) self.m01);
        _U.putFloat(address + 16L, (float) self.m11);
        _U.putFloat(address + 20L, (float) self.m21);
        return buf;
    }

    private static ByteBuffer storeCMFloatAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m10);
        _U.putFloat(bytes, address + 8L, (float) self.m20);
        _U.putFloat(bytes, address + 12L, (float) self.m01);
        _U.putFloat(bytes, address + 16L, (float) self.m11);
        _U.putFloat(bytes, address + 20L, (float) self.m21);
        return buf;
    }

    public Double3x2 loadCMFloatAbsolute(Double3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m20 = _U.getFloat(address + 8L);
        self.m01 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m21 = _U.getFloat(address + 20L);
        return self;
    }

    private static Double3x2 loadCMFloatAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4L);
        self.m20 = _U.getFloat(bytes, address + 8L);
        self.m01 = _U.getFloat(bytes, address + 12L);
        self.m11 = _U.getFloat(bytes, address + 16L);
        self.m21 = _U.getFloat(bytes, address + 20L);
        return self;
    }

    public DoubleBuffer storeRMAbsolute(Double3x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m10);
        _U.putDouble(address + 24L, self.m11);
        _U.putDouble(address + 32L, self.m20);
        _U.putDouble(address + 40L, self.m21);
        return buf;
    }

    public Double3x2 loadRMAbsolute(Double3x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8L);
        self.m10 = _U.getDouble(address + 16L);
        self.m11 = _U.getDouble(address + 24L);
        self.m20 = _U.getDouble(address + 32L);
        self.m21 = _U.getDouble(address + 40L);
        return self;
    }

    public ByteBuffer storeRMAbsolute(Double3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m10);
        _U.putDouble(address + 24L, self.m11);
        _U.putDouble(address + 32L, self.m20);
        _U.putDouble(address + 40L, self.m21);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, self.m10);
        _U.putDouble(bytes, address + 24L, self.m11);
        _U.putDouble(bytes, address + 32L, self.m20);
        _U.putDouble(bytes, address + 40L, self.m21);
        return buf;
    }

    public Double3x2 loadRMAbsolute(Double3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8L);
        self.m10 = _U.getDouble(address + 16L);
        self.m11 = _U.getDouble(address + 24L);
        self.m20 = _U.getDouble(address + 32L);
        self.m21 = _U.getDouble(address + 40L);
        return self;
    }

    private static Double3x2 loadRMAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getDouble(bytes, address);
        self.m01 = _U.getDouble(bytes, address + 8L);
        self.m10 = _U.getDouble(bytes, address + 16L);
        self.m11 = _U.getDouble(bytes, address + 24L);
        self.m20 = _U.getDouble(bytes, address + 32L);
        self.m21 = _U.getDouble(bytes, address + 40L);
        return self;
    }

    public FloatBuffer storeRMAbsolute(Double3x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m01);
        _U.putFloat(address + 8L, (float) self.m10);
        _U.putFloat(address + 12L, (float) self.m11);
        _U.putFloat(address + 16L, (float) self.m20);
        _U.putFloat(address + 20L, (float) self.m21);
        return buf;
    }

    public Double3x2 loadRMAbsolute(Double3x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m10 = _U.getFloat(address + 8L);
        self.m11 = _U.getFloat(address + 12L);
        self.m20 = _U.getFloat(address + 16L);
        self.m21 = _U.getFloat(address + 20L);
        return self;
    }

    public ByteBuffer storeRMFloatAbsolute(Double3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m01);
        _U.putFloat(address + 8L, (float) self.m10);
        _U.putFloat(address + 12L, (float) self.m11);
        _U.putFloat(address + 16L, (float) self.m20);
        _U.putFloat(address + 20L, (float) self.m21);
        return buf;
    }

    private static ByteBuffer storeRMFloatAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m01);
        _U.putFloat(bytes, address + 8L, (float) self.m10);
        _U.putFloat(bytes, address + 12L, (float) self.m11);
        _U.putFloat(bytes, address + 16L, (float) self.m20);
        _U.putFloat(bytes, address + 20L, (float) self.m21);
        return buf;
    }

    public Double3x2 loadRMFloatAbsolute(Double3x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m10 = _U.getFloat(address + 8L);
        self.m11 = _U.getFloat(address + 12L);
        self.m20 = _U.getFloat(address + 16L);
        self.m21 = _U.getFloat(address + 20L);
        return self;
    }

    private static Double3x2 loadRMFloatAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4L);
        self.m10 = _U.getFloat(bytes, address + 8L);
        self.m11 = _U.getFloat(bytes, address + 12L);
        self.m20 = _U.getFloat(bytes, address + 16L);
        self.m21 = _U.getFloat(bytes, address + 20L);
        return self;
    }

    public DoubleBuffer storeCMAbsolute(Double3x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m10);
        _U.putDouble(address + 16, self.m20);
        _U.putDouble(_p1, self.m01);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m21);
        return buf;
    }

    public Double3x2 loadCMAbsolute(Double3x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = _U.getDouble(address);
        self.m10 = _U.getDouble(address + 8);
        self.m20 = _U.getDouble(address + 16);
        self.m01 = _U.getDouble(_p1);
        self.m11 = _U.getDouble(_p1 + 8);
        self.m21 = _U.getDouble(_p1 + 16);
        return self;
    }

    public ByteBuffer storeCMAbsolute(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m10);
        _U.putDouble(address + 16, self.m20);
        _U.putDouble(_p1, self.m01);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m21);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m10);
        _U.putDouble(bytes, address + 16, self.m20);
        _U.putDouble(bytes, _p1, self.m01);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m21);
        return buf;
    }

    public Double3x2 loadCMAbsolute(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = _U.getDouble(address);
        self.m10 = _U.getDouble(address + 8);
        self.m20 = _U.getDouble(address + 16);
        self.m01 = _U.getDouble(_p1);
        self.m11 = _U.getDouble(_p1 + 8);
        self.m21 = _U.getDouble(_p1 + 16);
        return self;
    }

    private static Double3x2 loadCMAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = _U.getDouble(bytes, address);
        self.m10 = _U.getDouble(bytes, address + 8);
        self.m20 = _U.getDouble(bytes, address + 16);
        self.m01 = _U.getDouble(bytes, _p1);
        self.m11 = _U.getDouble(bytes, _p1 + 8);
        self.m21 = _U.getDouble(bytes, _p1 + 16);
        return self;
    }

    public FloatBuffer storeCMAbsolute(Double3x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4, (float) self.m10);
        _U.putFloat(address + 8, (float) self.m20);
        _U.putFloat(_p1, (float) self.m01);
        _U.putFloat(_p1 + 4, (float) self.m11);
        _U.putFloat(_p1 + 8, (float) self.m21);
        return buf;
    }

    public Double3x2 loadCMAbsolute(Double3x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4);
        self.m20 = _U.getFloat(address + 8);
        self.m01 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m21 = _U.getFloat(_p1 + 8);
        return self;
    }

    public ByteBuffer storeCMFloatAbsolute(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMFloatAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4, (float) self.m10);
        _U.putFloat(address + 8, (float) self.m20);
        _U.putFloat(_p1, (float) self.m01);
        _U.putFloat(_p1 + 4, (float) self.m11);
        _U.putFloat(_p1 + 8, (float) self.m21);
        return buf;
    }

    private static ByteBuffer storeCMFloatAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMFloatAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4, (float) self.m10);
        _U.putFloat(bytes, address + 8, (float) self.m20);
        _U.putFloat(bytes, _p1, (float) self.m01);
        _U.putFloat(bytes, _p1 + 4, (float) self.m11);
        _U.putFloat(bytes, _p1 + 8, (float) self.m21);
        return buf;
    }

    public Double3x2 loadCMFloatAbsolute(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMFloatAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4);
        self.m20 = _U.getFloat(address + 8);
        self.m01 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m21 = _U.getFloat(_p1 + 8);
        return self;
    }

    private static Double3x2 loadCMFloatAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMFloatAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4);
        self.m20 = _U.getFloat(bytes, address + 8);
        self.m01 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m21 = _U.getFloat(bytes, _p1 + 8);
        return self;
    }

    public DoubleBuffer storeRMAbsolute(Double3x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m01);
        _U.putDouble(_p1, self.m10);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p2, self.m20);
        _U.putDouble(_p2 + 8, self.m21);
        return buf;
    }

    public Double3x2 loadRMAbsolute(Double3x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8);
        self.m10 = _U.getDouble(_p1);
        self.m11 = _U.getDouble(_p1 + 8);
        self.m20 = _U.getDouble(_p2);
        self.m21 = _U.getDouble(_p2 + 8);
        return self;
    }

    public ByteBuffer storeRMAbsolute(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m01);
        _U.putDouble(_p1, self.m10);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p2, self.m20);
        _U.putDouble(_p2 + 8, self.m21);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m01);
        _U.putDouble(bytes, _p1, self.m10);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p2, self.m20);
        _U.putDouble(bytes, _p2 + 8, self.m21);
        return buf;
    }

    public Double3x2 loadRMAbsolute(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8);
        self.m10 = _U.getDouble(_p1);
        self.m11 = _U.getDouble(_p1 + 8);
        self.m20 = _U.getDouble(_p2);
        self.m21 = _U.getDouble(_p2 + 8);
        return self;
    }

    private static Double3x2 loadRMAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getDouble(bytes, address);
        self.m01 = _U.getDouble(bytes, address + 8);
        self.m10 = _U.getDouble(bytes, _p1);
        self.m11 = _U.getDouble(bytes, _p1 + 8);
        self.m20 = _U.getDouble(bytes, _p2);
        self.m21 = _U.getDouble(bytes, _p2 + 8);
        return self;
    }

    public FloatBuffer storeRMAbsolute(Double3x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4, (float) self.m01);
        _U.putFloat(_p1, (float) self.m10);
        _U.putFloat(_p1 + 4, (float) self.m11);
        _U.putFloat(_p2, (float) self.m20);
        _U.putFloat(_p2 + 4, (float) self.m21);
        return buf;
    }

    public Double3x2 loadRMAbsolute(Double3x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4);
        self.m10 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m20 = _U.getFloat(_p2);
        self.m21 = _U.getFloat(_p2 + 4);
        return self;
    }

    public ByteBuffer storeRMFloatAbsolute(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMFloatAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4, (float) self.m01);
        _U.putFloat(_p1, (float) self.m10);
        _U.putFloat(_p1 + 4, (float) self.m11);
        _U.putFloat(_p2, (float) self.m20);
        _U.putFloat(_p2 + 4, (float) self.m21);
        return buf;
    }

    private static ByteBuffer storeRMFloatAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMFloatAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4, (float) self.m01);
        _U.putFloat(bytes, _p1, (float) self.m10);
        _U.putFloat(bytes, _p1 + 4, (float) self.m11);
        _U.putFloat(bytes, _p2, (float) self.m20);
        _U.putFloat(bytes, _p2 + 4, (float) self.m21);
        return buf;
    }

    public Double3x2 loadRMFloatAbsolute(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMFloatAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4);
        self.m10 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m20 = _U.getFloat(_p2);
        self.m21 = _U.getFloat(_p2 + 4);
        return self;
    }

    private static Double3x2 loadRMFloatAbsoluteElse(Double3x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMFloatAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4);
        self.m10 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m20 = _U.getFloat(bytes, _p2);
        self.m21 = _U.getFloat(bytes, _p2 + 4);
        return self;
    }
}
