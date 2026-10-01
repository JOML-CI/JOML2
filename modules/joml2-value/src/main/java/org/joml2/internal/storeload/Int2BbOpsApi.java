// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Int2BbOpsApi implements Int2BbOps {
    public IntBuffer storeAbsolute(Int2 self, int index, IntBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        return buf;
    }
    public Int2 loadAbsolute(int index, IntBuffer buf) {
        int _c0 = buf.get(index + 0);
        int _c1 = buf.get(index + 1);
        return new Int2(_c0, _c1);
    }
    public ByteBuffer storeAbsolute(Int2 self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, self.x());
        buf.putInt(index + 4, self.y());
        return buf;
    }
    public Int2 loadAbsolute(int index, ByteBuffer buf) {
        int _c0 = buf.getInt(index + 0);
        int _c1 = buf.getInt(index + 4);
        return new Int2(_c0, _c1);
    }
    public LongBuffer storeAbsolute(Int2 self, int index, LongBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        return buf;
    }
    public Int2 loadAbsolute(int index, LongBuffer buf) {
        int _c0 = (int) buf.get(index + 0);
        int _c1 = (int) buf.get(index + 1);
        return new Int2(_c0, _c1);
    }
    public ByteBuffer storeLongAbsolute(Int2 self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.x());
        buf.putLong(index + 8, self.y());
        return buf;
    }
    public Int2 loadLongAbsolute(int index, ByteBuffer buf) {
        int _c0 = (int) buf.getLong(index + 0);
        int _c1 = (int) buf.getLong(index + 8);
        return new Int2(_c0, _c1);
    }
}
