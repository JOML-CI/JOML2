// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short3RawOpsUnsafe implements Short3RawOps {
    public Short3 storeUnsafe(Short3 self, long address) {
        U.putShort(address + 0L, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        return self;
    }
    public Short3 loadUnsafe(long address) {
        short _c0 = U.getShort(address + 0L);
        short _c1 = U.getShort(address + 2L);
        short _c2 = U.getShort(address + 4L);
        return new Short3(_c0, _c1, _c2);
    }
    public Short3 storeByteUnsafe(Short3 self, long address) {
        U.putByte(address + 0L, (byte) self.x());
        U.putByte(address + 1L, (byte) self.y());
        U.putByte(address + 2L, (byte) self.z());
        return self;
    }
    public Short3 loadByteUnsafe(long address) {
        short _c0 = U.getByte(address + 0L);
        short _c1 = U.getByte(address + 1L);
        short _c2 = U.getByte(address + 2L);
        return new Short3(_c0, _c1, _c2);
    }
}
