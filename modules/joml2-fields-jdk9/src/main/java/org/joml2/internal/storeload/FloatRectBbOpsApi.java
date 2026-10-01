// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatRectBbOpsApi implements FloatRectBbOps {
    public FloatBuffer storeAbsolute(FloatRectImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.minX);
        buf.put(index + 1, self.minY);
        buf.put(index + 2, self.maxX);
        buf.put(index + 3, self.maxY);
        return buf;
    }
    public FloatRect loadAbsolute(FloatRectImpl self, int index, FloatBuffer buf) {
        self.minX = buf.get(index + 0);
        self.minY = buf.get(index + 1);
        self.maxX = buf.get(index + 2);
        self.maxY = buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatRectImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.minX);
        buf.putFloat(index + 4, self.minY);
        buf.putFloat(index + 8, self.maxX);
        buf.putFloat(index + 12, self.maxY);
        return buf;
    }
    public FloatRect loadAbsolute(FloatRectImpl self, int index, ByteBuffer buf) {
        self.minX = buf.getFloat(index + 0);
        self.minY = buf.getFloat(index + 4);
        self.maxX = buf.getFloat(index + 8);
        self.maxY = buf.getFloat(index + 12);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatRectImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.minX);
        buf.put(index + 1, self.minY);
        buf.put(index + 2, self.maxX);
        buf.put(index + 3, self.maxY);
        return buf;
    }
    public FloatRect loadAbsolute(FloatRectImpl self, int index, DoubleBuffer buf) {
        self.minX = (float) buf.get(index + 0);
        self.minY = (float) buf.get(index + 1);
        self.maxX = (float) buf.get(index + 2);
        self.maxY = (float) buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatRectImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.minX);
        buf.putDouble(index + 8, self.minY);
        buf.putDouble(index + 16, self.maxX);
        buf.putDouble(index + 24, self.maxY);
        return buf;
    }
    public FloatRect loadDoubleAbsolute(FloatRectImpl self, int index, ByteBuffer buf) {
        self.minX = (float) buf.getDouble(index + 0);
        self.minY = (float) buf.getDouble(index + 8);
        self.maxX = (float) buf.getDouble(index + 16);
        self.maxY = (float) buf.getDouble(index + 24);
        return self;
    }
}
