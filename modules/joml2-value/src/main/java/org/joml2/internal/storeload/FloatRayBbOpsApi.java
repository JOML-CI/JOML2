// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatRayBbOpsApi implements FloatRayBbOps {
    public FloatBuffer storeAbsolute(FloatRay self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.oX());
        buf.put(index + 1, self.oY());
        buf.put(index + 2, self.oZ());
        buf.put(index + 3, self.dX());
        buf.put(index + 4, self.dY());
        buf.put(index + 5, self.dZ());
        return buf;
    }
    public FloatRay loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        float _c4 = buf.get(index + 4);
        float _c5 = buf.get(index + 5);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeAbsolute(FloatRay self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.oX());
        buf.putFloat(index + 4, self.oY());
        buf.putFloat(index + 8, self.oZ());
        buf.putFloat(index + 12, self.dX());
        buf.putFloat(index + 16, self.dY());
        buf.putFloat(index + 20, self.dZ());
        return buf;
    }
    public FloatRay loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        float _c4 = buf.getFloat(index + 16);
        float _c5 = buf.getFloat(index + 20);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public DoubleBuffer storeAbsolute(FloatRay self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.oX());
        buf.put(index + 1, self.oY());
        buf.put(index + 2, self.oZ());
        buf.put(index + 3, self.dX());
        buf.put(index + 4, self.dY());
        buf.put(index + 5, self.dZ());
        return buf;
    }
    public FloatRay loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        float _c4 = (float) buf.get(index + 4);
        float _c5 = (float) buf.get(index + 5);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeDoubleAbsolute(FloatRay self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.oX());
        buf.putDouble(index + 8, self.oY());
        buf.putDouble(index + 16, self.oZ());
        buf.putDouble(index + 24, self.dX());
        buf.putDouble(index + 32, self.dY());
        buf.putDouble(index + 40, self.dZ());
        return buf;
    }
    public FloatRay loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        float _c4 = (float) buf.getDouble(index + 32);
        float _c5 = (float) buf.getDouble(index + 40);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
