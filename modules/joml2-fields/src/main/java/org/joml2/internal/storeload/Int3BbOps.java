// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public abstract class Int3BbOps {
    public abstract IntBuffer storeAbsolute(Int3Impl self, int index, IntBuffer buf);
    public abstract Int3 loadAbsolute(Int3Impl self, int index, IntBuffer buf);
    public abstract ByteBuffer storeAbsolute(Int3Impl self, int index, ByteBuffer buf);
    public abstract Int3 loadAbsolute(Int3Impl self, int index, ByteBuffer buf);
    public abstract LongBuffer storeAbsolute(Int3Impl self, int index, LongBuffer buf);
    public abstract Int3 loadAbsolute(Int3Impl self, int index, LongBuffer buf);
    public abstract ByteBuffer storeLongAbsolute(Int3Impl self, int index, ByteBuffer buf);
    public abstract Int3 loadLongAbsolute(Int3Impl self, int index, ByteBuffer buf);
}
