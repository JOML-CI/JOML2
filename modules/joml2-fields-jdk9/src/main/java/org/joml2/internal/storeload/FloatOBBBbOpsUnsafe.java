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

public final class FloatOBBBbOpsUnsafe extends FloatOBBBbOps {
    private static final FloatOBBBbOpsApi API = new FloatOBBBbOpsApi();
    private static final FloatOBBRawOpsUnsafe RAW = new FloatOBBRawOpsUnsafe();

    public FloatBuffer storeAbsolute(FloatOBBImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, self.cX);
        _U.putFloat(address + 4L, self.cY);
        _U.putFloat(address + 8L, self.cZ);
        _U.putFloat(address + 12L, self.uXx);
        _U.putFloat(address + 16L, self.uXy);
        _U.putFloat(address + 20L, self.uXz);
        _U.putFloat(address + 24L, self.uYx);
        _U.putFloat(address + 28L, self.uYy);
        _U.putFloat(address + 32L, self.uYz);
        _U.putFloat(address + 36L, self.uZx);
        _U.putFloat(address + 40L, self.uZy);
        _U.putFloat(address + 44L, self.uZz);
        _U.putFloat(address + 48L, self.hsX);
        _U.putFloat(address + 52L, self.hsY);
        _U.putFloat(address + 56L, self.hsZ);
        return buf;
    }

    public FloatOBB loadAbsolute(FloatOBBImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        self.cX = _U.getFloat(address);
        self.cY = _U.getFloat(address + 4L);
        self.cZ = _U.getFloat(address + 8L);
        self.uXx = _U.getFloat(address + 12L);
        self.uXy = _U.getFloat(address + 16L);
        self.uXz = _U.getFloat(address + 20L);
        self.uYx = _U.getFloat(address + 24L);
        self.uYy = _U.getFloat(address + 28L);
        self.uYz = _U.getFloat(address + 32L);
        self.uZx = _U.getFloat(address + 36L);
        self.uZy = _U.getFloat(address + 40L);
        self.uZz = _U.getFloat(address + 44L);
        self.hsX = _U.getFloat(address + 48L);
        self.hsY = _U.getFloat(address + 52L);
        self.hsZ = _U.getFloat(address + 56L);
        return self;
    }

    public ByteBuffer storeAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, self.cX);
        _U.putFloat(address + 4L, self.cY);
        _U.putFloat(address + 8L, self.cZ);
        _U.putFloat(address + 12L, self.uXx);
        _U.putFloat(address + 16L, self.uXy);
        _U.putFloat(address + 20L, self.uXz);
        _U.putFloat(address + 24L, self.uYx);
        _U.putFloat(address + 28L, self.uYy);
        _U.putFloat(address + 32L, self.uYz);
        _U.putFloat(address + 36L, self.uZx);
        _U.putFloat(address + 40L, self.uZy);
        _U.putFloat(address + 44L, self.uZz);
        _U.putFloat(address + 48L, self.hsX);
        _U.putFloat(address + 52L, self.hsY);
        _U.putFloat(address + 56L, self.hsZ);
        return buf;
    }

    private static ByteBuffer storeAbsoluteElse(FloatOBBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        RAW.storeHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public FloatOBB loadAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.cX = _U.getFloat(address);
        self.cY = _U.getFloat(address + 4L);
        self.cZ = _U.getFloat(address + 8L);
        self.uXx = _U.getFloat(address + 12L);
        self.uXy = _U.getFloat(address + 16L);
        self.uXz = _U.getFloat(address + 20L);
        self.uYx = _U.getFloat(address + 24L);
        self.uYy = _U.getFloat(address + 28L);
        self.uYz = _U.getFloat(address + 32L);
        self.uZx = _U.getFloat(address + 36L);
        self.uZy = _U.getFloat(address + 40L);
        self.uZz = _U.getFloat(address + 44L);
        self.hsX = _U.getFloat(address + 48L);
        self.hsY = _U.getFloat(address + 52L);
        self.hsZ = _U.getFloat(address + 56L);
        return self;
    }

    private static FloatOBB loadAbsoluteElse(FloatOBBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.cX = _U.getFloat(bytes, address);
        self.cY = _U.getFloat(bytes, address + 4L);
        self.cZ = _U.getFloat(bytes, address + 8L);
        self.uXx = _U.getFloat(bytes, address + 12L);
        self.uXy = _U.getFloat(bytes, address + 16L);
        self.uXz = _U.getFloat(bytes, address + 20L);
        self.uYx = _U.getFloat(bytes, address + 24L);
        self.uYy = _U.getFloat(bytes, address + 28L);
        self.uYz = _U.getFloat(bytes, address + 32L);
        self.uZx = _U.getFloat(bytes, address + 36L);
        self.uZy = _U.getFloat(bytes, address + 40L);
        self.uZz = _U.getFloat(bytes, address + 44L);
        self.hsX = _U.getFloat(bytes, address + 48L);
        self.hsY = _U.getFloat(bytes, address + 52L);
        self.hsZ = _U.getFloat(bytes, address + 56L);
        return self;
    }

    public DoubleBuffer storeAbsolute(FloatOBBImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        _U.putDouble(address, self.cX);
        _U.putDouble(address + 8L, self.cY);
        _U.putDouble(address + 16L, self.cZ);
        _U.putDouble(address + 24L, self.uXx);
        _U.putDouble(address + 32L, self.uXy);
        _U.putDouble(address + 40L, self.uXz);
        _U.putDouble(address + 48L, self.uYx);
        _U.putDouble(address + 56L, self.uYy);
        _U.putDouble(address + 64L, self.uYz);
        _U.putDouble(address + 72L, self.uZx);
        _U.putDouble(address + 80L, self.uZy);
        _U.putDouble(address + 88L, self.uZz);
        _U.putDouble(address + 96L, self.hsX);
        _U.putDouble(address + 104L, self.hsY);
        _U.putDouble(address + 112L, self.hsZ);
        return buf;
    }

    public FloatOBB loadAbsolute(FloatOBBImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.cX = (float) _U.getDouble(address);
        self.cY = (float) _U.getDouble(address + 8L);
        self.cZ = (float) _U.getDouble(address + 16L);
        self.uXx = (float) _U.getDouble(address + 24L);
        self.uXy = (float) _U.getDouble(address + 32L);
        self.uXz = (float) _U.getDouble(address + 40L);
        self.uYx = (float) _U.getDouble(address + 48L);
        self.uYy = (float) _U.getDouble(address + 56L);
        self.uYz = (float) _U.getDouble(address + 64L);
        self.uZx = (float) _U.getDouble(address + 72L);
        self.uZy = (float) _U.getDouble(address + 80L);
        self.uZz = (float) _U.getDouble(address + 88L);
        self.hsX = (float) _U.getDouble(address + 96L);
        self.hsY = (float) _U.getDouble(address + 104L);
        self.hsZ = (float) _U.getDouble(address + 112L);
        return self;
    }

    public ByteBuffer storeDoubleAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putDouble(address, self.cX);
        _U.putDouble(address + 8L, self.cY);
        _U.putDouble(address + 16L, self.cZ);
        _U.putDouble(address + 24L, self.uXx);
        _U.putDouble(address + 32L, self.uXy);
        _U.putDouble(address + 40L, self.uXz);
        _U.putDouble(address + 48L, self.uYx);
        _U.putDouble(address + 56L, self.uYy);
        _U.putDouble(address + 64L, self.uYz);
        _U.putDouble(address + 72L, self.uZx);
        _U.putDouble(address + 80L, self.uZy);
        _U.putDouble(address + 88L, self.uZz);
        _U.putDouble(address + 96L, self.hsX);
        _U.putDouble(address + 104L, self.hsY);
        _U.putDouble(address + 112L, self.hsZ);
        return buf;
    }

    private static ByteBuffer storeDoubleAbsoluteElse(FloatOBBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeDoubleAbsolute(self, index, buf);
        RAW.storeDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public FloatOBB loadDoubleAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadDoubleAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.cX = (float) _U.getDouble(address);
        self.cY = (float) _U.getDouble(address + 8L);
        self.cZ = (float) _U.getDouble(address + 16L);
        self.uXx = (float) _U.getDouble(address + 24L);
        self.uXy = (float) _U.getDouble(address + 32L);
        self.uXz = (float) _U.getDouble(address + 40L);
        self.uYx = (float) _U.getDouble(address + 48L);
        self.uYy = (float) _U.getDouble(address + 56L);
        self.uYz = (float) _U.getDouble(address + 64L);
        self.uZx = (float) _U.getDouble(address + 72L);
        self.uZy = (float) _U.getDouble(address + 80L);
        self.uZz = (float) _U.getDouble(address + 88L);
        self.hsX = (float) _U.getDouble(address + 96L);
        self.hsY = (float) _U.getDouble(address + 104L);
        self.hsZ = (float) _U.getDouble(address + 112L);
        return self;
    }

    private static FloatOBB loadDoubleAbsoluteElse(FloatOBBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadDoubleAbsolute(self, index, buf);
        return RAW.loadDoubleHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }
}
