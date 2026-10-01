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

    public DoubleBuffer storeCMAbsolute(Double4x4 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public Double4x4 loadCMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        double _c0 = U.getDouble(address);
        double _c4 = U.getDouble(address + 8L);
        double _c8 = U.getDouble(address + 16L);
        double _c12 = U.getDouble(address + 24L);
        double _c1 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        double _c9 = U.getDouble(address + 48L);
        double _c13 = U.getDouble(address + 56L);
        double _c2 = U.getDouble(address + 64L);
        double _c6 = U.getDouble(address + 72L);
        double _c10 = U.getDouble(address + 80L);
        double _c14 = U.getDouble(address + 88L);
        double _c3 = U.getDouble(address + 96L);
        double _c7 = U.getDouble(address + 104L);
        double _c11 = U.getDouble(address + 112L);
        double _c15 = U.getDouble(address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeCMAbsolute(Double4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Double4x4 loadCMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getDouble(address);
        double _c4 = U.getDouble(address + 8L);
        double _c8 = U.getDouble(address + 16L);
        double _c12 = U.getDouble(address + 24L);
        double _c1 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        double _c9 = U.getDouble(address + 48L);
        double _c13 = U.getDouble(address + 56L);
        double _c2 = U.getDouble(address + 64L);
        double _c6 = U.getDouble(address + 72L);
        double _c10 = U.getDouble(address + 80L);
        double _c14 = U.getDouble(address + 88L);
        double _c3 = U.getDouble(address + 96L);
        double _c7 = U.getDouble(address + 104L);
        double _c11 = U.getDouble(address + 112L);
        double _c15 = U.getDouble(address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public FloatBuffer storeCMAbsolute(Double4x4 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public Double4x4 loadCMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        double _c0 = U.getFloat(address);
        double _c4 = U.getFloat(address + 4L);
        double _c8 = U.getFloat(address + 8L);
        double _c12 = U.getFloat(address + 12L);
        double _c1 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        double _c9 = U.getFloat(address + 24L);
        double _c13 = U.getFloat(address + 28L);
        double _c2 = U.getFloat(address + 32L);
        double _c6 = U.getFloat(address + 36L);
        double _c10 = U.getFloat(address + 40L);
        double _c14 = U.getFloat(address + 44L);
        double _c3 = U.getFloat(address + 48L);
        double _c7 = U.getFloat(address + 52L);
        double _c11 = U.getFloat(address + 56L);
        double _c15 = U.getFloat(address + 60L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMFloatAbsolute(self, index, buf);
        RAW.storeCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Double4x4 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMFloatAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getFloat(address);
        double _c4 = U.getFloat(address + 4L);
        double _c8 = U.getFloat(address + 8L);
        double _c12 = U.getFloat(address + 12L);
        double _c1 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        double _c9 = U.getFloat(address + 24L);
        double _c13 = U.getFloat(address + 28L);
        double _c2 = U.getFloat(address + 32L);
        double _c6 = U.getFloat(address + 36L);
        double _c10 = U.getFloat(address + 40L);
        double _c14 = U.getFloat(address + 44L);
        double _c3 = U.getFloat(address + 48L);
        double _c7 = U.getFloat(address + 52L);
        double _c11 = U.getFloat(address + 56L);
        double _c15 = U.getFloat(address + 60L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public DoubleBuffer storeRMAbsolute(Double4x4 self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }
    public Double4x4 loadRMAbsolute(int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        double _c6 = U.getDouble(address + 48L);
        double _c7 = U.getDouble(address + 56L);
        double _c8 = U.getDouble(address + 64L);
        double _c9 = U.getDouble(address + 72L);
        double _c10 = U.getDouble(address + 80L);
        double _c11 = U.getDouble(address + 88L);
        double _c12 = U.getDouble(address + 96L);
        double _c13 = U.getDouble(address + 104L);
        double _c14 = U.getDouble(address + 112L);
        double _c15 = U.getDouble(address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeRMAbsolute(Double4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Double4x4 loadRMAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        double _c6 = U.getDouble(address + 48L);
        double _c7 = U.getDouble(address + 56L);
        double _c8 = U.getDouble(address + 64L);
        double _c9 = U.getDouble(address + 72L);
        double _c10 = U.getDouble(address + 80L);
        double _c11 = U.getDouble(address + 88L);
        double _c12 = U.getDouble(address + 96L);
        double _c13 = U.getDouble(address + 104L);
        double _c14 = U.getDouble(address + 112L);
        double _c15 = U.getDouble(address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public FloatBuffer storeRMAbsolute(Double4x4 self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }
    public Double4x4 loadRMAbsolute(int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        double _c6 = U.getFloat(address + 24L);
        double _c7 = U.getFloat(address + 28L);
        double _c8 = U.getFloat(address + 32L);
        double _c9 = U.getFloat(address + 36L);
        double _c10 = U.getFloat(address + 40L);
        double _c11 = U.getFloat(address + 44L);
        double _c12 = U.getFloat(address + 48L);
        double _c13 = U.getFloat(address + 52L);
        double _c14 = U.getFloat(address + 56L);
        double _c15 = U.getFloat(address + 60L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x4 self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMFloatAbsolute(self, index, buf);
        RAW.storeRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Double4x4 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMFloatAbsolute(index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        double _c6 = U.getFloat(address + 24L);
        double _c7 = U.getFloat(address + 28L);
        double _c8 = U.getFloat(address + 32L);
        double _c9 = U.getFloat(address + 36L);
        double _c10 = U.getFloat(address + 40L);
        double _c11 = U.getFloat(address + 44L);
        double _c12 = U.getFloat(address + 48L);
        double _c13 = U.getFloat(address + 52L);
        double _c14 = U.getFloat(address + 56L);
        double _c15 = U.getFloat(address + 60L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public DoubleBuffer storeCMAbsolute(Double4x4 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }
    public Double4x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        return RAW.loadCMUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
    }
    public ByteBuffer storeCMAbsolute(Double4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Double4x4 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        return RAW.loadCMUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public FloatBuffer storeCMAbsolute(Double4x4 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        RAW.storeCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }
    public Double4x4 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(index, buf, stride);
        return RAW.loadCMFloatUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMFloatAbsolute(self, index, buf, stride);
        RAW.storeCMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Double4x4 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMFloatAbsolute(index, buf, stride);
        return RAW.loadCMFloatUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public DoubleBuffer storeRMAbsolute(Double4x4 self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
        return buf;
    }
    public Double4x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        return RAW.loadRMUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L, stride);
    }
    public ByteBuffer storeRMAbsolute(Double4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Double4x4 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        return RAW.loadRMUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
    public FloatBuffer storeRMAbsolute(Double4x4 self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        RAW.storeRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
        return buf;
    }
    public Double4x4 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(index, buf, stride);
        return RAW.loadRMFloatUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L, stride);
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x4 self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMFloatAbsolute(self, index, buf, stride);
        RAW.storeRMFloatUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
        return buf;
    }
    public Double4x4 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMFloatAbsolute(index, buf, stride);
        return RAW.loadRMFloatUnsafe(U.getLong(buf, BB_ADDRESS_OFFSET) + index, stride);
    }
}
