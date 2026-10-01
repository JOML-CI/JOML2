// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public interface Byte3BbOps {
    ByteBuffer storeAbsolute(Byte3Impl self, int index, ByteBuffer buf);
    Byte3 loadAbsolute(Byte3Impl self, int index, ByteBuffer buf);
    ShortBuffer storeAbsolute(Byte3Impl self, int index, ShortBuffer buf);
    Byte3 loadAbsolute(Byte3Impl self, int index, ShortBuffer buf);
    ByteBuffer storeShortAbsolute(Byte3Impl self, int index, ByteBuffer buf);
    Byte3 loadShortAbsolute(Byte3Impl self, int index, ByteBuffer buf);
}
