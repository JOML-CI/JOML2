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

public final class DoublePlaneBbOpsUnsafe extends DoublePlaneBbOps {
    private static final DoublePlaneBbOpsApi API = new DoublePlaneBbOpsApi();

    public DoubleBuffer storeAbsolute(DoublePlaneImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.a);
        U.putDouble(address + 8L, self.b);
        U.putDouble(address + 16L, self.c);
        U.putDouble(address + 24L, self.d);
        return buf;
    }

    public DoublePlane loadAbsolute(DoublePlaneImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.a = U.getDouble(address);
        self.b = U.getDouble(address + 8L);
        self.c = U.getDouble(address + 16L);
        self.d = U.getDouble(address + 24L);
        return self;
    }

    public ByteBuffer storeAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.a);
        U.putDouble(address + 8L, self.b);
        U.putDouble(address + 16L, self.c);
        U.putDouble(address + 24L, self.d);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(DoublePlaneImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putDouble(bytes, address, self.a);
        U.putDouble(bytes, address + 8L, self.b);
        U.putDouble(bytes, address + 16L, self.c);
        U.putDouble(bytes, address + 24L, self.d);
        return buf;
    }

    public DoublePlane loadAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.a = U.getDouble(address);
        self.b = U.getDouble(address + 8L);
        self.c = U.getDouble(address + 16L);
        self.d = U.getDouble(address + 24L);
        return self;
    }

    private static DoublePlane loadAbsoluteElse(DoublePlaneImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.a = U.getDouble(bytes, address);
        self.b = U.getDouble(bytes, address + 8L);
        self.c = U.getDouble(bytes, address + 16L);
        self.d = U.getDouble(bytes, address + 24L);
        return self;
    }

    public FloatBuffer storeAbsolute(DoublePlaneImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, (float) self.a);
        U.putFloat(address + 4L, (float) self.b);
        U.putFloat(address + 8L, (float) self.c);
        U.putFloat(address + 12L, (float) self.d);
        return buf;
    }

    public DoublePlane loadAbsolute(DoublePlaneImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.a = U.getFloat(address);
        self.b = U.getFloat(address + 4L);
        self.c = U.getFloat(address + 8L);
        self.d = U.getFloat(address + 12L);
        return self;
    }

    public ByteBuffer storeFloatAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, (float) self.a);
        U.putFloat(address + 4L, (float) self.b);
        U.putFloat(address + 8L, (float) self.c);
        U.putFloat(address + 12L, (float) self.d);
        return buf;
    }

    private static ByteBuffer storeFloatAbsoluteElse(DoublePlaneImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        U.putFloat(bytes, address, (float) self.a);
        U.putFloat(bytes, address + 4L, (float) self.b);
        U.putFloat(bytes, address + 8L, (float) self.c);
        U.putFloat(bytes, address + 12L, (float) self.d);
        return buf;
    }

    public DoublePlane loadFloatAbsolute(DoublePlaneImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.a = U.getFloat(address);
        self.b = U.getFloat(address + 4L);
        self.c = U.getFloat(address + 8L);
        self.d = U.getFloat(address + 12L);
        return self;
    }

    private static DoublePlane loadFloatAbsoluteElse(DoublePlaneImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadFloatAbsolute(self, index, buf);
        long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.a = U.getFloat(bytes, address);
        self.b = U.getFloat(bytes, address + 4L);
        self.c = U.getFloat(bytes, address + 8L);
        self.d = U.getFloat(bytes, address + 12L);
        return self;
    }
}
