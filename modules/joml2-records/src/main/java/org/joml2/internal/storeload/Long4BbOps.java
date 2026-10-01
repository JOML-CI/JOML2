// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface Long4BbOps {
    LongBuffer storeAbsolute(Long4 self, int index, LongBuffer buf);
    Long4 loadAbsolute(int index, LongBuffer buf);
    ByteBuffer storeAbsolute(Long4 self, int index, ByteBuffer buf);
    Long4 loadAbsolute(int index, ByteBuffer buf);
    IntBuffer storeAbsolute(Long4 self, int index, IntBuffer buf);
    Long4 loadAbsolute(int index, IntBuffer buf);
    ByteBuffer storeIntAbsolute(Long4 self, int index, ByteBuffer buf);
    Long4 loadIntAbsolute(int index, ByteBuffer buf);
}
