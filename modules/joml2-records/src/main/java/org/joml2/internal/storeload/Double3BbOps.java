// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Double3BbOps {
    public abstract DoubleBuffer storeAbsolute(Double3 self, int index, DoubleBuffer buf);
    public abstract Double3 loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(Double3 self, int index, ByteBuffer buf);
    public abstract Double3 loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(Double3 self, int index, FloatBuffer buf);
    public abstract Double3 loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(Double3 self, int index, ByteBuffer buf);
    public abstract Double3 loadFloatAbsolute(int index, ByteBuffer buf);
}
