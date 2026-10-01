// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public interface Byte3BbOps {
    ByteBuffer storeAbsolute(Byte3 self, int index, ByteBuffer buf);
    Byte3 loadAbsolute(int index, ByteBuffer buf);
    ShortBuffer storeAbsolute(Byte3 self, int index, ShortBuffer buf);
    Byte3 loadAbsolute(int index, ShortBuffer buf);
    ByteBuffer storeShortAbsolute(Byte3 self, int index, ByteBuffer buf);
    Byte3 loadShortAbsolute(int index, ByteBuffer buf);
}
