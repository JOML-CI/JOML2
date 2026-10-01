// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Short4BbOpsApi implements Short4BbOps {
    public ShortBuffer storeAbsolute(Short4 self, int index, ShortBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        buf.put(index + 2, self.z());
        buf.put(index + 3, self.w());
        return buf;
    }
    public Short4 loadAbsolute(int index, ShortBuffer buf) {
        short _c0 = buf.get(index + 0);
        short _c1 = buf.get(index + 1);
        short _c2 = buf.get(index + 2);
        short _c3 = buf.get(index + 3);
        return new Short4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeAbsolute(Short4 self, int index, ByteBuffer buf) {
        buf.putShort(index + 0, self.x());
        buf.putShort(index + 2, self.y());
        buf.putShort(index + 4, self.z());
        buf.putShort(index + 6, self.w());
        return buf;
    }
    public Short4 loadAbsolute(int index, ByteBuffer buf) {
        short _c0 = buf.getShort(index + 0);
        short _c1 = buf.getShort(index + 2);
        short _c2 = buf.getShort(index + 4);
        short _c3 = buf.getShort(index + 6);
        return new Short4(_c0, _c1, _c2, _c3);
    }
    public ByteBuffer storeByteAbsolute(Short4 self, int index, ByteBuffer buf) {
        buf.put(index + 0, (byte) self.x());
        buf.put(index + 1, (byte) self.y());
        buf.put(index + 2, (byte) self.z());
        buf.put(index + 3, (byte) self.w());
        return buf;
    }
    public Short4 loadByteAbsolute(int index, ByteBuffer buf) {
        short _c0 = buf.get(index + 0);
        short _c1 = buf.get(index + 1);
        short _c2 = buf.get(index + 2);
        short _c3 = buf.get(index + 3);
        return new Short4(_c0, _c1, _c2, _c3);
    }
}
