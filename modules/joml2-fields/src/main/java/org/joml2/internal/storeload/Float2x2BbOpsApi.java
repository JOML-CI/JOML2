// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float2x2BbOpsApi implements Float2x2BbOps {
    public FloatBuffer storeCMAbsolute(Float2x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m01);
        buf.put(index + 3, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, FloatBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m10 = buf.get(index + 1);
        self.m01 = buf.get(index + 2);
        self.m11 = buf.get(index + 3);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m10);
        buf.putFloat(index + 8, self.m01);
        buf.putFloat(index + 12, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getFloat(index + 0);
        self.m10 = buf.getFloat(index + 4);
        self.m01 = buf.getFloat(index + 8);
        self.m11 = buf.getFloat(index + 12);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, self.m01);
        buf.put(index + 3, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        self.m00 = (float) buf.get(index + 0);
        self.m10 = (float) buf.get(index + 1);
        self.m01 = (float) buf.get(index + 2);
        self.m11 = (float) buf.get(index + 3);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, self.m01);
        buf.putDouble(index + 24, self.m11);
        return buf;
    }
    public Float2x2 loadCMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        self.m00 = (float) buf.getDouble(index + 0);
        self.m10 = (float) buf.getDouble(index + 8);
        self.m01 = (float) buf.getDouble(index + 16);
        self.m11 = (float) buf.getDouble(index + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float2x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m10);
        buf.put(index + 3, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, FloatBuffer buf) {
        self.m00 = buf.get(index + 0);
        self.m01 = buf.get(index + 1);
        self.m10 = buf.get(index + 2);
        self.m11 = buf.get(index + 3);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m01);
        buf.putFloat(index + 8, self.m10);
        buf.putFloat(index + 12, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        self.m00 = buf.getFloat(index + 0);
        self.m01 = buf.getFloat(index + 4);
        self.m10 = buf.getFloat(index + 8);
        self.m11 = buf.getFloat(index + 12);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, self.m10);
        buf.put(index + 3, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        self.m00 = (float) buf.get(index + 0);
        self.m01 = (float) buf.get(index + 1);
        self.m10 = (float) buf.get(index + 2);
        self.m11 = (float) buf.get(index + 3);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(index + 16, self.m10);
        buf.putDouble(index + 24, self.m11);
        return buf;
    }
    public Float2x2 loadRMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        self.m00 = (float) buf.getDouble(index + 0);
        self.m01 = (float) buf.getDouble(index + 8);
        self.m10 = (float) buf.getDouble(index + 16);
        self.m11 = (float) buf.getDouble(index + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Float2x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(_p1, self.m01);
        buf.put(_p1 + 1, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        self.m00 = buf.get(index);
        self.m10 = buf.get(index + 1);
        self.m01 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        buf.putFloat(index, self.m00);
        buf.putFloat(index + 4, self.m10);
        buf.putFloat(_p1, self.m01);
        buf.putFloat(_p1 + 4, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        self.m00 = buf.getFloat(index);
        self.m10 = buf.getFloat(index + 4);
        self.m01 = buf.getFloat(_p1);
        self.m11 = buf.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(_p1, self.m01);
        buf.put(_p1 + 1, self.m11);
        return buf;
    }
    public Float2x2 loadCMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        self.m00 = (float) buf.get(index);
        self.m10 = (float) buf.get(index + 1);
        self.m01 = (float) buf.get(_p1);
        self.m11 = (float) buf.get(_p1 + 1);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        buf.putDouble(index, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(_p1, self.m01);
        buf.putDouble(_p1 + 8, self.m11);
        return buf;
    }
    public Float2x2 loadCMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        self.m00 = (float) buf.getDouble(index);
        self.m10 = (float) buf.getDouble(index + 8);
        self.m01 = (float) buf.getDouble(_p1);
        self.m11 = (float) buf.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float2x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(_p1, self.m10);
        buf.put(_p1 + 1, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        self.m00 = buf.get(index);
        self.m01 = buf.get(index + 1);
        self.m10 = buf.get(_p1);
        self.m11 = buf.get(_p1 + 1);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        buf.putFloat(index, self.m00);
        buf.putFloat(index + 4, self.m01);
        buf.putFloat(_p1, self.m10);
        buf.putFloat(_p1 + 4, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        self.m00 = buf.getFloat(index);
        self.m01 = buf.getFloat(index + 4);
        self.m10 = buf.getFloat(_p1);
        self.m11 = buf.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(_p1, self.m10);
        buf.put(_p1 + 1, self.m11);
        return buf;
    }
    public Float2x2 loadRMAbsolute(Float2x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        self.m00 = (float) buf.get(index);
        self.m01 = (float) buf.get(index + 1);
        self.m10 = (float) buf.get(_p1);
        self.m11 = (float) buf.get(_p1 + 1);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        buf.putDouble(index, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(_p1, self.m10);
        buf.putDouble(_p1 + 8, self.m11);
        return buf;
    }
    public Float2x2 loadRMDoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        self.m00 = (float) buf.getDouble(index);
        self.m01 = (float) buf.getDouble(index + 8);
        self.m10 = (float) buf.getDouble(_p1);
        self.m11 = (float) buf.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCM3x3Absolute(Float2x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, self.m01);
        buf.put(index + 4, self.m11);
        buf.put(index + 5, 0.0f);
        buf.put(index + 6, 0.0f);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM3x3Absolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m10);
        buf.putFloat(index + 8, 0.0f);
        buf.putFloat(index + 12, self.m01);
        buf.putFloat(index + 16, self.m11);
        buf.putFloat(index + 20, 0.0f);
        buf.putFloat(index + 24, 0.0f);
        buf.putFloat(index + 28, 0.0f);
        buf.putFloat(index + 32, 1.0f);
        return buf;
    }
    public DoubleBuffer storeCM3x3Absolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, self.m01);
        buf.put(index + 4, self.m11);
        buf.put(index + 5, 0.0f);
        buf.put(index + 6, 0.0f);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM3x3DoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, 0.0f);
        buf.putDouble(index + 24, self.m01);
        buf.putDouble(index + 32, self.m11);
        buf.putDouble(index + 40, 0.0f);
        buf.putDouble(index + 48, 0.0f);
        buf.putDouble(index + 56, 0.0f);
        buf.putDouble(index + 64, 1.0f);
        return buf;
    }
    public FloatBuffer storeRM3x3Absolute(Float2x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, self.m10);
        buf.put(index + 4, self.m11);
        buf.put(index + 5, 0.0f);
        buf.put(index + 6, 0.0f);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM3x3Absolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m01);
        buf.putFloat(index + 8, 0.0f);
        buf.putFloat(index + 12, self.m10);
        buf.putFloat(index + 16, self.m11);
        buf.putFloat(index + 20, 0.0f);
        buf.putFloat(index + 24, 0.0f);
        buf.putFloat(index + 28, 0.0f);
        buf.putFloat(index + 32, 1.0f);
        return buf;
    }
    public DoubleBuffer storeRM3x3Absolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, self.m10);
        buf.put(index + 4, self.m11);
        buf.put(index + 5, 0.0f);
        buf.put(index + 6, 0.0f);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM3x3DoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(index + 16, 0.0f);
        buf.putDouble(index + 24, self.m10);
        buf.putDouble(index + 32, self.m11);
        buf.putDouble(index + 40, 0.0f);
        buf.putDouble(index + 48, 0.0f);
        buf.putDouble(index + 56, 0.0f);
        buf.putDouble(index + 64, 1.0f);
        return buf;
    }
    public FloatBuffer storeCM4x4Absolute(Float2x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, 0.0f);
        buf.put(index + 4, self.m01);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, 0.0f);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, 0.0f);
        buf.put(index + 9, 0.0f);
        buf.put(index + 10, 1.0f);
        buf.put(index + 11, 0.0f);
        buf.put(index + 12, 0.0f);
        buf.put(index + 13, 0.0f);
        buf.put(index + 14, 0.0f);
        buf.put(index + 15, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM4x4Absolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m10);
        buf.putFloat(index + 8, 0.0f);
        buf.putFloat(index + 12, 0.0f);
        buf.putFloat(index + 16, self.m01);
        buf.putFloat(index + 20, self.m11);
        buf.putFloat(index + 24, 0.0f);
        buf.putFloat(index + 28, 0.0f);
        buf.putFloat(index + 32, 0.0f);
        buf.putFloat(index + 36, 0.0f);
        buf.putFloat(index + 40, 1.0f);
        buf.putFloat(index + 44, 0.0f);
        buf.putFloat(index + 48, 0.0f);
        buf.putFloat(index + 52, 0.0f);
        buf.putFloat(index + 56, 0.0f);
        buf.putFloat(index + 60, 1.0f);
        return buf;
    }
    public DoubleBuffer storeCM4x4Absolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m10);
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, 0.0f);
        buf.put(index + 4, self.m01);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, 0.0f);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, 0.0f);
        buf.put(index + 9, 0.0f);
        buf.put(index + 10, 1.0f);
        buf.put(index + 11, 0.0f);
        buf.put(index + 12, 0.0f);
        buf.put(index + 13, 0.0f);
        buf.put(index + 14, 0.0f);
        buf.put(index + 15, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM4x4DoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m10);
        buf.putDouble(index + 16, 0.0f);
        buf.putDouble(index + 24, 0.0f);
        buf.putDouble(index + 32, self.m01);
        buf.putDouble(index + 40, self.m11);
        buf.putDouble(index + 48, 0.0f);
        buf.putDouble(index + 56, 0.0f);
        buf.putDouble(index + 64, 0.0f);
        buf.putDouble(index + 72, 0.0f);
        buf.putDouble(index + 80, 1.0f);
        buf.putDouble(index + 88, 0.0f);
        buf.putDouble(index + 96, 0.0f);
        buf.putDouble(index + 104, 0.0f);
        buf.putDouble(index + 112, 0.0f);
        buf.putDouble(index + 120, 1.0f);
        return buf;
    }
    public FloatBuffer storeRM4x4Absolute(Float2x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, 0.0f);
        buf.put(index + 4, self.m10);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, 0.0f);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, 0.0f);
        buf.put(index + 9, 0.0f);
        buf.put(index + 10, 1.0f);
        buf.put(index + 11, 0.0f);
        buf.put(index + 12, 0.0f);
        buf.put(index + 13, 0.0f);
        buf.put(index + 14, 0.0f);
        buf.put(index + 15, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM4x4Absolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00);
        buf.putFloat(index + 4, self.m01);
        buf.putFloat(index + 8, 0.0f);
        buf.putFloat(index + 12, 0.0f);
        buf.putFloat(index + 16, self.m10);
        buf.putFloat(index + 20, self.m11);
        buf.putFloat(index + 24, 0.0f);
        buf.putFloat(index + 28, 0.0f);
        buf.putFloat(index + 32, 0.0f);
        buf.putFloat(index + 36, 0.0f);
        buf.putFloat(index + 40, 1.0f);
        buf.putFloat(index + 44, 0.0f);
        buf.putFloat(index + 48, 0.0f);
        buf.putFloat(index + 52, 0.0f);
        buf.putFloat(index + 56, 0.0f);
        buf.putFloat(index + 60, 1.0f);
        return buf;
    }
    public DoubleBuffer storeRM4x4Absolute(Float2x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00);
        buf.put(index + 1, self.m01);
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, 0.0f);
        buf.put(index + 4, self.m10);
        buf.put(index + 5, self.m11);
        buf.put(index + 6, 0.0f);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, 0.0f);
        buf.put(index + 9, 0.0f);
        buf.put(index + 10, 1.0f);
        buf.put(index + 11, 0.0f);
        buf.put(index + 12, 0.0f);
        buf.put(index + 13, 0.0f);
        buf.put(index + 14, 0.0f);
        buf.put(index + 15, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM4x4DoubleAbsolute(Float2x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00);
        buf.putDouble(index + 8, self.m01);
        buf.putDouble(index + 16, 0.0f);
        buf.putDouble(index + 24, 0.0f);
        buf.putDouble(index + 32, self.m10);
        buf.putDouble(index + 40, self.m11);
        buf.putDouble(index + 48, 0.0f);
        buf.putDouble(index + 56, 0.0f);
        buf.putDouble(index + 64, 0.0f);
        buf.putDouble(index + 72, 0.0f);
        buf.putDouble(index + 80, 1.0f);
        buf.putDouble(index + 88, 0.0f);
        buf.putDouble(index + 96, 0.0f);
        buf.putDouble(index + 104, 0.0f);
        buf.putDouble(index + 112, 0.0f);
        buf.putDouble(index + 120, 1.0f);
        return buf;
    }
}
