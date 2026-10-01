// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Double4x4BbOpsApi implements Double4x4BbOps {
    public DoubleBuffer storeCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf) {
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
        buf.put(index + 12, self.data[12]);
        buf.put(index + 13, self.data[13]);
        buf.put(index + 14, self.data[14]);
        buf.put(index + 15, self.data[15]);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf) {
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
        self.data[12] = buf.get(index + 12);
        self.data[13] = buf.get(index + 13);
        self.data[14] = buf.get(index + 14);
        self.data[15] = buf.get(index + 15);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
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
        buf.putDouble(index + 96, self.data[12]);
        buf.putDouble(index + 104, self.data[13]);
        buf.putDouble(index + 112, self.data[14]);
        buf.putDouble(index + 120, self.data[15]);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getDouble(index + 0);
        self.data[1] = buf.getDouble(index + 8);
        self.data[2] = buf.getDouble(index + 16);
        self.data[3] = buf.getDouble(index + 24);
        self.data[4] = buf.getDouble(index + 32);
        self.data[5] = buf.getDouble(index + 40);
        self.data[6] = buf.getDouble(index + 48);
        self.data[7] = buf.getDouble(index + 56);
        self.data[8] = buf.getDouble(index + 64);
        self.data[9] = buf.getDouble(index + 72);
        self.data[10] = buf.getDouble(index + 80);
        self.data[11] = buf.getDouble(index + 88);
        self.data[12] = buf.getDouble(index + 96);
        self.data[13] = buf.getDouble(index + 104);
        self.data[14] = buf.getDouble(index + 112);
        self.data[15] = buf.getDouble(index + 120);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[1]);
        buf.put(index + 2, (float) self.data[2]);
        buf.put(index + 3, (float) self.data[3]);
        buf.put(index + 4, (float) self.data[4]);
        buf.put(index + 5, (float) self.data[5]);
        buf.put(index + 6, (float) self.data[6]);
        buf.put(index + 7, (float) self.data[7]);
        buf.put(index + 8, (float) self.data[8]);
        buf.put(index + 9, (float) self.data[9]);
        buf.put(index + 10, (float) self.data[10]);
        buf.put(index + 11, (float) self.data[11]);
        buf.put(index + 12, (float) self.data[12]);
        buf.put(index + 13, (float) self.data[13]);
        buf.put(index + 14, (float) self.data[14]);
        buf.put(index + 15, (float) self.data[15]);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf) {
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
        self.data[12] = buf.get(index + 12);
        self.data[13] = buf.get(index + 13);
        self.data[14] = buf.get(index + 14);
        self.data[15] = buf.get(index + 15);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[1]);
        buf.putFloat(index + 8, (float) self.data[2]);
        buf.putFloat(index + 12, (float) self.data[3]);
        buf.putFloat(index + 16, (float) self.data[4]);
        buf.putFloat(index + 20, (float) self.data[5]);
        buf.putFloat(index + 24, (float) self.data[6]);
        buf.putFloat(index + 28, (float) self.data[7]);
        buf.putFloat(index + 32, (float) self.data[8]);
        buf.putFloat(index + 36, (float) self.data[9]);
        buf.putFloat(index + 40, (float) self.data[10]);
        buf.putFloat(index + 44, (float) self.data[11]);
        buf.putFloat(index + 48, (float) self.data[12]);
        buf.putFloat(index + 52, (float) self.data[13]);
        buf.putFloat(index + 56, (float) self.data[14]);
        buf.putFloat(index + 60, (float) self.data[15]);
        return buf;
    }
    public Double4x4 loadCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
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
        self.data[12] = buf.getFloat(index + 48);
        self.data[13] = buf.getFloat(index + 52);
        self.data[14] = buf.getFloat(index + 56);
        self.data[15] = buf.getFloat(index + 60);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[4]);
        buf.put(index + 2, self.data[8]);
        buf.put(index + 3, self.data[12]);
        buf.put(index + 4, self.data[1]);
        buf.put(index + 5, self.data[5]);
        buf.put(index + 6, self.data[9]);
        buf.put(index + 7, self.data[13]);
        buf.put(index + 8, self.data[2]);
        buf.put(index + 9, self.data[6]);
        buf.put(index + 10, self.data[10]);
        buf.put(index + 11, self.data[14]);
        buf.put(index + 12, self.data[3]);
        buf.put(index + 13, self.data[7]);
        buf.put(index + 14, self.data[11]);
        buf.put(index + 15, self.data[15]);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[4] = buf.get(index + 1);
        self.data[8] = buf.get(index + 2);
        self.data[12] = buf.get(index + 3);
        self.data[1] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        self.data[9] = buf.get(index + 6);
        self.data[13] = buf.get(index + 7);
        self.data[2] = buf.get(index + 8);
        self.data[6] = buf.get(index + 9);
        self.data[10] = buf.get(index + 10);
        self.data[14] = buf.get(index + 11);
        self.data[3] = buf.get(index + 12);
        self.data[7] = buf.get(index + 13);
        self.data[11] = buf.get(index + 14);
        self.data[15] = buf.get(index + 15);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[4]);
        buf.putDouble(index + 16, self.data[8]);
        buf.putDouble(index + 24, self.data[12]);
        buf.putDouble(index + 32, self.data[1]);
        buf.putDouble(index + 40, self.data[5]);
        buf.putDouble(index + 48, self.data[9]);
        buf.putDouble(index + 56, self.data[13]);
        buf.putDouble(index + 64, self.data[2]);
        buf.putDouble(index + 72, self.data[6]);
        buf.putDouble(index + 80, self.data[10]);
        buf.putDouble(index + 88, self.data[14]);
        buf.putDouble(index + 96, self.data[3]);
        buf.putDouble(index + 104, self.data[7]);
        buf.putDouble(index + 112, self.data[11]);
        buf.putDouble(index + 120, self.data[15]);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getDouble(index + 0);
        self.data[4] = buf.getDouble(index + 8);
        self.data[8] = buf.getDouble(index + 16);
        self.data[12] = buf.getDouble(index + 24);
        self.data[1] = buf.getDouble(index + 32);
        self.data[5] = buf.getDouble(index + 40);
        self.data[9] = buf.getDouble(index + 48);
        self.data[13] = buf.getDouble(index + 56);
        self.data[2] = buf.getDouble(index + 64);
        self.data[6] = buf.getDouble(index + 72);
        self.data[10] = buf.getDouble(index + 80);
        self.data[14] = buf.getDouble(index + 88);
        self.data[3] = buf.getDouble(index + 96);
        self.data[7] = buf.getDouble(index + 104);
        self.data[11] = buf.getDouble(index + 112);
        self.data[15] = buf.getDouble(index + 120);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[4]);
        buf.put(index + 2, (float) self.data[8]);
        buf.put(index + 3, (float) self.data[12]);
        buf.put(index + 4, (float) self.data[1]);
        buf.put(index + 5, (float) self.data[5]);
        buf.put(index + 6, (float) self.data[9]);
        buf.put(index + 7, (float) self.data[13]);
        buf.put(index + 8, (float) self.data[2]);
        buf.put(index + 9, (float) self.data[6]);
        buf.put(index + 10, (float) self.data[10]);
        buf.put(index + 11, (float) self.data[14]);
        buf.put(index + 12, (float) self.data[3]);
        buf.put(index + 13, (float) self.data[7]);
        buf.put(index + 14, (float) self.data[11]);
        buf.put(index + 15, (float) self.data[15]);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[4] = buf.get(index + 1);
        self.data[8] = buf.get(index + 2);
        self.data[12] = buf.get(index + 3);
        self.data[1] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        self.data[9] = buf.get(index + 6);
        self.data[13] = buf.get(index + 7);
        self.data[2] = buf.get(index + 8);
        self.data[6] = buf.get(index + 9);
        self.data[10] = buf.get(index + 10);
        self.data[14] = buf.get(index + 11);
        self.data[3] = buf.get(index + 12);
        self.data[7] = buf.get(index + 13);
        self.data[11] = buf.get(index + 14);
        self.data[15] = buf.get(index + 15);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[4]);
        buf.putFloat(index + 8, (float) self.data[8]);
        buf.putFloat(index + 12, (float) self.data[12]);
        buf.putFloat(index + 16, (float) self.data[1]);
        buf.putFloat(index + 20, (float) self.data[5]);
        buf.putFloat(index + 24, (float) self.data[9]);
        buf.putFloat(index + 28, (float) self.data[13]);
        buf.putFloat(index + 32, (float) self.data[2]);
        buf.putFloat(index + 36, (float) self.data[6]);
        buf.putFloat(index + 40, (float) self.data[10]);
        buf.putFloat(index + 44, (float) self.data[14]);
        buf.putFloat(index + 48, (float) self.data[3]);
        buf.putFloat(index + 52, (float) self.data[7]);
        buf.putFloat(index + 56, (float) self.data[11]);
        buf.putFloat(index + 60, (float) self.data[15]);
        return buf;
    }
    public Double4x4 loadRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[4] = buf.getFloat(index + 4);
        self.data[8] = buf.getFloat(index + 8);
        self.data[12] = buf.getFloat(index + 12);
        self.data[1] = buf.getFloat(index + 16);
        self.data[5] = buf.getFloat(index + 20);
        self.data[9] = buf.getFloat(index + 24);
        self.data[13] = buf.getFloat(index + 28);
        self.data[2] = buf.getFloat(index + 32);
        self.data[6] = buf.getFloat(index + 36);
        self.data[10] = buf.getFloat(index + 40);
        self.data[14] = buf.getFloat(index + 44);
        self.data[3] = buf.getFloat(index + 48);
        self.data[7] = buf.getFloat(index + 52);
        self.data[11] = buf.getFloat(index + 56);
        self.data[15] = buf.getFloat(index + 60);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
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
        buf.put(_p3, self.data[12]);
        buf.put(_p3 + 1, self.data[13]);
        buf.put(_p3 + 2, self.data[14]);
        buf.put(_p3 + 3, self.data[15]);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
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
        self.data[12] = buf.get(_p3);
        self.data[13] = buf.get(_p3 + 1);
        self.data[14] = buf.get(_p3 + 2);
        self.data[15] = buf.get(_p3 + 3);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
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
        buf.putDouble(_p3, self.data[12]);
        buf.putDouble(_p3 + 8, self.data[13]);
        buf.putDouble(_p3 + 16, self.data[14]);
        buf.putDouble(_p3 + 24, self.data[15]);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.data[0] = buf.getDouble(index);
        self.data[1] = buf.getDouble(index + 8);
        self.data[2] = buf.getDouble(index + 16);
        self.data[3] = buf.getDouble(index + 24);
        self.data[4] = buf.getDouble(_p1);
        self.data[5] = buf.getDouble(_p1 + 8);
        self.data[6] = buf.getDouble(_p1 + 16);
        self.data[7] = buf.getDouble(_p1 + 24);
        self.data[8] = buf.getDouble(_p2);
        self.data[9] = buf.getDouble(_p2 + 8);
        self.data[10] = buf.getDouble(_p2 + 16);
        self.data[11] = buf.getDouble(_p2 + 24);
        self.data[12] = buf.getDouble(_p3);
        self.data[13] = buf.getDouble(_p3 + 8);
        self.data[14] = buf.getDouble(_p3 + 16);
        self.data[15] = buf.getDouble(_p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[1]);
        buf.put(index + 2, (float) self.data[2]);
        buf.put(index + 3, (float) self.data[3]);
        buf.put(_p1, (float) self.data[4]);
        buf.put(_p1 + 1, (float) self.data[5]);
        buf.put(_p1 + 2, (float) self.data[6]);
        buf.put(_p1 + 3, (float) self.data[7]);
        buf.put(_p2, (float) self.data[8]);
        buf.put(_p2 + 1, (float) self.data[9]);
        buf.put(_p2 + 2, (float) self.data[10]);
        buf.put(_p2 + 3, (float) self.data[11]);
        buf.put(_p3, (float) self.data[12]);
        buf.put(_p3 + 1, (float) self.data[13]);
        buf.put(_p3 + 2, (float) self.data[14]);
        buf.put(_p3 + 3, (float) self.data[15]);
        return buf;
    }
    public Double4x4 loadCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
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
        self.data[12] = buf.get(_p3);
        self.data[13] = buf.get(_p3 + 1);
        self.data[14] = buf.get(_p3 + 2);
        self.data[15] = buf.get(_p3 + 3);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[1]);
        buf.putFloat(index + 8, (float) self.data[2]);
        buf.putFloat(index + 12, (float) self.data[3]);
        buf.putFloat(_p1, (float) self.data[4]);
        buf.putFloat(_p1 + 4, (float) self.data[5]);
        buf.putFloat(_p1 + 8, (float) self.data[6]);
        buf.putFloat(_p1 + 12, (float) self.data[7]);
        buf.putFloat(_p2, (float) self.data[8]);
        buf.putFloat(_p2 + 4, (float) self.data[9]);
        buf.putFloat(_p2 + 8, (float) self.data[10]);
        buf.putFloat(_p2 + 12, (float) self.data[11]);
        buf.putFloat(_p3, (float) self.data[12]);
        buf.putFloat(_p3 + 4, (float) self.data[13]);
        buf.putFloat(_p3 + 8, (float) self.data[14]);
        buf.putFloat(_p3 + 12, (float) self.data[15]);
        return buf;
    }
    public Double4x4 loadCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
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
        self.data[12] = buf.getFloat(_p3);
        self.data[13] = buf.getFloat(_p3 + 4);
        self.data[14] = buf.getFloat(_p3 + 8);
        self.data[15] = buf.getFloat(_p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[4]);
        buf.put(index + 2, self.data[8]);
        buf.put(index + 3, self.data[12]);
        buf.put(_p1, self.data[1]);
        buf.put(_p1 + 1, self.data[5]);
        buf.put(_p1 + 2, self.data[9]);
        buf.put(_p1 + 3, self.data[13]);
        buf.put(_p2, self.data[2]);
        buf.put(_p2 + 1, self.data[6]);
        buf.put(_p2 + 2, self.data[10]);
        buf.put(_p2 + 3, self.data[14]);
        buf.put(_p3, self.data[3]);
        buf.put(_p3 + 1, self.data[7]);
        buf.put(_p3 + 2, self.data[11]);
        buf.put(_p3 + 3, self.data[15]);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.data[0] = buf.get(index);
        self.data[4] = buf.get(index + 1);
        self.data[8] = buf.get(index + 2);
        self.data[12] = buf.get(index + 3);
        self.data[1] = buf.get(_p1);
        self.data[5] = buf.get(_p1 + 1);
        self.data[9] = buf.get(_p1 + 2);
        self.data[13] = buf.get(_p1 + 3);
        self.data[2] = buf.get(_p2);
        self.data[6] = buf.get(_p2 + 1);
        self.data[10] = buf.get(_p2 + 2);
        self.data[14] = buf.get(_p2 + 3);
        self.data[3] = buf.get(_p3);
        self.data[7] = buf.get(_p3 + 1);
        self.data[11] = buf.get(_p3 + 2);
        self.data[15] = buf.get(_p3 + 3);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putDouble(index, self.data[0]);
        buf.putDouble(index + 8, self.data[4]);
        buf.putDouble(index + 16, self.data[8]);
        buf.putDouble(index + 24, self.data[12]);
        buf.putDouble(_p1, self.data[1]);
        buf.putDouble(_p1 + 8, self.data[5]);
        buf.putDouble(_p1 + 16, self.data[9]);
        buf.putDouble(_p1 + 24, self.data[13]);
        buf.putDouble(_p2, self.data[2]);
        buf.putDouble(_p2 + 8, self.data[6]);
        buf.putDouble(_p2 + 16, self.data[10]);
        buf.putDouble(_p2 + 24, self.data[14]);
        buf.putDouble(_p3, self.data[3]);
        buf.putDouble(_p3 + 8, self.data[7]);
        buf.putDouble(_p3 + 16, self.data[11]);
        buf.putDouble(_p3 + 24, self.data[15]);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.data[0] = buf.getDouble(index);
        self.data[4] = buf.getDouble(index + 8);
        self.data[8] = buf.getDouble(index + 16);
        self.data[12] = buf.getDouble(index + 24);
        self.data[1] = buf.getDouble(_p1);
        self.data[5] = buf.getDouble(_p1 + 8);
        self.data[9] = buf.getDouble(_p1 + 16);
        self.data[13] = buf.getDouble(_p1 + 24);
        self.data[2] = buf.getDouble(_p2);
        self.data[6] = buf.getDouble(_p2 + 8);
        self.data[10] = buf.getDouble(_p2 + 16);
        self.data[14] = buf.getDouble(_p2 + 24);
        self.data[3] = buf.getDouble(_p3);
        self.data[7] = buf.getDouble(_p3 + 8);
        self.data[11] = buf.getDouble(_p3 + 16);
        self.data[15] = buf.getDouble(_p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public FloatBuffer storeRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, (float) self.data[0]);
        buf.put(index + 1, (float) self.data[4]);
        buf.put(index + 2, (float) self.data[8]);
        buf.put(index + 3, (float) self.data[12]);
        buf.put(_p1, (float) self.data[1]);
        buf.put(_p1 + 1, (float) self.data[5]);
        buf.put(_p1 + 2, (float) self.data[9]);
        buf.put(_p1 + 3, (float) self.data[13]);
        buf.put(_p2, (float) self.data[2]);
        buf.put(_p2 + 1, (float) self.data[6]);
        buf.put(_p2 + 2, (float) self.data[10]);
        buf.put(_p2 + 3, (float) self.data[14]);
        buf.put(_p3, (float) self.data[3]);
        buf.put(_p3 + 1, (float) self.data[7]);
        buf.put(_p3 + 2, (float) self.data[11]);
        buf.put(_p3 + 3, (float) self.data[15]);
        return buf;
    }
    public Double4x4 loadRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.data[0] = buf.get(index);
        self.data[4] = buf.get(index + 1);
        self.data[8] = buf.get(index + 2);
        self.data[12] = buf.get(index + 3);
        self.data[1] = buf.get(_p1);
        self.data[5] = buf.get(_p1 + 1);
        self.data[9] = buf.get(_p1 + 2);
        self.data[13] = buf.get(_p1 + 3);
        self.data[2] = buf.get(_p2);
        self.data[6] = buf.get(_p2 + 1);
        self.data[10] = buf.get(_p2 + 2);
        self.data[14] = buf.get(_p2 + 3);
        self.data[3] = buf.get(_p3);
        self.data[7] = buf.get(_p3 + 1);
        self.data[11] = buf.get(_p3 + 2);
        self.data[15] = buf.get(_p3 + 3);
        self.properties = self.determineProperties();
        return self;
    }
    public ByteBuffer storeRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, (float) self.data[0]);
        buf.putFloat(index + 4, (float) self.data[4]);
        buf.putFloat(index + 8, (float) self.data[8]);
        buf.putFloat(index + 12, (float) self.data[12]);
        buf.putFloat(_p1, (float) self.data[1]);
        buf.putFloat(_p1 + 4, (float) self.data[5]);
        buf.putFloat(_p1 + 8, (float) self.data[9]);
        buf.putFloat(_p1 + 12, (float) self.data[13]);
        buf.putFloat(_p2, (float) self.data[2]);
        buf.putFloat(_p2 + 4, (float) self.data[6]);
        buf.putFloat(_p2 + 8, (float) self.data[10]);
        buf.putFloat(_p2 + 12, (float) self.data[14]);
        buf.putFloat(_p3, (float) self.data[3]);
        buf.putFloat(_p3 + 4, (float) self.data[7]);
        buf.putFloat(_p3 + 8, (float) self.data[11]);
        buf.putFloat(_p3 + 12, (float) self.data[15]);
        return buf;
    }
    public Double4x4 loadRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.data[0] = buf.getFloat(index);
        self.data[4] = buf.getFloat(index + 4);
        self.data[8] = buf.getFloat(index + 8);
        self.data[12] = buf.getFloat(index + 12);
        self.data[1] = buf.getFloat(_p1);
        self.data[5] = buf.getFloat(_p1 + 4);
        self.data[9] = buf.getFloat(_p1 + 8);
        self.data[13] = buf.getFloat(_p1 + 12);
        self.data[2] = buf.getFloat(_p2);
        self.data[6] = buf.getFloat(_p2 + 4);
        self.data[10] = buf.getFloat(_p2 + 8);
        self.data[14] = buf.getFloat(_p2 + 12);
        self.data[3] = buf.getFloat(_p3);
        self.data[7] = buf.getFloat(_p3 + 4);
        self.data[11] = buf.getFloat(_p3 + 8);
        self.data[15] = buf.getFloat(_p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }
}
