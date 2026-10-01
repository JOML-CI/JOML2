// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public abstract class Int2BbOps {
    public abstract IntBuffer storeAbsolute(Int2Impl self, int index, IntBuffer buf);
    public abstract Int2 loadAbsolute(Int2Impl self, int index, IntBuffer buf);
    public abstract ByteBuffer storeAbsolute(Int2Impl self, int index, ByteBuffer buf);
    public abstract Int2 loadAbsolute(Int2Impl self, int index, ByteBuffer buf);
    public abstract LongBuffer storeAbsolute(Int2Impl self, int index, LongBuffer buf);
    public abstract Int2 loadAbsolute(Int2Impl self, int index, LongBuffer buf);
    public abstract ByteBuffer storeLongAbsolute(Int2Impl self, int index, ByteBuffer buf);
    public abstract Int2 loadLongAbsolute(Int2Impl self, int index, ByteBuffer buf);
}
