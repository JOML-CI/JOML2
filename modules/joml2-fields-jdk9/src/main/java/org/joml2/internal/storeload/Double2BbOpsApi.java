// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Double2BbOpsApi extends Double2BbOps {
    public DoubleBuffer storeAbsolute(Double2Impl self, int index, DoubleBuffer buf) {
        buf.put(index, self.x);
        buf.put(index + 1, self.y);
        return buf;
    }
    public Double2 loadAbsolute(Double2Impl self, int index, DoubleBuffer buf) {
        self.x = buf.get(index);
        self.y = buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index, self.x);
        buf.putDouble(index + 8, self.y);
        return buf;
    }
    public Double2 loadAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        self.x = buf.getDouble(index);
        self.y = buf.getDouble(index + 8);
        return self;
    }
    public FloatBuffer storeAbsolute(Double2Impl self, int index, FloatBuffer buf) {
        buf.put(index, (float) self.x);
        buf.put(index + 1, (float) self.y);
        return buf;
    }
    public Double2 loadAbsolute(Double2Impl self, int index, FloatBuffer buf) {
        self.x = buf.get(index);
        self.y = buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeFloatAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index, (float) self.x);
        buf.putFloat(index + 4, (float) self.y);
        return buf;
    }
    public Double2 loadFloatAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        self.x = buf.getFloat(index);
        self.y = buf.getFloat(index + 4);
        return self;
    }
}
