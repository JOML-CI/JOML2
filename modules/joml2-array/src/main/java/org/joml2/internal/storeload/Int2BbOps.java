// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface Int2BbOps {
    IntBuffer storeAbsolute(Int2Impl self, int index, IntBuffer buf);
    Int2 loadAbsolute(Int2Impl self, int index, IntBuffer buf);
    ByteBuffer storeAbsolute(Int2Impl self, int index, ByteBuffer buf);
    Int2 loadAbsolute(Int2Impl self, int index, ByteBuffer buf);
    LongBuffer storeAbsolute(Int2Impl self, int index, LongBuffer buf);
    Int2 loadAbsolute(Int2Impl self, int index, LongBuffer buf);
    ByteBuffer storeLongAbsolute(Int2Impl self, int index, ByteBuffer buf);
    Int2 loadLongAbsolute(Int2Impl self, int index, ByteBuffer buf);
}
