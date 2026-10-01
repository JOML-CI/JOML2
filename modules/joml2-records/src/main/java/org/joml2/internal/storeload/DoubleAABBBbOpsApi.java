// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleAABBBbOpsApi implements DoubleAABBBbOps {
    public DoubleBuffer storeAbsolute(DoubleAABB self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.minX());
        buf.put(index + 1, self.minY());
        buf.put(index + 2, self.minZ());
        buf.put(index + 3, self.maxX());
        buf.put(index + 4, self.maxY());
        buf.put(index + 5, self.maxZ());
        return buf;
    }
    public DoubleAABB loadAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeAbsolute(DoubleAABB self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.minX());
        buf.putDouble(index + 8, self.minY());
        buf.putDouble(index + 16, self.minZ());
        buf.putDouble(index + 24, self.maxX());
        buf.putDouble(index + 32, self.maxY());
        buf.putDouble(index + 40, self.maxZ());
        return buf;
    }
    public DoubleAABB loadAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        double _c4 = buf.getDouble(index + 32);
        double _c5 = buf.getDouble(index + 40);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public FloatBuffer storeAbsolute(DoubleAABB self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.minX());
        buf.put(index + 1, (float) self.minY());
        buf.put(index + 2, (float) self.minZ());
        buf.put(index + 3, (float) self.maxX());
        buf.put(index + 4, (float) self.maxY());
        buf.put(index + 5, (float) self.maxZ());
        return buf;
    }
    public DoubleAABB loadAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeFloatAbsolute(DoubleAABB self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.minX());
        buf.putFloat(index + 4, (float) self.minY());
        buf.putFloat(index + 8, (float) self.minZ());
        buf.putFloat(index + 12, (float) self.maxX());
        buf.putFloat(index + 16, (float) self.maxY());
        buf.putFloat(index + 20, (float) self.maxZ());
        return buf;
    }
    public DoubleAABB loadFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        double _c4 = buf.getFloat(index + 16);
        double _c5 = buf.getFloat(index + 20);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
