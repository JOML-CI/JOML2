// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatDualQuatBbOpsApi implements FloatDualQuatBbOps {
    public FloatBuffer storeAbsolute(FloatDualQuat self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.rX());
        buf.put(index + 1, self.rY());
        buf.put(index + 2, self.rZ());
        buf.put(index + 3, self.rW());
        buf.put(index + 4, self.dX());
        buf.put(index + 5, self.dY());
        buf.put(index + 6, self.dZ());
        buf.put(index + 7, self.dW());
        return buf;
    }
    public FloatDualQuat loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        float _c4 = buf.get(index + 4);
        float _c5 = buf.get(index + 5);
        float _c6 = buf.get(index + 6);
        float _c7 = buf.get(index + 7);
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public ByteBuffer storeAbsolute(FloatDualQuat self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.rX());
        buf.putFloat(index + 4, self.rY());
        buf.putFloat(index + 8, self.rZ());
        buf.putFloat(index + 12, self.rW());
        buf.putFloat(index + 16, self.dX());
        buf.putFloat(index + 20, self.dY());
        buf.putFloat(index + 24, self.dZ());
        buf.putFloat(index + 28, self.dW());
        return buf;
    }
    public FloatDualQuat loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        float _c4 = buf.getFloat(index + 16);
        float _c5 = buf.getFloat(index + 20);
        float _c6 = buf.getFloat(index + 24);
        float _c7 = buf.getFloat(index + 28);
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public DoubleBuffer storeAbsolute(FloatDualQuat self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.rX());
        buf.put(index + 1, self.rY());
        buf.put(index + 2, self.rZ());
        buf.put(index + 3, self.rW());
        buf.put(index + 4, self.dX());
        buf.put(index + 5, self.dY());
        buf.put(index + 6, self.dZ());
        buf.put(index + 7, self.dW());
        return buf;
    }
    public FloatDualQuat loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        float _c4 = (float) buf.get(index + 4);
        float _c5 = (float) buf.get(index + 5);
        float _c6 = (float) buf.get(index + 6);
        float _c7 = (float) buf.get(index + 7);
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public ByteBuffer storeDoubleAbsolute(FloatDualQuat self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.rX());
        buf.putDouble(index + 8, self.rY());
        buf.putDouble(index + 16, self.rZ());
        buf.putDouble(index + 24, self.rW());
        buf.putDouble(index + 32, self.dX());
        buf.putDouble(index + 40, self.dY());
        buf.putDouble(index + 48, self.dZ());
        buf.putDouble(index + 56, self.dW());
        return buf;
    }
    public FloatDualQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        float _c4 = (float) buf.getDouble(index + 32);
        float _c5 = (float) buf.getDouble(index + 40);
        float _c6 = (float) buf.getDouble(index + 48);
        float _c7 = (float) buf.getDouble(index + 56);
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
