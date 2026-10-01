// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoublePlaneBbOpsApi implements DoublePlaneBbOps {
    public DoubleBuffer storeAbsolute(DoublePlaneImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.a);
        buf.put(index + 1, self.b);
        buf.put(index + 2, self.c);
        buf.put(index + 3, self.d);
        return buf;
    }
    public DoublePlane loadAbsolute(DoublePlaneImpl self, int index, DoubleBuffer buf) {
        self.a = buf.get(index + 0);
        self.b = buf.get(index + 1);
        self.c = buf.get(index + 2);
        self.d = buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.a);
        buf.putDouble(index + 8, self.b);
        buf.putDouble(index + 16, self.c);
        buf.putDouble(index + 24, self.d);
        return buf;
    }
    public DoublePlane loadAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf) {
        self.a = buf.getDouble(index + 0);
        self.b = buf.getDouble(index + 8);
        self.c = buf.getDouble(index + 16);
        self.d = buf.getDouble(index + 24);
        return self;
    }
    public FloatBuffer storeAbsolute(DoublePlaneImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.a);
        buf.put(index + 1, (float) self.b);
        buf.put(index + 2, (float) self.c);
        buf.put(index + 3, (float) self.d);
        return buf;
    }
    public DoublePlane loadAbsolute(DoublePlaneImpl self, int index, FloatBuffer buf) {
        self.a = buf.get(index + 0);
        self.b = buf.get(index + 1);
        self.c = buf.get(index + 2);
        self.d = buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeFloatAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.a);
        buf.putFloat(index + 4, (float) self.b);
        buf.putFloat(index + 8, (float) self.c);
        buf.putFloat(index + 12, (float) self.d);
        return buf;
    }
    public DoublePlane loadFloatAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf) {
        self.a = buf.getFloat(index + 0);
        self.b = buf.getFloat(index + 4);
        self.c = buf.getFloat(index + 8);
        self.d = buf.getFloat(index + 12);
        return self;
    }
}
