// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.unsafe;

import java.lang.reflect.Field;

public final class JdkInternalUnsafeOps implements UnsafeOps {
    private static final jdk.internal.misc.Unsafe U = jdk.internal.misc.Unsafe.getUnsafe();
    public void putFloat(long a, float x)   { U.putFloat(a, x); }
    public void putDouble(long a, double x) { U.putDouble(a, x); }
    public void putByte(long a, byte x)     { U.putByte(a, x); }
    public void putShort(long a, short x)   { U.putShort(a, x); }
    public void putInt(long a, int x)       { U.putInt(a, x); }
    public void putLong(long a, long x)     { U.putLong(a, x); }
    public float getFloat(long a)           { return U.getFloat(a); }
    public double getDouble(long a)         { return U.getDouble(a); }
    public byte getByte(long a)             { return U.getByte(a); }
    public short getShort(long a)           { return U.getShort(a); }
    public int getInt(long a)               { return U.getInt(a); }
    public long getLong(long a)             { return U.getLong(a); }
    public long getLong(Object b, long o)   { return U.getLong(b, o); }
    public long objectFieldOffset(Field f)  { return U.objectFieldOffset(f); }
    public long arrayBaseOffset(Class<?> c) { return U.arrayBaseOffset(c); }
    public void copyMemory(Object sb, long so, Object db, long doff, long n) { U.copyMemory(sb, so, db, doff, n); }
}
