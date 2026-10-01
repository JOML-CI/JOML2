// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Byte3BbOpsApi extends Byte3BbOps {
    public ByteBuffer storeAbsolute(Byte3 self, int index, ByteBuffer buf) {
        buf.put(index, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        return buf;
    }
    public Byte3 loadAbsolute(int index, ByteBuffer buf) {
        byte _c0 = buf.get(index);
        byte _c1 = buf.get(index + 1);
        byte _c2 = buf.get(index + 2);
        return new Byte3(_c0, _c1, _c2);
    }
    public ShortBuffer storeAbsolute(Byte3 self, int index, ShortBuffer buf) {
        buf.put(index, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        return buf;
    }
    public Byte3 loadAbsolute(int index, ShortBuffer buf) {
        byte _c0 = (byte) buf.get(index);
        byte _c1 = (byte) buf.get(index + 1);
        byte _c2 = (byte) buf.get(index + 2);
        return new Byte3(_c0, _c1, _c2);
    }
    public ByteBuffer storeShortAbsolute(Byte3 self, int index, ByteBuffer buf) {
        buf.putShort(index, self.x());
        buf.putShort(index + 2, self.y());
        buf.putShort(index + 4, self.z());
        return buf;
    }
    public Byte3 loadShortAbsolute(int index, ByteBuffer buf) {
        byte _c0 = (byte) buf.getShort(index);
        byte _c1 = (byte) buf.getShort(index + 2);
        byte _c2 = (byte) buf.getShort(index + 4);
        return new Byte3(_c0, _c1, _c2);
    }
}
