// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float3x2BbOpsApi implements Float3x2BbOps {
    public FloatBuffer storeCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(index + 3, self.m01);
        buf.put(index + 4, self.m11);
        buf.put(index + 5, self.m21);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m01 = buf.get(index + 3);
        self.m11 = buf.get(index + 4);
        self.m21 = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m10);
        buf.putFloat(index + 8, self.m20);
        buf.putFloat(index + 12, self.m01);
        buf.putFloat(index + 16, self.m11);
        buf.putFloat(index + 20, self.m21);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getFloat(index + 0);
        self.m10 = buf.getFloat(index + 4);
        self.m20 = buf.getFloat(index + 8);
        self.m01 = buf.getFloat(index + 12);
        self.m11 = buf.getFloat(index + 16);
        self.m21 = buf.getFloat(index + 20);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(index + 3, self.m01);
        buf.put(index + 4, self.m11);
        buf.put(index + 5, self.m21);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        self.m00 = (float) buf.get(index + 0);
        self.m10 = (float) buf.get(index + 1);
        self.m20 = (float) buf.get(index + 2);
        self.m01 = (float) buf.get(index + 3);
        self.m11 = (float) buf.get(index + 4);
        self.m21 = (float) buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, self.m20);
        buf.putDouble(index + 24, self.m01);
        buf.putDouble(index + 32, self.m11);
        buf.putDouble(index + 40, self.m21);
        return buf;
    }
    public Float3x2 loadCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        self.m00 = (float) buf.getDouble(index + 0);
        self.m10 = (float) buf.getDouble(index + 8);
        self.m20 = (float) buf.getDouble(index + 16);
        self.m01 = (float) buf.getDouble(index + 24);
        self.m11 = (float) buf.getDouble(index + 32);
        self.m21 = (float) buf.getDouble(index + 40);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m10);
        buf.put(index + 3, self.m11);
        buf.put(index + 4, self.m20);
        buf.put(index + 5, self.m21);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m01 = buf.get(index + 1);
        self.m10 = buf.get(index + 2);
        self.m11 = buf.get(index + 3);
        self.m20 = buf.get(index + 4);
        self.m21 = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m01);
        buf.putFloat(index + 8, self.m10);
        buf.putFloat(index + 12, self.m11);
        buf.putFloat(index + 16, self.m20);
        buf.putFloat(index + 20, self.m21);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getFloat(index + 0);
        self.m01 = buf.getFloat(index + 4);
        self.m10 = buf.getFloat(index + 8);
        self.m11 = buf.getFloat(index + 12);
        self.m20 = buf.getFloat(index + 16);
        self.m21 = buf.getFloat(index + 20);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m10);
        buf.put(index + 3, self.m11);
        buf.put(index + 4, self.m20);
        buf.put(index + 5, self.m21);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        self.m00 = (float) buf.get(index + 0);
        self.m01 = (float) buf.get(index + 1);
        self.m10 = (float) buf.get(index + 2);
        self.m11 = (float) buf.get(index + 3);
        self.m20 = (float) buf.get(index + 4);
        self.m21 = (float) buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(index + 16, self.m10);
        buf.putDouble(index + 24, self.m11);
        buf.putDouble(index + 32, self.m20);
        buf.putDouble(index + 40, self.m21);
        return buf;
    }
    public Float3x2 loadRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        self.m00 = (float) buf.getDouble(index + 0);
        self.m01 = (float) buf.getDouble(index + 8);
        self.m10 = (float) buf.getDouble(index + 16);
        self.m11 = (float) buf.getDouble(index + 24);
        self.m20 = (float) buf.getDouble(index + 32);
        self.m21 = (float) buf.getDouble(index + 40);
        return self;
    }
    public FloatBuffer storeCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(_p1, self.m01);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p1 + 2, self.m21);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        self.m00 = buf.get(index);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m01 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m21 = buf.get(_p1 + 2);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        buf.putFloat(index, self.m00);
        buf.putFloat(index + 4, self.m10);
        buf.putFloat(index + 8, self.m20);
        buf.putFloat(_p1, self.m01);
        buf.putFloat(_p1 + 4, self.m11);
        buf.putFloat(_p1 + 8, self.m21);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        self.m00 = buf.getFloat(index);
        self.m10 = buf.getFloat(index + 4);
        self.m20 = buf.getFloat(index + 8);
        self.m01 = buf.getFloat(_p1);
        self.m11 = buf.getFloat(_p1 + 4);
        self.m21 = buf.getFloat(_p1 + 8);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(_p1, self.m01);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p1 + 2, self.m21);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        self.m00 = (float) buf.get(index);
        self.m10 = (float) buf.get(index + 1);
        self.m20 = (float) buf.get(index + 2);
        self.m01 = (float) buf.get(_p1);
        self.m11 = (float) buf.get(_p1 + 1);
        self.m21 = (float) buf.get(_p1 + 2);
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        buf.putDouble(index, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, self.m20);
        buf.putDouble(_p1, self.m01);
        buf.putDouble(_p1 + 8, self.m11);
        buf.putDouble(_p1 + 16, self.m21);
        return buf;
    }
    public Float3x2 loadCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        self.m00 = (float) buf.getDouble(index);
        self.m10 = (float) buf.getDouble(index + 8);
        self.m20 = (float) buf.getDouble(index + 16);
        self.m01 = (float) buf.getDouble(_p1);
        self.m11 = (float) buf.getDouble(_p1 + 8);
        self.m21 = (float) buf.getDouble(_p1 + 16);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(_p1, self.m10);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p2, self.m20);
        buf.put(_p2 + 1, self.m21);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.m00 = buf.get(index);
        self.m01 = buf.get(index + 1);
        self.m10 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m20 = buf.get(_p2);
        self.m21 = buf.get(_p2 + 1);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putFloat(index, self.m00);
        buf.putFloat(index + 4, self.m01);
        buf.putFloat(_p1, self.m10);
        buf.putFloat(_p1 + 4, self.m11);
        buf.putFloat(_p2, self.m20);
        buf.putFloat(_p2 + 4, self.m21);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.m00 = buf.getFloat(index);
        self.m01 = buf.getFloat(index + 4);
        self.m10 = buf.getFloat(_p1);
        self.m11 = buf.getFloat(_p1 + 4);
        self.m20 = buf.getFloat(_p2);
        self.m21 = buf.getFloat(_p2 + 4);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(_p1, self.m10);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p2, self.m20);
        buf.put(_p2 + 1, self.m21);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.m00 = (float) buf.get(index);
        self.m01 = (float) buf.get(index + 1);
        self.m10 = (float) buf.get(_p1);
        self.m11 = (float) buf.get(_p1 + 1);
        self.m20 = (float) buf.get(_p2);
        self.m21 = (float) buf.get(_p2 + 1);
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putDouble(index, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(_p1, self.m10);
        buf.putDouble(_p1 + 8, self.m11);
        buf.putDouble(_p2, self.m20);
        buf.putDouble(_p2 + 8, self.m21);
        return buf;
    }
    public Float3x2 loadRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.m00 = (float) buf.getDouble(index);
        self.m01 = (float) buf.getDouble(index + 8);
        self.m10 = (float) buf.getDouble(_p1);
        self.m11 = (float) buf.getDouble(_p1 + 8);
        self.m20 = (float) buf.getDouble(_p2);
        self.m21 = (float) buf.getDouble(_p2 + 8);
        return self;
    }
}
