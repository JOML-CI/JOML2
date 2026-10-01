// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Byte2BbOpsApi implements Byte2BbOps {
    public ByteBuffer storeAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        return buf;
    }
    public Byte2 loadAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        self.x = buf.get(index + 0);
        self.y = buf.get(index + 1);
        return self;
    }
    public ShortBuffer storeAbsolute(Byte2Impl self, int index, ShortBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        return buf;
    }
    public Byte2 loadAbsolute(Byte2Impl self, int index, ShortBuffer buf) {
        self.x = (byte) buf.get(index + 0);
        self.y = (byte) buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeShortAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        buf.putShort(index + 0, self.x);
        buf.putShort(index + 2, self.y);
        return buf;
    }
    public Byte2 loadShortAbsolute(Byte2Impl self, int index, ByteBuffer buf) {
        self.x = (byte) buf.getShort(index + 0);
        self.y = (byte) buf.getShort(index + 2);
        return self;
    }
}
