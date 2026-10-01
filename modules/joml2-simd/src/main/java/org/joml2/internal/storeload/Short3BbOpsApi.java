// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Short3BbOpsApi implements Short3BbOps {
    public ShortBuffer storeAbsolute(Short3Impl self, int index, ShortBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        return buf;
    }
    public Short3 loadAbsolute(Short3Impl self, int index, ShortBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        return self;
    }
    public ByteBuffer storeAbsolute(Short3Impl self, int index, ByteBuffer buf) {
        buf.putShort(index + 0, self.data[0]);
        buf.putShort(index + 2, self.data[1]);
        buf.putShort(index + 4, self.data[2]);
        return buf;
    }
    public Short3 loadAbsolute(Short3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.getShort(index + 0);
        self.data[1] = buf.getShort(index + 2);
        self.data[2] = buf.getShort(index + 4);
        return self;
    }
    public ByteBuffer storeByteAbsolute(Short3Impl self, int index, ByteBuffer buf) {
        buf.put(index + 0, (byte) self.data[0]);
        buf.put(index + 1, (byte) self.data[1]);
        buf.put(index + 2, (byte) self.data[2]);
        return buf;
    }
    public Short3 loadByteAbsolute(Short3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        return self;
    }
}
