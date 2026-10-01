// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public interface Byte2BbOps {
    ByteBuffer storeAbsolute(Byte2Impl self, int index, ByteBuffer buf);
    Byte2 loadAbsolute(Byte2Impl self, int index, ByteBuffer buf);
    ShortBuffer storeAbsolute(Byte2Impl self, int index, ShortBuffer buf);
    Byte2 loadAbsolute(Byte2Impl self, int index, ShortBuffer buf);
    ByteBuffer storeShortAbsolute(Byte2Impl self, int index, ByteBuffer buf);
    Byte2 loadShortAbsolute(Byte2Impl self, int index, ByteBuffer buf);
}
