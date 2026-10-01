// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float2x3BbOps {
    FloatBuffer storeCMAbsolute(Float2x3Impl self, int index, FloatBuffer buf);
    Float2x3 loadCMAbsolute(Float2x3Impl self, int index, FloatBuffer buf);
    ByteBuffer storeCMAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    Float2x3 loadCMAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf);
    Float2x3 loadCMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeCMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    Float2x3 loadCMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Float2x3Impl self, int index, FloatBuffer buf);
    Float2x3 loadRMAbsolute(Float2x3Impl self, int index, FloatBuffer buf);
    ByteBuffer storeRMAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    Float2x3 loadRMAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf);
    Float2x3 loadRMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeRMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    Float2x3 loadRMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Float2x3Impl self, int index, FloatBuffer buf, int stride);
    Float2x3 loadCMAbsolute(Float2x3Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    Float2x3 loadCMAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    DoubleBuffer storeCMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf, int stride);
    Float2x3 loadCMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    Float2x3 loadCMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Float2x3Impl self, int index, FloatBuffer buf, int stride);
    Float2x3 loadRMAbsolute(Float2x3Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    Float2x3 loadRMAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf, int stride);
    Float2x3 loadRMAbsolute(Float2x3Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    Float2x3 loadRMDoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf, int stride);
    FloatBuffer storeCM3x3Absolute(Float2x3Impl self, int index, FloatBuffer buf);
    ByteBuffer storeCM3x3Absolute(Float2x3Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeCM3x3Absolute(Float2x3Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeCM3x3DoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    FloatBuffer storeRM3x3Absolute(Float2x3Impl self, int index, FloatBuffer buf);
    ByteBuffer storeRM3x3Absolute(Float2x3Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeRM3x3Absolute(Float2x3Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeRM3x3DoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    FloatBuffer storeCM4x4Absolute(Float2x3Impl self, int index, FloatBuffer buf);
    ByteBuffer storeCM4x4Absolute(Float2x3Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeCM4x4Absolute(Float2x3Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeCM4x4DoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
    FloatBuffer storeRM4x4Absolute(Float2x3Impl self, int index, FloatBuffer buf);
    ByteBuffer storeRM4x4Absolute(Float2x3Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeRM4x4Absolute(Float2x3Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeRM4x4DoubleAbsolute(Float2x3Impl self, int index, ByteBuffer buf);
}
