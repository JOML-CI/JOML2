// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float4x4BbOps {
    FloatBuffer storeCMAbsolute(Float4x4 self, int index, FloatBuffer buf);
    Float4x4 loadCMAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeCMAbsolute(Float4x4 self, int index, ByteBuffer buf);
    Float4x4 loadCMAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Float4x4 self, int index, DoubleBuffer buf);
    Float4x4 loadCMAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeCMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf);
    Float4x4 loadCMDoubleAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Float4x4 self, int index, FloatBuffer buf);
    Float4x4 loadRMAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeRMAbsolute(Float4x4 self, int index, ByteBuffer buf);
    Float4x4 loadRMAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Float4x4 self, int index, DoubleBuffer buf);
    Float4x4 loadRMAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeRMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf);
    Float4x4 loadRMDoubleAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Float4x4 self, int index, FloatBuffer buf, int stride);
    Float4x4 loadCMAbsolute(int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride);
    Float4x4 loadCMAbsolute(int index, ByteBuffer buf, int stride);
    DoubleBuffer storeCMAbsolute(Float4x4 self, int index, DoubleBuffer buf, int stride);
    Float4x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride);
    Float4x4 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Float4x4 self, int index, FloatBuffer buf, int stride);
    Float4x4 loadRMAbsolute(int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride);
    Float4x4 loadRMAbsolute(int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Float4x4 self, int index, DoubleBuffer buf, int stride);
    Float4x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMDoubleAbsolute(Float4x4 self, int index, ByteBuffer buf, int stride);
    Float4x4 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride);
}
