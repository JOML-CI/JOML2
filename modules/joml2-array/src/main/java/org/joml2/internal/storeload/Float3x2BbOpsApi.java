// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float3x2BbOpsApi implements Float3x2BbOps {
    public FloatBuffer storeCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(index + 4, self.data[4]);
        buf.put(index + 5, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        self.data[4] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.data[0]);
        buf.putFloat(index + 4, self.data[1]);
        buf.putFloat(index + 8, self.data[2]);
        buf.putFloat(index + 12, self.data[3]);
        buf.putFloat(index + 16, self.data[4]);
        buf.putFloat(index + 20, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[1] = buf.getFloat(index + 4);
        self.data[2] = buf.getFloat(index + 8);
        self.data[3] = buf.getFloat(index + 12);
        self.data[4] = buf.getFloat(index + 16);
        self.data[5] = buf.getFloat(index + 20);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(index + 4, self.data[4]);
        buf.put(index + 5, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        self.data[0] = (float) buf.get(index + 0);
        self.data[1] = (float) buf.get(index + 1);
        self.data[2] = (float) buf.get(index + 2);
        self.data[3] = (float) buf.get(index + 3);
        self.data[4] = (float) buf.get(index + 4);
        self.data[5] = (float) buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(index + 16, self.data[2]);
        buf.putDouble(index + 24, self.data[3]);
        buf.putDouble(index + 32, self.data[4]);
        buf.putDouble(index + 40, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        self.data[0] = (float) buf.getDouble(index + 0);
        self.data[1] = (float) buf.getDouble(index + 8);
        self.data[2] = (float) buf.getDouble(index + 16);
        self.data[3] = (float) buf.getDouble(index + 24);
        self.data[4] = (float) buf.getDouble(index + 32);
        self.data[5] = (float) buf.getDouble(index + 40);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[3]);
        buf.put(index + 2, self.data[1]);
        buf.put(index + 3, self.data[4]);
        buf.put(index + 4, self.data[2]);
        buf.put(index + 5, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[3] = buf.get(index + 1);
        self.data[1] = buf.get(index + 2);
        self.data[4] = buf.get(index + 3);
        self.data[2] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.data[0]);
        buf.putFloat(index + 4, self.data[3]);
        buf.putFloat(index + 8, self.data[1]);
        buf.putFloat(index + 12, self.data[4]);
        buf.putFloat(index + 16, self.data[2]);
        buf.putFloat(index + 20, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[3] = buf.getFloat(index + 4);
        self.data[1] = buf.getFloat(index + 8);
        self.data[4] = buf.getFloat(index + 12);
        self.data[2] = buf.getFloat(index + 16);
        self.data[5] = buf.getFloat(index + 20);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[3]);
        buf.put(index + 2, self.data[1]);
        buf.put(index + 3, self.data[4]);
        buf.put(index + 4, self.data[2]);
        buf.put(index + 5, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf) {
        self.data[0] = (float) buf.get(index + 0);
        self.data[3] = (float) buf.get(index + 1);
        self.data[1] = (float) buf.get(index + 2);
        self.data[4] = (float) buf.get(index + 3);
        self.data[2] = (float) buf.get(index + 4);
        self.data[5] = (float) buf.get(index + 5);
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[3]);
        buf.putDouble(index + 16, self.data[1]);
        buf.putDouble(index + 24, self.data[4]);
        buf.putDouble(index + 32, self.data[2]);
        buf.putDouble(index + 40, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf) {
        self.data[0] = (float) buf.getDouble(index + 0);
        self.data[3] = (float) buf.getDouble(index + 8);
        self.data[1] = (float) buf.getDouble(index + 16);
        self.data[4] = (float) buf.getDouble(index + 24);
        self.data[2] = (float) buf.getDouble(index + 32);
        self.data[5] = (float) buf.getDouble(index + 40);
        return self;
    }
    public FloatBuffer storeCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(_p1, self.data[3]);
        buf.put(_p1 + 1, self.data[4]);
        buf.put(_p1 + 2, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        self.data[0] = buf.get(index);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(_p1);
        self.data[4] = buf.get(_p1 + 1);
        self.data[5] = buf.get(_p1 + 2);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        buf.putFloat(index, self.data[0]);
        buf.putFloat(index + 4, self.data[1]);
        buf.putFloat(index + 8, self.data[2]);
        buf.putFloat(_p1, self.data[3]);
        buf.putFloat(_p1 + 4, self.data[4]);
        buf.putFloat(_p1 + 8, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        self.data[0] = buf.getFloat(index);
        self.data[1] = buf.getFloat(index + 4);
        self.data[2] = buf.getFloat(index + 8);
        self.data[3] = buf.getFloat(_p1);
        self.data[4] = buf.getFloat(_p1 + 4);
        self.data[5] = buf.getFloat(_p1 + 8);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(_p1, self.data[3]);
        buf.put(_p1 + 1, self.data[4]);
        buf.put(_p1 + 2, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        self.data[0] = (float) buf.get(index);
        self.data[1] = (float) buf.get(index + 1);
        self.data[2] = (float) buf.get(index + 2);
        self.data[3] = (float) buf.get(_p1);
        self.data[4] = (float) buf.get(_p1 + 1);
        self.data[5] = (float) buf.get(_p1 + 2);
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        buf.putDouble(index, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(index + 16, self.data[2]);
        buf.putDouble(_p1, self.data[3]);
        buf.putDouble(_p1 + 8, self.data[4]);
        buf.putDouble(_p1 + 16, self.data[5]);
        return buf;
    }
    public Float3x2 loadCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        self.data[0] = (float) buf.getDouble(index);
        self.data[1] = (float) buf.getDouble(index + 8);
        self.data[2] = (float) buf.getDouble(index + 16);
        self.data[3] = (float) buf.getDouble(_p1);
        self.data[4] = (float) buf.getDouble(_p1 + 8);
        self.data[5] = (float) buf.getDouble(_p1 + 16);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[3]);
        buf.put(_p1, self.data[1]);
        buf.put(_p1 + 1, self.data[4]);
        buf.put(_p2, self.data[2]);
        buf.put(_p2 + 1, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.data[0] = buf.get(index);
        self.data[3] = buf.get(index + 1);
        self.data[1] = buf.get(_p1);
        self.data[4] = buf.get(_p1 + 1);
        self.data[2] = buf.get(_p2);
        self.data[5] = buf.get(_p2 + 1);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putFloat(index, self.data[0]);
        buf.putFloat(index + 4, self.data[3]);
        buf.putFloat(_p1, self.data[1]);
        buf.putFloat(_p1 + 4, self.data[4]);
        buf.putFloat(_p2, self.data[2]);
        buf.putFloat(_p2 + 4, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.data[0] = buf.getFloat(index);
        self.data[3] = buf.getFloat(index + 4);
        self.data[1] = buf.getFloat(_p1);
        self.data[4] = buf.getFloat(_p1 + 4);
        self.data[2] = buf.getFloat(_p2);
        self.data[5] = buf.getFloat(_p2 + 4);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[3]);
        buf.put(_p1, self.data[1]);
        buf.put(_p1 + 1, self.data[4]);
        buf.put(_p2, self.data[2]);
        buf.put(_p2 + 1, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        self.data[0] = (float) buf.get(index);
        self.data[3] = (float) buf.get(index + 1);
        self.data[1] = (float) buf.get(_p1);
        self.data[4] = (float) buf.get(_p1 + 1);
        self.data[2] = (float) buf.get(_p2);
        self.data[5] = (float) buf.get(_p2 + 1);
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        buf.putDouble(index, self.data[0]);
        buf.putDouble(index + 8, self.data[3]);
        buf.putDouble(_p1, self.data[1]);
        buf.putDouble(_p1 + 8, self.data[4]);
        buf.putDouble(_p2, self.data[2]);
        buf.putDouble(_p2 + 8, self.data[5]);
        return buf;
    }
    public Float3x2 loadRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        self.data[0] = (float) buf.getDouble(index);
        self.data[3] = (float) buf.getDouble(index + 8);
        self.data[1] = (float) buf.getDouble(_p1);
        self.data[4] = (float) buf.getDouble(_p1 + 8);
        self.data[2] = (float) buf.getDouble(_p2);
        self.data[5] = (float) buf.getDouble(_p2 + 8);
        return self;
    }
}
