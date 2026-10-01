// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Int4BbOpsApi implements Int4BbOps {
    public IntBuffer storeAbsolute(Int4 self, int index, IntBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        buf.put(index + 3, self.w());
        return buf;
    }
    public Int4 loadAbsolute(int index, IntBuffer buf) {
        int _c0 = buf.get(index + 0);
        int _c1 = buf.get(index + 1);
        int _c2 = buf.get(index + 2);
        int _c3 = buf.get(index + 3);
        return new Int4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(Int4 self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, self.x());
        buf.putInt(index + 4, self.y());
        buf.putInt(index + 8, self.z());
        buf.putInt(index + 12, self.w());
        return buf;
    }
    public Int4 loadAbsolute(int index, ByteBuffer buf) {
        int _c0 = buf.getInt(index + 0);
        int _c1 = buf.getInt(index + 4);
        int _c2 = buf.getInt(index + 8);
        int _c3 = buf.getInt(index + 12);
        return new Int4(_c0, _c1, _c2, _c3);
    }
    public LongBuffer storeAbsolute(Int4 self, int index, LongBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        buf.put(index + 3, self.w());
        return buf;
    }
    public Int4 loadAbsolute(int index, LongBuffer buf) {
        int _c0 = (int) buf.get(index + 0);
        int _c1 = (int) buf.get(index + 1);
        int _c2 = (int) buf.get(index + 2);
        int _c3 = (int) buf.get(index + 3);
        return new Int4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeLongAbsolute(Int4 self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.x());
        buf.putLong(index + 8, self.y());
        buf.putLong(index + 16, self.z());
        buf.putLong(index + 24, self.w());
        return buf;
    }
    public Int4 loadLongAbsolute(int index, ByteBuffer buf) {
        int _c0 = (int) buf.getLong(index + 0);
        int _c1 = (int) buf.getLong(index + 8);
        int _c2 = (int) buf.getLong(index + 16);
        int _c3 = (int) buf.getLong(index + 24);
        return new Int4(_c0, _c1, _c2, _c3);
    }
}
