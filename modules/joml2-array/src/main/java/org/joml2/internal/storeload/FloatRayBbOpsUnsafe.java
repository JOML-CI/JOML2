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

public final class FloatRayBbOpsUnsafe extends FloatRayBbOps {

    private static final FloatRayBbOpsApi API = new FloatRayBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeAbsolute(FloatRayImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[5]);
        return buf;
    }
    public FloatRay loadAbsolute(FloatRayImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[5]);
        return buf;
    }
    public FloatRay loadAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatRayImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        return buf;
    }
    public FloatRay loadAbsolute(FloatRayImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        return buf;
    }
    public FloatRay loadDoubleAbsolute(FloatRayImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        return self;
    }
}
