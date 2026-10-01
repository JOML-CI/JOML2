// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface Int4BbOps {
    IntBuffer storeAbsolute(Int4Impl self, int index, IntBuffer buf);
    Int4 loadAbsolute(Int4Impl self, int index, IntBuffer buf);
    ByteBuffer storeAbsolute(Int4Impl self, int index, ByteBuffer buf);
    Int4 loadAbsolute(Int4Impl self, int index, ByteBuffer buf);
    LongBuffer storeAbsolute(Int4Impl self, int index, LongBuffer buf);
    Int4 loadAbsolute(Int4Impl self, int index, LongBuffer buf);
    ByteBuffer storeLongAbsolute(Int4Impl self, int index, ByteBuffer buf);
    Int4 loadLongAbsolute(Int4Impl self, int index, ByteBuffer buf);
}
