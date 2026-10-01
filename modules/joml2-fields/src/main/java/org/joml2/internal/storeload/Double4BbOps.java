// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Double4BbOps {
    public abstract DoubleBuffer storeAbsolute(Double4Impl self, int index, DoubleBuffer buf);
    public abstract Double4 loadAbsolute(Double4Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(Double4Impl self, int index, ByteBuffer buf);
    public abstract Double4 loadAbsolute(Double4Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(Double4Impl self, int index, FloatBuffer buf);
    public abstract Double4 loadAbsolute(Double4Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(Double4Impl self, int index, ByteBuffer buf);
    public abstract Double4 loadFloatAbsolute(Double4Impl self, int index, ByteBuffer buf);
}
