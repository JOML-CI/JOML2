// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Int3BbOpsApi implements Int3BbOps {
    public IntBuffer storeAbsolute(Int3 self, int index, IntBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        return buf;
    }
    public Int3 loadAbsolute(int index, IntBuffer buf) {
        int _c0 = buf.get(index + 0);
        int _c1 = buf.get(index + 1);
        int _c2 = buf.get(index + 2);
        return new Int3(_c0, _c1, _c2);
    }
    public ByteBuffer storeAbsolute(Int3 self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, self.x());
        buf.putInt(index + 4, self.y());
        buf.putInt(index + 8, self.z());
        return buf;
    }
    public Int3 loadAbsolute(int index, ByteBuffer buf) {
        int _c0 = buf.getInt(index + 0);
        int _c1 = buf.getInt(index + 4);
        int _c2 = buf.getInt(index + 8);
        return new Int3(_c0, _c1, _c2);
    }
    public LongBuffer storeAbsolute(Int3 self, int index, LongBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        return buf;
    }
    public Int3 loadAbsolute(int index, LongBuffer buf) {
        int _c0 = (int) buf.get(index + 0);
        int _c1 = (int) buf.get(index + 1);
        int _c2 = (int) buf.get(index + 2);
        return new Int3(_c0, _c1, _c2);
    }
    public ByteBuffer storeLongAbsolute(Int3 self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.x());
        buf.putLong(index + 8, self.y());
        buf.putLong(index + 16, self.z());
        return buf;
    }
    public Int3 loadLongAbsolute(int index, ByteBuffer buf) {
        int _c0 = (int) buf.getLong(index + 0);
        int _c1 = (int) buf.getLong(index + 8);
        int _c2 = (int) buf.getLong(index + 16);
        return new Int3(_c0, _c1, _c2);
    }
}
