// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Float2x3BbOps {
    public abstract FloatBuffer storeCMAbsolute(Float2x3Impl self, int index, FloatBuffer buf);
    public abstract Float2x3 loadCMAbsolute(Float2x3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeCMAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract Float2x3 loadCMAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf);
    public abstract Float2x3 loadCMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract Float2x3 loadCMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeRMAbsolute(Float2x3Impl self, int index, FloatBuffer buf);
    public abstract Float2x3 loadRMAbsolute(Float2x3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeRMAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract Float2x3 loadRMAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf);
    public abstract Float2x3 loadRMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract Float2x3 loadRMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeCMAbsolute(Float2x3Impl self, int index, FloatBuffer buf, int stride);
    public abstract Float2x3 loadCMAbsolute(Float2x3Impl self, int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeCMAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract Float2x3 loadCMAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeCMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf, int stride);
    public abstract Float2x3 loadCMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeCMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract Float2x3 loadCMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeRMAbsolute(Float2x3Impl self, int index, FloatBuffer buf, int stride);
    public abstract Float2x3 loadRMAbsolute(Float2x3Impl self, int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeRMAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract Float2x3 loadRMAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeRMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf, int stride);
    public abstract Float2x3 loadRMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeRMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract Float2x3 loadRMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeCM3x3Absolute(Float2x3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeCM3x3Absolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCM3x3Absolute(Float2x3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCM3x3DoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeRM3x3Absolute(Float2x3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeRM3x3Absolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRM3x3Absolute(Float2x3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRM3x3DoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeCM4x4Absolute(Float2x3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeCM4x4Absolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCM4x4Absolute(Float2x3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCM4x4DoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeRM4x4Absolute(Float2x3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeRM4x4Absolute(Float2x3Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRM4x4Absolute(Float2x3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRM4x4DoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
}
