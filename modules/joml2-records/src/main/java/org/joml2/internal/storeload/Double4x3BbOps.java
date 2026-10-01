// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Double4x3BbOps {
    DoubleBuffer storeCMAbsolute(Double4x3 self, int index, DoubleBuffer buf);
    Double4x3 loadCMAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeCMAbsolute(Double4x3 self, int index, ByteBuffer buf);
    Double4x3 loadCMAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Double4x3 self, int index, FloatBuffer buf);
    Double4x3 loadCMAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeCMFloatAbsolute(Double4x3 self, int index, ByteBuffer buf);
    Double4x3 loadCMFloatAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Double4x3 self, int index, DoubleBuffer buf);
    Double4x3 loadRMAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeRMAbsolute(Double4x3 self, int index, ByteBuffer buf);
    Double4x3 loadRMAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Double4x3 self, int index, FloatBuffer buf);
    Double4x3 loadRMAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeRMFloatAbsolute(Double4x3 self, int index, ByteBuffer buf);
    Double4x3 loadRMFloatAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Double4x3 self, int index, DoubleBuffer buf, int stride);
    Double4x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Double4x3 self, int index, ByteBuffer buf, int stride);
    Double4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride);
    FloatBuffer storeCMAbsolute(Double4x3 self, int index, FloatBuffer buf, int stride);
    Double4x3 loadCMAbsolute(int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMFloatAbsolute(Double4x3 self, int index, ByteBuffer buf, int stride);
    Double4x3 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Double4x3 self, int index, DoubleBuffer buf, int stride);
    Double4x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Double4x3 self, int index, ByteBuffer buf, int stride);
    Double4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Double4x3 self, int index, FloatBuffer buf, int stride);
    Double4x3 loadRMAbsolute(int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMFloatAbsolute(Double4x3 self, int index, ByteBuffer buf, int stride);
    Double4x3 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride);
}
