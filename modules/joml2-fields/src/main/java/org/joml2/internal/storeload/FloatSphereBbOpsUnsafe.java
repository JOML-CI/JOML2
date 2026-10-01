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

public final class FloatSphereBbOpsUnsafe extends FloatSphereBbOps {

    private static final FloatSphereBbOpsApi API = new FloatSphereBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeAbsolute(FloatSphereImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.x);
        U.putFloat(address + 4L, self.y);
        U.putFloat(address + 8L, self.z);
        U.putFloat(address + 12L, self.r);
        return buf;
    }
    public FloatSphere loadAbsolute(FloatSphereImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.x = U.getFloat(address);
        self.y = U.getFloat(address + 4L);
        self.z = U.getFloat(address + 8L);
        self.r = U.getFloat(address + 12L);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatSphereImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.x);
        U.putFloat(address + 4L, self.y);
        U.putFloat(address + 8L, self.z);
        U.putFloat(address + 12L, self.r);
        return buf;
    }
    public FloatSphere loadAbsolute(FloatSphereImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = U.getFloat(address);
        self.y = U.getFloat(address + 4L);
        self.z = U.getFloat(address + 8L);
        self.r = U.getFloat(address + 12L);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatSphereImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.x);
        U.putDouble(address + 8L, self.y);
        U.putDouble(address + 16L, self.z);
        U.putDouble(address + 24L, self.r);
        return buf;
    }
    public FloatSphere loadAbsolute(FloatSphereImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.x = (float) U.getDouble(address);
        self.y = (float) U.getDouble(address + 8L);
        self.z = (float) U.getDouble(address + 16L);
        self.r = (float) U.getDouble(address + 24L);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatSphereImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.x);
        U.putDouble(address + 8L, self.y);
        U.putDouble(address + 16L, self.z);
        U.putDouble(address + 24L, self.r);
        return buf;
    }
    public FloatSphere loadDoubleAbsolute(FloatSphereImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.x = (float) U.getDouble(address);
        self.y = (float) U.getDouble(address + 8L);
        self.z = (float) U.getDouble(address + 16L);
        self.r = (float) U.getDouble(address + 24L);
        return self;
    }
}
