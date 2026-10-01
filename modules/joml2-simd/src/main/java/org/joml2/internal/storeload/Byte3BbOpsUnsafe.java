// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Byte3BbOpsUnsafe implements Byte3BbOps {

    private static final Byte3BbOpsApi API = new Byte3BbOpsApi();
    private static final Byte3RawOpsUnsafe RAW = new Byte3RawOpsUnsafe();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public ByteBuffer storeAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        RAW.storeUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Byte3 loadAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        return RAW.loadUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
    }
    public ShortBuffer storeAbsolute(Byte3Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        RAW.storeShortUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + (long) index * 2);
        return buf;
    }
    public Byte3 loadAbsolute(Byte3Impl self, int index, ShortBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        return RAW.loadShortUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + (long) index * 2);
    }
    public ByteBuffer storeShortAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeShortAbsolute(self, index, buf);
        RAW.storeShortUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
        return buf;
    }
    public Byte3 loadShortAbsolute(Byte3Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadShortAbsolute(self, index, buf);
        return RAW.loadShortUnsafe(self, U.getLong(buf, BB_ADDRESS_OFFSET) + index);
    }
}
