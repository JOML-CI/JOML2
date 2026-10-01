// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Double4x2BbOps {
    DoubleBuffer storeCMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf);
    Double4x2 loadCMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeCMAbsolute(Double4x2Impl self, int index, ByteBuffer buf);
    Double4x2 loadCMAbsolute(Double4x2Impl self, int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Double4x2Impl self, int index, FloatBuffer buf);
    Double4x2 loadCMAbsolute(Double4x2Impl self, int index, FloatBuffer buf);
    ByteBuffer storeCMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf);
    Double4x2 loadCMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf);
    Double4x2 loadRMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf);
    ByteBuffer storeRMAbsolute(Double4x2Impl self, int index, ByteBuffer buf);
    Double4x2 loadRMAbsolute(Double4x2Impl self, int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Double4x2Impl self, int index, FloatBuffer buf);
    Double4x2 loadRMAbsolute(Double4x2Impl self, int index, FloatBuffer buf);
    ByteBuffer storeRMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf);
    Double4x2 loadRMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf, int stride);
    Double4x2 loadCMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride);
    Double4x2 loadCMAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride);
    FloatBuffer storeCMAbsolute(Double4x2Impl self, int index, FloatBuffer buf, int stride);
    Double4x2 loadCMAbsolute(Double4x2Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride);
    Double4x2 loadCMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf, int stride);
    Double4x2 loadRMAbsolute(Double4x2Impl self, int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride);
    Double4x2 loadRMAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Double4x2Impl self, int index, FloatBuffer buf, int stride);
    Double4x2 loadRMAbsolute(Double4x2Impl self, int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride);
    Double4x2 loadRMFloatAbsolute(Double4x2Impl self, int index, ByteBuffer buf, int stride);
}
