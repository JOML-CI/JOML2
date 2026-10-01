// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte3RawOpsUnsafe implements Byte3RawOps {
    public Byte3 storeUnsafe(Byte3Impl self, long address) {
        U.putByte(address + 0L, self.x);
        U.putByte(address + 1L, self.y);
        U.putByte(address + 2L, self.z);
        return self;
    }
    public Byte3 loadUnsafe(Byte3Impl self, long address) {
        self.x = U.getByte(address + 0L);
        self.y = U.getByte(address + 1L);
        self.z = U.getByte(address + 2L);
        return self;
    }
    public Byte3 storeShortUnsafe(Byte3Impl self, long address) {
        U.putShort(address + 0L, self.x);
        U.putShort(address + 2L, self.y);
        U.putShort(address + 4L, self.z);
        return self;
    }
    public Byte3 loadShortUnsafe(Byte3Impl self, long address) {
        self.x = (byte) U.getShort(address + 0L);
        self.y = (byte) U.getShort(address + 2L);
        self.z = (byte) U.getShort(address + 4L);
        return self;
    }
}
