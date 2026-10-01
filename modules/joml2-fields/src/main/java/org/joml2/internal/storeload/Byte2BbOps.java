// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public abstract class Byte2BbOps {
    public abstract ByteBuffer storeAbsolute(Byte2Impl self, int index, ByteBuffer buf);
    public abstract Byte2 loadAbsolute(Byte2Impl self, int index, ByteBuffer buf);
    public abstract ShortBuffer storeAbsolute(Byte2Impl self, int index, ShortBuffer buf);
    public abstract Byte2 loadAbsolute(Byte2Impl self, int index, ShortBuffer buf);
    public abstract ByteBuffer storeShortAbsolute(Byte2Impl self, int index, ByteBuffer buf);
    public abstract Byte2 loadShortAbsolute(Byte2Impl self, int index, ByteBuffer buf);
}
