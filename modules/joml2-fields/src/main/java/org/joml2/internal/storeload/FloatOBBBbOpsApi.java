// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatOBBBbOpsApi implements FloatOBBBbOps {
    public FloatBuffer storeAbsolute(FloatOBBImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.cX);
        buf.put(index + 1, self.cY);
        buf.put(index + 2, self.cZ);
        buf.put(index + 3, self.uXx);
        buf.put(index + 4, self.uXy);
        buf.put(index + 5, self.uXz);
        buf.put(index + 6, self.uYx);
        buf.put(index + 7, self.uYy);
        buf.put(index + 8, self.uYz);
        buf.put(index + 9, self.uZx);
        buf.put(index + 10, self.uZy);
        buf.put(index + 11, self.uZz);
        buf.put(index + 12, self.hsX);
        buf.put(index + 13, self.hsY);
        buf.put(index + 14, self.hsZ);
        return buf;
    }
    public FloatOBB loadAbsolute(FloatOBBImpl self, int index, FloatBuffer buf) {
        self.cX = buf.get(index + 0);
        self.cY = buf.get(index + 1);
        self.cZ = buf.get(index + 2);
        self.uXx = buf.get(index + 3);
        self.uXy = buf.get(index + 4);
        self.uXz = buf.get(index + 5);
        self.uYx = buf.get(index + 6);
        self.uYy = buf.get(index + 7);
        self.uYz = buf.get(index + 8);
        self.uZx = buf.get(index + 9);
        self.uZy = buf.get(index + 10);
        self.uZz = buf.get(index + 11);
        self.hsX = buf.get(index + 12);
        self.hsY = buf.get(index + 13);
        self.hsZ = buf.get(index + 14);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.cX);
        buf.putFloat(index + 4, self.cY);
        buf.putFloat(index + 8, self.cZ);
        buf.putFloat(index + 12, self.uXx);
        buf.putFloat(index + 16, self.uXy);
        buf.putFloat(index + 20, self.uXz);
        buf.putFloat(index + 24, self.uYx);
        buf.putFloat(index + 28, self.uYy);
        buf.putFloat(index + 32, self.uYz);
        buf.putFloat(index + 36, self.uZx);
        buf.putFloat(index + 40, self.uZy);
        buf.putFloat(index + 44, self.uZz);
        buf.putFloat(index + 48, self.hsX);
        buf.putFloat(index + 52, self.hsY);
        buf.putFloat(index + 56, self.hsZ);
        return buf;
    }
    public FloatOBB loadAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        self.cX = buf.getFloat(index + 0);
        self.cY = buf.getFloat(index + 4);
        self.cZ = buf.getFloat(index + 8);
        self.uXx = buf.getFloat(index + 12);
        self.uXy = buf.getFloat(index + 16);
        self.uXz = buf.getFloat(index + 20);
        self.uYx = buf.getFloat(index + 24);
        self.uYy = buf.getFloat(index + 28);
        self.uYz = buf.getFloat(index + 32);
        self.uZx = buf.getFloat(index + 36);
        self.uZy = buf.getFloat(index + 40);
        self.uZz = buf.getFloat(index + 44);
        self.hsX = buf.getFloat(index + 48);
        self.hsY = buf.getFloat(index + 52);
        self.hsZ = buf.getFloat(index + 56);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatOBBImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.cX);
        buf.put(index + 1, self.cY);
        buf.put(index + 2, self.cZ);
        buf.put(index + 3, self.uXx);
        buf.put(index + 4, self.uXy);
        buf.put(index + 5, self.uXz);
        buf.put(index + 6, self.uYx);
        buf.put(index + 7, self.uYy);
        buf.put(index + 8, self.uYz);
        buf.put(index + 9, self.uZx);
        buf.put(index + 10, self.uZy);
        buf.put(index + 11, self.uZz);
        buf.put(index + 12, self.hsX);
        buf.put(index + 13, self.hsY);
        buf.put(index + 14, self.hsZ);
        return buf;
    }
    public FloatOBB loadAbsolute(FloatOBBImpl self, int index, DoubleBuffer buf) {
        self.cX = (float) buf.get(index + 0);
        self.cY = (float) buf.get(index + 1);
        self.cZ = (float) buf.get(index + 2);
        self.uXx = (float) buf.get(index + 3);
        self.uXy = (float) buf.get(index + 4);
        self.uXz = (float) buf.get(index + 5);
        self.uYx = (float) buf.get(index + 6);
        self.uYy = (float) buf.get(index + 7);
        self.uYz = (float) buf.get(index + 8);
        self.uZx = (float) buf.get(index + 9);
        self.uZy = (float) buf.get(index + 10);
        self.uZz = (float) buf.get(index + 11);
        self.hsX = (float) buf.get(index + 12);
        self.hsY = (float) buf.get(index + 13);
        self.hsZ = (float) buf.get(index + 14);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.cX);
        buf.putDouble(index + 8, self.cY);
        buf.putDouble(index + 16, self.cZ);
        buf.putDouble(index + 24, self.uXx);
        buf.putDouble(index + 32, self.uXy);
        buf.putDouble(index + 40, self.uXz);
        buf.putDouble(index + 48, self.uYx);
        buf.putDouble(index + 56, self.uYy);
        buf.putDouble(index + 64, self.uYz);
        buf.putDouble(index + 72, self.uZx);
        buf.putDouble(index + 80, self.uZy);
        buf.putDouble(index + 88, self.uZz);
        buf.putDouble(index + 96, self.hsX);
        buf.putDouble(index + 104, self.hsY);
        buf.putDouble(index + 112, self.hsZ);
        return buf;
    }
    public FloatOBB loadDoubleAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        self.cX = (float) buf.getDouble(index + 0);
        self.cY = (float) buf.getDouble(index + 8);
        self.cZ = (float) buf.getDouble(index + 16);
        self.uXx = (float) buf.getDouble(index + 24);
        self.uXy = (float) buf.getDouble(index + 32);
        self.uXz = (float) buf.getDouble(index + 40);
        self.uYx = (float) buf.getDouble(index + 48);
        self.uYy = (float) buf.getDouble(index + 56);
        self.uYz = (float) buf.getDouble(index + 64);
        self.uZx = (float) buf.getDouble(index + 72);
        self.uZy = (float) buf.getDouble(index + 80);
        self.uZz = (float) buf.getDouble(index + 88);
        self.hsX = (float) buf.getDouble(index + 96);
        self.hsY = (float) buf.getDouble(index + 104);
        self.hsZ = (float) buf.getDouble(index + 112);
        return self;
    }
}
