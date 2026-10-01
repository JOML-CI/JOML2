// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public final class Float3BbOpsApi implements Float3BbOps {
    public FloatBuffer storeAbsolute(Float3Impl self, int index, FloatBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        return buf;
    }
    public Float3 loadAbsolute(Float3Impl self, int index, FloatBuffer buf) {
        self.x = buf.get(index + 0);
        self.y = buf.get(index + 1);
        self.z = buf.get(index + 2);
        return self;
    }
    public ByteBuffer storeAbsolute(Float3Impl self, int index, ByteBuffer buf) {
        buf.putFloat(index + 0, self.x);
        buf.putFloat(index + 4, self.y);
        buf.putFloat(index + 8, self.z);
        return buf;
    }
    public Float3 loadAbsolute(Float3Impl self, int index, ByteBuffer buf) {
        self.x = buf.getFloat(index + 0);
        self.y = buf.getFloat(index + 4);
        self.z = buf.getFloat(index + 8);
        return self;
    }
    public DoubleBuffer storeAbsolute(Float3Impl self, int index, DoubleBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        return buf;
    }
    public Float3 loadAbsolute(Float3Impl self, int index, DoubleBuffer buf) {
        self.x = (float) buf.get(index + 0);
        self.y = (float) buf.get(index + 1);
        self.z = (float) buf.get(index + 2);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(Float3Impl self, int index, ByteBuffer buf) {
        buf.putDouble(index + 0, self.x);
        buf.putDouble(index + 8, self.y);
        buf.putDouble(index + 16, self.z);
        return buf;
    }
    public Float3 loadDoubleAbsolute(Float3Impl self, int index, ByteBuffer buf) {
        self.x = (float) buf.getDouble(index + 0);
        self.y = (float) buf.getDouble(index + 8);
        self.z = (float) buf.getDouble(index + 16);
        return self;
    }
}
