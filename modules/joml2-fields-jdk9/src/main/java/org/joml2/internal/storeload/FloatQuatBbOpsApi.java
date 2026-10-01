// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatQuatBbOpsApi implements FloatQuatBbOps {
    public FloatBuffer storeAbsolute(FloatQuatImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        buf.put(index + 3, self.w);
        return buf;
    }
    public FloatQuat loadAbsolute(FloatQuatImpl self, int index, FloatBuffer buf) {
        self.x = buf.get(index + 0);
        self.y = buf.get(index + 1);
        self.z = buf.get(index + 2);
        self.w = buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatQuatImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.x);
        buf.putFloat(index + 4, self.y);
        buf.putFloat(index + 8, self.z);
        buf.putFloat(index + 12, self.w);
        return buf;
    }
    public FloatQuat loadAbsolute(FloatQuatImpl self, int index, ByteBuffer buf) {
        self.x = buf.getFloat(index + 0);
        self.y = buf.getFloat(index + 4);
        self.z = buf.getFloat(index + 8);
        self.w = buf.getFloat(index + 12);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatQuatImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        buf.put(index + 3, self.w);
        return buf;
    }
    public FloatQuat loadAbsolute(FloatQuatImpl self, int index, DoubleBuffer buf) {
        self.x = (float) buf.get(index + 0);
        self.y = (float) buf.get(index + 1);
        self.z = (float) buf.get(index + 2);
        self.w = (float) buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatQuatImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.x);
        buf.putDouble(index + 8, self.y);
        buf.putDouble(index + 16, self.z);
        buf.putDouble(index + 24, self.w);
        return buf;
    }
    public FloatQuat loadDoubleAbsolute(FloatQuatImpl self, int index, ByteBuffer buf) {
        self.x = (float) buf.getDouble(index + 0);
        self.y = (float) buf.getDouble(index + 8);
        self.z = (float) buf.getDouble(index + 16);
        self.w = (float) buf.getDouble(index + 24);
        return self;
    }
}
