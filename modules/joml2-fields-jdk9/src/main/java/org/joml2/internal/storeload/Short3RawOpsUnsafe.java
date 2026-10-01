// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short3RawOpsUnsafe implements Short3RawOps {
    public Short3 storeUnsafe(Short3Impl self, long address) {
        U.putShort(address + 0L, self.x);
        U.putShort(address + 2L, self.y);
        U.putShort(address + 4L, self.z);
        return self;
    }
    public Short3 loadUnsafe(Short3Impl self, long address) {
        self.x = U.getShort(address + 0L);
        self.y = U.getShort(address + 2L);
        self.z = U.getShort(address + 4L);
        return self;
    }
    public Short3 storeByteUnsafe(Short3Impl self, long address) {
        U.putByte(address + 0L, (byte) self.x);
        U.putByte(address + 1L, (byte) self.y);
        U.putByte(address + 2L, (byte) self.z);
        return self;
    }
    public Short3 loadByteUnsafe(Short3Impl self, long address) {
        self.x = U.getByte(address + 0L);
        self.y = U.getByte(address + 1L);
        self.z = U.getByte(address + 2L);
        return self;
    }
}
