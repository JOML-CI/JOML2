// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short3RawOpsUnsafe implements Short3RawOps {
    public Short3 storeUnsafe(Short3Impl self, long address) {
        U.putShort(address + 0L, self.data[0]);
        U.putShort(address + 2L, self.data[1]);
        U.putShort(address + 4L, self.data[2]);
        return self;
    }
    public Short3 loadUnsafe(Short3Impl self, long address) {
        self.data[0] = U.getShort(address + 0L);
        self.data[1] = U.getShort(address + 2L);
        self.data[2] = U.getShort(address + 4L);
        return self;
    }
    public Short3 storeByteUnsafe(Short3Impl self, long address) {
        U.putByte(address + 0L, (byte) self.data[0]);
        U.putByte(address + 1L, (byte) self.data[1]);
        U.putByte(address + 2L, (byte) self.data[2]);
        return self;
    }
    public Short3 loadByteUnsafe(Short3Impl self, long address) {
        self.data[0] = U.getByte(address + 0L);
        self.data[1] = U.getByte(address + 1L);
        self.data[2] = U.getByte(address + 2L);
        return self;
    }
}
