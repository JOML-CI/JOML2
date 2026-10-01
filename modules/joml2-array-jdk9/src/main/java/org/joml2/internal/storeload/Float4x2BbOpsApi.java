// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float4x2BbOpsApi implements Float4x2BbOps {
    public FloatBuffer storeCMAbsolute(Float4x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(index + 4, self.data[4]);
        buf.put(index + 5, self.data[5]);
        buf.put(index + 6, self.data[6]);
        buf.put(index + 7, self.data[7]);
        return buf;
    }
    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        self.data[4] = buf.get(index + 4);
        self.data[5] = buf.get(index + 5);
        self.data[6] = buf.get(index + 6);
        self.data[7] = buf.get(index + 7);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.data[0]);
        buf.putFloat(index + 4, self.data[1]);
        buf.putFloat(index + 8, self.data[2]);
        buf.putFloat(index + 12, self.data[3]);
        buf.putFloat(index + 16, self.data[4]);
        buf.putFloat(index + 20, self.data[5]);
        buf.putFloat(index + 24, self.data[6]);
        buf.putFloat(index + 28, self.data[7]);
        return buf;
    }
    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[1] = buf.getFloat(index + 4);
        self.data[2] = buf.getFloat(index + 8);
        self.data[3] = buf.getFloat(index + 12);
        self.data[4] = buf.getFloat(index + 16);
        self.data[5] = buf.getFloat(index + 20);
        self.data[6] = buf.getFloat(index + 24);
        self.data[7] = buf.getFloat(index + 28);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(index + 4, self.data[4]);
        buf.put(index + 5, self.data[5]);
        buf.put(index + 6, self.data[6]);
        buf.put(index + 7, self.data[7]);
        return buf;
    }
    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf) {
        self.data[0] = (float) buf.get(index + 0);
        self.data[1] = (float) buf.get(index + 1);
        self.data[2] = (float) buf.get(index + 2);
        self.data[3] = (float) buf.get(index + 3);
        self.data[4] = (float) buf.get(index + 4);
        self.data[5] = (float) buf.get(index + 5);
        self.data[6] = (float) buf.get(index + 6);
        self.data[7] = (float) buf.get(index + 7);
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(index + 16, self.data[2]);
        buf.putDouble(index + 24, self.data[3]);
        buf.putDouble(index + 32, self.data[4]);
        buf.putDouble(index + 40, self.data[5]);
        buf.putDouble(index + 48, self.data[6]);
        buf.putDouble(index + 56, self.data[7]);
        return buf;
    }
    public Float4x2 loadCMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        self.data[0] = (float) buf.getDouble(index + 0);
        self.data[1] = (float) buf.getDouble(index + 8);
        self.data[2] = (float) buf.getDouble(index + 16);
        self.data[3] = (float) buf.getDouble(index + 24);
        self.data[4] = (float) buf.getDouble(index + 32);
        self.data[5] = (float) buf.getDouble(index + 40);
        self.data[6] = (float) buf.getDouble(index + 48);
        self.data[7] = (float) buf.getDouble(index + 56);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float4x2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[4]);
        buf.put(index + 2, self.data[1]);
        buf.put(index + 3, self.data[5]);
        buf.put(index + 4, self.data[2]);
        buf.put(index + 5, self.data[6]);
        buf.put(index + 6, self.data[3]);
        buf.put(index + 7, self.data[7]);
        return buf;
    }
    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[4] = buf.get(index + 1);
        self.data[1] = buf.get(index + 2);
        self.data[5] = buf.get(index + 3);
        self.data[2] = buf.get(index + 4);
        self.data[6] = buf.get(index + 5);
        self.data[3] = buf.get(index + 6);
        self.data[7] = buf.get(index + 7);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.data[0]);
        buf.putFloat(index + 4, self.data[4]);
        buf.putFloat(index + 8, self.data[1]);
        buf.putFloat(index + 12, self.data[5]);
        buf.putFloat(index + 16, self.data[2]);
        buf.putFloat(index + 20, self.data[6]);
        buf.putFloat(index + 24, self.data[3]);
        buf.putFloat(index + 28, self.data[7]);
        return buf;
    }
    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[4] = buf.getFloat(index + 4);
        self.data[1] = buf.getFloat(index + 8);
        self.data[5] = buf.getFloat(index + 12);
        self.data[2] = buf.getFloat(index + 16);
        self.data[6] = buf.getFloat(index + 20);
        self.data[3] = buf.getFloat(index + 24);
        self.data[7] = buf.getFloat(index + 28);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[4]);
        buf.put(index + 2, self.data[1]);
        buf.put(index + 3, self.data[5]);
        buf.put(index + 4, self.data[2]);
        buf.put(index + 5, self.data[6]);
        buf.put(index + 6, self.data[3]);
        buf.put(index + 7, self.data[7]);
        return buf;
    }
    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf) {
        self.data[0] = (float) buf.get(index + 0);
        self.data[4] = (float) buf.get(index + 1);
        self.data[1] = (float) buf.get(index + 2);
        self.data[5] = (float) buf.get(index + 3);
        self.data[2] = (float) buf.get(index + 4);
        self.data[6] = (float) buf.get(index + 5);
        self.data[3] = (float) buf.get(index + 6);
        self.data[7] = (float) buf.get(index + 7);
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[4]);
        buf.putDouble(index + 16, self.data[1]);
        buf.putDouble(index + 24, self.data[5]);
        buf.putDouble(index + 32, self.data[2]);
        buf.putDouble(index + 40, self.data[6]);
        buf.putDouble(index + 48, self.data[3]);
        buf.putDouble(index + 56, self.data[7]);
        return buf;
    }
    public Float4x2 loadRMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf) {
        self.data[0] = (float) buf.getDouble(index + 0);
        self.data[4] = (float) buf.getDouble(index + 8);
        self.data[1] = (float) buf.getDouble(index + 16);
        self.data[5] = (float) buf.getDouble(index + 24);
        self.data[2] = (float) buf.getDouble(index + 32);
        self.data[6] = (float) buf.getDouble(index + 40);
        self.data[3] = (float) buf.getDouble(index + 48);
        self.data[7] = (float) buf.getDouble(index + 56);
        return self;
    }
    public FloatBuffer storeCMAbsolute(Float4x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(_p1, self.data[4]);
        buf.put(_p1 + 1, self.data[5]);
        buf.put(_p1 + 2, self.data[6]);
        buf.put(_p1 + 3, self.data[7]);
        return buf;
    }
    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        self.data[0] = buf.get(index);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        self.data[4] = buf.get(_p1);
        self.data[5] = buf.get(_p1 + 1);
        self.data[6] = buf.get(_p1 + 2);
        self.data[7] = buf.get(_p1 + 3);
        return self;
    }
    public ByteBuffer storeCMAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        buf.putFloat(index, self.data[0]);
        buf.putFloat(index + 4, self.data[1]);
        buf.putFloat(index + 8, self.data[2]);
        buf.putFloat(index + 12, self.data[3]);
        buf.putFloat(_p1, self.data[4]);
        buf.putFloat(_p1 + 4, self.data[5]);
        buf.putFloat(_p1 + 8, self.data[6]);
        buf.putFloat(_p1 + 12, self.data[7]);
        return buf;
    }
    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        self.data[0] = buf.getFloat(index);
        self.data[1] = buf.getFloat(index + 4);
        self.data[2] = buf.getFloat(index + 8);
        self.data[3] = buf.getFloat(index + 12);
        self.data[4] = buf.getFloat(_p1);
        self.data[5] = buf.getFloat(_p1 + 4);
        self.data[6] = buf.getFloat(_p1 + 8);
        self.data[7] = buf.getFloat(_p1 + 12);
        return self;
    }
    public DoubleBuffer storeCMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        buf.put(_p1, self.data[4]);
        buf.put(_p1 + 1, self.data[5]);
        buf.put(_p1 + 2, self.data[6]);
        buf.put(_p1 + 3, self.data[7]);
        return buf;
    }
    public Float4x2 loadCMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        self.data[0] = (float) buf.get(index);
        self.data[1] = (float) buf.get(index + 1);
        self.data[2] = (float) buf.get(index + 2);
        self.data[3] = (float) buf.get(index + 3);
        self.data[4] = (float) buf.get(_p1);
        self.data[5] = (float) buf.get(_p1 + 1);
        self.data[6] = (float) buf.get(_p1 + 2);
        self.data[7] = (float) buf.get(_p1 + 3);
        return self;
    }
    public ByteBuffer storeCMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        buf.putDouble(index, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        buf.putDouble(index + 16, self.data[2]);
        buf.putDouble(index + 24, self.data[3]);
        buf.putDouble(_p1, self.data[4]);
        buf.putDouble(_p1 + 8, self.data[5]);
        buf.putDouble(_p1 + 16, self.data[6]);
        buf.putDouble(_p1 + 24, self.data[7]);
        return buf;
    }
    public Float4x2 loadCMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        self.data[0] = (float) buf.getDouble(index);
        self.data[1] = (float) buf.getDouble(index + 8);
        self.data[2] = (float) buf.getDouble(index + 16);
        self.data[3] = (float) buf.getDouble(index + 24);
        self.data[4] = (float) buf.getDouble(_p1);
        self.data[5] = (float) buf.getDouble(_p1 + 8);
        self.data[6] = (float) buf.getDouble(_p1 + 16);
        self.data[7] = (float) buf.getDouble(_p1 + 24);
        return self;
    }
    public FloatBuffer storeRMAbsolute(Float4x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[4]);
        buf.put(_p1, self.data[1]);
        buf.put(_p1 + 1, self.data[5]);
        buf.put(_p2, self.data[2]);
        buf.put(_p2 + 1, self.data[6]);
        buf.put(_p3, self.data[3]);
        buf.put(_p3 + 1, self.data[7]);
        return buf;
    }
    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, FloatBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.data[0] = buf.get(index);
        self.data[4] = buf.get(index + 1);
        self.data[1] = buf.get(_p1);
        self.data[5] = buf.get(_p1 + 1);
        self.data[2] = buf.get(_p2);
        self.data[6] = buf.get(_p2 + 1);
        self.data[3] = buf.get(_p3);
        self.data[7] = buf.get(_p3 + 1);
        return self;
    }
    public ByteBuffer storeRMAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putFloat(index, self.data[0]);
        buf.putFloat(index + 4, self.data[4]);
        buf.putFloat(_p1, self.data[1]);
        buf.putFloat(_p1 + 4, self.data[5]);
        buf.putFloat(_p2, self.data[2]);
        buf.putFloat(_p2 + 4, self.data[6]);
        buf.putFloat(_p3, self.data[3]);
        buf.putFloat(_p3 + 4, self.data[7]);
        return buf;
    }
    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 4;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.data[0] = buf.getFloat(index);
        self.data[4] = buf.getFloat(index + 4);
        self.data[1] = buf.getFloat(_p1);
        self.data[5] = buf.getFloat(_p1 + 4);
        self.data[2] = buf.getFloat(_p2);
        self.data[6] = buf.getFloat(_p2 + 4);
        self.data[3] = buf.getFloat(_p3);
        self.data[7] = buf.getFloat(_p3 + 4);
        return self;
    }
    public DoubleBuffer storeRMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[4]);
        buf.put(_p1, self.data[1]);
        buf.put(_p1 + 1, self.data[5]);
        buf.put(_p2, self.data[2]);
        buf.put(_p2 + 1, self.data[6]);
        buf.put(_p3, self.data[3]);
        buf.put(_p3 + 1, self.data[7]);
        return buf;
    }
    public Float4x2 loadRMAbsolute(Float4x2Impl self, int index, DoubleBuffer buf, int stride) {
        int _p1 = index + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        self.data[0] = (float) buf.get(index);
        self.data[4] = (float) buf.get(index + 1);
        self.data[1] = (float) buf.get(_p1);
        self.data[5] = (float) buf.get(_p1 + 1);
        self.data[2] = (float) buf.get(_p2);
        self.data[6] = (float) buf.get(_p2 + 1);
        self.data[3] = (float) buf.get(_p3);
        self.data[7] = (float) buf.get(_p3 + 1);
        return self;
    }
    public ByteBuffer storeRMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        buf.putDouble(index, self.data[0]);
        buf.putDouble(index + 8, self.data[4]);
        buf.putDouble(_p1, self.data[1]);
        buf.putDouble(_p1 + 8, self.data[5]);
        buf.putDouble(_p2, self.data[2]);
        buf.putDouble(_p2 + 8, self.data[6]);
        buf.putDouble(_p3, self.data[3]);
        buf.putDouble(_p3 + 8, self.data[7]);
        return buf;
    }
    public Float4x2 loadRMDoubleAbsolute(Float4x2Impl self, int index, ByteBuffer buf, int stride) {
        int _ps = stride * 8;
        int _p1 = index + _ps;
        int _p2 = _p1 + _ps;
        int _p3 = _p2 + _ps;
        self.data[0] = (float) buf.getDouble(index);
        self.data[4] = (float) buf.getDouble(index + 8);
        self.data[1] = (float) buf.getDouble(_p1);
        self.data[5] = (float) buf.getDouble(_p1 + 8);
        self.data[2] = (float) buf.getDouble(_p2);
        self.data[6] = (float) buf.getDouble(_p2 + 8);
        self.data[3] = (float) buf.getDouble(_p3);
        self.data[7] = (float) buf.getDouble(_p3 + 8);
        return self;
    }
}
