// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatQuatBbOpsApi implements FloatQuatBbOps {
    public FloatBuffer storeAbsolute(FloatQuat self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        buf.put(index + 3, self.w());
        return buf;
    }
    public FloatQuat loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        return new FloatQuat(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(FloatQuat self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.x());
        buf.putFloat(index + 4, self.y());
        buf.putFloat(index + 8, self.z());
        buf.putFloat(index + 12, self.w());
        return buf;
    }
    public FloatQuat loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        return new FloatQuat(_c0, _c1, _c2, _c3);
    }
    public DoubleBuffer storeAbsolute(FloatQuat self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        buf.put(index + 3, self.w());
        return buf;
    }
    public FloatQuat loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        return new FloatQuat(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeDoubleAbsolute(FloatQuat self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.x());
        buf.putDouble(index + 8, self.y());
        buf.putDouble(index + 16, self.z());
        buf.putDouble(index + 24, self.w());
        return buf;
    }
    public FloatQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        return new FloatQuat(_c0, _c1, _c2, _c3);
    }
}
