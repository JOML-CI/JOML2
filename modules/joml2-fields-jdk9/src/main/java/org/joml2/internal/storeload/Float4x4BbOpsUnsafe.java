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

public final class Float4x4BbOpsUnsafe extends Float4x4BbOps {
    private static final Float4x4BbOpsApi API = new Float4x4BbOpsApi();
    private static final Float4x4RawOpsUnsafe RAW = new Float4x4RawOpsUnsafe();

    public FloatBuffer storeCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m20);
        _U.putFloat(address + 12L, self.m30);
        _U.putFloat(address + 16L, self.m01);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m21);
        _U.putFloat(address + 28L, self.m31);
        _U.putFloat(address + 32L, self.m02);
        _U.putFloat(address + 36L, self.m12);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, self.m32);
        _U.putFloat(address + 48L, self.m03);
        _U.putFloat(address + 52L, self.m13);
        _U.putFloat(address + 56L, self.m23);
        _U.putFloat(address + 60L, self.m33);
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m20 = _U.getFloat(address + 8L);
        self.m30 = _U.getFloat(address + 12L);
        self.m01 = _U.getFloat(address + 16L);
        self.m11 = _U.getFloat(address + 20L);
        self.m21 = _U.getFloat(address + 24L);
        self.m31 = _U.getFloat(address + 28L);
        self.m02 = _U.getFloat(address + 32L);
        self.m12 = _U.getFloat(address + 36L);
        self.m22 = _U.getFloat(address + 40L);
        self.m32 = _U.getFloat(address + 44L);
        self.m03 = _U.getFloat(address + 48L);
        self.m13 = _U.getFloat(address + 52L);
        self.m23 = _U.getFloat(address + 56L);
        self.m33 = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m20);
        _U.putFloat(address + 12L, self.m30);
        _U.putFloat(address + 16L, self.m01);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m21);
        _U.putFloat(address + 28L, self.m31);
        _U.putFloat(address + 32L, self.m02);
        _U.putFloat(address + 36L, self.m12);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, self.m32);
        _U.putFloat(address + 48L, self.m03);
        _U.putFloat(address + 52L, self.m13);
        _U.putFloat(address + 56L, self.m23);
        _U.putFloat(address + 60L, self.m33);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m20 = _U.getFloat(address + 8L);
        self.m30 = _U.getFloat(address + 12L);
        self.m01 = _U.getFloat(address + 16L);
        self.m11 = _U.getFloat(address + 20L);
        self.m21 = _U.getFloat(address + 24L);
        self.m31 = _U.getFloat(address + 28L);
        self.m02 = _U.getFloat(address + 32L);
        self.m12 = _U.getFloat(address + 36L);
        self.m22 = _U.getFloat(address + 40L);
        self.m32 = _U.getFloat(address + 44L);
        self.m03 = _U.getFloat(address + 48L);
        self.m13 = _U.getFloat(address + 52L);
        self.m23 = _U.getFloat(address + 56L);
        self.m33 = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float4x4 loadCMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf);
        return RAW.loadCMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    public DoubleBuffer storeCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        return RAW.loadCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
    }

    public ByteBuffer storeCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf);
        RAW.storeCMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public Float4x4 loadCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(self, index, buf);
        return RAW.loadCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    private static Float4x4 loadCMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(self, index, buf);
        return RAW.loadCMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    public FloatBuffer storeRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, self.m02);
        _U.putFloat(address + 12L, self.m03);
        _U.putFloat(address + 16L, self.m10);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m12);
        _U.putFloat(address + 28L, self.m13);
        _U.putFloat(address + 32L, self.m20);
        _U.putFloat(address + 36L, self.m21);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, self.m23);
        _U.putFloat(address + 48L, self.m30);
        _U.putFloat(address + 52L, self.m31);
        _U.putFloat(address + 56L, self.m32);
        _U.putFloat(address + 60L, self.m33);
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m02 = _U.getFloat(address + 8L);
        self.m03 = _U.getFloat(address + 12L);
        self.m10 = _U.getFloat(address + 16L);
        self.m11 = _U.getFloat(address + 20L);
        self.m12 = _U.getFloat(address + 24L);
        self.m13 = _U.getFloat(address + 28L);
        self.m20 = _U.getFloat(address + 32L);
        self.m21 = _U.getFloat(address + 36L);
        self.m22 = _U.getFloat(address + 40L);
        self.m23 = _U.getFloat(address + 44L);
        self.m30 = _U.getFloat(address + 48L);
        self.m31 = _U.getFloat(address + 52L);
        self.m32 = _U.getFloat(address + 56L);
        self.m33 = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, self.m02);
        _U.putFloat(address + 12L, self.m03);
        _U.putFloat(address + 16L, self.m10);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m12);
        _U.putFloat(address + 28L, self.m13);
        _U.putFloat(address + 32L, self.m20);
        _U.putFloat(address + 36L, self.m21);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, self.m23);
        _U.putFloat(address + 48L, self.m30);
        _U.putFloat(address + 52L, self.m31);
        _U.putFloat(address + 56L, self.m32);
        _U.putFloat(address + 60L, self.m33);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m02 = _U.getFloat(address + 8L);
        self.m03 = _U.getFloat(address + 12L);
        self.m10 = _U.getFloat(address + 16L);
        self.m11 = _U.getFloat(address + 20L);
        self.m12 = _U.getFloat(address + 24L);
        self.m13 = _U.getFloat(address + 28L);
        self.m20 = _U.getFloat(address + 32L);
        self.m21 = _U.getFloat(address + 36L);
        self.m22 = _U.getFloat(address + 40L);
        self.m23 = _U.getFloat(address + 44L);
        self.m30 = _U.getFloat(address + 48L);
        self.m31 = _U.getFloat(address + 52L);
        self.m32 = _U.getFloat(address + 56L);
        self.m33 = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float4x4 loadRMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf);
        return RAW.loadRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    public DoubleBuffer storeRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        return RAW.loadRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
    }

    public ByteBuffer storeRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf);
        RAW.storeRMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public Float4x4 loadRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(self, index, buf);
        return RAW.loadRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    private static Float4x4 loadRMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(self, index, buf);
        return RAW.loadRMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    public FloatBuffer storeCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        return RAW.loadCMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L, stride);
    }

    public ByteBuffer storeCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf, stride);
        return RAW.loadCMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    private static Float4x4 loadCMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf, stride);
        return RAW.loadCMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    public DoubleBuffer storeCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        return RAW.loadCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L, stride);
    }

    public ByteBuffer storeCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf, stride);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        RAW.storeCMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x4 loadCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(self, index, buf, stride);
        return RAW.loadCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    private static Float4x4 loadCMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(self, index, buf, stride);
        return RAW.loadCMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    public FloatBuffer storeRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        return RAW.loadRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L, stride);
    }

    public ByteBuffer storeRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf, stride);
        return RAW.loadRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    private static Float4x4 loadRMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf, stride);
        return RAW.loadRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    public DoubleBuffer storeRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        return RAW.loadRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L, stride);
    }

    public ByteBuffer storeRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf, stride);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        RAW.storeRMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x4 loadRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(self, index, buf, stride);
        return RAW.loadRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    private static Float4x4 loadRMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(self, index, buf, stride);
        return RAW.loadRMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }
}
