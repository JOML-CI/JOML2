// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.unsafe;

import java.lang.reflect.Field;

/**
 * The Unsafe accesses the store/load kernels make through {@link UnsafeOpsHolder#U}: an abstract
 * class rather than an interface, so every call is an invokevirtual (3 bytes) and not an
 * invokeinterface (5) - the bytes HotSpot's inlining limits count; C2 binds the static final
 * holder field's exact class either way.
 */
public abstract class UnsafeOps {
    UnsafeOps() {}

    public abstract void putFloat(long address, float x);
    public abstract void putDouble(long address, double x);
    public abstract void putByte(long address, byte x);
    public abstract void putShort(long address, short x);
    public abstract void putInt(long address, int x);
    public abstract void putLong(long address, long x);
    public abstract float getFloat(long address);
    public abstract double getDouble(long address);
    public abstract byte getByte(long address);
    public abstract short getShort(long address);
    public abstract int getInt(long address);
    public abstract long getLong(long address);
    public abstract long getLong(Object base, long offset);
    public abstract void putLong(Object base, long offset, long x);
    public abstract long objectFieldOffset(Field field);
    public abstract long arrayBaseOffset(Class<?> arrayClass);
    public abstract void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes);
    /** Whether the platform does 8-byte accesses at any address (x86-64, AArch64). */
    public abstract boolean unalignedAccess();
}
