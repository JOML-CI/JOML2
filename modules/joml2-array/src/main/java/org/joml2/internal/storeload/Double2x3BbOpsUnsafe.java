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

public final class Double2x3BbOpsUnsafe extends Double2x3BbOps {

    private static final Double2x3BbOpsApi API = new Double2x3BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DoubleBuffer storeCMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf) {
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
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8L);
        self.data[2] = U.getDouble(address + 16L);
        self.data[3] = U.getDouble(address + 24L);
        self.data[4] = U.getDouble(address + 32L);
        self.data[5] = U.getDouble(address + 40L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8L);
        self.data[2] = U.getDouble(address + 16L);
        self.data[3] = U.getDouble(address + 24L);
        self.data[4] = U.getDouble(address + 32L);
        self.data[5] = U.getDouble(address + 40L);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double2x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, (float) self.data[2]);
        U.putFloat(address + 12L, (float) self.data[3]);
        U.putFloat(address + 16L, (float) self.data[4]);
        U.putFloat(address + 20L, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, (float) self.data[2]);
        U.putFloat(address + 12L, (float) self.data[3]);
        U.putFloat(address + 16L, (float) self.data[4]);
        U.putFloat(address + 20L, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadCMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, self.data[4]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = U.getDouble(address);
        self.data[2] = U.getDouble(address + 8L);
        self.data[4] = U.getDouble(address + 16L);
        self.data[1] = U.getDouble(address + 24L);
        self.data[3] = U.getDouble(address + 32L);
        self.data[5] = U.getDouble(address + 40L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, self.data[4]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getDouble(address);
        self.data[2] = U.getDouble(address + 8L);
        self.data[4] = U.getDouble(address + 16L);
        self.data[1] = U.getDouble(address + 24L);
        self.data[3] = U.getDouble(address + 32L);
        self.data[5] = U.getDouble(address + 40L);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double2x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[2]);
        U.putFloat(address + 8L, (float) self.data[4]);
        U.putFloat(address + 12L, (float) self.data[1]);
        U.putFloat(address + 16L, (float) self.data[3]);
        U.putFloat(address + 20L, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.data[0] = U.getFloat(address);
        self.data[2] = U.getFloat(address + 4L);
        self.data[4] = U.getFloat(address + 8L);
        self.data[1] = U.getFloat(address + 12L);
        self.data[3] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[2]);
        U.putFloat(address + 8L, (float) self.data[4]);
        U.putFloat(address + 12L, (float) self.data[1]);
        U.putFloat(address + 16L, (float) self.data[3]);
        U.putFloat(address + 20L, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadRMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getFloat(address);
        self.data[2] = U.getFloat(address + 4L);
        self.data[4] = U.getFloat(address + 8L);
        self.data[1] = U.getFloat(address + 12L);
        self.data[3] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(_p1, self.data[2]);
        U.putDouble(_p1 + 8, self.data[3]);
        U.putDouble(_p2, self.data[4]);
        U.putDouble(_p2 + 8, self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8);
        self.data[2] = U.getDouble(_p1);
        self.data[3] = U.getDouble(_p1 + 8);
        self.data[4] = U.getDouble(_p2);
        self.data[5] = U.getDouble(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(_p1, self.data[2]);
        U.putDouble(_p1 + 8, self.data[3]);
        U.putDouble(_p2, self.data[4]);
        U.putDouble(_p2 + 8, self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8);
        self.data[2] = U.getDouble(_p1);
        self.data[3] = U.getDouble(_p1 + 8);
        self.data[4] = U.getDouble(_p2);
        self.data[5] = U.getDouble(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double2x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[1]);
        U.putFloat(_p1, (float) self.data[2]);
        U.putFloat(_p1 + 4, (float) self.data[3]);
        U.putFloat(_p2, (float) self.data[4]);
        U.putFloat(_p2 + 4, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(_p1);
        self.data[3] = U.getFloat(_p1 + 4);
        self.data[4] = U.getFloat(_p2);
        self.data[5] = U.getFloat(_p2 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMFloatAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[1]);
        U.putFloat(_p1, (float) self.data[2]);
        U.putFloat(_p1 + 4, (float) self.data[3]);
        U.putFloat(_p2, (float) self.data[4]);
        U.putFloat(_p2 + 4, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadCMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMFloatAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(_p1);
        self.data[3] = U.getFloat(_p1 + 4);
        self.data[4] = U.getFloat(_p2);
        self.data[5] = U.getFloat(_p2 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[2]);
        U.putDouble(address + 16, self.data[4]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[3]);
        U.putDouble(_p1 + 16, self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = U.getDouble(address);
        self.data[2] = U.getDouble(address + 8);
        self.data[4] = U.getDouble(address + 16);
        self.data[1] = U.getDouble(_p1);
        self.data[3] = U.getDouble(_p1 + 8);
        self.data[5] = U.getDouble(_p1 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[2]);
        U.putDouble(address + 16, self.data[4]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[3]);
        U.putDouble(_p1 + 16, self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = U.getDouble(address);
        self.data[2] = U.getDouble(address + 8);
        self.data[4] = U.getDouble(address + 16);
        self.data[1] = U.getDouble(_p1);
        self.data[3] = U.getDouble(_p1 + 8);
        self.data[5] = U.getDouble(_p1 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double2x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[2]);
        U.putFloat(address + 8, (float) self.data[4]);
        U.putFloat(_p1, (float) self.data[1]);
        U.putFloat(_p1 + 4, (float) self.data[3]);
        U.putFloat(_p1 + 8, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[2] = U.getFloat(address + 4);
        self.data[4] = U.getFloat(address + 8);
        self.data[1] = U.getFloat(_p1);
        self.data[3] = U.getFloat(_p1 + 4);
        self.data[5] = U.getFloat(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMFloatAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[2]);
        U.putFloat(address + 8, (float) self.data[4]);
        U.putFloat(_p1, (float) self.data[1]);
        U.putFloat(_p1 + 4, (float) self.data[3]);
        U.putFloat(_p1 + 8, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadRMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMFloatAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[2] = U.getFloat(address + 4);
        self.data[4] = U.getFloat(address + 8);
        self.data[1] = U.getFloat(_p1);
        self.data[3] = U.getFloat(_p1 + 4);
        self.data[5] = U.getFloat(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCM3x3Absolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.data[2]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, 0.0);
        U.putDouble(address + 48L, self.data[4]);
        U.putDouble(address + 56L, self.data[5]);
        U.putDouble(address + 64L, 1.0);
        return buf;
    }
    public ByteBuffer storeCM3x3Absolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.data[2]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, 0.0);
        U.putDouble(address + 48L, self.data[4]);
        U.putDouble(address + 56L, self.data[5]);
        U.putDouble(address + 64L, 1.0);
        return buf;
    }
    public FloatBuffer storeCM3x3Absolute(Double2x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, (float) self.data[2]);
        U.putFloat(address + 16L, (float) self.data[3]);
        U.putFloat(address + 20L, 0.0f);
        U.putFloat(address + 24L, (float) self.data[4]);
        U.putFloat(address + 28L, (float) self.data[5]);
        U.putFloat(address + 32L, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM3x3FloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM3x3FloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, (float) self.data[2]);
        U.putFloat(address + 16L, (float) self.data[3]);
        U.putFloat(address + 20L, 0.0f);
        U.putFloat(address + 24L, (float) self.data[4]);
        U.putFloat(address + 28L, (float) self.data[5]);
        U.putFloat(address + 32L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeRM3x3Absolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, self.data[4]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 1.0);
        return buf;
    }
    public ByteBuffer storeRM3x3Absolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, self.data[4]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 1.0);
        return buf;
    }
    public FloatBuffer storeRM3x3Absolute(Double2x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[2]);
        U.putFloat(address + 8L, (float) self.data[4]);
        U.putFloat(address + 12L, (float) self.data[1]);
        U.putFloat(address + 16L, (float) self.data[3]);
        U.putFloat(address + 20L, (float) self.data[5]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM3x3FloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM3x3FloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[2]);
        U.putFloat(address + 8L, (float) self.data[4]);
        U.putFloat(address + 12L, (float) self.data[1]);
        U.putFloat(address + 16L, (float) self.data[3]);
        U.putFloat(address + 20L, (float) self.data[5]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeCM4x4Absolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.data[2]);
        U.putDouble(address + 40L, self.data[3]);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 0.0);
        U.putDouble(address + 72L, 0.0);
        U.putDouble(address + 80L, 1.0);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, self.data[4]);
        U.putDouble(address + 104L, self.data[5]);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return buf;
    }
    public ByteBuffer storeCM4x4Absolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.data[2]);
        U.putDouble(address + 40L, self.data[3]);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 0.0);
        U.putDouble(address + 72L, 0.0);
        U.putDouble(address + 80L, 1.0);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, self.data[4]);
        U.putDouble(address + 104L, self.data[5]);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return buf;
    }
    public FloatBuffer storeCM4x4Absolute(Double2x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, (float) self.data[2]);
        U.putFloat(address + 20L, (float) self.data[3]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 0.0f);
        U.putFloat(address + 36L, 0.0f);
        U.putFloat(address + 40L, 1.0f);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, (float) self.data[4]);
        U.putFloat(address + 52L, (float) self.data[5]);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM4x4FloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4FloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, (float) self.data[2]);
        U.putFloat(address + 20L, (float) self.data[3]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 0.0f);
        U.putFloat(address + 36L, 0.0f);
        U.putFloat(address + 40L, 1.0f);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, (float) self.data[4]);
        U.putFloat(address + 52L, (float) self.data[5]);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeRM4x4Absolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.data[4]);
        U.putDouble(address + 32L, self.data[1]);
        U.putDouble(address + 40L, self.data[3]);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, self.data[5]);
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
    public ByteBuffer storeRM4x4Absolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.data[4]);
        U.putDouble(address + 32L, self.data[1]);
        U.putDouble(address + 40L, self.data[3]);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, self.data[5]);
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
    public FloatBuffer storeRM4x4Absolute(Double2x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[2]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, (float) self.data[4]);
        U.putFloat(address + 16L, (float) self.data[1]);
        U.putFloat(address + 20L, (float) self.data[3]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, (float) self.data[5]);
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
    public ByteBuffer storeRM4x4FloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4FloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[2]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, (float) self.data[4]);
        U.putFloat(address + 16L, (float) self.data[1]);
        U.putFloat(address + 20L, (float) self.data[3]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, (float) self.data[5]);
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
}
