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

public final class Float4x4BbOpsUnsafe extends Float4x4BbOps {
    private static final Float4x4BbOpsApi API = new Float4x4BbOpsApi();
    private static final Float4x4RawOpsUnsafe RAW = new Float4x4RawOpsUnsafe();

    public FloatBuffer storeCMAbsolute(Float4x4 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }

    public Float4x4 loadCMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = _U.getFloat(address);
        float _c4 = _U.getFloat(address + 4L);
        float _c8 = _U.getFloat(address + 8L);
        float _c12 = _U.getFloat(address + 12L);
        float _c1 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c9 = _U.getFloat(address + 24L);
        float _c13 = _U.getFloat(address + 28L);
        float _c2 = _U.getFloat(address + 32L);
        float _c6 = _U.getFloat(address + 36L);
        float _c10 = _U.getFloat(address + 40L);
        float _c14 = _U.getFloat(address + 44L);
        float _c3 = _U.getFloat(address + 48L);
        float _c7 = _U.getFloat(address + 52L);
        float _c11 = _U.getFloat(address + 56L);
        float _c15 = _U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public ByteBuffer storeCMAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
        RAW.storeCMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float4x4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public Float4x4 loadCMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = _U.getFloat(address);
        float _c4 = _U.getFloat(address + 4L);
        float _c8 = _U.getFloat(address + 8L);
        float _c12 = _U.getFloat(address + 12L);
        float _c1 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c9 = _U.getFloat(address + 24L);
        float _c13 = _U.getFloat(address + 28L);
        float _c2 = _U.getFloat(address + 32L);
        float _c6 = _U.getFloat(address + 36L);
        float _c10 = _U.getFloat(address + 40L);
        float _c14 = _U.getFloat(address + 44L);
        float _c3 = _U.getFloat(address + 48L);
        float _c7 = _U.getFloat(address + 52L);
        float _c11 = _U.getFloat(address + 56L);
        float _c15 = _U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Float4x4 loadCMAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(index, buf);
        return RAW.loadCMHeap(bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    public DoubleBuffer storeCMAbsolute(Float4x4 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }

    public Float4x4 loadCMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) _U.getDouble(address);
        float _c4 = (float) _U.getDouble(address + 8L);
        float _c8 = (float) _U.getDouble(address + 16L);
        float _c12 = (float) _U.getDouble(address + 24L);
        float _c1 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c9 = (float) _U.getDouble(address + 48L);
        float _c13 = (float) _U.getDouble(address + 56L);
        float _c2 = (float) _U.getDouble(address + 64L);
        float _c6 = (float) _U.getDouble(address + 72L);
        float _c10 = (float) _U.getDouble(address + 80L);
        float _c14 = (float) _U.getDouble(address + 88L);
        float _c3 = (float) _U.getDouble(address + 96L);
        float _c7 = (float) _U.getDouble(address + 104L);
        float _c11 = (float) _U.getDouble(address + 112L);
        float _c15 = (float) _U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public ByteBuffer storeCMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf);
        RAW.storeCMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public Float4x4 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) _U.getDouble(address);
        float _c4 = (float) _U.getDouble(address + 8L);
        float _c8 = (float) _U.getDouble(address + 16L);
        float _c12 = (float) _U.getDouble(address + 24L);
        float _c1 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c9 = (float) _U.getDouble(address + 48L);
        float _c13 = (float) _U.getDouble(address + 56L);
        float _c2 = (float) _U.getDouble(address + 64L);
        float _c6 = (float) _U.getDouble(address + 72L);
        float _c10 = (float) _U.getDouble(address + 80L);
        float _c14 = (float) _U.getDouble(address + 88L);
        float _c3 = (float) _U.getDouble(address + 96L);
        float _c7 = (float) _U.getDouble(address + 104L);
        float _c11 = (float) _U.getDouble(address + 112L);
        float _c15 = (float) _U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Float4x4 loadCMDoubleAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(index, buf);
        return RAW.loadCMDoubleHeap(bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    public FloatBuffer storeRMAbsolute(Float4x4 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }

    public Float4x4 loadRMAbsolute(int index, FloatBuffer buf) {
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
        float _c12 = _U.getFloat(address + 48L);
        float _c13 = _U.getFloat(address + 52L);
        float _c14 = _U.getFloat(address + 56L);
        float _c15 = _U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public ByteBuffer storeRMAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
        RAW.storeRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float4x4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public Float4x4 loadRMAbsolute(int index, ByteBuffer buf) {
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
        float _c12 = _U.getFloat(address + 48L);
        float _c13 = _U.getFloat(address + 52L);
        float _c14 = _U.getFloat(address + 56L);
        float _c15 = _U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Float4x4 loadRMAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(index, buf);
        return RAW.loadRMHeap(bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    public DoubleBuffer storeRMAbsolute(Float4x4 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }

    public Float4x4 loadRMAbsolute(int index, DoubleBuffer buf) {
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
        float _c12 = (float) _U.getDouble(address + 96L);
        float _c13 = (float) _U.getDouble(address + 104L);
        float _c14 = (float) _U.getDouble(address + 112L);
        float _c15 = (float) _U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public ByteBuffer storeRMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x4 self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf);
        RAW.storeRMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public Float4x4 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
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
        float _c12 = (float) _U.getDouble(address + 96L);
        float _c13 = (float) _U.getDouble(address + 104L);
        float _c14 = (float) _U.getDouble(address + 112L);
        float _c15 = (float) _U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    private static Float4x4 loadRMDoubleAbsoluteElse(int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(index, buf);
        return RAW.loadRMDoubleHeap(bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    public FloatBuffer storeCMAbsolute(Float4x4 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }

    public Float4x4 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        return RAW.loadCMUnsafe(U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L, stride);
    }

    public ByteBuffer storeCMAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float4x4 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x4 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(index, buf, stride);
        return RAW.loadCMUnsafe(U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    private static Float4x4 loadCMAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(index, buf, stride);
        return RAW.loadCMHeap(bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    public DoubleBuffer storeCMAbsolute(Float4x4 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }

    public Float4x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        return RAW.loadCMDoubleUnsafe(U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L, stride);
    }

    public ByteBuffer storeCMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf, stride);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x4 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        RAW.storeCMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x4 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(index, buf, stride);
        return RAW.loadCMDoubleUnsafe(U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    private static Float4x4 loadCMDoubleAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(index, buf, stride);
        return RAW.loadCMDoubleHeap(bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    public FloatBuffer storeRMAbsolute(Float4x4 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }

    public Float4x4 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        return RAW.loadRMUnsafe(U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L, stride);
    }

    public ByteBuffer storeRMAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float4x4 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x4 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(index, buf, stride);
        return RAW.loadRMUnsafe(U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    private static Float4x4 loadRMAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(index, buf, stride);
        return RAW.loadRMHeap(bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    public DoubleBuffer storeRMAbsolute(Float4x4 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }

    public Float4x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        return RAW.loadRMDoubleUnsafe(U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L, stride);
    }

    public ByteBuffer storeRMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf, stride);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x4 self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        RAW.storeRMDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }

    public Float4x4 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(index, buf, stride);
        return RAW.loadRMDoubleUnsafe(U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }

    private static Float4x4 loadRMDoubleAbsoluteElse(int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(index, buf, stride);
        return RAW.loadRMDoubleHeap(bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index, stride);
    }
}
