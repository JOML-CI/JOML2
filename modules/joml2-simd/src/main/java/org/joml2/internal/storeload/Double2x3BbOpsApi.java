// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Double2x3BbOpsApi implements Double2x3BbOps {
    public DoubleBuffer storeCMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(index + 4, self.data[4]);
        buf.put(index + 5, self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        self.data[4] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(index + 16, self.data[2]);
        buf.putDouble(index + 24, self.data[3]);
        buf.putDouble(index + 32, self.data[4]);
        buf.putDouble(index + 40, self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getDouble(index + 0);
        self.data[1] = buf.getDouble(index + 8);
        self.data[2] = buf.getDouble(index + 16);
        self.data[3] = buf.getDouble(index + 24);
        self.data[4] = buf.getDouble(index + 32);
        self.data[5] = buf.getDouble(index + 40);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double2x3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[1]);
        buf.put(index + 2, (float) self.data[2]);
        buf.put(index + 3, (float) self.data[3]);
        buf.put(index + 4, (float) self.data[4]);
        buf.put(index + 5, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        self.data[4] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[1]);
        buf.putFloat(index + 8, (float) self.data[2]);
        buf.putFloat(index + 12, (float) self.data[3]);
        buf.putFloat(index + 16, (float) self.data[4]);
        buf.putFloat(index + 20, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadCMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[1] = buf.getFloat(index + 4);
        self.data[2] = buf.getFloat(index + 8);
        self.data[3] = buf.getFloat(index + 12);
        self.data[4] = buf.getFloat(index + 16);
        self.data[5] = buf.getFloat(index + 20);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[2]);
        buf.put(index + 2, self.data[4]);
        buf.put(index + 3, self.data[1]);
        buf.put(index + 4, self.data[3]);
        buf.put(index + 5, self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[2] = buf.get(index + 1);
        self.data[4] = buf.get(index + 2);
        self.data[1] = buf.get(index + 3);
        self.data[3] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[2]);
        buf.putDouble(index + 16, self.data[4]);
        buf.putDouble(index + 24, self.data[1]);
        buf.putDouble(index + 32, self.data[3]);
        buf.putDouble(index + 40, self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getDouble(index + 0);
        self.data[2] = buf.getDouble(index + 8);
        self.data[4] = buf.getDouble(index + 16);
        self.data[1] = buf.getDouble(index + 24);
        self.data[3] = buf.getDouble(index + 32);
        self.data[5] = buf.getDouble(index + 40);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double2x3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[2]);
        buf.put(index + 2, (float) self.data[4]);
        buf.put(index + 3, (float) self.data[1]);
        buf.put(index + 4, (float) self.data[3]);
        buf.put(index + 5, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[2] = buf.get(index + 1);
        self.data[4] = buf.get(index + 2);
        self.data[1] = buf.get(index + 3);
        self.data[3] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[2]);
        buf.putFloat(index + 8, (float) self.data[4]);
        buf.putFloat(index + 12, (float) self.data[1]);
        buf.putFloat(index + 16, (float) self.data[3]);
        buf.putFloat(index + 20, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadRMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[2] = buf.getFloat(index + 4);
        self.data[4] = buf.getFloat(index + 8);
        self.data[1] = buf.getFloat(index + 12);
        self.data[3] = buf.getFloat(index + 16);
        self.data[5] = buf.getFloat(index + 20);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(_p1, self.data[2]);
        buf.put(_p1 + 1, self.data[3]);
        buf.put(_p2, self.data[4]);
        buf.put(_p2 + 1, self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.data[0] = buf.get(index);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(_p1);
        self.data[3] = buf.get(_p1 + 1);
        self.data[4] = buf.get(_p2);
        self.data[5] = buf.get(_p2 + 1);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putDouble(index, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(_p1, self.data[2]);
        buf.putDouble(_p1 + 8, self.data[3]);
        buf.putDouble(_p2, self.data[4]);
        buf.putDouble(_p2 + 8, self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.data[0] = buf.getDouble(index);
        self.data[1] = buf.getDouble(index + 8);
        self.data[2] = buf.getDouble(_p1);
        self.data[3] = buf.getDouble(_p1 + 8);
        self.data[4] = buf.getDouble(_p2);
        self.data[5] = buf.getDouble(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double2x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[1]);
        buf.put(_p1, (float) self.data[2]);
        buf.put(_p1 + 1, (float) self.data[3]);
        buf.put(_p2, (float) self.data[4]);
        buf.put(_p2 + 1, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadCMAbsolute(Double2x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.data[0] = buf.get(index);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(_p1);
        self.data[3] = buf.get(_p1 + 1);
        self.data[4] = buf.get(_p2);
        self.data[5] = buf.get(_p2 + 1);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putFloat(index, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[1]);
        buf.putFloat(_p1, (float) self.data[2]);
        buf.putFloat(_p1 + 4, (float) self.data[3]);
        buf.putFloat(_p2, (float) self.data[4]);
        buf.putFloat(_p2 + 4, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadCMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.data[0] = buf.getFloat(index);
        self.data[1] = buf.getFloat(index + 4);
        self.data[2] = buf.getFloat(_p1);
        self.data[3] = buf.getFloat(_p1 + 4);
        self.data[4] = buf.getFloat(_p2);
        self.data[5] = buf.getFloat(_p2 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[2]);
        buf.put(index + 2, self.data[4]);
        buf.put(_p1, self.data[1]);
        buf.put(_p1 + 1, self.data[3]);
        buf.put(_p1 + 2, self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        self.data[0] = buf.get(index);
        self.data[2] = buf.get(index + 1);
        self.data[4] = buf.get(index + 2);
        self.data[1] = buf.get(_p1);
        self.data[3] = buf.get(_p1 + 1);
        self.data[5] = buf.get(_p1 + 2);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        buf.putDouble(index, self.data[0]);
        buf.putDouble(index + 8, self.data[2]);
        buf.putDouble(index + 16, self.data[4]);
        buf.putDouble(_p1, self.data[1]);
        buf.putDouble(_p1 + 8, self.data[3]);
        buf.putDouble(_p1 + 16, self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        self.data[0] = buf.getDouble(index);
        self.data[2] = buf.getDouble(index + 8);
        self.data[4] = buf.getDouble(index + 16);
        self.data[1] = buf.getDouble(_p1);
        self.data[3] = buf.getDouble(_p1 + 8);
        self.data[5] = buf.getDouble(_p1 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double2x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[2]);
        buf.put(index + 2, (float) self.data[4]);
        buf.put(_p1, (float) self.data[1]);
        buf.put(_p1 + 1, (float) self.data[3]);
        buf.put(_p1 + 2, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadRMAbsolute(Double2x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        self.data[0] = buf.get(index);
        self.data[2] = buf.get(index + 1);
        self.data[4] = buf.get(index + 2);
        self.data[1] = buf.get(_p1);
        self.data[3] = buf.get(_p1 + 1);
        self.data[5] = buf.get(_p1 + 2);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        buf.putFloat(index, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[2]);
        buf.putFloat(index + 8, (float) self.data[4]);
        buf.putFloat(_p1, (float) self.data[1]);
        buf.putFloat(_p1 + 4, (float) self.data[3]);
        buf.putFloat(_p1 + 8, (float) self.data[5]);
        return buf;
    }
    public Double2x3 loadRMFloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        self.data[0] = buf.getFloat(index);
        self.data[2] = buf.getFloat(index + 4);
        self.data[4] = buf.getFloat(index + 8);
        self.data[1] = buf.getFloat(_p1);
        self.data[3] = buf.getFloat(_p1 + 4);
        self.data[5] = buf.getFloat(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCM3x3Absolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, 0.0);
        buf.put(index + 3, self.data[2]);
        buf.put(index + 4, self.data[3]);
        buf.put(index + 5, 0.0);
        buf.put(index + 6, self.data[4]);
        buf.put(index + 7, self.data[5]);
        buf.put(index + 8, 1.0);
        return buf;
    }
    public ByteBuffer storeCM3x3Absolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(index + 16, 0.0);
        buf.putDouble(index + 24, self.data[2]);
        buf.putDouble(index + 32, self.data[3]);
        buf.putDouble(index + 40, 0.0);
        buf.putDouble(index + 48, self.data[4]);
        buf.putDouble(index + 56, self.data[5]);
        buf.putDouble(index + 64, 1.0);
        return buf;
    }
    public FloatBuffer storeCM3x3Absolute(Double2x3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[1]);
        buf.put(index + 2, (float) 0.0);
        buf.put(index + 3, (float) self.data[2]);
        buf.put(index + 4, (float) self.data[3]);
        buf.put(index + 5, (float) 0.0);
        buf.put(index + 6, (float) self.data[4]);
        buf.put(index + 7, (float) self.data[5]);
        buf.put(index + 8, (float) 1.0);
        return buf;
    }
    public ByteBuffer storeCM3x3FloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[1]);
        buf.putFloat(index + 8, (float) 0.0);
        buf.putFloat(index + 12, (float) self.data[2]);
        buf.putFloat(index + 16, (float) self.data[3]);
        buf.putFloat(index + 20, (float) 0.0);
        buf.putFloat(index + 24, (float) self.data[4]);
        buf.putFloat(index + 28, (float) self.data[5]);
        buf.putFloat(index + 32, (float) 1.0);
        return buf;
    }
    public DoubleBuffer storeRM3x3Absolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[2]);
        buf.put(index + 2, self.data[4]);
        buf.put(index + 3, self.data[1]);
        buf.put(index + 4, self.data[3]);
        buf.put(index + 5, self.data[5]);
        buf.put(index + 6, 0.0);
        buf.put(index + 7, 0.0);
        buf.put(index + 8, 1.0);
        return buf;
    }
    public ByteBuffer storeRM3x3Absolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[2]);
        buf.putDouble(index + 16, self.data[4]);
        buf.putDouble(index + 24, self.data[1]);
        buf.putDouble(index + 32, self.data[3]);
        buf.putDouble(index + 40, self.data[5]);
        buf.putDouble(index + 48, 0.0);
        buf.putDouble(index + 56, 0.0);
        buf.putDouble(index + 64, 1.0);
        return buf;
    }
    public FloatBuffer storeRM3x3Absolute(Double2x3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[2]);
        buf.put(index + 2, (float) self.data[4]);
        buf.put(index + 3, (float) self.data[1]);
        buf.put(index + 4, (float) self.data[3]);
        buf.put(index + 5, (float) self.data[5]);
        buf.put(index + 6, (float) 0.0);
        buf.put(index + 7, (float) 0.0);
        buf.put(index + 8, (float) 1.0);
        return buf;
    }
    public ByteBuffer storeRM3x3FloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[2]);
        buf.putFloat(index + 8, (float) self.data[4]);
        buf.putFloat(index + 12, (float) self.data[1]);
        buf.putFloat(index + 16, (float) self.data[3]);
        buf.putFloat(index + 20, (float) self.data[5]);
        buf.putFloat(index + 24, (float) 0.0);
        buf.putFloat(index + 28, (float) 0.0);
        buf.putFloat(index + 32, (float) 1.0);
        return buf;
    }
    public DoubleBuffer storeCM4x4Absolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, 0.0);
        buf.put(index + 3, 0.0);
        buf.put(index + 4, self.data[2]);
        buf.put(index + 5, self.data[3]);
        buf.put(index + 6, 0.0);
        buf.put(index + 7, 0.0);
        buf.put(index + 8, 0.0);
        buf.put(index + 9, 0.0);
        buf.put(index + 10, 1.0);
        buf.put(index + 11, 0.0);
        buf.put(index + 12, self.data[4]);
        buf.put(index + 13, self.data[5]);
        buf.put(index + 14, 0.0);
        buf.put(index + 15, 1.0);
        return buf;
    }
    public ByteBuffer storeCM4x4Absolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(index + 16, 0.0);
        buf.putDouble(index + 24, 0.0);
        buf.putDouble(index + 32, self.data[2]);
        buf.putDouble(index + 40, self.data[3]);
        buf.putDouble(index + 48, 0.0);
        buf.putDouble(index + 56, 0.0);
        buf.putDouble(index + 64, 0.0);
        buf.putDouble(index + 72, 0.0);
        buf.putDouble(index + 80, 1.0);
        buf.putDouble(index + 88, 0.0);
        buf.putDouble(index + 96, self.data[4]);
        buf.putDouble(index + 104, self.data[5]);
        buf.putDouble(index + 112, 0.0);
        buf.putDouble(index + 120, 1.0);
        return buf;
    }
    public FloatBuffer storeCM4x4Absolute(Double2x3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[1]);
        buf.put(index + 2, (float) 0.0);
        buf.put(index + 3, (float) 0.0);
        buf.put(index + 4, (float) self.data[2]);
        buf.put(index + 5, (float) self.data[3]);
        buf.put(index + 6, (float) 0.0);
        buf.put(index + 7, (float) 0.0);
        buf.put(index + 8, (float) 0.0);
        buf.put(index + 9, (float) 0.0);
        buf.put(index + 10, (float) 1.0);
        buf.put(index + 11, (float) 0.0);
        buf.put(index + 12, (float) self.data[4]);
        buf.put(index + 13, (float) self.data[5]);
        buf.put(index + 14, (float) 0.0);
        buf.put(index + 15, (float) 1.0);
        return buf;
    }
    public ByteBuffer storeCM4x4FloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[1]);
        buf.putFloat(index + 8, (float) 0.0);
        buf.putFloat(index + 12, (float) 0.0);
        buf.putFloat(index + 16, (float) self.data[2]);
        buf.putFloat(index + 20, (float) self.data[3]);
        buf.putFloat(index + 24, (float) 0.0);
        buf.putFloat(index + 28, (float) 0.0);
        buf.putFloat(index + 32, (float) 0.0);
        buf.putFloat(index + 36, (float) 0.0);
        buf.putFloat(index + 40, (float) 1.0);
        buf.putFloat(index + 44, (float) 0.0);
        buf.putFloat(index + 48, (float) self.data[4]);
        buf.putFloat(index + 52, (float) self.data[5]);
        buf.putFloat(index + 56, (float) 0.0);
        buf.putFloat(index + 60, (float) 1.0);
        return buf;
    }
    public DoubleBuffer storeRM4x4Absolute(Double2x3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[2]);
        buf.put(index + 2, 0.0);
        buf.put(index + 3, self.data[4]);
        buf.put(index + 4, self.data[1]);
        buf.put(index + 5, self.data[3]);
        buf.put(index + 6, 0.0);
        buf.put(index + 7, self.data[5]);
        buf.put(index + 8, 0.0);
        buf.put(index + 9, 0.0);
        buf.put(index + 10, 1.0);
        buf.put(index + 11, 0.0);
        buf.put(index + 12, 0.0);
        buf.put(index + 13, 0.0);
        buf.put(index + 14, 0.0);
        buf.put(index + 15, 1.0);
        return buf;
    }
    public ByteBuffer storeRM4x4Absolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[2]);
        buf.putDouble(index + 16, 0.0);
        buf.putDouble(index + 24, self.data[4]);
        buf.putDouble(index + 32, self.data[1]);
        buf.putDouble(index + 40, self.data[3]);
        buf.putDouble(index + 48, 0.0);
        buf.putDouble(index + 56, self.data[5]);
        buf.putDouble(index + 64, 0.0);
        buf.putDouble(index + 72, 0.0);
        buf.putDouble(index + 80, 1.0);
        buf.putDouble(index + 88, 0.0);
        buf.putDouble(index + 96, 0.0);
        buf.putDouble(index + 104, 0.0);
        buf.putDouble(index + 112, 0.0);
        buf.putDouble(index + 120, 1.0);
        return buf;
    }
    public FloatBuffer storeRM4x4Absolute(Double2x3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[2]);
        buf.put(index + 2, (float) 0.0);
        buf.put(index + 3, (float) self.data[4]);
        buf.put(index + 4, (float) self.data[1]);
        buf.put(index + 5, (float) self.data[3]);
        buf.put(index + 6, (float) 0.0);
        buf.put(index + 7, (float) self.data[5]);
        buf.put(index + 8, (float) 0.0);
        buf.put(index + 9, (float) 0.0);
        buf.put(index + 10, (float) 1.0);
        buf.put(index + 11, (float) 0.0);
        buf.put(index + 12, (float) 0.0);
        buf.put(index + 13, (float) 0.0);
        buf.put(index + 14, (float) 0.0);
        buf.put(index + 15, (float) 1.0);
        return buf;
    }
    public ByteBuffer storeRM4x4FloatAbsolute(Double2x3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[2]);
        buf.putFloat(index + 8, (float) 0.0);
        buf.putFloat(index + 12, (float) self.data[4]);
        buf.putFloat(index + 16, (float) self.data[1]);
        buf.putFloat(index + 20, (float) self.data[3]);
        buf.putFloat(index + 24, (float) 0.0);
        buf.putFloat(index + 28, (float) self.data[5]);
        buf.putFloat(index + 32, (float) 0.0);
        buf.putFloat(index + 36, (float) 0.0);
        buf.putFloat(index + 40, (float) 1.0);
        buf.putFloat(index + 44, (float) 0.0);
        buf.putFloat(index + 48, (float) 0.0);
        buf.putFloat(index + 52, (float) 0.0);
        buf.putFloat(index + 56, (float) 0.0);
        buf.putFloat(index + 60, (float) 1.0);
        return buf;
    }
}
