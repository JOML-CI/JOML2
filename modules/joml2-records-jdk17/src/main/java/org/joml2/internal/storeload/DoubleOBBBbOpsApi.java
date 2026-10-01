// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleOBBBbOpsApi implements DoubleOBBBbOps {
    public DoubleBuffer storeAbsolute(DoubleOBB self, int index, DoubleBuffer buf) {
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
    public DoubleOBB loadAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        double _c6 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        double _c8 = buf.get(index + 8);
        double _c9 = buf.get(index + 9);
        double _c10 = buf.get(index + 10);
        double _c11 = buf.get(index + 11);
        double _c12 = buf.get(index + 12);
        double _c13 = buf.get(index + 13);
        double _c14 = buf.get(index + 14);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public ByteBuffer storeAbsolute(DoubleOBB self, int index, ByteBuffer buf) {
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
    public DoubleOBB loadAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        double _c4 = buf.getDouble(index + 32);
        double _c5 = buf.getDouble(index + 40);
        double _c6 = buf.getDouble(index + 48);
        double _c7 = buf.getDouble(index + 56);
        double _c8 = buf.getDouble(index + 64);
        double _c9 = buf.getDouble(index + 72);
        double _c10 = buf.getDouble(index + 80);
        double _c11 = buf.getDouble(index + 88);
        double _c12 = buf.getDouble(index + 96);
        double _c13 = buf.getDouble(index + 104);
        double _c14 = buf.getDouble(index + 112);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public FloatBuffer storeAbsolute(DoubleOBB self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.cX());
        buf.put(index + 1, (float) self.cY());
        buf.put(index + 2, (float) self.cZ());
        buf.put(index + 3, (float) self.uXx());
        buf.put(index + 4, (float) self.uXy());
        buf.put(index + 5, (float) self.uXz());
        buf.put(index + 6, (float) self.uYx());
        buf.put(index + 7, (float) self.uYy());
        buf.put(index + 8, (float) self.uYz());
        buf.put(index + 9, (float) self.uZx());
        buf.put(index + 10, (float) self.uZy());
        buf.put(index + 11, (float) self.uZz());
        buf.put(index + 12, (float) self.hsX());
        buf.put(index + 13, (float) self.hsY());
        buf.put(index + 14, (float) self.hsZ());
        return buf;
    }
    public DoubleOBB loadAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        double _c6 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        double _c8 = buf.get(index + 8);
        double _c9 = buf.get(index + 9);
        double _c10 = buf.get(index + 10);
        double _c11 = buf.get(index + 11);
        double _c12 = buf.get(index + 12);
        double _c13 = buf.get(index + 13);
        double _c14 = buf.get(index + 14);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public ByteBuffer storeFloatAbsolute(DoubleOBB self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.cX());
        buf.putFloat(index + 4, (float) self.cY());
        buf.putFloat(index + 8, (float) self.cZ());
        buf.putFloat(index + 12, (float) self.uXx());
        buf.putFloat(index + 16, (float) self.uXy());
        buf.putFloat(index + 20, (float) self.uXz());
        buf.putFloat(index + 24, (float) self.uYx());
        buf.putFloat(index + 28, (float) self.uYy());
        buf.putFloat(index + 32, (float) self.uYz());
        buf.putFloat(index + 36, (float) self.uZx());
        buf.putFloat(index + 40, (float) self.uZy());
        buf.putFloat(index + 44, (float) self.uZz());
        buf.putFloat(index + 48, (float) self.hsX());
        buf.putFloat(index + 52, (float) self.hsY());
        buf.putFloat(index + 56, (float) self.hsZ());
        return buf;
    }
    public DoubleOBB loadFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        double _c4 = buf.getFloat(index + 16);
        double _c5 = buf.getFloat(index + 20);
        double _c6 = buf.getFloat(index + 24);
        double _c7 = buf.getFloat(index + 28);
        double _c8 = buf.getFloat(index + 32);
        double _c9 = buf.getFloat(index + 36);
        double _c10 = buf.getFloat(index + 40);
        double _c11 = buf.getFloat(index + 44);
        double _c12 = buf.getFloat(index + 48);
        double _c13 = buf.getFloat(index + 52);
        double _c14 = buf.getFloat(index + 56);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
}
