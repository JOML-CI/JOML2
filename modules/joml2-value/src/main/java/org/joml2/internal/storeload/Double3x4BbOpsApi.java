// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Double3x4BbOpsApi implements Double3x4BbOps {
    public DoubleBuffer storeCMAbsolute(Double3x4 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, self.m20());
        buf.put(index + 3, self.m01());
        buf.put(index + 4, self.m11());
        buf.put(index + 5, self.m21());
        buf.put(index + 6, self.m02());
        buf.put(index + 7, self.m12());
        buf.put(index + 8, self.m22());
        buf.put(index + 9, self.m03());
        buf.put(index + 10, self.m13());
        buf.put(index + 11, self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c4 = buf.get(index + 1);
        double _c8 = buf.get(index + 2);
        double _c1 = buf.get(index + 3);
        double _c5 = buf.get(index + 4);
        double _c9 = buf.get(index + 5);
        double _c2 = buf.get(index + 6);
        double _c6 = buf.get(index + 7);
        double _c10 = buf.get(index + 8);
        double _c3 = buf.get(index + 9);
        double _c7 = buf.get(index + 10);
        double _c11 = buf.get(index + 11);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeCMAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(index + 16, self.m20());
        buf.putDouble(index + 24, self.m01());
        buf.putDouble(index + 32, self.m11());
        buf.putDouble(index + 40, self.m21());
        buf.putDouble(index + 48, self.m02());
        buf.putDouble(index + 56, self.m12());
        buf.putDouble(index + 64, self.m22());
        buf.putDouble(index + 72, self.m03());
        buf.putDouble(index + 80, self.m13());
        buf.putDouble(index + 88, self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c4 = buf.getDouble(index + 8);
        double _c8 = buf.getDouble(index + 16);
        double _c1 = buf.getDouble(index + 24);
        double _c5 = buf.getDouble(index + 32);
        double _c9 = buf.getDouble(index + 40);
        double _c2 = buf.getDouble(index + 48);
        double _c6 = buf.getDouble(index + 56);
        double _c10 = buf.getDouble(index + 64);
        double _c3 = buf.getDouble(index + 72);
        double _c7 = buf.getDouble(index + 80);
        double _c11 = buf.getDouble(index + 88);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public FloatBuffer storeCMAbsolute(Double3x4 self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.m00());
        buf.put(index + 1, (float) self.m10());
        buf.put(index + 2, (float) self.m20());
        buf.put(index + 3, (float) self.m01());
        buf.put(index + 4, (float) self.m11());
        buf.put(index + 5, (float) self.m21());
        buf.put(index + 6, (float) self.m02());
        buf.put(index + 7, (float) self.m12());
        buf.put(index + 8, (float) self.m22());
        buf.put(index + 9, (float) self.m03());
        buf.put(index + 10, (float) self.m13());
        buf.put(index + 11, (float) self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c4 = buf.get(index + 1);
        double _c8 = buf.get(index + 2);
        double _c1 = buf.get(index + 3);
        double _c5 = buf.get(index + 4);
        double _c9 = buf.get(index + 5);
        double _c2 = buf.get(index + 6);
        double _c6 = buf.get(index + 7);
        double _c10 = buf.get(index + 8);
        double _c3 = buf.get(index + 9);
        double _c7 = buf.get(index + 10);
        double _c11 = buf.get(index + 11);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeCMFloatAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m10());
        buf.putFloat(index + 8, (float) self.m20());
        buf.putFloat(index + 12, (float) self.m01());
        buf.putFloat(index + 16, (float) self.m11());
        buf.putFloat(index + 20, (float) self.m21());
        buf.putFloat(index + 24, (float) self.m02());
        buf.putFloat(index + 28, (float) self.m12());
        buf.putFloat(index + 32, (float) self.m22());
        buf.putFloat(index + 36, (float) self.m03());
        buf.putFloat(index + 40, (float) self.m13());
        buf.putFloat(index + 44, (float) self.m23());
        return buf;
    }
    public Double3x4 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c4 = buf.getFloat(index + 4);
        double _c8 = buf.getFloat(index + 8);
        double _c1 = buf.getFloat(index + 12);
        double _c5 = buf.getFloat(index + 16);
        double _c9 = buf.getFloat(index + 20);
        double _c2 = buf.getFloat(index + 24);
        double _c6 = buf.getFloat(index + 28);
        double _c10 = buf.getFloat(index + 32);
        double _c3 = buf.getFloat(index + 36);
        double _c7 = buf.getFloat(index + 40);
        double _c11 = buf.getFloat(index + 44);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public DoubleBuffer storeRMAbsolute(Double3x4 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00());
        buf.put(index + 1, self.m01());
        buf.put(index + 2, self.m02());
        buf.put(index + 3, self.m03());
        buf.put(index + 4, self.m10());
        buf.put(index + 5, self.m11());
        buf.put(index + 6, self.m12());
        buf.put(index + 7, self.m13());
        buf.put(index + 8, self.m20());
        buf.put(index + 9, self.m21());
        buf.put(index + 10, self.m22());
        buf.put(index + 11, self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, DoubleBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        double _c6 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        double _c8 = buf.get(index + 8);
        double _c9 = buf.get(index + 9);
        double _c10 = buf.get(index + 10);
        double _c11 = buf.get(index + 11);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeRMAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00());
        buf.putDouble(index + 8, self.m01());
        buf.putDouble(index + 16, self.m02());
        buf.putDouble(index + 24, self.m03());
        buf.putDouble(index + 32, self.m10());
        buf.putDouble(index + 40, self.m11());
        buf.putDouble(index + 48, self.m12());
        buf.putDouble(index + 56, self.m13());
        buf.putDouble(index + 64, self.m20());
        buf.putDouble(index + 72, self.m21());
        buf.putDouble(index + 80, self.m22());
        buf.putDouble(index + 88, self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getDouble(index + 0);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        double _c4 = buf.getDouble(index + 32);
        double _c5 = buf.getDouble(index + 40);
        double _c6 = buf.getDouble(index + 48);
        double _c7 = buf.getDouble(index + 56);
        double _c8 = buf.getDouble(index + 64);
        double _c9 = buf.getDouble(index + 72);
        double _c10 = buf.getDouble(index + 80);
        double _c11 = buf.getDouble(index + 88);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public FloatBuffer storeRMAbsolute(Double3x4 self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.m00());
        buf.put(index + 1, (float) self.m01());
        buf.put(index + 2, (float) self.m02());
        buf.put(index + 3, (float) self.m03());
        buf.put(index + 4, (float) self.m10());
        buf.put(index + 5, (float) self.m11());
        buf.put(index + 6, (float) self.m12());
        buf.put(index + 7, (float) self.m13());
        buf.put(index + 8, (float) self.m20());
        buf.put(index + 9, (float) self.m21());
        buf.put(index + 10, (float) self.m22());
        buf.put(index + 11, (float) self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, FloatBuffer buf) {
        double _c0 = buf.get(index + 0);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(index + 4);
        double _c5 = buf.get(index + 5);
        double _c6 = buf.get(index + 6);
        double _c7 = buf.get(index + 7);
        double _c8 = buf.get(index + 8);
        double _c9 = buf.get(index + 9);
        double _c10 = buf.get(index + 10);
        double _c11 = buf.get(index + 11);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeRMFloatAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m01());
        buf.putFloat(index + 8, (float) self.m02());
        buf.putFloat(index + 12, (float) self.m03());
        buf.putFloat(index + 16, (float) self.m10());
        buf.putFloat(index + 20, (float) self.m11());
        buf.putFloat(index + 24, (float) self.m12());
        buf.putFloat(index + 28, (float) self.m13());
        buf.putFloat(index + 32, (float) self.m20());
        buf.putFloat(index + 36, (float) self.m21());
        buf.putFloat(index + 40, (float) self.m22());
        buf.putFloat(index + 44, (float) self.m23());
        return buf;
    }
    public Double3x4 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        double _c0 = buf.getFloat(index + 0);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        double _c4 = buf.getFloat(index + 16);
        double _c5 = buf.getFloat(index + 20);
        double _c6 = buf.getFloat(index + 24);
        double _c7 = buf.getFloat(index + 28);
        double _c8 = buf.getFloat(index + 32);
        double _c9 = buf.getFloat(index + 36);
        double _c10 = buf.getFloat(index + 40);
        double _c11 = buf.getFloat(index + 44);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public DoubleBuffer storeCMAbsolute(Double3x4 self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, self.m20());
        buf.put(_p1, self.m01());
        buf.put(_p1 + 1, self.m11());
        buf.put(_p1 + 2, self.m21());
        buf.put(_p2, self.m02());
        buf.put(_p2 + 1, self.m12());
        buf.put(_p2 + 2, self.m22());
        buf.put(_p3, self.m03());
        buf.put(_p3 + 1, self.m13());
        buf.put(_p3 + 2, self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double _c0 = buf.get(index);
        double _c4 = buf.get(index + 1);
        double _c8 = buf.get(index + 2);
        double _c1 = buf.get(_p1);
        double _c5 = buf.get(_p1 + 1);
        double _c9 = buf.get(_p1 + 2);
        double _c2 = buf.get(_p2);
        double _c6 = buf.get(_p2 + 1);
        double _c10 = buf.get(_p2 + 2);
        double _c3 = buf.get(_p3);
        double _c7 = buf.get(_p3 + 1);
        double _c11 = buf.get(_p3 + 2);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeCMAbsolute(Double3x4 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(index + 16, self.m20());
        buf.putDouble(_p1, self.m01());
        buf.putDouble(_p1 + 8, self.m11());
        buf.putDouble(_p1 + 16, self.m21());
        buf.putDouble(_p2, self.m02());
        buf.putDouble(_p2 + 8, self.m12());
        buf.putDouble(_p2 + 16, self.m22());
        buf.putDouble(_p3, self.m03());
        buf.putDouble(_p3 + 8, self.m13());
        buf.putDouble(_p3 + 16, self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        double _c0 = buf.getDouble(index);
        double _c4 = buf.getDouble(index + 8);
        double _c8 = buf.getDouble(index + 16);
        double _c1 = buf.getDouble(_p1);
        double _c5 = buf.getDouble(_p1 + 8);
        double _c9 = buf.getDouble(_p1 + 16);
        double _c2 = buf.getDouble(_p2);
        double _c6 = buf.getDouble(_p2 + 8);
        double _c10 = buf.getDouble(_p2 + 16);
        double _c3 = buf.getDouble(_p3);
        double _c7 = buf.getDouble(_p3 + 8);
        double _c11 = buf.getDouble(_p3 + 16);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public FloatBuffer storeCMAbsolute(Double3x4 self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m10());
        buf.put(index + 2, (float) self.m20());
        buf.put(_p1, (float) self.m01());
        buf.put(_p1 + 1, (float) self.m11());
        buf.put(_p1 + 2, (float) self.m21());
        buf.put(_p2, (float) self.m02());
        buf.put(_p2 + 1, (float) self.m12());
        buf.put(_p2 + 2, (float) self.m22());
        buf.put(_p3, (float) self.m03());
        buf.put(_p3 + 1, (float) self.m13());
        buf.put(_p3 + 2, (float) self.m23());
        return buf;
    }
    public Double3x4 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double _c0 = buf.get(index);
        double _c4 = buf.get(index + 1);
        double _c8 = buf.get(index + 2);
        double _c1 = buf.get(_p1);
        double _c5 = buf.get(_p1 + 1);
        double _c9 = buf.get(_p1 + 2);
        double _c2 = buf.get(_p2);
        double _c6 = buf.get(_p2 + 1);
        double _c10 = buf.get(_p2 + 2);
        double _c3 = buf.get(_p3);
        double _c7 = buf.get(_p3 + 1);
        double _c11 = buf.get(_p3 + 2);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeCMFloatAbsolute(Double3x4 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m10());
        buf.putFloat(index + 8, (float) self.m20());
        buf.putFloat(_p1, (float) self.m01());
        buf.putFloat(_p1 + 4, (float) self.m11());
        buf.putFloat(_p1 + 8, (float) self.m21());
        buf.putFloat(_p2, (float) self.m02());
        buf.putFloat(_p2 + 4, (float) self.m12());
        buf.putFloat(_p2 + 8, (float) self.m22());
        buf.putFloat(_p3, (float) self.m03());
        buf.putFloat(_p3 + 4, (float) self.m13());
        buf.putFloat(_p3 + 8, (float) self.m23());
        return buf;
    }
    public Double3x4 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        double _c0 = buf.getFloat(index);
        double _c4 = buf.getFloat(index + 4);
        double _c8 = buf.getFloat(index + 8);
        double _c1 = buf.getFloat(_p1);
        double _c5 = buf.getFloat(_p1 + 4);
        double _c9 = buf.getFloat(_p1 + 8);
        double _c2 = buf.getFloat(_p2);
        double _c6 = buf.getFloat(_p2 + 4);
        double _c10 = buf.getFloat(_p2 + 8);
        double _c3 = buf.getFloat(_p3);
        double _c7 = buf.getFloat(_p3 + 4);
        double _c11 = buf.getFloat(_p3 + 8);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public DoubleBuffer storeRMAbsolute(Double3x4 self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.m00());
        buf.put(index + 1, self.m01());
        buf.put(index + 2, self.m02());
        buf.put(index + 3, self.m03());
        buf.put(_p1, self.m10());
        buf.put(_p1 + 1, self.m11());
        buf.put(_p1 + 2, self.m12());
        buf.put(_p1 + 3, self.m13());
        buf.put(_p2, self.m20());
        buf.put(_p2 + 1, self.m21());
        buf.put(_p2 + 2, self.m22());
        buf.put(_p2 + 3, self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        double _c0 = buf.get(index);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(_p1);
        double _c5 = buf.get(_p1 + 1);
        double _c6 = buf.get(_p1 + 2);
        double _c7 = buf.get(_p1 + 3);
        double _c8 = buf.get(_p2);
        double _c9 = buf.get(_p2 + 1);
        double _c10 = buf.get(_p2 + 2);
        double _c11 = buf.get(_p2 + 3);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeRMAbsolute(Double3x4 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m01());
        buf.putDouble(index + 16, self.m02());
        buf.putDouble(index + 24, self.m03());
        buf.putDouble(_p1, self.m10());
        buf.putDouble(_p1 + 8, self.m11());
        buf.putDouble(_p1 + 16, self.m12());
        buf.putDouble(_p1 + 24, self.m13());
        buf.putDouble(_p2, self.m20());
        buf.putDouble(_p2 + 8, self.m21());
        buf.putDouble(_p2 + 16, self.m22());
        buf.putDouble(_p2 + 24, self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        double _c0 = buf.getDouble(index);
        double _c1 = buf.getDouble(index + 8);
        double _c2 = buf.getDouble(index + 16);
        double _c3 = buf.getDouble(index + 24);
        double _c4 = buf.getDouble(_p1);
        double _c5 = buf.getDouble(_p1 + 8);
        double _c6 = buf.getDouble(_p1 + 16);
        double _c7 = buf.getDouble(_p1 + 24);
        double _c8 = buf.getDouble(_p2);
        double _c9 = buf.getDouble(_p2 + 8);
        double _c10 = buf.getDouble(_p2 + 16);
        double _c11 = buf.getDouble(_p2 + 24);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public FloatBuffer storeRMAbsolute(Double3x4 self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, (float) self.m00());
        buf.put(index + 1, (float) self.m01());
        buf.put(index + 2, (float) self.m02());
        buf.put(index + 3, (float) self.m03());
        buf.put(_p1, (float) self.m10());
        buf.put(_p1 + 1, (float) self.m11());
        buf.put(_p1 + 2, (float) self.m12());
        buf.put(_p1 + 3, (float) self.m13());
        buf.put(_p2, (float) self.m20());
        buf.put(_p2 + 1, (float) self.m21());
        buf.put(_p2 + 2, (float) self.m22());
        buf.put(_p2 + 3, (float) self.m23());
        return buf;
    }
    public Double3x4 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        double _c0 = buf.get(index);
        double _c1 = buf.get(index + 1);
        double _c2 = buf.get(index + 2);
        double _c3 = buf.get(index + 3);
        double _c4 = buf.get(_p1);
        double _c5 = buf.get(_p1 + 1);
        double _c6 = buf.get(_p1 + 2);
        double _c7 = buf.get(_p1 + 3);
        double _c8 = buf.get(_p2);
        double _c9 = buf.get(_p2 + 1);
        double _c10 = buf.get(_p2 + 2);
        double _c11 = buf.get(_p2 + 3);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public ByteBuffer storeRMFloatAbsolute(Double3x4 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putFloat(index, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m01());
        buf.putFloat(index + 8, (float) self.m02());
        buf.putFloat(index + 12, (float) self.m03());
        buf.putFloat(_p1, (float) self.m10());
        buf.putFloat(_p1 + 4, (float) self.m11());
        buf.putFloat(_p1 + 8, (float) self.m12());
        buf.putFloat(_p1 + 12, (float) self.m13());
        buf.putFloat(_p2, (float) self.m20());
        buf.putFloat(_p2 + 4, (float) self.m21());
        buf.putFloat(_p2 + 8, (float) self.m22());
        buf.putFloat(_p2 + 12, (float) self.m23());
        return buf;
    }
    public Double3x4 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        double _c0 = buf.getFloat(index);
        double _c1 = buf.getFloat(index + 4);
        double _c2 = buf.getFloat(index + 8);
        double _c3 = buf.getFloat(index + 12);
        double _c4 = buf.getFloat(_p1);
        double _c5 = buf.getFloat(_p1 + 4);
        double _c6 = buf.getFloat(_p1 + 8);
        double _c7 = buf.getFloat(_p1 + 12);
        double _c8 = buf.getFloat(_p2);
        double _c9 = buf.getFloat(_p2 + 4);
        double _c10 = buf.getFloat(_p2 + 8);
        double _c11 = buf.getFloat(_p2 + 12);
        return new Double3x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11);
    }
    public DoubleBuffer storeCM4x4Absolute(Double3x4 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, self.m20());
        buf.put(index + 3, 0.0);
        buf.put(index + 4, self.m01());
        buf.put(index + 5, self.m11());
        buf.put(index + 6, self.m21());
        buf.put(index + 7, 0.0);
        buf.put(index + 8, self.m02());
        buf.put(index + 9, self.m12());
        buf.put(index + 10, self.m22());
        buf.put(index + 11, 0.0);
        buf.put(index + 12, self.m03());
        buf.put(index + 13, self.m13());
        buf.put(index + 14, self.m23());
        buf.put(index + 15, 1.0);
        return buf;
    }
    public ByteBuffer storeCM4x4Absolute(Double3x4 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(index + 16, self.m20());
        buf.putDouble(index + 24, 0.0);
        buf.putDouble(index + 32, self.m01());
        buf.putDouble(index + 40, self.m11());
        buf.putDouble(index + 48, self.m21());
        buf.putDouble(index + 56, 0.0);
        buf.putDouble(index + 64, self.m02());
        buf.putDouble(index + 72, self.m12());
        buf.putDouble(index + 80, self.m22());
        buf.putDouble(index + 88, 0.0);
        buf.putDouble(index + 96, self.m03());
        buf.putDouble(index + 104, self.m13());
        buf.putDouble(index + 112, self.m23());
        buf.putDouble(index + 120, 1.0);
        return buf;
    }
    public FloatBuffer storeCM4x4Absolute(Double3x4 self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.m00());
        buf.put(index + 1, (float) self.m10());
        buf.put(index + 2, (float) self.m20());
        buf.put(index + 3, (float) 0.0);
        buf.put(index + 4, (float) self.m01());
        buf.put(index + 5, (float) self.m11());
        buf.put(index + 6, (float) self.m21());
        buf.put(index + 7, (float) 0.0);
        buf.put(index + 8, (float) self.m02());
        buf.put(index + 9, (float) self.m12());
        buf.put(index + 10, (float) self.m22());
        buf.put(index + 11, (float) 0.0);
        buf.put(index + 12, (float) self.m03());
        buf.put(index + 13, (float) self.m13());
        buf.put(index + 14, (float) self.m23());
        buf.put(index + 15, (float) 1.0);
        return buf;
    }
    public ByteBuffer storeCM4x4FloatAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m10());
        buf.putFloat(index + 8, (float) self.m20());
        buf.putFloat(index + 12, (float) 0.0);
        buf.putFloat(index + 16, (float) self.m01());
        buf.putFloat(index + 20, (float) self.m11());
        buf.putFloat(index + 24, (float) self.m21());
        buf.putFloat(index + 28, (float) 0.0);
        buf.putFloat(index + 32, (float) self.m02());
        buf.putFloat(index + 36, (float) self.m12());
        buf.putFloat(index + 40, (float) self.m22());
        buf.putFloat(index + 44, (float) 0.0);
        buf.putFloat(index + 48, (float) self.m03());
        buf.putFloat(index + 52, (float) self.m13());
        buf.putFloat(index + 56, (float) self.m23());
        buf.putFloat(index + 60, (float) 1.0);
        return buf;
    }
    public DoubleBuffer storeRM4x4Absolute(Double3x4 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00());
        buf.put(index + 1, self.m01());
        buf.put(index + 2, self.m02());
        buf.put(index + 3, self.m03());
        buf.put(index + 4, self.m10());
        buf.put(index + 5, self.m11());
        buf.put(index + 6, self.m12());
        buf.put(index + 7, self.m13());
        buf.put(index + 8, self.m20());
        buf.put(index + 9, self.m21());
        buf.put(index + 10, self.m22());
        buf.put(index + 11, self.m23());
        buf.put(index + 12, 0.0);
        buf.put(index + 13, 0.0);
        buf.put(index + 14, 0.0);
        buf.put(index + 15, 1.0);
        return buf;
    }
    public ByteBuffer storeRM4x4Absolute(Double3x4 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00());
        buf.putDouble(index + 8, self.m01());
        buf.putDouble(index + 16, self.m02());
        buf.putDouble(index + 24, self.m03());
        buf.putDouble(index + 32, self.m10());
        buf.putDouble(index + 40, self.m11());
        buf.putDouble(index + 48, self.m12());
        buf.putDouble(index + 56, self.m13());
        buf.putDouble(index + 64, self.m20());
        buf.putDouble(index + 72, self.m21());
        buf.putDouble(index + 80, self.m22());
        buf.putDouble(index + 88, self.m23());
        buf.putDouble(index + 96, 0.0);
        buf.putDouble(index + 104, 0.0);
        buf.putDouble(index + 112, 0.0);
        buf.putDouble(index + 120, 1.0);
        return buf;
    }
    public FloatBuffer storeRM4x4Absolute(Double3x4 self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.m00());
        buf.put(index + 1, (float) self.m01());
        buf.put(index + 2, (float) self.m02());
        buf.put(index + 3, (float) self.m03());
        buf.put(index + 4, (float) self.m10());
        buf.put(index + 5, (float) self.m11());
        buf.put(index + 6, (float) self.m12());
        buf.put(index + 7, (float) self.m13());
        buf.put(index + 8, (float) self.m20());
        buf.put(index + 9, (float) self.m21());
        buf.put(index + 10, (float) self.m22());
        buf.put(index + 11, (float) self.m23());
        buf.put(index + 12, (float) 0.0);
        buf.put(index + 13, (float) 0.0);
        buf.put(index + 14, (float) 0.0);
        buf.put(index + 15, (float) 1.0);
        return buf;
    }
    public ByteBuffer storeRM4x4FloatAbsolute(Double3x4 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.m00());
        buf.putFloat(index + 4, (float) self.m01());
        buf.putFloat(index + 8, (float) self.m02());
        buf.putFloat(index + 12, (float) self.m03());
        buf.putFloat(index + 16, (float) self.m10());
        buf.putFloat(index + 20, (float) self.m11());
        buf.putFloat(index + 24, (float) self.m12());
        buf.putFloat(index + 28, (float) self.m13());
        buf.putFloat(index + 32, (float) self.m20());
        buf.putFloat(index + 36, (float) self.m21());
        buf.putFloat(index + 40, (float) self.m22());
        buf.putFloat(index + 44, (float) self.m23());
        buf.putFloat(index + 48, (float) 0.0);
        buf.putFloat(index + 52, (float) 0.0);
        buf.putFloat(index + 56, (float) 0.0);
        buf.putFloat(index + 60, (float) 1.0);
        return buf;
    }
}
