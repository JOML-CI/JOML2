// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleRayBbOpsApi implements DoubleRayBbOps {
    public DoubleBuffer storeAbsolute(DoubleRay self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.oX());
        buf.put(index + 1, self.oY());
        buf.put(index + 2, self.oZ());
        buf.put(index + 3, self.dX());
        buf.put(index + 4, self.dY());
        buf.put(index + 5, self.dZ());
        return buf;
    }
    public DoubleRay loadAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeAbsolute(DoubleRay self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.oX());
        buf.putDouble(index + 8, self.oY());
        buf.putDouble(index + 16, self.oZ());
        buf.putDouble(index + 24, self.dX());
        buf.putDouble(index + 32, self.dY());
        buf.putDouble(index + 40, self.dZ());
        return buf;
    }
    public DoubleRay loadAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        double _c4 = buf.getDouble(index + 32);
        double _c5 = buf.getDouble(index + 40);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public FloatBuffer storeAbsolute(DoubleRay self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.oX());
        buf.put(index + 1, (float) self.oY());
        buf.put(index + 2, (float) self.oZ());
        buf.put(index + 3, (float) self.dX());
        buf.put(index + 4, (float) self.dY());
        buf.put(index + 5, (float) self.dZ());
        return buf;
    }
    public DoubleRay loadAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeFloatAbsolute(DoubleRay self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.oX());
        buf.putFloat(index + 4, (float) self.oY());
        buf.putFloat(index + 8, (float) self.oZ());
        buf.putFloat(index + 12, (float) self.dX());
        buf.putFloat(index + 16, (float) self.dY());
        buf.putFloat(index + 20, (float) self.dZ());
        return buf;
    }
    public DoubleRay loadFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        double _c4 = buf.getFloat(index + 16);
        double _c5 = buf.getFloat(index + 20);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
