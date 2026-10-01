// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class IntRectRawOpsUnsafe implements IntRectRawOps {
    public IntRect storeUnsafe(IntRectImpl self, long address) {
        U.putInt(address + 0L, self.data[0]);
        U.putInt(address + 4L, self.data[1]);
        U.putInt(address + 8L, self.data[2]);
        U.putInt(address + 12L, self.data[3]);
        return self;
    }
    public IntRect loadUnsafe(IntRectImpl self, long address) {
        self.data[0] = U.getInt(address + 0L);
        self.data[1] = U.getInt(address + 4L);
        self.data[2] = U.getInt(address + 8L);
        self.data[3] = U.getInt(address + 12L);
        return self;
    }
    public IntRect storeLongUnsafe(IntRectImpl self, long address) {
        U.putLong(address + 0L, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        U.putLong(address + 16L, self.data[2]);
        U.putLong(address + 24L, self.data[3]);
        return self;
    }
    public IntRect loadLongUnsafe(IntRectImpl self, long address) {
        self.data[0] = (int) U.getLong(address + 0L);
        self.data[1] = (int) U.getLong(address + 8L);
        self.data[2] = (int) U.getLong(address + 16L);
        self.data[3] = (int) U.getLong(address + 24L);
        return self;
    }
}
