// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float2x2BbOps {
    FloatBuffer storeCMAbsolute(Float2x2 self, int index, FloatBuffer buf);
    Float2x2 loadCMAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeCMAbsolute(Float2x2 self, int index, ByteBuffer buf);
    Float2x2 loadCMAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Float2x2 self, int index, DoubleBuffer buf);
    Float2x2 loadCMAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeCMDoubleAbsolute(Float2x2 self, int index, ByteBuffer buf);
    Float2x2 loadCMDoubleAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Float2x2 self, int index, FloatBuffer buf);
    Float2x2 loadRMAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeRMAbsolute(Float2x2 self, int index, ByteBuffer buf);
    Float2x2 loadRMAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Float2x2 self, int index, DoubleBuffer buf);
    Float2x2 loadRMAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeRMDoubleAbsolute(Float2x2 self, int index, ByteBuffer buf);
    Float2x2 loadRMDoubleAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Float2x2 self, int index, FloatBuffer buf, int stride);
    Float2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Float2x2 self, int index, ByteBuffer buf, int stride);
    Float2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride);
    DoubleBuffer storeCMAbsolute(Float2x2 self, int index, DoubleBuffer buf, int stride);
    Float2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMDoubleAbsolute(Float2x2 self, int index, ByteBuffer buf, int stride);
    Float2x2 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Float2x2 self, int index, FloatBuffer buf, int stride);
    Float2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Float2x2 self, int index, ByteBuffer buf, int stride);
    Float2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Float2x2 self, int index, DoubleBuffer buf, int stride);
    Float2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMDoubleAbsolute(Float2x2 self, int index, ByteBuffer buf, int stride);
    Float2x2 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride);
    FloatBuffer storeCM3x3Absolute(Float2x2 self, int index, FloatBuffer buf);
    ByteBuffer storeCM3x3Absolute(Float2x2 self, int index, ByteBuffer buf);
    DoubleBuffer storeCM3x3Absolute(Float2x2 self, int index, DoubleBuffer buf);
    ByteBuffer storeCM3x3DoubleAbsolute(Float2x2 self, int index, ByteBuffer buf);
    FloatBuffer storeRM3x3Absolute(Float2x2 self, int index, FloatBuffer buf);
    ByteBuffer storeRM3x3Absolute(Float2x2 self, int index, ByteBuffer buf);
    DoubleBuffer storeRM3x3Absolute(Float2x2 self, int index, DoubleBuffer buf);
    ByteBuffer storeRM3x3DoubleAbsolute(Float2x2 self, int index, ByteBuffer buf);
    FloatBuffer storeCM4x4Absolute(Float2x2 self, int index, FloatBuffer buf);
    ByteBuffer storeCM4x4Absolute(Float2x2 self, int index, ByteBuffer buf);
    DoubleBuffer storeCM4x4Absolute(Float2x2 self, int index, DoubleBuffer buf);
    ByteBuffer storeCM4x4DoubleAbsolute(Float2x2 self, int index, ByteBuffer buf);
    FloatBuffer storeRM4x4Absolute(Float2x2 self, int index, FloatBuffer buf);
    ByteBuffer storeRM4x4Absolute(Float2x2 self, int index, ByteBuffer buf);
    DoubleBuffer storeRM4x4Absolute(Float2x2 self, int index, DoubleBuffer buf);
    ByteBuffer storeRM4x4DoubleAbsolute(Float2x2 self, int index, ByteBuffer buf);
}
