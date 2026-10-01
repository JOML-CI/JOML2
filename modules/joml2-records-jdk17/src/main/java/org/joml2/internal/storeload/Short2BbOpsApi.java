// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Short2BbOpsApi implements Short2BbOps {
    public ShortBuffer storeAbsolute(Short2 self, int index, ShortBuffer buf) {
        buf.put(index + 0, self.x());
        buf.put(index + 1, self.y());
        return buf;
    }
    public Short2 loadAbsolute(int index, ShortBuffer buf) {
        short _c0 = buf.get(index + 0);
        short _c1 = buf.get(index + 1);
        return new Short2(_c0, _c1);
    }
    public ByteBuffer storeAbsolute(Short2 self, int index, ByteBuffer buf) {
        buf.putShort(index + 0, self.x());
        buf.putShort(index + 2, self.y());
        return buf;
    }
    public Short2 loadAbsolute(int index, ByteBuffer buf) {
        short _c0 = buf.getShort(index + 0);
        short _c1 = buf.getShort(index + 2);
        return new Short2(_c0, _c1);
    }
    public ByteBuffer storeByteAbsolute(Short2 self, int index, ByteBuffer buf) {
        buf.put(index + 0, (byte) self.x());
        buf.put(index + 1, (byte) self.y());
        return buf;
    }
    public Short2 loadByteAbsolute(int index, ByteBuffer buf) {
        short _c0 = buf.get(index + 0);
        short _c1 = buf.get(index + 1);
        return new Short2(_c0, _c1);
    }
}
