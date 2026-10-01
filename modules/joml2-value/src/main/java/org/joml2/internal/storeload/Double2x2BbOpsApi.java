// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Double2x2BbOpsApi extends Double2x2BbOps {
    public DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf) {
        buf.put(index, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, self.m01());
        buf.put(index + 3, self.m11());
        return buf;
    }
    public Double2x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index);
        double _c2 = buf.get(index + 1);
        double _c1 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(index + 16, self.m01());
        buf.putDouble(index + 24, self.m11());
        return buf;
    }
    public Double2x2 loadCMAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index);
        double _c2 = buf.getDouble(index + 8);
        double _c1 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf) {
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m10());
        buf.put(index + 2, (float) self.m01());
        buf.put(index + 3, (float) self.m11());
        return buf;
    }
    public Double2x2 loadCMAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index);
        double _c2 = buf.get(index + 1);
        double _c1 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m10());
        buf.putFloat(index + 8, (float) self.m01());
        buf.putFloat(index + 12, (float) self.m11());
        return buf;
    }
    public Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index);
        double _c2 = buf.getFloat(index + 4);
        double _c1 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf) {
        buf.put(index, self.m00());
        buf.put(index + 1, self.m01());
        buf.put(index + 2, self.m10());
        buf.put(index + 3, self.m11());
        return buf;
    }
    public Double2x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m01());
        buf.putDouble(index + 16, self.m10());
        buf.putDouble(index + 24, self.m11());
        return buf;
    }
    public Double2x2 loadRMAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf) {
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m01());
        buf.put(index + 2, (float) self.m10());
        buf.put(index + 3, (float) self.m11());
        return buf;
    }
    public Double2x2 loadRMAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m01());
        buf.putFloat(index + 8, (float) self.m10());
        buf.putFloat(index + 12, (float) self.m11());
        return buf;
    }
    public Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(_p1, self.m01());
        buf.put(_p1 + 1, self.m11());
        return buf;
    }
    public Double2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        double _c0 = buf.get(index);
        double _c2 = buf.get(index + 1);
        double _c1 = buf.get(_p1);
        double _c3 = buf.get(_p1 + 1);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(_p1, self.m01());
        buf.putDouble(_p1 + 8, self.m11());
        return buf;
    }
    public Double2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        double _c0 = buf.getDouble(index);
        double _c2 = buf.getDouble(index + 8);
        double _c1 = buf.getDouble(_p1);
        double _c3 = buf.getDouble(_p1 + 8);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m10());
        buf.put(_p1, (float) self.m01());
        buf.put(_p1 + 1, (float) self.m11());
        return buf;
    }
    public Double2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        double _c0 = buf.get(index);
        double _c2 = buf.get(index + 1);
        double _c1 = buf.get(_p1);
        double _c3 = buf.get(_p1 + 1);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m10());
        buf.putFloat(_p1, (float) self.m01());
        buf.putFloat(_p1 + 4, (float) self.m11());
        return buf;
    }
    public Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        double _c0 = buf.getFloat(index);
        double _c2 = buf.getFloat(index + 4);
        double _c1 = buf.getFloat(_p1);
        double _c3 = buf.getFloat(_p1 + 4);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.m00());
        buf.put(index + 1, self.m01());
        buf.put(_p1, self.m10());
        buf.put(_p1 + 1, self.m11());
        return buf;
    }
    public Double2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        double _c0 = buf.get(index);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(_p1);
        double _c3 = buf.get(_p1 + 1);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m01());
        buf.putDouble(_p1, self.m10());
        buf.putDouble(_p1 + 8, self.m11());
        return buf;
    }
    public Double2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        double _c0 = buf.getDouble(index);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(_p1);
        double _c3 = buf.getDouble(_p1 + 8);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m01());
        buf.put(_p1, (float) self.m10());
        buf.put(_p1 + 1, (float) self.m11());
        return buf;
    }
    public Double2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        double _c0 = buf.get(index);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(_p1);
        double _c3 = buf.get(_p1 + 1);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m01());
        buf.putFloat(_p1, (float) self.m10());
        buf.putFloat(_p1 + 4, (float) self.m11());
        return buf;
    }
    public Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        double _c0 = buf.getFloat(index);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(_p1);
        double _c3 = buf.getFloat(_p1 + 4);
        return new Double2x2(_c0, _c1, _c2, _c3);
    }
    public DoubleBuffer storeCM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf) {
        buf.put(index, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, 0.0);
        buf.put(index + 3, self.m01());
        buf.put(index + 4, self.m11());
        buf.put(index + 5, 0.0);
        buf.put(index + 6, 0.0);
        buf.put(index + 7, 0.0);
        buf.put(index + 8, 1.0);
        return buf;
    }
    public ByteBuffer storeCM3x3Absolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(index + 16, 0.0);
        buf.putDouble(index + 24, self.m01());
        buf.putDouble(index + 32, self.m11());
        buf.putDouble(index + 40, 0.0);
        buf.putDouble(index + 48, 0.0);
        buf.putDouble(index + 56, 0.0);
        buf.putDouble(index + 64, 1.0);
        return buf;
    }
    public FloatBuffer storeCM3x3Absolute(Double2x2 self, int index, FloatBuffer buf) {
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m10());
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, (float) self.m01());
        buf.put(index + 4, (float) self.m11());
        buf.put(index + 5, 0.0f);
        buf.put(index + 6, 0.0f);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, 1.0f);
        return buf;
    }
    public ByteBuffer storeCM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m10());
        buf.putFloat(index + 8, 0.0f);
        buf.putFloat(index + 12, (float) self.m01());
        buf.putFloat(index + 16, (float) self.m11());
        buf.putFloat(index + 20, 0.0f);
        buf.putFloat(index + 24, 0.0f);
        buf.putFloat(index + 28, 0.0f);
        buf.putFloat(index + 32, 1.0f);
        return buf;
    }
    public DoubleBuffer storeRM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf) {
        buf.put(index, self.m00());
        buf.put(index + 1, self.m01());
        buf.put(index + 2, 0.0);
        buf.put(index + 3, self.m10());
        buf.put(index + 4, self.m11());
        buf.put(index + 5, 0.0);
        buf.put(index + 6, 0.0);
        buf.put(index + 7, 0.0);
        buf.put(index + 8, 1.0);
        return buf;
    }
    public ByteBuffer storeRM3x3Absolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m01());
        buf.putDouble(index + 16, 0.0);
        buf.putDouble(index + 24, self.m10());
        buf.putDouble(index + 32, self.m11());
        buf.putDouble(index + 40, 0.0);
        buf.putDouble(index + 48, 0.0);
        buf.putDouble(index + 56, 0.0);
        buf.putDouble(index + 64, 1.0);
        return buf;
    }
    public FloatBuffer storeRM3x3Absolute(Double2x2 self, int index, FloatBuffer buf) {
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m01());
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, (float) self.m10());
        buf.put(index + 4, (float) self.m11());
        buf.put(index + 5, 0.0f);
        buf.put(index + 6, 0.0f);
        buf.put(index + 7, 0.0f);
        buf.put(index + 8, 1.0f);
        return buf;
    }
    public ByteBuffer storeRM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m01());
        buf.putFloat(index + 8, 0.0f);
        buf.putFloat(index + 12, (float) self.m10());
        buf.putFloat(index + 16, (float) self.m11());
        buf.putFloat(index + 20, 0.0f);
        buf.putFloat(index + 24, 0.0f);
        buf.putFloat(index + 28, 0.0f);
        buf.putFloat(index + 32, 1.0f);
        return buf;
    }
    public DoubleBuffer storeCM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf) {
        buf.put(index, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, 0.0);
        buf.put(index + 3, 0.0);
        buf.put(index + 4, self.m01());
        buf.put(index + 5, self.m11());
        buf.put(index + 6, 0.0);
        buf.put(index + 7, 0.0);
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
    public ByteBuffer storeCM4x4Absolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(index + 16, 0.0);
        buf.putDouble(index + 24, 0.0);
        buf.putDouble(index + 32, self.m01());
        buf.putDouble(index + 40, self.m11());
        buf.putDouble(index + 48, 0.0);
        buf.putDouble(index + 56, 0.0);
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
    public FloatBuffer storeCM4x4Absolute(Double2x2 self, int index, FloatBuffer buf) {
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m10());
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, 0.0f);
        buf.put(index + 4, (float) self.m01());
        buf.put(index + 5, (float) self.m11());
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
    public ByteBuffer storeCM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m10());
        buf.putFloat(index + 8, 0.0f);
        buf.putFloat(index + 12, 0.0f);
        buf.putFloat(index + 16, (float) self.m01());
        buf.putFloat(index + 20, (float) self.m11());
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
    public DoubleBuffer storeRM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf) {
        buf.put(index, self.m00());
        buf.put(index + 1, self.m01());
        buf.put(index + 2, 0.0);
        buf.put(index + 3, 0.0);
        buf.put(index + 4, self.m10());
        buf.put(index + 5, self.m11());
        buf.put(index + 6, 0.0);
        buf.put(index + 7, 0.0);
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
    public ByteBuffer storeRM4x4Absolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m01());
        buf.putDouble(index + 16, 0.0);
        buf.putDouble(index + 24, 0.0);
        buf.putDouble(index + 32, self.m10());
        buf.putDouble(index + 40, self.m11());
        buf.putDouble(index + 48, 0.0);
        buf.putDouble(index + 56, 0.0);
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
    public FloatBuffer storeRM4x4Absolute(Double2x2 self, int index, FloatBuffer buf) {
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m01());
        buf.put(index + 2, 0.0f);
        buf.put(index + 3, 0.0f);
        buf.put(index + 4, (float) self.m10());
        buf.put(index + 5, (float) self.m11());
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
    public ByteBuffer storeRM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf) {
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m01());
        buf.putFloat(index + 8, 0.0f);
        buf.putFloat(index + 12, 0.0f);
        buf.putFloat(index + 16, (float) self.m10());
        buf.putFloat(index + 20, (float) self.m11());
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
}
