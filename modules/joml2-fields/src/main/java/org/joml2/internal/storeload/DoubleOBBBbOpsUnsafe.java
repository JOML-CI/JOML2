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

public final class DoubleOBBBbOpsUnsafe extends DoubleOBBBbOps {
    private static final DoubleOBBBbOpsApi API = new DoubleOBBBbOpsApi();
    private static final DoubleOBBRawOpsUnsafe RAW = new DoubleOBBRawOpsUnsafe();

    public DoubleBuffer storeAbsolute(DoubleOBBImpl self, int index, DoubleBuffer buf) {
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

    public DoubleOBB loadAbsolute(DoubleOBBImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
        self.cX = _U.getDouble(address);
        self.cY = _U.getDouble(address + 8L);
        self.cZ = _U.getDouble(address + 16L);
        self.uXx = _U.getDouble(address + 24L);
        self.uXy = _U.getDouble(address + 32L);
        self.uXz = _U.getDouble(address + 40L);
        self.uYx = _U.getDouble(address + 48L);
        self.uYy = _U.getDouble(address + 56L);
        self.uYz = _U.getDouble(address + 64L);
        self.uZx = _U.getDouble(address + 72L);
        self.uZy = _U.getDouble(address + 80L);
        self.uZz = _U.getDouble(address + 88L);
        self.hsX = _U.getDouble(address + 96L);
        self.hsY = _U.getDouble(address + 104L);
        self.hsZ = _U.getDouble(address + 112L);
        return self;
    }

    public ByteBuffer storeAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
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

    private static ByteBuffer storeAbsoluteElse(DoubleOBBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeAbsolute(self, index, buf);
        RAW.storeHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public DoubleOBB loadAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.cX = _U.getDouble(address);
        self.cY = _U.getDouble(address + 8L);
        self.cZ = _U.getDouble(address + 16L);
        self.uXx = _U.getDouble(address + 24L);
        self.uXy = _U.getDouble(address + 32L);
        self.uXz = _U.getDouble(address + 40L);
        self.uYx = _U.getDouble(address + 48L);
        self.uYy = _U.getDouble(address + 56L);
        self.uYz = _U.getDouble(address + 64L);
        self.uZx = _U.getDouble(address + 72L);
        self.uZy = _U.getDouble(address + 80L);
        self.uZz = _U.getDouble(address + 88L);
        self.hsX = _U.getDouble(address + 96L);
        self.hsY = _U.getDouble(address + 104L);
        self.hsZ = _U.getDouble(address + 112L);
        return self;
    }

    private static DoubleOBB loadAbsoluteElse(DoubleOBBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        self.cX = _U.getDouble(bytes, address);
        self.cY = _U.getDouble(bytes, address + 8L);
        self.cZ = _U.getDouble(bytes, address + 16L);
        self.uXx = _U.getDouble(bytes, address + 24L);
        self.uXy = _U.getDouble(bytes, address + 32L);
        self.uXz = _U.getDouble(bytes, address + 40L);
        self.uYx = _U.getDouble(bytes, address + 48L);
        self.uYy = _U.getDouble(bytes, address + 56L);
        self.uYz = _U.getDouble(bytes, address + 64L);
        self.uZx = _U.getDouble(bytes, address + 72L);
        self.uZy = _U.getDouble(bytes, address + 80L);
        self.uZz = _U.getDouble(bytes, address + 88L);
        self.hsX = _U.getDouble(bytes, address + 96L);
        self.hsY = _U.getDouble(bytes, address + 104L);
        self.hsZ = _U.getDouble(bytes, address + 112L);
        return self;
    }

    public FloatBuffer storeAbsolute(DoubleOBBImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
        _U.putFloat(address, (float) self.cX);
        _U.putFloat(address + 4L, (float) self.cY);
        _U.putFloat(address + 8L, (float) self.cZ);
        _U.putFloat(address + 12L, (float) self.uXx);
        _U.putFloat(address + 16L, (float) self.uXy);
        _U.putFloat(address + 20L, (float) self.uXz);
        _U.putFloat(address + 24L, (float) self.uYx);
        _U.putFloat(address + 28L, (float) self.uYy);
        _U.putFloat(address + 32L, (float) self.uYz);
        _U.putFloat(address + 36L, (float) self.uZx);
        _U.putFloat(address + 40L, (float) self.uZy);
        _U.putFloat(address + 44L, (float) self.uZz);
        _U.putFloat(address + 48L, (float) self.hsX);
        _U.putFloat(address + 52L, (float) self.hsY);
        _U.putFloat(address + 56L, (float) self.hsZ);
        return buf;
    }

    public DoubleOBB loadAbsolute(DoubleOBBImpl self, int index, FloatBuffer buf) {
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

    public ByteBuffer storeFloatAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
        _U.putFloat(address, (float) self.cX);
        _U.putFloat(address + 4L, (float) self.cY);
        _U.putFloat(address + 8L, (float) self.cZ);
        _U.putFloat(address + 12L, (float) self.uXx);
        _U.putFloat(address + 16L, (float) self.uXy);
        _U.putFloat(address + 20L, (float) self.uXz);
        _U.putFloat(address + 24L, (float) self.uYx);
        _U.putFloat(address + 28L, (float) self.uYy);
        _U.putFloat(address + 32L, (float) self.uYz);
        _U.putFloat(address + 36L, (float) self.uZx);
        _U.putFloat(address + 40L, (float) self.uZy);
        _U.putFloat(address + 44L, (float) self.uZz);
        _U.putFloat(address + 48L, (float) self.hsX);
        _U.putFloat(address + 52L, (float) self.hsY);
        _U.putFloat(address + 56L, (float) self.hsZ);
        return buf;
    }

    private static ByteBuffer storeFloatAbsoluteElse(DoubleOBBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
        RAW.storeFloatHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
        return buf;
    }

    public DoubleOBB loadFloatAbsolute(DoubleOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(self, index, buf);
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

    private static DoubleOBB loadFloatAbsoluteElse(DoubleOBBImpl self, int index, ByteBuffer buf) {
        byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
        if (bytes == null) return API.loadFloatAbsolute(self, index, buf);
        return RAW.loadFloatHeap(self, bytes, U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index);
    }
}
