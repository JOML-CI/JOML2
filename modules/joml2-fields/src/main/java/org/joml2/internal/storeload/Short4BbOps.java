// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public abstract class Short4BbOps {
    public abstract ShortBuffer storeAbsolute(Short4Impl self, int index, ShortBuffer buf);
    public abstract Short4 loadAbsolute(Short4Impl self, int index, ShortBuffer buf);
    public abstract ByteBuffer storeAbsolute(Short4Impl self, int index, ByteBuffer buf);
    public abstract Short4 loadAbsolute(Short4Impl self, int index, ByteBuffer buf);
    public abstract ByteBuffer storeByteAbsolute(Short4Impl self, int index, ByteBuffer buf);
    public abstract Short4 loadByteAbsolute(Short4Impl self, int index, ByteBuffer buf);
}
