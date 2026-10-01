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

public final class DoubleRectBbOpsUnsafe extends DoubleRectBbOps {
    private static final DoubleRectBbOpsApi API = new DoubleRectBbOpsApi();

    public DoubleBuffer storeAbsolute(DoubleRectImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.maxX);
        U.putDouble(address + 24L, self.maxY);
        return buf;
    }

    public DoubleRect loadAbsolute(DoubleRectImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.minX = U.getDouble(address);
        self.minY = U.getDouble(address + 8L);
        self.maxX = U.getDouble(address + 16L);
        self.maxY = U.getDouble(address + 24L);
        return self;
    }

    public ByteBuffer storeAbsolute(DoubleRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.maxX);
        U.putDouble(address + 24L, self.maxY);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(DoubleRectImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putDouble(bytes, address, self.minX);
        U.putDouble(bytes, address + 8L, self.minY);
        U.putDouble(bytes, address + 16L, self.maxX);
        U.putDouble(bytes, address + 24L, self.maxY);
        return buf;
    }

    public DoubleRect loadAbsolute(DoubleRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.minX = U.getDouble(address);
        self.minY = U.getDouble(address + 8L);
        self.maxX = U.getDouble(address + 16L);
        self.maxY = U.getDouble(address + 24L);
        return self;
    }

    private static DoubleRect loadAbsoluteElse(DoubleRectImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.minX = U.getDouble(bytes, address);
        self.minY = U.getDouble(bytes, address + 8L);
        self.maxX = U.getDouble(bytes, address + 16L);
        self.maxY = U.getDouble(bytes, address + 24L);
        return self;
    }

    public FloatBuffer storeAbsolute(DoubleRectImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.minX);
        U.putFloat(address + 4L, (float) self.minY);
        U.putFloat(address + 8L, (float) self.maxX);
        U.putFloat(address + 12L, (float) self.maxY);
        return buf;
    }

    public DoubleRect loadAbsolute(DoubleRectImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.maxX = U.getFloat(address + 8L);
        self.maxY = U.getFloat(address + 12L);
        return self;
    }

    public ByteBuffer storeFloatAbsolute(DoubleRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.minX);
        U.putFloat(address + 4L, (float) self.minY);
        U.putFloat(address + 8L, (float) self.maxX);
        U.putFloat(address + 12L, (float) self.maxY);
        return buf;
    }

    private static ByteBuffer storeFloatAbsoluteElse(DoubleRectImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putFloat(bytes, address, (float) self.minX);
        U.putFloat(bytes, address + 4L, (float) self.minY);
        U.putFloat(bytes, address + 8L, (float) self.maxX);
        U.putFloat(bytes, address + 12L, (float) self.maxY);
        return buf;
    }

    public DoubleRect loadFloatAbsolute(DoubleRectImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.maxX = U.getFloat(address + 8L);
        self.maxY = U.getFloat(address + 12L);
        return self;
    }

    private static DoubleRect loadFloatAbsoluteElse(DoubleRectImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.minX = U.getFloat(bytes, address);
        self.minY = U.getFloat(bytes, address + 4L);
        self.maxX = U.getFloat(bytes, address + 8L);
        self.maxY = U.getFloat(bytes, address + 12L);
        return self;
    }
}
