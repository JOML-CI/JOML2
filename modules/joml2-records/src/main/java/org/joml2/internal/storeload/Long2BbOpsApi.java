// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Long2BbOpsApi implements Long2BbOps {
    public LongBuffer storeAbsolute(Long2 self, int index, LongBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        return buf;
    }
    public Long2 loadAbsolute(int index, LongBuffer buf) {
        long _c0 = buf.get(index + 0);
        long _c1 = buf.get(index + 1);
        return new Long2(_c0, _c1);
    }
    public ByteBuffer storeAbsolute(Long2 self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.x());
        buf.putLong(index + 8, self.y());
        return buf;
    }
    public Long2 loadAbsolute(int index, ByteBuffer buf) {
        long _c0 = buf.getLong(index + 0);
        long _c1 = buf.getLong(index + 8);
        return new Long2(_c0, _c1);
    }
    public IntBuffer storeAbsolute(Long2 self, int index, IntBuffer buf) {
        buf.put(index + 0, (int) self.x());
        buf.put(index + 1, (int) self.y());
        return buf;
    }
    public Long2 loadAbsolute(int index, IntBuffer buf) {
        long _c0 = buf.get(index + 0);
        long _c1 = buf.get(index + 1);
        return new Long2(_c0, _c1);
    }
    public ByteBuffer storeIntAbsolute(Long2 self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, (int) self.x());
        buf.putInt(index + 4, (int) self.y());
        return buf;
    }
    public Long2 loadIntAbsolute(int index, ByteBuffer buf) {
        long _c0 = buf.getInt(index + 0);
        long _c1 = buf.getInt(index + 4);
        return new Long2(_c0, _c1);
    }
}
