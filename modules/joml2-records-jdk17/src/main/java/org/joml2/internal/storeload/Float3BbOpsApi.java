// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float3BbOpsApi implements Float3BbOps {
    public FloatBuffer storeAbsolute(Float3 self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        return buf;
    }
    public Float3 loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        return new Float3(_c0, _c1, _c2);
    }
    public ByteBuffer storeAbsolute(Float3 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.x());
        buf.putFloat(index + 4, self.y());
        buf.putFloat(index + 8, self.z());
        return buf;
    }
    public Float3 loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        return new Float3(_c0, _c1, _c2);
    }
    public DoubleBuffer storeAbsolute(Float3 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        return buf;
    }
    public Float3 loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        return new Float3(_c0, _c1, _c2);
    }
    public ByteBuffer storeDoubleAbsolute(Float3 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.x());
        buf.putDouble(index + 8, self.y());
        buf.putDouble(index + 16, self.z());
        return buf;
    }
    public Float3 loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        return new Float3(_c0, _c1, _c2);
    }
}
