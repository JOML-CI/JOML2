// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Double4x2BbOpsApi implements Double4x2BbOps {
    public DoubleBuffer storeCMAbsolute(Double4x2 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, self.m20());
        buf.put(index + 3, self.m30());
        buf.put(index + 4, self.m01());
        buf.put(index + 5, self.m11());
        buf.put(index + 6, self.m21());
        buf.put(index + 7, self.m31());
        return buf;
    }
    public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c2 = buf.get(index + 1);
        double _c4 = buf.get(index + 2);
        double _c6 = buf.get(index + 3);
        double _c1 = buf.get(index + 4);
        double _c3 = buf.get(index + 5);
        double _c5 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public ByteBuffer storeCMAbsolute(Double4x2 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(index + 16, self.m20());
        buf.putDouble(index + 24, self.m30());
        buf.putDouble(index + 32, self.m01());
        buf.putDouble(index + 40, self.m11());
        buf.putDouble(index + 48, self.m21());
        buf.putDouble(index + 56, self.m31());
        return buf;
    }
    public Double4x2 loadCMAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c2 = buf.getDouble(index + 8);
        double _c4 = buf.getDouble(index + 16);
        double _c6 = buf.getDouble(index + 24);
        double _c1 = buf.getDouble(index + 32);
        double _c3 = buf.getDouble(index + 40);
        double _c5 = buf.getDouble(index + 48);
        double _c7 = buf.getDouble(index + 56);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public FloatBuffer storeCMAbsolute(Double4x2 self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.m00());
        buf.put(index + 1, (float) self.m10());
        buf.put(index + 2, (float) self.m20());
        buf.put(index + 3, (float) self.m30());
        buf.put(index + 4, (float) self.m01());
        buf.put(index + 5, (float) self.m11());
        buf.put(index + 6, (float) self.m21());
        buf.put(index + 7, (float) self.m31());
        return buf;
    }
    public Double4x2 loadCMAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c2 = buf.get(index + 1);
        double _c4 = buf.get(index + 2);
        double _c6 = buf.get(index + 3);
        double _c1 = buf.get(index + 4);
        double _c3 = buf.get(index + 5);
        double _c5 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m10());
        buf.putFloat(index + 8, (float) self.m20());
        buf.putFloat(index + 12, (float) self.m30());
        buf.putFloat(index + 16, (float) self.m01());
        buf.putFloat(index + 20, (float) self.m11());
        buf.putFloat(index + 24, (float) self.m21());
        buf.putFloat(index + 28, (float) self.m31());
        return buf;
    }
    public Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c2 = buf.getFloat(index + 4);
        double _c4 = buf.getFloat(index + 8);
        double _c6 = buf.getFloat(index + 12);
        double _c1 = buf.getFloat(index + 16);
        double _c3 = buf.getFloat(index + 20);
        double _c5 = buf.getFloat(index + 24);
        double _c7 = buf.getFloat(index + 28);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public DoubleBuffer storeRMAbsolute(Double4x2 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00());
        buf.put(index + 1, self.m01());
        buf.put(index + 2, self.m10());
        buf.put(index + 3, self.m11());
        buf.put(index + 4, self.m20());
        buf.put(index + 5, self.m21());
        buf.put(index + 6, self.m30());
        buf.put(index + 7, self.m31());
        return buf;
    }
    public Double4x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        double _c6 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public ByteBuffer storeRMAbsolute(Double4x2 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00());
        buf.putDouble(index + 8, self.m01());
        buf.putDouble(index + 16, self.m10());
        buf.putDouble(index + 24, self.m11());
        buf.putDouble(index + 32, self.m20());
        buf.putDouble(index + 40, self.m21());
        buf.putDouble(index + 48, self.m30());
        buf.putDouble(index + 56, self.m31());
        return buf;
    }
    public Double4x2 loadRMAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        double _c4 = buf.getDouble(index + 32);
        double _c5 = buf.getDouble(index + 40);
        double _c6 = buf.getDouble(index + 48);
        double _c7 = buf.getDouble(index + 56);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public FloatBuffer storeRMAbsolute(Double4x2 self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.m00());
        buf.put(index + 1, (float) self.m01());
        buf.put(index + 2, (float) self.m10());
        buf.put(index + 3, (float) self.m11());
        buf.put(index + 4, (float) self.m20());
        buf.put(index + 5, (float) self.m21());
        buf.put(index + 6, (float) self.m30());
        buf.put(index + 7, (float) self.m31());
        return buf;
    }
    public Double4x2 loadRMAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        double _c6 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m01());
        buf.putFloat(index + 8, (float) self.m10());
        buf.putFloat(index + 12, (float) self.m11());
        buf.putFloat(index + 16, (float) self.m20());
        buf.putFloat(index + 20, (float) self.m21());
        buf.putFloat(index + 24, (float) self.m30());
        buf.putFloat(index + 28, (float) self.m31());
        return buf;
    }
    public Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        double _c4 = buf.getFloat(index + 16);
        double _c5 = buf.getFloat(index + 20);
        double _c6 = buf.getFloat(index + 24);
        double _c7 = buf.getFloat(index + 28);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public DoubleBuffer storeCMAbsolute(Double4x2 self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, self.m20());
        buf.put(index + 3, self.m30());
        buf.put(_p1, self.m01());
        buf.put(_p1 + 1, self.m11());
        buf.put(_p1 + 2, self.m21());
        buf.put(_p1 + 3, self.m31());
        return buf;
    }
    public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        double _c0 = buf.get(index);
        double _c2 = buf.get(index + 1);
        double _c4 = buf.get(index + 2);
        double _c6 = buf.get(index + 3);
        double _c1 = buf.get(_p1);
        double _c3 = buf.get(_p1 + 1);
        double _c5 = buf.get(_p1 + 2);
        double _c7 = buf.get(_p1 + 3);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public ByteBuffer storeCMAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(index + 16, self.m20());
        buf.putDouble(index + 24, self.m30());
        buf.putDouble(_p1, self.m01());
        buf.putDouble(_p1 + 8, self.m11());
        buf.putDouble(_p1 + 16, self.m21());
        buf.putDouble(_p1 + 24, self.m31());
        return buf;
    }
    public Double4x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        double _c0 = buf.getDouble(index);
        double _c2 = buf.getDouble(index + 8);
        double _c4 = buf.getDouble(index + 16);
        double _c6 = buf.getDouble(index + 24);
        double _c1 = buf.getDouble(_p1);
        double _c3 = buf.getDouble(_p1 + 8);
        double _c5 = buf.getDouble(_p1 + 16);
        double _c7 = buf.getDouble(_p1 + 24);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public FloatBuffer storeCMAbsolute(Double4x2 self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m10());
        buf.put(index + 2, (float) self.m20());
        buf.put(index + 3, (float) self.m30());
        buf.put(_p1, (float) self.m01());
        buf.put(_p1 + 1, (float) self.m11());
        buf.put(_p1 + 2, (float) self.m21());
        buf.put(_p1 + 3, (float) self.m31());
        return buf;
    }
    public Double4x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        double _c0 = buf.get(index);
        double _c2 = buf.get(index + 1);
        double _c4 = buf.get(index + 2);
        double _c6 = buf.get(index + 3);
        double _c1 = buf.get(_p1);
        double _c3 = buf.get(_p1 + 1);
        double _c5 = buf.get(_p1 + 2);
        double _c7 = buf.get(_p1 + 3);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m10());
        buf.putFloat(index + 8, (float) self.m20());
        buf.putFloat(index + 12, (float) self.m30());
        buf.putFloat(_p1, (float) self.m01());
        buf.putFloat(_p1 + 4, (float) self.m11());
        buf.putFloat(_p1 + 8, (float) self.m21());
        buf.putFloat(_p1 + 12, (float) self.m31());
        return buf;
    }
    public Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        double _c0 = buf.getFloat(index);
        double _c2 = buf.getFloat(index + 4);
        double _c4 = buf.getFloat(index + 8);
        double _c6 = buf.getFloat(index + 12);
        double _c1 = buf.getFloat(_p1);
        double _c3 = buf.getFloat(_p1 + 4);
        double _c5 = buf.getFloat(_p1 + 8);
        double _c7 = buf.getFloat(_p1 + 12);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public DoubleBuffer storeRMAbsolute(Double4x2 self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.m00());
        buf.put(index + 1, self.m01());
        buf.put(_p1, self.m10());
        buf.put(_p1 + 1, self.m11());
        buf.put(_p2, self.m20());
        buf.put(_p2 + 1, self.m21());
        buf.put(_p3, self.m30());
        buf.put(_p3 + 1, self.m31());
        return buf;
    }
    public Double4x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double _c0 = buf.get(index);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(_p1);
        double _c3 = buf.get(_p1 + 1);
        double _c4 = buf.get(_p2);
        double _c5 = buf.get(_p2 + 1);
        double _c6 = buf.get(_p3);
        double _c7 = buf.get(_p3 + 1);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public ByteBuffer storeRMAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m01());
        buf.putDouble(_p1, self.m10());
        buf.putDouble(_p1 + 8, self.m11());
        buf.putDouble(_p2, self.m20());
        buf.putDouble(_p2 + 8, self.m21());
        buf.putDouble(_p3, self.m30());
        buf.putDouble(_p3 + 8, self.m31());
        return buf;
    }
    public Double4x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        double _c0 = buf.getDouble(index);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(_p1);
        double _c3 = buf.getDouble(_p1 + 8);
        double _c4 = buf.getDouble(_p2);
        double _c5 = buf.getDouble(_p2 + 8);
        double _c6 = buf.getDouble(_p3);
        double _c7 = buf.getDouble(_p3 + 8);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public FloatBuffer storeRMAbsolute(Double4x2 self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m01());
        buf.put(_p1, (float) self.m10());
        buf.put(_p1 + 1, (float) self.m11());
        buf.put(_p2, (float) self.m20());
        buf.put(_p2 + 1, (float) self.m21());
        buf.put(_p3, (float) self.m30());
        buf.put(_p3 + 1, (float) self.m31());
        return buf;
    }
    public Double4x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double _c0 = buf.get(index);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(_p1);
        double _c3 = buf.get(_p1 + 1);
        double _c4 = buf.get(_p2);
        double _c5 = buf.get(_p2 + 1);
        double _c6 = buf.get(_p3);
        double _c7 = buf.get(_p3 + 1);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m01());
        buf.putFloat(_p1, (float) self.m10());
        buf.putFloat(_p1 + 4, (float) self.m11());
        buf.putFloat(_p2, (float) self.m20());
        buf.putFloat(_p2 + 4, (float) self.m21());
        buf.putFloat(_p3, (float) self.m30());
        buf.putFloat(_p3 + 4, (float) self.m31());
        return buf;
    }
    public Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        double _c0 = buf.getFloat(index);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(_p1);
        double _c3 = buf.getFloat(_p1 + 4);
        double _c4 = buf.getFloat(_p2);
        double _c5 = buf.getFloat(_p2 + 4);
        double _c6 = buf.getFloat(_p3);
        double _c7 = buf.getFloat(_p3 + 4);
        return new Double4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
