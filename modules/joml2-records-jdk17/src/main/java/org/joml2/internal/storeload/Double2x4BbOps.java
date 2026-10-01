// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Double2x4BbOps {
    public abstract DoubleBuffer storeCMAbsolute(Double2x4 self, int index, DoubleBuffer buf);
    public abstract Double2x4 loadCMAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCMAbsolute(Double2x4 self, int index, ByteBuffer buf);
    public abstract Double2x4 loadCMAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeCMAbsolute(Double2x4 self, int index, FloatBuffer buf);
    public abstract Double2x4 loadCMAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeCMFloatAbsolute(Double2x4 self, int index, ByteBuffer buf);
    public abstract Double2x4 loadCMFloatAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRMAbsolute(Double2x4 self, int index, DoubleBuffer buf);
    public abstract Double2x4 loadRMAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRMAbsolute(Double2x4 self, int index, ByteBuffer buf);
    public abstract Double2x4 loadRMAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeRMAbsolute(Double2x4 self, int index, FloatBuffer buf);
    public abstract Double2x4 loadRMAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeRMFloatAbsolute(Double2x4 self, int index, ByteBuffer buf);
    public abstract Double2x4 loadRMFloatAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCMAbsolute(Double2x4 self, int index, DoubleBuffer buf, int stride);
    public abstract Double2x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeCMAbsolute(Double2x4 self, int index, ByteBuffer buf, int stride);
    public abstract Double2x4 loadCMAbsolute(int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeCMAbsolute(Double2x4 self, int index, FloatBuffer buf, int stride);
    public abstract Double2x4 loadCMAbsolute(int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeCMFloatAbsolute(Double2x4 self, int index, ByteBuffer buf, int stride);
    public abstract Double2x4 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeRMAbsolute(Double2x4 self, int index, DoubleBuffer buf, int stride);
    public abstract Double2x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeRMAbsolute(Double2x4 self, int index, ByteBuffer buf, int stride);
    public abstract Double2x4 loadRMAbsolute(int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeRMAbsolute(Double2x4 self, int index, FloatBuffer buf, int stride);
    public abstract Double2x4 loadRMAbsolute(int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeRMFloatAbsolute(Double2x4 self, int index, ByteBuffer buf, int stride);
    public abstract Double2x4 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride);
}
