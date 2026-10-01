// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Float3x2BbOps {
    FloatBuffer storeCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf);
    Float3x2 loadCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf);
    ByteBuffer storeCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf);
    Float3x2 loadCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf);
    Float3x2 loadCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf);
    Float3x2 loadCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf);
    Float3x2 loadRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf);
    ByteBuffer storeRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf);
    Float3x2 loadRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf);
    Float3x2 loadRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf);
    Float3x2 loadRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride);
    Float3x2 loadCMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride);
    Float3x2 loadCMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride);
    DoubleBuffer storeCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride);
    Float3x2 loadCMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride);
    Float3x2 loadCMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride);
    Float3x2 loadRMAbsolute(Float3x2Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride);
    Float3x2 loadRMAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride);
    Float3x2 loadRMAbsolute(Float3x2Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride);
    Float3x2 loadRMDoubleAbsolute(Float3x2Impl self, int index, ByteBuffer buf, int stride);
}
