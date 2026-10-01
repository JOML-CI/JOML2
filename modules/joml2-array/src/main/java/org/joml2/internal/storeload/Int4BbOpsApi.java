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
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        return buf;
    }
    public Int4 loadAbsolute(Int4Impl self, int index, IntBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeAbsolute(Int4Impl self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, self.data[0]);
        buf.putInt(index + 4, self.data[1]);
        buf.putInt(index + 8, self.data[2]);
        buf.putInt(index + 12, self.data[3]);
        return buf;
    }
    public Int4 loadAbsolute(Int4Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getInt(index + 0);
        self.data[1] = buf.getInt(index + 4);
        self.data[2] = buf.getInt(index + 8);
        self.data[3] = buf.getInt(index + 12);
        return self;
    }
    public LongBuffer storeAbsolute(Int4Impl self, int index, LongBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        return buf;
    }
    public Int4 loadAbsolute(Int4Impl self, int index, LongBuffer buf) {
        self.data[0] = (int) buf.get(index + 0);
        self.data[1] = (int) buf.get(index + 1);
        self.data[2] = (int) buf.get(index + 2);
        self.data[3] = (int) buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeLongAbsolute(Int4Impl self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.data[0]);
        buf.putLong(index + 8, self.data[1]);
        buf.putLong(index + 16, self.data[2]);
        buf.putLong(index + 24, self.data[3]);
        return buf;
    }
    public Int4 loadLongAbsolute(Int4Impl self, int index, ByteBuffer buf) {
        self.data[0] = (int) buf.getLong(index + 0);
        self.data[1] = (int) buf.getLong(index + 8);
        self.data[2] = (int) buf.getLong(index + 16);
        self.data[3] = (int) buf.getLong(index + 24);
        return self;
    }
}
