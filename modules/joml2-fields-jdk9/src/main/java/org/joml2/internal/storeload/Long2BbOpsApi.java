// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Long2BbOpsApi extends Long2BbOps {
    public LongBuffer storeAbsolute(Long2Impl self, int index, LongBuffer buf) {
        buf.put(index, self.x);
        buf.put(index + 1, self.y);
        return buf;
    }
    public Long2 loadAbsolute(Long2Impl self, int index, LongBuffer buf) {
        self.x = buf.get(index);
        self.y = buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        buf.putLong(index, self.x);
        buf.putLong(index + 8, self.y);
        return buf;
    }
    public Long2 loadAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        self.x = buf.getLong(index);
        self.y = buf.getLong(index + 8);
        return self;
    }
    public IntBuffer storeAbsolute(Long2Impl self, int index, IntBuffer buf) {
        buf.put(index, (int) self.x);
        buf.put(index + 1, (int) self.y);
        return buf;
    }
    public Long2 loadAbsolute(Long2Impl self, int index, IntBuffer buf) {
        self.x = buf.get(index);
        self.y = buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeIntAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        buf.putInt(index, (int) self.x);
        buf.putInt(index + 4, (int) self.y);
        return buf;
    }
    public Long2 loadIntAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        self.x = buf.getInt(index);
        self.y = buf.getInt(index + 4);
        return self;
    }
}
