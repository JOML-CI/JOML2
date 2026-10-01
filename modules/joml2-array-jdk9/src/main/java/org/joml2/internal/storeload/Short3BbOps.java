// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public interface Short3BbOps {
    ShortBuffer storeAbsolute(Short3Impl self, int index, ShortBuffer buf);
    Short3 loadAbsolute(Short3Impl self, int index, ShortBuffer buf);
    ByteBuffer storeAbsolute(Short3Impl self, int index, ByteBuffer buf);
    Short3 loadAbsolute(Short3Impl self, int index, ByteBuffer buf);
    ByteBuffer storeByteAbsolute(Short3Impl self, int index, ByteBuffer buf);
    Short3 loadByteAbsolute(Short3Impl self, int index, ByteBuffer buf);
}
