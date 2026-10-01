// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float3x4BbOpsApi implements Float3x4BbOps {
    public FloatBuffer storeCMAbsolute(Float3x4Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(index + 3, self.m01);
        buf.put(index + 4, self.m11);
        buf.put(index + 5, self.m21);
        buf.put(index + 6, self.m02);
        buf.put(index + 7, self.m12);
        buf.put(index + 8, self.m22);
        buf.put(index + 9, self.m03);
        buf.put(index + 10, self.m13);
        buf.put(index + 11, self.m23);
        return buf;
    }
    public Float3x4 loadCMAbsolute(Float3x4Impl self, int index, FloatBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m01 = buf.get(index + 3);
        self.m11 = buf.get(index + 4);
        self.m21 = buf.get(index + 5);
        self.m02 = buf.get(index + 6);
        self.m12 = buf.get(index + 7);
        self.m22 = buf.get(index + 8);
        self.m03 = buf.get(index + 9);
        self.m13 = buf.get(index + 10);
        self.m23 = buf.get(index + 11);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float3x4Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m10);
        buf.putFloat(index + 8, self.m20);
        buf.putFloat(index + 12, self.m01);
        buf.putFloat(index + 16, self.m11);
        buf.putFloat(index + 20, self.m21);
        buf.putFloat(index + 24, self.m02);
        buf.putFloat(index + 28, self.m12);
        buf.putFloat(index + 32, self.m22);
        buf.putFloat(index + 36, self.m03);
        buf.putFloat(index + 40, self.m13);
        buf.putFloat(index + 44, self.m23);
        return buf;
    }
    public Float3x4 loadCMAbsolute(Float3x4Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getFloat(index + 0);
        self.m10 = buf.getFloat(index + 4);
        self.m20 = buf.getFloat(index + 8);
        self.m01 = buf.getFloat(index + 12);
        self.m11 = buf.getFloat(index + 16);
        self.m21 = buf.getFloat(index + 20);
        self.m02 = buf.getFloat(index + 24);
        self.m12 = buf.getFloat(index + 28);
        self.m22 = buf.getFloat(index + 32);
        self.m03 = buf.getFloat(index + 36);
        self.m13 = buf.getFloat(index + 40);
        self.m23 = buf.getFloat(index + 44);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(index + 3, self.m01);
        buf.put(index + 4, self.m11);
        buf.put(index + 5, self.m21);
        buf.put(index + 6, self.m02);
        buf.put(index + 7, self.m12);
        buf.put(index + 8, self.m22);
        buf.put(index + 9, self.m03);
        buf.put(index + 10, self.m13);
        buf.put(index + 11, self.m23);
        return buf;
    }
    public Float3x4 loadCMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf) {
        self.m00 = (float) buf.get(index + 0);
        self.m10 = (float) buf.get(index + 1);
        self.m20 = (float) buf.get(index + 2);
        self.m01 = (float) buf.get(index + 3);
        self.m11 = (float) buf.get(index + 4);
        self.m21 = (float) buf.get(index + 5);
        self.m02 = (float) buf.get(index + 6);
        self.m12 = (float) buf.get(index + 7);
        self.m22 = (float) buf.get(index + 8);
        self.m03 = (float) buf.get(index + 9);
        self.m13 = (float) buf.get(index + 10);
        self.m23 = (float) buf.get(index + 11);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, self.m20);
        buf.putDouble(index + 24, self.m01);
        buf.putDouble(index + 32, self.m11);
        buf.putDouble(index + 40, self.m21);
        buf.putDouble(index + 48, self.m02);
        buf.putDouble(index + 56, self.m12);
        buf.putDouble(index + 64, self.m22);
        buf.putDouble(index + 72, self.m03);
        buf.putDouble(index + 80, self.m13);
        buf.putDouble(index + 88, self.m23);
        return buf;
    }
    public Float3x4 loadCMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf) {
        self.m00 = (float) buf.getDouble(index + 0);
        self.m10 = (float) buf.getDouble(index + 8);
        self.m20 = (float) buf.getDouble(index + 16);
        self.m01 = (float) buf.getDouble(index + 24);
        self.m11 = (float) buf.getDouble(index + 32);
        self.m21 = (float) buf.getDouble(index + 40);
        self.m02 = (float) buf.getDouble(index + 48);
        self.m12 = (float) buf.getDouble(index + 56);
        self.m22 = (float) buf.getDouble(index + 64);
        self.m03 = (float) buf.getDouble(index + 72);
        self.m13 = (float) buf.getDouble(index + 80);
        self.m23 = (float) buf.getDouble(index + 88);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float3x4Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m02);
        buf.put(index + 3, self.m03);
        buf.put(index + 4, self.m10);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, self.m12);
        buf.put(index + 7, self.m13);
        buf.put(index + 8, self.m20);
        buf.put(index + 9, self.m21);
        buf.put(index + 10, self.m22);
        buf.put(index + 11, self.m23);
        return buf;
    }
    public Float3x4 loadRMAbsolute(Float3x4Impl self, int index, FloatBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m01 = buf.get(index + 1);
        self.m02 = buf.get(index + 2);
        self.m03 = buf.get(index + 3);
        self.m10 = buf.get(index + 4);
        self.m11 = buf.get(index + 5);
        self.m12 = buf.get(index + 6);
        self.m13 = buf.get(index + 7);
        self.m20 = buf.get(index + 8);
        self.m21 = buf.get(index + 9);
        self.m22 = buf.get(index + 10);
        self.m23 = buf.get(index + 11);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float3x4Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m01);
        buf.putFloat(index + 8, self.m02);
        buf.putFloat(index + 12, self.m03);
        buf.putFloat(index + 16, self.m10);
        buf.putFloat(index + 20, self.m11);
        buf.putFloat(index + 24, self.m12);
        buf.putFloat(index + 28, self.m13);
        buf.putFloat(index + 32, self.m20);
        buf.putFloat(index + 36, self.m21);
        buf.putFloat(index + 40, self.m22);
        buf.putFloat(index + 44, self.m23);
        return buf;
    }
    public Float3x4 loadRMAbsolute(Float3x4Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getFloat(index + 0);
        self.m01 = buf.getFloat(index + 4);
        self.m02 = buf.getFloat(index + 8);
        self.m03 = buf.getFloat(index + 12);
        self.m10 = buf.getFloat(index + 16);
        self.m11 = buf.getFloat(index + 20);
        self.m12 = buf.getFloat(index + 24);
        self.m13 = buf.getFloat(index + 28);
        self.m20 = buf.getFloat(index + 32);
        self.m21 = buf.getFloat(index + 36);
        self.m22 = buf.getFloat(index + 40);
        self.m23 = buf.getFloat(index + 44);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m02);
        buf.put(index + 3, self.m03);
        buf.put(index + 4, self.m10);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, self.m12);
        buf.put(index + 7, self.m13);
        buf.put(index + 8, self.m20);
        buf.put(index + 9, self.m21);
        buf.put(index + 10, self.m22);
        buf.put(index + 11, self.m23);
        return buf;
    }
    public Float3x4 loadRMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf) {
        self.m00 = (float) buf.get(index + 0);
        self.m01 = (float) buf.get(index + 1);
        self.m02 = (float) buf.get(index + 2);
        self.m03 = (float) buf.get(index + 3);
        self.m10 = (float) buf.get(index + 4);
        self.m11 = (float) buf.get(index + 5);
        self.m12 = (float) buf.get(index + 6);
        self.m13 = (float) buf.get(index + 7);
        self.m20 = (float) buf.get(index + 8);
        self.m21 = (float) buf.get(index + 9);
        self.m22 = (float) buf.get(index + 10);
        self.m23 = (float) buf.get(index + 11);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(index + 16, self.m02);
        buf.putDouble(index + 24, self.m03);
        buf.putDouble(index + 32, self.m10);
        buf.putDouble(index + 40, self.m11);
        buf.putDouble(index + 48, self.m12);
        buf.putDouble(index + 56, self.m13);
        buf.putDouble(index + 64, self.m20);
        buf.putDouble(index + 72, self.m21);
        buf.putDouble(index + 80, self.m22);
        buf.putDouble(index + 88, self.m23);
        return buf;
    }
    public Float3x4 loadRMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf) {
        self.m00 = (float) buf.getDouble(index + 0);
        self.m01 = (float) buf.getDouble(index + 8);
        self.m02 = (float) buf.getDouble(index + 16);
        self.m03 = (float) buf.getDouble(index + 24);
        self.m10 = (float) buf.getDouble(index + 32);
        self.m11 = (float) buf.getDouble(index + 40);
        self.m12 = (float) buf.getDouble(index + 48);
        self.m13 = (float) buf.getDouble(index + 56);
        self.m20 = (float) buf.getDouble(index + 64);
        self.m21 = (float) buf.getDouble(index + 72);
        self.m22 = (float) buf.getDouble(index + 80);
        self.m23 = (float) buf.getDouble(index + 88);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Float3x4Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(_p1, self.m01);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p1 + 2, self.m21);
        buf.put(_p2, self.m02);
        buf.put(_p2 + 1, self.m12);
        buf.put(_p2 + 2, self.m22);
        buf.put(_p3, self.m03);
        buf.put(_p3 + 1, self.m13);
        buf.put(_p3 + 2, self.m23);
        return buf;
    }
    public Float3x4 loadCMAbsolute(Float3x4Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.m00 = buf.get(index);
        self.m10 = buf.get(index + 1);
        self.m20 = buf.get(index + 2);
        self.m01 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m21 = buf.get(_p1 + 2);
        self.m02 = buf.get(_p2);
        self.m12 = buf.get(_p2 + 1);
        self.m22 = buf.get(_p2 + 2);
        self.m03 = buf.get(_p3);
        self.m13 = buf.get(_p3 + 1);
        self.m23 = buf.get(_p3 + 2);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, self.m00);
        buf.putFloat(index + 4, self.m10);
        buf.putFloat(index + 8, self.m20);
        buf.putFloat(_p1, self.m01);
        buf.putFloat(_p1 + 4, self.m11);
        buf.putFloat(_p1 + 8, self.m21);
        buf.putFloat(_p2, self.m02);
        buf.putFloat(_p2 + 4, self.m12);
        buf.putFloat(_p2 + 8, self.m22);
        buf.putFloat(_p3, self.m03);
        buf.putFloat(_p3 + 4, self.m13);
        buf.putFloat(_p3 + 8, self.m23);
        return buf;
    }
    public Float3x4 loadCMAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.m00 = buf.getFloat(index);
        self.m10 = buf.getFloat(index + 4);
        self.m20 = buf.getFloat(index + 8);
        self.m01 = buf.getFloat(_p1);
        self.m11 = buf.getFloat(_p1 + 4);
        self.m21 = buf.getFloat(_p1 + 8);
        self.m02 = buf.getFloat(_p2);
        self.m12 = buf.getFloat(_p2 + 4);
        self.m22 = buf.getFloat(_p2 + 8);
        self.m03 = buf.getFloat(_p3);
        self.m13 = buf.getFloat(_p3 + 4);
        self.m23 = buf.getFloat(_p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(_p1, self.m01);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p1 + 2, self.m21);
        buf.put(_p2, self.m02);
        buf.put(_p2 + 1, self.m12);
        buf.put(_p2 + 2, self.m22);
        buf.put(_p3, self.m03);
        buf.put(_p3 + 1, self.m13);
        buf.put(_p3 + 2, self.m23);
        return buf;
    }
    public Float3x4 loadCMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.m00 = (float) buf.get(index);
        self.m10 = (float) buf.get(index + 1);
        self.m20 = (float) buf.get(index + 2);
        self.m01 = (float) buf.get(_p1);
        self.m11 = (float) buf.get(_p1 + 1);
        self.m21 = (float) buf.get(_p1 + 2);
        self.m02 = (float) buf.get(_p2);
        self.m12 = (float) buf.get(_p2 + 1);
        self.m22 = (float) buf.get(_p2 + 2);
        self.m03 = (float) buf.get(_p3);
        self.m13 = (float) buf.get(_p3 + 1);
        self.m23 = (float) buf.get(_p3 + 2);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putDouble(index, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, self.m20);
        buf.putDouble(_p1, self.m01);
        buf.putDouble(_p1 + 8, self.m11);
        buf.putDouble(_p1 + 16, self.m21);
        buf.putDouble(_p2, self.m02);
        buf.putDouble(_p2 + 8, self.m12);
        buf.putDouble(_p2 + 16, self.m22);
        buf.putDouble(_p3, self.m03);
        buf.putDouble(_p3 + 8, self.m13);
        buf.putDouble(_p3 + 16, self.m23);
        return buf;
    }
    public Float3x4 loadCMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.m00 = (float) buf.getDouble(index);
        self.m10 = (float) buf.getDouble(index + 8);
        self.m20 = (float) buf.getDouble(index + 16);
        self.m01 = (float) buf.getDouble(_p1);
        self.m11 = (float) buf.getDouble(_p1 + 8);
        self.m21 = (float) buf.getDouble(_p1 + 16);
        self.m02 = (float) buf.getDouble(_p2);
        self.m12 = (float) buf.getDouble(_p2 + 8);
        self.m22 = (float) buf.getDouble(_p2 + 16);
        self.m03 = (float) buf.getDouble(_p3);
        self.m13 = (float) buf.getDouble(_p3 + 8);
        self.m23 = (float) buf.getDouble(_p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float3x4Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m02);
        buf.put(index + 3, self.m03);
        buf.put(_p1, self.m10);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p1 + 2, self.m12);
        buf.put(_p1 + 3, self.m13);
        buf.put(_p2, self.m20);
        buf.put(_p2 + 1, self.m21);
        buf.put(_p2 + 2, self.m22);
        buf.put(_p2 + 3, self.m23);
        return buf;
    }
    public Float3x4 loadRMAbsolute(Float3x4Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.m00 = buf.get(index);
        self.m01 = buf.get(index + 1);
        self.m02 = buf.get(index + 2);
        self.m03 = buf.get(index + 3);
        self.m10 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.m12 = buf.get(_p1 + 2);
        self.m13 = buf.get(_p1 + 3);
        self.m20 = buf.get(_p2);
        self.m21 = buf.get(_p2 + 1);
        self.m22 = buf.get(_p2 + 2);
        self.m23 = buf.get(_p2 + 3);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putFloat(index, self.m00);
        buf.putFloat(index + 4, self.m01);
        buf.putFloat(index + 8, self.m02);
        buf.putFloat(index + 12, self.m03);
        buf.putFloat(_p1, self.m10);
        buf.putFloat(_p1 + 4, self.m11);
        buf.putFloat(_p1 + 8, self.m12);
        buf.putFloat(_p1 + 12, self.m13);
        buf.putFloat(_p2, self.m20);
        buf.putFloat(_p2 + 4, self.m21);
        buf.putFloat(_p2 + 8, self.m22);
        buf.putFloat(_p2 + 12, self.m23);
        return buf;
    }
    public Float3x4 loadRMAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.m00 = buf.getFloat(index);
        self.m01 = buf.getFloat(index + 4);
        self.m02 = buf.getFloat(index + 8);
        self.m03 = buf.getFloat(index + 12);
        self.m10 = buf.getFloat(_p1);
        self.m11 = buf.getFloat(_p1 + 4);
        self.m12 = buf.getFloat(_p1 + 8);
        self.m13 = buf.getFloat(_p1 + 12);
        self.m20 = buf.getFloat(_p2);
        self.m21 = buf.getFloat(_p2 + 4);
        self.m22 = buf.getFloat(_p2 + 8);
        self.m23 = buf.getFloat(_p2 + 12);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m02);
        buf.put(index + 3, self.m03);
        buf.put(_p1, self.m10);
        buf.put(_p1 + 1, self.m11);
        buf.put(_p1 + 2, self.m12);
        buf.put(_p1 + 3, self.m13);
        buf.put(_p2, self.m20);
        buf.put(_p2 + 1, self.m21);
        buf.put(_p2 + 2, self.m22);
        buf.put(_p2 + 3, self.m23);
        return buf;
    }
    public Float3x4 loadRMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.m00 = (float) buf.get(index);
        self.m01 = (float) buf.get(index + 1);
        self.m02 = (float) buf.get(index + 2);
        self.m03 = (float) buf.get(index + 3);
        self.m10 = (float) buf.get(_p1);
        self.m11 = (float) buf.get(_p1 + 1);
        self.m12 = (float) buf.get(_p1 + 2);
        self.m13 = (float) buf.get(_p1 + 3);
        self.m20 = (float) buf.get(_p2);
        self.m21 = (float) buf.get(_p2 + 1);
        self.m22 = (float) buf.get(_p2 + 2);
        self.m23 = (float) buf.get(_p2 + 3);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putDouble(index, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(index + 16, self.m02);
        buf.putDouble(index + 24, self.m03);
        buf.putDouble(_p1, self.m10);
        buf.putDouble(_p1 + 8, self.m11);
        buf.putDouble(_p1 + 16, self.m12);
        buf.putDouble(_p1 + 24, self.m13);
        buf.putDouble(_p2, self.m20);
        buf.putDouble(_p2 + 8, self.m21);
        buf.putDouble(_p2 + 16, self.m22);
        buf.putDouble(_p2 + 24, self.m23);
        return buf;
    }
    public Float3x4 loadRMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.m00 = (float) buf.getDouble(index);
        self.m01 = (float) buf.getDouble(index + 8);
        self.m02 = (float) buf.getDouble(index + 16);
        self.m03 = (float) buf.getDouble(index + 24);
        self.m10 = (float) buf.getDouble(_p1);
        self.m11 = (float) buf.getDouble(_p1 + 8);
        self.m12 = (float) buf.getDouble(_p1 + 16);
        self.m13 = (float) buf.getDouble(_p1 + 24);
        self.m20 = (float) buf.getDouble(_p2);
        self.m21 = (float) buf.getDouble(_p2 + 8);
        self.m22 = (float) buf.getDouble(_p2 + 16);
        self.m23 = (float) buf.getDouble(_p2 + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCM4x4Absolute(Float3x4Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(index + 3, 0.0f);
        buf.put(index + 4, self.m01);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, self.m21);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, self.m02);
        buf.put(index + 9, self.m12);
        buf.put(index + 10, self.m22);
        buf.put(index + 11, 0.0f);
        buf.put(index + 12, self.m03);
        buf.put(index + 13, self.m13);
        buf.put(index + 14, self.m23);
        buf.put(index + 15, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM4x4Absolute(Float3x4Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m10);
        buf.putFloat(index + 8, self.m20);
        buf.putFloat(index + 12, 0.0f);
        buf.putFloat(index + 16, self.m01);
        buf.putFloat(index + 20, self.m11);
        buf.putFloat(index + 24, self.m21);
        buf.putFloat(index + 28, 0.0f);
        buf.putFloat(index + 32, self.m02);
        buf.putFloat(index + 36, self.m12);
        buf.putFloat(index + 40, self.m22);
        buf.putFloat(index + 44, 0.0f);
        buf.putFloat(index + 48, self.m03);
        buf.putFloat(index + 52, self.m13);
        buf.putFloat(index + 56, self.m23);
        buf.putFloat(index + 60, 1.0f);
        return buf;
    }
    public DoubleBuffer storeCM4x4Absolute(Float3x4Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m20);
        buf.put(index + 3, 0.0f);
        buf.put(index + 4, self.m01);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, self.m21);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, self.m02);
        buf.put(index + 9, self.m12);
        buf.put(index + 10, self.m22);
        buf.put(index + 11, 0.0f);
        buf.put(index + 12, self.m03);
        buf.put(index + 13, self.m13);
        buf.put(index + 14, self.m23);
        buf.put(index + 15, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM4x4DoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, self.m20);
        buf.putDouble(index + 24, 0.0f);
        buf.putDouble(index + 32, self.m01);
        buf.putDouble(index + 40, self.m11);
        buf.putDouble(index + 48, self.m21);
        buf.putDouble(index + 56, 0.0f);
        buf.putDouble(index + 64, self.m02);
        buf.putDouble(index + 72, self.m12);
        buf.putDouble(index + 80, self.m22);
        buf.putDouble(index + 88, 0.0f);
        buf.putDouble(index + 96, self.m03);
        buf.putDouble(index + 104, self.m13);
        buf.putDouble(index + 112, self.m23);
        buf.putDouble(index + 120, 1.0f);
        return buf;
    }
    public FloatBuffer storeRM4x4Absolute(Float3x4Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m02);
        buf.put(index + 3, self.m03);
        buf.put(index + 4, self.m10);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, self.m12);
        buf.put(index + 7, self.m13);
        buf.put(index + 8, self.m20);
        buf.put(index + 9, self.m21);
        buf.put(index + 10, self.m22);
        buf.put(index + 11, self.m23);
        buf.put(index + 12, 0.0f);
        buf.put(index + 13, 0.0f);
        buf.put(index + 14, 0.0f);
        buf.put(index + 15, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM4x4Absolute(Float3x4Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m01);
        buf.putFloat(index + 8, self.m02);
        buf.putFloat(index + 12, self.m03);
        buf.putFloat(index + 16, self.m10);
        buf.putFloat(index + 20, self.m11);
        buf.putFloat(index + 24, self.m12);
        buf.putFloat(index + 28, self.m13);
        buf.putFloat(index + 32, self.m20);
        buf.putFloat(index + 36, self.m21);
        buf.putFloat(index + 40, self.m22);
        buf.putFloat(index + 44, self.m23);
        buf.putFloat(index + 48, 0.0f);
        buf.putFloat(index + 52, 0.0f);
        buf.putFloat(index + 56, 0.0f);
        buf.putFloat(index + 60, 1.0f);
        return buf;
    }
    public DoubleBuffer storeRM4x4Absolute(Float3x4Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m02);
        buf.put(index + 3, self.m03);
        buf.put(index + 4, self.m10);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, self.m12);
        buf.put(index + 7, self.m13);
        buf.put(index + 8, self.m20);
        buf.put(index + 9, self.m21);
        buf.put(index + 10, self.m22);
        buf.put(index + 11, self.m23);
        buf.put(index + 12, 0.0f);
        buf.put(index + 13, 0.0f);
        buf.put(index + 14, 0.0f);
        buf.put(index + 15, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM4x4DoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(index + 16, self.m02);
        buf.putDouble(index + 24, self.m03);
        buf.putDouble(index + 32, self.m10);
        buf.putDouble(index + 40, self.m11);
        buf.putDouble(index + 48, self.m12);
        buf.putDouble(index + 56, self.m13);
        buf.putDouble(index + 64, self.m20);
        buf.putDouble(index + 72, self.m21);
        buf.putDouble(index + 80, self.m22);
        buf.putDouble(index + 88, self.m23);
        buf.putDouble(index + 96, 0.0f);
        buf.putDouble(index + 104, 0.0f);
        buf.putDouble(index + 112, 0.0f);
        buf.putDouble(index + 120, 1.0f);
        return buf;
    }
}
