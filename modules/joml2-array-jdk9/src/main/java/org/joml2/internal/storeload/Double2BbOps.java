// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Double2BbOps {
    public abstract DoubleBuffer storeAbsolute(Double2Impl self, int index, DoubleBuffer buf);
    public abstract Double2 loadAbsolute(Double2Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(Double2Impl self, int index, ByteBuffer buf);
    public abstract Double2 loadAbsolute(Double2Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(Double2Impl self, int index, FloatBuffer buf);
    public abstract Double2 loadAbsolute(Double2Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(Double2Impl self, int index, ByteBuffer buf);
    public abstract Double2 loadFloatAbsolute(Double2Impl self, int index, ByteBuffer buf);
}
