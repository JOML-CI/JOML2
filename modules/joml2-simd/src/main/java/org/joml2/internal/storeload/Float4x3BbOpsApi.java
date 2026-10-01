// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float4x3BbOpsApi implements Float4x3BbOps {
    public FloatBuffer storeCMAbsolute(Float4x3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(index + 4, self.data[4]);
        buf.put(index + 5, self.data[5]);
        buf.put(index + 6, self.data[6]);
        buf.put(index + 7, self.data[7]);
        buf.put(index + 8, self.data[8]);
        buf.put(index + 9, self.data[9]);
        buf.put(index + 10, self.data[10]);
        buf.put(index + 11, self.data[11]);
        return buf;
    }
    public Float4x3 loadCMAbsolute(Float4x3Impl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        self.data[4] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        self.data[6] = buf.get(index + 6);
        self.data[7] = buf.get(index + 7);
        self.data[8] = buf.get(index + 8);
        self.data[9] = buf.get(index + 9);
        self.data[10] = buf.get(index + 10);
        self.data[11] = buf.get(index + 11);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float4x3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.data[0]);
        buf.putFloat(index + 4, self.data[1]);
        buf.putFloat(index + 8, self.data[2]);
        buf.putFloat(index + 12, self.data[3]);
        buf.putFloat(index + 16, self.data[4]);
        buf.putFloat(index + 20, self.data[5]);
        buf.putFloat(index + 24, self.data[6]);
        buf.putFloat(index + 28, self.data[7]);
        buf.putFloat(index + 32, self.data[8]);
        buf.putFloat(index + 36, self.data[9]);
        buf.putFloat(index + 40, self.data[10]);
        buf.putFloat(index + 44, self.data[11]);
        return buf;
    }
    public Float4x3 loadCMAbsolute(Float4x3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[1] = buf.getFloat(index + 4);
        self.data[2] = buf.getFloat(index + 8);
        self.data[3] = buf.getFloat(index + 12);
        self.data[4] = buf.getFloat(index + 16);
        self.data[5] = buf.getFloat(index + 20);
        self.data[6] = buf.getFloat(index + 24);
        self.data[7] = buf.getFloat(index + 28);
        self.data[8] = buf.getFloat(index + 32);
        self.data[9] = buf.getFloat(index + 36);
        self.data[10] = buf.getFloat(index + 40);
        self.data[11] = buf.getFloat(index + 44);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float4x3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(index + 4, self.data[4]);
        buf.put(index + 5, self.data[5]);
        buf.put(index + 6, self.data[6]);
        buf.put(index + 7, self.data[7]);
        buf.put(index + 8, self.data[8]);
        buf.put(index + 9, self.data[9]);
        buf.put(index + 10, self.data[10]);
        buf.put(index + 11, self.data[11]);
        return buf;
    }
    public Float4x3 loadCMAbsolute(Float4x3Impl self, int index, DoubleBuffer buf) {
        self.data[0] = (float) buf.get(index + 0);
        self.data[1] = (float) buf.get(index + 1);
        self.data[2] = (float) buf.get(index + 2);
        self.data[3] = (float) buf.get(index + 3);
        self.data[4] = (float) buf.get(index + 4);
        self.data[5] = (float) buf.get(index + 5);
        self.data[6] = (float) buf.get(index + 6);
        self.data[7] = (float) buf.get(index + 7);
        self.data[8] = (float) buf.get(index + 8);
        self.data[9] = (float) buf.get(index + 9);
        self.data[10] = (float) buf.get(index + 10);
        self.data[11] = (float) buf.get(index + 11);
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float4x3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(index + 16, self.data[2]);
        buf.putDouble(index + 24, self.data[3]);
        buf.putDouble(index + 32, self.data[4]);
        buf.putDouble(index + 40, self.data[5]);
        buf.putDouble(index + 48, self.data[6]);
        buf.putDouble(index + 56, self.data[7]);
        buf.putDouble(index + 64, self.data[8]);
        buf.putDouble(index + 72, self.data[9]);
        buf.putDouble(index + 80, self.data[10]);
        buf.putDouble(index + 88, self.data[11]);
        return buf;
    }
    public Float4x3 loadCMDoubleAbsolute(Float4x3Impl self, int index, ByteBuffer buf) {
        self.data[0] = (float) buf.getDouble(index + 0);
        self.data[1] = (float) buf.getDouble(index + 8);
        self.data[2] = (float) buf.getDouble(index + 16);
        self.data[3] = (float) buf.getDouble(index + 24);
        self.data[4] = (float) buf.getDouble(index + 32);
        self.data[5] = (float) buf.getDouble(index + 40);
        self.data[6] = (float) buf.getDouble(index + 48);
        self.data[7] = (float) buf.getDouble(index + 56);
        self.data[8] = (float) buf.getDouble(index + 64);
        self.data[9] = (float) buf.getDouble(index + 72);
        self.data[10] = (float) buf.getDouble(index + 80);
        self.data[11] = (float) buf.getDouble(index + 88);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float4x3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[4]);
        buf.put(index + 2, self.data[8]);
        buf.put(index + 3, self.data[1]);
        buf.put(index + 4, self.data[5]);
        buf.put(index + 5, self.data[9]);
        buf.put(index + 6, self.data[2]);
        buf.put(index + 7, self.data[6]);
        buf.put(index + 8, self.data[10]);
        buf.put(index + 9, self.data[3]);
        buf.put(index + 10, self.data[7]);
        buf.put(index + 11, self.data[11]);
        return buf;
    }
    public Float4x3 loadRMAbsolute(Float4x3Impl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[4] = buf.get(index + 1);
        self.data[8] = buf.get(index + 2);
        self.data[1] = buf.get(index + 3);
        self.data[5] = buf.get(index + 4);
        self.data[9] = buf.get(index + 5);
        self.data[2] = buf.get(index + 6);
        self.data[6] = buf.get(index + 7);
        self.data[10] = buf.get(index + 8);
        self.data[3] = buf.get(index + 9);
        self.data[7] = buf.get(index + 10);
        self.data[11] = buf.get(index + 11);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float4x3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.data[0]);
        buf.putFloat(index + 4, self.data[4]);
        buf.putFloat(index + 8, self.data[8]);
        buf.putFloat(index + 12, self.data[1]);
        buf.putFloat(index + 16, self.data[5]);
        buf.putFloat(index + 20, self.data[9]);
        buf.putFloat(index + 24, self.data[2]);
        buf.putFloat(index + 28, self.data[6]);
        buf.putFloat(index + 32, self.data[10]);
        buf.putFloat(index + 36, self.data[3]);
        buf.putFloat(index + 40, self.data[7]);
        buf.putFloat(index + 44, self.data[11]);
        return buf;
    }
    public Float4x3 loadRMAbsolute(Float4x3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[4] = buf.getFloat(index + 4);
        self.data[8] = buf.getFloat(index + 8);
        self.data[1] = buf.getFloat(index + 12);
        self.data[5] = buf.getFloat(index + 16);
        self.data[9] = buf.getFloat(index + 20);
        self.data[2] = buf.getFloat(index + 24);
        self.data[6] = buf.getFloat(index + 28);
        self.data[10] = buf.getFloat(index + 32);
        self.data[3] = buf.getFloat(index + 36);
        self.data[7] = buf.getFloat(index + 40);
        self.data[11] = buf.getFloat(index + 44);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float4x3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[4]);
        buf.put(index + 2, self.data[8]);
        buf.put(index + 3, self.data[1]);
        buf.put(index + 4, self.data[5]);
        buf.put(index + 5, self.data[9]);
        buf.put(index + 6, self.data[2]);
        buf.put(index + 7, self.data[6]);
        buf.put(index + 8, self.data[10]);
        buf.put(index + 9, self.data[3]);
        buf.put(index + 10, self.data[7]);
        buf.put(index + 11, self.data[11]);
        return buf;
    }
    public Float4x3 loadRMAbsolute(Float4x3Impl self, int index, DoubleBuffer buf) {
        self.data[0] = (float) buf.get(index + 0);
        self.data[4] = (float) buf.get(index + 1);
        self.data[8] = (float) buf.get(index + 2);
        self.data[1] = (float) buf.get(index + 3);
        self.data[5] = (float) buf.get(index + 4);
        self.data[9] = (float) buf.get(index + 5);
        self.data[2] = (float) buf.get(index + 6);
        self.data[6] = (float) buf.get(index + 7);
        self.data[10] = (float) buf.get(index + 8);
        self.data[3] = (float) buf.get(index + 9);
        self.data[7] = (float) buf.get(index + 10);
        self.data[11] = (float) buf.get(index + 11);
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float4x3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[4]);
        buf.putDouble(index + 16, self.data[8]);
        buf.putDouble(index + 24, self.data[1]);
        buf.putDouble(index + 32, self.data[5]);
        buf.putDouble(index + 40, self.data[9]);
        buf.putDouble(index + 48, self.data[2]);
        buf.putDouble(index + 56, self.data[6]);
        buf.putDouble(index + 64, self.data[10]);
        buf.putDouble(index + 72, self.data[3]);
        buf.putDouble(index + 80, self.data[7]);
        buf.putDouble(index + 88, self.data[11]);
        return buf;
    }
    public Float4x3 loadRMDoubleAbsolute(Float4x3Impl self, int index, ByteBuffer buf) {
        self.data[0] = (float) buf.getDouble(index + 0);
        self.data[4] = (float) buf.getDouble(index + 8);
        self.data[8] = (float) buf.getDouble(index + 16);
        self.data[1] = (float) buf.getDouble(index + 24);
        self.data[5] = (float) buf.getDouble(index + 32);
        self.data[9] = (float) buf.getDouble(index + 40);
        self.data[2] = (float) buf.getDouble(index + 48);
        self.data[6] = (float) buf.getDouble(index + 56);
        self.data[10] = (float) buf.getDouble(index + 64);
        self.data[3] = (float) buf.getDouble(index + 72);
        self.data[7] = (float) buf.getDouble(index + 80);
        self.data[11] = (float) buf.getDouble(index + 88);
        return self;
    }
    public FloatBuffer storeCMAbsolute(Float4x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(_p1, self.data[4]);
        buf.put(_p1 + 1, self.data[5]);
        buf.put(_p1 + 2, self.data[6]);
        buf.put(_p1 + 3, self.data[7]);
        buf.put(_p2, self.data[8]);
        buf.put(_p2 + 1, self.data[9]);
        buf.put(_p2 + 2, self.data[10]);
        buf.put(_p2 + 3, self.data[11]);
        return buf;
    }
    public Float4x3 loadCMAbsolute(Float4x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.data[0] = buf.get(index);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        self.data[4] = buf.get(_p1);
        self.data[5] = buf.get(_p1 + 1);
        self.data[6] = buf.get(_p1 + 2);
        self.data[7] = buf.get(_p1 + 3);
        self.data[8] = buf.get(_p2);
        self.data[9] = buf.get(_p2 + 1);
        self.data[10] = buf.get(_p2 + 2);
        self.data[11] = buf.get(_p2 + 3);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putFloat(index, self.data[0]);
        buf.putFloat(index + 4, self.data[1]);
        buf.putFloat(index + 8, self.data[2]);
        buf.putFloat(index + 12, self.data[3]);
        buf.putFloat(_p1, self.data[4]);
        buf.putFloat(_p1 + 4, self.data[5]);
        buf.putFloat(_p1 + 8, self.data[6]);
        buf.putFloat(_p1 + 12, self.data[7]);
        buf.putFloat(_p2, self.data[8]);
        buf.putFloat(_p2 + 4, self.data[9]);
        buf.putFloat(_p2 + 8, self.data[10]);
        buf.putFloat(_p2 + 12, self.data[11]);
        return buf;
    }
    public Float4x3 loadCMAbsolute(Float4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.data[0] = buf.getFloat(index);
        self.data[1] = buf.getFloat(index + 4);
        self.data[2] = buf.getFloat(index + 8);
        self.data[3] = buf.getFloat(index + 12);
        self.data[4] = buf.getFloat(_p1);
        self.data[5] = buf.getFloat(_p1 + 4);
        self.data[6] = buf.getFloat(_p1 + 8);
        self.data[7] = buf.getFloat(_p1 + 12);
        self.data[8] = buf.getFloat(_p2);
        self.data[9] = buf.getFloat(_p2 + 4);
        self.data[10] = buf.getFloat(_p2 + 8);
        self.data[11] = buf.getFloat(_p2 + 12);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float4x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(_p1, self.data[4]);
        buf.put(_p1 + 1, self.data[5]);
        buf.put(_p1 + 2, self.data[6]);
        buf.put(_p1 + 3, self.data[7]);
        buf.put(_p2, self.data[8]);
        buf.put(_p2 + 1, self.data[9]);
        buf.put(_p2 + 2, self.data[10]);
        buf.put(_p2 + 3, self.data[11]);
        return buf;
    }
    public Float4x3 loadCMAbsolute(Float4x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.data[0] = (float) buf.get(index);
        self.data[1] = (float) buf.get(index + 1);
        self.data[2] = (float) buf.get(index + 2);
        self.data[3] = (float) buf.get(index + 3);
        self.data[4] = (float) buf.get(_p1);
        self.data[5] = (float) buf.get(_p1 + 1);
        self.data[6] = (float) buf.get(_p1 + 2);
        self.data[7] = (float) buf.get(_p1 + 3);
        self.data[8] = (float) buf.get(_p2);
        self.data[9] = (float) buf.get(_p2 + 1);
        self.data[10] = (float) buf.get(_p2 + 2);
        self.data[11] = (float) buf.get(_p2 + 3);
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putDouble(index, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(index + 16, self.data[2]);
        buf.putDouble(index + 24, self.data[3]);
        buf.putDouble(_p1, self.data[4]);
        buf.putDouble(_p1 + 8, self.data[5]);
        buf.putDouble(_p1 + 16, self.data[6]);
        buf.putDouble(_p1 + 24, self.data[7]);
        buf.putDouble(_p2, self.data[8]);
        buf.putDouble(_p2 + 8, self.data[9]);
        buf.putDouble(_p2 + 16, self.data[10]);
        buf.putDouble(_p2 + 24, self.data[11]);
        return buf;
    }
    public Float4x3 loadCMDoubleAbsolute(Float4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.data[0] = (float) buf.getDouble(index);
        self.data[1] = (float) buf.getDouble(index + 8);
        self.data[2] = (float) buf.getDouble(index + 16);
        self.data[3] = (float) buf.getDouble(index + 24);
        self.data[4] = (float) buf.getDouble(_p1);
        self.data[5] = (float) buf.getDouble(_p1 + 8);
        self.data[6] = (float) buf.getDouble(_p1 + 16);
        self.data[7] = (float) buf.getDouble(_p1 + 24);
        self.data[8] = (float) buf.getDouble(_p2);
        self.data[9] = (float) buf.getDouble(_p2 + 8);
        self.data[10] = (float) buf.getDouble(_p2 + 16);
        self.data[11] = (float) buf.getDouble(_p2 + 24);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float4x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[4]);
        buf.put(index + 2, self.data[8]);
        buf.put(_p1, self.data[1]);
        buf.put(_p1 + 1, self.data[5]);
        buf.put(_p1 + 2, self.data[9]);
        buf.put(_p2, self.data[2]);
        buf.put(_p2 + 1, self.data[6]);
        buf.put(_p2 + 2, self.data[10]);
        buf.put(_p3, self.data[3]);
        buf.put(_p3 + 1, self.data[7]);
        buf.put(_p3 + 2, self.data[11]);
        return buf;
    }
    public Float4x3 loadRMAbsolute(Float4x3Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.data[0] = buf.get(index);
        self.data[4] = buf.get(index + 1);
        self.data[8] = buf.get(index + 2);
        self.data[1] = buf.get(_p1);
        self.data[5] = buf.get(_p1 + 1);
        self.data[9] = buf.get(_p1 + 2);
        self.data[2] = buf.get(_p2);
        self.data[6] = buf.get(_p2 + 1);
        self.data[10] = buf.get(_p2 + 2);
        self.data[3] = buf.get(_p3);
        self.data[7] = buf.get(_p3 + 1);
        self.data[11] = buf.get(_p3 + 2);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, self.data[0]);
        buf.putFloat(index + 4, self.data[4]);
        buf.putFloat(index + 8, self.data[8]);
        buf.putFloat(_p1, self.data[1]);
        buf.putFloat(_p1 + 4, self.data[5]);
        buf.putFloat(_p1 + 8, self.data[9]);
        buf.putFloat(_p2, self.data[2]);
        buf.putFloat(_p2 + 4, self.data[6]);
        buf.putFloat(_p2 + 8, self.data[10]);
        buf.putFloat(_p3, self.data[3]);
        buf.putFloat(_p3 + 4, self.data[7]);
        buf.putFloat(_p3 + 8, self.data[11]);
        return buf;
    }
    public Float4x3 loadRMAbsolute(Float4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.data[0] = buf.getFloat(index);
        self.data[4] = buf.getFloat(index + 4);
        self.data[8] = buf.getFloat(index + 8);
        self.data[1] = buf.getFloat(_p1);
        self.data[5] = buf.getFloat(_p1 + 4);
        self.data[9] = buf.getFloat(_p1 + 8);
        self.data[2] = buf.getFloat(_p2);
        self.data[6] = buf.getFloat(_p2 + 4);
        self.data[10] = buf.getFloat(_p2 + 8);
        self.data[3] = buf.getFloat(_p3);
        self.data[7] = buf.getFloat(_p3 + 4);
        self.data[11] = buf.getFloat(_p3 + 8);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float4x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[4]);
        buf.put(index + 2, self.data[8]);
        buf.put(_p1, self.data[1]);
        buf.put(_p1 + 1, self.data[5]);
        buf.put(_p1 + 2, self.data[9]);
        buf.put(_p2, self.data[2]);
        buf.put(_p2 + 1, self.data[6]);
        buf.put(_p2 + 2, self.data[10]);
        buf.put(_p3, self.data[3]);
        buf.put(_p3 + 1, self.data[7]);
        buf.put(_p3 + 2, self.data[11]);
        return buf;
    }
    public Float4x3 loadRMAbsolute(Float4x3Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.data[0] = (float) buf.get(index);
        self.data[4] = (float) buf.get(index + 1);
        self.data[8] = (float) buf.get(index + 2);
        self.data[1] = (float) buf.get(_p1);
        self.data[5] = (float) buf.get(_p1 + 1);
        self.data[9] = (float) buf.get(_p1 + 2);
        self.data[2] = (float) buf.get(_p2);
        self.data[6] = (float) buf.get(_p2 + 1);
        self.data[10] = (float) buf.get(_p2 + 2);
        self.data[3] = (float) buf.get(_p3);
        self.data[7] = (float) buf.get(_p3 + 1);
        self.data[11] = (float) buf.get(_p3 + 2);
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putDouble(index, self.data[0]);
        buf.putDouble(index + 8, self.data[4]);
        buf.putDouble(index + 16, self.data[8]);
        buf.putDouble(_p1, self.data[1]);
        buf.putDouble(_p1 + 8, self.data[5]);
        buf.putDouble(_p1 + 16, self.data[9]);
        buf.putDouble(_p2, self.data[2]);
        buf.putDouble(_p2 + 8, self.data[6]);
        buf.putDouble(_p2 + 16, self.data[10]);
        buf.putDouble(_p3, self.data[3]);
        buf.putDouble(_p3 + 8, self.data[7]);
        buf.putDouble(_p3 + 16, self.data[11]);
        return buf;
    }
    public Float4x3 loadRMDoubleAbsolute(Float4x3Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.data[0] = (float) buf.getDouble(index);
        self.data[4] = (float) buf.getDouble(index + 8);
        self.data[8] = (float) buf.getDouble(index + 16);
        self.data[1] = (float) buf.getDouble(_p1);
        self.data[5] = (float) buf.getDouble(_p1 + 8);
        self.data[9] = (float) buf.getDouble(_p1 + 16);
        self.data[2] = (float) buf.getDouble(_p2);
        self.data[6] = (float) buf.getDouble(_p2 + 8);
        self.data[10] = (float) buf.getDouble(_p2 + 16);
        self.data[3] = (float) buf.getDouble(_p3);
        self.data[7] = (float) buf.getDouble(_p3 + 8);
        self.data[11] = (float) buf.getDouble(_p3 + 16);
        return self;
    }
}
