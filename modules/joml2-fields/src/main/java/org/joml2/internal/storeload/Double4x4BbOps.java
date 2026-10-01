// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Double4x4BbOps {
    public abstract DoubleBuffer storeCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf);
    public abstract Double4x4 loadCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf);
    public abstract Double4x4 loadCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf);
    public abstract Double4x4 loadCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf);
    public abstract Double4x4 loadCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf);
    public abstract Double4x4 loadRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf);
    public abstract Double4x4 loadRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf);
    public abstract Double4x4 loadRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf);
    public abstract Double4x4 loadRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride);
    public abstract Double4x4 loadCMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract Double4x4 loadCMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride);
    public abstract Double4x4 loadCMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract Double4x4 loadCMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride);
    public abstract Double4x4 loadRMAbsolute(Double4x4Impl self, int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract Double4x4 loadRMAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride);
    public abstract Double4x4 loadRMAbsolute(Double4x4Impl self, int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract Double4x4 loadRMFloatAbsolute(Double4x4Impl self, int index, ByteBuffer buf, int stride);
}
