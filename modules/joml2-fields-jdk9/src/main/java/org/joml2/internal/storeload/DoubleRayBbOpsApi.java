// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleRayBbOpsApi implements DoubleRayBbOps {
    public DoubleBuffer storeAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.oX);
        buf.put(index + 1, self.oY);
        buf.put(index + 2, self.oZ);
        buf.put(index + 3, self.dX);
        buf.put(index + 4, self.dY);
        buf.put(index + 5, self.dZ);
        return buf;
    }
    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf) {
        self.oX = buf.get(index + 0);
        self.oY = buf.get(index + 1);
        self.oZ = buf.get(index + 2);
        self.dX = buf.get(index + 3);
        self.dY = buf.get(index + 4);
        self.dZ = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.oX);
        buf.putDouble(index + 8, self.oY);
        buf.putDouble(index + 16, self.oZ);
        buf.putDouble(index + 24, self.dX);
        buf.putDouble(index + 32, self.dY);
        buf.putDouble(index + 40, self.dZ);
        return buf;
    }
    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        self.oX = buf.getDouble(index + 0);
        self.oY = buf.getDouble(index + 8);
        self.oZ = buf.getDouble(index + 16);
        self.dX = buf.getDouble(index + 24);
        self.dY = buf.getDouble(index + 32);
        self.dZ = buf.getDouble(index + 40);
        return self;
    }
    public FloatBuffer storeAbsolute(DoubleRayImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.oX);
        buf.put(index + 1, (float) self.oY);
        buf.put(index + 2, (float) self.oZ);
        buf.put(index + 3, (float) self.dX);
        buf.put(index + 4, (float) self.dY);
        buf.put(index + 5, (float) self.dZ);
        return buf;
    }
    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, FloatBuffer buf) {
        self.oX = buf.get(index + 0);
        self.oY = buf.get(index + 1);
        self.oZ = buf.get(index + 2);
        self.dX = buf.get(index + 3);
        self.dY = buf.get(index + 4);
        self.dZ = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.oX);
        buf.putFloat(index + 4, (float) self.oY);
        buf.putFloat(index + 8, (float) self.oZ);
        buf.putFloat(index + 12, (float) self.dX);
        buf.putFloat(index + 16, (float) self.dY);
        buf.putFloat(index + 20, (float) self.dZ);
        return buf;
    }
    public DoubleRay loadFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        self.oX = buf.getFloat(index + 0);
        self.oY = buf.getFloat(index + 4);
        self.oZ = buf.getFloat(index + 8);
        self.dX = buf.getFloat(index + 12);
        self.dY = buf.getFloat(index + 16);
        self.dZ = buf.getFloat(index + 20);
        return self;
    }
}
