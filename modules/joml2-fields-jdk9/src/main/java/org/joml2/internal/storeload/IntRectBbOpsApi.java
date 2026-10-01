// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public final class IntRectBbOpsApi implements IntRectBbOps {
    public IntBuffer storeAbsolute(IntRectImpl self, int index, IntBuffer buf) {
        buf.put(index + 0, self.minX);
        buf.put(index + 1, self.minY);
        buf.put(index + 2, self.maxX);
        buf.put(index + 3, self.maxY);
        return buf;
    }
    public IntRect loadAbsolute(IntRectImpl self, int index, IntBuffer buf) {
        self.minX = buf.get(index + 0);
        self.minY = buf.get(index + 1);
        self.maxX = buf.get(index + 2);
        self.maxY = buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeAbsolute(IntRectImpl self, int index, ByteBuffer buf) {
        buf.putInt(index + 0, self.minX);
        buf.putInt(index + 4, self.minY);
        buf.putInt(index + 8, self.maxX);
        buf.putInt(index + 12, self.maxY);
        return buf;
    }
    public IntRect loadAbsolute(IntRectImpl self, int index, ByteBuffer buf) {
        self.minX = buf.getInt(index + 0);
        self.minY = buf.getInt(index + 4);
        self.maxX = buf.getInt(index + 8);
        self.maxY = buf.getInt(index + 12);
        return self;
    }
    public LongBuffer storeAbsolute(IntRectImpl self, int index, LongBuffer buf) {
        buf.put(index + 0, self.minX);
        buf.put(index + 1, self.minY);
        buf.put(index + 2, self.maxX);
        buf.put(index + 3, self.maxY);
        return buf;
    }
    public IntRect loadAbsolute(IntRectImpl self, int index, LongBuffer buf) {
        self.minX = (int) buf.get(index + 0);
        self.minY = (int) buf.get(index + 1);
        self.maxX = (int) buf.get(index + 2);
        self.maxY = (int) buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeLongAbsolute(IntRectImpl self, int index, ByteBuffer buf) {
        buf.putLong(index + 0, self.minX);
        buf.putLong(index + 8, self.minY);
        buf.putLong(index + 16, self.maxX);
        buf.putLong(index + 24, self.maxY);
        return buf;
    }
    public IntRect loadLongAbsolute(IntRectImpl self, int index, ByteBuffer buf) {
        self.minX = (int) buf.getLong(index + 0);
        self.minY = (int) buf.getLong(index + 8);
        self.maxX = (int) buf.getLong(index + 16);
        self.maxY = (int) buf.getLong(index + 24);
        return self;
    }
}
