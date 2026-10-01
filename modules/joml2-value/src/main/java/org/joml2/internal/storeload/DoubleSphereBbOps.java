// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class DoubleSphereBbOps {
    public abstract DoubleBuffer storeAbsolute(DoubleSphere self, int index, DoubleBuffer buf);
    public abstract DoubleSphere loadAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeAbsolute(DoubleSphere self, int index, ByteBuffer buf);
    public abstract DoubleSphere loadAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeAbsolute(DoubleSphere self, int index, FloatBuffer buf);
    public abstract DoubleSphere loadAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeFloatAbsolute(DoubleSphere self, int index, ByteBuffer buf);
    public abstract DoubleSphere loadFloatAbsolute(int index, ByteBuffer buf);
}
