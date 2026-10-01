// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long2BbOpsUnsafe extends Long2BbOps {

    private static final Long2BbOpsApi API = new Long2BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public LongBuffer storeAbsolute(Long2Impl self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        return buf;
    }
    public Long2 loadAbsolute(Long2Impl self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.x = U.getLong(address);
        self.y = U.getLong(address + 8L);
        return self;
    }
    public ByteBuffer storeAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        return buf;
    }
    public Long2 loadAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = U.getLong(address);
        self.y = U.getLong(address + 8L);
        return self;
    }
    public IntBuffer storeAbsolute(Long2Impl self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putInt(address, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        return buf;
    }
    public Long2 loadAbsolute(Long2Impl self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        return self;
    }
    public ByteBuffer storeIntAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeIntAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putInt(address, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        return buf;
    }
    public Long2 loadIntAbsolute(Long2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadIntAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        return self;
    }
}
