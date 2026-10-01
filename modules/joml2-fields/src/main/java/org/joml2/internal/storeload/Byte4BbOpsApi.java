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
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        buf.put(index + 3, self.w);
        return buf;
    }
    public Byte4 loadAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        self.x = buf.get(index + 0);
        self.y = buf.get(index + 1);
        self.z = buf.get(index + 2);
        self.w = buf.get(index + 3);
        return self;
    }
    public ShortBuffer storeAbsolute(Byte4Impl self, int index, ShortBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        buf.put(index + 3, self.w);
        return buf;
    }
    public Byte4 loadAbsolute(Byte4Impl self, int index, ShortBuffer buf) {
        self.x = (byte) buf.get(index + 0);
        self.y = (byte) buf.get(index + 1);
        self.z = (byte) buf.get(index + 2);
        self.w = (byte) buf.get(index + 3);
        return self;
    }
    public ByteBuffer storeShortAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        buf.putShort(index + 0, self.x);
        buf.putShort(index + 2, self.y);
        buf.putShort(index + 4, self.z);
        buf.putShort(index + 6, self.w);
        return buf;
    }
    public Byte4 loadShortAbsolute(Byte4Impl self, int index, ByteBuffer buf) {
        self.x = (byte) buf.getShort(index + 0);
        self.y = (byte) buf.getShort(index + 2);
        self.z = (byte) buf.getShort(index + 4);
        self.w = (byte) buf.getShort(index + 6);
        return self;
    }
}
