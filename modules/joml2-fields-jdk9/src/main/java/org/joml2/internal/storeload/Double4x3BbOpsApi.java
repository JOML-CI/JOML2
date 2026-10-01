// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Double4x3BbOpsApi implements Double4x3BbOps {
    public DoubleBuffer storeCMAbsolute(Double4x3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(index + 3, self.m30);
        buf.put(index + 4, self.m01);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, self.m21);
        buf.put(index + 7, self.m31);
        buf.put(index + 8, self.m02);
        buf.put(index + 9, self.m12);
        buf.put(index + 10, self.m22);
        buf.put(index + 11, self.m32);
        return buf;
    }
    public Double4x3 loadCMAbsolute(Double4x3Impl self, int index, DoubleBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m30 = buf.get(index + 3);
        self.m01 = buf.get(index + 4);
        self.m11 = buf.get(index + 5);
        self.m21 = buf.get(index + 6);
        self.m31 = buf.get(index + 7);
        self.m02 = buf.get(index + 8);
        self.m12 = buf.get(index + 9);
        self.m22 = buf.get(index + 10);
        self.m32 = buf.get(index + 11);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Double4x3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, self.m20);
        buf.putDouble(index + 24, self.m30);
        buf.putDouble(index + 32, self.m01);
        buf.putDouble(index + 40, self.m11);
        buf.putDouble(index + 48, self.m21);
        buf.putDouble(index + 56, self.m31);
        buf.putDouble(index + 64, self.m02);
        buf.putDouble(index + 72, self.m12);
        buf.putDouble(index + 80, self.m22);
        buf.putDouble(index + 88, self.m32);
        return buf;
    }
    public Double4x3 loadCMAbsolute(Double4x3Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getDouble(index + 0);
        self.m10 = buf.getDouble(index + 8);
        self.m20 = buf.getDouble(index + 16);
        self.m30 = buf.getDouble(index + 24);
        self.m01 = buf.getDouble(index + 32);
        self.m11 = buf.getDouble(index + 40);
        self.m21 = buf.getDouble(index + 48);
        self.m31 = buf.getDouble(index + 56);
        self.m02 = buf.getDouble(index + 64);
        self.m12 = buf.getDouble(index + 72);
        self.m22 = buf.getDouble(index + 80);
        self.m32 = buf.getDouble(index + 88);
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double4x3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.m00);
        buf.put(index + 1, (float) self.m10);
        buf.put(index + 2, (float) self.m20);
        buf.put(index + 3, (float) self.m30);
        buf.put(index + 4, (float) self.m01);
        buf.put(index + 5, (float) self.m11);
        buf.put(index + 6, (float) self.m21);
        buf.put(index + 7, (float) self.m31);
        buf.put(index + 8, (float) self.m02);
        buf.put(index + 9, (float) self.m12);
        buf.put(index + 10, (float) self.m22);
        buf.put(index + 11, (float) self.m32);
        return buf;
    }
    public Double4x3 loadCMAbsolute(Double4x3Impl self, int index, FloatBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m30 = buf.get(index + 3);
        self.m01 = buf.get(index + 4);
        self.m11 = buf.get(index + 5);
        self.m21 = buf.get(index + 6);
        self.m31 = buf.get(index + 7);
        self.m02 = buf.get(index + 8);
        self.m12 = buf.get(index + 9);
        self.m22 = buf.get(index + 10);
        self.m32 = buf.get(index + 11);
        return self;
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.m00);
        buf.putFloat(index + 4, (float) self.m10);
        buf.putFloat(index + 8, (float) self.m20);
        buf.putFloat(index + 12, (float) self.m30);
        buf.putFloat(index + 16, (float) self.m01);
        buf.putFloat(index + 20, (float) self.m11);
        buf.putFloat(index + 24, (float) self.m21);
        buf.putFloat(index + 28, (float) self.m31);
        buf.putFloat(index + 32, (float) self.m02);
        buf.putFloat(index + 36, (float) self.m12);
        buf.putFloat(index + 40, (float) self.m22);
        buf.putFloat(index + 44, (float) self.m32);
        return buf;
    }
    public Double4x3 loadCMFloatAbsolute(Double4x3Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getFloat(index + 0);
        self.m10 = buf.getFloat(index + 4);
        self.m20 = buf.getFloat(index + 8);
        self.m30 = buf.getFloat(index + 12);
        self.m01 = buf.getFloat(index + 16);
        self.m11 = buf.getFloat(index + 20);
        self.m21 = buf.getFloat(index + 24);
        self.m31 = buf.getFloat(index + 28);
        self.m02 = buf.getFloat(index + 32);
        self.m12 = buf.getFloat(index + 36);
        self.m22 = buf.getFloat(index + 40);
        self.m32 = buf.getFloat(index + 44);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Double4x3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m02);
        buf.put(index + 3, self.m10);
        buf.put(index + 4, self.m11);
        buf.put(index + 5, self.m12);
        buf.put(index + 6, self.m20);
        buf.put(index + 7, self.m21);
        buf.put(index + 8, self.m22);
        buf.put(index + 9, self.m30);
        buf.put(index + 10, self.m31);
        buf.put(index + 11, self.m32);
        return buf;
    }
    public Double4x3 loadRMAbsolute(Double4x3Impl self, int index, DoubleBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m01 = buf.get(index + 1);
        self.m02 = buf.get(index + 2);
        self.m10 = buf.get(index + 3);
        self.m11 = buf.get(index + 4);
        self.m12 = buf.get(index + 5);
        self.m20 = buf.get(index + 6);
        self.m21 = buf.get(index + 7);
        self.m22 = buf.get(index + 8);
        self.m30 = buf.get(index + 9);
        self.m31 = buf.get(index + 10);
        self.m32 = buf.get(index + 11);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Double4x3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(index + 16, self.m02);
        buf.putDouble(index + 24, self.m10);
        buf.putDouble(index + 32, self.m11);
        buf.putDouble(index + 40, self.m12);
        buf.putDouble(index + 48, self.m20);
        buf.putDouble(index + 56, self.m21);
        buf.putDouble(index + 64, self.m22);
        buf.putDouble(index + 72, self.m30);
        buf.putDouble(index + 80, self.m31);
        buf.putDouble(index + 88, self.m32);
        return buf;
    }
    public Double4x3 loadRMAbsolute(Double4x3Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getDouble(index + 0);
        self.m01 = buf.getDouble(index + 8);
        self.m02 = buf.getDouble(index + 16);
        self.m10 = buf.getDouble(index + 24);
        self.m11 = buf.getDouble(index + 32);
        self.m12 = buf.getDouble(index + 40);
        self.m20 = buf.getDouble(index + 48);
        self.m21 = buf.getDouble(index + 56);
        self.m22 = buf.getDouble(index + 64);
        self.m30 = buf.getDouble(index + 72);
        self.m31 = buf.getDouble(index + 80);
        self.m32 = buf.getDouble(index + 88);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double4x3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.m00);
        buf.put(index + 1, (float) self.m01);
        buf.put(index + 2, (float) self.m02);
        buf.put(index + 3, (float) self.m10);
        buf.put(index + 4, (float) self.m11);
        buf.put(index + 5, (float) self.m12);
        buf.put(index + 6, (float) self.m20);
        buf.put(index + 7, (float) self.m21);
        buf.put(index + 8, (float) self.m22);
        buf.put(index + 9, (float) self.m30);
        buf.put(index + 10, (float) self.m31);
        buf.put(index + 11, (float) self.m32);
        return buf;
    }
    public Double4x3 loadRMAbsolute(Double4x3Impl self, int index, FloatBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m01 = buf.get(index + 1);
        self.m02 = buf.get(index + 2);
        self.m10 = buf.get(index + 3);
        self.m11 = buf.get(index + 4);
        self.m12 = buf.get(index + 5);
        self.m20 = buf.get(index + 6);
        self.m21 = buf.get(index + 7);
        self.m22 = buf.get(index + 8);
        self.m30 = buf.get(index + 9);
        self.m31 = buf.get(index + 10);
        self.m32 = buf.get(index + 11);
        return self;
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.m00);
        buf.putFloat(index + 4, (float) self.m01);
        buf.putFloat(index + 8, (float) self.m02);
        buf.putFloat(index + 12, (float) self.m10);
        buf.putFloat(index + 16, (float) self.m11);
        buf.putFloat(index + 20, (float) self.m12);
        buf.putFloat(index + 24, (float) self.m20);
        buf.putFloat(index + 28, (float) self.m21);
        buf.putFloat(index + 32, (float) self.m22);
        buf.putFloat(index + 36, (float) self.m30);
        buf.putFloat(index + 40, (float) self.m31);
        buf.putFloat(index + 44, (float) self.m32);
        return buf;
    }
    public Double4x3 loadRMFloatAbsolute(Double4x3Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getFloat(index + 0);
        self.m01 = buf.getFloat(index + 4);
        self.m02 = buf.getFloat(index + 8);
        self.m10 = buf.getFloat(index + 12);
        self.m11 = buf.getFloat(index + 16);
        self.m12 = buf.getFloat(index + 20);
        self.m20 = buf.getFloat(index + 24);
        self.m21 = buf.getFloat(index + 28);
        self.m22 = buf.getFloat(index + 32);
        self.m30 = buf.getFloat(index + 36);
        self.m31 = buf.getFloat(index + 40);
        self.m32 = buf.getFloat(index + 44);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Double4x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(index + 3, self.m30);
        buf.put(_p1, self.m01);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p1 + 2, self.m21);
        buf.put(_p1 + 3, self.m31);
        buf.put(_p2, self.m02);
        buf.put(_p2 + 1, self.m12);
        buf.put(_p2 + 2, self.m22);
        buf.put(_p2 + 3, self.m32);
        return buf;
    }
    public Double4x3 loadCMAbsolute(Double4x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.m00 = buf.get(index);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m30 = buf.get(index + 3);
        self.m01 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m21 = buf.get(_p1 + 2);
        self.m31 = buf.get(_p1 + 3);
        self.m02 = buf.get(_p2);
        self.m12 = buf.get(_p2 + 1);
        self.m22 = buf.get(_p2 + 2);
        self.m32 = buf.get(_p2 + 3);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Double4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putDouble(index, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, self.m20);
        buf.putDouble(index + 24, self.m30);
        buf.putDouble(_p1, self.m01);
        buf.putDouble(_p1 + 8, self.m11);
        buf.putDouble(_p1 + 16, self.m21);
        buf.putDouble(_p1 + 24, self.m31);
        buf.putDouble(_p2, self.m02);
        buf.putDouble(_p2 + 8, self.m12);
        buf.putDouble(_p2 + 16, self.m22);
        buf.putDouble(_p2 + 24, self.m32);
        return buf;
    }
    public Double4x3 loadCMAbsolute(Double4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.m00 = buf.getDouble(index);
        self.m10 = buf.getDouble(index + 8);
        self.m20 = buf.getDouble(index + 16);
        self.m30 = buf.getDouble(index + 24);
        self.m01 = buf.getDouble(_p1);
        self.m11 = buf.getDouble(_p1 + 8);
        self.m21 = buf.getDouble(_p1 + 16);
        self.m31 = buf.getDouble(_p1 + 24);
        self.m02 = buf.getDouble(_p2);
        self.m12 = buf.getDouble(_p2 + 8);
        self.m22 = buf.getDouble(_p2 + 16);
        self.m32 = buf.getDouble(_p2 + 24);
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double4x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, (float) self.m00);
        buf.put(index + 1, (float) self.m10);
        buf.put(index + 2, (float) self.m20);
        buf.put(index + 3, (float) self.m30);
        buf.put(_p1, (float) self.m01);
        buf.put(_p1 + 1, (float) self.m11);
        buf.put(_p1 + 2, (float) self.m21);
        buf.put(_p1 + 3, (float) self.m31);
        buf.put(_p2, (float) self.m02);
        buf.put(_p2 + 1, (float) self.m12);
        buf.put(_p2 + 2, (float) self.m22);
        buf.put(_p2 + 3, (float) self.m32);
        return buf;
    }
    public Double4x3 loadCMAbsolute(Double4x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.m00 = buf.get(index);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m30 = buf.get(index + 3);
        self.m01 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m21 = buf.get(_p1 + 2);
        self.m31 = buf.get(_p1 + 3);
        self.m02 = buf.get(_p2);
        self.m12 = buf.get(_p2 + 1);
        self.m22 = buf.get(_p2 + 2);
        self.m32 = buf.get(_p2 + 3);
        return self;
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putFloat(index, (float) self.m00);
        buf.putFloat(index + 4, (float) self.m10);
        buf.putFloat(index + 8, (float) self.m20);
        buf.putFloat(index + 12, (float) self.m30);
        buf.putFloat(_p1, (float) self.m01);
        buf.putFloat(_p1 + 4, (float) self.m11);
        buf.putFloat(_p1 + 8, (float) self.m21);
        buf.putFloat(_p1 + 12, (float) self.m31);
        buf.putFloat(_p2, (float) self.m02);
        buf.putFloat(_p2 + 4, (float) self.m12);
        buf.putFloat(_p2 + 8, (float) self.m22);
        buf.putFloat(_p2 + 12, (float) self.m32);
        return buf;
    }
    public Double4x3 loadCMFloatAbsolute(Double4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.m00 = buf.getFloat(index);
        self.m10 = buf.getFloat(index + 4);
        self.m20 = buf.getFloat(index + 8);
        self.m30 = buf.getFloat(index + 12);
        self.m01 = buf.getFloat(_p1);
        self.m11 = buf.getFloat(_p1 + 4);
        self.m21 = buf.getFloat(_p1 + 8);
        self.m31 = buf.getFloat(_p1 + 12);
        self.m02 = buf.getFloat(_p2);
        self.m12 = buf.getFloat(_p2 + 4);
        self.m22 = buf.getFloat(_p2 + 8);
        self.m32 = buf.getFloat(_p2 + 12);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Double4x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m02);
        buf.put(_p1, self.m10);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p1 + 2, self.m12);
        buf.put(_p2, self.m20);
        buf.put(_p2 + 1, self.m21);
        buf.put(_p2 + 2, self.m22);
        buf.put(_p3, self.m30);
        buf.put(_p3 + 1, self.m31);
        buf.put(_p3 + 2, self.m32);
        return buf;
    }
    public Double4x3 loadRMAbsolute(Double4x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.m00 = buf.get(index);
        self.m01 = buf.get(index + 1);
        self.m02 = buf.get(index + 2);
        self.m10 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m12 = buf.get(_p1 + 2);
        self.m20 = buf.get(_p2);
        self.m21 = buf.get(_p2 + 1);
        self.m22 = buf.get(_p2 + 2);
        self.m30 = buf.get(_p3);
        self.m31 = buf.get(_p3 + 1);
        self.m32 = buf.get(_p3 + 2);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Double4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putDouble(index, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(index + 16, self.m02);
        buf.putDouble(_p1, self.m10);
        buf.putDouble(_p1 + 8, self.m11);
        buf.putDouble(_p1 + 16, self.m12);
        buf.putDouble(_p2, self.m20);
        buf.putDouble(_p2 + 8, self.m21);
        buf.putDouble(_p2 + 16, self.m22);
        buf.putDouble(_p3, self.m30);
        buf.putDouble(_p3 + 8, self.m31);
        buf.putDouble(_p3 + 16, self.m32);
        return buf;
    }
    public Double4x3 loadRMAbsolute(Double4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.m00 = buf.getDouble(index);
        self.m01 = buf.getDouble(index + 8);
        self.m02 = buf.getDouble(index + 16);
        self.m10 = buf.getDouble(_p1);
        self.m11 = buf.getDouble(_p1 + 8);
        self.m12 = buf.getDouble(_p1 + 16);
        self.m20 = buf.getDouble(_p2);
        self.m21 = buf.getDouble(_p2 + 8);
        self.m22 = buf.getDouble(_p2 + 16);
        self.m30 = buf.getDouble(_p3);
        self.m31 = buf.getDouble(_p3 + 8);
        self.m32 = buf.getDouble(_p3 + 16);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double4x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, (float) self.m00);
        buf.put(index + 1, (float) self.m01);
        buf.put(index + 2, (float) self.m02);
        buf.put(_p1, (float) self.m10);
        buf.put(_p1 + 1, (float) self.m11);
        buf.put(_p1 + 2, (float) self.m12);
        buf.put(_p2, (float) self.m20);
        buf.put(_p2 + 1, (float) self.m21);
        buf.put(_p2 + 2, (float) self.m22);
        buf.put(_p3, (float) self.m30);
        buf.put(_p3 + 1, (float) self.m31);
        buf.put(_p3 + 2, (float) self.m32);
        return buf;
    }
    public Double4x3 loadRMAbsolute(Double4x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.m00 = buf.get(index);
        self.m01 = buf.get(index + 1);
        self.m02 = buf.get(index + 2);
        self.m10 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m12 = buf.get(_p1 + 2);
        self.m20 = buf.get(_p2);
        self.m21 = buf.get(_p2 + 1);
        self.m22 = buf.get(_p2 + 2);
        self.m30 = buf.get(_p3);
        self.m31 = buf.get(_p3 + 1);
        self.m32 = buf.get(_p3 + 2);
        return self;
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, (float) self.m00);
        buf.putFloat(index + 4, (float) self.m01);
        buf.putFloat(index + 8, (float) self.m02);
        buf.putFloat(_p1, (float) self.m10);
        buf.putFloat(_p1 + 4, (float) self.m11);
        buf.putFloat(_p1 + 8, (float) self.m12);
        buf.putFloat(_p2, (float) self.m20);
        buf.putFloat(_p2 + 4, (float) self.m21);
        buf.putFloat(_p2 + 8, (float) self.m22);
        buf.putFloat(_p3, (float) self.m30);
        buf.putFloat(_p3 + 4, (float) self.m31);
        buf.putFloat(_p3 + 8, (float) self.m32);
        return buf;
    }
    public Double4x3 loadRMFloatAbsolute(Double4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.m00 = buf.getFloat(index);
        self.m01 = buf.getFloat(index + 4);
        self.m02 = buf.getFloat(index + 8);
        self.m10 = buf.getFloat(_p1);
        self.m11 = buf.getFloat(_p1 + 4);
        self.m12 = buf.getFloat(_p1 + 8);
        self.m20 = buf.getFloat(_p2);
        self.m21 = buf.getFloat(_p2 + 4);
        self.m22 = buf.getFloat(_p2 + 8);
        self.m30 = buf.getFloat(_p3);
        self.m31 = buf.getFloat(_p3 + 4);
        self.m32 = buf.getFloat(_p3 + 8);
        return self;
    }
}
