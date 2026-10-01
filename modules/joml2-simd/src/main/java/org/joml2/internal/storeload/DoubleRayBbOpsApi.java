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
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(index + 4, self.data[4]);
        buf.put(index + 5, self.data[5]);
        return buf;
    }
    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, DoubleBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        self.data[4] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(index + 16, self.data[2]);
        buf.putDouble(index + 24, self.data[3]);
        buf.putDouble(index + 32, self.data[4]);
        buf.putDouble(index + 40, self.data[5]);
        return buf;
    }
    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getDouble(index + 0);
        self.data[1] = buf.getDouble(index + 8);
        self.data[2] = buf.getDouble(index + 16);
        self.data[3] = buf.getDouble(index + 24);
        self.data[4] = buf.getDouble(index + 32);
        self.data[5] = buf.getDouble(index + 40);
        return self;
    }
    public FloatBuffer storeAbsolute(DoubleRayImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[1]);
        buf.put(index + 2, (float) self.data[2]);
        buf.put(index + 3, (float) self.data[3]);
        buf.put(index + 4, (float) self.data[4]);
        buf.put(index + 5, (float) self.data[5]);
        return buf;
    }
    public DoubleRay loadAbsolute(DoubleRayImpl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        self.data[4] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[1]);
        buf.putFloat(index + 8, (float) self.data[2]);
        buf.putFloat(index + 12, (float) self.data[3]);
        buf.putFloat(index + 16, (float) self.data[4]);
        buf.putFloat(index + 20, (float) self.data[5]);
        return buf;
    }
    public DoubleRay loadFloatAbsolute(DoubleRayImpl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[1] = buf.getFloat(index + 4);
        self.data[2] = buf.getFloat(index + 8);
        self.data[3] = buf.getFloat(index + 12);
        self.data[4] = buf.getFloat(index + 16);
        self.data[5] = buf.getFloat(index + 20);
        return self;
    }
}
