// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public abstract class Int4BbOps {
    public abstract IntBuffer storeAbsolute(Int4Impl self, int index, IntBuffer buf);
    public abstract Int4 loadAbsolute(Int4Impl self, int index, IntBuffer buf);
    public abstract ByteBuffer storeAbsolute(Int4Impl self, int index, ByteBuffer buf);
    public abstract Int4 loadAbsolute(Int4Impl self, int index, ByteBuffer buf);
    public abstract LongBuffer storeAbsolute(Int4Impl self, int index, LongBuffer buf);
    public abstract Int4 loadAbsolute(Int4Impl self, int index, LongBuffer buf);
    public abstract ByteBuffer storeLongAbsolute(Int4Impl self, int index, ByteBuffer buf);
    public abstract Int4 loadLongAbsolute(Int4Impl self, int index, ByteBuffer buf);
}
