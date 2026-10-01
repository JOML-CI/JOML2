// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class Float3x4BbOps {
    public abstract FloatBuffer storeCMAbsolute(Float3x4 self, int index, FloatBuffer buf);
    public abstract Float3x4 loadCMAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeCMAbsolute(Float3x4 self, int index, ByteBuffer buf);
    public abstract Float3x4 loadCMAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCMAbsolute(Float3x4 self, int index, DoubleBuffer buf);
    public abstract Float3x4 loadCMAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCMDoubleAbsolute(Float3x4 self, int index, ByteBuffer buf);
    public abstract Float3x4 loadCMDoubleAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeRMAbsolute(Float3x4 self, int index, FloatBuffer buf);
    public abstract Float3x4 loadRMAbsolute(int index, FloatBuffer buf);
    public abstract ByteBuffer storeRMAbsolute(Float3x4 self, int index, ByteBuffer buf);
    public abstract Float3x4 loadRMAbsolute(int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRMAbsolute(Float3x4 self, int index, DoubleBuffer buf);
    public abstract Float3x4 loadRMAbsolute(int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRMDoubleAbsolute(Float3x4 self, int index, ByteBuffer buf);
    public abstract Float3x4 loadRMDoubleAbsolute(int index, ByteBuffer buf);
    public abstract FloatBuffer storeCMAbsolute(Float3x4 self, int index, FloatBuffer buf, int stride);
    public abstract Float3x4 loadCMAbsolute(int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeCMAbsolute(Float3x4 self, int index, ByteBuffer buf, int stride);
    public abstract Float3x4 loadCMAbsolute(int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeCMAbsolute(Float3x4 self, int index, DoubleBuffer buf, int stride);
    public abstract Float3x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeCMDoubleAbsolute(Float3x4 self, int index, ByteBuffer buf, int stride);
    public abstract Float3x4 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeRMAbsolute(Float3x4 self, int index, FloatBuffer buf, int stride);
    public abstract Float3x4 loadRMAbsolute(int index, FloatBuffer buf, int stride);
    public abstract ByteBuffer storeRMAbsolute(Float3x4 self, int index, ByteBuffer buf, int stride);
    public abstract Float3x4 loadRMAbsolute(int index, ByteBuffer buf, int stride);
    public abstract DoubleBuffer storeRMAbsolute(Float3x4 self, int index, DoubleBuffer buf, int stride);
    public abstract Float3x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
    public abstract ByteBuffer storeRMDoubleAbsolute(Float3x4 self, int index, ByteBuffer buf, int stride);
    public abstract Float3x4 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride);
    public abstract FloatBuffer storeCM4x4Absolute(Float3x4 self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeCM4x4Absolute(Float3x4 self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeCM4x4Absolute(Float3x4 self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeCM4x4DoubleAbsolute(Float3x4 self, int index, ByteBuffer buf);
    public abstract FloatBuffer storeRM4x4Absolute(Float3x4 self, int index, FloatBuffer buf);
    public abstract ByteBuffer storeRM4x4Absolute(Float3x4 self, int index, ByteBuffer buf);
    public abstract DoubleBuffer storeRM4x4Absolute(Float3x4 self, int index, DoubleBuffer buf);
    public abstract ByteBuffer storeRM4x4DoubleAbsolute(Float3x4 self, int index, ByteBuffer buf);
}
