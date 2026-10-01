// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Double3BbOpsApi implements Double3BbOps {
    public DoubleBuffer storeAbsolute(Double3 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        return buf;
    }
    public Double3 loadAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        return new Double3(_c0, _c1, _c2);
    }
    public ByteBuffer storeAbsolute(Double3 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.x());
        buf.putDouble(index + 8, self.y());
        buf.putDouble(index + 16, self.z());
        return buf;
    }
    public Double3 loadAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        return new Double3(_c0, _c1, _c2);
    }
    public FloatBuffer storeAbsolute(Double3 self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.x());
        buf.put(index + 1, (float) self.y());
        buf.put(index + 2, (float) self.z());
        return buf;
    }
    public Double3 loadAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        return new Double3(_c0, _c1, _c2);
    }
    public ByteBuffer storeFloatAbsolute(Double3 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.x());
        buf.putFloat(index + 4, (float) self.y());
        buf.putFloat(index + 8, (float) self.z());
        return buf;
    }
    public Double3 loadFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        return new Double3(_c0, _c1, _c2);
    }
}
