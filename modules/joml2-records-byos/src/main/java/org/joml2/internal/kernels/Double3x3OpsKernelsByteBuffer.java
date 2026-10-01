// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3x3Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double3x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double3x3OpsKernelsByteBuffer {
    private Double3x3OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        Double3x3OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (col) {
            case 0: _idxSw0 = src.getDouble(srcOffset); _idxSw1 = src.getDouble(srcOffset + 8); _idxSw2 = src.getDouble(srcOffset + 16); break;
            case 1: _idxSw0 = src.getDouble(srcOffset + 24); _idxSw1 = src.getDouble(srcOffset + 32); _idxSw2 = src.getDouble(srcOffset + 40); break;
            case 2: _idxSw0 = src.getDouble(srcOffset + 48); _idxSw1 = src.getDouble(srcOffset + 56); _idxSw2 = src.getDouble(srcOffset + 64); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.putDouble(destOffset, _idxSw0);
        dest.putDouble(destOffset + 8, _idxSw1);
        dest.putDouble(destOffset + 16, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = java.lang.Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 <= java.lang.Math.fma(_self02, _self02, _t1) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, Math.atan2(_self21, _self11));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(java.lang.Math.fma(_self10, _self22, -_self12 * _self20), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = ((_self12) * (_self12) + (_self22 * _self22));
        if (_t1 <= ((_self02) * (_self02) + (_t1)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, Math.atan2(_self21, _self11));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(((_self10) * (_self22) - (_self12 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        Double3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = java.lang.Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 <= epsilon * java.lang.Math.fma(_self02, _self02, _t1)) {
            dest.putDouble(destOffset, Math.atan2(_self21, _self11));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(java.lang.Math.fma(_self10, _self22, -_self12 * _self20), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = ((_self12) * (_self12) + (_self22 * _self22));
        if (_t1 <= epsilon * ((_self02) * (_self02) + (_t1))) {
            dest.putDouble(destOffset, Math.atan2(_self21, _self11));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(((_self10) * (_self22) - (_self12 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = java.lang.Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 <= java.lang.Math.fma(_self01, _self01, _t1) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self22));
            dest.putDouble(destOffset + 8, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_self21, _self11));
            dest.putDouble(destOffset + 8, Math.atan2(java.lang.Math.fma(_self10, _self21, -(_self11 * _self20)), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.putDouble(destOffset + 16, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = ((_self11) * (_self11) + (_self21 * _self21));
        if (_t1 <= ((_self01) * (_self01) + (_t1)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self22));
            dest.putDouble(destOffset + 8, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_self21, _self11));
            dest.putDouble(destOffset + 8, Math.atan2(((_self10) * (_self21) - (_self11 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.putDouble(destOffset + 16, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        Double3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = java.lang.Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 <= epsilon * java.lang.Math.fma(_self01, _self01, _t1)) {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self22));
            dest.putDouble(destOffset + 8, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_self21, _self11));
            dest.putDouble(destOffset + 8, Math.atan2(java.lang.Math.fma(_self10, _self21, -(_self11 * _self20)), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.putDouble(destOffset + 16, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = ((_self11) * (_self11) + (_self21 * _self21));
        if (_t1 <= epsilon * ((_self01) * (_self01) + (_t1))) {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self22));
            dest.putDouble(destOffset + 8, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_self21, _self11));
            dest.putDouble(destOffset + 8, Math.atan2(((_self10) * (_self21) - (_self11 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.putDouble(destOffset + 16, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = java.lang.Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 <= java.lang.Math.fma(_self12, _self12, _t1) * 7.888609052210118E-31) {
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self00));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
        }
        dest.putDouble(destOffset, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = ((_self02) * (_self02) + (_self22 * _self22));
        if (_t1 <= ((_self12) * (_self12) + (_t1)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self00));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(((_self02) * (_self21) - (_self01 * _self22)), ((_self00) * (_self22) - (_self02 * _self20))));
        }
        dest.putDouble(destOffset, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        Double3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        double _self00 = src.getDouble(srcOffset);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = java.lang.Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 <= epsilon * java.lang.Math.fma(_self12, _self12, _t1)) {
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self00));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
        }
        dest.putDouble(destOffset, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _self00 = src.getDouble(srcOffset);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = ((_self02) * (_self02) + (_self22 * _self22));
        if (_t1 <= epsilon * ((_self12) * (_self12) + (_t1))) {
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self00));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(((_self02) * (_self21) - (_self01 * _self22)), ((_self00) * (_self22) - (_self02 * _self20))));
        }
        dest.putDouble(destOffset, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = java.lang.Math.fma(_self00, _self00, _self20 * _self20);
        if (_t1 <= java.lang.Math.fma(_self10, _self10, _t1) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self22));
        } else {
            dest.putDouble(destOffset, Math.atan2(java.lang.Math.fma(_self00, _self21, _self01 * -_self20), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self00));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = ((_self00) * (_self00) + (_self20 * _self20));
        if (_t1 <= ((_self10) * (_self10) + (_t1)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self22));
        } else {
            dest.putDouble(destOffset, Math.atan2(((_self00) * (_self21) - (_self01 * _self20)), ((_self00) * (_self22) - (_self02 * _self20))));
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self00));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        Double3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = java.lang.Math.fma(_self00, _self00, _self20 * _self20);
        if (_t1 <= epsilon * java.lang.Math.fma(_self10, _self10, _t1)) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self22));
        } else {
            dest.putDouble(destOffset, Math.atan2(java.lang.Math.fma(_self00, _self21, _self01 * -_self20), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self00));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = ((_self00) * (_self00) + (_self20 * _self20));
        if (_t1 <= epsilon * ((_self10) * (_self10) + (_t1))) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self22));
        } else {
            dest.putDouble(destOffset, Math.atan2(((_self00) * (_self21) - (_self01 * _self20)), ((_self00) * (_self22) - (_self02 * _self20))));
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self00));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _t1 = java.lang.Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 <= java.lang.Math.fma(_self21, _self21, _t1) * 7.888609052210118E-31) {
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self00));
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(java.lang.Math.fma(_self02, _self11, -_self01 * _self12), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self11));
        }
        dest.putDouble(destOffset, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _t1 = ((_self01) * (_self01) + (_self11 * _self11));
        if (_t1 <= ((_self21) * (_self21) + (_t1)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self00));
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(((_self02) * (_self11) - (_self01 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self11));
        }
        dest.putDouble(destOffset, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        Double3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _t1 = java.lang.Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 <= epsilon * java.lang.Math.fma(_self21, _self21, _t1)) {
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self00));
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(java.lang.Math.fma(_self02, _self11, -_self01 * _self12), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self11));
        }
        dest.putDouble(destOffset, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _t1 = ((_self01) * (_self01) + (_self11 * _self11));
        if (_t1 <= epsilon * ((_self21) * (_self21) + (_t1))) {
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self00));
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(((_self02) * (_self11) - (_self01 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self11));
        }
        dest.putDouble(destOffset, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _t1 = java.lang.Math.fma(_self00, _self00, _self10 * _self10);
        if (_t1 <= java.lang.Math.fma(_self20, _self20, _t1) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self11));
        } else {
            dest.putDouble(destOffset, Math.atan2(java.lang.Math.fma(_self02, _self10, -(_self00 * _self12)), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self00));
        }
        dest.putDouble(destOffset + 8, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _t1 = ((_self00) * (_self00) + (_self10 * _self10));
        if (_t1 <= ((_self20) * (_self20) + (_t1)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self11));
        } else {
            dest.putDouble(destOffset, Math.atan2(((_self02) * (_self10) - (_self00 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self00));
        }
        dest.putDouble(destOffset + 8, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        Double3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _t1 = java.lang.Math.fma(_self00, _self00, _self10 * _self10);
        if (_t1 <= epsilon * java.lang.Math.fma(_self20, _self20, _t1)) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self11));
        } else {
            dest.putDouble(destOffset, Math.atan2(java.lang.Math.fma(_self02, _self10, -(_self00 * _self12)), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self00));
        }
        dest.putDouble(destOffset + 8, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _t1 = ((_self00) * (_self00) + (_self10 * _self10));
        if (_t1 <= epsilon * ((_self20) * (_self20) + (_t1))) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self11));
        } else {
            dest.putDouble(destOffset, Math.atan2(((_self02) * (_self10) - (_self00 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self00));
        }
        dest.putDouble(destOffset + 8, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getNormalizedRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getNormalizedRotation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getNormalizedRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getNormalizedRotation_api_fma(dest, destOffset, src, srcOffset);
        return getNormalizedRotation_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getNormalizedRotation_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        double _t7 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        double _t8 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t21, _t23, _t27;
        if (_t6 != 0.0) {
            _t21 = _self01 * _t9;
            _t23 = _self11 * _t9;
            _t27 = _self21 * _t9;
        } else {
            _t21 = 0.0;
            _t23 = 0.0;
            _t27 = 0.0;
        }
        double _t22, _t24, _t26;
        if (_t7 != 0.0) {
            _t22 = _self12 * _t10;
            _t24 = _self02 * _t10;
            _t26 = _self22 * _t10;
        } else {
            _t22 = 0.0;
            _t24 = 0.0;
            _t26 = 0.0;
        }
        return getNormalizedRotation_api_fma_sfeef3f82_1(dest, destOffset, _self00, _self10, _self20, _t8, (1.0 / java.lang.Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_fma_sfeef3f82_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _t8, double _t11, double _t21, double _t23, double _t27, double _t22, double _t24, double _t26) {
        double _t25, _t28, _t29;
        if (_t8 != 0.0) {
            _t25 = _self20 * _t11;
            _t28 = _self00 * _t11;
            _t29 = _self10 * _t11;
        } else {
            _t25 = 0.0;
            _t28 = 0.0;
            _t29 = 0.0;
        }
        double _t49, _t50, _t51;
        if (java.lang.Math.fma(java.lang.Math.fma(_t21, _t22, -(_t23 * _t24)), _t25, java.lang.Math.fma(java.lang.Math.fma(_t23, _t26, -(_t27 * _t22)), _t28, java.lang.Math.fma(_t27, _t24, -(_t21 * _t26)) * _t29)) < 0.0) {
            _t49 = -_t28;
            _t50 = -_t29;
            _t51 = -_t25;
        } else {
            _t49 = _t28;
            _t50 = _t29;
            _t51 = _t25;
        }
        double _t52 = _t49 + _t23;
        double _t58 = _t52 + _t26;
        double _t62 = 1.0 + _t58;
        double _t63 = 1.0 + (_t49 - (_t23 + _t26));
        double _t64 = 1.0 + (_t23 - (_t49 + _t26));
        double _t65 = 1.0 + (_t26 - _t52);
        return getNormalizedRotation_api_fma_sfeef3f82_2(dest, destOffset, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5 * (1.0 / java.lang.Math.sqrt(_t62)), 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_fma_sfeef3f82_2(java.nio.ByteBuffer dest, int destOffset, double _t23, double _t26, double _t36, double _t39, double _t49, double _t53, double _t55, double _t56, double _t57, double _t58, double _t62, double _t63, double _t64, double _t65, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t58 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t36);
            dest.putDouble(destOffset + 8, _sp0 * _t56);
            dest.putDouble(destOffset + 16, _sp0 * _t57);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t63));
                dest.putDouble(destOffset + 8, _sp3 * _t53);
                dest.putDouble(destOffset + 16, _sp3 * _t55);
                dest.putDouble(destOffset + 24, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.putDouble(destOffset, _sp1 * _t53);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t64));
                    dest.putDouble(destOffset + 16, _sp1 * _t39);
                    dest.putDouble(destOffset + 24, _sp1 * _t56);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t55);
                    dest.putDouble(destOffset + 8, _sp2 * _t39);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t65));
                    dest.putDouble(destOffset + 24, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    /** {@code getNormalizedRotation_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        double _t7 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        double _t8 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t21, _t23, _t27;
        if (_t6 != 0.0) {
            _t21 = _self01 * _t9;
            _t23 = _self11 * _t9;
            _t27 = _self21 * _t9;
        } else {
            _t21 = 0.0;
            _t23 = 0.0;
            _t27 = 0.0;
        }
        double _t22, _t24, _t26;
        if (_t7 != 0.0) {
            _t22 = _self12 * _t10;
            _t24 = _self02 * _t10;
            _t26 = _self22 * _t10;
        } else {
            _t22 = 0.0;
            _t24 = 0.0;
            _t26 = 0.0;
        }
        return getNormalizedRotation_api_mulAdd_sc7b493e1_1(dest, destOffset, _self00, _self10, _self20, _t8, (1.0 / java.lang.Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_mulAdd_sc7b493e1_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _t8, double _t11, double _t21, double _t23, double _t27, double _t22, double _t24, double _t26) {
        double _t25, _t28, _t29;
        if (_t8 != 0.0) {
            _t25 = _self20 * _t11;
            _t28 = _self00 * _t11;
            _t29 = _self10 * _t11;
        } else {
            _t25 = 0.0;
            _t28 = 0.0;
            _t29 = 0.0;
        }
        double _t49, _t50, _t51;
        if (((((_t21) * (_t22) - (_t23 * _t24))) * (_t25) + (((((_t23) * (_t26) - (_t27 * _t22))) * (_t28) + (((_t27) * (_t24) - (_t21 * _t26)) * _t29)))) < 0.0) {
            _t49 = -_t28;
            _t50 = -_t29;
            _t51 = -_t25;
        } else {
            _t49 = _t28;
            _t50 = _t29;
            _t51 = _t25;
        }
        double _t52 = _t49 + _t23;
        double _t58 = _t52 + _t26;
        double _t62 = 1.0 + _t58;
        double _t63 = 1.0 + (_t49 - (_t23 + _t26));
        double _t64 = 1.0 + (_t23 - (_t49 + _t26));
        double _t65 = 1.0 + (_t26 - _t52);
        return getNormalizedRotation_api_mulAdd_sc7b493e1_2(dest, destOffset, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5 * (1.0 / java.lang.Math.sqrt(_t62)), 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_mulAdd_sc7b493e1_2(java.nio.ByteBuffer dest, int destOffset, double _t23, double _t26, double _t36, double _t39, double _t49, double _t53, double _t55, double _t56, double _t57, double _t58, double _t62, double _t63, double _t64, double _t65, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t58 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t36);
            dest.putDouble(destOffset + 8, _sp0 * _t56);
            dest.putDouble(destOffset + 16, _sp0 * _t57);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t63));
                dest.putDouble(destOffset + 8, _sp3 * _t53);
                dest.putDouble(destOffset + 16, _sp3 * _t55);
                dest.putDouble(destOffset + 24, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.putDouble(destOffset, _sp1 * _t53);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t64));
                    dest.putDouble(destOffset + 16, _sp1 * _t39);
                    dest.putDouble(destOffset + 24, _sp1 * _t56);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t55);
                    dest.putDouble(destOffset + 8, _sp2 * _t39);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t65));
                    dest.putDouble(destOffset + 24, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer getRow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        Double3x3OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, row);
        return dest;
    }

    public static java.nio.ByteBuffer getRow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (row) {
            case 0: _idxSw0 = src.getDouble(srcOffset); _idxSw1 = src.getDouble(srcOffset + 24); _idxSw2 = src.getDouble(srcOffset + 48); break;
            case 1: _idxSw0 = src.getDouble(srcOffset + 8); _idxSw1 = src.getDouble(srcOffset + 32); _idxSw2 = src.getDouble(srcOffset + 56); break;
            case 2: _idxSw0 = src.getDouble(srcOffset + 16); _idxSw1 = src.getDouble(srcOffset + 40); _idxSw2 = src.getDouble(srcOffset + 64); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.putDouble(destOffset, _idxSw0);
        dest.putDouble(destOffset + 8, _idxSw1);
        dest.putDouble(destOffset + 16, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getScale_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getScale_api_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, java.lang.Math.sqrt(java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10))));
        dest.putDouble(destOffset + 8, java.lang.Math.sqrt(java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11))));
        dest.putDouble(destOffset + 16, java.lang.Math.sqrt(java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    /** {@code getScale_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getScale_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, java.lang.Math.sqrt(((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))))));
        dest.putDouble(destOffset + 8, java.lang.Math.sqrt(((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))))));
        dest.putDouble(destOffset + 16, java.lang.Math.sqrt(((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))))));
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getTranslation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        dest.putDouble(destOffset, _self02);
        dest.putDouble(destOffset + 8, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer getUnnormalizedRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getUnnormalizedRotation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getUnnormalizedRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = _self00 + _self11;
        double _t10 = _self22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_self00 - (_self11 + _self22));
        double _t16 = 1.0 + (_self11 - (_self00 + _self22));
        double _t17 = 1.0 + (_self22 - _t0);
        return getUnnormalizedRotation_api_s9cd1b5fe_1(dest, destOffset, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getUnnormalizedRotation_api_s9cd1b5fe_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self11, double _self22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t1);
            dest.putDouble(destOffset + 8, _sp0 * _t7);
            dest.putDouble(destOffset + 16, _sp0 * _t9);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.putDouble(destOffset + 8, _sp3 * _t4);
                dest.putDouble(destOffset + 16, _sp3 * _t6);
                dest.putDouble(destOffset + 24, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    dest.putDouble(destOffset, _sp1 * _t4);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.putDouble(destOffset + 16, _sp1 * _t8);
                    dest.putDouble(destOffset + 24, _sp1 * _t7);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t6);
                    dest.putDouble(destOffset + 8, _sp2 * _t8);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.putDouble(destOffset + 24, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer cofactor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cofactor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return cofactor_api_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, java.lang.Math.fma(_self11, _self22, -_self12 * _self21));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self01, _self12, _self02 * -_self11));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self12, _self20, -_self10 * _self22));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self00, _self22, -(_self02 * _self20)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self02, _self10, _self00 * -_self12));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self10, _self21, -_self11 * _self20));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self01, _self20, -(_self00 * _self21)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self00, _self11, _self01 * -_self10));
        return dest;
    }

    /** {@code cofactor_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer cofactor_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, ((_self11) * (_self22) - (_self12 * _self21)));
        dest.putDouble(destOffset + 8, ((_self02) * (_self21) - (_self01 * _self22)));
        dest.putDouble(destOffset + 16, ((_self01) * (_self12) - (_self02 * _self11)));
        dest.putDouble(destOffset + 24, ((_self12) * (_self20) - (_self10 * _self22)));
        dest.putDouble(destOffset + 32, ((_self00) * (_self22) - (_self02 * _self20)));
        dest.putDouble(destOffset + 40, ((_self02) * (_self10) - (_self00 * _self12)));
        dest.putDouble(destOffset + 48, ((_self10) * (_self21) - (_self11 * _self20)));
        dest.putDouble(destOffset + 56, ((_self01) * (_self20) - (_self00 * _self21)));
        dest.putDouble(destOffset + 64, ((_self00) * (_self11) - (_self01 * _self10)));
        return dest;
    }

    public static double determinant_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return determinant_api_mulAdd(src, srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t3 = -(_self11 * _self20);
        double _t4 = -_self12 * _self21;
        double _t5 = -_self12 * _self20;
        return java.lang.Math.fma(src.getDouble(srcOffset + 48), java.lang.Math.fma(_self10, _self21, _t3) - java.lang.Math.fma(_self11, _self20, _t3), java.lang.Math.fma(src.getDouble(srcOffset), java.lang.Math.fma(_self11, _self22, _t4) - java.lang.Math.fma(_self12, _self21, _t4), src.getDouble(srcOffset + 24) * (java.lang.Math.fma(_self12, _self20, _t5) - java.lang.Math.fma(_self10, _self22, _t5))));
    }

    /** {@code determinant_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double determinant_api_mulAdd(java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t3 = -(_self11 * _self20);
        double _t4 = -_self12 * _self21;
        double _t5 = -_self12 * _self20;
        return ((src.getDouble(srcOffset + 48)) * (((_self10) * (_self21) + (_t3)) - ((_self11) * (_self20) + (_t3))) + (((src.getDouble(srcOffset)) * (((_self11) * (_self22) + (_t4)) - ((_self12) * (_self21) + (_t4))) + (src.getDouble(srcOffset + 24) * (((_self12) * (_self20) + (_t5)) - ((_self10) * (_self22) + (_t5)))))));
    }

    public static double frobeniusNorm_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self20 = src.getDouble(srcOffset + 16);
            double _self01 = src.getDouble(srcOffset + 24);
            double _self11 = src.getDouble(srcOffset + 32);
            double _self21 = src.getDouble(srcOffset + 40);
            double _self02 = src.getDouble(srcOffset + 48);
            double _self12 = src.getDouble(srcOffset + 56);
            double _self22 = src.getDouble(srcOffset + 64);
            return java.lang.Math.sqrt(java.lang.Math.fma(_self00, _self00, _self01 * _self01) + java.lang.Math.fma(_self02, _self02, _self10 * _self10) + (java.lang.Math.fma(_self11, _self11, _self12 * _self12) + java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self21, _self21, _self22 * _self22))));
        } else {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self20 = src.getDouble(srcOffset + 16);
            double _self01 = src.getDouble(srcOffset + 24);
            double _self11 = src.getDouble(srcOffset + 32);
            double _self21 = src.getDouble(srcOffset + 40);
            double _self02 = src.getDouble(srcOffset + 48);
            double _self12 = src.getDouble(srcOffset + 56);
            double _self22 = src.getDouble(srcOffset + 64);
            return java.lang.Math.sqrt(((_self00) * (_self00) + (_self01 * _self01)) + ((_self02) * (_self02) + (_self10 * _self10)) + (((_self11) * (_self11) + (_self12 * _self12)) + ((_self20) * (_self20) + (((_self21) * (_self21) + (_self22 * _self22))))));
        }
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_api_fma(dest, destOffset, src, srcOffset);
        return invert_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code invert_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t10 = -_self12 * _self21;
        double _t11 = -(_self11 * _self20);
        double _t12 = -_self12 * _self20;
        double _t26 = java.lang.Math.fma(_self11, _self22, _t10) - java.lang.Math.fma(_self12, _self21, _t10);
        double _t27 = java.lang.Math.fma(_self10, _self21, _t11) - java.lang.Math.fma(_self11, _self20, _t11);
        double _t33 = java.lang.Math.fma(_self02, _t27, java.lang.Math.fma(_self00, _t26, _self01 * (java.lang.Math.fma(_self12, _self20, _t12) - java.lang.Math.fma(_self10, _self22, _t12))));
        if (!(java.lang.Math.abs(_t33) > 2.2250738585072014E-308 && java.lang.Math.abs(_t33) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        return invert_api_fma_s8d266039_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self01 * _self22, -_self02 * _self11, -(_self10 * _self22), -_self02 * _self20, -_self00 * _self12, -_self00 * _self21, -_self01 * _self10, _t26, _t27, 1.0 / _t33);
    }

    /** Piece 2 of {@code invert_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_api_fma_s8d266039_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t33_inv) {
        dest.putDouble(destOffset, _t26 * _t33_inv);
        dest.putDouble(destOffset + 8, (java.lang.Math.fma(_self12, _self20, _t15) - java.lang.Math.fma(_self10, _self22, _t15)) * _t33_inv);
        dest.putDouble(destOffset + 16, _t27 * _t33_inv);
        dest.putDouble(destOffset + 24, (java.lang.Math.fma(_self02, _self21, _t13) - java.lang.Math.fma(_self01, _self22, _t13)) * _t33_inv);
        dest.putDouble(destOffset + 32, (java.lang.Math.fma(_self00, _self22, _t16) - java.lang.Math.fma(_self02, _self20, _t16)) * _t33_inv);
        dest.putDouble(destOffset + 40, (java.lang.Math.fma(_self01, _self20, _t18) - java.lang.Math.fma(_self00, _self21, _t18)) * _t33_inv);
        dest.putDouble(destOffset + 48, (java.lang.Math.fma(_self01, _self12, _t14) - java.lang.Math.fma(_self02, _self11, _t14)) * _t33_inv);
        dest.putDouble(destOffset + 56, (java.lang.Math.fma(_self02, _self10, _t17) - java.lang.Math.fma(_self00, _self12, _t17)) * _t33_inv);
        dest.putDouble(destOffset + 64, (java.lang.Math.fma(_self00, _self11, _t19) - java.lang.Math.fma(_self01, _self10, _t19)) * _t33_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t10 = -_self12 * _self21;
        double _t11 = -(_self11 * _self20);
        double _t12 = -_self12 * _self20;
        double _t26 = ((_self11) * (_self22) + (_t10)) - ((_self12) * (_self21) + (_t10));
        double _t27 = ((_self10) * (_self21) + (_t11)) - ((_self11) * (_self20) + (_t11));
        double _t33 = ((_self02) * (_t27) + (((_self00) * (_t26) + (_self01 * (((_self12) * (_self20) + (_t12)) - ((_self10) * (_self22) + (_t12)))))));
        if (!(java.lang.Math.abs(_t33) > 2.2250738585072014E-308 && java.lang.Math.abs(_t33) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t33_inv = 1.0 / _t33;
        dest.putDouble(destOffset, _t26 * _t33_inv);
        return invert_api_mulAdd_s68293728_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self01 * _self22, -_self02 * _self11, -(_self10 * _self22), -_self02 * _self20, -_self00 * _self12, -_self00 * _self21, -_self01 * _self10, _t27, _t33_inv);
    }

    /** Piece 2 of {@code invert_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_api_mulAdd_s68293728_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t27, double _t33_inv) {
        dest.putDouble(destOffset + 8, (((_self12) * (_self20) + (_t15)) - ((_self10) * (_self22) + (_t15))) * _t33_inv);
        dest.putDouble(destOffset + 16, _t27 * _t33_inv);
        dest.putDouble(destOffset + 24, (((_self02) * (_self21) + (_t13)) - ((_self01) * (_self22) + (_t13))) * _t33_inv);
        dest.putDouble(destOffset + 32, (((_self00) * (_self22) + (_t16)) - ((_self02) * (_self20) + (_t16))) * _t33_inv);
        dest.putDouble(destOffset + 40, (((_self01) * (_self20) + (_t18)) - ((_self00) * (_self21) + (_t18))) * _t33_inv);
        dest.putDouble(destOffset + 48, (((_self01) * (_self12) + (_t14)) - ((_self02) * (_self11) + (_t14))) * _t33_inv);
        dest.putDouble(destOffset + 56, (((_self02) * (_self10) + (_t17)) - ((_self00) * (_self12) + (_t17))) * _t33_inv);
        dest.putDouble(destOffset + 64, (((_self00) * (_self11) + (_t19)) - ((_self01) * (_self10) + (_t19))) * _t33_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invert_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code invert_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t12 = _self11 * _t0;
        double _t13 = _self22 * _t1;
        double _t14 = _self12 * _t0;
        double _t15 = _self21 * _t1;
        double _t16 = _self10 * _t0;
        double _t17 = _self20 * _t1;
        double _t18 = _self02 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self01 * _t2;
        double _t31 = -_t14 * _t15;
        return invert_degenerate_api_fma_s86b1d2cc_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -(_t12 * _t17), -_t14 * _t17, -_t20 * _t13, -_t18 * _t12, -(_t16 * _t13), -_t18 * _t17, -_t19 * _t14, -_t19 * _t15, -_t20 * _t16, java.lang.Math.fma(_t12, _t13, _t31) - java.lang.Math.fma(_t14, _t15, _t31));
    }

    /** Piece 2 of {@code invert_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_fma_s86b1d2cc_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _t2, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40, double _t47) {
        double _t48 = java.lang.Math.fma(_t16, _t15, _t32) - java.lang.Math.fma(_t12, _t17, _t32);
        double _t53_inv = 1.0 / java.lang.Math.fma(_t48, _t18, java.lang.Math.fma(_t47, _t19, (java.lang.Math.fma(_t14, _t17, _t33) - java.lang.Math.fma(_t16, _t13, _t33)) * _t20));
        double _sp2 = _t1 * _t53_inv;
        double _sp1 = _t0 * _t53_inv;
        double _sp0 = _t2 * _t53_inv;
        dest.putDouble(destOffset, _t47 * _sp0);
        dest.putDouble(destOffset + 8, (java.lang.Math.fma(_t14, _t17, _t36) - java.lang.Math.fma(_t16, _t13, _t36)) * _sp0);
        dest.putDouble(destOffset + 16, _t48 * _sp0);
        dest.putDouble(destOffset + 24, (java.lang.Math.fma(_t18, _t15, _t34) - java.lang.Math.fma(_t20, _t13, _t34)) * _sp1);
        dest.putDouble(destOffset + 32, (java.lang.Math.fma(_t19, _t13, _t37) - java.lang.Math.fma(_t18, _t17, _t37)) * _sp1);
        dest.putDouble(destOffset + 40, (java.lang.Math.fma(_t20, _t17, _t39) - java.lang.Math.fma(_t19, _t15, _t39)) * _sp1);
        dest.putDouble(destOffset + 48, (java.lang.Math.fma(_t20, _t14, _t35) - java.lang.Math.fma(_t18, _t12, _t35)) * _sp2);
        return invert_degenerate_api_fma_s86b1d2cc_2(dest, destOffset, _t12, _t14, _t16, _t18, _t19, _t20, _t38, _t40, _sp2);
    }

    /** Piece 3 of {@code invert_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_fma_s86b1d2cc_2(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t14, double _t16, double _t18, double _t19, double _t20, double _t38, double _t40, double _sp2) {
        dest.putDouble(destOffset + 56, (java.lang.Math.fma(_t18, _t16, _t38) - java.lang.Math.fma(_t19, _t14, _t38)) * _sp2);
        dest.putDouble(destOffset + 64, (java.lang.Math.fma(_t19, _t12, _t40) - java.lang.Math.fma(_t20, _t16, _t40)) * _sp2);
        return dest;
    }

    /** {@code invert_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t12 = _self11 * _t0;
        double _t13 = _self22 * _t1;
        double _t14 = _self12 * _t0;
        double _t15 = _self21 * _t1;
        double _t16 = _self10 * _t0;
        double _t17 = _self20 * _t1;
        double _t18 = _self02 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self01 * _t2;
        double _t31 = -_t14 * _t15;
        return invert_degenerate_api_mulAdd_sc0a0a973_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -(_t12 * _t17), -_t14 * _t17, -_t20 * _t13, -_t18 * _t12, -(_t16 * _t13), -_t18 * _t17, -_t19 * _t14, -_t19 * _t15, -_t20 * _t16, ((_t12) * (_t13) + (_t31)) - ((_t14) * (_t15) + (_t31)));
    }

    /** Piece 2 of {@code invert_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_mulAdd_sc0a0a973_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _t2, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40, double _t47) {
        double _t48 = ((_t16) * (_t15) + (_t32)) - ((_t12) * (_t17) + (_t32));
        double _t53_inv = 1.0 / ((_t48) * (_t18) + (((_t47) * (_t19) + ((((_t14) * (_t17) + (_t33)) - ((_t16) * (_t13) + (_t33))) * _t20))));
        double _sp2 = _t1 * _t53_inv;
        double _sp1 = _t0 * _t53_inv;
        double _sp0 = _t2 * _t53_inv;
        dest.putDouble(destOffset, _t47 * _sp0);
        dest.putDouble(destOffset + 8, (((_t14) * (_t17) + (_t36)) - ((_t16) * (_t13) + (_t36))) * _sp0);
        dest.putDouble(destOffset + 16, _t48 * _sp0);
        dest.putDouble(destOffset + 24, (((_t18) * (_t15) + (_t34)) - ((_t20) * (_t13) + (_t34))) * _sp1);
        dest.putDouble(destOffset + 32, (((_t19) * (_t13) + (_t37)) - ((_t18) * (_t17) + (_t37))) * _sp1);
        dest.putDouble(destOffset + 40, (((_t20) * (_t17) + (_t39)) - ((_t19) * (_t15) + (_t39))) * _sp1);
        dest.putDouble(destOffset + 48, (((_t20) * (_t14) + (_t35)) - ((_t18) * (_t12) + (_t35))) * _sp2);
        dest.putDouble(destOffset + 56, (((_t18) * (_t16) + (_t38)) - ((_t19) * (_t14) + (_t38))) * _sp2);
        dest.putDouble(destOffset + 64, (((_t19) * (_t12) + (_t40)) - ((_t20) * (_t16) + (_t40))) * _sp2);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        return invertProduct_api_fma_s2dc24fa2_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, -_other02 * _other11, -(_other11 * _other20), -_other12 * _other21, -_other12 * _other20, -(_self11 * _self20), -_self12 * _self21, -_self12 * _self20, -_other01 * _other22, -_self10 * _self22, -_self00 * _self21, -_self02 * _self20);
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma_s2dc24fa2_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30) {
        double _t81 = java.lang.Math.fma(_other10, _other21, _t21) - java.lang.Math.fma(_other11, _other20, _t21);
        double _t82 = java.lang.Math.fma(_other11, _other22, _t22) - java.lang.Math.fma(_other12, _other21, _t22);
        double _t108 = java.lang.Math.fma(_other02, _t81, java.lang.Math.fma(_other00, _t82, _other01 * (java.lang.Math.fma(_other12, _other20, _t23) - java.lang.Math.fma(_other10, _other22, _t23))));
        if (!(java.lang.Math.abs(_t108) > 2.2250738585072014E-308 && java.lang.Math.abs(_t108) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_fma_s2dc24fa2_2(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t20, _t26, _t27, _t28, _t29, _t30, -(_self01 * _self22), _self01 * -_self10, -_self00 * _self12, -_self02 * _self11, -_other00 * _other12, -_other02 * _other20, -(_other10 * _other22), -_other01 * _other10, -_other00 * _other21, _t81, _t82, java.lang.Math.fma(_self10, _self21, _t24) - java.lang.Math.fma(_self11, _self20, _t24), java.lang.Math.fma(_self11, _self22, _t25) - java.lang.Math.fma(_self12, _self21, _t25), _t108);
    }

    /** Piece 3 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma_s2dc24fa2_2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t20, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t81, double _t82, double _t84, double _t85, double _t108) {
        double _t109 = java.lang.Math.fma(_self02, _t84, java.lang.Math.fma(_self00, _t85, _self01 * (java.lang.Math.fma(_self12, _self20, _t26) - java.lang.Math.fma(_self10, _self22, _t26))));
        if (!(java.lang.Math.abs(_t109) > 2.2250738585072014E-308 && java.lang.Math.abs(_t109) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_fma_s2dc24fa2_3(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t33, _t34, _t35, _t36, _t37, _t38, _t39, _t81, _t108, _t109, (java.lang.Math.fma(_other01, _other12, _t20) - java.lang.Math.fma(_other02, _other11, _t20)) / _t108, _t84 / _t109, (java.lang.Math.fma(_other02, _other21, _t27) - java.lang.Math.fma(_other01, _other22, _t27)) / _t108, (java.lang.Math.fma(_self12, _self20, _t28) - java.lang.Math.fma(_self10, _self22, _t28)) / _t109, _t82 / _t108, _t85 / _t109, (java.lang.Math.fma(_self01, _self20, _t29) - java.lang.Math.fma(_self00, _self21, _t29)) / _t109, (java.lang.Math.fma(_self00, _self22, _t30) - java.lang.Math.fma(_self02, _self20, _t30)) / _t109, (java.lang.Math.fma(_self02, _self21, _t31) - java.lang.Math.fma(_self01, _self22, _t31)) / _t109, (java.lang.Math.fma(_self00, _self11, _t32) - java.lang.Math.fma(_self01, _self10, _t32)) / _t109);
    }

    /** Piece 4 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma_s2dc24fa2_3(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t81, double _t108, double _t109, double _t128, double _t129, double _t130, double _t131, double _t132, double _t133, double _t134, double _t135, double _t136, double _t137) {
        double _t140 = (java.lang.Math.fma(_other02, _other10, _t35) - java.lang.Math.fma(_other00, _other12, _t35)) / _t108;
        double _t141 = (java.lang.Math.fma(_other00, _other22, _t36) - java.lang.Math.fma(_other02, _other20, _t36)) / _t108;
        double _t142 = (java.lang.Math.fma(_other12, _other20, _t37) - java.lang.Math.fma(_other10, _other22, _t37)) / _t108;
        double _t143 = (java.lang.Math.fma(_other00, _other11, _t38) - java.lang.Math.fma(_other01, _other10, _t38)) / _t108;
        double _t144 = (java.lang.Math.fma(_other01, _other20, _t39) - java.lang.Math.fma(_other00, _other21, _t39)) / _t108;
        double _t145 = _t81 / _t108;
        dest.putDouble(destOffset, java.lang.Math.fma(_t128, _t129, java.lang.Math.fma(_t130, _t131, _t132 * _t133)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t140, _t129, java.lang.Math.fma(_t141, _t131, _t142 * _t133)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t143, _t129, java.lang.Math.fma(_t144, _t131, _t145 * _t133)));
        return invertProduct_api_fma_s2dc24fa2_4(dest, destOffset, _t128, _t130, _t132, _t134, _t135, _t136, _t137, (java.lang.Math.fma(_self02, _self10, _t33) - java.lang.Math.fma(_self00, _self12, _t33)) / _t109, (java.lang.Math.fma(_self01, _self12, _t34) - java.lang.Math.fma(_self02, _self11, _t34)) / _t109, _t140, _t141, _t142, _t143, _t144, _t145);
    }

    /** Piece 5 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma_s2dc24fa2_4(java.nio.ByteBuffer dest, int destOffset, double _t128, double _t130, double _t132, double _t134, double _t135, double _t136, double _t137, double _t138, double _t139, double _t140, double _t141, double _t142, double _t143, double _t144, double _t145) {
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t128, _t134, java.lang.Math.fma(_t130, _t135, _t132 * _t136)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t140, _t134, java.lang.Math.fma(_t141, _t135, _t142 * _t136)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t143, _t134, java.lang.Math.fma(_t144, _t135, _t145 * _t136)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_t128, _t137, java.lang.Math.fma(_t130, _t138, _t132 * _t139)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t140, _t137, java.lang.Math.fma(_t141, _t138, _t142 * _t139)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_t143, _t137, java.lang.Math.fma(_t144, _t138, _t145 * _t139)));
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        return invertProduct_api_mulAdd_s234a47a5_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, -_other02 * _other11, -(_other11 * _other20), -_other12 * _other21, -_other12 * _other20, -(_self11 * _self20), -_self12 * _self21, -_self12 * _self20, -_other01 * _other22, -_self10 * _self22, -_self00 * _self21, -_self02 * _self20);
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30) {
        double _t81 = ((_other10) * (_other21) + (_t21)) - ((_other11) * (_other20) + (_t21));
        double _t82 = ((_other11) * (_other22) + (_t22)) - ((_other12) * (_other21) + (_t22));
        double _t84 = ((_self10) * (_self21) + (_t24)) - ((_self11) * (_self20) + (_t24));
        double _t85 = ((_self11) * (_self22) + (_t25)) - ((_self12) * (_self21) + (_t25));
        double _t108 = ((_other02) * (_t81) + (((_other00) * (_t82) + (_other01 * (((_other12) * (_other20) + (_t23)) - ((_other10) * (_other22) + (_t23)))))));
        if (!(java.lang.Math.abs(_t108) > 2.2250738585072014E-308 && java.lang.Math.abs(_t108) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd_s234a47a5_2(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t20, _t27, _t28, _t29, _t30, -(_self01 * _self22), _self01 * -_self10, -_self00 * _self12, -_self02 * _self11, -_other00 * _other12, -_other02 * _other20, -(_other10 * _other22), -_other01 * _other10, -_other00 * _other21, _t81, _t82, _t84, _t85, _t108, ((_self02) * (_t84) + (((_self00) * (_t85) + (_self01 * (((_self12) * (_self20) + (_t26)) - ((_self10) * (_self22) + (_t26))))))));
    }

    /** Piece 3 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t20, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t81, double _t82, double _t84, double _t85, double _t108, double _t109) {
        if (!(java.lang.Math.abs(_t109) > 2.2250738585072014E-308 && java.lang.Math.abs(_t109) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd_s234a47a5_3(dest, destOffset, _other00, _other10, _other20, _other01, _other11, _other21, _other12, _other22, _t37, _t38, _t39, _t81, _t108, (((_other01) * (_other12) + (_t20)) - ((_other02) * (_other11) + (_t20))) / _t108, _t84 / _t109, (((_other02) * (_other21) + (_t27)) - ((_other01) * (_other22) + (_t27))) / _t108, (((_self12) * (_self20) + (_t28)) - ((_self10) * (_self22) + (_t28))) / _t109, _t82 / _t108, _t85 / _t109, (((_self01) * (_self20) + (_t29)) - ((_self00) * (_self21) + (_t29))) / _t109, (((_self00) * (_self22) + (_t30)) - ((_self02) * (_self20) + (_t30))) / _t109, (((_self02) * (_self21) + (_t31)) - ((_self01) * (_self22) + (_t31))) / _t109, (((_self00) * (_self11) + (_t32)) - ((_self01) * (_self10) + (_t32))) / _t109, (((_self02) * (_self10) + (_t33)) - ((_self00) * (_self12) + (_t33))) / _t109, (((_self01) * (_self12) + (_t34)) - ((_self02) * (_self11) + (_t34))) / _t109, (((_other02) * (_other10) + (_t35)) - ((_other00) * (_other12) + (_t35))) / _t108, (((_other00) * (_other22) + (_t36)) - ((_other02) * (_other20) + (_t36))) / _t108);
    }

    /** Piece 4 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_3(java.nio.ByteBuffer dest, int destOffset, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other12, double _other22, double _t37, double _t38, double _t39, double _t81, double _t108, double _t128, double _t129, double _t130, double _t131, double _t132, double _t133, double _t134, double _t135, double _t136, double _t137, double _t138, double _t139, double _t140, double _t141) {
        double _t142 = (((_other12) * (_other20) + (_t37)) - ((_other10) * (_other22) + (_t37))) / _t108;
        double _t143 = (((_other00) * (_other11) + (_t38)) - ((_other01) * (_other10) + (_t38))) / _t108;
        double _t144 = (((_other01) * (_other20) + (_t39)) - ((_other00) * (_other21) + (_t39))) / _t108;
        double _t145 = _t81 / _t108;
        dest.putDouble(destOffset, ((_t128) * (_t129) + (((_t130) * (_t131) + (_t132 * _t133)))));
        dest.putDouble(destOffset + 8, ((_t140) * (_t129) + (((_t141) * (_t131) + (_t142 * _t133)))));
        dest.putDouble(destOffset + 16, ((_t143) * (_t129) + (((_t144) * (_t131) + (_t145 * _t133)))));
        dest.putDouble(destOffset + 24, ((_t128) * (_t134) + (((_t130) * (_t135) + (_t132 * _t136)))));
        dest.putDouble(destOffset + 32, ((_t140) * (_t134) + (((_t141) * (_t135) + (_t142 * _t136)))));
        dest.putDouble(destOffset + 40, ((_t143) * (_t134) + (((_t144) * (_t135) + (_t145 * _t136)))));
        dest.putDouble(destOffset + 48, ((_t128) * (_t137) + (((_t130) * (_t138) + (_t132 * _t139)))));
        dest.putDouble(destOffset + 56, ((_t140) * (_t137) + (((_t141) * (_t138) + (_t142 * _t139)))));
        dest.putDouble(destOffset + 64, ((_t143) * (_t137) + (((_t144) * (_t138) + (_t145 * _t139)))));
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        return invertProduct_degenerate_api_fma_sdbea5e19_10(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Part 1 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_1(double _other10, double _other20, double _other11, double _other21, double _other12, double _other22) {
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t27 = _other11 * _t1;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t30 = _other20 * _t2;
        double _t63 = -(_t27 * _t30);
        return java.lang.Math.fma((_other10 * _t1), (_other21 * _t2), _t63) - java.lang.Math.fma(_t27, _t30, _t63);
    }

    /** Part 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_2(double _other10, double _other20, double _other11, double _other21, double _other12, double _other22) {
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t25 = _other12 * _t1;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t29 = _other21 * _t2;
        double _t64 = -_t25 * _t29;
        return java.lang.Math.fma((_other11 * _t1), (_other22 * _t2), _t64) - java.lang.Math.fma(_t25, _t29, _t64);
    }

    /** Part 3 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_3(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t123, double _t124) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t25 = _other12 * _t1;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t30 = _other20 * _t2;
        double _t65 = -_t25 * _t30;
        return java.lang.Math.fma(_t123, (_other02 * _t0), java.lang.Math.fma(_t124, (_other00 * _t0), (java.lang.Math.fma(_t25, _t30, _t65) - java.lang.Math.fma((_other10 * _t1), (_other22 * _t2), _t65)) * (_other01 * _t0)));
    }

    /** Part 4 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_4(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t26 = _other02 * _t0;
        double _t27 = _other11 * _t1;
        double _t62 = -_t26 * _t27;
        return (java.lang.Math.fma((_other01 * _t0), (_other12 * _t1), _t62) - java.lang.Math.fma(_t26, _t27, _t62)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_5(double _self10, double _self20, double _self11, double _self21, double _self12, double _self22) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t35 = _self11 * _t3;
        double _t36 = _self20 * _t4;
        double _t66 = -_t35 * _t36;
        return java.lang.Math.fma((_self10 * _t3), (_self21 * _t4), _t66) - java.lang.Math.fma(_t35, _t36, _t66);
    }

    /** Part 6 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_6(double _self10, double _self20, double _self11, double _self21, double _self12, double _self22) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t34 = _self21 * _t4;
        double _t39 = _self12 * _t3;
        double _t67 = -_t39 * _t34;
        return java.lang.Math.fma((_self11 * _t3), (_self22 * _t4), _t67) - java.lang.Math.fma(_t39, _t34, _t67);
    }

    /** Part 7 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_7(double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t126, double _t127) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t36 = _self20 * _t4;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t39 = _self12 * _t3;
        double _t68 = -(_t39 * _t36);
        return java.lang.Math.fma(_t126, (_self02 * _t5), java.lang.Math.fma(_t127, (_self00 * _t5), (java.lang.Math.fma(_t39, _t36, _t68) - java.lang.Math.fma((_self10 * _t3), (_self22 * _t4), _t68)) * (_self01 * _t5)));
    }

    /** Part 8 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_8(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t24 = _other01 * _t0;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t31 = _other22 * _t2;
        double _t69 = -_t24 * _t31;
        return (java.lang.Math.fma((_other02 * _t0), (_other21 * _t2), _t69) - java.lang.Math.fma(_t24, _t31, _t69)) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 9 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_9(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other01, double _other02, double _t124, double _t166, double _t186, double _t126, double _t127, double _t167, double _t188) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t33 = _self10 * _t3;
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t38 = _self22 * _t4;
        double _t70 = -_t33 * _t38;
        double _t189 = (java.lang.Math.fma((_self12 * _t3), (_self20 * _t4), _t70) - java.lang.Math.fma(_t33, _t38, _t70)) * _t5 / _t167;
        dest.putDouble(destOffset, java.lang.Math.fma(_t186, (_t126 * _t5 / _t167), java.lang.Math.fma(_t188, _t189, (_t124 * (unitScale(_other00, _other01, _other02)) / _t166) * (_t127 * _t5 / _t167))));
        return _t189;
    }

    /** Part of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_fma_sdbea5e19_528(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t123, double _t124, double _t166, double _t186, double _t126, double _t127, double _t167, double _t188, double _t189) {
        double _t198 = invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t199 = invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t200 = invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_3(dest, destOffset, _self00, _self01, _self02, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t126, _t127, _t167, _t189, _t198, _t199);
        double _t201 = invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t202 = invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_5(dest, destOffset, _self00, _self01, _self02, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t166, _t126, _t127, _t167, _t189, _t201);
        invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_6(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t123, _t124, _t166, _t186, _t167, _t188, _t198, _t199, _t200, _t201, _t202);
        invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_7(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t123, _t124, _t166, _t186, _t167, _t188, _t198, _t199, _t200, _t201, _t202);
    }

    /** Part 1 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_1(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t25 = _other12 * _t1;
        double _t32 = _other00 * _t0;
        double _t77 = -_t32 * _t25;
        return (java.lang.Math.fma((_other02 * _t0), (_other10 * _t1), _t77) - java.lang.Math.fma(_t32, _t25, _t77)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 2 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_2(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t26 = _other02 * _t0;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t30 = _other20 * _t2;
        double _t78 = -_t26 * _t30;
        return (java.lang.Math.fma((_other00 * _t0), (_other22 * _t2), _t78) - java.lang.Math.fma(_t26, _t30, _t78)) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_3(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166, double _t126, double _t127, double _t167, double _t189, double _t198, double _t199) {
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t28 = _other10 * _t1;
        double _t31 = _other22 * _t2;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t79 = -(_t28 * _t31);
        double _t200 = (java.lang.Math.fma((_other12 * _t1), (_other20 * _t2), _t79) - java.lang.Math.fma(_t28, _t31, _t79)) * (unitScale(_other00, _other01, _other02)) / _t166;
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t198, (_t126 * _t5 / _t167), java.lang.Math.fma(_t199, _t189, _t200 * (_t127 * _t5 / _t167))));
        return _t200;
    }

    /** Part 4 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_4(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t24 = _other01 * _t0;
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t28 = _other10 * _t1;
        double _t80 = -_t24 * _t28;
        return (java.lang.Math.fma((_other00 * _t0), (_other11 * _t1), _t80) - java.lang.Math.fma(_t24, _t28, _t80)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_5(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t123, double _t166, double _t126, double _t127, double _t167, double _t189, double _t201) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t29 = _other21 * _t2;
        double _t32 = _other00 * _t0;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t81 = -_t32 * _t29;
        double _t202 = (java.lang.Math.fma((_other01 * _t0), (_other20 * _t2), _t81) - java.lang.Math.fma(_t32, _t29, _t81)) * (unitScale(_other10, _other11, _other12)) / _t166;
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t201, (_t126 * _t5 / _t167), java.lang.Math.fma(_t202, _t189, (_t123 * _t0 / _t166) * (_t127 * _t5 / _t167))));
        return _t202;
    }

    /** Part 6 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_6(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other01, double _other02, double _t123, double _t124, double _t166, double _t186, double _t167, double _t188, double _t198, double _t199, double _t200, double _t201, double _t202) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t34 = _self21 * _t4;
        double _t36 = _self20 * _t4;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t37 = _self02 * _t5;
        double _t38 = _self22 * _t4;
        double _t40 = _self00 * _t5;
        double _t41 = _self01 * _t5;
        double _t71 = -(_t40 * _t34);
        double _t192 = (java.lang.Math.fma(_t41, _t36, _t71) - java.lang.Math.fma(_t40, _t34, _t71)) * _t3 / _t167;
        double _t72 = -(_t37 * _t36);
        double _t193 = (java.lang.Math.fma(_t40, _t38, _t72) - java.lang.Math.fma(_t37, _t36, _t72)) * _t3 / _t167;
        double _t73 = -(_t41 * _t38);
        double _t194 = (java.lang.Math.fma(_t37, _t34, _t73) - java.lang.Math.fma(_t41, _t38, _t73)) * _t3 / _t167;
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t186, _t192, java.lang.Math.fma(_t188, _t193, (_t124 * _t0 / _t166) * _t194)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t198, _t192, java.lang.Math.fma(_t199, _t193, _t200 * _t194)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t201, _t192, java.lang.Math.fma(_t202, _t193, (_t123 * _t0 / _t166) * _t194)));
    }

    /** Part 7 of {@code invertProduct_degenerate_api_fma_sdbea5e19_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_fma_sdbea5e19_528_s7295a774_7(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other01, double _other02, double _t123, double _t124, double _t166, double _t186, double _t167, double _t188, double _t198, double _t199, double _t200, double _t201, double _t202) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t33 = _self10 * _t3;
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t35 = _self11 * _t3;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t37 = _self02 * _t5;
        double _t39 = _self12 * _t3;
        double _t40 = _self00 * _t5;
        double _t41 = _self01 * _t5;
        double _t74 = _t41 * -_t33;
        double _t195 = (java.lang.Math.fma(_t40, _t35, _t74) - java.lang.Math.fma(_t41, _t33, _t74)) * _t4 / _t167;
        double _t75 = _t40 * -_t39;
        double _t196 = (java.lang.Math.fma(_t37, _t33, _t75) - java.lang.Math.fma(_t40, _t39, _t75)) * _t4 / _t167;
        double _t76 = _t37 * -_t35;
        double _t197 = (java.lang.Math.fma(_t41, _t39, _t76) - java.lang.Math.fma(_t37, _t35, _t76)) * _t4 / _t167;
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_t186, _t195, java.lang.Math.fma(_t188, _t196, (_t124 * _t0 / _t166) * _t197)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t198, _t195, java.lang.Math.fma(_t199, _t196, _t200 * _t197)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_t201, _t195, java.lang.Math.fma(_t202, _t196, (_t123 * _t0 / _t166) * _t197)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma_sdbea5e19_10(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        double _t123 = invertProduct_degenerate_api_fma_sdbea5e19_1(_other10, _other20, _other11, _other21, _other12, _other22);
        double _t124 = invertProduct_degenerate_api_fma_sdbea5e19_2(_other10, _other20, _other11, _other21, _other12, _other22);
        double _t166 = invertProduct_degenerate_api_fma_sdbea5e19_3(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t124);
        double _t186 = invertProduct_degenerate_api_fma_sdbea5e19_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t126 = invertProduct_degenerate_api_fma_sdbea5e19_5(_self10, _self20, _self11, _self21, _self12, _self22);
        double _t127 = invertProduct_degenerate_api_fma_sdbea5e19_6(_self10, _self20, _self11, _self21, _self12, _self22);
        double _t167 = invertProduct_degenerate_api_fma_sdbea5e19_7(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t126, _t127);
        double _t188 = invertProduct_degenerate_api_fma_sdbea5e19_8(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        invertProduct_degenerate_api_fma_sdbea5e19_528(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t124, _t166, _t186, _t126, _t127, _t167, _t188, invertProduct_degenerate_api_fma_sdbea5e19_9(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t124, _t166, _t186, _t126, _t127, _t167, _t188));
        return dest;
    }

    /** {@code invertProduct_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t1 = unitScale(_other10, _other11, _other12);
        return invertProduct_degenerate_api_mulAdd_s913a584c_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other21, _other22, _t0, _t1, unitScale(_other20, _other21, _other22), unitScale(_self10, _self11, _self12), unitScale(_self20, _self21, _self22), unitScale(_self00, _self01, _self02), _other01 * _t0, _other12 * _t1, _other02 * _t0, _other11 * _t1);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd_s913a584c_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other21, double _other22, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t24, double _t25, double _t26, double _t27) {
        double _t28 = _other10 * _t1;
        double _t29 = _other21 * _t2;
        double _t30 = _other20 * _t2;
        double _t31 = _other22 * _t2;
        double _t32 = _other00 * _t0;
        double _t33 = _self10 * _t3;
        double _t34 = _self21 * _t4;
        double _t35 = _self11 * _t3;
        double _t36 = _self20 * _t4;
        double _t37 = _self02 * _t5;
        double _t38 = _self22 * _t4;
        double _t39 = _self12 * _t3;
        double _t40 = _self00 * _t5;
        double _t41 = _self01 * _t5;
        double _t63 = -(_t27 * _t30);
        return invertProduct_degenerate_api_mulAdd_s913a584c_2(dest, destOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t33, _t34, _t35, _t36, _t37, _t38, _t39, _t40, _t41, -_t26 * _t27, -_t25 * _t29, -_t25 * _t30, -_t35 * _t36, -_t39 * _t34, -(_t39 * _t36), -_t24 * _t31, -_t33 * _t38, -(_t40 * _t34), -(_t37 * _t36), -(_t41 * _t38), _t41 * -_t33, _t40 * -_t39, _t37 * -_t35, -_t32 * _t25, -_t26 * _t30, -(_t28 * _t31), -_t24 * _t28, -_t32 * _t29, ((_t28) * (_t29) + (_t63)) - ((_t27) * (_t30) + (_t63)));
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd_s913a584c_2(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40, double _t41, double _t62, double _t64, double _t65, double _t66, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78, double _t79, double _t80, double _t81, double _t123) {
        double _t124 = ((_t27) * (_t31) + (_t64)) - ((_t25) * (_t29) + (_t64));
        double _t126 = ((_t33) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66));
        double _t127 = ((_t35) * (_t38) + (_t67)) - ((_t39) * (_t34) + (_t67));
        double _t166 = ((_t123) * (_t26) + (((_t124) * (_t32) + ((((_t25) * (_t30) + (_t65)) - ((_t28) * (_t31) + (_t65))) * _t24))));
        double _t167 = ((_t126) * (_t37) + (((_t127) * (_t40) + ((((_t39) * (_t36) + (_t68)) - ((_t33) * (_t38) + (_t68))) * _t41))));
        return invertProduct_degenerate_api_mulAdd_s913a584c_3(dest, destOffset, _t0, _t1, _t2, _t3, _t4, _t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t33, _t34, _t35, _t36, _t37, _t38, _t39, _t40, _t41, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t79, _t80, _t81, _t123, _t166, _t167, (((_t24) * (_t25) + (_t62)) - ((_t26) * (_t27) + (_t62))) * _t2 / _t166, _t126 * _t5 / _t167, (((_t26) * (_t29) + (_t69)) - ((_t24) * (_t31) + (_t69))) * _t1 / _t166, (((_t39) * (_t36) + (_t70)) - ((_t33) * (_t38) + (_t70))) * _t5 / _t167, _t124 * _t0 / _t166, _t127 * _t5 / _t167);
    }

    /** Piece 4 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd_s913a584c_3(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40, double _t41, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78, double _t79, double _t80, double _t81, double _t123, double _t166, double _t167, double _t186, double _t187, double _t188, double _t189, double _t190, double _t191) {
        dest.putDouble(destOffset, ((_t186) * (_t187) + (((_t188) * (_t189) + (_t190 * _t191)))));
        return invertProduct_degenerate_api_mulAdd_s913a584c_4(dest, destOffset, _t186, _t187, _t188, _t189, _t190, _t191, (((_t41) * (_t36) + (_t71)) - ((_t40) * (_t34) + (_t71))) * _t3 / _t167, (((_t40) * (_t38) + (_t72)) - ((_t37) * (_t36) + (_t72))) * _t3 / _t167, (((_t37) * (_t34) + (_t73)) - ((_t41) * (_t38) + (_t73))) * _t3 / _t167, (((_t40) * (_t35) + (_t74)) - ((_t41) * (_t33) + (_t74))) * _t4 / _t167, (((_t37) * (_t33) + (_t75)) - ((_t40) * (_t39) + (_t75))) * _t4 / _t167, (((_t41) * (_t39) + (_t76)) - ((_t37) * (_t35) + (_t76))) * _t4 / _t167, (((_t26) * (_t28) + (_t77)) - ((_t32) * (_t25) + (_t77))) * _t2 / _t166, (((_t32) * (_t31) + (_t78)) - ((_t26) * (_t30) + (_t78))) * _t1 / _t166, (((_t25) * (_t30) + (_t79)) - ((_t28) * (_t31) + (_t79))) * _t0 / _t166, (((_t32) * (_t27) + (_t80)) - ((_t24) * (_t28) + (_t80))) * _t2 / _t166, (((_t24) * (_t30) + (_t81)) - ((_t32) * (_t29) + (_t81))) * _t1 / _t166, _t123 * _t0 / _t166);
    }

    /** Piece 5 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd_s913a584c_4(java.nio.ByteBuffer dest, int destOffset, double _t186, double _t187, double _t188, double _t189, double _t190, double _t191, double _t192, double _t193, double _t194, double _t195, double _t196, double _t197, double _t198, double _t199, double _t200, double _t201, double _t202, double _t203) {
        dest.putDouble(destOffset + 8, ((_t198) * (_t187) + (((_t199) * (_t189) + (_t200 * _t191)))));
        dest.putDouble(destOffset + 16, ((_t201) * (_t187) + (((_t202) * (_t189) + (_t203 * _t191)))));
        dest.putDouble(destOffset + 24, ((_t186) * (_t192) + (((_t188) * (_t193) + (_t190 * _t194)))));
        dest.putDouble(destOffset + 32, ((_t198) * (_t192) + (((_t199) * (_t193) + (_t200 * _t194)))));
        dest.putDouble(destOffset + 40, ((_t201) * (_t192) + (((_t202) * (_t193) + (_t203 * _t194)))));
        dest.putDouble(destOffset + 48, ((_t186) * (_t195) + (((_t188) * (_t196) + (_t190 * _t197)))));
        dest.putDouble(destOffset + 56, ((_t198) * (_t195) + (((_t199) * (_t196) + (_t200 * _t197)))));
        dest.putDouble(destOffset + 64, ((_t201) * (_t195) + (((_t202) * (_t196) + (_t203 * _t197)))));
        return dest;
    }

    public static java.nio.ByteBuffer normal_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_api_fma(dest, destOffset, src, srcOffset);
        return normal_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code normal_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normal_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t10 = -_self12 * _self21;
        double _t11 = -(_self11 * _self20);
        double _t12 = -_self12 * _self20;
        double _t26 = java.lang.Math.fma(_self11, _self22, _t10) - java.lang.Math.fma(_self12, _self21, _t10);
        double _t27 = java.lang.Math.fma(_self10, _self21, _t11) - java.lang.Math.fma(_self11, _self20, _t11);
        double _t33 = java.lang.Math.fma(_self02, _t27, java.lang.Math.fma(_self00, _t26, _self01 * (java.lang.Math.fma(_self12, _self20, _t12) - java.lang.Math.fma(_self10, _self22, _t12))));
        if (!(java.lang.Math.abs(_t33) > 2.2250738585072014E-308 && java.lang.Math.abs(_t33) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        return normal_api_fma_sa6461d64_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self10 * _self22, -(_self01 * _self22), -_self02 * _self20, -_self00 * _self21, -_self02 * _self11, -_self00 * _self12, _self01 * -_self10, _t26, _t27, 1.0 / _t33);
    }

    /** Piece 2 of {@code normal_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_api_fma_sa6461d64_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t33_inv) {
        dest.putDouble(destOffset, _t26 * _t33_inv);
        dest.putDouble(destOffset + 8, (java.lang.Math.fma(_self02, _self21, _t14) - java.lang.Math.fma(_self01, _self22, _t14)) * _t33_inv);
        dest.putDouble(destOffset + 16, (java.lang.Math.fma(_self01, _self12, _t17) - java.lang.Math.fma(_self02, _self11, _t17)) * _t33_inv);
        dest.putDouble(destOffset + 24, (java.lang.Math.fma(_self12, _self20, _t13) - java.lang.Math.fma(_self10, _self22, _t13)) * _t33_inv);
        dest.putDouble(destOffset + 32, (java.lang.Math.fma(_self00, _self22, _t15) - java.lang.Math.fma(_self02, _self20, _t15)) * _t33_inv);
        dest.putDouble(destOffset + 40, (java.lang.Math.fma(_self02, _self10, _t18) - java.lang.Math.fma(_self00, _self12, _t18)) * _t33_inv);
        dest.putDouble(destOffset + 48, _t27 * _t33_inv);
        dest.putDouble(destOffset + 56, (java.lang.Math.fma(_self01, _self20, _t16) - java.lang.Math.fma(_self00, _self21, _t16)) * _t33_inv);
        dest.putDouble(destOffset + 64, (java.lang.Math.fma(_self00, _self11, _t19) - java.lang.Math.fma(_self01, _self10, _t19)) * _t33_inv);
        return dest;
    }

    /** {@code normal_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normal_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t10 = -_self12 * _self21;
        double _t11 = -(_self11 * _self20);
        double _t12 = -_self12 * _self20;
        double _t26 = ((_self11) * (_self22) + (_t10)) - ((_self12) * (_self21) + (_t10));
        double _t27 = ((_self10) * (_self21) + (_t11)) - ((_self11) * (_self20) + (_t11));
        double _t33 = ((_self02) * (_t27) + (((_self00) * (_t26) + (_self01 * (((_self12) * (_self20) + (_t12)) - ((_self10) * (_self22) + (_t12)))))));
        if (!(java.lang.Math.abs(_t33) > 2.2250738585072014E-308 && java.lang.Math.abs(_t33) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        double _t33_inv = 1.0 / _t33;
        dest.putDouble(destOffset, _t26 * _t33_inv);
        return normal_api_mulAdd_sddc059ab_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self10 * _self22, -(_self01 * _self22), -_self02 * _self20, -_self00 * _self21, -_self02 * _self11, -_self00 * _self12, _self01 * -_self10, _t27, _t33_inv);
    }

    /** Piece 2 of {@code normal_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_api_mulAdd_sddc059ab_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t27, double _t33_inv) {
        dest.putDouble(destOffset + 8, (((_self02) * (_self21) + (_t14)) - ((_self01) * (_self22) + (_t14))) * _t33_inv);
        dest.putDouble(destOffset + 16, (((_self01) * (_self12) + (_t17)) - ((_self02) * (_self11) + (_t17))) * _t33_inv);
        dest.putDouble(destOffset + 24, (((_self12) * (_self20) + (_t13)) - ((_self10) * (_self22) + (_t13))) * _t33_inv);
        dest.putDouble(destOffset + 32, (((_self00) * (_self22) + (_t15)) - ((_self02) * (_self20) + (_t15))) * _t33_inv);
        dest.putDouble(destOffset + 40, (((_self02) * (_self10) + (_t18)) - ((_self00) * (_self12) + (_t18))) * _t33_inv);
        dest.putDouble(destOffset + 48, _t27 * _t33_inv);
        dest.putDouble(destOffset + 56, (((_self01) * (_self20) + (_t16)) - ((_self00) * (_self21) + (_t16))) * _t33_inv);
        dest.putDouble(destOffset + 64, (((_self00) * (_self11) + (_t19)) - ((_self01) * (_self10) + (_t19))) * _t33_inv);
        return dest;
    }

    public static java.nio.ByteBuffer normal_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return normal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer normal_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return normal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code normal_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t12 = _self11 * _t0;
        double _t13 = _self22 * _t1;
        double _t14 = _self12 * _t0;
        double _t15 = _self21 * _t1;
        double _t16 = _self10 * _t0;
        double _t17 = _self20 * _t1;
        double _t18 = _self02 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self01 * _t2;
        double _t31 = -_t14 * _t15;
        return normal_degenerate_api_fma_sc11f5cfb_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -(_t12 * _t17), -_t14 * _t17, -_t16 * _t13, -(_t20 * _t13), -_t18 * _t17, -_t19 * _t15, -_t18 * _t12, -_t19 * _t14, _t20 * -_t16, java.lang.Math.fma(_t12, _t13, _t31) - java.lang.Math.fma(_t14, _t15, _t31));
    }

    /** Piece 2 of {@code normal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_fma_sc11f5cfb_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _t2, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40, double _t47) {
        double _t48 = java.lang.Math.fma(_t16, _t15, _t32) - java.lang.Math.fma(_t12, _t17, _t32);
        double _t53_inv = 1.0 / java.lang.Math.fma(_t48, _t18, java.lang.Math.fma(_t47, _t19, (java.lang.Math.fma(_t14, _t17, _t33) - java.lang.Math.fma(_t16, _t13, _t33)) * _t20));
        double _sp2 = _t1 * _t53_inv;
        double _sp1 = _t0 * _t53_inv;
        double _sp0 = _t2 * _t53_inv;
        dest.putDouble(destOffset, _t47 * _sp0);
        dest.putDouble(destOffset + 8, (java.lang.Math.fma(_t18, _t15, _t35) - java.lang.Math.fma(_t20, _t13, _t35)) * _sp1);
        dest.putDouble(destOffset + 16, (java.lang.Math.fma(_t20, _t14, _t38) - java.lang.Math.fma(_t18, _t12, _t38)) * _sp2);
        dest.putDouble(destOffset + 24, (java.lang.Math.fma(_t14, _t17, _t34) - java.lang.Math.fma(_t16, _t13, _t34)) * _sp0);
        dest.putDouble(destOffset + 32, (java.lang.Math.fma(_t19, _t13, _t36) - java.lang.Math.fma(_t18, _t17, _t36)) * _sp1);
        dest.putDouble(destOffset + 40, (java.lang.Math.fma(_t18, _t16, _t39) - java.lang.Math.fma(_t19, _t14, _t39)) * _sp2);
        dest.putDouble(destOffset + 48, _t48 * _sp0);
        return normal_degenerate_api_fma_sc11f5cfb_2(dest, destOffset, _t12, _t15, _t16, _t17, _t19, _t20, _t37, _t40, _sp2, _sp1);
    }

    /** Piece 3 of {@code normal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_fma_sc11f5cfb_2(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t15, double _t16, double _t17, double _t19, double _t20, double _t37, double _t40, double _sp2, double _sp1) {
        dest.putDouble(destOffset + 56, (java.lang.Math.fma(_t20, _t17, _t37) - java.lang.Math.fma(_t19, _t15, _t37)) * _sp1);
        dest.putDouble(destOffset + 64, (java.lang.Math.fma(_t19, _t12, _t40) - java.lang.Math.fma(_t20, _t16, _t40)) * _sp2);
        return dest;
    }

    /** {@code normal_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t12 = _self11 * _t0;
        double _t13 = _self22 * _t1;
        double _t14 = _self12 * _t0;
        double _t15 = _self21 * _t1;
        double _t16 = _self10 * _t0;
        double _t17 = _self20 * _t1;
        double _t18 = _self02 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self01 * _t2;
        double _t31 = -_t14 * _t15;
        return normal_degenerate_api_mulAdd_s55a86ee2_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -(_t12 * _t17), -_t14 * _t17, -_t16 * _t13, -(_t20 * _t13), -_t18 * _t17, -_t19 * _t15, -_t18 * _t12, -_t19 * _t14, _t20 * -_t16, ((_t12) * (_t13) + (_t31)) - ((_t14) * (_t15) + (_t31)));
    }

    /** Piece 2 of {@code normal_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_mulAdd_s55a86ee2_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _t2, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40, double _t47) {
        double _t48 = ((_t16) * (_t15) + (_t32)) - ((_t12) * (_t17) + (_t32));
        double _t53_inv = 1.0 / ((_t48) * (_t18) + (((_t47) * (_t19) + ((((_t14) * (_t17) + (_t33)) - ((_t16) * (_t13) + (_t33))) * _t20))));
        double _sp2 = _t1 * _t53_inv;
        double _sp1 = _t0 * _t53_inv;
        double _sp0 = _t2 * _t53_inv;
        dest.putDouble(destOffset, _t47 * _sp0);
        dest.putDouble(destOffset + 8, (((_t18) * (_t15) + (_t35)) - ((_t20) * (_t13) + (_t35))) * _sp1);
        dest.putDouble(destOffset + 16, (((_t20) * (_t14) + (_t38)) - ((_t18) * (_t12) + (_t38))) * _sp2);
        dest.putDouble(destOffset + 24, (((_t14) * (_t17) + (_t34)) - ((_t16) * (_t13) + (_t34))) * _sp0);
        dest.putDouble(destOffset + 32, (((_t19) * (_t13) + (_t36)) - ((_t18) * (_t17) + (_t36))) * _sp1);
        dest.putDouble(destOffset + 40, (((_t18) * (_t16) + (_t39)) - ((_t19) * (_t14) + (_t39))) * _sp2);
        dest.putDouble(destOffset + 48, _t48 * _sp0);
        dest.putDouble(destOffset + 56, (((_t20) * (_t17) + (_t37)) - ((_t19) * (_t15) + (_t37))) * _sp1);
        dest.putDouble(destOffset + 64, (((_t19) * (_t12) + (_t40)) - ((_t20) * (_t16) + (_t40))) * _sp2);
        return dest;
    }

    public static double trace_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3x3OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static double trace_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getDouble(srcOffset + 64) + (src.getDouble(srcOffset) + src.getDouble(srcOffset + 32));
    }

    public static java.nio.ByteBuffer transpose_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer transpose_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, _self10);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self12);
        dest.putDouble(destOffset + 48, _self20);
        dest.putDouble(destOffset + 56, _self21);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            double _eother = other.getDouble(otherOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _eother + _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, scalar * _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, -_eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            double _eother = other.getDouble(otherOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _eself - _eother);
        }
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _ev = v.getDouble(vOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _ev);
        }
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m10 = m.getDouble(mOffset + 8);
        double _m01 = m.getDouble(mOffset + 16);
        double _m11 = m.getDouble(mOffset + 24);
        dest.putDouble(destOffset, _m00);
        dest.putDouble(destOffset + 8, _m10);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _m01);
        dest.putDouble(destOffset + 32, _m11);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x3OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m10 = m.getDouble(mOffset + 8);
        double _m01 = m.getDouble(mOffset + 16);
        double _m11 = m.getDouble(mOffset + 24);
        double _m02 = m.getDouble(mOffset + 32);
        double _m12 = m.getDouble(mOffset + 40);
        dest.putDouble(destOffset, _m00);
        dest.putDouble(destOffset + 8, _m10);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _m01);
        dest.putDouble(destOffset + 32, _m11);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, _m02);
        dest.putDouble(destOffset + 56, _m12);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x3OpsKernelsAddress.setMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m01 = m.getDouble(mOffset + 8);
        double _m02 = m.getDouble(mOffset + 16);
        double _m10 = m.getDouble(mOffset + 32);
        double _m11 = m.getDouble(mOffset + 40);
        double _m12 = m.getDouble(mOffset + 48);
        double _m20 = m.getDouble(mOffset + 64);
        double _m21 = m.getDouble(mOffset + 72);
        double _m22 = m.getDouble(mOffset + 80);
        dest.putDouble(destOffset, _m00);
        dest.putDouble(destOffset + 8, _m10);
        dest.putDouble(destOffset + 16, _m20);
        dest.putDouble(destOffset + 24, _m01);
        dest.putDouble(destOffset + 32, _m11);
        dest.putDouble(destOffset + 40, _m21);
        dest.putDouble(destOffset + 48, _m02);
        dest.putDouble(destOffset + 56, _m12);
        dest.putDouble(destOffset + 64, _m22);
        return dest;
    }

    public static java.nio.ByteBuffer setMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x3OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            int _lom = _l * 4;
            double _em0 = m.getDouble(mOffset + _lom * 8);
            double _em1 = m.getDouble(mOffset + (_lom + 1) * 8);
            double _em2 = m.getDouble(mOffset + (_lom + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, _em0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _em1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _em2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double tX, double tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double tX, double tY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, tX);
        dest.putDouble(destOffset + 56, tY);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Double3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        double _tx = t.getDouble(tOffset);
        double _ty = t.getDouble(tOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, _tx);
        dest.putDouble(destOffset + 56, _ty);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromRigid_unsafe(java.nio.ByteBuffer dest, int destOffset, double rRX, double rRY, double rRZ, double rRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromRigid_api(java.nio.ByteBuffer dest, int destOffset, double rRX, double rRY, double rRZ, double rRW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromRigid_api_mulAdd(dest, destOffset, rRX, rRY, rRZ, rRW);
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        dest.putDouble(destOffset, java.lang.Math.fma(-2.0, java.lang.Math.fma(rRY, rRY, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(rRX, rRY, _t1));
        dest.putDouble(destOffset + 16, 2.0 * java.lang.Math.fma(rRX, rRZ, -_t2));
        dest.putDouble(destOffset + 24, 2.0 * java.lang.Math.fma(rRX, rRY, -_t1));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(-2.0, java.lang.Math.fma(rRX, rRX, _t0), 1.0));
        dest.putDouble(destOffset + 40, 2.0 * java.lang.Math.fma(rRX, rRW, rRY * rRZ));
        dest.putDouble(destOffset + 48, 2.0 * java.lang.Math.fma(rRX, rRZ, _t2));
        dest.putDouble(destOffset + 56, 2.0 * java.lang.Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(-2.0, java.lang.Math.fma(rRX, rRX, rRY * rRY), 1.0));
        return dest;
    }

    /** {@code makeFromRigid_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeFromRigid_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        dest.putDouble(destOffset, ((1.0) - (2.0) * (((rRY) * (rRY) + (_t0)))));
        dest.putDouble(destOffset + 8, 2.0 * ((rRX) * (rRY) + (_t1)));
        dest.putDouble(destOffset + 16, 2.0 * ((rRX) * (rRZ) - (_t2)));
        dest.putDouble(destOffset + 24, 2.0 * ((rRX) * (rRY) - (_t1)));
        dest.putDouble(destOffset + 32, ((1.0) - (2.0) * (((rRX) * (rRX) + (_t0)))));
        dest.putDouble(destOffset + 40, 2.0 * ((rRX) * (rRW) + (rRY * rRZ)));
        dest.putDouble(destOffset + 48, 2.0 * ((rRX) * (rRZ) + (_t2)));
        dest.putDouble(destOffset + 56, 2.0 * ((rRY) * (rRZ) - (rRX * rRW)));
        dest.putDouble(destOffset + 64, ((1.0) - (2.0) * (((rRX) * (rRX) + (rRY * rRY)))));
        return dest;
    }

    public static java.nio.ByteBuffer makeFromTransform_unsafe(java.nio.ByteBuffer dest, int destOffset, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromTransform_api(java.nio.ByteBuffer dest, int destOffset, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromTransform_api_mulAdd(dest, destOffset, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        dest.putDouble(destOffset, java.lang.Math.fma(-java.lang.Math.fma(tRY, tRY, _t3), _t0, tSX));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(tRX, tRY, _t4) * _t0);
        dest.putDouble(destOffset + 16, java.lang.Math.fma(tRX, tRZ, -_t5) * _t0);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(tRX, tRY, -_t4) * _t1);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(-java.lang.Math.fma(tRX, tRX, _t3), _t1, tSY));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(tRX, tRZ, _t5) * _t2);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        dest.putDouble(destOffset + 64, java.lang.Math.fma(-java.lang.Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        return dest;
    }

    /** {@code makeFromTransform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeFromTransform_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        dest.putDouble(destOffset, ((tSX) - ((tRY) * (tRY) + (_t3)) * (_t0)));
        dest.putDouble(destOffset + 8, ((tRX) * (tRY) + (_t4)) * _t0);
        dest.putDouble(destOffset + 16, ((tRX) * (tRZ) - (_t5)) * _t0);
        dest.putDouble(destOffset + 24, ((tRX) * (tRY) - (_t4)) * _t1);
        dest.putDouble(destOffset + 32, ((tSY) - ((tRX) * (tRX) + (_t3)) * (_t1)));
        dest.putDouble(destOffset + 40, ((tRX) * (tRW) + (tRY * tRZ)) * _t1);
        dest.putDouble(destOffset + 48, ((tRX) * (tRZ) + (_t5)) * _t2);
        dest.putDouble(destOffset + 56, ((tRY) * (tRZ) - (tRX * tRW)) * _t2);
        dest.putDouble(destOffset + 64, ((tSZ) - ((tRX) * (tRX) + (tRY * tRY)) * (_t2)));
        return dest;
    }

    public static java.nio.ByteBuffer to2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self01);
        dest.putDouble(destOffset + 24, _self11);
        return dest;
    }

    public static java.nio.ByteBuffer to2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _loself = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _loself * 8);
            double _eself1 = src.getDouble(srcOffset + (_loself + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer to3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.to3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _self10);
        dest.putDouble(destOffset + 40, _self11);
        dest.putDouble(destOffset + 48, _self12);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _self20);
        dest.putDouble(destOffset + 72, _self21);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer to4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _self01);
        dest.putDouble(destOffset + 40, _self11);
        dest.putDouble(destOffset + 48, _self21);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _self02);
        dest.putDouble(destOffset + 72, _self12);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, 0.0);
        dest.putDouble(destOffset + 96, 0.0);
        dest.putDouble(destOffset + 104, 0.0);
        dest.putDouble(destOffset + 112, 0.0);
        dest.putDouble(destOffset + 120, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = 1.0 - _self00;
        double _t13 = _self22 + (_self00 + _self11);
        double _t14 = 1.0 + _t13;
        double _t15 = _self00 + (1.0 - _self11 - _self22);
        double _t16 = _self11 + (_t1 - _self22);
        double _t17 = _self22 + (_t1 - _self11);
        return toDualQuat_api_s7675600_1(dest, destOffset, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t13, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code toDualQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toDualQuat_api_s7675600_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self11, double _self22, double _t3, double _t5, double _t6, double _t7, double _t8, double _t9, double _t13, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t13 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t3);
            dest.putDouble(destOffset + 8, _sp0 * _t7);
            dest.putDouble(destOffset + 16, _sp0 * _t9);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.putDouble(destOffset + 8, _sp3 * _t5);
                dest.putDouble(destOffset + 16, _sp3 * _t6);
                dest.putDouble(destOffset + 24, _sp3 * _t3);
            } else {
                if (_self11 > _self22) {
                    dest.putDouble(destOffset, _sp1 * _t5);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.putDouble(destOffset + 16, _sp1 * _t8);
                    dest.putDouble(destOffset + 24, _sp1 * _t7);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t6);
                    dest.putDouble(destOffset + 8, _sp2 * _t8);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.putDouble(destOffset + 24, _sp2 * _t9);
                }
            }
        }
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_api_fma(dest, destOffset, src, srcOffset);
        return toRigid_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toRigid_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _ct0 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _ct1 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _ct2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        toRigid_api_fma_se91a3fa7_515(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _ct0, _ct1, _ct2);
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        return dest;
    }

    /** Part of {@code toRigid_api_fma}, split to fit the inline budget; reached only through it. */
    private static void toRigid_api_fma_se91a3fa7_515(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _ct0, double _ct1, double _ct2) {
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _t18 = _self10 * _t17;
        double _t19 = _self22 * _t16;
        double _t20 = _self12 * _t16;
        double _t21 = _self20 * _t17;
        double _t23 = _self21 * _t15;
        double _t24 = _self11 * _t15;
        double _t26 = _self00 * _t17;
        double _t47, _t48, _t49;
        if (java.lang.Math.fma(-java.lang.Math.fma(_t18, _t19, -(_t20 * _t21)), _self01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t18, _t23, -(_t24 * _t21)), _self02 * _t16, java.lang.Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        double _t51 = 1.0 + _t47;
        double _t63 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t51));
        toRigid_api_fma_se91a3fa7_515_sfc051044_1(dest, destOffset, _self11, _self22, -_self11, -_self22, _t15, _t16, _t19, _t24, java.lang.Math.fma(_self12, _t16, _t23), java.lang.Math.fma(_self21, _t15, -_t20), _t47, _t51, 1.0 - _t47, java.lang.Math.fma(_self01, _t15, _t48), java.lang.Math.fma(_self02, _t16, _t49), java.lang.Math.fma(_self02, _t16, -_t49), java.lang.Math.fma(-_self01, _t15, _t48), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 2 of {@code toRigid_api_fma_se91a3fa7_515}, split to fit the inline budget; reached only through it. */
    private static void toRigid_api_fma_se91a3fa7_515_sfc051044_1(java.nio.ByteBuffer dest, int destOffset, double _self11, double _self22, double _t0, double _t1, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t51, double _t52, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0) {
        double _t65 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        double _t66 = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t52));
        double _t67 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t51));
        toRigid_api_fma_se91a3fa7_515_sfc051044_2(dest, destOffset, _self11, _self22, _t15, _t16, _t19, _t24, _t31, _t35, _t47, _t54, _t55, _t56, _t57, _t63, _sp0, _t65, _t66, _t67, 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)));
    }

    /** Piece 3 of {@code toRigid_api_fma_se91a3fa7_515}, split to fit the inline budget; reached only through it. */
    private static void toRigid_api_fma_se91a3fa7_515_sfc051044_2(java.nio.ByteBuffer dest, int destOffset, double _self11, double _self22, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t47)) > 0.0) {
            dest.putDouble(destOffset + 24, _sp0 * _t35);
            dest.putDouble(destOffset + 32, _sp0 * _t56);
            dest.putDouble(destOffset + 40, _sp0 * _t57);
            dest.putDouble(destOffset + 48, 0.5 * java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t67));
                dest.putDouble(destOffset + 32, _sp3 * _t54);
                dest.putDouble(destOffset + 40, _sp3 * _t55);
                dest.putDouble(destOffset + 48, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.putDouble(destOffset + 24, _sp1 * _t54);
                    dest.putDouble(destOffset + 32, 0.5 * java.lang.Math.sqrt(_t65));
                    dest.putDouble(destOffset + 40, _sp1 * _t31);
                    dest.putDouble(destOffset + 48, _sp1 * _t56);
                } else {
                    dest.putDouble(destOffset + 24, _sp2 * _t55);
                    dest.putDouble(destOffset + 32, _sp2 * _t31);
                    dest.putDouble(destOffset + 40, 0.5 * java.lang.Math.sqrt(_t66));
                    dest.putDouble(destOffset + 48, _sp2 * _t57);
                }
            }
        }
    }

    /** {@code toRigid_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _ct0 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        return toRigid_api_mulAdd_sc991057e_1(dest, destOffset, _self00, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t15, _t16, _t17, _self10 * _t17, _self22 * _t16, _self12 * _t16, _self20 * _t17, _self21 * _t15);
    }

    /** Piece 2 of {@code toRigid_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_mulAdd_sc991057e_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t0, double _t1, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t23) {
        double _t24 = _self11 * _t15;
        double _t26 = _self00 * _t17;
        double _t47, _t48, _t49;
        if (((((((_t18) * (_t23) - (_t24 * _t21))) * (_self02 * _t16) + (((_t24) * (_t19) - (_t20 * _t23)) * _t26))) - ((_t18) * (_t19) - (_t20 * _t21)) * (_self01 * _t15)) < 0.0) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        double _t51 = 1.0 + _t47;
        double _t52 = 1.0 - _t47;
        double _t63 = ((_self11) * (_t15) + (((_self22) * (_t16) + (_t51))));
        double _t65 = ((_self11) * (_t15) + (((_t1) * (_t16) + (_t52))));
        double _t66 = ((_self22) * (_t16) + (((_t0) * (_t15) + (_t52))));
        double _t67 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t51))));
        return toRigid_api_mulAdd_sc991057e_2(dest, destOffset, _self11, _self22, _t15, _t16, _t19, _t24, ((_self12) * (_t16) + (_t23)), ((_self21) * (_t15) - (_t20)), _t47, ((_self01) * (_t15) + (_t48)), ((_self02) * (_t16) + (_t49)), ((_self02) * (_t16) - (_t49)), ((_t48) - (_self01) * (_t15)), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), _t65, _t66, _t67, 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)));
    }

    /** Piece 3 of {@code toRigid_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_mulAdd_sc991057e_2(java.nio.ByteBuffer dest, int destOffset, double _self11, double _self22, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67, double _sp1, double _sp2, double _sp3) {
        if (((_self11) * (_t15) + (((_self22) * (_t16) + (_t47)))) > 0.0) {
            dest.putDouble(destOffset + 24, _sp0 * _t35);
            dest.putDouble(destOffset + 32, _sp0 * _t56);
            dest.putDouble(destOffset + 40, _sp0 * _t57);
            dest.putDouble(destOffset + 48, 0.5 * java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t67));
                dest.putDouble(destOffset + 32, _sp3 * _t54);
                dest.putDouble(destOffset + 40, _sp3 * _t55);
                dest.putDouble(destOffset + 48, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.putDouble(destOffset + 24, _sp1 * _t54);
                    dest.putDouble(destOffset + 32, 0.5 * java.lang.Math.sqrt(_t65));
                    dest.putDouble(destOffset + 40, _sp1 * _t31);
                    dest.putDouble(destOffset + 48, _sp1 * _t56);
                } else {
                    dest.putDouble(destOffset + 24, _sp2 * _t55);
                    dest.putDouble(destOffset + 32, _sp2 * _t31);
                    dest.putDouble(destOffset + 40, 0.5 * java.lang.Math.sqrt(_t66));
                    dest.putDouble(destOffset + 48, _sp2 * _t57);
                }
            }
        }
        return toRigid_api_mulAdd_sc991057e_3(dest, destOffset);
    }

    /** Piece 4 of {@code toRigid_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_mulAdd_sc991057e_3(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toRigid_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer toRigid_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toRigid_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toRigid_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toRigid_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = unitScale(_self01, _self11, _self21);
        double _t1 = unitScale(_self02, _self12, _self22);
        double _t2 = unitScale(_self00, _self10, _self20);
        double _t12 = _self21 * _t0;
        double _t13 = _self01 * _t0;
        double _t14 = _self11 * _t0;
        double _t15 = _self22 * _t1;
        double _t16 = _self02 * _t1;
        double _t17 = _self12 * _t1;
        double _t18 = _self20 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self10 * _t2;
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
        double _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t89, _t89, _t74 * _t74))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
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
                if (_t29 <= 0.0) {
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
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
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
                if (_t29 <= 0.0) {
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
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (java.lang.Math.fma(java.lang.Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, java.lang.Math.fma(java.lang.Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, java.lang.Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t208));
        if (_t206 > 0.0) {
            dest.putDouble(destOffset + 24, _sp0 * _t182);
            dest.putDouble(destOffset + 32, _sp0 * _t201);
            dest.putDouble(destOffset + 40, _sp0 * _t202);
            dest.putDouble(destOffset + 48, 0.5 * java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t208));
                dest.putDouble(destOffset + 32, _sp3 * _t199);
                dest.putDouble(destOffset + 40, _sp3 * _t200);
                dest.putDouble(destOffset + 48, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.putDouble(destOffset + 24, _sp1 * _t199);
                    dest.putDouble(destOffset + 32, 0.5 * java.lang.Math.sqrt(_t209));
                    dest.putDouble(destOffset + 40, _sp1 * _t184);
                    dest.putDouble(destOffset + 48, _sp1 * _t201);
                } else {
                    dest.putDouble(destOffset + 24, _sp2 * _t200);
                    dest.putDouble(destOffset + 32, _sp2 * _t184);
                    dest.putDouble(destOffset + 40, 0.5 * java.lang.Math.sqrt(_t210));
                    dest.putDouble(destOffset + 48, _sp2 * _t202);
                }
            }
        }
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        return dest;
    }

    /** {@code toRigid_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toRigid_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = unitScale(_self01, _self11, _self21);
        double _t1 = unitScale(_self02, _self12, _self22);
        double _t2 = unitScale(_self00, _self10, _self20);
        double _t12 = _self21 * _t0;
        double _t13 = _self01 * _t0;
        double _t14 = _self11 * _t0;
        double _t15 = _self22 * _t1;
        double _t16 = _self02 * _t1;
        double _t17 = _self12 * _t1;
        double _t18 = _self20 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self10 * _t2;
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
        double _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / java.lang.Math.sqrt(((_t75) * (_t75) + (((_t87) * (_t87) + (_t72 * _t72))))));
        double _t100 = (1.0 / java.lang.Math.sqrt(((_t76) * (_t76) + (((_t88) * (_t88) + (_t73 * _t73))))));
        double _t101 = (1.0 / java.lang.Math.sqrt(((_t77) * (_t77) + (((_t89) * (_t89) + (_t74 * _t74))))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
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
                if (_t29 <= 0.0) {
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
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
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
                if (_t29 <= 0.0) {
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
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (((((_t165) * (_t166) - (_t167 * _t168))) * (_t169) + (((((_t170) * (_t168) - (_t165 * _t171))) * (_t172) + (((_t167) * (_t171) - (_t170 * _t166)) * _t173)))) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t208));
        if (_t206 > 0.0) {
            dest.putDouble(destOffset + 24, _sp0 * _t182);
            dest.putDouble(destOffset + 32, _sp0 * _t201);
            dest.putDouble(destOffset + 40, _sp0 * _t202);
            dest.putDouble(destOffset + 48, 0.5 * java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t208));
                dest.putDouble(destOffset + 32, _sp3 * _t199);
                dest.putDouble(destOffset + 40, _sp3 * _t200);
                dest.putDouble(destOffset + 48, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.putDouble(destOffset + 24, _sp1 * _t199);
                    dest.putDouble(destOffset + 32, 0.5 * java.lang.Math.sqrt(_t209));
                    dest.putDouble(destOffset + 40, _sp1 * _t184);
                    dest.putDouble(destOffset + 48, _sp1 * _t201);
                } else {
                    dest.putDouble(destOffset + 24, _sp2 * _t200);
                    dest.putDouble(destOffset + 32, _sp2 * _t184);
                    dest.putDouble(destOffset + 40, 0.5 * java.lang.Math.sqrt(_t210));
                    dest.putDouble(destOffset + 48, _sp2 * _t202);
                }
            }
        }
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_api_fma(dest, destOffset, src, srcOffset);
        return toTransform_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toTransform_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t12 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t14 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        return toTransform_api_fma_sc11e3d02_1(dest, destOffset, _self00, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, (1.0 / java.lang.Math.sqrt(_t12)), _t16, _t18, _t17, _self10 * _t17, _self22 * _t16);
    }

    /** Piece 2 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_fma_sc11e3d02_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t17, double _t19, double _t20) {
        double _t21 = _self12 * _t16;
        double _t22 = _self20 * _t17;
        double _t24 = _self21 * _t15;
        double _t25 = _self11 * _t15;
        double _t27 = _self00 * _t17;
        double _t47 = java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _self01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _self02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
        double _t48, _t49, _t50;
        if (_t47 < 0.0) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        double _t64 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t52));
        return toTransform_api_fma_sc11e3d02_2(dest, destOffset, _self22, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, java.lang.Math.fma(_self12, _t16, _t24), java.lang.Math.fma(_self21, _t15, -_t21), Math.max(_t25, _t20), _t47, _t48, _t52, _t53, java.lang.Math.fma(_self01, _t15, _t49), java.lang.Math.fma(_self02, _t16, _t50), java.lang.Math.fma(_self02, _t16, -_t50), java.lang.Math.fma(-_self01, _t15, _t49), java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t48)), _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t53)));
    }

    /** Piece 3 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_fma_sc11e3d02_2(java.nio.ByteBuffer dest, int destOffset, double _self22, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t66) {
        double _t67 = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t53));
        double _t68 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t68));
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        dest.putDouble(destOffset + 32, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        return toTransform_api_fma_sc11e3d02_3(dest, destOffset, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t56, _t57, _t58, _t63, _t64, _sp0, _t67, _sp1, _sp2, _sp3);
    }

    /** Piece 4 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_fma_sc11e3d02_3(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t13, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t67, double _sp1, double _sp2, double _sp3) {
        dest.putDouble(destOffset + 40, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * java.lang.Math.sqrt(_t67));
        dest.putDouble(destOffset + 48, _t63 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.putDouble(destOffset + 56, _t47 < 0.0 ? -_t18 : _t18);
        dest.putDouble(destOffset + 64, java.lang.Math.sqrt(_t12));
        dest.putDouble(destOffset + 72, java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code toTransform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t12 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t14 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        return toTransform_api_mulAdd_s47b95861_4(dest, destOffset, _self00, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, (1.0 / java.lang.Math.sqrt(_t12)), _t16, _t18, _t17, _self10 * _t17, _self22 * _t16, _self12 * _t16, _self20 * _t17);
    }

    /** Part 1 of {@code toTransform_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double toTransform_api_mulAdd_s47b95861_1(double _self01, double _self02, double _t15, double _t16, double _t19, double _t20, double _t21, double _t22, double _t24, double _t25, double _t27) {
        return ((((((_t19) * (_t24) - (_t25 * _t22))) * (_self02 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_self01 * _t15));
    }

    /** Part 2 of {@code toTransform_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static void toTransform_api_mulAdd_s47b95861_2(java.nio.ByteBuffer dest, int destOffset, double _t20, double _t25, double _t32, double _t36, double _t37, double _t48, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t66, double _t67, double _t68, double _sp1, double _sp2) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t68));
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        dest.putDouble(destOffset + 32, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        dest.putDouble(destOffset + 40, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * java.lang.Math.sqrt(_t67));
        dest.putDouble(destOffset + 48, _t63 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
    }

    /** Part 3 of {@code toTransform_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_mulAdd_s47b95861_3(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t13, double _t18, double _t47) {
        dest.putDouble(destOffset + 56, _t47 < 0.0 ? -_t18 : _t18);
        dest.putDouble(destOffset + 64, java.lang.Math.sqrt(_t12));
        dest.putDouble(destOffset + 72, java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** Piece 2 of {@code toTransform_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_mulAdd_s47b95861_4(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t17, double _t19, double _t20, double _t21, double _t22) {
        double _t24 = _self21 * _t15;
        double _t25 = _self11 * _t15;
        double _t27 = _self00 * _t17;
        double _t47 = toTransform_api_mulAdd_s47b95861_1(_self01, _self02, _t15, _t16, _t19, _t20, _t21, _t22, _t24, _t25, _t27);
        double _t48, _t49, _t50;
        if (_t47 < 0.0) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        double _t64 = ((_self11) * (_t15) + (((_self22) * (_t16) + (_t52))));
        double _t66 = ((_self11) * (_t15) + (((_t1) * (_t16) + (_t53))));
        double _t67 = ((_self22) * (_t16) + (((_t0) * (_t15) + (_t53))));
        toTransform_api_mulAdd_s47b95861_2(dest, destOffset, _t20, _t25, ((_self12) * (_t16) + (_t24)), ((_self21) * (_t15) - (_t21)), Math.max(_t25, _t20), _t48, ((_self01) * (_t15) + (_t49)), ((_self02) * (_t16) + (_t50)), ((_self02) * (_t16) - (_t50)), ((_t49) - (_self01) * (_t15)), ((_self11) * (_t15) + (((_self22) * (_t16) + (_t48)))), _t64, (0.5 * (1.0 / java.lang.Math.sqrt(_t64))), _t66, _t67, ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))), (0.5 * (1.0 / java.lang.Math.sqrt(_t66))), (0.5 * (1.0 / java.lang.Math.sqrt(_t67))));
        return toTransform_api_mulAdd_s47b95861_3(dest, destOffset, _t12, _t13, _t18, _t47);
    }

    public static java.nio.ByteBuffer toTransform_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toTransform_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer toTransform_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toTransform_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toTransform_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toTransform_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = unitScale(_self01, _self11, _self21);
        double _t1 = unitScale(_self02, _self12, _self22);
        double _t2 = unitScale(_self00, _self10, _self20);
        double _t12 = _self21 * _t0;
        double _t13 = _self01 * _t0;
        double _t14 = _self11 * _t0;
        double _t15 = _self22 * _t1;
        double _t16 = _self02 * _t1;
        double _t17 = _self12 * _t1;
        double _t18 = _self20 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self10 * _t2;
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
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
                if (_t29 <= 0.0) {
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
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
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
                if (_t29 <= 0.0) {
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
        double _t185 = _t173 - _t169;
        double _t186 = Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = java.lang.Math.fma(java.lang.Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, java.lang.Math.fma(java.lang.Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, java.lang.Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t211));
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.putDouble(destOffset + 32, _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        dest.putDouble(destOffset + 40, _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213));
        dest.putDouble(destOffset + 48, _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.putDouble(destOffset + 56, _t196 < 0.0 ? -_t56 : _t56);
        dest.putDouble(destOffset + 64, _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0);
        dest.putDouble(destOffset + 72, _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    /** {@code toTransform_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toTransform_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = unitScale(_self01, _self11, _self21);
        double _t1 = unitScale(_self02, _self12, _self22);
        double _t2 = unitScale(_self00, _self10, _self20);
        double _t12 = _self21 * _t0;
        double _t13 = _self01 * _t0;
        double _t14 = _self11 * _t0;
        double _t15 = _self22 * _t1;
        double _t16 = _self02 * _t1;
        double _t17 = _self12 * _t1;
        double _t18 = _self20 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self10 * _t2;
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        double _t103 = (1.0 / java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        double _t104 = (1.0 / java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
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
                if (_t29 <= 0.0) {
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
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
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
                if (_t29 <= 0.0) {
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
        double _t185 = _t173 - _t169;
        double _t186 = Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = ((((_t168) * (_t169) - (_t170 * _t171))) * (_t172) + (((((_t173) * (_t171) - (_t168 * _t174))) * (_t175) + (((_t170) * (_t174) - (_t173 * _t169)) * _t176))));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t211));
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.putDouble(destOffset + 32, _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        dest.putDouble(destOffset + 40, _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213));
        dest.putDouble(destOffset + 48, _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.putDouble(destOffset + 56, _t196 < 0.0 ? -_t56 : _t56);
        dest.putDouble(destOffset + 64, _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0);
        dest.putDouble(destOffset + 72, _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeRotation_api_fma(dest, destOffset, src, srcOffset);
        return decomposeRotation_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code decomposeRotation_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        double _t7, _t8, _t9;
        if (_t2 != 0.0) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0;
            _t8 = 0.0;
            _t9 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(_self21, _t7, java.lang.Math.fma(_self01, _t8, _self11 * _t9));
        double _t30 = -java.lang.Math.fma(java.lang.Math.fma(_t20, _t7, _self21), _t7, java.lang.Math.fma(java.lang.Math.fma(_t20, _t8, _self01), _t8, java.lang.Math.fma(_t20, _t9, _self11) * _t9));
        return decomposeRotation_api_fma_sf6399b1a_1(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, -java.lang.Math.fma(_self22, _t7, java.lang.Math.fma(_self02, _t8, _self12 * _t9)), java.lang.Math.fma(_t20, _t7, java.lang.Math.fma(_t30, _t7, _self21)), java.lang.Math.fma(_t20, _t8, java.lang.Math.fma(_t30, _t8, _self01)), java.lang.Math.fma(_t20, _t9, java.lang.Math.fma(_t30, _t9, _self11)));
    }

    /** Piece 2 of {@code decomposeRotation_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_fma_sf6399b1a_1(java.nio.ByteBuffer dest, int destOffset, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t19, double _t34, double _t35, double _t36) {
        double _t39 = java.lang.Math.fma(_t34, _t34, java.lang.Math.fma(_t35, _t35, _t36 * _t36));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t39));
        double _t44, _t45, _t46;
        if (_t39 != 0.0) {
            _t44 = _t34 * _t40;
            _t45 = _t35 * _t40;
            _t46 = _t36 * _t40;
        } else {
            _t44 = 0.0;
            _t45 = 0.0;
            _t46 = 0.0;
        }
        return decomposeRotation_api_fma_sf6399b1a_2(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, _t19, _t44, _t45, _t46, java.lang.Math.fma(_t8, _t46, -(_t9 * _t45)), java.lang.Math.fma(_t9, _t44, -(_t7 * _t46)), java.lang.Math.fma(_t7, _t45, -(_t8 * _t44)), -java.lang.Math.fma(java.lang.Math.fma(_t19, _t7, _self22), _t44, java.lang.Math.fma(java.lang.Math.fma(_t19, _t8, _self02), _t45, java.lang.Math.fma(_t19, _t9, _self12) * _t46)));
    }

    /** Piece 3 of {@code decomposeRotation_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_fma_sf6399b1a_2(java.nio.ByteBuffer dest, int destOffset, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t19, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t68) {
        double _t78, _t79, _t80, _t84, _t85, _t86;
        if (java.lang.Math.fma(java.lang.Math.fma(_t19, _t7, java.lang.Math.fma(_t68, _t44, _self22)), _t60, java.lang.Math.fma(java.lang.Math.fma(_t19, _t8, java.lang.Math.fma(_t68, _t45, _self02)), _t61, java.lang.Math.fma(_t19, _t9, java.lang.Math.fma(_t68, _t46, _self12)) * _t62)) < 0.0) {
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
        double _t81 = _t78 + _t46;
        double _t94 = _t81 + _t84;
        double _t98 = 1.0 + _t94;
        double _t99 = 1.0 + (_t78 - (_t84 + _t46));
        double _t100 = 1.0 + (_t46 - (_t78 + _t84));
        double _t101 = 1.0 + (_t84 - _t81);
        return decomposeRotation_api_fma_sf6399b1a_3(dest, destOffset, _t46, _t78, _t84, _t79 + _t45, _t79 - _t45, _t44 - _t85, _t85 + _t44, _t80 + _t86, _t86 - _t80, _t94, _t98, _t99, _t100, _t101, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t101)), 0.5 * (1.0 / java.lang.Math.sqrt(_t99)));
    }

    /** Piece 4 of {@code decomposeRotation_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_fma_sf6399b1a_3(java.nio.ByteBuffer dest, int destOffset, double _t46, double _t78, double _t84, double _t82, double _t83, double _t87, double _t90, double _t92, double _t93, double _t94, double _t98, double _t99, double _t100, double _t101, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t94 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t87);
            dest.putDouble(destOffset + 8, _sp0 * _t93);
            dest.putDouble(destOffset + 16, _sp0 * _t83);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t98));
        } else {
            if (_t78 > Math.max(_t46, _t84)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t99));
                dest.putDouble(destOffset + 8, _sp3 * _t82);
                dest.putDouble(destOffset + 16, _sp3 * _t92);
                dest.putDouble(destOffset + 24, _sp3 * _t87);
            } else {
                if (_t46 > _t84) {
                    dest.putDouble(destOffset, _sp1 * _t82);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t100));
                    dest.putDouble(destOffset + 16, _sp1 * _t90);
                    dest.putDouble(destOffset + 24, _sp1 * _t93);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t92);
                    dest.putDouble(destOffset + 8, _sp2 * _t90);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t101));
                    dest.putDouble(destOffset + 24, _sp2 * _t83);
                }
            }
        }
        return dest;
    }

    /** {@code decomposeRotation_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        double _t7, _t8, _t9;
        if (_t2 != 0.0) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0;
            _t8 = 0.0;
            _t9 = 0.0;
        }
        double _t20 = -((_self21) * (_t7) + (((_self01) * (_t8) + (_self11 * _t9))));
        double _t30 = -((((_t20) * (_t7) + (_self21))) * (_t7) + (((((_t20) * (_t8) + (_self01))) * (_t8) + (((_t20) * (_t9) + (_self11)) * _t9))));
        return decomposeRotation_api_mulAdd_s90d6c139_1(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, -((_self22) * (_t7) + (((_self02) * (_t8) + (_self12 * _t9)))), ((_t20) * (_t7) + (((_t30) * (_t7) + (_self21)))), ((_t20) * (_t8) + (((_t30) * (_t8) + (_self01)))), ((_t20) * (_t9) + (((_t30) * (_t9) + (_self11)))));
    }

    /** Piece 2 of {@code decomposeRotation_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_mulAdd_s90d6c139_1(java.nio.ByteBuffer dest, int destOffset, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t19, double _t34, double _t35, double _t36) {
        double _t39 = ((_t34) * (_t34) + (((_t35) * (_t35) + (_t36 * _t36))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t39));
        double _t44, _t45, _t46;
        if (_t39 != 0.0) {
            _t44 = _t34 * _t40;
            _t45 = _t35 * _t40;
            _t46 = _t36 * _t40;
        } else {
            _t44 = 0.0;
            _t45 = 0.0;
            _t46 = 0.0;
        }
        double _t60 = ((_t8) * (_t46) - (_t9 * _t45));
        double _t61 = ((_t9) * (_t44) - (_t7 * _t46));
        double _t62 = ((_t7) * (_t45) - (_t8 * _t44));
        double _t68 = -((((_t19) * (_t7) + (_self22))) * (_t44) + (((((_t19) * (_t8) + (_self02))) * (_t45) + (((_t19) * (_t9) + (_self12)) * _t46))));
        double _t78, _t79, _t80, _t84, _t85, _t86;
        if (((((_t19) * (_t7) + (((_t68) * (_t44) + (_self22))))) * (_t60) + (((((_t19) * (_t8) + (((_t68) * (_t45) + (_self02))))) * (_t61) + (((_t19) * (_t9) + (((_t68) * (_t46) + (_self12)))) * _t62)))) < 0.0) {
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
    private static java.nio.ByteBuffer decomposeRotation_api_mulAdd_s90d6c139_2(java.nio.ByteBuffer dest, int destOffset, double _t44, double _t45, double _t46, double _t78, double _t79, double _t80, double _t84, double _t85, double _t86) {
        double _t81 = _t78 + _t46;
        double _t94 = _t81 + _t84;
        double _t98 = 1.0 + _t94;
        double _t99 = 1.0 + (_t78 - (_t84 + _t46));
        double _t100 = 1.0 + (_t46 - (_t78 + _t84));
        double _t101 = 1.0 + (_t84 - _t81);
        return decomposeRotation_api_mulAdd_s90d6c139_3(dest, destOffset, _t46, _t78, _t84, _t79 + _t45, _t79 - _t45, _t44 - _t85, _t85 + _t44, _t80 + _t86, _t86 - _t80, _t94, _t98, _t99, _t100, _t101, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t101)), 0.5 * (1.0 / java.lang.Math.sqrt(_t99)));
    }

    /** Piece 4 of {@code decomposeRotation_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_mulAdd_s90d6c139_3(java.nio.ByteBuffer dest, int destOffset, double _t46, double _t78, double _t84, double _t82, double _t83, double _t87, double _t90, double _t92, double _t93, double _t94, double _t98, double _t99, double _t100, double _t101, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t94 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t87);
            dest.putDouble(destOffset + 8, _sp0 * _t93);
            dest.putDouble(destOffset + 16, _sp0 * _t83);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t98));
        } else {
            if (_t78 > Math.max(_t46, _t84)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t99));
                dest.putDouble(destOffset + 8, _sp3 * _t82);
                dest.putDouble(destOffset + 16, _sp3 * _t92);
                dest.putDouble(destOffset + 24, _sp3 * _t87);
            } else {
                if (_t46 > _t84) {
                    dest.putDouble(destOffset, _sp1 * _t82);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t100));
                    dest.putDouble(destOffset + 16, _sp1 * _t90);
                    dest.putDouble(destOffset + 24, _sp1 * _t93);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t92);
                    dest.putDouble(destOffset + 8, _sp2 * _t90);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t101));
                    dest.putDouble(destOffset + 24, _sp2 * _t83);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer decomposeScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeScale_api_fma(dest, destOffset, src, srcOffset);
        return decomposeScale_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code decomposeScale_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeScale_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        double _t4 = java.lang.Math.sqrt(_t2);
        double _t3 = 1.0 / _t4;
        double _t8, _t9, _t10;
        if (_t2 != 0.0) {
            _t8 = _self20 * _t3;
            _t9 = _self00 * _t3;
            _t10 = _self10 * _t3;
        } else {
            _t8 = 0.0;
            _t9 = 0.0;
            _t10 = 0.0;
        }
        double _t18 = -java.lang.Math.fma(_self21, _t8, java.lang.Math.fma(_self01, _t9, _self11 * _t10));
        double _t28 = -java.lang.Math.fma(java.lang.Math.fma(_t18, _t8, _self21), _t8, java.lang.Math.fma(java.lang.Math.fma(_t18, _t9, _self01), _t9, java.lang.Math.fma(_t18, _t10, _self11) * _t10));
        return decomposeScale_api_fma_scab348b2_1(dest, destOffset, _self11, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -java.lang.Math.fma(_self22, _t8, java.lang.Math.fma(_self02, _t9, _self12 * _t10)), _t18, _t28, java.lang.Math.fma(_t18, _t8, java.lang.Math.fma(_t28, _t8, _self21)), java.lang.Math.fma(_t18, _t9, java.lang.Math.fma(_t28, _t9, _self01)));
    }

    /** Piece 2 of {@code decomposeScale_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeScale_api_fma_scab348b2_1(java.nio.ByteBuffer dest, int destOffset, double _self11, double _self02, double _self12, double _self22, double _t4, double _t8, double _t9, double _t10, double _t17, double _t18, double _t28, double _t32, double _t33) {
        double _t34 = java.lang.Math.fma(_t18, _t10, java.lang.Math.fma(_t28, _t10, _self11));
        double _t37 = java.lang.Math.fma(_t32, _t32, java.lang.Math.fma(_t33, _t33, _t34 * _t34));
        double _t38 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t42, _t43, _t44;
        if (_t37 != 0.0) {
            _t42 = _t32 * _t38;
            _t43 = _t33 * _t38;
            _t44 = _t34 * _t38;
        } else {
            _t42 = 0.0;
            _t43 = 0.0;
            _t44 = 0.0;
        }
        double _t48 = -java.lang.Math.fma(java.lang.Math.fma(_t17, _t8, _self22), _t42, java.lang.Math.fma(java.lang.Math.fma(_t17, _t9, _self02), _t43, java.lang.Math.fma(_t17, _t10, _self12) * _t44));
        double _t52 = java.lang.Math.fma(_t17, _t8, java.lang.Math.fma(_t48, _t42, _self22));
        double _t53 = java.lang.Math.fma(_t17, _t9, java.lang.Math.fma(_t48, _t43, _self02));
        double _t54 = java.lang.Math.fma(_t17, _t10, java.lang.Math.fma(_t48, _t44, _self12));
        dest.putDouble(destOffset, java.lang.Math.fma(_t52, java.lang.Math.fma(_t9, _t44, -(_t10 * _t43)), java.lang.Math.fma(_t53, java.lang.Math.fma(_t10, _t42, -(_t8 * _t44)), _t54 * java.lang.Math.fma(_t8, _t43, -(_t9 * _t42)))) < 0.0 ? -_t4 : _t4);
        dest.putDouble(destOffset + 8, java.lang.Math.sqrt(_t37));
        dest.putDouble(destOffset + 16, java.lang.Math.sqrt(java.lang.Math.fma(_t52, _t52, java.lang.Math.fma(_t53, _t53, _t54 * _t54))));
        return dest;
    }

    /** {@code decomposeScale_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeScale_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        double _t4 = java.lang.Math.sqrt(_t2);
        double _t3 = 1.0 / _t4;
        double _t8, _t9, _t10;
        if (_t2 != 0.0) {
            _t8 = _self20 * _t3;
            _t9 = _self00 * _t3;
            _t10 = _self10 * _t3;
        } else {
            _t8 = 0.0;
            _t9 = 0.0;
            _t10 = 0.0;
        }
        double _t18 = -((_self21) * (_t8) + (((_self01) * (_t9) + (_self11 * _t10))));
        double _t28 = -((((_t18) * (_t8) + (_self21))) * (_t8) + (((((_t18) * (_t9) + (_self01))) * (_t9) + (((_t18) * (_t10) + (_self11)) * _t10))));
        return decomposeScale_api_mulAdd_sf0e64bd1_1(dest, destOffset, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -((_self22) * (_t8) + (((_self02) * (_t9) + (_self12 * _t10)))), ((_t18) * (_t8) + (((_t28) * (_t8) + (_self21)))), ((_t18) * (_t9) + (((_t28) * (_t9) + (_self01)))), ((_t18) * (_t10) + (((_t28) * (_t10) + (_self11)))));
    }

    /** Piece 2 of {@code decomposeScale_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeScale_api_mulAdd_sf0e64bd1_1(java.nio.ByteBuffer dest, int destOffset, double _self02, double _self12, double _self22, double _t4, double _t8, double _t9, double _t10, double _t17, double _t32, double _t33, double _t34) {
        double _t37 = ((_t32) * (_t32) + (((_t33) * (_t33) + (_t34 * _t34))));
        double _t38 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t42, _t43, _t44;
        if (_t37 != 0.0) {
            _t42 = _t32 * _t38;
            _t43 = _t33 * _t38;
            _t44 = _t34 * _t38;
        } else {
            _t42 = 0.0;
            _t43 = 0.0;
            _t44 = 0.0;
        }
        double _t48 = -((((_t17) * (_t8) + (_self22))) * (_t42) + (((((_t17) * (_t9) + (_self02))) * (_t43) + (((_t17) * (_t10) + (_self12)) * _t44))));
        double _t52 = ((_t17) * (_t8) + (((_t48) * (_t42) + (_self22))));
        double _t53 = ((_t17) * (_t9) + (((_t48) * (_t43) + (_self02))));
        double _t54 = ((_t17) * (_t10) + (((_t48) * (_t44) + (_self12))));
        dest.putDouble(destOffset, ((_t52) * (((_t9) * (_t44) - (_t10 * _t43))) + (((_t53) * (((_t10) * (_t42) - (_t8 * _t44))) + (_t54 * ((_t8) * (_t43) - (_t9 * _t42)))))) < 0.0 ? -_t4 : _t4);
        dest.putDouble(destOffset + 8, java.lang.Math.sqrt(_t37));
        dest.putDouble(destOffset + 16, java.lang.Math.sqrt(((_t52) * (_t52) + (((_t53) * (_t53) + (_t54 * _t54))))));
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSkew_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSkew_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSkew_api_fma(dest, destOffset, src, srcOffset);
        return decomposeSkew_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code decomposeSkew_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        double _t7, _t8, _t9;
        if (_t2 != 0.0) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0;
            _t8 = 0.0;
            _t9 = 0.0;
        }
        double _t14 = java.lang.Math.fma(_self22, _t7, java.lang.Math.fma(_self02, _t8, _self12 * _t9));
        double _t15 = java.lang.Math.fma(_self21, _t7, java.lang.Math.fma(_self01, _t8, _self11 * _t9));
        double _t17 = -_t15;
        double _t26 = java.lang.Math.fma(java.lang.Math.fma(_t17, _t7, _self21), _t7, java.lang.Math.fma(java.lang.Math.fma(_t17, _t8, _self01), _t8, java.lang.Math.fma(_t17, _t9, _self11) * _t9));
        double _t27 = -_t26;
        return decomposeSkew_api_fma_sdc543dea_1(dest, destOffset, _self01, _self11, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, _t17, _t26, _t27, java.lang.Math.fma(_t17, _t7, java.lang.Math.fma(_t27, _t7, _self21)));
    }

    /** Piece 2 of {@code decomposeSkew_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_fma_sdc543dea_1(java.nio.ByteBuffer dest, int destOffset, double _self01, double _self11, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t14, double _t15, double _t16, double _t17, double _t26, double _t27, double _t32) {
        double _t33 = java.lang.Math.fma(_t17, _t8, java.lang.Math.fma(_t27, _t8, _self01));
        double _t34 = java.lang.Math.fma(_t17, _t9, java.lang.Math.fma(_t27, _t9, _self11));
        double _t37 = java.lang.Math.fma(_t32, _t32, java.lang.Math.fma(_t33, _t33, _t34 * _t34));
        double _t38 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43, _t44, _t45;
        if (_t37 != 0.0) {
            _t43 = _t32 * _t38;
            _t44 = _t33 * _t38;
            _t45 = _t34 * _t38;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t57 = java.lang.Math.fma(java.lang.Math.fma(_t16, _t7, _self22), _t43, java.lang.Math.fma(java.lang.Math.fma(_t16, _t8, _self02), _t44, java.lang.Math.fma(_t16, _t9, _self12) * _t45));
        double _t58 = -_t57;
        double _t62 = java.lang.Math.fma(_t16, _t7, java.lang.Math.fma(_t58, _t43, _self22));
        double _t63 = java.lang.Math.fma(_t16, _t8, java.lang.Math.fma(_t58, _t44, _self02));
        double _t64 = java.lang.Math.fma(_t16, _t9, java.lang.Math.fma(_t58, _t45, _self12));
        double _t71 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t62, _t62, java.lang.Math.fma(_t63, _t63, _t64 * _t64))));
        return decomposeSkew_api_fma_sdc543dea_2(dest, destOffset, _t7, _t8, _t9, (_t15 + _t26) * _t38, _t43, _t44, _t45, _t57, _t62, _t63, _t64, _t71, _t14 * _t71);
    }

    /** Piece 3 of {@code decomposeSkew_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_fma_sdc543dea_2(java.nio.ByteBuffer dest, int destOffset, double _t7, double _t8, double _t9, double _t39, double _t43, double _t44, double _t45, double _t57, double _t62, double _t63, double _t64, double _t71, double _t72) {
        if (java.lang.Math.fma(_t62, java.lang.Math.fma(_t8, _t45, -(_t9 * _t44)), java.lang.Math.fma(_t63, java.lang.Math.fma(_t9, _t43, -(_t7 * _t45)), _t64 * java.lang.Math.fma(_t7, _t44, -(_t8 * _t43)))) < 0.0) {
            dest.putDouble(destOffset + 8, -_t72);
            dest.putDouble(destOffset + 16, -_t39);
        } else {
            dest.putDouble(destOffset + 8, _t72);
            dest.putDouble(destOffset + 16, _t39);
        }
        dest.putDouble(destOffset, _t57 * _t71);
        return dest;
    }

    /** {@code decomposeSkew_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        double _t7, _t8, _t9;
        if (_t2 != 0.0) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0;
            _t8 = 0.0;
            _t9 = 0.0;
        }
        double _t14 = ((_self22) * (_t7) + (((_self02) * (_t8) + (_self12 * _t9))));
        double _t15 = ((_self21) * (_t7) + (((_self01) * (_t8) + (_self11 * _t9))));
        double _t17 = -_t15;
        double _t26 = ((((_t17) * (_t7) + (_self21))) * (_t7) + (((((_t17) * (_t8) + (_self01))) * (_t8) + (((_t17) * (_t9) + (_self11)) * _t9))));
        double _t27 = -_t26;
        return decomposeSkew_api_mulAdd_sc0f25c09_1(dest, destOffset, _self11, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, _t17, _t26, _t27, ((_t17) * (_t7) + (((_t27) * (_t7) + (_self21)))), ((_t17) * (_t8) + (((_t27) * (_t8) + (_self01)))));
    }

    /** Piece 2 of {@code decomposeSkew_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_mulAdd_sc0f25c09_1(java.nio.ByteBuffer dest, int destOffset, double _self11, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t14, double _t15, double _t16, double _t17, double _t26, double _t27, double _t32, double _t33) {
        double _t34 = ((_t17) * (_t9) + (((_t27) * (_t9) + (_self11))));
        double _t37 = ((_t32) * (_t32) + (((_t33) * (_t33) + (_t34 * _t34))));
        double _t38 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43, _t44, _t45;
        if (_t37 != 0.0) {
            _t43 = _t32 * _t38;
            _t44 = _t33 * _t38;
            _t45 = _t34 * _t38;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t57 = ((((_t16) * (_t7) + (_self22))) * (_t43) + (((((_t16) * (_t8) + (_self02))) * (_t44) + (((_t16) * (_t9) + (_self12)) * _t45))));
        double _t58 = -_t57;
        double _t62 = ((_t16) * (_t7) + (((_t58) * (_t43) + (_self22))));
        double _t63 = ((_t16) * (_t8) + (((_t58) * (_t44) + (_self02))));
        double _t64 = ((_t16) * (_t9) + (((_t58) * (_t45) + (_self12))));
        double _t71 = (1.0 / java.lang.Math.sqrt(((_t62) * (_t62) + (((_t63) * (_t63) + (_t64 * _t64))))));
        return decomposeSkew_api_mulAdd_sc0f25c09_2(dest, destOffset, _t7, _t8, _t9, (_t15 + _t26) * _t38, _t43, _t44, _t45, _t57, _t62, _t63, _t64, _t71, _t14 * _t71);
    }

    /** Piece 3 of {@code decomposeSkew_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_mulAdd_sc0f25c09_2(java.nio.ByteBuffer dest, int destOffset, double _t7, double _t8, double _t9, double _t39, double _t43, double _t44, double _t45, double _t57, double _t62, double _t63, double _t64, double _t71, double _t72) {
        if (((_t62) * (((_t8) * (_t45) - (_t9 * _t44))) + (((_t63) * (((_t9) * (_t43) - (_t7 * _t45))) + (_t64 * ((_t7) * (_t44) - (_t8 * _t43)))))) < 0.0) {
            dest.putDouble(destOffset + 8, -_t72);
            dest.putDouble(destOffset + 16, -_t39);
        } else {
            dest.putDouble(destOffset + 8, _t72);
            dest.putDouble(destOffset + 16, _t39);
        }
        dest.putDouble(destOffset, _t57 * _t71);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 9; _i++) {
                double _eself = src.getDouble(srcOffset + _i * 8);
                double _eother = other.getDouble(otherOffset + _i * 8);
                dest.putDouble(destOffset + _i * 8, java.lang.Math.fma(t, _eother - _eself, _eself));
            }
            return dest;
        } else {
            for (int _i = 0; _i < 9; _i++) {
                double _eself = src.getDouble(srcOffset + _i * 8);
                double _eother = other.getDouble(otherOffset + _i * 8);
                dest.putDouble(destOffset + _i * 8, ((t) * (_eother - _eself) + (_eself)));
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right.getDouble(rightOffset + _lo * 8);
            double _eright1 = right.getDouble(rightOffset + (_lo + 1) * 8);
            double _eright2 = right.getDouble(rightOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.putDouble(destOffset + (_lo + 1) * 8, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.putDouble(destOffset + (_lo + 2) * 8, java.lang.Math.fma(_eright2, _self22, java.lang.Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right.getDouble(rightOffset + _lo * 8);
            double _eright1 = right.getDouble(rightOffset + (_lo + 1) * 8);
            double _eright2 = right.getDouble(rightOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, ((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01)))));
            dest.putDouble(destOffset + (_lo + 1) * 8, ((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11)))));
            dest.putDouble(destOffset + (_lo + 2) * 8, ((_eright2) * (_self22) + (((_eright0) * (_self20) + (_eright1 * _self21)))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double3x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x2_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        dest.putDouble(destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_right00, _self20, _right10 * _self21));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_right01, _self20, _right11 * _self21));
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    /** {@code mulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulMat2x2_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        dest.putDouble(destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.putDouble(destOffset + 8, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.putDouble(destOffset + 16, ((_right00) * (_self20) + (_right10 * _self21)));
        dest.putDouble(destOffset + 24, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.putDouble(destOffset + 32, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.putDouble(destOffset + 40, ((_right01) * (_self20) + (_right11 * _self21)));
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double3x3OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x3_api_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x3_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@code mulMat2x3_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulMat2x3_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        double _right02 = right.getDouble(rightOffset + 32);
        double _right12 = right.getDouble(rightOffset + 40);
        dest.putDouble(destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_right00, _self20, _right10 * _self21));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_right01, _self20, _right11 * _self21));
        return mulMat2x3_api_fma_se9bb1c32_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer mulMat2x3_api_fma_se9bb1c32_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _right02, double _right12) {
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_right02, _self00, java.lang.Math.fma(_right12, _self01, _self02)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_right02, _self10, java.lang.Math.fma(_right12, _self11, _self12)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_right02, _self20, java.lang.Math.fma(_right12, _self21, _self22)));
        return dest;
    }

    /** {@code mulMat2x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulMat2x3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        double _right02 = right.getDouble(rightOffset + 32);
        double _right12 = right.getDouble(rightOffset + 40);
        dest.putDouble(destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.putDouble(destOffset + 8, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.putDouble(destOffset + 16, ((_right00) * (_self20) + (_right10 * _self21)));
        dest.putDouble(destOffset + 24, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.putDouble(destOffset + 32, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.putDouble(destOffset + 40, ((_right01) * (_self20) + (_right11 * _self21)));
        return mulMat2x3_api_mulAdd_sa5480ad5_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer mulMat2x3_api_mulAdd_sa5480ad5_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _right02, double _right12) {
        dest.putDouble(destOffset + 48, ((_right02) * (_self00) + (((_right12) * (_self01) + (_self02)))));
        dest.putDouble(destOffset + 56, ((_right02) * (_self10) + (((_right12) * (_self11) + (_self12)))));
        dest.putDouble(destOffset + 64, ((_right02) * (_self20) + (((_right12) * (_self21) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, java.lang.Math.fma(_other02, _eself2, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.putDouble(destOffset + (_lo + 1) * 8, java.lang.Math.fma(_other12, _eself2, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.putDouble(destOffset + (_lo + 2) * 8, java.lang.Math.fma(_other22, _eself2, java.lang.Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, ((_other02) * (_eself2) + (((_other00) * (_eself0) + (_other01 * _eself1)))));
            dest.putDouble(destOffset + (_lo + 1) * 8, ((_other12) * (_eself2) + (((_other10) * (_eself0) + (_other11 * _eself1)))));
            dest.putDouble(destOffset + (_lo + 2) * 8, ((_other22) * (_eself2) + (((_other20) * (_eself0) + (_other21 * _eself1)))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x2_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.putDouble(destOffset + (_lo + 1) * 8, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    /** {@code preMulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMulMat2x2_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, ((_other00) * (_eself0) + (_other01 * _eself1)));
            dest.putDouble(destOffset + (_lo + 1) * 8, ((_other10) * (_eself0) + (_other11 * _eself1)));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x3_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _other02 = other.getDouble(otherOffset + 32);
        double _other12 = other.getDouble(otherOffset + 40);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, java.lang.Math.fma(_other02, _eself2, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.putDouble(destOffset + (_lo + 1) * 8, java.lang.Math.fma(_other12, _eself2, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    /** {@code preMulMat2x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMulMat2x3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _other02 = other.getDouble(otherOffset + 32);
        double _other12 = other.getDouble(otherOffset + 40);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, ((_other02) * (_eself2) + (((_other00) * (_eself0) + (_other01 * _eself1)))));
            dest.putDouble(destOffset + (_lo + 1) * 8, ((_other12) * (_eself2) + (((_other10) * (_eself0) + (_other11 * _eself1)))));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 9; _i++) {
                double _eself = src.getDouble(srcOffset + _i * 8);
                double _eother = other.getDouble(otherOffset + _i * 8);
                dest.putDouble(destOffset + _i * 8, java.lang.Math.fma(weight, _eother, _eself));
            }
            return dest;
        } else {
            for (int _i = 0; _i < 9; _i++) {
                double _eself = src.getDouble(srcOffset + _i * 8);
                double _eother = other.getDouble(otherOffset + _i * 8);
                dest.putDouble(destOffset + _i * 8, ((weight) * (_eother) + (_eself)));
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer makeOuterProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, colZ, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_api(java.nio.ByteBuffer dest, int destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        dest.putDouble(destOffset, colX * rowX);
        dest.putDouble(destOffset + 8, colY * rowX);
        dest.putDouble(destOffset + 16, colZ * rowX);
        dest.putDouble(destOffset + 24, colX * rowY);
        dest.putDouble(destOffset + 32, colY * rowY);
        dest.putDouble(destOffset + 40, colZ * rowY);
        dest.putDouble(destOffset + 48, colX * rowZ);
        dest.putDouble(destOffset + 56, colY * rowZ);
        dest.putDouble(destOffset + 64, colZ * rowZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _colBase = UnsafeOpsHolder.U.getLong(col, UnsafeCopy.BB_ADDRESS_OFFSET) + colOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Double3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        double _colx = col.getDouble(colOffset);
        double _coly = col.getDouble(colOffset + 8);
        double _colz = col.getDouble(colOffset + 16);
        double _rowx = row.getDouble(rowOffset);
        double _rowy = row.getDouble(rowOffset + 8);
        double _rowz = row.getDouble(rowOffset + 16);
        dest.putDouble(destOffset, _colx * _rowx);
        dest.putDouble(destOffset + 8, _coly * _rowx);
        dest.putDouble(destOffset + 16, _colz * _rowx);
        dest.putDouble(destOffset + 24, _colx * _rowy);
        dest.putDouble(destOffset + 32, _coly * _rowy);
        dest.putDouble(destOffset + 40, _colz * _rowy);
        dest.putDouble(destOffset + 48, _colx * _rowz);
        dest.putDouble(destOffset + 56, _coly * _rowz);
        dest.putDouble(destOffset + 64, _colz * _rowz);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t11, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        return lookAlong_lh_api_fma_sb3012894_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t11, -(_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb3012894_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        double _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        return lookAlong_lh_api_fma_sb3012894_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb3012894_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        double _t18 = ((_t17) * (_t11) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t12) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        double _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        return lookAlong_lh_api_mulAdd_s5ebb6655_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s5ebb6655_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32) {
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        double _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        dest.putDouble(destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        return lookAlong_lh_api_mulAdd_s5ebb6655_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t38, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s5ebb6655_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t38, double _t48) {
        dest.putDouble(destOffset + 48, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _t0 = -src.getDouble(srcOffset + 48);
        double _t1 = -src.getDouble(srcOffset + 56);
        double _t2 = -src.getDouble(srcOffset + 64);
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        double _t21 = java.lang.Math.fma(_t20, _t16, upY);
        double _t22 = java.lang.Math.fma(_t20, _t14, upX);
        double _t23 = java.lang.Math.fma(_t20, _t15, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        double _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        double _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.putDouble(destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _t0 = -src.getDouble(srcOffset + 48);
        double _t1 = -src.getDouble(srcOffset + 56);
        double _t2 = -src.getDouble(srcOffset + 64);
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        double _t21 = ((_t20) * (_t16) + (upY));
        double _t22 = ((_t20) * (_t14) + (upX));
        double _t23 = ((_t20) * (_t15) + (upZ));
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        double _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        double _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.putDouble(destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.putDouble(destOffset + 8, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.putDouble(destOffset + 16, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.putDouble(destOffset + 24, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.putDouble(destOffset + 32, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.putDouble(destOffset + 40, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.putDouble(destOffset + 48, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.putDouble(destOffset + 56, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.putDouble(destOffset + 64, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        return lookAlong_lh_api_fma_sb591f666_1(dest, destOffset, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t17, _t11, _upx), java.lang.Math.fma(_t17, _t13, _upy), java.lang.Math.fma(_t17, _t12, _upz));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb591f666_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20) {
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        double _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        return lookAlong_lh_api_fma_sb591f666_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)), java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb591f666_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        return lookAlong_lh_api_mulAdd_sc04bd8c5_1(dest, destOffset, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t11) + (_upx)), ((_t17) * (_t13) + (_upy)), ((_t17) * (_t12) + (_upz)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sc04bd8c5_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20) {
        double _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        double _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        double _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        dest.putDouble(destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        return lookAlong_lh_api_mulAdd_sc04bd8c5_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sc04bd8c5_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        dest.putDouble(destOffset + 32, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _t0 = -src.getDouble(srcOffset + 48);
        double _t1 = -src.getDouble(srcOffset + 56);
        double _t2 = -src.getDouble(srcOffset + 64);
        double _t7 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        double _t21 = java.lang.Math.fma(_t20, _t16, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t14, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t15, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        double _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        double _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.putDouble(destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _t0 = -src.getDouble(srcOffset + 48);
        double _t1 = -src.getDouble(srcOffset + 56);
        double _t2 = -src.getDouble(srcOffset + 64);
        double _t7 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        double _t21 = ((_t20) * (_t16) + (_upy));
        double _t22 = ((_t20) * (_t14) + (_upx));
        double _t23 = ((_t20) * (_t15) + (_upz));
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        double _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        double _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.putDouble(destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.putDouble(destOffset + 8, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.putDouble(destOffset + 16, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.putDouble(destOffset + 24, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.putDouble(destOffset + 32, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.putDouble(destOffset + 40, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.putDouble(destOffset + 48, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.putDouble(destOffset + 56, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.putDouble(destOffset + 64, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t11, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        return lookAlong_lh_api_fma_sb08e4d77_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t11, -(_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb08e4d77_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        double _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        return lookAlong_lh_api_fma_sb08e4d77_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb08e4d77_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        double _t18 = ((_t17) * (_t11) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t12) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        double _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        return lookAlong_lh_api_mulAdd_s9160dca6_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s9160dca6_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32) {
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        double _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        dest.putDouble(destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        return lookAlong_lh_api_mulAdd_s9160dca6_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t38, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s9160dca6_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t38, double _t48) {
        dest.putDouble(destOffset + 48, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _t0 = -src.getDouble(srcOffset + 48);
        double _t1 = -src.getDouble(srcOffset + 56);
        double _t2 = -src.getDouble(srcOffset + 64);
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        double _t21 = java.lang.Math.fma(_t20, _t16, upY);
        double _t22 = java.lang.Math.fma(_t20, _t14, upX);
        double _t23 = java.lang.Math.fma(_t20, _t15, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        double _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        double _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.putDouble(destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _t0 = -src.getDouble(srcOffset + 48);
        double _t1 = -src.getDouble(srcOffset + 56);
        double _t2 = -src.getDouble(srcOffset + 64);
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        double _t21 = ((_t20) * (_t16) + (upY));
        double _t22 = ((_t20) * (_t14) + (upX));
        double _t23 = ((_t20) * (_t15) + (upZ));
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        double _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        double _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.putDouble(destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.putDouble(destOffset + 8, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.putDouble(destOffset + 16, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.putDouble(destOffset + 24, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.putDouble(destOffset + 32, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.putDouble(destOffset + 40, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.putDouble(destOffset + 48, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.putDouble(destOffset + 56, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.putDouble(destOffset + 64, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        return lookAlong_lh_api_fma_sb01e715d_1(dest, destOffset, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t17, java.lang.Math.fma(_t17, _t11, _upx), java.lang.Math.fma(_t17, _t13, _upy));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb01e715d_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t17, double _t18, double _t19) {
        double _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        double _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        return lookAlong_lh_api_fma_sb01e715d_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)), java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb01e715d_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        return lookAlong_lh_api_mulAdd_sb74a88b6_1(dest, destOffset, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t11) + (_upx)), ((_t17) * (_t13) + (_upy)), ((_t17) * (_t12) + (_upz)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sb74a88b6_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20) {
        double _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        double _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        double _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        dest.putDouble(destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        return lookAlong_lh_api_mulAdd_sb74a88b6_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sb74a88b6_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        dest.putDouble(destOffset + 32, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _t0 = -src.getDouble(srcOffset + 48);
        double _t1 = -src.getDouble(srcOffset + 56);
        double _t2 = -src.getDouble(srcOffset + 64);
        double _t7 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        double _t21 = java.lang.Math.fma(_t20, _t16, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t14, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t15, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        double _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        double _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.putDouble(destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _t0 = -src.getDouble(srcOffset + 48);
        double _t1 = -src.getDouble(srcOffset + 56);
        double _t2 = -src.getDouble(srcOffset + 64);
        double _t7 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        double _t21 = ((_t20) * (_t16) + (_upy));
        double _t22 = ((_t20) * (_t14) + (_upx));
        double _t23 = ((_t20) * (_t15) + (_upz));
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        double _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        double _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.putDouble(destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.putDouble(destOffset + 8, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.putDouble(destOffset + 16, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.putDouble(destOffset + 24, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.putDouble(destOffset + 32, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.putDouble(destOffset + 40, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.putDouble(destOffset + 48, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.putDouble(destOffset + 56, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.putDouble(destOffset + 64, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_api(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromDualQuat_api_mulAdd(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = java.lang.Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        dest.putDouble(destOffset, java.lang.Math.fma(-2.0, _t0, _t6));
        dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(dqRX, dqRY, _t2));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(-2.0, _t3, _sp0 * dqRZ));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(-2.0, _t2, _sp0 * dqRY));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(-2.0, _t4, _t6));
        dest.putDouble(destOffset + 40, 2.0 * java.lang.Math.fma(dqRX, dqRW, _t5));
        dest.putDouble(destOffset + 48, 2.0 * java.lang.Math.fma(dqRX, dqRZ, _t3));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(-2.0, dqRX * dqRW, _t5 + _t5));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(-2.0, _t4, java.lang.Math.fma(-2.0, _t0, 1.0)));
        return dest;
    }

    /** {@code makeFromDualQuat_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeFromDualQuat_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = ((1.0) - (2.0) * (dqRZ * dqRZ));
        dest.putDouble(destOffset, ((_t6) - (2.0) * (_t0)));
        dest.putDouble(destOffset + 8, 2.0 * ((dqRX) * (dqRY) + (_t2)));
        dest.putDouble(destOffset + 16, ((_sp0 * dqRZ) - (2.0) * (_t3)));
        dest.putDouble(destOffset + 24, ((_sp0 * dqRY) - (2.0) * (_t2)));
        dest.putDouble(destOffset + 32, ((_t6) - (2.0) * (_t4)));
        dest.putDouble(destOffset + 40, 2.0 * ((dqRX) * (dqRW) + (_t5)));
        dest.putDouble(destOffset + 48, 2.0 * ((dqRX) * (dqRZ) + (_t3)));
        dest.putDouble(destOffset + 56, ((_t5 + _t5) - (2.0) * (dqRX * dqRW)));
        dest.putDouble(destOffset + 64, ((((1.0) - (2.0) * (_t0))) - (2.0) * (_t4)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, _t0);
        dest.putDouble(destOffset + 8, _t1);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, -_t1);
        dest.putDouble(destOffset + 32, _t0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationAxis_api_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = axisX * axisY;
        double _t4 = axisX * axisZ;
        double _t5 = axisY * axisZ;
        dest.putDouble(destOffset, java.lang.Math.fma(_t2, axisX * axisX, _t0));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(axisZ, _t1, _t2 * _t3));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t2, _t4, -(axisY * _t1)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t2, _t3, -(axisZ * _t1)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t2, axisY * axisY, _t0));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(axisX, _t1, _t2 * _t5));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(axisY, _t1, _t2 * _t4));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t2, _t5, -(axisX * _t1)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_t2, axisZ * axisZ, _t0));
        return dest;
    }

    /** {@code makeRotationAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = axisX * axisY;
        double _t4 = axisX * axisZ;
        double _t5 = axisY * axisZ;
        dest.putDouble(destOffset, ((_t2) * (axisX * axisX) + (_t0)));
        dest.putDouble(destOffset + 8, ((axisZ) * (_t1) + (_t2 * _t3)));
        dest.putDouble(destOffset + 16, ((_t2) * (_t4) - (axisY * _t1)));
        dest.putDouble(destOffset + 24, ((_t2) * (_t3) - (axisZ * _t1)));
        dest.putDouble(destOffset + 32, ((_t2) * (axisY * axisY) + (_t0)));
        dest.putDouble(destOffset + 40, ((axisX) * (_t1) + (_t2 * _t5)));
        dest.putDouble(destOffset + 48, ((axisY) * (_t1) + (_t2 * _t4)));
        dest.putDouble(destOffset + 56, ((_t2) * (_t5) - (axisX * _t1)));
        dest.putDouble(destOffset + 64, ((_t2) * (axisZ * axisZ) + (_t0)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationAxis_api_mulAdd(dest, destOffset, axis, axisOffset, angle);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = _axisx * _axisy;
        double _t4 = _axisx * _axisz;
        double _t5 = _axisy * _axisz;
        dest.putDouble(destOffset, java.lang.Math.fma(_t2, _axisx * _axisx, _t0));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_axisz, _t1, _t2 * _t3));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t2, _t4, -(_axisy * _t1)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t2, _t3, -(_axisz * _t1)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t2, _axisy * _axisy, _t0));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_axisx, _t1, _t2 * _t5));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_axisy, _t1, _t2 * _t4));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t2, _t5, -(_axisx * _t1)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_t2, _axisz * _axisz, _t0));
        return dest;
    }

    /** {@code makeRotationAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = _axisx * _axisy;
        double _t4 = _axisx * _axisz;
        double _t5 = _axisy * _axisz;
        dest.putDouble(destOffset, ((_t2) * (_axisx * _axisx) + (_t0)));
        dest.putDouble(destOffset + 8, ((_axisz) * (_t1) + (_t2 * _t3)));
        dest.putDouble(destOffset + 16, ((_t2) * (_t4) - (_axisy * _t1)));
        dest.putDouble(destOffset + 24, ((_t2) * (_t3) - (_axisz * _t1)));
        dest.putDouble(destOffset + 32, ((_t2) * (_axisy * _axisy) + (_t0)));
        dest.putDouble(destOffset + 40, ((_axisx) * (_t1) + (_t2 * _t5)));
        dest.putDouble(destOffset + 48, ((_axisy) * (_t1) + (_t2 * _t4)));
        dest.putDouble(destOffset + 56, ((_t2) * (_t5) - (_axisx * _t1)));
        dest.putDouble(destOffset + 64, ((_t2) * (_axisz * _axisz) + (_t0)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        return makeRotationLookAlong_lh_api_fma_s3f97fbc8_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s3f97fbc8_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.putDouble(destOffset + 16, _t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.putDouble(destOffset + 40, _t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, ((_t39) * (_t13) - (_t38 * _t11)));
        dest.putDouble(destOffset + 16, _t12);
        return makeRotationLookAlong_lh_api_mulAdd_se21d4cf9_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_se21d4cf9_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.putDouble(destOffset + 40, _t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t13, upY);
        double _t19 = java.lang.Math.fma(_t17, _t12, upX);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        return makeRotationLookAlong_rh_api_fma_s48e198b6_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s48e198b6_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.putDouble(destOffset + 16, -_t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.putDouble(destOffset + 40, -_t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.putDouble(destOffset + 64, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t13) + (upY));
        double _t19 = ((_t17) * (_t12) + (upX));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, ((_t38) * (_t11) - (_t39 * _t13)));
        dest.putDouble(destOffset + 16, -_t12);
        return makeRotationLookAlong_rh_api_mulAdd_s75d14d6f_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s75d14d6f_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.putDouble(destOffset + 40, -_t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.putDouble(destOffset + 64, -_t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_lh_api_fma_sef1062f2_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_sef1062f2_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.putDouble(destOffset + 16, _t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.putDouble(destOffset + 40, _t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_lh_api_mulAdd_s434491b1_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s434491b1_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, ((_t39) * (_t13) - (_t38 * _t11)));
        dest.putDouble(destOffset + 16, _t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.putDouble(destOffset + 40, _t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t19 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_rh_api_fma_s4a7ddb90_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s4a7ddb90_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.putDouble(destOffset + 16, -_t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.putDouble(destOffset + 40, -_t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.putDouble(destOffset + 64, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t13) + (_upy));
        double _t19 = ((_t17) * (_t12) + (_upx));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_rh_api_mulAdd_s24d9588f_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s24d9588f_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, ((_t38) * (_t11) - (_t39 * _t13)));
        dest.putDouble(destOffset + 16, -_t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.putDouble(destOffset + 40, -_t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.putDouble(destOffset + 64, -_t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        return makeRotationLookAlong_lh_api_fma_s3960771b_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s3960771b_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.putDouble(destOffset + 16, _t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.putDouble(destOffset + 40, _t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, ((_t39) * (_t13) - (_t38 * _t11)));
        dest.putDouble(destOffset + 16, _t12);
        return makeRotationLookAlong_lh_api_mulAdd_s769baa6a_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s769baa6a_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.putDouble(destOffset + 40, _t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t13, upY);
        double _t19 = java.lang.Math.fma(_t17, _t12, upX);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        return makeRotationLookAlong_rh_api_fma_s30114dcd_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s30114dcd_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.putDouble(destOffset + 16, -_t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.putDouble(destOffset + 40, -_t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.putDouble(destOffset + 64, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t13) + (upY));
        double _t19 = ((_t17) * (_t12) + (upX));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, ((_t38) * (_t11) - (_t39 * _t13)));
        dest.putDouble(destOffset + 16, -_t12);
        return makeRotationLookAlong_rh_api_mulAdd_saf09d8c_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_saf09d8c_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.putDouble(destOffset + 40, -_t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.putDouble(destOffset + 64, -_t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_lh_api_fma_sbf22fc99_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_sbf22fc99_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.putDouble(destOffset + 16, _t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.putDouble(destOffset + 40, _t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_lh_api_mulAdd_s57c6b232_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s57c6b232_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, ((_t39) * (_t13) - (_t38 * _t11)));
        dest.putDouble(destOffset + 16, _t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.putDouble(destOffset + 40, _t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t19 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_rh_api_fma_s100a78b3_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s100a78b3_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.putDouble(destOffset + 16, -_t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.putDouble(destOffset + 40, -_t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.putDouble(destOffset + 64, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t13) + (_upy));
        double _t19 = ((_t17) * (_t12) + (_upx));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_rh_api_mulAdd_sfb3028ac_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_sfb3028ac_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, ((_t38) * (_t11) - (_t39 * _t13)));
        dest.putDouble(destOffset + 16, -_t12);
        dest.putDouble(destOffset + 24, _t38);
        dest.putDouble(destOffset + 32, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.putDouble(destOffset + 40, -_t13);
        dest.putDouble(destOffset + 48, _t39);
        dest.putDouble(destOffset + 56, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.putDouble(destOffset + 64, -_t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_api(java.nio.ByteBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationQuat_api_mulAdd(dest, destOffset, qX, qY, qZ, qW);
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dest.putDouble(destOffset, java.lang.Math.fma(-2.0, java.lang.Math.fma(qY, qY, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(qX, qY, _t1));
        dest.putDouble(destOffset + 16, 2.0 * java.lang.Math.fma(qX, qZ, -_t2));
        dest.putDouble(destOffset + 24, 2.0 * java.lang.Math.fma(qX, qY, -_t1));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(-2.0, java.lang.Math.fma(qX, qX, _t0), 1.0));
        dest.putDouble(destOffset + 40, 2.0 * java.lang.Math.fma(qX, qW, qY * qZ));
        dest.putDouble(destOffset + 48, 2.0 * java.lang.Math.fma(qX, qZ, _t2));
        dest.putDouble(destOffset + 56, 2.0 * java.lang.Math.fma(qY, qZ, -(qX * qW)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(-2.0, java.lang.Math.fma(qX, qX, qY * qY), 1.0));
        return dest;
    }

    /** {@code makeRotationQuat_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationQuat_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dest.putDouble(destOffset, ((1.0) - (2.0) * (((qY) * (qY) + (_t0)))));
        dest.putDouble(destOffset + 8, 2.0 * ((qX) * (qY) + (_t1)));
        dest.putDouble(destOffset + 16, 2.0 * ((qX) * (qZ) - (_t2)));
        dest.putDouble(destOffset + 24, 2.0 * ((qX) * (qY) - (_t1)));
        dest.putDouble(destOffset + 32, ((1.0) - (2.0) * (((qX) * (qX) + (_t0)))));
        dest.putDouble(destOffset + 40, 2.0 * ((qX) * (qW) + (qY * qZ)));
        dest.putDouble(destOffset + 48, 2.0 * ((qX) * (qZ) + (_t2)));
        dest.putDouble(destOffset + 56, 2.0 * ((qY) * (qZ) - (qX * qW)));
        dest.putDouble(destOffset + 64, ((1.0) - (2.0) * (((qX) * (qX) + (qY * qY)))));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        Double3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationQuat_api_mulAdd(dest, destOffset, q, qOffset);
        double _qx = q.getDouble(qOffset);
        double _qy = q.getDouble(qOffset + 8);
        double _qz = q.getDouble(qOffset + 16);
        double _qw = q.getDouble(qOffset + 24);
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        dest.putDouble(destOffset, java.lang.Math.fma(-2.0, java.lang.Math.fma(_qy, _qy, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(_qx, _qy, _t1));
        dest.putDouble(destOffset + 16, 2.0 * java.lang.Math.fma(_qx, _qz, -_t2));
        dest.putDouble(destOffset + 24, 2.0 * java.lang.Math.fma(_qx, _qy, -_t1));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(-2.0, java.lang.Math.fma(_qx, _qx, _t0), 1.0));
        dest.putDouble(destOffset + 40, 2.0 * java.lang.Math.fma(_qx, _qw, _qy * _qz));
        dest.putDouble(destOffset + 48, 2.0 * java.lang.Math.fma(_qx, _qz, _t2));
        dest.putDouble(destOffset + 56, 2.0 * java.lang.Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(-2.0, java.lang.Math.fma(_qx, _qx, _qy * _qy), 1.0));
        return dest;
    }

    /** {@code makeRotationQuat_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationQuat_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        double _qx = q.getDouble(qOffset);
        double _qy = q.getDouble(qOffset + 8);
        double _qz = q.getDouble(qOffset + 16);
        double _qw = q.getDouble(qOffset + 24);
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        dest.putDouble(destOffset, ((1.0) - (2.0) * (((_qy) * (_qy) + (_t0)))));
        dest.putDouble(destOffset + 8, 2.0 * ((_qx) * (_qy) + (_t1)));
        dest.putDouble(destOffset + 16, 2.0 * ((_qx) * (_qz) - (_t2)));
        dest.putDouble(destOffset + 24, 2.0 * ((_qx) * (_qy) - (_t1)));
        dest.putDouble(destOffset + 32, ((1.0) - (2.0) * (((_qx) * (_qx) + (_t0)))));
        dest.putDouble(destOffset + 40, 2.0 * ((_qx) * (_qw) + (_qy * _qz)));
        dest.putDouble(destOffset + 48, 2.0 * ((_qx) * (_qz) + (_t2)));
        dest.putDouble(destOffset + 56, 2.0 * ((_qy) * (_qz) - (_qx * _qw)));
        dest.putDouble(destOffset + 64, ((1.0) - (2.0) * (((_qx) * (_qx) + (_qy * _qy)))));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, _t39);
        return makeRotationTowards_api_fma_s7d31ceef_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma_s7d31ceef_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 16, _t38);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 48, _t12);
        dest.putDouble(destOffset + 56, _t13);
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, _t39);
        dest.putDouble(destOffset + 16, _t38);
        return makeRotationTowards_api_mulAdd_s2f4ec874_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd_s2f4ec874_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 24, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.putDouble(destOffset + 32, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.putDouble(destOffset + 40, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.putDouble(destOffset + 48, _t12);
        dest.putDouble(destOffset + 56, _t13);
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationTowards_api_fma_s21abc90f_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma_s21abc90f_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, _t39);
        dest.putDouble(destOffset + 16, _t38);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 48, _t12);
        dest.putDouble(destOffset + 56, _t13);
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationTowards_api_mulAdd_s3d769cc6_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd_s3d769cc6_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, _t39);
        dest.putDouble(destOffset + 16, _t38);
        dest.putDouble(destOffset + 24, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.putDouble(destOffset + 32, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.putDouble(destOffset + 40, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.putDouble(destOffset + 48, _t12);
        dest.putDouble(destOffset + 56, _t13);
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, _t39);
        return makeRotationTowards_api_fma_sf4b47f0c_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma_sf4b47f0c_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 16, _t38);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 48, _t12);
        dest.putDouble(destOffset + 56, _t13);
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, _t39);
        dest.putDouble(destOffset + 16, _t38);
        return makeRotationTowards_api_mulAdd_s53220cd7_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd_s53220cd7_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 24, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.putDouble(destOffset + 32, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.putDouble(destOffset + 40, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.putDouble(destOffset + 48, _t12);
        dest.putDouble(destOffset + 56, _t13);
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationTowards_api_fma_s73fb92c_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_fma_s73fb92c_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, _t39);
        dest.putDouble(destOffset + 16, _t38);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 48, _t12);
        dest.putDouble(destOffset + 56, _t13);
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationTowards_api_mulAdd_sefeca3bd_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTowards_api_mulAdd_sefeca3bd_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, _t39);
        dest.putDouble(destOffset + 16, _t38);
        dest.putDouble(destOffset + 24, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.putDouble(destOffset + 32, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.putDouble(destOffset + 40, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.putDouble(destOffset + 48, _t12);
        dest.putDouble(destOffset + 56, _t13);
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t0);
        dest.putDouble(destOffset + 40, _t1);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, -_t1);
        dest.putDouble(destOffset + 64, _t0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_api(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXYZ_api_mulAdd(dest, destOffset, angleX, angleY, angleZ);
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t4 * _t3;
        double _t7 = _t3 * _t5;
        dest.putDouble(destOffset, _t0 * _t1);
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t6, _t1, _t2 * _t5));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t4, _t2, -(_t7 * _t1)));
        dest.putDouble(destOffset + 24, -_t2 * _t0);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t5, _t1, _t6 * -_t2));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t7, _t2, _t4 * _t1));
        dest.putDouble(destOffset + 48, _t3);
        dest.putDouble(destOffset + 56, -(_t4 * _t0));
        dest.putDouble(destOffset + 64, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t4 * _t3;
        double _t7 = _t3 * _t5;
        dest.putDouble(destOffset, _t0 * _t1);
        dest.putDouble(destOffset + 8, ((_t6) * (_t1) + (_t2 * _t5)));
        dest.putDouble(destOffset + 16, ((_t4) * (_t2) - (_t7 * _t1)));
        dest.putDouble(destOffset + 24, -_t2 * _t0);
        dest.putDouble(destOffset + 32, ((_t5) * (_t1) - (_t6 * _t2)));
        dest.putDouble(destOffset + 40, ((_t7) * (_t2) + (_t4 * _t1)));
        dest.putDouble(destOffset + 48, _t3);
        dest.putDouble(destOffset + 56, -(_t4 * _t0));
        dest.putDouble(destOffset + 64, _t5 * _t0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_api(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXZY_api_mulAdd(dest, destOffset, angleX, angleZ, angleY);
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.sinWithCos(angleX);
        double _t6 = _t2 * _t4;
        double _t7 = _t5 * _t2;
        dest.putDouble(destOffset, _t0 * _t1);
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t6, _t0, _t5 * _t3));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t7, _t0, -(_t3 * _t4)));
        dest.putDouble(destOffset + 24, -_t2);
        dest.putDouble(destOffset + 32, _t4 * _t1);
        dest.putDouble(destOffset + 40, _t5 * _t1);
        dest.putDouble(destOffset + 48, _t3 * _t1);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t6, _t3, -(_t5 * _t0)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_t7, _t3, _t4 * _t0));
        return dest;
    }

    /** {@code makeRotationXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.sinWithCos(angleX);
        double _t6 = _t2 * _t4;
        double _t7 = _t5 * _t2;
        dest.putDouble(destOffset, _t0 * _t1);
        dest.putDouble(destOffset + 8, ((_t6) * (_t0) + (_t5 * _t3)));
        dest.putDouble(destOffset + 16, ((_t7) * (_t0) - (_t3 * _t4)));
        dest.putDouble(destOffset + 24, -_t2);
        dest.putDouble(destOffset + 32, _t4 * _t1);
        dest.putDouble(destOffset + 40, _t5 * _t1);
        dest.putDouble(destOffset + 48, _t3 * _t1);
        dest.putDouble(destOffset + 56, ((_t6) * (_t3) - (_t5 * _t0)));
        dest.putDouble(destOffset + 64, ((_t7) * (_t3) + (_t4 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, _t0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, -_t1);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, _t1);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _t0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_api(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYXZ_api_mulAdd(dest, destOffset, angleY, angleX, angleZ);
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleY);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.cosWithSin(angleY);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        dest.putDouble(destOffset, java.lang.Math.fma(_t6, _t2, _t3 * _t4));
        dest.putDouble(destOffset + 8, _t2 * _t5);
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t7, _t2, -(_t1 * _t4)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t6, _t4, -(_t2 * _t3)));
        dest.putDouble(destOffset + 32, _t5 * _t4);
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t7, _t4, _t1 * _t2));
        dest.putDouble(destOffset + 48, _t1 * _t5);
        dest.putDouble(destOffset + 56, -_t0);
        dest.putDouble(destOffset + 64, _t5 * _t3);
        return dest;
    }

    /** {@code makeRotationYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleY);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.cosWithSin(angleY);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        dest.putDouble(destOffset, ((_t6) * (_t2) + (_t3 * _t4)));
        dest.putDouble(destOffset + 8, _t2 * _t5);
        dest.putDouble(destOffset + 16, ((_t7) * (_t2) - (_t1 * _t4)));
        dest.putDouble(destOffset + 24, ((_t6) * (_t4) - (_t2 * _t3)));
        dest.putDouble(destOffset + 32, _t5 * _t4);
        dest.putDouble(destOffset + 40, ((_t7) * (_t4) + (_t1 * _t2)));
        dest.putDouble(destOffset + 48, _t1 * _t5);
        dest.putDouble(destOffset + 56, -_t0);
        dest.putDouble(destOffset + 64, _t5 * _t3);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_api(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYZX_api_mulAdd(dest, destOffset, angleY, angleZ, angleX);
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleX);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.sinWithCos(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t4 * _t0;
        double _t7 = _t3 * _t4;
        dest.putDouble(destOffset, _t0 * _t1);
        dest.putDouble(destOffset + 8, _t4);
        dest.putDouble(destOffset + 16, -(_t3 * _t1));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t2, _t3, -(_t6 * _t5)));
        dest.putDouble(destOffset + 32, _t5 * _t1);
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t7, _t5, _t2 * _t0));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_t6, _t2, _t3 * _t5));
        dest.putDouble(destOffset + 56, -_t2 * _t1);
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_t5, _t0, _t7 * -_t2));
        return dest;
    }

    /** {@code makeRotationYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleX);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.sinWithCos(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t4 * _t0;
        double _t7 = _t3 * _t4;
        dest.putDouble(destOffset, _t0 * _t1);
        dest.putDouble(destOffset + 8, _t4);
        dest.putDouble(destOffset + 16, -(_t3 * _t1));
        dest.putDouble(destOffset + 24, ((_t2) * (_t3) - (_t6 * _t5)));
        dest.putDouble(destOffset + 32, _t5 * _t1);
        dest.putDouble(destOffset + 40, ((_t7) * (_t5) + (_t2 * _t0)));
        dest.putDouble(destOffset + 48, ((_t6) * (_t2) + (_t3 * _t5)));
        dest.putDouble(destOffset + 56, -_t2 * _t1);
        dest.putDouble(destOffset + 64, ((_t5) * (_t0) - (_t7 * _t2)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_api(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZXY_api_mulAdd(dest, destOffset, angleZ, angleX, angleY);
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleX);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.sinWithCos(angleY);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t2 * _t3;
        double _t7 = _t2 * _t1;
        dest.putDouble(destOffset, java.lang.Math.fma(_t0, _t1, _t6 * -_t4));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t7, _t4, _t3 * _t0));
        dest.putDouble(destOffset + 16, -_t4 * _t5);
        dest.putDouble(destOffset + 24, -(_t3 * _t5));
        dest.putDouble(destOffset + 32, _t5 * _t1);
        dest.putDouble(destOffset + 40, _t2);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_t6, _t0, _t4 * _t1));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t4, _t3, -(_t7 * _t0)));
        dest.putDouble(destOffset + 64, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleX);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.sinWithCos(angleY);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t2 * _t3;
        double _t7 = _t2 * _t1;
        dest.putDouble(destOffset, ((_t0) * (_t1) - (_t6 * _t4)));
        dest.putDouble(destOffset + 8, ((_t7) * (_t4) + (_t3 * _t0)));
        dest.putDouble(destOffset + 16, -_t4 * _t5);
        dest.putDouble(destOffset + 24, -(_t3 * _t5));
        dest.putDouble(destOffset + 32, _t5 * _t1);
        dest.putDouble(destOffset + 40, _t2);
        dest.putDouble(destOffset + 48, ((_t6) * (_t0) + (_t4 * _t1)));
        dest.putDouble(destOffset + 56, ((_t4) * (_t3) - (_t7 * _t0)));
        dest.putDouble(destOffset + 64, _t5 * _t0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_api(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZYX_api_mulAdd(dest, destOffset, angleZ, angleY, angleX);
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleY);
        double _t3 = Math.sinWithCos(angleX);
        double _t4 = Math.sinWithCos(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        dest.putDouble(destOffset, _t0 * _t1);
        dest.putDouble(destOffset + 8, _t4 * _t0);
        dest.putDouble(destOffset + 16, -_t2);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t6, _t3, -(_t4 * _t5)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t7, _t3, _t5 * _t1));
        dest.putDouble(destOffset + 40, _t3 * _t0);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_t6, _t5, _t3 * _t4));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_t7, _t5, -(_t3 * _t1)));
        dest.putDouble(destOffset + 64, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleY);
        double _t3 = Math.sinWithCos(angleX);
        double _t4 = Math.sinWithCos(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        dest.putDouble(destOffset, _t0 * _t1);
        dest.putDouble(destOffset + 8, _t4 * _t0);
        dest.putDouble(destOffset + 16, -_t2);
        dest.putDouble(destOffset + 24, ((_t6) * (_t3) - (_t4 * _t5)));
        dest.putDouble(destOffset + 32, ((_t7) * (_t3) + (_t5 * _t1)));
        dest.putDouble(destOffset + 40, _t3 * _t0);
        dest.putDouble(destOffset + 48, ((_t6) * (_t5) + (_t3 * _t4)));
        dest.putDouble(destOffset + 56, ((_t7) * (_t5) - (_t3 * _t1)));
        dest.putDouble(destOffset + 64, _t5 * _t0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        dest.putDouble(destOffset, vX);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, vY);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, _vx);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _vy);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, double s) {
        dest.putDouble(destOffset, s);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, s);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, vX);
        dest.putDouble(destOffset + 56, vY);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, _vx);
        dest.putDouble(destOffset + 56, _vy);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeView_unsafe(java.nio.ByteBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer makeView_api(java.nio.ByteBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dest.putDouble(destOffset, _t0_inv + _t0_inv);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t1_inv + _t1_inv);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, -(left + right) * _t0_inv);
        dest.putDouble(destOffset + 56, -(bottom + top) * _t1_inv);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self02, _t1, _self12 * _t0));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    /** {@code preRotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.putDouble(destOffset + 8, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.putDouble(destOffset + 32, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, ((_self02) * (_t0) - (_self12 * _t1)));
        dest.putDouble(destOffset + 56, ((_self02) * (_t1) + (_self12 * _t0)));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_fma(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@code preRotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        double _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        dest.putDouble(destOffset, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1))));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self20, _t11, java.lang.Math.fma(_self00, _t1, _self10 * _t0)));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self21, _t10, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1))));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self21, _t11, java.lang.Math.fma(_self01, _t1, _self11 * _t0)));
        return preRotateAround_api_fma_s8239c84d_1(dest, destOffset, _t0, _t1, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_fma_s8239c84d_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self22, _t10, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1))));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self02, _t1, _self12 * _t0)));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        double _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        dest.putDouble(destOffset, ((_self20) * (_t10) + (((_self00) * (_t0) - (_self10 * _t1)))));
        dest.putDouble(destOffset + 8, ((_self20) * (_t11) + (((_self00) * (_t1) + (_self10 * _t0)))));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, ((_self21) * (_t10) + (((_self01) * (_t0) - (_self11 * _t1)))));
        dest.putDouble(destOffset + 32, ((_self21) * (_t11) + (((_self01) * (_t1) + (_self11 * _t0)))));
        dest.putDouble(destOffset + 40, _self21);
        return preRotateAround_api_mulAdd_s4a4c5012_1(dest, destOffset, _t0, _t1, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd_s4a4c5012_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _self02, double _self12, double _self22, double _t10, double _t11) {
        dest.putDouble(destOffset + 48, ((_self22) * (_t10) + (((_self02) * (_t0) - (_self12 * _t1)))));
        dest.putDouble(destOffset + 56, ((_self22) * (_t11) + (((_self02) * (_t1) + (_self12 * _t0)))));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@code preRotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1);
        double _t11 = java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1));
        dest.putDouble(destOffset, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1))));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self20, _t11, java.lang.Math.fma(_self00, _t1, _self10 * _t0)));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self21, _t10, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1))));
        return preRotateAround_api_fma_s3fb203cc_1(dest, destOffset, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_fma_s3fb203cc_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self21, _t11, java.lang.Math.fma(_self01, _t1, _self11 * _t0)));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self22, _t10, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1))));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self02, _t1, _self12 * _t0)));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = ((_pivotx) * (_t9) + (_pivoty * _t1));
        double _t11 = ((_pivoty) * (_t9) - (_pivotx * _t1));
        dest.putDouble(destOffset, ((_self20) * (_t10) + (((_self00) * (_t0) - (_self10 * _t1)))));
        dest.putDouble(destOffset + 8, ((_self20) * (_t11) + (((_self00) * (_t1) + (_self10 * _t0)))));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, ((_self21) * (_t10) + (((_self01) * (_t0) - (_self11 * _t1)))));
        dest.putDouble(destOffset + 32, ((_self21) * (_t11) + (((_self01) * (_t1) + (_self11 * _t0)))));
        return preRotateAround_api_mulAdd_s56cb368f_1(dest, destOffset, _t0, _t1, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd_s56cb368f_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, ((_self22) * (_t10) + (((_self02) * (_t0) - (_self12 * _t1)))));
        dest.putDouble(destOffset + 56, ((_self22) * (_t11) + (((_self02) * (_t1) + (_self12 * _t0)))));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_api_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@code preRotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = 1.0 - _t1;
        double _t3 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t7 = axisY * axisZ;
        double _t18 = java.lang.Math.fma(_t2, axisX * axisX, _t1);
        double _t21 = java.lang.Math.fma(axisY, _t0, _t2 * _t3);
        double _t24 = java.lang.Math.fma(_t2, _t5, -(axisZ * _t0));
        dest.putDouble(destOffset, java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24)));
        return preRotateAxis_api_fma_s8d9cb893_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, java.lang.Math.fma(_t2, axisY * axisY, _t1), java.lang.Math.fma(_t2, axisZ * axisZ, _t1), _t21, java.lang.Math.fma(axisZ, _t0, _t2 * _t5), java.lang.Math.fma(axisX, _t0, _t2 * _t7), _t24, java.lang.Math.fma(_t2, _t7, -(axisX * _t0)), java.lang.Math.fma(_t2, _t3, -(axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_fma_s8d9cb893_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    /** {@code preRotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = 1.0 - _t1;
        double _t3 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t7 = axisY * axisZ;
        double _t18 = ((_t2) * (axisX * axisX) + (_t1));
        double _t21 = ((axisY) * (_t0) + (_t2 * _t3));
        double _t24 = ((_t2) * (_t5) - (axisZ * _t0));
        dest.putDouble(destOffset, ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24)))));
        return preRotateAxis_api_mulAdd_sc9670598_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, ((_t2) * (axisY * axisY) + (_t1)), ((_t2) * (axisZ * axisZ) + (_t1)), _t21, ((axisZ) * (_t0) + (_t2 * _t5)), ((axisX) * (_t0) + (_t2 * _t7)), _t24, ((_t2) * (_t7) - (axisX * _t0)), ((_t2) * (_t3) - (axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_mulAdd_sc9670598_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset + 8, ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19)))));
        dest.putDouble(destOffset + 16, ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23)))));
        dest.putDouble(destOffset + 24, ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24)))));
        dest.putDouble(destOffset + 32, ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19)))));
        dest.putDouble(destOffset + 40, ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23)))));
        dest.putDouble(destOffset + 48, ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24)))));
        dest.putDouble(destOffset + 56, ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23)))));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@code preRotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = 1.0 - _t1;
        double _t3 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t7 = _axisy * _axisz;
        return preRotateAxis_api_fma_sce522dca_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_t2, _axisx * _axisx, _t1), java.lang.Math.fma(_t2, _axisy * _axisy, _t1), java.lang.Math.fma(_t2, _axisz * _axisz, _t1), java.lang.Math.fma(_axisy, _t0, _t2 * _t3), java.lang.Math.fma(_axisz, _t0, _t2 * _t5), java.lang.Math.fma(_axisx, _t0, _t2 * _t7), java.lang.Math.fma(_t2, _t5, -(_axisz * _t0)), java.lang.Math.fma(_t2, _t7, -(_axisx * _t0)), java.lang.Math.fma(_t2, _t3, -(_axisy * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_fma_sce522dca_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset, java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    /** {@code preRotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = 1.0 - _t1;
        double _t3 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t7 = _axisy * _axisz;
        return preRotateAxis_api_mulAdd_sc2603c55_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_t2) * (_axisx * _axisx) + (_t1)), ((_t2) * (_axisy * _axisy) + (_t1)), ((_t2) * (_axisz * _axisz) + (_t1)), ((_axisy) * (_t0) + (_t2 * _t3)), ((_axisz) * (_t0) + (_t2 * _t5)), ((_axisx) * (_t0) + (_t2 * _t7)), ((_t2) * (_t5) - (_axisz * _t0)), ((_t2) * (_t7) - (_axisx * _t0)), ((_t2) * (_t3) - (_axisy * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_mulAdd_sc2603c55_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset, ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24)))));
        dest.putDouble(destOffset + 8, ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19)))));
        dest.putDouble(destOffset + 16, ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23)))));
        dest.putDouble(destOffset + 24, ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24)))));
        dest.putDouble(destOffset + 32, ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19)))));
        dest.putDouble(destOffset + 40, ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23)))));
        dest.putDouble(destOffset + 48, ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24)))));
        dest.putDouble(destOffset + 56, ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23)))));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateX_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _t0, -(_self20 * _t1)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self10, _t1, _self20 * _t0));
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self11, _t0, -(_self21 * _t1)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self11, _t1, _self21 * _t0));
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t0, -(_self22 * _t1)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self12, _t1, _self22 * _t0));
        return dest;
    }

    /** {@code preRotateX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, ((_self10) * (_t0) - (_self20 * _t1)));
        dest.putDouble(destOffset + 16, ((_self10) * (_t1) + (_self20 * _t0)));
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, ((_self11) * (_t0) - (_self21 * _t1)));
        dest.putDouble(destOffset + 40, ((_self11) * (_t1) + (_self21 * _t0)));
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, ((_self12) * (_t0) - (_self22 * _t1)));
        dest.putDouble(destOffset + 64, ((_self12) * (_t1) + (_self22 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateY_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, _self20 * _t1));
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self20, _t0, -(_self00 * _t1)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self01, _t0, _self21 * _t1));
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self21, _t0, -(_self01 * _t1)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t0, _self22 * _t1));
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t0, -(_self02 * _t1)));
        return dest;
    }

    /** {@code preRotateY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, ((_self00) * (_t0) + (_self20 * _t1)));
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, ((_self20) * (_t0) - (_self00 * _t1)));
        dest.putDouble(destOffset + 24, ((_self01) * (_t0) + (_self21 * _t1)));
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, ((_self21) * (_t0) - (_self01 * _t1)));
        dest.putDouble(destOffset + 48, ((_self02) * (_t0) + (_self22 * _t1)));
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, ((_self22) * (_t0) - (_self02 * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0 * vX);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1 * vY);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0 * _vx);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1 * _vy);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, s * _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, s * _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.putDouble(destOffset, java.lang.Math.fma(s, _self00, _self20 * _t1));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(s, _self10, _self20 * _t2));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(s, _self01, _self21 * _t1));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(s, _self11, _self21 * _t2));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(s, _self02, _self22 * _t1));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(s, _self12, _self22 * _t2));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.putDouble(destOffset, ((s) * (_self00) + (_self20 * _t1)));
        dest.putDouble(destOffset + 8, ((s) * (_self10) + (_self20 * _t2)));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, ((s) * (_self01) + (_self21 * _t1)));
        dest.putDouble(destOffset + 32, ((s) * (_self11) + (_self21 * _t2)));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, ((s) * (_self02) + (_self22 * _t1)));
        dest.putDouble(destOffset + 56, ((s) * (_self12) + (_self22 * _t2)));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivot.getDouble(pivotOffset) * _t0;
        double _t2 = pivot.getDouble(pivotOffset + 8) * _t0;
        dest.putDouble(destOffset, java.lang.Math.fma(s, _self00, _self20 * _t1));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(s, _self10, _self20 * _t2));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(s, _self01, _self21 * _t1));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(s, _self11, _self21 * _t2));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(s, _self02, _self22 * _t1));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(s, _self12, _self22 * _t2));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivot.getDouble(pivotOffset) * _t0;
        double _t2 = pivot.getDouble(pivotOffset + 8) * _t0;
        dest.putDouble(destOffset, ((s) * (_self00) + (_self20 * _t1)));
        dest.putDouble(destOffset + 8, ((s) * (_self10) + (_self20 * _t2)));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, ((s) * (_self01) + (_self21 * _t1)));
        dest.putDouble(destOffset + 32, ((s) * (_self11) + (_self21 * _t2)));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, ((s) * (_self02) + (_self22 * _t1)));
        dest.putDouble(destOffset + 56, ((s) * (_self12) + (_self22 * _t2)));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.putDouble(destOffset, java.lang.Math.fma(sX, _self00, _self20 * _t2));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(sY, _self10, _self20 * _t3));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(sX, _self01, _self21 * _t2));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(sY, _self11, _self21 * _t3));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(sX, _self02, _self22 * _t2));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(sY, _self12, _self22 * _t3));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.putDouble(destOffset, ((sX) * (_self00) + (_self20 * _t2)));
        dest.putDouble(destOffset + 8, ((sY) * (_self10) + (_self20 * _t3)));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, ((sX) * (_self01) + (_self21 * _t2)));
        dest.putDouble(destOffset + 32, ((sY) * (_self11) + (_self21 * _t3)));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, ((sX) * (_self02) + (_self22 * _t2)));
        dest.putDouble(destOffset + 56, ((sY) * (_self12) + (_self22 * _t3)));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@code preScaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = pivot.getDouble(pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.getDouble(pivotOffset + 8) * (1.0 - _sy);
        dest.putDouble(destOffset, java.lang.Math.fma(_sx, _self00, _self20 * _t2));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_sy, _self10, _self20 * _t3));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_sx, _self01, _self21 * _t2));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_sy, _self11, _self21 * _t3));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_sx, _self02, _self22 * _t2));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_sy, _self12, _self22 * _t3));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = pivot.getDouble(pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.getDouble(pivotOffset + 8) * (1.0 - _sy);
        dest.putDouble(destOffset, ((_sx) * (_self00) + (_self20 * _t2)));
        dest.putDouble(destOffset + 8, ((_sy) * (_self10) + (_self20 * _t3)));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, ((_sx) * (_self01) + (_self21 * _t2)));
        dest.putDouble(destOffset + 32, ((_sy) * (_self11) + (_self21 * _t3)));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, ((_sx) * (_self02) + (_self22 * _t2)));
        dest.putDouble(destOffset + 56, ((_sy) * (_self12) + (_self22 * _t3)));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                double _eself0 = src.getDouble(srcOffset + _lo * 8);
                double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
                double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
                dest.putDouble(destOffset + _lo * 8, java.lang.Math.fma(_eself2, vX, _eself0));
                dest.putDouble(destOffset + (_lo + 1) * 8, java.lang.Math.fma(_eself2, vY, _eself1));
                dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            }
            return dest;
        } else {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                double _eself0 = src.getDouble(srcOffset + _lo * 8);
                double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
                double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
                dest.putDouble(destOffset + _lo * 8, ((_eself2) * (vX) + (_eself0)));
                dest.putDouble(destOffset + (_lo + 1) * 8, ((_eself2) * (vY) + (_eself1)));
                dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                double _eself0 = src.getDouble(srcOffset + _lo * 8);
                double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
                double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
                dest.putDouble(destOffset + _lo * 8, java.lang.Math.fma(_eself2, _vx, _eself0));
                dest.putDouble(destOffset + (_lo + 1) * 8, java.lang.Math.fma(_eself2, _vy, _eself1));
                dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            }
            return dest;
        } else {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                double _eself0 = src.getDouble(srcOffset + _lo * 8);
                double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
                double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
                dest.putDouble(destOffset + _lo * 8, ((_eself2) * (_vx) + (_eself0)));
                dest.putDouble(destOffset + (_lo + 1) * 8, ((_eself2) * (_vy) + (_eself1)));
                dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putDouble(destOffset + 8, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putDouble(destOffset + 16, ((_self20) * (_t0) + (_self21 * _t1)));
        dest.putDouble(destOffset + 24, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putDouble(destOffset + 32, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.putDouble(destOffset + 40, ((_self21) * (_t0) - (_self20 * _t1)));
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_fma(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@code rotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        return rotateAround_api_fma_sd4405654_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(pivotX, _t9, pivotY * _t1), java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_fma_sd4405654_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self21, _t11, _self22)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.putDouble(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putDouble(destOffset + 8, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putDouble(destOffset + 16, ((_self20) * (_t0) + (_self21 * _t1)));
        dest.putDouble(destOffset + 24, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putDouble(destOffset + 32, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.putDouble(destOffset + 40, ((_self21) * (_t0) - (_self20 * _t1)));
        return rotateAround_api_mulAdd_s93d028d_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((pivotX) * (_t9) + (pivotY * _t1)), ((pivotY) * (_t9) - (pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd_s93d028d_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        dest.putDouble(destOffset + 48, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.putDouble(destOffset + 56, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        dest.putDouble(destOffset + 64, ((_self20) * (_t10) + (((_self21) * (_t11) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@code rotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        return rotateAround_api_fma_s61a58619_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1), java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_fma_s61a58619_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self21, _t11, _self22)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.putDouble(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putDouble(destOffset + 8, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putDouble(destOffset + 16, ((_self20) * (_t0) + (_self21 * _t1)));
        dest.putDouble(destOffset + 24, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putDouble(destOffset + 32, ((_self11) * (_t0) - (_self10 * _t1)));
        return rotateAround_api_mulAdd_s14481c0c_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_pivotx) * (_t9) + (_pivoty * _t1)), ((_pivoty) * (_t9) - (_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd_s14481c0c_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        dest.putDouble(destOffset + 40, ((_self21) * (_t0) - (_self20 * _t1)));
        dest.putDouble(destOffset + 48, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.putDouble(destOffset + 56, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        dest.putDouble(destOffset + 64, ((_self20) * (_t10) + (((_self21) * (_t11) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@code rotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = 1.0 - _t0;
        double _t3 = axisX * axisZ;
        double _t6 = axisX * axisY;
        double _t7 = axisY * axisZ;
        double _t18 = java.lang.Math.fma(_t2, axisX * axisX, _t0);
        double _t21 = java.lang.Math.fma(axisZ, _t1, _t2 * _t6);
        double _t24 = java.lang.Math.fma(_t2, _t3, -(axisY * _t1));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21)));
        return rotateAxis_api_fma_sefd7ac9a_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, java.lang.Math.fma(_t2, axisY * axisY, _t0), java.lang.Math.fma(_t2, axisZ * axisZ, _t0), _t21, java.lang.Math.fma(axisX, _t1, _t2 * _t7), java.lang.Math.fma(axisY, _t1, _t2 * _t3), _t24, java.lang.Math.fma(_t2, _t6, -(axisZ * _t1)), java.lang.Math.fma(_t2, _t7, -(axisX * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_fma_sefd7ac9a_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = 1.0 - _t0;
        double _t3 = axisX * axisZ;
        double _t6 = axisX * axisY;
        double _t7 = axisY * axisZ;
        double _t18 = ((_t2) * (axisX * axisX) + (_t0));
        double _t21 = ((axisZ) * (_t1) + (_t2 * _t6));
        double _t24 = ((_t2) * (_t3) - (axisY * _t1));
        dest.putDouble(destOffset, ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21)))));
        return rotateAxis_api_mulAdd_se051f3b_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, ((_t2) * (axisY * axisY) + (_t0)), ((_t2) * (axisZ * axisZ) + (_t0)), _t21, ((axisX) * (_t1) + (_t2 * _t7)), ((axisY) * (_t1) + (_t2 * _t3)), _t24, ((_t2) * (_t6) - (axisZ * _t1)), ((_t2) * (_t7) - (axisX * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd_se051f3b_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset + 8, ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@code rotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = 1.0 - _t0;
        double _t3 = _axisx * _axisz;
        double _t6 = _axisx * _axisy;
        double _t7 = _axisy * _axisz;
        return rotateAxis_api_fma_s42453b27_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_t2, _axisx * _axisx, _t0), java.lang.Math.fma(_t2, _axisy * _axisy, _t0), java.lang.Math.fma(_t2, _axisz * _axisz, _t0), java.lang.Math.fma(_axisz, _t1, _t2 * _t6), java.lang.Math.fma(_axisx, _t1, _t2 * _t7), java.lang.Math.fma(_axisy, _t1, _t2 * _t3), java.lang.Math.fma(_t2, _t3, -(_axisy * _t1)), java.lang.Math.fma(_t2, _t6, -(_axisz * _t1)), java.lang.Math.fma(_t2, _t7, -(_axisx * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_fma_s42453b27_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = 1.0 - _t0;
        double _t3 = _axisx * _axisz;
        double _t6 = _axisx * _axisy;
        double _t7 = _axisy * _axisz;
        return rotateAxis_api_mulAdd_s2fc0bf52_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_t2) * (_axisx * _axisx) + (_t0)), ((_t2) * (_axisy * _axisy) + (_t0)), ((_t2) * (_axisz * _axisz) + (_t0)), ((_axisz) * (_t1) + (_t2 * _t6)), ((_axisx) * (_t1) + (_t2 * _t7)), ((_axisy) * (_t1) + (_t2 * _t3)), ((_t2) * (_t3) - (_axisy * _t1)), ((_t2) * (_t6) - (_axisz * _t1)), ((_t2) * (_t7) - (_axisx * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd_s2fc0bf52_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset, ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        return rotateTowards_api_fma_sddfa4cf6_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_sddfa4cf6_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t39, _t12, -(_t38 * _t13));
        double _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        double _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        return rotateTowards_api_fma_sddfa4cf6_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_sddfa4cf6_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        return rotateTowards_api_mulAdd_s77eccb67_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_s77eccb67_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32) {
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        double _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        double _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        dest.putDouble(destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        return rotateTowards_api_mulAdd_s77eccb67_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_s77eccb67_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13) {
        dest.putDouble(destOffset + 48, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        return rotateTowards_api_fma_s224fa7d0_1(dest, destOffset, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t17, _t12, _upx), java.lang.Math.fma(_t17, _t13, _upy), java.lang.Math.fma(_t17, _t11, _upz));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s224fa7d0_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20) {
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        return rotateTowards_api_fma_s224fa7d0_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)), java.lang.Math.fma(_t37, _t13, -(_t39 * _t11)), java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s224fa7d0_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        return rotateTowards_api_mulAdd_sce791f87_1(dest, destOffset, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t12) + (_upx)), ((_t17) * (_t13) + (_upy)), ((_t17) * (_t11) + (_upz)));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sce791f87_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20) {
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        double _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        double _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        dest.putDouble(destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        return rotateTowards_api_mulAdd_sce791f87_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sce791f87_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        dest.putDouble(destOffset + 32, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        return rotateTowards_api_fma_s68978d8d_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s68978d8d_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t39, _t12, -(_t38 * _t13));
        double _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        double _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        return rotateTowards_api_fma_s68978d8d_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s68978d8d_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        return rotateTowards_api_mulAdd_sb86cdfb4_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sb86cdfb4_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32) {
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        double _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        double _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        dest.putDouble(destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        return rotateTowards_api_mulAdd_sb86cdfb4_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sb86cdfb4_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13) {
        dest.putDouble(destOffset + 48, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        return rotateTowards_api_fma_sb1148773_1(dest, destOffset, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t17, java.lang.Math.fma(_t17, _t12, _upx), java.lang.Math.fma(_t17, _t13, _upy));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_sb1148773_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t17, double _t18, double _t19) {
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        return rotateTowards_api_fma_sb1148773_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)), java.lang.Math.fma(_t37, _t13, -(_t39 * _t11)), java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_sb1148773_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        return rotateTowards_api_mulAdd_s6c9d3854_1(dest, destOffset, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t12) + (_upx)), ((_t17) * (_t13) + (_upy)), ((_t17) * (_t11) + (_upz)));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_s6c9d3854_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20) {
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        double _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        double _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        dest.putDouble(destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        return rotateTowards_api_mulAdd_s6c9d3854_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_s6c9d3854_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        dest.putDouble(destOffset + 32, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateX_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self01, _t0, _self02 * _t1));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self11, _t0, _self12 * _t1));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self21, _t0, _self22 * _t1));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t0, -(_self01 * _t1)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t0, -(_self11 * _t1)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t0, -(_self21 * _t1)));
        return dest;
    }

    /** {@code rotateX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, ((_self01) * (_t0) + (_self02 * _t1)));
        dest.putDouble(destOffset + 32, ((_self11) * (_t0) + (_self12 * _t1)));
        dest.putDouble(destOffset + 40, ((_self21) * (_t0) + (_self22 * _t1)));
        dest.putDouble(destOffset + 48, ((_self02) * (_t0) - (_self01 * _t1)));
        dest.putDouble(destOffset + 56, ((_self12) * (_t0) - (_self11 * _t1)));
        dest.putDouble(destOffset + 64, ((_self22) * (_t0) - (_self21 * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX180_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateX180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX180_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, -_self01);
        dest.putDouble(destOffset + 32, -_self11);
        dest.putDouble(destOffset + 40, -_self21);
        dest.putDouble(destOffset + 48, -_self02);
        dest.putDouble(destOffset + 56, -_self12);
        dest.putDouble(destOffset + 64, -_self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX270_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateX270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX270_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, -_self02);
        dest.putDouble(destOffset + 32, -_self12);
        dest.putDouble(destOffset + 40, -_self22);
        dest.putDouble(destOffset + 48, _self01);
        dest.putDouble(destOffset + 56, _self11);
        dest.putDouble(destOffset + 64, _self21);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX90_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateX90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX90_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self02);
        dest.putDouble(destOffset + 32, _self12);
        dest.putDouble(destOffset + 40, _self22);
        dest.putDouble(destOffset + 48, -_self01);
        dest.putDouble(destOffset + 56, -_self11);
        dest.putDouble(destOffset + 64, -_self21);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_api_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t2 = Math.sinWithCos(angleY);
        double _t3 = Math.cosWithSin(angleX);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t2 * _t3;
        double _t7 = _t5 * _t4;
        double _t8 = _t0 * _t2;
        double _t18 = java.lang.Math.fma(_t8, _t4, _t1 * _t3);
        double _t20 = java.lang.Math.fma(_t0, _t1, -(_t6 * _t4));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t7, _self01 * _t18)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t7, _self11 * _t18)));
        return rotateXYZ_api_fma_see905d02_1(dest, destOffset, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t1 * _t5, _t3 * _t5, _t0 * _t5, _t18, java.lang.Math.fma(_t6, _t1, _t0 * _t4), _t20, java.lang.Math.fma(_t3, _t4, -(_t8 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_fma_see905d02_1(java.nio.ByteBuffer dest, int destOffset, double _t2, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t7, _self21 * _t18)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self01, _t21, -(_self00 * _t11))));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self11, _t21, -(_self10 * _t11))));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self21, _t21, -(_self20 * _t11))));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t2, -(_self01 * _t13))));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t2, -(_self11 * _t13))));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t2, -(_self21 * _t13))));
        return dest;
    }

    /** {@code rotateXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t2 = Math.sinWithCos(angleY);
        double _t3 = Math.cosWithSin(angleX);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t2 * _t3;
        double _t7 = _t5 * _t4;
        double _t8 = _t0 * _t2;
        double _t18 = ((_t8) * (_t4) + (_t1 * _t3));
        double _t20 = ((_t0) * (_t1) - (_t6 * _t4));
        dest.putDouble(destOffset, ((_self02) * (_t20) + (((_self00) * (_t7) + (_self01 * _t18)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t20) + (((_self10) * (_t7) + (_self11 * _t18)))));
        return rotateXYZ_api_mulAdd_s78b80749_1(dest, destOffset, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t1 * _t5, _t3 * _t5, _t0 * _t5, _t18, ((_t6) * (_t1) + (_t0 * _t4)), _t20, ((_t3) * (_t4) - (_t8 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_mulAdd_s78b80749_1(java.nio.ByteBuffer dest, int destOffset, double _t2, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, ((_self22) * (_t20) + (((_self20) * (_t7) + (_self21 * _t18)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t19) + (((_self01) * (_t21) - (_self00 * _t11)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t19) + (((_self11) * (_t21) - (_self10 * _t11)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t19) + (((_self21) * (_t21) - (_self20 * _t11)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t12) + (((_self00) * (_t2) - (_self01 * _t13)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t12) + (((_self10) * (_t2) - (_self11 * _t13)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t12) + (((_self20) * (_t2) - (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_api_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.cosWithSin(angleZ);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t0 * _t1;
        double _t8 = _t2 * _t5;
        double _t9 = _t1 * _t4;
        double _t18 = java.lang.Math.fma(_t9, _t2, _t0 * _t3);
        double _t20 = java.lang.Math.fma(_t6, _t2, -(_t3 * _t4));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t8, _self01 * _t18)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t8, _self11 * _t18)));
        return rotateXZY_api_fma_se99e10f2_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t8, _t0 * _t5, _t4 * _t5, _t3 * _t5, _t18, java.lang.Math.fma(_t6, _t3, _t4 * _t2), _t20, java.lang.Math.fma(_t9, _t3, -(_t0 * _t2)));
    }

    /** Piece 2 of {@code rotateXZY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_fma_se99e10f2_1(java.nio.ByteBuffer dest, int destOffset, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t8, double _t11, double _t12, double _t14, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t8, _self21 * _t18)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self01, _t12, -(_self00 * _t1))));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self11, _t12, -(_self10 * _t1))));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self21, _t12, -(_self20 * _t1))));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t14, _self01 * _t21)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t14, _self11 * _t21)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t14, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.cosWithSin(angleZ);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t0 * _t1;
        double _t8 = _t2 * _t5;
        double _t9 = _t1 * _t4;
        double _t18 = ((_t9) * (_t2) + (_t0 * _t3));
        double _t20 = ((_t6) * (_t2) - (_t3 * _t4));
        dest.putDouble(destOffset, ((_self02) * (_t20) + (((_self00) * (_t8) + (_self01 * _t18)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t20) + (((_self10) * (_t8) + (_self11 * _t18)))));
        return rotateXZY_api_mulAdd_s6bf69a59_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t8, _t0 * _t5, _t4 * _t5, _t3 * _t5, _t18, ((_t6) * (_t3) + (_t4 * _t2)), _t20, ((_t9) * (_t3) - (_t0 * _t2)));
    }

    /** Piece 2 of {@code rotateXZY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_mulAdd_s6bf69a59_1(java.nio.ByteBuffer dest, int destOffset, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t8, double _t11, double _t12, double _t14, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, ((_self22) * (_t20) + (((_self20) * (_t8) + (_self21 * _t18)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t11) + (((_self01) * (_t12) - (_self00 * _t1)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t11) + (((_self11) * (_t12) - (_self10 * _t1)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t11) + (((_self21) * (_t12) - (_self20 * _t1)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t19) + (((_self00) * (_t14) + (_self01 * _t21)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t19) + (((_self10) * (_t14) + (_self11 * _t21)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t19) + (((_self20) * (_t14) + (_self21 * _t21)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateY_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, -(_self02 * _t1)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _t0, -(_self12 * _t1)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self20, _t0, -(_self22 * _t1)));
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self00, _t1, _self02 * _t0));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self10, _t1, _self12 * _t0));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self20, _t1, _self22 * _t0));
        return dest;
    }

    /** {@code rotateY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, ((_self00) * (_t0) - (_self02 * _t1)));
        dest.putDouble(destOffset + 8, ((_self10) * (_t0) - (_self12 * _t1)));
        dest.putDouble(destOffset + 16, ((_self20) * (_t0) - (_self22 * _t1)));
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, ((_self00) * (_t1) + (_self02 * _t0)));
        dest.putDouble(destOffset + 56, ((_self10) * (_t1) + (_self12 * _t0)));
        dest.putDouble(destOffset + 64, ((_self20) * (_t1) + (_self22 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY180_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateY180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY180_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, -_self00);
        dest.putDouble(destOffset + 8, -_self10);
        dest.putDouble(destOffset + 16, -_self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, -_self02);
        dest.putDouble(destOffset + 56, -_self12);
        dest.putDouble(destOffset + 64, -_self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY270_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateY270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY270_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self02);
        dest.putDouble(destOffset + 8, _self12);
        dest.putDouble(destOffset + 16, _self22);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, -_self00);
        dest.putDouble(destOffset + 56, -_self10);
        dest.putDouble(destOffset + 64, -_self20);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY90_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateY90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY90_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, -_self02);
        dest.putDouble(destOffset + 8, -_self12);
        dest.putDouble(destOffset + 16, -_self22);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, _self00);
        dest.putDouble(destOffset + 56, _self10);
        dest.putDouble(destOffset + 64, _self20);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_api_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t2 * _t5;
        double _t18 = java.lang.Math.fma(_t8, _t2, _t1 * _t4);
        double _t20 = java.lang.Math.fma(_t6, _t2, -(_t3 * _t4));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t18, _self01 * _t10)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t18, _self11 * _t10)));
        return rotateYXZ_api_fma_s544792f2_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t5 * _t4, _t5 * _t1, _t3 * _t5, _t18, java.lang.Math.fma(_t6, _t4, _t3 * _t2), _t20, java.lang.Math.fma(_t8, _t4, -(_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_fma_s544792f2_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t13, double _t14, double _t15, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t18, _self21 * _t10)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t21, _self01 * _t13)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t21, _self11 * _t13)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t21, _self21 * _t13)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self00, _t15, -(_self01 * _t0))));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t14, java.lang.Math.fma(_self10, _t15, -(_self11 * _t0))));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self20, _t15, -(_self21 * _t0))));
        return dest;
    }

    /** {@code rotateYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t2 * _t5;
        double _t18 = ((_t8) * (_t2) + (_t1 * _t4));
        double _t20 = ((_t6) * (_t2) - (_t3 * _t4));
        dest.putDouble(destOffset, ((_self02) * (_t20) + (((_self00) * (_t18) + (_self01 * _t10)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_t20) + (((_self10) * (_t18) + (_self11 * _t10)))));
        return rotateYXZ_api_mulAdd_s152fcba5_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t5 * _t4, _t5 * _t1, _t3 * _t5, _t18, ((_t6) * (_t4) + (_t3 * _t2)), _t20, ((_t8) * (_t4) - (_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_mulAdd_s152fcba5_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t13, double _t14, double _t15, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, ((_self22) * (_t20) + (((_self20) * (_t18) + (_self21 * _t10)))));
        dest.putDouble(destOffset + 24, ((_self02) * (_t19) + (((_self00) * (_t21) + (_self01 * _t13)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t19) + (((_self10) * (_t21) + (_self11 * _t13)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t19) + (((_self20) * (_t21) + (_self21 * _t13)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t14) + (((_self00) * (_t15) - (_self01 * _t0)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t14) + (((_self10) * (_t15) - (_self11 * _t0)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t14) + (((_self20) * (_t15) - (_self21 * _t0)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_api_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.sinWithCos(angleX);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t0 * _t1;
        double _t7 = _t2 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t3 * _t2;
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, -_t6, java.lang.Math.fma(_self00, _t7, _self01 * _t3)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, -_t6, java.lang.Math.fma(_self10, _t7, _self11 * _t3)));
        return rotateYZX_api_fma_sa75c85b2_1(dest, destOffset, _t3, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, java.lang.Math.fma(_t8, _t4, _t5 * _t2), java.lang.Math.fma(_t10, _t5, _t0 * _t4), java.lang.Math.fma(_t5, _t0, -(_t10 * _t4)), java.lang.Math.fma(_t4, _t2, -(_t8 * _t5)));
    }

    /** Piece 2 of {@code rotateYZX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_fma_sa75c85b2_1(java.nio.ByteBuffer dest, int destOffset, double _t3, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t7, double _t11, double _t13, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, -_t6, java.lang.Math.fma(_self20, _t7, _self21 * _t3)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t18, java.lang.Math.fma(_self00, _t20, _self01 * _t11)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t18, java.lang.Math.fma(_self10, _t20, _self11 * _t11)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t18, java.lang.Math.fma(_self20, _t20, _self21 * _t11)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t21, java.lang.Math.fma(_self00, _t19, -(_self01 * _t13))));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t21, java.lang.Math.fma(_self10, _t19, -(_self11 * _t13))));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self20, _t19, -(_self21 * _t13))));
        return dest;
    }

    /** {@code rotateYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.sinWithCos(angleX);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t0 * _t1;
        double _t7 = _t2 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t3 * _t2;
        dest.putDouble(destOffset, ((((_self00) * (_t7) + (_self01 * _t3))) - (_self02) * (_t6)));
        dest.putDouble(destOffset + 8, ((((_self10) * (_t7) + (_self11 * _t3))) - (_self12) * (_t6)));
        return rotateYZX_api_mulAdd_scd14a6b1_1(dest, destOffset, _t3, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, ((_t8) * (_t4) + (_t5 * _t2)), ((_t10) * (_t5) + (_t0 * _t4)), ((_t5) * (_t0) - (_t10 * _t4)), ((_t4) * (_t2) - (_t8 * _t5)));
    }

    /** Piece 2 of {@code rotateYZX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_mulAdd_scd14a6b1_1(java.nio.ByteBuffer dest, int destOffset, double _t3, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t7, double _t11, double _t13, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, ((((_self20) * (_t7) + (_self21 * _t3))) - (_self22) * (_t6)));
        dest.putDouble(destOffset + 24, ((_self02) * (_t18) + (((_self00) * (_t20) + (_self01 * _t11)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t18) + (((_self10) * (_t20) + (_self11 * _t11)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t18) + (((_self20) * (_t20) + (_self21 * _t11)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t21) + (((_self00) * (_t19) - (_self01 * _t13)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t21) + (((_self10) * (_t19) - (_self11 * _t13)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t21) + (((_self20) * (_t19) - (_self21 * _t13)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ180_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateZ180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ180_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, -_self00);
        dest.putDouble(destOffset + 8, -_self10);
        dest.putDouble(destOffset + 16, -_self20);
        dest.putDouble(destOffset + 24, -_self01);
        dest.putDouble(destOffset + 32, -_self11);
        dest.putDouble(destOffset + 40, -_self21);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ270_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateZ270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ270_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, -_self01);
        dest.putDouble(destOffset + 8, -_self11);
        dest.putDouble(destOffset + 16, -_self21);
        dest.putDouble(destOffset + 24, _self00);
        dest.putDouble(destOffset + 32, _self10);
        dest.putDouble(destOffset + 40, _self20);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ90_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateZ90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ90_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self01);
        dest.putDouble(destOffset + 8, _self11);
        dest.putDouble(destOffset + 16, _self21);
        dest.putDouble(destOffset + 24, -_self00);
        dest.putDouble(destOffset + 32, -_self10);
        dest.putDouble(destOffset + 40, -_self20);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_api_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleX);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.cosWithSin(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.sinWithCos(angleZ);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t0 * _t1;
        double _t7 = _t4 * _t5;
        double _t8 = _t4 * _t3;
        double _t18 = java.lang.Math.fma(_t8, _t0, _t5 * _t2);
        double _t20 = java.lang.Math.fma(_t2, _t3, -(_t7 * _t0));
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, -_t6, java.lang.Math.fma(_self00, _t20, _self01 * _t18)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, -_t6, java.lang.Math.fma(_self10, _t20, _self11 * _t18)));
        return rotateZXY_api_fma_s9244902_1(dest, destOffset, _t4, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t1 * _t3, _t5 * _t1, _t1 * _t2, _t18, java.lang.Math.fma(_t7, _t2, _t0 * _t3), _t20, java.lang.Math.fma(_t0, _t5, -(_t8 * _t2)));
    }

    /** Piece 2 of {@code rotateZXY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_fma_s9244902_1(java.nio.ByteBuffer dest, int destOffset, double _t4, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t10, double _t11, double _t12, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, -_t6, java.lang.Math.fma(_self20, _t20, _self21 * _t18)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t4, java.lang.Math.fma(_self01, _t10, -(_self00 * _t11))));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t4, java.lang.Math.fma(_self11, _t10, -(_self10 * _t11))));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t4, java.lang.Math.fma(_self21, _t10, -(_self20 * _t11))));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t19, _self01 * _t21)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t19, _self11 * _t21)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleX);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.cosWithSin(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.sinWithCos(angleZ);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t0 * _t1;
        double _t7 = _t4 * _t5;
        double _t8 = _t4 * _t3;
        double _t18 = ((_t8) * (_t0) + (_t5 * _t2));
        double _t20 = ((_t2) * (_t3) - (_t7 * _t0));
        dest.putDouble(destOffset, ((((_self00) * (_t20) + (_self01 * _t18))) - (_self02) * (_t6)));
        dest.putDouble(destOffset + 8, ((((_self10) * (_t20) + (_self11 * _t18))) - (_self12) * (_t6)));
        return rotateZXY_api_mulAdd_sf4200725_1(dest, destOffset, _t4, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t1 * _t3, _t5 * _t1, _t1 * _t2, _t18, ((_t7) * (_t2) + (_t0 * _t3)), _t20, ((_t0) * (_t5) - (_t8 * _t2)));
    }

    /** Piece 2 of {@code rotateZXY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_mulAdd_sf4200725_1(java.nio.ByteBuffer dest, int destOffset, double _t4, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t10, double _t11, double _t12, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, ((((_self20) * (_t20) + (_self21 * _t18))) - (_self22) * (_t6)));
        dest.putDouble(destOffset + 24, ((_self02) * (_t4) + (((_self01) * (_t10) - (_self00 * _t11)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t4) + (((_self11) * (_t10) - (_self10 * _t11)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t4) + (((_self21) * (_t10) - (_self20 * _t11)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t12) + (((_self00) * (_t19) + (_self01 * _t21)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t12) + (((_self10) * (_t19) + (_self11 * _t21)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t12) + (((_self20) * (_t19) + (_self21 * _t21)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_api_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.cosWithSin(angleZ);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.cosWithSin(angleX);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t1 * _t2;
        double _t7 = _t3 * _t1;
        double _t9 = _t0 * _t2;
        double _t11 = _t0 * _t3;
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, -_t0, java.lang.Math.fma(_self00, _t6, _self01 * _t7)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, -_t0, java.lang.Math.fma(_self10, _t6, _self11 * _t7)));
        return rotateZYX_api_fma_s2ba94e2a_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, java.lang.Math.fma(_t11, _t4, _t5 * _t2), java.lang.Math.fma(_t9, _t5, _t4 * _t3), java.lang.Math.fma(_t9, _t4, -(_t3 * _t5)), java.lang.Math.fma(_t11, _t5, -(_t4 * _t2)));
    }

    /** Piece 2 of {@code rotateZYX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_fma_s2ba94e2a_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t7, double _t8, double _t13, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, -_t0, java.lang.Math.fma(_self20, _t6, _self21 * _t7)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self02, _t8, java.lang.Math.fma(_self00, _t20, _self01 * _t18)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self12, _t8, java.lang.Math.fma(_self10, _t20, _self11 * _t18)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self22, _t8, java.lang.Math.fma(_self20, _t20, _self21 * _t18)));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t19, _self01 * _t21)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t19, _self11 * _t21)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.cosWithSin(angleZ);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.cosWithSin(angleX);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = _t1 * _t2;
        double _t7 = _t3 * _t1;
        double _t9 = _t0 * _t2;
        double _t11 = _t0 * _t3;
        dest.putDouble(destOffset, ((((_self00) * (_t6) + (_self01 * _t7))) - (_self02) * (_t0)));
        dest.putDouble(destOffset + 8, ((((_self10) * (_t6) + (_self11 * _t7))) - (_self12) * (_t0)));
        return rotateZYX_api_mulAdd_s41701511_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, ((_t11) * (_t4) + (_t5 * _t2)), ((_t9) * (_t5) + (_t4 * _t3)), ((_t9) * (_t4) - (_t3 * _t5)), ((_t11) * (_t5) - (_t4 * _t2)));
    }

    /** Piece 2 of {@code rotateZYX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_mulAdd_s41701511_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t7, double _t8, double _t13, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, ((((_self20) * (_t6) + (_self21 * _t7))) - (_self22) * (_t0)));
        dest.putDouble(destOffset + 24, ((_self02) * (_t8) + (((_self00) * (_t20) + (_self01 * _t18)))));
        dest.putDouble(destOffset + 32, ((_self12) * (_t8) + (((_self10) * (_t20) + (_self11 * _t18)))));
        dest.putDouble(destOffset + 40, ((_self22) * (_t8) + (((_self20) * (_t20) + (_self21 * _t18)))));
        dest.putDouble(destOffset + 48, ((_self02) * (_t13) + (((_self00) * (_t19) + (_self01 * _t21)))));
        dest.putDouble(destOffset + 56, ((_self12) * (_t13) + (((_self10) * (_t19) + (_self11 * _t21)))));
        dest.putDouble(destOffset + 64, ((_self22) * (_t13) + (((_self20) * (_t19) + (_self21 * _t21)))));
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00 * vX);
        dest.putDouble(destOffset + 8, _self10 * vX);
        dest.putDouble(destOffset + 16, _self20 * vX);
        dest.putDouble(destOffset + 24, _self01 * vY);
        dest.putDouble(destOffset + 32, _self11 * vY);
        dest.putDouble(destOffset + 40, _self21 * vY);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00 * _vx);
        dest.putDouble(destOffset + 8, _self10 * _vx);
        dest.putDouble(destOffset + 16, _self20 * _vx);
        dest.putDouble(destOffset + 24, _self01 * _vy);
        dest.putDouble(destOffset + 32, _self11 * _vy);
        dest.putDouble(destOffset + 40, _self21 * _vy);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self20);
        dest.putDouble(destOffset + 24, s * _self01);
        dest.putDouble(destOffset + 32, s * _self11);
        dest.putDouble(destOffset + 40, s * _self21);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self20);
        dest.putDouble(destOffset + 24, s * _self01);
        dest.putDouble(destOffset + 32, s * _self11);
        dest.putDouble(destOffset + 40, s * _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self20);
        dest.putDouble(destOffset + 24, s * _self01);
        dest.putDouble(destOffset + 32, s * _self11);
        dest.putDouble(destOffset + 40, s * _self21);
        dest.putDouble(destOffset + 48, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.putDouble(destOffset + 56, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        dest.putDouble(destOffset + 64, ((_self20) * (_t1) + (((_self21) * (_t2) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivot.getDouble(pivotOffset) * _t0;
        double _t2 = pivot.getDouble(pivotOffset + 8) * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self20);
        dest.putDouble(destOffset + 24, s * _self01);
        dest.putDouble(destOffset + 32, s * _self11);
        dest.putDouble(destOffset + 40, s * _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivot.getDouble(pivotOffset) * _t0;
        double _t2 = pivot.getDouble(pivotOffset + 8) * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self20);
        dest.putDouble(destOffset + 24, s * _self01);
        dest.putDouble(destOffset + 32, s * _self11);
        dest.putDouble(destOffset + 40, s * _self21);
        dest.putDouble(destOffset + 48, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.putDouble(destOffset + 56, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        dest.putDouble(destOffset + 64, ((_self20) * (_t1) + (((_self21) * (_t2) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.putDouble(destOffset, sX * _self00);
        dest.putDouble(destOffset + 8, sX * _self10);
        dest.putDouble(destOffset + 16, sX * _self20);
        dest.putDouble(destOffset + 24, sY * _self01);
        dest.putDouble(destOffset + 32, sY * _self11);
        dest.putDouble(destOffset + 40, sY * _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self20, _t2, java.lang.Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.putDouble(destOffset, sX * _self00);
        dest.putDouble(destOffset + 8, sX * _self10);
        dest.putDouble(destOffset + 16, sX * _self20);
        dest.putDouble(destOffset + 24, sY * _self01);
        dest.putDouble(destOffset + 32, sY * _self11);
        dest.putDouble(destOffset + 40, sY * _self21);
        dest.putDouble(destOffset + 48, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.putDouble(destOffset + 56, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        dest.putDouble(destOffset + 64, ((_self20) * (_t2) + (((_self21) * (_t3) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@code scaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = pivot.getDouble(pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.getDouble(pivotOffset + 8) * (1.0 - _sy);
        dest.putDouble(destOffset, _sx * _self00);
        dest.putDouble(destOffset + 8, _sx * _self10);
        dest.putDouble(destOffset + 16, _sx * _self20);
        dest.putDouble(destOffset + 24, _sy * _self01);
        dest.putDouble(destOffset + 32, _sy * _self11);
        dest.putDouble(destOffset + 40, _sy * _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self20, _t2, java.lang.Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = pivot.getDouble(pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.getDouble(pivotOffset + 8) * (1.0 - _sy);
        dest.putDouble(destOffset, _sx * _self00);
        dest.putDouble(destOffset + 8, _sx * _self10);
        dest.putDouble(destOffset + 16, _sx * _self20);
        dest.putDouble(destOffset + 24, _sy * _self01);
        dest.putDouble(destOffset + 32, _sy * _self11);
        dest.putDouble(destOffset + 40, _sy * _self21);
        dest.putDouble(destOffset + 48, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.putDouble(destOffset + 56, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        dest.putDouble(destOffset + 64, ((_self20) * (_t2) + (((_self21) * (_t3) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self20, vX, java.lang.Math.fma(_self21, vY, _self22)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer translate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
        dest.putDouble(destOffset + 56, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        dest.putDouble(destOffset + 64, ((_self20) * (vX) + (((_self21) * (vY) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_self20, _vx, java.lang.Math.fma(_self21, _vy, _self22)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer translate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
        dest.putDouble(destOffset + 56, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
        dest.putDouble(destOffset + 64, ((_self20) * (_vx) + (((_self21) * (_vy) + (_self22)))));
        return dest;
    }

    public static java.nio.ByteBuffer view_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer view_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        dest.putDouble(destOffset, _sp0 * _self00);
        dest.putDouble(destOffset + 8, _sp0 * _self10);
        dest.putDouble(destOffset + 16, _sp0 * _self20);
        dest.putDouble(destOffset + 24, _sp1 * _self01);
        dest.putDouble(destOffset + 32, _sp1 * _self11);
        dest.putDouble(destOffset + 40, _sp1 * _self21);
        dest.putDouble(destOffset + 48, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.putDouble(destOffset + 56, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        dest.putDouble(destOffset + 64, _self22 + (-(_self20 * _sp2) - _self21 * _sp3));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, vZ, java.lang.Math.fma(_self00, vX, _self01 * vY)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, vZ, java.lang.Math.fma(_self10, vX, _self11 * vY)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, vZ, java.lang.Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    /** {@code mulVec3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulVec3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, ((_self02) * (vZ) + (((_self00) * (vX) + (_self01 * vY)))));
        dest.putDouble(destOffset + 8, ((_self12) * (vZ) + (((_self10) * (vX) + (_self11 * vY)))));
        dest.putDouble(destOffset + 16, ((_self22) * (vZ) + (((_self20) * (vX) + (_self21 * vY)))));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, java.lang.Math.fma(_self02, _vz, java.lang.Math.fma(_self00, _vx, _self01 * _vy)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _vz, java.lang.Math.fma(_self10, _vx, _self11 * _vy)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self22, _vz, java.lang.Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    /** {@code mulVec3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulVec3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, ((_self02) * (_vz) + (((_self00) * (_vx) + (_self01 * _vy)))));
        dest.putDouble(destOffset + 8, ((_self12) * (_vz) + (((_self10) * (_vx) + (_self11 * _vy)))));
        dest.putDouble(destOffset + 16, ((_self22) * (_vz) + (((_self20) * (_vx) + (_self21 * _vy)))));
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 24);
            double _self11 = src.getDouble(srcOffset + 32);
            dest.putDouble(destOffset, java.lang.Math.fma(_self00, vX, _self01 * vY));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 24);
            double _self11 = src.getDouble(srcOffset + 32);
            dest.putDouble(destOffset, ((_self00) * (vX) + (_self01 * vY)));
            dest.putDouble(destOffset + 8, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 24);
            double _self11 = src.getDouble(srcOffset + 32);
            dest.putDouble(destOffset, java.lang.Math.fma(_self00, _vx, _self01 * _vy));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 24);
            double _self11 = src.getDouble(srcOffset + 32);
            dest.putDouble(destOffset, ((_self00) * (_vx) + (_self01 * _vy)));
            dest.putDouble(destOffset + 8, ((_self10) * (_vx) + (_self11 * _vy)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 24);
            double _self11 = src.getDouble(srcOffset + 32);
            double _self02 = src.getDouble(srcOffset + 48);
            double _self12 = src.getDouble(srcOffset + 56);
            dest.putDouble(destOffset, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
            return dest;
        } else {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 24);
            double _self11 = src.getDouble(srcOffset + 32);
            double _self02 = src.getDouble(srcOffset + 48);
            double _self12 = src.getDouble(srcOffset + 56);
            dest.putDouble(destOffset, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
            dest.putDouble(destOffset + 8, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 24);
            double _self11 = src.getDouble(srcOffset + 32);
            double _self02 = src.getDouble(srcOffset + 48);
            double _self12 = src.getDouble(srcOffset + 56);
            dest.putDouble(destOffset, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
            return dest;
        } else {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 24);
            double _self11 = src.getDouble(srcOffset + 32);
            double _self02 = src.getDouble(srcOffset + 48);
            double _self12 = src.getDouble(srcOffset + 56);
            dest.putDouble(destOffset, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
            dest.putDouble(destOffset + 8, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
            return dest;
        }
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-1022, 2^1022], so zero and subnormal
     * values scale up without overflow and the largest doubles land in [2, 4).
     */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
