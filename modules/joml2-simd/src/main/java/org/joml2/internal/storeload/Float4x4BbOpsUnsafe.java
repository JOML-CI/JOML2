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

public final class Float4x4BbOpsUnsafe extends Float4x4BbOps {
    private static final Float4x4BbOpsApi API = new Float4x4BbOpsApi();
    private static final Float4x4RawOpsUnsafe RAW = new Float4x4RawOpsUnsafe();

    public FloatBuffer storeCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8)
                U.putLong(address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
        } else {
            for (int _k = 0; _k < 16; _k++)
                U.putFloat(address + 4L * _k, _d[_k]);
        }
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(address + _k));
        } else {
            for (int _k = 0; _k < 16; _k++)
                _d[_k] = U.getFloat(address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8)
                U.putLong(address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
        } else {
            for (int _k = 0; _k < 16; _k++)
                U.putFloat(address + 4L * _k, _d[_k]);
        }
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8)
                U.putLong(bytes, address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
        } else {
            for (int _k = 0; _k < 16; _k++)
                U.putFloat(bytes, address + 4L * _k, _d[_k]);
        }
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(address + _k));
        } else {
            for (int _k = 0; _k < 16; _k++)
                _d[_k] = U.getFloat(address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }

    private static Float4x4 loadCMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(bytes, address + _k));
        } else {
            for (int _k = 0; _k < 16; _k++)
                _d[_k] = U.getFloat(bytes, address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public DoubleBuffer storeCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf);
        return RAW.loadCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
    }

    public ByteBuffer storeCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf);
        RAW.storeCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += 32L) {
            U.putDouble(bytes, _a, _d[_o]);
            U.putDouble(bytes, _a + 8L, _d[_o + 1]);
            U.putDouble(bytes, _a + 16L, _d[_o + 2]);
            U.putDouble(bytes, _a + 24L, _d[_o + 3]);
        }
        return buf;
    }

    public Float4x4 loadCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(self, index, buf);
        return RAW.loadCMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    private static Float4x4 loadCMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += 32L) {
            _d[_o] = (float) U.getDouble(bytes, _a);
            _d[_o + 1] = (float) U.getDouble(bytes, _a + 8L);
            _d[_o + 2] = (float) U.getDouble(bytes, _a + 16L);
            _d[_o + 3] = (float) U.getDouble(bytes, _a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public FloatBuffer storeRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L);
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        return RAW.loadRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L);
    }

    public ByteBuffer storeRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf);
        RAW.storeRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf);
        return RAW.loadRMUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    private static Float4x4 loadRMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf);
        return RAW.loadRMHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    public DoubleBuffer storeRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf);
        return RAW.loadRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L);
    }

    public ByteBuffer storeRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf);
        RAW.storeRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += 32L) {
            U.putDouble(bytes, _a, _d[_o]);
            U.putDouble(bytes, _a + 8L, _d[_o + 4]);
            U.putDouble(bytes, _a + 16L, _d[_o + 8]);
            U.putDouble(bytes, _a + 24L, _d[_o + 12]);
        }
        return buf;
    }

    public Float4x4 loadRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(self, index, buf);
        return RAW.loadRMDoubleUnsafe(self, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }

    private static Float4x4 loadRMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += 32L) {
            _d[_o] = (float) U.getDouble(bytes, _a);
            _d[_o + 4] = (float) U.getDouble(bytes, _a + 8L);
            _d[_o + 8] = (float) U.getDouble(bytes, _a + 16L);
            _d[_o + 12] = (float) U.getDouble(bytes, _a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public FloatBuffer storeCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putFloat(_a, _d[_o]);
            U.putFloat(_a + 4L, _d[_o + 1]);
            U.putFloat(_a + 8L, _d[_o + 2]);
            U.putFloat(_a + 12L, _d[_o + 3]);
        }
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = U.getFloat(_a);
            _d[_o + 1] = U.getFloat(_a + 4L);
            _d[_o + 2] = U.getFloat(_a + 8L);
            _d[_o + 3] = U.getFloat(_a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMAbsoluteElse(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putFloat(_a, _d[_o]);
            U.putFloat(_a + 4L, _d[_o + 1]);
            U.putFloat(_a + 8L, _d[_o + 2]);
            U.putFloat(_a + 12L, _d[_o + 3]);
        }
        return buf;
    }

    private static ByteBuffer storeCMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putFloat(bytes, _a, _d[_o]);
            U.putFloat(bytes, _a + 4L, _d[_o + 1]);
            U.putFloat(bytes, _a + 8L, _d[_o + 2]);
            U.putFloat(bytes, _a + 12L, _d[_o + 3]);
        }
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMAbsoluteElse(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = U.getFloat(_a);
            _d[_o + 1] = U.getFloat(_a + 4L);
            _d[_o + 2] = U.getFloat(_a + 8L);
            _d[_o + 3] = U.getFloat(_a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    private static Float4x4 loadCMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = U.getFloat(bytes, _a);
            _d[_o + 1] = U.getFloat(bytes, _a + 4L);
            _d[_o + 2] = U.getFloat(bytes, _a + 8L);
            _d[_o + 3] = U.getFloat(bytes, _a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public DoubleBuffer storeCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putDouble(_a, _d[_o]);
            U.putDouble(_a + 8L, _d[_o + 1]);
            U.putDouble(_a + 16L, _d[_o + 2]);
            U.putDouble(_a + 24L, _d[_o + 3]);
        }
        return buf;
    }

    public Float4x4 loadCMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadCMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = (float) U.getDouble(_a);
            _d[_o + 1] = (float) U.getDouble(_a + 8L);
            _d[_o + 2] = (float) U.getDouble(_a + 16L);
            _d[_o + 3] = (float) U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeCMDoubleAbsoluteElse(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putDouble(_a, _d[_o]);
            U.putDouble(_a + 8L, _d[_o + 1]);
            U.putDouble(_a + 16L, _d[_o + 2]);
            U.putDouble(_a + 24L, _d[_o + 3]);
        }
        return buf;
    }

    private static ByteBuffer storeCMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeCMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putDouble(bytes, _a, _d[_o]);
            U.putDouble(bytes, _a + 8L, _d[_o + 1]);
            U.putDouble(bytes, _a + 16L, _d[_o + 2]);
            U.putDouble(bytes, _a + 24L, _d[_o + 3]);
        }
        return buf;
    }

    public Float4x4 loadCMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadCMDoubleAbsoluteElse(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = (float) U.getDouble(_a);
            _d[_o + 1] = (float) U.getDouble(_a + 8L);
            _d[_o + 2] = (float) U.getDouble(_a + 16L);
            _d[_o + 3] = (float) U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    private static Float4x4 loadCMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadCMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = (float) U.getDouble(bytes, _a);
            _d[_o + 1] = (float) U.getDouble(bytes, _a + 8L);
            _d[_o + 2] = (float) U.getDouble(bytes, _a + 16L);
            _d[_o + 3] = (float) U.getDouble(bytes, _a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public FloatBuffer storeRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putFloat(_a, _d[_o]);
            U.putFloat(_a + 4L, _d[_o + 4]);
            U.putFloat(_a + 8L, _d[_o + 8]);
            U.putFloat(_a + 12L, _d[_o + 12]);
        }
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, FloatBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = U.getFloat(_a);
            _d[_o + 4] = U.getFloat(_a + 4L);
            _d[_o + 8] = U.getFloat(_a + 8L);
            _d[_o + 12] = U.getFloat(_a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMAbsoluteElse(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putFloat(_a, _d[_o]);
            U.putFloat(_a + 4L, _d[_o + 4]);
            U.putFloat(_a + 8L, _d[_o + 8]);
            U.putFloat(_a + 12L, _d[_o + 12]);
        }
        return buf;
    }

    private static ByteBuffer storeRMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putFloat(bytes, _a, _d[_o]);
            U.putFloat(bytes, _a + 4L, _d[_o + 4]);
            U.putFloat(bytes, _a + 8L, _d[_o + 8]);
            U.putFloat(bytes, _a + 12L, _d[_o + 12]);
        }
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMAbsoluteElse(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = U.getFloat(_a);
            _d[_o + 4] = U.getFloat(_a + 4L);
            _d[_o + 8] = U.getFloat(_a + 8L);
            _d[_o + 12] = U.getFloat(_a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    private static Float4x4 loadRMAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = U.getFloat(bytes, _a);
            _d[_o + 4] = U.getFloat(bytes, _a + 4L);
            _d[_o + 8] = U.getFloat(bytes, _a + 8L);
            _d[_o + 12] = U.getFloat(bytes, _a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public DoubleBuffer storeRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putDouble(_a, _d[_o]);
            U.putDouble(_a + 8L, _d[_o + 4]);
            U.putDouble(_a + 16L, _d[_o + 8]);
            U.putDouble(_a + 24L, _d[_o + 12]);
        }
        return buf;
    }

    public Float4x4 loadRMAbsolute(Float4x4Impl self, int index, DoubleBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadRMAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = (float) U.getDouble(_a);
            _d[_o + 4] = (float) U.getDouble(_a + 8L);
            _d[_o + 8] = (float) U.getDouble(_a + 16L);
            _d[_o + 12] = (float) U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public ByteBuffer storeRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeRMDoubleAbsoluteElse(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putDouble(_a, _d[_o]);
            U.putDouble(_a + 8L, _d[_o + 4]);
            U.putDouble(_a + 16L, _d[_o + 8]);
            U.putDouble(_a + 24L, _d[_o + 12]);
        }
        return buf;
    }

    private static ByteBuffer storeRMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeRMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putDouble(bytes, _a, _d[_o]);
            U.putDouble(bytes, _a + 8L, _d[_o + 4]);
            U.putDouble(bytes, _a + 16L, _d[_o + 8]);
            U.putDouble(bytes, _a + 24L, _d[_o + 12]);
        }
        return buf;
    }

    public Float4x4 loadRMDoubleAbsolute(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadRMDoubleAbsoluteElse(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = (float) U.getDouble(_a);
            _d[_o + 4] = (float) U.getDouble(_a + 8L);
            _d[_o + 8] = (float) U.getDouble(_a + 16L);
            _d[_o + 12] = (float) U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    private static Float4x4 loadRMDoubleAbsoluteElse(Float4x4Impl self, int index, ByteBuffer buf, int stride) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadRMDoubleAbsolute(self, index, buf, stride);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = (float) U.getDouble(bytes, _a);
            _d[_o + 4] = (float) U.getDouble(bytes, _a + 8L);
            _d[_o + 8] = (float) U.getDouble(bytes, _a + 16L);
            _d[_o + 12] = (float) U.getDouble(bytes, _a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }
}
