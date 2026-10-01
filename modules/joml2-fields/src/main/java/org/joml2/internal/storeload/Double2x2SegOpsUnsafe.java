// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2x2SegOpsUnsafe extends Double2x2SegOps {
    private static final Double2x2SegOpsMS MS = new Double2x2SegOpsMS();

    public MemorySegment storeCM(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m01);
        U.putDouble(address + 24L, self.m11);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.m00);
        U.putDouble(bytes, address + 8L, self.m10);
        U.putDouble(bytes, address + 16L, self.m01);
        U.putDouble(bytes, address + 24L, self.m11);
        return dest;
    }

    public Double2x2 loadCM(Double2x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8L);
        self.m01 = U.getDouble(address + 16L);
        self.m11 = U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double2x2 loadCMOnHeap(Double2x2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.m00 = U.getDouble(bytes, address);
        self.m10 = U.getDouble(bytes, address + 8L);
        self.m01 = U.getDouble(bytes, address + 16L);
        self.m11 = U.getDouble(bytes, address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMFloat(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m10);
        U.putFloat(address + 8L, (float) self.m01);
        U.putFloat(address + 12L, (float) self.m11);
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, (float) self.m00);
        U.putFloat(bytes, address + 4L, (float) self.m10);
        U.putFloat(bytes, address + 8L, (float) self.m01);
        U.putFloat(bytes, address + 12L, (float) self.m11);
        return dest;
    }

    public Double2x2 loadCMFloat(Double2x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4L);
        self.m01 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double2x2 loadCMFloatOnHeap(Double2x2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.m00 = U.getFloat(bytes, address);
        self.m10 = U.getFloat(bytes, address + 4L);
        self.m01 = U.getFloat(bytes, address + 8L);
        self.m11 = U.getFloat(bytes, address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m10);
        U.putDouble(address + 24L, self.m11);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putDouble(bytes, address, self.m00);
        U.putDouble(bytes, address + 8L, self.m01);
        U.putDouble(bytes, address + 16L, self.m10);
        U.putDouble(bytes, address + 24L, self.m11);
        return dest;
    }

    public Double2x2 loadRM(Double2x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8L);
        self.m10 = U.getDouble(address + 16L);
        self.m11 = U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double2x2 loadRMOnHeap(Double2x2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.m00 = U.getDouble(bytes, address);
        self.m01 = U.getDouble(bytes, address + 8L);
        self.m10 = U.getDouble(bytes, address + 16L);
        self.m11 = U.getDouble(bytes, address + 24L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMFloat(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m01);
        U.putFloat(address + 8L, (float) self.m10);
        U.putFloat(address + 12L, (float) self.m11);
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        U.putFloat(bytes, address, (float) self.m00);
        U.putFloat(bytes, address + 4L, (float) self.m01);
        U.putFloat(bytes, address + 8L, (float) self.m10);
        U.putFloat(bytes, address + 12L, (float) self.m11);
        return dest;
    }

    public Double2x2 loadRMFloat(Double2x2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4L);
        self.m10 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double2x2 loadRMFloatOnHeap(Double2x2Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        self.m00 = U.getFloat(bytes, address);
        self.m01 = U.getFloat(bytes, address + 4L);
        self.m10 = U.getFloat(bytes, address + 8L);
        self.m11 = U.getFloat(bytes, address + 12L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Double2x2Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double2x2Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(bytes, address, self.m00);
        U.putDouble(bytes, address + 8, self.m10);
        U.putDouble(bytes, _p1, self.m01);
        U.putDouble(bytes, _p1 + 8, self.m11);
        return dest;
    }

    public Double2x2 loadCM(Double2x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8);
        self.m01 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double2x2 loadCMOnHeap(Double2x2Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = U.getDouble(bytes, address);
        self.m10 = U.getDouble(bytes, address + 8);
        self.m01 = U.getDouble(bytes, _p1);
        self.m11 = U.getDouble(bytes, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMFloat(Double2x2Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m10);
        U.putFloat(_p1, (float) self.m01);
        U.putFloat(_p1 + 4, (float) self.m11);
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double2x2Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(bytes, address, (float) self.m00);
        U.putFloat(bytes, address + 4, (float) self.m10);
        U.putFloat(bytes, _p1, (float) self.m01);
        U.putFloat(bytes, _p1 + 4, (float) self.m11);
        return dest;
    }

    public Double2x2 loadCMFloat(Double2x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double2x2 loadCMFloatOnHeap(Double2x2Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(bytes, address);
        self.m10 = U.getFloat(bytes, address + 4);
        self.m01 = U.getFloat(bytes, _p1);
        self.m11 = U.getFloat(bytes, _p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Double2x2Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m01);
        U.putDouble(_p1, self.m10);
        U.putDouble(_p1 + 8, self.m11);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double2x2Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        U.putDouble(bytes, address, self.m00);
        U.putDouble(bytes, address + 8, self.m01);
        U.putDouble(bytes, _p1, self.m10);
        U.putDouble(bytes, _p1 + 8, self.m11);
        return dest;
    }

    public Double2x2 loadRM(Double2x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8);
        self.m10 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double2x2 loadRMOnHeap(Double2x2Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        self.m00 = U.getDouble(bytes, address);
        self.m01 = U.getDouble(bytes, address + 8);
        self.m10 = U.getDouble(bytes, _p1);
        self.m11 = U.getDouble(bytes, _p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMFloat(Double2x2Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m01);
        U.putFloat(_p1, (float) self.m10);
        U.putFloat(_p1 + 4, (float) self.m11);
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double2x2Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        U.putFloat(bytes, address, (float) self.m00);
        U.putFloat(bytes, address + 4, (float) self.m01);
        U.putFloat(bytes, _p1, (float) self.m10);
        U.putFloat(bytes, _p1 + 4, (float) self.m11);
        return dest;
    }

    public Double2x2 loadRMFloat(Double2x2Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4);
        self.m10 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double2x2 loadRMFloatOnHeap(Double2x2Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(bytes, address);
        self.m01 = U.getFloat(bytes, address + 4);
        self.m10 = U.getFloat(bytes, _p1);
        self.m11 = U.getFloat(bytes, _p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM3x3(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM3x3(self, offset, dest);
        if (!dest.isNative()) return storeCM3x3OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m01);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return dest;
    }

    private static MemorySegment storeCM3x3OnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM3x3(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m01);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return dest;
    }

    public MemorySegment storeCM3x3Float(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM3x3Float(self, offset, dest);
        if (!dest.isNative()) return storeCM3x3FloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m10);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, (float) self.m01);
        _U.putFloat(address + 16L, (float) self.m11);
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return dest;
    }

    private static MemorySegment storeCM3x3FloatOnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM3x3Float(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m10);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, (float) self.m01);
        _U.putFloat(bytes, address + 16L, (float) self.m11);
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return dest;
    }

    public MemorySegment storeRM3x3(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM3x3(self, offset, dest);
        if (!dest.isNative()) return storeRM3x3OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, self.m10);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, 0.0);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 1.0);
        return dest;
    }

    private static MemorySegment storeRM3x3OnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM3x3(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, self.m10);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, 0.0);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 1.0);
        return dest;
    }

    public MemorySegment storeRM3x3Float(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM3x3Float(self, offset, dest);
        if (!dest.isNative()) return storeRM3x3FloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m01);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, (float) self.m10);
        _U.putFloat(address + 16L, (float) self.m11);
        _U.putFloat(address + 20L, 0.0f);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 1.0f);
        return dest;
    }

    private static MemorySegment storeRM3x3FloatOnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM3x3Float(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m01);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, (float) self.m10);
        _U.putFloat(bytes, address + 16L, (float) self.m11);
        _U.putFloat(bytes, address + 20L, 0.0f);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 1.0f);
        return dest;
    }

    public MemorySegment storeCM4x4(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m01);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 0.0);
        _U.putDouble(address + 72L, 0.0);
        _U.putDouble(address + 80L, 1.0);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return dest;
    }

    private static MemorySegment storeCM4x4OnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.m01);
        _U.putDouble(bytes, address + 40L, self.m11);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 0.0);
        _U.putDouble(bytes, address + 72L, 0.0);
        _U.putDouble(bytes, address + 80L, 1.0);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return dest;
    }

    public MemorySegment storeCM4x4Float(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4Float(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4FloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m10);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.m01);
        _U.putFloat(address + 20L, (float) self.m11);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 0.0f);
        _U.putFloat(address + 36L, 0.0f);
        _U.putFloat(address + 40L, 1.0f);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return dest;
    }

    private static MemorySegment storeCM4x4FloatOnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4Float(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m10);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.m01);
        _U.putFloat(bytes, address + 20L, (float) self.m11);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 0.0f);
        _U.putFloat(bytes, address + 36L, 0.0f);
        _U.putFloat(bytes, address + 40L, 1.0f);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return dest;
    }

    public MemorySegment storeRM4x4(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, 0.0);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m10);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, 0.0);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, 0.0);
        _U.putDouble(address + 72L, 0.0);
        _U.putDouble(address + 80L, 1.0);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return dest;
    }

    private static MemorySegment storeRM4x4OnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, 0.0);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.m10);
        _U.putDouble(bytes, address + 40L, self.m11);
        _U.putDouble(bytes, address + 48L, 0.0);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, 0.0);
        _U.putDouble(bytes, address + 72L, 0.0);
        _U.putDouble(bytes, address + 80L, 1.0);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return dest;
    }

    public MemorySegment storeRM4x4Float(Double2x2Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4Float(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4FloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m01);
        _U.putFloat(address + 8L, 0.0f);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.m10);
        _U.putFloat(address + 20L, (float) self.m11);
        _U.putFloat(address + 24L, 0.0f);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, 0.0f);
        _U.putFloat(address + 36L, 0.0f);
        _U.putFloat(address + 40L, 1.0f);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return dest;
    }

    private static MemorySegment storeRM4x4FloatOnHeap(Double2x2Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4Float(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m01);
        _U.putFloat(bytes, address + 8L, 0.0f);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.m10);
        _U.putFloat(bytes, address + 20L, (float) self.m11);
        _U.putFloat(bytes, address + 24L, 0.0f);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, 0.0f);
        _U.putFloat(bytes, address + 36L, 0.0f);
        _U.putFloat(bytes, address + 40L, 1.0f);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return dest;
    }
}
