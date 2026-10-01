// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Int2BbOpsApi implements Int2BbOps {
    public IntBuffer storeAbsolute(Int2Impl self, int index, IntBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        return buf;
    }
    public Int2 loadAbsolute(Int2Impl self, int index, IntBuffer buf) {
        self.x = buf.get(index + 0);
        self.y = buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeAbsolute(Int2Impl self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, self.x);
        buf.putInt(index + 4, self.y);
        return buf;
    }
    public Int2 loadAbsolute(Int2Impl self, int index, ByteBuffer buf) {
        self.x = buf.getInt(index + 0);
        self.y = buf.getInt(index + 4);
        return self;
    }
    public LongBuffer storeAbsolute(Int2Impl self, int index, LongBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        return buf;
    }
    public Int2 loadAbsolute(Int2Impl self, int index, LongBuffer buf) {
        self.x = (int) buf.get(index + 0);
        self.y = (int) buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeLongAbsolute(Int2Impl self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.x);
        buf.putLong(index + 8, self.y);
        return buf;
    }
    public Int2 loadLongAbsolute(Int2Impl self, int index, ByteBuffer buf) {
        self.x = (int) buf.getLong(index + 0);
        self.y = (int) buf.getLong(index + 8);
        return self;
    }
}
