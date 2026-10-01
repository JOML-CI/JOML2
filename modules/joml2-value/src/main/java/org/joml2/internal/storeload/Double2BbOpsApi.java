// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Double2BbOpsApi implements Double2BbOps {
    public DoubleBuffer storeAbsolute(Double2 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        return buf;
    }
    public Double2 loadAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        return new Double2(_c0, _c1);
    }
    public ByteBuffer storeAbsolute(Double2 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.x());
        buf.putDouble(index + 8, self.y());
        return buf;
    }
    public Double2 loadAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        return new Double2(_c0, _c1);
    }
    public FloatBuffer storeAbsolute(Double2 self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.x());
        buf.put(index + 1, (float) self.y());
        return buf;
    }
    public Double2 loadAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        return new Double2(_c0, _c1);
    }
    public ByteBuffer storeFloatAbsolute(Double2 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.x());
        buf.putFloat(index + 4, (float) self.y());
        return buf;
    }
    public Double2 loadFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        return new Double2(_c0, _c1);
    }
}
