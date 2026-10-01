// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatRectBbOpsApi implements FloatRectBbOps {
    public FloatBuffer storeAbsolute(FloatRect self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.minX());
        buf.put(index + 1, self.minY());
        buf.put(index + 2, self.maxX());
        buf.put(index + 3, self.maxY());
        return buf;
    }
    public FloatRect loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        return new FloatRect(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(FloatRect self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.minX());
        buf.putFloat(index + 4, self.minY());
        buf.putFloat(index + 8, self.maxX());
        buf.putFloat(index + 12, self.maxY());
        return buf;
    }
    public FloatRect loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        return new FloatRect(_c0, _c1, _c2, _c3);
    }
    public DoubleBuffer storeAbsolute(FloatRect self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.minX());
        buf.put(index + 1, self.minY());
        buf.put(index + 2, self.maxX());
        buf.put(index + 3, self.maxY());
        return buf;
    }
    public FloatRect loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        return new FloatRect(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeDoubleAbsolute(FloatRect self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.minX());
        buf.putDouble(index + 8, self.minY());
        buf.putDouble(index + 16, self.maxX());
        buf.putDouble(index + 24, self.maxY());
        return buf;
    }
    public FloatRect loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        return new FloatRect(_c0, _c1, _c2, _c3);
    }
}
