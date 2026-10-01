// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x4SegOpsUnsafe extends Double4x4SegOps {
    private static final Double4x4SegOpsMS MS = new Double4x4SegOpsMS();
    private static final Double4x4RawOpsUnsafe RAW = new Double4x4RawOpsUnsafe();

    public MemorySegment storeCMFloat(Double4x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest);
        RAW.storeCMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        double[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += 16L) {
            U.putFloat(bytes, _a, (float) _d[_o]);
            U.putFloat(bytes, _a + 4L, (float) _d[_o + 1]);
            U.putFloat(bytes, _a + 8L, (float) _d[_o + 2]);
            U.putFloat(bytes, _a + 12L, (float) _d[_o + 3]);
        }
        return dest;
    }

    public Double4x4 loadCMFloat(Double4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src);
        return RAW.loadCMFloatUnsafe(self, src.address() + offset);
    }

    private static Double4x4 loadCMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        double[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += 16L) {
            _d[_o] = U.getFloat(bytes, _a);
            _d[_o + 1] = U.getFloat(bytes, _a + 4L);
            _d[_o + 2] = U.getFloat(bytes, _a + 8L);
            _d[_o + 3] = U.getFloat(bytes, _a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRM(Double4x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest);
        RAW.storeRMUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest);
        RAW.storeRMHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Double4x4 loadRM(Double4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src);
        return RAW.loadRMUnsafe(self, src.address() + offset);
    }

    private static Double4x4 loadRMOnHeap(Double4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src);
        return RAW.loadRMHeap(self, bytes, HeapSegments.offset(src) + offset);
    }

    public MemorySegment storeRMFloat(Double4x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest);
        RAW.storeRMFloatUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest);
        long address = HeapSegments.offset(dest) + offset;
        double[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += 16L) {
            U.putFloat(bytes, _a, (float) _d[_o]);
            U.putFloat(bytes, _a + 4L, (float) _d[_o + 4]);
            U.putFloat(bytes, _a + 8L, (float) _d[_o + 8]);
            U.putFloat(bytes, _a + 12L, (float) _d[_o + 12]);
        }
        return dest;
    }

    public Double4x4 loadRMFloat(Double4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src);
        return RAW.loadRMFloatUnsafe(self, src.address() + offset);
    }

    private static Double4x4 loadRMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src);
        long address = HeapSegments.offset(src) + offset;
        double[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += 16L) {
            _d[_o] = U.getFloat(bytes, _a);
            _d[_o + 4] = U.getFloat(bytes, _a + 4L);
            _d[_o + 8] = U.getFloat(bytes, _a + 8L);
            _d[_o + 12] = U.getFloat(bytes, _a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCM(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putDouble(_a, _d[_o]);
            U.putDouble(_a + 8L, _d[_o + 1]);
            U.putDouble(_a + 16L, _d[_o + 2]);
            U.putDouble(_a + 24L, _d[_o + 3]);
        }
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putDouble(bytes, _a, _d[_o]);
            U.putDouble(bytes, _a + 8L, _d[_o + 1]);
            U.putDouble(bytes, _a + 16L, _d[_o + 2]);
            U.putDouble(bytes, _a + 24L, _d[_o + 3]);
        }
        return dest;
    }

    public Double4x4 loadCM(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = U.getDouble(_a);
            _d[_o + 1] = U.getDouble(_a + 8L);
            _d[_o + 2] = U.getDouble(_a + 16L);
            _d[_o + 3] = U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    private static Double4x4 loadCMOnHeap(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = U.getDouble(bytes, _a);
            _d[_o + 1] = U.getDouble(bytes, _a + 8L);
            _d[_o + 2] = U.getDouble(bytes, _a + 16L);
            _d[_o + 3] = U.getDouble(bytes, _a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeCMFloat(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeCMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeCMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putFloat(_a, (float) _d[_o]);
            U.putFloat(_a + 4L, (float) _d[_o + 1]);
            U.putFloat(_a + 8L, (float) _d[_o + 2]);
            U.putFloat(_a + 12L, (float) _d[_o + 3]);
        }
        return dest;
    }

    private static MemorySegment storeCMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putFloat(bytes, _a, (float) _d[_o]);
            U.putFloat(bytes, _a + 4L, (float) _d[_o + 1]);
            U.putFloat(bytes, _a + 8L, (float) _d[_o + 2]);
            U.putFloat(bytes, _a + 12L, (float) _d[_o + 3]);
        }
        return dest;
    }

    public Double4x4 loadCMFloat(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadCMFloatOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        double[] _d = self.data;
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

    private static Double4x4 loadCMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCMFloat(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        double[] _d = self.data;
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

    public MemorySegment storeRM(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRM(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putDouble(_a, _d[_o]);
            U.putDouble(_a + 8L, _d[_o + 4]);
            U.putDouble(_a + 16L, _d[_o + 8]);
            U.putDouble(_a + 24L, _d[_o + 12]);
        }
        return dest;
    }

    private static MemorySegment storeRMOnHeap(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRM(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putDouble(bytes, _a, _d[_o]);
            U.putDouble(bytes, _a + 8L, _d[_o + 4]);
            U.putDouble(bytes, _a + 16L, _d[_o + 8]);
            U.putDouble(bytes, _a + 24L, _d[_o + 12]);
        }
        return dest;
    }

    public Double4x4 loadRM(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = U.getDouble(_a);
            _d[_o + 4] = U.getDouble(_a + 8L);
            _d[_o + 8] = U.getDouble(_a + 16L);
            _d[_o + 12] = U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    private static Double4x4 loadRMOnHeap(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRM(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = U.getDouble(bytes, _a);
            _d[_o + 4] = U.getDouble(bytes, _a + 8L);
            _d[_o + 8] = U.getDouble(bytes, _a + 16L);
            _d[_o + 12] = U.getDouble(bytes, _a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public MemorySegment storeRMFloat(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        if (dest.isReadOnly()) return MS.storeRMFloat(self, offset, dest, stride);
        if (!dest.isNative()) return storeRMFloatOnHeap(self, offset, dest, stride);
        long address = dest.address() + offset;
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putFloat(_a, (float) _d[_o]);
            U.putFloat(_a + 4L, (float) _d[_o + 4]);
            U.putFloat(_a + 8L, (float) _d[_o + 8]);
            U.putFloat(_a + 12L, (float) _d[_o + 12]);
        }
        return dest;
    }

    private static MemorySegment storeRMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment dest, int stride) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeRMFloat(self, offset, dest, stride);
        long address = HeapSegments.offset(dest) + offset;
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putFloat(bytes, _a, (float) _d[_o]);
            U.putFloat(bytes, _a + 4L, (float) _d[_o + 4]);
            U.putFloat(bytes, _a + 8L, (float) _d[_o + 8]);
            U.putFloat(bytes, _a + 12L, (float) _d[_o + 12]);
        }
        return dest;
    }

    public Double4x4 loadRMFloat(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        if (!src.isNative()) return loadRMFloatOnHeap(self, offset, src, stride);
        long address = src.address() + offset;
        double[] _d = self.data;
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

    private static Double4x4 loadRMFloatOnHeap(Double4x4Impl self, long offset, MemorySegment src, int stride) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadRMFloat(self, offset, src, stride);
        long address = HeapSegments.offset(src) + offset;
        double[] _d = self.data;
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

    public MemorySegment storeCM(Double4x4Impl self, long offset, MemorySegment dest) {
        if (dest.isReadOnly()) return MS.storeCM(self, offset, dest);
        if (!dest.isNative()) return storeCMOnHeap(self, offset, dest);
        RAW.storeCMUnsafe(self, dest.address() + offset);
        return dest;
    }

    private static MemorySegment storeCMOnHeap(Double4x4Impl self, long offset, MemorySegment dest) {
        byte[] bytes = HeapSegments.bytes(dest);
        if (bytes == null) return MS.storeCM(self, offset, dest);
        RAW.storeCMHeap(self, bytes, HeapSegments.offset(dest) + offset);
        return dest;
    }

    public Double4x4 loadCM(Double4x4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return loadCMOnHeap(self, offset, src);
        return RAW.loadCMUnsafe(self, src.address() + offset);
    }

    private static Double4x4 loadCMOnHeap(Double4x4Impl self, long offset, MemorySegment src) {
        byte[] bytes = HeapSegments.bytes(src);
        if (bytes == null) return MS.loadCM(self, offset, src);
        return RAW.loadCMHeap(self, bytes, HeapSegments.offset(src) + offset);
    }
}
