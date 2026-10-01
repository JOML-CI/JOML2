// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.unsafe;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class UnsafeCopy {
    private UnsafeCopy() {}
    public static final long FLOAT_ARRAY_BASE  = U == null ? 0L : U.arrayBaseOffset(float[].class);
    public static final long DOUBLE_ARRAY_BASE = U == null ? 0L : U.arrayBaseOffset(double[].class);
    public static final long INT_ARRAY_BASE    = U == null ? 0L : U.arrayBaseOffset(int[].class);
    public static final long LONG_ARRAY_BASE   = U == null ? 0L : U.arrayBaseOffset(long[].class);
    public static final long SHORT_ARRAY_BASE  = U == null ? 0L : U.arrayBaseOffset(short[].class);
    public static final long BYTE_ARRAY_BASE   = U == null ? 0L : U.arrayBaseOffset(byte[].class);
    /** 8-byte accesses at 4-byte-aligned addresses are safe and fast here (x86-64, AArch64): a block
     *  of floats is copied as long pairs (a float[]'s data starts 4 bytes off an 8-byte boundary
     *  under compact object headers). */
    public static final boolean UNALIGNED_LONGS = U != null && U.unalignedAccess();
    public static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U == null ? 0L : U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    /** The offset of {@code ByteBuffer.hb}, the array behind a heap buffer - whose {@code address} is
     *  then the Unsafe offset of its first byte in it - or -1 on a JDK that lays them out otherwise. */
    public static final long BB_HB_OFFSET = heapBufferArrayOffset();

    private static long heapBufferArrayOffset() {
        if (U == null) return -1L;
        try {
            long hb = U.objectFieldOffset(ByteBuffer.class.getDeclaredField("hb"));
            byte[] probe = new byte[16];
            ByteBuffer b = ByteBuffer.wrap(probe, 4, 8).slice();
            return U.getObject(b, hb) == probe && U.getLong(b, BB_ADDRESS_OFFSET) == BYTE_ARRAY_BASE + 4L ? hb : -1L;
        } catch (Throwable t) {
            return -1L;
        }
    }

    /** The {@code byte[]} behind heap buffer {@code b} (not a direct one), or null where the layout is unknown. */
    public static byte[] heapBytes(ByteBuffer b) {
        if (BB_HB_OFFSET < 0L) return null;
        Object a = U.getObject(b, BB_HB_OFFSET);
        return a instanceof byte[] ? (byte[]) a : null;
    }
}
