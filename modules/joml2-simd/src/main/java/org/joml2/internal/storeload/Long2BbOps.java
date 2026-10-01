// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface Long2BbOps {
    LongBuffer storeAbsolute(Long2Impl self, int index, LongBuffer buf);
    Long2 loadAbsolute(Long2Impl self, int index, LongBuffer buf);
    ByteBuffer storeAbsolute(Long2Impl self, int index, ByteBuffer buf);
    Long2 loadAbsolute(Long2Impl self, int index, ByteBuffer buf);
    IntBuffer storeAbsolute(Long2Impl self, int index, IntBuffer buf);
    Long2 loadAbsolute(Long2Impl self, int index, IntBuffer buf);
    ByteBuffer storeIntAbsolute(Long2Impl self, int index, ByteBuffer buf);
    Long2 loadIntAbsolute(Long2Impl self, int index, ByteBuffer buf);
}
