// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Double4x2BbOpsApi implements Double4x2BbOps {
    public DoubleBuffer storeCMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(index + 3, self.m30);
        buf.put(index + 4, self.m01);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, self.m21);
        buf.put(index + 7, self.m31);
        return buf;
    }
    public Double4x2 loadCMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m30 = buf.get(index + 3);
        self.m01 = buf.get(index + 4);
        self.m11 = buf.get(index + 5);
        self.m21 = buf.get(index + 6);
        self.m31 = buf.get(index + 7);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Double4x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, self.m20);
        buf.putDouble(index + 24, self.m30);
        buf.putDouble(index + 32, self.m01);
        buf.putDouble(index + 40, self.m11);
        buf.putDouble(index + 48, self.m21);
        buf.putDouble(index + 56, self.m31);
        return buf;
    }
    public Double4x2 loadCMAbsolute(Double4x2Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getDouble(index + 0);
        self.m10 = buf.getDouble(index + 8);
        self.m20 = buf.getDouble(index + 16);
        self.m30 = buf.getDouble(index + 24);
        self.m01 = buf.getDouble(index + 32);
        self.m11 = buf.getDouble(index + 40);
        self.m21 = buf.getDouble(index + 48);
        self.m31 = buf.getDouble(index + 56);
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double4x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.m00);
        buf.put(index + 1, (float) self.m10);
        buf.put(index + 2, (float) self.m20);
        buf.put(index + 3, (float) self.m30);
        buf.put(index + 4, (float) self.m01);
        buf.put(index + 5, (float) self.m11);
        buf.put(index + 6, (float) self.m21);
        buf.put(index + 7, (float) self.m31);
        return buf;
    }
    public Double4x2 loadCMAbsolute(Double4x2Impl self, int index, FloatBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m30 = buf.get(index + 3);
        self.m01 = buf.get(index + 4);
        self.m11 = buf.get(index + 5);
        self.m21 = buf.get(index + 6);
        self.m31 = buf.get(index + 7);
        return self;
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.m00);
        buf.putFloat(index + 4, (float) self.m10);
        buf.putFloat(index + 8, (float) self.m20);
        buf.putFloat(index + 12, (float) self.m30);
        buf.putFloat(index + 16, (float) self.m01);
        buf.putFloat(index + 20, (float) self.m11);
        buf.putFloat(index + 24, (float) self.m21);
        buf.putFloat(index + 28, (float) self.m31);
        return buf;
    }
    public Double4x2 loadCMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getFloat(index + 0);
        self.m10 = buf.getFloat(index + 4);
        self.m20 = buf.getFloat(index + 8);
        self.m30 = buf.getFloat(index + 12);
        self.m01 = buf.getFloat(index + 16);
        self.m11 = buf.getFloat(index + 20);
        self.m21 = buf.getFloat(index + 24);
        self.m31 = buf.getFloat(index + 28);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m10);
        buf.put(index + 3, self.m11);
        buf.put(index + 4, self.m20);
        buf.put(index + 5, self.m21);
        buf.put(index + 6, self.m30);
        buf.put(index + 7, self.m31);
        return buf;
    }
    public Double4x2 loadRMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m01 = buf.get(index + 1);
        self.m10 = buf.get(index + 2);
        self.m11 = buf.get(index + 3);
        self.m20 = buf.get(index + 4);
        self.m21 = buf.get(index + 5);
        self.m30 = buf.get(index + 6);
        self.m31 = buf.get(index + 7);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Double4x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(index + 16, self.m10);
        buf.putDouble(index + 24, self.m11);
        buf.putDouble(index + 32, self.m20);
        buf.putDouble(index + 40, self.m21);
        buf.putDouble(index + 48, self.m30);
        buf.putDouble(index + 56, self.m31);
        return buf;
    }
    public Double4x2 loadRMAbsolute(Double4x2Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getDouble(index + 0);
        self.m01 = buf.getDouble(index + 8);
        self.m10 = buf.getDouble(index + 16);
        self.m11 = buf.getDouble(index + 24);
        self.m20 = buf.getDouble(index + 32);
        self.m21 = buf.getDouble(index + 40);
        self.m30 = buf.getDouble(index + 48);
        self.m31 = buf.getDouble(index + 56);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double4x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.m00);
        buf.put(index + 1, (float) self.m01);
        buf.put(index + 2, (float) self.m10);
        buf.put(index + 3, (float) self.m11);
        buf.put(index + 4, (float) self.m20);
        buf.put(index + 5, (float) self.m21);
        buf.put(index + 6, (float) self.m30);
        buf.put(index + 7, (float) self.m31);
        return buf;
    }
    public Double4x2 loadRMAbsolute(Double4x2Impl self, int index, FloatBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m01 = buf.get(index + 1);
        self.m10 = buf.get(index + 2);
        self.m11 = buf.get(index + 3);
        self.m20 = buf.get(index + 4);
        self.m21 = buf.get(index + 5);
        self.m30 = buf.get(index + 6);
        self.m31 = buf.get(index + 7);
        return self;
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.m00);
        buf.putFloat(index + 4, (float) self.m01);
        buf.putFloat(index + 8, (float) self.m10);
        buf.putFloat(index + 12, (float) self.m11);
        buf.putFloat(index + 16, (float) self.m20);
        buf.putFloat(index + 20, (float) self.m21);
        buf.putFloat(index + 24, (float) self.m30);
        buf.putFloat(index + 28, (float) self.m31);
        return buf;
    }
    public Double4x2 loadRMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getFloat(index + 0);
        self.m01 = buf.getFloat(index + 4);
        self.m10 = buf.getFloat(index + 8);
        self.m11 = buf.getFloat(index + 12);
        self.m20 = buf.getFloat(index + 16);
        self.m21 = buf.getFloat(index + 20);
        self.m30 = buf.getFloat(index + 24);
        self.m31 = buf.getFloat(index + 28);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(index + 3, self.m30);
        buf.put(_p1, self.m01);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p1 + 2, self.m21);
        buf.put(_p1 + 3, self.m31);
        return buf;
    }
    public Double4x2 loadCMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        self.m00 = buf.get(index);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m30 = buf.get(index + 3);
        self.m01 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m21 = buf.get(_p1 + 2);
        self.m31 = buf.get(_p1 + 3);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        buf.putDouble(index, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, self.m20);
        buf.putDouble(index + 24, self.m30);
        buf.putDouble(_p1, self.m01);
        buf.putDouble(_p1 + 8, self.m11);
        buf.putDouble(_p1 + 16, self.m21);
        buf.putDouble(_p1 + 24, self.m31);
        return buf;
    }
    public Double4x2 loadCMAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        self.m00 = buf.getDouble(index);
        self.m10 = buf.getDouble(index + 8);
        self.m20 = buf.getDouble(index + 16);
        self.m30 = buf.getDouble(index + 24);
        self.m01 = buf.getDouble(_p1);
        self.m11 = buf.getDouble(_p1 + 8);
        self.m21 = buf.getDouble(_p1 + 16);
        self.m31 = buf.getDouble(_p1 + 24);
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double4x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, (float) self.m00);
        buf.put(index + 1, (float) self.m10);
        buf.put(index + 2, (float) self.m20);
        buf.put(index + 3, (float) self.m30);
        buf.put(_p1, (float) self.m01);
        buf.put(_p1 + 1, (float) self.m11);
        buf.put(_p1 + 2, (float) self.m21);
        buf.put(_p1 + 3, (float) self.m31);
        return buf;
    }
    public Double4x2 loadCMAbsolute(Double4x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        self.m00 = buf.get(index);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m30 = buf.get(index + 3);
        self.m01 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m21 = buf.get(_p1 + 2);
        self.m31 = buf.get(_p1 + 3);
        return self;
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        buf.putFloat(index, (float) self.m00);
        buf.putFloat(index + 4, (float) self.m10);
        buf.putFloat(index + 8, (float) self.m20);
        buf.putFloat(index + 12, (float) self.m30);
        buf.putFloat(_p1, (float) self.m01);
        buf.putFloat(_p1 + 4, (float) self.m11);
        buf.putFloat(_p1 + 8, (float) self.m21);
        buf.putFloat(_p1 + 12, (float) self.m31);
        return buf;
    }
    public Double4x2 loadCMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        self.m00 = buf.getFloat(index);
        self.m10 = buf.getFloat(index + 4);
        self.m20 = buf.getFloat(index + 8);
        self.m30 = buf.getFloat(index + 12);
        self.m01 = buf.getFloat(_p1);
        self.m11 = buf.getFloat(_p1 + 4);
        self.m21 = buf.getFloat(_p1 + 8);
        self.m31 = buf.getFloat(_p1 + 12);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(_p1, self.m10);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p2, self.m20);
        buf.put(_p2 + 1, self.m21);
        buf.put(_p3, self.m30);
        buf.put(_p3 + 1, self.m31);
        return buf;
    }
    public Double4x2 loadRMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.m00 = buf.get(index);
        self.m01 = buf.get(index + 1);
        self.m10 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m20 = buf.get(_p2);
        self.m21 = buf.get(_p2 + 1);
        self.m30 = buf.get(_p3);
        self.m31 = buf.get(_p3 + 1);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putDouble(index, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(_p1, self.m10);
        buf.putDouble(_p1 + 8, self.m11);
        buf.putDouble(_p2, self.m20);
        buf.putDouble(_p2 + 8, self.m21);
        buf.putDouble(_p3, self.m30);
        buf.putDouble(_p3 + 8, self.m31);
        return buf;
    }
    public Double4x2 loadRMAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.m00 = buf.getDouble(index);
        self.m01 = buf.getDouble(index + 8);
        self.m10 = buf.getDouble(_p1);
        self.m11 = buf.getDouble(_p1 + 8);
        self.m20 = buf.getDouble(_p2);
        self.m21 = buf.getDouble(_p2 + 8);
        self.m30 = buf.getDouble(_p3);
        self.m31 = buf.getDouble(_p3 + 8);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double4x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, (float) self.m00);
        buf.put(index + 1, (float) self.m01);
        buf.put(_p1, (float) self.m10);
        buf.put(_p1 + 1, (float) self.m11);
        buf.put(_p2, (float) self.m20);
        buf.put(_p2 + 1, (float) self.m21);
        buf.put(_p3, (float) self.m30);
        buf.put(_p3 + 1, (float) self.m31);
        return buf;
    }
    public Double4x2 loadRMAbsolute(Double4x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.m00 = buf.get(index);
        self.m01 = buf.get(index + 1);
        self.m10 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m20 = buf.get(_p2);
        self.m21 = buf.get(_p2 + 1);
        self.m30 = buf.get(_p3);
        self.m31 = buf.get(_p3 + 1);
        return self;
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, (float) self.m00);
        buf.putFloat(index + 4, (float) self.m01);
        buf.putFloat(_p1, (float) self.m10);
        buf.putFloat(_p1 + 4, (float) self.m11);
        buf.putFloat(_p2, (float) self.m20);
        buf.putFloat(_p2 + 4, (float) self.m21);
        buf.putFloat(_p3, (float) self.m30);
        buf.putFloat(_p3 + 4, (float) self.m31);
        return buf;
    }
    public Double4x2 loadRMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.m00 = buf.getFloat(index);
        self.m01 = buf.getFloat(index + 4);
        self.m10 = buf.getFloat(_p1);
        self.m11 = buf.getFloat(_p1 + 4);
        self.m20 = buf.getFloat(_p2);
        self.m21 = buf.getFloat(_p2 + 4);
        self.m30 = buf.getFloat(_p3);
        self.m31 = buf.getFloat(_p3 + 4);
        return self;
    }
}
