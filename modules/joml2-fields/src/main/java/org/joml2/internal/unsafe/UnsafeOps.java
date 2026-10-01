// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.unsafe;

import java.lang.reflect.Field;

public interface UnsafeOps {
    void putFloat(long address, float x);
    void putDouble(long address, double x);
    void putByte(long address, byte x);
    void putShort(long address, short x);
    void putInt(long address, int x);
    void putLong(long address, long x);
    float getFloat(long address);
    double getDouble(long address);
    byte getByte(long address);
    short getShort(long address);
    int getInt(long address);
    long getLong(long address);
    long getLong(Object base, long offset);
    long objectFieldOffset(Field field);
    long arrayBaseOffset(Class<?> arrayClass);
    void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes);
}
