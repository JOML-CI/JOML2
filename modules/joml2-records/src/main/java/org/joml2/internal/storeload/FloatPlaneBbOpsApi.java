// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatPlaneBbOpsApi extends FloatPlaneBbOps {
    public FloatBuffer storeAbsolute(FloatPlane self, int index, FloatBuffer buf) {
        buf.put(index, self.a());
        buf.put(index + 1, self.b());
        buf.put(index + 2, self.c());
        buf.put(index + 3, self.d());
        return buf;
    }
    public FloatPlane loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(FloatPlane self, int index, ByteBuffer buf) {
        buf.putFloat(index, self.a());
        buf.putFloat(index + 4, self.b());
        buf.putFloat(index + 8, self.c());
        buf.putFloat(index + 12, self.d());
        return buf;
    }
    public FloatPlane loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }
    public DoubleBuffer storeAbsolute(FloatPlane self, int index, DoubleBuffer buf) {
        buf.put(index, self.a());
        buf.put(index + 1, self.b());
        buf.put(index + 2, self.c());
        buf.put(index + 3, self.d());
        return buf;
    }
    public FloatPlane loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeDoubleAbsolute(FloatPlane self, int index, ByteBuffer buf) {
        buf.putDouble(index, self.a());
        buf.putDouble(index + 8, self.b());
        buf.putDouble(index + 16, self.c());
        buf.putDouble(index + 24, self.d());
        return buf;
    }
    public FloatPlane loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        return new FloatPlane(_c0, _c1, _c2, _c3);
    }
}
