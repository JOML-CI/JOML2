// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float4Ops} whose leading storage
 * parameter is a typed {@link java.nio.FloatBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float4Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float4OpsKernelsTypedBuffer {
    private Float4OpsKernelsTypedBuffer() {}

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, otherX + src.get(srcOffset));
        dest.put(destOffset + 1, otherY + _selfy);
        dest.put(destOffset + 2, otherZ + _selfz);
        dest.put(destOffset + 3, otherW + _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, other.get(otherOffset) + src.get(srcOffset));
        dest.put(destOffset + 1, _othery + _selfy);
        dest.put(destOffset + 2, _otherz + _selfz);
        dest.put(destOffset + 3, _otherw + _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) / scalar);
        dest.put(destOffset + 1, _selfy / scalar);
        dest.put(destOffset + 2, _selfz / scalar);
        dest.put(destOffset + 3, _selfw / scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) / otherX);
        dest.put(destOffset + 1, _selfy / otherY);
        dest.put(destOffset + 2, _selfz / otherZ);
        dest.put(destOffset + 3, _selfw / otherW);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, src.get(srcOffset) / other.get(otherOffset));
        dest.put(destOffset + 1, _selfy / _othery);
        dest.put(destOffset + 2, _selfz / _otherz);
        dest.put(destOffset + 3, _selfw / _otherw);
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b, cX, cY, cZ, cW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), b, cX));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, b, cY));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, b, cZ));
            dest.put(destOffset + 3, java.lang.Math.fma(_selfw, b, cW));
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((src.get(srcOffset)) * (b) + (cX)));
            dest.put(destOffset + 1, ((_selfy) * (b) + (cY)));
            dest.put(destOffset + 2, ((_selfz) * (b) + (cZ)));
            dest.put(destOffset + 3, ((_selfw) * (b) + (cW)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 4L;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 4) {
            Float4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, c.array(), c.arrayOffset() + cOffset, b);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, c, cOffset, b);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _cy = c.get(cOffset + 1);
            float _cz = c.get(cOffset + 2);
            float _cw = c.get(cOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), b, c.get(cOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, b, _cy));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, b, _cz));
            dest.put(destOffset + 3, java.lang.Math.fma(_selfw, b, _cw));
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _cy = c.get(cOffset + 1);
            float _cz = c.get(cOffset + 2);
            float _cw = c.get(cOffset + 3);
            dest.put(destOffset, ((src.get(srcOffset)) * (b) + (c.get(cOffset))));
            dest.put(destOffset + 1, ((_selfy) * (b) + (_cy)));
            dest.put(destOffset + 2, ((_selfz) * (b) + (_cz)));
            dest.put(destOffset + 3, ((_selfw) * (b) + (_cw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), bX, cX));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, bY, cY));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, bZ, cZ));
            dest.put(destOffset + 3, java.lang.Math.fma(_selfw, bW, cW));
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((src.get(srcOffset)) * (bX) + (cX)));
            dest.put(destOffset + 1, ((_selfy) * (bY) + (cY)));
            dest.put(destOffset + 2, ((_selfz) * (bZ) + (cZ)));
            dest.put(destOffset + 3, ((_selfw) * (bW) + (cW)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 4L;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 4 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 4) {
            Float4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_apiGet_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _by = b.get(bOffset + 1);
        float _bz = b.get(bOffset + 2);
        float _bw = b.get(bOffset + 3);
        float _cy = c.get(cOffset + 1);
        float _cz = c.get(cOffset + 2);
        float _cw = c.get(cOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), b.get(bOffset), c.get(cOffset)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _by, _cy));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _bz, _cz));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    /** {@code fma_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer fma_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _by = b.get(bOffset + 1);
        float _bz = b.get(bOffset + 2);
        float _bw = b.get(bOffset + 3);
        float _cy = c.get(cOffset + 1);
        float _cz = c.get(cOffset + 2);
        float _cw = c.get(cOffset + 3);
        dest.put(destOffset, ((src.get(srcOffset)) * (b.get(bOffset)) + (c.get(cOffset))));
        dest.put(destOffset + 1, ((_selfy) * (_by) + (_cy)));
        dest.put(destOffset + 2, ((_selfz) * (_bz) + (_cz)));
        dest.put(destOffset + 3, ((_selfw) * (_bw) + (_cw)));
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, scalar * src.get(srcOffset));
        dest.put(destOffset + 1, scalar * _selfy);
        dest.put(destOffset + 2, scalar * _selfz);
        dest.put(destOffset + 3, scalar * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, otherX * src.get(srcOffset));
        dest.put(destOffset + 1, otherY * _selfy);
        dest.put(destOffset + 2, otherZ * _selfz);
        dest.put(destOffset + 3, otherW * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, other.get(otherOffset) * src.get(srcOffset));
        dest.put(destOffset + 1, _othery * _selfy);
        dest.put(destOffset + 2, _otherz * _selfz);
        dest.put(destOffset + 3, _otherw * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer negate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_selfy);
        dest.put(destOffset + 2, -_selfz);
        dest.put(destOffset + 3, -_selfw);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) - otherX);
        dest.put(destOffset + 1, _selfy - otherY);
        dest.put(destOffset + 2, _selfz - otherZ);
        dest.put(destOffset + 3, _selfw - otherW);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, src.get(srcOffset) - other.get(otherOffset));
        dest.put(destOffset + 1, _selfy - _othery);
        dest.put(destOffset + 2, _selfz - _otherz);
        dest.put(destOffset + 3, _selfw - _otherw);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, float u, float v, float w) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float4OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v, w);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_api(java.nio.FloatBuffer dest, int destOffset, float u, float v, float w) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float4Ops.makeUniformDirection(dest.array(), dest.arrayOffset() + destOffset, u, v, w);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.makeUniformDirection_apiGet(dest, destOffset, u, v, w);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_apiGet(java.nio.FloatBuffer dest, int destOffset, float u, float v, float w) {
        float _t0 = (float) java.lang.Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = (float) java.lang.Math.sqrt(1.0f - u);
        dest.put(destOffset, Math.cosWithSin(_t1) * _t4);
        dest.put(destOffset + 1, Math.sinWithCos(_t1) * _t4);
        dest.put(destOffset + 2, Math.cosWithSin(_t3) * _t0);
        dest.put(destOffset + 3, Math.sinWithCos(_t3) * _t0);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float4OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float4Ops.set(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ, vW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        dest.put(destOffset, vX);
        dest.put(destOffset + 1, vY);
        dest.put(destOffset + 2, vZ);
        dest.put(destOffset + 3, vW);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4) {
            Float4Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _vw = v.get(vOffset + 3);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, _vy);
        dest.put(destOffset + 2, _vz);
        dest.put(destOffset + 3, _vw);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float4OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float4Ops.set(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, float s) {
        dest.put(destOffset, s);
        dest.put(destOffset + 1, s);
        dest.put(destOffset + 2, s);
        dest.put(destOffset + 3, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float4OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float4Ops.makeZero(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.makeZero_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.put(destOffset, java.lang.Math.fma(p1X, _t7, src.get(srcOffset) * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2));
        dest.put(destOffset + 1, java.lang.Math.fma(p1Y, _t7, _selfy * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2));
        dest.put(destOffset + 2, java.lang.Math.fma(p1Z, _t7, _selfz * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2));
        dest.put(destOffset + 3, java.lang.Math.fma(p1W, _t7, _selfw * _t8) + java.lang.Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    /** {@code bezier_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer bezier_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.put(destOffset, ((p1X) * (_t7) + (src.get(srcOffset) * _t8)) + ((p2X) * (_t6) + (p3X * _t2)));
        dest.put(destOffset + 1, ((p1Y) * (_t7) + (_selfy * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2)));
        dest.put(destOffset + 2, ((p1Z) * (_t7) + (_selfz * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2)));
        dest.put(destOffset + 3, ((p1W) * (_t7) + (_selfw * _t8)) + ((p2W) * (_t6) + (p3W * _t2)));
        return dest;
    }

    public static java.nio.FloatBuffer bezier_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 4L;
        Float4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Float4Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_apiGet_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezier_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezier_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer bezier_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.put(destOffset, java.lang.Math.fma(p1.get(p1Offset), _t7, src.get(srcOffset) * _t8) + java.lang.Math.fma(p2.get(p2Offset), _t6, p3.get(p3Offset) * _t2));
        dest.put(destOffset + 1, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        return bezier_apiGet_fma_s850d9246_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _p3z, _p3w, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer bezier_apiGet_fma_s850d9246_1(java.nio.FloatBuffer dest, int destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _p3z, float _p3w, float _t2, float _t6, float _t7, float _t8) {
        dest.put(destOffset + 2, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        dest.put(destOffset + 3, java.lang.Math.fma(_p1w, _t7, _selfw * _t8) + java.lang.Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    /** {@code bezier_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer bezier_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.put(destOffset, ((p1.get(p1Offset)) * (_t7) + (src.get(srcOffset) * _t8)) + ((p2.get(p2Offset)) * (_t6) + (p3.get(p3Offset) * _t2)));
        dest.put(destOffset + 1, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        return bezier_apiGet_mulAdd_scbdac99d_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _p3z, _p3w, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer bezier_apiGet_mulAdd_scbdac99d_1(java.nio.FloatBuffer dest, int destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _p3z, float _p3w, float _t2, float _t6, float _t7, float _t8) {
        dest.put(destOffset + 2, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        dest.put(destOffset + 3, ((_p1w) * (_t7) + (_selfw * _t8)) + ((_p2w) * (_t6) + (_p3w * _t2)));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.put(destOffset, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, src.get(srcOffset) * _t4)));
        dest.put(destOffset + 1, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.put(destOffset + 2, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
        dest.put(destOffset + 3, java.lang.Math.fma(p2W, _t0, java.lang.Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer bezier2_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.put(destOffset, ((p2X) * (_t0) + (((p1X) * (_t3) + (src.get(srcOffset) * _t4)))));
        dest.put(destOffset + 1, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
        dest.put(destOffset + 2, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
        dest.put(destOffset + 3, ((p2W) * (_t0) + (((p1W) * (_t3) + (_selfw * _t4)))));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        Float4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4) {
            Float4Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.put(destOffset, java.lang.Math.fma(p2.get(p2Offset), _t0, java.lang.Math.fma(p1.get(p1Offset), _t3, src.get(srcOffset) * _t4)));
        dest.put(destOffset + 1, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.put(destOffset + 2, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        dest.put(destOffset + 3, java.lang.Math.fma(_p2w, _t0, java.lang.Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer bezier2_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.put(destOffset, ((p2.get(p2Offset)) * (_t0) + (((p1.get(p1Offset)) * (_t3) + (src.get(srcOffset) * _t4)))));
        dest.put(destOffset + 1, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        dest.put(destOffset + 2, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        dest.put(destOffset + 3, ((_p2w) * (_t0) + (((_p1w) * (_t3) + (_selfw * _t4)))));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            dest.put(destOffset, java.lang.Math.fma(p1X - src.get(srcOffset), _t2, (p2X - p1X) * _t1));
            dest.put(destOffset + 1, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
            dest.put(destOffset + 2, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
            dest.put(destOffset + 3, java.lang.Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            dest.put(destOffset, ((p1X - src.get(srcOffset)) * (_t2) + ((p2X - p1X) * _t1)));
            dest.put(destOffset + 1, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
            dest.put(destOffset + 2, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
            dest.put(destOffset + 3, ((p1W - _selfw) * (_t2) + ((p2W - p1W) * _t1)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer bezier2Tangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        Float4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4) {
            Float4Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.put(destOffset, java.lang.Math.fma(_p1x - src.get(srcOffset), _t2, (p2.get(p2Offset) - _p1x) * _t1));
        dest.put(destOffset + 1, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.put(destOffset + 2, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        dest.put(destOffset + 3, java.lang.Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer bezier2Tangent_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.put(destOffset, ((_p1x - src.get(srcOffset)) * (_t2) + ((p2.get(p2Offset) - _p1x) * _t1)));
        dest.put(destOffset + 1, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        dest.put(destOffset + 2, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        dest.put(destOffset + 3, ((_p1w - _selfw) * (_t2) + ((_p2w - _p1w) * _t1)));
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.put(destOffset, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - src.get(srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.put(destOffset + 1, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.put(destOffset + 2, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        dest.put(destOffset + 3, java.lang.Math.fma(p3W - p2W, _t2, java.lang.Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer bezierTangent_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.put(destOffset, ((p3X - p2X) * (_t2) + (((p1X - src.get(srcOffset)) * (_t6) + ((p2X - p1X) * _t5)))));
        dest.put(destOffset + 1, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        dest.put(destOffset + 2, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
        dest.put(destOffset + 3, ((p3W - p2W) * (_t2) + (((p1W - _selfw) * (_t6) + ((p2W - p1W) * _t5)))));
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 4L;
        Float4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Float4Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_apiGet_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezierTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezierTangent_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer bezierTangent_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.put(destOffset, java.lang.Math.fma(p3.get(p3Offset) - _p2x, _t2, java.lang.Math.fma(_p1x - src.get(srcOffset), _t6, (_p2x - _p1x) * _t5)));
        dest.put(destOffset + 1, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return bezierTangent_apiGet_fma_se83a79ff_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _p3z, _p3w, _t2, _t5, _t6);
    }

    /** Piece 2 of {@code bezierTangent_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer bezierTangent_apiGet_fma_se83a79ff_1(java.nio.FloatBuffer dest, int destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _p3z, float _p3w, float _t2, float _t5, float _t6) {
        dest.put(destOffset + 2, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        dest.put(destOffset + 3, java.lang.Math.fma(_p3w - _p2w, _t2, java.lang.Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer bezierTangent_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.put(destOffset, ((p3.get(p3Offset) - _p2x) * (_t2) + (((_p1x - src.get(srcOffset)) * (_t6) + ((_p2x - _p1x) * _t5)))));
        dest.put(destOffset + 1, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        return bezierTangent_apiGet_mulAdd_s6e7bbac2_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _p3z, _p3w, _t2, _t5, _t6);
    }

    /** Piece 2 of {@code bezierTangent_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer bezierTangent_apiGet_mulAdd_s6e7bbac2_1(java.nio.FloatBuffer dest, int destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _p3z, float _p3w, float _t2, float _t5, float _t6) {
        dest.put(destOffset + 2, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        dest.put(destOffset + 3, ((_p3w - _p2w) * (_t2) + (((_p1w - _selfw) * (_t6) + ((_p2w - _p1w) * _t5)))));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_apiGet_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRom_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRom_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer catmullRom_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.put(destOffset, 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_apiGet_fma_sd104d2d9_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer catmullRom_apiGet_fma_sd104d2d9_1(java.nio.FloatBuffer dest, int destOffset, float p1Z, float p1W, float p2Z, float p2W, float p3Z, float p3W, float t, float _selfz, float _selfw, float _t0, float _t1) {
        dest.put(destOffset + 2, 0.5f * (java.lang.Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        dest.put(destOffset + 3, 0.5f * (java.lang.Math.fma(2.0f, p1W, t * (p2W - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer catmullRom_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.put(destOffset, 0.5f * (((2.0f) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) * (_t0) + (((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X)) * _t1))));
        dest.put(destOffset + 1, 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) * (_t0) + (((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y)) * _t1))));
        dest.put(destOffset + 2, 0.5f * (((2.0f) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) * (_t0) + (((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z)) * _t1))));
        return catmullRom_apiGet_mulAdd_sbf4c27a2_1(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer catmullRom_apiGet_mulAdd_sbf4c27a2_1(java.nio.FloatBuffer dest, int destOffset, float p1W, float p2W, float p3W, float t, float _selfw, float _t0, float _t1) {
        dest.put(destOffset + 3, 0.5f * (((2.0f) * (p1W) + (t * (p2W - _selfw))) + ((((((2.0f) * (_selfw) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) * (_t0) + (((((3.0f) * (p1W) + (p3W - _selfw))) - (3.0f) * (p2W)) * _t1))));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 4L;
        Float4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Float4Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_apiGet_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRom_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRom_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer catmullRom_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3x = p3.get(p3Offset);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.put(destOffset, 0.5f * (java.lang.Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        return catmullRom_apiGet_fma_s7e133db1_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer catmullRom_apiGet_fma_s7e133db1_1(java.nio.FloatBuffer dest, int destOffset, float t, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t0, float _t1) {
        dest.put(destOffset + 1, 0.5f * (java.lang.Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        dest.put(destOffset + 2, 0.5f * (java.lang.Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        dest.put(destOffset + 3, 0.5f * (java.lang.Math.fma(2.0f, _p1w, t * (_p2w - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1w, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, _p2w, -_p3w))), _t0, java.lang.Math.fma(-3.0f, _p2w, java.lang.Math.fma(3.0f, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer catmullRom_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3x = p3.get(p3Offset);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.put(destOffset, 0.5f * (((2.0f) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) * (_t0) + (((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x)) * _t1))));
        return catmullRom_apiGet_mulAdd_sd0402d44_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer catmullRom_apiGet_mulAdd_sd0402d44_1(java.nio.FloatBuffer dest, int destOffset, float t, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t0, float _t1) {
        dest.put(destOffset + 1, 0.5f * (((2.0f) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) * (_t0) + (((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y)) * _t1))));
        dest.put(destOffset + 2, 0.5f * (((2.0f) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) * (_t0) + (((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z)) * _t1))));
        dest.put(destOffset + 3, 0.5f * (((2.0f) * (_p1w) + (t * (_p2w - _selfw))) + ((((((2.0f) * (_selfw) + (((4.0f) * (_p2w) - (_p3w))))) - (5.0f) * (_p1w))) * (_t0) + (((((3.0f) * (_p1w) + (_p3w - _selfw))) - (3.0f) * (_p2w)) * _t1))));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_apiGet_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRomTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRomTangent_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer catmullRomTangent_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        dest.put(destOffset, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.put(destOffset + 1, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.put(destOffset + 2, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return catmullRomTangent_apiGet_fma_sd52b8f0_1(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer catmullRomTangent_apiGet_fma_sd52b8f0_1(java.nio.FloatBuffer dest, int destOffset, float p1W, float p2W, float p3W, float t, float _selfw, float _t0) {
        dest.put(destOffset + 3, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, p2W, -p3W))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer catmullRomTangent_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        dest.put(destOffset, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) + (((3.0f * ((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X))) * (_t0) + (p2X - _selfx)))));
        dest.put(destOffset + 1, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) + (((3.0f * ((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        dest.put(destOffset + 2, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) + (((3.0f * ((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
        return catmullRomTangent_apiGet_mulAdd_s9cd87be9_1(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer catmullRomTangent_apiGet_mulAdd_s9cd87be9_1(java.nio.FloatBuffer dest, int destOffset, float p1W, float p2W, float p3W, float t, float _selfw, float _t0) {
        dest.put(destOffset + 3, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfw) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) + (((3.0f * ((((3.0f) * (p1W) + (p3W - _selfw))) - (3.0f) * (p2W))) * (_t0) + (p2W - _selfw)))));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 4L;
        Float4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Float4Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_apiGet_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRomTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRomTangent_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer catmullRomTangent_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3x = p3.get(p3Offset);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t0 = t * t;
        dest.put(destOffset, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        return catmullRomTangent_apiGet_fma_s11170b4a_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer catmullRomTangent_apiGet_fma_s11170b4a_1(java.nio.FloatBuffer dest, int destOffset, float t, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t0) {
        dest.put(destOffset + 1, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.put(destOffset + 2, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        dest.put(destOffset + 3, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1w, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, _p2w, -_p3w))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2w, java.lang.Math.fma(3.0f, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer catmullRomTangent_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3x = p3.get(p3Offset);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t0 = t * t;
        dest.put(destOffset, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) + (((3.0f * ((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        return catmullRomTangent_apiGet_mulAdd_sa266d041_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer catmullRomTangent_apiGet_mulAdd_sa266d041_1(java.nio.FloatBuffer dest, int destOffset, float t, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t0) {
        dest.put(destOffset + 1, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) + (((3.0f * ((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        dest.put(destOffset + 2, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) + (((3.0f * ((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        dest.put(destOffset + 3, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfw) + (((4.0f) * (_p2w) - (_p3w))))) - (5.0f) * (_p1w))) + (((3.0f * ((((3.0f) * (_p1w) + (_p3w - _selfw))) - (3.0f) * (_p2w))) * (_t0) + (_p2w - _selfw)))));
        return dest;
    }

    public static java.nio.FloatBuffer hermite_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_apiGet_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t10, t0W * _t7) + java.lang.Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    /** {@code hermite_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer hermite_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        dest.put(destOffset, ((src.get(srcOffset)) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        dest.put(destOffset + 1, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        dest.put(destOffset + 2, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
        dest.put(destOffset + 3, ((_selfw) * (_t10) + (t0W * _t7)) + ((t1W) * (_t5) + (v1W * _t9)));
        return dest;
    }

    public static java.nio.FloatBuffer hermite_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset * 4L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset * 4L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset * 4L;
        Float4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 4 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 4 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 4) {
            Float4Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_apiGet_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_apiGet_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermite_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer hermite_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0y = t0.get(t0Offset + 1);
        float _t0z = t0.get(t0Offset + 2);
        float _t0w = t0.get(t0Offset + 3);
        float _v1y = v1.get(v1Offset + 1);
        float _v1z = v1.get(v1Offset + 2);
        float _v1w = v1.get(v1Offset + 3);
        float _t1y = t1.get(t1Offset + 1);
        float _t1z = t1.get(t1Offset + 2);
        float _t1w = t1.get(t1Offset + 3);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), _t10, t0.get(t0Offset) * _t7) + java.lang.Math.fma(t1.get(t1Offset), _t5, v1.get(v1Offset) * _t9));
        return hermite_apiGet_fma_s6461fbb1_1(dest, destOffset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t5, _t7, _t9, _t10);
    }

    /** Piece 2 of {@code hermite_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer hermite_apiGet_fma_s6461fbb1_1(java.nio.FloatBuffer dest, int destOffset, float _selfy, float _selfz, float _selfw, float _t0y, float _t0z, float _t0w, float _v1y, float _v1z, float _v1w, float _t1y, float _t1z, float _t1w, float _t5, float _t7, float _t9, float _t10) {
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t10, _t0w * _t7) + java.lang.Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    /** {@code hermite_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer hermite_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0y = t0.get(t0Offset + 1);
        float _t0z = t0.get(t0Offset + 2);
        float _t0w = t0.get(t0Offset + 3);
        float _v1y = v1.get(v1Offset + 1);
        float _v1z = v1.get(v1Offset + 2);
        float _v1w = v1.get(v1Offset + 3);
        float _t1y = t1.get(t1Offset + 1);
        float _t1z = t1.get(t1Offset + 2);
        float _t1w = t1.get(t1Offset + 3);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        dest.put(destOffset, ((src.get(srcOffset)) * (_t10) + (t0.get(t0Offset) * _t7)) + ((t1.get(t1Offset)) * (_t5) + (v1.get(v1Offset) * _t9)));
        dest.put(destOffset + 1, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        return hermite_apiGet_mulAdd_s72ec04dc_1(dest, destOffset, _selfz, _selfw, _t0z, _t0w, _v1z, _v1w, _t1z, _t1w, _t5, _t7, _t9, _t10);
    }

    /** Piece 2 of {@code hermite_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer hermite_apiGet_mulAdd_s72ec04dc_1(java.nio.FloatBuffer dest, int destOffset, float _selfz, float _selfw, float _t0z, float _t0w, float _v1z, float _v1w, float _t1z, float _t1w, float _t5, float _t7, float _t9, float _t10) {
        dest.put(destOffset + 2, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        dest.put(destOffset + 3, ((_selfw) * (_t10) + (_t0w * _t7)) + ((_t1w) * (_t5) + (_v1w * _t9)));
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t6, t0W * _t9) + java.lang.Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    /** {@code hermiteTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer hermiteTangent_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        dest.put(destOffset, ((src.get(srcOffset)) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        dest.put(destOffset + 1, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        dest.put(destOffset + 2, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
        dest.put(destOffset + 3, ((_selfw) * (_t6) + (t0W * _t9)) + ((t1W) * (_t8) + (v1W * _t7)));
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset * 4L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset * 4L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset * 4L;
        Float4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 4 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 4 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 4) {
            Float4Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_apiGet_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermiteTangent_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer hermiteTangent_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0y = t0.get(t0Offset + 1);
        float _t0z = t0.get(t0Offset + 2);
        float _t0w = t0.get(t0Offset + 3);
        float _v1y = v1.get(v1Offset + 1);
        float _v1z = v1.get(v1Offset + 2);
        float _v1w = v1.get(v1Offset + 3);
        float _t1y = t1.get(t1Offset + 1);
        float _t1z = t1.get(t1Offset + 2);
        float _t1w = t1.get(t1Offset + 3);
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), _t6, t0.get(t0Offset) * _t9) + java.lang.Math.fma(t1.get(t1Offset), _t8, v1.get(v1Offset) * _t7));
        return hermiteTangent_apiGet_fma_s2f9d3802_1(dest, destOffset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t6, _t7, _t8, _t9);
    }

    /** Piece 2 of {@code hermiteTangent_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer hermiteTangent_apiGet_fma_s2f9d3802_1(java.nio.FloatBuffer dest, int destOffset, float _selfy, float _selfz, float _selfw, float _t0y, float _t0z, float _t0w, float _v1y, float _v1z, float _v1w, float _t1y, float _t1z, float _t1w, float _t6, float _t7, float _t8, float _t9) {
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t6, _t0w * _t9) + java.lang.Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    /** {@code hermiteTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer hermiteTangent_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0y = t0.get(t0Offset + 1);
        float _t0z = t0.get(t0Offset + 2);
        float _t0w = t0.get(t0Offset + 3);
        float _v1y = v1.get(v1Offset + 1);
        float _v1z = v1.get(v1Offset + 2);
        float _v1w = v1.get(v1Offset + 3);
        float _t1y = t1.get(t1Offset + 1);
        float _t1z = t1.get(t1Offset + 2);
        float _t1w = t1.get(t1Offset + 3);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        dest.put(destOffset, ((src.get(srcOffset)) * (_t6) + (t0.get(t0Offset) * _t9)) + ((t1.get(t1Offset)) * (_t8) + (v1.get(v1Offset) * _t7)));
        dest.put(destOffset + 1, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        return hermiteTangent_apiGet_mulAdd_s85ba6009_1(dest, destOffset, _selfz, _selfw, _t0z, _t0w, _v1z, _v1w, _t1z, _t1w, _t6, _t7, _t8, _t9);
    }

    /** Piece 2 of {@code hermiteTangent_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer hermiteTangent_apiGet_mulAdd_s85ba6009_1(java.nio.FloatBuffer dest, int destOffset, float _selfz, float _selfw, float _t0z, float _t0w, float _v1z, float _v1w, float _t1z, float _t1w, float _t6, float _t7, float _t8, float _t9) {
        dest.put(destOffset + 2, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        dest.put(destOffset + 3, ((_selfw) * (_t6) + (_t0w * _t9)) + ((_t1w) * (_t8) + (_v1w * _t7)));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(t, otherW - _selfw, _selfw));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (otherY - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.put(destOffset + 3, ((t) * (otherW - _selfw) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(t, other.get(otherOffset) - _selfx, _selfx));
        dest.put(destOffset + 1, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        dest.put(destOffset + 2, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        dest.put(destOffset + 3, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, ((t) * (other.get(otherOffset) - _selfx) + (_selfx)));
        dest.put(destOffset + 1, ((t) * (_othery - _selfy) + (_selfy)));
        dest.put(destOffset + 2, ((t) * (_otherz - _selfz) + (_selfz)));
        dest.put(destOffset + 3, ((t) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(tW, otherW - _selfw, _selfw));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((tX) * (otherX - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((tY) * (otherY - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((tZ) * (otherZ - _selfz) + (_selfz)));
            dest.put(destOffset + 3, ((tW) * (otherW - _selfw) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset * 4L;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 4) {
            Float4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _ty = t.get(tOffset + 1);
        float _tz = t.get(tOffset + 2);
        float _tw = t.get(tOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(t.get(tOffset), other.get(otherOffset) - _selfx, _selfx));
        dest.put(destOffset + 1, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
        dest.put(destOffset + 2, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
        dest.put(destOffset + 3, java.lang.Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _ty = t.get(tOffset + 1);
        float _tz = t.get(tOffset + 2);
        float _tw = t.get(tOffset + 3);
        dest.put(destOffset, ((t.get(tOffset)) * (other.get(otherOffset) - _selfx) + (_selfx)));
        dest.put(destOffset + 1, ((_ty) * (_othery - _selfy) + (_selfy)));
        dest.put(destOffset + 2, ((_tz) * (_otherz - _selfz) + (_selfz)));
        dest.put(destOffset + 3, ((_tw) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_apiGet_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        return slerp_apiGet_fma_s95f71c27_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24))));
    }

    /** Piece 2 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma_s95f71c27_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31) {
        float _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.put(destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        return slerp_apiGet_fma_s95f71c27_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma_s95f71c27_2(java.nio.FloatBuffer dest, int destOffset, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        dest.put(destOffset + 1, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.put(destOffset + 2, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.put(destOffset + 3, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        float _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        return slerp_apiGet_mulAdd_s9a6845f4_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t31, ((otherW) * (_t14) - (_t31 * _t19)));
    }

    /** Piece 2 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd_s9a6845f4_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31, float _t40) {
        float _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        float _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        float _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.put(destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        dest.put(destOffset + 1, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.put(destOffset + 2, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.put(destOffset + 3, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Float4OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest.put(destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.put(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.put(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.put(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
                    dest.put(destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.put(destOffset + 1, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.put(destOffset + 2, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.put(destOffset + 3, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.put(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.put(destOffset + 1, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.put(destOffset + 2, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.put(destOffset + 3, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.put(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.put(destOffset + 1, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.put(destOffset + 2, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.put(destOffset + 3, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.put(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (otherY - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.put(destOffset + 3, ((t) * (otherW - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_apiGet_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_apiGet_fma_s9ca82601_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma_s9ca82601_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_apiGet_fma_s9ca82601_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma_s9ca82601_2(java.nio.FloatBuffer dest, int destOffset, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        dest.put(destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.put(destOffset + 1, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.put(destOffset + 2, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.put(destOffset + 3, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_apiGet_mulAdd_sc5b36c0_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd_sc5b36c0_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        float _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        float _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        float _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        float _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.put(destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_apiGet_mulAdd_sc5b36c0_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd_sc5b36c0_2(java.nio.FloatBuffer dest, int destOffset, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        dest.put(destOffset + 1, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.put(destOffset + 2, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.put(destOffset + 3, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float4OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest.put(destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.put(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.put(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.put(destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
                    dest.put(destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.put(destOffset + 1, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.put(destOffset + 2, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.put(destOffset + 3, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.put(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.put(destOffset + 1, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.put(destOffset + 2, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.put(destOffset + 3, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.put(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.put(destOffset + 1, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.put(destOffset + 2, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.put(destOffset + 3, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.put(destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (_othery - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (_otherz - _selfz) + (_selfz)));
            dest.put(destOffset + 3, ((t) * (_otherw - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_apiGet_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        return slerp_apiGet_fma_sc1baa2bd_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24))));
    }

    /** Piece 2 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma_sc1baa2bd_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31) {
        float _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.put(destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        return slerp_apiGet_fma_sc1baa2bd_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma_sc1baa2bd_2(java.nio.FloatBuffer dest, int destOffset, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        dest.put(destOffset + 1, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.put(destOffset + 2, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.put(destOffset + 3, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        return slerp_apiGet_mulAdd_s45c43438_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24)))))));
    }

    /** Piece 2 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd_s45c43438_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31) {
        float _t40 = ((otherW) * (_t14) - (_t31 * _t19));
        float _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        float _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        float _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.put(destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        dest.put(destOffset + 1, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.put(destOffset + 2, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.put(destOffset + 3, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return Float4OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    dest.put(destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.put(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.put(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.put(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    dest.put(destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.put(destOffset + 1, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.put(destOffset + 2, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.put(destOffset + 3, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.put(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.put(destOffset + 1, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.put(destOffset + 2, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.put(destOffset + 3, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.put(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.put(destOffset + 1, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.put(destOffset + 2, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.put(destOffset + 3, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.put(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (otherY - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.put(destOffset + 3, ((t) * (otherW - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t, epsilon);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_apiGet_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_apiGet_fma_sa7d85f67_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma_sa7d85f67_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_apiGet_fma_sa7d85f67_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma_sa7d85f67_2(java.nio.FloatBuffer dest, int destOffset, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        dest.put(destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.put(destOffset + 1, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.put(destOffset + 2, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.put(destOffset + 3, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_apiGet_mulAdd_s52320114_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd_s52320114_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        float _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        float _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        float _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        float _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.put(destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_apiGet_mulAdd_s52320114_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd_s52320114_2(java.nio.FloatBuffer dest, int destOffset, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        dest.put(destOffset + 1, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.put(destOffset + 2, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.put(destOffset + 3, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return Float4OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t, epsilon);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    dest.put(destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.put(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.put(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.put(destOffset + 1, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.put(destOffset + 2, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.put(destOffset + 3, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.put(destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    dest.put(destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.put(destOffset + 1, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.put(destOffset + 2, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.put(destOffset + 3, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.put(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.put(destOffset + 1, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.put(destOffset + 2, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.put(destOffset + 3, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.put(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.put(destOffset + 1, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.put(destOffset + 2, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.put(destOffset + 3, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.put(destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (_othery - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (_otherz - _selfz) + (_selfz)));
            dest.put(destOffset + 3, ((t) * (_otherw - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.nio.FloatBuffer absolute_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer absolute_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.absolute(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.absolute_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer absolute_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.abs(src.get(srcOffset)));
        dest.put(destOffset + 1, java.lang.Math.abs(_selfy));
        dest.put(destOffset + 2, java.lang.Math.abs(_selfz));
        dest.put(destOffset + 3, java.lang.Math.abs(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer acos_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer acos_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.acos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.acos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer acos_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.acos(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.acos(_selfy));
        dest.put(destOffset + 2, Math.acos(_selfz));
        dest.put(destOffset + 3, Math.acos(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, bW, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(scalar, bX, src.get(srcOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(scalar, bY, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(scalar, bZ, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(scalar, bW, _selfw));
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((scalar) * (bX) + (src.get(srcOffset))));
            dest.put(destOffset + 1, ((scalar) * (bY) + (_selfy)));
            dest.put(destOffset + 2, ((scalar) * (bZ) + (_selfz)));
            dest.put(destOffset + 3, ((scalar) * (bW) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 4L;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 4) {
            Float4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _by = b.get(bOffset + 1);
            float _bz = b.get(bOffset + 2);
            float _bw = b.get(bOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(scalar, b.get(bOffset), src.get(srcOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(scalar, _by, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(scalar, _bz, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(scalar, _bw, _selfw));
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _by = b.get(bOffset + 1);
            float _bz = b.get(bOffset + 2);
            float _bw = b.get(bOffset + 3);
            dest.put(destOffset, ((scalar) * (b.get(bOffset)) + (src.get(srcOffset))));
            dest.put(destOffset + 1, ((scalar) * (_by) + (_selfy)));
            dest.put(destOffset + 2, ((scalar) * (_bz) + (_selfz)));
            dest.put(destOffset + 3, ((scalar) * (_bw) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(bX, cX, src.get(srcOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(bY, cY, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(bZ, cZ, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(bW, cW, _selfw));
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((bX) * (cX) + (src.get(srcOffset))));
            dest.put(destOffset + 1, ((bY) * (cY) + (_selfy)));
            dest.put(destOffset + 2, ((bZ) * (cZ) + (_selfz)));
            dest.put(destOffset + 3, ((bW) * (cW) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 4L;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 4 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 4) {
            Float4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_apiGet_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _by = b.get(bOffset + 1);
        float _bz = b.get(bOffset + 2);
        float _bw = b.get(bOffset + 3);
        float _cy = c.get(cOffset + 1);
        float _cz = c.get(cOffset + 2);
        float _cw = c.get(cOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(b.get(bOffset), c.get(cOffset), src.get(srcOffset)));
        dest.put(destOffset + 1, java.lang.Math.fma(_by, _cy, _selfy));
        dest.put(destOffset + 2, java.lang.Math.fma(_bz, _cz, _selfz));
        dest.put(destOffset + 3, java.lang.Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    /** {@code addScaled_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer addScaled_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _by = b.get(bOffset + 1);
        float _bz = b.get(bOffset + 2);
        float _bw = b.get(bOffset + 3);
        float _cy = c.get(cOffset + 1);
        float _cz = c.get(cOffset + 2);
        float _cw = c.get(cOffset + 3);
        dest.put(destOffset, ((b.get(bOffset)) * (c.get(cOffset)) + (src.get(srcOffset))));
        dest.put(destOffset + 1, ((_by) * (_cy) + (_selfy)));
        dest.put(destOffset + 2, ((_bz) * (_cz) + (_selfz)));
        dest.put(destOffset + 3, ((_bw) * (_cw) + (_selfw)));
        return dest;
    }

    public static float angleBetween_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_apiGet_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_apiGet_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_apiGet_fma(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t6 = otherZ * -_selfw;
        float _t7 = otherY * -_selfw;
        float _t8 = -(otherY * _selfz);
        float _t9 = -otherX * _selfw;
        float _t10 = -otherX * _selfy;
        float _t11 = -otherX * _selfz;
        float _t24 = java.lang.Math.fma(otherW, _selfz, _t6) - java.lang.Math.fma(otherZ, _selfw, _t6);
        float _t25 = java.lang.Math.fma(otherW, _selfy, _t7) - java.lang.Math.fma(otherY, _selfw, _t7);
        float _t26 = java.lang.Math.fma(otherZ, _selfy, _t8) - java.lang.Math.fma(otherY, _selfz, _t8);
        float _t27 = java.lang.Math.fma(otherW, _selfx, _t9) - java.lang.Math.fma(otherX, _selfw, _t9);
        float _t28 = java.lang.Math.fma(otherY, _selfx, _t10) - java.lang.Math.fma(otherX, _selfy, _t10);
        float _t29 = java.lang.Math.fma(otherZ, _selfx, _t11) - java.lang.Math.fma(otherX, _selfz, _t11);
        float _ct0 = java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherW, _selfw, java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    /** {@code angleBetween_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_apiGet_mulAdd(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t6 = otherZ * -_selfw;
        float _t7 = otherY * -_selfw;
        float _t8 = -(otherY * _selfz);
        float _t9 = -otherX * _selfw;
        float _t10 = -otherX * _selfy;
        float _t11 = -otherX * _selfz;
        float _t24 = ((otherW) * (_selfz) + (_t6)) - ((otherZ) * (_selfw) + (_t6));
        float _t25 = ((otherW) * (_selfy) + (_t7)) - ((otherY) * (_selfw) + (_t7));
        float _t26 = ((otherZ) * (_selfy) + (_t8)) - ((otherY) * (_selfz) + (_t8));
        float _t27 = ((otherW) * (_selfx) + (_t9)) - ((otherX) * (_selfw) + (_t9));
        float _t28 = ((otherY) * (_selfx) + (_t10)) - ((otherX) * (_selfy) + (_t10));
        float _t29 = ((otherZ) * (_selfx) + (_t11)) - ((otherX) * (_selfz) + (_t11));
        float _ct0 = ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))))))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((otherW) * (_selfw) + (((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))))));
    }

    public static float angleBetween_degenerate(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_apiGet_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_apiGet_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_apiGet_fma(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        float _t34 = -(_t20 * _t17);
        return angleBetween_degenerate_apiGet_fma_sc32c2ac5_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33), java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34));
    }

    /** Piece 2 of {@code angleBetween_degenerate_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_apiGet_fma_sc32c2ac5_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t35, float _t48, float _t49, float _t50, float _t51, float _t52) {
        float _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_apiGet_mulAdd(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        float _t34 = -(_t20 * _t17);
        float _t35 = _t20 * -_t19;
        return angleBetween_degenerate_apiGet_mulAdd_sccfceb02_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)), ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34)), ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_apiGet_mulAdd_sccfceb02_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53) {
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static float angleBetween_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float4OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_apiGet_fma(src, srcOffset, other, otherOffset);
        return angleBetween_apiGet_mulAdd(src, srcOffset, other, otherOffset);
    }

    /** {@code angleBetween_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_apiGet_fma(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t6 = _otherz * -_selfw;
        float _t7 = _othery * -_selfw;
        float _t8 = -(_othery * _selfz);
        float _t9 = -_otherx * _selfw;
        float _t10 = -_otherx * _selfy;
        float _t11 = -_otherx * _selfz;
        float _t24 = java.lang.Math.fma(_otherw, _selfz, _t6) - java.lang.Math.fma(_otherz, _selfw, _t6);
        float _t25 = java.lang.Math.fma(_otherw, _selfy, _t7) - java.lang.Math.fma(_othery, _selfw, _t7);
        float _t26 = java.lang.Math.fma(_otherz, _selfy, _t8) - java.lang.Math.fma(_othery, _selfz, _t8);
        float _t27 = java.lang.Math.fma(_otherw, _selfx, _t9) - java.lang.Math.fma(_otherx, _selfw, _t9);
        float _t28 = java.lang.Math.fma(_othery, _selfx, _t10) - java.lang.Math.fma(_otherx, _selfy, _t10);
        float _t29 = java.lang.Math.fma(_otherz, _selfx, _t11) - java.lang.Math.fma(_otherx, _selfz, _t11);
        return angleBetween_apiGet_fma_s4186bfe6_1(src, srcOffset, other, otherOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29))))));
    }

    /** Piece 2 of {@code angleBetween_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_apiGet_fma_s4186bfe6_1(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherw, _selfw, java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    /** {@code angleBetween_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_apiGet_mulAdd(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t6 = _otherz * -_selfw;
        float _t7 = _othery * -_selfw;
        float _t8 = -(_othery * _selfz);
        float _t9 = -_otherx * _selfw;
        float _t10 = -_otherx * _selfy;
        float _t11 = -_otherx * _selfz;
        float _t24 = ((_otherw) * (_selfz) + (_t6)) - ((_otherz) * (_selfw) + (_t6));
        float _t25 = ((_otherw) * (_selfy) + (_t7)) - ((_othery) * (_selfw) + (_t7));
        float _t26 = ((_otherz) * (_selfy) + (_t8)) - ((_othery) * (_selfz) + (_t8));
        float _t27 = ((_otherw) * (_selfx) + (_t9)) - ((_otherx) * (_selfw) + (_t9));
        float _t28 = ((_othery) * (_selfx) + (_t10)) - ((_otherx) * (_selfy) + (_t10));
        float _t29 = ((_otherz) * (_selfx) + (_t11)) - ((_otherx) * (_selfz) + (_t11));
        return angleBetween_apiGet_mulAdd_s1aa40fb9_1(src, srcOffset, other, otherOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))))))))));
    }

    /** Piece 2 of {@code angleBetween_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_apiGet_mulAdd_s1aa40fb9_1(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((_otherw) * (_selfw) + (((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))))));
    }

    public static float angleBetween_degenerate(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_apiGet_fma(src, srcOffset, other, otherOffset);
        return angleBetween_degenerate_apiGet_mulAdd(src, srcOffset, other, otherOffset);
    }

    /** {@code angleBetween_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_apiGet_fma(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        return angleBetween_degenerate_apiGet_fma_sea9d028b_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33));
    }

    /** Piece 2 of {@code angleBetween_degenerate_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_apiGet_fma_sea9d028b_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t34, float _t35, float _t48, float _t49, float _t50, float _t51) {
        float _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        float _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_apiGet_mulAdd(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        return angleBetween_degenerate_apiGet_mulAdd_s4fec8326_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_apiGet_mulAdd_s4fec8326_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t34, float _t35, float _t48, float _t49, float _t50, float _t51) {
        float _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        float _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static java.nio.FloatBuffer asin_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer asin_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.asin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.asin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer asin_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.asin(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.asin(_selfy));
        dest.put(destOffset + 2, Math.asin(_selfz));
        dest.put(destOffset + 3, Math.asin(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer atan_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer atan_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.atan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.atan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer atan_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.atan(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.atan(_selfy));
        dest.put(destOffset + 2, Math.atan(_selfz));
        dest.put(destOffset + 3, Math.atan(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), x));
        dest.put(destOffset + 1, Math.atan2(_selfy, x));
        dest.put(destOffset + 2, Math.atan2(_selfz, x));
        dest.put(destOffset + 3, Math.atan2(_selfw, x));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ, float xW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ, xW);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ, float xW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, xX, xY, xZ, xW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, xX, xY, xZ, xW);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ, float xW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), xX));
        dest.put(destOffset + 1, Math.atan2(_selfy, xY));
        dest.put(destOffset + 2, Math.atan2(_selfz, xZ));
        dest.put(destOffset + 3, Math.atan2(_selfw, xW));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + xOffset * 4L;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && x.hasArray() && xOffset >= 0 && xOffset <= x.limit() - 4) {
            Float4Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x.array(), x.arrayOffset() + xOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x, xOffset);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _xy = x.get(xOffset + 1);
        float _xz = x.get(xOffset + 2);
        float _xw = x.get(xOffset + 3);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), x.get(xOffset)));
        dest.put(destOffset + 1, Math.atan2(_selfy, _xy));
        dest.put(destOffset + 2, Math.atan2(_selfz, _xz));
        dest.put(destOffset + 3, Math.atan2(_selfw, _xw));
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.cbrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.cbrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.cbrt(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cbrt(_selfy));
        dest.put(destOffset + 2, Math.cbrt(_selfz));
        dest.put(destOffset + 3, Math.cbrt(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer ceil_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer ceil_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.ceil(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.ceil_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer ceil_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.ceil(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.ceil(_selfy));
        dest.put(destOffset + 2, Math.ceil(_selfz));
        dest.put(destOffset + 3, Math.ceil(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min, max);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, max);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.min(Math.max(src.get(srcOffset), min), max));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, min), max));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, min), max));
        dest.put(destOffset + 3, Math.min(Math.max(_selfw, min), max));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.min(Math.max(src.get(srcOffset), minX), maxX));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, minY), maxY));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, minZ), maxZ));
        dest.put(destOffset + 3, Math.min(Math.max(_selfw, minW), maxW));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + minOffset * 4L;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + maxOffset * 4L;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && min.hasArray() && minOffset >= 0 && minOffset <= min.limit() - 4 && max.hasArray() && maxOffset >= 0 && maxOffset <= max.limit() - 4) {
            Float4Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min.array(), min.arrayOffset() + minOffset, max.array(), max.arrayOffset() + maxOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _miny = min.get(minOffset + 1);
        float _minz = min.get(minOffset + 2);
        float _minw = min.get(minOffset + 3);
        float _maxy = max.get(maxOffset + 1);
        float _maxz = max.get(maxOffset + 2);
        float _maxw = max.get(maxOffset + 3);
        dest.put(destOffset, Math.min(Math.max(src.get(srcOffset), min.get(minOffset)), max.get(maxOffset)));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, _miny), _maxy));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, _minz), _maxz));
        dest.put(destOffset + 3, Math.min(Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static float compAdd_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.compAdd(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.compAdd_apiGet(src, srcOffset);
    }

    public static float compAdd_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return src.get(srcOffset + 3) + (src.get(srcOffset + 2) + (src.get(srcOffset) + src.get(srcOffset + 1)));
    }

    public static float compMax_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.compMax(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.compMax_apiGet(src, srcOffset);
    }

    public static float compMax_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return Math.max(Math.max(Math.max(src.get(srcOffset), src.get(srcOffset + 1)), src.get(srcOffset + 2)), src.get(srcOffset + 3));
    }

    public static float compMin_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.compMin(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.compMin_apiGet(src, srcOffset);
    }

    public static float compMin_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return Math.min(Math.min(Math.min(src.get(srcOffset), src.get(srcOffset + 1)), src.get(srcOffset + 2)), src.get(srcOffset + 3));
    }

    public static float compMul_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.compMul(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.compMul_apiGet(src, srcOffset);
    }

    public static float compMul_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return src.get(srcOffset + 3) * src.get(srcOffset + 2) * src.get(srcOffset) * src.get(srcOffset + 1);
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), sign));
        dest.put(destOffset + 1, Math.copySign(_selfy, sign));
        dest.put(destOffset + 2, Math.copySign(_selfz, sign));
        dest.put(destOffset + 3, Math.copySign(_selfw, sign));
        return dest;
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ, float signW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ, signW);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ, float signW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, signX, signY, signZ, signW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, signX, signY, signZ, signW);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ, float signW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), signX));
        dest.put(destOffset + 1, Math.copySign(_selfy, signY));
        dest.put(destOffset + 2, Math.copySign(_selfz, signZ));
        dest.put(destOffset + 3, Math.copySign(_selfw, signW));
        return dest;
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + signOffset * 4L;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && sign.hasArray() && signOffset >= 0 && signOffset <= sign.limit() - 4) {
            Float4Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign.array(), sign.arrayOffset() + signOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign, signOffset);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _signy = sign.get(signOffset + 1);
        float _signz = sign.get(signOffset + 2);
        float _signw = sign.get(signOffset + 3);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), sign.get(signOffset)));
        dest.put(destOffset + 1, Math.copySign(_selfy, _signy));
        dest.put(destOffset + 2, Math.copySign(_selfz, _signz));
        dest.put(destOffset + 3, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static java.nio.FloatBuffer cos_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cos_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.cos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.cos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cos_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.cos(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cos(_selfy));
        dest.put(destOffset + 2, Math.cos(_selfz));
        dest.put(destOffset + 3, Math.cos(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer cosh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cosh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.cosh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.cosh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cosh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.cosh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cosh(_selfy));
        dest.put(destOffset + 2, Math.cosh(_selfz));
        dest.put(destOffset + 3, Math.cosh(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer cross_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.nio.FloatBuffer cross_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.cross(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.cross_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.nio.FloatBuffer cross_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_apiGet_fma(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return cross_apiGet_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
    }

    /** {@code cross_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer cross_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = java.lang.Math.fma(vY, wZ, _t6) - java.lang.Math.fma(vZ, wY, _t6);
        float _t25 = java.lang.Math.fma(vZ, wW, _t7) - java.lang.Math.fma(vW, wZ, _t7);
        float _t26 = java.lang.Math.fma(vY, wW, _t8) - java.lang.Math.fma(vW, wY, _t8);
        float _t27 = java.lang.Math.fma(vX, wZ, _t9) - java.lang.Math.fma(vZ, wX, _t9);
        float _t28 = java.lang.Math.fma(vX, wW, _t10) - java.lang.Math.fma(vW, wX, _t10);
        dest.put(destOffset, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        dest.put(destOffset + 1, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        return cross_apiGet_fma_sb4547be7_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t24, _t26, _t27, _t28, java.lang.Math.fma(vX, wY, _t11) - java.lang.Math.fma(vY, wX, _t11));
    }

    /** Piece 2 of {@code cross_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer cross_apiGet_fma_sb4547be7_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t24, float _t26, float _t27, float _t28, float _t29) {
        dest.put(destOffset + 2, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        dest.put(destOffset + 3, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer cross_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = ((vY) * (wZ) + (_t6)) - ((vZ) * (wY) + (_t6));
        float _t25 = ((vZ) * (wW) + (_t7)) - ((vW) * (wZ) + (_t7));
        float _t26 = ((vY) * (wW) + (_t8)) - ((vW) * (wY) + (_t8));
        float _t27 = ((vX) * (wZ) + (_t9)) - ((vZ) * (wX) + (_t9));
        float _t28 = ((vX) * (wW) + (_t10)) - ((vW) * (wX) + (_t10));
        float _t29 = ((vX) * (wY) + (_t11)) - ((vY) * (wX) + (_t11));
        dest.put(destOffset, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        dest.put(destOffset + 1, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        dest.put(destOffset + 2, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        dest.put(destOffset + 3, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
        return dest;
    }

    public static java.nio.FloatBuffer cross_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset, java.nio.FloatBuffer w, int wOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        long _wBase = UnsafeOpsHolder.U.getLong(w, UnsafeCopy.BB_ADDRESS_OFFSET) + wOffset * 4L;
        Float4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _vBase, _wBase);
        return dest;
    }

    public static java.nio.FloatBuffer cross_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset, java.nio.FloatBuffer w, int wOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4 && w.hasArray() && wOffset >= 0 && wOffset <= w.limit() - 4) {
            Float4Ops.cross(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset, w.array(), w.arrayOffset() + wOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.cross_apiGet(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cross_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset, java.nio.FloatBuffer w, int wOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_apiGet_fma(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
        return cross_apiGet_mulAdd(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
    }

    /** {@code cross_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer cross_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset, java.nio.FloatBuffer w, int wOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _vw = v.get(vOffset + 3);
        float _wx = w.get(wOffset);
        float _wy = w.get(wOffset + 1);
        float _wz = w.get(wOffset + 2);
        float _ww = w.get(wOffset + 3);
        float _t6 = -_vz * _wy;
        float _t7 = -_vw * _wz;
        float _t8 = -_vw * _wy;
        float _t9 = -_vz * _wx;
        float _t10 = -_vw * _wx;
        float _t11 = -(_vy * _wx);
        return cross_apiGet_fma_s1bd898cd_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, java.lang.Math.fma(_vy, _wz, _t6) - java.lang.Math.fma(_vz, _wy, _t6), java.lang.Math.fma(_vz, _ww, _t7) - java.lang.Math.fma(_vw, _wz, _t7), java.lang.Math.fma(_vy, _ww, _t8) - java.lang.Math.fma(_vw, _wy, _t8), java.lang.Math.fma(_vx, _wz, _t9) - java.lang.Math.fma(_vz, _wx, _t9), java.lang.Math.fma(_vx, _ww, _t10) - java.lang.Math.fma(_vw, _wx, _t10), java.lang.Math.fma(_vx, _wy, _t11) - java.lang.Math.fma(_vy, _wx, _t11));
    }

    /** Piece 2 of {@code cross_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer cross_apiGet_fma_s1bd898cd_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        dest.put(destOffset, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        dest.put(destOffset + 1, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        dest.put(destOffset + 3, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer cross_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset, java.nio.FloatBuffer w, int wOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _vw = v.get(vOffset + 3);
        float _wx = w.get(wOffset);
        float _wy = w.get(wOffset + 1);
        float _wz = w.get(wOffset + 2);
        float _ww = w.get(wOffset + 3);
        float _t6 = -_vz * _wy;
        float _t7 = -_vw * _wz;
        float _t8 = -_vw * _wy;
        float _t9 = -_vz * _wx;
        float _t10 = -_vw * _wx;
        float _t11 = -(_vy * _wx);
        return cross_apiGet_mulAdd_s6da8d3d8_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, ((_vy) * (_wz) + (_t6)) - ((_vz) * (_wy) + (_t6)), ((_vz) * (_ww) + (_t7)) - ((_vw) * (_wz) + (_t7)), ((_vy) * (_ww) + (_t8)) - ((_vw) * (_wy) + (_t8)), ((_vx) * (_wz) + (_t9)) - ((_vz) * (_wx) + (_t9)), ((_vx) * (_ww) + (_t10)) - ((_vw) * (_wx) + (_t10)), ((_vx) * (_wy) + (_t11)) - ((_vy) * (_wx) + (_t11)));
    }

    /** Piece 2 of {@code cross_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer cross_apiGet_mulAdd_s6da8d3d8_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        dest.put(destOffset, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        dest.put(destOffset + 1, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        dest.put(destOffset + 2, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        dest.put(destOffset + 3, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
        return dest;
    }

    public static java.nio.FloatBuffer degrees_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer degrees_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.degrees(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.degrees_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer degrees_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.toDegrees(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.toDegrees(_selfy));
        dest.put(destOffset + 2, Math.toDegrees(_selfz));
        dest.put(destOffset + 3, Math.toDegrees(_selfw));
        return dest;
    }

    public static float distance_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float distance_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.distance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float distance_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.get(srcOffset + 3) - otherW;
            float _t1 = src.get(srcOffset + 2) - otherZ;
            float _t2 = src.get(srcOffset) - otherX;
            float _t3 = src.get(srcOffset + 1) - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            float _t0 = src.get(srcOffset + 3) - otherW;
            float _t1 = src.get(srcOffset + 2) - otherZ;
            float _t2 = src.get(srcOffset) - otherX;
            float _t3 = src.get(srcOffset + 1) - otherY;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
        }
    }

    public static float distance_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float4OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4Ops.distance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float distance_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.get(srcOffset + 3) - other.get(otherOffset + 3);
            float _t1 = src.get(srcOffset + 2) - other.get(otherOffset + 2);
            float _t2 = src.get(srcOffset) - other.get(otherOffset);
            float _t3 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            float _t0 = src.get(srcOffset + 3) - other.get(otherOffset + 3);
            float _t1 = src.get(srcOffset + 2) - other.get(otherOffset + 2);
            float _t2 = src.get(srcOffset) - other.get(otherOffset);
            float _t3 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
        }
    }

    public static float distanceSquared_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float distanceSquared_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float distanceSquared_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.get(srcOffset + 3) - otherW;
            float _t1 = src.get(srcOffset + 2) - otherZ;
            float _t2 = src.get(srcOffset) - otherX;
            float _t3 = src.get(srcOffset + 1) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float _t0 = src.get(srcOffset + 3) - otherW;
            float _t1 = src.get(srcOffset + 2) - otherZ;
            float _t2 = src.get(srcOffset) - otherX;
            float _t3 = src.get(srcOffset + 1) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }

    public static float distanceSquared_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float distanceSquared_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.get(srcOffset + 3) - other.get(otherOffset + 3);
            float _t1 = src.get(srcOffset + 2) - other.get(otherOffset + 2);
            float _t2 = src.get(srcOffset) - other.get(otherOffset);
            float _t3 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float _t0 = src.get(srcOffset + 3) - other.get(otherOffset + 3);
            float _t1 = src.get(srcOffset + 2) - other.get(otherOffset + 2);
            float _t2 = src.get(srcOffset) - other.get(otherOffset);
            float _t3 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }

    public static float dot_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float dot_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.dot(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float dot_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, src.get(srcOffset + 3), java.lang.Math.fma(otherZ, src.get(srcOffset + 2), java.lang.Math.fma(otherX, src.get(srcOffset), otherY * src.get(srcOffset + 1))));
        } else {
            return ((otherW) * (src.get(srcOffset + 3)) + (((otherZ) * (src.get(srcOffset + 2)) + (((otherX) * (src.get(srcOffset)) + (otherY * src.get(srcOffset + 1)))))));
        }
    }

    public static float dot_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float4OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4Ops.dot(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float dot_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(other.get(otherOffset + 3), src.get(srcOffset + 3), java.lang.Math.fma(other.get(otherOffset + 2), src.get(srcOffset + 2), java.lang.Math.fma(other.get(otherOffset), src.get(srcOffset), other.get(otherOffset + 1) * src.get(srcOffset + 1))));
        } else {
            return ((other.get(otherOffset + 3)) * (src.get(srcOffset + 3)) + (((other.get(otherOffset + 2)) * (src.get(srcOffset + 2)) + (((other.get(otherOffset)) * (src.get(srcOffset)) + (other.get(otherOffset + 1) * src.get(srcOffset + 1)))))));
        }
    }

    public static java.nio.FloatBuffer exp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer exp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.exp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.exp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer exp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.exp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.exp(_selfy));
        dest.put(destOffset + 2, Math.exp(_selfz));
        dest.put(destOffset + 3, Math.exp(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer exp2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer exp2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.exp2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.exp2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer exp2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.pow(2.0f, src.get(srcOffset)));
        dest.put(destOffset + 1, Math.pow(2.0f, _selfy));
        dest.put(destOffset + 2, Math.pow(2.0f, _selfz));
        dest.put(destOffset + 3, Math.pow(2.0f, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer expm1_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer expm1_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.expm1(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.expm1_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer expm1_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.expm1(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.expm1(_selfy));
        dest.put(destOffset + 2, Math.expm1(_selfz));
        dest.put(destOffset + 3, Math.expm1(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            dest.put(destOffset, src.get(srcOffset) * _t4);
            dest.put(destOffset + 1, _selfy * _t4);
            dest.put(destOffset + 2, _selfz * _t4);
            dest.put(destOffset + 3, _selfw * _t4);
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            dest.put(destOffset, src.get(srcOffset) * _t4);
            dest.put(destOffset + 1, _selfy * _t4);
            dest.put(destOffset + 2, _selfz * _t4);
            dest.put(destOffset + 3, _selfw * _t4);
            return dest;
        }
    }

    public static java.nio.FloatBuffer faceforward_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + IOffset * 4L;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + NrefOffset * 4L;
        Float4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && I.hasArray() && IOffset >= 0 && IOffset <= I.limit() - 4 && Nref.hasArray() && NrefOffset >= 0 && NrefOffset <= Nref.limit() - 4) {
            Float4Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, I.array(), I.arrayOffset() + IOffset, Nref.array(), Nref.arrayOffset() + NrefOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_apiGet_mulAdd(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = java.lang.Math.fma(I.get(IOffset + 3), Nref.get(NrefOffset + 3), java.lang.Math.fma(I.get(IOffset + 2), Nref.get(NrefOffset + 2), java.lang.Math.fma(I.get(IOffset), Nref.get(NrefOffset), I.get(IOffset + 1) * Nref.get(NrefOffset + 1)))) < 0.0f ? 1.0f : -1.0f;
        dest.put(destOffset, src.get(srcOffset) * _t4);
        dest.put(destOffset + 1, _selfy * _t4);
        dest.put(destOffset + 2, _selfz * _t4);
        dest.put(destOffset + 3, _selfw * _t4);
        return dest;
    }

    /** {@code faceforward_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer faceforward_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = ((I.get(IOffset + 3)) * (Nref.get(NrefOffset + 3)) + (((I.get(IOffset + 2)) * (Nref.get(NrefOffset + 2)) + (((I.get(IOffset)) * (Nref.get(NrefOffset)) + (I.get(IOffset + 1) * Nref.get(NrefOffset + 1))))))) < 0.0f ? 1.0f : -1.0f;
        dest.put(destOffset, src.get(srcOffset) * _t4);
        dest.put(destOffset + 1, _selfy * _t4);
        dest.put(destOffset + 2, _selfz * _t4);
        dest.put(destOffset + 3, _selfw * _t4);
        return dest;
    }

    public static java.nio.FloatBuffer floor_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer floor_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.floor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.floor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer floor_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.floor(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.floor(_selfy));
        dest.put(destOffset + 2, Math.floor(_selfz));
        dest.put(destOffset + 3, Math.floor(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer fract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer fract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.fract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.fract_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer fract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        dest.put(destOffset + 1, Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        dest.put(destOffset + 2, Math.min(_selfz - Math.floor(_selfz), 0.99999994f));
        dest.put(destOffset + 3, Math.min(_selfw - Math.floor(_selfw), 0.99999994f));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), y));
        dest.put(destOffset + 1, Math.hypot(_selfy, y));
        dest.put(destOffset + 2, Math.hypot(_selfz, y));
        dest.put(destOffset + 3, Math.hypot(_selfw, y));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY, yZ, yW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), yX));
        dest.put(destOffset + 1, Math.hypot(_selfy, yY));
        dest.put(destOffset + 2, Math.hypot(_selfz, yZ));
        dest.put(destOffset + 3, Math.hypot(_selfw, yW));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset * 4L;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 4) {
            Float4Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _yy = y.get(yOffset + 1);
        float _yz = y.get(yOffset + 2);
        float _yw = y.get(yOffset + 3);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), y.get(yOffset)));
        dest.put(destOffset + 1, Math.hypot(_selfy, _yy));
        dest.put(destOffset + 2, Math.hypot(_selfz, _yz));
        dest.put(destOffset + 3, Math.hypot(_selfw, _yw));
        return dest;
    }

    public static java.nio.FloatBuffer inverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer inverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.inverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.inverse_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer inverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, 1.0f / src.get(srcOffset));
        dest.put(destOffset + 1, 1.0f / _selfy);
        dest.put(destOffset + 2, 1.0f / _selfz);
        dest.put(destOffset + 3, 1.0f / _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.inverseSqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.inverseSqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, (1.0f / (float) java.lang.Math.sqrt(src.get(srcOffset))));
        dest.put(destOffset + 1, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        dest.put(destOffset + 2, (1.0f / (float) java.lang.Math.sqrt(_selfz)));
        dest.put(destOffset + 3, (1.0f / (float) java.lang.Math.sqrt(_selfw)));
        return dest;
    }

    public static float length_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.length(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.length_apiGet(src, srcOffset);
    }

    public static float length_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))));
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            return (float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))))));
        }
    }

    public static float lengthSquared_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.lengthSquared(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.lengthSquared_apiGet(src, srcOffset);
    }

    public static float lengthSquared_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            return java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            return ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        }
    }

    public static java.nio.FloatBuffer log_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.log(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.log_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.log(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log(_selfy));
        dest.put(destOffset + 2, Math.log(_selfz));
        dest.put(destOffset + 3, Math.log(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer log10_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log10_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.log10(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.log10_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log10_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.log10(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log10(_selfy));
        dest.put(destOffset + 2, Math.log10(_selfz));
        dest.put(destOffset + 3, Math.log10(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer log1p_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log1p_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.log1p(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.log1p_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log1p_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.log1p(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log1p(_selfy));
        dest.put(destOffset + 2, Math.log1p(_selfz));
        dest.put(destOffset + 3, Math.log1p(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer log2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.log2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.log2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.log2(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log2(_selfy));
        dest.put(destOffset + 2, Math.log2(_selfz));
        dest.put(destOffset + 3, Math.log2(_selfw));
        return dest;
    }

    public static float manhattanDistance_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float manhattanDistance_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float manhattanDistance_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return java.lang.Math.abs(src.get(srcOffset) - otherX) + java.lang.Math.abs(src.get(srcOffset + 1) - otherY) + java.lang.Math.abs(src.get(srcOffset + 2) - otherZ) + java.lang.Math.abs(src.get(srcOffset + 3) - otherW);
    }

    public static float manhattanDistance_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static float manhattanDistance_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float manhattanDistance_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        return java.lang.Math.abs(src.get(srcOffset) - other.get(otherOffset)) + java.lang.Math.abs(src.get(srcOffset + 1) - other.get(otherOffset + 1)) + java.lang.Math.abs(src.get(srcOffset + 2) - other.get(otherOffset + 2)) + java.lang.Math.abs(src.get(srcOffset + 3) - other.get(otherOffset + 3));
    }

    public static float manhattanLength_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float4OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.manhattanLength(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.manhattanLength_apiGet(src, srcOffset);
    }

    public static float manhattanLength_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return java.lang.Math.abs(src.get(srcOffset)) + java.lang.Math.abs(src.get(srcOffset + 1)) + java.lang.Math.abs(src.get(srcOffset + 2)) + java.lang.Math.abs(src.get(srcOffset + 3));
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.max(src.get(srcOffset), scalar));
        dest.put(destOffset + 1, Math.max(_selfy, scalar));
        dest.put(destOffset + 2, Math.max(_selfz, scalar));
        dest.put(destOffset + 3, Math.max(_selfw, scalar));
        return dest;
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.max(src.get(srcOffset), otherX));
        dest.put(destOffset + 1, Math.max(_selfy, otherY));
        dest.put(destOffset + 2, Math.max(_selfz, otherZ));
        dest.put(destOffset + 3, Math.max(_selfw, otherW));
        return dest;
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, Math.max(src.get(srcOffset), other.get(otherOffset)));
        dest.put(destOffset + 1, Math.max(_selfy, _othery));
        dest.put(destOffset + 2, Math.max(_selfz, _otherz));
        dest.put(destOffset + 3, Math.max(_selfw, _otherw));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.min(src.get(srcOffset), scalar));
        dest.put(destOffset + 1, Math.min(_selfy, scalar));
        dest.put(destOffset + 2, Math.min(_selfz, scalar));
        dest.put(destOffset + 3, Math.min(_selfw, scalar));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.min(src.get(srcOffset), otherX));
        dest.put(destOffset + 1, Math.min(_selfy, otherY));
        dest.put(destOffset + 2, Math.min(_selfz, otherZ));
        dest.put(destOffset + 3, Math.min(_selfw, otherW));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, Math.min(src.get(srcOffset), other.get(otherOffset)));
        dest.put(destOffset + 1, Math.min(_selfy, _othery));
        dest.put(destOffset + 2, Math.min(_selfz, _otherz));
        dest.put(destOffset + 3, Math.min(_selfw, _otherw));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, flooredMod(_selfx, y));
        dest.put(destOffset + 1, flooredMod(_selfy, y));
        dest.put(destOffset + 2, flooredMod(_selfz, y));
        dest.put(destOffset + 3, flooredMod(_selfw, y));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY, yZ, yW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, flooredMod(_selfx, yX));
        dest.put(destOffset + 1, flooredMod(_selfy, yY));
        dest.put(destOffset + 2, flooredMod(_selfz, yZ));
        dest.put(destOffset + 3, flooredMod(_selfw, yW));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset * 4L;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 4) {
            Float4Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _yx = y.get(yOffset);
        float _yy = y.get(yOffset + 1);
        float _yz = y.get(yOffset + 2);
        float _yw = y.get(yOffset + 3);
        dest.put(destOffset, flooredMod(_selfx, _yx));
        dest.put(destOffset + 1, flooredMod(_selfy, _yy));
        dest.put(destOffset + 2, flooredMod(_selfz, _yz));
        dest.put(destOffset + 3, flooredMod(_selfw, _yw));
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.nextDown(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.nextDown_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.nextDown(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.nextDown(_selfy));
        dest.put(destOffset + 2, Math.nextDown(_selfz));
        dest.put(destOffset + 3, Math.nextDown(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.nextUp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.nextUp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.nextUp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.nextUp(_selfy));
        dest.put(destOffset + 2, Math.nextUp(_selfz));
        dest.put(destOffset + 3, Math.nextUp(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer normalize_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalize_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.normalize(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.normalize_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalize_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.put(destOffset, _selfx * _t4);
            dest.put(destOffset + 1, _selfy * _t4);
            dest.put(destOffset + 2, _selfz * _t4);
            dest.put(destOffset + 3, _selfw * _t4);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code normalize_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer normalize_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.put(destOffset, _selfx * _t4);
            dest.put(destOffset + 1, _selfy * _t4);
            dest.put(destOffset + 2, _selfz * _t4);
            dest.put(destOffset + 3, _selfw * _t4);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.normalizeMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, length);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.normalizeMul_apiGet(dest, destOffset, src, srcOffset, length);
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizeMul_apiGet_mulAdd(dest, destOffset, src, srcOffset, length);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.put(destOffset, _selfx * _t5);
            dest.put(destOffset + 1, _selfy * _t5);
            dest.put(destOffset + 2, _selfz * _t5);
            dest.put(destOffset + 3, _selfw * _t5);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code normalizeMul_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer normalizeMul_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.put(destOffset, _selfx * _t5);
            dest.put(destOffset + 1, _selfy * _t5);
            dest.put(destOffset + 2, _selfz * _t5);
            dest.put(destOffset + 3, _selfw * _t5);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ, rowW);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rowX, rowY, rowZ, rowW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, rowX * _selfx);
        dest.put(destOffset + 1, rowX * _selfy);
        dest.put(destOffset + 2, rowX * _selfz);
        dest.put(destOffset + 3, rowX * _selfw);
        dest.put(destOffset + 4, rowY * _selfx);
        dest.put(destOffset + 5, rowY * _selfy);
        dest.put(destOffset + 6, rowY * _selfz);
        dest.put(destOffset + 7, rowY * _selfw);
        dest.put(destOffset + 8, rowZ * _selfx);
        dest.put(destOffset + 9, rowZ * _selfy);
        dest.put(destOffset + 10, rowZ * _selfz);
        dest.put(destOffset + 11, rowZ * _selfw);
        dest.put(destOffset + 12, rowW * _selfx);
        dest.put(destOffset + 13, rowW * _selfy);
        dest.put(destOffset + 14, rowW * _selfz);
        dest.put(destOffset + 15, rowW * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset * 4L;
        Float4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 4) {
            Float4Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _rowx = row.get(rowOffset);
        float _rowy = row.get(rowOffset + 1);
        float _rowz = row.get(rowOffset + 2);
        float _roww = row.get(rowOffset + 3);
        dest.put(destOffset, _rowx * _selfx);
        dest.put(destOffset + 1, _rowx * _selfy);
        dest.put(destOffset + 2, _rowx * _selfz);
        dest.put(destOffset + 3, _rowx * _selfw);
        dest.put(destOffset + 4, _rowy * _selfx);
        dest.put(destOffset + 5, _rowy * _selfy);
        dest.put(destOffset + 6, _rowy * _selfz);
        dest.put(destOffset + 7, _rowy * _selfw);
        dest.put(destOffset + 8, _rowz * _selfx);
        dest.put(destOffset + 9, _rowz * _selfy);
        dest.put(destOffset + 10, _rowz * _selfz);
        dest.put(destOffset + 11, _rowz * _selfw);
        dest.put(destOffset + 12, _roww * _selfx);
        dest.put(destOffset + 13, _roww * _selfy);
        dest.put(destOffset + 14, _roww * _selfz);
        dest.put(destOffset + 15, _roww * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponent));
        dest.put(destOffset + 1, Math.pow(_selfy, exponent));
        dest.put(destOffset + 2, Math.pow(_selfz, exponent));
        dest.put(destOffset + 3, Math.pow(_selfw, exponent));
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponentX, exponentY, exponentZ, exponentW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponentX));
        dest.put(destOffset + 1, Math.pow(_selfy, exponentY));
        dest.put(destOffset + 2, Math.pow(_selfz, exponentZ));
        dest.put(destOffset + 3, Math.pow(_selfw, exponentW));
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + exponentOffset * 4L;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && exponent.hasArray() && exponentOffset >= 0 && exponentOffset <= exponent.limit() - 4) {
            Float4Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent.array(), exponent.arrayOffset() + exponentOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _exponenty = exponent.get(exponentOffset + 1);
        float _exponentz = exponent.get(exponentOffset + 2);
        float _exponentw = exponent.get(exponentOffset + 3);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponent.get(exponentOffset)));
        dest.put(destOffset + 1, Math.pow(_selfy, _exponenty));
        dest.put(destOffset + 2, Math.pow(_selfz, _exponentz));
        dest.put(destOffset + 3, Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static java.nio.FloatBuffer project_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.nio.FloatBuffer project_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, ontoX, ontoY, ontoZ, ontoW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.nio.FloatBuffer project_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t9 = java.lang.Math.fma(ontoW, src.get(srcOffset + 3), java.lang.Math.fma(ontoZ, src.get(srcOffset + 2), java.lang.Math.fma(ontoX, src.get(srcOffset), ontoY * src.get(srcOffset + 1)))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            dest.put(destOffset, ontoX * _t9);
            dest.put(destOffset + 1, ontoY * _t9);
            dest.put(destOffset + 2, ontoZ * _t9);
            dest.put(destOffset + 3, ontoW * _t9);
            return dest;
        } else {
            float _t9 = ((ontoW) * (src.get(srcOffset + 3)) + (((ontoZ) * (src.get(srcOffset + 2)) + (((ontoX) * (src.get(srcOffset)) + (ontoY * src.get(srcOffset + 1))))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            dest.put(destOffset, ontoX * _t9);
            dest.put(destOffset + 1, ontoY * _t9);
            dest.put(destOffset + 2, ontoZ * _t9);
            dest.put(destOffset + 3, ontoW * _t9);
            return dest;
        }
    }

    public static java.nio.FloatBuffer project_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + ontoOffset * 4L;
        Float4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.FloatBuffer project_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && onto.hasArray() && ontoOffset >= 0 && ontoOffset <= onto.limit() - 4) {
            Float4Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, onto.array(), onto.arrayOffset() + ontoOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return dest;
    }

    public static java.nio.FloatBuffer project_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_apiGet_mulAdd(dest, destOffset, src, srcOffset, onto, ontoOffset);
        float _ontox = onto.get(ontoOffset);
        float _ontoy = onto.get(ontoOffset + 1);
        float _ontoz = onto.get(ontoOffset + 2);
        float _ontow = onto.get(ontoOffset + 3);
        float _t9 = java.lang.Math.fma(_ontow, src.get(srcOffset + 3), java.lang.Math.fma(_ontoz, src.get(srcOffset + 2), java.lang.Math.fma(_ontox, src.get(srcOffset), _ontoy * src.get(srcOffset + 1)))) / java.lang.Math.fma(_ontow, _ontow, java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest.put(destOffset, _ontox * _t9);
        dest.put(destOffset + 1, _ontoy * _t9);
        dest.put(destOffset + 2, _ontoz * _t9);
        dest.put(destOffset + 3, _ontow * _t9);
        return dest;
    }

    /** {@code project_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer project_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        float _ontox = onto.get(ontoOffset);
        float _ontoy = onto.get(ontoOffset + 1);
        float _ontoz = onto.get(ontoOffset + 2);
        float _ontow = onto.get(ontoOffset + 3);
        float _t9 = ((_ontow) * (src.get(srcOffset + 3)) + (((_ontoz) * (src.get(srcOffset + 2)) + (((_ontox) * (src.get(srcOffset)) + (_ontoy * src.get(srcOffset + 1))))))) / ((_ontow) * (_ontow) + (((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))))));
        dest.put(destOffset, _ontox * _t9);
        dest.put(destOffset + 1, _ontoy * _t9);
        dest.put(destOffset + 2, _ontoz * _t9);
        dest.put(destOffset + 3, _ontow * _t9);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, normalW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t3 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
            dest.put(destOffset, java.lang.Math.fma(-normalX, _t3, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(-normalY, _t3, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(-normalZ, _t3, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(-normalW, _t3, _selfw));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t3 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
            dest.put(destOffset, ((_selfx) - (normalX) * (_t3)));
            dest.put(destOffset + 1, ((_selfy) - (normalY) * (_t3)));
            dest.put(destOffset + 2, ((_selfz) - (normalZ) * (_t3)));
            dest.put(destOffset + 3, ((_selfw) - (normalW) * (_t3)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer projectOnPlane_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 4L;
        Float4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 4) {
            Float4Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_apiGet_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _normalx = normal.get(normalOffset);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _normalw = normal.get(normalOffset + 3);
        float _t3 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.put(destOffset, java.lang.Math.fma(-_normalx, _t3, _selfx));
        dest.put(destOffset + 1, java.lang.Math.fma(-_normaly, _t3, _selfy));
        dest.put(destOffset + 2, java.lang.Math.fma(-_normalz, _t3, _selfz));
        dest.put(destOffset + 3, java.lang.Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    /** {@code projectOnPlane_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer projectOnPlane_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _normalx = normal.get(normalOffset);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _normalw = normal.get(normalOffset + 3);
        float _t3 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        dest.put(destOffset, ((_selfx) - (_normalx) * (_t3)));
        dest.put(destOffset + 1, ((_selfy) - (_normaly) * (_t3)));
        dest.put(destOffset + 2, ((_selfz) - (_normalz) * (_t3)));
        dest.put(destOffset + 3, ((_selfw) - (_normalw) * (_t3)));
        return dest;
    }

    public static java.nio.FloatBuffer radians_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer radians_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.radians(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.radians_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer radians_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.toRadians(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.toRadians(_selfy));
        dest.put(destOffset + 2, Math.toRadians(_selfz));
        dest.put(destOffset + 3, Math.toRadians(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer reflect_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, normalW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t4 = 2.0f * java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
            dest.put(destOffset, java.lang.Math.fma(-normalX, _t4, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(-normalY, _t4, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(-normalZ, _t4, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(-normalW, _t4, _selfw));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t4 = 2.0f * ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
            dest.put(destOffset, ((_selfx) - (normalX) * (_t4)));
            dest.put(destOffset + 1, ((_selfy) - (normalY) * (_t4)));
            dest.put(destOffset + 2, ((_selfz) - (normalZ) * (_t4)));
            dest.put(destOffset + 3, ((_selfw) - (normalW) * (_t4)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer reflect_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 4L;
        Float4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 4) {
            Float4Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_apiGet_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _normalx = normal.get(normalOffset);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _normalw = normal.get(normalOffset + 3);
        float _t4 = 2.0f * java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.put(destOffset, java.lang.Math.fma(-_normalx, _t4, _selfx));
        dest.put(destOffset + 1, java.lang.Math.fma(-_normaly, _t4, _selfy));
        dest.put(destOffset + 2, java.lang.Math.fma(-_normalz, _t4, _selfz));
        dest.put(destOffset + 3, java.lang.Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    /** {@code reflect_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer reflect_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _normalx = normal.get(normalOffset);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _normalw = normal.get(normalOffset + 3);
        float _t4 = 2.0f * ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        dest.put(destOffset, ((_selfx) - (_normalx) * (_t4)));
        dest.put(destOffset + 1, ((_selfy) - (_normaly) * (_t4)));
        dest.put(destOffset + 2, ((_selfz) - (_normalz) * (_t4)));
        dest.put(destOffset + 3, ((_selfw) - (_normalw) * (_t4)));
        return dest;
    }

    public static java.nio.FloatBuffer refract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, normalW, eta);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_apiGet_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        float _t11 = java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dest.put(destOffset, java.lang.Math.fma(eta, _selfx, -(normalX * _t11)));
            dest.put(destOffset + 1, java.lang.Math.fma(eta, _selfy, -(normalY * _t11)));
            dest.put(destOffset + 2, java.lang.Math.fma(eta, _selfz, -(normalZ * _t11)));
            dest.put(destOffset + 3, java.lang.Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code refract_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer refract_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        float _t8 = ((((_t4) * (_t4) - (1.0f))) * (eta * eta) + (1.0f));
        float _t11 = ((eta) * (_t4) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
        if (_t8 >= 0.0f) {
            dest.put(destOffset, ((eta) * (_selfx) - (normalX * _t11)));
            dest.put(destOffset + 1, ((eta) * (_selfy) - (normalY * _t11)));
            dest.put(destOffset + 2, ((eta) * (_selfz) - (normalZ * _t11)));
            dest.put(destOffset + 3, ((eta) * (_selfw) - (normalW * _t11)));
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer refract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 4L;
        Float4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 4) {
            Float4Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset, eta);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_apiGet_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _normalx = normal.get(normalOffset);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _normalw = normal.get(normalOffset + 3);
        float _t4 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        float _t11 = java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dest.put(destOffset, java.lang.Math.fma(eta, _selfx, -(_normalx * _t11)));
            dest.put(destOffset + 1, java.lang.Math.fma(eta, _selfy, -(_normaly * _t11)));
            dest.put(destOffset + 2, java.lang.Math.fma(eta, _selfz, -(_normalz * _t11)));
            dest.put(destOffset + 3, java.lang.Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code refract_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer refract_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _normalx = normal.get(normalOffset);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _normalw = normal.get(normalOffset + 3);
        float _t4 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        float _t8 = ((((_t4) * (_t4) - (1.0f))) * (eta * eta) + (1.0f));
        float _t11 = ((eta) * (_t4) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
        if (_t8 >= 0.0f) {
            dest.put(destOffset, ((eta) * (_selfx) - (_normalx * _t11)));
            dest.put(destOffset + 1, ((eta) * (_selfy) - (_normaly * _t11)));
            dest.put(destOffset + 2, ((eta) * (_selfz) - (_normalz * _t11)));
            dest.put(destOffset + 3, ((eta) * (_selfw) - (_normalw * _t11)));
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer round_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer round_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.round(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.round_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer round_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.rint(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.rint(_selfy));
        dest.put(destOffset + 2, Math.rint(_selfz));
        dest.put(destOffset + 3, Math.rint(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer sign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.sign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sign_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.signum(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.signum(_selfy));
        dest.put(destOffset + 2, Math.signum(_selfz));
        dest.put(destOffset + 3, Math.signum(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer sin_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sin_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.sin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sin_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.sin(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.sin(_selfy));
        dest.put(destOffset + 2, Math.sin(_selfz));
        dest.put(destOffset + 3, Math.sin(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer sinh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sinh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.sinh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sinh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sinh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.sinh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.sinh(_selfy));
        dest.put(destOffset + 2, Math.sinh(_selfz));
        dest.put(destOffset + 3, Math.sinh(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0, edge1);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge1);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_apiGet_mulAdd(dest, destOffset, src, srcOffset, edge0, edge1);
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset) - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 1) - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 2) - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 3) - edge0) * _t0_inv));
        dest.put(destOffset, java.lang.Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.put(destOffset + 1, java.lang.Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        dest.put(destOffset + 2, java.lang.Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15);
        dest.put(destOffset + 3, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        return dest;
    }

    /** {@code smoothstep_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer smoothstep_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset) - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 1) - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 2) - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 3) - edge0) * _t0_inv));
        dest.put(destOffset, ((3.0f) - (2.0f) * (_t13)) * _t13 * _t13);
        dest.put(destOffset + 1, ((3.0f) - (2.0f) * (_t14)) * _t14 * _t14);
        dest.put(destOffset + 2, ((3.0f) - (2.0f) * (_t15)) * _t15 * _t15);
        dest.put(destOffset + 3, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_apiGet_mulAdd(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 1) - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 2) - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 3) - edge0W) / (edge1W - edge0W)));
        dest.put(destOffset, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        dest.put(destOffset + 1, java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        dest.put(destOffset + 2, java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        dest.put(destOffset + 3, java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer smoothstep_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 1) - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 2) - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 3) - edge0W) / (edge1W - edge0W)));
        dest.put(destOffset, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        dest.put(destOffset + 1, ((3.0f) - (2.0f) * (_t17)) * _t17 * _t17);
        dest.put(destOffset + 2, ((3.0f) - (2.0f) * (_t18)) * _t18 * _t18);
        dest.put(destOffset + 3, ((3.0f) - (2.0f) * (_t19)) * _t19 * _t19);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + edge0Offset * 4L;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + edge1Offset * 4L;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && edge0.hasArray() && edge0Offset >= 0 && edge0Offset <= edge0.limit() - 4 && edge1.hasArray() && edge1Offset >= 0 && edge1Offset <= edge1.limit() - 4) {
            Float4Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0.array(), edge0.arrayOffset() + edge0Offset, edge1.array(), edge1.arrayOffset() + edge1Offset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_apiGet_fma(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return smoothstep_apiGet_mulAdd(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@code smoothstep_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer smoothstep_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        float _edge0x = edge0.get(edge0Offset);
        float _edge0y = edge0.get(edge0Offset + 1);
        float _edge0z = edge0.get(edge0Offset + 2);
        float _edge0w = edge0.get(edge0Offset + 3);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset) - _edge0x) / (edge1.get(edge1Offset) - _edge0x)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 1) - _edge0y) / (edge1.get(edge1Offset + 1) - _edge0y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 2) - _edge0z) / (edge1.get(edge1Offset + 2) - _edge0z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 3) - _edge0w) / (edge1.get(edge1Offset + 3) - _edge0w)));
        dest.put(destOffset, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        dest.put(destOffset + 1, java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        dest.put(destOffset + 2, java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        dest.put(destOffset + 3, java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer smoothstep_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        float _edge0x = edge0.get(edge0Offset);
        float _edge0y = edge0.get(edge0Offset + 1);
        float _edge0z = edge0.get(edge0Offset + 2);
        float _edge0w = edge0.get(edge0Offset + 3);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset) - _edge0x) / (edge1.get(edge1Offset) - _edge0x)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 1) - _edge0y) / (edge1.get(edge1Offset + 1) - _edge0y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 2) - _edge0z) / (edge1.get(edge1Offset + 2) - _edge0z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.get(srcOffset + 3) - _edge0w) / (edge1.get(edge1Offset + 3) - _edge0w)));
        dest.put(destOffset, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        dest.put(destOffset + 1, ((3.0f) - (2.0f) * (_t17)) * _t17 * _t17);
        dest.put(destOffset + 2, ((3.0f) - (2.0f) * (_t18)) * _t18 * _t18);
        dest.put(destOffset + 3, ((3.0f) - (2.0f) * (_t19)) * _t19 * _t19);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.sqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, (float) java.lang.Math.sqrt(src.get(srcOffset)));
        dest.put(destOffset + 1, (float) java.lang.Math.sqrt(_selfy));
        dest.put(destOffset + 2, (float) java.lang.Math.sqrt(_selfz));
        dest.put(destOffset + 3, (float) java.lang.Math.sqrt(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) < edge ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < edge ? 0.0f : 1.0f);
        dest.put(destOffset + 2, _selfz < edge ? 0.0f : 1.0f);
        dest.put(destOffset + 3, _selfw < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edgeX, edgeY, edgeZ, edgeW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) < edgeX ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < edgeY ? 0.0f : 1.0f);
        dest.put(destOffset + 2, _selfz < edgeZ ? 0.0f : 1.0f);
        dest.put(destOffset + 3, _selfw < edgeW ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + edgeOffset * 4L;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && edge.hasArray() && edgeOffset >= 0 && edgeOffset <= edge.limit() - 4) {
            Float4Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge.array(), edge.arrayOffset() + edgeOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _edgey = edge.get(edgeOffset + 1);
        float _edgez = edge.get(edgeOffset + 2);
        float _edgew = edge.get(edgeOffset + 3);
        dest.put(destOffset, src.get(srcOffset) < edge.get(edgeOffset) ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < _edgey ? 0.0f : 1.0f);
        dest.put(destOffset + 2, _selfz < _edgez ? 0.0f : 1.0f);
        dest.put(destOffset + 3, _selfw < _edgew ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer tan_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer tan_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.tan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.tan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer tan_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.tan(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.tan(_selfy));
        dest.put(destOffset + 2, Math.tan(_selfz));
        dest.put(destOffset + 3, Math.tan(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer tanh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer tanh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.tanh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.tanh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer tanh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.tanh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.tanh(_selfy));
        dest.put(destOffset + 2, Math.tanh(_selfz));
        dest.put(destOffset + 3, Math.tanh(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer trunc_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer trunc_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.trunc(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.trunc_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer trunc_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.put(destOffset + 1, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.put(destOffset + 2, _selfz >= 0.0f ? Math.floor(_selfz) : Math.ceil(_selfz));
        dest.put(destOffset + 3, _selfw >= 0.0f ? Math.floor(_selfw) : Math.ceil(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer ulp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer ulp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.ulp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.ulp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer ulp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.ulp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.ulp(_selfy));
        dest.put(destOffset + 2, Math.ulp(_selfz));
        dest.put(destOffset + 3, Math.ulp(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer preMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 4L;
        Float4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Float4Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_apiGet_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_apiGet_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMul_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer preMul_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _mat10 = mat.get(matOffset + 1);
        float _mat20 = mat.get(matOffset + 2);
        float _mat30 = mat.get(matOffset + 3);
        float _mat11 = mat.get(matOffset + 5);
        float _mat21 = mat.get(matOffset + 6);
        float _mat31 = mat.get(matOffset + 7);
        float _mat12 = mat.get(matOffset + 9);
        float _mat22 = mat.get(matOffset + 10);
        float _mat32 = mat.get(matOffset + 11);
        float _mat13 = mat.get(matOffset + 13);
        float _mat23 = mat.get(matOffset + 14);
        float _mat33 = mat.get(matOffset + 15);
        dest.put(destOffset, java.lang.Math.fma(mat.get(matOffset + 12), _selfw, java.lang.Math.fma(mat.get(matOffset + 8), _selfz, java.lang.Math.fma(mat.get(matOffset), _selfx, mat.get(matOffset + 4) * _selfy))));
        dest.put(destOffset + 1, java.lang.Math.fma(_mat13, _selfw, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        return preMul_apiGet_fma_s72083f28_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _mat20, _mat30, _mat21, _mat31, _mat22, _mat32, _mat23, _mat33);
    }

    /** Piece 2 of {@code preMul_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer preMul_apiGet_fma_s72083f28_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _mat20, float _mat30, float _mat21, float _mat31, float _mat22, float _mat32, float _mat23, float _mat33) {
        dest.put(destOffset + 2, java.lang.Math.fma(_mat23, _selfw, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        dest.put(destOffset + 3, java.lang.Math.fma(_mat33, _selfw, java.lang.Math.fma(_mat32, _selfz, java.lang.Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    /** {@code preMul_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer preMul_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _mat10 = mat.get(matOffset + 1);
        float _mat20 = mat.get(matOffset + 2);
        float _mat30 = mat.get(matOffset + 3);
        float _mat11 = mat.get(matOffset + 5);
        float _mat21 = mat.get(matOffset + 6);
        float _mat31 = mat.get(matOffset + 7);
        float _mat12 = mat.get(matOffset + 9);
        float _mat22 = mat.get(matOffset + 10);
        float _mat32 = mat.get(matOffset + 11);
        float _mat13 = mat.get(matOffset + 13);
        float _mat23 = mat.get(matOffset + 14);
        float _mat33 = mat.get(matOffset + 15);
        dest.put(destOffset, ((mat.get(matOffset + 12)) * (_selfw) + (((mat.get(matOffset + 8)) * (_selfz) + (((mat.get(matOffset)) * (_selfx) + (mat.get(matOffset + 4) * _selfy)))))));
        dest.put(destOffset + 1, ((_mat13) * (_selfw) + (((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))))));
        return preMul_apiGet_mulAdd_s179c05d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _mat20, _mat30, _mat21, _mat31, _mat22, _mat32, _mat23, _mat33);
    }

    /** Piece 2 of {@code preMul_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer preMul_apiGet_mulAdd_s179c05d_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _mat20, float _mat30, float _mat21, float _mat31, float _mat22, float _mat32, float _mat23, float _mat33) {
        dest.put(destOffset + 2, ((_mat23) * (_selfw) + (((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))))));
        dest.put(destOffset + 3, ((_mat33) * (_selfw) + (((_mat32) * (_selfz) + (((_mat30) * (_selfx) + (_mat31 * _selfy)))))));
        return dest;
    }

    public static java.nio.FloatBuffer rotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_apiGet_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.put(destOffset, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.put(destOffset + 1, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        dest.put(destOffset + 2, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    /** {@code rotate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotate_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((quatX) * (_selfy) - (quatY * _selfx));
        float _t10 = 2.0f * ((quatZ) * (_selfx) - (quatX * _selfz));
        float _t11 = 2.0f * ((quatY) * (_selfz) - (quatZ * _selfy));
        dest.put(destOffset, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        dest.put(destOffset + 1, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        dest.put(destOffset + 2, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset * 4L;
        Float4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4) {
            Float4Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_apiGet_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _quatx = quat.get(quatOffset);
        float _quaty = quat.get(quatOffset + 1);
        float _quatz = quat.get(quatOffset + 2);
        float _quatw = quat.get(quatOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.put(destOffset, java.lang.Math.fma(_quaty, _t9, java.lang.Math.fma(-_quatz, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.put(destOffset + 1, java.lang.Math.fma(_quatz, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfy))));
        dest.put(destOffset + 2, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quaty, _t11, java.lang.Math.fma(_quatw, _t9, _selfz))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    /** {@code rotate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotate_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _quatx = quat.get(quatOffset);
        float _quaty = quat.get(quatOffset + 1);
        float _quatz = quat.get(quatOffset + 2);
        float _quatw = quat.get(quatOffset + 3);
        float _t9 = 2.0f * ((_quatx) * (_selfy) - (_quaty * _selfx));
        float _t10 = 2.0f * ((_quatz) * (_selfx) - (_quatx * _selfz));
        float _t11 = 2.0f * ((_quaty) * (_selfz) - (_quatz * _selfy));
        dest.put(destOffset, ((_quaty) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quatz) * (_t10)))));
        dest.put(destOffset + 1, ((_quatz) * (_t11) + (((((_quatw) * (_t10) + (_selfy))) - (_quatx) * (_t9)))));
        dest.put(destOffset + 2, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfz))) - (_quaty) * (_t11)))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_apiGet_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        dest.put(destOffset, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        dest.put(destOffset + 1, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        dest.put(destOffset + 2, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    /** {@code rotateAxis_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateAxis_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        dest.put(destOffset, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        dest.put(destOffset + 1, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        dest.put(destOffset + 2, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 4L;
        Float4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Float4Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_apiGet_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        float _axisx = axis.get(axisOffset);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.put(destOffset, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        dest.put(destOffset + 1, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        dest.put(destOffset + 2, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    /** {@code rotateAxis_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateAxis_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        float _axisx = axis.get(axisOffset);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        dest.put(destOffset, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        dest.put(destOffset + 1, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        dest.put(destOffset + 2, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotateInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateInverse_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_apiGet_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.put(destOffset, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.put(destOffset + 1, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        dest.put(destOffset + 2, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    /** {@code rotateInverse_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateInverse_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((quatX) * (_selfz) - (quatZ * _selfx));
        float _t10 = 2.0f * ((quatY) * (_selfx) - (quatX * _selfy));
        float _t11 = 2.0f * ((quatZ) * (_selfy) - (quatY * _selfz));
        dest.put(destOffset, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        dest.put(destOffset + 1, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        dest.put(destOffset + 2, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset * 4L;
        Float4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4) {
            Float4Ops.rotateInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateInverse_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_apiGet_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _quatx = quat.get(quatOffset);
        float _quaty = quat.get(quatOffset + 1);
        float _quatz = quat.get(quatOffset + 2);
        float _quatw = quat.get(quatOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.put(destOffset, java.lang.Math.fma(_quatz, _t9, java.lang.Math.fma(-_quaty, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.put(destOffset + 1, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quatz, _t11, java.lang.Math.fma(_quatw, _t9, _selfy))));
        dest.put(destOffset + 2, java.lang.Math.fma(_quaty, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfz))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    /** {@code rotateInverse_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateInverse_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _quatx = quat.get(quatOffset);
        float _quaty = quat.get(quatOffset + 1);
        float _quatz = quat.get(quatOffset + 2);
        float _quatw = quat.get(quatOffset + 3);
        float _t9 = 2.0f * ((_quatx) * (_selfz) - (_quatz * _selfx));
        float _t10 = 2.0f * ((_quaty) * (_selfx) - (_quatx * _selfy));
        float _t11 = 2.0f * ((_quatz) * (_selfy) - (_quaty * _selfz));
        dest.put(destOffset, ((_quatz) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quaty) * (_t10)))));
        dest.put(destOffset + 1, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfy))) - (_quatz) * (_t11)))));
        dest.put(destOffset + 2, ((_quaty) * (_t11) + (((((_quatw) * (_t10) + (_selfz))) - (_quatx) * (_t9)))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, src.get(srcOffset));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
            dest.put(destOffset + 3, _selfw);
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, src.get(srcOffset));
            dest.put(destOffset + 1, ((_selfy) * (_t0) - (_selfz * _t1)));
            dest.put(destOffset + 2, ((_selfy) * (_t1) + (_selfz * _t0)));
            dest.put(destOffset + 3, _selfw);
            return dest;
        }
    }

    public static java.nio.FloatBuffer rotateY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
            dest.put(destOffset + 3, _selfw);
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, ((_selfx) * (_t0) + (_selfz * _t1)));
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, ((_selfz) * (_t0) - (_selfx * _t1)));
            dest.put(destOffset + 3, _selfw);
            return dest;
        }
    }

    public static java.nio.FloatBuffer rotateZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
            dest.put(destOffset + 2, _selfz);
            dest.put(destOffset + 3, _selfw);
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, ((_selfx) * (_t0) - (_selfy * _t1)));
            dest.put(destOffset + 1, ((_selfx) * (_t1) + (_selfy * _t0)));
            dest.put(destOffset + 2, _selfz);
            dest.put(destOffset + 3, _selfw);
            return dest;
        }
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-126, 2^126], so zero and subnormal
     * values scale up without overflow and the largest floats land in [2, 4).
     */
    private static float unitScale(float a, float b, float c) {
        int e = java.lang.Math.max(java.lang.Math.max(Float.floatToRawIntBits(a) & 0x7F800000,
                Float.floatToRawIntBits(b) & 0x7F800000), Float.floatToRawIntBits(c) & 0x7F800000);
        return Float.intBitsToFloat(0x7F000000 - java.lang.Math.min(java.lang.Math.max(e, 0x00800000), 0x7E800000));
    }

    /**
     * The floored remainder of x and y, exactly kotlin.Float.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static float flooredMod(float x, float y) {
        float q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p24f && java.lang.Math.abs(y) <= Float.MAX_VALUE) {
            float r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0f), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        float r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }
}
