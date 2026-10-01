// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

public final class JdkInternalUnsafeOps extends UnsafeOps {
    private static final jdk.internal.misc.Unsafe U = jdk.internal.misc.Unsafe.getUnsafe();
    // The members whose descriptor differs between JDKs are bound to what the running JDK
    // declares, not to what the compiling one did: arrayBaseOffset returns int up to JDK 24 and
    // long from 25; getReference is getObject up to JDK 11 (and getObject is gone from 23).
    // A static final MethodHandle compiles to the direct call.
    private static final MethodHandle ARRAY_BASE_OFFSET = first(
            find("arrayBaseOffset", MethodType.methodType(long.class, Class.class)),
            find("arrayBaseOffset", MethodType.methodType(int.class, Class.class)))
            .asType(MethodType.methodType(long.class, jdk.internal.misc.Unsafe.class, Class.class));
    private static final MethodHandle GET_REFERENCE = first(
            find("getReference", MethodType.methodType(Object.class, Object.class, long.class)),
            find("getObject", MethodType.methodType(Object.class, Object.class, long.class)));
    private static MethodHandle find(String name, MethodType type) {
        try {
            return MethodHandles.lookup().findVirtual(jdk.internal.misc.Unsafe.class, name, type);
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
    private static MethodHandle first(MethodHandle a, MethodHandle b) {
        if (a != null) return a;
        if (b != null) return b;
        throw new IllegalStateException("jdk.internal.misc.Unsafe declares neither form");
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
    public Object getObject(Object b, long o) {
        try {
            return (Object) GET_REFERENCE.invokeExact(U, b, o);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable t) {
            throw new IllegalStateException(t);
        }
    }
    public long objectFieldOffset(Field f)  { return U.objectFieldOffset(f); }
    public long arrayBaseOffset(Class<?> c) {
        try {
            return (long) ARRAY_BASE_OFFSET.invokeExact(U, c);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable t) {
            throw new IllegalStateException(t);
        }
    }
    public void copyMemory(Object sb, long so, Object db, long doff, long n) { U.copyMemory(sb, so, db, doff, n); }
    public boolean unalignedAccess()        { return U.unalignedAccess(); }
}
