// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Double2x2BbOps {
    DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf);
    Double2x2 loadCMAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf);
    Double2x2 loadCMAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf);
    Double2x2 loadCMAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf);
    Double2x2 loadRMAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf);
    Double2x2 loadRMAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf);
    Double2x2 loadRMAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride);
    Double2x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
    Double2x2 loadCMAbsolute(int index, ByteBuffer buf, int stride);
    FloatBuffer storeCMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride);
    Double2x2 loadCMAbsolute(int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
    Double2x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Double2x2 self, int index, DoubleBuffer buf, int stride);
    Double2x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
    Double2x2 loadRMAbsolute(int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Double2x2 self, int index, FloatBuffer buf, int stride);
    Double2x2 loadRMAbsolute(int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMFloatAbsolute(Double2x2 self, int index, ByteBuffer buf, int stride);
    Double2x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride);
    DoubleBuffer storeCM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf);
    ByteBuffer storeCM3x3Absolute(Double2x2 self, int index, ByteBuffer buf);
    FloatBuffer storeCM3x3Absolute(Double2x2 self, int index, FloatBuffer buf);
    ByteBuffer storeCM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    DoubleBuffer storeRM3x3Absolute(Double2x2 self, int index, DoubleBuffer buf);
    ByteBuffer storeRM3x3Absolute(Double2x2 self, int index, ByteBuffer buf);
    FloatBuffer storeRM3x3Absolute(Double2x2 self, int index, FloatBuffer buf);
    ByteBuffer storeRM3x3FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    DoubleBuffer storeCM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf);
    ByteBuffer storeCM4x4Absolute(Double2x2 self, int index, ByteBuffer buf);
    FloatBuffer storeCM4x4Absolute(Double2x2 self, int index, FloatBuffer buf);
    ByteBuffer storeCM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
    DoubleBuffer storeRM4x4Absolute(Double2x2 self, int index, DoubleBuffer buf);
    ByteBuffer storeRM4x4Absolute(Double2x2 self, int index, ByteBuffer buf);
    FloatBuffer storeRM4x4Absolute(Double2x2 self, int index, FloatBuffer buf);
    ByteBuffer storeRM4x4FloatAbsolute(Double2x2 self, int index, ByteBuffer buf);
}
