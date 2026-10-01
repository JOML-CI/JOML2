// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Long3BbOpsApi implements Long3BbOps {
    public LongBuffer storeAbsolute(Long3Impl self, int index, LongBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        return buf;
    }
    public Long3 loadAbsolute(Long3Impl self, int index, LongBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        return self;
    }
    public ByteBuffer storeAbsolute(Long3Impl self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.data[0]);
        buf.putLong(index + 8, self.data[1]);
        buf.putLong(index + 16, self.data[2]);
        return buf;
    }
    public Long3 loadAbsolute(Long3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getLong(index + 0);
        self.data[1] = buf.getLong(index + 8);
        self.data[2] = buf.getLong(index + 16);
        return self;
    }
    public IntBuffer storeAbsolute(Long3Impl self, int index, IntBuffer buf) {
        buf.put(index + 0, (int) self.data[0]);
        buf.put(index + 1, (int) self.data[1]);
        buf.put(index + 2, (int) self.data[2]);
        return buf;
    }
    public Long3 loadAbsolute(Long3Impl self, int index, IntBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        return self;
    }
    public ByteBuffer storeIntAbsolute(Long3Impl self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, (int) self.data[0]);
        buf.putInt(index + 4, (int) self.data[1]);
        buf.putInt(index + 8, (int) self.data[2]);
        return buf;
    }
    public Long3 loadIntAbsolute(Long3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getInt(index + 0);
        self.data[1] = buf.getInt(index + 4);
        self.data[2] = buf.getInt(index + 8);
        return self;
    }
}
