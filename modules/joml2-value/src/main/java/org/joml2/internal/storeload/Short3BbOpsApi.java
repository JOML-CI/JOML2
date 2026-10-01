// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Short3BbOpsApi implements Short3BbOps {
    public ShortBuffer storeAbsolute(Short3 self, int index, ShortBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        return buf;
    }
    public Short3 loadAbsolute(int index, ShortBuffer buf) {
        short _c0 = buf.get(index + 0);
        short _c1 = buf.get(index + 1);
        short _c2 = buf.get(index + 2);
        return new Short3(_c0, _c1, _c2);
    }
    public ByteBuffer storeAbsolute(Short3 self, int index, ByteBuffer buf) {
        buf.putShort(index + 0, self.x());
        buf.putShort(index + 2, self.y());
        buf.putShort(index + 4, self.z());
        return buf;
    }
    public Short3 loadAbsolute(int index, ByteBuffer buf) {
        short _c0 = buf.getShort(index + 0);
        short _c1 = buf.getShort(index + 2);
        short _c2 = buf.getShort(index + 4);
        return new Short3(_c0, _c1, _c2);
    }
    public ByteBuffer storeByteAbsolute(Short3 self, int index, ByteBuffer buf) {
        buf.put(index + 0, (byte) self.x());
        buf.put(index + 1, (byte) self.y());
        buf.put(index + 2, (byte) self.z());
        return buf;
    }
    public Short3 loadByteAbsolute(int index, ByteBuffer buf) {
        short _c0 = buf.get(index + 0);
        short _c1 = buf.get(index + 1);
        short _c2 = buf.get(index + 2);
        return new Short3(_c0, _c1, _c2);
    }
}
