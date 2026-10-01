// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float2BbOpsApi implements Float2BbOps {
    public FloatBuffer storeAbsolute(Float2 self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        return buf;
    }
    public Float2 loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        return new Float2(_c0, _c1);
    }
    public ByteBuffer storeAbsolute(Float2 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.x());
        buf.putFloat(index + 4, self.y());
        return buf;
    }
    public Float2 loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        return new Float2(_c0, _c1);
    }
    public DoubleBuffer storeAbsolute(Float2 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        return buf;
    }
    public Float2 loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        return new Float2(_c0, _c1);
    }
    public ByteBuffer storeDoubleAbsolute(Float2 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.x());
        buf.putDouble(index + 8, self.y());
        return buf;
    }
    public Float2 loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        return new Float2(_c0, _c1);
    }
}
