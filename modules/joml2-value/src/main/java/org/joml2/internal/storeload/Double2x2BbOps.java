// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Double2x2BbOps {
    public abstract DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf);
    public abstract Double2x2 loadCMAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract Double2x2 loadCMAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf);
    public abstract Double2x2 loadCMAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf);
    public abstract Double2x2 loadRMAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract Double2x2 loadRMAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf);
    public abstract Double2x2 loadRMAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride);
    public abstract Double2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
    public abstract Double2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride);
    public abstract Double2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
    public abstract Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride);
    public abstract Double2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
    public abstract Double2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride);
    public abstract Double2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
    public abstract Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeCM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCM3x3Absolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeCM3x3Absolute(Double2x2 self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeCM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRM3x3Absolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeRM3x3Absolute(Double2x2 self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeRM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCM4x4Absolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeCM4x4Absolute(Double2x2 self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeCM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRM4x4Absolute(Double2x2 self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeRM4x4Absolute(Double2x2 self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeRM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
}
