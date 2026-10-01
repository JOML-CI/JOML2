// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Byte4BbOpsApi implements Byte4BbOps {
    public ByteBuffer storeAbsolute(Byte4 self, int index, ByteBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        buf.put(index + 3, self.w());
        return buf;
    }
    public Byte4 loadAbsolute(int index, ByteBuffer buf) {
        byte _c0 = buf.get(index + 0);
        byte _c1 = buf.get(index + 1);
        byte _c2 = buf.get(index + 2);
        byte _c3 = buf.get(index + 3);
        return new Byte4(_c0, _c1, _c2, _c3);
    }
    public ShortBuffer storeAbsolute(Byte4 self, int index, ShortBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        buf.put(index + 3, self.w());
        return buf;
    }
    public Byte4 loadAbsolute(int index, ShortBuffer buf) {
        byte _c0 = (byte) buf.get(index + 0);
        byte _c1 = (byte) buf.get(index + 1);
        byte _c2 = (byte) buf.get(index + 2);
        byte _c3 = (byte) buf.get(index + 3);
        return new Byte4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeShortAbsolute(Byte4 self, int index, ByteBuffer buf) {
        buf.putShort(index + 0, self.x());
        buf.putShort(index + 2, self.y());
        buf.putShort(index + 4, self.z());
        buf.putShort(index + 6, self.w());
        return buf;
    }
    public Byte4 loadShortAbsolute(int index, ByteBuffer buf) {
        byte _c0 = (byte) buf.getShort(index + 0);
        byte _c1 = (byte) buf.getShort(index + 2);
        byte _c2 = (byte) buf.getShort(index + 4);
        byte _c3 = (byte) buf.getShort(index + 6);
        return new Byte4(_c0, _c1, _c2, _c3);
    }
}
