// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.unsafe;

import java.lang.reflect.Field;

// sun.misc.Unsafe is the backend where jdk.internal.misc is not exported: its memory access
// methods are deprecated for removal, which the class knows
@SuppressWarnings("removal")
public final class SunMiscUnsafeOps extends UnsafeOps {
    private static final sun.misc.Unsafe U = load();
    private static sun.misc.Unsafe load() {
        try {
            Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (sun.misc.Unsafe) f.get(null);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    // the absolute-address accesses call the (base, offset) form with a null base, which the
    // (address) form only forwards to: one inlined call less per element for C2's node budget
    public void putFloat(long a, float x)   { U.putFloat(null, a, x); }
    public void putDouble(long a, double x) { U.putDouble(null, a, x); }
    public void putByte(long a, byte x)     { U.putByte(null, a, x); }
    public void putShort(long a, short x)   { U.putShort(null, a, x); }
    public void putInt(long a, int x)       { U.putInt(null, a, x); }
    public void putLong(long a, long x)     { U.putLong(null, a, x); }
    public float getFloat(long a)           { return U.getFloat(null, a); }
    public double getDouble(long a)         { return U.getDouble(null, a); }
    public byte getByte(long a)             { return U.getByte(null, a); }
    public short getShort(long a)           { return U.getShort(null, a); }
    public int getInt(long a)               { return U.getInt(null, a); }
    public long getLong(long a)             { return U.getLong(null, a); }
    public long getLong(Object b, long o)   { return U.getLong(b, o); }
    public void putLong(Object b, long o, long x) { U.putLong(b, o, x); }
    public float getFloat(Object b, long o) { return U.getFloat(b, o); }
    public double getDouble(Object b, long o) { return U.getDouble(b, o); }
    public byte getByte(Object b, long o)   { return U.getByte(b, o); }
    public short getShort(Object b, long o) { return U.getShort(b, o); }
    public int getInt(Object b, long o)     { return U.getInt(b, o); }
    public void putFloat(Object b, long o, float x) { U.putFloat(b, o, x); }
    public void putDouble(Object b, long o, double x) { U.putDouble(b, o, x); }
    public void putByte(Object b, long o, byte x) { U.putByte(b, o, x); }
    public void putShort(Object b, long o, short x) { U.putShort(b, o, x); }
    public void putInt(Object b, long o, int x) { U.putInt(b, o, x); }
    public Object getObject(Object b, long o) { return U.getObject(b, o); }
    public long objectFieldOffset(Field f)  { return U.objectFieldOffset(f); }
    public long arrayBaseOffset(Class<?> c) { return U.arrayBaseOffset(c); }
    public void copyMemory(Object sb, long so, Object db, long doff, long n) { U.copyMemory(sb, so, db, doff, n); }
    // sun.misc.Unsafe does not say; these are the architectures jdk.internal.misc.Unsafe answers true for
    public boolean unalignedAccess() {
        String arch = System.getProperty("os.arch");
        return "amd64".equals(arch) || "x86_64".equals(arch) || "aarch64".equals(arch);
    }
}
