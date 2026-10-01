// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Byte3BbOpsApi extends Byte3BbOps {
    public ByteBuffer storeAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        return buf;
    }
    public Byte3 loadAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.get(index);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        return self;
    }
    public ShortBuffer storeAbsolute(Byte3Impl self, int index, ShortBuffer buf) {
        buf.put(index, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        return buf;
    }
    public Byte3 loadAbsolute(Byte3Impl self, int index, ShortBuffer buf) {
        self.data[0] = (byte) buf.get(index);
        self.data[1] = (byte) buf.get(index + 1);
        self.data[2] = (byte) buf.get(index + 2);
        return self;
    }
    public ByteBuffer storeShortAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        buf.putShort(index, self.data[0]);
        buf.putShort(index + 2, self.data[1]);
        buf.putShort(index + 4, self.data[2]);
        return buf;
    }
    public Byte3 loadShortAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        self.data[0] = (byte) buf.getShort(index);
        self.data[1] = (byte) buf.getShort(index + 2);
        self.data[2] = (byte) buf.getShort(index + 4);
        return self;
    }
}
