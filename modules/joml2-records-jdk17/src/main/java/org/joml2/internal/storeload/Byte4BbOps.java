// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public abstract class Byte4BbOps {
    public abstract ByteBuffer storeAbsolute(Byte4 self, int index, ByteBuffer buf);
    public abstract Byte4 loadAbsolute(int index, ByteBuffer buf);
    public abstract ShortBuffer storeAbsolute(Byte4 self, int index, ShortBuffer buf);
    public abstract Byte4 loadAbsolute(int index, ShortBuffer buf);
    public abstract ByteBuffer storeShortAbsolute(Byte4 self, int index, ByteBuffer buf);
    public abstract Byte4 loadShortAbsolute(int index, ByteBuffer buf);
}
