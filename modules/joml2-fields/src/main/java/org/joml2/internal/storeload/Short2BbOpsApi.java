// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public final class Short2BbOpsApi implements Short2BbOps {
    public ShortBuffer storeAbsolute(Short2Impl self, int index, ShortBuffer buf) {
        buf.put(index + 0, self.x);
        buf.put(index + 1, self.y);
        return buf;
    }
    public Short2 loadAbsolute(Short2Impl self, int index, ShortBuffer buf) {
        self.x = buf.get(index + 0);
        self.y = buf.get(index + 1);
        return self;
    }
    public ByteBuffer storeAbsolute(Short2Impl self, int index, ByteBuffer buf) {
        buf.putShort(index + 0, self.x);
        buf.putShort(index + 2, self.y);
        return buf;
    }
    public Short2 loadAbsolute(Short2Impl self, int index, ByteBuffer buf) {
        self.x = buf.getShort(index + 0);
        self.y = buf.getShort(index + 2);
        return self;
    }
    public ByteBuffer storeByteAbsolute(Short2Impl self, int index, ByteBuffer buf) {
        buf.put(index + 0, (byte) self.x);
        buf.put(index + 1, (byte) self.y);
        return buf;
    }
    public Short2 loadByteAbsolute(Short2Impl self, int index, ByteBuffer buf) {
        self.x = buf.get(index + 0);
        self.y = buf.get(index + 1);
        return self;
    }
}
