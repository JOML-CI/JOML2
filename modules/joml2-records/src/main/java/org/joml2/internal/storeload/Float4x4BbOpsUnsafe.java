// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
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

    public FloatBuffer storeCMAbsolute(Float4x4 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = U.getFloat(address);
        float _c4 = U.getFloat(address + 4L);
        float _c8 = U.getFloat(address + 8L);
        float _c12 = U.getFloat(address + 12L);
        float _c1 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c9 = U.getFloat(address + 24L);
        float _c13 = U.getFloat(address + 28L);
        float _c2 = U.getFloat(address + 32L);
        float _c6 = U.getFloat(address + 36L);
        float _c10 = U.getFloat(address + 40L);
        float _c14 = U.getFloat(address + 44L);
        float _c3 = U.getFloat(address + 48L);
        float _c7 = U.getFloat(address + 52L);
        float _c11 = U.getFloat(address + 56L);
        float _c15 = U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeCMAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = U.getFloat(address);
        float _c4 = U.getFloat(address + 4L);
        float _c8 = U.getFloat(address + 8L);
        float _c12 = U.getFloat(address + 12L);
        float _c1 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c9 = U.getFloat(address + 24L);
        float _c13 = U.getFloat(address + 28L);
        float _c2 = U.getFloat(address + 32L);
        float _c6 = U.getFloat(address + 36L);
        float _c10 = U.getFloat(address + 40L);
        float _c14 = U.getFloat(address + 44L);
        float _c3 = U.getFloat(address + 48L);
        float _c7 = U.getFloat(address + 52L);
        float _c11 = U.getFloat(address + 56L);
        float _c15 = U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public DoubleBuffer storeCMAbsolute(Float4x4 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) U.getDouble(address);
        float _c4 = (float) U.getDouble(address + 8L);
        float _c8 = (float) U.getDouble(address + 16L);
        float _c12 = (float) U.getDouble(address + 24L);
        float _c1 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c9 = (float) U.getDouble(address + 48L);
        float _c13 = (float) U.getDouble(address + 56L);
        float _c2 = (float) U.getDouble(address + 64L);
        float _c6 = (float) U.getDouble(address + 72L);
        float _c10 = (float) U.getDouble(address + 80L);
        float _c14 = (float) U.getDouble(address + 88L);
        float _c3 = (float) U.getDouble(address + 96L);
        float _c7 = (float) U.getDouble(address + 104L);
        float _c11 = (float) U.getDouble(address + 112L);
        float _c15 = (float) U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeCMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Float4x4 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) U.getDouble(address);
        float _c4 = (float) U.getDouble(address + 8L);
        float _c8 = (float) U.getDouble(address + 16L);
        float _c12 = (float) U.getDouble(address + 24L);
        float _c1 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c9 = (float) U.getDouble(address + 48L);
        float _c13 = (float) U.getDouble(address + 56L);
        float _c2 = (float) U.getDouble(address + 64L);
        float _c6 = (float) U.getDouble(address + 72L);
        float _c10 = (float) U.getDouble(address + 80L);
        float _c14 = (float) U.getDouble(address + 88L);
        float _c3 = (float) U.getDouble(address + 96L);
        float _c7 = (float) U.getDouble(address + 104L);
        float _c11 = (float) U.getDouble(address + 112L);
        float _c15 = (float) U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public FloatBuffer storeRMAbsolute(Float4x4 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        float _c9 = U.getFloat(address + 36L);
        float _c10 = U.getFloat(address + 40L);
        float _c11 = U.getFloat(address + 44L);
        float _c12 = U.getFloat(address + 48L);
        float _c13 = U.getFloat(address + 52L);
        float _c14 = U.getFloat(address + 56L);
        float _c15 = U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeRMAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        float _c9 = U.getFloat(address + 36L);
        float _c10 = U.getFloat(address + 40L);
        float _c11 = U.getFloat(address + 44L);
        float _c12 = U.getFloat(address + 48L);
        float _c13 = U.getFloat(address + 52L);
        float _c14 = U.getFloat(address + 56L);
        float _c15 = U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public DoubleBuffer storeRMAbsolute(Float4x4 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        float _c8 = (float) U.getDouble(address + 64L);
        float _c9 = (float) U.getDouble(address + 72L);
        float _c10 = (float) U.getDouble(address + 80L);
        float _c11 = (float) U.getDouble(address + 88L);
        float _c12 = (float) U.getDouble(address + 96L);
        float _c13 = (float) U.getDouble(address + 104L);
        float _c14 = (float) U.getDouble(address + 112L);
        float _c15 = (float) U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeRMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Float4x4 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        float _c8 = (float) U.getDouble(address + 64L);
        float _c9 = (float) U.getDouble(address + 72L);
        float _c10 = (float) U.getDouble(address + 80L);
        float _c11 = (float) U.getDouble(address + 88L);
        float _c12 = (float) U.getDouble(address + 96L);
        float _c13 = (float) U.getDouble(address + 104L);
        float _c14 = (float) U.getDouble(address + 112L);
        float _c15 = (float) U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public FloatBuffer storeCMAbsolute(Float4x4 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        return RAW.loadCMUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
    }
    public ByteBuffer storeCMAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        return RAW.loadCMUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public DoubleBuffer storeCMAbsolute(Float4x4 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        return RAW.loadCMDoubleUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
    }
    public ByteBuffer storeCMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Float4x4 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMDoubleAbsolute(index, buf, stride);
        return RAW.loadCMDoubleUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public FloatBuffer storeRMAbsolute(Float4x4 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        return RAW.loadRMUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
    }
    public ByteBuffer storeRMAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        return RAW.loadRMUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public DoubleBuffer storeRMAbsolute(Float4x4 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        return RAW.loadRMDoubleUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
    }
    public ByteBuffer storeRMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Float4x4 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMDoubleAbsolute(index, buf, stride);
        return RAW.loadRMDoubleUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
}
