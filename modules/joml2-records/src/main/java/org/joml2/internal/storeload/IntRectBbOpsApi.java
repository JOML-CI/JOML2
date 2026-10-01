// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class IntRectBbOpsApi implements IntRectBbOps {
    public IntBuffer storeAbsolute(IntRect self, int index, IntBuffer buf) {
        buf.put(index + 0, self.minX());
        buf.put(index + 1, self.minY());
        buf.put(index + 2, self.maxX());
        buf.put(index + 3, self.maxY());
        return buf;
    }
    public IntRect loadAbsolute(int index, IntBuffer buf) {
        int _c0 = buf.get(index + 0);
        int _c1 = buf.get(index + 1);
        int _c2 = buf.get(index + 2);
        int _c3 = buf.get(index + 3);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(IntRect self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, self.minX());
        buf.putInt(index + 4, self.minY());
        buf.putInt(index + 8, self.maxX());
        buf.putInt(index + 12, self.maxY());
        return buf;
    }
    public IntRect loadAbsolute(int index, ByteBuffer buf) {
        int _c0 = buf.getInt(index + 0);
        int _c1 = buf.getInt(index + 4);
        int _c2 = buf.getInt(index + 8);
        int _c3 = buf.getInt(index + 12);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
    public LongBuffer storeAbsolute(IntRect self, int index, LongBuffer buf) {
        buf.put(index + 0, self.minX());
        buf.put(index + 1, self.minY());
        buf.put(index + 2, self.maxX());
        buf.put(index + 3, self.maxY());
        return buf;
    }
    public IntRect loadAbsolute(int index, LongBuffer buf) {
        int _c0 = (int) buf.get(index + 0);
        int _c1 = (int) buf.get(index + 1);
        int _c2 = (int) buf.get(index + 2);
        int _c3 = (int) buf.get(index + 3);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeLongAbsolute(IntRect self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.minX());
        buf.putLong(index + 8, self.minY());
        buf.putLong(index + 16, self.maxX());
        buf.putLong(index + 24, self.maxY());
        return buf;
    }
    public IntRect loadLongAbsolute(int index, ByteBuffer buf) {
        int _c0 = (int) buf.getLong(index + 0);
        int _c1 = (int) buf.getLong(index + 8);
        int _c2 = (int) buf.getLong(index + 16);
        int _c3 = (int) buf.getLong(index + 24);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
}
