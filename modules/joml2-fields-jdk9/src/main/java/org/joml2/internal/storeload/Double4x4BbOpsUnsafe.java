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

public final class Double4x4BbOpsUnsafe extends Double4x4BbOps {

    private static final Double4x4BbOpsApi API = new Double4x4BbOpsApi();
    private static final Double4x4RawOpsUnsafe RAW = new Double4x4RawOpsUnsafe();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DoubleBuffer storeCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        return RAW.loadCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
    }
    public ByteBuffer storeCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, self.m30);
        U.putDouble(address + 32L, self.m01);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m21);
        U.putDouble(address + 56L, self.m31);
        U.putDouble(address + 64L, self.m02);
        U.putDouble(address + 72L, self.m12);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, self.m32);
        U.putDouble(address + 96L, self.m03);
        U.putDouble(address + 104L, self.m13);
        U.putDouble(address + 112L, self.m23);
        U.putDouble(address + 120L, self.m33);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8L);
        self.m20 = U.getDouble(address + 16L);
        self.m30 = U.getDouble(address + 24L);
        self.m01 = U.getDouble(address + 32L);
        self.m11 = U.getDouble(address + 40L);
        self.m21 = U.getDouble(address + 48L);
        self.m31 = U.getDouble(address + 56L);
        self.m02 = U.getDouble(address + 64L);
        self.m12 = U.getDouble(address + 72L);
        self.m22 = U.getDouble(address + 80L);
        self.m32 = U.getDouble(address + 88L);
        self.m03 = U.getDouble(address + 96L);
        self.m13 = U.getDouble(address + 104L);
        self.m23 = U.getDouble(address + 112L);
        self.m33 = U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        return RAW.loadCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMFloatAbsolute(self, index, buf);
        RAW.storeCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Double4x4 loadCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMFloatAbsolute(self, index, buf);
        return RAW.loadCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
    }
    public DoubleBuffer storeRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        return RAW.loadRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
    }
    public ByteBuffer storeRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m02);
        U.putDouble(address + 24L, self.m03);
        U.putDouble(address + 32L, self.m10);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m12);
        U.putDouble(address + 56L, self.m13);
        U.putDouble(address + 64L, self.m20);
        U.putDouble(address + 72L, self.m21);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, self.m23);
        U.putDouble(address + 96L, self.m30);
        U.putDouble(address + 104L, self.m31);
        U.putDouble(address + 112L, self.m32);
        U.putDouble(address + 120L, self.m33);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8L);
        self.m02 = U.getDouble(address + 16L);
        self.m03 = U.getDouble(address + 24L);
        self.m10 = U.getDouble(address + 32L);
        self.m11 = U.getDouble(address + 40L);
        self.m12 = U.getDouble(address + 48L);
        self.m13 = U.getDouble(address + 56L);
        self.m20 = U.getDouble(address + 64L);
        self.m21 = U.getDouble(address + 72L);
        self.m22 = U.getDouble(address + 80L);
        self.m23 = U.getDouble(address + 88L);
        self.m30 = U.getDouble(address + 96L);
        self.m31 = U.getDouble(address + 104L);
        self.m32 = U.getDouble(address + 112L);
        self.m33 = U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        return RAW.loadRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMFloatAbsolute(self, index, buf);
        RAW.storeRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Double4x4 loadRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMFloatAbsolute(self, index, buf);
        return RAW.loadRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
    }
    public DoubleBuffer storeCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        return RAW.loadCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
    }
    public ByteBuffer storeCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        return RAW.loadCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public FloatBuffer storeCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        return RAW.loadCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMFloatAbsolute(self, index, buf, stride);
        RAW.storeCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Double4x4 loadCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMFloatAbsolute(self, index, buf, stride);
        return RAW.loadCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public DoubleBuffer storeRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        return RAW.loadRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
    }
    public ByteBuffer storeRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        return RAW.loadRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public FloatBuffer storeRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        return RAW.loadRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMFloatAbsolute(self, index, buf, stride);
        RAW.storeRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Double4x4 loadRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMFloatAbsolute(self, index, buf, stride);
        return RAW.loadRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
}
