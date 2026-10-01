// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2BbOpsUnsafe extends Double2BbOps {
    private static final Double2BbOpsApi API = new Double2BbOpsApi();

    public DoubleBuffer storeAbsolute(Double2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.x);
        U.putDouble(address + 8L, self.y);
        return buf;
    }

    public Double2 loadAbsolute(Double2Impl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.x = U.getDouble(address);
        self.y = U.getDouble(address + 8L);
        return self;
    }

    public ByteBuffer storeAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.x);
        U.putDouble(address + 8L, self.y);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(Double2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putDouble(bytes, address, self.x);
        U.putDouble(bytes, address + 8L, self.y);
        return buf;
    }

    public Double2 loadAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getDouble(address);
        self.y = U.getDouble(address + 8L);
        return self;
    }

    private static Double2 loadAbsoluteElse(Double2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getDouble(bytes, address);
        self.y = U.getDouble(bytes, address + 8L);
        return self;
    }

    public FloatBuffer storeAbsolute(Double2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.x);
        U.putFloat(address + 4L, (float) self.y);
        return buf;
    }

    public Double2 loadAbsolute(Double2Impl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.x = U.getFloat(address);
        self.y = U.getFloat(address + 4L);
        return self;
    }

    public ByteBuffer storeFloatAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.x);
        U.putFloat(address + 4L, (float) self.y);
        return buf;
    }

    private static ByteBuffer storeFloatAbsoluteElse(Double2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putFloat(bytes, address, (float) self.x);
        U.putFloat(bytes, address + 4L, (float) self.y);
        return buf;
    }

    public Double2 loadFloatAbsolute(Double2Impl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getFloat(address);
        self.y = U.getFloat(address + 4L);
        return self;
    }

    private static Double2 loadFloatAbsoluteElse(Double2Impl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.x = U.getFloat(bytes, address);
        self.y = U.getFloat(bytes, address + 4L);
        return self;
    }
}
