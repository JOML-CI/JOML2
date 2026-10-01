// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Byte2BbOpsApi implements Byte2BbOps {
    public ByteBuffer storeAbsolute(Byte2 self, int index, ByteBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        return buf;
    }
    public Byte2 loadAbsolute(int index, ByteBuffer buf) {
        byte _c0 = buf.get(index + 0);
        byte _c1 = buf.get(index + 1);
        return new Byte2(_c0, _c1);
    }
    public ShortBuffer storeAbsolute(Byte2 self, int index, ShortBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        return buf;
    }
    public Byte2 loadAbsolute(int index, ShortBuffer buf) {
        byte _c0 = (byte) buf.get(index + 0);
        byte _c1 = (byte) buf.get(index + 1);
        return new Byte2(_c0, _c1);
    }
    public ByteBuffer storeShortAbsolute(Byte2 self, int index, ByteBuffer buf) {
        buf.putShort(index + 0, self.x());
        buf.putShort(index + 2, self.y());
        return buf;
    }
    public Byte2 loadShortAbsolute(int index, ByteBuffer buf) {
        byte _c0 = (byte) buf.getShort(index + 0);
        byte _c1 = (byte) buf.getShort(index + 2);
        return new Byte2(_c0, _c1);
    }
}
