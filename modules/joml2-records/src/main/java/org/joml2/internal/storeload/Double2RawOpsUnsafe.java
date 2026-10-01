// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2RawOpsUnsafe extends Double2RawOps {
    public Double2 storeUnsafe(Double2 self, long address) {
        U.putDouble(address, self.x());
        U.putDouble(address + 8L, self.y());
        return self;
    }

    public Double2 storeHeap(Double2 self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.x());
        U.putDouble(bytes, address + 8L, self.y());
        return self;
    }

    public Double2 loadUnsafe(long address) {
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8L);
        return new Double2(_c0, _c1);
    }

    public Double2 loadHeap(byte[] bytes, long address) {
        double _c0 = U.getDouble(bytes, address);
        double _c1 = U.getDouble(bytes, address + 8L);
        return new Double2(_c0, _c1);
    }

    public Double2 storeFloatUnsafe(Double2 self, long address) {
        U.putFloat(address, (float) self.x());
        U.putFloat(address + 4L, (float) self.y());
        return self;
    }

    public Double2 storeFloatHeap(Double2 self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.x());
        U.putFloat(bytes, address + 4L, (float) self.y());
        return self;
    }

    public Double2 loadFloatUnsafe(long address) {
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4L);
        return new Double2(_c0, _c1);
    }

    public Double2 loadFloatHeap(byte[] bytes, long address) {
        double _c0 = U.getFloat(bytes, address);
        double _c1 = U.getFloat(bytes, address + 4L);
        return new Double2(_c0, _c1);
    }
}
