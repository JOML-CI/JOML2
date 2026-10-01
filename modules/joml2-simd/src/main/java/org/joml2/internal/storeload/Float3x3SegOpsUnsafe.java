// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3x3SegOpsUnsafe extends Float3x3SegOps {
    private static final Float3x3SegOpsMS MS = new Float3x3SegOpsMS();
    private static final Float3x3RawOpsUnsafe RAW = new Float3x3RawOpsUnsafe();

    public MemorySegment storeCM(Float3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 32; _k += 8)
                U.putLong(address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
            U.putFloat(address + 32L, _d[8]);
        } else {
            for (int _k = 0; _k < 9; _k++)
                U.putFloat(address + 4L * _k, _d[_k]);
        }
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 32; _k += 8)
                U.putLong(bytes, address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
            U.putFloat(bytes, address + 32L, _d[8]);
        } else {
            for (int _k = 0; _k < 9; _k++)
                U.putFloat(bytes, address + 4L * _k, _d[_k]);
        }
        return dest;
    }

    public Float3x3 loadCM(Float3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src);
        long address = src.address() + offset;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 32; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(address + _k));
            _d[8] = U.getFloat(address + 32L);
        } else {
            for (int _k = 0; _k < 9; _k++)
                _d[_k] = U.getFloat(address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadCMOnHeap(Float3x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 32; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(bytes, address + _k));
            _d[8] = U.getFloat(bytes, address + 32L);
        } else {
            for (int _k = 0; _k < 9; _k++)
                _d[_k] = U.getFloat(bytes, address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMDouble(Float3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[1]);
        _U.putDouble(address + 16L, self.data[2]);
        _U.putDouble(address + 24L, self.data[3]);
        _U.putDouble(address + 32L, self.data[4]);
        _U.putDouble(address + 40L, self.data[5]);
        _U.putDouble(address + 48L, self.data[6]);
        _U.putDouble(address + 56L, self.data[7]);
        _U.putDouble(address + 64L, self.data[8]);
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[1]);
        _U.putDouble(bytes, address + 16L, self.data[2]);
        _U.putDouble(bytes, address + 24L, self.data[3]);
        _U.putDouble(bytes, address + 32L, self.data[4]);
        _U.putDouble(bytes, address + 40L, self.data[5]);
        _U.putDouble(bytes, address + 48L, self.data[6]);
        _U.putDouble(bytes, address + 56L, self.data[7]);
        _U.putDouble(bytes, address + 64L, self.data[8]);
        return dest;
    }

    public Float3x3 loadCMDouble(Float3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[1] = (float) _U.getDouble(address + 8L);
        self.data[2] = (float) _U.getDouble(address + 16L);
        self.data[3] = (float) _U.getDouble(address + 24L);
        self.data[4] = (float) _U.getDouble(address + 32L);
        self.data[5] = (float) _U.getDouble(address + 40L);
        self.data[6] = (float) _U.getDouble(address + 48L);
        self.data[7] = (float) _U.getDouble(address + 56L);
        self.data[8] = (float) _U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadCMDoubleOnHeap(Float3x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[1] = (float) _U.getDouble(bytes, address + 8L);
        self.data[2] = (float) _U.getDouble(bytes, address + 16L);
        self.data[3] = (float) _U.getDouble(bytes, address + 24L);
        self.data[4] = (float) _U.getDouble(bytes, address + 32L);
        self.data[5] = (float) _U.getDouble(bytes, address + 40L);
        self.data[6] = (float) _U.getDouble(bytes, address + 48L);
        self.data[7] = (float) _U.getDouble(bytes, address + 56L);
        self.data[8] = (float) _U.getDouble(bytes, address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Float3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[3]);
        _U.putFloat(address + 8L, self.data[6]);
        _U.putFloat(address + 12L, self.data[1]);
        _U.putFloat(address + 16L, self.data[4]);
        _U.putFloat(address + 20L, self.data[7]);
        _U.putFloat(address + 24L, self.data[2]);
        _U.putFloat(address + 28L, self.data[5]);
        _U.putFloat(address + 32L, self.data[8]);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4L, self.data[3]);
        _U.putFloat(bytes, address + 8L, self.data[6]);
        _U.putFloat(bytes, address + 12L, self.data[1]);
        _U.putFloat(bytes, address + 16L, self.data[4]);
        _U.putFloat(bytes, address + 20L, self.data[7]);
        _U.putFloat(bytes, address + 24L, self.data[2]);
        _U.putFloat(bytes, address + 28L, self.data[5]);
        _U.putFloat(bytes, address + 32L, self.data[8]);
        return dest;
    }

    public Float3x3 loadRM(Float3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[3] = _U.getFloat(address + 4L);
        self.data[6] = _U.getFloat(address + 8L);
        self.data[1] = _U.getFloat(address + 12L);
        self.data[4] = _U.getFloat(address + 16L);
        self.data[7] = _U.getFloat(address + 20L);
        self.data[2] = _U.getFloat(address + 24L);
        self.data[5] = _U.getFloat(address + 28L);
        self.data[8] = _U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadRMOnHeap(Float3x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[3] = _U.getFloat(bytes, address + 4L);
        self.data[6] = _U.getFloat(bytes, address + 8L);
        self.data[1] = _U.getFloat(bytes, address + 12L);
        self.data[4] = _U.getFloat(bytes, address + 16L);
        self.data[7] = _U.getFloat(bytes, address + 20L);
        self.data[2] = _U.getFloat(bytes, address + 24L);
        self.data[5] = _U.getFloat(bytes, address + 28L);
        self.data[8] = _U.getFloat(bytes, address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMDouble(Float3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[3]);
        _U.putDouble(address + 16L, self.data[6]);
        _U.putDouble(address + 24L, self.data[1]);
        _U.putDouble(address + 32L, self.data[4]);
        _U.putDouble(address + 40L, self.data[7]);
        _U.putDouble(address + 48L, self.data[2]);
        _U.putDouble(address + 56L, self.data[5]);
        _U.putDouble(address + 64L, self.data[8]);
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[3]);
        _U.putDouble(bytes, address + 16L, self.data[6]);
        _U.putDouble(bytes, address + 24L, self.data[1]);
        _U.putDouble(bytes, address + 32L, self.data[4]);
        _U.putDouble(bytes, address + 40L, self.data[7]);
        _U.putDouble(bytes, address + 48L, self.data[2]);
        _U.putDouble(bytes, address + 56L, self.data[5]);
        _U.putDouble(bytes, address + 64L, self.data[8]);
        return dest;
    }

    public Float3x3 loadRMDouble(Float3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMDoubleOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[3] = (float) _U.getDouble(address + 8L);
        self.data[6] = (float) _U.getDouble(address + 16L);
        self.data[1] = (float) _U.getDouble(address + 24L);
        self.data[4] = (float) _U.getDouble(address + 32L);
        self.data[7] = (float) _U.getDouble(address + 40L);
        self.data[2] = (float) _U.getDouble(address + 48L);
        self.data[5] = (float) _U.getDouble(address + 56L);
        self.data[8] = (float) _U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadRMDoubleOnHeap(Float3x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[3] = (float) _U.getDouble(bytes, address + 8L);
        self.data[6] = (float) _U.getDouble(bytes, address + 16L);
        self.data[1] = (float) _U.getDouble(bytes, address + 24L);
        self.data[4] = (float) _U.getDouble(bytes, address + 32L);
        self.data[7] = (float) _U.getDouble(bytes, address + 40L);
        self.data[2] = (float) _U.getDouble(bytes, address + 48L);
        self.data[5] = (float) _U.getDouble(bytes, address + 56L);
        self.data[8] = (float) _U.getDouble(bytes, address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4, self.data[1]);
        _U.putFloat(address + 8, self.data[2]);
        _U.putFloat(_p1, self.data[3]);
        _U.putFloat(_p1 + 4, self.data[4]);
        _U.putFloat(_p1 + 8, self.data[5]);
        _U.putFloat(_p2, self.data[6]);
        _U.putFloat(_p2 + 4, self.data[7]);
        _U.putFloat(_p2 + 8, self.data[8]);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4, self.data[1]);
        _U.putFloat(bytes, address + 8, self.data[2]);
        _U.putFloat(bytes, _p1, self.data[3]);
        _U.putFloat(bytes, _p1 + 4, self.data[4]);
        _U.putFloat(bytes, _p1 + 8, self.data[5]);
        _U.putFloat(bytes, _p2, self.data[6]);
        _U.putFloat(bytes, _p2 + 4, self.data[7]);
        _U.putFloat(bytes, _p2 + 8, self.data[8]);
        return dest;
    }

    public Float3x3 loadCM(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4);
        self.data[2] = _U.getFloat(address + 8);
        self.data[3] = _U.getFloat(_p1);
        self.data[4] = _U.getFloat(_p1 + 4);
        self.data[5] = _U.getFloat(_p1 + 8);
        self.data[6] = _U.getFloat(_p2);
        self.data[7] = _U.getFloat(_p2 + 4);
        self.data[8] = _U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadCMOnHeap(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4);
        self.data[2] = _U.getFloat(bytes, address + 8);
        self.data[3] = _U.getFloat(bytes, _p1);
        self.data[4] = _U.getFloat(bytes, _p1 + 4);
        self.data[5] = _U.getFloat(bytes, _p1 + 8);
        self.data[6] = _U.getFloat(bytes, _p2);
        self.data[7] = _U.getFloat(bytes, _p2 + 4);
        self.data[8] = _U.getFloat(bytes, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMDouble(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMDoubleOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[1]);
        _U.putDouble(address + 16, self.data[2]);
        _U.putDouble(_p1, self.data[3]);
        _U.putDouble(_p1 + 8, self.data[4]);
        _U.putDouble(_p1 + 16, self.data[5]);
        _U.putDouble(_p2, self.data[6]);
        _U.putDouble(_p2 + 8, self.data[7]);
        _U.putDouble(_p2 + 16, self.data[8]);
        return dest;
    }

    private static MemorySegment storeCMDoubleOnHeap(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMDouble(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[1]);
        _U.putDouble(bytes, address + 16, self.data[2]);
        _U.putDouble(bytes, _p1, self.data[3]);
        _U.putDouble(bytes, _p1 + 8, self.data[4]);
        _U.putDouble(bytes, _p1 + 16, self.data[5]);
        _U.putDouble(bytes, _p2, self.data[6]);
        _U.putDouble(bytes, _p2 + 8, self.data[7]);
        _U.putDouble(bytes, _p2 + 16, self.data[8]);
        return dest;
    }

    public Float3x3 loadCMDouble(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMDoubleOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[1] = (float) _U.getDouble(address + 8);
        self.data[2] = (float) _U.getDouble(address + 16);
        self.data[3] = (float) _U.getDouble(_p1);
        self.data[4] = (float) _U.getDouble(_p1 + 8);
        self.data[5] = (float) _U.getDouble(_p1 + 16);
        self.data[6] = (float) _U.getDouble(_p2);
        self.data[7] = (float) _U.getDouble(_p2 + 8);
        self.data[8] = (float) _U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadCMDoubleOnHeap(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMDouble(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[1] = (float) _U.getDouble(bytes, address + 8);
        self.data[2] = (float) _U.getDouble(bytes, address + 16);
        self.data[3] = (float) _U.getDouble(bytes, _p1);
        self.data[4] = (float) _U.getDouble(bytes, _p1 + 8);
        self.data[5] = (float) _U.getDouble(bytes, _p1 + 16);
        self.data[6] = (float) _U.getDouble(bytes, _p2);
        self.data[7] = (float) _U.getDouble(bytes, _p2 + 8);
        self.data[8] = (float) _U.getDouble(bytes, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4, self.data[3]);
        _U.putFloat(address + 8, self.data[6]);
        _U.putFloat(_p1, self.data[1]);
        _U.putFloat(_p1 + 4, self.data[4]);
        _U.putFloat(_p1 + 8, self.data[7]);
        _U.putFloat(_p2, self.data[2]);
        _U.putFloat(_p2 + 4, self.data[5]);
        _U.putFloat(_p2 + 8, self.data[8]);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4, self.data[3]);
        _U.putFloat(bytes, address + 8, self.data[6]);
        _U.putFloat(bytes, _p1, self.data[1]);
        _U.putFloat(bytes, _p1 + 4, self.data[4]);
        _U.putFloat(bytes, _p1 + 8, self.data[7]);
        _U.putFloat(bytes, _p2, self.data[2]);
        _U.putFloat(bytes, _p2 + 4, self.data[5]);
        _U.putFloat(bytes, _p2 + 8, self.data[8]);
        return dest;
    }

    public Float3x3 loadRM(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[3] = _U.getFloat(address + 4);
        self.data[6] = _U.getFloat(address + 8);
        self.data[1] = _U.getFloat(_p1);
        self.data[4] = _U.getFloat(_p1 + 4);
        self.data[7] = _U.getFloat(_p1 + 8);
        self.data[2] = _U.getFloat(_p2);
        self.data[5] = _U.getFloat(_p2 + 4);
        self.data[8] = _U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadRMOnHeap(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[3] = _U.getFloat(bytes, address + 4);
        self.data[6] = _U.getFloat(bytes, address + 8);
        self.data[1] = _U.getFloat(bytes, _p1);
        self.data[4] = _U.getFloat(bytes, _p1 + 4);
        self.data[7] = _U.getFloat(bytes, _p1 + 8);
        self.data[2] = _U.getFloat(bytes, _p2);
        self.data[5] = _U.getFloat(bytes, _p2 + 4);
        self.data[8] = _U.getFloat(bytes, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMDouble(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMDouble(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMDoubleOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[3]);
        _U.putDouble(address + 16, self.data[6]);
        _U.putDouble(_p1, self.data[1]);
        _U.putDouble(_p1 + 8, self.data[4]);
        _U.putDouble(_p1 + 16, self.data[7]);
        _U.putDouble(_p2, self.data[2]);
        _U.putDouble(_p2 + 8, self.data[5]);
        _U.putDouble(_p2 + 16, self.data[8]);
        return dest;
    }

    private static MemorySegment storeRMDoubleOnHeap(Float3x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMDouble(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[3]);
        _U.putDouble(bytes, address + 16, self.data[6]);
        _U.putDouble(bytes, _p1, self.data[1]);
        _U.putDouble(bytes, _p1 + 8, self.data[4]);
        _U.putDouble(bytes, _p1 + 16, self.data[7]);
        _U.putDouble(bytes, _p2, self.data[2]);
        _U.putDouble(bytes, _p2 + 8, self.data[5]);
        _U.putDouble(bytes, _p2 + 16, self.data[8]);
        return dest;
    }

    public Float3x3 loadRMDouble(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMDoubleOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[3] = (float) _U.getDouble(address + 8);
        self.data[6] = (float) _U.getDouble(address + 16);
        self.data[1] = (float) _U.getDouble(_p1);
        self.data[4] = (float) _U.getDouble(_p1 + 8);
        self.data[7] = (float) _U.getDouble(_p1 + 16);
        self.data[2] = (float) _U.getDouble(_p2);
        self.data[5] = (float) _U.getDouble(_p2 + 8);
        self.data[8] = (float) _U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    private static Float3x3 loadRMDoubleOnHeap(Float3x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMDouble(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[3] = (float) _U.getDouble(bytes, address + 8);
        self.data[6] = (float) _U.getDouble(bytes, address + 16);
        self.data[1] = (float) _U.getDouble(bytes, _p1);
        self.data[4] = (float) _U.getDouble(bytes, _p1 + 8);
        self.data[7] = (float) _U.getDouble(bytes, _p1 + 16);
        self.data[2] = (float) _U.getDouble(bytes, _p2);
        self.data[5] = (float) _U.getDouble(bytes, _p2 + 8);
        self.data[8] = (float) _U.getDouble(bytes, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM4x4(Float3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[1]);
        _U.putFloat(address + 8L, self.data[2]);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.data[3]);
        _U.putFloat(address + 20L, self.data[4]);
        _U.putFloat(address + 24L, self.data[5]);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, self.data[6]);
        _U.putFloat(address + 36L, self.data[7]);
        _U.putFloat(address + 40L, self.data[8]);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return dest;
    }

    private static MemorySegment storeCM4x4OnHeap(Float3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4(self, offset, dest);
        RAW.storeCM4x4Heap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public MemorySegment storeCM4x4Double(Float3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4Double(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4DoubleOnHeap(self, offset, dest);
        RAW.storeCM4x4DoubleUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCM4x4DoubleOnHeap(Float3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4Double(self, offset, dest);
        RAW.storeCM4x4DoubleHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public MemorySegment storeRM4x4(Float3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[3]);
        _U.putFloat(address + 8L, self.data[6]);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.data[1]);
        _U.putFloat(address + 20L, self.data[4]);
        _U.putFloat(address + 24L, self.data[7]);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, self.data[2]);
        _U.putFloat(address + 36L, self.data[5]);
        _U.putFloat(address + 40L, self.data[8]);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return dest;
    }

    private static MemorySegment storeRM4x4OnHeap(Float3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4(self, offset, dest);
        RAW.storeRM4x4Heap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public MemorySegment storeRM4x4Double(Float3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4Double(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4DoubleOnHeap(self, offset, dest);
        RAW.storeRM4x4DoubleUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRM4x4DoubleOnHeap(Float3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4Double(self, offset, dest);
        RAW.storeRM4x4DoubleHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }
}
