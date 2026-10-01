// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatTransformBbOpsApi implements FloatTransformBbOps {
    public FloatBuffer storeAbsolute(FloatTransform self, int index, FloatBuffer buf) {
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
    public FloatTransform loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        float _c4 = buf.get(index + 4);
        float _c5 = buf.get(index + 5);
        float _c6 = buf.get(index + 6);
        float _c7 = buf.get(index + 7);
        float _c8 = buf.get(index + 8);
        float _c9 = buf.get(index + 9);
        return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
    public ByteBuffer storeAbsolute(FloatTransform self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.tX());
        buf.putFloat(index + 4, self.tY());
        buf.putFloat(index + 8, self.tZ());
        buf.putFloat(index + 12, self.rX());
        buf.putFloat(index + 16, self.rY());
        buf.putFloat(index + 20, self.rZ());
        buf.putFloat(index + 24, self.rW());
        buf.putFloat(index + 28, self.sX());
        buf.putFloat(index + 32, self.sY());
        buf.putFloat(index + 36, self.sZ());
        return buf;
    }
    public FloatTransform loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        float _c4 = buf.getFloat(index + 16);
        float _c5 = buf.getFloat(index + 20);
        float _c6 = buf.getFloat(index + 24);
        float _c7 = buf.getFloat(index + 28);
        float _c8 = buf.getFloat(index + 32);
        float _c9 = buf.getFloat(index + 36);
        return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
    public DoubleBuffer storeAbsolute(FloatTransform self, int index, DoubleBuffer buf) {
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
    public FloatTransform loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        float _c4 = (float) buf.get(index + 4);
        float _c5 = (float) buf.get(index + 5);
        float _c6 = (float) buf.get(index + 6);
        float _c7 = (float) buf.get(index + 7);
        float _c8 = (float) buf.get(index + 8);
        float _c9 = (float) buf.get(index + 9);
        return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
    public ByteBuffer storeDoubleAbsolute(FloatTransform self, int index, ByteBuffer buf) {
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
    public FloatTransform loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        float _c4 = (float) buf.getDouble(index + 32);
        float _c5 = (float) buf.getDouble(index + 40);
        float _c6 = (float) buf.getDouble(index + 48);
        float _c7 = (float) buf.getDouble(index + 56);
        float _c8 = (float) buf.getDouble(index + 64);
        float _c9 = (float) buf.getDouble(index + 72);
        return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
}
