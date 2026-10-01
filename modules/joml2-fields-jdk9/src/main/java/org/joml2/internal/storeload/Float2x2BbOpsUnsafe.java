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

public final class Float2x2BbOpsUnsafe extends Float2x2BbOps {

    private static final Float2x2BbOpsApi API = new Float2x2BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeCMAbsolute(Float2x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m01);
        U.putFloat(address + 12L, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4L);
        self.m01 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m01);
        U.putFloat(address + 12L, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4L);
        self.m01 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m01);
        U.putDouble(address + 24L, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8L);
        self.m01 = (float) U.getDouble(address + 16L);
        self.m11 = (float) U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m01);
        U.putDouble(address + 24L, self.m11);
        return buf;
    }
    public Float2x2 loadCMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8L);
        self.m01 = (float) U.getDouble(address + 16L);
        self.m11 = (float) U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float2x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m10);
        U.putFloat(address + 12L, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4L);
        self.m10 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m10);
        U.putFloat(address + 12L, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4L);
        self.m10 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m10);
        U.putDouble(address + 24L, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8L);
        self.m10 = (float) U.getDouble(address + 16L);
        self.m11 = (float) U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m10);
        U.putDouble(address + 24L, self.m11);
        return buf;
    }
    public Float2x2 loadRMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8L);
        self.m10 = (float) U.getDouble(address + 16L);
        self.m11 = (float) U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Float2x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m10);
        U.putFloat(_p1, self.m01);
        U.putFloat(_p1 + 4, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m10);
        U.putFloat(_p1, self.m01);
        U.putFloat(_p1 + 4, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8);
        self.m01 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        return buf;
    }
    public Float2x2 loadCMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8);
        self.m01 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float2x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m01);
        U.putFloat(_p1, self.m10);
        U.putFloat(_p1 + 4, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4);
        self.m10 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m01);
        U.putFloat(_p1, self.m10);
        U.putFloat(_p1 + 4, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4);
        self.m10 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m01);
        U.putDouble(_p1, self.m10);
        U.putDouble(_p1 + 8, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8);
        self.m10 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m01);
        U.putDouble(_p1, self.m10);
        U.putDouble(_p1 + 8, self.m11);
        return buf;
    }
    public Float2x2 loadRMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8);
        self.m10 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCM3x3Absolute(Float2x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, self.m01);
        U.putFloat(address + 16L, self.m11);
        U.putFloat(address + 20L, 0.0f);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM3x3Absolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, self.m01);
        U.putFloat(address + 16L, self.m11);
        U.putFloat(address + 20L, 0.0f);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeCM3x3Absolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.m01);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, 0.0);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 1.0);
        return buf;
    }
    public ByteBuffer storeCM3x3DoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3DoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.m01);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, 0.0);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 1.0);
        return buf;
    }
    public FloatBuffer storeRM3x3Absolute(Float2x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, self.m10);
        U.putFloat(address + 16L, self.m11);
        U.putFloat(address + 20L, 0.0f);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM3x3Absolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, self.m10);
        U.putFloat(address + 16L, self.m11);
        U.putFloat(address + 20L, 0.0f);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeRM3x3Absolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.m10);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, 0.0);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 1.0);
        return buf;
    }
    public ByteBuffer storeRM3x3DoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3DoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.m10);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, 0.0);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 1.0);
        return buf;
    }
    public FloatBuffer storeCM4x4Absolute(Float2x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m01);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 0.0f);
        U.putFloat(address + 36L, 0.0f);
        U.putFloat(address + 40L, 1.0f);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM4x4Absolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m01);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 0.0f);
        U.putFloat(address + 36L, 0.0f);
        U.putFloat(address + 40L, 1.0f);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeCM4x4Absolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.m01);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 0.0);
        U.putDouble(address + 72L, 0.0);
        U.putDouble(address + 80L, 1.0);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, 0.0);
        U.putDouble(address + 104L, 0.0);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return buf;
    }
    public ByteBuffer storeCM4x4DoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4DoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.m01);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 0.0);
        U.putDouble(address + 72L, 0.0);
        U.putDouble(address + 80L, 1.0);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, 0.0);
        U.putDouble(address + 104L, 0.0);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return buf;
    }
    public FloatBuffer storeRM4x4Absolute(Float2x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m10);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 0.0f);
        U.putFloat(address + 36L, 0.0f);
        U.putFloat(address + 40L, 1.0f);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM4x4Absolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m10);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 0.0f);
        U.putFloat(address + 36L, 0.0f);
        U.putFloat(address + 40L, 1.0f);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeRM4x4Absolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.m10);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 0.0);
        U.putDouble(address + 72L, 0.0);
        U.putDouble(address + 80L, 1.0);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, 0.0);
        U.putDouble(address + 104L, 0.0);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return buf;
    }
    public ByteBuffer storeRM4x4DoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4DoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.m10);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 0.0);
        U.putDouble(address + 72L, 0.0);
        U.putDouble(address + 80L, 1.0);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, 0.0);
        U.putDouble(address + 104L, 0.0);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return buf;
    }
}
