// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface Int3BbOps {
    IntBuffer storeAbsolute(Int3Impl self, int index, IntBuffer buf);
    Int3 loadAbsolute(Int3Impl self, int index, IntBuffer buf);
    ByteBuffer storeAbsolute(Int3Impl self, int index, ByteBuffer buf);
    Int3 loadAbsolute(Int3Impl self, int index, ByteBuffer buf);
    LongBuffer storeAbsolute(Int3Impl self, int index, LongBuffer buf);
    Int3 loadAbsolute(Int3Impl self, int index, LongBuffer buf);
    ByteBuffer storeLongAbsolute(Int3Impl self, int index, ByteBuffer buf);
    Int3 loadLongAbsolute(Int3Impl self, int index, ByteBuffer buf);
}
