// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3x3SegOpsUnsafe extends Double3x3SegOps {
    private static final Double3x3SegOpsMS MS = new Double3x3SegOpsMS();

    public MemorySegment storeCM(Double3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, self.m01);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m21);
        _U.putDouble(address + 48L, self.m02);
        _U.putDouble(address + 56L, self.m12);
        _U.putDouble(address + 64L, self.m22);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, self.m20);
        _U.putDouble(bytes, address + 24L, self.m01);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, self.m21);
        _U.putDouble(bytes, address + 48L, self.m02);
        _U.putDouble(bytes, address + 56L, self.m12);
        _U.putDouble(bytes, address + 64L, self.m22);
        return dest;
    }

    public Double3x3 loadCM(Double3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m10 = _U.getDouble(address + 8L);
        self.m20 = _U.getDouble(address + 16L);
        self.m01 = _U.getDouble(address + 24L);
        self.m11 = _U.getDouble(address + 32L);
        self.m21 = _U.getDouble(address + 40L);
        self.m02 = _U.getDouble(address + 48L);
        self.m12 = _U.getDouble(address + 56L);
        self.m22 = _U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x3 loadCMOnHeap(Double3x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m10 = _U.getDouble(bytes, address + 8L);
        self.m20 = _U.getDouble(bytes, address + 16L);
        self.m01 = _U.getDouble(bytes, address + 24L);
        self.m11 = _U.getDouble(bytes, address + 32L);
        self.m21 = _U.getDouble(bytes, address + 40L);
        self.m02 = _U.getDouble(bytes, address + 48L);
        self.m12 = _U.getDouble(bytes, address + 56L);
        self.m22 = _U.getDouble(bytes, address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMFloat(Double3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m10);
        _U.putFloat(address + 8L, (float) self.m20);
        _U.putFloat(address + 12L, (float) self.m01);
        _U.putFloat(address + 16L, (float) self.m11);
        _U.putFloat(address + 20L, (float) self.m21);
        _U.putFloat(address + 24L, (float) self.m02);
        _U.putFloat(address + 28L, (float) self.m12);
        _U.putFloat(address + 32L, (float) self.m22);
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m10);
        _U.putFloat(bytes, address + 8L, (float) self.m20);
        _U.putFloat(bytes, address + 12L, (float) self.m01);
        _U.putFloat(bytes, address + 16L, (float) self.m11);
        _U.putFloat(bytes, address + 20L, (float) self.m21);
        _U.putFloat(bytes, address + 24L, (float) self.m02);
        _U.putFloat(bytes, address + 28L, (float) self.m12);
        _U.putFloat(bytes, address + 32L, (float) self.m22);
        return dest;
    }

    public Double3x3 loadCMFloat(Double3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4L);
        self.m20 = _U.getFloat(address + 8L);
        self.m01 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m21 = _U.getFloat(address + 20L);
        self.m02 = _U.getFloat(address + 24L);
        self.m12 = _U.getFloat(address + 28L);
        self.m22 = _U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x3 loadCMFloatOnHeap(Double3x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4L);
        self.m20 = _U.getFloat(bytes, address + 8L);
        self.m01 = _U.getFloat(bytes, address + 12L);
        self.m11 = _U.getFloat(bytes, address + 16L);
        self.m21 = _U.getFloat(bytes, address + 20L);
        self.m02 = _U.getFloat(bytes, address + 24L);
        self.m12 = _U.getFloat(bytes, address + 28L);
        self.m22 = _U.getFloat(bytes, address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Double3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, self.m10);
        _U.putDouble(address + 32L, self.m11);
        _U.putDouble(address + 40L, self.m12);
        _U.putDouble(address + 48L, self.m20);
        _U.putDouble(address + 56L, self.m21);
        _U.putDouble(address + 64L, self.m22);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, self.m02);
        _U.putDouble(bytes, address + 24L, self.m10);
        _U.putDouble(bytes, address + 32L, self.m11);
        _U.putDouble(bytes, address + 40L, self.m12);
        _U.putDouble(bytes, address + 48L, self.m20);
        _U.putDouble(bytes, address + 56L, self.m21);
        _U.putDouble(bytes, address + 64L, self.m22);
        return dest;
    }

    public Double3x3 loadRM(Double3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8L);
        self.m02 = _U.getDouble(address + 16L);
        self.m10 = _U.getDouble(address + 24L);
        self.m11 = _U.getDouble(address + 32L);
        self.m12 = _U.getDouble(address + 40L);
        self.m20 = _U.getDouble(address + 48L);
        self.m21 = _U.getDouble(address + 56L);
        self.m22 = _U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x3 loadRMOnHeap(Double3x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m01 = _U.getDouble(bytes, address + 8L);
        self.m02 = _U.getDouble(bytes, address + 16L);
        self.m10 = _U.getDouble(bytes, address + 24L);
        self.m11 = _U.getDouble(bytes, address + 32L);
        self.m12 = _U.getDouble(bytes, address + 40L);
        self.m20 = _U.getDouble(bytes, address + 48L);
        self.m21 = _U.getDouble(bytes, address + 56L);
        self.m22 = _U.getDouble(bytes, address + 64L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMFloat(Double3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m01);
        _U.putFloat(address + 8L, (float) self.m02);
        _U.putFloat(address + 12L, (float) self.m10);
        _U.putFloat(address + 16L, (float) self.m11);
        _U.putFloat(address + 20L, (float) self.m12);
        _U.putFloat(address + 24L, (float) self.m20);
        _U.putFloat(address + 28L, (float) self.m21);
        _U.putFloat(address + 32L, (float) self.m22);
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m01);
        _U.putFloat(bytes, address + 8L, (float) self.m02);
        _U.putFloat(bytes, address + 12L, (float) self.m10);
        _U.putFloat(bytes, address + 16L, (float) self.m11);
        _U.putFloat(bytes, address + 20L, (float) self.m12);
        _U.putFloat(bytes, address + 24L, (float) self.m20);
        _U.putFloat(bytes, address + 28L, (float) self.m21);
        _U.putFloat(bytes, address + 32L, (float) self.m22);
        return dest;
    }

    public Double3x3 loadRMFloat(Double3x3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src);
        long address = src.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4L);
        self.m02 = _U.getFloat(address + 8L);
        self.m10 = _U.getFloat(address + 12L);
        self.m11 = _U.getFloat(address + 16L);
        self.m12 = _U.getFloat(address + 20L);
        self.m20 = _U.getFloat(address + 24L);
        self.m21 = _U.getFloat(address + 28L);
        self.m22 = _U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x3 loadRMFloatOnHeap(Double3x3Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4L);
        self.m02 = _U.getFloat(bytes, address + 8L);
        self.m10 = _U.getFloat(bytes, address + 12L);
        self.m11 = _U.getFloat(bytes, address + 16L);
        self.m12 = _U.getFloat(bytes, address + 20L);
        self.m20 = _U.getFloat(bytes, address + 24L);
        self.m21 = _U.getFloat(bytes, address + 28L);
        self.m22 = _U.getFloat(bytes, address + 32L);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m10);
        _U.putDouble(address + 16, self.m20);
        _U.putDouble(_p1, self.m01);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m21);
        _U.putDouble(_p2, self.m02);
        _U.putDouble(_p2 + 8, self.m12);
        _U.putDouble(_p2 + 16, self.m22);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m10);
        _U.putDouble(bytes, address + 16, self.m20);
        _U.putDouble(bytes, _p1, self.m01);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m21);
        _U.putDouble(bytes, _p2, self.m02);
        _U.putDouble(bytes, _p2 + 8, self.m12);
        _U.putDouble(bytes, _p2 + 16, self.m22);
        return dest;
    }

    public Double3x3 loadCM(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m10 = _U.getDouble(address + 8);
        self.m20 = _U.getDouble(address + 16);
        self.m01 = _U.getDouble(_p1);
        self.m11 = _U.getDouble(_p1 + 8);
        self.m21 = _U.getDouble(_p1 + 16);
        self.m02 = _U.getDouble(_p2);
        self.m12 = _U.getDouble(_p2 + 8);
        self.m22 = _U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x3 loadCMOnHeap(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m10 = _U.getDouble(bytes, address + 8);
        self.m20 = _U.getDouble(bytes, address + 16);
        self.m01 = _U.getDouble(bytes, _p1);
        self.m11 = _U.getDouble(bytes, _p1 + 8);
        self.m21 = _U.getDouble(bytes, _p1 + 16);
        self.m02 = _U.getDouble(bytes, _p2);
        self.m12 = _U.getDouble(bytes, _p2 + 8);
        self.m22 = _U.getDouble(bytes, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMFloat(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4, (float) self.m10);
        _U.putFloat(address + 8, (float) self.m20);
        _U.putFloat(_p1, (float) self.m01);
        _U.putFloat(_p1 + 4, (float) self.m11);
        _U.putFloat(_p1 + 8, (float) self.m21);
        _U.putFloat(_p2, (float) self.m02);
        _U.putFloat(_p2 + 4, (float) self.m12);
        _U.putFloat(_p2 + 8, (float) self.m22);
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4, (float) self.m10);
        _U.putFloat(bytes, address + 8, (float) self.m20);
        _U.putFloat(bytes, _p1, (float) self.m01);
        _U.putFloat(bytes, _p1 + 4, (float) self.m11);
        _U.putFloat(bytes, _p1 + 8, (float) self.m21);
        _U.putFloat(bytes, _p2, (float) self.m02);
        _U.putFloat(bytes, _p2 + 4, (float) self.m12);
        _U.putFloat(bytes, _p2 + 8, (float) self.m22);
        return dest;
    }

    public Double3x3 loadCMFloat(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m10 = _U.getFloat(address + 4);
        self.m20 = _U.getFloat(address + 8);
        self.m01 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m21 = _U.getFloat(_p1 + 8);
        self.m02 = _U.getFloat(_p2);
        self.m12 = _U.getFloat(_p2 + 4);
        self.m22 = _U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x3 loadCMFloatOnHeap(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m10 = _U.getFloat(bytes, address + 4);
        self.m20 = _U.getFloat(bytes, address + 8);
        self.m01 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m21 = _U.getFloat(bytes, _p1 + 8);
        self.m02 = _U.getFloat(bytes, _p2);
        self.m12 = _U.getFloat(bytes, _p2 + 4);
        self.m22 = _U.getFloat(bytes, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8, self.m01);
        _U.putDouble(address + 16, self.m02);
        _U.putDouble(_p1, self.m10);
        _U.putDouble(_p1 + 8, self.m11);
        _U.putDouble(_p1 + 16, self.m12);
        _U.putDouble(_p2, self.m20);
        _U.putDouble(_p2 + 8, self.m21);
        _U.putDouble(_p2 + 16, self.m22);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8, self.m01);
        _U.putDouble(bytes, address + 16, self.m02);
        _U.putDouble(bytes, _p1, self.m10);
        _U.putDouble(bytes, _p1 + 8, self.m11);
        _U.putDouble(bytes, _p1 + 16, self.m12);
        _U.putDouble(bytes, _p2, self.m20);
        _U.putDouble(bytes, _p2 + 8, self.m21);
        _U.putDouble(bytes, _p2 + 16, self.m22);
        return dest;
    }

    public Double3x3 loadRM(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(address);
        self.m01 = _U.getDouble(address + 8);
        self.m02 = _U.getDouble(address + 16);
        self.m10 = _U.getDouble(_p1);
        self.m11 = _U.getDouble(_p1 + 8);
        self.m12 = _U.getDouble(_p1 + 16);
        self.m20 = _U.getDouble(_p2);
        self.m21 = _U.getDouble(_p2 + 8);
        self.m22 = _U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x3 loadRMOnHeap(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getDouble(bytes, address);
        self.m01 = _U.getDouble(bytes, address + 8);
        self.m02 = _U.getDouble(bytes, address + 16);
        self.m10 = _U.getDouble(bytes, _p1);
        self.m11 = _U.getDouble(bytes, _p1 + 8);
        self.m12 = _U.getDouble(bytes, _p1 + 16);
        self.m20 = _U.getDouble(bytes, _p2);
        self.m21 = _U.getDouble(bytes, _p2 + 8);
        self.m22 = _U.getDouble(bytes, _p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMFloat(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4, (float) self.m01);
        _U.putFloat(address + 8, (float) self.m02);
        _U.putFloat(_p1, (float) self.m10);
        _U.putFloat(_p1 + 4, (float) self.m11);
        _U.putFloat(_p1 + 8, (float) self.m12);
        _U.putFloat(_p2, (float) self.m20);
        _U.putFloat(_p2 + 4, (float) self.m21);
        _U.putFloat(_p2 + 8, (float) self.m22);
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double3x3Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4, (float) self.m01);
        _U.putFloat(bytes, address + 8, (float) self.m02);
        _U.putFloat(bytes, _p1, (float) self.m10);
        _U.putFloat(bytes, _p1 + 4, (float) self.m11);
        _U.putFloat(bytes, _p1 + 8, (float) self.m12);
        _U.putFloat(bytes, _p2, (float) self.m20);
        _U.putFloat(bytes, _p2 + 4, (float) self.m21);
        _U.putFloat(bytes, _p2 + 8, (float) self.m22);
        return dest;
    }

    public Double3x3 loadRMFloat(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(address);
        self.m01 = _U.getFloat(address + 4);
        self.m02 = _U.getFloat(address + 8);
        self.m10 = _U.getFloat(_p1);
        self.m11 = _U.getFloat(_p1 + 4);
        self.m12 = _U.getFloat(_p1 + 8);
        self.m20 = _U.getFloat(_p2);
        self.m21 = _U.getFloat(_p2 + 4);
        self.m22 = _U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    private static Double3x3 loadRMFloatOnHeap(Double3x3Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.m00 = _U.getFloat(bytes, address);
        self.m01 = _U.getFloat(bytes, address + 4);
        self.m02 = _U.getFloat(bytes, address + 8);
        self.m10 = _U.getFloat(bytes, _p1);
        self.m11 = _U.getFloat(bytes, _p1 + 4);
        self.m12 = _U.getFloat(bytes, _p1 + 8);
        self.m20 = _U.getFloat(bytes, _p2);
        self.m21 = _U.getFloat(bytes, _p2 + 4);
        self.m22 = _U.getFloat(bytes, _p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM4x4(Double3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m10);
        _U.putDouble(address + 16L, self.m20);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m01);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, self.m21);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.m02);
        _U.putDouble(address + 72L, self.m12);
        _U.putDouble(address + 80L, self.m22);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return dest;
    }

    private static MemorySegment storeCM4x4OnHeap(Double3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m10);
        _U.putDouble(bytes, address + 16L, self.m20);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.m01);
        _U.putDouble(bytes, address + 40L, self.m11);
        _U.putDouble(bytes, address + 48L, self.m21);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, self.m02);
        _U.putDouble(bytes, address + 72L, self.m12);
        _U.putDouble(bytes, address + 80L, self.m22);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return dest;
    }

    public MemorySegment storeCM4x4Float(Double3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM4x4Float(self, offset, dest);
        if (!dest.isNative()) return storeCM4x4FloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m10);
        _U.putFloat(address + 8L, (float) self.m20);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.m01);
        _U.putFloat(address + 20L, (float) self.m11);
        _U.putFloat(address + 24L, (float) self.m21);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, (float) self.m02);
        _U.putFloat(address + 36L, (float) self.m12);
        _U.putFloat(address + 40L, (float) self.m22);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return dest;
    }

    private static MemorySegment storeCM4x4FloatOnHeap(Double3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM4x4Float(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m10);
        _U.putFloat(bytes, address + 8L, (float) self.m20);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.m01);
        _U.putFloat(bytes, address + 20L, (float) self.m11);
        _U.putFloat(bytes, address + 24L, (float) self.m21);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, (float) self.m02);
        _U.putFloat(bytes, address + 36L, (float) self.m12);
        _U.putFloat(bytes, address + 40L, (float) self.m22);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return dest;
    }

    public MemorySegment storeRM4x4(Double3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4OnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00);
        _U.putDouble(address + 8L, self.m01);
        _U.putDouble(address + 16L, self.m02);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.m10);
        _U.putDouble(address + 40L, self.m11);
        _U.putDouble(address + 48L, self.m12);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.m20);
        _U.putDouble(address + 72L, self.m21);
        _U.putDouble(address + 80L, self.m22);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return dest;
    }

    private static MemorySegment storeRM4x4OnHeap(Double3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00);
        _U.putDouble(bytes, address + 8L, self.m01);
        _U.putDouble(bytes, address + 16L, self.m02);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.m10);
        _U.putDouble(bytes, address + 40L, self.m11);
        _U.putDouble(bytes, address + 48L, self.m12);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, self.m20);
        _U.putDouble(bytes, address + 72L, self.m21);
        _U.putDouble(bytes, address + 80L, self.m22);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return dest;
    }

    public MemorySegment storeRM4x4Float(Double3x3Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM4x4Float(self, offset, dest);
        if (!dest.isNative()) return storeRM4x4FloatOnHeap(self, offset, dest);
        long address = dest.address() + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00);
        _U.putFloat(address + 4L, (float) self.m01);
        _U.putFloat(address + 8L, (float) self.m02);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, (float) self.m10);
        _U.putFloat(address + 20L, (float) self.m11);
        _U.putFloat(address + 24L, (float) self.m12);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, (float) self.m20);
        _U.putFloat(address + 36L, (float) self.m21);
        _U.putFloat(address + 40L, (float) self.m22);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return dest;
    }

    private static MemorySegment storeRM4x4FloatOnHeap(Double3x3Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM4x4Float(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00);
        _U.putFloat(bytes, address + 4L, (float) self.m01);
        _U.putFloat(bytes, address + 8L, (float) self.m02);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, (float) self.m10);
        _U.putFloat(bytes, address + 20L, (float) self.m11);
        _U.putFloat(bytes, address + 24L, (float) self.m12);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, (float) self.m20);
        _U.putFloat(bytes, address + 36L, (float) self.m21);
        _U.putFloat(bytes, address + 40L, (float) self.m22);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return dest;
    }
}
