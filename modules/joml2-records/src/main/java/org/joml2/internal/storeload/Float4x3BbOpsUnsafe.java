// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x3BbOpsUnsafe extends Float4x3BbOps {
    private static final Float4x3BbOpsApi API = new Float4x3BbOpsApi();
    private static final Float4x3RawOpsUnsafe RAW = new Float4x3RawOpsUnsafe();

    public FloatBuffer storeCMAbsolute(Float4x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, self.m20());
        _U.putFloat(address + 12L, self.m30());
        _U.putFloat(address + 16L, self.m01());
        _U.putFloat(address + 20L, self.m11());
        _U.putFloat(address + 24L, self.m21());
        _U.putFloat(address + 28L, self.m31());
        _U.putFloat(address + 32L, self.m02());
        _U.putFloat(address + 36L, self.m12());
        _U.putFloat(address + 40L, self.m22());
        _U.putFloat(address + 44L, self.m32());
        return buf;
    }

    public Float4x3 loadCMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = _U.getFloat(address);
        float _c3 = _U.getFloat(address + 4L);
        float _c6 = _U.getFloat(address + 8L);
        float _c9 = _U.getFloat(address + 12L);
        float _c1 = _U.getFloat(address + 16L);
        float _c4 = _U.getFloat(address + 20L);
        float _c7 = _U.getFloat(address + 24L);
        float _c10 = _U.getFloat(address + 28L);
        float _c2 = _U.getFloat(address + 32L);
        float _c5 = _U.getFloat(address + 36L);
        float _c8 = _U.getFloat(address + 40L);
        float _c11 = _U.getFloat(address + 44L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public ByteBuffer storeCMAbsolute(Float4x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, self.m20());
        _U.putFloat(address + 12L, self.m30());
        _U.putFloat(address + 16L, self.m01());
        _U.putFloat(address + 20L, self.m11());
        _U.putFloat(address + 24L, self.m21());
        _U.putFloat(address + 28L, self.m31());
        _U.putFloat(address + 32L, self.m02());
        _U.putFloat(address + 36L, self.m12());
        _U.putFloat(address + 40L, self.m22());
        _U.putFloat(address + 44L, self.m32());
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float4x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m10());
        _U.putFloat(bytes, address + 8L, self.m20());
        _U.putFloat(bytes, address + 12L, self.m30());
        _U.putFloat(bytes, address + 16L, self.m01());
        _U.putFloat(bytes, address + 20L, self.m11());
        _U.putFloat(bytes, address + 24L, self.m21());
        _U.putFloat(bytes, address + 28L, self.m31());
        _U.putFloat(bytes, address + 32L, self.m02());
        _U.putFloat(bytes, address + 36L, self.m12());
        _U.putFloat(bytes, address + 40L, self.m22());
        _U.putFloat(bytes, address + 44L, self.m32());
        return buf;
    }

    public Float4x3 loadCMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = _U.getFloat(address);
        float _c3 = _U.getFloat(address + 4L);
        float _c6 = _U.getFloat(address + 8L);
        float _c9 = _U.getFloat(address + 12L);
        float _c1 = _U.getFloat(address + 16L);
        float _c4 = _U.getFloat(address + 20L);
        float _c7 = _U.getFloat(address + 24L);
        float _c10 = _U.getFloat(address + 28L);
        float _c2 = _U.getFloat(address + 32L);
        float _c5 = _U.getFloat(address + 36L);
        float _c8 = _U.getFloat(address + 40L);
        float _c11 = _U.getFloat(address + 44L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Float4x3 loadCMAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = _U.getFloat(bytes, address);
        float _c3 = _U.getFloat(bytes, address + 4L);
        float _c6 = _U.getFloat(bytes, address + 8L);
        float _c9 = _U.getFloat(bytes, address + 12L);
        float _c1 = _U.getFloat(bytes, address + 16L);
        float _c4 = _U.getFloat(bytes, address + 20L);
        float _c7 = _U.getFloat(bytes, address + 24L);
        float _c10 = _U.getFloat(bytes, address + 28L);
        float _c2 = _U.getFloat(bytes, address + 32L);
        float _c5 = _U.getFloat(bytes, address + 36L);
        float _c8 = _U.getFloat(bytes, address + 40L);
        float _c11 = _U.getFloat(bytes, address + 44L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public DoubleBuffer storeCMAbsolute(Float4x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, self.m20());
        _U.putDouble(address + 24L, self.m30());
        _U.putDouble(address + 32L, self.m01());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, self.m21());
        _U.putDouble(address + 56L, self.m31());
        _U.putDouble(address + 64L, self.m02());
        _U.putDouble(address + 72L, self.m12());
        _U.putDouble(address + 80L, self.m22());
        _U.putDouble(address + 88L, self.m32());
        return buf;
    }

    public Float4x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) _U.getDouble(address);
        float _c3 = (float) _U.getDouble(address + 8L);
        float _c6 = (float) _U.getDouble(address + 16L);
        float _c9 = (float) _U.getDouble(address + 24L);
        float _c1 = (float) _U.getDouble(address + 32L);
        float _c4 = (float) _U.getDouble(address + 40L);
        float _c7 = (float) _U.getDouble(address + 48L);
        float _c10 = (float) _U.getDouble(address + 56L);
        float _c2 = (float) _U.getDouble(address + 64L);
        float _c5 = (float) _U.getDouble(address + 72L);
        float _c8 = (float) _U.getDouble(address + 80L);
        float _c11 = (float) _U.getDouble(address + 88L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public ByteBuffer storeCMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, self.m20());
        _U.putDouble(address + 24L, self.m30());
        _U.putDouble(address + 32L, self.m01());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, self.m21());
        _U.putDouble(address + 56L, self.m31());
        _U.putDouble(address + 64L, self.m02());
        _U.putDouble(address + 72L, self.m12());
        _U.putDouble(address + 80L, self.m22());
        _U.putDouble(address + 88L, self.m32());
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m10());
        _U.putDouble(bytes, address + 16L, self.m20());
        _U.putDouble(bytes, address + 24L, self.m30());
        _U.putDouble(bytes, address + 32L, self.m01());
        _U.putDouble(bytes, address + 40L, self.m11());
        _U.putDouble(bytes, address + 48L, self.m21());
        _U.putDouble(bytes, address + 56L, self.m31());
        _U.putDouble(bytes, address + 64L, self.m02());
        _U.putDouble(bytes, address + 72L, self.m12());
        _U.putDouble(bytes, address + 80L, self.m22());
        _U.putDouble(bytes, address + 88L, self.m32());
        return buf;
    }

    public Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) _U.getDouble(address);
        float _c3 = (float) _U.getDouble(address + 8L);
        float _c6 = (float) _U.getDouble(address + 16L);
        float _c9 = (float) _U.getDouble(address + 24L);
        float _c1 = (float) _U.getDouble(address + 32L);
        float _c4 = (float) _U.getDouble(address + 40L);
        float _c7 = (float) _U.getDouble(address + 48L);
        float _c10 = (float) _U.getDouble(address + 56L);
        float _c2 = (float) _U.getDouble(address + 64L);
        float _c5 = (float) _U.getDouble(address + 72L);
        float _c8 = (float) _U.getDouble(address + 80L);
        float _c11 = (float) _U.getDouble(address + 88L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Float4x3 loadCMDoubleAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c3 = (float) _U.getDouble(bytes, address + 8L);
        float _c6 = (float) _U.getDouble(bytes, address + 16L);
        float _c9 = (float) _U.getDouble(bytes, address + 24L);
        float _c1 = (float) _U.getDouble(bytes, address + 32L);
        float _c4 = (float) _U.getDouble(bytes, address + 40L);
        float _c7 = (float) _U.getDouble(bytes, address + 48L);
        float _c10 = (float) _U.getDouble(bytes, address + 56L);
        float _c2 = (float) _U.getDouble(bytes, address + 64L);
        float _c5 = (float) _U.getDouble(bytes, address + 72L);
        float _c8 = (float) _U.getDouble(bytes, address + 80L);
        float _c11 = (float) _U.getDouble(bytes, address + 88L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public FloatBuffer storeRMAbsolute(Float4x3 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, self.m02());
        _U.putFloat(address + 12L, self.m10());
        _U.putFloat(address + 16L, self.m11());
        _U.putFloat(address + 20L, self.m12());
        _U.putFloat(address + 24L, self.m20());
        _U.putFloat(address + 28L, self.m21());
        _U.putFloat(address + 32L, self.m22());
        _U.putFloat(address + 36L, self.m30());
        _U.putFloat(address + 40L, self.m31());
        _U.putFloat(address + 44L, self.m32());
        return buf;
    }

    public Float4x3 loadRMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c6 = _U.getFloat(address + 24L);
        float _c7 = _U.getFloat(address + 28L);
        float _c8 = _U.getFloat(address + 32L);
        float _c9 = _U.getFloat(address + 36L);
        float _c10 = _U.getFloat(address + 40L);
        float _c11 = _U.getFloat(address + 44L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public ByteBuffer storeRMAbsolute(Float4x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, self.m02());
        _U.putFloat(address + 12L, self.m10());
        _U.putFloat(address + 16L, self.m11());
        _U.putFloat(address + 20L, self.m12());
        _U.putFloat(address + 24L, self.m20());
        _U.putFloat(address + 28L, self.m21());
        _U.putFloat(address + 32L, self.m22());
        _U.putFloat(address + 36L, self.m30());
        _U.putFloat(address + 40L, self.m31());
        _U.putFloat(address + 44L, self.m32());
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float4x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m01());
        _U.putFloat(bytes, address + 8L, self.m02());
        _U.putFloat(bytes, address + 12L, self.m10());
        _U.putFloat(bytes, address + 16L, self.m11());
        _U.putFloat(bytes, address + 20L, self.m12());
        _U.putFloat(bytes, address + 24L, self.m20());
        _U.putFloat(bytes, address + 28L, self.m21());
        _U.putFloat(bytes, address + 32L, self.m22());
        _U.putFloat(bytes, address + 36L, self.m30());
        _U.putFloat(bytes, address + 40L, self.m31());
        _U.putFloat(bytes, address + 44L, self.m32());
        return buf;
    }

    public Float4x3 loadRMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c6 = _U.getFloat(address + 24L);
        float _c7 = _U.getFloat(address + 28L);
        float _c8 = _U.getFloat(address + 32L);
        float _c9 = _U.getFloat(address + 36L);
        float _c10 = _U.getFloat(address + 40L);
        float _c11 = _U.getFloat(address + 44L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Float4x3 loadRMAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4L);
        float _c2 = _U.getFloat(bytes, address + 8L);
        float _c3 = _U.getFloat(bytes, address + 12L);
        float _c4 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        float _c6 = _U.getFloat(bytes, address + 24L);
        float _c7 = _U.getFloat(bytes, address + 28L);
        float _c8 = _U.getFloat(bytes, address + 32L);
        float _c9 = _U.getFloat(bytes, address + 36L);
        float _c10 = _U.getFloat(bytes, address + 40L);
        float _c11 = _U.getFloat(bytes, address + 44L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public DoubleBuffer storeRMAbsolute(Float4x3 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, self.m02());
        _U.putDouble(address + 24L, self.m10());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, self.m12());
        _U.putDouble(address + 48L, self.m20());
        _U.putDouble(address + 56L, self.m21());
        _U.putDouble(address + 64L, self.m22());
        _U.putDouble(address + 72L, self.m30());
        _U.putDouble(address + 80L, self.m31());
        _U.putDouble(address + 88L, self.m32());
        return buf;
    }

    public Float4x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c6 = (float) _U.getDouble(address + 48L);
        float _c7 = (float) _U.getDouble(address + 56L);
        float _c8 = (float) _U.getDouble(address + 64L);
        float _c9 = (float) _U.getDouble(address + 72L);
        float _c10 = (float) _U.getDouble(address + 80L);
        float _c11 = (float) _U.getDouble(address + 88L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public ByteBuffer storeRMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, self.m02());
        _U.putDouble(address + 24L, self.m10());
        _U.putDouble(address + 32L, self.m11());
        _U.putDouble(address + 40L, self.m12());
        _U.putDouble(address + 48L, self.m20());
        _U.putDouble(address + 56L, self.m21());
        _U.putDouble(address + 64L, self.m22());
        _U.putDouble(address + 72L, self.m30());
        _U.putDouble(address + 80L, self.m31());
        _U.putDouble(address + 88L, self.m32());
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x3 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, self.m02());
        _U.putDouble(bytes, address + 24L, self.m10());
        _U.putDouble(bytes, address + 32L, self.m11());
        _U.putDouble(bytes, address + 40L, self.m12());
        _U.putDouble(bytes, address + 48L, self.m20());
        _U.putDouble(bytes, address + 56L, self.m21());
        _U.putDouble(bytes, address + 64L, self.m22());
        _U.putDouble(bytes, address + 72L, self.m30());
        _U.putDouble(bytes, address + 80L, self.m31());
        _U.putDouble(bytes, address + 88L, self.m32());
        return buf;
    }

    public Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c6 = (float) _U.getDouble(address + 48L);
        float _c7 = (float) _U.getDouble(address + 56L);
        float _c8 = (float) _U.getDouble(address + 64L);
        float _c9 = (float) _U.getDouble(address + 72L);
        float _c10 = (float) _U.getDouble(address + 80L);
        float _c11 = (float) _U.getDouble(address + 88L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Float4x3 loadRMDoubleAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8L);
        float _c2 = (float) _U.getDouble(bytes, address + 16L);
        float _c3 = (float) _U.getDouble(bytes, address + 24L);
        float _c4 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        float _c6 = (float) _U.getDouble(bytes, address + 48L);
        float _c7 = (float) _U.getDouble(bytes, address + 56L);
        float _c8 = (float) _U.getDouble(bytes, address + 64L);
        float _c9 = (float) _U.getDouble(bytes, address + 72L);
        float _c10 = (float) _U.getDouble(bytes, address + 80L);
        float _c11 = (float) _U.getDouble(bytes, address + 88L);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public FloatBuffer storeCMAbsolute(Float4x3 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m10());
        _U.putFloat(address + 8, self.m20());
        _U.putFloat(address + 12, self.m30());
        _U.putFloat(_p1, self.m01());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p1 + 8, self.m21());
        _U.putFloat(_p1 + 12, self.m31());
        _U.putFloat(_p2, self.m02());
        _U.putFloat(_p2 + 4, self.m12());
        _U.putFloat(_p2 + 8, self.m22());
        _U.putFloat(_p2 + 12, self.m32());
        return buf;
    }

    public Float4x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = _U.getFloat(address);
        float _c3 = _U.getFloat(address + 4);
        float _c6 = _U.getFloat(address + 8);
        float _c9 = _U.getFloat(address + 12);
        float _c1 = _U.getFloat(_p1);
        float _c4 = _U.getFloat(_p1 + 4);
        float _c7 = _U.getFloat(_p1 + 8);
        float _c10 = _U.getFloat(_p1 + 12);
        float _c2 = _U.getFloat(_p2);
        float _c5 = _U.getFloat(_p2 + 4);
        float _c8 = _U.getFloat(_p2 + 8);
        float _c11 = _U.getFloat(_p2 + 12);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public ByteBuffer storeCMAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m10());
        _U.putFloat(address + 8, self.m20());
        _U.putFloat(address + 12, self.m30());
        _U.putFloat(_p1, self.m01());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p1 + 8, self.m21());
        _U.putFloat(_p1 + 12, self.m31());
        _U.putFloat(_p2, self.m02());
        _U.putFloat(_p2 + 4, self.m12());
        _U.putFloat(_p2 + 8, self.m22());
        _U.putFloat(_p2 + 12, self.m32());
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float4x3 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = _U.getFloat(address);
        float _c3 = _U.getFloat(address + 4);
        float _c6 = _U.getFloat(address + 8);
        float _c9 = _U.getFloat(address + 12);
        float _c1 = _U.getFloat(_p1);
        float _c4 = _U.getFloat(_p1 + 4);
        float _c7 = _U.getFloat(_p1 + 8);
        float _c10 = _U.getFloat(_p1 + 12);
        float _c2 = _U.getFloat(_p2);
        float _c5 = _U.getFloat(_p2 + 4);
        float _c8 = _U.getFloat(_p2 + 8);
        float _c11 = _U.getFloat(_p2 + 12);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Float4x3 loadCMAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = _U.getFloat(bytes, address);
        float _c3 = _U.getFloat(bytes, address + 4);
        float _c6 = _U.getFloat(bytes, address + 8);
        float _c9 = _U.getFloat(bytes, address + 12);
        float _c1 = _U.getFloat(bytes, _p1);
        float _c4 = _U.getFloat(bytes, _p1 + 4);
        float _c7 = _U.getFloat(bytes, _p1 + 8);
        float _c10 = _U.getFloat(bytes, _p1 + 12);
        float _c2 = _U.getFloat(bytes, _p2);
        float _c5 = _U.getFloat(bytes, _p2 + 4);
        float _c8 = _U.getFloat(bytes, _p2 + 8);
        float _c11 = _U.getFloat(bytes, _p2 + 12);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public DoubleBuffer storeCMAbsolute(Float4x3 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m10());
        _U.putDouble(address + 16, self.m20());
        _U.putDouble(address + 24, self.m30());
        _U.putDouble(_p1, self.m01());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m21());
        _U.putDouble(_p1 + 24, self.m31());
        _U.putDouble(_p2, self.m02());
        _U.putDouble(_p2 + 8, self.m12());
        _U.putDouble(_p2 + 16, self.m22());
        _U.putDouble(_p2 + 24, self.m32());
        return buf;
    }

    public Float4x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) _U.getDouble(address);
        float _c3 = (float) _U.getDouble(address + 8);
        float _c6 = (float) _U.getDouble(address + 16);
        float _c9 = (float) _U.getDouble(address + 24);
        float _c1 = (float) _U.getDouble(_p1);
        float _c4 = (float) _U.getDouble(_p1 + 8);
        float _c7 = (float) _U.getDouble(_p1 + 16);
        float _c10 = (float) _U.getDouble(_p1 + 24);
        float _c2 = (float) _U.getDouble(_p2);
        float _c5 = (float) _U.getDouble(_p2 + 8);
        float _c8 = (float) _U.getDouble(_p2 + 16);
        float _c11 = (float) _U.getDouble(_p2 + 24);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public ByteBuffer storeCMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m10());
        _U.putDouble(address + 16, self.m20());
        _U.putDouble(address + 24, self.m30());
        _U.putDouble(_p1, self.m01());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m21());
        _U.putDouble(_p1 + 24, self.m31());
        _U.putDouble(_p2, self.m02());
        _U.putDouble(_p2 + 8, self.m12());
        _U.putDouble(_p2 + 16, self.m22());
        _U.putDouble(_p2 + 24, self.m32());
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x3 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        RAW.storeCMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) _U.getDouble(address);
        float _c3 = (float) _U.getDouble(address + 8);
        float _c6 = (float) _U.getDouble(address + 16);
        float _c9 = (float) _U.getDouble(address + 24);
        float _c1 = (float) _U.getDouble(_p1);
        float _c4 = (float) _U.getDouble(_p1 + 8);
        float _c7 = (float) _U.getDouble(_p1 + 16);
        float _c10 = (float) _U.getDouble(_p1 + 24);
        float _c2 = (float) _U.getDouble(_p2);
        float _c5 = (float) _U.getDouble(_p2 + 8);
        float _c8 = (float) _U.getDouble(_p2 + 16);
        float _c11 = (float) _U.getDouble(_p2 + 24);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Float4x3 loadCMDoubleAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c3 = (float) _U.getDouble(bytes, address + 8);
        float _c6 = (float) _U.getDouble(bytes, address + 16);
        float _c9 = (float) _U.getDouble(bytes, address + 24);
        float _c1 = (float) _U.getDouble(bytes, _p1);
        float _c4 = (float) _U.getDouble(bytes, _p1 + 8);
        float _c7 = (float) _U.getDouble(bytes, _p1 + 16);
        float _c10 = (float) _U.getDouble(bytes, _p1 + 24);
        float _c2 = (float) _U.getDouble(bytes, _p2);
        float _c5 = (float) _U.getDouble(bytes, _p2 + 8);
        float _c8 = (float) _U.getDouble(bytes, _p2 + 16);
        float _c11 = (float) _U.getDouble(bytes, _p2 + 24);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public FloatBuffer storeRMAbsolute(Float4x3 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m01());
        _U.putFloat(address + 8, self.m02());
        _U.putFloat(_p1, self.m10());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p1 + 8, self.m12());
        _U.putFloat(_p2, self.m20());
        _U.putFloat(_p2 + 4, self.m21());
        _U.putFloat(_p2 + 8, self.m22());
        _U.putFloat(_p3, self.m30());
        _U.putFloat(_p3 + 4, self.m31());
        _U.putFloat(_p3 + 8, self.m32());
        return buf;
    }

    public Float4x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4);
        float _c2 = _U.getFloat(address + 8);
        float _c3 = _U.getFloat(_p1);
        float _c4 = _U.getFloat(_p1 + 4);
        float _c5 = _U.getFloat(_p1 + 8);
        float _c6 = _U.getFloat(_p2);
        float _c7 = _U.getFloat(_p2 + 4);
        float _c8 = _U.getFloat(_p2 + 8);
        float _c9 = _U.getFloat(_p3);
        float _c10 = _U.getFloat(_p3 + 4);
        float _c11 = _U.getFloat(_p3 + 8);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public ByteBuffer storeRMAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m01());
        _U.putFloat(address + 8, self.m02());
        _U.putFloat(_p1, self.m10());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p1 + 8, self.m12());
        _U.putFloat(_p2, self.m20());
        _U.putFloat(_p2 + 4, self.m21());
        _U.putFloat(_p2 + 8, self.m22());
        _U.putFloat(_p3, self.m30());
        _U.putFloat(_p3 + 4, self.m31());
        _U.putFloat(_p3 + 8, self.m32());
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float4x3 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4);
        float _c2 = _U.getFloat(address + 8);
        float _c3 = _U.getFloat(_p1);
        float _c4 = _U.getFloat(_p1 + 4);
        float _c5 = _U.getFloat(_p1 + 8);
        float _c6 = _U.getFloat(_p2);
        float _c7 = _U.getFloat(_p2 + 4);
        float _c8 = _U.getFloat(_p2 + 8);
        float _c9 = _U.getFloat(_p3);
        float _c10 = _U.getFloat(_p3 + 4);
        float _c11 = _U.getFloat(_p3 + 8);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Float4x3 loadRMAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4);
        float _c2 = _U.getFloat(bytes, address + 8);
        float _c3 = _U.getFloat(bytes, _p1);
        float _c4 = _U.getFloat(bytes, _p1 + 4);
        float _c5 = _U.getFloat(bytes, _p1 + 8);
        float _c6 = _U.getFloat(bytes, _p2);
        float _c7 = _U.getFloat(bytes, _p2 + 4);
        float _c8 = _U.getFloat(bytes, _p2 + 8);
        float _c9 = _U.getFloat(bytes, _p3);
        float _c10 = _U.getFloat(bytes, _p3 + 4);
        float _c11 = _U.getFloat(bytes, _p3 + 8);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public DoubleBuffer storeRMAbsolute(Float4x3 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m01());
        _U.putDouble(address + 16, self.m02());
        _U.putDouble(_p1, self.m10());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m12());
        _U.putDouble(_p2, self.m20());
        _U.putDouble(_p2 + 8, self.m21());
        _U.putDouble(_p2 + 16, self.m22());
        _U.putDouble(_p3, self.m30());
        _U.putDouble(_p3 + 8, self.m31());
        _U.putDouble(_p3 + 16, self.m32());
        return buf;
    }

    public Float4x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8);
        float _c2 = (float) _U.getDouble(address + 16);
        float _c3 = (float) _U.getDouble(_p1);
        float _c4 = (float) _U.getDouble(_p1 + 8);
        float _c5 = (float) _U.getDouble(_p1 + 16);
        float _c6 = (float) _U.getDouble(_p2);
        float _c7 = (float) _U.getDouble(_p2 + 8);
        float _c8 = (float) _U.getDouble(_p2 + 16);
        float _c9 = (float) _U.getDouble(_p3);
        float _c10 = (float) _U.getDouble(_p3 + 8);
        float _c11 = (float) _U.getDouble(_p3 + 16);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    public ByteBuffer storeRMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m01());
        _U.putDouble(address + 16, self.m02());
        _U.putDouble(_p1, self.m10());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m12());
        _U.putDouble(_p2, self.m20());
        _U.putDouble(_p2 + 8, self.m21());
        _U.putDouble(_p2 + 16, self.m22());
        _U.putDouble(_p3, self.m30());
        _U.putDouble(_p3 + 8, self.m31());
        _U.putDouble(_p3 + 16, self.m32());
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x3 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        RAW.storeRMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8);
        float _c2 = (float) _U.getDouble(address + 16);
        float _c3 = (float) _U.getDouble(_p1);
        float _c4 = (float) _U.getDouble(_p1 + 8);
        float _c5 = (float) _U.getDouble(_p1 + 16);
        float _c6 = (float) _U.getDouble(_p2);
        float _c7 = (float) _U.getDouble(_p2 + 8);
        float _c8 = (float) _U.getDouble(_p2 + 16);
        float _c9 = (float) _U.getDouble(_p3);
        float _c10 = (float) _U.getDouble(_p3 + 8);
        float _c11 = (float) _U.getDouble(_p3 + 16);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }

    private static Float4x3 loadRMDoubleAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(index, buf, stride);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8);
        float _c2 = (float) _U.getDouble(bytes, address + 16);
        float _c3 = (float) _U.getDouble(bytes, _p1);
        float _c4 = (float) _U.getDouble(bytes, _p1 + 8);
        float _c5 = (float) _U.getDouble(bytes, _p1 + 16);
        float _c6 = (float) _U.getDouble(bytes, _p2);
        float _c7 = (float) _U.getDouble(bytes, _p2 + 8);
        float _c8 = (float) _U.getDouble(bytes, _p2 + 16);
        float _c9 = (float) _U.getDouble(bytes, _p3);
        float _c10 = (float) _U.getDouble(bytes, _p3 + 8);
        float _c11 = (float) _U.getDouble(bytes, _p3 + 16);
        return new Float4x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
}
