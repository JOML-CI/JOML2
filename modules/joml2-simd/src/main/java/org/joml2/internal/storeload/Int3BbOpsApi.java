// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class Int3BbOpsApi implements Int3BbOps {
    public IntBuffer storeAbsolute(Int3Impl self, int index, IntBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        return buf;
    }
    public Int3 loadAbsolute(Int3Impl self, int index, IntBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        return self;
    }
    public ByteBuffer storeAbsolute(Int3Impl self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, self.data[0]);
        buf.putInt(index + 4, self.data[1]);
        buf.putInt(index + 8, self.data[2]);
        return buf;
    }
    public Int3 loadAbsolute(Int3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getInt(index + 0);
        self.data[1] = buf.getInt(index + 4);
        self.data[2] = buf.getInt(index + 8);
        return self;
    }
    public LongBuffer storeAbsolute(Int3Impl self, int index, LongBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        return buf;
    }
    public Int3 loadAbsolute(Int3Impl self, int index, LongBuffer buf) {
        self.data[0] = (int) buf.get(index + 0);
        self.data[1] = (int) buf.get(index + 1);
        self.data[2] = (int) buf.get(index + 2);
        return self;
    }
    public ByteBuffer storeLongAbsolute(Int3Impl self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.data[0]);
        buf.putLong(index + 8, self.data[1]);
        buf.putLong(index + 16, self.data[2]);
        return buf;
    }
    public Int3 loadLongAbsolute(Int3Impl self, int index, ByteBuffer buf) {
        self.data[0] = (int) buf.getLong(index + 0);
        self.data[1] = (int) buf.getLong(index + 8);
        self.data[2] = (int) buf.getLong(index + 16);
        return self;
    }
}
