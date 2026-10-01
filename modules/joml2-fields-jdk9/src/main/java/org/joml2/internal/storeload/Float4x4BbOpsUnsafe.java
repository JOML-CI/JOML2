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

public final class Float4x4BbOpsUnsafe extends Float4x4BbOps {

    private static final Float4x4BbOpsApi API = new Float4x4BbOpsApi();
    private static final Float4x4RawOpsUnsafe RAW = new Float4x4RawOpsUnsafe();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        return RAW.loadCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
    }
    public ByteBuffer storeCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m20);
        U.putFloat(address + 12L, self.m30);
        U.putFloat(address + 16L, self.m01);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, self.m21);
        U.putFloat(address + 28L, self.m31);
        U.putFloat(address + 32L, self.m02);
        U.putFloat(address + 36L, self.m12);
        U.putFloat(address + 40L, self.m22);
        U.putFloat(address + 44L, self.m32);
        U.putFloat(address + 48L, self.m03);
        U.putFloat(address + 52L, self.m13);
        U.putFloat(address + 56L, self.m23);
        U.putFloat(address + 60L, self.m33);
        return buf;
    }
    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4L);
        self.m20 = U.getFloat(address + 8L);
        self.m30 = U.getFloat(address + 12L);
        self.m01 = U.getFloat(address + 16L);
        self.m11 = U.getFloat(address + 20L);
        self.m21 = U.getFloat(address + 24L);
        self.m31 = U.getFloat(address + 28L);
        self.m02 = U.getFloat(address + 32L);
        self.m12 = U.getFloat(address + 36L);
        self.m22 = U.getFloat(address + 40L);
        self.m32 = U.getFloat(address + 44L);
        self.m03 = U.getFloat(address + 48L);
        self.m13 = U.getFloat(address + 52L);
        self.m23 = U.getFloat(address + 56L);
        self.m33 = U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        return RAW.loadCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
    }
    public ByteBuffer storeCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Float4x4 loadCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(self, index, buf);
        return RAW.loadCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
    }
    public FloatBuffer storeRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        return RAW.loadRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
    }
    public ByteBuffer storeRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m02);
        U.putFloat(address + 12L, self.m03);
        U.putFloat(address + 16L, self.m10);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, self.m12);
        U.putFloat(address + 28L, self.m13);
        U.putFloat(address + 32L, self.m20);
        U.putFloat(address + 36L, self.m21);
        U.putFloat(address + 40L, self.m22);
        U.putFloat(address + 44L, self.m23);
        U.putFloat(address + 48L, self.m30);
        U.putFloat(address + 52L, self.m31);
        U.putFloat(address + 56L, self.m32);
        U.putFloat(address + 60L, self.m33);
        return buf;
    }
    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4L);
        self.m02 = U.getFloat(address + 8L);
        self.m03 = U.getFloat(address + 12L);
        self.m10 = U.getFloat(address + 16L);
        self.m11 = U.getFloat(address + 20L);
        self.m12 = U.getFloat(address + 24L);
        self.m13 = U.getFloat(address + 28L);
        self.m20 = U.getFloat(address + 32L);
        self.m21 = U.getFloat(address + 36L);
        self.m22 = U.getFloat(address + 40L);
        self.m23 = U.getFloat(address + 44L);
        self.m30 = U.getFloat(address + 48L);
        self.m31 = U.getFloat(address + 52L);
        self.m32 = U.getFloat(address + 56L);
        self.m33 = U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        return RAW.loadRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
    }
    public ByteBuffer storeRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Float4x4 loadRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(self, index, buf);
        return RAW.loadRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
    }
    public FloatBuffer storeCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }
    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        return RAW.loadCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
    }
    public ByteBuffer storeCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        return RAW.loadCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public DoubleBuffer storeCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }
    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        return RAW.loadCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
    }
    public ByteBuffer storeCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Float4x4 loadCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(self, index, buf, stride);
        return RAW.loadCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public FloatBuffer storeRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }
    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        return RAW.loadRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
    }
    public ByteBuffer storeRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        return RAW.loadRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public DoubleBuffer storeRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }
    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        return RAW.loadRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
    }
    public ByteBuffer storeRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Float4x4 loadRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(self, index, buf, stride);
        return RAW.loadRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
}
