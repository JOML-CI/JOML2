// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleTransformBbOpsApi implements DoubleTransformBbOps {
    public DoubleBuffer storeAbsolute(DoubleTransform self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.tX());
        buf.put(index + 1, self.tY());
        buf.put(index + 2, self.tZ());
        buf.put(index + 3, self.rX());
        buf.put(index + 4, self.rY());
        buf.put(index + 5, self.rZ());
        buf.put(index + 6, self.rW());
        buf.put(index + 7, self.sX());
        buf.put(index + 8, self.sY());
        buf.put(index + 9, self.sZ());
        return buf;
    }
    public DoubleTransform loadAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        double _c6 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        double _c8 = buf.get(index + 8);
        double _c9 = buf.get(index + 9);
        return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
    public ByteBuffer storeAbsolute(DoubleTransform self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.tX());
        buf.putDouble(index + 8, self.tY());
        buf.putDouble(index + 16, self.tZ());
        buf.putDouble(index + 24, self.rX());
        buf.putDouble(index + 32, self.rY());
        buf.putDouble(index + 40, self.rZ());
        buf.putDouble(index + 48, self.rW());
        buf.putDouble(index + 56, self.sX());
        buf.putDouble(index + 64, self.sY());
        buf.putDouble(index + 72, self.sZ());
        return buf;
    }
    public DoubleTransform loadAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        double _c4 = buf.getDouble(index + 32);
        double _c5 = buf.getDouble(index + 40);
        double _c6 = buf.getDouble(index + 48);
        double _c7 = buf.getDouble(index + 56);
        double _c8 = buf.getDouble(index + 64);
        double _c9 = buf.getDouble(index + 72);
        return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
    public FloatBuffer storeAbsolute(DoubleTransform self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.tX());
        buf.put(index + 1, (float) self.tY());
        buf.put(index + 2, (float) self.tZ());
        buf.put(index + 3, (float) self.rX());
        buf.put(index + 4, (float) self.rY());
        buf.put(index + 5, (float) self.rZ());
        buf.put(index + 6, (float) self.rW());
        buf.put(index + 7, (float) self.sX());
        buf.put(index + 8, (float) self.sY());
        buf.put(index + 9, (float) self.sZ());
        return buf;
    }
    public DoubleTransform loadAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        double _c6 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        double _c8 = buf.get(index + 8);
        double _c9 = buf.get(index + 9);
        return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
    public ByteBuffer storeFloatAbsolute(DoubleTransform self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.tX());
        buf.putFloat(index + 4, (float) self.tY());
        buf.putFloat(index + 8, (float) self.tZ());
        buf.putFloat(index + 12, (float) self.rX());
        buf.putFloat(index + 16, (float) self.rY());
        buf.putFloat(index + 20, (float) self.rZ());
        buf.putFloat(index + 24, (float) self.rW());
        buf.putFloat(index + 28, (float) self.sX());
        buf.putFloat(index + 32, (float) self.sY());
        buf.putFloat(index + 36, (float) self.sZ());
        return buf;
    }
    public DoubleTransform loadFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        double _c4 = buf.getFloat(index + 16);
        double _c5 = buf.getFloat(index + 20);
        double _c6 = buf.getFloat(index + 24);
        double _c7 = buf.getFloat(index + 28);
        double _c8 = buf.getFloat(index + 32);
        double _c9 = buf.getFloat(index + 36);
        return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
}
