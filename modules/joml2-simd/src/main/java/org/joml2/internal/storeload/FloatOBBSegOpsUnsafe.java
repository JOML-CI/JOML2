// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatOBBSegOpsUnsafe extends FloatOBBSegOps {

    private static final FloatOBBSegOpsMS MS = new FloatOBBSegOpsMS();
    private static final FloatOBBRawOpsUnsafe RAW = new FloatOBBRawOpsUnsafe();

    public MemorySegment store(FloatOBBImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 56; _k += 8) U.putLong(address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
            U.putFloat(address + 56L, _d[14]);
        } else {
            for (int _k = 0; _k < 15; _k++) U.putFloat(address + 4L * _k, _d[_k]);
        }
        return dest;
    }
    public FloatOBB load(FloatOBBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 56; _k += 8) U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(address + _k));
            _d[14] = U.getFloat(address + 56L);
        } else {
            for (int _k = 0; _k < 15; _k++) _d[_k] = U.getFloat(address + 4L * _k);
        }
        return self;
    }
    public MemorySegment storeDouble(FloatOBBImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        RAW.storeDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatOBB loadDouble(FloatOBBImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(self, offset, src);
        return RAW.loadDoubleUnsafe(self, src.address() + offset);
    }
}
