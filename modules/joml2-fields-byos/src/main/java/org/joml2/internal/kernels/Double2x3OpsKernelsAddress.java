// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x3Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double2x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double2x3OpsKernelsAddress {
    private Double2x3OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        UnsafeOpsHolder.U.putDouble(dest, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        return dest;
    }

    public static double getRotationAngle_unsafe(long src) {
        return Math.atan2(UnsafeOpsHolder.U.getDouble(src + 8L), UnsafeOpsHolder.U.getDouble(src));
    }

    public static long getRow_unsafe(long dest, long src, int row) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        UnsafeOpsHolder.U.putDouble(dest, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _idxSw2);
        return dest;
    }

    public static long getTranslation_unsafe(long dest, long src) {
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self12);
        return dest;
    }

    public static double determinant_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t1 = -(_self01 * _self10);
            return java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(src + 24L), _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        } else {
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t1 = -(_self01 * _self10);
            return ((UnsafeOpsHolder.U.getDouble(src)) * (UnsafeOpsHolder.U.getDouble(src + 24L)) + (_t1)) - ((_self01) * (_self10) + (_t1));
        }
    }

    public static double frobeniusNorm_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
            return java.lang.Math.sqrt(java.lang.Math.fma(_self12, _self12, java.lang.Math.fma(_self11, _self11, java.lang.Math.fma(_self10, _self10, java.lang.Math.fma(_self02, _self02, java.lang.Math.fma(_self00, _self00, _self01 * _self01))))));
        } else {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
            return java.lang.Math.sqrt(((_self12) * (_self12) + (((_self11) * (_self11) + (((_self10) * (_self10) + (((_self02) * (_self02) + (((_self00) * (_self00) + (_self01 * _self01)))))))))));
        }
    }

    public static long invert_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_unsafe_mulAdd(dest, src);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t3 = -(_self01 * _self10);
        double _t4 = -(_self01 * _self12);
        double _t5 = -(_self02 * _self10);
        double _t9 = java.lang.Math.fma(_self00, _self11, _t3) - java.lang.Math.fma(_self01, _self10, _t3);
        if (!(java.lang.Math.abs(_t9) > 2.2250738585072014E-308 && java.lang.Math.abs(_t9) < 4.49423283715579E307)) return Double2x3OpsKernelsAddress.invert_degenerate(dest, src);
        double _t9_inv = 1.0 / _t9;
        UnsafeOpsHolder.U.putDouble(dest, _self11 * _t9_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_self10 * _t9_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_self01 * _t9_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self00 * _t9_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, (java.lang.Math.fma(_self01, _self12, _t4) - java.lang.Math.fma(_self02, _self11, _t4)) * _t9_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (java.lang.Math.fma(_self02, _self10, _t5) - java.lang.Math.fma(_self00, _self12, _t5)) * _t9_inv);
        return dest;
    }

    /** {@code invert_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t3 = -(_self01 * _self10);
        double _t4 = -(_self01 * _self12);
        double _t5 = -(_self02 * _self10);
        double _t9 = ((_self00) * (_self11) + (_t3)) - ((_self01) * (_self10) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 2.2250738585072014E-308 && java.lang.Math.abs(_t9) < 4.49423283715579E307)) return Double2x3OpsKernelsAddress.invert_degenerate(dest, src);
        double _t9_inv = 1.0 / _t9;
        UnsafeOpsHolder.U.putDouble(dest, _self11 * _t9_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_self10 * _t9_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_self01 * _t9_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self00 * _t9_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, (((_self01) * (_self12) + (_t4)) - ((_self02) * (_self11) + (_t4))) * _t9_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (((_self02) * (_self10) + (_t5)) - ((_self00) * (_self12) + (_t5))) * _t9_inv);
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        Double2x3OpsKernelsSegment.invert_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
        return dest;
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_unsafe_fma(dest, src);
        return invert_degenerate_unsafe_mulAdd(dest, src);
    }

    /** {@code invert_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_degenerate_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t8 = _self11 * _t0;
        double _t9 = _self00 * _t1;
        double _t10 = _self01 * _t1;
        double _t11 = _self10 * _t0;
        double _t12 = _self02 * _t1;
        double _t13 = _self12 * _t0;
        double _t17 = -(_t10 * _t11);
        double _t22_inv = 1.0 / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        double _sp1 = _t0 * _t22_inv;
        double _sp0 = _t1 * _t22_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t8 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t11 * _sp0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t10 * _sp1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t9 * _sp1);
        return invert_degenerate_unsafe_fma_sb8d74ee8_1(dest, _t8, _t9, _t10, _t11, _t12, _t13, -(_t10 * _t13), -(_t12 * _t11), _t22_inv);
    }

    /** Piece 2 of {@code invert_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_fma_sb8d74ee8_1(long dest, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t18, double _t19, double _t22_inv) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv);
        return dest;
    }

    /** {@code invert_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_degenerate_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t8 = _self11 * _t0;
        double _t9 = _self00 * _t1;
        double _t10 = _self01 * _t1;
        double _t11 = _self10 * _t0;
        double _t12 = _self02 * _t1;
        double _t13 = _self12 * _t0;
        double _t17 = -(_t10 * _t11);
        double _t22_inv = 1.0 / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        double _sp1 = _t0 * _t22_inv;
        double _sp0 = _t1 * _t22_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t8 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t11 * _sp0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t10 * _sp1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t9 * _sp1);
        return invert_degenerate_unsafe_mulAdd_sff6f5c69_1(dest, _t8, _t9, _t10, _t11, _t12, _t13, -(_t10 * _t13), -(_t12 * _t11), _t22_inv);
    }

    /** Piece 2 of {@code invert_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_mulAdd_sff6f5c69_1(long dest, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t18, double _t19, double _t22_inv) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_unsafe_fma(dest, src, other);
        return invertProduct_unsafe_mulAdd(dest, src, other);
    }

    /** {@code invertProduct_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_fma(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _t6 = -(_other01 * _other10);
        double _t7 = -(_self01 * _self10);
        double _t24 = java.lang.Math.fma(_other00, _other11, _t6) - java.lang.Math.fma(_other01, _other10, _t6);
        if (!(java.lang.Math.abs(_t24) > 2.2250738585072014E-308 && java.lang.Math.abs(_t24) < 4.49423283715579E307)) return Double2x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        return invertProduct_unsafe_fma_saeb649aa_1(dest, src, other, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12, -(_self02 * _self10), -(_self01 * _self12), -(_other01 * _other12), -(_other02 * _other10), _t24, 1.0 / _t24, java.lang.Math.fma(_self00, _self11, _t7) - java.lang.Math.fma(_self01, _self10, _t7));
    }

    /** Piece 2 of {@code invertProduct_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_fma_saeb649aa_1(long dest, long src, long other, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t8, double _t9, double _t10, double _t11, double _t24, double _t24_inv, double _t25) {
        if (!(java.lang.Math.abs(_t25) > 2.2250738585072014E-308 && java.lang.Math.abs(_t25) < 4.49423283715579E307)) return Double2x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        double _t25_inv = 1.0 / _t25;
        double _t36 = _other11 / _t24;
        double _t37 = _self11 / _t25;
        double _t38 = _self00 / _t25;
        double _t41 = _other00 / _t24;
        double _t42 = -(_other01 * _t24_inv);
        double _t43 = -(_self10 * _t25_inv);
        double _t44 = -(_self01 * _t25_inv);
        double _t45 = -(_other10 * _t24_inv);
        double _t52 = _t36 * _t37;
        double _t54 = _t44 * _t36;
        double _t55 = _t43 * _t41;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54));
        return invertProduct_unsafe_fma_saeb649aa_2(dest, _other00, _other10, _other01, _other11, _other02, _other12, _t10, _t11, _t24_inv, _t36, _t38, _t41, _t42, _t44, _t45, -((java.lang.Math.fma(_self00, _self12, _t8) - java.lang.Math.fma(_self02, _self10, _t8)) * _t25_inv), -((java.lang.Math.fma(_self02, _self11, _t9) - java.lang.Math.fma(_self01, _self12, _t9)) * _t25_inv), _t41 * _t38);
    }

    /** Piece 3 of {@code invertProduct_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_fma_saeb649aa_2(long dest, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t10, double _t11, double _t24_inv, double _t36, double _t38, double _t41, double _t42, double _t44, double _t45, double _t50, double _t51, double _t53) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t42, _t50, java.lang.Math.fma(_t51, _t36, -((java.lang.Math.fma(_other02, _other11, _t10) - java.lang.Math.fma(_other01, _other12, _t10)) * _t24_inv))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t45, _t51, java.lang.Math.fma(_t50, _t41, -((java.lang.Math.fma(_other00, _other12, _t11) - java.lang.Math.fma(_other02, _other10, _t11)) * _t24_inv))));
        return dest;
    }

    /** {@code invertProduct_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_mulAdd(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _t6 = -(_other01 * _other10);
        double _t7 = -(_self01 * _self10);
        double _t24 = ((_other00) * (_other11) + (_t6)) - ((_other01) * (_other10) + (_t6));
        if (!(java.lang.Math.abs(_t24) > 2.2250738585072014E-308 && java.lang.Math.abs(_t24) < 4.49423283715579E307)) return Double2x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        return invertProduct_unsafe_mulAdd_s7f31bc6f_1(dest, src, other, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12, -(_self02 * _self10), -(_self01 * _self12), -(_other01 * _other12), -(_other02 * _other10), _t24, 1.0 / _t24, ((_self00) * (_self11) + (_t7)) - ((_self01) * (_self10) + (_t7)));
    }

    /** Piece 2 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_mulAdd_s7f31bc6f_1(long dest, long src, long other, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t8, double _t9, double _t10, double _t11, double _t24, double _t24_inv, double _t25) {
        if (!(java.lang.Math.abs(_t25) > 2.2250738585072014E-308 && java.lang.Math.abs(_t25) < 4.49423283715579E307)) return Double2x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        double _t25_inv = 1.0 / _t25;
        double _t36 = _other11 / _t24;
        double _t37 = _self11 / _t25;
        double _t38 = _self00 / _t25;
        double _t41 = _other00 / _t24;
        double _t42 = -(_other01 * _t24_inv);
        double _t43 = -(_self10 * _t25_inv);
        double _t44 = -(_self01 * _t25_inv);
        double _t45 = -(_other10 * _t24_inv);
        double _t52 = _t36 * _t37;
        double _t53 = _t41 * _t38;
        double _t54 = _t44 * _t36;
        double _t55 = _t43 * _t41;
        UnsafeOpsHolder.U.putDouble(dest, ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53)));
        return invertProduct_unsafe_mulAdd_s7f31bc6f_2(dest, _other00, _other10, _other01, _other11, _other02, _other12, _t10, _t11, _t24_inv, _t36, _t41, _t42, _t45, -((((_self00) * (_self12) + (_t8)) - ((_self02) * (_self10) + (_t8))) * _t25_inv), -((((_self02) * (_self11) + (_t9)) - ((_self01) * (_self12) + (_t9))) * _t25_inv));
    }

    /** Piece 3 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_mulAdd_s7f31bc6f_2(long dest, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t10, double _t11, double _t24_inv, double _t36, double _t41, double _t42, double _t45, double _t50, double _t51) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t42) * (_t50) + (((_t51) * (_t36) - ((((_other02) * (_other11) + (_t10)) - ((_other01) * (_other12) + (_t10))) * _t24_inv)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t45) * (_t51) + (((_t50) * (_t41) - ((((_other00) * (_other12) + (_t11)) - ((_other02) * (_other10) + (_t11))) * _t24_inv)))));
        return dest;
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        Double2x3OpsKernelsSegment.invertProduct_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 48L), 0L);
        return dest;
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_unsafe_fma(dest, src, other);
        return invertProduct_degenerate_unsafe_mulAdd(dest, src, other);
    }

    /** {@code invertProduct_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_degenerate_unsafe_fma(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t16 = _other01 * _t0;
        double _t19 = _other10 * _t1;
        double _t20 = _self10 * _t2;
        double _t23 = _self01 * _t3;
        return invertProduct_degenerate_unsafe_fma_see1ccc65_1(dest, _t0, _t1, _t2, _t3, _t16, _other00 * _t0, _other11 * _t1, _t19, _t20, _self00 * _t3, _self11 * _t2, _t23, _self12 * _t2, _self02 * _t3, _other02 * _t0, _other12 * _t1, -(_t16 * _t19), -(_t23 * _t20));
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_fma_see1ccc65_1(long dest, double _t0, double _t1, double _t2, double _t3, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t42, double _t43) {
        double _t44 = -(_t25 * _t20);
        double _t45 = -(_t23 * _t24);
        double _t56 = java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42);
        double _t56_inv = 1.0 / _t56;
        double _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        double _t57_inv = 1.0 / _t57;
        double _t70 = _t18 * _t0 / _t56;
        double _t71 = _t22 * _t3 / _t57;
        double _t72 = _t21 * _t2 / _t57;
        double _t75 = _t17 * _t1 / _t56;
        double _t76 = -(_t16 * _t1 * _t56_inv);
        double _t77 = -(_t20 * _t3 * _t57_inv);
        double _t78 = -(_t23 * _t2 * _t57_inv);
        double _t86 = _t70 * _t71;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86));
        return invertProduct_degenerate_unsafe_fma_see1ccc65_2(dest, _t16, _t17, _t18, _t19, _t26, _t27, -(_t16 * _t27), -(_t26 * _t19), _t56_inv, _t70, _t71, _t72, _t75, _t76, _t77, _t78, -(_t19 * _t0 * _t56_inv), -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv), -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv), _t75 * _t72, _t78 * _t70, _t77 * _t75);
    }

    /** Piece 3 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_fma_see1ccc65_2(long dest, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t46, double _t47, double _t56_inv, double _t70, double _t71, double _t72, double _t75, double _t76, double _t77, double _t78, double _t79, double _t84, double _t85, double _t87, double _t88, double _t89) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv))));
        return dest;
    }

    /** {@code invertProduct_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_degenerate_unsafe_mulAdd(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t16 = _other01 * _t0;
        double _t19 = _other10 * _t1;
        double _t20 = _self10 * _t2;
        double _t23 = _self01 * _t3;
        return invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_1(dest, _t0, _t1, _t2, _t3, _t16, _other00 * _t0, _other11 * _t1, _t19, _t20, _self00 * _t3, _self11 * _t2, _t23, _self12 * _t2, _self02 * _t3, _other02 * _t0, _other12 * _t1, -(_t16 * _t19), -(_t23 * _t20));
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_1(long dest, double _t0, double _t1, double _t2, double _t3, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t42, double _t43) {
        double _t44 = -(_t25 * _t20);
        double _t45 = -(_t23 * _t24);
        double _t56 = ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42));
        double _t56_inv = 1.0 / _t56;
        double _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        double _t57_inv = 1.0 / _t57;
        double _t70 = _t18 * _t0 / _t56;
        double _t71 = _t22 * _t3 / _t57;
        double _t72 = _t21 * _t2 / _t57;
        double _t75 = _t17 * _t1 / _t56;
        double _t76 = -(_t16 * _t1 * _t56_inv);
        double _t77 = -(_t20 * _t3 * _t57_inv);
        double _t78 = -(_t23 * _t2 * _t57_inv);
        double _t86 = _t70 * _t71;
        UnsafeOpsHolder.U.putDouble(dest, ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86)));
        return invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_2(dest, _t16, _t17, _t18, _t19, _t26, _t27, -(_t16 * _t27), -(_t26 * _t19), _t56_inv, _t70, _t71, _t72, _t75, _t76, _t77, _t78, -(_t19 * _t0 * _t56_inv), -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv), -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv), _t75 * _t72, _t78 * _t70, _t77 * _t75);
    }

    /** Piece 3 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_2(long dest, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t46, double _t47, double _t56_inv, double _t70, double _t71, double _t72, double _t75, double _t76, double _t77, double _t78, double _t79, double _t84, double _t85, double _t87, double _t88, double _t89) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv)))));
        return dest;
    }

    public static long transpose_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
            double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eother0 + _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eother1 + _eself1);
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double scalar) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, scalar * _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, scalar * _eself1);
        }
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself1);
        }
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
            double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0 - _eother0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1 - _eother1);
        }
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _ev0 = UnsafeOpsHolder.U.getDouble(v + _lo * 8L);
            double _ev1 = UnsafeOpsHolder.U.getDouble(v + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _ev0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _ev1);
        }
        return dest;
    }

    public static long setMat2x2_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _m00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _m10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _m01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _m11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long setMat3x3_unsafe(long dest, long m) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _lom = _l * 3;
            double _em0 = UnsafeOpsHolder.U.getDouble(m + _lom * 8L);
            double _em1 = UnsafeOpsHolder.U.getDouble(m + (_lom + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _em0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _em1);
        }
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, double tX, double tY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, tX);
        UnsafeOpsHolder.U.putDouble(dest + 40L, tY);
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, long t) {
        double _tx = UnsafeOpsHolder.U.getDouble(t);
        double _ty = UnsafeOpsHolder.U.getDouble(t + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _tx);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _ty);
        return dest;
    }

    public static long to2x2_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        return dest;
    }

    public static long to3x3_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_unsafe_mulAdd(dest, src, other, t);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
            double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, java.lang.Math.fma(t, _eother0 - _eself0, _eself0));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, java.lang.Math.fma(t, _eother1 - _eself1, _eself1));
        }
        return dest;
    }

    /** {@code lerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lerp_unsafe_mulAdd(long dest, long src, long other, double t) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
            double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, ((t) * (_eother0 - _eself0) + (_eself0)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, ((t) * (_eother1 - _eself1) + (_eself1)));
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long right) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mul_unsafe_fma(dest, src, right);
        return mul_unsafe_mulAdd(dest, src, right);
    }

    /** {@code mul_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mul_unsafe_fma(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        double _right02 = UnsafeOpsHolder.U.getDouble(right + 32L);
        double _right12 = UnsafeOpsHolder.U.getDouble(right + 40L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_right02, _self00, java.lang.Math.fma(_right12, _self01, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_right02, _self10, java.lang.Math.fma(_right12, _self11, _self12)));
        return dest;
    }

    /** {@code mul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mul_unsafe_mulAdd(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        double _right02 = UnsafeOpsHolder.U.getDouble(right + 32L);
        double _right12 = UnsafeOpsHolder.U.getDouble(right + 40L);
        UnsafeOpsHolder.U.putDouble(dest, ((_right00) * (_self00) + (_right10 * _self01)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_right00) * (_self10) + (_right10 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_right01) * (_self00) + (_right11 * _self01)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_right01) * (_self10) + (_right11 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_right02) * (_self00) + (((_right12) * (_self01) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_right02) * (_self10) + (((_right12) * (_self11) + (_self12)))));
        return dest;
    }

    public static long mulMat2x2_unsafe(long dest, long src, long right) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x2_unsafe_mulAdd(dest, src, right);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    /** {@code mulMat2x2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulMat2x2_unsafe_mulAdd(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        UnsafeOpsHolder.U.putDouble(dest, ((_right00) * (_self00) + (_right10 * _self01)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_right00) * (_self10) + (_right10 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_right01) * (_self00) + (_right11 * _self01)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_right01) * (_self10) + (_right11 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long mulMat3x3_unsafe(long dest, long src, long right) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat3x3_unsafe_mulAdd(dest, src, right);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = UnsafeOpsHolder.U.getDouble(right + _lo * 8L);
            double _eright1 = UnsafeOpsHolder.U.getDouble(right + (_lo + 1) * 8L);
            double _eright2 = UnsafeOpsHolder.U.getDouble(right + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eright2);
        }
        return dest;
    }

    /** {@code mulMat3x3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulMat3x3_unsafe_mulAdd(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = UnsafeOpsHolder.U.getDouble(right + _lo * 8L);
            double _eright1 = UnsafeOpsHolder.U.getDouble(right + (_lo + 1) * 8L);
            double _eright2 = UnsafeOpsHolder.U.getDouble(right + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, ((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01)))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, ((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11)))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eright2);
        }
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_fma(dest, src, other);
        return preMul_unsafe_mulAdd(dest, src, other);
    }

    /** {@code preMul_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_fma(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_other00, _self02, java.lang.Math.fma(_other01, _self12, _other02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_other10, _self02, java.lang.Math.fma(_other11, _self12, _other12)));
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        UnsafeOpsHolder.U.putDouble(dest, ((_other00) * (_self00) + (_other01 * _self10)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_other10) * (_self00) + (_other11 * _self10)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_other00) * (_self01) + (_other01 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_other10) * (_self01) + (_other11 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_other00) * (_self02) + (((_other01) * (_self12) + (_other02)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_other10) * (_self02) + (((_other11) * (_self12) + (_other12)))));
        return dest;
    }

    public static long preMulMat2x2_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x2_unsafe_mulAdd(dest, src, other);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1));
        }
        return dest;
    }

    /** {@code preMulMat2x2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulMat2x2_unsafe_mulAdd(long dest, long src, long other) {
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, ((_other00) * (_eself0) + (_other01 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, ((_other10) * (_eself0) + (_other11 * _eself1)));
        }
        return dest;
    }

    public static long preMulMat3x3_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat3x3_unsafe_fma(dest, src, other);
        return preMulMat3x3_unsafe_mulAdd(dest, src, other);
    }

    /** {@code preMulMat3x3_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulMat3x3_unsafe_fma(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_other20, _self00, _other21 * _self10));
        return preMulMat3x3_unsafe_fma_sf90fb838_1(dest, _self01, _self11, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preMulMat3x3_unsafe_fma_sf90fb838_1(long dest, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_other20, _self01, _other21 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_other00, _self02, java.lang.Math.fma(_other01, _self12, _other02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_other10, _self02, java.lang.Math.fma(_other11, _self12, _other12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_other20, _self02, java.lang.Math.fma(_other21, _self12, _other22)));
        return dest;
    }

    /** {@code preMulMat3x3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulMat3x3_unsafe_mulAdd(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        UnsafeOpsHolder.U.putDouble(dest, ((_other00) * (_self00) + (_other01 * _self10)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_other10) * (_self00) + (_other11 * _self10)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_other20) * (_self00) + (_other21 * _self10)));
        return preMulMat3x3_unsafe_mulAdd_s84f6bed5_1(dest, _self01, _self11, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preMulMat3x3_unsafe_mulAdd_s84f6bed5_1(long dest, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_other00) * (_self01) + (_other01 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_other10) * (_self01) + (_other11 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_other20) * (_self01) + (_other21 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_other00) * (_self02) + (((_other01) * (_self12) + (_other02)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_other10) * (_self02) + (((_other11) * (_self12) + (_other12)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_other20) * (_self02) + (((_other21) * (_self12) + (_other22)))));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, double weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
                double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
                double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
                double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
                UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, java.lang.Math.fma(weight, _eother0, _eself0));
                UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, java.lang.Math.fma(weight, _eother1, _eself1));
            }
            return dest;
        } else {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
                double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
                double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
                double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
                UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, ((weight) * (_eother0) + (_eself0)));
                UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, ((weight) * (_eother1) + (_eself1)));
            }
            return dest;
        }
    }

    public static long makeRotation_unsafe(long dest, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putDouble(dest, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t1);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double vX, double vY) {
        UnsafeOpsHolder.U.putDouble(dest, vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, vY);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _vy);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double s) {
        UnsafeOpsHolder.U.putDouble(dest, s);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, double vX, double vY) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, vX);
        UnsafeOpsHolder.U.putDouble(dest + 40L, vY);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _vy);
        return dest;
    }

    public static long makeView_unsafe(long dest, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        UnsafeOpsHolder.U.putDouble(dest, _t0_inv + _t0_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t1_inv + _t1_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -(left + right) * _t0_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -(bottom + top) * _t1_inv);
        return dest;
    }

    public static long preRotate_unsafe(long dest, long src, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_unsafe_mulAdd(dest, src, angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self02, _t1, _self12 * _t0));
        return dest;
    }

    /** {@code preRotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotate_unsafe_mulAdd(long dest, long src, double angle) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self00) * (_t1) + (_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self01) * (_t0) - (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self01) * (_t1) + (_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self02) * (_t0) - (_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self02) * (_t1) + (_self12 * _t0)));
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, double angle, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_unsafe_mulAdd(dest, src, angle, pivotX, pivotY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t6 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self02, _t1, _self12 * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1)));
        return dest;
    }

    /** {@code preRotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_mulAdd(long dest, long src, double angle, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t6 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self00) * (_t1) + (_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self01) * (_t0) - (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self01) * (_t1) + (_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((pivotX) * (_t6) + (pivotY * _t1)) + ((_self02) * (_t0) - (_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self02) * (_t1) + (_self12 * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1)));
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, long pivot, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_unsafe_fma(dest, src, pivot, angle);
        return preRotateAround_unsafe_mulAdd(dest, src, pivot, angle);
    }

    /** {@code preRotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_fma(long dest, long src, long pivot, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t6 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_pivotx, _t6, _pivoty * _t1) + java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self02, _t1, _self12 * _t0) + java.lang.Math.fma(_pivoty, _t6, -(_pivotx * _t1)));
        return dest;
    }

    /** {@code preRotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_mulAdd(long dest, long src, long pivot, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t6 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self00) * (_t1) + (_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self01) * (_t0) - (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self01) * (_t1) + (_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_pivotx) * (_t6) + (_pivoty * _t1)) + ((_self02) * (_t0) - (_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self02) * (_t1) + (_self12 * _t0)) + ((_pivoty) * (_t6) - (_pivotx * _t1)));
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0 * vX);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1 * vY);
        }
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0 * _vx);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1 * _vy);
        }
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, s * _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, s * _eself1);
        }
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, double s, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_mulAdd(dest, src, s, pivotX, pivotY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = 1.0 - s;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(s, _self02, pivotX * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(s, _self12, pivotY * _t0));
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, double s, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = 1.0 - s;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((s) * (_self02) + (pivotX * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((s) * (_self12) + (pivotY * _t0)));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long pivot, double s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_mulAdd(dest, src, pivot, s);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = 1.0 - s;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(s, _self02, _pivotx * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(s, _self12, _pivoty * _t0));
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, long pivot, double s) {
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = 1.0 - s;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((s) * (_self02) + (_pivotx * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((s) * (_self12) + (_pivoty * _t0)));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_mulAdd(dest, src, sX, sY, pivotX, pivotY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sY * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sX * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(pivotX, 1.0 - sX, sX * _self02));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(pivotY, 1.0 - sY, sY * _self12));
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sY * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sX * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((pivotX) * (1.0 - sX) + (sX * _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((pivotY) * (1.0 - sY) + (sY * _self12)));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long s, long pivot) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_mulAdd(dest, src, s, pivot);
        double _sx = UnsafeOpsHolder.U.getDouble(s);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sy * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sx * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_pivotx, 1.0 - _sx, _sx * _self02));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_pivoty, 1.0 - _sy, _sy * _self12));
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, long s, long pivot) {
        double _sx = UnsafeOpsHolder.U.getDouble(s);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sy * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sx * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_pivotx) * (1.0 - _sx) + (_sx * _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_pivoty) * (1.0 - _sy) + (_sy * _self12)));
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02 + vX);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12 + vY);
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02 + _vx);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12 + _vy);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_mulAdd(dest, src, angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, double angle) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) + (_self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_t0) + (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self01) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self11) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, double angle, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_unsafe_mulAdd(dest, src, angle, pivotX, pivotY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        double _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        return dest;
    }

    /** {@code rotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_mulAdd(long dest, long src, double angle, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        double _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) + (_self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_t0) + (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self01) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self11) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long pivot, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_unsafe_fma(dest, src, pivot, angle);
        return rotateAround_unsafe_mulAdd(dest, src, pivot, angle);
    }

    /** {@code rotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_fma(long dest, long src, long pivot, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        return rotateAround_unsafe_fma_s2dc3d2bb_1(dest, _self00, _self10, _self01, _self11, _self02, _self12, java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1), java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_fma_s2dc3d2bb_1(long dest, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _t10, double _t11) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        return dest;
    }

    /** {@code rotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_mulAdd(long dest, long src, long pivot, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = ((_pivotx) * (_t9) + (_pivoty * _t1));
        double _t11 = ((_pivoty) * (_t9) - (_pivotx * _t1));
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) + (_self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_t0) + (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self01) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self11) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        return dest;
    }

    public static long scale_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, double s, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_mulAdd(dest, src, s, pivotX, pivotY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, double s, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long pivot, double s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_mulAdd(dest, src, pivot, s);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = 1.0 - s;
        double _t1 = UnsafeOpsHolder.U.getDouble(pivot) * _t0;
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * _t0;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = 1.0 - s;
        double _t1 = UnsafeOpsHolder.U.getDouble(pivot) * _t0;
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * _t0;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_mulAdd(dest, src, sX, sY, pivotX, pivotY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        UnsafeOpsHolder.U.putDouble(dest, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sX * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sY * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        UnsafeOpsHolder.U.putDouble(dest, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sX * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sY * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long s, long pivot) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_mulAdd(dest, src, s, pivot);
        double _sx = UnsafeOpsHolder.U.getDouble(s);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot) * (1.0 - _sx);
        double _t3 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * (1.0 - _sy);
        UnsafeOpsHolder.U.putDouble(dest, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sx * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sy * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, long s, long pivot) {
        double _sx = UnsafeOpsHolder.U.getDouble(s);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot) * (1.0 - _sx);
        double _t3 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * (1.0 - _sy);
        UnsafeOpsHolder.U.putDouble(dest, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sx * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sy * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, double vX, double vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_unsafe_mulAdd(dest, src, vX, vY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        return dest;
    }

    /** {@code translate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long translate_unsafe_mulAdd(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_unsafe_mulAdd(dest, src, v);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    /** {@code translate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long translate_unsafe_mulAdd(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
        return dest;
    }

    public static long view_unsafe(long dest, long src, double left, double right, double bottom, double top) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        UnsafeOpsHolder.U.putDouble(dest, _sp0 * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, double vX, double vY, double vZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, vZ, java.lang.Math.fma(_self00, vX, _self01 * vY)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, vZ, java.lang.Math.fma(_self10, vX, _self11 * vY)));
            return dest;
        } else {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
            UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (vZ) + (((_self00) * (vX) + (_self01 * vY)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (vZ) + (((_self10) * (vX) + (_self11 * vY)))));
            return dest;
        }
    }

    public static long mulVec3_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_unsafe_mulAdd(dest, src, v);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _vz, java.lang.Math.fma(_self00, _vx, _self01 * _vy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _vz, java.lang.Math.fma(_self10, _vx, _self11 * _vy)));
        return dest;
    }

    /** {@code mulVec3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulVec3_unsafe_mulAdd(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_vz) + (((_self00) * (_vx) + (_self01 * _vy)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_vz) + (((_self10) * (_vx) + (_self11 * _vy)))));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, vX, _self01 * vY));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (vX) + (_self01 * vY)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static long transformDirection_unsafe(long dest, long src, long v) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = UnsafeOpsHolder.U.getDouble(v);
            double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _vx, _self01 * _vy));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            double _vx = UnsafeOpsHolder.U.getDouble(v);
            double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_vx) + (_self01 * _vy)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_vx) + (_self11 * _vy)));
            return dest;
        }
    }

    public static long transformPosition_unsafe(long dest, long src, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
            return dest;
        } else {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
            UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
            return dest;
        }
    }

    public static long transformPosition_unsafe(long dest, long src, long v) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = UnsafeOpsHolder.U.getDouble(v);
            double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
            return dest;
        } else {
            double _vx = UnsafeOpsHolder.U.getDouble(v);
            double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
            UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
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
