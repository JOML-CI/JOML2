// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleAABBBbOpsApi implements DoubleAABBBbOps {
    public DoubleBuffer storeAbsolute(DoubleAABBImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.minX);
        buf.put(index + 1, self.minY);
        buf.put(index + 2, self.minZ);
        buf.put(index + 3, self.maxX);
        buf.put(index + 4, self.maxY);
        buf.put(index + 5, self.maxZ);
        return buf;
    }
    public DoubleAABB loadAbsolute(DoubleAABBImpl self, int index, DoubleBuffer buf) {
        self.minX = buf.get(index + 0);
        self.minY = buf.get(index + 1);
        self.minZ = buf.get(index + 2);
        self.maxX = buf.get(index + 3);
        self.maxY = buf.get(index + 4);
        self.maxZ = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.minX);
        buf.putDouble(index + 8, self.minY);
        buf.putDouble(index + 16, self.minZ);
        buf.putDouble(index + 24, self.maxX);
        buf.putDouble(index + 32, self.maxY);
        buf.putDouble(index + 40, self.maxZ);
        return buf;
    }
    public DoubleAABB loadAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf) {
        self.minX = buf.getDouble(index + 0);
        self.minY = buf.getDouble(index + 8);
        self.minZ = buf.getDouble(index + 16);
        self.maxX = buf.getDouble(index + 24);
        self.maxY = buf.getDouble(index + 32);
        self.maxZ = buf.getDouble(index + 40);
        return self;
    }
    public FloatBuffer storeAbsolute(DoubleAABBImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.minX);
        buf.put(index + 1, (float) self.minY);
        buf.put(index + 2, (float) self.minZ);
        buf.put(index + 3, (float) self.maxX);
        buf.put(index + 4, (float) self.maxY);
        buf.put(index + 5, (float) self.maxZ);
        return buf;
    }
    public DoubleAABB loadAbsolute(DoubleAABBImpl self, int index, FloatBuffer buf) {
        self.minX = buf.get(index + 0);
        self.minY = buf.get(index + 1);
        self.minZ = buf.get(index + 2);
        self.maxX = buf.get(index + 3);
        self.maxY = buf.get(index + 4);
        self.maxZ = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeFloatAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.minX);
        buf.putFloat(index + 4, (float) self.minY);
        buf.putFloat(index + 8, (float) self.minZ);
        buf.putFloat(index + 12, (float) self.maxX);
        buf.putFloat(index + 16, (float) self.maxY);
        buf.putFloat(index + 20, (float) self.maxZ);
        return buf;
    }
    public DoubleAABB loadFloatAbsolute(DoubleAABBImpl self, int index, ByteBuffer buf) {
        self.minX = buf.getFloat(index + 0);
        self.minY = buf.getFloat(index + 4);
        self.minZ = buf.getFloat(index + 8);
        self.maxX = buf.getFloat(index + 12);
        self.maxY = buf.getFloat(index + 16);
        self.maxZ = buf.getFloat(index + 20);
        return self;
    }
}
