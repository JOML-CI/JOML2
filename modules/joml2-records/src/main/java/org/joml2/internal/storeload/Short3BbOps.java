// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public abstract class Short3BbOps {
    public abstract ShortBuffer storeAbsolute(Short3 self, int index, ShortBuffer buf);
    public abstract Short3 loadAbsolute(int index, ShortBuffer buf);
    public abstract ByteBuffer storeAbsolute(Short3 self, int index, ByteBuffer buf);
    public abstract Short3 loadAbsolute(int index, ByteBuffer buf);
    public abstract ByteBuffer storeByteAbsolute(Short3 self, int index, ByteBuffer buf);
    public abstract Short3 loadByteAbsolute(int index, ByteBuffer buf);
}
