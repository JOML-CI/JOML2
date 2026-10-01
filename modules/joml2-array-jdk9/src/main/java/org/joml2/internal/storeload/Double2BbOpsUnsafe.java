// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2BbOpsUnsafe extends Double2BbOps {

    private static final Double2BbOpsApi API = new Double2BbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DoubleBuffer storeAbsolute(Double2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        return buf;
    }
    public Double2 loadAbsolute(Double2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8L);
        return self;
    }
    public ByteBuffer storeAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        return buf;
    }
    public Double2 loadAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8L);
        return self;
    }
    public FloatBuffer storeAbsolute(Double2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        return buf;
    }
    public Double2 loadAbsolute(Double2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        return self;
    }
    public ByteBuffer storeFloatAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        return buf;
    }
    public Double2 loadFloatAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        return self;
    }
}
