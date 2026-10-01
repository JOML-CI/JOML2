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

    public FloatBuffer storeCMAbsolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 32; _k += 8) U.putLong(address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
            U.putFloat(address + 32L, _d[8]);
        } else {
            for (int _k = 0; _k < 9; _k++) U.putFloat(address + 4L * _k, _d[_k]);
        }
        return buf;
    }
    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 32; _k += 8) U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(address + _k));
            _d[8] = U.getFloat(address + 32L);
        } else {
            for (int _k = 0; _k < 9; _k++) _d[_k] = U.getFloat(address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 32; _k += 8) U.putLong(address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
            U.putFloat(address + 32L, _d[8]);
        } else {
            for (int _k = 0; _k < 9; _k++) U.putFloat(address + 4L * _k, _d[_k]);
        }
        return buf;
    }
    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 32; _k += 8) U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(address + _k));
            _d[8] = U.getFloat(address + 32L);
        } else {
            for (int _k = 0; _k < 9; _k++) _d[_k] = U.getFloat(address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, self.data[6]);
        U.putDouble(address + 56L, self.data[7]);
        U.putDouble(address + 64L, self.data[8]);
        return buf;
    }
    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        self.data[6] = (float) U.getDouble(address + 48L);
        self.data[7] = (float) U.getDouble(address + 56L);
        self.data[8] = (float) U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, self.data[6]);
        U.putDouble(address + 56L, self.data[7]);
        U.putDouble(address + 64L, self.data[8]);
        return buf;
    }
    public Float3x3 loadCMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        self.data[6] = (float) U.getDouble(address + 48L);
        self.data[7] = (float) U.getDouble(address + 56L);
        self.data[8] = (float) U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[3]);
        U.putFloat(address + 8L, self.data[6]);
        U.putFloat(address + 12L, self.data[1]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[7]);
        U.putFloat(address + 24L, self.data[2]);
        U.putFloat(address + 28L, self.data[5]);
        U.putFloat(address + 32L, self.data[8]);
        return buf;
    }
    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4L);
        self.data[6] = U.getFloat(address + 8L);
        self.data[1] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[7] = U.getFloat(address + 20L);
        self.data[2] = U.getFloat(address + 24L);
        self.data[5] = U.getFloat(address + 28L);
        self.data[8] = U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[3]);
        U.putFloat(address + 8L, self.data[6]);
        U.putFloat(address + 12L, self.data[1]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[7]);
        U.putFloat(address + 24L, self.data[2]);
        U.putFloat(address + 28L, self.data[5]);
        U.putFloat(address + 32L, self.data[8]);
        return buf;
    }
    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4L);
        self.data[6] = U.getFloat(address + 8L);
        self.data[1] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[7] = U.getFloat(address + 20L);
        self.data[2] = U.getFloat(address + 24L);
        self.data[5] = U.getFloat(address + 28L);
        self.data[8] = U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[3]);
        U.putDouble(address + 16L, self.data[6]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[7]);
        U.putDouble(address + 48L, self.data[2]);
        U.putDouble(address + 56L, self.data[5]);
        U.putDouble(address + 64L, self.data[8]);
        return buf;
    }
    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8L);
        self.data[6] = (float) U.getDouble(address + 16L);
        self.data[1] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[7] = (float) U.getDouble(address + 40L);
        self.data[2] = (float) U.getDouble(address + 48L);
        self.data[5] = (float) U.getDouble(address + 56L);
        self.data[8] = (float) U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[3]);
        U.putDouble(address + 16L, self.data[6]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[7]);
        U.putDouble(address + 48L, self.data[2]);
        U.putDouble(address + 56L, self.data[5]);
        U.putDouble(address + 64L, self.data[8]);
        return buf;
    }
    public Float3x3 loadRMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8L);
        self.data[6] = (float) U.getDouble(address + 16L);
        self.data[1] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[7] = (float) U.getDouble(address + 40L);
        self.data[2] = (float) U.getDouble(address + 48L);
        self.data[5] = (float) U.getDouble(address + 56L);
        self.data[8] = (float) U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Float3x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[1]);
        U.putFloat(address + 8, self.data[2]);
        U.putFloat(_p1, self.data[3]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p1 + 8, self.data[5]);
        U.putFloat(_p2, self.data[6]);
        U.putFloat(_p2 + 4, self.data[7]);
        U.putFloat(_p2 + 8, self.data[8]);
        return buf;
    }
    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[5] = U.getFloat(_p1 + 8);
        self.data[6] = U.getFloat(_p2);
        self.data[7] = U.getFloat(_p2 + 4);
        self.data[8] = U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[1]);
        U.putFloat(address + 8, self.data[2]);
        U.putFloat(_p1, self.data[3]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p1 + 8, self.data[5]);
        U.putFloat(_p2, self.data[6]);
        U.putFloat(_p2 + 4, self.data[7]);
        U.putFloat(_p2 + 8, self.data[8]);
        return buf;
    }
    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[5] = U.getFloat(_p1 + 8);
        self.data[6] = U.getFloat(_p2);
        self.data[7] = U.getFloat(_p2 + 4);
        self.data[8] = U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(_p1, self.data[3]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p1 + 16, self.data[5]);
        U.putDouble(_p2, self.data[6]);
        U.putDouble(_p2 + 8, self.data[7]);
        U.putDouble(_p2 + 16, self.data[8]);
        return buf;
    }
    public Float3x3 loadCMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8);
        self.data[2] = (float) U.getDouble(address + 16);
        self.data[3] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[5] = (float) U.getDouble(_p1 + 16);
        self.data[6] = (float) U.getDouble(_p2);
        self.data[7] = (float) U.getDouble(_p2 + 8);
        self.data[8] = (float) U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(_p1, self.data[3]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p1 + 16, self.data[5]);
        U.putDouble(_p2, self.data[6]);
        U.putDouble(_p2 + 8, self.data[7]);
        U.putDouble(_p2 + 16, self.data[8]);
        return buf;
    }
    public Float3x3 loadCMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8);
        self.data[2] = (float) U.getDouble(address + 16);
        self.data[3] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[5] = (float) U.getDouble(_p1 + 16);
        self.data[6] = (float) U.getDouble(_p2);
        self.data[7] = (float) U.getDouble(_p2 + 8);
        self.data[8] = (float) U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float3x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[3]);
        U.putFloat(address + 8, self.data[6]);
        U.putFloat(_p1, self.data[1]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p1 + 8, self.data[7]);
        U.putFloat(_p2, self.data[2]);
        U.putFloat(_p2 + 4, self.data[5]);
        U.putFloat(_p2 + 8, self.data[8]);
        return buf;
    }
    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4);
        self.data[6] = U.getFloat(address + 8);
        self.data[1] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[7] = U.getFloat(_p1 + 8);
        self.data[2] = U.getFloat(_p2);
        self.data[5] = U.getFloat(_p2 + 4);
        self.data[8] = U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[3]);
        U.putFloat(address + 8, self.data[6]);
        U.putFloat(_p1, self.data[1]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p1 + 8, self.data[7]);
        U.putFloat(_p2, self.data[2]);
        U.putFloat(_p2 + 4, self.data[5]);
        U.putFloat(_p2 + 8, self.data[8]);
        return buf;
    }
    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4);
        self.data[6] = U.getFloat(address + 8);
        self.data[1] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[7] = U.getFloat(_p1 + 8);
        self.data[2] = U.getFloat(_p2);
        self.data[5] = U.getFloat(_p2 + 4);
        self.data[8] = U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[3]);
        U.putDouble(address + 16, self.data[6]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p1 + 16, self.data[7]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[5]);
        U.putDouble(_p2 + 16, self.data[8]);
        return buf;
    }
    public Float3x3 loadRMAbsolute(Float3x3Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8);
        self.data[6] = (float) U.getDouble(address + 16);
        self.data[1] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[7] = (float) U.getDouble(_p1 + 16);
        self.data[2] = (float) U.getDouble(_p2);
        self.data[5] = (float) U.getDouble(_p2 + 8);
        self.data[8] = (float) U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[3]);
        U.putDouble(address + 16, self.data[6]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p1 + 16, self.data[7]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[5]);
        U.putDouble(_p2 + 16, self.data[8]);
        return buf;
    }
    public Float3x3 loadRMDoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8);
        self.data[6] = (float) U.getDouble(address + 16);
        self.data[1] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[7] = (float) U.getDouble(_p1 + 16);
        self.data[2] = (float) U.getDouble(_p2);
        self.data[5] = (float) U.getDouble(_p2 + 8);
        self.data[8] = (float) U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCM4x4Absolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4Unsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public ByteBuffer storeCM4x4Absolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.data[3]);
        U.putFloat(address + 20L, self.data[4]);
        U.putFloat(address + 24L, self.data[5]);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.data[6]);
        U.putFloat(address + 36L, self.data[7]);
        U.putFloat(address + 40L, self.data[8]);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeCM4x4Absolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4DoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public ByteBuffer storeCM4x4DoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4DoubleAbsolute(self, index, buf);
        RAW.storeCM4x4DoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public FloatBuffer storeRM4x4Absolute(Float3x3Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4Unsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public ByteBuffer storeRM4x4Absolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[3]);
        U.putFloat(address + 8L, self.data[6]);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.data[1]);
        U.putFloat(address + 20L, self.data[4]);
        U.putFloat(address + 24L, self.data[7]);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.data[2]);
        U.putFloat(address + 36L, self.data[5]);
        U.putFloat(address + 40L, self.data[8]);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return buf;
    }
    public DoubleBuffer storeRM4x4Absolute(Float3x3Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4DoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public ByteBuffer storeRM4x4DoubleAbsolute(Float3x3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4DoubleAbsolute(self, index, buf);
        RAW.storeRM4x4DoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
}
