// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class DoubleDualQuatBbOpsApi implements DoubleDualQuatBbOps {
    public DoubleBuffer storeAbsolute(DoubleDualQuatImpl self, int index, DoubleBuffer buf) {
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
    public DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, DoubleBuffer buf) {
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
    public ByteBuffer storeAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
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
    public DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        self.rX = buf.getDouble(index + 0);
        self.rY = buf.getDouble(index + 8);
        self.rZ = buf.getDouble(index + 16);
        self.rW = buf.getDouble(index + 24);
        self.dX = buf.getDouble(index + 32);
        self.dY = buf.getDouble(index + 40);
        self.dZ = buf.getDouble(index + 48);
        self.dW = buf.getDouble(index + 56);
        return self;
    }
    public FloatBuffer storeAbsolute(DoubleDualQuatImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.rX);
        buf.put(index + 1, (float) self.rY);
        buf.put(index + 2, (float) self.rZ);
        buf.put(index + 3, (float) self.rW);
        buf.put(index + 4, (float) self.dX);
        buf.put(index + 5, (float) self.dY);
        buf.put(index + 6, (float) self.dZ);
        buf.put(index + 7, (float) self.dW);
        return buf;
    }
    public DoubleDualQuat loadAbsolute(DoubleDualQuatImpl self, int index, FloatBuffer buf) {
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
    public ByteBuffer storeFloatAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.rX);
        buf.putFloat(index + 4, (float) self.rY);
        buf.putFloat(index + 8, (float) self.rZ);
        buf.putFloat(index + 12, (float) self.rW);
        buf.putFloat(index + 16, (float) self.dX);
        buf.putFloat(index + 20, (float) self.dY);
        buf.putFloat(index + 24, (float) self.dZ);
        buf.putFloat(index + 28, (float) self.dW);
        return buf;
    }
    public DoubleDualQuat loadFloatAbsolute(DoubleDualQuatImpl self, int index, ByteBuffer buf) {
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
}
