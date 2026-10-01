// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float2x4BbOps {
    FloatBuffer storeCMAbsolute(Float2x4Impl self, int index, FloatBuffer buf);
    Float2x4 loadCMAbsolute(Float2x4Impl self, int index, FloatBuffer buf);
    ByteBuffer storeCMAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    Float2x4 loadCMAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf);
    Float2x4 loadCMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeCMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    Float2x4 loadCMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Float2x4Impl self, int index, FloatBuffer buf);
    Float2x4 loadRMAbsolute(Float2x4Impl self, int index, FloatBuffer buf);
    ByteBuffer storeRMAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    Float2x4 loadRMAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf);
    Float2x4 loadRMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeRMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    Float2x4 loadRMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Float2x4Impl self, int index, FloatBuffer buf, int stride);
    Float2x4 loadCMAbsolute(Float2x4Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    Float2x4 loadCMAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    DoubleBuffer storeCMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf, int stride);
    Float2x4 loadCMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    Float2x4 loadCMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Float2x4Impl self, int index, FloatBuffer buf, int stride);
    Float2x4 loadRMAbsolute(Float2x4Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    Float2x4 loadRMAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf, int stride);
    Float2x4 loadRMAbsolute(Float2x4Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
    Float2x4 loadRMDoubleAbsolute(Float2x4Impl self, int index, ByteBuffer buf, int stride);
}
