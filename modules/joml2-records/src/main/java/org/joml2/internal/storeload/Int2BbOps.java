// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public abstract class Int2BbOps {
    public abstract IntBuffer storeAbsolute(Int2 self, int index, IntBuffer buf);
    public abstract Int2 loadAbsolute(int index, IntBuffer buf);
    public abstract ByteBuffer storeAbsolute(Int2 self, int index, ByteBuffer buf);
    public abstract Int2 loadAbsolute(int index, ByteBuffer buf);
    public abstract LongBuffer storeAbsolute(Int2 self, int index, LongBuffer buf);
    public abstract Int2 loadAbsolute(int index, LongBuffer buf);
    public abstract ByteBuffer storeLongAbsolute(Int2 self, int index, ByteBuffer buf);
    public abstract Int2 loadLongAbsolute(int index, ByteBuffer buf);
}
