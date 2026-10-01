// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Double3x3BbOps {
    public abstract DoubleBuffer storeCMAbsolute(Double3x3Impl self, int index, DoubleBuffer buf);
    public abstract Double3x3 loadCMAbsolute(Double3x3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCMAbsolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract Double3x3 loadCMAbsolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeCMAbsolute(Double3x3Impl self, int index, FloatBuffer buf);
    public abstract Double3x3 loadCMAbsolute(Double3x3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeCMFloatAbsolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract Double3x3 loadCMFloatAbsolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRMAbsolute(Double3x3Impl self, int index, DoubleBuffer buf);
    public abstract Double3x3 loadRMAbsolute(Double3x3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRMAbsolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract Double3x3 loadRMAbsolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeRMAbsolute(Double3x3Impl self, int index, FloatBuffer buf);
    public abstract Double3x3 loadRMAbsolute(Double3x3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeRMFloatAbsolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract Double3x3 loadRMFloatAbsolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCMAbsolute(Double3x3Impl self, int index, DoubleBuffer buf, int stride);
    public abstract Double3x3 loadCMAbsolute(Double3x3Impl self, int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeCMAbsolute(Double3x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract Double3x3 loadCMAbsolute(Double3x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeCMAbsolute(Double3x3Impl self, int index, FloatBuffer buf, int stride);
    public abstract Double3x3 loadCMAbsolute(Double3x3Impl self, int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeCMFloatAbsolute(Double3x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract Double3x3 loadCMFloatAbsolute(Double3x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeRMAbsolute(Double3x3Impl self, int index, DoubleBuffer buf, int stride);
    public abstract Double3x3 loadRMAbsolute(Double3x3Impl self, int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeRMAbsolute(Double3x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract Double3x3 loadRMAbsolute(Double3x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeRMAbsolute(Double3x3Impl self, int index, FloatBuffer buf, int stride);
    public abstract Double3x3 loadRMAbsolute(Double3x3Impl self, int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeRMFloatAbsolute(Double3x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract Double3x3 loadRMFloatAbsolute(Double3x3Impl self, int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeCM4x4Absolute(Double3x3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCM4x4Absolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeCM4x4Absolute(Double3x3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeCM4x4FloatAbsolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRM4x4Absolute(Double3x3Impl self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRM4x4Absolute(Double3x3Impl self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeRM4x4Absolute(Double3x3Impl self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeRM4x4FloatAbsolute(Double3x3Impl self, int index, ByteBuffer buf);
}
