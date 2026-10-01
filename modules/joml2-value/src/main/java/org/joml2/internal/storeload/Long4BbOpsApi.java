// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Long4BbOpsApi implements Long4BbOps {
    public LongBuffer storeAbsolute(Long4 self, int index, LongBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        buf.put(index + 3, self.w());
        return buf;
    }
    public Long4 loadAbsolute(int index, LongBuffer buf) {
        long _c0 = buf.get(index + 0);
        long _c1 = buf.get(index + 1);
        long _c2 = buf.get(index + 2);
        long _c3 = buf.get(index + 3);
        return new Long4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(Long4 self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.x());
        buf.putLong(index + 8, self.y());
        buf.putLong(index + 16, self.z());
        buf.putLong(index + 24, self.w());
        return buf;
    }
    public Long4 loadAbsolute(int index, ByteBuffer buf) {
        long _c0 = buf.getLong(index + 0);
        long _c1 = buf.getLong(index + 8);
        long _c2 = buf.getLong(index + 16);
        long _c3 = buf.getLong(index + 24);
        return new Long4(_c0, _c1, _c2, _c3);
    }
    public IntBuffer storeAbsolute(Long4 self, int index, IntBuffer buf) {
        buf.put(index + 0, (int) self.x());
        buf.put(index + 1, (int) self.y());
        buf.put(index + 2, (int) self.z());
        buf.put(index + 3, (int) self.w());
        return buf;
    }
    public Long4 loadAbsolute(int index, IntBuffer buf) {
        long _c0 = buf.get(index + 0);
        long _c1 = buf.get(index + 1);
        long _c2 = buf.get(index + 2);
        long _c3 = buf.get(index + 3);
        return new Long4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeIntAbsolute(Long4 self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, (int) self.x());
        buf.putInt(index + 4, (int) self.y());
        buf.putInt(index + 8, (int) self.z());
        buf.putInt(index + 12, (int) self.w());
        return buf;
    }
    public Long4 loadIntAbsolute(int index, ByteBuffer buf) {
        long _c0 = buf.getInt(index + 0);
        long _c1 = buf.getInt(index + 4);
        long _c2 = buf.getInt(index + 8);
        long _c3 = buf.getInt(index + 12);
        return new Long4(_c0, _c1, _c2, _c3);
    }
}
