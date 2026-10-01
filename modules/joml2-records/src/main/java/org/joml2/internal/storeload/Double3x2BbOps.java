// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface Double3x2BbOps {
    DoubleBuffer storeCMAbsolute(Double3x2 self, int index, DoubleBuffer buf);
    Double3x2 loadCMAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeCMAbsolute(Double3x2 self, int index, ByteBuffer buf);
    Double3x2 loadCMAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeCMAbsolute(Double3x2 self, int index, FloatBuffer buf);
    Double3x2 loadCMAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeCMFloatAbsolute(Double3x2 self, int index, ByteBuffer buf);
    Double3x2 loadCMFloatAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeRMAbsolute(Double3x2 self, int index, DoubleBuffer buf);
    Double3x2 loadRMAbsolute(int index, DoubleBuffer buf);
    ByteBuffer storeRMAbsolute(Double3x2 self, int index, ByteBuffer buf);
    Double3x2 loadRMAbsolute(int index, ByteBuffer buf);
    FloatBuffer storeRMAbsolute(Double3x2 self, int index, FloatBuffer buf);
    Double3x2 loadRMAbsolute(int index, FloatBuffer buf);
    ByteBuffer storeRMFloatAbsolute(Double3x2 self, int index, ByteBuffer buf);
    Double3x2 loadRMFloatAbsolute(int index, ByteBuffer buf);
    DoubleBuffer storeCMAbsolute(Double3x2 self, int index, DoubleBuffer buf, int stride);
    Double3x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride);
    ByteBuffer storeCMAbsolute(Double3x2 self, int index, ByteBuffer buf, int stride);
    Double3x2 loadCMAbsolute(int index, ByteBuffer buf, int stride);
    FloatBuffer storeCMAbsolute(Double3x2 self, int index, FloatBuffer buf, int stride);
    Double3x2 loadCMAbsolute(int index, FloatBuffer buf, int stride);
    ByteBuffer storeCMFloatAbsolute(Double3x2 self, int index, ByteBuffer buf, int stride);
    Double3x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride);
    DoubleBuffer storeRMAbsolute(Double3x2 self, int index, DoubleBuffer buf, int stride);
    Double3x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride);
    ByteBuffer storeRMAbsolute(Double3x2 self, int index, ByteBuffer buf, int stride);
    Double3x2 loadRMAbsolute(int index, ByteBuffer buf, int stride);
    FloatBuffer storeRMAbsolute(Double3x2 self, int index, FloatBuffer buf, int stride);
    Double3x2 loadRMAbsolute(int index, FloatBuffer buf, int stride);
    ByteBuffer storeRMFloatAbsolute(Double3x2 self, int index, ByteBuffer buf, int stride);
    Double3x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride);
}
