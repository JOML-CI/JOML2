// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float4x4BbOpsApi implements Float4x4BbOps {
    public FloatBuffer storeCMAbsolute(Float4x4 self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, self.m20());
        buf.put(index + 3, self.m30());
        buf.put(index + 4, self.m01());
        buf.put(index + 5, self.m11());
        buf.put(index + 6, self.m21());
        buf.put(index + 7, self.m31());
        buf.put(index + 8, self.m02());
        buf.put(index + 9, self.m12());
        buf.put(index + 10, self.m22());
        buf.put(index + 11, self.m32());
        buf.put(index + 12, self.m03());
        buf.put(index + 13, self.m13());
        buf.put(index + 14, self.m23());
        buf.put(index + 15, self.m33());
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c4 = buf.get(index + 1);
        float _c8 = buf.get(index + 2);
        float _c12 = buf.get(index + 3);
        float _c1 = buf.get(index + 4);
        float _c5 = buf.get(index + 5);
        float _c9 = buf.get(index + 6);
        float _c13 = buf.get(index + 7);
        float _c2 = buf.get(index + 8);
        float _c6 = buf.get(index + 9);
        float _c10 = buf.get(index + 10);
        float _c14 = buf.get(index + 11);
        float _c3 = buf.get(index + 12);
        float _c7 = buf.get(index + 13);
        float _c11 = buf.get(index + 14);
        float _c15 = buf.get(index + 15);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeCMAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00());
        buf.putFloat(index + 4, self.m10());
        buf.putFloat(index + 8, self.m20());
        buf.putFloat(index + 12, self.m30());
        buf.putFloat(index + 16, self.m01());
        buf.putFloat(index + 20, self.m11());
        buf.putFloat(index + 24, self.m21());
        buf.putFloat(index + 28, self.m31());
        buf.putFloat(index + 32, self.m02());
        buf.putFloat(index + 36, self.m12());
        buf.putFloat(index + 40, self.m22());
        buf.putFloat(index + 44, self.m32());
        buf.putFloat(index + 48, self.m03());
        buf.putFloat(index + 52, self.m13());
        buf.putFloat(index + 56, self.m23());
        buf.putFloat(index + 60, self.m33());
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c4 = buf.getFloat(index + 4);
        float _c8 = buf.getFloat(index + 8);
        float _c12 = buf.getFloat(index + 12);
        float _c1 = buf.getFloat(index + 16);
        float _c5 = buf.getFloat(index + 20);
        float _c9 = buf.getFloat(index + 24);
        float _c13 = buf.getFloat(index + 28);
        float _c2 = buf.getFloat(index + 32);
        float _c6 = buf.getFloat(index + 36);
        float _c10 = buf.getFloat(index + 40);
        float _c14 = buf.getFloat(index + 44);
        float _c3 = buf.getFloat(index + 48);
        float _c7 = buf.getFloat(index + 52);
        float _c11 = buf.getFloat(index + 56);
        float _c15 = buf.getFloat(index + 60);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public DoubleBuffer storeCMAbsolute(Float4x4 self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, self.m20());
        buf.put(index + 3, self.m30());
        buf.put(index + 4, self.m01());
        buf.put(index + 5, self.m11());
        buf.put(index + 6, self.m21());
        buf.put(index + 7, self.m31());
        buf.put(index + 8, self.m02());
        buf.put(index + 9, self.m12());
        buf.put(index + 10, self.m22());
        buf.put(index + 11, self.m32());
        buf.put(index + 12, self.m03());
        buf.put(index + 13, self.m13());
        buf.put(index + 14, self.m23());
        buf.put(index + 15, self.m33());
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c4 = (float) buf.get(index + 1);
        float _c8 = (float) buf.get(index + 2);
        float _c12 = (float) buf.get(index + 3);
        float _c1 = (float) buf.get(index + 4);
        float _c5 = (float) buf.get(index + 5);
        float _c9 = (float) buf.get(index + 6);
        float _c13 = (float) buf.get(index + 7);
        float _c2 = (float) buf.get(index + 8);
        float _c6 = (float) buf.get(index + 9);
        float _c10 = (float) buf.get(index + 10);
        float _c14 = (float) buf.get(index + 11);
        float _c3 = (float) buf.get(index + 12);
        float _c7 = (float) buf.get(index + 13);
        float _c11 = (float) buf.get(index + 14);
        float _c15 = (float) buf.get(index + 15);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeCMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(index + 16, self.m20());
        buf.putDouble(index + 24, self.m30());
        buf.putDouble(index + 32, self.m01());
        buf.putDouble(index + 40, self.m11());
        buf.putDouble(index + 48, self.m21());
        buf.putDouble(index + 56, self.m31());
        buf.putDouble(index + 64, self.m02());
        buf.putDouble(index + 72, self.m12());
        buf.putDouble(index + 80, self.m22());
        buf.putDouble(index + 88, self.m32());
        buf.putDouble(index + 96, self.m03());
        buf.putDouble(index + 104, self.m13());
        buf.putDouble(index + 112, self.m23());
        buf.putDouble(index + 120, self.m33());
        return buf;
    }
    public Float4x4 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c4 = (float) buf.getDouble(index + 8);
        float _c8 = (float) buf.getDouble(index + 16);
        float _c12 = (float) buf.getDouble(index + 24);
        float _c1 = (float) buf.getDouble(index + 32);
        float _c5 = (float) buf.getDouble(index + 40);
        float _c9 = (float) buf.getDouble(index + 48);
        float _c13 = (float) buf.getDouble(index + 56);
        float _c2 = (float) buf.getDouble(index + 64);
        float _c6 = (float) buf.getDouble(index + 72);
        float _c10 = (float) buf.getDouble(index + 80);
        float _c14 = (float) buf.getDouble(index + 88);
        float _c3 = (float) buf.getDouble(index + 96);
        float _c7 = (float) buf.getDouble(index + 104);
        float _c11 = (float) buf.getDouble(index + 112);
        float _c15 = (float) buf.getDouble(index + 120);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public FloatBuffer storeRMAbsolute(Float4x4 self, int index, FloatBuffer buf) {
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
        buf.put(index + 12, self.m30());
        buf.put(index + 13, self.m31());
        buf.put(index + 14, self.m32());
        buf.put(index + 15, self.m33());
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, FloatBuffer buf) {
        float _c0 = buf.get(index + 0);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        float _c4 = buf.get(index + 4);
        float _c5 = buf.get(index + 5);
        float _c6 = buf.get(index + 6);
        float _c7 = buf.get(index + 7);
        float _c8 = buf.get(index + 8);
        float _c9 = buf.get(index + 9);
        float _c10 = buf.get(index + 10);
        float _c11 = buf.get(index + 11);
        float _c12 = buf.get(index + 12);
        float _c13 = buf.get(index + 13);
        float _c14 = buf.get(index + 14);
        float _c15 = buf.get(index + 15);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeRMAbsolute(Float4x4 self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.m00());
        buf.putFloat(index + 4, self.m01());
        buf.putFloat(index + 8, self.m02());
        buf.putFloat(index + 12, self.m03());
        buf.putFloat(index + 16, self.m10());
        buf.putFloat(index + 20, self.m11());
        buf.putFloat(index + 24, self.m12());
        buf.putFloat(index + 28, self.m13());
        buf.putFloat(index + 32, self.m20());
        buf.putFloat(index + 36, self.m21());
        buf.putFloat(index + 40, self.m22());
        buf.putFloat(index + 44, self.m23());
        buf.putFloat(index + 48, self.m30());
        buf.putFloat(index + 52, self.m31());
        buf.putFloat(index + 56, self.m32());
        buf.putFloat(index + 60, self.m33());
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, ByteBuffer buf) {
        float _c0 = buf.getFloat(index + 0);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        float _c4 = buf.getFloat(index + 16);
        float _c5 = buf.getFloat(index + 20);
        float _c6 = buf.getFloat(index + 24);
        float _c7 = buf.getFloat(index + 28);
        float _c8 = buf.getFloat(index + 32);
        float _c9 = buf.getFloat(index + 36);
        float _c10 = buf.getFloat(index + 40);
        float _c11 = buf.getFloat(index + 44);
        float _c12 = buf.getFloat(index + 48);
        float _c13 = buf.getFloat(index + 52);
        float _c14 = buf.getFloat(index + 56);
        float _c15 = buf.getFloat(index + 60);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public DoubleBuffer storeRMAbsolute(Float4x4 self, int index, DoubleBuffer buf) {
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
        buf.put(index + 12, self.m30());
        buf.put(index + 13, self.m31());
        buf.put(index + 14, self.m32());
        buf.put(index + 15, self.m33());
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, DoubleBuffer buf) {
        float _c0 = (float) buf.get(index + 0);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        float _c4 = (float) buf.get(index + 4);
        float _c5 = (float) buf.get(index + 5);
        float _c6 = (float) buf.get(index + 6);
        float _c7 = (float) buf.get(index + 7);
        float _c8 = (float) buf.get(index + 8);
        float _c9 = (float) buf.get(index + 9);
        float _c10 = (float) buf.get(index + 10);
        float _c11 = (float) buf.get(index + 11);
        float _c12 = (float) buf.get(index + 12);
        float _c13 = (float) buf.get(index + 13);
        float _c14 = (float) buf.get(index + 14);
        float _c15 = (float) buf.get(index + 15);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeRMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf) {
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
        buf.putDouble(index + 96, self.m30());
        buf.putDouble(index + 104, self.m31());
        buf.putDouble(index + 112, self.m32());
        buf.putDouble(index + 120, self.m33());
        return buf;
    }
    public Float4x4 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        float _c0 = (float) buf.getDouble(index + 0);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        float _c4 = (float) buf.getDouble(index + 32);
        float _c5 = (float) buf.getDouble(index + 40);
        float _c6 = (float) buf.getDouble(index + 48);
        float _c7 = (float) buf.getDouble(index + 56);
        float _c8 = (float) buf.getDouble(index + 64);
        float _c9 = (float) buf.getDouble(index + 72);
        float _c10 = (float) buf.getDouble(index + 80);
        float _c11 = (float) buf.getDouble(index + 88);
        float _c12 = (float) buf.getDouble(index + 96);
        float _c13 = (float) buf.getDouble(index + 104);
        float _c14 = (float) buf.getDouble(index + 112);
        float _c15 = (float) buf.getDouble(index + 120);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public FloatBuffer storeCMAbsolute(Float4x4 self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, self.m20());
        buf.put(index + 3, self.m30());
        buf.put(_p1, self.m01());
        buf.put(_p1 + 1, self.m11());
        buf.put(_p1 + 2, self.m21());
        buf.put(_p1 + 3, self.m31());
        buf.put(_p2, self.m02());
        buf.put(_p2 + 1, self.m12());
        buf.put(_p2 + 2, self.m22());
        buf.put(_p2 + 3, self.m32());
        buf.put(_p3, self.m03());
        buf.put(_p3 + 1, self.m13());
        buf.put(_p3 + 2, self.m23());
        buf.put(_p3 + 3, self.m33());
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        float _c0 = buf.get(index);
        float _c4 = buf.get(index + 1);
        float _c8 = buf.get(index + 2);
        float _c12 = buf.get(index + 3);
        float _c1 = buf.get(_p1);
        float _c5 = buf.get(_p1 + 1);
        float _c9 = buf.get(_p1 + 2);
        float _c13 = buf.get(_p1 + 3);
        float _c2 = buf.get(_p2);
        float _c6 = buf.get(_p2 + 1);
        float _c10 = buf.get(_p2 + 2);
        float _c14 = buf.get(_p2 + 3);
        float _c3 = buf.get(_p3);
        float _c7 = buf.get(_p3 + 1);
        float _c11 = buf.get(_p3 + 2);
        float _c15 = buf.get(_p3 + 3);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeCMAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, self.m00());
        buf.putFloat(index + 4, self.m10());
        buf.putFloat(index + 8, self.m20());
        buf.putFloat(index + 12, self.m30());
        buf.putFloat(_p1, self.m01());
        buf.putFloat(_p1 + 4, self.m11());
        buf.putFloat(_p1 + 8, self.m21());
        buf.putFloat(_p1 + 12, self.m31());
        buf.putFloat(_p2, self.m02());
        buf.putFloat(_p2 + 4, self.m12());
        buf.putFloat(_p2 + 8, self.m22());
        buf.putFloat(_p2 + 12, self.m32());
        buf.putFloat(_p3, self.m03());
        buf.putFloat(_p3 + 4, self.m13());
        buf.putFloat(_p3 + 8, self.m23());
        buf.putFloat(_p3 + 12, self.m33());
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        float _c0 = buf.getFloat(index);
        float _c4 = buf.getFloat(index + 4);
        float _c8 = buf.getFloat(index + 8);
        float _c12 = buf.getFloat(index + 12);
        float _c1 = buf.getFloat(_p1);
        float _c5 = buf.getFloat(_p1 + 4);
        float _c9 = buf.getFloat(_p1 + 8);
        float _c13 = buf.getFloat(_p1 + 12);
        float _c2 = buf.getFloat(_p2);
        float _c6 = buf.getFloat(_p2 + 4);
        float _c10 = buf.getFloat(_p2 + 8);
        float _c14 = buf.getFloat(_p2 + 12);
        float _c3 = buf.getFloat(_p3);
        float _c7 = buf.getFloat(_p3 + 4);
        float _c11 = buf.getFloat(_p3 + 8);
        float _c15 = buf.getFloat(_p3 + 12);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public DoubleBuffer storeCMAbsolute(Float4x4 self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.m00());
        buf.put(index + 1, self.m10());
        buf.put(index + 2, self.m20());
        buf.put(index + 3, self.m30());
        buf.put(_p1, self.m01());
        buf.put(_p1 + 1, self.m11());
        buf.put(_p1 + 2, self.m21());
        buf.put(_p1 + 3, self.m31());
        buf.put(_p2, self.m02());
        buf.put(_p2 + 1, self.m12());
        buf.put(_p2 + 2, self.m22());
        buf.put(_p2 + 3, self.m32());
        buf.put(_p3, self.m03());
        buf.put(_p3 + 1, self.m13());
        buf.put(_p3 + 2, self.m23());
        buf.put(_p3 + 3, self.m33());
        return buf;
    }
    public Float4x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        float _c0 = (float) buf.get(index);
        float _c4 = (float) buf.get(index + 1);
        float _c8 = (float) buf.get(index + 2);
        float _c12 = (float) buf.get(index + 3);
        float _c1 = (float) buf.get(_p1);
        float _c5 = (float) buf.get(_p1 + 1);
        float _c9 = (float) buf.get(_p1 + 2);
        float _c13 = (float) buf.get(_p1 + 3);
        float _c2 = (float) buf.get(_p2);
        float _c6 = (float) buf.get(_p2 + 1);
        float _c10 = (float) buf.get(_p2 + 2);
        float _c14 = (float) buf.get(_p2 + 3);
        float _c3 = (float) buf.get(_p3);
        float _c7 = (float) buf.get(_p3 + 1);
        float _c11 = (float) buf.get(_p3 + 2);
        float _c15 = (float) buf.get(_p3 + 3);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeCMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putDouble(index, self.m00());
        buf.putDouble(index + 8, self.m10());
        buf.putDouble(index + 16, self.m20());
        buf.putDouble(index + 24, self.m30());
        buf.putDouble(_p1, self.m01());
        buf.putDouble(_p1 + 8, self.m11());
        buf.putDouble(_p1 + 16, self.m21());
        buf.putDouble(_p1 + 24, self.m31());
        buf.putDouble(_p2, self.m02());
        buf.putDouble(_p2 + 8, self.m12());
        buf.putDouble(_p2 + 16, self.m22());
        buf.putDouble(_p2 + 24, self.m32());
        buf.putDouble(_p3, self.m03());
        buf.putDouble(_p3 + 8, self.m13());
        buf.putDouble(_p3 + 16, self.m23());
        buf.putDouble(_p3 + 24, self.m33());
        return buf;
    }
    public Float4x4 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        float _c0 = (float) buf.getDouble(index);
        float _c4 = (float) buf.getDouble(index + 8);
        float _c8 = (float) buf.getDouble(index + 16);
        float _c12 = (float) buf.getDouble(index + 24);
        float _c1 = (float) buf.getDouble(_p1);
        float _c5 = (float) buf.getDouble(_p1 + 8);
        float _c9 = (float) buf.getDouble(_p1 + 16);
        float _c13 = (float) buf.getDouble(_p1 + 24);
        float _c2 = (float) buf.getDouble(_p2);
        float _c6 = (float) buf.getDouble(_p2 + 8);
        float _c10 = (float) buf.getDouble(_p2 + 16);
        float _c14 = (float) buf.getDouble(_p2 + 24);
        float _c3 = (float) buf.getDouble(_p3);
        float _c7 = (float) buf.getDouble(_p3 + 8);
        float _c11 = (float) buf.getDouble(_p3 + 16);
        float _c15 = (float) buf.getDouble(_p3 + 24);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public FloatBuffer storeRMAbsolute(Float4x4 self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
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
        buf.put(_p3, self.m30());
        buf.put(_p3 + 1, self.m31());
        buf.put(_p3 + 2, self.m32());
        buf.put(_p3 + 3, self.m33());
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        float _c0 = buf.get(index);
        float _c1 = buf.get(index + 1);
        float _c2 = buf.get(index + 2);
        float _c3 = buf.get(index + 3);
        float _c4 = buf.get(_p1);
        float _c5 = buf.get(_p1 + 1);
        float _c6 = buf.get(_p1 + 2);
        float _c7 = buf.get(_p1 + 3);
        float _c8 = buf.get(_p2);
        float _c9 = buf.get(_p2 + 1);
        float _c10 = buf.get(_p2 + 2);
        float _c11 = buf.get(_p2 + 3);
        float _c12 = buf.get(_p3);
        float _c13 = buf.get(_p3 + 1);
        float _c14 = buf.get(_p3 + 2);
        float _c15 = buf.get(_p3 + 3);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeRMAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, self.m00());
        buf.putFloat(index + 4, self.m01());
        buf.putFloat(index + 8, self.m02());
        buf.putFloat(index + 12, self.m03());
        buf.putFloat(_p1, self.m10());
        buf.putFloat(_p1 + 4, self.m11());
        buf.putFloat(_p1 + 8, self.m12());
        buf.putFloat(_p1 + 12, self.m13());
        buf.putFloat(_p2, self.m20());
        buf.putFloat(_p2 + 4, self.m21());
        buf.putFloat(_p2 + 8, self.m22());
        buf.putFloat(_p2 + 12, self.m23());
        buf.putFloat(_p3, self.m30());
        buf.putFloat(_p3 + 4, self.m31());
        buf.putFloat(_p3 + 8, self.m32());
        buf.putFloat(_p3 + 12, self.m33());
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        float _c0 = buf.getFloat(index);
        float _c1 = buf.getFloat(index + 4);
        float _c2 = buf.getFloat(index + 8);
        float _c3 = buf.getFloat(index + 12);
        float _c4 = buf.getFloat(_p1);
        float _c5 = buf.getFloat(_p1 + 4);
        float _c6 = buf.getFloat(_p1 + 8);
        float _c7 = buf.getFloat(_p1 + 12);
        float _c8 = buf.getFloat(_p2);
        float _c9 = buf.getFloat(_p2 + 4);
        float _c10 = buf.getFloat(_p2 + 8);
        float _c11 = buf.getFloat(_p2 + 12);
        float _c12 = buf.getFloat(_p3);
        float _c13 = buf.getFloat(_p3 + 4);
        float _c14 = buf.getFloat(_p3 + 8);
        float _c15 = buf.getFloat(_p3 + 12);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public DoubleBuffer storeRMAbsolute(Float4x4 self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
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
        buf.put(_p3, self.m30());
        buf.put(_p3 + 1, self.m31());
        buf.put(_p3 + 2, self.m32());
        buf.put(_p3 + 3, self.m33());
        return buf;
    }
    public Float4x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        float _c0 = (float) buf.get(index);
        float _c1 = (float) buf.get(index + 1);
        float _c2 = (float) buf.get(index + 2);
        float _c3 = (float) buf.get(index + 3);
        float _c4 = (float) buf.get(_p1);
        float _c5 = (float) buf.get(_p1 + 1);
        float _c6 = (float) buf.get(_p1 + 2);
        float _c7 = (float) buf.get(_p1 + 3);
        float _c8 = (float) buf.get(_p2);
        float _c9 = (float) buf.get(_p2 + 1);
        float _c10 = (float) buf.get(_p2 + 2);
        float _c11 = (float) buf.get(_p2 + 3);
        float _c12 = (float) buf.get(_p3);
        float _c13 = (float) buf.get(_p3 + 1);
        float _c14 = (float) buf.get(_p3 + 2);
        float _c15 = (float) buf.get(_p3 + 3);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
    public ByteBuffer storeRMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
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
        buf.putDouble(_p3, self.m30());
        buf.putDouble(_p3 + 8, self.m31());
        buf.putDouble(_p3 + 16, self.m32());
        buf.putDouble(_p3 + 24, self.m33());
        return buf;
    }
    public Float4x4 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        float _c0 = (float) buf.getDouble(index);
        float _c1 = (float) buf.getDouble(index + 8);
        float _c2 = (float) buf.getDouble(index + 16);
        float _c3 = (float) buf.getDouble(index + 24);
        float _c4 = (float) buf.getDouble(_p1);
        float _c5 = (float) buf.getDouble(_p1 + 8);
        float _c6 = (float) buf.getDouble(_p1 + 16);
        float _c7 = (float) buf.getDouble(_p1 + 24);
        float _c8 = (float) buf.getDouble(_p2);
        float _c9 = (float) buf.getDouble(_p2 + 8);
        float _c10 = (float) buf.getDouble(_p2 + 16);
        float _c11 = (float) buf.getDouble(_p2 + 24);
        float _c12 = (float) buf.getDouble(_p3);
        float _c13 = (float) buf.getDouble(_p3 + 8);
        float _c14 = (float) buf.getDouble(_p3 + 16);
        float _c15 = (float) buf.getDouble(_p3 + 24);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
}
