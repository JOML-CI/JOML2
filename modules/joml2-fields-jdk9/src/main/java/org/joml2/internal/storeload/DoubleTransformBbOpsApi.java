// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleTransformBbOpsApi implements DoubleTransformBbOps {
    public DoubleBuffer storeAbsolute(DoubleTransformImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.tX);
        buf.put(index + 1, self.tY);
        buf.put(index + 2, self.tZ);
        buf.put(index + 3, self.rX);
        buf.put(index + 4, self.rY);
        buf.put(index + 5, self.rZ);
        buf.put(index + 6, self.rW);
        buf.put(index + 7, self.sX);
        buf.put(index + 8, self.sY);
        buf.put(index + 9, self.sZ);
        return buf;
    }
    public DoubleTransform loadAbsolute(DoubleTransformImpl self, int index, DoubleBuffer buf) {
        self.tX = buf.get(index + 0);
        self.tY = buf.get(index + 1);
        self.tZ = buf.get(index + 2);
        self.rX = buf.get(index + 3);
        self.rY = buf.get(index + 4);
        self.rZ = buf.get(index + 5);
        self.rW = buf.get(index + 6);
        self.sX = buf.get(index + 7);
        self.sY = buf.get(index + 8);
        self.sZ = buf.get(index + 9);
        return self;
    }
    public ByteBuffer storeAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.tX);
        buf.putDouble(index + 8, self.tY);
        buf.putDouble(index + 16, self.tZ);
        buf.putDouble(index + 24, self.rX);
        buf.putDouble(index + 32, self.rY);
        buf.putDouble(index + 40, self.rZ);
        buf.putDouble(index + 48, self.rW);
        buf.putDouble(index + 56, self.sX);
        buf.putDouble(index + 64, self.sY);
        buf.putDouble(index + 72, self.sZ);
        return buf;
    }
    public DoubleTransform loadAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf) {
        self.tX = buf.getDouble(index + 0);
        self.tY = buf.getDouble(index + 8);
        self.tZ = buf.getDouble(index + 16);
        self.rX = buf.getDouble(index + 24);
        self.rY = buf.getDouble(index + 32);
        self.rZ = buf.getDouble(index + 40);
        self.rW = buf.getDouble(index + 48);
        self.sX = buf.getDouble(index + 56);
        self.sY = buf.getDouble(index + 64);
        self.sZ = buf.getDouble(index + 72);
        return self;
    }
    public FloatBuffer storeAbsolute(DoubleTransformImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.tX);
        buf.put(index + 1, (float) self.tY);
        buf.put(index + 2, (float) self.tZ);
        buf.put(index + 3, (float) self.rX);
        buf.put(index + 4, (float) self.rY);
        buf.put(index + 5, (float) self.rZ);
        buf.put(index + 6, (float) self.rW);
        buf.put(index + 7, (float) self.sX);
        buf.put(index + 8, (float) self.sY);
        buf.put(index + 9, (float) self.sZ);
        return buf;
    }
    public DoubleTransform loadAbsolute(DoubleTransformImpl self, int index, FloatBuffer buf) {
        self.tX = buf.get(index + 0);
        self.tY = buf.get(index + 1);
        self.tZ = buf.get(index + 2);
        self.rX = buf.get(index + 3);
        self.rY = buf.get(index + 4);
        self.rZ = buf.get(index + 5);
        self.rW = buf.get(index + 6);
        self.sX = buf.get(index + 7);
        self.sY = buf.get(index + 8);
        self.sZ = buf.get(index + 9);
        return self;
    }
    public ByteBuffer storeFloatAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.tX);
        buf.putFloat(index + 4, (float) self.tY);
        buf.putFloat(index + 8, (float) self.tZ);
        buf.putFloat(index + 12, (float) self.rX);
        buf.putFloat(index + 16, (float) self.rY);
        buf.putFloat(index + 20, (float) self.rZ);
        buf.putFloat(index + 24, (float) self.rW);
        buf.putFloat(index + 28, (float) self.sX);
        buf.putFloat(index + 32, (float) self.sY);
        buf.putFloat(index + 36, (float) self.sZ);
        return buf;
    }
    public DoubleTransform loadFloatAbsolute(DoubleTransformImpl self, int index, ByteBuffer buf) {
        self.tX = buf.getFloat(index + 0);
        self.tY = buf.getFloat(index + 4);
        self.tZ = buf.getFloat(index + 8);
        self.rX = buf.getFloat(index + 12);
        self.rY = buf.getFloat(index + 16);
        self.rZ = buf.getFloat(index + 20);
        self.rW = buf.getFloat(index + 24);
        self.sX = buf.getFloat(index + 28);
        self.sY = buf.getFloat(index + 32);
        self.sZ = buf.getFloat(index + 36);
        return self;
    }
}
