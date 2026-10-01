// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float2BbOpsApi implements Float2BbOps {
    public FloatBuffer storeAbsolute(Float2Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        return buf;
    }
    public Float2 loadAbsolute(Float2Impl self, int index, FloatBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeAbsolute(Float2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.data[0]);
        buf.putFloat(index + 4, self.data[1]);
        return buf;
    }
    public Float2 loadAbsolute(Float2Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getFloat(index + 0);
        self.data[1] = buf.getFloat(index + 4);
        return self;
    }
    public DoubleBuffer storeAbsolute(Float2Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        return buf;
    }
    public Float2 loadAbsolute(Float2Impl self, int index, DoubleBuffer buf) {
        self.data[0] = (float) buf.get(index + 0);
        self.data[1] = (float) buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(Float2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.data[0]);
        buf.putDouble(index + 8, self.data[1]);
        return buf;
    }
    public Float2 loadDoubleAbsolute(Float2Impl self, int index, ByteBuffer buf) {
        self.data[0] = (float) buf.getDouble(index + 0);
        self.data[1] = (float) buf.getDouble(index + 8);
        return self;
    }
}
