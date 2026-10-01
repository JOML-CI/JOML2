// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface Long3BbOps {
    LongBuffer storeAbsolute(Long3Impl self, int index, LongBuffer buf);
    Long3 loadAbsolute(Long3Impl self, int index, LongBuffer buf);
    ByteBuffer storeAbsolute(Long3Impl self, int index, ByteBuffer buf);
    Long3 loadAbsolute(Long3Impl self, int index, ByteBuffer buf);
    IntBuffer storeAbsolute(Long3Impl self, int index, IntBuffer buf);
    Long3 loadAbsolute(Long3Impl self, int index, IntBuffer buf);
    ByteBuffer storeIntAbsolute(Long3Impl self, int index, ByteBuffer buf);
    Long3 loadIntAbsolute(Long3Impl self, int index, ByteBuffer buf);
}
