// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Double4x2BbOps {
    public abstract DoubleBuffer storeCMAbsolute(Double4x2 self, int index, DoubleBuffer buf);
    public abstract Double4x2 loadCMAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCMAbsolute(Double4x2 self, int index, ByteBuffer buf);
    public abstract Double4x2 loadCMAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeCMAbsolute(Double4x2 self, int index, FloatBuffer buf);
    public abstract Double4x2 loadCMAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeCMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf);
    public abstract Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRMAbsolute(Double4x2 self, int index, DoubleBuffer buf);
    public abstract Double4x2 loadRMAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRMAbsolute(Double4x2 self, int index, ByteBuffer buf);
    public abstract Double4x2 loadRMAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeRMAbsolute(Double4x2 self, int index, FloatBuffer buf);
    public abstract Double4x2 loadRMAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeRMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf);
    public abstract Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCMAbsolute(Double4x2 self, int index, DoubleBuffer buf, int stride);
    public abstract Double4x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeCMAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride);
    public abstract Double4x2 loadCMAbsolute(int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeCMAbsolute(Double4x2 self, int index, FloatBuffer buf, int stride);
    public abstract Double4x2 loadCMAbsolute(int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeCMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride);
    public abstract Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeRMAbsolute(Double4x2 self, int index, DoubleBuffer buf, int stride);
    public abstract Double4x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeRMAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride);
    public abstract Double4x2 loadRMAbsolute(int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeRMAbsolute(Double4x2 self, int index, FloatBuffer buf, int stride);
    public abstract Double4x2 loadRMAbsolute(int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeRMFloatAbsolute(Double4x2 self, int index, ByteBuffer buf, int stride);
    public abstract Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride);
}
