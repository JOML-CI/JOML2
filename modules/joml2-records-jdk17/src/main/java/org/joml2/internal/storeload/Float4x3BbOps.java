// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Float4x3BbOps {
    public abstract FloatBuffer storeCMAbsolute(Float4x3 self, int index, FloatBuffer buf);
    public abstract Float4x3 loadCMAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeCMAbsolute(Float4x3 self, int index, ByteBuffer buf);
    public abstract Float4x3 loadCMAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCMAbsolute(Float4x3 self, int index, DoubleBuffer buf);
    public abstract Float4x3 loadCMAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf);
    public abstract Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeRMAbsolute(Float4x3 self, int index, FloatBuffer buf);
    public abstract Float4x3 loadRMAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeRMAbsolute(Float4x3 self, int index, ByteBuffer buf);
    public abstract Float4x3 loadRMAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRMAbsolute(Float4x3 self, int index, DoubleBuffer buf);
    public abstract Float4x3 loadRMAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf);
    public abstract Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeCMAbsolute(Float4x3 self, int index, FloatBuffer buf, int stride);
    public abstract Float4x3 loadCMAbsolute(int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeCMAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride);
    public abstract Float4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeCMAbsolute(Float4x3 self, int index, DoubleBuffer buf, int stride);
    public abstract Float4x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeCMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride);
    public abstract Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeRMAbsolute(Float4x3 self, int index, FloatBuffer buf, int stride);
    public abstract Float4x3 loadRMAbsolute(int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeRMAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride);
    public abstract Float4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeRMAbsolute(Float4x3 self, int index, DoubleBuffer buf, int stride);
    public abstract Float4x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeRMDoubleAbsolute(Float4x3 self, int index, ByteBuffer buf, int stride);
    public abstract Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride);
}
