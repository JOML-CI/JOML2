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

public final class FloatOBBBbOpsUnsafe extends FloatOBBBbOps {

    private static final FloatOBBBbOpsApi API = new FloatOBBBbOpsApi();

    private static final long BB_ADDRESS_OFFSET;
    static {
        try {
            BB_ADDRESS_OFFSET = U.objectFieldOffset(Buffer.class.getDeclaredField("address"));
        } catch (ReflectiveOperationException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public FloatBuffer storeAbsolute(FloatOBBImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        U.putFloat(address, self.cX);
        U.putFloat(address + 4L, self.cY);
        U.putFloat(address + 8L, self.cZ);
        U.putFloat(address + 12L, self.uXx);
        U.putFloat(address + 16L, self.uXy);
        U.putFloat(address + 20L, self.uXz);
        U.putFloat(address + 24L, self.uYx);
        U.putFloat(address + 28L, self.uYy);
        U.putFloat(address + 32L, self.uYz);
        U.putFloat(address + 36L, self.uZx);
        U.putFloat(address + 40L, self.uZy);
        U.putFloat(address + 44L, self.uZz);
        U.putFloat(address + 48L, self.hsX);
        U.putFloat(address + 52L, self.hsY);
        U.putFloat(address + 56L, self.hsZ);
        return buf;
    }
    public FloatOBB loadAbsolute(FloatOBBImpl self, int index, FloatBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 4L;
        self.cX = U.getFloat(address);
        self.cY = U.getFloat(address + 4L);
        self.cZ = U.getFloat(address + 8L);
        self.uXx = U.getFloat(address + 12L);
        self.uXy = U.getFloat(address + 16L);
        self.uXz = U.getFloat(address + 20L);
        self.uYx = U.getFloat(address + 24L);
        self.uYy = U.getFloat(address + 28L);
        self.uYz = U.getFloat(address + 32L);
        self.uZx = U.getFloat(address + 36L);
        self.uZy = U.getFloat(address + 40L);
        self.uZz = U.getFloat(address + 44L);
        self.hsX = U.getFloat(address + 48L);
        self.hsY = U.getFloat(address + 52L);
        self.hsZ = U.getFloat(address + 56L);
        return self;
    }
    public ByteBuffer storeAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putFloat(address, self.cX);
        U.putFloat(address + 4L, self.cY);
        U.putFloat(address + 8L, self.cZ);
        U.putFloat(address + 12L, self.uXx);
        U.putFloat(address + 16L, self.uXy);
        U.putFloat(address + 20L, self.uXz);
        U.putFloat(address + 24L, self.uYx);
        U.putFloat(address + 28L, self.uYy);
        U.putFloat(address + 32L, self.uYz);
        U.putFloat(address + 36L, self.uZx);
        U.putFloat(address + 40L, self.uZy);
        U.putFloat(address + 44L, self.uZz);
        U.putFloat(address + 48L, self.hsX);
        U.putFloat(address + 52L, self.hsY);
        U.putFloat(address + 56L, self.hsZ);
        return buf;
    }
    public FloatOBB loadAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.cX = U.getFloat(address);
        self.cY = U.getFloat(address + 4L);
        self.cZ = U.getFloat(address + 8L);
        self.uXx = U.getFloat(address + 12L);
        self.uXy = U.getFloat(address + 16L);
        self.uXz = U.getFloat(address + 20L);
        self.uYx = U.getFloat(address + 24L);
        self.uYy = U.getFloat(address + 28L);
        self.uYz = U.getFloat(address + 32L);
        self.uZx = U.getFloat(address + 36L);
        self.uZy = U.getFloat(address + 40L);
        self.uZz = U.getFloat(address + 44L);
        self.hsX = U.getFloat(address + 48L);
        self.hsY = U.getFloat(address + 52L);
        self.hsZ = U.getFloat(address + 56L);
        return self;
    }
    public DoubleBuffer storeAbsolute(FloatOBBImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        U.putDouble(address, self.cX);
        U.putDouble(address + 8L, self.cY);
        U.putDouble(address + 16L, self.cZ);
        U.putDouble(address + 24L, self.uXx);
        U.putDouble(address + 32L, self.uXy);
        U.putDouble(address + 40L, self.uXz);
        U.putDouble(address + 48L, self.uYx);
        U.putDouble(address + 56L, self.uYy);
        U.putDouble(address + 64L, self.uYz);
        U.putDouble(address + 72L, self.uZx);
        U.putDouble(address + 80L, self.uZy);
        U.putDouble(address + 88L, self.uZz);
        U.putDouble(address + 96L, self.hsX);
        U.putDouble(address + 104L, self.hsY);
        U.putDouble(address + 112L, self.hsZ);
        return buf;
    }
    public FloatOBB loadAbsolute(FloatOBBImpl self, int index, DoubleBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index * 8L;
        self.cX = (float) U.getDouble(address);
        self.cY = (float) U.getDouble(address + 8L);
        self.cZ = (float) U.getDouble(address + 16L);
        self.uXx = (float) U.getDouble(address + 24L);
        self.uXy = (float) U.getDouble(address + 32L);
        self.uXz = (float) U.getDouble(address + 40L);
        self.uYx = (float) U.getDouble(address + 48L);
        self.uYy = (float) U.getDouble(address + 56L);
        self.uYz = (float) U.getDouble(address + 64L);
        self.uZx = (float) U.getDouble(address + 72L);
        self.uZy = (float) U.getDouble(address + 80L);
        self.uZz = (float) U.getDouble(address + 88L);
        self.hsX = (float) U.getDouble(address + 96L);
        self.hsY = (float) U.getDouble(address + 104L);
        self.hsZ = (float) U.getDouble(address + 112L);
        return self;
    }
    public ByteBuffer storeDoubleAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        U.putDouble(address, self.cX);
        U.putDouble(address + 8L, self.cY);
        U.putDouble(address + 16L, self.cZ);
        U.putDouble(address + 24L, self.uXx);
        U.putDouble(address + 32L, self.uXy);
        U.putDouble(address + 40L, self.uXz);
        U.putDouble(address + 48L, self.uYx);
        U.putDouble(address + 56L, self.uYy);
        U.putDouble(address + 64L, self.uYz);
        U.putDouble(address + 72L, self.uZx);
        U.putDouble(address + 80L, self.uZy);
        U.putDouble(address + 88L, self.uZz);
        U.putDouble(address + 96L, self.hsX);
        U.putDouble(address + 104L, self.hsY);
        U.putDouble(address + 112L, self.hsZ);
        return buf;
    }
    public FloatOBB loadDoubleAbsolute(FloatOBBImpl self, int index, ByteBuffer buf) {
        if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadDoubleAbsolute(self, index, buf);
        long address = U.getLong(buf, BB_ADDRESS_OFFSET) + index;
        self.cX = (float) U.getDouble(address);
        self.cY = (float) U.getDouble(address + 8L);
        self.cZ = (float) U.getDouble(address + 16L);
        self.uXx = (float) U.getDouble(address + 24L);
        self.uXy = (float) U.getDouble(address + 32L);
        self.uXz = (float) U.getDouble(address + 40L);
        self.uYx = (float) U.getDouble(address + 48L);
        self.uYy = (float) U.getDouble(address + 56L);
        self.uYz = (float) U.getDouble(address + 64L);
        self.uZx = (float) U.getDouble(address + 72L);
        self.uZy = (float) U.getDouble(address + 80L);
        self.uZz = (float) U.getDouble(address + 88L);
        self.hsX = (float) U.getDouble(address + 96L);
        self.hsY = (float) U.getDouble(address + 104L);
        self.hsZ = (float) U.getDouble(address + 112L);
        return self;
    }
}
