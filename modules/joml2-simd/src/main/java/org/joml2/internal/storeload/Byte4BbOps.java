// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public interface Byte4BbOps {
    ByteBuffer storeAbsolute(Byte4Impl self, int index, ByteBuffer buf);
    Byte4 loadAbsolute(Byte4Impl self, int index, ByteBuffer buf);
    ShortBuffer storeAbsolute(Byte4Impl self, int index, ShortBuffer buf);
    Byte4 loadAbsolute(Byte4Impl self, int index, ShortBuffer buf);
    ByteBuffer storeShortAbsolute(Byte4Impl self, int index, ByteBuffer buf);
    Byte4 loadShortAbsolute(Byte4Impl self, int index, ByteBuffer buf);
}
