// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short4RawOpsUnsafe implements Short4RawOps {
    public Short4 storeUnsafe(Short4 self, long address) {
        U.putShort(address + 0L, self.x());
        U.putShort(address + 2L, self.y());
        U.putShort(address + 4L, self.z());
        U.putShort(address + 6L, self.w());
        return self;
    }
    public Short4 loadUnsafe(long address) {
        short _c0 = U.getShort(address + 0L);
        short _c1 = U.getShort(address + 2L);
        short _c2 = U.getShort(address + 4L);
        short _c3 = U.getShort(address + 6L);
        return new Short4(_c0, _c1, _c2, _c3);
    }
    public Short4 storeByteUnsafe(Short4 self, long address) {
        U.putByte(address + 0L, (byte) self.x());
        U.putByte(address + 1L, (byte) self.y());
        U.putByte(address + 2L, (byte) self.z());
        U.putByte(address + 3L, (byte) self.w());
        return self;
    }
    public Short4 loadByteUnsafe(long address) {
        short _c0 = U.getByte(address + 0L);
        short _c1 = U.getByte(address + 1L);
        short _c2 = U.getByte(address + 2L);
        short _c3 = U.getByte(address + 3L);
        return new Short4(_c0, _c1, _c2, _c3);
    }
}
