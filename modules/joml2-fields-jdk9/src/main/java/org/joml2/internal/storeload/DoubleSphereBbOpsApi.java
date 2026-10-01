// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleSphereBbOpsApi implements DoubleSphereBbOps {
    public DoubleBuffer storeAbsolute(DoubleSphereImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        buf.put(index + 3, self.r);
        return buf;
    }
    public DoubleSphere loadAbsolute(DoubleSphereImpl self, int index, DoubleBuffer buf) {
        self.x = buf.get(index + 0);
        self.y = buf.get(index + 1);
        self.z = buf.get(index + 2);
        self.r = buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.x);
        buf.putDouble(index + 8, self.y);
        buf.putDouble(index + 16, self.z);
        buf.putDouble(index + 24, self.r);
        return buf;
    }
    public DoubleSphere loadAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf) {
        self.x = buf.getDouble(index + 0);
        self.y = buf.getDouble(index + 8);
        self.z = buf.getDouble(index + 16);
        self.r = buf.getDouble(index + 24);
        return self;
    }
    public FloatBuffer storeAbsolute(DoubleSphereImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.x);
        buf.put(index + 1, (float) self.y);
        buf.put(index + 2, (float) self.z);
        buf.put(index + 3, (float) self.r);
        return buf;
    }
    public DoubleSphere loadAbsolute(DoubleSphereImpl self, int index, FloatBuffer buf) {
        self.x = buf.get(index + 0);
        self.y = buf.get(index + 1);
        self.z = buf.get(index + 2);
        self.r = buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeFloatAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.x);
        buf.putFloat(index + 4, (float) self.y);
        buf.putFloat(index + 8, (float) self.z);
        buf.putFloat(index + 12, (float) self.r);
        return buf;
    }
    public DoubleSphere loadFloatAbsolute(DoubleSphereImpl self, int index, ByteBuffer buf) {
        self.x = buf.getFloat(index + 0);
        self.y = buf.getFloat(index + 4);
        self.z = buf.getFloat(index + 8);
        self.r = buf.getFloat(index + 12);
        return self;
    }
}
