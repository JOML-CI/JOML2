// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public abstract class Byte3BbOps {
    public abstract ByteBuffer storeAbsolute(Byte3Impl self, int index, ByteBuffer buf);
    public abstract Byte3 loadAbsolute(Byte3Impl self, int index, ByteBuffer buf);
    public abstract ShortBuffer storeAbsolute(Byte3Impl self, int index, ShortBuffer buf);
    public abstract Byte3 loadAbsolute(Byte3Impl self, int index, ShortBuffer buf);
    public abstract ByteBuffer storeShortAbsolute(Byte3Impl self, int index, ByteBuffer buf);
    public abstract Byte3 loadShortAbsolute(Byte3Impl self, int index, ByteBuffer buf);
}
