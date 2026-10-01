// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Float3BbOps {
    public abstract FloatBuffer storeAbsolute(Float3Impl self, int index, FloatBuffer buf);
    public abstract Float3 loadAbsolute(Float3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeAbsolute(Float3Impl self, int index, ByteBuffer buf);
    public abstract Float3 loadAbsolute(Float3Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeAbsolute(Float3Impl self, int index, DoubleBuffer buf);
    public abstract Float3 loadAbsolute(Float3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeDoubleAbsolute(Float3Impl self, int index, ByteBuffer buf);
    public abstract Float3 loadDoubleAbsolute(Float3Impl self, int index, ByteBuffer buf);
}
