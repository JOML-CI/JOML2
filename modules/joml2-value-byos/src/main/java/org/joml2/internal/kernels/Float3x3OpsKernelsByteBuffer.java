// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3x3Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float3x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float3x3OpsKernelsByteBuffer {
    private Float3x3OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        Float3x3OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (col) {
            case 0: _idxSw0 = src.getFloat(srcOffset); _idxSw1 = src.getFloat(srcOffset + 4); _idxSw2 = src.getFloat(srcOffset + 8); break;
            case 1: _idxSw0 = src.getFloat(srcOffset + 12); _idxSw1 = src.getFloat(srcOffset + 16); _idxSw2 = src.getFloat(srcOffset + 20); break;
            case 2: _idxSw0 = src.getFloat(srcOffset + 24); _idxSw1 = src.getFloat(srcOffset + 28); _idxSw2 = src.getFloat(srcOffset + 32); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.putFloat(destOffset, _idxSw0);
        dest.putFloat(destOffset + 4, _idxSw1);
        dest.putFloat(destOffset + 8, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = java.lang.Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 <= java.lang.Math.fma(_self02, _self02, _t1) * 2.2737368E-13f) {
            dest.putFloat(destOffset, Math.atan2(_self21, _self11));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(-_self12, _self22));
            dest.putFloat(destOffset + 8, Math.atan2(java.lang.Math.fma(_self10, _self22, -_self12 * _self20), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = ((_self12) * (_self12) + (_self22 * _self22));
        if (_t1 <= ((_self02) * (_self02) + (_t1)) * 2.2737368E-13f) {
            dest.putFloat(destOffset, Math.atan2(_self21, _self11));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(-_self12, _self22));
            dest.putFloat(destOffset + 8, Math.atan2(((_self10) * (_self22) - (_self12 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        Float3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = java.lang.Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 <= epsilon * java.lang.Math.fma(_self02, _self02, _t1)) {
            dest.putFloat(destOffset, Math.atan2(_self21, _self11));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(-_self12, _self22));
            dest.putFloat(destOffset + 8, Math.atan2(java.lang.Math.fma(_self10, _self22, -_self12 * _self20), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = ((_self12) * (_self12) + (_self22 * _self22));
        if (_t1 <= epsilon * ((_self02) * (_self02) + (_t1))) {
            dest.putFloat(destOffset, Math.atan2(_self21, _self11));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(-_self12, _self22));
            dest.putFloat(destOffset + 8, Math.atan2(((_self10) * (_self22) - (_self12 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = java.lang.Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 <= java.lang.Math.fma(_self01, _self01, _t1) * 2.2737368E-13f) {
            dest.putFloat(destOffset, Math.atan2(-_self12, _self22));
            dest.putFloat(destOffset + 4, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_self21, _self11));
            dest.putFloat(destOffset + 4, Math.atan2(java.lang.Math.fma(_self10, _self21, -(_self11 * _self20)), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.putFloat(destOffset + 8, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = ((_self11) * (_self11) + (_self21 * _self21));
        if (_t1 <= ((_self01) * (_self01) + (_t1)) * 2.2737368E-13f) {
            dest.putFloat(destOffset, Math.atan2(-_self12, _self22));
            dest.putFloat(destOffset + 4, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_self21, _self11));
            dest.putFloat(destOffset + 4, Math.atan2(((_self10) * (_self21) - (_self11 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.putFloat(destOffset + 8, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        Float3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = java.lang.Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 <= epsilon * java.lang.Math.fma(_self01, _self01, _t1)) {
            dest.putFloat(destOffset, Math.atan2(-_self12, _self22));
            dest.putFloat(destOffset + 4, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_self21, _self11));
            dest.putFloat(destOffset + 4, Math.atan2(java.lang.Math.fma(_self10, _self21, -(_self11 * _self20)), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.putFloat(destOffset + 8, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = ((_self11) * (_self11) + (_self21 * _self21));
        if (_t1 <= epsilon * ((_self01) * (_self01) + (_t1))) {
            dest.putFloat(destOffset, Math.atan2(-_self12, _self22));
            dest.putFloat(destOffset + 4, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_self21, _self11));
            dest.putFloat(destOffset + 4, Math.atan2(((_self10) * (_self21) - (_self11 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.putFloat(destOffset + 8, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = java.lang.Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 <= java.lang.Math.fma(_self12, _self12, _t1) * 2.2737368E-13f) {
            dest.putFloat(destOffset + 4, Math.atan2(-_self20, _self00));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(_self02, _self22));
            dest.putFloat(destOffset + 8, Math.atan2(java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
        }
        dest.putFloat(destOffset, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = ((_self02) * (_self02) + (_self22 * _self22));
        if (_t1 <= ((_self12) * (_self12) + (_t1)) * 2.2737368E-13f) {
            dest.putFloat(destOffset + 4, Math.atan2(-_self20, _self00));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(_self02, _self22));
            dest.putFloat(destOffset + 8, Math.atan2(((_self02) * (_self21) - (_self01 * _self22)), ((_self00) * (_self22) - (_self02 * _self20))));
        }
        dest.putFloat(destOffset, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        Float3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _self00 = src.getFloat(srcOffset);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = java.lang.Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 <= epsilon * java.lang.Math.fma(_self12, _self12, _t1)) {
            dest.putFloat(destOffset + 4, Math.atan2(-_self20, _self00));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(_self02, _self22));
            dest.putFloat(destOffset + 8, Math.atan2(java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
        }
        dest.putFloat(destOffset, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _self00 = src.getFloat(srcOffset);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = ((_self02) * (_self02) + (_self22 * _self22));
        if (_t1 <= epsilon * ((_self12) * (_self12) + (_t1))) {
            dest.putFloat(destOffset + 4, Math.atan2(-_self20, _self00));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(_self02, _self22));
            dest.putFloat(destOffset + 8, Math.atan2(((_self02) * (_self21) - (_self01 * _self22)), ((_self00) * (_self22) - (_self02 * _self20))));
        }
        dest.putFloat(destOffset, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self20 * _self20);
        if (_t1 <= java.lang.Math.fma(_self10, _self10, _t1) * 2.2737368E-13f) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, Math.atan2(_self02, _self22));
        } else {
            dest.putFloat(destOffset, Math.atan2(java.lang.Math.fma(_self00, _self21, _self01 * -_self20), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
            dest.putFloat(destOffset + 4, Math.atan2(-_self20, _self00));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = ((_self00) * (_self00) + (_self20 * _self20));
        if (_t1 <= ((_self10) * (_self10) + (_t1)) * 2.2737368E-13f) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, Math.atan2(_self02, _self22));
        } else {
            dest.putFloat(destOffset, Math.atan2(((_self00) * (_self21) - (_self01 * _self20)), ((_self00) * (_self22) - (_self02 * _self20))));
            dest.putFloat(destOffset + 4, Math.atan2(-_self20, _self00));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        Float3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self20 * _self20);
        if (_t1 <= epsilon * java.lang.Math.fma(_self10, _self10, _t1)) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, Math.atan2(_self02, _self22));
        } else {
            dest.putFloat(destOffset, Math.atan2(java.lang.Math.fma(_self00, _self21, _self01 * -_self20), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
            dest.putFloat(destOffset + 4, Math.atan2(-_self20, _self00));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = ((_self00) * (_self00) + (_self20 * _self20));
        if (_t1 <= epsilon * ((_self10) * (_self10) + (_t1))) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, Math.atan2(_self02, _self22));
        } else {
            dest.putFloat(destOffset, Math.atan2(((_self00) * (_self21) - (_self01 * _self20)), ((_self00) * (_self22) - (_self02 * _self20))));
            dest.putFloat(destOffset + 4, Math.atan2(-_self20, _self00));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _t1 = java.lang.Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 <= java.lang.Math.fma(_self21, _self21, _t1) * 2.2737368E-13f) {
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(_self10, _self00));
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(java.lang.Math.fma(_self02, _self11, -_self01 * _self12), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.putFloat(destOffset + 8, Math.atan2(-_self01, _self11));
        }
        dest.putFloat(destOffset, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _t1 = ((_self01) * (_self01) + (_self11 * _self11));
        if (_t1 <= ((_self21) * (_self21) + (_t1)) * 2.2737368E-13f) {
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(_self10, _self00));
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(((_self02) * (_self11) - (_self01 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.putFloat(destOffset + 8, Math.atan2(-_self01, _self11));
        }
        dest.putFloat(destOffset, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        Float3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _t1 = java.lang.Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 <= epsilon * java.lang.Math.fma(_self21, _self21, _t1)) {
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(_self10, _self00));
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(java.lang.Math.fma(_self02, _self11, -_self01 * _self12), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.putFloat(destOffset + 8, Math.atan2(-_self01, _self11));
        }
        dest.putFloat(destOffset, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _t1 = ((_self01) * (_self01) + (_self11 * _self11));
        if (_t1 <= epsilon * ((_self21) * (_self21) + (_t1))) {
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(_self10, _self00));
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(((_self02) * (_self11) - (_self01 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.putFloat(destOffset + 8, Math.atan2(-_self01, _self11));
        }
        dest.putFloat(destOffset, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self10 * _self10);
        if (_t1 <= java.lang.Math.fma(_self20, _self20, _t1) * 2.2737368E-13f) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(-_self01, _self11));
        } else {
            dest.putFloat(destOffset, Math.atan2(java.lang.Math.fma(_self02, _self10, -(_self00 * _self12)), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.putFloat(destOffset + 8, Math.atan2(_self10, _self00));
        }
        dest.putFloat(destOffset + 4, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _t1 = ((_self00) * (_self00) + (_self10 * _self10));
        if (_t1 <= ((_self20) * (_self20) + (_t1)) * 2.2737368E-13f) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(-_self01, _self11));
        } else {
            dest.putFloat(destOffset, Math.atan2(((_self02) * (_self10) - (_self00 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.putFloat(destOffset + 8, Math.atan2(_self10, _self00));
        }
        dest.putFloat(destOffset + 4, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        Float3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self10 * _self10);
        if (_t1 <= epsilon * java.lang.Math.fma(_self20, _self20, _t1)) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(-_self01, _self11));
        } else {
            dest.putFloat(destOffset, Math.atan2(java.lang.Math.fma(_self02, _self10, -(_self00 * _self12)), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.putFloat(destOffset + 8, Math.atan2(_self10, _self00));
        }
        dest.putFloat(destOffset + 4, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _t1 = ((_self00) * (_self00) + (_self10 * _self10));
        if (_t1 <= epsilon * ((_self20) * (_self20) + (_t1))) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(-_self01, _self11));
        } else {
            dest.putFloat(destOffset, Math.atan2(((_self02) * (_self10) - (_self00 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.putFloat(destOffset + 8, Math.atan2(_self10, _self00));
        }
        dest.putFloat(destOffset + 4, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getNormalizedRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float3x3OpsKernelsAddress.getNormalizedRotation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getNormalizedRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getNormalizedRotation_api_fma(dest, destOffset, src, srcOffset);
        return getNormalizedRotation_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getNormalizedRotation_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        float _t7 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        float _t8 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t21, _t23, _t27;
        if (_t6 != 0.0f) {
            _t21 = _self01 * _t9;
            _t23 = _self11 * _t9;
            _t27 = _self21 * _t9;
        } else {
            _t21 = 0.0f;
            _t23 = 0.0f;
            _t27 = 0.0f;
        }
        float _t22, _t24, _t26;
        if (_t7 != 0.0f) {
            _t22 = _self12 * _t10;
            _t24 = _self02 * _t10;
            _t26 = _self22 * _t10;
        } else {
            _t22 = 0.0f;
            _t24 = 0.0f;
            _t26 = 0.0f;
        }
        return getNormalizedRotation_api_fma_sfeef3f82_1(dest, destOffset, _self00, _self10, _self20, _t8, (1.0f / (float) java.lang.Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_fma_sfeef3f82_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _t8, float _t11, float _t21, float _t23, float _t27, float _t22, float _t24, float _t26) {
        float _t25, _t28, _t29;
        if (_t8 != 0.0f) {
            _t25 = _self20 * _t11;
            _t28 = _self00 * _t11;
            _t29 = _self10 * _t11;
        } else {
            _t25 = 0.0f;
            _t28 = 0.0f;
            _t29 = 0.0f;
        }
        float _t49, _t50, _t51;
        if (java.lang.Math.fma(java.lang.Math.fma(_t21, _t22, -(_t23 * _t24)), _t25, java.lang.Math.fma(java.lang.Math.fma(_t23, _t26, -(_t27 * _t22)), _t28, java.lang.Math.fma(_t27, _t24, -(_t21 * _t26)) * _t29)) < 0.0f) {
            _t49 = -_t28;
            _t50 = -_t29;
            _t51 = -_t25;
        } else {
            _t49 = _t28;
            _t50 = _t29;
            _t51 = _t25;
        }
        float _t52 = _t49 + _t23;
        float _t58 = _t52 + _t26;
        float _t62 = 1.0f + _t58;
        float _t63 = 1.0f + (_t49 - (_t23 + _t26));
        float _t64 = 1.0f + (_t23 - (_t49 + _t26));
        float _t65 = 1.0f + (_t26 - _t52);
        return getNormalizedRotation_api_fma_sfeef3f82_2(dest, destOffset, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t62)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_fma_sfeef3f82_2(java.nio.ByteBuffer dest, int destOffset, float _t23, float _t26, float _t36, float _t39, float _t49, float _t53, float _t55, float _t56, float _t57, float _t58, float _t62, float _t63, float _t64, float _t65, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t58 > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t36);
            dest.putFloat(destOffset + 4, _sp0 * _t56);
            dest.putFloat(destOffset + 8, _sp0 * _t57);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t63));
                dest.putFloat(destOffset + 4, _sp3 * _t53);
                dest.putFloat(destOffset + 8, _sp3 * _t55);
                dest.putFloat(destOffset + 12, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.putFloat(destOffset, _sp1 * _t53);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t64));
                    dest.putFloat(destOffset + 8, _sp1 * _t39);
                    dest.putFloat(destOffset + 12, _sp1 * _t56);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t55);
                    dest.putFloat(destOffset + 4, _sp2 * _t39);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    dest.putFloat(destOffset + 12, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    /** {@code getNormalizedRotation_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        float _t7 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        float _t8 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t21, _t23, _t27;
        if (_t6 != 0.0f) {
            _t21 = _self01 * _t9;
            _t23 = _self11 * _t9;
            _t27 = _self21 * _t9;
        } else {
            _t21 = 0.0f;
            _t23 = 0.0f;
            _t27 = 0.0f;
        }
        float _t22, _t24, _t26;
        if (_t7 != 0.0f) {
            _t22 = _self12 * _t10;
            _t24 = _self02 * _t10;
            _t26 = _self22 * _t10;
        } else {
            _t22 = 0.0f;
            _t24 = 0.0f;
            _t26 = 0.0f;
        }
        return getNormalizedRotation_api_mulAdd_sc7b493e1_1(dest, destOffset, _self00, _self10, _self20, _t8, (1.0f / (float) java.lang.Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_mulAdd_sc7b493e1_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _t8, float _t11, float _t21, float _t23, float _t27, float _t22, float _t24, float _t26) {
        float _t25, _t28, _t29;
        if (_t8 != 0.0f) {
            _t25 = _self20 * _t11;
            _t28 = _self00 * _t11;
            _t29 = _self10 * _t11;
        } else {
            _t25 = 0.0f;
            _t28 = 0.0f;
            _t29 = 0.0f;
        }
        float _t49, _t50, _t51;
        if (((((_t21) * (_t22) - (_t23 * _t24))) * (_t25) + (((((_t23) * (_t26) - (_t27 * _t22))) * (_t28) + (((_t27) * (_t24) - (_t21 * _t26)) * _t29)))) < 0.0f) {
            _t49 = -_t28;
            _t50 = -_t29;
            _t51 = -_t25;
        } else {
            _t49 = _t28;
            _t50 = _t29;
            _t51 = _t25;
        }
        float _t52 = _t49 + _t23;
        float _t58 = _t52 + _t26;
        float _t62 = 1.0f + _t58;
        float _t63 = 1.0f + (_t49 - (_t23 + _t26));
        float _t64 = 1.0f + (_t23 - (_t49 + _t26));
        float _t65 = 1.0f + (_t26 - _t52);
        return getNormalizedRotation_api_mulAdd_sc7b493e1_2(dest, destOffset, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t62)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_mulAdd_sc7b493e1_2(java.nio.ByteBuffer dest, int destOffset, float _t23, float _t26, float _t36, float _t39, float _t49, float _t53, float _t55, float _t56, float _t57, float _t58, float _t62, float _t63, float _t64, float _t65, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t58 > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t36);
            dest.putFloat(destOffset + 4, _sp0 * _t56);
            dest.putFloat(destOffset + 8, _sp0 * _t57);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t63));
                dest.putFloat(destOffset + 4, _sp3 * _t53);
                dest.putFloat(destOffset + 8, _sp3 * _t55);
                dest.putFloat(destOffset + 12, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.putFloat(destOffset, _sp1 * _t53);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t64));
                    dest.putFloat(destOffset + 8, _sp1 * _t39);
                    dest.putFloat(destOffset + 12, _sp1 * _t56);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t55);
                    dest.putFloat(destOffset + 4, _sp2 * _t39);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    dest.putFloat(destOffset + 12, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer getRow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        Float3x3OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, row);
        return dest;
    }

    public static java.nio.ByteBuffer getRow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (row) {
            case 0: _idxSw0 = src.getFloat(srcOffset); _idxSw1 = src.getFloat(srcOffset + 12); _idxSw2 = src.getFloat(srcOffset + 24); break;
            case 1: _idxSw0 = src.getFloat(srcOffset + 4); _idxSw1 = src.getFloat(srcOffset + 16); _idxSw2 = src.getFloat(srcOffset + 28); break;
            case 2: _idxSw0 = src.getFloat(srcOffset + 8); _idxSw1 = src.getFloat(srcOffset + 20); _idxSw2 = src.getFloat(srcOffset + 32); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.putFloat(destOffset, _idxSw0);
        dest.putFloat(destOffset + 4, _idxSw1);
        dest.putFloat(destOffset + 8, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float3x3OpsKernelsAddress.getScale_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getScale_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, (float) java.lang.Math.sqrt(java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10))));
        dest.putFloat(destOffset + 4, (float) java.lang.Math.sqrt(java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11))));
        dest.putFloat(destOffset + 8, (float) java.lang.Math.sqrt(java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    /** {@code getScale_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getScale_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, (float) java.lang.Math.sqrt(((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))))));
        dest.putFloat(destOffset + 4, (float) java.lang.Math.sqrt(((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))))));
        dest.putFloat(destOffset + 8, (float) java.lang.Math.sqrt(((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))))));
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float3x3OpsKernelsAddress.getTranslation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        dest.putFloat(destOffset, _self02);
        dest.putFloat(destOffset + 4, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer getUnnormalizedRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float3x3OpsKernelsAddress.getUnnormalizedRotation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getUnnormalizedRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = _self00 + _self11;
        float _t10 = _self22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_self00 - (_self11 + _self22));
        float _t16 = 1.0f + (_self11 - (_self00 + _self22));
        float _t17 = 1.0f + (_self22 - _t0);
        return getUnnormalizedRotation_api_s9cd1b5fe_1(dest, destOffset, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getUnnormalizedRotation_api_s9cd1b5fe_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self11, float _self22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t1);
            dest.putFloat(destOffset + 4, _sp0 * _t7);
            dest.putFloat(destOffset + 8, _sp0 * _t9);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.putFloat(destOffset + 4, _sp3 * _t4);
                dest.putFloat(destOffset + 8, _sp3 * _t6);
                dest.putFloat(destOffset + 12, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    dest.putFloat(destOffset, _sp1 * _t4);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.putFloat(destOffset + 8, _sp1 * _t8);
                    dest.putFloat(destOffset + 12, _sp1 * _t7);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t6);
                    dest.putFloat(destOffset + 4, _sp2 * _t8);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.putFloat(destOffset + 12, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer cofactor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cofactor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return cofactor_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, java.lang.Math.fma(_self11, _self22, -_self12 * _self21));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self01, _self12, _self02 * -_self11));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self12, _self20, -_self10 * _self22));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self00, _self22, -(_self02 * _self20)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self02, _self10, _self00 * -_self12));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self10, _self21, -_self11 * _self20));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self01, _self20, -(_self00 * _self21)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self00, _self11, _self01 * -_self10));
        return dest;
    }

    /** {@code cofactor_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer cofactor_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, ((_self11) * (_self22) - (_self12 * _self21)));
        dest.putFloat(destOffset + 4, ((_self02) * (_self21) - (_self01 * _self22)));
        dest.putFloat(destOffset + 8, ((_self01) * (_self12) - (_self02 * _self11)));
        dest.putFloat(destOffset + 12, ((_self12) * (_self20) - (_self10 * _self22)));
        dest.putFloat(destOffset + 16, ((_self00) * (_self22) - (_self02 * _self20)));
        dest.putFloat(destOffset + 20, ((_self02) * (_self10) - (_self00 * _self12)));
        dest.putFloat(destOffset + 24, ((_self10) * (_self21) - (_self11 * _self20)));
        dest.putFloat(destOffset + 28, ((_self01) * (_self20) - (_self00 * _self21)));
        dest.putFloat(destOffset + 32, ((_self00) * (_self11) - (_self01 * _self10)));
        return dest;
    }

    public static float determinant_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static float determinant_api(java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return determinant_api_mulAdd(src, srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t3 = -(_self11 * _self20);
        float _t4 = -_self12 * _self21;
        float _t5 = -_self12 * _self20;
        return java.lang.Math.fma(src.getFloat(srcOffset + 24), java.lang.Math.fma(_self10, _self21, _t3) - java.lang.Math.fma(_self11, _self20, _t3), java.lang.Math.fma(src.getFloat(srcOffset), java.lang.Math.fma(_self11, _self22, _t4) - java.lang.Math.fma(_self12, _self21, _t4), src.getFloat(srcOffset + 12) * (java.lang.Math.fma(_self12, _self20, _t5) - java.lang.Math.fma(_self10, _self22, _t5))));
    }

    /** {@code determinant_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float determinant_api_mulAdd(java.nio.ByteBuffer src, int srcOffset) {
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t3 = -(_self11 * _self20);
        float _t4 = -_self12 * _self21;
        float _t5 = -_self12 * _self20;
        return ((src.getFloat(srcOffset + 24)) * (((_self10) * (_self21) + (_t3)) - ((_self11) * (_self20) + (_t3))) + (((src.getFloat(srcOffset)) * (((_self11) * (_self22) + (_t4)) - ((_self12) * (_self21) + (_t4))) + (src.getFloat(srcOffset + 12) * (((_self12) * (_self20) + (_t5)) - ((_self10) * (_self22) + (_t5)))))));
    }

    public static float frobeniusNorm_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self20 = src.getFloat(srcOffset + 8);
            float _self01 = src.getFloat(srcOffset + 12);
            float _self11 = src.getFloat(srcOffset + 16);
            float _self21 = src.getFloat(srcOffset + 20);
            float _self02 = src.getFloat(srcOffset + 24);
            float _self12 = src.getFloat(srcOffset + 28);
            float _self22 = src.getFloat(srcOffset + 32);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_self00, _self00, _self01 * _self01) + java.lang.Math.fma(_self02, _self02, _self10 * _self10) + (java.lang.Math.fma(_self11, _self11, _self12 * _self12) + java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self21, _self21, _self22 * _self22))));
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self20 = src.getFloat(srcOffset + 8);
            float _self01 = src.getFloat(srcOffset + 12);
            float _self11 = src.getFloat(srcOffset + 16);
            float _self21 = src.getFloat(srcOffset + 20);
            float _self02 = src.getFloat(srcOffset + 24);
            float _self12 = src.getFloat(srcOffset + 28);
            float _self22 = src.getFloat(srcOffset + 32);
            return (float) java.lang.Math.sqrt(((_self00) * (_self00) + (_self01 * _self01)) + ((_self02) * (_self02) + (_self10 * _self10)) + (((_self11) * (_self11) + (_self12 * _self12)) + ((_self20) * (_self20) + (((_self21) * (_self21) + (_self22 * _self22))))));
        }
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_api_fma(dest, destOffset, src, srcOffset);
        return invert_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code invert_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        float _t12 = -_self12 * _self20;
        float _t26 = java.lang.Math.fma(_self11, _self22, _t10) - java.lang.Math.fma(_self12, _self21, _t10);
        float _t27 = java.lang.Math.fma(_self10, _self21, _t11) - java.lang.Math.fma(_self11, _self20, _t11);
        float _t33 = java.lang.Math.fma(_self02, _t27, java.lang.Math.fma(_self00, _t26, _self01 * (java.lang.Math.fma(_self12, _self20, _t12) - java.lang.Math.fma(_self10, _self22, _t12))));
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        return invert_api_fma_s8d266039_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self01 * _self22, -_self02 * _self11, -(_self10 * _self22), -_self02 * _self20, -_self00 * _self12, -_self00 * _self21, -_self01 * _self10, _t26, _t27, 1.0f / _t33);
    }

    /** Piece 2 of {@code invert_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_api_fma_s8d266039_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t33_inv) {
        dest.putFloat(destOffset, _t26 * _t33_inv);
        dest.putFloat(destOffset + 4, (java.lang.Math.fma(_self12, _self20, _t15) - java.lang.Math.fma(_self10, _self22, _t15)) * _t33_inv);
        dest.putFloat(destOffset + 8, _t27 * _t33_inv);
        dest.putFloat(destOffset + 12, (java.lang.Math.fma(_self02, _self21, _t13) - java.lang.Math.fma(_self01, _self22, _t13)) * _t33_inv);
        dest.putFloat(destOffset + 16, (java.lang.Math.fma(_self00, _self22, _t16) - java.lang.Math.fma(_self02, _self20, _t16)) * _t33_inv);
        dest.putFloat(destOffset + 20, (java.lang.Math.fma(_self01, _self20, _t18) - java.lang.Math.fma(_self00, _self21, _t18)) * _t33_inv);
        dest.putFloat(destOffset + 24, (java.lang.Math.fma(_self01, _self12, _t14) - java.lang.Math.fma(_self02, _self11, _t14)) * _t33_inv);
        dest.putFloat(destOffset + 28, (java.lang.Math.fma(_self02, _self10, _t17) - java.lang.Math.fma(_self00, _self12, _t17)) * _t33_inv);
        dest.putFloat(destOffset + 32, (java.lang.Math.fma(_self00, _self11, _t19) - java.lang.Math.fma(_self01, _self10, _t19)) * _t33_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        float _t12 = -_self12 * _self20;
        float _t26 = ((_self11) * (_self22) + (_t10)) - ((_self12) * (_self21) + (_t10));
        float _t27 = ((_self10) * (_self21) + (_t11)) - ((_self11) * (_self20) + (_t11));
        float _t33 = ((_self02) * (_t27) + (((_self00) * (_t26) + (_self01 * (((_self12) * (_self20) + (_t12)) - ((_self10) * (_self22) + (_t12)))))));
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t33_inv = 1.0f / _t33;
        dest.putFloat(destOffset, _t26 * _t33_inv);
        return invert_api_mulAdd_s68293728_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self01 * _self22, -_self02 * _self11, -(_self10 * _self22), -_self02 * _self20, -_self00 * _self12, -_self00 * _self21, -_self01 * _self10, _t27, _t33_inv);
    }

    /** Piece 2 of {@code invert_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_api_mulAdd_s68293728_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t27, float _t33_inv) {
        dest.putFloat(destOffset + 4, (((_self12) * (_self20) + (_t15)) - ((_self10) * (_self22) + (_t15))) * _t33_inv);
        dest.putFloat(destOffset + 8, _t27 * _t33_inv);
        dest.putFloat(destOffset + 12, (((_self02) * (_self21) + (_t13)) - ((_self01) * (_self22) + (_t13))) * _t33_inv);
        dest.putFloat(destOffset + 16, (((_self00) * (_self22) + (_t16)) - ((_self02) * (_self20) + (_t16))) * _t33_inv);
        dest.putFloat(destOffset + 20, (((_self01) * (_self20) + (_t18)) - ((_self00) * (_self21) + (_t18))) * _t33_inv);
        dest.putFloat(destOffset + 24, (((_self01) * (_self12) + (_t14)) - ((_self02) * (_self11) + (_t14))) * _t33_inv);
        dest.putFloat(destOffset + 28, (((_self02) * (_self10) + (_t17)) - ((_self00) * (_self12) + (_t17))) * _t33_inv);
        dest.putFloat(destOffset + 32, (((_self00) * (_self11) + (_t19)) - ((_self01) * (_self10) + (_t19))) * _t33_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invert_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code invert_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t12 = _self11 * _t0;
        float _t13 = _self22 * _t1;
        float _t14 = _self12 * _t0;
        float _t15 = _self21 * _t1;
        float _t16 = _self10 * _t0;
        float _t17 = _self20 * _t1;
        float _t18 = _self02 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self01 * _t2;
        float _t31 = -_t14 * _t15;
        return invert_degenerate_api_fma_s86b1d2cc_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -(_t12 * _t17), -_t14 * _t17, -_t20 * _t13, -_t18 * _t12, -(_t16 * _t13), -_t18 * _t17, -_t19 * _t14, -_t19 * _t15, -_t20 * _t16, java.lang.Math.fma(_t12, _t13, _t31) - java.lang.Math.fma(_t14, _t15, _t31));
    }

    /** Piece 2 of {@code invert_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_fma_s86b1d2cc_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40, float _t47) {
        float _t48 = java.lang.Math.fma(_t16, _t15, _t32) - java.lang.Math.fma(_t12, _t17, _t32);
        float _t53_inv = 1.0f / java.lang.Math.fma(_t48, _t18, java.lang.Math.fma(_t47, _t19, (java.lang.Math.fma(_t14, _t17, _t33) - java.lang.Math.fma(_t16, _t13, _t33)) * _t20));
        float _sp2 = _t1 * _t53_inv;
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        dest.putFloat(destOffset, _t47 * _sp0);
        dest.putFloat(destOffset + 4, (java.lang.Math.fma(_t14, _t17, _t36) - java.lang.Math.fma(_t16, _t13, _t36)) * _sp0);
        dest.putFloat(destOffset + 8, _t48 * _sp0);
        dest.putFloat(destOffset + 12, (java.lang.Math.fma(_t18, _t15, _t34) - java.lang.Math.fma(_t20, _t13, _t34)) * _sp1);
        dest.putFloat(destOffset + 16, (java.lang.Math.fma(_t19, _t13, _t37) - java.lang.Math.fma(_t18, _t17, _t37)) * _sp1);
        dest.putFloat(destOffset + 20, (java.lang.Math.fma(_t20, _t17, _t39) - java.lang.Math.fma(_t19, _t15, _t39)) * _sp1);
        dest.putFloat(destOffset + 24, (java.lang.Math.fma(_t20, _t14, _t35) - java.lang.Math.fma(_t18, _t12, _t35)) * _sp2);
        return invert_degenerate_api_fma_s86b1d2cc_2(dest, destOffset, _t12, _t14, _t16, _t18, _t19, _t20, _t38, _t40, _sp2);
    }

    /** Piece 3 of {@code invert_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_fma_s86b1d2cc_2(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t14, float _t16, float _t18, float _t19, float _t20, float _t38, float _t40, float _sp2) {
        dest.putFloat(destOffset + 28, (java.lang.Math.fma(_t18, _t16, _t38) - java.lang.Math.fma(_t19, _t14, _t38)) * _sp2);
        dest.putFloat(destOffset + 32, (java.lang.Math.fma(_t19, _t12, _t40) - java.lang.Math.fma(_t20, _t16, _t40)) * _sp2);
        return dest;
    }

    /** {@code invert_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t12 = _self11 * _t0;
        float _t13 = _self22 * _t1;
        float _t14 = _self12 * _t0;
        float _t15 = _self21 * _t1;
        float _t16 = _self10 * _t0;
        float _t17 = _self20 * _t1;
        float _t18 = _self02 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self01 * _t2;
        float _t31 = -_t14 * _t15;
        return invert_degenerate_api_mulAdd_sc0a0a973_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -(_t12 * _t17), -_t14 * _t17, -_t20 * _t13, -_t18 * _t12, -(_t16 * _t13), -_t18 * _t17, -_t19 * _t14, -_t19 * _t15, -_t20 * _t16, ((_t12) * (_t13) + (_t31)) - ((_t14) * (_t15) + (_t31)));
    }

    /** Piece 2 of {@code invert_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_mulAdd_sc0a0a973_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40, float _t47) {
        float _t48 = ((_t16) * (_t15) + (_t32)) - ((_t12) * (_t17) + (_t32));
        float _t53_inv = 1.0f / ((_t48) * (_t18) + (((_t47) * (_t19) + ((((_t14) * (_t17) + (_t33)) - ((_t16) * (_t13) + (_t33))) * _t20))));
        float _sp2 = _t1 * _t53_inv;
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        dest.putFloat(destOffset, _t47 * _sp0);
        dest.putFloat(destOffset + 4, (((_t14) * (_t17) + (_t36)) - ((_t16) * (_t13) + (_t36))) * _sp0);
        dest.putFloat(destOffset + 8, _t48 * _sp0);
        dest.putFloat(destOffset + 12, (((_t18) * (_t15) + (_t34)) - ((_t20) * (_t13) + (_t34))) * _sp1);
        dest.putFloat(destOffset + 16, (((_t19) * (_t13) + (_t37)) - ((_t18) * (_t17) + (_t37))) * _sp1);
        dest.putFloat(destOffset + 20, (((_t20) * (_t17) + (_t39)) - ((_t19) * (_t15) + (_t39))) * _sp1);
        dest.putFloat(destOffset + 24, (((_t20) * (_t14) + (_t35)) - ((_t18) * (_t12) + (_t35))) * _sp2);
        dest.putFloat(destOffset + 28, (((_t18) * (_t16) + (_t38)) - ((_t19) * (_t14) + (_t38))) * _sp2);
        dest.putFloat(destOffset + 32, (((_t19) * (_t12) + (_t40)) - ((_t20) * (_t16) + (_t40))) * _sp2);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other01 = other.getFloat(otherOffset + 12);
        float _other11 = other.getFloat(otherOffset + 16);
        float _other21 = other.getFloat(otherOffset + 20);
        float _other02 = other.getFloat(otherOffset + 24);
        float _other12 = other.getFloat(otherOffset + 28);
        float _other22 = other.getFloat(otherOffset + 32);
        float _t21 = -(_other11 * _other20);
        float _t22 = -_other12 * _other21;
        return invertProduct_api_fma_s2dc24fa2_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, java.lang.Math.fma(_other10, _other21, _t21) - java.lang.Math.fma(_other11, _other20, _t21), java.lang.Math.fma(_other11, _other22, _t22) - java.lang.Math.fma(_other12, _other21, _t22), -_other12 * _other20);
    }

    /** Part of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_api_fma_s2dc24fa2_526(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t81, float _t82, float _t108, float _t84, float _t85, float _t109) {
        float _t20 = -_other02 * _other11;
        float _t128 = (java.lang.Math.fma(_other01, _other12, _t20) - java.lang.Math.fma(_other02, _other11, _t20)) / _t108;
        float _t27 = -_other01 * _other22;
        float _t130 = (java.lang.Math.fma(_other02, _other21, _t27) - java.lang.Math.fma(_other01, _other22, _t27)) / _t108;
        float _t131 = invertProduct_api_fma_s2dc24fa2_526_s2b2fee87_1(dest, destOffset, _self10, _self20, _self12, _self22, _t82, _t108, _t84, _t85, _t109, _t128, _t130);
        float _t35 = -_other00 * _other12;
        float _t140 = (java.lang.Math.fma(_other02, _other10, _t35) - java.lang.Math.fma(_other00, _other12, _t35)) / _t108;
        float _t36 = -_other02 * _other20;
        float _t141 = (java.lang.Math.fma(_other00, _other22, _t36) - java.lang.Math.fma(_other02, _other20, _t36)) / _t108;
        float _t142 = invertProduct_api_fma_s2dc24fa2_526_s2b2fee87_2(dest, destOffset, _other10, _other20, _other12, _other22, _t108, _t84, _t85, _t109, _t131, _t140, _t141);
        float _t38 = -_other01 * _other10;
        float _t143 = (java.lang.Math.fma(_other00, _other11, _t38) - java.lang.Math.fma(_other01, _other10, _t38)) / _t108;
        invertProduct_api_fma_s2dc24fa2_526_s2b2fee87_4(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _t81, _t82, _t108, _t109, _t128, _t130, _t140, _t141, _t142, _t143, invertProduct_api_fma_s2dc24fa2_526_s2b2fee87_3(dest, destOffset, _self00, _self20, _self01, _self21, _self02, _self22, _other00, _other20, _other01, _other21, _t81, _t82, _t108, _t84, _t85, _t109, _t128, _t130, _t131, _t140, _t141, _t142, _t143));
    }

    /** Part 1 of {@code invertProduct_api_fma_s2dc24fa2_526}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_api_fma_s2dc24fa2_526_s2b2fee87_1(java.nio.ByteBuffer dest, int destOffset, float _self10, float _self20, float _self12, float _self22, float _t82, float _t108, float _t84, float _t85, float _t109, float _t128, float _t130) {
        float _t28 = -_self10 * _self22;
        float _t131 = (java.lang.Math.fma(_self12, _self20, _t28) - java.lang.Math.fma(_self10, _self22, _t28)) / _t109;
        dest.putFloat(destOffset, java.lang.Math.fma(_t128, (_t84 / _t109), java.lang.Math.fma(_t130, _t131, (_t82 / _t108) * (_t85 / _t109))));
        return _t131;
    }

    /** Part 2 of {@code invertProduct_api_fma_s2dc24fa2_526}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_api_fma_s2dc24fa2_526_s2b2fee87_2(java.nio.ByteBuffer dest, int destOffset, float _other10, float _other20, float _other12, float _other22, float _t108, float _t84, float _t85, float _t109, float _t131, float _t140, float _t141) {
        float _t37 = -(_other10 * _other22);
        float _t142 = (java.lang.Math.fma(_other12, _other20, _t37) - java.lang.Math.fma(_other10, _other22, _t37)) / _t108;
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t140, (_t84 / _t109), java.lang.Math.fma(_t141, _t131, _t142 * (_t85 / _t109))));
        return _t142;
    }

    /** Part 3 of {@code invertProduct_api_fma_s2dc24fa2_526}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_api_fma_s2dc24fa2_526_s2b2fee87_3(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self20, float _self01, float _self21, float _self02, float _self22, float _other00, float _other20, float _other01, float _other21, float _t81, float _t82, float _t108, float _t84, float _t85, float _t109, float _t128, float _t130, float _t131, float _t140, float _t141, float _t142, float _t143) {
        float _t39 = -_other00 * _other21;
        float _t144 = (java.lang.Math.fma(_other01, _other20, _t39) - java.lang.Math.fma(_other00, _other21, _t39)) / _t108;
        float _t145 = _t81 / _t108;
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t143, (_t84 / _t109), java.lang.Math.fma(_t144, _t131, _t145 * (_t85 / _t109))));
        float _t29 = -_self00 * _self21;
        float _t134 = (java.lang.Math.fma(_self01, _self20, _t29) - java.lang.Math.fma(_self00, _self21, _t29)) / _t109;
        float _t30 = -_self02 * _self20;
        float _t135 = (java.lang.Math.fma(_self00, _self22, _t30) - java.lang.Math.fma(_self02, _self20, _t30)) / _t109;
        float _t31 = -(_self01 * _self22);
        float _t136 = (java.lang.Math.fma(_self02, _self21, _t31) - java.lang.Math.fma(_self01, _self22, _t31)) / _t109;
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t128, _t134, java.lang.Math.fma(_t130, _t135, (_t82 / _t108) * _t136)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t140, _t134, java.lang.Math.fma(_t141, _t135, _t142 * _t136)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t143, _t134, java.lang.Math.fma(_t144, _t135, _t145 * _t136)));
        return _t144;
    }

    /** Part 4 of {@code invertProduct_api_fma_s2dc24fa2_526}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_api_fma_s2dc24fa2_526_s2b2fee87_4(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _t81, float _t82, float _t108, float _t109, float _t128, float _t130, float _t140, float _t141, float _t142, float _t143, float _t144) {
        float _t32 = _self01 * -_self10;
        float _t137 = (java.lang.Math.fma(_self00, _self11, _t32) - java.lang.Math.fma(_self01, _self10, _t32)) / _t109;
        float _t33 = -_self00 * _self12;
        float _t138 = (java.lang.Math.fma(_self02, _self10, _t33) - java.lang.Math.fma(_self00, _self12, _t33)) / _t109;
        float _t34 = -_self02 * _self11;
        float _t139 = (java.lang.Math.fma(_self01, _self12, _t34) - java.lang.Math.fma(_self02, _self11, _t34)) / _t109;
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_t128, _t137, java.lang.Math.fma(_t130, _t138, (_t82 / _t108) * _t139)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t140, _t137, java.lang.Math.fma(_t141, _t138, _t142 * _t139)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_t143, _t137, java.lang.Math.fma(_t144, _t138, (_t81 / _t108) * _t139)));
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma_s2dc24fa2_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t81, float _t82, float _t23) {
        float _t108 = java.lang.Math.fma(_other02, _t81, java.lang.Math.fma(_other00, _t82, _other01 * (java.lang.Math.fma(_other12, _other20, _t23) - java.lang.Math.fma(_other10, _other22, _t23))));
        if (!(java.lang.Math.abs(_t108) > 1.1754944E-38f && java.lang.Math.abs(_t108) < 8.507059E37f)) return Float3x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t24 = -(_self11 * _self20);
        float _t84 = java.lang.Math.fma(_self10, _self21, _t24) - java.lang.Math.fma(_self11, _self20, _t24);
        float _t25 = -_self12 * _self21;
        float _t85 = java.lang.Math.fma(_self11, _self22, _t25) - java.lang.Math.fma(_self12, _self21, _t25);
        float _t26 = -_self12 * _self20;
        float _t109 = java.lang.Math.fma(_self02, _t84, java.lang.Math.fma(_self00, _t85, _self01 * (java.lang.Math.fma(_self12, _self20, _t26) - java.lang.Math.fma(_self10, _self22, _t26))));
        if (!(java.lang.Math.abs(_t109) > 1.1754944E-38f && java.lang.Math.abs(_t109) < 8.507059E37f)) return Float3x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        invertProduct_api_fma_s2dc24fa2_526(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t81, _t82, _t108, _t84, _t85, _t109);
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other01 = other.getFloat(otherOffset + 12);
        float _other11 = other.getFloat(otherOffset + 16);
        float _other21 = other.getFloat(otherOffset + 20);
        float _other02 = other.getFloat(otherOffset + 24);
        float _other12 = other.getFloat(otherOffset + 28);
        float _other22 = other.getFloat(otherOffset + 32);
        return invertProduct_api_mulAdd_s234a47a5_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, -_other02 * _other11, -(_other11 * _other20), -_other12 * _other21, -_other12 * _other20, -(_self11 * _self20), -_self12 * _self21, -_self12 * _self20, -_other01 * _other22, -_self10 * _self22, -_self00 * _self21, -_self02 * _self20);
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30) {
        float _t81 = ((_other10) * (_other21) + (_t21)) - ((_other11) * (_other20) + (_t21));
        float _t82 = ((_other11) * (_other22) + (_t22)) - ((_other12) * (_other21) + (_t22));
        float _t84 = ((_self10) * (_self21) + (_t24)) - ((_self11) * (_self20) + (_t24));
        float _t85 = ((_self11) * (_self22) + (_t25)) - ((_self12) * (_self21) + (_t25));
        float _t108 = ((_other02) * (_t81) + (((_other00) * (_t82) + (_other01 * (((_other12) * (_other20) + (_t23)) - ((_other10) * (_other22) + (_t23)))))));
        if (!(java.lang.Math.abs(_t108) > 1.1754944E-38f && java.lang.Math.abs(_t108) < 8.507059E37f)) return Float3x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd_s234a47a5_2(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t20, _t27, _t28, _t29, _t30, -(_self01 * _self22), _self01 * -_self10, -_self00 * _self12, -_self02 * _self11, -_other00 * _other12, -_other02 * _other20, -(_other10 * _other22), -_other01 * _other10, -_other00 * _other21, _t81, _t82, _t84, _t85, _t108, ((_self02) * (_t84) + (((_self00) * (_t85) + (_self01 * (((_self12) * (_self20) + (_t26)) - ((_self10) * (_self22) + (_t26))))))));
    }

    /** Piece 3 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t20, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t81, float _t82, float _t84, float _t85, float _t108, float _t109) {
        if (!(java.lang.Math.abs(_t109) > 1.1754944E-38f && java.lang.Math.abs(_t109) < 8.507059E37f)) return Float3x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd_s234a47a5_3(dest, destOffset, _other00, _other10, _other20, _other01, _other11, _other21, _other12, _other22, _t37, _t38, _t39, _t81, _t108, (((_other01) * (_other12) + (_t20)) - ((_other02) * (_other11) + (_t20))) / _t108, _t84 / _t109, (((_other02) * (_other21) + (_t27)) - ((_other01) * (_other22) + (_t27))) / _t108, (((_self12) * (_self20) + (_t28)) - ((_self10) * (_self22) + (_t28))) / _t109, _t82 / _t108, _t85 / _t109, (((_self01) * (_self20) + (_t29)) - ((_self00) * (_self21) + (_t29))) / _t109, (((_self00) * (_self22) + (_t30)) - ((_self02) * (_self20) + (_t30))) / _t109, (((_self02) * (_self21) + (_t31)) - ((_self01) * (_self22) + (_t31))) / _t109, (((_self00) * (_self11) + (_t32)) - ((_self01) * (_self10) + (_t32))) / _t109, (((_self02) * (_self10) + (_t33)) - ((_self00) * (_self12) + (_t33))) / _t109, (((_self01) * (_self12) + (_t34)) - ((_self02) * (_self11) + (_t34))) / _t109, (((_other02) * (_other10) + (_t35)) - ((_other00) * (_other12) + (_t35))) / _t108, (((_other00) * (_other22) + (_t36)) - ((_other02) * (_other20) + (_t36))) / _t108);
    }

    /** Piece 4 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_3(java.nio.ByteBuffer dest, int destOffset, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other12, float _other22, float _t37, float _t38, float _t39, float _t81, float _t108, float _t128, float _t129, float _t130, float _t131, float _t132, float _t133, float _t134, float _t135, float _t136, float _t137, float _t138, float _t139, float _t140, float _t141) {
        float _t142 = (((_other12) * (_other20) + (_t37)) - ((_other10) * (_other22) + (_t37))) / _t108;
        float _t143 = (((_other00) * (_other11) + (_t38)) - ((_other01) * (_other10) + (_t38))) / _t108;
        float _t144 = (((_other01) * (_other20) + (_t39)) - ((_other00) * (_other21) + (_t39))) / _t108;
        float _t145 = _t81 / _t108;
        dest.putFloat(destOffset, ((_t128) * (_t129) + (((_t130) * (_t131) + (_t132 * _t133)))));
        dest.putFloat(destOffset + 4, ((_t140) * (_t129) + (((_t141) * (_t131) + (_t142 * _t133)))));
        dest.putFloat(destOffset + 8, ((_t143) * (_t129) + (((_t144) * (_t131) + (_t145 * _t133)))));
        dest.putFloat(destOffset + 12, ((_t128) * (_t134) + (((_t130) * (_t135) + (_t132 * _t136)))));
        dest.putFloat(destOffset + 16, ((_t140) * (_t134) + (((_t141) * (_t135) + (_t142 * _t136)))));
        dest.putFloat(destOffset + 20, ((_t143) * (_t134) + (((_t144) * (_t135) + (_t145 * _t136)))));
        dest.putFloat(destOffset + 24, ((_t128) * (_t137) + (((_t130) * (_t138) + (_t132 * _t139)))));
        dest.putFloat(destOffset + 28, ((_t140) * (_t137) + (((_t141) * (_t138) + (_t142 * _t139)))));
        dest.putFloat(destOffset + 32, ((_t143) * (_t137) + (((_t144) * (_t138) + (_t145 * _t139)))));
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other01 = other.getFloat(otherOffset + 12);
        float _other11 = other.getFloat(otherOffset + 16);
        float _other21 = other.getFloat(otherOffset + 20);
        float _other02 = other.getFloat(otherOffset + 24);
        float _other12 = other.getFloat(otherOffset + 28);
        float _other22 = other.getFloat(otherOffset + 32);
        return invertProduct_degenerate_api_fma_sdbea5e19_10(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Part 1 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_1(float _other10, float _other20, float _other11, float _other21, float _other12, float _other22) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t27 = _other11 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t63 = -(_t27 * _t30);
        return java.lang.Math.fma((_other10 * _t1), (_other21 * _t2), _t63) - java.lang.Math.fma(_t27, _t30, _t63);
    }

    /** Part 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_2(float _other10, float _other20, float _other11, float _other21, float _other12, float _other22) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t64 = -_t25 * _t29;
        return java.lang.Math.fma((_other11 * _t1), (_other22 * _t2), _t64) - java.lang.Math.fma(_t25, _t29, _t64);
    }

    /** Part 3 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_3(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t123, float _t124) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t65 = -_t25 * _t30;
        return java.lang.Math.fma(_t123, (_other02 * _t0), java.lang.Math.fma(_t124, (_other00 * _t0), (java.lang.Math.fma(_t25, _t30, _t65) - java.lang.Math.fma((_other10 * _t1), (_other22 * _t2), _t65)) * (_other01 * _t0)));
    }

    /** Part 4 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_4(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t26 = _other02 * _t0;
        float _t27 = _other11 * _t1;
        float _t62 = -_t26 * _t27;
        return (java.lang.Math.fma((_other01 * _t0), (_other12 * _t1), _t62) - java.lang.Math.fma(_t26, _t27, _t62)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_5(float _self10, float _self20, float _self11, float _self21, float _self12, float _self22) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        return java.lang.Math.fma((_self10 * _t3), (_self21 * _t4), _t66) - java.lang.Math.fma(_t35, _t36, _t66);
    }

    /** Part 6 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_6(float _self10, float _self20, float _self11, float _self21, float _self12, float _self22) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        return java.lang.Math.fma((_self11 * _t3), (_self22 * _t4), _t67) - java.lang.Math.fma(_t39, _t34, _t67);
    }

    /** Part 7 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_7(float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t126, float _t127) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t36 = _self20 * _t4;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t39 = _self12 * _t3;
        float _t68 = -(_t39 * _t36);
        return java.lang.Math.fma(_t126, (_self02 * _t5), java.lang.Math.fma(_t127, (_self00 * _t5), (java.lang.Math.fma(_t39, _t36, _t68) - java.lang.Math.fma((_self10 * _t3), (_self22 * _t4), _t68)) * (_self01 * _t5)));
    }

    /** Part 8 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_8(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t24 = _other01 * _t0;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t31 = _other22 * _t2;
        float _t69 = -_t24 * _t31;
        return (java.lang.Math.fma((_other02 * _t0), (_other21 * _t2), _t69) - java.lang.Math.fma(_t24, _t31, _t69)) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 9 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_9(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other01, float _other02, float _t124, float _t166, float _t186, float _t126, float _t127, float _t167, float _t188) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t33 = _self10 * _t3;
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t38 = _self22 * _t4;
        float _t70 = -_t33 * _t38;
        float _t189 = (java.lang.Math.fma((_self12 * _t3), (_self20 * _t4), _t70) - java.lang.Math.fma(_t33, _t38, _t70)) * _t5 / _t167;
        dest.putFloat(destOffset, java.lang.Math.fma(_t186, (_t126 * _t5 / _t167), java.lang.Math.fma(_t188, _t189, (_t124 * (unitScale(_other00, _other01, _other02)) / _t166) * (_t127 * _t5 / _t167))));
        return _t189;
    }

    /** Part of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_fma_sdbea5e19_528(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t123, float _t124, float _t166, float _t186, float _t126, float _t127, float _t167, float _t188, float _t189) {
        float _t198 = invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t199 = invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t200 = invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_3(dest, destOffset, _self00, _self01, _self02, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t126, _t127, _t167, _t189, _t198, _t199);
        float _t201 = invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t202 = invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_5(dest, destOffset, _self00, _self01, _self02, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t166, _t126, _t127, _t167, _t189, _t201);
        invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_6(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t123, _t124, _t166, _t186, _t167, _t188, _t198, _t199, _t200, _t201, _t202);
        invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_7(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t123, _t124, _t166, _t186, _t167, _t188, _t198, _t199, _t200, _t201, _t202);
    }

    /** Part 1 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_1(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t32 = _other00 * _t0;
        float _t77 = -_t32 * _t25;
        return (java.lang.Math.fma((_other02 * _t0), (_other10 * _t1), _t77) - java.lang.Math.fma(_t32, _t25, _t77)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 2 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_2(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t26 = _other02 * _t0;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t78 = -_t26 * _t30;
        return (java.lang.Math.fma((_other00 * _t0), (_other22 * _t2), _t78) - java.lang.Math.fma(_t26, _t30, _t78)) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_3(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self01, float _self02, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t126, float _t127, float _t167, float _t189, float _t198, float _t199) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t28 = _other10 * _t1;
        float _t31 = _other22 * _t2;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t79 = -(_t28 * _t31);
        float _t200 = (java.lang.Math.fma((_other12 * _t1), (_other20 * _t2), _t79) - java.lang.Math.fma(_t28, _t31, _t79)) * (unitScale(_other00, _other01, _other02)) / _t166;
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t198, (_t126 * _t5 / _t167), java.lang.Math.fma(_t199, _t189, _t200 * (_t127 * _t5 / _t167))));
        return _t200;
    }

    /** Part 4 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_4(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t24 = _other01 * _t0;
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t28 = _other10 * _t1;
        float _t80 = -_t24 * _t28;
        return (java.lang.Math.fma((_other00 * _t0), (_other11 * _t1), _t80) - java.lang.Math.fma(_t24, _t28, _t80)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_5(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self01, float _self02, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t123, float _t166, float _t126, float _t127, float _t167, float _t189, float _t201) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t32 = _other00 * _t0;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t81 = -_t32 * _t29;
        float _t202 = (java.lang.Math.fma((_other01 * _t0), (_other20 * _t2), _t81) - java.lang.Math.fma(_t32, _t29, _t81)) * (unitScale(_other10, _other11, _other12)) / _t166;
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t201, (_t126 * _t5 / _t167), java.lang.Math.fma(_t202, _t189, (_t123 * _t0 / _t166) * (_t127 * _t5 / _t167))));
        return _t202;
    }

    /** Part 6 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_6(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other01, float _other02, float _t123, float _t124, float _t166, float _t186, float _t167, float _t188, float _t198, float _t199, float _t200, float _t201, float _t202) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t36 = _self20 * _t4;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t37 = _self02 * _t5;
        float _t38 = _self22 * _t4;
        float _t40 = _self00 * _t5;
        float _t41 = _self01 * _t5;
        float _t71 = -(_t40 * _t34);
        float _t192 = (java.lang.Math.fma(_t41, _t36, _t71) - java.lang.Math.fma(_t40, _t34, _t71)) * _t3 / _t167;
        float _t72 = -(_t37 * _t36);
        float _t193 = (java.lang.Math.fma(_t40, _t38, _t72) - java.lang.Math.fma(_t37, _t36, _t72)) * _t3 / _t167;
        float _t73 = -(_t41 * _t38);
        float _t194 = (java.lang.Math.fma(_t37, _t34, _t73) - java.lang.Math.fma(_t41, _t38, _t73)) * _t3 / _t167;
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t186, _t192, java.lang.Math.fma(_t188, _t193, (_t124 * _t0 / _t166) * _t194)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t198, _t192, java.lang.Math.fma(_t199, _t193, _t200 * _t194)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t201, _t192, java.lang.Math.fma(_t202, _t193, (_t123 * _t0 / _t166) * _t194)));
    }

    /** Part 7 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_fma_sdbea5e19_528_s1a3a3f59_7(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other01, float _other02, float _t123, float _t124, float _t166, float _t186, float _t167, float _t188, float _t198, float _t199, float _t200, float _t201, float _t202) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t33 = _self10 * _t3;
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t35 = _self11 * _t3;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t37 = _self02 * _t5;
        float _t39 = _self12 * _t3;
        float _t40 = _self00 * _t5;
        float _t41 = _self01 * _t5;
        float _t74 = _t41 * -_t33;
        float _t195 = (java.lang.Math.fma(_t40, _t35, _t74) - java.lang.Math.fma(_t41, _t33, _t74)) * _t4 / _t167;
        float _t75 = _t40 * -_t39;
        float _t196 = (java.lang.Math.fma(_t37, _t33, _t75) - java.lang.Math.fma(_t40, _t39, _t75)) * _t4 / _t167;
        float _t76 = _t37 * -_t35;
        float _t197 = (java.lang.Math.fma(_t41, _t39, _t76) - java.lang.Math.fma(_t37, _t35, _t76)) * _t4 / _t167;
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_t186, _t195, java.lang.Math.fma(_t188, _t196, (_t124 * _t0 / _t166) * _t197)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t198, _t195, java.lang.Math.fma(_t199, _t196, _t200 * _t197)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_t201, _t195, java.lang.Math.fma(_t202, _t196, (_t123 * _t0 / _t166) * _t197)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma_sdbea5e19_10(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        float _t123 = invertProduct_degenerate_api_fma_sdbea5e19_1(_other10, _other20, _other11, _other21, _other12, _other22);
        float _t124 = invertProduct_degenerate_api_fma_sdbea5e19_2(_other10, _other20, _other11, _other21, _other12, _other22);
        float _t166 = invertProduct_degenerate_api_fma_sdbea5e19_3(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t124);
        float _t186 = invertProduct_degenerate_api_fma_sdbea5e19_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t126 = invertProduct_degenerate_api_fma_sdbea5e19_5(_self10, _self20, _self11, _self21, _self12, _self22);
        float _t127 = invertProduct_degenerate_api_fma_sdbea5e19_6(_self10, _self20, _self11, _self21, _self12, _self22);
        float _t167 = invertProduct_degenerate_api_fma_sdbea5e19_7(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t126, _t127);
        float _t188 = invertProduct_degenerate_api_fma_sdbea5e19_8(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        invertProduct_degenerate_api_fma_sdbea5e19_528(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t124, _t166, _t186, _t126, _t127, _t167, _t188, invertProduct_degenerate_api_fma_sdbea5e19_9(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t124, _t166, _t186, _t126, _t127, _t167, _t188));
        return dest;
    }

    /** {@code invertProduct_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other01 = other.getFloat(otherOffset + 12);
        float _other11 = other.getFloat(otherOffset + 16);
        float _other21 = other.getFloat(otherOffset + 20);
        float _other02 = other.getFloat(otherOffset + 24);
        float _other12 = other.getFloat(otherOffset + 28);
        float _other22 = other.getFloat(otherOffset + 32);
        return invertProduct_degenerate_api_mulAdd_s913a584c_9(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Part 1 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_1(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t27 = _other11 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t28 = _other10 * _t1;
        float _t29 = _other21 * _t2;
        float _t30 = _other20 * _t2;
        float _t63 = -(_t27 * _t30);
        float _t31 = _other22 * _t2;
        float _t64 = -_t25 * _t29;
        float _t65 = -_t25 * _t30;
        return (((((_t28) * (_t29) + (_t63)) - ((_t27) * (_t30) + (_t63)))) * ((_other02 * _t0)) + ((((((_t27) * (_t31) + (_t64)) - ((_t25) * (_t29) + (_t64)))) * ((_other00 * _t0)) + ((((_t25) * (_t30) + (_t65)) - ((_t28) * (_t31) + (_t65))) * (_other01 * _t0)))));
    }

    /** Part 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_2(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t26 = _other02 * _t0;
        float _t27 = _other11 * _t1;
        float _t62 = -_t26 * _t27;
        return ((((_other01 * _t0)) * ((_other12 * _t1)) + (_t62)) - ((_t26) * (_t27) + (_t62))) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_3(float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t33 = _self10 * _t3;
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t38 = _self22 * _t4;
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        float _t68 = -(_t39 * _t36);
        return (((((_t33) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66)))) * ((_self02 * _t5)) + ((((((_t35) * (_t38) + (_t67)) - ((_t39) * (_t34) + (_t67)))) * ((_self00 * _t5)) + ((((_t39) * (_t36) + (_t68)) - ((_t33) * (_t38) + (_t68))) * (_self01 * _t5)))));
    }

    /** Part 4 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_4(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t24 = _other01 * _t0;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t31 = _other22 * _t2;
        float _t69 = -_t24 * _t31;
        return ((((_other02 * _t0)) * ((_other21 * _t2)) + (_t69)) - ((_t24) * (_t31) + (_t69))) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_5(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t186, float _t167, float _t188) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t64 = -_t25 * _t29;
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t33 = _self10 * _t3;
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t38 = _self22 * _t4;
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        float _t70 = -_t33 * _t38;
        float _t189 = (((_t39) * (_t36) + (_t70)) - ((_t33) * (_t38) + (_t70))) * _t5 / _t167;
        dest.putFloat(destOffset, ((_t186) * (((((_t33) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66))) * _t5 / _t167)) + (((_t188) * (_t189) + ((((((_other11 * _t1)) * ((_other22 * _t2)) + (_t64)) - ((_t25) * (_t29) + (_t64))) * (unitScale(_other00, _other01, _other02)) / _t166) * ((((_t35) * (_t38) + (_t67)) - ((_t39) * (_t34) + (_t67))) * _t5 / _t167))))));
        return _t189;
    }

    /** Part 6 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_6(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t32 = _other00 * _t0;
        float _t77 = -_t32 * _t25;
        return ((((_other02 * _t0)) * ((_other10 * _t1)) + (_t77)) - ((_t32) * (_t25) + (_t77))) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 7 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_7(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t26 = _other02 * _t0;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t78 = -_t26 * _t30;
        return ((((_other00 * _t0)) * ((_other22 * _t2)) + (_t78)) - ((_t26) * (_t30) + (_t78))) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 8 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_8(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t167, float _t189, float _t198, float _t199) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t28 = _other10 * _t1;
        float _t31 = _other22 * _t2;
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        float _t79 = -(_t28 * _t31);
        float _t200 = ((((_other12 * _t1)) * ((_other20 * _t2)) + (_t79)) - ((_t28) * (_t31) + (_t79))) * (unitScale(_other00, _other01, _other02)) / _t166;
        dest.putFloat(destOffset + 4, ((_t198) * ((((((_self10 * _t3)) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66))) * _t5 / _t167)) + (((_t199) * (_t189) + (_t200 * ((((_t35) * ((_self22 * _t4)) + (_t67)) - ((_t39) * (_t34) + (_t67))) * _t5 / _t167))))));
        return _t200;
    }

    /** Part of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_mulAdd_s913a584c_528(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t186, float _t167, float _t188, float _t189, float _t198, float _t199, float _t200) {
        float _t190 = invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t201 = invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t202 = invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_3(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t203 = invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_4(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t167, _t189, _t201, _t202);
        invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_5(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t186, _t167, _t188, _t198, _t199, _t200, _t190, _t201, _t202, _t203);
        invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_6(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t186, _t167, _t188, _t198, _t199, _t200, _t190, _t201, _t202, _t203);
    }

    /** Part 1 of {@code invertProduct_degenerate_api_mulAdd_s913a584c_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_1(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t64 = -_t25 * _t29;
        return ((((_other11 * _t1)) * ((_other22 * _t2)) + (_t64)) - ((_t25) * (_t29) + (_t64))) * (unitScale(_other00, _other01, _other02)) / _t166;
    }

    /** Part 2 of {@code invertProduct_degenerate_api_mulAdd_s913a584c_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_2(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t24 = _other01 * _t0;
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t28 = _other10 * _t1;
        float _t80 = -_t24 * _t28;
        return ((((_other00 * _t0)) * ((_other11 * _t1)) + (_t80)) - ((_t24) * (_t28) + (_t80))) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_api_mulAdd_s913a584c_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_3(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t32 = _other00 * _t0;
        float _t81 = -_t32 * _t29;
        return ((((_other01 * _t0)) * ((_other20 * _t2)) + (_t81)) - ((_t32) * (_t29) + (_t81))) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 4 of {@code invertProduct_degenerate_api_mulAdd_s913a584c_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_4(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t167, float _t189, float _t201, float _t202) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t27 = _other11 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t63 = -(_t27 * _t30);
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        float _t203 = ((((_other10 * _t1)) * ((_other21 * _t2)) + (_t63)) - ((_t27) * (_t30) + (_t63))) * (unitScale(_other00, _other01, _other02)) / _t166;
        dest.putFloat(destOffset + 8, ((_t201) * ((((((_self10 * _t3)) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66))) * _t5 / _t167)) + (((_t202) * (_t189) + (_t203 * ((((_t35) * ((_self22 * _t4)) + (_t67)) - ((_t39) * (_t34) + (_t67))) * _t5 / _t167))))));
        return _t203;
    }

    /** Part 5 of {@code invertProduct_degenerate_api_mulAdd_s913a584c_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_5(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t186, float _t167, float _t188, float _t198, float _t199, float _t200, float _t190, float _t201, float _t202, float _t203) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t36 = _self20 * _t4;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t37 = _self02 * _t5;
        float _t38 = _self22 * _t4;
        float _t40 = _self00 * _t5;
        float _t41 = _self01 * _t5;
        float _t71 = -(_t40 * _t34);
        float _t192 = (((_t41) * (_t36) + (_t71)) - ((_t40) * (_t34) + (_t71))) * _t3 / _t167;
        float _t72 = -(_t37 * _t36);
        float _t193 = (((_t40) * (_t38) + (_t72)) - ((_t37) * (_t36) + (_t72))) * _t3 / _t167;
        float _t73 = -(_t41 * _t38);
        float _t194 = (((_t37) * (_t34) + (_t73)) - ((_t41) * (_t38) + (_t73))) * _t3 / _t167;
        dest.putFloat(destOffset + 12, ((_t186) * (_t192) + (((_t188) * (_t193) + (_t190 * _t194)))));
        dest.putFloat(destOffset + 16, ((_t198) * (_t192) + (((_t199) * (_t193) + (_t200 * _t194)))));
        dest.putFloat(destOffset + 20, ((_t201) * (_t192) + (((_t202) * (_t193) + (_t203 * _t194)))));
    }

    /** Part 6 of {@code invertProduct_degenerate_api_mulAdd_s913a584c_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_mulAdd_s913a584c_528_s28c3faa8_6(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t186, float _t167, float _t188, float _t198, float _t199, float _t200, float _t190, float _t201, float _t202, float _t203) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t33 = _self10 * _t3;
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t35 = _self11 * _t3;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t37 = _self02 * _t5;
        float _t39 = _self12 * _t3;
        float _t40 = _self00 * _t5;
        float _t41 = _self01 * _t5;
        float _t74 = _t41 * -_t33;
        float _t195 = (((_t40) * (_t35) + (_t74)) - ((_t41) * (_t33) + (_t74))) * _t4 / _t167;
        float _t75 = _t40 * -_t39;
        float _t196 = (((_t37) * (_t33) + (_t75)) - ((_t40) * (_t39) + (_t75))) * _t4 / _t167;
        float _t76 = _t37 * -_t35;
        float _t197 = (((_t41) * (_t39) + (_t76)) - ((_t37) * (_t35) + (_t76))) * _t4 / _t167;
        dest.putFloat(destOffset + 24, ((_t186) * (_t195) + (((_t188) * (_t196) + (_t190 * _t197)))));
        dest.putFloat(destOffset + 28, ((_t198) * (_t195) + (((_t199) * (_t196) + (_t200 * _t197)))));
        dest.putFloat(destOffset + 32, ((_t201) * (_t195) + (((_t202) * (_t196) + (_t203 * _t197)))));
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd_s913a584c_9(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        float _t166 = invertProduct_degenerate_api_mulAdd_s913a584c_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
        float _t186 = invertProduct_degenerate_api_mulAdd_s913a584c_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t167 = invertProduct_degenerate_api_mulAdd_s913a584c_3(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
        float _t188 = invertProduct_degenerate_api_mulAdd_s913a584c_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t189 = invertProduct_degenerate_api_mulAdd_s913a584c_5(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t186, _t167, _t188);
        float _t198 = invertProduct_degenerate_api_mulAdd_s913a584c_6(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t199 = invertProduct_degenerate_api_mulAdd_s913a584c_7(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        invertProduct_degenerate_api_mulAdd_s913a584c_528(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t186, _t167, _t188, _t189, _t198, _t199, invertProduct_degenerate_api_mulAdd_s913a584c_8(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t167, _t189, _t198, _t199));
        return dest;
    }

    public static java.nio.ByteBuffer normal_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_api_fma(dest, destOffset, src, srcOffset);
        return normal_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code normal_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normal_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        float _t12 = -_self12 * _self20;
        float _t26 = java.lang.Math.fma(_self11, _self22, _t10) - java.lang.Math.fma(_self12, _self21, _t10);
        float _t27 = java.lang.Math.fma(_self10, _self21, _t11) - java.lang.Math.fma(_self11, _self20, _t11);
        float _t33 = java.lang.Math.fma(_self02, _t27, java.lang.Math.fma(_self00, _t26, _self01 * (java.lang.Math.fma(_self12, _self20, _t12) - java.lang.Math.fma(_self10, _self22, _t12))));
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsByteBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        return normal_api_fma_sa6461d64_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self10 * _self22, -(_self01 * _self22), -_self02 * _self20, -_self00 * _self21, -_self02 * _self11, -_self00 * _self12, _self01 * -_self10, _t26, _t27, 1.0f / _t33);
    }

    /** Piece 2 of {@code normal_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_api_fma_sa6461d64_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t33_inv) {
        dest.putFloat(destOffset, _t26 * _t33_inv);
        dest.putFloat(destOffset + 4, (java.lang.Math.fma(_self02, _self21, _t14) - java.lang.Math.fma(_self01, _self22, _t14)) * _t33_inv);
        dest.putFloat(destOffset + 8, (java.lang.Math.fma(_self01, _self12, _t17) - java.lang.Math.fma(_self02, _self11, _t17)) * _t33_inv);
        dest.putFloat(destOffset + 12, (java.lang.Math.fma(_self12, _self20, _t13) - java.lang.Math.fma(_self10, _self22, _t13)) * _t33_inv);
        dest.putFloat(destOffset + 16, (java.lang.Math.fma(_self00, _self22, _t15) - java.lang.Math.fma(_self02, _self20, _t15)) * _t33_inv);
        dest.putFloat(destOffset + 20, (java.lang.Math.fma(_self02, _self10, _t18) - java.lang.Math.fma(_self00, _self12, _t18)) * _t33_inv);
        dest.putFloat(destOffset + 24, _t27 * _t33_inv);
        dest.putFloat(destOffset + 28, (java.lang.Math.fma(_self01, _self20, _t16) - java.lang.Math.fma(_self00, _self21, _t16)) * _t33_inv);
        dest.putFloat(destOffset + 32, (java.lang.Math.fma(_self00, _self11, _t19) - java.lang.Math.fma(_self01, _self10, _t19)) * _t33_inv);
        return dest;
    }

    /** {@code normal_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normal_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        float _t12 = -_self12 * _self20;
        float _t26 = ((_self11) * (_self22) + (_t10)) - ((_self12) * (_self21) + (_t10));
        float _t27 = ((_self10) * (_self21) + (_t11)) - ((_self11) * (_self20) + (_t11));
        float _t33 = ((_self02) * (_t27) + (((_self00) * (_t26) + (_self01 * (((_self12) * (_self20) + (_t12)) - ((_self10) * (_self22) + (_t12)))))));
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsByteBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        float _t33_inv = 1.0f / _t33;
        dest.putFloat(destOffset, _t26 * _t33_inv);
        return normal_api_mulAdd_sddc059ab_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self10 * _self22, -(_self01 * _self22), -_self02 * _self20, -_self00 * _self21, -_self02 * _self11, -_self00 * _self12, _self01 * -_self10, _t27, _t33_inv);
    }

    /** Piece 2 of {@code normal_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_api_mulAdd_sddc059ab_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t27, float _t33_inv) {
        dest.putFloat(destOffset + 4, (((_self02) * (_self21) + (_t14)) - ((_self01) * (_self22) + (_t14))) * _t33_inv);
        dest.putFloat(destOffset + 8, (((_self01) * (_self12) + (_t17)) - ((_self02) * (_self11) + (_t17))) * _t33_inv);
        dest.putFloat(destOffset + 12, (((_self12) * (_self20) + (_t13)) - ((_self10) * (_self22) + (_t13))) * _t33_inv);
        dest.putFloat(destOffset + 16, (((_self00) * (_self22) + (_t15)) - ((_self02) * (_self20) + (_t15))) * _t33_inv);
        dest.putFloat(destOffset + 20, (((_self02) * (_self10) + (_t18)) - ((_self00) * (_self12) + (_t18))) * _t33_inv);
        dest.putFloat(destOffset + 24, _t27 * _t33_inv);
        dest.putFloat(destOffset + 28, (((_self01) * (_self20) + (_t16)) - ((_self00) * (_self21) + (_t16))) * _t33_inv);
        dest.putFloat(destOffset + 32, (((_self00) * (_self11) + (_t19)) - ((_self01) * (_self10) + (_t19))) * _t33_inv);
        return dest;
    }

    public static java.nio.ByteBuffer normal_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return normal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer normal_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return normal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code normal_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t12 = _self11 * _t0;
        float _t13 = _self22 * _t1;
        float _t14 = _self12 * _t0;
        float _t15 = _self21 * _t1;
        float _t16 = _self10 * _t0;
        float _t17 = _self20 * _t1;
        float _t18 = _self02 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self01 * _t2;
        float _t31 = -_t14 * _t15;
        return normal_degenerate_api_fma_sc11f5cfb_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -(_t12 * _t17), -_t14 * _t17, -_t16 * _t13, -(_t20 * _t13), -_t18 * _t17, -_t19 * _t15, -_t18 * _t12, -_t19 * _t14, _t20 * -_t16, java.lang.Math.fma(_t12, _t13, _t31) - java.lang.Math.fma(_t14, _t15, _t31));
    }

    /** Piece 2 of {@code normal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_fma_sc11f5cfb_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40, float _t47) {
        float _t48 = java.lang.Math.fma(_t16, _t15, _t32) - java.lang.Math.fma(_t12, _t17, _t32);
        float _t53_inv = 1.0f / java.lang.Math.fma(_t48, _t18, java.lang.Math.fma(_t47, _t19, (java.lang.Math.fma(_t14, _t17, _t33) - java.lang.Math.fma(_t16, _t13, _t33)) * _t20));
        float _sp2 = _t1 * _t53_inv;
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        dest.putFloat(destOffset, _t47 * _sp0);
        dest.putFloat(destOffset + 4, (java.lang.Math.fma(_t18, _t15, _t35) - java.lang.Math.fma(_t20, _t13, _t35)) * _sp1);
        dest.putFloat(destOffset + 8, (java.lang.Math.fma(_t20, _t14, _t38) - java.lang.Math.fma(_t18, _t12, _t38)) * _sp2);
        dest.putFloat(destOffset + 12, (java.lang.Math.fma(_t14, _t17, _t34) - java.lang.Math.fma(_t16, _t13, _t34)) * _sp0);
        dest.putFloat(destOffset + 16, (java.lang.Math.fma(_t19, _t13, _t36) - java.lang.Math.fma(_t18, _t17, _t36)) * _sp1);
        dest.putFloat(destOffset + 20, (java.lang.Math.fma(_t18, _t16, _t39) - java.lang.Math.fma(_t19, _t14, _t39)) * _sp2);
        dest.putFloat(destOffset + 24, _t48 * _sp0);
        return normal_degenerate_api_fma_sc11f5cfb_2(dest, destOffset, _t12, _t15, _t16, _t17, _t19, _t20, _t37, _t40, _sp2, _sp1);
    }

    /** Piece 3 of {@code normal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_fma_sc11f5cfb_2(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t15, float _t16, float _t17, float _t19, float _t20, float _t37, float _t40, float _sp2, float _sp1) {
        dest.putFloat(destOffset + 28, (java.lang.Math.fma(_t20, _t17, _t37) - java.lang.Math.fma(_t19, _t15, _t37)) * _sp1);
        dest.putFloat(destOffset + 32, (java.lang.Math.fma(_t19, _t12, _t40) - java.lang.Math.fma(_t20, _t16, _t40)) * _sp2);
        return dest;
    }

    /** {@code normal_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t12 = _self11 * _t0;
        float _t13 = _self22 * _t1;
        float _t14 = _self12 * _t0;
        float _t15 = _self21 * _t1;
        float _t16 = _self10 * _t0;
        float _t17 = _self20 * _t1;
        float _t18 = _self02 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self01 * _t2;
        float _t31 = -_t14 * _t15;
        return normal_degenerate_api_mulAdd_s55a86ee2_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -(_t12 * _t17), -_t14 * _t17, -_t16 * _t13, -(_t20 * _t13), -_t18 * _t17, -_t19 * _t15, -_t18 * _t12, -_t19 * _t14, _t20 * -_t16, ((_t12) * (_t13) + (_t31)) - ((_t14) * (_t15) + (_t31)));
    }

    /** Piece 2 of {@code normal_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_mulAdd_s55a86ee2_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40, float _t47) {
        float _t48 = ((_t16) * (_t15) + (_t32)) - ((_t12) * (_t17) + (_t32));
        float _t53_inv = 1.0f / ((_t48) * (_t18) + (((_t47) * (_t19) + ((((_t14) * (_t17) + (_t33)) - ((_t16) * (_t13) + (_t33))) * _t20))));
        float _sp2 = _t1 * _t53_inv;
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        dest.putFloat(destOffset, _t47 * _sp0);
        dest.putFloat(destOffset + 4, (((_t18) * (_t15) + (_t35)) - ((_t20) * (_t13) + (_t35))) * _sp1);
        dest.putFloat(destOffset + 8, (((_t20) * (_t14) + (_t38)) - ((_t18) * (_t12) + (_t38))) * _sp2);
        dest.putFloat(destOffset + 12, (((_t14) * (_t17) + (_t34)) - ((_t16) * (_t13) + (_t34))) * _sp0);
        dest.putFloat(destOffset + 16, (((_t19) * (_t13) + (_t36)) - ((_t18) * (_t17) + (_t36))) * _sp1);
        dest.putFloat(destOffset + 20, (((_t18) * (_t16) + (_t39)) - ((_t19) * (_t14) + (_t39))) * _sp2);
        dest.putFloat(destOffset + 24, _t48 * _sp0);
        dest.putFloat(destOffset + 28, (((_t20) * (_t17) + (_t37)) - ((_t19) * (_t15) + (_t37))) * _sp1);
        dest.putFloat(destOffset + 32, (((_t19) * (_t12) + (_t40)) - ((_t20) * (_t16) + (_t40))) * _sp2);
        return dest;
    }

    public static float trace_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3x3OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static float trace_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getFloat(srcOffset + 32) + (src.getFloat(srcOffset) + src.getFloat(srcOffset + 16));
    }

    public static java.nio.ByteBuffer transpose_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer transpose_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, _self02);
        dest.putFloat(destOffset + 12, _self10);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self12);
        dest.putFloat(destOffset + 24, _self20);
        dest.putFloat(destOffset + 28, _self21);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            float _eother = other.getFloat(otherOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, _eother + _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, scalar * _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, -_eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            float _eother = other.getFloat(otherOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, _eself - _eother);
        }
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        for (int _i = 0; _i < 9; _i++) {
            float _ev = v.getFloat(vOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, _ev);
        }
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float3x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset);
        float _m10 = m.getFloat(mOffset + 4);
        float _m01 = m.getFloat(mOffset + 8);
        float _m11 = m.getFloat(mOffset + 12);
        dest.putFloat(destOffset, _m00);
        dest.putFloat(destOffset + 4, _m10);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _m01);
        dest.putFloat(destOffset + 16, _m11);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float3x3OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset);
        float _m10 = m.getFloat(mOffset + 4);
        float _m01 = m.getFloat(mOffset + 8);
        float _m11 = m.getFloat(mOffset + 12);
        float _m02 = m.getFloat(mOffset + 16);
        float _m12 = m.getFloat(mOffset + 20);
        dest.putFloat(destOffset, _m00);
        dest.putFloat(destOffset + 4, _m10);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _m01);
        dest.putFloat(destOffset + 16, _m11);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, _m02);
        dest.putFloat(destOffset + 28, _m12);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float3x3OpsKernelsAddress.setMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset);
        float _m01 = m.getFloat(mOffset + 4);
        float _m02 = m.getFloat(mOffset + 8);
        float _m10 = m.getFloat(mOffset + 16);
        float _m11 = m.getFloat(mOffset + 20);
        float _m12 = m.getFloat(mOffset + 24);
        float _m20 = m.getFloat(mOffset + 32);
        float _m21 = m.getFloat(mOffset + 36);
        float _m22 = m.getFloat(mOffset + 40);
        dest.putFloat(destOffset, _m00);
        dest.putFloat(destOffset + 4, _m10);
        dest.putFloat(destOffset + 8, _m20);
        dest.putFloat(destOffset + 12, _m01);
        dest.putFloat(destOffset + 16, _m11);
        dest.putFloat(destOffset + 20, _m21);
        dest.putFloat(destOffset + 24, _m02);
        dest.putFloat(destOffset + 28, _m12);
        dest.putFloat(destOffset + 32, _m22);
        return dest;
    }

    public static java.nio.ByteBuffer setMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float3x3OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            int _lom = _l * 4;
            float _em0 = m.getFloat(mOffset + _lom * 4);
            float _em1 = m.getFloat(mOffset + (_lom + 1) * 4);
            float _em2 = m.getFloat(mOffset + (_lom + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, _em0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _em1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _em2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float tX, float tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float tX, float tY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, tX);
        dest.putFloat(destOffset + 28, tY);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Float3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        float _tx = t.getFloat(tOffset);
        float _ty = t.getFloat(tOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, _tx);
        dest.putFloat(destOffset + 28, _ty);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromRigid_unsafe(java.nio.ByteBuffer dest, int destOffset, float rRX, float rRY, float rRZ, float rRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromRigid_api(java.nio.ByteBuffer dest, int destOffset, float rRX, float rRY, float rRZ, float rRW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromRigid_api_mulAdd(dest, destOffset, rRX, rRY, rRZ, rRW);
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(rRY, rRY, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(rRX, rRY, _t1));
        dest.putFloat(destOffset + 8, 2.0f * java.lang.Math.fma(rRX, rRZ, -_t2));
        dest.putFloat(destOffset + 12, 2.0f * java.lang.Math.fma(rRX, rRY, -_t1));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(-2.0f, java.lang.Math.fma(rRX, rRX, _t0), 1.0f));
        dest.putFloat(destOffset + 20, 2.0f * java.lang.Math.fma(rRX, rRW, rRY * rRZ));
        dest.putFloat(destOffset + 24, 2.0f * java.lang.Math.fma(rRX, rRZ, _t2));
        dest.putFloat(destOffset + 28, 2.0f * java.lang.Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(-2.0f, java.lang.Math.fma(rRX, rRX, rRY * rRY), 1.0f));
        return dest;
    }

    /** {@code makeFromRigid_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeFromRigid_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float rRX, float rRY, float rRZ, float rRW) {
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        dest.putFloat(destOffset, ((1.0f) - (2.0f) * (((rRY) * (rRY) + (_t0)))));
        dest.putFloat(destOffset + 4, 2.0f * ((rRX) * (rRY) + (_t1)));
        dest.putFloat(destOffset + 8, 2.0f * ((rRX) * (rRZ) - (_t2)));
        dest.putFloat(destOffset + 12, 2.0f * ((rRX) * (rRY) - (_t1)));
        dest.putFloat(destOffset + 16, ((1.0f) - (2.0f) * (((rRX) * (rRX) + (_t0)))));
        dest.putFloat(destOffset + 20, 2.0f * ((rRX) * (rRW) + (rRY * rRZ)));
        dest.putFloat(destOffset + 24, 2.0f * ((rRX) * (rRZ) + (_t2)));
        dest.putFloat(destOffset + 28, 2.0f * ((rRY) * (rRZ) - (rRX * rRW)));
        dest.putFloat(destOffset + 32, ((1.0f) - (2.0f) * (((rRX) * (rRX) + (rRY * rRY)))));
        return dest;
    }

    public static java.nio.ByteBuffer makeFromTransform_unsafe(java.nio.ByteBuffer dest, int destOffset, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromTransform_api(java.nio.ByteBuffer dest, int destOffset, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromTransform_api_mulAdd(dest, destOffset, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        dest.putFloat(destOffset, java.lang.Math.fma(-java.lang.Math.fma(tRY, tRY, _t3), _t0, tSX));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(tRX, tRY, _t4) * _t0);
        dest.putFloat(destOffset + 8, java.lang.Math.fma(tRX, tRZ, -_t5) * _t0);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(tRX, tRY, -_t4) * _t1);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(-java.lang.Math.fma(tRX, tRX, _t3), _t1, tSY));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(tRX, tRZ, _t5) * _t2);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        dest.putFloat(destOffset + 32, java.lang.Math.fma(-java.lang.Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        return dest;
    }

    /** {@code makeFromTransform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeFromTransform_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        dest.putFloat(destOffset, ((tSX) - ((tRY) * (tRY) + (_t3)) * (_t0)));
        dest.putFloat(destOffset + 4, ((tRX) * (tRY) + (_t4)) * _t0);
        dest.putFloat(destOffset + 8, ((tRX) * (tRZ) - (_t5)) * _t0);
        dest.putFloat(destOffset + 12, ((tRX) * (tRY) - (_t4)) * _t1);
        dest.putFloat(destOffset + 16, ((tSY) - ((tRX) * (tRX) + (_t3)) * (_t1)));
        dest.putFloat(destOffset + 20, ((tRX) * (tRW) + (tRY * tRZ)) * _t1);
        dest.putFloat(destOffset + 24, ((tRX) * (tRZ) + (_t5)) * _t2);
        dest.putFloat(destOffset + 28, ((tRY) * (tRZ) - (tRX * tRW)) * _t2);
        dest.putFloat(destOffset + 32, ((tSZ) - ((tRX) * (tRX) + (tRY * tRY)) * (_t2)));
        return dest;
    }

    public static java.nio.ByteBuffer to2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        return dest;
    }

    public static java.nio.ByteBuffer to2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _loself = _l * 3;
            float _eself0 = src.getFloat(srcOffset + _loself * 4);
            float _eself1 = src.getFloat(srcOffset + (_loself + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer to3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.to3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, _self02);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _self10);
        dest.putFloat(destOffset + 20, _self11);
        dest.putFloat(destOffset + 24, _self12);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, _self20);
        dest.putFloat(destOffset + 36, _self21);
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer to4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _self01);
        dest.putFloat(destOffset + 20, _self11);
        dest.putFloat(destOffset + 24, _self21);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, _self02);
        dest.putFloat(destOffset + 36, _self12);
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, 0.0f);
        dest.putFloat(destOffset + 48, 0.0f);
        dest.putFloat(destOffset + 52, 0.0f);
        dest.putFloat(destOffset + 56, 0.0f);
        dest.putFloat(destOffset + 60, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t1 = 1.0f - _self00;
        float _t13 = _self22 + (_self00 + _self11);
        float _t14 = 1.0f + _t13;
        float _t15 = _self00 + (1.0f - _self11 - _self22);
        float _t16 = _self11 + (_t1 - _self22);
        float _t17 = _self22 + (_t1 - _self11);
        return toDualQuat_api_s7675600_1(dest, destOffset, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t13, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code toDualQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toDualQuat_api_s7675600_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self11, float _self22, float _t3, float _t5, float _t6, float _t7, float _t8, float _t9, float _t13, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t13 > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t3);
            dest.putFloat(destOffset + 4, _sp0 * _t7);
            dest.putFloat(destOffset + 8, _sp0 * _t9);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.putFloat(destOffset + 4, _sp3 * _t5);
                dest.putFloat(destOffset + 8, _sp3 * _t6);
                dest.putFloat(destOffset + 12, _sp3 * _t3);
            } else {
                if (_self11 > _self22) {
                    dest.putFloat(destOffset, _sp1 * _t5);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.putFloat(destOffset + 8, _sp1 * _t8);
                    dest.putFloat(destOffset + 12, _sp1 * _t7);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t6);
                    dest.putFloat(destOffset + 4, _sp2 * _t8);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.putFloat(destOffset + 12, _sp2 * _t9);
                }
            }
        }
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_api_fma(dest, destOffset, src, srcOffset);
        return toRigid_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toRigid_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _ct0 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _ct1 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _ct2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        toRigid_api_fma_se91a3fa7_515(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _ct0, _ct1, _ct2);
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        return dest;
    }

    /** Part of {@code toRigid_api_fma}, split to fit the inline budget; reached only through it. */
    private static void toRigid_api_fma_se91a3fa7_515(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _ct0, float _ct1, float _ct2) {
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _t18 = _self10 * _t17;
        float _t19 = _self22 * _t16;
        float _t20 = _self12 * _t16;
        float _t21 = _self20 * _t17;
        float _t23 = _self21 * _t15;
        float _t24 = _self11 * _t15;
        float _t26 = _self00 * _t17;
        float _t47, _t48, _t49;
        if (java.lang.Math.fma(-java.lang.Math.fma(_t18, _t19, -(_t20 * _t21)), _self01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t18, _t23, -(_t24 * _t21)), _self02 * _t16, java.lang.Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        float _t51 = 1.0f + _t47;
        float _t63 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t51));
        toRigid_api_fma_se91a3fa7_515_s5f991e0a_1(dest, destOffset, _self11, _self22, -_self11, -_self22, _t15, _t16, _t19, _t24, java.lang.Math.fma(_self12, _t16, _t23), java.lang.Math.fma(_self21, _t15, -_t20), _t47, _t51, 1.0f - _t47, java.lang.Math.fma(_self01, _t15, _t48), java.lang.Math.fma(_self02, _t16, _t49), java.lang.Math.fma(_self02, _t16, -_t49), java.lang.Math.fma(-_self01, _t15, _t48), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)));
    }

    /** Piece 2 of {@code toRigid_api_fma_se91a3fa7_515}, split to fit the inline budget; reached only through it. */
    private static void toRigid_api_fma_se91a3fa7_515_s5f991e0a_1(java.nio.ByteBuffer dest, int destOffset, float _self11, float _self22, float _t0, float _t1, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t51, float _t52, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0) {
        float _t65 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        float _t66 = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t52));
        float _t67 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t51));
        toRigid_api_fma_se91a3fa7_515_s5f991e0a_2(dest, destOffset, _self11, _self22, _t15, _t16, _t19, _t24, _t31, _t35, _t47, _t54, _t55, _t56, _t57, _t63, _sp0, _t65, _t66, _t67, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67)));
    }

    /** Piece 3 of {@code toRigid_api_fma_se91a3fa7_515}, split to fit the inline budget; reached only through it. */
    private static void toRigid_api_fma_se91a3fa7_515_s5f991e0a_2(java.nio.ByteBuffer dest, int destOffset, float _self11, float _self22, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t47)) > 0.0f) {
            dest.putFloat(destOffset + 12, _sp0 * _t35);
            dest.putFloat(destOffset + 16, _sp0 * _t56);
            dest.putFloat(destOffset + 20, _sp0 * _t57);
            dest.putFloat(destOffset + 24, 0.5f * (float) java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t67));
                dest.putFloat(destOffset + 16, _sp3 * _t54);
                dest.putFloat(destOffset + 20, _sp3 * _t55);
                dest.putFloat(destOffset + 24, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.putFloat(destOffset + 12, _sp1 * _t54);
                    dest.putFloat(destOffset + 16, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    dest.putFloat(destOffset + 20, _sp1 * _t31);
                    dest.putFloat(destOffset + 24, _sp1 * _t56);
                } else {
                    dest.putFloat(destOffset + 12, _sp2 * _t55);
                    dest.putFloat(destOffset + 16, _sp2 * _t31);
                    dest.putFloat(destOffset + 20, 0.5f * (float) java.lang.Math.sqrt(_t66));
                    dest.putFloat(destOffset + 24, _sp2 * _t57);
                }
            }
        }
    }

    /** {@code toRigid_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _ct0 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _ct1 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        return toRigid_api_mulAdd_sc991057e_1(dest, destOffset, _self00, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, (1.0f / (float) java.lang.Math.sqrt(_ct0)), _t16, _t17, _self10 * _t17, _self22 * _t16, _self12 * _t16, _self20 * _t17);
    }

    /** Piece 2 of {@code toRigid_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_mulAdd_sc991057e_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t0, float _t1, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21) {
        float _t23 = _self21 * _t15;
        float _t24 = _self11 * _t15;
        float _t26 = _self00 * _t17;
        float _t47, _t48, _t49;
        if (((((((_t18) * (_t23) - (_t24 * _t21))) * (_self02 * _t16) + (((_t24) * (_t19) - (_t20 * _t23)) * _t26))) - ((_t18) * (_t19) - (_t20 * _t21)) * (_self01 * _t15)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        float _t51 = 1.0f + _t47;
        float _t52 = 1.0f - _t47;
        float _t63 = ((_self11) * (_t15) + (((_self22) * (_t16) + (_t51))));
        float _t65 = ((_self11) * (_t15) + (((_t1) * (_t16) + (_t52))));
        float _t66 = ((_self22) * (_t16) + (((_t0) * (_t15) + (_t52))));
        float _t67 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t51))));
        return toRigid_api_mulAdd_sc991057e_2(dest, destOffset, _self11, _self22, _t15, _t16, _t19, _t24, ((_self12) * (_t16) + (_t23)), ((_self21) * (_t15) - (_t20)), _t47, ((_self01) * (_t15) + (_t48)), ((_self02) * (_t16) + (_t49)), ((_self02) * (_t16) - (_t49)), ((_t48) - (_self01) * (_t15)), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)), _t65, _t66, _t67, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67)));
    }

    /** Piece 3 of {@code toRigid_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_mulAdd_sc991057e_2(java.nio.ByteBuffer dest, int destOffset, float _self11, float _self22, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1, float _sp2, float _sp3) {
        if (((_self11) * (_t15) + (((_self22) * (_t16) + (_t47)))) > 0.0f) {
            dest.putFloat(destOffset + 12, _sp0 * _t35);
            dest.putFloat(destOffset + 16, _sp0 * _t56);
            dest.putFloat(destOffset + 20, _sp0 * _t57);
            dest.putFloat(destOffset + 24, 0.5f * (float) java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t67));
                dest.putFloat(destOffset + 16, _sp3 * _t54);
                dest.putFloat(destOffset + 20, _sp3 * _t55);
                dest.putFloat(destOffset + 24, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.putFloat(destOffset + 12, _sp1 * _t54);
                    dest.putFloat(destOffset + 16, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    dest.putFloat(destOffset + 20, _sp1 * _t31);
                    dest.putFloat(destOffset + 24, _sp1 * _t56);
                } else {
                    dest.putFloat(destOffset + 12, _sp2 * _t55);
                    dest.putFloat(destOffset + 16, _sp2 * _t31);
                    dest.putFloat(destOffset + 20, 0.5f * (float) java.lang.Math.sqrt(_t66));
                    dest.putFloat(destOffset + 24, _sp2 * _t57);
                }
            }
        }
        return toRigid_api_mulAdd_sc991057e_3(dest, destOffset);
    }

    /** Piece 4 of {@code toRigid_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_mulAdd_sc991057e_3(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toRigid_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer toRigid_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toRigid_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toRigid_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toRigid_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = unitScale(_self01, _self11, _self21);
        float _t1 = unitScale(_self02, _self12, _self22);
        float _t2 = unitScale(_self00, _self10, _self20);
        float _t12 = _self21 * _t0;
        float _t13 = _self01 * _t0;
        float _t14 = _self11 * _t0;
        float _t15 = _self22 * _t1;
        float _t16 = _self02 * _t1;
        float _t17 = _self12 * _t1;
        float _t18 = _self20 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self10 * _t2;
        float _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
        float _t33 = _t30 * _t18;
        float _t34 = _t30 * _t19;
        float _t35 = _t30 * _t20;
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t89, _t89, _t74 * _t74))));
        float _t102 = _t99 * _t72;
        float _t103 = _t100 * _t73;
        float _t104 = _t101 * _t74;
        float _t105 = _t100 * _t76;
        float _t106 = _t99 * _t75;
        float _t107 = _t101 * _t77;
        float _t114 = _t100 * _t88;
        float _t115 = _t101 * _t89;
        float _t116 = _t99 * _t87;
        float _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t167 = 1.0f;
                    _t170 = 0.0f;
                    _t166 = 0.0f;
                    _t168 = 0.0f;
                    _t171 = 1.0f;
                    _t169 = 0.0f;
                    _t172 = 0.0f;
                    _t173 = 1.0f;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = java.lang.Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = java.lang.Math.fma(_t35, _t106, -_t33 * _t116);
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = java.lang.Math.fma(_t36, _t105, -_t37 * _t114);
                    _t167 = java.lang.Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = java.lang.Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = java.lang.Math.fma(_t33, _t36, _t35 * -_t37);
                    _t167 = java.lang.Math.fma(_t34, _t37, -_t33 * _t38);
                    _t170 = java.lang.Math.fma(_t35, _t38, -(_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = java.lang.Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = java.lang.Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = java.lang.Math.fma(_t41, _t107, -_t40 * _t115);
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = java.lang.Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = java.lang.Math.fma(_t35, _t40, -_t33 * _t41);
                    _t171 = java.lang.Math.fma(_t34, _t41, -(_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = java.lang.Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = java.lang.Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = java.lang.Math.fma(_t41, _t37, -_t40 * _t36);
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
        float _t194, _t195, _t196;
        if (java.lang.Math.fma(java.lang.Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, java.lang.Math.fma(java.lang.Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, java.lang.Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        float _t199 = _t195 + _t165;
        float _t200 = _t196 + _t168;
        float _t201 = _t168 - _t196;
        float _t202 = _t195 - _t165;
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dest.putFloat(destOffset + 12, _sp0 * _t182);
            dest.putFloat(destOffset + 16, _sp0 * _t201);
            dest.putFloat(destOffset + 20, _sp0 * _t202);
            dest.putFloat(destOffset + 24, 0.5f * (float) java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t208));
                dest.putFloat(destOffset + 16, _sp3 * _t199);
                dest.putFloat(destOffset + 20, _sp3 * _t200);
                dest.putFloat(destOffset + 24, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.putFloat(destOffset + 12, _sp1 * _t199);
                    dest.putFloat(destOffset + 16, 0.5f * (float) java.lang.Math.sqrt(_t209));
                    dest.putFloat(destOffset + 20, _sp1 * _t184);
                    dest.putFloat(destOffset + 24, _sp1 * _t201);
                } else {
                    dest.putFloat(destOffset + 12, _sp2 * _t200);
                    dest.putFloat(destOffset + 16, _sp2 * _t184);
                    dest.putFloat(destOffset + 20, 0.5f * (float) java.lang.Math.sqrt(_t210));
                    dest.putFloat(destOffset + 24, _sp2 * _t202);
                }
            }
        }
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        return dest;
    }

    /** {@code toRigid_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toRigid_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = unitScale(_self01, _self11, _self21);
        float _t1 = unitScale(_self02, _self12, _self22);
        float _t2 = unitScale(_self00, _self10, _self20);
        float _t12 = _self21 * _t0;
        float _t13 = _self01 * _t0;
        float _t14 = _self11 * _t0;
        float _t15 = _self22 * _t1;
        float _t16 = _self02 * _t1;
        float _t17 = _self12 * _t1;
        float _t18 = _self20 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self10 * _t2;
        float _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        float _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
        float _t33 = _t30 * _t18;
        float _t34 = _t30 * _t19;
        float _t35 = _t30 * _t20;
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(((_t75) * (_t75) + (((_t87) * (_t87) + (_t72 * _t72))))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(((_t76) * (_t76) + (((_t88) * (_t88) + (_t73 * _t73))))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(((_t77) * (_t77) + (((_t89) * (_t89) + (_t74 * _t74))))));
        float _t102 = _t99 * _t72;
        float _t103 = _t100 * _t73;
        float _t104 = _t101 * _t74;
        float _t105 = _t100 * _t76;
        float _t106 = _t99 * _t75;
        float _t107 = _t101 * _t77;
        float _t114 = _t100 * _t88;
        float _t115 = _t101 * _t89;
        float _t116 = _t99 * _t87;
        float _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t167 = 1.0f;
                    _t170 = 0.0f;
                    _t166 = 0.0f;
                    _t168 = 0.0f;
                    _t171 = 1.0f;
                    _t169 = 0.0f;
                    _t172 = 0.0f;
                    _t173 = 1.0f;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = ((_t33) * (_t102) - (_t34 * _t106));
                    _t168 = ((_t35) * (_t106) - (_t33 * _t116));
                    _t171 = ((_t34) * (_t116) - (_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = ((_t36) * (_t105) - (_t37 * _t114));
                    _t167 = ((_t37) * (_t103) - (_t38 * _t105));
                    _t170 = ((_t38) * (_t114) - (_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = ((_t33) * (_t36) - (_t35 * _t37));
                    _t167 = ((_t34) * (_t37) - (_t33 * _t38));
                    _t170 = ((_t35) * (_t38) - (_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = ((_t39) * (_t115) - (_t41 * _t104));
                    _t172 = ((_t40) * (_t104) - (_t39 * _t107));
                    _t173 = ((_t41) * (_t107) - (_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = ((_t33) * (_t39) - (_t34 * _t40));
                    _t168 = ((_t35) * (_t40) - (_t33 * _t41));
                    _t171 = ((_t34) * (_t41) - (_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = ((_t39) * (_t36) - (_t41 * _t38));
                    _t172 = ((_t40) * (_t38) - (_t39 * _t37));
                    _t173 = ((_t41) * (_t37) - (_t40 * _t36));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
        float _t194, _t195, _t196;
        if (((((_t165) * (_t166) - (_t167 * _t168))) * (_t169) + (((((_t170) * (_t168) - (_t165 * _t171))) * (_t172) + (((_t167) * (_t171) - (_t170 * _t166)) * _t173)))) < 0.0f) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        float _t199 = _t195 + _t165;
        float _t200 = _t196 + _t168;
        float _t201 = _t168 - _t196;
        float _t202 = _t195 - _t165;
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dest.putFloat(destOffset + 12, _sp0 * _t182);
            dest.putFloat(destOffset + 16, _sp0 * _t201);
            dest.putFloat(destOffset + 20, _sp0 * _t202);
            dest.putFloat(destOffset + 24, 0.5f * (float) java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t208));
                dest.putFloat(destOffset + 16, _sp3 * _t199);
                dest.putFloat(destOffset + 20, _sp3 * _t200);
                dest.putFloat(destOffset + 24, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.putFloat(destOffset + 12, _sp1 * _t199);
                    dest.putFloat(destOffset + 16, 0.5f * (float) java.lang.Math.sqrt(_t209));
                    dest.putFloat(destOffset + 20, _sp1 * _t184);
                    dest.putFloat(destOffset + 24, _sp1 * _t201);
                } else {
                    dest.putFloat(destOffset + 12, _sp2 * _t200);
                    dest.putFloat(destOffset + 16, _sp2 * _t184);
                    dest.putFloat(destOffset + 20, 0.5f * (float) java.lang.Math.sqrt(_t210));
                    dest.putFloat(destOffset + 24, _sp2 * _t202);
                }
            }
        }
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_api_fma(dest, destOffset, src, srcOffset);
        return toTransform_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toTransform_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t12 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t14 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        return toTransform_api_fma_sc11e3d02_1(dest, destOffset, _self00, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, (1.0f / (float) java.lang.Math.sqrt(_t12)), (1.0f / (float) java.lang.Math.sqrt(_t13)), _t18, _t17, _self10 * _t17);
    }

    /** Piece 2 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_fma_sc11e3d02_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t17, float _t19) {
        float _t20 = _self22 * _t16;
        float _t21 = _self12 * _t16;
        float _t22 = _self20 * _t17;
        float _t24 = _self21 * _t15;
        float _t25 = _self11 * _t15;
        float _t27 = _self00 * _t17;
        float _t47 = java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _self01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _self02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
        float _t48, _t49, _t50;
        if (_t47 < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        float _t52 = 1.0f + _t48;
        float _t64 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t52));
        return toTransform_api_fma_sc11e3d02_2(dest, destOffset, _self11, _self22, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, java.lang.Math.fma(_self12, _t16, _t24), java.lang.Math.fma(_self21, _t15, -_t21), Math.max(_t25, _t20), _t47, _t48, _t52, 1.0f - _t48, java.lang.Math.fma(_self01, _t15, _t49), java.lang.Math.fma(_self02, _t16, _t50), java.lang.Math.fma(_self02, _t16, -_t50), java.lang.Math.fma(-_self01, _t15, _t49), java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t48)), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)));
    }

    /** Piece 3 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_fma_sc11e3d02_2(java.nio.ByteBuffer dest, int destOffset, float _self11, float _self22, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t52, float _t53, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0) {
        float _t66 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t53));
        float _t67 = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t53));
        float _t68 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        dest.putFloat(destOffset + 16, _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        return toTransform_api_fma_sc11e3d02_3(dest, destOffset, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t56, _t57, _t58, _t63, _t64, _sp0, _t67, _sp1, _sp2, _sp3);
    }

    /** Piece 4 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_fma_sc11e3d02_3(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t67, float _sp1, float _sp2, float _sp3) {
        dest.putFloat(destOffset + 20, _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) java.lang.Math.sqrt(_t67));
        dest.putFloat(destOffset + 24, _t63 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.putFloat(destOffset + 28, _t47 < 0.0f ? -_t18 : _t18);
        dest.putFloat(destOffset + 32, (float) java.lang.Math.sqrt(_t12));
        dest.putFloat(destOffset + 36, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code toTransform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t12 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t14 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        return toTransform_api_mulAdd_s47b95861_1(dest, destOffset, _self00, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, (1.0f / (float) java.lang.Math.sqrt(_t12)), _t16, _t18, _t17, _self10 * _t17, _self22 * _t16);
    }

    /** Piece 2 of {@code toTransform_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_mulAdd_s47b95861_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t17, float _t19, float _t20) {
        float _t21 = _self12 * _t16;
        float _t22 = _self20 * _t17;
        float _t24 = _self21 * _t15;
        float _t25 = _self11 * _t15;
        float _t27 = _self00 * _t17;
        float _t47 = ((((((_t19) * (_t24) - (_t25 * _t22))) * (_self02 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_self01 * _t15));
        float _t48, _t49, _t50;
        if (_t47 < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        float _t64 = ((_self11) * (_t15) + (((_self22) * (_t16) + (_t52))));
        return toTransform_api_mulAdd_s47b95861_2(dest, destOffset, _t12, _t13, _t18, _t20, _t25, ((_self12) * (_t16) + (_t24)), ((_self21) * (_t15) - (_t21)), Math.max(_t25, _t20), _t47, _t48, ((_self01) * (_t15) + (_t49)), ((_self02) * (_t16) + (_t50)), ((_self02) * (_t16) - (_t50)), ((_t49) - (_self01) * (_t15)), ((_self11) * (_t15) + (((_self22) * (_t16) + (_t48)))), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), ((_self11) * (_t15) + (((_t1) * (_t16) + (_t53)))), ((_self22) * (_t16) + (((_t0) * (_t15) + (_t53)))), ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))));
    }

    /** Piece 3 of {@code toTransform_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_mulAdd_s47b95861_2(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _t68) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        dest.putFloat(destOffset + 16, _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        dest.putFloat(destOffset + 20, _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) java.lang.Math.sqrt(_t67));
        return toTransform_api_mulAdd_s47b95861_3(dest, destOffset, _t12, _t13, _t18, _t20, _t25, _t36, _t37, _t47, _t48, _t57, _t58, _t63, _t64, _sp1, _sp2, _sp3);
    }

    /** Piece 4 of {@code toTransform_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_mulAdd_s47b95861_3(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t13, float _t18, float _t20, float _t25, float _t36, float _t37, float _t47, float _t48, float _t57, float _t58, float _t63, float _t64, float _sp1, float _sp2, float _sp3) {
        dest.putFloat(destOffset + 24, _t63 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.putFloat(destOffset + 28, _t47 < 0.0f ? -_t18 : _t18);
        dest.putFloat(destOffset + 32, (float) java.lang.Math.sqrt(_t12));
        dest.putFloat(destOffset + 36, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toTransform_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer toTransform_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toTransform_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toTransform_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toTransform_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = unitScale(_self01, _self11, _self21);
        float _t1 = unitScale(_self02, _self12, _self22);
        float _t2 = unitScale(_self00, _self10, _self20);
        float _t12 = _self21 * _t0;
        float _t13 = _self01 * _t0;
        float _t14 = _self11 * _t0;
        float _t15 = _self22 * _t1;
        float _t16 = _self02 * _t1;
        float _t17 = _self12 * _t1;
        float _t18 = _self20 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self10 * _t2;
        float _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        float _t105 = _t102 * _t75;
        float _t106 = _t103 * _t76;
        float _t107 = _t104 * _t77;
        float _t108 = _t103 * _t79;
        float _t109 = _t102 * _t78;
        float _t110 = _t104 * _t80;
        float _t117 = _t103 * _t91;
        float _t118 = _t104 * _t92;
        float _t119 = _t102 * _t90;
        float _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t170 = 1.0f;
                    _t173 = 0.0f;
                    _t169 = 0.0f;
                    _t171 = 0.0f;
                    _t174 = 1.0f;
                    _t172 = 0.0f;
                    _t175 = 0.0f;
                    _t176 = 1.0f;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = java.lang.Math.fma(_t38, _t108, -_t39 * _t117);
                    _t170 = java.lang.Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                    _t170 = java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
                    _t173 = java.lang.Math.fma(_t37, _t40, -(_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = java.lang.Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = java.lang.Math.fma(_t43, _t110, -_t42 * _t118);
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = java.lang.Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = java.lang.Math.fma(_t37, _t42, -_t35 * _t43);
                    _t174 = java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = java.lang.Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = java.lang.Math.fma(_t43, _t39, -_t42 * _t38);
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        float _t185 = _t173 - _t169;
        float _t186 = Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = java.lang.Math.fma(java.lang.Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, java.lang.Math.fma(java.lang.Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, java.lang.Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        float _t197, _t198, _t199;
        if (_t196 < 0.0f) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        float _t202 = _t198 + _t168;
        float _t203 = _t199 + _t171;
        float _t204 = _t171 - _t199;
        float _t205 = _t198 - _t168;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211));
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.putFloat(destOffset + 16, _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        dest.putFloat(destOffset + 20, _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213));
        dest.putFloat(destOffset + 24, _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.putFloat(destOffset + 28, _t196 < 0.0f ? -_t56 : _t56);
        dest.putFloat(destOffset + 32, _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0);
        dest.putFloat(destOffset + 36, _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    /** {@code toTransform_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toTransform_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = unitScale(_self01, _self11, _self21);
        float _t1 = unitScale(_self02, _self12, _self22);
        float _t2 = unitScale(_self00, _self10, _self20);
        float _t12 = _self21 * _t0;
        float _t13 = _self01 * _t0;
        float _t14 = _self11 * _t0;
        float _t15 = _self22 * _t1;
        float _t16 = _self02 * _t1;
        float _t17 = _self12 * _t1;
        float _t18 = _self20 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self10 * _t2;
        float _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        float _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        float _t105 = _t102 * _t75;
        float _t106 = _t103 * _t76;
        float _t107 = _t104 * _t77;
        float _t108 = _t103 * _t79;
        float _t109 = _t102 * _t78;
        float _t110 = _t104 * _t80;
        float _t117 = _t103 * _t91;
        float _t118 = _t104 * _t92;
        float _t119 = _t102 * _t90;
        float _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t170 = 1.0f;
                    _t173 = 0.0f;
                    _t169 = 0.0f;
                    _t171 = 0.0f;
                    _t174 = 1.0f;
                    _t172 = 0.0f;
                    _t175 = 0.0f;
                    _t176 = 1.0f;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                    _t174 = ((_t36) * (_t119) - (_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = ((_t38) * (_t108) - (_t39 * _t117));
                    _t170 = ((_t39) * (_t106) - (_t40 * _t108));
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = ((_t35) * (_t38) - (_t37 * _t39));
                    _t170 = ((_t36) * (_t39) - (_t35 * _t40));
                    _t173 = ((_t37) * (_t40) - (_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = ((_t41) * (_t118) - (_t43 * _t107));
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                    _t176 = ((_t43) * (_t110) - (_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = ((_t35) * (_t41) - (_t36 * _t42));
                    _t171 = ((_t37) * (_t42) - (_t35 * _t43));
                    _t174 = ((_t36) * (_t43) - (_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = ((_t41) * (_t38) - (_t43 * _t40));
                    _t175 = ((_t42) * (_t40) - (_t41 * _t39));
                    _t176 = ((_t43) * (_t39) - (_t42 * _t38));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        float _t185 = _t173 - _t169;
        float _t186 = Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = ((((_t168) * (_t169) - (_t170 * _t171))) * (_t172) + (((((_t173) * (_t171) - (_t168 * _t174))) * (_t175) + (((_t170) * (_t174) - (_t173 * _t169)) * _t176))));
        float _t197, _t198, _t199;
        if (_t196 < 0.0f) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        float _t202 = _t198 + _t168;
        float _t203 = _t199 + _t171;
        float _t204 = _t171 - _t199;
        float _t205 = _t198 - _t168;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211));
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.putFloat(destOffset + 16, _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        dest.putFloat(destOffset + 20, _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213));
        dest.putFloat(destOffset + 24, _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.putFloat(destOffset + 28, _t196 < 0.0f ? -_t56 : _t56);
        dest.putFloat(destOffset + 32, _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0);
        dest.putFloat(destOffset + 36, _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeRotation_api_fma(dest, destOffset, src, srcOffset);
        return decomposeRotation_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code decomposeRotation_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_self21, _t7, java.lang.Math.fma(_self01, _t8, _self11 * _t9));
        float _t30 = -java.lang.Math.fma(java.lang.Math.fma(_t20, _t7, _self21), _t7, java.lang.Math.fma(java.lang.Math.fma(_t20, _t8, _self01), _t8, java.lang.Math.fma(_t20, _t9, _self11) * _t9));
        return decomposeRotation_api_fma_sf6399b1a_1(dest, destOffset, _self11, _self02, _self12, _self22, _t7, _t8, _t9, -java.lang.Math.fma(_self22, _t7, java.lang.Math.fma(_self02, _t8, _self12 * _t9)), _t20, _t30, java.lang.Math.fma(_t20, _t7, java.lang.Math.fma(_t30, _t7, _self21)), java.lang.Math.fma(_t20, _t8, java.lang.Math.fma(_t30, _t8, _self01)));
    }

    /** Piece 2 of {@code decomposeRotation_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_fma_sf6399b1a_1(java.nio.ByteBuffer dest, int destOffset, float _self11, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t19, float _t20, float _t30, float _t34, float _t35) {
        float _t36 = java.lang.Math.fma(_t20, _t9, java.lang.Math.fma(_t30, _t9, _self11));
        float _t39 = java.lang.Math.fma(_t34, _t34, java.lang.Math.fma(_t35, _t35, _t36 * _t36));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t39));
        float _t44, _t45, _t46;
        if (_t39 != 0.0f) {
            _t44 = _t34 * _t40;
            _t45 = _t35 * _t40;
            _t46 = _t36 * _t40;
        } else {
            _t44 = 0.0f;
            _t45 = 0.0f;
            _t46 = 0.0f;
        }
        return decomposeRotation_api_fma_sf6399b1a_2(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, _t19, _t44, _t45, _t46, java.lang.Math.fma(_t8, _t46, -(_t9 * _t45)), java.lang.Math.fma(_t9, _t44, -(_t7 * _t46)), java.lang.Math.fma(_t7, _t45, -(_t8 * _t44)), -java.lang.Math.fma(java.lang.Math.fma(_t19, _t7, _self22), _t44, java.lang.Math.fma(java.lang.Math.fma(_t19, _t8, _self02), _t45, java.lang.Math.fma(_t19, _t9, _self12) * _t46)));
    }

    /** Piece 3 of {@code decomposeRotation_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_fma_sf6399b1a_2(java.nio.ByteBuffer dest, int destOffset, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t19, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t68) {
        float _t78, _t79, _t80, _t84, _t85, _t86;
        if (java.lang.Math.fma(java.lang.Math.fma(_t19, _t7, java.lang.Math.fma(_t68, _t44, _self22)), _t60, java.lang.Math.fma(java.lang.Math.fma(_t19, _t8, java.lang.Math.fma(_t68, _t45, _self02)), _t61, java.lang.Math.fma(_t19, _t9, java.lang.Math.fma(_t68, _t46, _self12)) * _t62)) < 0.0f) {
            _t78 = -_t8;
            _t79 = -_t9;
            _t80 = -_t7;
            _t84 = java.lang.Math.fma(_t9, _t45, -_t8 * _t46);
            _t85 = java.lang.Math.fma(_t8, _t44, -_t7 * _t45);
            _t86 = java.lang.Math.fma(_t7, _t46, -_t9 * _t44);
        } else {
            _t78 = _t8;
            _t79 = _t9;
            _t80 = _t7;
            _t84 = _t60;
            _t85 = _t62;
            _t86 = _t61;
        }
        float _t81 = _t78 + _t46;
        float _t94 = _t81 + _t84;
        float _t98 = 1.0f + _t94;
        float _t99 = 1.0f + (_t78 - (_t84 + _t46));
        float _t100 = 1.0f + (_t46 - (_t78 + _t84));
        float _t101 = 1.0f + (_t84 - _t81);
        return decomposeRotation_api_fma_sf6399b1a_3(dest, destOffset, _t46, _t78, _t84, _t79 + _t45, _t79 - _t45, _t44 - _t85, _t85 + _t44, _t80 + _t86, _t86 - _t80, _t94, _t98, _t99, _t100, _t101, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /** Piece 4 of {@code decomposeRotation_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_fma_sf6399b1a_3(java.nio.ByteBuffer dest, int destOffset, float _t46, float _t78, float _t84, float _t82, float _t83, float _t87, float _t90, float _t92, float _t93, float _t94, float _t98, float _t99, float _t100, float _t101, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t94 > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t87);
            dest.putFloat(destOffset + 4, _sp0 * _t93);
            dest.putFloat(destOffset + 8, _sp0 * _t83);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t98));
        } else {
            if (_t78 > Math.max(_t46, _t84)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t99));
                dest.putFloat(destOffset + 4, _sp3 * _t82);
                dest.putFloat(destOffset + 8, _sp3 * _t92);
                dest.putFloat(destOffset + 12, _sp3 * _t87);
            } else {
                if (_t46 > _t84) {
                    dest.putFloat(destOffset, _sp1 * _t82);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t100));
                    dest.putFloat(destOffset + 8, _sp1 * _t90);
                    dest.putFloat(destOffset + 12, _sp1 * _t93);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t92);
                    dest.putFloat(destOffset + 4, _sp2 * _t90);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t101));
                    dest.putFloat(destOffset + 12, _sp2 * _t83);
                }
            }
        }
        return dest;
    }

    /** {@code decomposeRotation_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t20 = -((_self21) * (_t7) + (((_self01) * (_t8) + (_self11 * _t9))));
        float _t30 = -((((_t20) * (_t7) + (_self21))) * (_t7) + (((((_t20) * (_t8) + (_self01))) * (_t8) + (((_t20) * (_t9) + (_self11)) * _t9))));
        return decomposeRotation_api_mulAdd_s90d6c139_1(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, -((_self22) * (_t7) + (((_self02) * (_t8) + (_self12 * _t9)))), ((_t20) * (_t7) + (((_t30) * (_t7) + (_self21)))), ((_t20) * (_t8) + (((_t30) * (_t8) + (_self01)))), ((_t20) * (_t9) + (((_t30) * (_t9) + (_self11)))));
    }

    /** Piece 2 of {@code decomposeRotation_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_mulAdd_s90d6c139_1(java.nio.ByteBuffer dest, int destOffset, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t19, float _t34, float _t35, float _t36) {
        float _t39 = ((_t34) * (_t34) + (((_t35) * (_t35) + (_t36 * _t36))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t39));
        float _t44, _t45, _t46;
        if (_t39 != 0.0f) {
            _t44 = _t34 * _t40;
            _t45 = _t35 * _t40;
            _t46 = _t36 * _t40;
        } else {
            _t44 = 0.0f;
            _t45 = 0.0f;
            _t46 = 0.0f;
        }
        float _t60 = ((_t8) * (_t46) - (_t9 * _t45));
        float _t61 = ((_t9) * (_t44) - (_t7 * _t46));
        float _t62 = ((_t7) * (_t45) - (_t8 * _t44));
        float _t68 = -((((_t19) * (_t7) + (_self22))) * (_t44) + (((((_t19) * (_t8) + (_self02))) * (_t45) + (((_t19) * (_t9) + (_self12)) * _t46))));
        float _t78, _t79, _t80, _t84, _t85, _t86;
        if (((((_t19) * (_t7) + (((_t68) * (_t44) + (_self22))))) * (_t60) + (((((_t19) * (_t8) + (((_t68) * (_t45) + (_self02))))) * (_t61) + (((_t19) * (_t9) + (((_t68) * (_t46) + (_self12)))) * _t62)))) < 0.0f) {
            _t78 = -_t8;
            _t79 = -_t9;
            _t80 = -_t7;
            _t84 = ((_t9) * (_t45) - (_t8 * _t46));
            _t85 = ((_t8) * (_t44) - (_t7 * _t45));
            _t86 = ((_t7) * (_t46) - (_t9 * _t44));
        } else {
            _t78 = _t8;
            _t79 = _t9;
            _t80 = _t7;
            _t84 = _t60;
            _t85 = _t62;
            _t86 = _t61;
        }
        return decomposeRotation_api_mulAdd_s90d6c139_2(dest, destOffset, _t44, _t45, _t46, _t78, _t79, _t80, _t84, _t85, _t86);
    }

    /** Piece 3 of {@code decomposeRotation_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_mulAdd_s90d6c139_2(java.nio.ByteBuffer dest, int destOffset, float _t44, float _t45, float _t46, float _t78, float _t79, float _t80, float _t84, float _t85, float _t86) {
        float _t81 = _t78 + _t46;
        float _t94 = _t81 + _t84;
        float _t98 = 1.0f + _t94;
        float _t99 = 1.0f + (_t78 - (_t84 + _t46));
        float _t100 = 1.0f + (_t46 - (_t78 + _t84));
        float _t101 = 1.0f + (_t84 - _t81);
        return decomposeRotation_api_mulAdd_s90d6c139_3(dest, destOffset, _t46, _t78, _t84, _t79 + _t45, _t79 - _t45, _t44 - _t85, _t85 + _t44, _t80 + _t86, _t86 - _t80, _t94, _t98, _t99, _t100, _t101, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /** Piece 4 of {@code decomposeRotation_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_mulAdd_s90d6c139_3(java.nio.ByteBuffer dest, int destOffset, float _t46, float _t78, float _t84, float _t82, float _t83, float _t87, float _t90, float _t92, float _t93, float _t94, float _t98, float _t99, float _t100, float _t101, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t94 > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t87);
            dest.putFloat(destOffset + 4, _sp0 * _t93);
            dest.putFloat(destOffset + 8, _sp0 * _t83);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t98));
        } else {
            if (_t78 > Math.max(_t46, _t84)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t99));
                dest.putFloat(destOffset + 4, _sp3 * _t82);
                dest.putFloat(destOffset + 8, _sp3 * _t92);
                dest.putFloat(destOffset + 12, _sp3 * _t87);
            } else {
                if (_t46 > _t84) {
                    dest.putFloat(destOffset, _sp1 * _t82);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t100));
                    dest.putFloat(destOffset + 8, _sp1 * _t90);
                    dest.putFloat(destOffset + 12, _sp1 * _t93);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t92);
                    dest.putFloat(destOffset + 4, _sp2 * _t90);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t101));
                    dest.putFloat(destOffset + 12, _sp2 * _t83);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer decomposeScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeScale_api_fma(dest, destOffset, src, srcOffset);
        return decomposeScale_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code decomposeScale_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeScale_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        float _t4 = (float) java.lang.Math.sqrt(_t2);
        float _t3 = 1.0f / _t4;
        float _t8, _t9, _t10;
        if (_t2 != 0.0f) {
            _t8 = _self20 * _t3;
            _t9 = _self00 * _t3;
            _t10 = _self10 * _t3;
        } else {
            _t8 = 0.0f;
            _t9 = 0.0f;
            _t10 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_self21, _t8, java.lang.Math.fma(_self01, _t9, _self11 * _t10));
        float _t28 = -java.lang.Math.fma(java.lang.Math.fma(_t18, _t8, _self21), _t8, java.lang.Math.fma(java.lang.Math.fma(_t18, _t9, _self01), _t9, java.lang.Math.fma(_t18, _t10, _self11) * _t10));
        return decomposeScale_api_fma_scab348b2_1(dest, destOffset, _self11, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -java.lang.Math.fma(_self22, _t8, java.lang.Math.fma(_self02, _t9, _self12 * _t10)), _t18, _t28, java.lang.Math.fma(_t18, _t8, java.lang.Math.fma(_t28, _t8, _self21)), java.lang.Math.fma(_t18, _t9, java.lang.Math.fma(_t28, _t9, _self01)));
    }

    /** Piece 2 of {@code decomposeScale_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeScale_api_fma_scab348b2_1(java.nio.ByteBuffer dest, int destOffset, float _self11, float _self02, float _self12, float _self22, float _t4, float _t8, float _t9, float _t10, float _t17, float _t18, float _t28, float _t32, float _t33) {
        float _t34 = java.lang.Math.fma(_t18, _t10, java.lang.Math.fma(_t28, _t10, _self11));
        float _t37 = java.lang.Math.fma(_t32, _t32, java.lang.Math.fma(_t33, _t33, _t34 * _t34));
        float _t38 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t42, _t43, _t44;
        if (_t37 != 0.0f) {
            _t42 = _t32 * _t38;
            _t43 = _t33 * _t38;
            _t44 = _t34 * _t38;
        } else {
            _t42 = 0.0f;
            _t43 = 0.0f;
            _t44 = 0.0f;
        }
        float _t48 = -java.lang.Math.fma(java.lang.Math.fma(_t17, _t8, _self22), _t42, java.lang.Math.fma(java.lang.Math.fma(_t17, _t9, _self02), _t43, java.lang.Math.fma(_t17, _t10, _self12) * _t44));
        float _t52 = java.lang.Math.fma(_t17, _t8, java.lang.Math.fma(_t48, _t42, _self22));
        float _t53 = java.lang.Math.fma(_t17, _t9, java.lang.Math.fma(_t48, _t43, _self02));
        float _t54 = java.lang.Math.fma(_t17, _t10, java.lang.Math.fma(_t48, _t44, _self12));
        dest.putFloat(destOffset, java.lang.Math.fma(_t52, java.lang.Math.fma(_t9, _t44, -(_t10 * _t43)), java.lang.Math.fma(_t53, java.lang.Math.fma(_t10, _t42, -(_t8 * _t44)), _t54 * java.lang.Math.fma(_t8, _t43, -(_t9 * _t42)))) < 0.0f ? -_t4 : _t4);
        dest.putFloat(destOffset + 4, (float) java.lang.Math.sqrt(_t37));
        dest.putFloat(destOffset + 8, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t52, _t52, java.lang.Math.fma(_t53, _t53, _t54 * _t54))));
        return dest;
    }

    /** {@code decomposeScale_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeScale_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        float _t4 = (float) java.lang.Math.sqrt(_t2);
        float _t3 = 1.0f / _t4;
        float _t8, _t9, _t10;
        if (_t2 != 0.0f) {
            _t8 = _self20 * _t3;
            _t9 = _self00 * _t3;
            _t10 = _self10 * _t3;
        } else {
            _t8 = 0.0f;
            _t9 = 0.0f;
            _t10 = 0.0f;
        }
        float _t18 = -((_self21) * (_t8) + (((_self01) * (_t9) + (_self11 * _t10))));
        float _t28 = -((((_t18) * (_t8) + (_self21))) * (_t8) + (((((_t18) * (_t9) + (_self01))) * (_t9) + (((_t18) * (_t10) + (_self11)) * _t10))));
        return decomposeScale_api_mulAdd_sf0e64bd1_1(dest, destOffset, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -((_self22) * (_t8) + (((_self02) * (_t9) + (_self12 * _t10)))), ((_t18) * (_t8) + (((_t28) * (_t8) + (_self21)))), ((_t18) * (_t9) + (((_t28) * (_t9) + (_self01)))), ((_t18) * (_t10) + (((_t28) * (_t10) + (_self11)))));
    }

    /** Piece 2 of {@code decomposeScale_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeScale_api_mulAdd_sf0e64bd1_1(java.nio.ByteBuffer dest, int destOffset, float _self02, float _self12, float _self22, float _t4, float _t8, float _t9, float _t10, float _t17, float _t32, float _t33, float _t34) {
        float _t37 = ((_t32) * (_t32) + (((_t33) * (_t33) + (_t34 * _t34))));
        float _t38 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t42, _t43, _t44;
        if (_t37 != 0.0f) {
            _t42 = _t32 * _t38;
            _t43 = _t33 * _t38;
            _t44 = _t34 * _t38;
        } else {
            _t42 = 0.0f;
            _t43 = 0.0f;
            _t44 = 0.0f;
        }
        float _t48 = -((((_t17) * (_t8) + (_self22))) * (_t42) + (((((_t17) * (_t9) + (_self02))) * (_t43) + (((_t17) * (_t10) + (_self12)) * _t44))));
        float _t52 = ((_t17) * (_t8) + (((_t48) * (_t42) + (_self22))));
        float _t53 = ((_t17) * (_t9) + (((_t48) * (_t43) + (_self02))));
        float _t54 = ((_t17) * (_t10) + (((_t48) * (_t44) + (_self12))));
        dest.putFloat(destOffset, ((_t52) * (((_t9) * (_t44) - (_t10 * _t43))) + (((_t53) * (((_t10) * (_t42) - (_t8 * _t44))) + (_t54 * ((_t8) * (_t43) - (_t9 * _t42)))))) < 0.0f ? -_t4 : _t4);
        dest.putFloat(destOffset + 4, (float) java.lang.Math.sqrt(_t37));
        dest.putFloat(destOffset + 8, (float) java.lang.Math.sqrt(((_t52) * (_t52) + (((_t53) * (_t53) + (_t54 * _t54))))));
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSkew_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSkew_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSkew_api_fma(dest, destOffset, src, srcOffset);
        return decomposeSkew_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code decomposeSkew_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t14 = java.lang.Math.fma(_self22, _t7, java.lang.Math.fma(_self02, _t8, _self12 * _t9));
        float _t15 = java.lang.Math.fma(_self21, _t7, java.lang.Math.fma(_self01, _t8, _self11 * _t9));
        float _t17 = -_t15;
        float _t26 = java.lang.Math.fma(java.lang.Math.fma(_t17, _t7, _self21), _t7, java.lang.Math.fma(java.lang.Math.fma(_t17, _t8, _self01), _t8, java.lang.Math.fma(_t17, _t9, _self11) * _t9));
        float _t27 = -_t26;
        return decomposeSkew_api_fma_sdc543dea_1(dest, destOffset, _self01, _self11, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, _t17, _t26, _t27, java.lang.Math.fma(_t17, _t7, java.lang.Math.fma(_t27, _t7, _self21)));
    }

    /** Piece 2 of {@code decomposeSkew_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_fma_sdc543dea_1(java.nio.ByteBuffer dest, int destOffset, float _self01, float _self11, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t14, float _t15, float _t16, float _t17, float _t26, float _t27, float _t32) {
        float _t33 = java.lang.Math.fma(_t17, _t8, java.lang.Math.fma(_t27, _t8, _self01));
        float _t34 = java.lang.Math.fma(_t17, _t9, java.lang.Math.fma(_t27, _t9, _self11));
        float _t37 = java.lang.Math.fma(_t32, _t32, java.lang.Math.fma(_t33, _t33, _t34 * _t34));
        float _t38 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43, _t44, _t45;
        if (_t37 != 0.0f) {
            _t43 = _t32 * _t38;
            _t44 = _t33 * _t38;
            _t45 = _t34 * _t38;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t57 = java.lang.Math.fma(java.lang.Math.fma(_t16, _t7, _self22), _t43, java.lang.Math.fma(java.lang.Math.fma(_t16, _t8, _self02), _t44, java.lang.Math.fma(_t16, _t9, _self12) * _t45));
        float _t58 = -_t57;
        float _t62 = java.lang.Math.fma(_t16, _t7, java.lang.Math.fma(_t58, _t43, _self22));
        float _t63 = java.lang.Math.fma(_t16, _t8, java.lang.Math.fma(_t58, _t44, _self02));
        float _t64 = java.lang.Math.fma(_t16, _t9, java.lang.Math.fma(_t58, _t45, _self12));
        float _t71 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t62, _t62, java.lang.Math.fma(_t63, _t63, _t64 * _t64))));
        return decomposeSkew_api_fma_sdc543dea_2(dest, destOffset, _t7, _t8, _t9, (_t15 + _t26) * _t38, _t43, _t44, _t45, _t57, _t62, _t63, _t64, _t71, _t14 * _t71);
    }

    /** Piece 3 of {@code decomposeSkew_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_fma_sdc543dea_2(java.nio.ByteBuffer dest, int destOffset, float _t7, float _t8, float _t9, float _t39, float _t43, float _t44, float _t45, float _t57, float _t62, float _t63, float _t64, float _t71, float _t72) {
        if (java.lang.Math.fma(_t62, java.lang.Math.fma(_t8, _t45, -(_t9 * _t44)), java.lang.Math.fma(_t63, java.lang.Math.fma(_t9, _t43, -(_t7 * _t45)), _t64 * java.lang.Math.fma(_t7, _t44, -(_t8 * _t43)))) < 0.0f) {
            dest.putFloat(destOffset + 4, -_t72);
            dest.putFloat(destOffset + 8, -_t39);
        } else {
            dest.putFloat(destOffset + 4, _t72);
            dest.putFloat(destOffset + 8, _t39);
        }
        dest.putFloat(destOffset, _t57 * _t71);
        return dest;
    }

    /** {@code decomposeSkew_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t14 = ((_self22) * (_t7) + (((_self02) * (_t8) + (_self12 * _t9))));
        float _t15 = ((_self21) * (_t7) + (((_self01) * (_t8) + (_self11 * _t9))));
        float _t17 = -_t15;
        float _t26 = ((((_t17) * (_t7) + (_self21))) * (_t7) + (((((_t17) * (_t8) + (_self01))) * (_t8) + (((_t17) * (_t9) + (_self11)) * _t9))));
        float _t27 = -_t26;
        return decomposeSkew_api_mulAdd_sc0f25c09_1(dest, destOffset, _self11, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, _t17, _t26, _t27, ((_t17) * (_t7) + (((_t27) * (_t7) + (_self21)))), ((_t17) * (_t8) + (((_t27) * (_t8) + (_self01)))));
    }

    /** Piece 2 of {@code decomposeSkew_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_mulAdd_sc0f25c09_1(java.nio.ByteBuffer dest, int destOffset, float _self11, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t14, float _t15, float _t16, float _t17, float _t26, float _t27, float _t32, float _t33) {
        float _t34 = ((_t17) * (_t9) + (((_t27) * (_t9) + (_self11))));
        float _t37 = ((_t32) * (_t32) + (((_t33) * (_t33) + (_t34 * _t34))));
        float _t38 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43, _t44, _t45;
        if (_t37 != 0.0f) {
            _t43 = _t32 * _t38;
            _t44 = _t33 * _t38;
            _t45 = _t34 * _t38;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t57 = ((((_t16) * (_t7) + (_self22))) * (_t43) + (((((_t16) * (_t8) + (_self02))) * (_t44) + (((_t16) * (_t9) + (_self12)) * _t45))));
        float _t58 = -_t57;
        float _t62 = ((_t16) * (_t7) + (((_t58) * (_t43) + (_self22))));
        float _t63 = ((_t16) * (_t8) + (((_t58) * (_t44) + (_self02))));
        float _t64 = ((_t16) * (_t9) + (((_t58) * (_t45) + (_self12))));
        float _t71 = (1.0f / (float) java.lang.Math.sqrt(((_t62) * (_t62) + (((_t63) * (_t63) + (_t64 * _t64))))));
        return decomposeSkew_api_mulAdd_sc0f25c09_2(dest, destOffset, _t7, _t8, _t9, (_t15 + _t26) * _t38, _t43, _t44, _t45, _t57, _t62, _t63, _t64, _t71, _t14 * _t71);
    }

    /** Piece 3 of {@code decomposeSkew_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_mulAdd_sc0f25c09_2(java.nio.ByteBuffer dest, int destOffset, float _t7, float _t8, float _t9, float _t39, float _t43, float _t44, float _t45, float _t57, float _t62, float _t63, float _t64, float _t71, float _t72) {
        if (((_t62) * (((_t8) * (_t45) - (_t9 * _t44))) + (((_t63) * (((_t9) * (_t43) - (_t7 * _t45))) + (_t64 * ((_t7) * (_t44) - (_t8 * _t43)))))) < 0.0f) {
            dest.putFloat(destOffset + 4, -_t72);
            dest.putFloat(destOffset + 8, -_t39);
        } else {
            dest.putFloat(destOffset + 4, _t72);
            dest.putFloat(destOffset + 8, _t39);
        }
        dest.putFloat(destOffset, _t57 * _t71);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 9; _i++) {
                float _eself = src.getFloat(srcOffset + _i * 4);
                float _eother = other.getFloat(otherOffset + _i * 4);
                dest.putFloat(destOffset + _i * 4, java.lang.Math.fma(t, _eother - _eself, _eself));
            }
            return dest;
        } else {
            for (int _i = 0; _i < 9; _i++) {
                float _eself = src.getFloat(srcOffset + _i * 4);
                float _eother = other.getFloat(otherOffset + _i * 4);
                dest.putFloat(destOffset + _i * 4, ((t) * (_eother - _eself) + (_eself)));
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = right.getFloat(rightOffset + _lo * 4);
            float _eright1 = right.getFloat(rightOffset + (_lo + 1) * 4);
            float _eright2 = right.getFloat(rightOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.putFloat(destOffset + (_lo + 1) * 4, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.putFloat(destOffset + (_lo + 2) * 4, java.lang.Math.fma(_eright2, _self22, java.lang.Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = right.getFloat(rightOffset + _lo * 4);
            float _eright1 = right.getFloat(rightOffset + (_lo + 1) * 4);
            float _eright2 = right.getFloat(rightOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, ((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01)))));
            dest.putFloat(destOffset + (_lo + 1) * 4, ((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11)))));
            dest.putFloat(destOffset + (_lo + 2) * 4, ((_eright2) * (_self22) + (((_eright0) * (_self20) + (_eright1 * _self21)))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float3x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x2_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_right00, _self20, _right10 * _self21));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_right01, _self20, _right11 * _self21));
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    /** {@code mulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulMat2x2_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        dest.putFloat(destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.putFloat(destOffset + 4, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.putFloat(destOffset + 8, ((_right00) * (_self20) + (_right10 * _self21)));
        dest.putFloat(destOffset + 12, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.putFloat(destOffset + 16, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.putFloat(destOffset + 20, ((_right01) * (_self20) + (_right11 * _self21)));
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float3x3OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x3_api_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x3_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@code mulMat2x3_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulMat2x3_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        float _right02 = right.getFloat(rightOffset + 16);
        float _right12 = right.getFloat(rightOffset + 20);
        dest.putFloat(destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_right00, _self20, _right10 * _self21));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_right01, _self20, _right11 * _self21));
        return mulMat2x3_api_fma_se9bb1c32_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer mulMat2x3_api_fma_se9bb1c32_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _right02, float _right12) {
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_right02, _self00, java.lang.Math.fma(_right12, _self01, _self02)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_right02, _self10, java.lang.Math.fma(_right12, _self11, _self12)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_right02, _self20, java.lang.Math.fma(_right12, _self21, _self22)));
        return dest;
    }

    /** {@code mulMat2x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulMat2x3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        float _right02 = right.getFloat(rightOffset + 16);
        float _right12 = right.getFloat(rightOffset + 20);
        dest.putFloat(destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.putFloat(destOffset + 4, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.putFloat(destOffset + 8, ((_right00) * (_self20) + (_right10 * _self21)));
        dest.putFloat(destOffset + 12, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.putFloat(destOffset + 16, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.putFloat(destOffset + 20, ((_right01) * (_self20) + (_right11 * _self21)));
        return mulMat2x3_api_mulAdd_sa5480ad5_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer mulMat2x3_api_mulAdd_sa5480ad5_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _right02, float _right12) {
        dest.putFloat(destOffset + 24, ((_right02) * (_self00) + (((_right12) * (_self01) + (_self02)))));
        dest.putFloat(destOffset + 28, ((_right02) * (_self10) + (((_right12) * (_self11) + (_self12)))));
        dest.putFloat(destOffset + 32, ((_right02) * (_self20) + (((_right12) * (_self21) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other01 = other.getFloat(otherOffset + 12);
        float _other11 = other.getFloat(otherOffset + 16);
        float _other21 = other.getFloat(otherOffset + 20);
        float _other02 = other.getFloat(otherOffset + 24);
        float _other12 = other.getFloat(otherOffset + 28);
        float _other22 = other.getFloat(otherOffset + 32);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, java.lang.Math.fma(_other02, _eself2, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.putFloat(destOffset + (_lo + 1) * 4, java.lang.Math.fma(_other12, _eself2, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.putFloat(destOffset + (_lo + 2) * 4, java.lang.Math.fma(_other22, _eself2, java.lang.Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other01 = other.getFloat(otherOffset + 12);
        float _other11 = other.getFloat(otherOffset + 16);
        float _other21 = other.getFloat(otherOffset + 20);
        float _other02 = other.getFloat(otherOffset + 24);
        float _other12 = other.getFloat(otherOffset + 28);
        float _other22 = other.getFloat(otherOffset + 32);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, ((_other02) * (_eself2) + (((_other00) * (_eself0) + (_other01 * _eself1)))));
            dest.putFloat(destOffset + (_lo + 1) * 4, ((_other12) * (_eself2) + (((_other10) * (_eself0) + (_other11 * _eself1)))));
            dest.putFloat(destOffset + (_lo + 2) * 4, ((_other22) * (_eself2) + (((_other20) * (_eself0) + (_other21 * _eself1)))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x2_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.putFloat(destOffset + (_lo + 1) * 4, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1));
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
        }
        return dest;
    }

    /** {@code preMulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMulMat2x2_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, ((_other00) * (_eself0) + (_other01 * _eself1)));
            dest.putFloat(destOffset + (_lo + 1) * 4, ((_other10) * (_eself0) + (_other11 * _eself1)));
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x3OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x3_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, java.lang.Math.fma(_other02, _eself2, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.putFloat(destOffset + (_lo + 1) * 4, java.lang.Math.fma(_other12, _eself2, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
        }
        return dest;
    }

    /** {@code preMulMat2x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMulMat2x3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, ((_other02) * (_eself2) + (((_other00) * (_eself0) + (_other01 * _eself1)))));
            dest.putFloat(destOffset + (_lo + 1) * 4, ((_other12) * (_eself2) + (((_other10) * (_eself0) + (_other11 * _eself1)))));
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 9; _i++) {
                float _eself = src.getFloat(srcOffset + _i * 4);
                float _eother = other.getFloat(otherOffset + _i * 4);
                dest.putFloat(destOffset + _i * 4, java.lang.Math.fma(weight, _eother, _eself));
            }
            return dest;
        } else {
            for (int _i = 0; _i < 9; _i++) {
                float _eself = src.getFloat(srcOffset + _i * 4);
                float _eother = other.getFloat(otherOffset + _i * 4);
                dest.putFloat(destOffset + _i * 4, ((weight) * (_eother) + (_eself)));
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer makeOuterProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, float colX, float colY, float colZ, float rowX, float rowY, float rowZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, colZ, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_api(java.nio.ByteBuffer dest, int destOffset, float colX, float colY, float colZ, float rowX, float rowY, float rowZ) {
        dest.putFloat(destOffset, colX * rowX);
        dest.putFloat(destOffset + 4, colY * rowX);
        dest.putFloat(destOffset + 8, colZ * rowX);
        dest.putFloat(destOffset + 12, colX * rowY);
        dest.putFloat(destOffset + 16, colY * rowY);
        dest.putFloat(destOffset + 20, colZ * rowY);
        dest.putFloat(destOffset + 24, colX * rowZ);
        dest.putFloat(destOffset + 28, colY * rowZ);
        dest.putFloat(destOffset + 32, colZ * rowZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _colBase = UnsafeOpsHolder.U.getLong(col, UnsafeCopy.BB_ADDRESS_OFFSET) + colOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Float3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        float _colx = col.getFloat(colOffset);
        float _coly = col.getFloat(colOffset + 4);
        float _colz = col.getFloat(colOffset + 8);
        float _rowx = row.getFloat(rowOffset);
        float _rowy = row.getFloat(rowOffset + 4);
        float _rowz = row.getFloat(rowOffset + 8);
        dest.putFloat(destOffset, _colx * _rowx);
        dest.putFloat(destOffset + 4, _coly * _rowx);
        dest.putFloat(destOffset + 8, _colz * _rowx);
        dest.putFloat(destOffset + 12, _colx * _rowy);
        dest.putFloat(destOffset + 16, _coly * _rowy);
        dest.putFloat(destOffset + 20, _colz * _rowy);
        dest.putFloat(destOffset + 24, _colx * _rowz);
        dest.putFloat(destOffset + 28, _coly * _rowz);
        dest.putFloat(destOffset + 32, _colz * _rowz);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t11, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        return lookAlong_lh_api_fma_s49fc4fd4_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t11, -(_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_s49fc4fd4_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        float _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        return lookAlong_lh_api_fma_s49fc4fd4_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_s49fc4fd4_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        float _t18 = ((_t17) * (_t11) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t12) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        return lookAlong_lh_api_mulAdd_s81b66cf1_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s81b66cf1_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32) {
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        float _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        dest.putFloat(destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        return lookAlong_lh_api_mulAdd_s81b66cf1_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t38, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s81b66cf1_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t38, float _t48) {
        dest.putFloat(destOffset + 24, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _t0 = -src.getFloat(srcOffset + 24);
        float _t1 = -src.getFloat(srcOffset + 28);
        float _t2 = -src.getFloat(srcOffset + 32);
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, upY);
        float _t22 = java.lang.Math.fma(_t20, _t14, upX);
        float _t23 = java.lang.Math.fma(_t20, _t15, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.putFloat(destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _t0 = -src.getFloat(srcOffset + 24);
        float _t1 = -src.getFloat(srcOffset + 28);
        float _t2 = -src.getFloat(srcOffset + 32);
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        float _t21 = ((_t20) * (_t16) + (upY));
        float _t22 = ((_t20) * (_t14) + (upX));
        float _t23 = ((_t20) * (_t15) + (upZ));
        float _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        float _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.putFloat(destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.putFloat(destOffset + 4, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.putFloat(destOffset + 8, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.putFloat(destOffset + 12, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.putFloat(destOffset + 16, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.putFloat(destOffset + 20, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.putFloat(destOffset + 24, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.putFloat(destOffset + 28, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.putFloat(destOffset + 32, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        return lookAlong_lh_api_fma_sb591f666_1(dest, destOffset, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t17, _t11, _upx), java.lang.Math.fma(_t17, _t13, _upy), java.lang.Math.fma(_t17, _t12, _upz));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb591f666_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        return lookAlong_lh_api_fma_sb591f666_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)), java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb591f666_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        return lookAlong_lh_api_mulAdd_sc04bd8c5_1(dest, destOffset, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t11) + (_upx)), ((_t17) * (_t13) + (_upy)), ((_t17) * (_t12) + (_upz)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sc04bd8c5_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        float _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        dest.putFloat(destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        return lookAlong_lh_api_mulAdd_sc04bd8c5_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sc04bd8c5_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        dest.putFloat(destOffset + 16, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _t0 = -src.getFloat(srcOffset + 24);
        float _t1 = -src.getFloat(srcOffset + 28);
        float _t2 = -src.getFloat(srcOffset + 32);
        float _t7 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t14, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t15, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.putFloat(destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _t0 = -src.getFloat(srcOffset + 24);
        float _t1 = -src.getFloat(srcOffset + 28);
        float _t2 = -src.getFloat(srcOffset + 32);
        float _t7 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        float _t21 = ((_t20) * (_t16) + (_upy));
        float _t22 = ((_t20) * (_t14) + (_upx));
        float _t23 = ((_t20) * (_t15) + (_upz));
        float _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        float _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.putFloat(destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.putFloat(destOffset + 4, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.putFloat(destOffset + 8, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.putFloat(destOffset + 12, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.putFloat(destOffset + 16, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.putFloat(destOffset + 20, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.putFloat(destOffset + 24, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.putFloat(destOffset + 28, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.putFloat(destOffset + 32, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t11, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        return lookAlong_lh_api_fma_s1872ff18_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t11, -(_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_s1872ff18_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        float _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        return lookAlong_lh_api_fma_s1872ff18_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_s1872ff18_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        float _t18 = ((_t17) * (_t11) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t12) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        return lookAlong_lh_api_mulAdd_s7e0def17_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s7e0def17_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32) {
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        float _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        dest.putFloat(destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        return lookAlong_lh_api_mulAdd_s7e0def17_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t38, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s7e0def17_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t38, float _t48) {
        dest.putFloat(destOffset + 24, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _t0 = -src.getFloat(srcOffset + 24);
        float _t1 = -src.getFloat(srcOffset + 28);
        float _t2 = -src.getFloat(srcOffset + 32);
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, upY);
        float _t22 = java.lang.Math.fma(_t20, _t14, upX);
        float _t23 = java.lang.Math.fma(_t20, _t15, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.putFloat(destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _t0 = -src.getFloat(srcOffset + 24);
        float _t1 = -src.getFloat(srcOffset + 28);
        float _t2 = -src.getFloat(srcOffset + 32);
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        float _t21 = ((_t20) * (_t16) + (upY));
        float _t22 = ((_t20) * (_t14) + (upX));
        float _t23 = ((_t20) * (_t15) + (upZ));
        float _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        float _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.putFloat(destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.putFloat(destOffset + 4, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.putFloat(destOffset + 8, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.putFloat(destOffset + 12, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.putFloat(destOffset + 16, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.putFloat(destOffset + 20, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.putFloat(destOffset + 24, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.putFloat(destOffset + 28, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.putFloat(destOffset + 32, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        return lookAlong_lh_api_fma_sa6db55b2_1(dest, destOffset, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t17, java.lang.Math.fma(_t17, _t11, _upx), java.lang.Math.fma(_t17, _t13, _upy));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sa6db55b2_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t17, float _t18, float _t19) {
        float _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        return lookAlong_lh_api_fma_sa6db55b2_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)), java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sa6db55b2_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        return lookAlong_lh_api_mulAdd_safcfb0cb_1(dest, destOffset, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t11) + (_upx)), ((_t17) * (_t13) + (_upy)), ((_t17) * (_t12) + (_upz)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_safcfb0cb_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        float _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        dest.putFloat(destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        return lookAlong_lh_api_mulAdd_safcfb0cb_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_safcfb0cb_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        dest.putFloat(destOffset + 16, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _t0 = -src.getFloat(srcOffset + 24);
        float _t1 = -src.getFloat(srcOffset + 28);
        float _t2 = -src.getFloat(srcOffset + 32);
        float _t7 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t14, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t15, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.putFloat(destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _t0 = -src.getFloat(srcOffset + 24);
        float _t1 = -src.getFloat(srcOffset + 28);
        float _t2 = -src.getFloat(srcOffset + 32);
        float _t7 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        float _t21 = ((_t20) * (_t16) + (_upy));
        float _t22 = ((_t20) * (_t14) + (_upx));
        float _t23 = ((_t20) * (_t15) + (_upz));
        float _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        float _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.putFloat(destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.putFloat(destOffset + 4, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.putFloat(destOffset + 8, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.putFloat(destOffset + 12, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.putFloat(destOffset + 16, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.putFloat(destOffset + 20, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.putFloat(destOffset + 24, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.putFloat(destOffset + 28, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.putFloat(destOffset + 32, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_api(java.nio.ByteBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromDualQuat_api_mulAdd(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = java.lang.Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, _t0, _t6));
        dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(dqRX, dqRY, _t2));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(-2.0f, _t3, _sp0 * dqRZ));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(-2.0f, _t2, _sp0 * dqRY));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(-2.0f, _t4, _t6));
        dest.putFloat(destOffset + 20, 2.0f * java.lang.Math.fma(dqRX, dqRW, _t5));
        dest.putFloat(destOffset + 24, 2.0f * java.lang.Math.fma(dqRX, dqRZ, _t3));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f)));
        return dest;
    }

    /** {@code makeFromDualQuat_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeFromDualQuat_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = ((1.0f) - (2.0f) * (dqRZ * dqRZ));
        dest.putFloat(destOffset, ((_t6) - (2.0f) * (_t0)));
        dest.putFloat(destOffset + 4, 2.0f * ((dqRX) * (dqRY) + (_t2)));
        dest.putFloat(destOffset + 8, ((_sp0 * dqRZ) - (2.0f) * (_t3)));
        dest.putFloat(destOffset + 12, ((_sp0 * dqRY) - (2.0f) * (_t2)));
        dest.putFloat(destOffset + 16, ((_t6) - (2.0f) * (_t4)));
        dest.putFloat(destOffset + 20, 2.0f * ((dqRX) * (dqRW) + (_t5)));
        dest.putFloat(destOffset + 24, 2.0f * ((dqRX) * (dqRZ) + (_t3)));
        dest.putFloat(destOffset + 28, ((_t5 + _t5) - (2.0f) * (dqRX * dqRW)));
        dest.putFloat(destOffset + 32, ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, _t0);
        dest.putFloat(destOffset + 4, _t1);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, -_t1);
        dest.putFloat(destOffset + 16, _t0);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationAxis_api_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisY;
        float _t4 = axisX * axisZ;
        float _t5 = axisY * axisZ;
        dest.putFloat(destOffset, java.lang.Math.fma(_t2, axisX * axisX, _t0));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(axisZ, _t1, _t2 * _t3));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t2, _t4, -(axisY * _t1)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t2, _t3, -(axisZ * _t1)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t2, axisY * axisY, _t0));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(axisX, _t1, _t2 * _t5));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(axisY, _t1, _t2 * _t4));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t2, _t5, -(axisX * _t1)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_t2, axisZ * axisZ, _t0));
        return dest;
    }

    /** {@code makeRotationAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisY;
        float _t4 = axisX * axisZ;
        float _t5 = axisY * axisZ;
        dest.putFloat(destOffset, ((_t2) * (axisX * axisX) + (_t0)));
        dest.putFloat(destOffset + 4, ((axisZ) * (_t1) + (_t2 * _t3)));
        dest.putFloat(destOffset + 8, ((_t2) * (_t4) - (axisY * _t1)));
        dest.putFloat(destOffset + 12, ((_t2) * (_t3) - (axisZ * _t1)));
        dest.putFloat(destOffset + 16, ((_t2) * (axisY * axisY) + (_t0)));
        dest.putFloat(destOffset + 20, ((axisX) * (_t1) + (_t2 * _t5)));
        dest.putFloat(destOffset + 24, ((axisY) * (_t1) + (_t2 * _t4)));
        dest.putFloat(destOffset + 28, ((_t2) * (_t5) - (axisX * _t1)));
        dest.putFloat(destOffset + 32, ((_t2) * (axisZ * axisZ) + (_t0)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Float3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationAxis_api_mulAdd(dest, destOffset, axis, axisOffset, angle);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisy;
        float _t4 = _axisx * _axisz;
        float _t5 = _axisy * _axisz;
        dest.putFloat(destOffset, java.lang.Math.fma(_t2, _axisx * _axisx, _t0));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_axisz, _t1, _t2 * _t3));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t2, _t4, -(_axisy * _t1)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t2, _t3, -(_axisz * _t1)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t2, _axisy * _axisy, _t0));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_axisx, _t1, _t2 * _t5));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_axisy, _t1, _t2 * _t4));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t2, _t5, -(_axisx * _t1)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_t2, _axisz * _axisz, _t0));
        return dest;
    }

    /** {@code makeRotationAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisy;
        float _t4 = _axisx * _axisz;
        float _t5 = _axisy * _axisz;
        dest.putFloat(destOffset, ((_t2) * (_axisx * _axisx) + (_t0)));
        dest.putFloat(destOffset + 4, ((_axisz) * (_t1) + (_t2 * _t3)));
        dest.putFloat(destOffset + 8, ((_t2) * (_t4) - (_axisy * _t1)));
        dest.putFloat(destOffset + 12, ((_t2) * (_t3) - (_axisz * _t1)));
        dest.putFloat(destOffset + 16, ((_t2) * (_axisy * _axisy) + (_t0)));
        dest.putFloat(destOffset + 20, ((_axisx) * (_t1) + (_t2 * _t5)));
        dest.putFloat(destOffset + 24, ((_axisy) * (_t1) + (_t2 * _t4)));
        dest.putFloat(destOffset + 28, ((_t2) * (_t5) - (_axisx * _t1)));
        dest.putFloat(destOffset + 32, ((_t2) * (_axisz * _axisz) + (_t0)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        return makeRotationLookAlong_lh_api_fma_s127225f8_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s127225f8_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.putFloat(destOffset + 8, _t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.putFloat(destOffset + 20, _t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, ((_t39) * (_t13) - (_t38 * _t11)));
        dest.putFloat(destOffset + 8, _t12);
        return makeRotationLookAlong_lh_api_mulAdd_s873d6555_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s873d6555_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.putFloat(destOffset + 20, _t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t13, upY);
        float _t19 = java.lang.Math.fma(_t17, _t12, upX);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        return makeRotationLookAlong_rh_api_fma_s6fe5f686_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s6fe5f686_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.putFloat(destOffset + 8, -_t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.putFloat(destOffset + 20, -_t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.putFloat(destOffset + 32, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t13) + (upY));
        float _t19 = ((_t17) * (_t12) + (upX));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, ((_t38) * (_t11) - (_t39 * _t13)));
        dest.putFloat(destOffset + 8, -_t12);
        return makeRotationLookAlong_rh_api_mulAdd_sb1ba3fb3_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_sb1ba3fb3_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.putFloat(destOffset + 20, -_t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.putFloat(destOffset + 32, -_t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_lh_api_fma_sef1062f2_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_sef1062f2_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.putFloat(destOffset + 8, _t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.putFloat(destOffset + 20, _t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_lh_api_mulAdd_s434491b1_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s434491b1_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, ((_t39) * (_t13) - (_t38 * _t11)));
        dest.putFloat(destOffset + 8, _t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.putFloat(destOffset + 20, _t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t19 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_rh_api_fma_s4a7ddb90_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s4a7ddb90_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.putFloat(destOffset + 8, -_t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.putFloat(destOffset + 20, -_t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.putFloat(destOffset + 32, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t13) + (_upy));
        float _t19 = ((_t17) * (_t12) + (_upx));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_rh_api_mulAdd_s24d9588f_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s24d9588f_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, ((_t38) * (_t11) - (_t39 * _t13)));
        dest.putFloat(destOffset + 8, -_t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.putFloat(destOffset + 20, -_t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.putFloat(destOffset + 32, -_t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        return makeRotationLookAlong_lh_api_fma_s1d16192c_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s1d16192c_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.putFloat(destOffset + 8, _t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.putFloat(destOffset + 20, _t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, ((_t39) * (_t13) - (_t38 * _t11)));
        dest.putFloat(destOffset + 8, _t12);
        return makeRotationLookAlong_lh_api_mulAdd_se9291a5b_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_se9291a5b_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.putFloat(destOffset + 20, _t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t13, upY);
        float _t19 = java.lang.Math.fma(_t17, _t12, upX);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        return makeRotationLookAlong_rh_api_fma_s37c8ed52_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s37c8ed52_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.putFloat(destOffset + 8, -_t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.putFloat(destOffset + 20, -_t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.putFloat(destOffset + 32, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t13) + (upY));
        float _t19 = ((_t17) * (_t12) + (upX));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, ((_t38) * (_t11) - (_t39 * _t13)));
        dest.putFloat(destOffset + 8, -_t12);
        return makeRotationLookAlong_rh_api_mulAdd_s91892b99_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s91892b99_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.putFloat(destOffset + 20, -_t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.putFloat(destOffset + 32, -_t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_lh_api_fma_s66c78c6e_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s66c78c6e_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.putFloat(destOffset + 8, _t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.putFloat(destOffset + 20, _t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_lh_api_mulAdd_sd5e4a757_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_sd5e4a757_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, ((_t39) * (_t13) - (_t38 * _t11)));
        dest.putFloat(destOffset + 8, _t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.putFloat(destOffset + 20, _t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t19 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_rh_api_fma_sa41768e4_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_sa41768e4_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.putFloat(destOffset + 8, -_t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.putFloat(destOffset + 20, -_t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.putFloat(destOffset + 32, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t13) + (_upy));
        float _t19 = ((_t17) * (_t12) + (_upx));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_rh_api_mulAdd_s45260d45_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s45260d45_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, ((_t38) * (_t11) - (_t39 * _t13)));
        dest.putFloat(destOffset + 8, -_t12);
        dest.putFloat(destOffset + 12, _t38);
        dest.putFloat(destOffset + 16, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.putFloat(destOffset + 20, -_t13);
        dest.putFloat(destOffset + 24, _t39);
        dest.putFloat(destOffset + 28, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.putFloat(destOffset + 32, -_t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_api(java.nio.ByteBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationQuat_api_mulAdd(dest, destOffset, qX, qY, qZ, qW);
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(qY, qY, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(qX, qY, _t1));
        dest.putFloat(destOffset + 8, 2.0f * java.lang.Math.fma(qX, qZ, -_t2));
        dest.putFloat(destOffset + 12, 2.0f * java.lang.Math.fma(qX, qY, -_t1));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, _t0), 1.0f));
        dest.putFloat(destOffset + 20, 2.0f * java.lang.Math.fma(qX, qW, qY * qZ));
        dest.putFloat(destOffset + 24, 2.0f * java.lang.Math.fma(qX, qZ, _t2));
        dest.putFloat(destOffset + 28, 2.0f * java.lang.Math.fma(qY, qZ, -(qX * qW)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, qY * qY), 1.0f));
        return dest;
    }

    /** {@code makeRotationQuat_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationQuat_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dest.putFloat(destOffset, ((1.0f) - (2.0f) * (((qY) * (qY) + (_t0)))));
        dest.putFloat(destOffset + 4, 2.0f * ((qX) * (qY) + (_t1)));
        dest.putFloat(destOffset + 8, 2.0f * ((qX) * (qZ) - (_t2)));
        dest.putFloat(destOffset + 12, 2.0f * ((qX) * (qY) - (_t1)));
        dest.putFloat(destOffset + 16, ((1.0f) - (2.0f) * (((qX) * (qX) + (_t0)))));
        dest.putFloat(destOffset + 20, 2.0f * ((qX) * (qW) + (qY * qZ)));
        dest.putFloat(destOffset + 24, 2.0f * ((qX) * (qZ) + (_t2)));
        dest.putFloat(destOffset + 28, 2.0f * ((qY) * (qZ) - (qX * qW)));
        dest.putFloat(destOffset + 32, ((1.0f) - (2.0f) * (((qX) * (qX) + (qY * qY)))));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        Float3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationQuat_api_mulAdd(dest, destOffset, q, qOffset);
        float _qx = q.getFloat(qOffset);
        float _qy = q.getFloat(qOffset + 4);
        float _qz = q.getFloat(qOffset + 8);
        float _qw = q.getFloat(qOffset + 12);
        float _t0 = _qz * _qz;
        float _t1 = _qz * _qw;
        float _t2 = _qy * _qw;
        dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_qy, _qy, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(_qx, _qy, _t1));
        dest.putFloat(destOffset + 8, 2.0f * java.lang.Math.fma(_qx, _qz, -_t2));
        dest.putFloat(destOffset + 12, 2.0f * java.lang.Math.fma(_qx, _qy, -_t1));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_qx, _qx, _t0), 1.0f));
        dest.putFloat(destOffset + 20, 2.0f * java.lang.Math.fma(_qx, _qw, _qy * _qz));
        dest.putFloat(destOffset + 24, 2.0f * java.lang.Math.fma(_qx, _qz, _t2));
        dest.putFloat(destOffset + 28, 2.0f * java.lang.Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_qx, _qx, _qy * _qy), 1.0f));
        return dest;
    }

    /** {@code makeRotationQuat_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationQuat_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        float _qx = q.getFloat(qOffset);
        float _qy = q.getFloat(qOffset + 4);
        float _qz = q.getFloat(qOffset + 8);
        float _qw = q.getFloat(qOffset + 12);
        float _t0 = _qz * _qz;
        float _t1 = _qz * _qw;
        float _t2 = _qy * _qw;
        dest.putFloat(destOffset, ((1.0f) - (2.0f) * (((_qy) * (_qy) + (_t0)))));
        dest.putFloat(destOffset + 4, 2.0f * ((_qx) * (_qy) + (_t1)));
        dest.putFloat(destOffset + 8, 2.0f * ((_qx) * (_qz) - (_t2)));
        dest.putFloat(destOffset + 12, 2.0f * ((_qx) * (_qy) - (_t1)));
        dest.putFloat(destOffset + 16, ((1.0f) - (2.0f) * (((_qx) * (_qx) + (_t0)))));
        dest.putFloat(destOffset + 20, 2.0f * ((_qx) * (_qw) + (_qy * _qz)));
        dest.putFloat(destOffset + 24, 2.0f * ((_qx) * (_qz) + (_t2)));
        dest.putFloat(destOffset + 28, 2.0f * ((_qy) * (_qz) - (_qx * _qw)));
        dest.putFloat(destOffset + 32, ((1.0f) - (2.0f) * (((_qx) * (_qx) + (_qy * _qy)))));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, _t39);
        return makeRotationTowards_api_fma_s8e62d33_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma_s8e62d33_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 8, _t38);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putFloat(destOffset + 24, _t12);
        dest.putFloat(destOffset + 28, _t13);
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, _t39);
        dest.putFloat(destOffset + 8, _t38);
        return makeRotationTowards_api_mulAdd_s801a92b4_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd_s801a92b4_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 12, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.putFloat(destOffset + 16, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.putFloat(destOffset + 20, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.putFloat(destOffset + 24, _t12);
        dest.putFloat(destOffset + 28, _t13);
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationTowards_api_fma_s21abc90f_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma_s21abc90f_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, _t39);
        dest.putFloat(destOffset + 8, _t38);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putFloat(destOffset + 24, _t12);
        dest.putFloat(destOffset + 28, _t13);
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationTowards_api_mulAdd_s3d769cc6_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd_s3d769cc6_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, _t39);
        dest.putFloat(destOffset + 8, _t38);
        dest.putFloat(destOffset + 12, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.putFloat(destOffset + 16, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.putFloat(destOffset + 20, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.putFloat(destOffset + 24, _t12);
        dest.putFloat(destOffset + 28, _t13);
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, _t39);
        return makeRotationTowards_api_fma_sdf441819_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma_sdf441819_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 8, _t38);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putFloat(destOffset + 24, _t12);
        dest.putFloat(destOffset + 28, _t13);
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, _t39);
        dest.putFloat(destOffset + 8, _t38);
        return makeRotationTowards_api_mulAdd_s93e45178_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd_s93e45178_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest.putFloat(destOffset + 12, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.putFloat(destOffset + 16, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.putFloat(destOffset + 20, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.putFloat(destOffset + 24, _t12);
        dest.putFloat(destOffset + 28, _t13);
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationTowards_api_fma_s42f48ac5_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma_s42f48ac5_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, _t39);
        dest.putFloat(destOffset + 8, _t38);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putFloat(destOffset + 24, _t12);
        dest.putFloat(destOffset + 28, _t13);
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationTowards_api_mulAdd_s31b09712_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd_s31b09712_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, _t37);
        dest.putFloat(destOffset + 4, _t39);
        dest.putFloat(destOffset + 8, _t38);
        dest.putFloat(destOffset + 12, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.putFloat(destOffset + 16, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.putFloat(destOffset + 20, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.putFloat(destOffset + 24, _t12);
        dest.putFloat(destOffset + 28, _t13);
        dest.putFloat(destOffset + 32, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _t0);
        dest.putFloat(destOffset + 20, _t1);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, -_t1);
        dest.putFloat(destOffset + 32, _t0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_api(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXYZ_api_mulAdd(dest, destOffset, angleX, angleY, angleZ);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t3;
        float _t7 = _t3 * _t5;
        dest.putFloat(destOffset, _t0 * _t1);
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t6, _t1, _t2 * _t5));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t4, _t2, -(_t7 * _t1)));
        dest.putFloat(destOffset + 12, -_t2 * _t0);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t5, _t1, _t6 * -_t2));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t7, _t2, _t4 * _t1));
        dest.putFloat(destOffset + 24, _t3);
        dest.putFloat(destOffset + 28, -(_t4 * _t0));
        dest.putFloat(destOffset + 32, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t3;
        float _t7 = _t3 * _t5;
        dest.putFloat(destOffset, _t0 * _t1);
        dest.putFloat(destOffset + 4, ((_t6) * (_t1) + (_t2 * _t5)));
        dest.putFloat(destOffset + 8, ((_t4) * (_t2) - (_t7 * _t1)));
        dest.putFloat(destOffset + 12, -_t2 * _t0);
        dest.putFloat(destOffset + 16, ((_t5) * (_t1) - (_t6 * _t2)));
        dest.putFloat(destOffset + 20, ((_t7) * (_t2) + (_t4 * _t1)));
        dest.putFloat(destOffset + 24, _t3);
        dest.putFloat(destOffset + 28, -(_t4 * _t0));
        dest.putFloat(destOffset + 32, _t5 * _t0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_api(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXZY_api_mulAdd(dest, destOffset, angleX, angleZ, angleY);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _t6 = _t2 * _t4;
        float _t7 = _t5 * _t2;
        dest.putFloat(destOffset, _t0 * _t1);
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t6, _t0, _t5 * _t3));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t7, _t0, -(_t3 * _t4)));
        dest.putFloat(destOffset + 12, -_t2);
        dest.putFloat(destOffset + 16, _t4 * _t1);
        dest.putFloat(destOffset + 20, _t5 * _t1);
        dest.putFloat(destOffset + 24, _t3 * _t1);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t6, _t3, -(_t5 * _t0)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_t7, _t3, _t4 * _t0));
        return dest;
    }

    /** {@code makeRotationXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _t6 = _t2 * _t4;
        float _t7 = _t5 * _t2;
        dest.putFloat(destOffset, _t0 * _t1);
        dest.putFloat(destOffset + 4, ((_t6) * (_t0) + (_t5 * _t3)));
        dest.putFloat(destOffset + 8, ((_t7) * (_t0) - (_t3 * _t4)));
        dest.putFloat(destOffset + 12, -_t2);
        dest.putFloat(destOffset + 16, _t4 * _t1);
        dest.putFloat(destOffset + 20, _t5 * _t1);
        dest.putFloat(destOffset + 24, _t3 * _t1);
        dest.putFloat(destOffset + 28, ((_t6) * (_t3) - (_t5 * _t0)));
        dest.putFloat(destOffset + 32, ((_t7) * (_t3) + (_t4 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, _t0);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, -_t1);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, _t1);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, _t0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_api(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYXZ_api_mulAdd(dest, destOffset, angleY, angleX, angleZ);
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.cosWithSin(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        dest.putFloat(destOffset, java.lang.Math.fma(_t6, _t2, _t3 * _t4));
        dest.putFloat(destOffset + 4, _t2 * _t5);
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t7, _t2, -(_t1 * _t4)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t6, _t4, -(_t2 * _t3)));
        dest.putFloat(destOffset + 16, _t5 * _t4);
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t7, _t4, _t1 * _t2));
        dest.putFloat(destOffset + 24, _t1 * _t5);
        dest.putFloat(destOffset + 28, -_t0);
        dest.putFloat(destOffset + 32, _t5 * _t3);
        return dest;
    }

    /** {@code makeRotationYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.cosWithSin(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        dest.putFloat(destOffset, ((_t6) * (_t2) + (_t3 * _t4)));
        dest.putFloat(destOffset + 4, _t2 * _t5);
        dest.putFloat(destOffset + 8, ((_t7) * (_t2) - (_t1 * _t4)));
        dest.putFloat(destOffset + 12, ((_t6) * (_t4) - (_t2 * _t3)));
        dest.putFloat(destOffset + 16, _t5 * _t4);
        dest.putFloat(destOffset + 20, ((_t7) * (_t4) + (_t1 * _t2)));
        dest.putFloat(destOffset + 24, _t1 * _t5);
        dest.putFloat(destOffset + 28, -_t0);
        dest.putFloat(destOffset + 32, _t5 * _t3);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_api(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYZX_api_mulAdd(dest, destOffset, angleY, angleZ, angleX);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t0;
        float _t7 = _t3 * _t4;
        dest.putFloat(destOffset, _t0 * _t1);
        dest.putFloat(destOffset + 4, _t4);
        dest.putFloat(destOffset + 8, -(_t3 * _t1));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t2, _t3, -(_t6 * _t5)));
        dest.putFloat(destOffset + 16, _t5 * _t1);
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t7, _t5, _t2 * _t0));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_t6, _t2, _t3 * _t5));
        dest.putFloat(destOffset + 28, -_t2 * _t1);
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_t5, _t0, _t7 * -_t2));
        return dest;
    }

    /** {@code makeRotationYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t0;
        float _t7 = _t3 * _t4;
        dest.putFloat(destOffset, _t0 * _t1);
        dest.putFloat(destOffset + 4, _t4);
        dest.putFloat(destOffset + 8, -(_t3 * _t1));
        dest.putFloat(destOffset + 12, ((_t2) * (_t3) - (_t6 * _t5)));
        dest.putFloat(destOffset + 16, _t5 * _t1);
        dest.putFloat(destOffset + 20, ((_t7) * (_t5) + (_t2 * _t0)));
        dest.putFloat(destOffset + 24, ((_t6) * (_t2) + (_t3 * _t5)));
        dest.putFloat(destOffset + 28, -_t2 * _t1);
        dest.putFloat(destOffset + 32, ((_t5) * (_t0) - (_t7 * _t2)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_api(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZXY_api_mulAdd(dest, destOffset, angleZ, angleX, angleY);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleY);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t3;
        float _t7 = _t2 * _t1;
        dest.putFloat(destOffset, java.lang.Math.fma(_t0, _t1, _t6 * -_t4));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t7, _t4, _t3 * _t0));
        dest.putFloat(destOffset + 8, -_t4 * _t5);
        dest.putFloat(destOffset + 12, -(_t3 * _t5));
        dest.putFloat(destOffset + 16, _t5 * _t1);
        dest.putFloat(destOffset + 20, _t2);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_t6, _t0, _t4 * _t1));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t4, _t3, -(_t7 * _t0)));
        dest.putFloat(destOffset + 32, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleY);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t3;
        float _t7 = _t2 * _t1;
        dest.putFloat(destOffset, ((_t0) * (_t1) - (_t6 * _t4)));
        dest.putFloat(destOffset + 4, ((_t7) * (_t4) + (_t3 * _t0)));
        dest.putFloat(destOffset + 8, -_t4 * _t5);
        dest.putFloat(destOffset + 12, -(_t3 * _t5));
        dest.putFloat(destOffset + 16, _t5 * _t1);
        dest.putFloat(destOffset + 20, _t2);
        dest.putFloat(destOffset + 24, ((_t6) * (_t0) + (_t4 * _t1)));
        dest.putFloat(destOffset + 28, ((_t4) * (_t3) - (_t7 * _t0)));
        dest.putFloat(destOffset + 32, _t5 * _t0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_api(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZYX_api_mulAdd(dest, destOffset, angleZ, angleY, angleX);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.sinWithCos(angleX);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        dest.putFloat(destOffset, _t0 * _t1);
        dest.putFloat(destOffset + 4, _t4 * _t0);
        dest.putFloat(destOffset + 8, -_t2);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t6, _t3, -(_t4 * _t5)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t7, _t3, _t5 * _t1));
        dest.putFloat(destOffset + 20, _t3 * _t0);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_t6, _t5, _t3 * _t4));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_t7, _t5, -(_t3 * _t1)));
        dest.putFloat(destOffset + 32, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.sinWithCos(angleX);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        dest.putFloat(destOffset, _t0 * _t1);
        dest.putFloat(destOffset + 4, _t4 * _t0);
        dest.putFloat(destOffset + 8, -_t2);
        dest.putFloat(destOffset + 12, ((_t6) * (_t3) - (_t4 * _t5)));
        dest.putFloat(destOffset + 16, ((_t7) * (_t3) + (_t5 * _t1)));
        dest.putFloat(destOffset + 20, _t3 * _t0);
        dest.putFloat(destOffset + 24, ((_t6) * (_t5) + (_t3 * _t4)));
        dest.putFloat(destOffset + 28, ((_t7) * (_t5) - (_t3 * _t1)));
        dest.putFloat(destOffset + 32, _t5 * _t0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        dest.putFloat(destOffset, vX);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, vY);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset, _vx);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _vy);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, float s) {
        dest.putFloat(destOffset, s);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, s);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        dest.putFloat(destOffset, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, vX);
        dest.putFloat(destOffset + 28, vY);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, _vx);
        dest.putFloat(destOffset + 28, _vy);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeView_unsafe(java.nio.ByteBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer makeView_api(java.nio.ByteBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dest.putFloat(destOffset, _t0_inv + _t0_inv);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _t1_inv + _t1_inv);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, -(left + right) * _t0_inv);
        dest.putFloat(destOffset + 28, -(bottom + top) * _t1_inv);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self02, _t1, _self12 * _t0));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    /** {@code preRotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.putFloat(destOffset + 4, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.putFloat(destOffset + 16, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, ((_self02) * (_t0) - (_self12 * _t1)));
        dest.putFloat(destOffset + 28, ((_self02) * (_t1) + (_self12 * _t0)));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_fma(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@code preRotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        dest.putFloat(destOffset, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1))));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self20, _t11, java.lang.Math.fma(_self00, _t1, _self10 * _t0)));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self21, _t10, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1))));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self21, _t11, java.lang.Math.fma(_self01, _t1, _self11 * _t0)));
        dest.putFloat(destOffset + 20, _self21);
        return preRotateAround_api_fma_s10b0cfcc_1(dest, destOffset, _t0, _t1, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_fma_s10b0cfcc_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _self02, float _self12, float _self22, float _t10, float _t11) {
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self22, _t10, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1))));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self02, _t1, _self12 * _t0)));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        float _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        dest.putFloat(destOffset, ((_self20) * (_t10) + (((_self00) * (_t0) - (_self10 * _t1)))));
        dest.putFloat(destOffset + 4, ((_self20) * (_t11) + (((_self00) * (_t1) + (_self10 * _t0)))));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, ((_self21) * (_t10) + (((_self01) * (_t0) - (_self11 * _t1)))));
        dest.putFloat(destOffset + 16, ((_self21) * (_t11) + (((_self01) * (_t1) + (_self11 * _t0)))));
        dest.putFloat(destOffset + 20, _self21);
        return preRotateAround_api_mulAdd_s3058f549_1(dest, destOffset, _t0, _t1, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd_s3058f549_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _self02, float _self12, float _self22, float _t10, float _t11) {
        dest.putFloat(destOffset + 24, ((_self22) * (_t10) + (((_self02) * (_t0) - (_self12 * _t1)))));
        dest.putFloat(destOffset + 28, ((_self22) * (_t11) + (((_self02) * (_t1) + (_self12 * _t0)))));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@code preRotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1);
        float _t11 = java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1));
        dest.putFloat(destOffset, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1))));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self20, _t11, java.lang.Math.fma(_self00, _t1, _self10 * _t0)));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self21, _t10, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1))));
        return preRotateAround_api_fma_s4ee8456f_1(dest, destOffset, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_fma_s4ee8456f_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self21, _t11, java.lang.Math.fma(_self01, _t1, _self11 * _t0)));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self22, _t10, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1))));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self02, _t1, _self12 * _t0)));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((_pivotx) * (_t9) + (_pivoty * _t1));
        float _t11 = ((_pivoty) * (_t9) - (_pivotx * _t1));
        dest.putFloat(destOffset, ((_self20) * (_t10) + (((_self00) * (_t0) - (_self10 * _t1)))));
        dest.putFloat(destOffset + 4, ((_self20) * (_t11) + (((_self00) * (_t1) + (_self10 * _t0)))));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, ((_self21) * (_t10) + (((_self01) * (_t0) - (_self11 * _t1)))));
        dest.putFloat(destOffset + 16, ((_self21) * (_t11) + (((_self01) * (_t1) + (_self11 * _t0)))));
        return preRotateAround_api_mulAdd_s280364aa_1(dest, destOffset, _t0, _t1, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd_s280364aa_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, ((_self22) * (_t10) + (((_self02) * (_t0) - (_self12 * _t1)))));
        dest.putFloat(destOffset + 28, ((_self22) * (_t11) + (((_self02) * (_t1) + (_self12 * _t0)))));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_api_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@code preRotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = 1.0f - _t1;
        float _t3 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = axisY * axisZ;
        float _t18 = java.lang.Math.fma(_t2, axisX * axisX, _t1);
        float _t21 = java.lang.Math.fma(axisY, _t0, _t2 * _t3);
        float _t24 = java.lang.Math.fma(_t2, _t5, -(axisZ * _t0));
        dest.putFloat(destOffset, java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24)));
        return preRotateAxis_api_fma_sd79fddfd_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, java.lang.Math.fma(_t2, axisY * axisY, _t1), java.lang.Math.fma(_t2, axisZ * axisZ, _t1), _t21, java.lang.Math.fma(axisZ, _t0, _t2 * _t5), java.lang.Math.fma(axisX, _t0, _t2 * _t7), _t24, java.lang.Math.fma(_t2, _t7, -(axisX * _t0)), java.lang.Math.fma(_t2, _t3, -(axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_fma_sd79fddfd_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    /** {@code preRotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = 1.0f - _t1;
        float _t3 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = axisY * axisZ;
        float _t18 = ((_t2) * (axisX * axisX) + (_t1));
        float _t21 = ((axisY) * (_t0) + (_t2 * _t3));
        float _t24 = ((_t2) * (_t5) - (axisZ * _t0));
        dest.putFloat(destOffset, ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24)))));
        return preRotateAxis_api_mulAdd_s58ae11ba_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, ((_t2) * (axisY * axisY) + (_t1)), ((_t2) * (axisZ * axisZ) + (_t1)), _t21, ((axisZ) * (_t0) + (_t2 * _t5)), ((axisX) * (_t0) + (_t2 * _t7)), _t24, ((_t2) * (_t7) - (axisX * _t0)), ((_t2) * (_t3) - (axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_mulAdd_s58ae11ba_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest.putFloat(destOffset + 4, ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19)))));
        dest.putFloat(destOffset + 8, ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23)))));
        dest.putFloat(destOffset + 12, ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24)))));
        dest.putFloat(destOffset + 16, ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19)))));
        dest.putFloat(destOffset + 20, ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23)))));
        dest.putFloat(destOffset + 24, ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24)))));
        dest.putFloat(destOffset + 28, ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23)))));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Float3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@code preRotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = 1.0f - _t1;
        float _t3 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return preRotateAxis_api_fma_s7bf314f1_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_t2, _axisx * _axisx, _t1), java.lang.Math.fma(_t2, _axisy * _axisy, _t1), java.lang.Math.fma(_t2, _axisz * _axisz, _t1), java.lang.Math.fma(_axisy, _t0, _t2 * _t3), java.lang.Math.fma(_axisz, _t0, _t2 * _t5), java.lang.Math.fma(_axisx, _t0, _t2 * _t7), java.lang.Math.fma(_t2, _t5, -(_axisz * _t0)), java.lang.Math.fma(_t2, _t7, -(_axisx * _t0)), java.lang.Math.fma(_t2, _t3, -(_axisy * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_fma_s7bf314f1_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest.putFloat(destOffset, java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    /** {@code preRotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = 1.0f - _t1;
        float _t3 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return preRotateAxis_api_mulAdd_s5de2cc14_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_t2) * (_axisx * _axisx) + (_t1)), ((_t2) * (_axisy * _axisy) + (_t1)), ((_t2) * (_axisz * _axisz) + (_t1)), ((_axisy) * (_t0) + (_t2 * _t3)), ((_axisz) * (_t0) + (_t2 * _t5)), ((_axisx) * (_t0) + (_t2 * _t7)), ((_t2) * (_t5) - (_axisz * _t0)), ((_t2) * (_t7) - (_axisx * _t0)), ((_t2) * (_t3) - (_axisy * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_mulAdd_s5de2cc14_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest.putFloat(destOffset, ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24)))));
        dest.putFloat(destOffset + 4, ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19)))));
        dest.putFloat(destOffset + 8, ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23)))));
        dest.putFloat(destOffset + 12, ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24)))));
        dest.putFloat(destOffset + 16, ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19)))));
        dest.putFloat(destOffset + 20, ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23)))));
        dest.putFloat(destOffset + 24, ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24)))));
        dest.putFloat(destOffset + 28, ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23)))));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateX_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _t0, -(_self20 * _t1)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self10, _t1, _self20 * _t0));
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self11, _t0, -(_self21 * _t1)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self11, _t1, _self21 * _t0));
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t0, -(_self22 * _t1)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self12, _t1, _self22 * _t0));
        return dest;
    }

    /** {@code preRotateX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, ((_self10) * (_t0) - (_self20 * _t1)));
        dest.putFloat(destOffset + 8, ((_self10) * (_t1) + (_self20 * _t0)));
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, ((_self11) * (_t0) - (_self21 * _t1)));
        dest.putFloat(destOffset + 20, ((_self11) * (_t1) + (_self21 * _t0)));
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, ((_self12) * (_t0) - (_self22 * _t1)));
        dest.putFloat(destOffset + 32, ((_self12) * (_t1) + (_self22 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateY_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, _self20 * _t1));
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self20, _t0, -(_self00 * _t1)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self01, _t0, _self21 * _t1));
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self21, _t0, -(_self01 * _t1)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t0, _self22 * _t1));
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t0, -(_self02 * _t1)));
        return dest;
    }

    /** {@code preRotateY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, ((_self00) * (_t0) + (_self20 * _t1)));
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, ((_self20) * (_t0) - (_self00 * _t1)));
        dest.putFloat(destOffset + 12, ((_self01) * (_t0) + (_self21 * _t1)));
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, ((_self21) * (_t0) - (_self01 * _t1)));
        dest.putFloat(destOffset + 24, ((_self02) * (_t0) + (_self22 * _t1)));
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, ((_self22) * (_t0) - (_self02 * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0 * vX);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1 * vY);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0 * _vx);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1 * _vy);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, s * _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, s * _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.putFloat(destOffset, java.lang.Math.fma(s, _self00, _self20 * _t1));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(s, _self10, _self20 * _t2));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(s, _self01, _self21 * _t1));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(s, _self11, _self21 * _t2));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(s, _self02, _self22 * _t1));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(s, _self12, _self22 * _t2));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.putFloat(destOffset, ((s) * (_self00) + (_self20 * _t1)));
        dest.putFloat(destOffset + 4, ((s) * (_self10) + (_self20 * _t2)));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, ((s) * (_self01) + (_self21 * _t1)));
        dest.putFloat(destOffset + 16, ((s) * (_self11) + (_self21 * _t2)));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, ((s) * (_self02) + (_self22 * _t1)));
        dest.putFloat(destOffset + 28, ((s) * (_self12) + (_self22 * _t2)));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = 1.0f - s;
        float _t1 = pivot.getFloat(pivotOffset) * _t0;
        float _t2 = pivot.getFloat(pivotOffset + 4) * _t0;
        dest.putFloat(destOffset, java.lang.Math.fma(s, _self00, _self20 * _t1));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(s, _self10, _self20 * _t2));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(s, _self01, _self21 * _t1));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(s, _self11, _self21 * _t2));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(s, _self02, _self22 * _t1));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(s, _self12, _self22 * _t2));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = 1.0f - s;
        float _t1 = pivot.getFloat(pivotOffset) * _t0;
        float _t2 = pivot.getFloat(pivotOffset + 4) * _t0;
        dest.putFloat(destOffset, ((s) * (_self00) + (_self20 * _t1)));
        dest.putFloat(destOffset + 4, ((s) * (_self10) + (_self20 * _t2)));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, ((s) * (_self01) + (_self21 * _t1)));
        dest.putFloat(destOffset + 16, ((s) * (_self11) + (_self21 * _t2)));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, ((s) * (_self02) + (_self22 * _t1)));
        dest.putFloat(destOffset + 28, ((s) * (_self12) + (_self22 * _t2)));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.putFloat(destOffset, java.lang.Math.fma(sX, _self00, _self20 * _t2));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(sY, _self10, _self20 * _t3));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(sX, _self01, _self21 * _t2));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(sY, _self11, _self21 * _t3));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(sX, _self02, _self22 * _t2));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(sY, _self12, _self22 * _t3));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.putFloat(destOffset, ((sX) * (_self00) + (_self20 * _t2)));
        dest.putFloat(destOffset + 4, ((sY) * (_self10) + (_self20 * _t3)));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, ((sX) * (_self01) + (_self21 * _t2)));
        dest.putFloat(destOffset + 16, ((sY) * (_self11) + (_self21 * _t3)));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, ((sX) * (_self02) + (_self22 * _t2)));
        dest.putFloat(destOffset + 28, ((sY) * (_self12) + (_self22 * _t3)));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@code preScaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _sx = s.getFloat(sOffset);
        float _sy = s.getFloat(sOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = pivot.getFloat(pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.getFloat(pivotOffset + 4) * (1.0f - _sy);
        dest.putFloat(destOffset, java.lang.Math.fma(_sx, _self00, _self20 * _t2));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_sy, _self10, _self20 * _t3));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_sx, _self01, _self21 * _t2));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_sy, _self11, _self21 * _t3));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_sx, _self02, _self22 * _t2));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_sy, _self12, _self22 * _t3));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _sx = s.getFloat(sOffset);
        float _sy = s.getFloat(sOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = pivot.getFloat(pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.getFloat(pivotOffset + 4) * (1.0f - _sy);
        dest.putFloat(destOffset, ((_sx) * (_self00) + (_self20 * _t2)));
        dest.putFloat(destOffset + 4, ((_sy) * (_self10) + (_self20 * _t3)));
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, ((_sx) * (_self01) + (_self21 * _t2)));
        dest.putFloat(destOffset + 16, ((_sy) * (_self11) + (_self21 * _t3)));
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, ((_sx) * (_self02) + (_self22 * _t2)));
        dest.putFloat(destOffset + 28, ((_sy) * (_self12) + (_self22 * _t3)));
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                float _eself0 = src.getFloat(srcOffset + _lo * 4);
                float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
                float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
                dest.putFloat(destOffset + _lo * 4, java.lang.Math.fma(_eself2, vX, _eself0));
                dest.putFloat(destOffset + (_lo + 1) * 4, java.lang.Math.fma(_eself2, vY, _eself1));
                dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            }
            return dest;
        } else {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                float _eself0 = src.getFloat(srcOffset + _lo * 4);
                float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
                float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
                dest.putFloat(destOffset + _lo * 4, ((_eself2) * (vX) + (_eself0)));
                dest.putFloat(destOffset + (_lo + 1) * 4, ((_eself2) * (vY) + (_eself1)));
                dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                float _eself0 = src.getFloat(srcOffset + _lo * 4);
                float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
                float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
                dest.putFloat(destOffset + _lo * 4, java.lang.Math.fma(_eself2, _vx, _eself0));
                dest.putFloat(destOffset + (_lo + 1) * 4, java.lang.Math.fma(_eself2, _vy, _eself1));
                dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            }
            return dest;
        } else {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                float _eself0 = src.getFloat(srcOffset + _lo * 4);
                float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
                float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
                dest.putFloat(destOffset + _lo * 4, ((_eself2) * (_vx) + (_eself0)));
                dest.putFloat(destOffset + (_lo + 1) * 4, ((_eself2) * (_vy) + (_eself1)));
                dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putFloat(destOffset + 4, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putFloat(destOffset + 8, ((_self20) * (_t0) + (_self21 * _t1)));
        dest.putFloat(destOffset + 12, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putFloat(destOffset + 16, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.putFloat(destOffset + 20, ((_self21) * (_t0) - (_self20 * _t1)));
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_fma(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@code rotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        return rotateAround_api_fma_s48c9a07f_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(pivotX, _t9, pivotY * _t1), java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_fma_s48c9a07f_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self21, _t11, _self22)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.putFloat(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putFloat(destOffset + 4, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putFloat(destOffset + 8, ((_self20) * (_t0) + (_self21 * _t1)));
        dest.putFloat(destOffset + 12, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putFloat(destOffset + 16, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.putFloat(destOffset + 20, ((_self21) * (_t0) - (_self20 * _t1)));
        return rotateAround_api_mulAdd_sd4b5b20c_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((pivotX) * (_t9) + (pivotY * _t1)), ((pivotY) * (_t9) - (pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd_sd4b5b20c_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        dest.putFloat(destOffset + 24, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.putFloat(destOffset + 28, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        dest.putFloat(destOffset + 32, ((_self20) * (_t10) + (((_self21) * (_t11) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@code rotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        return rotateAround_api_fma_s5078f268_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1), java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_fma_s5078f268_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self21, _t11, _self22)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.putFloat(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putFloat(destOffset + 4, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putFloat(destOffset + 8, ((_self20) * (_t0) + (_self21 * _t1)));
        dest.putFloat(destOffset + 12, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putFloat(destOffset + 16, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.putFloat(destOffset + 20, ((_self21) * (_t0) - (_self20 * _t1)));
        return rotateAround_api_mulAdd_s9f05a1af_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_pivotx) * (_t9) + (_pivoty * _t1)), ((_pivoty) * (_t9) - (_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd_s9f05a1af_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        dest.putFloat(destOffset + 24, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.putFloat(destOffset + 28, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        dest.putFloat(destOffset + 32, ((_self20) * (_t10) + (((_self21) * (_t11) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@code rotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisZ;
        float _t6 = axisX * axisY;
        float _t7 = axisY * axisZ;
        float _t18 = java.lang.Math.fma(_t2, axisX * axisX, _t0);
        float _t21 = java.lang.Math.fma(axisZ, _t1, _t2 * _t6);
        float _t24 = java.lang.Math.fma(_t2, _t3, -(axisY * _t1));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21)));
        return rotateAxis_api_fma_s7d702344_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, java.lang.Math.fma(_t2, axisY * axisY, _t0), java.lang.Math.fma(_t2, axisZ * axisZ, _t0), _t21, java.lang.Math.fma(axisX, _t1, _t2 * _t7), java.lang.Math.fma(axisY, _t1, _t2 * _t3), _t24, java.lang.Math.fma(_t2, _t6, -(axisZ * _t1)), java.lang.Math.fma(_t2, _t7, -(axisX * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_fma_s7d702344_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisZ;
        float _t6 = axisX * axisY;
        float _t7 = axisY * axisZ;
        float _t18 = ((_t2) * (axisX * axisX) + (_t0));
        float _t21 = ((axisZ) * (_t1) + (_t2 * _t6));
        float _t24 = ((_t2) * (_t3) - (axisY * _t1));
        dest.putFloat(destOffset, ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21)))));
        return rotateAxis_api_mulAdd_s27738f25_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, ((_t2) * (axisY * axisY) + (_t0)), ((_t2) * (axisZ * axisZ) + (_t0)), _t21, ((axisX) * (_t1) + (_t2 * _t7)), ((axisY) * (_t1) + (_t2 * _t3)), _t24, ((_t2) * (_t6) - (axisZ * _t1)), ((_t2) * (_t7) - (axisX * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd_s27738f25_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest.putFloat(destOffset + 4, ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Float3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@code rotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisz;
        float _t6 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return rotateAxis_api_fma_s8086eb32_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_t2, _axisx * _axisx, _t0), java.lang.Math.fma(_t2, _axisy * _axisy, _t0), java.lang.Math.fma(_t2, _axisz * _axisz, _t0), java.lang.Math.fma(_axisz, _t1, _t2 * _t6), java.lang.Math.fma(_axisx, _t1, _t2 * _t7), java.lang.Math.fma(_axisy, _t1, _t2 * _t3), java.lang.Math.fma(_t2, _t3, -(_axisy * _t1)), java.lang.Math.fma(_t2, _t6, -(_axisz * _t1)), java.lang.Math.fma(_t2, _t7, -(_axisx * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_fma_s8086eb32_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisz;
        float _t6 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return rotateAxis_api_mulAdd_s4a5eda49_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_t2) * (_axisx * _axisx) + (_t0)), ((_t2) * (_axisy * _axisy) + (_t0)), ((_t2) * (_axisz * _axisz) + (_t0)), ((_axisz) * (_t1) + (_t2 * _t6)), ((_axisx) * (_t1) + (_t2 * _t7)), ((_axisy) * (_t1) + (_t2 * _t3)), ((_t2) * (_t3) - (_axisy * _t1)), ((_t2) * (_t6) - (_axisz * _t1)), ((_t2) * (_t7) - (_axisx * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd_s4a5eda49_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest.putFloat(destOffset, ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        return rotateTowards_api_fma_s2776b3c6_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s2776b3c6_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = java.lang.Math.fma(_t39, _t12, -(_t38 * _t13));
        float _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        float _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        return rotateTowards_api_fma_s2776b3c6_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s2776b3c6_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        return rotateTowards_api_mulAdd_sb36aaa4b_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sb36aaa4b_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32) {
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        float _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        float _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        dest.putFloat(destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        return rotateTowards_api_mulAdd_sb36aaa4b_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sb36aaa4b_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13) {
        dest.putFloat(destOffset + 24, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        return rotateTowards_api_fma_s224fa7d0_1(dest, destOffset, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t17, _t12, _upx), java.lang.Math.fma(_t17, _t13, _upy), java.lang.Math.fma(_t17, _t11, _upz));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s224fa7d0_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        return rotateTowards_api_fma_s224fa7d0_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)), java.lang.Math.fma(_t37, _t13, -(_t39 * _t11)), java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s224fa7d0_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        return rotateTowards_api_mulAdd_sce791f87_1(dest, destOffset, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t12) + (_upx)), ((_t17) * (_t13) + (_upy)), ((_t17) * (_t11) + (_upz)));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sce791f87_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        float _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        float _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        dest.putFloat(destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        return rotateTowards_api_mulAdd_sce791f87_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sce791f87_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        dest.putFloat(destOffset + 16, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        return rotateTowards_api_fma_s7dafc412_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s7dafc412_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = java.lang.Math.fma(_t39, _t12, -(_t38 * _t13));
        float _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        float _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        return rotateTowards_api_fma_s7dafc412_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s7dafc412_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        return rotateTowards_api_mulAdd_se94c67b1_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_se94c67b1_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32) {
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        float _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        float _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        dest.putFloat(destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        return rotateTowards_api_mulAdd_se94c67b1_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_se94c67b1_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13) {
        dest.putFloat(destOffset + 24, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        return rotateTowards_api_fma_s98e8cea4_1(dest, destOffset, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t17, java.lang.Math.fma(_t17, _t12, _upx), java.lang.Math.fma(_t17, _t13, _upy));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s98e8cea4_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t17, float _t18, float _t19) {
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        return rotateTowards_api_fma_s98e8cea4_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)), java.lang.Math.fma(_t37, _t13, -(_t39 * _t11)), java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s98e8cea4_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        return rotateTowards_api_mulAdd_sbec4039d_1(dest, destOffset, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t12) + (_upx)), ((_t17) * (_t13) + (_upy)), ((_t17) * (_t11) + (_upz)));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sbec4039d_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        float _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        float _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        dest.putFloat(destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        return rotateTowards_api_mulAdd_sbec4039d_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sbec4039d_2(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        dest.putFloat(destOffset + 16, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateX_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self01, _t0, _self02 * _t1));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self11, _t0, _self12 * _t1));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self21, _t0, _self22 * _t1));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t0, -(_self01 * _t1)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t0, -(_self11 * _t1)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t0, -(_self21 * _t1)));
        return dest;
    }

    /** {@code rotateX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, ((_self01) * (_t0) + (_self02 * _t1)));
        dest.putFloat(destOffset + 16, ((_self11) * (_t0) + (_self12 * _t1)));
        dest.putFloat(destOffset + 20, ((_self21) * (_t0) + (_self22 * _t1)));
        dest.putFloat(destOffset + 24, ((_self02) * (_t0) - (_self01 * _t1)));
        dest.putFloat(destOffset + 28, ((_self12) * (_t0) - (_self11 * _t1)));
        dest.putFloat(destOffset + 32, ((_self22) * (_t0) - (_self21 * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX180_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateX180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX180_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, -_self01);
        dest.putFloat(destOffset + 16, -_self11);
        dest.putFloat(destOffset + 20, -_self21);
        dest.putFloat(destOffset + 24, -_self02);
        dest.putFloat(destOffset + 28, -_self12);
        dest.putFloat(destOffset + 32, -_self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX270_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateX270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX270_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, -_self02);
        dest.putFloat(destOffset + 16, -_self12);
        dest.putFloat(destOffset + 20, -_self22);
        dest.putFloat(destOffset + 24, _self01);
        dest.putFloat(destOffset + 28, _self11);
        dest.putFloat(destOffset + 32, _self21);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX90_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateX90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX90_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, _self02);
        dest.putFloat(destOffset + 16, _self12);
        dest.putFloat(destOffset + 20, _self22);
        dest.putFloat(destOffset + 24, -_self01);
        dest.putFloat(destOffset + 28, -_self11);
        dest.putFloat(destOffset + 32, -_self21);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_api_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.cosWithSin(angleX);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t2 * _t3;
        float _t7 = _t5 * _t4;
        float _t8 = _t0 * _t2;
        float _t18 = java.lang.Math.fma(_t8, _t4, _t1 * _t3);
        float _t20 = java.lang.Math.fma(_t0, _t1, -(_t6 * _t4));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t7, _self01 * _t18)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t7, _self11 * _t18)));
        return rotateXYZ_api_fma_s4cd24c0b_1(dest, destOffset, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t1 * _t5, _t3 * _t5, _t0 * _t5, _t18, java.lang.Math.fma(_t6, _t1, _t0 * _t4), _t20, java.lang.Math.fma(_t3, _t4, -(_t8 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_fma_s4cd24c0b_1(java.nio.ByteBuffer dest, int destOffset, float _t2, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t7, _self21 * _t18)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self01, _t21, -(_self00 * _t11))));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self11, _t21, -(_self10 * _t11))));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self21, _t21, -(_self20 * _t11))));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t2, -(_self01 * _t13))));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t2, -(_self11 * _t13))));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t2, -(_self21 * _t13))));
        return dest;
    }

    /** {@code rotateXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.cosWithSin(angleX);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t2 * _t3;
        float _t7 = _t5 * _t4;
        float _t8 = _t0 * _t2;
        float _t18 = ((_t8) * (_t4) + (_t1 * _t3));
        float _t20 = ((_t0) * (_t1) - (_t6 * _t4));
        dest.putFloat(destOffset, ((_self02) * (_t20) + (((_self00) * (_t7) + (_self01 * _t18)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t20) + (((_self10) * (_t7) + (_self11 * _t18)))));
        return rotateXYZ_api_mulAdd_sb55b554a_1(dest, destOffset, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t1 * _t5, _t3 * _t5, _t0 * _t5, _t18, ((_t6) * (_t1) + (_t0 * _t4)), _t20, ((_t3) * (_t4) - (_t8 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_mulAdd_sb55b554a_1(java.nio.ByteBuffer dest, int destOffset, float _t2, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 8, ((_self22) * (_t20) + (((_self20) * (_t7) + (_self21 * _t18)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t19) + (((_self01) * (_t21) - (_self00 * _t11)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t19) + (((_self11) * (_t21) - (_self10 * _t11)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t19) + (((_self21) * (_t21) - (_self20 * _t11)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t12) + (((_self00) * (_t2) - (_self01 * _t13)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t12) + (((_self10) * (_t2) - (_self11 * _t13)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t12) + (((_self20) * (_t2) - (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_api_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.cosWithSin(angleZ);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t0 * _t1;
        float _t8 = _t2 * _t5;
        float _t9 = _t1 * _t4;
        float _t18 = java.lang.Math.fma(_t9, _t2, _t0 * _t3);
        float _t20 = java.lang.Math.fma(_t6, _t2, -(_t3 * _t4));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t8, _self01 * _t18)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t8, _self11 * _t18)));
        return rotateXZY_api_fma_s420c885d_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t8, _t0 * _t5, _t4 * _t5, _t3 * _t5, _t18, java.lang.Math.fma(_t6, _t3, _t4 * _t2), _t20, java.lang.Math.fma(_t9, _t3, -(_t0 * _t2)));
    }

    /** Piece 2 of {@code rotateXZY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_fma_s420c885d_1(java.nio.ByteBuffer dest, int destOffset, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t8, float _t11, float _t12, float _t14, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t8, _self21 * _t18)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self01, _t12, -(_self00 * _t1))));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self11, _t12, -(_self10 * _t1))));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self21, _t12, -(_self20 * _t1))));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t14, _self01 * _t21)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t14, _self11 * _t21)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t14, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.cosWithSin(angleZ);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t0 * _t1;
        float _t8 = _t2 * _t5;
        float _t9 = _t1 * _t4;
        float _t18 = ((_t9) * (_t2) + (_t0 * _t3));
        float _t20 = ((_t6) * (_t2) - (_t3 * _t4));
        dest.putFloat(destOffset, ((_self02) * (_t20) + (((_self00) * (_t8) + (_self01 * _t18)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t20) + (((_self10) * (_t8) + (_self11 * _t18)))));
        return rotateXZY_api_mulAdd_s3fa0fac4_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t8, _t0 * _t5, _t4 * _t5, _t3 * _t5, _t18, ((_t6) * (_t3) + (_t4 * _t2)), _t20, ((_t9) * (_t3) - (_t0 * _t2)));
    }

    /** Piece 2 of {@code rotateXZY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_mulAdd_s3fa0fac4_1(java.nio.ByteBuffer dest, int destOffset, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t8, float _t11, float _t12, float _t14, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 8, ((_self22) * (_t20) + (((_self20) * (_t8) + (_self21 * _t18)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t11) + (((_self01) * (_t12) - (_self00 * _t1)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t11) + (((_self11) * (_t12) - (_self10 * _t1)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t11) + (((_self21) * (_t12) - (_self20 * _t1)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t19) + (((_self00) * (_t14) + (_self01 * _t21)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t19) + (((_self10) * (_t14) + (_self11 * _t21)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t19) + (((_self20) * (_t14) + (_self21 * _t21)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateY_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, -(_self02 * _t1)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _t0, -(_self12 * _t1)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self20, _t0, -(_self22 * _t1)));
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self00, _t1, _self02 * _t0));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self10, _t1, _self12 * _t0));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self20, _t1, _self22 * _t0));
        return dest;
    }

    /** {@code rotateY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, ((_self00) * (_t0) - (_self02 * _t1)));
        dest.putFloat(destOffset + 4, ((_self10) * (_t0) - (_self12 * _t1)));
        dest.putFloat(destOffset + 8, ((_self20) * (_t0) - (_self22 * _t1)));
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, ((_self00) * (_t1) + (_self02 * _t0)));
        dest.putFloat(destOffset + 28, ((_self10) * (_t1) + (_self12 * _t0)));
        dest.putFloat(destOffset + 32, ((_self20) * (_t1) + (_self22 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY180_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateY180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY180_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, -_self00);
        dest.putFloat(destOffset + 4, -_self10);
        dest.putFloat(destOffset + 8, -_self20);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, -_self02);
        dest.putFloat(destOffset + 28, -_self12);
        dest.putFloat(destOffset + 32, -_self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY270_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateY270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY270_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self02);
        dest.putFloat(destOffset + 4, _self12);
        dest.putFloat(destOffset + 8, _self22);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, -_self00);
        dest.putFloat(destOffset + 28, -_self10);
        dest.putFloat(destOffset + 32, -_self20);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY90_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateY90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY90_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, -_self02);
        dest.putFloat(destOffset + 4, -_self12);
        dest.putFloat(destOffset + 8, -_self22);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, _self00);
        dest.putFloat(destOffset + 28, _self10);
        dest.putFloat(destOffset + 32, _self20);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_api_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t18 = java.lang.Math.fma(_t8, _t2, _t1 * _t4);
        float _t20 = java.lang.Math.fma(_t6, _t2, -(_t3 * _t4));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t18, _self01 * _t10)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t18, _self11 * _t10)));
        return rotateYXZ_api_fma_sd4cb8c19_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t5 * _t4, _t5 * _t1, _t3 * _t5, _t18, java.lang.Math.fma(_t6, _t4, _t3 * _t2), _t20, java.lang.Math.fma(_t8, _t4, -(_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_fma_sd4cb8c19_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t13, float _t14, float _t15, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t18, _self21 * _t10)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t21, _self01 * _t13)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t21, _self11 * _t13)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t21, _self21 * _t13)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self00, _t15, -(_self01 * _t0))));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t14, java.lang.Math.fma(_self10, _t15, -(_self11 * _t0))));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self20, _t15, -(_self21 * _t0))));
        return dest;
    }

    /** {@code rotateYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t18 = ((_t8) * (_t2) + (_t1 * _t4));
        float _t20 = ((_t6) * (_t2) - (_t3 * _t4));
        dest.putFloat(destOffset, ((_self02) * (_t20) + (((_self00) * (_t18) + (_self01 * _t10)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_t20) + (((_self10) * (_t18) + (_self11 * _t10)))));
        return rotateYXZ_api_mulAdd_s341c9108_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t5 * _t4, _t5 * _t1, _t3 * _t5, _t18, ((_t6) * (_t4) + (_t3 * _t2)), _t20, ((_t8) * (_t4) - (_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_mulAdd_s341c9108_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t13, float _t14, float _t15, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 8, ((_self22) * (_t20) + (((_self20) * (_t18) + (_self21 * _t10)))));
        dest.putFloat(destOffset + 12, ((_self02) * (_t19) + (((_self00) * (_t21) + (_self01 * _t13)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t19) + (((_self10) * (_t21) + (_self11 * _t13)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t19) + (((_self20) * (_t21) + (_self21 * _t13)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t14) + (((_self00) * (_t15) - (_self01 * _t0)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t14) + (((_self10) * (_t15) - (_self11 * _t0)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t14) + (((_self20) * (_t15) - (_self21 * _t0)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_api_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t0 * _t1;
        float _t7 = _t2 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t3 * _t2;
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, -_t6, java.lang.Math.fma(_self00, _t7, _self01 * _t3)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, -_t6, java.lang.Math.fma(_self10, _t7, _self11 * _t3)));
        return rotateYZX_api_fma_sc514d1e1_1(dest, destOffset, _t3, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, java.lang.Math.fma(_t8, _t4, _t5 * _t2), java.lang.Math.fma(_t10, _t5, _t0 * _t4), java.lang.Math.fma(_t5, _t0, -(_t10 * _t4)), java.lang.Math.fma(_t4, _t2, -(_t8 * _t5)));
    }

    /** Piece 2 of {@code rotateYZX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_fma_sc514d1e1_1(java.nio.ByteBuffer dest, int destOffset, float _t3, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t7, float _t11, float _t13, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, -_t6, java.lang.Math.fma(_self20, _t7, _self21 * _t3)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t18, java.lang.Math.fma(_self00, _t20, _self01 * _t11)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t18, java.lang.Math.fma(_self10, _t20, _self11 * _t11)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t18, java.lang.Math.fma(_self20, _t20, _self21 * _t11)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t21, java.lang.Math.fma(_self00, _t19, -(_self01 * _t13))));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t21, java.lang.Math.fma(_self10, _t19, -(_self11 * _t13))));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self20, _t19, -(_self21 * _t13))));
        return dest;
    }

    /** {@code rotateYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t0 * _t1;
        float _t7 = _t2 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t3 * _t2;
        dest.putFloat(destOffset, ((((_self00) * (_t7) + (_self01 * _t3))) - (_self02) * (_t6)));
        dest.putFloat(destOffset + 4, ((((_self10) * (_t7) + (_self11 * _t3))) - (_self12) * (_t6)));
        dest.putFloat(destOffset + 8, ((((_self20) * (_t7) + (_self21 * _t3))) - (_self22) * (_t6)));
        return rotateYZX_api_mulAdd_s206e6498_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t4 * _t1, _t5 * _t1, ((_t8) * (_t4) + (_t5 * _t2)), ((_t10) * (_t5) + (_t0 * _t4)), ((_t5) * (_t0) - (_t10 * _t4)), ((_t4) * (_t2) - (_t8 * _t5)));
    }

    /** Piece 2 of {@code rotateYZX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_mulAdd_s206e6498_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t13, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 12, ((_self02) * (_t18) + (((_self00) * (_t20) + (_self01 * _t11)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t18) + (((_self10) * (_t20) + (_self11 * _t11)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t18) + (((_self20) * (_t20) + (_self21 * _t11)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t21) + (((_self00) * (_t19) - (_self01 * _t13)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t21) + (((_self10) * (_t19) - (_self11 * _t13)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t21) + (((_self20) * (_t19) - (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ180_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateZ180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ180_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, -_self00);
        dest.putFloat(destOffset + 4, -_self10);
        dest.putFloat(destOffset + 8, -_self20);
        dest.putFloat(destOffset + 12, -_self01);
        dest.putFloat(destOffset + 16, -_self11);
        dest.putFloat(destOffset + 20, -_self21);
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ270_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateZ270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ270_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, -_self01);
        dest.putFloat(destOffset + 4, -_self11);
        dest.putFloat(destOffset + 8, -_self21);
        dest.putFloat(destOffset + 12, _self00);
        dest.putFloat(destOffset + 16, _self10);
        dest.putFloat(destOffset + 20, _self20);
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ90_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateZ90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ90_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self01);
        dest.putFloat(destOffset + 4, _self11);
        dest.putFloat(destOffset + 8, _self21);
        dest.putFloat(destOffset + 12, -_self00);
        dest.putFloat(destOffset + 16, -_self10);
        dest.putFloat(destOffset + 20, -_self20);
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_api_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.cosWithSin(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.sinWithCos(angleZ);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t0 * _t1;
        float _t7 = _t4 * _t5;
        float _t8 = _t4 * _t3;
        float _t18 = java.lang.Math.fma(_t8, _t0, _t5 * _t2);
        float _t20 = java.lang.Math.fma(_t2, _t3, -(_t7 * _t0));
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, -_t6, java.lang.Math.fma(_self00, _t20, _self01 * _t18)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, -_t6, java.lang.Math.fma(_self10, _t20, _self11 * _t18)));
        return rotateZXY_api_fma_sca9185f1_1(dest, destOffset, _t4, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t1 * _t3, _t5 * _t1, _t1 * _t2, _t18, java.lang.Math.fma(_t7, _t2, _t0 * _t3), _t20, java.lang.Math.fma(_t0, _t5, -(_t8 * _t2)));
    }

    /** Piece 2 of {@code rotateZXY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_fma_sca9185f1_1(java.nio.ByteBuffer dest, int destOffset, float _t4, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t10, float _t11, float _t12, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, -_t6, java.lang.Math.fma(_self20, _t20, _self21 * _t18)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t4, java.lang.Math.fma(_self01, _t10, -(_self00 * _t11))));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t4, java.lang.Math.fma(_self11, _t10, -(_self10 * _t11))));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t4, java.lang.Math.fma(_self21, _t10, -(_self20 * _t11))));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t19, _self01 * _t21)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t19, _self11 * _t21)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.cosWithSin(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.sinWithCos(angleZ);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t0 * _t1;
        float _t7 = _t4 * _t5;
        float _t8 = _t4 * _t3;
        float _t18 = ((_t8) * (_t0) + (_t5 * _t2));
        float _t20 = ((_t2) * (_t3) - (_t7 * _t0));
        dest.putFloat(destOffset, ((((_self00) * (_t20) + (_self01 * _t18))) - (_self02) * (_t6)));
        dest.putFloat(destOffset + 4, ((((_self10) * (_t20) + (_self11 * _t18))) - (_self12) * (_t6)));
        return rotateZXY_api_mulAdd_sa3856b00_1(dest, destOffset, _t4, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t1 * _t3, _t5 * _t1, _t1 * _t2, _t18, ((_t7) * (_t2) + (_t0 * _t3)), _t20, ((_t0) * (_t5) - (_t8 * _t2)));
    }

    /** Piece 2 of {@code rotateZXY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_mulAdd_sa3856b00_1(java.nio.ByteBuffer dest, int destOffset, float _t4, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t10, float _t11, float _t12, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 8, ((((_self20) * (_t20) + (_self21 * _t18))) - (_self22) * (_t6)));
        dest.putFloat(destOffset + 12, ((_self02) * (_t4) + (((_self01) * (_t10) - (_self00 * _t11)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t4) + (((_self11) * (_t10) - (_self10 * _t11)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t4) + (((_self21) * (_t10) - (_self20 * _t11)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t12) + (((_self00) * (_t19) + (_self01 * _t21)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t12) + (((_self10) * (_t19) + (_self11 * _t21)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t12) + (((_self20) * (_t19) + (_self21 * _t21)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_api_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.cosWithSin(angleZ);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t1 * _t2;
        float _t7 = _t3 * _t1;
        float _t9 = _t0 * _t2;
        float _t11 = _t0 * _t3;
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, -_t0, java.lang.Math.fma(_self00, _t6, _self01 * _t7)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, -_t0, java.lang.Math.fma(_self10, _t6, _self11 * _t7)));
        return rotateZYX_api_fma_s7d19b967_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, java.lang.Math.fma(_t11, _t4, _t5 * _t2), java.lang.Math.fma(_t9, _t5, _t4 * _t3), java.lang.Math.fma(_t9, _t4, -(_t3 * _t5)), java.lang.Math.fma(_t11, _t5, -(_t4 * _t2)));
    }

    /** Piece 2 of {@code rotateZYX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_fma_s7d19b967_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t7, float _t8, float _t13, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, -_t0, java.lang.Math.fma(_self20, _t6, _self21 * _t7)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self02, _t8, java.lang.Math.fma(_self00, _t20, _self01 * _t18)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self12, _t8, java.lang.Math.fma(_self10, _t20, _self11 * _t18)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self22, _t8, java.lang.Math.fma(_self20, _t20, _self21 * _t18)));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t19, _self01 * _t21)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t19, _self11 * _t21)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.cosWithSin(angleZ);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t6 = _t1 * _t2;
        float _t7 = _t3 * _t1;
        float _t9 = _t0 * _t2;
        float _t11 = _t0 * _t3;
        dest.putFloat(destOffset, ((((_self00) * (_t6) + (_self01 * _t7))) - (_self02) * (_t0)));
        dest.putFloat(destOffset + 4, ((((_self10) * (_t6) + (_self11 * _t7))) - (_self12) * (_t0)));
        dest.putFloat(destOffset + 8, ((((_self20) * (_t6) + (_self21 * _t7))) - (_self22) * (_t0)));
        return rotateZYX_api_mulAdd_s6987b2f6_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t4 * _t1, _t5 * _t1, ((_t11) * (_t4) + (_t5 * _t2)), ((_t9) * (_t5) + (_t4 * _t3)), ((_t9) * (_t4) - (_t3 * _t5)), ((_t11) * (_t5) - (_t4 * _t2)));
    }

    /** Piece 2 of {@code rotateZYX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_mulAdd_s6987b2f6_1(java.nio.ByteBuffer dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t8, float _t13, float _t18, float _t19, float _t20, float _t21) {
        dest.putFloat(destOffset + 12, ((_self02) * (_t8) + (((_self00) * (_t20) + (_self01 * _t18)))));
        dest.putFloat(destOffset + 16, ((_self12) * (_t8) + (((_self10) * (_t20) + (_self11 * _t18)))));
        dest.putFloat(destOffset + 20, ((_self22) * (_t8) + (((_self20) * (_t20) + (_self21 * _t18)))));
        dest.putFloat(destOffset + 24, ((_self02) * (_t13) + (((_self00) * (_t19) + (_self01 * _t21)))));
        dest.putFloat(destOffset + 28, ((_self12) * (_t13) + (((_self10) * (_t19) + (_self11 * _t21)))));
        dest.putFloat(destOffset + 32, ((_self22) * (_t13) + (((_self20) * (_t19) + (_self21 * _t21)))));
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00 * vX);
        dest.putFloat(destOffset + 4, _self10 * vX);
        dest.putFloat(destOffset + 8, _self20 * vX);
        dest.putFloat(destOffset + 12, _self01 * vY);
        dest.putFloat(destOffset + 16, _self11 * vY);
        dest.putFloat(destOffset + 20, _self21 * vY);
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00 * _vx);
        dest.putFloat(destOffset + 4, _self10 * _vx);
        dest.putFloat(destOffset + 8, _self20 * _vx);
        dest.putFloat(destOffset + 12, _self01 * _vy);
        dest.putFloat(destOffset + 16, _self11 * _vy);
        dest.putFloat(destOffset + 20, _self21 * _vy);
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self20);
        dest.putFloat(destOffset + 12, s * _self01);
        dest.putFloat(destOffset + 16, s * _self11);
        dest.putFloat(destOffset + 20, s * _self21);
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self20);
        dest.putFloat(destOffset + 12, s * _self01);
        dest.putFloat(destOffset + 16, s * _self11);
        dest.putFloat(destOffset + 20, s * _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self20);
        dest.putFloat(destOffset + 12, s * _self01);
        dest.putFloat(destOffset + 16, s * _self11);
        dest.putFloat(destOffset + 20, s * _self21);
        dest.putFloat(destOffset + 24, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.putFloat(destOffset + 28, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        dest.putFloat(destOffset + 32, ((_self20) * (_t1) + (((_self21) * (_t2) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = 1.0f - s;
        float _t1 = pivot.getFloat(pivotOffset) * _t0;
        float _t2 = pivot.getFloat(pivotOffset + 4) * _t0;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self20);
        dest.putFloat(destOffset + 12, s * _self01);
        dest.putFloat(destOffset + 16, s * _self11);
        dest.putFloat(destOffset + 20, s * _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0 = 1.0f - s;
        float _t1 = pivot.getFloat(pivotOffset) * _t0;
        float _t2 = pivot.getFloat(pivotOffset + 4) * _t0;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self20);
        dest.putFloat(destOffset + 12, s * _self01);
        dest.putFloat(destOffset + 16, s * _self11);
        dest.putFloat(destOffset + 20, s * _self21);
        dest.putFloat(destOffset + 24, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.putFloat(destOffset + 28, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        dest.putFloat(destOffset + 32, ((_self20) * (_t1) + (((_self21) * (_t2) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.putFloat(destOffset, sX * _self00);
        dest.putFloat(destOffset + 4, sX * _self10);
        dest.putFloat(destOffset + 8, sX * _self20);
        dest.putFloat(destOffset + 12, sY * _self01);
        dest.putFloat(destOffset + 16, sY * _self11);
        dest.putFloat(destOffset + 20, sY * _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self20, _t2, java.lang.Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.putFloat(destOffset, sX * _self00);
        dest.putFloat(destOffset + 4, sX * _self10);
        dest.putFloat(destOffset + 8, sX * _self20);
        dest.putFloat(destOffset + 12, sY * _self01);
        dest.putFloat(destOffset + 16, sY * _self11);
        dest.putFloat(destOffset + 20, sY * _self21);
        dest.putFloat(destOffset + 24, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.putFloat(destOffset + 28, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        dest.putFloat(destOffset + 32, ((_self20) * (_t2) + (((_self21) * (_t3) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@code scaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _sx = s.getFloat(sOffset);
        float _sy = s.getFloat(sOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = pivot.getFloat(pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.getFloat(pivotOffset + 4) * (1.0f - _sy);
        dest.putFloat(destOffset, _sx * _self00);
        dest.putFloat(destOffset + 4, _sx * _self10);
        dest.putFloat(destOffset + 8, _sx * _self20);
        dest.putFloat(destOffset + 12, _sy * _self01);
        dest.putFloat(destOffset + 16, _sy * _self11);
        dest.putFloat(destOffset + 20, _sy * _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self20, _t2, java.lang.Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _sx = s.getFloat(sOffset);
        float _sy = s.getFloat(sOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t2 = pivot.getFloat(pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.getFloat(pivotOffset + 4) * (1.0f - _sy);
        dest.putFloat(destOffset, _sx * _self00);
        dest.putFloat(destOffset + 4, _sx * _self10);
        dest.putFloat(destOffset + 8, _sx * _self20);
        dest.putFloat(destOffset + 12, _sy * _self01);
        dest.putFloat(destOffset + 16, _sy * _self11);
        dest.putFloat(destOffset + 20, _sy * _self21);
        dest.putFloat(destOffset + 24, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.putFloat(destOffset + 28, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        dest.putFloat(destOffset + 32, ((_self20) * (_t2) + (((_self21) * (_t3) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self20, vX, java.lang.Math.fma(_self21, vY, _self22)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer translate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
        dest.putFloat(destOffset + 28, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        dest.putFloat(destOffset + 32, ((_self20) * (vX) + (((_self21) * (vY) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_self20, _vx, java.lang.Math.fma(_self21, _vy, _self22)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer translate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
        dest.putFloat(destOffset + 28, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
        dest.putFloat(destOffset + 32, ((_self20) * (_vx) + (((_self21) * (_vy) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer view_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer view_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dest.putFloat(destOffset, _sp0 * _self00);
        dest.putFloat(destOffset + 4, _sp0 * _self10);
        dest.putFloat(destOffset + 8, _sp0 * _self20);
        dest.putFloat(destOffset + 12, _sp1 * _self01);
        dest.putFloat(destOffset + 16, _sp1 * _self11);
        dest.putFloat(destOffset + 20, _sp1 * _self21);
        dest.putFloat(destOffset + 24, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.putFloat(destOffset + 28, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        dest.putFloat(destOffset + 32, _self22 + (-(_self20 * _sp2) - _self21 * _sp3));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, vZ, java.lang.Math.fma(_self00, vX, _self01 * vY)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, vZ, java.lang.Math.fma(_self10, vX, _self11 * vY)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, vZ, java.lang.Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    /** {@code mulVec3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulVec3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, ((_self02) * (vZ) + (((_self00) * (vX) + (_self01 * vY)))));
        dest.putFloat(destOffset + 4, ((_self12) * (vZ) + (((_self10) * (vX) + (_self11 * vY)))));
        dest.putFloat(destOffset + 8, ((_self22) * (vZ) + (((_self20) * (vX) + (_self21 * vY)))));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, java.lang.Math.fma(_self02, _vz, java.lang.Math.fma(_self00, _vx, _self01 * _vy)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _vz, java.lang.Math.fma(_self10, _vx, _self11 * _vy)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self22, _vz, java.lang.Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    /** {@code mulVec3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulVec3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self20 = src.getFloat(srcOffset + 8);
        float _self01 = src.getFloat(srcOffset + 12);
        float _self11 = src.getFloat(srcOffset + 16);
        float _self21 = src.getFloat(srcOffset + 20);
        float _self02 = src.getFloat(srcOffset + 24);
        float _self12 = src.getFloat(srcOffset + 28);
        float _self22 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset, ((_self02) * (_vz) + (((_self00) * (_vx) + (_self01 * _vy)))));
        dest.putFloat(destOffset + 4, ((_self12) * (_vz) + (((_self10) * (_vx) + (_self11 * _vy)))));
        dest.putFloat(destOffset + 8, ((_self22) * (_vz) + (((_self20) * (_vx) + (_self21 * _vy)))));
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 12);
            float _self11 = src.getFloat(srcOffset + 16);
            dest.putFloat(destOffset, java.lang.Math.fma(_self00, vX, _self01 * vY));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 12);
            float _self11 = src.getFloat(srcOffset + 16);
            dest.putFloat(destOffset, ((_self00) * (vX) + (_self01 * vY)));
            dest.putFloat(destOffset + 4, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 12);
            float _self11 = src.getFloat(srcOffset + 16);
            dest.putFloat(destOffset, java.lang.Math.fma(_self00, _vx, _self01 * _vy));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 12);
            float _self11 = src.getFloat(srcOffset + 16);
            dest.putFloat(destOffset, ((_self00) * (_vx) + (_self01 * _vy)));
            dest.putFloat(destOffset + 4, ((_self10) * (_vx) + (_self11 * _vy)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 12);
            float _self11 = src.getFloat(srcOffset + 16);
            float _self02 = src.getFloat(srcOffset + 24);
            float _self12 = src.getFloat(srcOffset + 28);
            dest.putFloat(destOffset, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
            return dest;
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 12);
            float _self11 = src.getFloat(srcOffset + 16);
            float _self02 = src.getFloat(srcOffset + 24);
            float _self12 = src.getFloat(srcOffset + 28);
            dest.putFloat(destOffset, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
            dest.putFloat(destOffset + 4, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 12);
            float _self11 = src.getFloat(srcOffset + 16);
            float _self02 = src.getFloat(srcOffset + 24);
            float _self12 = src.getFloat(srcOffset + 28);
            dest.putFloat(destOffset, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
            return dest;
        } else {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 12);
            float _self11 = src.getFloat(srcOffset + 16);
            float _self02 = src.getFloat(srcOffset + 24);
            float _self12 = src.getFloat(srcOffset + 28);
            dest.putFloat(destOffset, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
            dest.putFloat(destOffset + 4, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
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
}
