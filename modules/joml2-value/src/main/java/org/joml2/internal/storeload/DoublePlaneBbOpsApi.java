// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoublePlaneBbOpsApi implements DoublePlaneBbOps {
    public DoubleBuffer storeAbsolute(DoublePlane self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.a());
        buf.put(index + 1, self.b());
        buf.put(index + 2, self.c());
        buf.put(index + 3, self.d());
        return buf;
    }
    public DoublePlane loadAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(DoublePlane self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.a());
        buf.putDouble(index + 8, self.b());
        buf.putDouble(index + 16, self.c());
        buf.putDouble(index + 24, self.d());
        return buf;
    }
    public DoublePlane loadAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
    public FloatBuffer storeAbsolute(DoublePlane self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.a());
        buf.put(index + 1, (float) self.b());
        buf.put(index + 2, (float) self.c());
        buf.put(index + 3, (float) self.d());
        return buf;
    }
    public DoublePlane loadAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeFloatAbsolute(DoublePlane self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.a());
        buf.putFloat(index + 4, (float) self.b());
        buf.putFloat(index + 8, (float) self.c());
        buf.putFloat(index + 12, (float) self.d());
        return buf;
    }
    public DoublePlane loadFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        return new DoublePlane(_c0, _c1, _c2, _c3);
    }
}
