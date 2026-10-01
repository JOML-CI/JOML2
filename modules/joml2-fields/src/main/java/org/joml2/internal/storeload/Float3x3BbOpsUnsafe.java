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

public final class Float3x3BbOpsUnsafe extends Float3x3BbOps {
    private static final Float3x3BbOpsApi API = new Float3x3BbOpsApi();
    private static final Float3x3RawOpsUnsafe RAW = new Float3x3RawOpsUnsafe();

    public FloatBuffer storeCMAbsolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m20);
        _U.putFloat(address + 12L, self.m01);
        _U.putFloat(address + 16L, self.m11);
        _U.putFloat(address + 20L, self.m21);
        _U.putFloat(address + 24L, self.m02);
        _U.putFloat(address + 28L, self.m12);
        _U.putFloat(address + 32L, self.m22);
        return buf;
    }

    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m20 = _U.getFloat(address + 8L);
        self.m01 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m21 = _U.getFloat(address + 20L);
        self.m02 = _U.getFloat(address + 24L);
        self.m12 = _U.getFloat(address + 28L);
        self.m22 = _U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m20);
        _U.putFloat(address + 12L, self.m01);
        _U.putFloat(address + 16L, self.m11);
        _U.putFloat(address + 20L, self.m21);
        _U.putFloat(address + 24L, self.m02);
        _U.putFloat(address + 28L, self.m12);
        _U.putFloat(address + 32L, self.m22);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m10);
        _U.putFloat(bytes, address + 8L, self.m20);
        _U.putFloat(bytes, address + 12L, self.m01);
        _U.putFloat(bytes, address + 16L, self.m11);
        _U.putFloat(bytes, address + 20L, self.m21);
        _U.putFloat(bytes, address + 24L, self.m02);
        _U.putFloat(bytes, address + 28L, self.m12);
        _U.putFloat(bytes, address + 32L, self.m22);
        return buf;
    }

    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m20 = _U.getFloat(address + 8L);
        self.m01 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m21 = _U.getFloat(address + 20L);
        self.m02 = _U.getFloat(address + 24L);
        self.m12 = _U.getFloat(address + 28L);
        self.m22 = _U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadCMAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4L);
        self.m20 = _U.getFloat(bytes, address + 8L);
        self.m01 = _U.getFloat(bytes, address + 12L);
        self.m11 = _U.getFloat(bytes, address + 16L);
        self.m21 = _U.getFloat(bytes, address + 20L);
        self.m02 = _U.getFloat(bytes, address + 24L);
        self.m12 = _U.getFloat(bytes, address + 28L);
        self.m22 = _U.getFloat(bytes, address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public DoubleBuffer storeCMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, self.m01);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m21);
        _U.putDouble(address + 48L, self.m02);
        _U.putDouble(address + 56L, self.m12);
        _U.putDouble(address + 64L, self.m22);
        return buf;
    }

    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8L);
        self.m20 = (float) _U.getDouble(address + 16L);
        self.m01 = (float) _U.getDouble(address + 24L);
        self.m11 = (float) _U.getDouble(address + 32L);
        self.m21 = (float) _U.getDouble(address + 40L);
        self.m02 = (float) _U.getDouble(address + 48L);
        self.m12 = (float) _U.getDouble(address + 56L);
        self.m22 = (float) _U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, self.m01);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m21);
        _U.putDouble(address + 48L, self.m02);
        _U.putDouble(address + 56L, self.m12);
        _U.putDouble(address + 64L, self.m22);
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, self.m20);
        _U.putDouble(bytes, address + 24L, self.m01);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, self.m21);
        _U.putDouble(bytes, address + 48L, self.m02);
        _U.putDouble(bytes, address + 56L, self.m12);
        _U.putDouble(bytes, address + 64L, self.m22);
        return buf;
    }

    public Float3x3 loadCMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8L);
        self.m20 = (float) _U.getDouble(address + 16L);
        self.m01 = (float) _U.getDouble(address + 24L);
        self.m11 = (float) _U.getDouble(address + 32L);
        self.m21 = (float) _U.getDouble(address + 40L);
        self.m02 = (float) _U.getDouble(address + 48L);
        self.m12 = (float) _U.getDouble(address + 56L);
        self.m22 = (float) _U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadCMDoubleAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m10 = (float) _U.getDouble(bytes, address + 8L);
        self.m20 = (float) _U.getDouble(bytes, address + 16L);
        self.m01 = (float) _U.getDouble(bytes, address + 24L);
        self.m11 = (float) _U.getDouble(bytes, address + 32L);
        self.m21 = (float) _U.getDouble(bytes, address + 40L);
        self.m02 = (float) _U.getDouble(bytes, address + 48L);
        self.m12 = (float) _U.getDouble(bytes, address + 56L);
        self.m22 = (float) _U.getDouble(bytes, address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public FloatBuffer storeRMAbsolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, self.m02);
        _U.putFloat(address + 12L, self.m10);
        _U.putFloat(address + 16L, self.m11);
        _U.putFloat(address + 20L, self.m12);
        _U.putFloat(address + 24L, self.m20);
        _U.putFloat(address + 28L, self.m21);
        _U.putFloat(address + 32L, self.m22);
        return buf;
    }

    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m02 = _U.getFloat(address + 8L);
        self.m10 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m12 = _U.getFloat(address + 20L);
        self.m20 = _U.getFloat(address + 24L);
        self.m21 = _U.getFloat(address + 28L);
        self.m22 = _U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, self.m02);
        _U.putFloat(address + 12L, self.m10);
        _U.putFloat(address + 16L, self.m11);
        _U.putFloat(address + 20L, self.m12);
        _U.putFloat(address + 24L, self.m20);
        _U.putFloat(address + 28L, self.m21);
        _U.putFloat(address + 32L, self.m22);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4L, self.m01);
        _U.putFloat(bytes, address + 8L, self.m02);
        _U.putFloat(bytes, address + 12L, self.m10);
        _U.putFloat(bytes, address + 16L, self.m11);
        _U.putFloat(bytes, address + 20L, self.m12);
        _U.putFloat(bytes, address + 24L, self.m20);
        _U.putFloat(bytes, address + 28L, self.m21);
        _U.putFloat(bytes, address + 32L, self.m22);
        return buf;
    }

    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m02 = _U.getFloat(address + 8L);
        self.m10 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m12 = _U.getFloat(address + 20L);
        self.m20 = _U.getFloat(address + 24L);
        self.m21 = _U.getFloat(address + 28L);
        self.m22 = _U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadRMAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4L);
        self.m02 = _U.getFloat(bytes, address + 8L);
        self.m10 = _U.getFloat(bytes, address + 12L);
        self.m11 = _U.getFloat(bytes, address + 16L);
        self.m12 = _U.getFloat(bytes, address + 20L);
        self.m20 = _U.getFloat(bytes, address + 24L);
        self.m21 = _U.getFloat(bytes, address + 28L);
        self.m22 = _U.getFloat(bytes, address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public DoubleBuffer storeRMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, self.m10);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m12);
        _U.putDouble(address + 48L, self.m20);
        _U.putDouble(address + 56L, self.m21);
        _U.putDouble(address + 64L, self.m22);
        return buf;
    }

    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.m00 = (float) _U.getDouble(address);
        self.m01 = (float) _U.getDouble(address + 8L);
        self.m02 = (float) _U.getDouble(address + 16L);
        self.m10 = (float) _U.getDouble(address + 24L);
        self.m11 = (float) _U.getDouble(address + 32L);
        self.m12 = (float) _U.getDouble(address + 40L);
        self.m20 = (float) _U.getDouble(address + 48L);
        self.m21 = (float) _U.getDouble(address + 56L);
        self.m22 = (float) _U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, self.m10);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m12);
        _U.putDouble(address + 48L, self.m20);
        _U.putDouble(address + 56L, self.m21);
        _U.putDouble(address + 64L, self.m22);
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, self.m02);
        _U.putDouble(bytes, address + 24L, self.m10);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, self.m12);
        _U.putDouble(bytes, address + 48L, self.m20);
        _U.putDouble(bytes, address + 56L, self.m21);
        _U.putDouble(bytes, address + 64L, self.m22);
        return buf;
    }

    public Float3x3 loadRMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = (float) _U.getDouble(address);
        self.m01 = (float) _U.getDouble(address + 8L);
        self.m02 = (float) _U.getDouble(address + 16L);
        self.m10 = (float) _U.getDouble(address + 24L);
        self.m11 = (float) _U.getDouble(address + 32L);
        self.m12 = (float) _U.getDouble(address + 40L);
        self.m20 = (float) _U.getDouble(address + 48L);
        self.m21 = (float) _U.getDouble(address + 56L);
        self.m22 = (float) _U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadRMDoubleAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m01 = (float) _U.getDouble(bytes, address + 8L);
        self.m02 = (float) _U.getDouble(bytes, address + 16L);
        self.m10 = (float) _U.getDouble(bytes, address + 24L);
        self.m11 = (float) _U.getDouble(bytes, address + 32L);
        self.m12 = (float) _U.getDouble(bytes, address + 40L);
        self.m20 = (float) _U.getDouble(bytes, address + 48L);
        self.m21 = (float) _U.getDouble(bytes, address + 56L);
        self.m22 = (float) _U.getDouble(bytes, address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public FloatBuffer storeCMAbsolute(Float3x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m10);
        _U.putFloat(address + 8, self.m20);
        _U.putFloat(_p1, self.m01);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p1 + 8, self.m21);
        _U.putFloat(_p2, self.m02);
        _U.putFloat(_p2 + 4, self.m12);
        _U.putFloat(_p2 + 8, self.m22);
        return buf;
    }

    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4);
        self.m20 = _U.getFloat(address + 8);
        self.m01 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m21 = _U.getFloat(_p1 + 8);
        self.m02 = _U.getFloat(_p2);
        self.m12 = _U.getFloat(_p2 + 4);
        self.m22 = _U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m10);
        _U.putFloat(address + 8, self.m20);
        _U.putFloat(_p1, self.m01);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p1 + 8, self.m21);
        _U.putFloat(_p2, self.m02);
        _U.putFloat(_p2 + 4, self.m12);
        _U.putFloat(_p2 + 8, self.m22);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4, self.m10);
        _U.putFloat(bytes, address + 8, self.m20);
        _U.putFloat(bytes, _p1, self.m01);
        _U.putFloat(bytes, _p1 + 4, self.m11);
        _U.putFloat(bytes, _p1 + 8, self.m21);
        _U.putFloat(bytes, _p2, self.m02);
        _U.putFloat(bytes, _p2 + 4, self.m12);
        _U.putFloat(bytes, _p2 + 8, self.m22);
        return buf;
    }

    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4);
        self.m20 = _U.getFloat(address + 8);
        self.m01 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m21 = _U.getFloat(_p1 + 8);
        self.m02 = _U.getFloat(_p2);
        self.m12 = _U.getFloat(_p2 + 4);
        self.m22 = _U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadCMAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4);
        self.m20 = _U.getFloat(bytes, address + 8);
        self.m01 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m21 = _U.getFloat(bytes, _p1 + 8);
        self.m02 = _U.getFloat(bytes, _p2);
        self.m12 = _U.getFloat(bytes, _p2 + 4);
        self.m22 = _U.getFloat(bytes, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public DoubleBuffer storeCMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m10);
        _U.putDouble(address + 16, self.m20);
        _U.putDouble(_p1, self.m01);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m21);
        _U.putDouble(_p2, self.m02);
        _U.putDouble(_p2 + 8, self.m12);
        _U.putDouble(_p2 + 16, self.m22);
        return buf;
    }

    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8);
        self.m20 = (float) _U.getDouble(address + 16);
        self.m01 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m21 = (float) _U.getDouble(_p1 + 16);
        self.m02 = (float) _U.getDouble(_p2);
        self.m12 = (float) _U.getDouble(_p2 + 8);
        self.m22 = (float) _U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m10);
        _U.putDouble(address + 16, self.m20);
        _U.putDouble(_p1, self.m01);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m21);
        _U.putDouble(_p2, self.m02);
        _U.putDouble(_p2 + 8, self.m12);
        _U.putDouble(_p2 + 16, self.m22);
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m10);
        _U.putDouble(bytes, address + 16, self.m20);
        _U.putDouble(bytes, _p1, self.m01);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m21);
        _U.putDouble(bytes, _p2, self.m02);
        _U.putDouble(bytes, _p2 + 8, self.m12);
        _U.putDouble(bytes, _p2 + 16, self.m22);
        return buf;
    }

    public Float3x3 loadCMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) _U.getDouble(address);
        self.m10 = (float) _U.getDouble(address + 8);
        self.m20 = (float) _U.getDouble(address + 16);
        self.m01 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m21 = (float) _U.getDouble(_p1 + 16);
        self.m02 = (float) _U.getDouble(_p2);
        self.m12 = (float) _U.getDouble(_p2 + 8);
        self.m22 = (float) _U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadCMDoubleAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m10 = (float) _U.getDouble(bytes, address + 8);
        self.m20 = (float) _U.getDouble(bytes, address + 16);
        self.m01 = (float) _U.getDouble(bytes, _p1);
        self.m11 = (float) _U.getDouble(bytes, _p1 + 8);
        self.m21 = (float) _U.getDouble(bytes, _p1 + 16);
        self.m02 = (float) _U.getDouble(bytes, _p2);
        self.m12 = (float) _U.getDouble(bytes, _p2 + 8);
        self.m22 = (float) _U.getDouble(bytes, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public FloatBuffer storeRMAbsolute(Float3x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m01);
        _U.putFloat(address + 8, self.m02);
        _U.putFloat(_p1, self.m10);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p1 + 8, self.m12);
        _U.putFloat(_p2, self.m20);
        _U.putFloat(_p2 + 4, self.m21);
        _U.putFloat(_p2 + 8, self.m22);
        return buf;
    }

    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4);
        self.m02 = _U.getFloat(address + 8);
        self.m10 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m12 = _U.getFloat(_p1 + 8);
        self.m20 = _U.getFloat(_p2);
        self.m21 = _U.getFloat(_p2 + 4);
        self.m22 = _U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4, self.m01);
        _U.putFloat(address + 8, self.m02);
        _U.putFloat(_p1, self.m10);
        _U.putFloat(_p1 + 4, self.m11);
        _U.putFloat(_p1 + 8, self.m12);
        _U.putFloat(_p2, self.m20);
        _U.putFloat(_p2 + 4, self.m21);
        _U.putFloat(_p2 + 8, self.m22);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(bytes, address, self.m00);
        _U.putFloat(bytes, address + 4, self.m01);
        _U.putFloat(bytes, address + 8, self.m02);
        _U.putFloat(bytes, _p1, self.m10);
        _U.putFloat(bytes, _p1 + 4, self.m11);
        _U.putFloat(bytes, _p1 + 8, self.m12);
        _U.putFloat(bytes, _p2, self.m20);
        _U.putFloat(bytes, _p2 + 4, self.m21);
        _U.putFloat(bytes, _p2 + 8, self.m22);
        return buf;
    }

    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4);
        self.m02 = _U.getFloat(address + 8);
        self.m10 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m12 = _U.getFloat(_p1 + 8);
        self.m20 = _U.getFloat(_p2);
        self.m21 = _U.getFloat(_p2 + 4);
        self.m22 = _U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadRMAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4);
        self.m02 = _U.getFloat(bytes, address + 8);
        self.m10 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m12 = _U.getFloat(bytes, _p1 + 8);
        self.m20 = _U.getFloat(bytes, _p2);
        self.m21 = _U.getFloat(bytes, _p2 + 4);
        self.m22 = _U.getFloat(bytes, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public DoubleBuffer storeRMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m01);
        _U.putDouble(address + 16, self.m02);
        _U.putDouble(_p1, self.m10);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m12);
        _U.putDouble(_p2, self.m20);
        _U.putDouble(_p2 + 8, self.m21);
        _U.putDouble(_p2 + 16, self.m22);
        return buf;
    }

    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) _U.getDouble(address);
        self.m01 = (float) _U.getDouble(address + 8);
        self.m02 = (float) _U.getDouble(address + 16);
        self.m10 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m12 = (float) _U.getDouble(_p1 + 16);
        self.m20 = (float) _U.getDouble(_p2);
        self.m21 = (float) _U.getDouble(_p2 + 8);
        self.m22 = (float) _U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m01);
        _U.putDouble(address + 16, self.m02);
        _U.putDouble(_p1, self.m10);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m12);
        _U.putDouble(_p2, self.m20);
        _U.putDouble(_p2 + 8, self.m21);
        _U.putDouble(_p2 + 16, self.m22);
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m01);
        _U.putDouble(bytes, address + 16, self.m02);
        _U.putDouble(bytes, _p1, self.m10);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m12);
        _U.putDouble(bytes, _p2, self.m20);
        _U.putDouble(bytes, _p2 + 8, self.m21);
        _U.putDouble(bytes, _p2 + 16, self.m22);
        return buf;
    }

    public Float3x3 loadRMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) _U.getDouble(address);
        self.m01 = (float) _U.getDouble(address + 8);
        self.m02 = (float) _U.getDouble(address + 16);
        self.m10 = (float) _U.getDouble(_p1);
        self.m11 = (float) _U.getDouble(_p1 + 8);
        self.m12 = (float) _U.getDouble(_p1 + 16);
        self.m20 = (float) _U.getDouble(_p2);
        self.m21 = (float) _U.getDouble(_p2 + 8);
        self.m22 = (float) _U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadRMDoubleAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) _U.getDouble(bytes, address);
        self.m01 = (float) _U.getDouble(bytes, address + 8);
        self.m02 = (float) _U.getDouble(bytes, address + 16);
        self.m10 = (float) _U.getDouble(bytes, _p1);
        self.m11 = (float) _U.getDouble(bytes, _p1 + 8);
        self.m12 = (float) _U.getDouble(bytes, _p1 + 16);
        self.m20 = (float) _U.getDouble(bytes, _p2);
        self.m21 = (float) _U.getDouble(bytes, _p2 + 8);
        self.m22 = (float) _U.getDouble(bytes, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public FloatBuffer storeCM4x4Absolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m20);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m01);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m21);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, self.m02);
        _U.putFloat(address + 36L, self.m12);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return buf;
    }

    public ByteBuffer storeCM4x4Absolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM4x4AbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m10);
        _U.putFloat(address + 8L, self.m20);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m01);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m21);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, self.m02);
        _U.putFloat(address + 36L, self.m12);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return buf;
    }

    private static ByteBuffer storeCM4x4AbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4Heap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public DoubleBuffer storeCM4x4Absolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m01);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, self.m21);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.m02);
        _U.putDouble(address + 72L, self.m12);
        _U.putDouble(address + 80L, self.m22);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return buf;
    }

    public ByteBuffer storeCM4x4DoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM4x4DoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m01);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, self.m21);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.m02);
        _U.putDouble(address + 72L, self.m12);
        _U.putDouble(address + 80L, self.m22);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return buf;
    }

    private static ByteBuffer storeCM4x4DoubleAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCM4x4DoubleAbsolute(self, index, buf);
        RAW.storeCM4x4DoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public FloatBuffer storeRM4x4Absolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, self.m02);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m10);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m12);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, self.m20);
        _U.putFloat(address + 36L, self.m21);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return buf;
    }

    public ByteBuffer storeRM4x4Absolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM4x4AbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00);
        _U.putFloat(address + 4L, self.m01);
        _U.putFloat(address + 8L, self.m02);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.m10);
        _U.putFloat(address + 20L, self.m11);
        _U.putFloat(address + 24L, self.m12);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, self.m20);
        _U.putFloat(address + 36L, self.m21);
        _U.putFloat(address + 40L, self.m22);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return buf;
    }

    private static ByteBuffer storeRM4x4AbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4Heap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public DoubleBuffer storeRM4x4Absolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m10);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, self.m12);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.m20);
        _U.putDouble(address + 72L, self.m21);
        _U.putDouble(address + 80L, self.m22);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return buf;
    }

    public ByteBuffer storeRM4x4DoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM4x4DoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m10);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, self.m12);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.m20);
        _U.putDouble(address + 72L, self.m21);
        _U.putDouble(address + 80L, self.m22);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return buf;
    }

    private static ByteBuffer storeRM4x4DoubleAbsoluteElse(Float3x3Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRM4x4DoubleAbsolute(self, index, buf);
        RAW.storeRM4x4DoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }
}
