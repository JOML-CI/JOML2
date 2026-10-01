// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public interface Short4BbOps {
    ShortBuffer storeAbsolute(Short4 self, int index, ShortBuffer buf);
    Short4 loadAbsolute(int index, ShortBuffer buf);
    ByteBuffer storeAbsolute(Short4 self, int index, ByteBuffer buf);
    Short4 loadAbsolute(int index, ByteBuffer buf);
    ByteBuffer storeByteAbsolute(Short4 self, int index, ByteBuffer buf);
    Short4 loadByteAbsolute(int index, ByteBuffer buf);
}
