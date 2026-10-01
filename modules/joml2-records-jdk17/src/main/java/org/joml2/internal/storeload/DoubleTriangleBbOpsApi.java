// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleTriangleBbOpsApi implements DoubleTriangleBbOps {
    public DoubleBuffer storeAbsolute(DoubleTriangle self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.v0X());
        buf.put(index + 1, self.v0Y());
        buf.put(index + 2, self.v0Z());
        buf.put(index + 3, self.v1X());
        buf.put(index + 4, self.v1Y());
        buf.put(index + 5, self.v1Z());
        buf.put(index + 6, self.v2X());
        buf.put(index + 7, self.v2Y());
        buf.put(index + 8, self.v2Z());
        return buf;
    }
    public DoubleTriangle loadAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        double _c6 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        double _c8 = buf.get(index + 8);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeAbsolute(DoubleTriangle self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.v0X());
        buf.putDouble(index + 8, self.v0Y());
        buf.putDouble(index + 16, self.v0Z());
        buf.putDouble(index + 24, self.v1X());
        buf.putDouble(index + 32, self.v1Y());
        buf.putDouble(index + 40, self.v1Z());
        buf.putDouble(index + 48, self.v2X());
        buf.putDouble(index + 56, self.v2Y());
        buf.putDouble(index + 64, self.v2Z());
        return buf;
    }
    public DoubleTriangle loadAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        double _c4 = buf.getDouble(index + 32);
        double _c5 = buf.getDouble(index + 40);
        double _c6 = buf.getDouble(index + 48);
        double _c7 = buf.getDouble(index + 56);
        double _c8 = buf.getDouble(index + 64);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public FloatBuffer storeAbsolute(DoubleTriangle self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.v0X());
        buf.put(index + 1, (float) self.v0Y());
        buf.put(index + 2, (float) self.v0Z());
        buf.put(index + 3, (float) self.v1X());
        buf.put(index + 4, (float) self.v1Y());
        buf.put(index + 5, (float) self.v1Z());
        buf.put(index + 6, (float) self.v2X());
        buf.put(index + 7, (float) self.v2Y());
        buf.put(index + 8, (float) self.v2Z());
        return buf;
    }
    public DoubleTriangle loadAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        double _c6 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        double _c8 = buf.get(index + 8);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
    public ByteBuffer storeFloatAbsolute(DoubleTriangle self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.v0X());
        buf.putFloat(index + 4, (float) self.v0Y());
        buf.putFloat(index + 8, (float) self.v0Z());
        buf.putFloat(index + 12, (float) self.v1X());
        buf.putFloat(index + 16, (float) self.v1Y());
        buf.putFloat(index + 20, (float) self.v1Z());
        buf.putFloat(index + 24, (float) self.v2X());
        buf.putFloat(index + 28, (float) self.v2Y());
        buf.putFloat(index + 32, (float) self.v2Z());
        return buf;
    }
    public DoubleTriangle loadFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        double _c4 = buf.getFloat(index + 16);
        double _c5 = buf.getFloat(index + 20);
        double _c6 = buf.getFloat(index + 24);
        double _c7 = buf.getFloat(index + 28);
        double _c8 = buf.getFloat(index + 32);
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }
}
