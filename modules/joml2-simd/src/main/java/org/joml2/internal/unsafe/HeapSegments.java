// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.unsafe;

import java.lang.foreign.MemorySegment;
import java.nio.ByteOrder;
import org.joml2.Joml;
import org.joml2.StoreLoadBackend;

import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

/**
 * A heap memory segment's backing array and where its first byte lies in it, read straight off the
 * JDK's segment object ({@code jdk.internal.foreign.HeapMemorySegmentImpl.base} and {@code offset})
 * under the UNSAFE backend: through them a {@code byte[]}-backed heap segment is written and read as
 * fast as a native one, where {@code heapBase()} and {@code address()} cost more than a 4x4 store
 * itself. The fields are resolved once and checked against a segment of a known array; under the
 * API backend (which never touches Unsafe), or on a JDK that lays the class out otherwise, the
 * answers come from {@code heapBase()} and {@code address()} instead.
 */
public final class HeapSegments {
    private HeapSegments() {}
    /** A lane copy reinterprets bytes as elements little-endian, which is native order only here. */
    private static final boolean LANES = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
    private static final long BASE, OFFSET;
    static {
        long base = -1L, offset = -1L;
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && U != null) {
            try {
                Class<?> c = Class.forName("jdk.internal.foreign.HeapMemorySegmentImpl");
                long b = U.objectFieldOffset(c.getDeclaredField("base"));
                long o = U.objectFieldOffset(c.getDeclaredField("offset"));
                byte[] probe = new byte[16];
                MemorySegment s = MemorySegment.ofArray(probe).asSlice(4);
                if (U.getObject(s, b) == probe && U.getLong(s, o) == UnsafeCopy.BYTE_ARRAY_BASE + 4L) {
                    base = b;
                    offset = o;
                }
            } catch (Throwable t) {
                // another layout, or no access: heapBase() and address()
            }
        }
        BASE = base;
        OFFSET = offset;
    }

    /**
     * The {@code byte[]} behind heap segment {@code s} (not a native one), or null: another array
     * type (or, read through {@code heapBase()}, a read-only segment).
     */
    public static byte[] bytes(MemorySegment s) {
        if (BASE >= 0L) return U.getObject(s, BASE) instanceof byte[] b ? b : null;
        return s.heapBase().orElse(null) instanceof byte[] b ? b : null;
    }

    /** The Unsafe offset of heap segment {@code s}'s first byte in its {@link #bytes} array (UNSAFE backend). */
    public static long offset(MemorySegment s) {
        return BASE >= 0L ? U.getLong(s, OFFSET) : UnsafeCopy.BYTE_ARRAY_BASE + s.address();
    }

    /**
     * The {@code byte[]} behind heap segment {@code s} (not a native one) for a lane copy -
     * writable for a {@code store}, on a little-endian platform - or null.
     */
    public static byte[] laneBytes(MemorySegment s, boolean store) {
        return LANES && !(store && s.isReadOnly()) ? bytes(s) : null;
    }

    /** The index of byte {@code offset} of heap segment {@code s} in its {@link #laneBytes} array. */
    public static int index(MemorySegment s, long offset) {
        return (int) (BASE >= 0L ? U.getLong(s, OFFSET) - UnsafeCopy.BYTE_ARRAY_BASE + offset : s.address() + offset);
    }
}
