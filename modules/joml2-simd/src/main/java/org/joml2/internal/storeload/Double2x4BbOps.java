// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Double2x4BbOps {
    DoubleBuffer storeCMAbsolute(Double2x4Impl self, int index, DoubleBuffer buf);
    Double2x4 loadCMAbsolute(Double2x4Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeCMAbsolute(Double2x4Impl self, int index, ByteBuffer buf);
    Double2x4 loadCMAbsolute(Double2x4Impl self, int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Double2x4Impl self, int index, FloatBuffer buf);
    Double2x4 loadCMAbsolute(Double2x4Impl self, int index, FloatBuffer buf);
    ByteBuffer storeCMFloatAbsolute(Double2x4Impl self, int index, ByteBuffer buf);
    Double2x4 loadCMFloatAbsolute(Double2x4Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Double2x4Impl self, int index, DoubleBuffer buf);
    Double2x4 loadRMAbsolute(Double2x4Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeRMAbsolute(Double2x4Impl self, int index, ByteBuffer buf);
    Double2x4 loadRMAbsolute(Double2x4Impl self, int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Double2x4Impl self, int index, FloatBuffer buf);
    Double2x4 loadRMAbsolute(Double2x4Impl self, int index, FloatBuffer buf);
    ByteBuffer storeRMFloatAbsolute(Double2x4Impl self, int index, ByteBuffer buf);
    Double2x4 loadRMFloatAbsolute(Double2x4Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Double2x4Impl self, int index, DoubleBuffer buf, int stride);
    Double2x4 loadCMAbsolute(Double2x4Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Double2x4Impl self, int index, ByteBuffer buf, int stride);
    Double2x4 loadCMAbsolute(Double2x4Impl self, int index, ByteBuffer buf, int stride);
    FloatBuffer storeCMAbsolute(Double2x4Impl self, int index, FloatBuffer buf, int stride);
    Double2x4 loadCMAbsolute(Double2x4Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMFloatAbsolute(Double2x4Impl self, int index, ByteBuffer buf, int stride);
    Double2x4 loadCMFloatAbsolute(Double2x4Impl self, int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Double2x4Impl self, int index, DoubleBuffer buf, int stride);
    Double2x4 loadRMAbsolute(Double2x4Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Double2x4Impl self, int index, ByteBuffer buf, int stride);
    Double2x4 loadRMAbsolute(Double2x4Impl self, int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Double2x4Impl self, int index, FloatBuffer buf, int stride);
    Double2x4 loadRMAbsolute(Double2x4Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMFloatAbsolute(Double2x4Impl self, int index, ByteBuffer buf, int stride);
    Double2x4 loadRMFloatAbsolute(Double2x4Impl self, int index, ByteBuffer buf, int stride);
}
