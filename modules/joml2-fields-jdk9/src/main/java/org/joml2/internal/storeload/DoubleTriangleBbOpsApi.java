// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleTriangleBbOpsApi implements DoubleTriangleBbOps {
    public DoubleBuffer storeAbsolute(DoubleTriangleImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.v0X);
        buf.put(index + 1, self.v0Y);
        buf.put(index + 2, self.v0Z);
        buf.put(index + 3, self.v1X);
        buf.put(index + 4, self.v1Y);
        buf.put(index + 5, self.v1Z);
        buf.put(index + 6, self.v2X);
        buf.put(index + 7, self.v2Y);
        buf.put(index + 8, self.v2Z);
        return buf;
    }
    public DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, DoubleBuffer buf) {
        self.v0X = buf.get(index + 0);
        self.v0Y = buf.get(index + 1);
        self.v0Z = buf.get(index + 2);
        self.v1X = buf.get(index + 3);
        self.v1Y = buf.get(index + 4);
        self.v1Z = buf.get(index + 5);
        self.v2X = buf.get(index + 6);
        self.v2Y = buf.get(index + 7);
        self.v2Z = buf.get(index + 8);
        return self;
    }
    public ByteBuffer storeAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.v0X);
        buf.putDouble(index + 8, self.v0Y);
        buf.putDouble(index + 16, self.v0Z);
        buf.putDouble(index + 24, self.v1X);
        buf.putDouble(index + 32, self.v1Y);
        buf.putDouble(index + 40, self.v1Z);
        buf.putDouble(index + 48, self.v2X);
        buf.putDouble(index + 56, self.v2Y);
        buf.putDouble(index + 64, self.v2Z);
        return buf;
    }
    public DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        self.v0X = buf.getDouble(index + 0);
        self.v0Y = buf.getDouble(index + 8);
        self.v0Z = buf.getDouble(index + 16);
        self.v1X = buf.getDouble(index + 24);
        self.v1Y = buf.getDouble(index + 32);
        self.v1Z = buf.getDouble(index + 40);
        self.v2X = buf.getDouble(index + 48);
        self.v2Y = buf.getDouble(index + 56);
        self.v2Z = buf.getDouble(index + 64);
        return self;
    }
    public FloatBuffer storeAbsolute(DoubleTriangleImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.v0X);
        buf.put(index + 1, (float) self.v0Y);
        buf.put(index + 2, (float) self.v0Z);
        buf.put(index + 3, (float) self.v1X);
        buf.put(index + 4, (float) self.v1Y);
        buf.put(index + 5, (float) self.v1Z);
        buf.put(index + 6, (float) self.v2X);
        buf.put(index + 7, (float) self.v2Y);
        buf.put(index + 8, (float) self.v2Z);
        return buf;
    }
    public DoubleTriangle loadAbsolute(DoubleTriangleImpl self, int index, FloatBuffer buf) {
        self.v0X = buf.get(index + 0);
        self.v0Y = buf.get(index + 1);
        self.v0Z = buf.get(index + 2);
        self.v1X = buf.get(index + 3);
        self.v1Y = buf.get(index + 4);
        self.v1Z = buf.get(index + 5);
        self.v2X = buf.get(index + 6);
        self.v2Y = buf.get(index + 7);
        self.v2Z = buf.get(index + 8);
        return self;
    }
    public ByteBuffer storeFloatAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.v0X);
        buf.putFloat(index + 4, (float) self.v0Y);
        buf.putFloat(index + 8, (float) self.v0Z);
        buf.putFloat(index + 12, (float) self.v1X);
        buf.putFloat(index + 16, (float) self.v1Y);
        buf.putFloat(index + 20, (float) self.v1Z);
        buf.putFloat(index + 24, (float) self.v2X);
        buf.putFloat(index + 28, (float) self.v2Y);
        buf.putFloat(index + 32, (float) self.v2Z);
        return buf;
    }
    public DoubleTriangle loadFloatAbsolute(DoubleTriangleImpl self, int index, ByteBuffer buf) {
        self.v0X = buf.getFloat(index + 0);
        self.v0Y = buf.getFloat(index + 4);
        self.v0Z = buf.getFloat(index + 8);
        self.v1X = buf.getFloat(index + 12);
        self.v1Y = buf.getFloat(index + 16);
        self.v1Z = buf.getFloat(index + 20);
        self.v2X = buf.getFloat(index + 24);
        self.v2Y = buf.getFloat(index + 28);
        self.v2Z = buf.getFloat(index + 32);
        return self;
    }
}
