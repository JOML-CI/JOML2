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

public final class FloatAABBBbOpsUnsafe extends FloatAABBBbOps {

    private static final FloatAABBBbOpsApi API = new FloatAABBBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeAbsolute(FloatAABBImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.minX);
        U.putFloat(address + 4L, self.minY);
        U.putFloat(address + 8L, self.minZ);
        U.putFloat(address + 12L, self.maxX);
        U.putFloat(address + 16L, self.maxY);
        U.putFloat(address + 20L, self.maxZ);
        return buf;
    }
    public FloatAABB loadAbsolute(FloatAABBImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.minZ = U.getFloat(address + 8L);
        self.maxX = U.getFloat(address + 12L);
        self.maxY = U.getFloat(address + 16L);
        self.maxZ = U.getFloat(address + 20L);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatAABBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.minX);
        U.putFloat(address + 4L, self.minY);
        U.putFloat(address + 8L, self.minZ);
        U.putFloat(address + 12L, self.maxX);
        U.putFloat(address + 16L, self.maxY);
        U.putFloat(address + 20L, self.maxZ);
        return buf;
    }
    public FloatAABB loadAbsolute(FloatAABBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.minX = U.getFloat(address);
        self.minY = U.getFloat(address + 4L);
        self.minZ = U.getFloat(address + 8L);
        self.maxX = U.getFloat(address + 12L);
        self.maxY = U.getFloat(address + 16L);
        self.maxZ = U.getFloat(address + 20L);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatAABBImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.minZ);
        U.putDouble(address + 24L, self.maxX);
        U.putDouble(address + 32L, self.maxY);
        U.putDouble(address + 40L, self.maxZ);
        return buf;
    }
    public FloatAABB loadAbsolute(FloatAABBImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.minX = (float) U.getDouble(address);
        self.minY = (float) U.getDouble(address + 8L);
        self.minZ = (float) U.getDouble(address + 16L);
        self.maxX = (float) U.getDouble(address + 24L);
        self.maxY = (float) U.getDouble(address + 32L);
        self.maxZ = (float) U.getDouble(address + 40L);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatAABBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.minX);
        U.putDouble(address + 8L, self.minY);
        U.putDouble(address + 16L, self.minZ);
        U.putDouble(address + 24L, self.maxX);
        U.putDouble(address + 32L, self.maxY);
        U.putDouble(address + 40L, self.maxZ);
        return buf;
    }
    public FloatAABB loadDoubleAbsolute(FloatAABBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.minX = (float) U.getDouble(address);
        self.minY = (float) U.getDouble(address + 8L);
        self.minZ = (float) U.getDouble(address + 16L);
        self.maxX = (float) U.getDouble(address + 24L);
        self.maxY = (float) U.getDouble(address + 32L);
        self.maxZ = (float) U.getDouble(address + 40L);
        return self;
    }
}
