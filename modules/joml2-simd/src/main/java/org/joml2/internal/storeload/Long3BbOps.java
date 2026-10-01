// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public abstract class Long3BbOps {
    public abstract LongBuffer storeAbsolute(Long3Impl self, int index, LongBuffer buf);
    public abstract Long3 loadAbsolute(Long3Impl self, int index, LongBuffer buf);
    public abstract ByteBuffer storeAbsolute(Long3Impl self, int index, ByteBuffer buf);
    public abstract Long3 loadAbsolute(Long3Impl self, int index, ByteBuffer buf);
    public abstract IntBuffer storeAbsolute(Long3Impl self, int index, IntBuffer buf);
    public abstract Long3 loadAbsolute(Long3Impl self, int index, IntBuffer buf);
    public abstract ByteBuffer storeIntAbsolute(Long3Impl self, int index, ByteBuffer buf);
    public abstract Long3 loadIntAbsolute(Long3Impl self, int index, ByteBuffer buf);
}
