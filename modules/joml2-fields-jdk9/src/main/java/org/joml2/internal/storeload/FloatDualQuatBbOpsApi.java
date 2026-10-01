// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatDualQuatBbOpsApi implements FloatDualQuatBbOps {
    public FloatBuffer storeAbsolute(FloatDualQuatImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.rX);
        buf.put(index + 1, self.rY);
        buf.put(index + 2, self.rZ);
        buf.put(index + 3, self.rW);
        buf.put(index + 4, self.dX);
        buf.put(index + 5, self.dY);
        buf.put(index + 6, self.dZ);
        buf.put(index + 7, self.dW);
        return buf;
    }
    public FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, FloatBuffer buf) {
        self.rX = buf.get(index + 0);
        self.rY = buf.get(index + 1);
        self.rZ = buf.get(index + 2);
        self.rW = buf.get(index + 3);
        self.dX = buf.get(index + 4);
        self.dY = buf.get(index + 5);
        self.dZ = buf.get(index + 6);
        self.dW = buf.get(index + 7);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.rX);
        buf.putFloat(index + 4, self.rY);
        buf.putFloat(index + 8, self.rZ);
        buf.putFloat(index + 12, self.rW);
        buf.putFloat(index + 16, self.dX);
        buf.putFloat(index + 20, self.dY);
        buf.putFloat(index + 24, self.dZ);
        buf.putFloat(index + 28, self.dW);
        return buf;
    }
    public FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        self.rX = buf.getFloat(index + 0);
        self.rY = buf.getFloat(index + 4);
        self.rZ = buf.getFloat(index + 8);
        self.rW = buf.getFloat(index + 12);
        self.dX = buf.getFloat(index + 16);
        self.dY = buf.getFloat(index + 20);
        self.dZ = buf.getFloat(index + 24);
        self.dW = buf.getFloat(index + 28);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatDualQuatImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.rX);
        buf.put(index + 1, self.rY);
        buf.put(index + 2, self.rZ);
        buf.put(index + 3, self.rW);
        buf.put(index + 4, self.dX);
        buf.put(index + 5, self.dY);
        buf.put(index + 6, self.dZ);
        buf.put(index + 7, self.dW);
        return buf;
    }
    public FloatDualQuat loadAbsolute(FloatDualQuatImpl self, int index, DoubleBuffer buf) {
        self.rX = (float) buf.get(index + 0);
        self.rY = (float) buf.get(index + 1);
        self.rZ = (float) buf.get(index + 2);
        self.rW = (float) buf.get(index + 3);
        self.dX = (float) buf.get(index + 4);
        self.dY = (float) buf.get(index + 5);
        self.dZ = (float) buf.get(index + 6);
        self.dW = (float) buf.get(index + 7);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.rX);
        buf.putDouble(index + 8, self.rY);
        buf.putDouble(index + 16, self.rZ);
        buf.putDouble(index + 24, self.rW);
        buf.putDouble(index + 32, self.dX);
        buf.putDouble(index + 40, self.dY);
        buf.putDouble(index + 48, self.dZ);
        buf.putDouble(index + 56, self.dW);
        return buf;
    }
    public FloatDualQuat loadDoubleAbsolute(FloatDualQuatImpl self, int index, ByteBuffer buf) {
        self.rX = (float) buf.getDouble(index + 0);
        self.rY = (float) buf.getDouble(index + 8);
        self.rZ = (float) buf.getDouble(index + 16);
        self.rW = (float) buf.getDouble(index + 24);
        self.dX = (float) buf.getDouble(index + 32);
        self.dY = (float) buf.getDouble(index + 40);
        self.dZ = (float) buf.getDouble(index + 48);
        self.dW = (float) buf.getDouble(index + 56);
        return self;
    }
}
