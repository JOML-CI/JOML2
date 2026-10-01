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

public final class Double3x4BbOpsUnsafe extends Double3x4BbOps {
    private static final Double3x4BbOpsApi API = new Double3x4BbOpsApi();
    private static final Double3x4RawOpsUnsafe RAW = new Double3x4RawOpsUnsafe();

    public DoubleBuffer storeCMAbsolute(Double3x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[4]);
        _U.putDouble(address + 16L, self.data[8]);
        _U.putDouble(address + 24L, self.data[1]);
        _U.putDouble(address + 32L, self.data[5]);
        _U.putDouble(address + 40L, self.data[9]);
        _U.putDouble(address + 48L, self.data[2]);
        _U.putDouble(address + 56L, self.data[6]);
        _U.putDouble(address + 64L, self.data[10]);
        _U.putDouble(address + 72L, self.data[3]);
        _U.putDouble(address + 80L, self.data[7]);
        _U.putDouble(address + 88L, self.data[11]);
        return buf;
    }

    public Double3x4 loadCMAbsolute(Double3x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = _U.getDouble(address);
        self.data[4] = _U.getDouble(address + 8L);
        self.data[8] = _U.getDouble(address + 16L);
        self.data[1] = _U.getDouble(address + 24L);
        self.data[5] = _U.getDouble(address + 32L);
        self.data[9] = _U.getDouble(address + 40L);
        self.data[2] = _U.getDouble(address + 48L);
        self.data[6] = _U.getDouble(address + 56L);
        self.data[10] = _U.getDouble(address + 64L);
        self.data[3] = _U.getDouble(address + 72L);
        self.data[7] = _U.getDouble(address + 80L);
        self.data[11] = _U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMAbsolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[4]);
        _U.putDouble(address + 16L, self.data[8]);
        _U.putDouble(address + 24L, self.data[1]);
        _U.putDouble(address + 32L, self.data[5]);
        _U.putDouble(address + 40L, self.data[9]);
        _U.putDouble(address + 48L, self.data[2]);
        _U.putDouble(address + 56L, self.data[6]);
        _U.putDouble(address + 64L, self.data[10]);
        _U.putDouble(address + 72L, self.data[3]);
        _U.putDouble(address + 80L, self.data[7]);
        _U.putDouble(address + 88L, self.data[11]);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[4]);
        _U.putDouble(bytes, address + 16L, self.data[8]);
        _U.putDouble(bytes, address + 24L, self.data[1]);
        _U.putDouble(bytes, address + 32L, self.data[5]);
        _U.putDouble(bytes, address + 40L, self.data[9]);
        _U.putDouble(bytes, address + 48L, self.data[2]);
        _U.putDouble(bytes, address + 56L, self.data[6]);
        _U.putDouble(bytes, address + 64L, self.data[10]);
        _U.putDouble(bytes, address + 72L, self.data[3]);
        _U.putDouble(bytes, address + 80L, self.data[7]);
        _U.putDouble(bytes, address + 88L, self.data[11]);
        return buf;
    }

    public Double3x4 loadCMAbsolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = _U.getDouble(address);
        self.data[4] = _U.getDouble(address + 8L);
        self.data[8] = _U.getDouble(address + 16L);
        self.data[1] = _U.getDouble(address + 24L);
        self.data[5] = _U.getDouble(address + 32L);
        self.data[9] = _U.getDouble(address + 40L);
        self.data[2] = _U.getDouble(address + 48L);
        self.data[6] = _U.getDouble(address + 56L);
        self.data[10] = _U.getDouble(address + 64L);
        self.data[3] = _U.getDouble(address + 72L);
        self.data[7] = _U.getDouble(address + 80L);
        self.data[11] = _U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadCMAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[4] = _U.getDouble(bytes, address + 8L);
        self.data[8] = _U.getDouble(bytes, address + 16L);
        self.data[1] = _U.getDouble(bytes, address + 24L);
        self.data[5] = _U.getDouble(bytes, address + 32L);
        self.data[9] = _U.getDouble(bytes, address + 40L);
        self.data[2] = _U.getDouble(bytes, address + 48L);
        self.data[6] = _U.getDouble(bytes, address + 56L);
        self.data[10] = _U.getDouble(bytes, address + 64L);
        self.data[3] = _U.getDouble(bytes, address + 72L);
        self.data[7] = _U.getDouble(bytes, address + 80L);
        self.data[11] = _U.getDouble(bytes, address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public FloatBuffer storeCMAbsolute(Double3x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[4]);
        _U.putFloat(address + 8L, (float) self.data[8]);
        _U.putFloat(address + 12L, (float) self.data[1]);
        _U.putFloat(address + 16L, (float) self.data[5]);
        _U.putFloat(address + 20L, (float) self.data[9]);
        _U.putFloat(address + 24L, (float) self.data[2]);
        _U.putFloat(address + 28L, (float) self.data[6]);
        _U.putFloat(address + 32L, (float) self.data[10]);
        _U.putFloat(address + 36L, (float) self.data[3]);
        _U.putFloat(address + 40L, (float) self.data[7]);
        _U.putFloat(address + 44L, (float) self.data[11]);
        return buf;
    }

    public Double3x4 loadCMAbsolute(Double3x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4L);
        self.data[8] = _U.getFloat(address + 8L);
        self.data[1] = _U.getFloat(address + 12L);
        self.data[5] = _U.getFloat(address + 16L);
        self.data[9] = _U.getFloat(address + 20L);
        self.data[2] = _U.getFloat(address + 24L);
        self.data[6] = _U.getFloat(address + 28L);
        self.data[10] = _U.getFloat(address + 32L);
        self.data[3] = _U.getFloat(address + 36L);
        self.data[7] = _U.getFloat(address + 40L);
        self.data[11] = _U.getFloat(address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMFloatAbsolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[4]);
        _U.putFloat(address + 8L, (float) self.data[8]);
        _U.putFloat(address + 12L, (float) self.data[1]);
        _U.putFloat(address + 16L, (float) self.data[5]);
        _U.putFloat(address + 20L, (float) self.data[9]);
        _U.putFloat(address + 24L, (float) self.data[2]);
        _U.putFloat(address + 28L, (float) self.data[6]);
        _U.putFloat(address + 32L, (float) self.data[10]);
        _U.putFloat(address + 36L, (float) self.data[3]);
        _U.putFloat(address + 40L, (float) self.data[7]);
        _U.putFloat(address + 44L, (float) self.data[11]);
        return buf;
    }

    private static ByteBuffer storeCMFloatAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[4]);
        _U.putFloat(bytes, address + 8L, (float) self.data[8]);
        _U.putFloat(bytes, address + 12L, (float) self.data[1]);
        _U.putFloat(bytes, address + 16L, (float) self.data[5]);
        _U.putFloat(bytes, address + 20L, (float) self.data[9]);
        _U.putFloat(bytes, address + 24L, (float) self.data[2]);
        _U.putFloat(bytes, address + 28L, (float) self.data[6]);
        _U.putFloat(bytes, address + 32L, (float) self.data[10]);
        _U.putFloat(bytes, address + 36L, (float) self.data[3]);
        _U.putFloat(bytes, address + 40L, (float) self.data[7]);
        _U.putFloat(bytes, address + 44L, (float) self.data[11]);
        return buf;
    }

    public Double3x4 loadCMFloatAbsolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4L);
        self.data[8] = _U.getFloat(address + 8L);
        self.data[1] = _U.getFloat(address + 12L);
        self.data[5] = _U.getFloat(address + 16L);
        self.data[9] = _U.getFloat(address + 20L);
        self.data[2] = _U.getFloat(address + 24L);
        self.data[6] = _U.getFloat(address + 28L);
        self.data[10] = _U.getFloat(address + 32L);
        self.data[3] = _U.getFloat(address + 36L);
        self.data[7] = _U.getFloat(address + 40L);
        self.data[11] = _U.getFloat(address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadCMFloatAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[4] = _U.getFloat(bytes, address + 4L);
        self.data[8] = _U.getFloat(bytes, address + 8L);
        self.data[1] = _U.getFloat(bytes, address + 12L);
        self.data[5] = _U.getFloat(bytes, address + 16L);
        self.data[9] = _U.getFloat(bytes, address + 20L);
        self.data[2] = _U.getFloat(bytes, address + 24L);
        self.data[6] = _U.getFloat(bytes, address + 28L);
        self.data[10] = _U.getFloat(bytes, address + 32L);
        self.data[3] = _U.getFloat(bytes, address + 36L);
        self.data[7] = _U.getFloat(bytes, address + 40L);
        self.data[11] = _U.getFloat(bytes, address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public DoubleBuffer storeRMAbsolute(Double3x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[1]);
        _U.putDouble(address + 16L, self.data[2]);
        _U.putDouble(address + 24L, self.data[3]);
        _U.putDouble(address + 32L, self.data[4]);
        _U.putDouble(address + 40L, self.data[5]);
        _U.putDouble(address + 48L, self.data[6]);
        _U.putDouble(address + 56L, self.data[7]);
        _U.putDouble(address + 64L, self.data[8]);
        _U.putDouble(address + 72L, self.data[9]);
        _U.putDouble(address + 80L, self.data[10]);
        _U.putDouble(address + 88L, self.data[11]);
        return buf;
    }

    public Double3x4 loadRMAbsolute(Double3x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8L);
        self.data[2] = _U.getDouble(address + 16L);
        self.data[3] = _U.getDouble(address + 24L);
        self.data[4] = _U.getDouble(address + 32L);
        self.data[5] = _U.getDouble(address + 40L);
        self.data[6] = _U.getDouble(address + 48L);
        self.data[7] = _U.getDouble(address + 56L);
        self.data[8] = _U.getDouble(address + 64L);
        self.data[9] = _U.getDouble(address + 72L);
        self.data[10] = _U.getDouble(address + 80L);
        self.data[11] = _U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMAbsolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[1]);
        _U.putDouble(address + 16L, self.data[2]);
        _U.putDouble(address + 24L, self.data[3]);
        _U.putDouble(address + 32L, self.data[4]);
        _U.putDouble(address + 40L, self.data[5]);
        _U.putDouble(address + 48L, self.data[6]);
        _U.putDouble(address + 56L, self.data[7]);
        _U.putDouble(address + 64L, self.data[8]);
        _U.putDouble(address + 72L, self.data[9]);
        _U.putDouble(address + 80L, self.data[10]);
        _U.putDouble(address + 88L, self.data[11]);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[1]);
        _U.putDouble(bytes, address + 16L, self.data[2]);
        _U.putDouble(bytes, address + 24L, self.data[3]);
        _U.putDouble(bytes, address + 32L, self.data[4]);
        _U.putDouble(bytes, address + 40L, self.data[5]);
        _U.putDouble(bytes, address + 48L, self.data[6]);
        _U.putDouble(bytes, address + 56L, self.data[7]);
        _U.putDouble(bytes, address + 64L, self.data[8]);
        _U.putDouble(bytes, address + 72L, self.data[9]);
        _U.putDouble(bytes, address + 80L, self.data[10]);
        _U.putDouble(bytes, address + 88L, self.data[11]);
        return buf;
    }

    public Double3x4 loadRMAbsolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8L);
        self.data[2] = _U.getDouble(address + 16L);
        self.data[3] = _U.getDouble(address + 24L);
        self.data[4] = _U.getDouble(address + 32L);
        self.data[5] = _U.getDouble(address + 40L);
        self.data[6] = _U.getDouble(address + 48L);
        self.data[7] = _U.getDouble(address + 56L);
        self.data[8] = _U.getDouble(address + 64L);
        self.data[9] = _U.getDouble(address + 72L);
        self.data[10] = _U.getDouble(address + 80L);
        self.data[11] = _U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadRMAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[1] = _U.getDouble(bytes, address + 8L);
        self.data[2] = _U.getDouble(bytes, address + 16L);
        self.data[3] = _U.getDouble(bytes, address + 24L);
        self.data[4] = _U.getDouble(bytes, address + 32L);
        self.data[5] = _U.getDouble(bytes, address + 40L);
        self.data[6] = _U.getDouble(bytes, address + 48L);
        self.data[7] = _U.getDouble(bytes, address + 56L);
        self.data[8] = _U.getDouble(bytes, address + 64L);
        self.data[9] = _U.getDouble(bytes, address + 72L);
        self.data[10] = _U.getDouble(bytes, address + 80L);
        self.data[11] = _U.getDouble(bytes, address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public FloatBuffer storeRMAbsolute(Double3x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[1]);
        _U.putFloat(address + 8L, (float) self.data[2]);
        _U.putFloat(address + 12L, (float) self.data[3]);
        _U.putFloat(address + 16L, (float) self.data[4]);
        _U.putFloat(address + 20L, (float) self.data[5]);
        _U.putFloat(address + 24L, (float) self.data[6]);
        _U.putFloat(address + 28L, (float) self.data[7]);
        _U.putFloat(address + 32L, (float) self.data[8]);
        _U.putFloat(address + 36L, (float) self.data[9]);
        _U.putFloat(address + 40L, (float) self.data[10]);
        _U.putFloat(address + 44L, (float) self.data[11]);
        return buf;
    }

    public Double3x4 loadRMAbsolute(Double3x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4L);
        self.data[2] = _U.getFloat(address + 8L);
        self.data[3] = _U.getFloat(address + 12L);
        self.data[4] = _U.getFloat(address + 16L);
        self.data[5] = _U.getFloat(address + 20L);
        self.data[6] = _U.getFloat(address + 24L);
        self.data[7] = _U.getFloat(address + 28L);
        self.data[8] = _U.getFloat(address + 32L);
        self.data[9] = _U.getFloat(address + 36L);
        self.data[10] = _U.getFloat(address + 40L);
        self.data[11] = _U.getFloat(address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMFloatAbsolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[1]);
        _U.putFloat(address + 8L, (float) self.data[2]);
        _U.putFloat(address + 12L, (float) self.data[3]);
        _U.putFloat(address + 16L, (float) self.data[4]);
        _U.putFloat(address + 20L, (float) self.data[5]);
        _U.putFloat(address + 24L, (float) self.data[6]);
        _U.putFloat(address + 28L, (float) self.data[7]);
        _U.putFloat(address + 32L, (float) self.data[8]);
        _U.putFloat(address + 36L, (float) self.data[9]);
        _U.putFloat(address + 40L, (float) self.data[10]);
        _U.putFloat(address + 44L, (float) self.data[11]);
        return buf;
    }

    private static ByteBuffer storeRMFloatAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[1]);
        _U.putFloat(bytes, address + 8L, (float) self.data[2]);
        _U.putFloat(bytes, address + 12L, (float) self.data[3]);
        _U.putFloat(bytes, address + 16L, (float) self.data[4]);
        _U.putFloat(bytes, address + 20L, (float) self.data[5]);
        _U.putFloat(bytes, address + 24L, (float) self.data[6]);
        _U.putFloat(bytes, address + 28L, (float) self.data[7]);
        _U.putFloat(bytes, address + 32L, (float) self.data[8]);
        _U.putFloat(bytes, address + 36L, (float) self.data[9]);
        _U.putFloat(bytes, address + 40L, (float) self.data[10]);
        _U.putFloat(bytes, address + 44L, (float) self.data[11]);
        return buf;
    }

    public Double3x4 loadRMFloatAbsolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4L);
        self.data[2] = _U.getFloat(address + 8L);
        self.data[3] = _U.getFloat(address + 12L);
        self.data[4] = _U.getFloat(address + 16L);
        self.data[5] = _U.getFloat(address + 20L);
        self.data[6] = _U.getFloat(address + 24L);
        self.data[7] = _U.getFloat(address + 28L);
        self.data[8] = _U.getFloat(address + 32L);
        self.data[9] = _U.getFloat(address + 36L);
        self.data[10] = _U.getFloat(address + 40L);
        self.data[11] = _U.getFloat(address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadRMFloatAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMFloatAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4L);
        self.data[2] = _U.getFloat(bytes, address + 8L);
        self.data[3] = _U.getFloat(bytes, address + 12L);
        self.data[4] = _U.getFloat(bytes, address + 16L);
        self.data[5] = _U.getFloat(bytes, address + 20L);
        self.data[6] = _U.getFloat(bytes, address + 24L);
        self.data[7] = _U.getFloat(bytes, address + 28L);
        self.data[8] = _U.getFloat(bytes, address + 32L);
        self.data[9] = _U.getFloat(bytes, address + 36L);
        self.data[10] = _U.getFloat(bytes, address + 40L);
        self.data[11] = _U.getFloat(bytes, address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public DoubleBuffer storeCMAbsolute(Double3x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[4]);
        _U.putDouble(address + 16, self.data[8]);
        _U.putDouble(_p1, self.data[1]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[9]);
        _U.putDouble(_p2, self.data[2]);
        _U.putDouble(_p2 + 8, self.data[6]);
        _U.putDouble(_p2 + 16, self.data[10]);
        _U.putDouble(_p3, self.data[3]);
        _U.putDouble(_p3 + 8, self.data[7]);
        _U.putDouble(_p3 + 16, self.data[11]);
        return buf;
    }

    public Double3x4 loadCMAbsolute(Double3x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = _U.getDouble(address);
        self.data[4] = _U.getDouble(address + 8);
        self.data[8] = _U.getDouble(address + 16);
        self.data[1] = _U.getDouble(_p1);
        self.data[5] = _U.getDouble(_p1 + 8);
        self.data[9] = _U.getDouble(_p1 + 16);
        self.data[2] = _U.getDouble(_p2);
        self.data[6] = _U.getDouble(_p2 + 8);
        self.data[10] = _U.getDouble(_p2 + 16);
        self.data[3] = _U.getDouble(_p3);
        self.data[7] = _U.getDouble(_p3 + 8);
        self.data[11] = _U.getDouble(_p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMAbsolute(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[4]);
        _U.putDouble(address + 16, self.data[8]);
        _U.putDouble(_p1, self.data[1]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[9]);
        _U.putDouble(_p2, self.data[2]);
        _U.putDouble(_p2 + 8, self.data[6]);
        _U.putDouble(_p2 + 16, self.data[10]);
        _U.putDouble(_p3, self.data[3]);
        _U.putDouble(_p3 + 8, self.data[7]);
        _U.putDouble(_p3 + 16, self.data[11]);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Double3x4 loadCMAbsolute(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = _U.getDouble(address);
        self.data[4] = _U.getDouble(address + 8);
        self.data[8] = _U.getDouble(address + 16);
        self.data[1] = _U.getDouble(_p1);
        self.data[5] = _U.getDouble(_p1 + 8);
        self.data[9] = _U.getDouble(_p1 + 16);
        self.data[2] = _U.getDouble(_p2);
        self.data[6] = _U.getDouble(_p2 + 8);
        self.data[10] = _U.getDouble(_p2 + 16);
        self.data[3] = _U.getDouble(_p3);
        self.data[7] = _U.getDouble(_p3 + 8);
        self.data[11] = _U.getDouble(_p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadCMAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf, stride);
        return RAW.loadCMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    public FloatBuffer storeCMAbsolute(Double3x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[4]);
        _U.putFloat(address + 8, (float) self.data[8]);
        _U.putFloat(_p1, (float) self.data[1]);
        _U.putFloat(_p1 + 4, (float) self.data[5]);
        _U.putFloat(_p1 + 8, (float) self.data[9]);
        _U.putFloat(_p2, (float) self.data[2]);
        _U.putFloat(_p2 + 4, (float) self.data[6]);
        _U.putFloat(_p2 + 8, (float) self.data[10]);
        _U.putFloat(_p3, (float) self.data[3]);
        _U.putFloat(_p3 + 4, (float) self.data[7]);
        _U.putFloat(_p3 + 8, (float) self.data[11]);
        return buf;
    }

    public Double3x4 loadCMAbsolute(Double3x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4);
        self.data[8] = _U.getFloat(address + 8);
        self.data[1] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[9] = _U.getFloat(_p1 + 8);
        self.data[2] = _U.getFloat(_p2);
        self.data[6] = _U.getFloat(_p2 + 4);
        self.data[10] = _U.getFloat(_p2 + 8);
        self.data[3] = _U.getFloat(_p3);
        self.data[7] = _U.getFloat(_p3 + 4);
        self.data[11] = _U.getFloat(_p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMFloatAbsolute(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMFloatAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[4]);
        _U.putFloat(address + 8, (float) self.data[8]);
        _U.putFloat(_p1, (float) self.data[1]);
        _U.putFloat(_p1 + 4, (float) self.data[5]);
        _U.putFloat(_p1 + 8, (float) self.data[9]);
        _U.putFloat(_p2, (float) self.data[2]);
        _U.putFloat(_p2 + 4, (float) self.data[6]);
        _U.putFloat(_p2 + 8, (float) self.data[10]);
        _U.putFloat(_p3, (float) self.data[3]);
        _U.putFloat(_p3 + 4, (float) self.data[7]);
        _U.putFloat(_p3 + 8, (float) self.data[11]);
        return buf;
    }

    private static ByteBuffer storeCMFloatAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMFloatAbsolute(self, index, buf, stride);
        RAW.storeCMFloatHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Double3x4 loadCMFloatAbsolute(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMFloatAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4);
        self.data[8] = _U.getFloat(address + 8);
        self.data[1] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[9] = _U.getFloat(_p1 + 8);
        self.data[2] = _U.getFloat(_p2);
        self.data[6] = _U.getFloat(_p2 + 4);
        self.data[10] = _U.getFloat(_p2 + 8);
        self.data[3] = _U.getFloat(_p3);
        self.data[7] = _U.getFloat(_p3 + 4);
        self.data[11] = _U.getFloat(_p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadCMFloatAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMFloatAbsolute(self, index, buf, stride);
        return RAW.loadCMFloatHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    public DoubleBuffer storeRMAbsolute(Double3x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[1]);
        _U.putDouble(address + 16, self.data[2]);
        _U.putDouble(address + 24, self.data[3]);
        _U.putDouble(_p1, self.data[4]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[6]);
        _U.putDouble(_p1 + 24, self.data[7]);
        _U.putDouble(_p2, self.data[8]);
        _U.putDouble(_p2 + 8, self.data[9]);
        _U.putDouble(_p2 + 16, self.data[10]);
        _U.putDouble(_p2 + 24, self.data[11]);
        return buf;
    }

    public Double3x4 loadRMAbsolute(Double3x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8);
        self.data[2] = _U.getDouble(address + 16);
        self.data[3] = _U.getDouble(address + 24);
        self.data[4] = _U.getDouble(_p1);
        self.data[5] = _U.getDouble(_p1 + 8);
        self.data[6] = _U.getDouble(_p1 + 16);
        self.data[7] = _U.getDouble(_p1 + 24);
        self.data[8] = _U.getDouble(_p2);
        self.data[9] = _U.getDouble(_p2 + 8);
        self.data[10] = _U.getDouble(_p2 + 16);
        self.data[11] = _U.getDouble(_p2 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMAbsolute(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[1]);
        _U.putDouble(address + 16, self.data[2]);
        _U.putDouble(address + 24, self.data[3]);
        _U.putDouble(_p1, self.data[4]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[6]);
        _U.putDouble(_p1 + 24, self.data[7]);
        _U.putDouble(_p2, self.data[8]);
        _U.putDouble(_p2 + 8, self.data[9]);
        _U.putDouble(_p2 + 16, self.data[10]);
        _U.putDouble(_p2 + 24, self.data[11]);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Double3x4 loadRMAbsolute(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8);
        self.data[2] = _U.getDouble(address + 16);
        self.data[3] = _U.getDouble(address + 24);
        self.data[4] = _U.getDouble(_p1);
        self.data[5] = _U.getDouble(_p1 + 8);
        self.data[6] = _U.getDouble(_p1 + 16);
        self.data[7] = _U.getDouble(_p1 + 24);
        self.data[8] = _U.getDouble(_p2);
        self.data[9] = _U.getDouble(_p2 + 8);
        self.data[10] = _U.getDouble(_p2 + 16);
        self.data[11] = _U.getDouble(_p2 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadRMAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf, stride);
        return RAW.loadRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    public FloatBuffer storeRMAbsolute(Double3x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[1]);
        _U.putFloat(address + 8, (float) self.data[2]);
        _U.putFloat(address + 12, (float) self.data[3]);
        _U.putFloat(_p1, (float) self.data[4]);
        _U.putFloat(_p1 + 4, (float) self.data[5]);
        _U.putFloat(_p1 + 8, (float) self.data[6]);
        _U.putFloat(_p1 + 12, (float) self.data[7]);
        _U.putFloat(_p2, (float) self.data[8]);
        _U.putFloat(_p2 + 4, (float) self.data[9]);
        _U.putFloat(_p2 + 8, (float) self.data[10]);
        _U.putFloat(_p2 + 12, (float) self.data[11]);
        return buf;
    }

    public Double3x4 loadRMAbsolute(Double3x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4);
        self.data[2] = _U.getFloat(address + 8);
        self.data[3] = _U.getFloat(address + 12);
        self.data[4] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[6] = _U.getFloat(_p1 + 8);
        self.data[7] = _U.getFloat(_p1 + 12);
        self.data[8] = _U.getFloat(_p2);
        self.data[9] = _U.getFloat(_p2 + 4);
        self.data[10] = _U.getFloat(_p2 + 8);
        self.data[11] = _U.getFloat(_p2 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMFloatAbsolute(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMFloatAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4, (float) self.data[1]);
        _U.putFloat(address + 8, (float) self.data[2]);
        _U.putFloat(address + 12, (float) self.data[3]);
        _U.putFloat(_p1, (float) self.data[4]);
        _U.putFloat(_p1 + 4, (float) self.data[5]);
        _U.putFloat(_p1 + 8, (float) self.data[6]);
        _U.putFloat(_p1 + 12, (float) self.data[7]);
        _U.putFloat(_p2, (float) self.data[8]);
        _U.putFloat(_p2 + 4, (float) self.data[9]);
        _U.putFloat(_p2 + 8, (float) self.data[10]);
        _U.putFloat(_p2 + 12, (float) self.data[11]);
        return buf;
    }

    private static ByteBuffer storeRMFloatAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMFloatAbsolute(self, index, buf, stride);
        RAW.storeRMFloatHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Double3x4 loadRMFloatAbsolute(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMFloatAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4);
        self.data[2] = _U.getFloat(address + 8);
        self.data[3] = _U.getFloat(address + 12);
        self.data[4] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[6] = _U.getFloat(_p1 + 8);
        self.data[7] = _U.getFloat(_p1 + 12);
        self.data[8] = _U.getFloat(_p2);
        self.data[9] = _U.getFloat(_p2 + 4);
        self.data[10] = _U.getFloat(_p2 + 8);
        self.data[11] = _U.getFloat(_p2 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x4 loadRMFloatAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMFloatAbsolute(self, index, buf, stride);
        return RAW.loadRMFloatHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    public DoubleBuffer storeCM4x4Absolute(Double3x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4Unsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }

    public ByteBuffer storeCM4x4Absolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM4x4AbsoluteElse(self, index, buf);
        RAW.storeCM4x4Unsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeCM4x4AbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4Heap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public FloatBuffer storeCM4x4Absolute(Double3x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCM4x4Absolute(self, index, buf);
        RAW.storeCM4x4FloatUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }

    public ByteBuffer storeCM4x4FloatAbsolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCM4x4FloatAbsoluteElse(self, index, buf);
        RAW.storeCM4x4FloatUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeCM4x4FloatAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCM4x4FloatAbsolute(self, index, buf);
        RAW.storeCM4x4FloatHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public DoubleBuffer storeRM4x4Absolute(Double3x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4Unsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }

    public ByteBuffer storeRM4x4Absolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM4x4AbsoluteElse(self, index, buf);
        RAW.storeRM4x4Unsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeRM4x4AbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4Heap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public FloatBuffer storeRM4x4Absolute(Double3x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRM4x4Absolute(self, index, buf);
        RAW.storeRM4x4FloatUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }

    public ByteBuffer storeRM4x4FloatAbsolute(Double3x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRM4x4FloatAbsoluteElse(self, index, buf);
        RAW.storeRM4x4FloatUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeRM4x4FloatAbsoluteElse(Double3x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRM4x4FloatAbsolute(self, index, buf);
        RAW.storeRM4x4FloatHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }
}
