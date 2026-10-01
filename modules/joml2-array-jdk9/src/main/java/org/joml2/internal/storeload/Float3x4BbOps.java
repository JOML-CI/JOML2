// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float3x4BbOps {
    FloatBuffer storeCMAbsolute(Float3x4Impl self, int index, FloatBuffer buf);
    Float3x4 loadCMAbsolute(Float3x4Impl self, int index, FloatBuffer buf);
    ByteBuffer storeCMAbsolute(Float3x4Impl self, int index, ByteBuffer buf);
    Float3x4 loadCMAbsolute(Float3x4Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf);
    Float3x4 loadCMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeCMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf);
    Float3x4 loadCMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Float3x4Impl self, int index, FloatBuffer buf);
    Float3x4 loadRMAbsolute(Float3x4Impl self, int index, FloatBuffer buf);
    ByteBuffer storeRMAbsolute(Float3x4Impl self, int index, ByteBuffer buf);
    Float3x4 loadRMAbsolute(Float3x4Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf);
    Float3x4 loadRMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeRMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf);
    Float3x4 loadRMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Float3x4Impl self, int index, FloatBuffer buf, int stride);
    Float3x4 loadCMAbsolute(Float3x4Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride);
    Float3x4 loadCMAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride);
    DoubleBuffer storeCMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf, int stride);
    Float3x4 loadCMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride);
    Float3x4 loadCMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Float3x4Impl self, int index, FloatBuffer buf, int stride);
    Float3x4 loadRMAbsolute(Float3x4Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride);
    Float3x4 loadRMAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf, int stride);
    Float3x4 loadRMAbsolute(Float3x4Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride);
    Float3x4 loadRMDoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf, int stride);
    FloatBuffer storeCM4x4Absolute(Float3x4Impl self, int index, FloatBuffer buf);
    ByteBuffer storeCM4x4Absolute(Float3x4Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeCM4x4Absolute(Float3x4Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeCM4x4DoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf);
    FloatBuffer storeRM4x4Absolute(Float3x4Impl self, int index, FloatBuffer buf);
    ByteBuffer storeRM4x4Absolute(Float3x4Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeRM4x4Absolute(Float3x4Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeRM4x4DoubleAbsolute(Float3x4Impl self, int index, ByteBuffer buf);
}
