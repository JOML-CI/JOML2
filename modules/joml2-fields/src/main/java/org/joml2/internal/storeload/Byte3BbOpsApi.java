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
        buf.put(index, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        return buf;
    }
    public Byte3 loadAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        self.x = buf.get(index);
        self.y = buf.get(index + 1);
        self.z = buf.get(index + 2);
        return self;
    }
    public ShortBuffer storeAbsolute(Byte3Impl self, int index, ShortBuffer buf) {
        buf.put(index, self.x);
        buf.put(index + 1, self.y);
        buf.put(index + 2, self.z);
        return buf;
    }
    public Byte3 loadAbsolute(Byte3Impl self, int index, ShortBuffer buf) {
        self.x = (byte) buf.get(index);
        self.y = (byte) buf.get(index + 1);
        self.z = (byte) buf.get(index + 2);
        return self;
    }
    public ByteBuffer storeShortAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        buf.putShort(index, self.x);
        buf.putShort(index + 2, self.y);
        buf.putShort(index + 4, self.z);
        return buf;
    }
    public Byte3 loadShortAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        self.x = (byte) buf.getShort(index);
        self.y = (byte) buf.getShort(index + 2);
        self.z = (byte) buf.getShort(index + 4);
        return self;
    }
}
