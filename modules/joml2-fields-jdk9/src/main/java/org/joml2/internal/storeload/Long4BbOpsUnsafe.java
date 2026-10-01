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

public final class Long4BbOpsUnsafe extends Long4BbOps {

    private static final Long4BbOpsApi API = new Long4BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public LongBuffer storeAbsolute(Long4Impl self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        U.putLong(address + 16L, self.z);
        U.putLong(address + 24L, self.w);
        return buf;
    }
    public Long4 loadAbsolute(Long4Impl self, int index, LongBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.x = U.getLong(address);
        self.y = U.getLong(address + 8L);
        self.z = U.getLong(address + 16L);
        self.w = U.getLong(address + 24L);
        return self;
    }
    public ByteBuffer storeAbsolute(Long4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        U.putLong(address + 16L, self.z);
        U.putLong(address + 24L, self.w);
        return buf;
    }
    public Long4 loadAbsolute(Long4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = U.getLong(address);
        self.y = U.getLong(address + 8L);
        self.z = U.getLong(address + 16L);
        self.w = U.getLong(address + 24L);
        return self;
    }
    public IntBuffer storeAbsolute(Long4Impl self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putInt(address, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        U.putInt(address + 8L, (int) self.z);
        U.putInt(address + 12L, (int) self.w);
        return buf;
    }
    public Long4 loadAbsolute(Long4Impl self, int index, IntBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        self.z = U.getInt(address + 8L);
        self.w = U.getInt(address + 12L);
        return self;
    }
    public ByteBuffer storeIntAbsolute(Long4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeIntAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putInt(address, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        U.putInt(address + 8L, (int) self.z);
        U.putInt(address + 12L, (int) self.w);
        return buf;
    }
    public Long4 loadIntAbsolute(Long4Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadIntAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        self.z = U.getInt(address + 8L);
        self.w = U.getInt(address + 12L);
        return self;
    }
}
