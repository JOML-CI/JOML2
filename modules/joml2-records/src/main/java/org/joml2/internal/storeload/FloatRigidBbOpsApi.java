// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatRigidBbOpsApi implements FloatRigidBbOps {
    public FloatBuffer storeAbsolute(FloatRigid self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.tX());
        buf.put(index + 1, self.tY());
        buf.put(index + 2, self.tZ());
        buf.put(index + 3, self.rX());
        buf.put(index + 4, self.rY());
        buf.put(index + 5, self.rZ());
        buf.put(index + 6, self.rW());
        return buf;
    }
    public FloatRigid loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        float _c4 = buf.get(index + 4);
        float _c5 = buf.get(index + 5);
        float _c6 = buf.get(index + 6);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
    public ByteBuffer storeAbsolute(FloatRigid self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.tX());
        buf.putFloat(index + 4, self.tY());
        buf.putFloat(index + 8, self.tZ());
        buf.putFloat(index + 12, self.rX());
        buf.putFloat(index + 16, self.rY());
        buf.putFloat(index + 20, self.rZ());
        buf.putFloat(index + 24, self.rW());
        return buf;
    }
    public FloatRigid loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        float _c4 = buf.getFloat(index + 16);
        float _c5 = buf.getFloat(index + 20);
        float _c6 = buf.getFloat(index + 24);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
    public DoubleBuffer storeAbsolute(FloatRigid self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.tX());
        buf.put(index + 1, self.tY());
        buf.put(index + 2, self.tZ());
        buf.put(index + 3, self.rX());
        buf.put(index + 4, self.rY());
        buf.put(index + 5, self.rZ());
        buf.put(index + 6, self.rW());
        return buf;
    }
    public FloatRigid loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        float _c4 = (float) buf.get(index + 4);
        float _c5 = (float) buf.get(index + 5);
        float _c6 = (float) buf.get(index + 6);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
    public ByteBuffer storeDoubleAbsolute(FloatRigid self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.tX());
        buf.putDouble(index + 8, self.tY());
        buf.putDouble(index + 16, self.tZ());
        buf.putDouble(index + 24, self.rX());
        buf.putDouble(index + 32, self.rY());
        buf.putDouble(index + 40, self.rZ());
        buf.putDouble(index + 48, self.rW());
        return buf;
    }
    public FloatRigid loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        float _c4 = (float) buf.getDouble(index + 32);
        float _c5 = (float) buf.getDouble(index + 40);
        float _c6 = (float) buf.getDouble(index + 48);
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }
}
