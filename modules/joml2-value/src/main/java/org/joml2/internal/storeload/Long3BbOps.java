// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface Long3BbOps {
    LongBuffer storeAbsolute(Long3 self, int index, LongBuffer buf);
    Long3 loadAbsolute(int index, LongBuffer buf);
    ByteBuffer storeAbsolute(Long3 self, int index, ByteBuffer buf);
    Long3 loadAbsolute(int index, ByteBuffer buf);
    IntBuffer storeAbsolute(Long3 self, int index, IntBuffer buf);
    Long3 loadAbsolute(int index, IntBuffer buf);
    ByteBuffer storeIntAbsolute(Long3 self, int index, ByteBuffer buf);
    Long3 loadIntAbsolute(int index, ByteBuffer buf);
}
