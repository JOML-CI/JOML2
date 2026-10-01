// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public abstract class Long2BbOps {
    public abstract LongBuffer storeAbsolute(Long2 self, int index, LongBuffer buf);
    public abstract Long2 loadAbsolute(int index, LongBuffer buf);
    public abstract ByteBuffer storeAbsolute(Long2 self, int index, ByteBuffer buf);
    public abstract Long2 loadAbsolute(int index, ByteBuffer buf);
    public abstract IntBuffer storeAbsolute(Long2 self, int index, IntBuffer buf);
    public abstract Long2 loadAbsolute(int index, IntBuffer buf);
    public abstract ByteBuffer storeIntAbsolute(Long2 self, int index, ByteBuffer buf);
    public abstract Long2 loadIntAbsolute(int index, ByteBuffer buf);
}
