// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public interface Byte4BbOps {
    ByteBuffer storeAbsolute(Byte4 self, int index, ByteBuffer buf);
    Byte4 loadAbsolute(int index, ByteBuffer buf);
    ShortBuffer storeAbsolute(Byte4 self, int index, ShortBuffer buf);
    Byte4 loadAbsolute(int index, ShortBuffer buf);
    ByteBuffer storeShortAbsolute(Byte4 self, int index, ByteBuffer buf);
    Byte4 loadShortAbsolute(int index, ByteBuffer buf);
}
