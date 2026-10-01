// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Long3BbOpsApi implements Long3BbOps {
    public LongBuffer storeAbsolute(Long3 self, int index, LongBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        return buf;
    }
    public Long3 loadAbsolute(int index, LongBuffer buf) {
        long _c0 = buf.get(index + 0);
        long _c1 = buf.get(index + 1);
        long _c2 = buf.get(index + 2);
        return new Long3(_c0, _c1, _c2);
    }
    public ByteBuffer storeAbsolute(Long3 self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.x());
        buf.putLong(index + 8, self.y());
        buf.putLong(index + 16, self.z());
        return buf;
    }
    public Long3 loadAbsolute(int index, ByteBuffer buf) {
        long _c0 = buf.getLong(index + 0);
        long _c1 = buf.getLong(index + 8);
        long _c2 = buf.getLong(index + 16);
        return new Long3(_c0, _c1, _c2);
    }
    public IntBuffer storeAbsolute(Long3 self, int index, IntBuffer buf) {
        buf.put(index + 0, (int) self.x());
        buf.put(index + 1, (int) self.y());
        buf.put(index + 2, (int) self.z());
        return buf;
    }
    public Long3 loadAbsolute(int index, IntBuffer buf) {
        long _c0 = buf.get(index + 0);
        long _c1 = buf.get(index + 1);
        long _c2 = buf.get(index + 2);
        return new Long3(_c0, _c1, _c2);
    }
    public ByteBuffer storeIntAbsolute(Long3 self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, (int) self.x());
        buf.putInt(index + 4, (int) self.y());
        buf.putInt(index + 8, (int) self.z());
        return buf;
    }
    public Long3 loadIntAbsolute(int index, ByteBuffer buf) {
        long _c0 = buf.getInt(index + 0);
        long _c1 = buf.getInt(index + 4);
        long _c2 = buf.getInt(index + 8);
        return new Long3(_c0, _c1, _c2);
    }
}
