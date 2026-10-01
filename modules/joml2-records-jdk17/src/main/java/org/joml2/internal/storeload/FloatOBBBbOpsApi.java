// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatOBBBbOpsApi implements FloatOBBBbOps {
    public FloatBuffer storeAbsolute(FloatOBB self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.cX());
        buf.put(index + 1, self.cY());
        buf.put(index + 2, self.cZ());
        buf.put(index + 3, self.uXx());
        buf.put(index + 4, self.uXy());
        buf.put(index + 5, self.uXz());
        buf.put(index + 6, self.uYx());
        buf.put(index + 7, self.uYy());
        buf.put(index + 8, self.uYz());
        buf.put(index + 9, self.uZx());
        buf.put(index + 10, self.uZy());
        buf.put(index + 11, self.uZz());
        buf.put(index + 12, self.hsX());
        buf.put(index + 13, self.hsY());
        buf.put(index + 14, self.hsZ());
        return buf;
    }
    public FloatOBB loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        float _c4 = buf.get(index + 4);
        float _c5 = buf.get(index + 5);
        float _c6 = buf.get(index + 6);
        float _c7 = buf.get(index + 7);
        float _c8 = buf.get(index + 8);
        float _c9 = buf.get(index + 9);
        float _c10 = buf.get(index + 10);
        float _c11 = buf.get(index + 11);
        float _c12 = buf.get(index + 12);
        float _c13 = buf.get(index + 13);
        float _c14 = buf.get(index + 14);
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public ByteBuffer storeAbsolute(FloatOBB self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.cX());
        buf.putFloat(index + 4, self.cY());
        buf.putFloat(index + 8, self.cZ());
        buf.putFloat(index + 12, self.uXx());
        buf.putFloat(index + 16, self.uXy());
        buf.putFloat(index + 20, self.uXz());
        buf.putFloat(index + 24, self.uYx());
        buf.putFloat(index + 28, self.uYy());
        buf.putFloat(index + 32, self.uYz());
        buf.putFloat(index + 36, self.uZx());
        buf.putFloat(index + 40, self.uZy());
        buf.putFloat(index + 44, self.uZz());
        buf.putFloat(index + 48, self.hsX());
        buf.putFloat(index + 52, self.hsY());
        buf.putFloat(index + 56, self.hsZ());
        return buf;
    }
    public FloatOBB loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        float _c4 = buf.getFloat(index + 16);
        float _c5 = buf.getFloat(index + 20);
        float _c6 = buf.getFloat(index + 24);
        float _c7 = buf.getFloat(index + 28);
        float _c8 = buf.getFloat(index + 32);
        float _c9 = buf.getFloat(index + 36);
        float _c10 = buf.getFloat(index + 40);
        float _c11 = buf.getFloat(index + 44);
        float _c12 = buf.getFloat(index + 48);
        float _c13 = buf.getFloat(index + 52);
        float _c14 = buf.getFloat(index + 56);
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public DoubleBuffer storeAbsolute(FloatOBB self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.cX());
        buf.put(index + 1, self.cY());
        buf.put(index + 2, self.cZ());
        buf.put(index + 3, self.uXx());
        buf.put(index + 4, self.uXy());
        buf.put(index + 5, self.uXz());
        buf.put(index + 6, self.uYx());
        buf.put(index + 7, self.uYy());
        buf.put(index + 8, self.uYz());
        buf.put(index + 9, self.uZx());
        buf.put(index + 10, self.uZy());
        buf.put(index + 11, self.uZz());
        buf.put(index + 12, self.hsX());
        buf.put(index + 13, self.hsY());
        buf.put(index + 14, self.hsZ());
        return buf;
    }
    public FloatOBB loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        float _c4 = (float) buf.get(index + 4);
        float _c5 = (float) buf.get(index + 5);
        float _c6 = (float) buf.get(index + 6);
        float _c7 = (float) buf.get(index + 7);
        float _c8 = (float) buf.get(index + 8);
        float _c9 = (float) buf.get(index + 9);
        float _c10 = (float) buf.get(index + 10);
        float _c11 = (float) buf.get(index + 11);
        float _c12 = (float) buf.get(index + 12);
        float _c13 = (float) buf.get(index + 13);
        float _c14 = (float) buf.get(index + 14);
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public ByteBuffer storeDoubleAbsolute(FloatOBB self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.cX());
        buf.putDouble(index + 8, self.cY());
        buf.putDouble(index + 16, self.cZ());
        buf.putDouble(index + 24, self.uXx());
        buf.putDouble(index + 32, self.uXy());
        buf.putDouble(index + 40, self.uXz());
        buf.putDouble(index + 48, self.uYx());
        buf.putDouble(index + 56, self.uYy());
        buf.putDouble(index + 64, self.uYz());
        buf.putDouble(index + 72, self.uZx());
        buf.putDouble(index + 80, self.uZy());
        buf.putDouble(index + 88, self.uZz());
        buf.putDouble(index + 96, self.hsX());
        buf.putDouble(index + 104, self.hsY());
        buf.putDouble(index + 112, self.hsZ());
        return buf;
    }
    public FloatOBB loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        float _c4 = (float) buf.getDouble(index + 32);
        float _c5 = (float) buf.getDouble(index + 40);
        float _c6 = (float) buf.getDouble(index + 48);
        float _c7 = (float) buf.getDouble(index + 56);
        float _c8 = (float) buf.getDouble(index + 64);
        float _c9 = (float) buf.getDouble(index + 72);
        float _c10 = (float) buf.getDouble(index + 80);
        float _c11 = (float) buf.getDouble(index + 88);
        float _c12 = (float) buf.getDouble(index + 96);
        float _c13 = (float) buf.getDouble(index + 104);
        float _c14 = (float) buf.getDouble(index + 112);
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
}
