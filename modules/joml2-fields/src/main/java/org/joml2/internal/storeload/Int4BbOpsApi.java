// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Int4BbOpsApi implements Int4BbOps {
    public IntBuffer storeAbsolute(Int4Impl self, int index, IntBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        buf.put(index + 3, self.w);
        return buf;
    }
    public Int4 loadAbsolute(Int4Impl self, int index, IntBuffer buf) {
        self.x = buf.get(index + 0);
        self.y = buf.get(index + 1);
        self.z = buf.get(index + 2);
        self.w = buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeAbsolute(Int4Impl self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, self.x);
        buf.putInt(index + 4, self.y);
        buf.putInt(index + 8, self.z);
        buf.putInt(index + 12, self.w);
        return buf;
    }
    public Int4 loadAbsolute(Int4Impl self, int index, ByteBuffer buf) {
        self.x = buf.getInt(index + 0);
        self.y = buf.getInt(index + 4);
        self.z = buf.getInt(index + 8);
        self.w = buf.getInt(index + 12);
        return self;
    }
    public LongBuffer storeAbsolute(Int4Impl self, int index, LongBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        buf.put(index + 3, self.w);
        return buf;
    }
    public Int4 loadAbsolute(Int4Impl self, int index, LongBuffer buf) {
        self.x = (int) buf.get(index + 0);
        self.y = (int) buf.get(index + 1);
        self.z = (int) buf.get(index + 2);
        self.w = (int) buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeLongAbsolute(Int4Impl self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.x);
        buf.putLong(index + 8, self.y);
        buf.putLong(index + 16, self.z);
        buf.putLong(index + 24, self.w);
        return buf;
    }
    public Int4 loadLongAbsolute(Int4Impl self, int index, ByteBuffer buf) {
        self.x = (int) buf.getLong(index + 0);
        self.y = (int) buf.getLong(index + 8);
        self.z = (int) buf.getLong(index + 16);
        self.w = (int) buf.getLong(index + 24);
        return self;
    }
}
