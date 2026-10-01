// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class FloatAABBBbOpsApi implements FloatAABBBbOps {
    public FloatBuffer storeAbsolute(FloatAABB self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.minX());
        buf.put(index + 1, self.minY());
        buf.put(index + 2, self.minZ());
        buf.put(index + 3, self.maxX());
        buf.put(index + 4, self.maxY());
        buf.put(index + 5, self.maxZ());
        return buf;
    }
    public FloatAABB loadAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        float _c4 = buf.get(index + 4);
        float _c5 = buf.get(index + 5);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeAbsolute(FloatAABB self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.minX());
        buf.putFloat(index + 4, self.minY());
        buf.putFloat(index + 8, self.minZ());
        buf.putFloat(index + 12, self.maxX());
        buf.putFloat(index + 16, self.maxY());
        buf.putFloat(index + 20, self.maxZ());
        return buf;
    }
    public FloatAABB loadAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        float _c4 = buf.getFloat(index + 16);
        float _c5 = buf.getFloat(index + 20);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public DoubleBuffer storeAbsolute(FloatAABB self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.minX());
        buf.put(index + 1, self.minY());
        buf.put(index + 2, self.minZ());
        buf.put(index + 3, self.maxX());
        buf.put(index + 4, self.maxY());
        buf.put(index + 5, self.maxZ());
        return buf;
    }
    public FloatAABB loadAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        float _c4 = (float) buf.get(index + 4);
        float _c5 = (float) buf.get(index + 5);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public ByteBuffer storeDoubleAbsolute(FloatAABB self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.minX());
        buf.putDouble(index + 8, self.minY());
        buf.putDouble(index + 16, self.minZ());
        buf.putDouble(index + 24, self.maxX());
        buf.putDouble(index + 32, self.maxY());
        buf.putDouble(index + 40, self.maxZ());
        return buf;
    }
    public FloatAABB loadDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        float _c4 = (float) buf.getDouble(index + 32);
        float _c5 = (float) buf.getDouble(index + 40);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
