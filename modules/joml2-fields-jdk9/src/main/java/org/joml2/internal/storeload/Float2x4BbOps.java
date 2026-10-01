// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Float2x4BbOps {
    public abstract FloatBuffer storeCMAbsolute(Float2x4Impl self, int index, FloatBuffer buf);
    public abstract Float2x4 loadCMAbsolute(Float2x4Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeCMAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    public abstract Float2x4 loadCMAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf);
    public abstract Float2x4 loadCMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    public abstract Float2x4 loadCMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeRMAbsolute(Float2x4Impl self, int index, FloatBuffer buf);
    public abstract Float2x4 loadRMAbsolute(Float2x4Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeRMAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    public abstract Float2x4 loadRMAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf);
    public abstract Float2x4 loadRMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    public abstract Float2x4 loadRMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeCMAbsolute(Float2x4Impl self, int index, FloatBuffer buf, int stride);
    public abstract Float2x4 loadCMAbsolute(Float2x4Impl self, int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeCMAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract Float2x4 loadCMAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeCMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf, int stride);
    public abstract Float2x4 loadCMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeCMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract Float2x4 loadCMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeRMAbsolute(Float2x4Impl self, int index, FloatBuffer buf, int stride);
    public abstract Float2x4 loadRMAbsolute(Float2x4Impl self, int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeRMAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract Float2x4 loadRMAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeRMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf, int stride);
    public abstract Float2x4 loadRMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeRMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    public abstract Float2x4 loadRMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
}
