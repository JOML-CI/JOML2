// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatRigidBbOpsApi implements FloatRigidBbOps {
    public FloatBuffer storeAbsolute(FloatRigidImpl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.tX);
        buf.put(index + 1, self.tY);
        buf.put(index + 2, self.tZ);
        buf.put(index + 3, self.rX);
        buf.put(index + 4, self.rY);
        buf.put(index + 5, self.rZ);
        buf.put(index + 6, self.rW);
        return buf;
    }
    public FloatRigid loadAbsolute(FloatRigidImpl self, int index, FloatBuffer buf) {
        self.tX = buf.get(index + 0);
        self.tY = buf.get(index + 1);
        self.tZ = buf.get(index + 2);
        self.rX = buf.get(index + 3);
        self.rY = buf.get(index + 4);
        self.rZ = buf.get(index + 5);
        self.rW = buf.get(index + 6);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.tX);
        buf.putFloat(index + 4, self.tY);
        buf.putFloat(index + 8, self.tZ);
        buf.putFloat(index + 12, self.rX);
        buf.putFloat(index + 16, self.rY);
        buf.putFloat(index + 20, self.rZ);
        buf.putFloat(index + 24, self.rW);
        return buf;
    }
    public FloatRigid loadAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        self.tX = buf.getFloat(index + 0);
        self.tY = buf.getFloat(index + 4);
        self.tZ = buf.getFloat(index + 8);
        self.rX = buf.getFloat(index + 12);
        self.rY = buf.getFloat(index + 16);
        self.rZ = buf.getFloat(index + 20);
        self.rW = buf.getFloat(index + 24);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatRigidImpl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.tX);
        buf.put(index + 1, self.tY);
        buf.put(index + 2, self.tZ);
        buf.put(index + 3, self.rX);
        buf.put(index + 4, self.rY);
        buf.put(index + 5, self.rZ);
        buf.put(index + 6, self.rW);
        return buf;
    }
    public FloatRigid loadAbsolute(FloatRigidImpl self, int index, DoubleBuffer buf) {
        self.tX = (float) buf.get(index + 0);
        self.tY = (float) buf.get(index + 1);
        self.tZ = (float) buf.get(index + 2);
        self.rX = (float) buf.get(index + 3);
        self.rY = (float) buf.get(index + 4);
        self.rZ = (float) buf.get(index + 5);
        self.rW = (float) buf.get(index + 6);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.tX);
        buf.putDouble(index + 8, self.tY);
        buf.putDouble(index + 16, self.tZ);
        buf.putDouble(index + 24, self.rX);
        buf.putDouble(index + 32, self.rY);
        buf.putDouble(index + 40, self.rZ);
        buf.putDouble(index + 48, self.rW);
        return buf;
    }
    public FloatRigid loadDoubleAbsolute(FloatRigidImpl self, int index, ByteBuffer buf) {
        self.tX = (float) buf.getDouble(index + 0);
        self.tY = (float) buf.getDouble(index + 8);
        self.tZ = (float) buf.getDouble(index + 16);
        self.rX = (float) buf.getDouble(index + 24);
        self.rY = (float) buf.getDouble(index + 32);
        self.rZ = (float) buf.getDouble(index + 40);
        self.rW = (float) buf.getDouble(index + 48);
        return self;
    }
}
