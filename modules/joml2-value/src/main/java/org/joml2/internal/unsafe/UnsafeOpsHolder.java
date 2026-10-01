// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.unsafe;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public final class UnsafeOpsHolder {
    private UnsafeOpsHolder() {}
    /** The resolved backend, or {@code null} when neither Unsafe flavour is usable on this JVM (the library then runs on the API backend). */
    public static final UnsafeOps U = resolve();
    private static UnsafeOps resolve() {
        try {
            UnsafeOps ops = new JdkInternalUnsafeOps();
            probe(ops);
            return ops;
        } catch (Throwable t) {
            // not exported, or linked against another JDK's signatures
        }
        try {
            // sun.misc.Unsafe may load but refuse memory access (JEP 471,
            // --sun-misc-unsafe-memory-access=deny): probe it the same way so
            // that case reports "unavailable" (null -> API backend) instead of
            // failing inside the first impl class initializer.
            UnsafeOps ops = new SunMiscUnsafeOps();
            probe(ops);
            return ops;
        } catch (Throwable t) {
            return null;
        }
    }
    /**
     * Links and executes every {@code jdk.internal.misc.Unsafe} member the backend uses, so
     * that a signature mismatch between the JDK the jar was compiled on and the running one
     * (e.g. {@code arrayBaseOffset} returning {@code int} up to JDK 22 and {@code long} from
     * JDK 25 - a {@code NoSuchMethodError} at first use) or any other {@code LinkageError}
     * selects the {@code sun.misc.Unsafe} fallback here, instead of failing later inside a
     * static initializer such as {@code UnsafeCopy}'s.
     */
    private static void probe(UnsafeOps ops) throws Throwable {
        long floatBase = ops.arrayBaseOffset(float[].class);
        ops.arrayBaseOffset(double[].class);
        ops.arrayBaseOffset(int[].class);
        ops.arrayBaseOffset(long[].class);
        ops.arrayBaseOffset(short[].class);
        ops.arrayBaseOffset(byte[].class);
        long addressOffset = ops.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        ByteBuffer scratch = ByteBuffer.allocateDirect(16);
        long a = ops.getLong(scratch, addressOffset);
        ops.putFloat(a, 1.5f);
        ops.putDouble(a + 8L, 2.5);
        if (ops.getFloat(a) != 1.5f || ops.getDouble(a + 8L) != 2.5) throw new IllegalStateException("float/double round trip");
        float[] heap = new float[2];
        ops.copyMemory(null, a, heap, floatBase, 4L);
        ops.copyMemory(heap, floatBase, heap, floatBase + 4L, 4L);
        if (heap[0] != 1.5f || heap[1] != 1.5f) throw new IllegalStateException("copyMemory round trip");
        ops.putLong(a, 0x0102030405060708L);
        ops.putInt(a + 8L, 0x090a0b0c);
        ops.putShort(a + 12L, (short) 0x0d0e);
        ops.putByte(a + 14L, (byte) 0x0f);
        if (ops.getLong(a) != 0x0102030405060708L || ops.getInt(a + 8L) != 0x090a0b0c
                || ops.getShort(a + 12L) != (short) 0x0d0e || ops.getByte(a + 14L) != (byte) 0x0f)
            throw new IllegalStateException("integral round trip");
        java.lang.ref.Reference.reachabilityFence(scratch);
    }
}
