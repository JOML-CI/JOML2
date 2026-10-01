// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleRectBbOpsApi implements DoubleRectBbOps {
    public DoubleBuffer storeAbsolute(DoubleRect self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.minX());
        buf.put(index + 1, self.minY());
        buf.put(index + 2, self.maxX());
        buf.put(index + 3, self.maxY());
        return buf;
    }
    public DoubleRect loadAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(DoubleRect self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.minX());
        buf.putDouble(index + 8, self.minY());
        buf.putDouble(index + 16, self.maxX());
        buf.putDouble(index + 24, self.maxY());
        return buf;
    }
    public DoubleRect loadAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }
    public FloatBuffer storeAbsolute(DoubleRect self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.minX());
        buf.put(index + 1, (float) self.minY());
        buf.put(index + 2, (float) self.maxX());
        buf.put(index + 3, (float) self.maxY());
        return buf;
    }
    public DoubleRect loadAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeFloatAbsolute(DoubleRect self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.minX());
        buf.putFloat(index + 4, (float) self.minY());
        buf.putFloat(index + 8, (float) self.maxX());
        buf.putFloat(index + 12, (float) self.maxY());
        return buf;
    }
    public DoubleRect loadFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }
}
