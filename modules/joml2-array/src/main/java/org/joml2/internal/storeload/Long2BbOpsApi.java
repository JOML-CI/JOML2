// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Long2BbOpsApi implements Long2BbOps {
    public LongBuffer storeAbsolute(Long2Impl self, int index, LongBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        return buf;
    }
    public Long2 loadAbsolute(Long2Impl self, int index, LongBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.data[0]);
        buf.putLong(index + 8, self.data[1]);
        return buf;
    }
    public Long2 loadAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getLong(index + 0);
        self.data[1] = buf.getLong(index + 8);
        return self;
    }
    public IntBuffer storeAbsolute(Long2Impl self, int index, IntBuffer buf) {
        buf.put(index + 0, (int) self.data[0]);
        buf.put(index + 1, (int) self.data[1]);
        return buf;
    }
    public Long2 loadAbsolute(Long2Impl self, int index, IntBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeIntAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, (int) self.data[0]);
        buf.putInt(index + 4, (int) self.data[1]);
        return buf;
    }
    public Long2 loadIntAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getInt(index + 0);
        self.data[1] = buf.getInt(index + 4);
        return self;
    }
}
