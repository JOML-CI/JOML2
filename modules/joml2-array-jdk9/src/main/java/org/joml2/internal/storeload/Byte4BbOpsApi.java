// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Byte4BbOpsApi implements Byte4BbOps {
    public ByteBuffer storeAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        return buf;
    }
    public Byte4 loadAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        self.data[0] = buf.get(index + 0);
        self.data[1] = buf.get(index + 1);
        self.data[2] = buf.get(index + 2);
        self.data[3] = buf.get(index + 3);
        return self;
    }
    public ShortBuffer storeAbsolute(Byte4Impl self, int index, ShortBuffer buf) {
        buf.put(index + 0, self.data[0]);
        buf.put(index + 1, self.data[1]);
        buf.put(index + 2, self.data[2]);
        buf.put(index + 3, self.data[3]);
        return buf;
    }
    public Byte4 loadAbsolute(Byte4Impl self, int index, ShortBuffer buf) {
        self.data[0] = (byte) buf.get(index + 0);
        self.data[1] = (byte) buf.get(index + 1);
        self.data[2] = (byte) buf.get(index + 2);
        self.data[3] = (byte) buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeShortAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        buf.putShort(index + 0, self.data[0]);
        buf.putShort(index + 2, self.data[1]);
        buf.putShort(index + 4, self.data[2]);
        buf.putShort(index + 6, self.data[3]);
        return buf;
    }
    public Byte4 loadShortAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        self.data[0] = (byte) buf.getShort(index + 0);
        self.data[1] = (byte) buf.getShort(index + 2);
        self.data[2] = (byte) buf.getShort(index + 4);
        self.data[3] = (byte) buf.getShort(index + 6);
        return self;
    }
}
