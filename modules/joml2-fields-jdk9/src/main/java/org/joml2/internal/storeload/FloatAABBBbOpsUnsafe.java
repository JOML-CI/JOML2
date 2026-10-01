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

public final class FloatAABBBbOpsUnsafe extends FloatAABBBbOps {
    private static final FloatAABBBbOpsApi API = new FloatAABBBbOpsApi();

    public FloatBuffer storeAbsolute(FloatAABBImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.minX);
        _U.putFloat(address + 4L, self.minY);
        _U.putFloat(address + 8L, self.minZ);
        _U.putFloat(address + 12L, self.maxX);
        _U.putFloat(address + 16L, self.maxY);
        _U.putFloat(address + 20L, self.maxZ);
        return buf;
    }

    public FloatAABB loadAbsolute(FloatAABBImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.minX = _U.getFloat(address);
        self.minY = _U.getFloat(address + 4L);
        self.minZ = _U.getFloat(address + 8L);
        self.maxX = _U.getFloat(address + 12L);
        self.maxY = _U.getFloat(address + 16L);
        self.maxZ = _U.getFloat(address + 20L);
        return self;
    }

    public ByteBuffer storeAbsolute(FloatAABBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.minX);
        _U.putFloat(address + 4L, self.minY);
        _U.putFloat(address + 8L, self.minZ);
        _U.putFloat(address + 12L, self.maxX);
        _U.putFloat(address + 16L, self.maxY);
        _U.putFloat(address + 20L, self.maxZ);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(FloatAABBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(bytes, address, self.minX);
        _U.putFloat(bytes, address + 4L, self.minY);
        _U.putFloat(bytes, address + 8L, self.minZ);
        _U.putFloat(bytes, address + 12L, self.maxX);
        _U.putFloat(bytes, address + 16L, self.maxY);
        _U.putFloat(bytes, address + 20L, self.maxZ);
        return buf;
    }

    public FloatAABB loadAbsolute(FloatAABBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.minX = _U.getFloat(address);
        self.minY = _U.getFloat(address + 4L);
        self.minZ = _U.getFloat(address + 8L);
        self.maxX = _U.getFloat(address + 12L);
        self.maxY = _U.getFloat(address + 16L);
        self.maxZ = _U.getFloat(address + 20L);
        return self;
    }

    private static FloatAABB loadAbsoluteElse(FloatAABBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.minX = _U.getFloat(bytes, address);
        self.minY = _U.getFloat(bytes, address + 4L);
        self.minZ = _U.getFloat(bytes, address + 8L);
        self.maxX = _U.getFloat(bytes, address + 12L);
        self.maxY = _U.getFloat(bytes, address + 16L);
        self.maxZ = _U.getFloat(bytes, address + 20L);
        return self;
    }

    public DoubleBuffer storeAbsolute(FloatAABBImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.minX);
        _U.putDouble(address + 8L, self.minY);
        _U.putDouble(address + 16L, self.minZ);
        _U.putDouble(address + 24L, self.maxX);
        _U.putDouble(address + 32L, self.maxY);
        _U.putDouble(address + 40L, self.maxZ);
        return buf;
    }

    public FloatAABB loadAbsolute(FloatAABBImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.minX = (float) _U.getDouble(address);
        self.minY = (float) _U.getDouble(address + 8L);
        self.minZ = (float) _U.getDouble(address + 16L);
        self.maxX = (float) _U.getDouble(address + 24L);
        self.maxY = (float) _U.getDouble(address + 32L);
        self.maxZ = (float) _U.getDouble(address + 40L);
        return self;
    }

    public ByteBuffer storeDoubleAbsolute(FloatAABBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.minX);
        _U.putDouble(address + 8L, self.minY);
        _U.putDouble(address + 16L, self.minZ);
        _U.putDouble(address + 24L, self.maxX);
        _U.putDouble(address + 32L, self.maxY);
        _U.putDouble(address + 40L, self.maxZ);
        return buf;
    }

    private static ByteBuffer storeDoubleAbsoluteElse(FloatAABBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(bytes, address, self.minX);
        _U.putDouble(bytes, address + 8L, self.minY);
        _U.putDouble(bytes, address + 16L, self.minZ);
        _U.putDouble(bytes, address + 24L, self.maxX);
        _U.putDouble(bytes, address + 32L, self.maxY);
        _U.putDouble(bytes, address + 40L, self.maxZ);
        return buf;
    }

    public FloatAABB loadDoubleAbsolute(FloatAABBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.minX = (float) _U.getDouble(address);
        self.minY = (float) _U.getDouble(address + 8L);
        self.minZ = (float) _U.getDouble(address + 16L);
        self.maxX = (float) _U.getDouble(address + 24L);
        self.maxY = (float) _U.getDouble(address + 32L);
        self.maxZ = (float) _U.getDouble(address + 40L);
        return self;
    }

    private static FloatAABB loadDoubleAbsoluteElse(FloatAABBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadDoubleAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.minX = (float) _U.getDouble(bytes, address);
        self.minY = (float) _U.getDouble(bytes, address + 8L);
        self.minZ = (float) _U.getDouble(bytes, address + 16L);
        self.maxX = (float) _U.getDouble(bytes, address + 24L);
        self.maxY = (float) _U.getDouble(bytes, address + 32L);
        self.maxZ = (float) _U.getDouble(bytes, address + 40L);
        return self;
    }
}
