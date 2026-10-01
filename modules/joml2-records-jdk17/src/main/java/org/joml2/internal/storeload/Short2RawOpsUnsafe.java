// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Short2RawOpsUnsafe implements Short2RawOps {
    public Short2 storeUnsafe(Short2 self, long address) {
        U.putShort(address + 0L, self.x());
        U.putShort(address + 2L, self.y());
        return self;
    }
    public Short2 loadUnsafe(long address) {
        short _c0 = U.getShort(address + 0L);
        short _c1 = U.getShort(address + 2L);
        return new Short2(_c0, _c1);
    }
    public Short2 storeByteUnsafe(Short2 self, long address) {
        U.putByte(address + 0L, (byte) self.x());
        U.putByte(address + 1L, (byte) self.y());
        return self;
    }
    public Short2 loadByteUnsafe(long address) {
        short _c0 = U.getByte(address + 0L);
        short _c1 = U.getByte(address + 1L);
        return new Short2(_c0, _c1);
    }
}
