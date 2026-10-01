// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public abstract class Long4BbOps {
    public abstract LongBuffer storeAbsolute(Long4Impl self, int index, LongBuffer buf);
    public abstract Long4 loadAbsolute(Long4Impl self, int index, LongBuffer buf);
    public abstract ByteBuffer storeAbsolute(Long4Impl self, int index, ByteBuffer buf);
    public abstract Long4 loadAbsolute(Long4Impl self, int index, ByteBuffer buf);
    public abstract IntBuffer storeAbsolute(Long4Impl self, int index, IntBuffer buf);
    public abstract Long4 loadAbsolute(Long4Impl self, int index, IntBuffer buf);
    public abstract ByteBuffer storeIntAbsolute(Long4Impl self, int index, ByteBuffer buf);
    public abstract Long4 loadIntAbsolute(Long4Impl self, int index, ByteBuffer buf);
}
