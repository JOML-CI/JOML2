// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public interface Short2BbOps {
    ShortBuffer storeAbsolute(Short2 self, int index, ShortBuffer buf);
    Short2 loadAbsolute(int index, ShortBuffer buf);
    ByteBuffer storeAbsolute(Short2 self, int index, ByteBuffer buf);
    Short2 loadAbsolute(int index, ByteBuffer buf);
    ByteBuffer storeByteAbsolute(Short2 self, int index, ByteBuffer buf);
    Short2 loadByteAbsolute(int index, ByteBuffer buf);
}
