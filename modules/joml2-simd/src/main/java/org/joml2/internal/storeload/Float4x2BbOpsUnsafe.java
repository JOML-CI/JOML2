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

public final class Float4x2BbOpsUnsafe extends Float4x2BbOps {
    private static final Float4x2BbOpsApi API = new Float4x2BbOpsApi();

    public FloatBuffer storeCMAbsolute(Float4x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[1]);
        _U.putFloat(address + 8L, self.data[2]);
        _U.putFloat(address + 12L, self.data[3]);
        _U.putFloat(address + 16L, self.data[4]);
        _U.putFloat(address + 20L, self.data[5]);
        _U.putFloat(address + 24L, self.data[6]);
        _U.putFloat(address + 28L, self.data[7]);
        return buf;
    }

    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
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
        return self;
    }

    public ByteBuffer storeCMAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[1]);
        _U.putFloat(address + 8L, self.data[2]);
        _U.putFloat(address + 12L, self.data[3]);
        _U.putFloat(address + 16L, self.data[4]);
        _U.putFloat(address + 20L, self.data[5]);
        _U.putFloat(address + 24L, self.data[6]);
        _U.putFloat(address + 28L, self.data[7]);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4L, self.data[1]);
        _U.putFloat(bytes, address + 8L, self.data[2]);
        _U.putFloat(bytes, address + 12L, self.data[3]);
        _U.putFloat(bytes, address + 16L, self.data[4]);
        _U.putFloat(bytes, address + 20L, self.data[5]);
        _U.putFloat(bytes, address + 24L, self.data[6]);
        _U.putFloat(bytes, address + 28L, self.data[7]);
        return buf;
    }

    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf);
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
        return self;
    }

    private static Float4x2 loadCMAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf);
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
        return self;
    }

    public DoubleBuffer storeCMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
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
        return buf;
    }

    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = (float) _U.getDouble(address);
        self.data[1] = (float) _U.getDouble(address + 8L);
        self.data[2] = (float) _U.getDouble(address + 16L);
        self.data[3] = (float) _U.getDouble(address + 24L);
        self.data[4] = (float) _U.getDouble(address + 32L);
        self.data[5] = (float) _U.getDouble(address + 40L);
        self.data[6] = (float) _U.getDouble(address + 48L);
        self.data[7] = (float) _U.getDouble(address + 56L);
        return self;
    }

    public ByteBuffer storeCMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf);
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
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf);
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
        return buf;
    }

    public Float4x2 loadCMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = (float) _U.getDouble(address);
        self.data[1] = (float) _U.getDouble(address + 8L);
        self.data[2] = (float) _U.getDouble(address + 16L);
        self.data[3] = (float) _U.getDouble(address + 24L);
        self.data[4] = (float) _U.getDouble(address + 32L);
        self.data[5] = (float) _U.getDouble(address + 40L);
        self.data[6] = (float) _U.getDouble(address + 48L);
        self.data[7] = (float) _U.getDouble(address + 56L);
        return self;
    }

    private static Float4x2 loadCMDoubleAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[1] = (float) _U.getDouble(bytes, address + 8L);
        self.data[2] = (float) _U.getDouble(bytes, address + 16L);
        self.data[3] = (float) _U.getDouble(bytes, address + 24L);
        self.data[4] = (float) _U.getDouble(bytes, address + 32L);
        self.data[5] = (float) _U.getDouble(bytes, address + 40L);
        self.data[6] = (float) _U.getDouble(bytes, address + 48L);
        self.data[7] = (float) _U.getDouble(bytes, address + 56L);
        return self;
    }

    public FloatBuffer storeRMAbsolute(Float4x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[4]);
        _U.putFloat(address + 8L, self.data[1]);
        _U.putFloat(address + 12L, self.data[5]);
        _U.putFloat(address + 16L, self.data[2]);
        _U.putFloat(address + 20L, self.data[6]);
        _U.putFloat(address + 24L, self.data[3]);
        _U.putFloat(address + 28L, self.data[7]);
        return buf;
    }

    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4L);
        self.data[1] = _U.getFloat(address + 8L);
        self.data[5] = _U.getFloat(address + 12L);
        self.data[2] = _U.getFloat(address + 16L);
        self.data[6] = _U.getFloat(address + 20L);
        self.data[3] = _U.getFloat(address + 24L);
        self.data[7] = _U.getFloat(address + 28L);
        return self;
    }

    public ByteBuffer storeRMAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[4]);
        _U.putFloat(address + 8L, self.data[1]);
        _U.putFloat(address + 12L, self.data[5]);
        _U.putFloat(address + 16L, self.data[2]);
        _U.putFloat(address + 20L, self.data[6]);
        _U.putFloat(address + 24L, self.data[3]);
        _U.putFloat(address + 28L, self.data[7]);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4L, self.data[4]);
        _U.putFloat(bytes, address + 8L, self.data[1]);
        _U.putFloat(bytes, address + 12L, self.data[5]);
        _U.putFloat(bytes, address + 16L, self.data[2]);
        _U.putFloat(bytes, address + 20L, self.data[6]);
        _U.putFloat(bytes, address + 24L, self.data[3]);
        _U.putFloat(bytes, address + 28L, self.data[7]);
        return buf;
    }

    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4L);
        self.data[1] = _U.getFloat(address + 8L);
        self.data[5] = _U.getFloat(address + 12L);
        self.data[2] = _U.getFloat(address + 16L);
        self.data[6] = _U.getFloat(address + 20L);
        self.data[3] = _U.getFloat(address + 24L);
        self.data[7] = _U.getFloat(address + 28L);
        return self;
    }

    private static Float4x2 loadRMAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[4] = _U.getFloat(bytes, address + 4L);
        self.data[1] = _U.getFloat(bytes, address + 8L);
        self.data[5] = _U.getFloat(bytes, address + 12L);
        self.data[2] = _U.getFloat(bytes, address + 16L);
        self.data[6] = _U.getFloat(bytes, address + 20L);
        self.data[3] = _U.getFloat(bytes, address + 24L);
        self.data[7] = _U.getFloat(bytes, address + 28L);
        return self;
    }

    public DoubleBuffer storeRMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[4]);
        _U.putDouble(address + 16L, self.data[1]);
        _U.putDouble(address + 24L, self.data[5]);
        _U.putDouble(address + 32L, self.data[2]);
        _U.putDouble(address + 40L, self.data[6]);
        _U.putDouble(address + 48L, self.data[3]);
        _U.putDouble(address + 56L, self.data[7]);
        return buf;
    }

    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = (float) _U.getDouble(address);
        self.data[4] = (float) _U.getDouble(address + 8L);
        self.data[1] = (float) _U.getDouble(address + 16L);
        self.data[5] = (float) _U.getDouble(address + 24L);
        self.data[2] = (float) _U.getDouble(address + 32L);
        self.data[6] = (float) _U.getDouble(address + 40L);
        self.data[3] = (float) _U.getDouble(address + 48L);
        self.data[7] = (float) _U.getDouble(address + 56L);
        return self;
    }

    public ByteBuffer storeRMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[4]);
        _U.putDouble(address + 16L, self.data[1]);
        _U.putDouble(address + 24L, self.data[5]);
        _U.putDouble(address + 32L, self.data[2]);
        _U.putDouble(address + 40L, self.data[6]);
        _U.putDouble(address + 48L, self.data[3]);
        _U.putDouble(address + 56L, self.data[7]);
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[4]);
        _U.putDouble(bytes, address + 16L, self.data[1]);
        _U.putDouble(bytes, address + 24L, self.data[5]);
        _U.putDouble(bytes, address + 32L, self.data[2]);
        _U.putDouble(bytes, address + 40L, self.data[6]);
        _U.putDouble(bytes, address + 48L, self.data[3]);
        _U.putDouble(bytes, address + 56L, self.data[7]);
        return buf;
    }

    public Float4x2 loadRMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = (float) _U.getDouble(address);
        self.data[4] = (float) _U.getDouble(address + 8L);
        self.data[1] = (float) _U.getDouble(address + 16L);
        self.data[5] = (float) _U.getDouble(address + 24L);
        self.data[2] = (float) _U.getDouble(address + 32L);
        self.data[6] = (float) _U.getDouble(address + 40L);
        self.data[3] = (float) _U.getDouble(address + 48L);
        self.data[7] = (float) _U.getDouble(address + 56L);
        return self;
    }

    private static Float4x2 loadRMDoubleAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[4] = (float) _U.getDouble(bytes, address + 8L);
        self.data[1] = (float) _U.getDouble(bytes, address + 16L);
        self.data[5] = (float) _U.getDouble(bytes, address + 24L);
        self.data[2] = (float) _U.getDouble(bytes, address + 32L);
        self.data[6] = (float) _U.getDouble(bytes, address + 40L);
        self.data[3] = (float) _U.getDouble(bytes, address + 48L);
        self.data[7] = (float) _U.getDouble(bytes, address + 56L);
        return self;
    }

    public FloatBuffer storeCMAbsolute(Float4x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4, self.data[1]);
        _U.putFloat(address + 8, self.data[2]);
        _U.putFloat(address + 12, self.data[3]);
        _U.putFloat(_p1, self.data[4]);
        _U.putFloat(_p1 + 4, self.data[5]);
        _U.putFloat(_p1 + 8, self.data[6]);
        _U.putFloat(_p1 + 12, self.data[7]);
        return buf;
    }

    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4);
        self.data[2] = _U.getFloat(address + 8);
        self.data[3] = _U.getFloat(address + 12);
        self.data[4] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[6] = _U.getFloat(_p1 + 8);
        self.data[7] = _U.getFloat(_p1 + 12);
        return self;
    }

    public ByteBuffer storeCMAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4, self.data[1]);
        _U.putFloat(address + 8, self.data[2]);
        _U.putFloat(address + 12, self.data[3]);
        _U.putFloat(_p1, self.data[4]);
        _U.putFloat(_p1 + 4, self.data[5]);
        _U.putFloat(_p1 + 8, self.data[6]);
        _U.putFloat(_p1 + 12, self.data[7]);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4, self.data[1]);
        _U.putFloat(bytes, address + 8, self.data[2]);
        _U.putFloat(bytes, address + 12, self.data[3]);
        _U.putFloat(bytes, _p1, self.data[4]);
        _U.putFloat(bytes, _p1 + 4, self.data[5]);
        _U.putFloat(bytes, _p1 + 8, self.data[6]);
        _U.putFloat(bytes, _p1 + 12, self.data[7]);
        return buf;
    }

    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4);
        self.data[2] = _U.getFloat(address + 8);
        self.data[3] = _U.getFloat(address + 12);
        self.data[4] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[6] = _U.getFloat(_p1 + 8);
        self.data[7] = _U.getFloat(_p1 + 12);
        return self;
    }

    private static Float4x2 loadCMAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4);
        self.data[2] = _U.getFloat(bytes, address + 8);
        self.data[3] = _U.getFloat(bytes, address + 12);
        self.data[4] = _U.getFloat(bytes, _p1);
        self.data[5] = _U.getFloat(bytes, _p1 + 4);
        self.data[6] = _U.getFloat(bytes, _p1 + 8);
        self.data[7] = _U.getFloat(bytes, _p1 + 12);
        return self;
    }

    public DoubleBuffer storeCMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[1]);
        _U.putDouble(address + 16, self.data[2]);
        _U.putDouble(address + 24, self.data[3]);
        _U.putDouble(_p1, self.data[4]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[6]);
        _U.putDouble(_p1 + 24, self.data[7]);
        return buf;
    }

    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = (float) _U.getDouble(address);
        self.data[1] = (float) _U.getDouble(address + 8);
        self.data[2] = (float) _U.getDouble(address + 16);
        self.data[3] = (float) _U.getDouble(address + 24);
        self.data[4] = (float) _U.getDouble(_p1);
        self.data[5] = (float) _U.getDouble(_p1 + 8);
        self.data[6] = (float) _U.getDouble(_p1 + 16);
        self.data[7] = (float) _U.getDouble(_p1 + 24);
        return self;
    }

    public ByteBuffer storeCMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[1]);
        _U.putDouble(address + 16, self.data[2]);
        _U.putDouble(address + 24, self.data[3]);
        _U.putDouble(_p1, self.data[4]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[6]);
        _U.putDouble(_p1 + 24, self.data[7]);
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[1]);
        _U.putDouble(bytes, address + 16, self.data[2]);
        _U.putDouble(bytes, address + 24, self.data[3]);
        _U.putDouble(bytes, _p1, self.data[4]);
        _U.putDouble(bytes, _p1 + 8, self.data[5]);
        _U.putDouble(bytes, _p1 + 16, self.data[6]);
        _U.putDouble(bytes, _p1 + 24, self.data[7]);
        return buf;
    }

    public Float4x2 loadCMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = (float) _U.getDouble(address);
        self.data[1] = (float) _U.getDouble(address + 8);
        self.data[2] = (float) _U.getDouble(address + 16);
        self.data[3] = (float) _U.getDouble(address + 24);
        self.data[4] = (float) _U.getDouble(_p1);
        self.data[5] = (float) _U.getDouble(_p1 + 8);
        self.data[6] = (float) _U.getDouble(_p1 + 16);
        self.data[7] = (float) _U.getDouble(_p1 + 24);
        return self;
    }

    private static Float4x2 loadCMDoubleAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[1] = (float) _U.getDouble(bytes, address + 8);
        self.data[2] = (float) _U.getDouble(bytes, address + 16);
        self.data[3] = (float) _U.getDouble(bytes, address + 24);
        self.data[4] = (float) _U.getDouble(bytes, _p1);
        self.data[5] = (float) _U.getDouble(bytes, _p1 + 8);
        self.data[6] = (float) _U.getDouble(bytes, _p1 + 16);
        self.data[7] = (float) _U.getDouble(bytes, _p1 + 24);
        return self;
    }

    public FloatBuffer storeRMAbsolute(Float4x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4, self.data[4]);
        _U.putFloat(_p1, self.data[1]);
        _U.putFloat(_p1 + 4, self.data[5]);
        _U.putFloat(_p2, self.data[2]);
        _U.putFloat(_p2 + 4, self.data[6]);
        _U.putFloat(_p3, self.data[3]);
        _U.putFloat(_p3 + 4, self.data[7]);
        return buf;
    }

    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4);
        self.data[1] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[2] = _U.getFloat(_p2);
        self.data[6] = _U.getFloat(_p2 + 4);
        self.data[3] = _U.getFloat(_p3);
        self.data[7] = _U.getFloat(_p3 + 4);
        return self;
    }

    public ByteBuffer storeRMAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4, self.data[4]);
        _U.putFloat(_p1, self.data[1]);
        _U.putFloat(_p1 + 4, self.data[5]);
        _U.putFloat(_p2, self.data[2]);
        _U.putFloat(_p2 + 4, self.data[6]);
        _U.putFloat(_p3, self.data[3]);
        _U.putFloat(_p3 + 4, self.data[7]);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4, self.data[4]);
        _U.putFloat(bytes, _p1, self.data[1]);
        _U.putFloat(bytes, _p1 + 4, self.data[5]);
        _U.putFloat(bytes, _p2, self.data[2]);
        _U.putFloat(bytes, _p2 + 4, self.data[6]);
        _U.putFloat(bytes, _p3, self.data[3]);
        _U.putFloat(bytes, _p3 + 4, self.data[7]);
        return buf;
    }

    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4);
        self.data[1] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[2] = _U.getFloat(_p2);
        self.data[6] = _U.getFloat(_p2 + 4);
        self.data[3] = _U.getFloat(_p3);
        self.data[7] = _U.getFloat(_p3 + 4);
        return self;
    }

    private static Float4x2 loadRMAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[4] = _U.getFloat(bytes, address + 4);
        self.data[1] = _U.getFloat(bytes, _p1);
        self.data[5] = _U.getFloat(bytes, _p1 + 4);
        self.data[2] = _U.getFloat(bytes, _p2);
        self.data[6] = _U.getFloat(bytes, _p2 + 4);
        self.data[3] = _U.getFloat(bytes, _p3);
        self.data[7] = _U.getFloat(bytes, _p3 + 4);
        return self;
    }

    public DoubleBuffer storeRMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[4]);
        _U.putDouble(_p1, self.data[1]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p2, self.data[2]);
        _U.putDouble(_p2 + 8, self.data[6]);
        _U.putDouble(_p3, self.data[3]);
        _U.putDouble(_p3 + 8, self.data[7]);
        return buf;
    }

    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = (float) _U.getDouble(address);
        self.data[4] = (float) _U.getDouble(address + 8);
        self.data[1] = (float) _U.getDouble(_p1);
        self.data[5] = (float) _U.getDouble(_p1 + 8);
        self.data[2] = (float) _U.getDouble(_p2);
        self.data[6] = (float) _U.getDouble(_p2 + 8);
        self.data[3] = (float) _U.getDouble(_p3);
        self.data[7] = (float) _U.getDouble(_p3 + 8);
        return self;
    }

    public ByteBuffer storeRMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[4]);
        _U.putDouble(_p1, self.data[1]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p2, self.data[2]);
        _U.putDouble(_p2 + 8, self.data[6]);
        _U.putDouble(_p3, self.data[3]);
        _U.putDouble(_p3 + 8, self.data[7]);
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[4]);
        _U.putDouble(bytes, _p1, self.data[1]);
        _U.putDouble(bytes, _p1 + 8, self.data[5]);
        _U.putDouble(bytes, _p2, self.data[2]);
        _U.putDouble(bytes, _p2 + 8, self.data[6]);
        _U.putDouble(bytes, _p3, self.data[3]);
        _U.putDouble(bytes, _p3 + 8, self.data[7]);
        return buf;
    }

    public Float4x2 loadRMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = (float) _U.getDouble(address);
        self.data[4] = (float) _U.getDouble(address + 8);
        self.data[1] = (float) _U.getDouble(_p1);
        self.data[5] = (float) _U.getDouble(_p1 + 8);
        self.data[2] = (float) _U.getDouble(_p2);
        self.data[6] = (float) _U.getDouble(_p2 + 8);
        self.data[3] = (float) _U.getDouble(_p3);
        self.data[7] = (float) _U.getDouble(_p3 + 8);
        return self;
    }

    private static Float4x2 loadRMDoubleAbsoluteElse(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[4] = (float) _U.getDouble(bytes, address + 8);
        self.data[1] = (float) _U.getDouble(bytes, _p1);
        self.data[5] = (float) _U.getDouble(bytes, _p1 + 8);
        self.data[2] = (float) _U.getDouble(bytes, _p2);
        self.data[6] = (float) _U.getDouble(bytes, _p2 + 8);
        self.data[3] = (float) _U.getDouble(bytes, _p3);
        self.data[7] = (float) _U.getDouble(bytes, _p3 + 8);
        return self;
    }
}
