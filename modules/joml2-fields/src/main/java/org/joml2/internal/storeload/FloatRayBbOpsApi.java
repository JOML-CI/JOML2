// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatRayBbOpsApi implements FloatRayBbOps {
    public FloatBuffer storeAbsolute(FloatRayImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.oX);
        buf.put(index + 1, self.oY);
        buf.put(index + 2, self.oZ);
        buf.put(index + 3, self.dX);
        buf.put(index + 4, self.dY);
        buf.put(index + 5, self.dZ);
        return buf;
    }
    public FloatRay loadAbsolute(FloatRayImpl self, int index, FloatBuffer buf) {
        self.oX = buf.get(index + 0);
        self.oY = buf.get(index + 1);
        self.oZ = buf.get(index + 2);
        self.dX = buf.get(index + 3);
        self.dY = buf.get(index + 4);
        self.dZ = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.oX);
        buf.putFloat(index + 4, self.oY);
        buf.putFloat(index + 8, self.oZ);
        buf.putFloat(index + 12, self.dX);
        buf.putFloat(index + 16, self.dY);
        buf.putFloat(index + 20, self.dZ);
        return buf;
    }
    public FloatRay loadAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        self.oX = buf.getFloat(index + 0);
        self.oY = buf.getFloat(index + 4);
        self.oZ = buf.getFloat(index + 8);
        self.dX = buf.getFloat(index + 12);
        self.dY = buf.getFloat(index + 16);
        self.dZ = buf.getFloat(index + 20);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatRayImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.oX);
        buf.put(index + 1, self.oY);
        buf.put(index + 2, self.oZ);
        buf.put(index + 3, self.dX);
        buf.put(index + 4, self.dY);
        buf.put(index + 5, self.dZ);
        return buf;
    }
    public FloatRay loadAbsolute(FloatRayImpl self, int index, DoubleBuffer buf) {
        self.oX = (float) buf.get(index + 0);
        self.oY = (float) buf.get(index + 1);
        self.oZ = (float) buf.get(index + 2);
        self.dX = (float) buf.get(index + 3);
        self.dY = (float) buf.get(index + 4);
        self.dZ = (float) buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.oX);
        buf.putDouble(index + 8, self.oY);
        buf.putDouble(index + 16, self.oZ);
        buf.putDouble(index + 24, self.dX);
        buf.putDouble(index + 32, self.dY);
        buf.putDouble(index + 40, self.dZ);
        return buf;
    }
    public FloatRay loadDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        self.oX = (float) buf.getDouble(index + 0);
        self.oY = (float) buf.getDouble(index + 8);
        self.oZ = (float) buf.getDouble(index + 16);
        self.dX = (float) buf.getDouble(index + 24);
        self.dY = (float) buf.getDouble(index + 32);
        self.dZ = (float) buf.getDouble(index + 40);
        return self;
    }
}
