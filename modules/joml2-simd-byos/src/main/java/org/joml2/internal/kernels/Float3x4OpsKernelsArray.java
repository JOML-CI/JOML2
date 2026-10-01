// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.opssimd.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3x4Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float3x4Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float3x4OpsKernelsArray {
    private Float3x4OpsKernelsArray() {}

    public static float[] invNegativeX_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeX_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self10 * _t0;
        float _t9 = _self21 * _t1;
        float _t10 = _self11 * _t0;
        float _t11 = _self20 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t13, _t11, _t19) - java.lang.Math.fma(_t8, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, _t28 * _t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = -(_t27 * _t32);
            dest[destOffset + 1] = -(_t28 * _t32);
            dest[destOffset + 2] = -(_t26 * _t32);
        } else {
            dest[destOffset] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    /** {@code invNegativeX_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invNegativeX_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self10 * _t0;
        float _t9 = _self21 * _t1;
        float _t10 = _self11 * _t0;
        float _t11 = _self20 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t13) * (_t11) + (_t19)) - ((_t8) * (_t12) + (_t19));
        float _t31 = ((_t26) * (_t26) + (((_t27) * (_t27) + (_t28 * _t28))));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = -(_t27 * _t32);
            dest[destOffset + 1] = -(_t28 * _t32);
            dest[destOffset + 2] = -(_t26 * _t32);
        } else {
            dest[destOffset] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    public static float[] invNegativeY_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeY_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self01 * _t0;
        float _t9 = _self20 * _t1;
        float _t10 = _self00 * _t0;
        float _t11 = _self21 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self02 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t13, _t11, _t19) - java.lang.Math.fma(_t8, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, _t28 * _t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = -(_t28 * _t32);
            dest[destOffset + 1] = -(_t27 * _t32);
            dest[destOffset + 2] = -(_t26 * _t32);
        } else {
            dest[destOffset] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    /** {@code invNegativeY_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invNegativeY_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self01 * _t0;
        float _t9 = _self20 * _t1;
        float _t10 = _self00 * _t0;
        float _t11 = _self21 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self02 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t13) * (_t11) + (_t19)) - ((_t8) * (_t12) + (_t19));
        float _t31 = ((_t26) * (_t26) + (((_t27) * (_t27) + (_t28 * _t28))));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = -(_t28 * _t32);
            dest[destOffset + 1] = -(_t27 * _t32);
            dest[destOffset + 2] = -(_t26 * _t32);
        } else {
            dest[destOffset] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    public static float[] invNegativeZ_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeZ_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self10, _self11, _self12);
        float _t8 = _self00 * _t0;
        float _t9 = _self11 * _t1;
        float _t10 = _self01 * _t0;
        float _t11 = _self10 * _t1;
        float _t12 = _self12 * _t1;
        float _t13 = _self02 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t13, _t11, _t19) - java.lang.Math.fma(_t8, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, _t28 * _t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = -(_t27 * _t32);
            dest[destOffset + 1] = -(_t28 * _t32);
            dest[destOffset + 2] = -(_t26 * _t32);
        } else {
            dest[destOffset] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    /** {@code invNegativeZ_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invNegativeZ_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self10, _self11, _self12);
        float _t8 = _self00 * _t0;
        float _t9 = _self11 * _t1;
        float _t10 = _self01 * _t0;
        float _t11 = _self10 * _t1;
        float _t12 = _self12 * _t1;
        float _t13 = _self02 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t13) * (_t11) + (_t19)) - ((_t8) * (_t12) + (_t19));
        float _t31 = ((_t26) * (_t26) + (((_t27) * (_t27) + (_t28 * _t28))));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = -(_t27 * _t32);
            dest[destOffset + 1] = -(_t28 * _t32);
            dest[destOffset + 2] = -(_t26 * _t32);
        } else {
            dest[destOffset] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    public static float[] invPositiveX_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveX_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self10 * _t0;
        float _t9 = _self21 * _t1;
        float _t10 = _self11 * _t0;
        float _t11 = _self20 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t13, _t11, _t19) - java.lang.Math.fma(_t8, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, _t28 * _t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = _t27 * _t32;
            dest[destOffset + 1] = _t28 * _t32;
            dest[destOffset + 2] = _t26 * _t32;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@code invPositiveX_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invPositiveX_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self10 * _t0;
        float _t9 = _self21 * _t1;
        float _t10 = _self11 * _t0;
        float _t11 = _self20 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t13) * (_t11) + (_t19)) - ((_t8) * (_t12) + (_t19));
        float _t31 = ((_t26) * (_t26) + (((_t27) * (_t27) + (_t28 * _t28))));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = _t27 * _t32;
            dest[destOffset + 1] = _t28 * _t32;
            dest[destOffset + 2] = _t26 * _t32;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    public static float[] invPositiveY_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveY_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self01 * _t0;
        float _t9 = _self20 * _t1;
        float _t10 = _self00 * _t0;
        float _t11 = _self21 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self02 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t13, _t11, _t19) - java.lang.Math.fma(_t8, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, _t28 * _t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = _t28 * _t32;
            dest[destOffset + 1] = _t27 * _t32;
            dest[destOffset + 2] = _t26 * _t32;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@code invPositiveY_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invPositiveY_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self01 * _t0;
        float _t9 = _self20 * _t1;
        float _t10 = _self00 * _t0;
        float _t11 = _self21 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self02 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t13) * (_t11) + (_t19)) - ((_t8) * (_t12) + (_t19));
        float _t31 = ((_t26) * (_t26) + (((_t27) * (_t27) + (_t28 * _t28))));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = _t28 * _t32;
            dest[destOffset + 1] = _t27 * _t32;
            dest[destOffset + 2] = _t26 * _t32;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    public static float[] invPositiveZ_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveZ_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self10, _self11, _self12);
        float _t8 = _self00 * _t0;
        float _t9 = _self11 * _t1;
        float _t10 = _self01 * _t0;
        float _t11 = _self10 * _t1;
        float _t12 = _self12 * _t1;
        float _t13 = _self02 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t13, _t11, _t19) - java.lang.Math.fma(_t8, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, _t28 * _t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = _t27 * _t32;
            dest[destOffset + 1] = _t28 * _t32;
            dest[destOffset + 2] = _t26 * _t32;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@code invPositiveZ_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invPositiveZ_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self10, _self11, _self12);
        float _t8 = _self00 * _t0;
        float _t9 = _self11 * _t1;
        float _t10 = _self01 * _t0;
        float _t11 = _self10 * _t1;
        float _t12 = _self12 * _t1;
        float _t13 = _self02 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t13 * _t9);
        float _t19 = -(_t8 * _t12);
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t13) * (_t11) + (_t19)) - ((_t8) * (_t12) + (_t19));
        float _t31 = ((_t26) * (_t26) + (((_t27) * (_t27) + (_t28 * _t28))));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        if (_t31 != 0.0f) {
            dest[destOffset] = _t27 * _t32;
            dest[destOffset + 1] = _t28 * _t32;
            dest[destOffset + 2] = _t26 * _t32;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    public static float[] invert_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t15 = _self11 * _t0;
        float _t16 = _self22 * _t1;
        float _t17 = _self12 * _t0;
        float _t18 = _self21 * _t1;
        float _t19 = _self10 * _t0;
        float _t20 = _self20 * _t1;
        float _t21 = _self02 * _t2;
        float _t22 = _self00 * _t2;
        float _t23 = _self01 * _t2;
        return invert_degenerate_sc8781f7c_1_fma(dest, destOffset, _t0, _t1, _t2, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _self23 * _t1, _self13 * _t0, _self03 * _t2, -_t17 * _t18, -(_t15 * _t20), -_t17 * _t20, -_t23 * _t16, -_t21 * _t15, -(_t19 * _t16), -_t21 * _t20, -_t22 * _t17, -_t22 * _t18, -_t23 * _t19);
    }

    /** {@code invert_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invert_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t15 = _self11 * _t0;
        float _t16 = _self22 * _t1;
        float _t17 = _self12 * _t0;
        float _t18 = _self21 * _t1;
        float _t19 = _self10 * _t0;
        float _t20 = _self20 * _t1;
        float _t21 = _self02 * _t2;
        float _t22 = _self00 * _t2;
        float _t23 = _self01 * _t2;
        return invert_degenerate_sc8781f7c_1_mulAdd(dest, destOffset, _t0, _t1, _t2, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _self23 * _t1, _self13 * _t0, _self03 * _t2, -_t17 * _t18, -(_t15 * _t20), -_t17 * _t20, -_t23 * _t16, -_t21 * _t15, -(_t19 * _t16), -_t21 * _t20, -_t22 * _t17, -_t22 * _t18, -_t23 * _t19);
    }

    /** Piece 2 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invert_degenerate_sc8781f7c_1_fma(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t37, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46) {
        float _t67 = java.lang.Math.fma(_t15, _t16, _t37) - java.lang.Math.fma(_t17, _t18, _t37);
        float _t68 = java.lang.Math.fma(_t19, _t18, _t38) - java.lang.Math.fma(_t15, _t20, _t38);
        float _t70 = java.lang.Math.fma(_t21, _t18, _t40) - java.lang.Math.fma(_t23, _t16, _t40);
        float _t71 = java.lang.Math.fma(_t23, _t17, _t41) - java.lang.Math.fma(_t21, _t15, _t41);
        float _t80_inv = 1.0f / java.lang.Math.fma(_t68, _t21, java.lang.Math.fma(_t67, _t22, (java.lang.Math.fma(_t17, _t20, _t39) - java.lang.Math.fma(_t19, _t16, _t39)) * _t23));
        float _sp2 = _t1 * _t80_inv;
        float _sp1 = _t0 * _t80_inv;
        float _sp0 = _t2 * _t80_inv;
        dest[destOffset] = _t67 * _sp0;
        dest[destOffset + 1] = _t70 * _sp1;
        dest[destOffset + 2] = _t71 * _sp2;
        return invert_degenerate_sc8781f7c_2_fma(dest, destOffset, _t24, _t25, _t26, _t67, _t68, _t70, _t71, java.lang.Math.fma(_t17, _t20, _t42) - java.lang.Math.fma(_t19, _t16, _t42), java.lang.Math.fma(_t22, _t16, _t43) - java.lang.Math.fma(_t21, _t20, _t43), java.lang.Math.fma(_t21, _t19, _t44) - java.lang.Math.fma(_t22, _t17, _t44), java.lang.Math.fma(_t23, _t20, _t45) - java.lang.Math.fma(_t22, _t18, _t45), java.lang.Math.fma(_t22, _t15, _t46) - java.lang.Math.fma(_t23, _t19, _t46), _t80_inv, _sp2, _sp1, _sp0);
    }

    /** Piece 2 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invert_degenerate_sc8781f7c_1_mulAdd(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t37, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46) {
        float _t67 = ((_t15) * (_t16) + (_t37)) - ((_t17) * (_t18) + (_t37));
        float _t68 = ((_t19) * (_t18) + (_t38)) - ((_t15) * (_t20) + (_t38));
        float _t70 = ((_t21) * (_t18) + (_t40)) - ((_t23) * (_t16) + (_t40));
        float _t71 = ((_t23) * (_t17) + (_t41)) - ((_t21) * (_t15) + (_t41));
        float _t80_inv = 1.0f / ((_t68) * (_t21) + (((_t67) * (_t22) + ((((_t17) * (_t20) + (_t39)) - ((_t19) * (_t16) + (_t39))) * _t23))));
        float _sp2 = _t1 * _t80_inv;
        float _sp1 = _t0 * _t80_inv;
        float _sp0 = _t2 * _t80_inv;
        dest[destOffset] = _t67 * _sp0;
        dest[destOffset + 1] = _t70 * _sp1;
        dest[destOffset + 2] = _t71 * _sp2;
        return invert_degenerate_sc8781f7c_2_mulAdd(dest, destOffset, _t24, _t25, _t26, _t67, _t68, _t70, _t71, ((_t17) * (_t20) + (_t42)) - ((_t19) * (_t16) + (_t42)), ((_t22) * (_t16) + (_t43)) - ((_t21) * (_t20) + (_t43)), ((_t21) * (_t19) + (_t44)) - ((_t22) * (_t17) + (_t44)), ((_t23) * (_t20) + (_t45)) - ((_t22) * (_t18) + (_t45)), ((_t22) * (_t15) + (_t46)) - ((_t23) * (_t19) + (_t46)), _t80_inv, _sp2, _sp1, _sp0);
    }

    /** Piece 3 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invert_degenerate_sc8781f7c_2_fma(float[] dest, int destOffset, float _t24, float _t25, float _t26, float _t67, float _t68, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t80_inv, float _sp2, float _sp1, float _sp0) {
        dest[destOffset + 3] = -java.lang.Math.fma(_t71, _t24, java.lang.Math.fma(_t70, _t25, _t67 * _t26)) * _t80_inv;
        dest[destOffset + 4] = _t72 * _sp0;
        dest[destOffset + 5] = _t73 * _sp1;
        dest[destOffset + 6] = _t74 * _sp2;
        dest[destOffset + 7] = -java.lang.Math.fma(_t74, _t24, java.lang.Math.fma(_t73, _t25, _t72 * _t26)) * _t80_inv;
        dest[destOffset + 8] = _t68 * _sp0;
        dest[destOffset + 9] = _t75 * _sp1;
        dest[destOffset + 10] = _t76 * _sp2;
        dest[destOffset + 11] = -java.lang.Math.fma(_t76, _t24, java.lang.Math.fma(_t75, _t25, _t68 * _t26)) * _t80_inv;
        return dest;
    }

    /** Piece 3 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invert_degenerate_sc8781f7c_2_mulAdd(float[] dest, int destOffset, float _t24, float _t25, float _t26, float _t67, float _t68, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t80_inv, float _sp2, float _sp1, float _sp0) {
        dest[destOffset + 3] = -((_t71) * (_t24) + (((_t70) * (_t25) + (_t67 * _t26)))) * _t80_inv;
        dest[destOffset + 4] = _t72 * _sp0;
        dest[destOffset + 5] = _t73 * _sp1;
        dest[destOffset + 6] = _t74 * _sp2;
        dest[destOffset + 7] = -((_t74) * (_t24) + (((_t73) * (_t25) + (_t72 * _t26)))) * _t80_inv;
        dest[destOffset + 8] = _t68 * _sp0;
        dest[destOffset + 9] = _t75 * _sp1;
        dest[destOffset + 10] = _t76 * _sp2;
        dest[destOffset + 11] = -((_t76) * (_t24) + (((_t75) * (_t25) + (_t68 * _t26)))) * _t80_inv;
        return dest;
    }

    public static float[] invertProduct_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invertProduct_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _other00 = other[otherOffset];
        float _other01 = other[otherOffset + 1];
        float _other02 = other[otherOffset + 2];
        float _other10 = other[otherOffset + 4];
        float _other11 = other[otherOffset + 5];
        float _other12 = other[otherOffset + 6];
        float _other20 = other[otherOffset + 8];
        float _other21 = other[otherOffset + 9];
        float _other22 = other[otherOffset + 10];
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        return invertProduct_degenerate_sffcca8a5_1_fma(dest, destOffset, _self00, _self01, _self02, src[srcOffset + 3], _self10, _self11, _self12, src[srcOffset + 7], _self20, _self21, _self22, src[srcOffset + 11], _other00, other[otherOffset + 3], other[otherOffset + 7], _other20, _other21, _other22, other[otherOffset + 11], _t0, _t1, unitScale(_other20, _other21, _other22), unitScale(_self10, _self11, _self12), unitScale(_self20, _self21, _self22), unitScale(_self00, _self01, _self02), _other01 * _t0, _other12 * _t1, _other02 * _t0, _other11 * _t1, _other10 * _t1);
    }

    /** {@code invertProduct_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invertProduct_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _other00 = other[otherOffset];
        float _other01 = other[otherOffset + 1];
        float _other02 = other[otherOffset + 2];
        float _other10 = other[otherOffset + 4];
        float _other11 = other[otherOffset + 5];
        float _other12 = other[otherOffset + 6];
        float _other20 = other[otherOffset + 8];
        float _other21 = other[otherOffset + 9];
        float _other22 = other[otherOffset + 10];
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        return invertProduct_degenerate_sffcca8a5_1_mulAdd(dest, destOffset, _self00, _self01, _self02, src[srcOffset + 3], _self10, _self11, _self12, src[srcOffset + 7], _self20, _self21, _self22, src[srcOffset + 11], _other00, other[otherOffset + 3], other[otherOffset + 7], _other20, _other21, _other22, other[otherOffset + 11], _t0, _t1, unitScale(_other20, _other21, _other22), unitScale(_self10, _self11, _self12), unitScale(_self20, _self21, _self22), unitScale(_self00, _self01, _self02), _other01 * _t0, _other12 * _t1, _other02 * _t0, _other11 * _t1, _other10 * _t1);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other00, float _other03, float _other13, float _other20, float _other21, float _other22, float _other23, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t30, float _t31, float _t32, float _t33, float _t34) {
        float _t35 = _other21 * _t2;
        float _t36 = _other20 * _t2;
        float _t37 = _other22 * _t2;
        float _t38 = _other00 * _t0;
        float _t39 = _self10 * _t3;
        float _t40 = _self21 * _t4;
        float _t41 = _self11 * _t3;
        float _t42 = _self20 * _t4;
        float _t43 = _self02 * _t5;
        float _t44 = _self22 * _t4;
        float _t45 = _self12 * _t3;
        float _t46 = _self00 * _t5;
        float _t47 = _self01 * _t5;
        return invertProduct_degenerate_sffcca8a5_2_fma(dest, destOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t30, _t31, _t32, _t33, _t34, _t35, _t36, _t37, _t38, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _self23 * _t4, _self13 * _t3, _self03 * _t5, _other23 * _t2, _other13 * _t1, _other03 * _t0, -_t32 * _t33, -(_t33 * _t36), -_t31 * _t35, -_t31 * _t36, -_t41 * _t42, -_t45 * _t40, -_t45 * _t42, -_t30 * _t37, -_t39 * _t44, -_t46 * _t40, -(_t43 * _t42), -(_t47 * _t44), _t47 * -_t39, -_t46 * _t45, _t43 * -_t41, -_t38 * _t31, -_t32 * _t36, -(_t34 * _t37), -_t30 * _t34, -_t38 * _t35);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other00, float _other03, float _other13, float _other20, float _other21, float _other22, float _other23, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t30, float _t31, float _t32, float _t33, float _t34) {
        float _t35 = _other21 * _t2;
        float _t36 = _other20 * _t2;
        float _t37 = _other22 * _t2;
        float _t38 = _other00 * _t0;
        float _t39 = _self10 * _t3;
        float _t40 = _self21 * _t4;
        float _t41 = _self11 * _t3;
        float _t42 = _self20 * _t4;
        float _t43 = _self02 * _t5;
        float _t44 = _self22 * _t4;
        float _t45 = _self12 * _t3;
        float _t46 = _self00 * _t5;
        float _t47 = _self01 * _t5;
        return invertProduct_degenerate_sffcca8a5_2_mulAdd(dest, destOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t30, _t31, _t32, _t33, _t34, _t35, _t36, _t37, _t38, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _self23 * _t4, _self13 * _t3, _self03 * _t5, _other23 * _t2, _other13 * _t1, _other03 * _t0, -_t32 * _t33, -(_t33 * _t36), -_t31 * _t35, -_t31 * _t36, -_t41 * _t42, -_t45 * _t40, -_t45 * _t42, -_t30 * _t37, -_t39 * _t44, -_t46 * _t40, -(_t43 * _t42), -(_t47 * _t44), _t47 * -_t39, -_t46 * _t45, _t43 * -_t41, -_t38 * _t31, -_t32 * _t36, -(_t34 * _t37), -_t30 * _t34, -_t38 * _t35);
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_2_fma(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79, float _t80, float _t81, float _t82, float _t83, float _t84, float _t85, float _t86, float _t87, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93) {
        return invertProduct_degenerate_sffcca8a5_3_fma(dest, destOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t30, _t31, _t32, _t33, _t34, _t35, _t36, _t37, _t38, _t39, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t48, _t49, _t50, _t51, _t52, _t53, _t77, _t80, _t88, _t89, _t90, _t91, _t92, _t93, java.lang.Math.fma(_t30, _t31, _t74) - java.lang.Math.fma(_t32, _t33, _t74), java.lang.Math.fma(_t34, _t35, _t75) - java.lang.Math.fma(_t33, _t36, _t75), java.lang.Math.fma(_t33, _t37, _t76) - java.lang.Math.fma(_t31, _t35, _t76), java.lang.Math.fma(_t39, _t40, _t78) - java.lang.Math.fma(_t41, _t42, _t78), java.lang.Math.fma(_t41, _t44, _t79) - java.lang.Math.fma(_t45, _t40, _t79), java.lang.Math.fma(_t32, _t35, _t81) - java.lang.Math.fma(_t30, _t37, _t81), java.lang.Math.fma(_t45, _t42, _t82) - java.lang.Math.fma(_t39, _t44, _t82), java.lang.Math.fma(_t47, _t42, _t83) - java.lang.Math.fma(_t46, _t40, _t83), java.lang.Math.fma(_t46, _t44, _t84) - java.lang.Math.fma(_t43, _t42, _t84), java.lang.Math.fma(_t43, _t40, _t85) - java.lang.Math.fma(_t47, _t44, _t85), java.lang.Math.fma(_t46, _t41, _t86) - java.lang.Math.fma(_t47, _t39, _t86), java.lang.Math.fma(_t43, _t39, _t87) - java.lang.Math.fma(_t46, _t45, _t87));
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_2_mulAdd(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79, float _t80, float _t81, float _t82, float _t83, float _t84, float _t85, float _t86, float _t87, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93) {
        return invertProduct_degenerate_sffcca8a5_3_mulAdd(dest, destOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t30, _t31, _t32, _t33, _t34, _t35, _t36, _t37, _t38, _t39, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t48, _t49, _t50, _t51, _t52, _t53, _t77, _t80, _t88, _t89, _t90, _t91, _t92, _t93, ((_t30) * (_t31) + (_t74)) - ((_t32) * (_t33) + (_t74)), ((_t34) * (_t35) + (_t75)) - ((_t33) * (_t36) + (_t75)), ((_t33) * (_t37) + (_t76)) - ((_t31) * (_t35) + (_t76)), ((_t39) * (_t40) + (_t78)) - ((_t41) * (_t42) + (_t78)), ((_t41) * (_t44) + (_t79)) - ((_t45) * (_t40) + (_t79)), ((_t32) * (_t35) + (_t81)) - ((_t30) * (_t37) + (_t81)), ((_t45) * (_t42) + (_t82)) - ((_t39) * (_t44) + (_t82)), ((_t47) * (_t42) + (_t83)) - ((_t46) * (_t40) + (_t83)), ((_t46) * (_t44) + (_t84)) - ((_t43) * (_t42) + (_t84)), ((_t43) * (_t40) + (_t85)) - ((_t47) * (_t44) + (_t85)), ((_t46) * (_t41) + (_t86)) - ((_t47) * (_t39) + (_t86)), ((_t43) * (_t39) + (_t87)) - ((_t46) * (_t45) + (_t87)));
    }

    /** Piece 4 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_3_fma(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53, float _t77, float _t80, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93, float _t134, float _t135, float _t136, float _t138, float _t139, float _t141, float _t142, float _t143, float _t144, float _t145, float _t146, float _t147) {
        float _t187 = java.lang.Math.fma(_t135, _t32, java.lang.Math.fma(_t136, _t38, (java.lang.Math.fma(_t31, _t36, _t77) - java.lang.Math.fma(_t34, _t37, _t77)) * _t30));
        float _t188 = java.lang.Math.fma(_t138, _t43, java.lang.Math.fma(_t139, _t46, (java.lang.Math.fma(_t45, _t42, _t80) - java.lang.Math.fma(_t39, _t44, _t80)) * _t47));
        return invertProduct_degenerate_sffcca8a5_4_fma(dest, destOffset, _t0, _t1, _t2, _t3, _t4, _t48, _t49, _t50, _t51, _t52, _t53, _t134, _t135, _t136, _t138, _t139, _t141, _t142, _t143, _t144, _t145, _t146, _t147, java.lang.Math.fma(_t47, _t45, _t88) - java.lang.Math.fma(_t43, _t41, _t88), java.lang.Math.fma(_t32, _t34, _t89) - java.lang.Math.fma(_t38, _t31, _t89), java.lang.Math.fma(_t38, _t37, _t90) - java.lang.Math.fma(_t32, _t36, _t90), java.lang.Math.fma(_t31, _t36, _t91) - java.lang.Math.fma(_t34, _t37, _t91), java.lang.Math.fma(_t38, _t33, _t92) - java.lang.Math.fma(_t30, _t34, _t92), java.lang.Math.fma(_t30, _t36, _t93) - java.lang.Math.fma(_t38, _t35, _t93), _t187, 1.0f / _t187, _t188, 1.0f / _t188, _t134 * _t2 / _t187, _t138 * _t5 / _t188, _t141 * _t1 / _t187, _t142 * _t5 / _t188, _t136 * _t0 / _t187, _t139 * _t5 / _t188, _t143 * _t3 / _t188);
    }

    /** Piece 4 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_3_mulAdd(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53, float _t77, float _t80, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93, float _t134, float _t135, float _t136, float _t138, float _t139, float _t141, float _t142, float _t143, float _t144, float _t145, float _t146, float _t147) {
        float _t187 = ((_t135) * (_t32) + (((_t136) * (_t38) + ((((_t31) * (_t36) + (_t77)) - ((_t34) * (_t37) + (_t77))) * _t30))));
        float _t188 = ((_t138) * (_t43) + (((_t139) * (_t46) + ((((_t45) * (_t42) + (_t80)) - ((_t39) * (_t44) + (_t80))) * _t47))));
        return invertProduct_degenerate_sffcca8a5_4_mulAdd(dest, destOffset, _t0, _t1, _t2, _t3, _t4, _t48, _t49, _t50, _t51, _t52, _t53, _t134, _t135, _t136, _t138, _t139, _t141, _t142, _t143, _t144, _t145, _t146, _t147, ((_t47) * (_t45) + (_t88)) - ((_t43) * (_t41) + (_t88)), ((_t32) * (_t34) + (_t89)) - ((_t38) * (_t31) + (_t89)), ((_t38) * (_t37) + (_t90)) - ((_t32) * (_t36) + (_t90)), ((_t31) * (_t36) + (_t91)) - ((_t34) * (_t37) + (_t91)), ((_t38) * (_t33) + (_t92)) - ((_t30) * (_t34) + (_t92)), ((_t30) * (_t36) + (_t93)) - ((_t38) * (_t35) + (_t93)), _t187, 1.0f / _t187, _t188, 1.0f / _t188, _t134 * _t2 / _t187, _t138 * _t5 / _t188, _t141 * _t1 / _t187, _t142 * _t5 / _t188, _t136 * _t0 / _t187, _t139 * _t5 / _t188, _t143 * _t3 / _t188);
    }

    /** Piece 5 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_4_fma(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53, float _t134, float _t135, float _t136, float _t138, float _t139, float _t141, float _t142, float _t143, float _t144, float _t145, float _t146, float _t147, float _t148, float _t149, float _t150, float _t151, float _t152, float _t153, float _t187, float _t187_inv, float _t188, float _t188_inv, float _t207, float _t208, float _t209, float _t210, float _t211, float _t212, float _t213) {
        float _t214 = _t144 * _t3 / _t188;
        float _t215 = _t145 * _t3 / _t188;
        float _t216 = _t146 * _t4 / _t188;
        float _t217 = _t147 * _t4 / _t188;
        float _t218 = _t148 * _t4 / _t188;
        dest[destOffset] = java.lang.Math.fma(_t207, _t208, java.lang.Math.fma(_t209, _t210, _t211 * _t212));
        dest[destOffset + 1] = java.lang.Math.fma(_t207, _t213, java.lang.Math.fma(_t209, _t214, _t211 * _t215));
        dest[destOffset + 2] = java.lang.Math.fma(_t207, _t216, java.lang.Math.fma(_t209, _t217, _t211 * _t218));
        return invertProduct_degenerate_sffcca8a5_5_fma(dest, destOffset, _t51, _t52, _t53, _t134, _t135, _t136, _t141, _t149, _t150, _t151, _t152, _t153, _t187_inv, _t207, _t208, _t209, _t210, _t211, _t212, _t213, _t214, _t215, _t216, _t217, _t218, _t149 * _t2 / _t187, _t150 * _t1 / _t187, _t151 * _t0 / _t187, _t152 * _t2 / _t187, _t153 * _t1 / _t187, _t135 * _t0 / _t187, -(java.lang.Math.fma(_t147, _t48, java.lang.Math.fma(_t144, _t49, _t142 * _t50)) * _t188_inv), -(java.lang.Math.fma(_t148, _t48, java.lang.Math.fma(_t145, _t49, _t139 * _t50)) * _t188_inv), -(java.lang.Math.fma(_t146, _t48, java.lang.Math.fma(_t143, _t49, _t138 * _t50)) * _t188_inv));
    }

    /** Piece 5 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_4_mulAdd(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53, float _t134, float _t135, float _t136, float _t138, float _t139, float _t141, float _t142, float _t143, float _t144, float _t145, float _t146, float _t147, float _t148, float _t149, float _t150, float _t151, float _t152, float _t153, float _t187, float _t187_inv, float _t188, float _t188_inv, float _t207, float _t208, float _t209, float _t210, float _t211, float _t212, float _t213) {
        float _t214 = _t144 * _t3 / _t188;
        float _t215 = _t145 * _t3 / _t188;
        float _t216 = _t146 * _t4 / _t188;
        float _t217 = _t147 * _t4 / _t188;
        float _t218 = _t148 * _t4 / _t188;
        dest[destOffset] = ((_t207) * (_t208) + (((_t209) * (_t210) + (_t211 * _t212))));
        dest[destOffset + 1] = ((_t207) * (_t213) + (((_t209) * (_t214) + (_t211 * _t215))));
        dest[destOffset + 2] = ((_t207) * (_t216) + (((_t209) * (_t217) + (_t211 * _t218))));
        return invertProduct_degenerate_sffcca8a5_5_mulAdd(dest, destOffset, _t51, _t52, _t53, _t134, _t135, _t136, _t141, _t149, _t150, _t151, _t152, _t153, _t187_inv, _t207, _t208, _t209, _t210, _t211, _t212, _t213, _t214, _t215, _t216, _t217, _t218, _t149 * _t2 / _t187, _t150 * _t1 / _t187, _t151 * _t0 / _t187, _t152 * _t2 / _t187, _t153 * _t1 / _t187, _t135 * _t0 / _t187, -(((_t147) * (_t48) + (((_t144) * (_t49) + (_t142 * _t50)))) * _t188_inv), -(((_t148) * (_t48) + (((_t145) * (_t49) + (_t139 * _t50)))) * _t188_inv), -(((_t146) * (_t48) + (((_t143) * (_t49) + (_t138 * _t50)))) * _t188_inv));
    }

    /** Piece 6 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_5_fma(float[] dest, int destOffset, float _t51, float _t52, float _t53, float _t134, float _t135, float _t136, float _t141, float _t149, float _t150, float _t151, float _t152, float _t153, float _t187_inv, float _t207, float _t208, float _t209, float _t210, float _t211, float _t212, float _t213, float _t214, float _t215, float _t216, float _t217, float _t218, float _t219, float _t220, float _t221, float _t222, float _t223, float _t224, float _t231, float _t232, float _t233) {
        dest[destOffset + 3] = java.lang.Math.fma(_t231, _t209, java.lang.Math.fma(_t232, _t211, java.lang.Math.fma(_t233, _t207, -(java.lang.Math.fma(_t134, _t51, java.lang.Math.fma(_t141, _t52, _t136 * _t53)) * _t187_inv))));
        dest[destOffset + 4] = java.lang.Math.fma(_t219, _t208, java.lang.Math.fma(_t220, _t210, _t221 * _t212));
        dest[destOffset + 5] = java.lang.Math.fma(_t219, _t213, java.lang.Math.fma(_t220, _t214, _t221 * _t215));
        dest[destOffset + 6] = java.lang.Math.fma(_t219, _t216, java.lang.Math.fma(_t220, _t217, _t221 * _t218));
        dest[destOffset + 7] = java.lang.Math.fma(_t231, _t220, java.lang.Math.fma(_t232, _t221, java.lang.Math.fma(_t233, _t219, -(java.lang.Math.fma(_t149, _t51, java.lang.Math.fma(_t150, _t52, _t151 * _t53)) * _t187_inv))));
        dest[destOffset + 8] = java.lang.Math.fma(_t222, _t208, java.lang.Math.fma(_t223, _t210, _t224 * _t212));
        dest[destOffset + 9] = java.lang.Math.fma(_t222, _t213, java.lang.Math.fma(_t223, _t214, _t224 * _t215));
        dest[destOffset + 10] = java.lang.Math.fma(_t222, _t216, java.lang.Math.fma(_t223, _t217, _t224 * _t218));
        dest[destOffset + 11] = java.lang.Math.fma(_t231, _t223, java.lang.Math.fma(_t232, _t224, java.lang.Math.fma(_t233, _t222, -(java.lang.Math.fma(_t152, _t51, java.lang.Math.fma(_t153, _t52, _t135 * _t53)) * _t187_inv))));
        return dest;
    }

    /** Piece 6 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_5_mulAdd(float[] dest, int destOffset, float _t51, float _t52, float _t53, float _t134, float _t135, float _t136, float _t141, float _t149, float _t150, float _t151, float _t152, float _t153, float _t187_inv, float _t207, float _t208, float _t209, float _t210, float _t211, float _t212, float _t213, float _t214, float _t215, float _t216, float _t217, float _t218, float _t219, float _t220, float _t221, float _t222, float _t223, float _t224, float _t231, float _t232, float _t233) {
        dest[destOffset + 3] = ((_t231) * (_t209) + (((_t232) * (_t211) + (((_t233) * (_t207) - (((_t134) * (_t51) + (((_t141) * (_t52) + (_t136 * _t53)))) * _t187_inv))))));
        dest[destOffset + 4] = ((_t219) * (_t208) + (((_t220) * (_t210) + (_t221 * _t212))));
        dest[destOffset + 5] = ((_t219) * (_t213) + (((_t220) * (_t214) + (_t221 * _t215))));
        dest[destOffset + 6] = ((_t219) * (_t216) + (((_t220) * (_t217) + (_t221 * _t218))));
        dest[destOffset + 7] = ((_t231) * (_t220) + (((_t232) * (_t221) + (((_t233) * (_t219) - (((_t149) * (_t51) + (((_t150) * (_t52) + (_t151 * _t53)))) * _t187_inv))))));
        dest[destOffset + 8] = ((_t222) * (_t208) + (((_t223) * (_t210) + (_t224 * _t212))));
        dest[destOffset + 9] = ((_t222) * (_t213) + (((_t223) * (_t214) + (_t224 * _t215))));
        dest[destOffset + 10] = ((_t222) * (_t216) + (((_t223) * (_t217) + (_t224 * _t218))));
        dest[destOffset + 11] = ((_t231) * (_t223) + (((_t232) * (_t224) + (((_t233) * (_t222) - (((_t152) * (_t51) + (((_t153) * (_t52) + (_t135 * _t53)))) * _t187_inv))))));
        return dest;
    }

    public static float[] transpose_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i];
        }
        return dest;
    }

    public static float[] add_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest[destOffset + _i] = other[otherOffset + _i] + src[srcOffset + _i];
        }
        return dest;
    }

    public static float[] mul_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        for (int _i = 0; _i < 12; _i++) {
            dest[destOffset + _i] = scalar * src[srcOffset + _i];
        }
        return dest;
    }

    public static float[] negate_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest[destOffset + _i] = -src[srcOffset + _i];
        }
        return dest;
    }

    public static float[] sub_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i] - other[otherOffset + _i];
        }
        return dest;
    }

    public static float[] set_scalar(float[] dest, int destOffset, float[] v, int vOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest[destOffset + _i] = v[vOffset + _i];
        }
        return dest;
    }

    public static float[] withTranslation_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float tX, float tY, float tZ) {
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = tX;
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = tY;
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = tZ;
        return dest;
    }

    public static float[] withTranslation_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] t, int tOffset) {
        float _tx = t[tOffset];
        float _ty = t[tOffset + 1];
        float _tz = t[tOffset + 2];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _tx;
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = _ty;
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _tz;
        return dest;
    }

    public static float[] toRigid_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_degenerate_fma(dest, destOffset, src, srcOffset);
        return toRigid_degenerate_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toRigid_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] toRigid_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
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
            dest[destOffset + 3] = _sp0 * _t182;
            dest[destOffset + 4] = _sp0 * _t201;
            dest[destOffset + 5] = _sp0 * _t202;
            dest[destOffset + 6] = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest[destOffset + 3] = 0.5f * (float) java.lang.Math.sqrt(_t208);
                dest[destOffset + 4] = _sp3 * _t199;
                dest[destOffset + 5] = _sp3 * _t200;
                dest[destOffset + 6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dest[destOffset + 3] = _sp1 * _t199;
                    dest[destOffset + 4] = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    dest[destOffset + 5] = _sp1 * _t184;
                    dest[destOffset + 6] = _sp1 * _t201;
                } else {
                    dest[destOffset + 3] = _sp2 * _t200;
                    dest[destOffset + 4] = _sp2 * _t184;
                    dest[destOffset + 5] = 0.5f * (float) java.lang.Math.sqrt(_t210);
                    dest[destOffset + 6] = _sp2 * _t202;
                }
            }
        }
        dest[destOffset] = _self03;
        dest[destOffset + 1] = _self13;
        dest[destOffset + 2] = _self23;
        return dest;
    }

    /** {@code toRigid_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] toRigid_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
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
            dest[destOffset + 3] = _sp0 * _t182;
            dest[destOffset + 4] = _sp0 * _t201;
            dest[destOffset + 5] = _sp0 * _t202;
            dest[destOffset + 6] = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest[destOffset + 3] = 0.5f * (float) java.lang.Math.sqrt(_t208);
                dest[destOffset + 4] = _sp3 * _t199;
                dest[destOffset + 5] = _sp3 * _t200;
                dest[destOffset + 6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dest[destOffset + 3] = _sp1 * _t199;
                    dest[destOffset + 4] = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    dest[destOffset + 5] = _sp1 * _t184;
                    dest[destOffset + 6] = _sp1 * _t201;
                } else {
                    dest[destOffset + 3] = _sp2 * _t200;
                    dest[destOffset + 4] = _sp2 * _t184;
                    dest[destOffset + 5] = 0.5f * (float) java.lang.Math.sqrt(_t210);
                    dest[destOffset + 6] = _sp2 * _t202;
                }
            }
        }
        dest[destOffset] = _self03;
        dest[destOffset + 1] = _self13;
        dest[destOffset + 2] = _self23;
        return dest;
    }

    public static float[] toTransform_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_degenerate_fma(dest, destOffset, src, srcOffset);
        return toTransform_degenerate_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toTransform_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] toTransform_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
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
        dest[destOffset] = _self03;
        dest[destOffset + 1] = _self13;
        dest[destOffset + 2] = _self23;
        dest[destOffset + 3] = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dest[destOffset + 4] = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        dest[destOffset + 5] = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213);
        dest[destOffset + 6] = _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dest[destOffset + 7] = _t196 < 0.0f ? -_t56 : _t56;
        dest[destOffset + 8] = _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0;
        dest[destOffset + 9] = _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1;
        return dest;
    }

    /** {@code toTransform_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] toTransform_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
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
        dest[destOffset] = _self03;
        dest[destOffset + 1] = _self13;
        dest[destOffset + 2] = _self23;
        dest[destOffset + 3] = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dest[destOffset + 4] = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        dest[destOffset + 5] = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213);
        dest[destOffset + 6] = _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dest[destOffset + 7] = _t196 < 0.0f ? -_t56 : _t56;
        dest[destOffset + 8] = _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0;
        dest[destOffset + 9] = _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1;
        return dest;
    }

    public static float[] makeIdentity_scalar(float[] dest, int destOffset) {
        dest[destOffset] = 1.0f;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 1.0f;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = 1.0f;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    public static float[] lerp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 12; _i++) {
                float _eself = src[srcOffset + _i];
                dest[destOffset + _i] = java.lang.Math.fma(t, other[otherOffset + _i] - _eself, _eself);
            }
            return dest;
        } else {
            for (int _i = 0; _i < 12; _i++) {
                float _eself = src[srcOffset + _i];
                dest[destOffset + _i] = ((t) * (other[otherOffset + _i] - _eself) + (_eself));
            }
            return dest;
        }
    }

    public static float[] mul_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_scalar_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        float _right00 = right[rightOffset];
        float _right01 = right[rightOffset + 1];
        float _right02 = right[rightOffset + 2];
        float _right03 = right[rightOffset + 3];
        float _right10 = right[rightOffset + 4];
        float _right11 = right[rightOffset + 5];
        float _right12 = right[rightOffset + 6];
        float _right13 = right[rightOffset + 7];
        float _right20 = right[rightOffset + 8];
        float _right21 = right[rightOffset + 9];
        float _right22 = right[rightOffset + 10];
        float _right23 = right[rightOffset + 11];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src[srcOffset + _lo];
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = java.lang.Math.fma(_right20, _eself2, java.lang.Math.fma(_right00, _eself0, _right10 * _eself1));
            dest[destOffset + _lo + 1] = java.lang.Math.fma(_right21, _eself2, java.lang.Math.fma(_right01, _eself0, _right11 * _eself1));
            dest[destOffset + _lo + 2] = java.lang.Math.fma(_right22, _eself2, java.lang.Math.fma(_right02, _eself0, _right12 * _eself1));
            dest[destOffset + _lo + 3] = java.lang.Math.fma(_right03, _eself0, java.lang.Math.fma(_right13, _eself1, java.lang.Math.fma(_right23, _eself2, _eself3)));
        }
        return dest;
    }

    /** {@code mul_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] mul_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _right00 = right[rightOffset];
        float _right01 = right[rightOffset + 1];
        float _right02 = right[rightOffset + 2];
        float _right03 = right[rightOffset + 3];
        float _right10 = right[rightOffset + 4];
        float _right11 = right[rightOffset + 5];
        float _right12 = right[rightOffset + 6];
        float _right13 = right[rightOffset + 7];
        float _right20 = right[rightOffset + 8];
        float _right21 = right[rightOffset + 9];
        float _right22 = right[rightOffset + 10];
        float _right23 = right[rightOffset + 11];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src[srcOffset + _lo];
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = ((_right20) * (_eself2) + (((_right00) * (_eself0) + (_right10 * _eself1))));
            dest[destOffset + _lo + 1] = ((_right21) * (_eself2) + (((_right01) * (_eself0) + (_right11 * _eself1))));
            dest[destOffset + _lo + 2] = ((_right22) * (_eself2) + (((_right02) * (_eself0) + (_right12 * _eself1))));
            dest[destOffset + _lo + 3] = ((_right03) * (_eself0) + (((_right13) * (_eself1) + (((_right23) * (_eself2) + (_eself3))))));
        }
        return dest;
    }

    public static float[] mulMat2x3_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x3_scalar_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        float _right00 = right[rightOffset];
        float _right10 = right[rightOffset + 1];
        float _right01 = right[rightOffset + 2];
        float _right11 = right[rightOffset + 3];
        float _right02 = right[rightOffset + 4];
        float _right12 = right[rightOffset + 5];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src[srcOffset + _lo];
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = java.lang.Math.fma(_right00, _eself0, _right10 * _eself1);
            dest[destOffset + _lo + 1] = java.lang.Math.fma(_right01, _eself0, _right11 * _eself1);
            dest[destOffset + _lo + 2] = _eself2;
            dest[destOffset + _lo + 3] = java.lang.Math.fma(_right02, _eself0, java.lang.Math.fma(_right12, _eself1, _eself3));
        }
        return dest;
    }

    /** {@code mulMat2x3_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] mulMat2x3_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _right00 = right[rightOffset];
        float _right10 = right[rightOffset + 1];
        float _right01 = right[rightOffset + 2];
        float _right11 = right[rightOffset + 3];
        float _right02 = right[rightOffset + 4];
        float _right12 = right[rightOffset + 5];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src[srcOffset + _lo];
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = ((_right00) * (_eself0) + (_right10 * _eself1));
            dest[destOffset + _lo + 1] = ((_right01) * (_eself0) + (_right11 * _eself1));
            dest[destOffset + _lo + 2] = _eself2;
            dest[destOffset + _lo + 3] = ((_right02) * (_eself0) + (((_right12) * (_eself1) + (_eself3))));
        }
        return dest;
    }

    public static float[] mulMat3x3_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat3x3_scalar_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        float _right00 = right[rightOffset];
        float _right10 = right[rightOffset + 1];
        float _right20 = right[rightOffset + 2];
        float _right01 = right[rightOffset + 3];
        float _right11 = right[rightOffset + 4];
        float _right21 = right[rightOffset + 5];
        float _right02 = right[rightOffset + 6];
        float _right12 = right[rightOffset + 7];
        float _right22 = right[rightOffset + 8];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src[srcOffset + _lo];
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = java.lang.Math.fma(_right20, _eself2, java.lang.Math.fma(_right00, _eself0, _right10 * _eself1));
            dest[destOffset + _lo + 1] = java.lang.Math.fma(_right21, _eself2, java.lang.Math.fma(_right01, _eself0, _right11 * _eself1));
            dest[destOffset + _lo + 2] = java.lang.Math.fma(_right22, _eself2, java.lang.Math.fma(_right02, _eself0, _right12 * _eself1));
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    /** {@code mulMat3x3_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] mulMat3x3_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _right00 = right[rightOffset];
        float _right10 = right[rightOffset + 1];
        float _right20 = right[rightOffset + 2];
        float _right01 = right[rightOffset + 3];
        float _right11 = right[rightOffset + 4];
        float _right21 = right[rightOffset + 5];
        float _right02 = right[rightOffset + 6];
        float _right12 = right[rightOffset + 7];
        float _right22 = right[rightOffset + 8];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src[srcOffset + _lo];
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = ((_right20) * (_eself2) + (((_right00) * (_eself0) + (_right10 * _eself1))));
            dest[destOffset + _lo + 1] = ((_right21) * (_eself2) + (((_right01) * (_eself0) + (_right11 * _eself1))));
            dest[destOffset + _lo + 2] = ((_right22) * (_eself2) + (((_right02) * (_eself0) + (_right12 * _eself1))));
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static float[] mulMat4x4_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat4x4_scalar_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        for (int _l = 0; _l < 4; _l++) {
            int _lo = _l * 4;
            float _eright0 = right[rightOffset + _lo];
            float _eright1 = right[rightOffset + _lo + 1];
            float _eright2 = right[rightOffset + _lo + 2];
            float _eright3 = right[rightOffset + _lo + 3];
            dest[destOffset + _lo] = java.lang.Math.fma(_eright3, _self03, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest[destOffset + _lo + 1] = java.lang.Math.fma(_eright3, _self13, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest[destOffset + _lo + 2] = java.lang.Math.fma(_eright3, _self23, java.lang.Math.fma(_eright2, _self22, java.lang.Math.fma(_eright0, _self20, _eright1 * _self21)));
            dest[destOffset + _lo + 3] = _eright3;
        }
        return dest;
    }

    /** {@code mulMat4x4_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] mulMat4x4_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        for (int _l = 0; _l < 4; _l++) {
            int _lo = _l * 4;
            float _eright0 = right[rightOffset + _lo];
            float _eright1 = right[rightOffset + _lo + 1];
            float _eright2 = right[rightOffset + _lo + 2];
            float _eright3 = right[rightOffset + _lo + 3];
            dest[destOffset + _lo] = ((_eright3) * (_self03) + (((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01))))));
            dest[destOffset + _lo + 1] = ((_eright3) * (_self13) + (((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11))))));
            dest[destOffset + _lo + 2] = ((_eright3) * (_self23) + (((_eright2) * (_self22) + (((_eright0) * (_self20) + (_eright1 * _self21))))));
            dest[destOffset + _lo + 3] = _eright3;
        }
        return dest;
    }

    public static float[] preMul_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_scalar_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eother0 = other[otherOffset + _lo];
            float _eother1 = other[otherOffset + _lo + 1];
            float _eother2 = other[otherOffset + _lo + 2];
            float _eother3 = other[otherOffset + _lo + 3];
            dest[destOffset + _lo] = java.lang.Math.fma(_eother2, _self20, java.lang.Math.fma(_eother0, _self00, _eother1 * _self10));
            dest[destOffset + _lo + 1] = java.lang.Math.fma(_eother2, _self21, java.lang.Math.fma(_eother0, _self01, _eother1 * _self11));
            dest[destOffset + _lo + 2] = java.lang.Math.fma(_eother2, _self22, java.lang.Math.fma(_eother0, _self02, _eother1 * _self12));
            dest[destOffset + _lo + 3] = java.lang.Math.fma(_eother0, _self03, java.lang.Math.fma(_eother1, _self13, java.lang.Math.fma(_eother2, _self23, _eother3)));
        }
        return dest;
    }

    /** {@code preMul_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preMul_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eother0 = other[otherOffset + _lo];
            float _eother1 = other[otherOffset + _lo + 1];
            float _eother2 = other[otherOffset + _lo + 2];
            float _eother3 = other[otherOffset + _lo + 3];
            dest[destOffset + _lo] = ((_eother2) * (_self20) + (((_eother0) * (_self00) + (_eother1 * _self10))));
            dest[destOffset + _lo + 1] = ((_eother2) * (_self21) + (((_eother0) * (_self01) + (_eother1 * _self11))));
            dest[destOffset + _lo + 2] = ((_eother2) * (_self22) + (((_eother0) * (_self02) + (_eother1 * _self12))));
            dest[destOffset + _lo + 3] = ((_eother0) * (_self03) + (((_eother1) * (_self13) + (((_eother2) * (_self23) + (_eother3))))));
        }
        return dest;
    }

    public static float[] preMulMat2x2_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x2_scalar_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x2_scalar_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMulMat2x2_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preMulMat2x2_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
        dest[destOffset] = java.lang.Math.fma(_other00, _self00, _other01 * _self10);
        dest[destOffset + 1] = java.lang.Math.fma(_other00, _self01, _other01 * _self11);
        dest[destOffset + 2] = java.lang.Math.fma(_other00, _self02, _other01 * _self12);
        dest[destOffset + 3] = java.lang.Math.fma(_other00, _self03, _other01 * _self13);
        dest[destOffset + 4] = java.lang.Math.fma(_other10, _self00, _other11 * _self10);
        dest[destOffset + 5] = java.lang.Math.fma(_other10, _self01, _other11 * _self11);
        dest[destOffset + 6] = java.lang.Math.fma(_other10, _self02, _other11 * _self12);
        dest[destOffset + 7] = java.lang.Math.fma(_other10, _self03, _other11 * _self13);
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /** {@code preMulMat2x2_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preMulMat2x2_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
        dest[destOffset] = ((_other00) * (_self00) + (_other01 * _self10));
        dest[destOffset + 1] = ((_other00) * (_self01) + (_other01 * _self11));
        dest[destOffset + 2] = ((_other00) * (_self02) + (_other01 * _self12));
        dest[destOffset + 3] = ((_other00) * (_self03) + (_other01 * _self13));
        dest[destOffset + 4] = ((_other10) * (_self00) + (_other11 * _self10));
        dest[destOffset + 5] = ((_other10) * (_self01) + (_other11 * _self11));
        dest[destOffset + 6] = ((_other10) * (_self02) + (_other11 * _self12));
        dest[destOffset + 7] = ((_other10) * (_self03) + (_other11 * _self13));
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] preMulMat2x3_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x3_scalar_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x3_scalar_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMulMat2x3_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preMulMat2x3_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
        float _other02 = other[otherOffset + 4];
        float _other12 = other[otherOffset + 5];
        dest[destOffset] = java.lang.Math.fma(_other00, _self00, _other01 * _self10);
        dest[destOffset + 1] = java.lang.Math.fma(_other00, _self01, _other01 * _self11);
        dest[destOffset + 2] = java.lang.Math.fma(_other00, _self02, _other01 * _self12);
        dest[destOffset + 3] = java.lang.Math.fma(_other00, _self03, java.lang.Math.fma(_other01, _self13, _other02));
        dest[destOffset + 4] = java.lang.Math.fma(_other10, _self00, _other11 * _self10);
        dest[destOffset + 5] = java.lang.Math.fma(_other10, _self01, _other11 * _self11);
        dest[destOffset + 6] = java.lang.Math.fma(_other10, _self02, _other11 * _self12);
        return preMulMat2x3_scalar_fma_sd4c6b4d1_1(dest, destOffset, _self03, _self13, _self20, _self21, _self22, _self23, _other10, _other11, _other12);
    }

    /** Piece 2 of {@code preMulMat2x3_scalar_fma}, split to fit the inline budget; reached only through it. */
    private static float[] preMulMat2x3_scalar_fma_sd4c6b4d1_1(float[] dest, int destOffset, float _self03, float _self13, float _self20, float _self21, float _self22, float _self23, float _other10, float _other11, float _other12) {
        dest[destOffset + 7] = java.lang.Math.fma(_other10, _self03, java.lang.Math.fma(_other11, _self13, _other12));
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /** {@code preMulMat2x3_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preMulMat2x3_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
        float _other02 = other[otherOffset + 4];
        float _other12 = other[otherOffset + 5];
        dest[destOffset] = ((_other00) * (_self00) + (_other01 * _self10));
        dest[destOffset + 1] = ((_other00) * (_self01) + (_other01 * _self11));
        dest[destOffset + 2] = ((_other00) * (_self02) + (_other01 * _self12));
        dest[destOffset + 3] = ((_other00) * (_self03) + (((_other01) * (_self13) + (_other02))));
        dest[destOffset + 4] = ((_other10) * (_self00) + (_other11 * _self10));
        dest[destOffset + 5] = ((_other10) * (_self01) + (_other11 * _self11));
        dest[destOffset + 6] = ((_other10) * (_self02) + (_other11 * _self12));
        dest[destOffset + 7] = ((_other10) * (_self03) + (((_other11) * (_self13) + (_other12))));
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] preMulMat3x3_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat3x3_scalar_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_scalar_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMulMat3x3_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preMulMat3x3_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other20 = other[otherOffset + 2];
        float _other01 = other[otherOffset + 3];
        float _other11 = other[otherOffset + 4];
        float _other21 = other[otherOffset + 5];
        float _other02 = other[otherOffset + 6];
        float _other12 = other[otherOffset + 7];
        float _other22 = other[otherOffset + 8];
        dest[destOffset] = java.lang.Math.fma(_other02, _self20, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest[destOffset + 1] = java.lang.Math.fma(_other02, _self21, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest[destOffset + 2] = java.lang.Math.fma(_other02, _self22, java.lang.Math.fma(_other00, _self02, _other01 * _self12));
        dest[destOffset + 3] = java.lang.Math.fma(_other02, _self23, java.lang.Math.fma(_other00, _self03, _other01 * _self13));
        return preMulMat3x3_scalar_s7dc065c3_1_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other10, _other20, _other11, _other21, _other12, _other22);
    }

    /** {@code preMulMat3x3_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preMulMat3x3_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other20 = other[otherOffset + 2];
        float _other01 = other[otherOffset + 3];
        float _other11 = other[otherOffset + 4];
        float _other21 = other[otherOffset + 5];
        float _other02 = other[otherOffset + 6];
        float _other12 = other[otherOffset + 7];
        float _other22 = other[otherOffset + 8];
        dest[destOffset] = ((_other02) * (_self20) + (((_other00) * (_self00) + (_other01 * _self10))));
        dest[destOffset + 1] = ((_other02) * (_self21) + (((_other00) * (_self01) + (_other01 * _self11))));
        dest[destOffset + 2] = ((_other02) * (_self22) + (((_other00) * (_self02) + (_other01 * _self12))));
        dest[destOffset + 3] = ((_other02) * (_self23) + (((_other00) * (_self03) + (_other01 * _self13))));
        return preMulMat3x3_scalar_s7dc065c3_1_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other10, _other20, _other11, _other21, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preMulMat3x3_scalar_s7dc065c3_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other10, float _other20, float _other11, float _other21, float _other12, float _other22) {
        dest[destOffset + 4] = java.lang.Math.fma(_other12, _self20, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest[destOffset + 5] = java.lang.Math.fma(_other12, _self21, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        dest[destOffset + 6] = java.lang.Math.fma(_other12, _self22, java.lang.Math.fma(_other10, _self02, _other11 * _self12));
        dest[destOffset + 7] = java.lang.Math.fma(_other12, _self23, java.lang.Math.fma(_other10, _self03, _other11 * _self13));
        dest[destOffset + 8] = java.lang.Math.fma(_other22, _self20, java.lang.Math.fma(_other20, _self00, _other21 * _self10));
        dest[destOffset + 9] = java.lang.Math.fma(_other22, _self21, java.lang.Math.fma(_other20, _self01, _other21 * _self11));
        dest[destOffset + 10] = java.lang.Math.fma(_other22, _self22, java.lang.Math.fma(_other20, _self02, _other21 * _self12));
        dest[destOffset + 11] = java.lang.Math.fma(_other22, _self23, java.lang.Math.fma(_other20, _self03, _other21 * _self13));
        return dest;
    }

    /** Piece 2 of {@code preMulMat3x3_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preMulMat3x3_scalar_s7dc065c3_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other10, float _other20, float _other11, float _other21, float _other12, float _other22) {
        dest[destOffset + 4] = ((_other12) * (_self20) + (((_other10) * (_self00) + (_other11 * _self10))));
        dest[destOffset + 5] = ((_other12) * (_self21) + (((_other10) * (_self01) + (_other11 * _self11))));
        dest[destOffset + 6] = ((_other12) * (_self22) + (((_other10) * (_self02) + (_other11 * _self12))));
        dest[destOffset + 7] = ((_other12) * (_self23) + (((_other10) * (_self03) + (_other11 * _self13))));
        dest[destOffset + 8] = ((_other22) * (_self20) + (((_other20) * (_self00) + (_other21 * _self10))));
        dest[destOffset + 9] = ((_other22) * (_self21) + (((_other20) * (_self01) + (_other21 * _self11))));
        dest[destOffset + 10] = ((_other22) * (_self22) + (((_other20) * (_self02) + (_other21 * _self12))));
        dest[destOffset + 11] = ((_other22) * (_self23) + (((_other20) * (_self03) + (_other21 * _self13))));
        return dest;
    }

    public static float[] preMulMat4x4_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat4x4_scalar_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat4x4_scalar_sc85c9d6b_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], other[otherOffset], other[otherOffset + 1], other[otherOffset + 2], other[otherOffset + 3], other[otherOffset + 4], other[otherOffset + 5], other[otherOffset + 6], other[otherOffset + 7], other[otherOffset + 8], other[otherOffset + 9], other[otherOffset + 10], other[otherOffset + 11], other[otherOffset + 12], other[otherOffset + 13], other[otherOffset + 14], other[otherOffset + 15]);
    }

    /** {@code preMulMat4x4_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preMulMat4x4_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        return preMulMat4x4_scalar_sc85c9d6b_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], other[otherOffset], other[otherOffset + 1], other[otherOffset + 2], other[otherOffset + 3], other[otherOffset + 4], other[otherOffset + 5], other[otherOffset + 6], other[otherOffset + 7], other[otherOffset + 8], other[otherOffset + 9], other[otherOffset + 10], other[otherOffset + 11], other[otherOffset + 12], other[otherOffset + 13], other[otherOffset + 14], other[otherOffset + 15]);
    }

    /** Piece 2 of {@code preMulMat4x4_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preMulMat4x4_scalar_sc85c9d6b_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other00, float _other10, float _other20, float _other30, float _other01, float _other11, float _other21, float _other31, float _other02, float _other12, float _other22, float _other32, float _other03, float _other13, float _other23, float _other33) {
        dest[destOffset] = java.lang.Math.fma(_other02, _self20, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest[destOffset + 1] = java.lang.Math.fma(_other12, _self20, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest[destOffset + 2] = java.lang.Math.fma(_other22, _self20, java.lang.Math.fma(_other20, _self00, _other21 * _self10));
        dest[destOffset + 3] = java.lang.Math.fma(_other32, _self20, java.lang.Math.fma(_other30, _self00, _other31 * _self10));
        dest[destOffset + 4] = java.lang.Math.fma(_other02, _self21, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest[destOffset + 5] = java.lang.Math.fma(_other12, _self21, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        dest[destOffset + 6] = java.lang.Math.fma(_other22, _self21, java.lang.Math.fma(_other20, _self01, _other21 * _self11));
        dest[destOffset + 7] = java.lang.Math.fma(_other32, _self21, java.lang.Math.fma(_other30, _self01, _other31 * _self11));
        dest[destOffset + 8] = java.lang.Math.fma(_other02, _self22, java.lang.Math.fma(_other00, _self02, _other01 * _self12));
        dest[destOffset + 9] = java.lang.Math.fma(_other12, _self22, java.lang.Math.fma(_other10, _self02, _other11 * _self12));
        return preMulMat4x4_scalar_sc85c9d6b_2_fma(dest, destOffset, _self02, _self03, _self12, _self13, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32, _other03, _other13, _other23, _other33);
    }

    /** Piece 2 of {@code preMulMat4x4_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preMulMat4x4_scalar_sc85c9d6b_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other00, float _other10, float _other20, float _other30, float _other01, float _other11, float _other21, float _other31, float _other02, float _other12, float _other22, float _other32, float _other03, float _other13, float _other23, float _other33) {
        dest[destOffset] = ((_other02) * (_self20) + (((_other00) * (_self00) + (_other01 * _self10))));
        dest[destOffset + 1] = ((_other12) * (_self20) + (((_other10) * (_self00) + (_other11 * _self10))));
        dest[destOffset + 2] = ((_other22) * (_self20) + (((_other20) * (_self00) + (_other21 * _self10))));
        dest[destOffset + 3] = ((_other32) * (_self20) + (((_other30) * (_self00) + (_other31 * _self10))));
        dest[destOffset + 4] = ((_other02) * (_self21) + (((_other00) * (_self01) + (_other01 * _self11))));
        dest[destOffset + 5] = ((_other12) * (_self21) + (((_other10) * (_self01) + (_other11 * _self11))));
        dest[destOffset + 6] = ((_other22) * (_self21) + (((_other20) * (_self01) + (_other21 * _self11))));
        dest[destOffset + 7] = ((_other32) * (_self21) + (((_other30) * (_self01) + (_other31 * _self11))));
        dest[destOffset + 8] = ((_other02) * (_self22) + (((_other00) * (_self02) + (_other01 * _self12))));
        dest[destOffset + 9] = ((_other12) * (_self22) + (((_other10) * (_self02) + (_other11 * _self12))));
        return preMulMat4x4_scalar_sc85c9d6b_2_mulAdd(dest, destOffset, _self02, _self03, _self12, _self13, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32, _other03, _other13, _other23, _other33);
    }

    /** Piece 3 of {@code preMulMat4x4_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preMulMat4x4_scalar_sc85c9d6b_2_fma(float[] dest, int destOffset, float _self02, float _self03, float _self12, float _self13, float _self22, float _self23, float _other00, float _other10, float _other20, float _other30, float _other01, float _other11, float _other21, float _other31, float _other02, float _other12, float _other22, float _other32, float _other03, float _other13, float _other23, float _other33) {
        dest[destOffset + 10] = java.lang.Math.fma(_other22, _self22, java.lang.Math.fma(_other20, _self02, _other21 * _self12));
        dest[destOffset + 11] = java.lang.Math.fma(_other32, _self22, java.lang.Math.fma(_other30, _self02, _other31 * _self12));
        dest[destOffset + 12] = java.lang.Math.fma(_other00, _self03, java.lang.Math.fma(_other01, _self13, java.lang.Math.fma(_other02, _self23, _other03)));
        dest[destOffset + 13] = java.lang.Math.fma(_other10, _self03, java.lang.Math.fma(_other11, _self13, java.lang.Math.fma(_other12, _self23, _other13)));
        dest[destOffset + 14] = java.lang.Math.fma(_other20, _self03, java.lang.Math.fma(_other21, _self13, java.lang.Math.fma(_other22, _self23, _other23)));
        dest[destOffset + 15] = java.lang.Math.fma(_other30, _self03, java.lang.Math.fma(_other31, _self13, java.lang.Math.fma(_other32, _self23, _other33)));
        return dest;
    }

    /** Piece 3 of {@code preMulMat4x4_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preMulMat4x4_scalar_sc85c9d6b_2_mulAdd(float[] dest, int destOffset, float _self02, float _self03, float _self12, float _self13, float _self22, float _self23, float _other00, float _other10, float _other20, float _other30, float _other01, float _other11, float _other21, float _other31, float _other02, float _other12, float _other22, float _other32, float _other03, float _other13, float _other23, float _other33) {
        dest[destOffset + 10] = ((_other22) * (_self22) + (((_other20) * (_self02) + (_other21 * _self12))));
        dest[destOffset + 11] = ((_other32) * (_self22) + (((_other30) * (_self02) + (_other31 * _self12))));
        dest[destOffset + 12] = ((_other00) * (_self03) + (((_other01) * (_self13) + (((_other02) * (_self23) + (_other03))))));
        dest[destOffset + 13] = ((_other10) * (_self03) + (((_other11) * (_self13) + (((_other12) * (_self23) + (_other13))))));
        dest[destOffset + 14] = ((_other20) * (_self03) + (((_other21) * (_self13) + (((_other22) * (_self23) + (_other23))))));
        dest[destOffset + 15] = ((_other30) * (_self03) + (((_other31) * (_self13) + (((_other32) * (_self23) + (_other33))))));
        return dest;
    }

    public static float[] addScaled_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 12; _i++) {
                dest[destOffset + _i] = java.lang.Math.fma(weight, other[otherOffset + _i], src[srcOffset + _i]);
            }
            return dest;
        } else {
            for (int _i = 0; _i < 12; _i++) {
                dest[destOffset + _i] = ((weight) * (other[otherOffset + _i]) + (src[srcOffset + _i]));
            }
            return dest;
        }
    }

    public static float[] composeTRSMul_scalar(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return composeTRSMul_scalar_mulAdd(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        float _t0 = scaleZ + scaleZ;
        float _t1 = scaleX + scaleX;
        float _t2 = scaleY + scaleY;
        float _t3 = rotationY * rotationW;
        float _t4 = rotationZ * rotationZ;
        float _t5 = rotationZ * rotationW;
        return composeTRSMul_scalar_s7c3dd3e8_1_fma(dest, destOffset, translationX, translationY, translationZ, m[mOffset], m[mOffset + 1], m[mOffset + 2], m[mOffset + 3], m[mOffset + 4], m[mOffset + 5], m[mOffset + 6], m[mOffset + 7], m[mOffset + 8], m[mOffset + 9], m[mOffset + 10], m[mOffset + 11], java.lang.Math.fma(rotationX, rotationZ, _t3) * _t0, java.lang.Math.fma(rotationX, rotationY, _t5) * _t1, java.lang.Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, java.lang.Math.fma(rotationX, rotationY, -_t5) * _t2, java.lang.Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, java.lang.Math.fma(rotationX, rotationZ, -_t3) * _t1, java.lang.Math.fma(-java.lang.Math.fma(rotationY, rotationY, _t4), _t1, scaleX), java.lang.Math.fma(-java.lang.Math.fma(rotationX, rotationX, _t4), _t2, scaleY), java.lang.Math.fma(-java.lang.Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
    }

    /** {@code composeTRSMul_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] composeTRSMul_scalar_mulAdd(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _t0 = scaleZ + scaleZ;
        float _t1 = scaleX + scaleX;
        float _t2 = scaleY + scaleY;
        float _t3 = rotationY * rotationW;
        float _t4 = rotationZ * rotationZ;
        float _t5 = rotationZ * rotationW;
        return composeTRSMul_scalar_s7c3dd3e8_1_mulAdd(dest, destOffset, translationX, translationY, translationZ, m[mOffset], m[mOffset + 1], m[mOffset + 2], m[mOffset + 3], m[mOffset + 4], m[mOffset + 5], m[mOffset + 6], m[mOffset + 7], m[mOffset + 8], m[mOffset + 9], m[mOffset + 10], m[mOffset + 11], ((rotationX) * (rotationZ) + (_t3)) * _t0, ((rotationX) * (rotationY) + (_t5)) * _t1, ((rotationX) * (rotationW) + (rotationY * rotationZ)) * _t2, ((rotationX) * (rotationY) - (_t5)) * _t2, ((rotationY) * (rotationZ) - (rotationX * rotationW)) * _t0, ((rotationX) * (rotationZ) - (_t3)) * _t1, ((scaleX) - ((rotationY) * (rotationY) + (_t4)) * (_t1)), ((scaleY) - ((rotationX) * (rotationX) + (_t4)) * (_t2)), ((scaleZ) - ((rotationX) * (rotationX) + (rotationY * rotationY)) * (_t0)));
    }

    /** Piece 2 of {@code composeTRSMul_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] composeTRSMul_scalar_s7c3dd3e8_1_fma(float[] dest, int destOffset, float translationX, float translationY, float translationZ, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32) {
        dest[destOffset] = java.lang.Math.fma(_m20, _t24, java.lang.Math.fma(_m00, _t30, _m10 * _t27));
        dest[destOffset + 1] = java.lang.Math.fma(_m21, _t24, java.lang.Math.fma(_m01, _t30, _m11 * _t27));
        dest[destOffset + 2] = java.lang.Math.fma(_m22, _t24, java.lang.Math.fma(_m02, _t30, _m12 * _t27));
        dest[destOffset + 3] = java.lang.Math.fma(_m03, _t30, java.lang.Math.fma(_m13, _t27, java.lang.Math.fma(_m23, _t24, translationX)));
        dest[destOffset + 4] = java.lang.Math.fma(_m20, _t28, java.lang.Math.fma(_m00, _t25, _m10 * _t31));
        dest[destOffset + 5] = java.lang.Math.fma(_m21, _t28, java.lang.Math.fma(_m01, _t25, _m11 * _t31));
        dest[destOffset + 6] = java.lang.Math.fma(_m22, _t28, java.lang.Math.fma(_m02, _t25, _m12 * _t31));
        dest[destOffset + 7] = java.lang.Math.fma(_m03, _t25, java.lang.Math.fma(_m13, _t31, java.lang.Math.fma(_m23, _t28, translationY)));
        dest[destOffset + 8] = java.lang.Math.fma(_m20, _t32, java.lang.Math.fma(_m00, _t29, _m10 * _t26));
        dest[destOffset + 9] = java.lang.Math.fma(_m21, _t32, java.lang.Math.fma(_m01, _t29, _m11 * _t26));
        dest[destOffset + 10] = java.lang.Math.fma(_m22, _t32, java.lang.Math.fma(_m02, _t29, _m12 * _t26));
        dest[destOffset + 11] = java.lang.Math.fma(_m03, _t29, java.lang.Math.fma(_m13, _t26, java.lang.Math.fma(_m23, _t32, translationZ)));
        return dest;
    }

    /** Piece 2 of {@code composeTRSMul_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] composeTRSMul_scalar_s7c3dd3e8_1_mulAdd(float[] dest, int destOffset, float translationX, float translationY, float translationZ, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32) {
        dest[destOffset] = ((_m20) * (_t24) + (((_m00) * (_t30) + (_m10 * _t27))));
        dest[destOffset + 1] = ((_m21) * (_t24) + (((_m01) * (_t30) + (_m11 * _t27))));
        dest[destOffset + 2] = ((_m22) * (_t24) + (((_m02) * (_t30) + (_m12 * _t27))));
        dest[destOffset + 3] = ((_m03) * (_t30) + (((_m13) * (_t27) + (((_m23) * (_t24) + (translationX))))));
        dest[destOffset + 4] = ((_m20) * (_t28) + (((_m00) * (_t25) + (_m10 * _t31))));
        dest[destOffset + 5] = ((_m21) * (_t28) + (((_m01) * (_t25) + (_m11 * _t31))));
        dest[destOffset + 6] = ((_m22) * (_t28) + (((_m02) * (_t25) + (_m12 * _t31))));
        dest[destOffset + 7] = ((_m03) * (_t25) + (((_m13) * (_t31) + (((_m23) * (_t28) + (translationY))))));
        dest[destOffset + 8] = ((_m20) * (_t32) + (((_m00) * (_t29) + (_m10 * _t26))));
        dest[destOffset + 9] = ((_m21) * (_t32) + (((_m01) * (_t29) + (_m11 * _t26))));
        dest[destOffset + 10] = ((_m22) * (_t32) + (((_m02) * (_t29) + (_m12 * _t26))));
        dest[destOffset + 11] = ((_m03) * (_t29) + (((_m13) * (_t26) + (((_m23) * (_t32) + (translationZ))))));
        return dest;
    }

    public static float[] composeTRSMul_scalar(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return composeTRSMul_scalar_fma(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return composeTRSMul_scalar_mulAdd(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
    }

    /** {@code composeTRSMul_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] composeTRSMul_scalar_fma(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        float _rotationx = rotation[rotationOffset];
        float _rotationy = rotation[rotationOffset + 1];
        float _rotationz = rotation[rotationOffset + 2];
        float _rotationw = rotation[rotationOffset + 3];
        float _scalex = scale[scaleOffset];
        float _scaley = scale[scaleOffset + 1];
        float _scalez = scale[scaleOffset + 2];
        float _t0 = _scalez + _scalez;
        float _t1 = _scalex + _scalex;
        float _t2 = _scaley + _scaley;
        float _t3 = _rotationy * _rotationw;
        float _t5 = _rotationz * _rotationw;
        return composeTRSMul_scalar_s6ef4f337_1_fma(dest, destOffset, translation[translationOffset], translation[translationOffset + 1], translation[translationOffset + 2], _rotationx, _rotationy, _scalex, _scaley, _scalez, m[mOffset], m[mOffset + 1], m[mOffset + 2], m[mOffset + 3], m[mOffset + 4], m[mOffset + 5], m[mOffset + 6], m[mOffset + 7], m[mOffset + 8], m[mOffset + 9], m[mOffset + 10], m[mOffset + 11], _t0, _t1, _t2, _rotationz * _rotationz, java.lang.Math.fma(_rotationx, _rotationz, _t3) * _t0, java.lang.Math.fma(_rotationx, _rotationy, _t5) * _t1, java.lang.Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, java.lang.Math.fma(_rotationx, _rotationy, -_t5) * _t2, java.lang.Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, java.lang.Math.fma(_rotationx, _rotationz, -_t3) * _t1);
    }

    /** {@code composeTRSMul_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] composeTRSMul_scalar_mulAdd(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        float _rotationx = rotation[rotationOffset];
        float _rotationy = rotation[rotationOffset + 1];
        float _rotationz = rotation[rotationOffset + 2];
        float _rotationw = rotation[rotationOffset + 3];
        float _scalex = scale[scaleOffset];
        float _scaley = scale[scaleOffset + 1];
        float _scalez = scale[scaleOffset + 2];
        float _t0 = _scalez + _scalez;
        float _t1 = _scalex + _scalex;
        float _t2 = _scaley + _scaley;
        float _t3 = _rotationy * _rotationw;
        float _t5 = _rotationz * _rotationw;
        return composeTRSMul_scalar_s6ef4f337_1_mulAdd(dest, destOffset, translation[translationOffset], translation[translationOffset + 1], translation[translationOffset + 2], _rotationx, _rotationy, _scalex, _scaley, _scalez, m[mOffset], m[mOffset + 1], m[mOffset + 2], m[mOffset + 3], m[mOffset + 4], m[mOffset + 5], m[mOffset + 6], m[mOffset + 7], m[mOffset + 8], m[mOffset + 9], m[mOffset + 10], m[mOffset + 11], _t0, _t1, _t2, _rotationz * _rotationz, ((_rotationx) * (_rotationz) + (_t3)) * _t0, ((_rotationx) * (_rotationy) + (_t5)) * _t1, ((_rotationx) * (_rotationw) + (_rotationy * _rotationz)) * _t2, ((_rotationx) * (_rotationy) - (_t5)) * _t2, ((_rotationy) * (_rotationz) - (_rotationx * _rotationw)) * _t0, ((_rotationx) * (_rotationz) - (_t3)) * _t1);
    }

    /** Piece 2 of {@code composeTRSMul_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] composeTRSMul_scalar_s6ef4f337_1_fma(float[] dest, int destOffset, float _translationx, float _translationy, float _translationz, float _rotationx, float _rotationy, float _scalex, float _scaley, float _scalez, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t0, float _t1, float _t2, float _t4, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        float _t30 = java.lang.Math.fma(-java.lang.Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex);
        float _t31 = java.lang.Math.fma(-java.lang.Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley);
        dest[destOffset] = java.lang.Math.fma(_m20, _t24, java.lang.Math.fma(_m00, _t30, _m10 * _t27));
        dest[destOffset + 1] = java.lang.Math.fma(_m21, _t24, java.lang.Math.fma(_m01, _t30, _m11 * _t27));
        dest[destOffset + 2] = java.lang.Math.fma(_m22, _t24, java.lang.Math.fma(_m02, _t30, _m12 * _t27));
        dest[destOffset + 3] = java.lang.Math.fma(_m03, _t30, java.lang.Math.fma(_m13, _t27, java.lang.Math.fma(_m23, _t24, _translationx)));
        dest[destOffset + 4] = java.lang.Math.fma(_m20, _t28, java.lang.Math.fma(_m00, _t25, _m10 * _t31));
        dest[destOffset + 5] = java.lang.Math.fma(_m21, _t28, java.lang.Math.fma(_m01, _t25, _m11 * _t31));
        dest[destOffset + 6] = java.lang.Math.fma(_m22, _t28, java.lang.Math.fma(_m02, _t25, _m12 * _t31));
        dest[destOffset + 7] = java.lang.Math.fma(_m03, _t25, java.lang.Math.fma(_m13, _t31, java.lang.Math.fma(_m23, _t28, _translationy)));
        return composeTRSMul_scalar_s6ef4f337_2_fma(dest, destOffset, _translationz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t26, _t29, java.lang.Math.fma(-java.lang.Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez));
    }

    /** Piece 2 of {@code composeTRSMul_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] composeTRSMul_scalar_s6ef4f337_1_mulAdd(float[] dest, int destOffset, float _translationx, float _translationy, float _translationz, float _rotationx, float _rotationy, float _scalex, float _scaley, float _scalez, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t0, float _t1, float _t2, float _t4, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        float _t30 = ((_scalex) - ((_rotationy) * (_rotationy) + (_t4)) * (_t1));
        float _t31 = ((_scaley) - ((_rotationx) * (_rotationx) + (_t4)) * (_t2));
        dest[destOffset] = ((_m20) * (_t24) + (((_m00) * (_t30) + (_m10 * _t27))));
        dest[destOffset + 1] = ((_m21) * (_t24) + (((_m01) * (_t30) + (_m11 * _t27))));
        dest[destOffset + 2] = ((_m22) * (_t24) + (((_m02) * (_t30) + (_m12 * _t27))));
        dest[destOffset + 3] = ((_m03) * (_t30) + (((_m13) * (_t27) + (((_m23) * (_t24) + (_translationx))))));
        dest[destOffset + 4] = ((_m20) * (_t28) + (((_m00) * (_t25) + (_m10 * _t31))));
        dest[destOffset + 5] = ((_m21) * (_t28) + (((_m01) * (_t25) + (_m11 * _t31))));
        dest[destOffset + 6] = ((_m22) * (_t28) + (((_m02) * (_t25) + (_m12 * _t31))));
        dest[destOffset + 7] = ((_m03) * (_t25) + (((_m13) * (_t31) + (((_m23) * (_t28) + (_translationy))))));
        return composeTRSMul_scalar_s6ef4f337_2_mulAdd(dest, destOffset, _translationz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t26, _t29, ((_scalez) - ((_rotationx) * (_rotationx) + (_rotationy * _rotationy)) * (_t0)));
    }

    /** Piece 3 of {@code composeTRSMul_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] composeTRSMul_scalar_s6ef4f337_2_fma(float[] dest, int destOffset, float _translationz, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t26, float _t29, float _t32) {
        dest[destOffset + 8] = java.lang.Math.fma(_m20, _t32, java.lang.Math.fma(_m00, _t29, _m10 * _t26));
        dest[destOffset + 9] = java.lang.Math.fma(_m21, _t32, java.lang.Math.fma(_m01, _t29, _m11 * _t26));
        dest[destOffset + 10] = java.lang.Math.fma(_m22, _t32, java.lang.Math.fma(_m02, _t29, _m12 * _t26));
        dest[destOffset + 11] = java.lang.Math.fma(_m03, _t29, java.lang.Math.fma(_m13, _t26, java.lang.Math.fma(_m23, _t32, _translationz)));
        return dest;
    }

    /** Piece 3 of {@code composeTRSMul_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] composeTRSMul_scalar_s6ef4f337_2_mulAdd(float[] dest, int destOffset, float _translationz, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t26, float _t29, float _t32) {
        dest[destOffset + 8] = ((_m20) * (_t32) + (((_m00) * (_t29) + (_m10 * _t26))));
        dest[destOffset + 9] = ((_m21) * (_t32) + (((_m01) * (_t29) + (_m11 * _t26))));
        dest[destOffset + 10] = ((_m22) * (_t32) + (((_m02) * (_t29) + (_m12 * _t26))));
        dest[destOffset + 11] = ((_m03) * (_t29) + (((_m13) * (_t26) + (((_m23) * (_t32) + (_translationz))))));
        return dest;
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return Float3x4OpsSimd.lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_scalar_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static float[] lookAlong_lh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_scalar_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_lh_scalar_s5b563cf9_1_fma(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** {@code lookAlong_lh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return lookAlong_lh_scalar_s5b563cf9_1_mulAdd(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_scalar_s5b563cf9_1_fma(float[] dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        float _t48 = java.lang.Math.fma(_t39, _t11, -(_t37 * _t13));
        dest[destOffset] = java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48));
        dest[destOffset + 7] = _self13;
        return lookAlong_lh_scalar_s5b563cf9_2_fma(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code lookAlong_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_scalar_s5b563cf9_1_mulAdd(float[] dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        float _t48 = ((_t39) * (_t11) - (_t37 * _t13));
        dest[destOffset] = ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46))));
        dest[destOffset + 1] = ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47))));
        dest[destOffset + 2] = ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46))));
        dest[destOffset + 5] = ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47))));
        dest[destOffset + 6] = ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48))));
        dest[destOffset + 7] = _self13;
        return lookAlong_lh_scalar_s5b563cf9_2_mulAdd(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /**
     * Piece 3 of {@code lookAlong_lh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_scalar_s5b563cf9_2_fma(float[] dest, int destOffset, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /**
     * Piece 3 of {@code lookAlong_lh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_scalar_s5b563cf9_2_mulAdd(float[] dest, int destOffset, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 8] = ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46))));
        dest[destOffset + 9] = ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47))));
        dest[destOffset + 10] = ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return Float3x4OpsSimd.lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_scalar_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static float[] lookAlong_rh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_scalar_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
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
        return lookAlong_rh_scalar_s184ef027_1_fma(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** {@code lookAlong_rh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
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
        return lookAlong_rh_scalar_s184ef027_1_mulAdd(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_scalar_s184ef027_1_fma(float[] dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        dest[destOffset] = java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49));
        dest[destOffset + 1] = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50));
        dest[destOffset + 2] = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49));
        dest[destOffset + 5] = java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50));
        dest[destOffset + 6] = java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51));
        dest[destOffset + 7] = _self13;
        return lookAlong_rh_scalar_s184ef027_2_fma(dest, destOffset, _self20, _self21, _self23, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /** Piece 2 of {@code lookAlong_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_scalar_s184ef027_1_mulAdd(float[] dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        dest[destOffset] = ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49))));
        dest[destOffset + 1] = ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50))));
        dest[destOffset + 2] = ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49))));
        dest[destOffset + 5] = ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50))));
        dest[destOffset + 6] = ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51))));
        dest[destOffset + 7] = _self13;
        return lookAlong_rh_scalar_s184ef027_2_mulAdd(dest, destOffset, _self20, _self21, _self23, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /**
     * Piece 3 of {@code lookAlong_rh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_scalar_s184ef027_2_fma(float[] dest, int destOffset, float _self20, float _self21, float _self23, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42, float _t49, float _t50, float _t51) {
        dest[destOffset + 8] = java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49));
        dest[destOffset + 9] = java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50));
        dest[destOffset + 10] = java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /**
     * Piece 3 of {@code lookAlong_rh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_scalar_s184ef027_2_mulAdd(float[] dest, int destOffset, float _self20, float _self21, float _self23, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42, float _t49, float _t50, float _t51) {
        dest[destOffset + 8] = ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49))));
        dest[destOffset + 9] = ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50))));
        dest[destOffset + 10] = ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
            return Float3x4OpsSimd.lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_scalar_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static float[] lookAlong_lh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_scalar_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        float _t18 = java.lang.Math.fma(_t17, _t11, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        return lookAlong_lh_scalar_s65cdbffd_1_fma(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t11, -(_t18 * _t12)));
    }

    /** {@code lookAlong_lh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        float _t18 = ((_t17) * (_t11) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t12) + (_upz));
        return lookAlong_lh_scalar_s65cdbffd_1_mulAdd(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t11)), ((_t19) * (_t12) - (_t20 * _t13)), ((_t20) * (_t11) - (_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_scalar_s65cdbffd_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        float _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        float _t48 = java.lang.Math.fma(_t39, _t11, -(_t37 * _t13));
        dest[destOffset] = java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47));
        return lookAlong_lh_scalar_s65cdbffd_2_fma(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code lookAlong_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_scalar_s65cdbffd_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        float _t48 = ((_t39) * (_t11) - (_t37 * _t13));
        dest[destOffset] = ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46))));
        dest[destOffset + 1] = ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47))));
        dest[destOffset + 2] = ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46))));
        dest[destOffset + 5] = ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47))));
        return lookAlong_lh_scalar_s65cdbffd_2_mulAdd(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /**
     * Piece 3 of {@code lookAlong_lh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_scalar_s65cdbffd_2_fma(float[] dest, int destOffset, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /**
     * Piece 3 of {@code lookAlong_lh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_scalar_s65cdbffd_2_mulAdd(float[] dest, int destOffset, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 6] = ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46))));
        dest[destOffset + 9] = ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47))));
        dest[destOffset + 10] = ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
            return Float3x4OpsSimd.lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_scalar_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static float[] lookAlong_rh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_scalar_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
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
        return lookAlong_rh_scalar_s45919c5b_1_fma(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t21, _t23, java.lang.Math.fma(_t21, _t14, -(_t22 * _t16)), java.lang.Math.fma(_t22, _t15, -(_t23 * _t14)));
    }

    /** {@code lookAlong_rh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
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
        return lookAlong_rh_scalar_s45919c5b_1_mulAdd(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t21, _t23, ((_t21) * (_t14) - (_t22 * _t16)), ((_t22) * (_t15) - (_t23 * _t14)));
    }

    /** Piece 2 of {@code lookAlong_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_scalar_s45919c5b_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
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
        dest[destOffset] = java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49));
        dest[destOffset + 1] = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50));
        dest[destOffset + 2] = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49));
        return lookAlong_rh_scalar_s45919c5b_2_fma(dest, destOffset, _self10, _self11, _self13, _self20, _self21, _self23, _t1, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /** Piece 2 of {@code lookAlong_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_scalar_s45919c5b_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
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
        dest[destOffset] = ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49))));
        dest[destOffset + 1] = ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50))));
        dest[destOffset + 2] = ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49))));
        return lookAlong_rh_scalar_s45919c5b_2_mulAdd(dest, destOffset, _self10, _self11, _self13, _self20, _self21, _self23, _t1, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /**
     * Piece 3 of {@code lookAlong_rh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_scalar_s45919c5b_2_fma(float[] dest, int destOffset, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42, float _t49, float _t50, float _t51) {
        dest[destOffset + 5] = java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50));
        dest[destOffset + 6] = java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49));
        dest[destOffset + 9] = java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50));
        dest[destOffset + 10] = java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /**
     * Piece 3 of {@code lookAlong_rh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_scalar_s45919c5b_2_mulAdd(float[] dest, int destOffset, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42, float _t49, float _t50, float _t51) {
        dest[destOffset + 5] = ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50))));
        dest[destOffset + 6] = ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49))));
        dest[destOffset + 9] = ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50))));
        dest[destOffset + 10] = ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            return Float3x4OpsSimd.lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_scalar_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static float[] lookAlong_lh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_scalar_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_lh_scalar_s28c02d7f_1_fma(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** {@code lookAlong_lh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return lookAlong_lh_scalar_s28c02d7f_1_mulAdd(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_scalar_s28c02d7f_1_fma(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        float _t48 = java.lang.Math.fma(_t39, _t11, -(_t37 * _t13));
        dest[destOffset] = java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48));
        dest[destOffset + 7] = _self13;
        return lookAlong_lh_scalar_s5b563cf9_2_fma(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code lookAlong_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_scalar_s28c02d7f_1_mulAdd(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        float _t48 = ((_t39) * (_t11) - (_t37 * _t13));
        dest[destOffset] = ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46))));
        dest[destOffset + 1] = ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47))));
        dest[destOffset + 2] = ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46))));
        dest[destOffset + 5] = ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47))));
        dest[destOffset + 6] = ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48))));
        dest[destOffset + 7] = _self13;
        return lookAlong_lh_scalar_s5b563cf9_2_mulAdd(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            return Float3x4OpsSimd.lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_scalar_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static float[] lookAlong_rh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_scalar_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
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
        return lookAlong_rh_scalar_s6d46febd_1_fma(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** {@code lookAlong_rh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
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
        return lookAlong_rh_scalar_s6d46febd_1_mulAdd(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_scalar_s6d46febd_1_fma(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        dest[destOffset] = java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49));
        dest[destOffset + 1] = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50));
        dest[destOffset + 2] = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49));
        dest[destOffset + 5] = java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50));
        dest[destOffset + 6] = java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51));
        dest[destOffset + 7] = _self13;
        return lookAlong_rh_scalar_s184ef027_2_fma(dest, destOffset, _self20, _self21, _self23, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /** Piece 2 of {@code lookAlong_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_scalar_s6d46febd_1_mulAdd(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        dest[destOffset] = ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49))));
        dest[destOffset + 1] = ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50))));
        dest[destOffset + 2] = ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49))));
        dest[destOffset + 5] = ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50))));
        dest[destOffset + 6] = ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51))));
        dest[destOffset + 7] = _self13;
        return lookAlong_rh_scalar_s184ef027_2_mulAdd(dest, destOffset, _self20, _self21, _self23, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
            return Float3x4OpsSimd.lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_scalar_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static float[] lookAlong_lh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_scalar_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        float _t18 = java.lang.Math.fma(_t17, _t11, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        return lookAlong_lh_scalar_se1401de3_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t11, -(_t18 * _t12)));
    }

    /** {@code lookAlong_lh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        float _t18 = ((_t17) * (_t11) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t12) + (_upz));
        return lookAlong_lh_scalar_se1401de3_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t11)), ((_t19) * (_t12) - (_t20 * _t13)), ((_t20) * (_t11) - (_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_scalar_se1401de3_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        float _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        float _t48 = java.lang.Math.fma(_t39, _t11, -(_t37 * _t13));
        dest[destOffset] = java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47));
        return lookAlong_lh_scalar_s65cdbffd_2_fma(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code lookAlong_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_scalar_se1401de3_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        float _t48 = ((_t39) * (_t11) - (_t37 * _t13));
        dest[destOffset] = ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46))));
        dest[destOffset + 1] = ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47))));
        dest[destOffset + 2] = ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46))));
        dest[destOffset + 5] = ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47))));
        return lookAlong_lh_scalar_s65cdbffd_2_mulAdd(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
            return Float3x4OpsSimd.lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_scalar_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static float[] lookAlong_rh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_scalar_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
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
        return lookAlong_rh_scalar_sd9390801_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t21, _t23, java.lang.Math.fma(_t21, _t14, -(_t22 * _t16)), java.lang.Math.fma(_t22, _t15, -(_t23 * _t14)));
    }

    /** {@code lookAlong_rh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
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
        return lookAlong_rh_scalar_sd9390801_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t21, _t23, ((_t21) * (_t14) - (_t22 * _t16)), ((_t22) * (_t15) - (_t23 * _t14)));
    }

    /** Piece 2 of {@code lookAlong_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_scalar_sd9390801_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
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
        dest[destOffset] = java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49));
        dest[destOffset + 1] = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50));
        dest[destOffset + 2] = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49));
        return lookAlong_rh_scalar_s45919c5b_2_fma(dest, destOffset, _self10, _self11, _self13, _self20, _self21, _self23, _t1, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /** Piece 2 of {@code lookAlong_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_scalar_sd9390801_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
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
        dest[destOffset] = ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49))));
        dest[destOffset + 1] = ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50))));
        dest[destOffset + 2] = ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49))));
        return lookAlong_rh_scalar_s45919c5b_2_mulAdd(dest, destOffset, _self10, _self11, _self13, _self20, _self21, _self23, _t1, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAt_lh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return Float3x4OpsSimd.lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_scalar_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static float[] lookAt_lh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_scalar_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    /** {@code lookAt_lh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t23 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        float _t24 = java.lang.Math.fma(_t23, _t14, upX);
        float _t25 = java.lang.Math.fma(_t23, _t16, upY);
        float _t26 = java.lang.Math.fma(_t23, _t15, upZ);
        return lookAt_lh_scalar_scc8c4645_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, java.lang.Math.fma(eyeZ, _t15, java.lang.Math.fma(eyeX, _t14, eyeY * _t16)), java.lang.Math.fma(_t24, _t16, -(_t25 * _t14)), java.lang.Math.fma(_t25, _t15, -(_t26 * _t16)), java.lang.Math.fma(_t26, _t14, -(_t24 * _t15)));
    }

    /** {@code lookAt_lh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t23 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        float _t24 = ((_t23) * (_t14) + (upX));
        float _t25 = ((_t23) * (_t16) + (upY));
        float _t26 = ((_t23) * (_t15) + (upZ));
        return lookAt_lh_scalar_scc8c4645_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, ((eyeZ) * (_t15) + (((eyeX) * (_t14) + (eyeY * _t16)))), ((_t24) * (_t16) - (_t25 * _t14)), ((_t25) * (_t15) - (_t26 * _t16)), ((_t26) * (_t14) - (_t24 * _t15)));
    }

    /** Piece 2 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_scalar_scc8c4645_1_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t33, float _t34, float _t35) {
        float _t38 = java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = java.lang.Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = java.lang.Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = java.lang.Math.fma(_t45, _t14, -(_t43 * _t16));
        dest[destOffset] = java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self00, _t43, _self01 * _t54));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t16, java.lang.Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t15, java.lang.Math.fma(_self00, _t44, _self01 * _t56));
        return lookAt_lh_scalar_scc8c4645_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(eyeZ, _t44, java.lang.Math.fma(eyeX, _t43, eyeY * _t45)), java.lang.Math.fma(eyeZ, _t56, java.lang.Math.fma(eyeX, _t54, eyeY * _t55)));
    }

    /** Piece 2 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_scalar_scc8c4645_1_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t33, float _t34, float _t35) {
        float _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = ((_t44) * (_t16) - (_t45 * _t15));
        float _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        float _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        dest[destOffset] = ((_self02) * (_t14) + (((_self00) * (_t43) + (_self01 * _t54))));
        dest[destOffset + 1] = ((_self02) * (_t16) + (((_self00) * (_t45) + (_self01 * _t55))));
        dest[destOffset + 2] = ((_self02) * (_t15) + (((_self00) * (_t44) + (_self01 * _t56))));
        return lookAt_lh_scalar_scc8c4645_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, ((eyeZ) * (_t44) + (((eyeX) * (_t43) + (eyeY * _t45)))), ((eyeZ) * (_t56) + (((eyeX) * (_t54) + (eyeY * _t55)))));
    }

    /**
     * Piece 3 of {@code lookAt_lh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_lh_scalar_scc8c4645_2_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
        dest[destOffset + 3] = java.lang.Math.fma(_self00, -_t58, java.lang.Math.fma(-_self01, _t60, java.lang.Math.fma(-_self02, _t22, _self03)));
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t14, java.lang.Math.fma(_self10, _t43, _self11 * _t54));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t16, java.lang.Math.fma(_self10, _t45, _self11 * _t55));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t15, java.lang.Math.fma(_self10, _t44, _self11 * _t56));
        dest[destOffset + 7] = java.lang.Math.fma(_self10, -_t58, java.lang.Math.fma(-_self11, _t60, java.lang.Math.fma(-_self12, _t22, _self13)));
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self20, _t43, _self21 * _t54));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_self20, _t45, _self21 * _t55));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t15, java.lang.Math.fma(_self20, _t44, _self21 * _t56));
        dest[destOffset + 11] = java.lang.Math.fma(_self20, -_t58, java.lang.Math.fma(-_self21, _t60, java.lang.Math.fma(-_self22, _t22, _self23)));
        return dest;
    }

    /**
     * Piece 3 of {@code lookAt_lh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_lh_scalar_scc8c4645_2_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
        dest[destOffset + 3] = ((((((_self03) - (_self02) * (_t22))) - (_self01) * (_t60))) - (_self00) * (_t58));
        dest[destOffset + 4] = ((_self12) * (_t14) + (((_self10) * (_t43) + (_self11 * _t54))));
        dest[destOffset + 5] = ((_self12) * (_t16) + (((_self10) * (_t45) + (_self11 * _t55))));
        dest[destOffset + 6] = ((_self12) * (_t15) + (((_self10) * (_t44) + (_self11 * _t56))));
        dest[destOffset + 7] = ((((((_self13) - (_self12) * (_t22))) - (_self11) * (_t60))) - (_self10) * (_t58));
        dest[destOffset + 8] = ((_self22) * (_t14) + (((_self20) * (_t43) + (_self21 * _t54))));
        dest[destOffset + 9] = ((_self22) * (_t16) + (((_self20) * (_t45) + (_self21 * _t55))));
        dest[destOffset + 10] = ((_self22) * (_t15) + (((_self20) * (_t44) + (_self21 * _t56))));
        dest[destOffset + 11] = ((((((_self23) - (_self22) * (_t22))) - (_self21) * (_t60))) - (_self20) * (_t58));
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return Float3x4OpsSimd.lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_scalar_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static float[] lookAt_rh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_scalar_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    /** {@code lookAt_rh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _t26 = -java.lang.Math.fma(upZ, _t18, java.lang.Math.fma(upX, _t17, upY * _t19));
        float _t27 = java.lang.Math.fma(_t26, _t19, upY);
        float _t28 = java.lang.Math.fma(_t26, _t17, upX);
        float _t29 = java.lang.Math.fma(_t26, _t18, upZ);
        return lookAt_rh_scalar_s15a48c73_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], -_self02, -_self12, -_self22, _t17, _t18, _t19, java.lang.Math.fma(eyeZ, _t18, java.lang.Math.fma(eyeX, _t17, eyeY * _t19)), _t27, _t29, java.lang.Math.fma(_t27, _t17, -(_t28 * _t19)), java.lang.Math.fma(_t28, _t18, -(_t29 * _t17)));
    }

    /** {@code lookAt_rh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = ((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _t26 = -((upZ) * (_t18) + (((upX) * (_t17) + (upY * _t19))));
        float _t27 = ((_t26) * (_t19) + (upY));
        float _t28 = ((_t26) * (_t17) + (upX));
        float _t29 = ((_t26) * (_t18) + (upZ));
        return lookAt_rh_scalar_s15a48c73_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], -_self02, -_self12, -_self22, _t17, _t18, _t19, ((eyeZ) * (_t18) + (((eyeX) * (_t17) + (eyeY * _t19)))), _t27, _t29, ((_t27) * (_t17) - (_t28 * _t19)), ((_t28) * (_t18) - (_t29 * _t17)));
    }

    /** Piece 2 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_scalar_s15a48c73_1_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t27, float _t29, float _t36, float _t37) {
        float _t38 = java.lang.Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = java.lang.Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = java.lang.Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = java.lang.Math.fma(_t46, _t19, -(_t47 * _t17));
        dest[destOffset] = java.lang.Math.fma(_t0, _t17, java.lang.Math.fma(_self00, _t46, _self01 * _t57));
        dest[destOffset + 1] = java.lang.Math.fma(_t0, _t19, java.lang.Math.fma(_self00, _t47, _self01 * _t58));
        return lookAt_rh_scalar_s15a48c73_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(eyeZ, _t48, java.lang.Math.fma(eyeX, _t46, eyeY * _t47)), java.lang.Math.fma(eyeZ, _t59, java.lang.Math.fma(eyeX, _t57, eyeY * _t58)));
    }

    /** Piece 2 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_scalar_s15a48c73_1_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t27, float _t29, float _t36, float _t37) {
        float _t38 = ((_t29) * (_t19) - (_t27 * _t18));
        float _t41 = ((_t36) * (_t36) + (((_t37) * (_t37) + (_t38 * _t38))));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = ((_t47) * (_t18) - (_t48 * _t19));
        float _t58 = ((_t48) * (_t17) - (_t46 * _t18));
        float _t59 = ((_t46) * (_t19) - (_t47 * _t17));
        dest[destOffset] = ((_t0) * (_t17) + (((_self00) * (_t46) + (_self01 * _t57))));
        dest[destOffset + 1] = ((_t0) * (_t19) + (((_self00) * (_t47) + (_self01 * _t58))));
        return lookAt_rh_scalar_s15a48c73_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, ((eyeZ) * (_t48) + (((eyeX) * (_t46) + (eyeY * _t47)))), ((eyeZ) * (_t59) + (((eyeX) * (_t57) + (eyeY * _t58)))));
    }

    /**
     * Piece 3 of {@code lookAt_rh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_rh_scalar_s15a48c73_2_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        dest[destOffset + 2] = java.lang.Math.fma(_t0, _t18, java.lang.Math.fma(_self00, _t48, _self01 * _t59));
        dest[destOffset + 3] = java.lang.Math.fma(_self00, -_t61, java.lang.Math.fma(-_self01, _t63, java.lang.Math.fma(_self02, _t25, _self03)));
        dest[destOffset + 4] = java.lang.Math.fma(_t1, _t17, java.lang.Math.fma(_self10, _t46, _self11 * _t57));
        dest[destOffset + 5] = java.lang.Math.fma(_t1, _t19, java.lang.Math.fma(_self10, _t47, _self11 * _t58));
        dest[destOffset + 6] = java.lang.Math.fma(_t1, _t18, java.lang.Math.fma(_self10, _t48, _self11 * _t59));
        dest[destOffset + 7] = java.lang.Math.fma(_self10, -_t61, java.lang.Math.fma(-_self11, _t63, java.lang.Math.fma(_self12, _t25, _self13)));
        dest[destOffset + 8] = java.lang.Math.fma(_t2, _t17, java.lang.Math.fma(_self20, _t46, _self21 * _t57));
        dest[destOffset + 9] = java.lang.Math.fma(_t2, _t19, java.lang.Math.fma(_self20, _t47, _self21 * _t58));
        dest[destOffset + 10] = java.lang.Math.fma(_t2, _t18, java.lang.Math.fma(_self20, _t48, _self21 * _t59));
        dest[destOffset + 11] = java.lang.Math.fma(_self20, -_t61, java.lang.Math.fma(-_self21, _t63, java.lang.Math.fma(_self22, _t25, _self23)));
        return dest;
    }

    /**
     * Piece 3 of {@code lookAt_rh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_rh_scalar_s15a48c73_2_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        dest[destOffset + 2] = ((_t0) * (_t18) + (((_self00) * (_t48) + (_self01 * _t59))));
        dest[destOffset + 3] = ((((((_self02) * (_t25) + (_self03))) - (_self01) * (_t63))) - (_self00) * (_t61));
        dest[destOffset + 4] = ((_t1) * (_t17) + (((_self10) * (_t46) + (_self11 * _t57))));
        dest[destOffset + 5] = ((_t1) * (_t19) + (((_self10) * (_t47) + (_self11 * _t58))));
        dest[destOffset + 6] = ((_t1) * (_t18) + (((_self10) * (_t48) + (_self11 * _t59))));
        dest[destOffset + 7] = ((((((_self12) * (_t25) + (_self13))) - (_self11) * (_t63))) - (_self10) * (_t61));
        dest[destOffset + 8] = ((_t2) * (_t17) + (((_self20) * (_t46) + (_self21 * _t57))));
        dest[destOffset + 9] = ((_t2) * (_t19) + (((_self20) * (_t47) + (_self21 * _t58))));
        dest[destOffset + 10] = ((_t2) * (_t18) + (((_self20) * (_t48) + (_self21 * _t59))));
        dest[destOffset + 11] = ((((((_self22) * (_t25) + (_self23))) - (_self21) * (_t63))) - (_self20) * (_t61));
        return dest;
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
            return Float3x4OpsSimd.lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_scalar_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static float[] lookAt_lh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_scalar_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    /** {@code lookAt_lh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t23 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        return lookAt_lh_scalar_s9377483b_1_fma(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, java.lang.Math.fma(_eyez, _t15, java.lang.Math.fma(_eyex, _t14, _eyey * _t16)), _t23, java.lang.Math.fma(_t23, _t14, _upx));
    }

    /** {@code lookAt_lh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t23 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        return lookAt_lh_scalar_s9377483b_1_mulAdd(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, ((_eyez) * (_t15) + (((_eyex) * (_t14) + (_eyey * _t16)))), _t23, ((_t23) * (_t14) + (_upx)));
    }

    /** Piece 2 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_scalar_s9377483b_1_fma(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t23, float _t24) {
        float _t25 = java.lang.Math.fma(_t23, _t16, _upy);
        float _t26 = java.lang.Math.fma(_t23, _t15, _upz);
        float _t33 = java.lang.Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = java.lang.Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = java.lang.Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = java.lang.Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = java.lang.Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = java.lang.Math.fma(_t45, _t14, -(_t43 * _t16));
        return lookAt_lh_scalar_s9377483b_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(_eyez, _t44, java.lang.Math.fma(_eyex, _t43, _eyey * _t45)), java.lang.Math.fma(_eyez, _t56, java.lang.Math.fma(_eyex, _t54, _eyey * _t55)));
    }

    /** Piece 2 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_scalar_s9377483b_1_mulAdd(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t23, float _t24) {
        float _t25 = ((_t23) * (_t16) + (_upy));
        float _t26 = ((_t23) * (_t15) + (_upz));
        float _t33 = ((_t24) * (_t16) - (_t25 * _t14));
        float _t34 = ((_t25) * (_t15) - (_t26 * _t16));
        float _t35 = ((_t26) * (_t14) - (_t24 * _t15));
        float _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = ((_t44) * (_t16) - (_t45 * _t15));
        float _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        float _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        return lookAt_lh_scalar_s9377483b_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, ((_eyez) * (_t44) + (((_eyex) * (_t43) + (_eyey * _t45)))), ((_eyez) * (_t56) + (((_eyex) * (_t54) + (_eyey * _t55)))));
    }

    /**
     * Piece 3 of {@code lookAt_lh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_lh_scalar_s9377483b_2_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
        dest[destOffset] = java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self00, _t43, _self01 * _t54));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t16, java.lang.Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t15, java.lang.Math.fma(_self00, _t44, _self01 * _t56));
        dest[destOffset + 3] = java.lang.Math.fma(_self00, -_t58, java.lang.Math.fma(-_self01, _t60, java.lang.Math.fma(-_self02, _t22, _self03)));
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t14, java.lang.Math.fma(_self10, _t43, _self11 * _t54));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t16, java.lang.Math.fma(_self10, _t45, _self11 * _t55));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t15, java.lang.Math.fma(_self10, _t44, _self11 * _t56));
        dest[destOffset + 7] = java.lang.Math.fma(_self10, -_t58, java.lang.Math.fma(-_self11, _t60, java.lang.Math.fma(-_self12, _t22, _self13)));
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self20, _t43, _self21 * _t54));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_self20, _t45, _self21 * _t55));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t15, java.lang.Math.fma(_self20, _t44, _self21 * _t56));
        dest[destOffset + 11] = java.lang.Math.fma(_self20, -_t58, java.lang.Math.fma(-_self21, _t60, java.lang.Math.fma(-_self22, _t22, _self23)));
        return dest;
    }

    /**
     * Piece 3 of {@code lookAt_lh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_lh_scalar_s9377483b_2_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
        dest[destOffset] = ((_self02) * (_t14) + (((_self00) * (_t43) + (_self01 * _t54))));
        dest[destOffset + 1] = ((_self02) * (_t16) + (((_self00) * (_t45) + (_self01 * _t55))));
        dest[destOffset + 2] = ((_self02) * (_t15) + (((_self00) * (_t44) + (_self01 * _t56))));
        dest[destOffset + 3] = ((((((_self03) - (_self02) * (_t22))) - (_self01) * (_t60))) - (_self00) * (_t58));
        dest[destOffset + 4] = ((_self12) * (_t14) + (((_self10) * (_t43) + (_self11 * _t54))));
        dest[destOffset + 5] = ((_self12) * (_t16) + (((_self10) * (_t45) + (_self11 * _t55))));
        dest[destOffset + 6] = ((_self12) * (_t15) + (((_self10) * (_t44) + (_self11 * _t56))));
        dest[destOffset + 7] = ((((((_self13) - (_self12) * (_t22))) - (_self11) * (_t60))) - (_self10) * (_t58));
        dest[destOffset + 8] = ((_self22) * (_t14) + (((_self20) * (_t43) + (_self21 * _t54))));
        dest[destOffset + 9] = ((_self22) * (_t16) + (((_self20) * (_t45) + (_self21 * _t55))));
        dest[destOffset + 10] = ((_self22) * (_t15) + (((_self20) * (_t44) + (_self21 * _t56))));
        dest[destOffset + 11] = ((((((_self23) - (_self22) * (_t22))) - (_self21) * (_t60))) - (_self20) * (_t58));
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
            return Float3x4OpsSimd.lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_scalar_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static float[] lookAt_rh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_scalar_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    /** {@code lookAt_rh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _t3 = center[centerOffset + 2] - _eyez;
        float _t4 = center[centerOffset] - _eyex;
        float _t5 = center[centerOffset + 1] - _eyey;
        float _t12 = java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t26 = -java.lang.Math.fma(_upz, _t18, java.lang.Math.fma(_upx, _t17, _upy * _t19));
        return lookAt_rh_scalar_sf19c5d41_2_fma(dest, destOffset, _eyex, _eyey, _eyez, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t17, _t18, _t19, _upx, _upy, _upz, java.lang.Math.fma(_t26, _t19, _upy), java.lang.Math.fma(_t26, _t17, _upx), java.lang.Math.fma(_t26, _t18, _upz));
    }

    /** {@code lookAt_rh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _t3 = center[centerOffset + 2] - _eyez;
        float _t4 = center[centerOffset] - _eyex;
        float _t5 = center[centerOffset + 1] - _eyey;
        float _t12 = ((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t26 = -((_upz) * (_t18) + (((_upx) * (_t17) + (_upy * _t19))));
        return lookAt_rh_scalar_sf19c5d41_2_mulAdd(dest, destOffset, _eyex, _eyey, _eyez, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t17, _t18, _t19, _upx, _upy, _upz, ((_t26) * (_t19) + (_upy)), ((_t26) * (_t17) + (_upx)), ((_t26) * (_t18) + (_upz)));
    }

    /**
     * Part 1 of {@code lookAt_rh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_rh_scalar_sf19c5d41_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t19, float _t18, float _t25, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        dest[destOffset] = java.lang.Math.fma(_t0, _t17, java.lang.Math.fma(_self00, _t46, _self01 * _t57));
        dest[destOffset + 1] = java.lang.Math.fma(_t0, _t19, java.lang.Math.fma(_self00, _t47, _self01 * _t58));
        dest[destOffset + 2] = java.lang.Math.fma(_t0, _t18, java.lang.Math.fma(_self00, _t48, _self01 * _t59));
        dest[destOffset + 3] = java.lang.Math.fma(_self00, -_t61, java.lang.Math.fma(-_self01, _t63, java.lang.Math.fma(_self02, _t25, _self03)));
        dest[destOffset + 4] = java.lang.Math.fma(_t1, _t17, java.lang.Math.fma(_self10, _t46, _self11 * _t57));
        dest[destOffset + 5] = java.lang.Math.fma(_t1, _t19, java.lang.Math.fma(_self10, _t47, _self11 * _t58));
        dest[destOffset + 6] = java.lang.Math.fma(_t1, _t18, java.lang.Math.fma(_self10, _t48, _self11 * _t59));
        dest[destOffset + 7] = java.lang.Math.fma(_self10, -_t61, java.lang.Math.fma(-_self11, _t63, java.lang.Math.fma(_self12, _t25, _self13)));
        dest[destOffset + 8] = java.lang.Math.fma(_t2, _t17, java.lang.Math.fma(_self20, _t46, _self21 * _t57));
        dest[destOffset + 9] = java.lang.Math.fma(_t2, _t19, java.lang.Math.fma(_self20, _t47, _self21 * _t58));
        dest[destOffset + 10] = java.lang.Math.fma(_t2, _t18, java.lang.Math.fma(_self20, _t48, _self21 * _t59));
        dest[destOffset + 11] = java.lang.Math.fma(_self20, -_t61, java.lang.Math.fma(-_self21, _t63, java.lang.Math.fma(_self22, _t25, _self23)));
        return dest;
    }

    /**
     * Part 1 of {@code lookAt_rh_scalar}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_rh_scalar_sf19c5d41_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t19, float _t18, float _t25, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        dest[destOffset] = ((_t0) * (_t17) + (((_self00) * (_t46) + (_self01 * _t57))));
        dest[destOffset + 1] = ((_t0) * (_t19) + (((_self00) * (_t47) + (_self01 * _t58))));
        dest[destOffset + 2] = ((_t0) * (_t18) + (((_self00) * (_t48) + (_self01 * _t59))));
        dest[destOffset + 3] = ((((((_self02) * (_t25) + (_self03))) - (_self01) * (_t63))) - (_self00) * (_t61));
        dest[destOffset + 4] = ((_t1) * (_t17) + (((_self10) * (_t46) + (_self11 * _t57))));
        dest[destOffset + 5] = ((_t1) * (_t19) + (((_self10) * (_t47) + (_self11 * _t58))));
        dest[destOffset + 6] = ((_t1) * (_t18) + (((_self10) * (_t48) + (_self11 * _t59))));
        dest[destOffset + 7] = ((((((_self12) * (_t25) + (_self13))) - (_self11) * (_t63))) - (_self10) * (_t61));
        dest[destOffset + 8] = ((_t2) * (_t17) + (((_self20) * (_t46) + (_self21 * _t57))));
        dest[destOffset + 9] = ((_t2) * (_t19) + (((_self20) * (_t47) + (_self21 * _t58))));
        dest[destOffset + 10] = ((_t2) * (_t18) + (((_self20) * (_t48) + (_self21 * _t59))));
        dest[destOffset + 11] = ((((((_self22) * (_t25) + (_self23))) - (_self21) * (_t63))) - (_self20) * (_t61));
        return dest;
    }

    /** Piece 2 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_scalar_sf19c5d41_2_fma(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t17, float _t18, float _t19, float _upx, float _upy, float _upz, float _t27, float _t28, float _t29) {
        float _t36 = java.lang.Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = java.lang.Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = java.lang.Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = java.lang.Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = java.lang.Math.fma(_t46, _t19, -(_t47 * _t17));
        return lookAt_rh_scalar_sf19c5d41_1_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, (-_self02), (-_self12), (-_self22), _t17, _t19, _t18, java.lang.Math.fma(_eyez, _t18, java.lang.Math.fma(_eyex, _t17, _eyey * _t19)), _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(_eyez, _t48, java.lang.Math.fma(_eyex, _t46, _eyey * _t47)), java.lang.Math.fma(_eyez, _t59, java.lang.Math.fma(_eyex, _t57, _eyey * _t58)));
    }

    /** Piece 2 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_scalar_sf19c5d41_2_mulAdd(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t17, float _t18, float _t19, float _upx, float _upy, float _upz, float _t27, float _t28, float _t29) {
        float _t36 = ((_t27) * (_t17) - (_t28 * _t19));
        float _t37 = ((_t28) * (_t18) - (_t29 * _t17));
        float _t38 = ((_t29) * (_t19) - (_t27 * _t18));
        float _t41 = ((_t36) * (_t36) + (((_t37) * (_t37) + (_t38 * _t38))));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = ((_t47) * (_t18) - (_t48 * _t19));
        float _t58 = ((_t48) * (_t17) - (_t46 * _t18));
        float _t59 = ((_t46) * (_t19) - (_t47 * _t17));
        return lookAt_rh_scalar_sf19c5d41_1_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, (-_self02), (-_self12), (-_self22), _t17, _t19, _t18, ((_eyez) * (_t18) + (((_eyex) * (_t17) + (_eyey * _t19)))), _t46, _t47, _t48, _t57, _t58, _t59, ((_eyez) * (_t48) + (((_eyex) * (_t46) + (_eyey * _t47)))), ((_eyez) * (_t59) + (((_eyex) * (_t57) + (_eyey * _t58)))));
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAt_lh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
            return Float3x4OpsSimd.lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_scalar_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    public static float[] lookAt_lh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_scalar_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAt_lh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t23 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        float _t24 = java.lang.Math.fma(_t23, _t14, upX);
        float _t25 = java.lang.Math.fma(_t23, _t16, upY);
        float _t26 = java.lang.Math.fma(_t23, _t15, upZ);
        return lookAt_lh_scalar_s80541d4b_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, java.lang.Math.fma(eyeZ, _t15, java.lang.Math.fma(eyeX, _t14, eyeY * _t16)), java.lang.Math.fma(_t24, _t16, -(_t25 * _t14)), java.lang.Math.fma(_t25, _t15, -(_t26 * _t16)), java.lang.Math.fma(_t26, _t14, -(_t24 * _t15)));
    }

    /** {@code lookAt_lh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t23 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        float _t24 = ((_t23) * (_t14) + (upX));
        float _t25 = ((_t23) * (_t16) + (upY));
        float _t26 = ((_t23) * (_t15) + (upZ));
        return lookAt_lh_scalar_s80541d4b_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, ((eyeZ) * (_t15) + (((eyeX) * (_t14) + (eyeY * _t16)))), ((_t24) * (_t16) - (_t25 * _t14)), ((_t25) * (_t15) - (_t26 * _t16)), ((_t26) * (_t14) - (_t24 * _t15)));
    }

    /** Piece 2 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_scalar_s80541d4b_1_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t33, float _t34, float _t35) {
        float _t38 = java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = java.lang.Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = java.lang.Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = java.lang.Math.fma(_t45, _t14, -(_t43 * _t16));
        dest[destOffset] = java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self00, _t43, _self01 * _t54));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t16, java.lang.Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t15, java.lang.Math.fma(_self00, _t44, _self01 * _t56));
        return lookAt_lh_scalar_scc8c4645_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(eyeZ, _t44, java.lang.Math.fma(eyeX, _t43, eyeY * _t45)), java.lang.Math.fma(eyeZ, _t56, java.lang.Math.fma(eyeX, _t54, eyeY * _t55)));
    }

    /** Piece 2 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_scalar_s80541d4b_1_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t33, float _t34, float _t35) {
        float _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = ((_t44) * (_t16) - (_t45 * _t15));
        float _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        float _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        dest[destOffset] = ((_self02) * (_t14) + (((_self00) * (_t43) + (_self01 * _t54))));
        dest[destOffset + 1] = ((_self02) * (_t16) + (((_self00) * (_t45) + (_self01 * _t55))));
        dest[destOffset + 2] = ((_self02) * (_t15) + (((_self00) * (_t44) + (_self01 * _t56))));
        return lookAt_lh_scalar_scc8c4645_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, ((eyeZ) * (_t44) + (((eyeX) * (_t43) + (eyeY * _t45)))), ((eyeZ) * (_t56) + (((eyeX) * (_t54) + (eyeY * _t55)))));
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
            return Float3x4OpsSimd.lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_scalar_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    public static float[] lookAt_rh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_scalar_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAt_rh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _t26 = -java.lang.Math.fma(upZ, _t18, java.lang.Math.fma(upX, _t17, upY * _t19));
        float _t27 = java.lang.Math.fma(_t26, _t19, upY);
        float _t28 = java.lang.Math.fma(_t26, _t17, upX);
        float _t29 = java.lang.Math.fma(_t26, _t18, upZ);
        return lookAt_rh_scalar_sacdd24d9_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], -_self02, -_self12, -_self22, _t17, _t18, _t19, java.lang.Math.fma(eyeZ, _t18, java.lang.Math.fma(eyeX, _t17, eyeY * _t19)), _t27, _t29, java.lang.Math.fma(_t27, _t17, -(_t28 * _t19)), java.lang.Math.fma(_t28, _t18, -(_t29 * _t17)));
    }

    /** {@code lookAt_rh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _self02 = src[srcOffset + 2];
        float _self12 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = ((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _t26 = -((upZ) * (_t18) + (((upX) * (_t17) + (upY * _t19))));
        float _t27 = ((_t26) * (_t19) + (upY));
        float _t28 = ((_t26) * (_t17) + (upX));
        float _t29 = ((_t26) * (_t18) + (upZ));
        return lookAt_rh_scalar_sacdd24d9_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], -_self02, -_self12, -_self22, _t17, _t18, _t19, ((eyeZ) * (_t18) + (((eyeX) * (_t17) + (eyeY * _t19)))), _t27, _t29, ((_t27) * (_t17) - (_t28 * _t19)), ((_t28) * (_t18) - (_t29 * _t17)));
    }

    /** Piece 2 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_scalar_sacdd24d9_1_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t27, float _t29, float _t36, float _t37) {
        float _t38 = java.lang.Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = java.lang.Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = java.lang.Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = java.lang.Math.fma(_t46, _t19, -(_t47 * _t17));
        dest[destOffset] = java.lang.Math.fma(_t0, _t17, java.lang.Math.fma(_self00, _t46, _self01 * _t57));
        dest[destOffset + 1] = java.lang.Math.fma(_t0, _t19, java.lang.Math.fma(_self00, _t47, _self01 * _t58));
        return lookAt_rh_scalar_s15a48c73_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(eyeZ, _t48, java.lang.Math.fma(eyeX, _t46, eyeY * _t47)), java.lang.Math.fma(eyeZ, _t59, java.lang.Math.fma(eyeX, _t57, eyeY * _t58)));
    }

    /** Piece 2 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_scalar_sacdd24d9_1_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t27, float _t29, float _t36, float _t37) {
        float _t38 = ((_t29) * (_t19) - (_t27 * _t18));
        float _t41 = ((_t36) * (_t36) + (((_t37) * (_t37) + (_t38 * _t38))));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = ((_t47) * (_t18) - (_t48 * _t19));
        float _t58 = ((_t48) * (_t17) - (_t46 * _t18));
        float _t59 = ((_t46) * (_t19) - (_t47 * _t17));
        dest[destOffset] = ((_t0) * (_t17) + (((_self00) * (_t46) + (_self01 * _t57))));
        dest[destOffset + 1] = ((_t0) * (_t19) + (((_self00) * (_t47) + (_self01 * _t58))));
        return lookAt_rh_scalar_s15a48c73_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, ((eyeZ) * (_t48) + (((eyeX) * (_t46) + (eyeY * _t47)))), ((eyeZ) * (_t59) + (((eyeX) * (_t57) + (eyeY * _t58)))));
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
            return Float3x4OpsSimd.lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_scalar_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        return lookAt_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
    }

    public static float[] lookAt_lh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_scalar_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        return lookAt_lh_scalar_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
    }

    /** {@code lookAt_lh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t23 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        return lookAt_lh_scalar_sfabb6e1_1_fma(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, java.lang.Math.fma(_eyez, _t15, java.lang.Math.fma(_eyex, _t14, _eyey * _t16)), _t23, java.lang.Math.fma(_t23, _t14, _upx));
    }

    /** {@code lookAt_lh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t23 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        return lookAt_lh_scalar_sfabb6e1_1_mulAdd(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, ((_eyez) * (_t15) + (((_eyex) * (_t14) + (_eyey * _t16)))), _t23, ((_t23) * (_t14) + (_upx)));
    }

    /** Piece 2 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_scalar_sfabb6e1_1_fma(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t23, float _t24) {
        float _t25 = java.lang.Math.fma(_t23, _t16, _upy);
        float _t26 = java.lang.Math.fma(_t23, _t15, _upz);
        float _t33 = java.lang.Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = java.lang.Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = java.lang.Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = java.lang.Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = java.lang.Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = java.lang.Math.fma(_t45, _t14, -(_t43 * _t16));
        return lookAt_lh_scalar_s9377483b_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(_eyez, _t44, java.lang.Math.fma(_eyex, _t43, _eyey * _t45)), java.lang.Math.fma(_eyez, _t56, java.lang.Math.fma(_eyex, _t54, _eyey * _t55)));
    }

    /** Piece 2 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_scalar_sfabb6e1_1_mulAdd(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t23, float _t24) {
        float _t25 = ((_t23) * (_t16) + (_upy));
        float _t26 = ((_t23) * (_t15) + (_upz));
        float _t33 = ((_t24) * (_t16) - (_t25 * _t14));
        float _t34 = ((_t25) * (_t15) - (_t26 * _t16));
        float _t35 = ((_t26) * (_t14) - (_t24 * _t15));
        float _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = ((_t44) * (_t16) - (_t45 * _t15));
        float _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        float _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        return lookAt_lh_scalar_s9377483b_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, ((_eyez) * (_t44) + (((_eyex) * (_t43) + (_eyey * _t45)))), ((_eyez) * (_t56) + (((_eyex) * (_t54) + (_eyey * _t55)))));
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float3x4OpsSimd.lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
            return Float3x4OpsSimd.lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        }
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_scalar_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        return lookAt_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
    }

    public static float[] lookAt_rh_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_scalar_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        return lookAt_rh_scalar_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
    }

    /** {@code lookAt_rh_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _t3 = center[centerOffset + 2] - _eyez;
        float _t4 = center[centerOffset] - _eyex;
        float _t5 = center[centerOffset + 1] - _eyey;
        float _t12 = java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t26 = -java.lang.Math.fma(_upz, _t18, java.lang.Math.fma(_upx, _t17, _upy * _t19));
        return lookAt_rh_scalar_se0f40c27_2_fma(dest, destOffset, epsilon, _eyex, _eyey, _eyez, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t17, _t18, _t19, _upx, _upy, _upz, java.lang.Math.fma(_t26, _t19, _upy), java.lang.Math.fma(_t26, _t17, _upx), java.lang.Math.fma(_t26, _t18, _upz));
    }

    /** {@code lookAt_rh_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _t3 = center[centerOffset + 2] - _eyez;
        float _t4 = center[centerOffset] - _eyex;
        float _t5 = center[centerOffset + 1] - _eyey;
        float _t12 = ((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t26 = -((_upz) * (_t18) + (((_upx) * (_t17) + (_upy * _t19))));
        return lookAt_rh_scalar_se0f40c27_2_mulAdd(dest, destOffset, epsilon, _eyex, _eyey, _eyez, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t17, _t18, _t19, _upx, _upy, _upz, ((_t26) * (_t19) + (_upy)), ((_t26) * (_t17) + (_upx)), ((_t26) * (_t18) + (_upz)));
    }

    /** Piece 2 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_scalar_se0f40c27_2_fma(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t17, float _t18, float _t19, float _upx, float _upy, float _upz, float _t27, float _t28, float _t29) {
        float _t36 = java.lang.Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = java.lang.Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = java.lang.Math.fma(_t36, _t36, java.lang.Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = java.lang.Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = java.lang.Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = java.lang.Math.fma(_t46, _t19, -(_t47 * _t17));
        return lookAt_rh_scalar_sf19c5d41_1_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, (-_self02), (-_self12), (-_self22), _t17, _t19, _t18, java.lang.Math.fma(_eyez, _t18, java.lang.Math.fma(_eyex, _t17, _eyey * _t19)), _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(_eyez, _t48, java.lang.Math.fma(_eyex, _t46, _eyey * _t47)), java.lang.Math.fma(_eyez, _t59, java.lang.Math.fma(_eyex, _t57, _eyey * _t58)));
    }

    /** Piece 2 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_scalar_se0f40c27_2_mulAdd(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t17, float _t18, float _t19, float _upx, float _upy, float _upz, float _t27, float _t28, float _t29) {
        float _t36 = ((_t27) * (_t17) - (_t28 * _t19));
        float _t37 = ((_t28) * (_t18) - (_t29 * _t17));
        float _t38 = ((_t29) * (_t19) - (_t27 * _t18));
        float _t41 = ((_t36) * (_t36) + (((_t37) * (_t37) + (_t38 * _t38))));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = ((_t47) * (_t18) - (_t48 * _t19));
        float _t58 = ((_t48) * (_t17) - (_t46 * _t18));
        float _t59 = ((_t46) * (_t19) - (_t47 * _t17));
        return lookAt_rh_scalar_sf19c5d41_1_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, (-_self02), (-_self12), (-_self22), _t17, _t19, _t18, ((_eyez) * (_t18) + (((_eyex) * (_t17) + (_eyey * _t19)))), _t46, _t47, _t48, _t57, _t58, _t59, ((_eyez) * (_t48) + (((_eyex) * (_t46) + (_eyey * _t47)))), ((_eyez) * (_t59) + (((_eyex) * (_t57) + (_eyey * _t58)))));
    }

    public static float[] makeLookAt_lh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeLookAt_lh_fma(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return makeLookAt_lh_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    /** {@code makeLookAt_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_lh_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t14, java.lang.Math.fma(upX, _t15, upY * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t15, upX);
        float _t22 = java.lang.Math.fma(_t20, _t16, upY);
        float _t23 = java.lang.Math.fma(_t20, _t14, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t16, -(_t22 * _t15));
        float _t31 = java.lang.Math.fma(_t22, _t14, -(_t23 * _t16));
        float _t32 = java.lang.Math.fma(_t23, _t15, -(_t21 * _t14));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_lh_fma_s7474d98d_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_lh_fma_s7474d98d_1(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeLookAt_lh_s583cd9f4_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t40, _t41, _t42);
    }

    /** {@code makeLookAt_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_lh_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((upZ) * (_t14) + (((upX) * (_t15) + (upY * _t16))));
        float _t21 = ((_t20) * (_t15) + (upX));
        float _t22 = ((_t20) * (_t16) + (upY));
        float _t23 = ((_t20) * (_t14) + (upZ));
        float _t30 = ((_t21) * (_t16) - (_t22 * _t15));
        float _t31 = ((_t22) * (_t14) - (_t23 * _t16));
        float _t32 = ((_t23) * (_t15) - (_t21 * _t14));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeLookAt_lh_s583cd9f4_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t40, _t41, _t42);
    }

    /**
     * Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeLookAt}; reached only through it.
     */
    private static float[] makeLookAt_lh_s583cd9f4_1_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = java.lang.Math.fma(_t42, _t16, -(_t41 * _t14));
        float _t50 = java.lang.Math.fma(_t40, _t14, -(_t42 * _t15));
        float _t51 = java.lang.Math.fma(_t41, _t15, -(_t40 * _t16));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -java.lang.Math.fma(eyeZ, _t42, java.lang.Math.fma(eyeX, _t40, eyeY * _t41));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -java.lang.Math.fma(eyeZ, _t51, java.lang.Math.fma(eyeX, _t49, eyeY * _t50));
        dest[destOffset + 8] = _t15;
        dest[destOffset + 9] = _t16;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = -java.lang.Math.fma(eyeZ, _t14, java.lang.Math.fma(eyeX, _t15, eyeY * _t16));
        return dest;
    }

    /**
     * Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeLookAt}; reached only through it.
     */
    private static float[] makeLookAt_lh_s583cd9f4_1_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = ((_t42) * (_t16) - (_t41 * _t14));
        float _t50 = ((_t40) * (_t14) - (_t42 * _t15));
        float _t51 = ((_t41) * (_t15) - (_t40 * _t16));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -((eyeZ) * (_t42) + (((eyeX) * (_t40) + (eyeY * _t41))));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -((eyeZ) * (_t51) + (((eyeX) * (_t49) + (eyeY * _t50))));
        dest[destOffset + 8] = _t15;
        dest[destOffset + 9] = _t16;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = -((eyeZ) * (_t14) + (((eyeX) * (_t15) + (eyeY * _t16))));
        return dest;
    }

    public static float[] makeLookAt_rh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeLookAt_rh_fma(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return makeLookAt_rh_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    /** {@code makeLookAt_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_rh_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t14, java.lang.Math.fma(upX, _t15, upY * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, upY);
        float _t22 = java.lang.Math.fma(_t20, _t15, upX);
        float _t23 = java.lang.Math.fma(_t20, _t14, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t15, -(_t22 * _t16));
        float _t31 = java.lang.Math.fma(_t22, _t14, -(_t23 * _t15));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t14));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_rh_fma_sddecd3ab_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_rh_fma_sddecd3ab_1(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return makeLookAt_rh_s5db7274a_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t40, _t41, _t42);
    }

    /** {@code makeLookAt_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_rh_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((upZ) * (_t14) + (((upX) * (_t15) + (upY * _t16))));
        float _t21 = ((_t20) * (_t16) + (upY));
        float _t22 = ((_t20) * (_t15) + (upX));
        float _t23 = ((_t20) * (_t14) + (upZ));
        float _t30 = ((_t21) * (_t15) - (_t22 * _t16));
        float _t31 = ((_t22) * (_t14) - (_t23 * _t15));
        float _t32 = ((_t23) * (_t16) - (_t21 * _t14));
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
        return makeLookAt_rh_s5db7274a_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t40, _t41, _t42);
    }

    /**
     * Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeLookAt}; reached only through it.
     */
    private static float[] makeLookAt_rh_s5db7274a_1_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = java.lang.Math.fma(_t41, _t14, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t15, -(_t40 * _t14));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t15));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -java.lang.Math.fma(eyeZ, _t42, java.lang.Math.fma(eyeX, _t40, eyeY * _t41));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -java.lang.Math.fma(eyeZ, _t51, java.lang.Math.fma(eyeX, _t49, eyeY * _t50));
        dest[destOffset + 8] = -_t15;
        dest[destOffset + 9] = -_t16;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = java.lang.Math.fma(eyeZ, _t14, java.lang.Math.fma(eyeX, _t15, eyeY * _t16));
        return dest;
    }

    /**
     * Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeLookAt}; reached only through it.
     */
    private static float[] makeLookAt_rh_s5db7274a_1_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = ((_t41) * (_t14) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t15) - (_t40 * _t14));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t15));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -((eyeZ) * (_t42) + (((eyeX) * (_t40) + (eyeY * _t41))));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -((eyeZ) * (_t51) + (((eyeX) * (_t49) + (eyeY * _t50))));
        dest[destOffset + 8] = -_t15;
        dest[destOffset + 9] = -_t16;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = ((eyeZ) * (_t14) + (((eyeX) * (_t15) + (eyeY * _t16))));
        return dest;
    }

    public static float[] makeLookAt_lh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeLookAt_lh_mulAdd(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t14, java.lang.Math.fma(_upx, _t15, _upy * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t15, _upx);
        float _t22 = java.lang.Math.fma(_t20, _t16, _upy);
        float _t23 = java.lang.Math.fma(_t20, _t14, _upz);
        return makeLookAt_lh_s61056a8a_1_fma(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, java.lang.Math.fma(_t21, _t16, -(_t22 * _t15)), java.lang.Math.fma(_t22, _t14, -(_t23 * _t16)), java.lang.Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** {@code makeLookAt_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_lh_mulAdd(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((_upz) * (_t14) + (((_upx) * (_t15) + (_upy * _t16))));
        float _t21 = ((_t20) * (_t15) + (_upx));
        float _t22 = ((_t20) * (_t16) + (_upy));
        float _t23 = ((_t20) * (_t14) + (_upz));
        return makeLookAt_lh_s61056a8a_1_mulAdd(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, ((_t21) * (_t16) - (_t22 * _t15)), ((_t22) * (_t14) - (_t23 * _t16)), ((_t23) * (_t15) - (_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_lh_s61056a8a_1_fma(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32) {
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = java.lang.Math.fma(_t42, _t16, -(_t41 * _t14));
        float _t50 = java.lang.Math.fma(_t40, _t14, -(_t42 * _t15));
        float _t51 = java.lang.Math.fma(_t41, _t15, -(_t40 * _t16));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -java.lang.Math.fma(_eyez, _t42, java.lang.Math.fma(_eyex, _t40, _eyey * _t41));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -java.lang.Math.fma(_eyez, _t51, java.lang.Math.fma(_eyex, _t49, _eyey * _t50));
        dest[destOffset + 8] = _t15;
        dest[destOffset + 9] = _t16;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = -java.lang.Math.fma(_eyez, _t14, java.lang.Math.fma(_eyex, _t15, _eyey * _t16));
        return dest;
    }

    /** Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_lh_s61056a8a_1_mulAdd(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32) {
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = ((_t42) * (_t16) - (_t41 * _t14));
        float _t50 = ((_t40) * (_t14) - (_t42 * _t15));
        float _t51 = ((_t41) * (_t15) - (_t40 * _t16));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -((_eyez) * (_t42) + (((_eyex) * (_t40) + (_eyey * _t41))));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -((_eyez) * (_t51) + (((_eyex) * (_t49) + (_eyey * _t50))));
        dest[destOffset + 8] = _t15;
        dest[destOffset + 9] = _t16;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = -((_eyez) * (_t14) + (((_eyex) * (_t15) + (_eyey * _t16))));
        return dest;
    }

    public static float[] makeLookAt_rh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeLookAt_rh_mulAdd(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t14, java.lang.Math.fma(_upx, _t15, _upy * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t15, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t14, _upz);
        return makeLookAt_rh_sc987b5b8_1_fma(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, java.lang.Math.fma(_t21, _t15, -(_t22 * _t16)), java.lang.Math.fma(_t22, _t14, -(_t23 * _t15)), java.lang.Math.fma(_t23, _t16, -(_t21 * _t14)));
    }

    /** {@code makeLookAt_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_rh_mulAdd(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((_upz) * (_t14) + (((_upx) * (_t15) + (_upy * _t16))));
        float _t21 = ((_t20) * (_t16) + (_upy));
        float _t22 = ((_t20) * (_t15) + (_upx));
        float _t23 = ((_t20) * (_t14) + (_upz));
        return makeLookAt_rh_sc987b5b8_1_mulAdd(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, ((_t21) * (_t15) - (_t22 * _t16)), ((_t22) * (_t14) - (_t23 * _t15)), ((_t23) * (_t16) - (_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_rh_sc987b5b8_1_fma(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32) {
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
        float _t49 = java.lang.Math.fma(_t41, _t14, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t15, -(_t40 * _t14));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t15));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -java.lang.Math.fma(_eyez, _t42, java.lang.Math.fma(_eyex, _t40, _eyey * _t41));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -java.lang.Math.fma(_eyez, _t51, java.lang.Math.fma(_eyex, _t49, _eyey * _t50));
        dest[destOffset + 8] = -_t15;
        dest[destOffset + 9] = -_t16;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = java.lang.Math.fma(_eyez, _t14, java.lang.Math.fma(_eyex, _t15, _eyey * _t16));
        return dest;
    }

    /** Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_rh_sc987b5b8_1_mulAdd(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32) {
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
        float _t49 = ((_t41) * (_t14) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t15) - (_t40 * _t14));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t15));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -((_eyez) * (_t42) + (((_eyex) * (_t40) + (_eyey * _t41))));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -((_eyez) * (_t51) + (((_eyex) * (_t49) + (_eyey * _t50))));
        dest[destOffset + 8] = -_t15;
        dest[destOffset + 9] = -_t16;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = ((_eyez) * (_t14) + (((_eyex) * (_t15) + (_eyey * _t16))));
        return dest;
    }

    public static float[] makeLookAt_lh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeLookAt_lh_fma(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return makeLookAt_lh_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeLookAt_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_lh_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t14, java.lang.Math.fma(upX, _t15, upY * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t15, upX);
        float _t22 = java.lang.Math.fma(_t20, _t16, upY);
        float _t23 = java.lang.Math.fma(_t20, _t14, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t16, -(_t22 * _t15));
        float _t31 = java.lang.Math.fma(_t22, _t14, -(_t23 * _t16));
        float _t32 = java.lang.Math.fma(_t23, _t15, -(_t21 * _t14));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_lh_fma_s8c46a9f3_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_lh_fma_s8c46a9f3_1(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeLookAt_lh_s583cd9f4_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t40, _t41, _t42);
    }

    /** {@code makeLookAt_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_lh_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((upZ) * (_t14) + (((upX) * (_t15) + (upY * _t16))));
        float _t21 = ((_t20) * (_t15) + (upX));
        float _t22 = ((_t20) * (_t16) + (upY));
        float _t23 = ((_t20) * (_t14) + (upZ));
        float _t30 = ((_t21) * (_t16) - (_t22 * _t15));
        float _t31 = ((_t22) * (_t14) - (_t23 * _t16));
        float _t32 = ((_t23) * (_t15) - (_t21 * _t14));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeLookAt_lh_s583cd9f4_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t40, _t41, _t42);
    }

    public static float[] makeLookAt_rh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeLookAt_rh_fma(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return makeLookAt_rh_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeLookAt_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_rh_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t14, java.lang.Math.fma(upX, _t15, upY * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, upY);
        float _t22 = java.lang.Math.fma(_t20, _t15, upX);
        float _t23 = java.lang.Math.fma(_t20, _t14, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t15, -(_t22 * _t16));
        float _t31 = java.lang.Math.fma(_t22, _t14, -(_t23 * _t15));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t14));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_rh_fma_s63df5391_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_rh_fma_s63df5391_1(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return makeLookAt_rh_s5db7274a_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t40, _t41, _t42);
    }

    /** {@code makeLookAt_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_rh_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((upZ) * (_t14) + (((upX) * (_t15) + (upY * _t16))));
        float _t21 = ((_t20) * (_t16) + (upY));
        float _t22 = ((_t20) * (_t15) + (upX));
        float _t23 = ((_t20) * (_t14) + (upZ));
        float _t30 = ((_t21) * (_t15) - (_t22 * _t16));
        float _t31 = ((_t22) * (_t14) - (_t23 * _t15));
        float _t32 = ((_t23) * (_t16) - (_t21 * _t14));
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
        return makeLookAt_rh_s5db7274a_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t40, _t41, _t42);
    }

    public static float[] makeLookAt_lh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeLookAt_lh_mulAdd(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t14, java.lang.Math.fma(_upx, _t15, _upy * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t15, _upx);
        float _t22 = java.lang.Math.fma(_t20, _t16, _upy);
        float _t23 = java.lang.Math.fma(_t20, _t14, _upz);
        return makeLookAt_lh_s96a80d46_1_fma(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, java.lang.Math.fma(_t21, _t16, -(_t22 * _t15)), java.lang.Math.fma(_t22, _t14, -(_t23 * _t16)), java.lang.Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** {@code makeLookAt_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_lh_mulAdd(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((_upz) * (_t14) + (((_upx) * (_t15) + (_upy * _t16))));
        float _t21 = ((_t20) * (_t15) + (_upx));
        float _t22 = ((_t20) * (_t16) + (_upy));
        float _t23 = ((_t20) * (_t14) + (_upz));
        return makeLookAt_lh_s96a80d46_1_mulAdd(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, ((_t21) * (_t16) - (_t22 * _t15)), ((_t22) * (_t14) - (_t23 * _t16)), ((_t23) * (_t15) - (_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_lh_s96a80d46_1_fma(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32) {
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = java.lang.Math.fma(_t42, _t16, -(_t41 * _t14));
        float _t50 = java.lang.Math.fma(_t40, _t14, -(_t42 * _t15));
        float _t51 = java.lang.Math.fma(_t41, _t15, -(_t40 * _t16));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -java.lang.Math.fma(_eyez, _t42, java.lang.Math.fma(_eyex, _t40, _eyey * _t41));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -java.lang.Math.fma(_eyez, _t51, java.lang.Math.fma(_eyex, _t49, _eyey * _t50));
        dest[destOffset + 8] = _t15;
        dest[destOffset + 9] = _t16;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = -java.lang.Math.fma(_eyez, _t14, java.lang.Math.fma(_eyex, _t15, _eyey * _t16));
        return dest;
    }

    /** Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_lh_s96a80d46_1_mulAdd(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32) {
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = ((_t42) * (_t16) - (_t41 * _t14));
        float _t50 = ((_t40) * (_t14) - (_t42 * _t15));
        float _t51 = ((_t41) * (_t15) - (_t40 * _t16));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -((_eyez) * (_t42) + (((_eyex) * (_t40) + (_eyey * _t41))));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -((_eyez) * (_t51) + (((_eyex) * (_t49) + (_eyey * _t50))));
        dest[destOffset + 8] = _t15;
        dest[destOffset + 9] = _t16;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = -((_eyez) * (_t14) + (((_eyex) * (_t15) + (_eyey * _t16))));
        return dest;
    }

    public static float[] makeLookAt_rh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeLookAt_rh_mulAdd(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t14, java.lang.Math.fma(_upx, _t15, _upy * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t15, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t14, _upz);
        return makeLookAt_rh_s38448a6c_1_fma(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, java.lang.Math.fma(_t21, _t15, -(_t22 * _t16)), java.lang.Math.fma(_t22, _t14, -(_t23 * _t15)), java.lang.Math.fma(_t23, _t16, -(_t21 * _t14)));
    }

    /** {@code makeLookAt_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeLookAt_rh_mulAdd(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((_upz) * (_t14) + (((_upx) * (_t15) + (_upy * _t16))));
        float _t21 = ((_t20) * (_t16) + (_upy));
        float _t22 = ((_t20) * (_t15) + (_upx));
        float _t23 = ((_t20) * (_t14) + (_upz));
        return makeLookAt_rh_s38448a6c_1_mulAdd(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, ((_t21) * (_t15) - (_t22 * _t16)), ((_t22) * (_t14) - (_t23 * _t15)), ((_t23) * (_t16) - (_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_rh_s38448a6c_1_fma(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32) {
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
        float _t49 = java.lang.Math.fma(_t41, _t14, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t15, -(_t40 * _t14));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t15));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -java.lang.Math.fma(_eyez, _t42, java.lang.Math.fma(_eyex, _t40, _eyey * _t41));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -java.lang.Math.fma(_eyez, _t51, java.lang.Math.fma(_eyex, _t49, _eyey * _t50));
        dest[destOffset + 8] = -_t15;
        dest[destOffset + 9] = -_t16;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = java.lang.Math.fma(_eyez, _t14, java.lang.Math.fma(_eyex, _t15, _eyey * _t16));
        return dest;
    }

    /** Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_rh_s38448a6c_1_mulAdd(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32) {
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
        float _t49 = ((_t41) * (_t14) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t15) - (_t40 * _t14));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t15));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -((_eyez) * (_t42) + (((_eyex) * (_t40) + (_eyey * _t41))));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -((_eyez) * (_t51) + (((_eyex) * (_t49) + (_eyey * _t50))));
        dest[destOffset + 8] = -_t15;
        dest[destOffset + 9] = -_t16;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = ((_eyez) * (_t14) + (((_eyex) * (_t15) + (_eyey * _t16))));
        return dest;
    }

    public static float[] makeRotationLookAlong_lh(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_fma(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        return makeRotationLookAlong_lh_s615e211c_1_fma(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_mulAdd(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        return makeRotationLookAlong_lh_s615e211c_1_mulAdd(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s615e211c_1_fma(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest[destOffset + 4] = java.lang.Math.fma(_t39, _t13, -(_t38 * _t11));
        dest[destOffset + 5] = java.lang.Math.fma(_t37, _t11, -(_t39 * _t12));
        dest[destOffset + 6] = java.lang.Math.fma(_t38, _t12, -(_t37 * _t13));
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t12;
        dest[destOffset + 9] = _t13;
        dest[destOffset + 10] = _t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_lh_s615e211c_1_mulAdd(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest[destOffset + 4] = ((_t39) * (_t13) - (_t38 * _t11));
        dest[destOffset + 5] = ((_t37) * (_t11) - (_t39 * _t12));
        dest[destOffset + 6] = ((_t38) * (_t12) - (_t37 * _t13));
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t12;
        dest[destOffset + 9] = _t13;
        dest[destOffset + 10] = _t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    public static float[] makeRotationLookAlong_rh(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_fma(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        return makeRotationLookAlong_rh_sa38f86b6_1_fma(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_mulAdd(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        return makeRotationLookAlong_rh_sa38f86b6_1_mulAdd(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_sa38f86b6_1_fma(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest[destOffset + 4] = java.lang.Math.fma(_t38, _t11, _t39 * -_t13);
        dest[destOffset + 5] = java.lang.Math.fma(_t39, _t12, _t37 * -_t11);
        dest[destOffset + 6] = java.lang.Math.fma(_t37, _t13, _t38 * -_t12);
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -_t12;
        dest[destOffset + 9] = -_t13;
        dest[destOffset + 10] = -_t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_rh_sa38f86b6_1_mulAdd(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        dest[destOffset + 4] = ((_t38) * (_t11) - (_t39 * _t13));
        dest[destOffset + 5] = ((_t39) * (_t12) - (_t37 * _t11));
        dest[destOffset + 6] = ((_t37) * (_t13) - (_t38 * _t12));
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -_t12;
        dest[destOffset + 9] = -_t13;
        dest[destOffset + 10] = -_t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    public static float[] makeRotationLookAlong_lh(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return makeRotationLookAlong_lh_s8bb6db3e_1_fma(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_mulAdd(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return makeRotationLookAlong_lh_s8bb6db3e_1_mulAdd(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s8bb6db3e_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = java.lang.Math.fma(_t39, _t13, -(_t38 * _t11));
        dest[destOffset + 5] = java.lang.Math.fma(_t37, _t11, -(_t39 * _t12));
        dest[destOffset + 6] = java.lang.Math.fma(_t38, _t12, -(_t37 * _t13));
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t12;
        dest[destOffset + 9] = _t13;
        dest[destOffset + 10] = _t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s8bb6db3e_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = ((_t39) * (_t13) - (_t38 * _t11));
        dest[destOffset + 5] = ((_t37) * (_t11) - (_t39 * _t12));
        dest[destOffset + 6] = ((_t38) * (_t12) - (_t37 * _t13));
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t12;
        dest[destOffset + 9] = _t13;
        dest[destOffset + 10] = _t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    public static float[] makeRotationLookAlong_rh(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return makeRotationLookAlong_rh_s41b44b40_1_fma(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_mulAdd(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return makeRotationLookAlong_rh_s41b44b40_1_mulAdd(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_s41b44b40_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = java.lang.Math.fma(_t38, _t11, _t39 * -_t13);
        dest[destOffset + 5] = java.lang.Math.fma(_t39, _t12, _t37 * -_t11);
        dest[destOffset + 6] = java.lang.Math.fma(_t37, _t13, _t38 * -_t12);
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -_t12;
        dest[destOffset + 9] = -_t13;
        dest[destOffset + 10] = -_t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_s41b44b40_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = ((_t38) * (_t11) - (_t39 * _t13));
        dest[destOffset + 5] = ((_t39) * (_t12) - (_t37 * _t11));
        dest[destOffset + 6] = ((_t37) * (_t13) - (_t38 * _t12));
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -_t12;
        dest[destOffset + 9] = -_t13;
        dest[destOffset + 10] = -_t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    public static float[] makeRotationLookAlong_lh(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_fma(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        return makeRotationLookAlong_lh_s615e211c_1_fma(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_mulAdd(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        return makeRotationLookAlong_lh_s615e211c_1_mulAdd(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    public static float[] makeRotationLookAlong_rh(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_fma(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        return makeRotationLookAlong_rh_sa38f86b6_1_fma(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_mulAdd(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        return makeRotationLookAlong_rh_sa38f86b6_1_mulAdd(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    public static float[] makeRotationLookAlong_lh(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return makeRotationLookAlong_lh_s2d7b7dca_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** {@code makeRotationLookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_lh_mulAdd(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return makeRotationLookAlong_lh_s2d7b7dca_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s2d7b7dca_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = java.lang.Math.fma(_t39, _t13, -(_t38 * _t11));
        dest[destOffset + 5] = java.lang.Math.fma(_t37, _t11, -(_t39 * _t12));
        dest[destOffset + 6] = java.lang.Math.fma(_t38, _t12, -(_t37 * _t13));
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t12;
        dest[destOffset + 9] = _t13;
        dest[destOffset + 10] = _t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_lh_s2d7b7dca_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = ((_t39) * (_t13) - (_t38 * _t11));
        dest[destOffset + 5] = ((_t37) * (_t11) - (_t39 * _t12));
        dest[destOffset + 6] = ((_t38) * (_t12) - (_t37 * _t13));
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t12;
        dest[destOffset + 9] = _t13;
        dest[destOffset + 10] = _t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    public static float[] makeRotationLookAlong_rh(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return makeRotationLookAlong_rh_s5700ca94_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** {@code makeRotationLookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] makeRotationLookAlong_rh_mulAdd(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return makeRotationLookAlong_rh_s5700ca94_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_s5700ca94_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = java.lang.Math.fma(_t38, _t11, _t39 * -_t13);
        dest[destOffset + 5] = java.lang.Math.fma(_t39, _t12, _t37 * -_t11);
        dest[destOffset + 6] = java.lang.Math.fma(_t37, _t13, _t38 * -_t12);
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -_t12;
        dest[destOffset + 9] = -_t13;
        dest[destOffset + 10] = -_t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_rh_s5700ca94_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t38;
        dest[destOffset + 2] = _t39;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = ((_t38) * (_t11) - (_t39 * _t13));
        dest[destOffset + 5] = ((_t39) * (_t12) - (_t37 * _t11));
        dest[destOffset + 6] = ((_t37) * (_t13) - (_t38 * _t12));
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -_t12;
        dest[destOffset + 9] = -_t13;
        dest[destOffset + 10] = -_t11;
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    public static float[] mapXYZ_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i];
        }
        return dest;
    }

    public static float[] mapXYnZ_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = _eself1;
            dest[destOffset + _lo + 2] = -_eself2;
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static float[] mapXnYZ_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = -_eself1;
            dest[destOffset + _lo + 2] = _eself2;
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static float[] preRotateAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_scalar_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        float _t0 = -rotY;
        float _t3 = -rotX;
        float _t4 = rotX + rotX;
        float _t5 = rotY + rotY;
        float _t6 = rotZ + rotZ;
        float _t7 = rotW * _t5;
        float _t8 = rotW * _t6;
        float _t10 = rotW * _t4;
        float _t14 = java.lang.Math.fma(-rotZ, _t6, 1.0f);
        return preRotateAround_scalar_s817544da_1_fma(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], -pivotZ, _t4, _t5, rotZ * _t6, java.lang.Math.fma(rotZ, _t4, _t7), java.lang.Math.fma(rotY, _t4, _t8), java.lang.Math.fma(rotZ, _t5, _t10), java.lang.Math.fma(rotY, _t4, -_t8), java.lang.Math.fma(rotZ, _t5, -_t10), java.lang.Math.fma(rotZ, _t4, -_t7), java.lang.Math.fma(_t0, _t5, _t14), java.lang.Math.fma(_t3, _t4, _t14), java.lang.Math.fma(_t3, _t4, java.lang.Math.fma(_t0, _t5, 1.0f)));
    }

    /** {@code preRotateAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -rotY;
        float _t3 = -rotX;
        float _t4 = rotX + rotX;
        float _t5 = rotY + rotY;
        float _t6 = rotZ + rotZ;
        float _t7 = rotW * _t5;
        float _t8 = rotW * _t6;
        float _t10 = rotW * _t4;
        float _t14 = ((1.0f) - (rotZ) * (_t6));
        return preRotateAround_scalar_s817544da_1_mulAdd(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], -pivotZ, _t4, _t5, rotZ * _t6, ((rotZ) * (_t4) + (_t7)), ((rotY) * (_t4) + (_t8)), ((rotZ) * (_t5) + (_t10)), ((rotY) * (_t4) - (_t8)), ((rotZ) * (_t5) - (_t10)), ((rotZ) * (_t4) - (_t7)), ((_t0) * (_t5) + (_t14)), ((_t3) * (_t4) + (_t14)), ((_t3) * (_t4) + (((_t0) * (_t5) + (1.0f)))));
    }

    /**
     * Piece 2 of {@code preRotateAround_scalar}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code preRotateAround}; reached only through it.
     */
    private static float[] preRotateAround_scalar_s817544da_1_fma(float[] dest, int destOffset, float rotX, float rotY, float pivotX, float pivotY, float pivotZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t2, float _t4, float _t5, float _t9, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        dest[destOffset] = java.lang.Math.fma(_self20, _t16, java.lang.Math.fma(_self00, _t22, _self10 * _t19));
        dest[destOffset + 1] = java.lang.Math.fma(_self21, _t16, java.lang.Math.fma(_self01, _t22, _self11 * _t19));
        dest[destOffset + 2] = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_self02, _t22, _self12 * _t19));
        dest[destOffset + 3] = java.lang.Math.fma(_self23, _t16, java.lang.Math.fma(_self03, _t22, _self13 * _t19)) + java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(pivotX, java.lang.Math.fma(rotY, _t5, _t9), -pivotY * _t19));
        dest[destOffset + 4] = java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t17, _self10 * _t23));
        dest[destOffset + 5] = java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t17, _self11 * _t23));
        dest[destOffset + 6] = java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t17, _self12 * _t23));
        dest[destOffset + 7] = java.lang.Math.fma(_self23, _t20, java.lang.Math.fma(_self03, _t17, _self13 * _t23)) + java.lang.Math.fma(_t2, _t20, java.lang.Math.fma(pivotY, java.lang.Math.fma(rotX, _t4, _t9), -pivotX * _t17));
        return preRotateAround_scalar_s817544da_2_fma(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t4, _t5, _t18, _t21, _t24);
    }

    /**
     * Piece 2 of {@code preRotateAround_scalar}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code preRotateAround}; reached only through it.
     */
    private static float[] preRotateAround_scalar_s817544da_1_mulAdd(float[] dest, int destOffset, float rotX, float rotY, float pivotX, float pivotY, float pivotZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t2, float _t4, float _t5, float _t9, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        dest[destOffset] = ((_self20) * (_t16) + (((_self00) * (_t22) + (_self10 * _t19))));
        dest[destOffset + 1] = ((_self21) * (_t16) + (((_self01) * (_t22) + (_self11 * _t19))));
        dest[destOffset + 2] = ((_self22) * (_t16) + (((_self02) * (_t22) + (_self12 * _t19))));
        dest[destOffset + 3] = ((_self23) * (_t16) + (((_self03) * (_t22) + (_self13 * _t19)))) + ((_t2) * (_t16) + (((pivotX) * (((rotY) * (_t5) + (_t9))) - (pivotY * _t19))));
        dest[destOffset + 4] = ((_self20) * (_t20) + (((_self00) * (_t17) + (_self10 * _t23))));
        dest[destOffset + 5] = ((_self21) * (_t20) + (((_self01) * (_t17) + (_self11 * _t23))));
        dest[destOffset + 6] = ((_self22) * (_t20) + (((_self02) * (_t17) + (_self12 * _t23))));
        dest[destOffset + 7] = ((_self23) * (_t20) + (((_self03) * (_t17) + (_self13 * _t23)))) + ((_t2) * (_t20) + (((pivotY) * (((rotX) * (_t4) + (_t9))) - (pivotX * _t17))));
        return preRotateAround_scalar_s817544da_2_mulAdd(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t4, _t5, _t18, _t21, _t24);
    }

    /**
     * Piece 3 of {@code preRotateAround_scalar}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code preRotateAround}; reached only through it.
     */
    private static float[] preRotateAround_scalar_s817544da_2_fma(float[] dest, int destOffset, float rotX, float rotY, float pivotX, float pivotY, float pivotZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t4, float _t5, float _t18, float _t21, float _t24) {
        dest[destOffset + 8] = java.lang.Math.fma(_self20, _t24, java.lang.Math.fma(_self00, _t21, _self10 * _t18));
        dest[destOffset + 9] = java.lang.Math.fma(_self21, _t24, java.lang.Math.fma(_self01, _t21, _self11 * _t18));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self02, _t21, _self12 * _t18));
        dest[destOffset + 11] = java.lang.Math.fma(_self23, _t24, java.lang.Math.fma(_self03, _t21, _self13 * _t18)) + java.lang.Math.fma(-pivotY, _t18, java.lang.Math.fma(pivotZ, java.lang.Math.fma(rotX, _t4, rotY * _t5), -pivotX * _t21));
        return dest;
    }

    /**
     * Piece 3 of {@code preRotateAround_scalar}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code preRotateAround}; reached only through it.
     */
    private static float[] preRotateAround_scalar_s817544da_2_mulAdd(float[] dest, int destOffset, float rotX, float rotY, float pivotX, float pivotY, float pivotZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t4, float _t5, float _t18, float _t21, float _t24) {
        dest[destOffset + 8] = ((_self20) * (_t24) + (((_self00) * (_t21) + (_self10 * _t18))));
        dest[destOffset + 9] = ((_self21) * (_t24) + (((_self01) * (_t21) + (_self11 * _t18))));
        dest[destOffset + 10] = ((_self22) * (_t24) + (((_self02) * (_t21) + (_self12 * _t18))));
        dest[destOffset + 11] = ((_self23) * (_t24) + (((_self03) * (_t21) + (_self13 * _t18)))) + ((((pivotZ) * (((rotX) * (_t4) + (rotY * _t5))) - (pivotX * _t21))) - (pivotY) * (_t18));
        return dest;
    }

    public static float[] preRotateAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_scalar_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_scalar_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    /** {@code preRotateAround_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateAround_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _rotx = rot[rotOffset];
        float _roty = rot[rotOffset + 1];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = -_roty;
        float _t3 = -_rotx;
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t4;
        float _t14 = java.lang.Math.fma(-_rotz, _t6, 1.0f);
        return preRotateAround_scalar_s817544da_1_fma(dest, destOffset, _rotx, _roty, pivot[pivotOffset], pivot[pivotOffset + 1], _pivotz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], -_pivotz, _t4, _t5, _rotz * _t6, java.lang.Math.fma(_rotz, _t4, _t7), java.lang.Math.fma(_roty, _t4, _t8), java.lang.Math.fma(_rotz, _t5, _t10), java.lang.Math.fma(_roty, _t4, -_t8), java.lang.Math.fma(_rotz, _t5, -_t10), java.lang.Math.fma(_rotz, _t4, -_t7), java.lang.Math.fma(_t0, _t5, _t14), java.lang.Math.fma(_t3, _t4, _t14), java.lang.Math.fma(_t3, _t4, java.lang.Math.fma(_t0, _t5, 1.0f)));
    }

    /** {@code preRotateAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _rotx = rot[rotOffset];
        float _roty = rot[rotOffset + 1];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = -_roty;
        float _t3 = -_rotx;
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t4;
        float _t14 = ((1.0f) - (_rotz) * (_t6));
        return preRotateAround_scalar_s817544da_1_mulAdd(dest, destOffset, _rotx, _roty, pivot[pivotOffset], pivot[pivotOffset + 1], _pivotz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], -_pivotz, _t4, _t5, _rotz * _t6, ((_rotz) * (_t4) + (_t7)), ((_roty) * (_t4) + (_t8)), ((_rotz) * (_t5) + (_t10)), ((_roty) * (_t4) - (_t8)), ((_rotz) * (_t5) - (_t10)), ((_rotz) * (_t4) - (_t7)), ((_t0) * (_t5) + (_t14)), ((_t3) * (_t4) + (_t14)), ((_t3) * (_t4) + (((_t0) * (_t5) + (1.0f)))));
    }

    public static float[] preRotateAxis_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_scalar_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_scalar_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@code preRotateAxis_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateAxis_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t2 = 1.0f - _t1;
        float _t3 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = axisY * axisZ;
        float _t18 = java.lang.Math.fma(_t2, axisX * axisX, _t1);
        float _t21 = java.lang.Math.fma(axisY, _t0, _t2 * _t3);
        float _t24 = java.lang.Math.fma(_t2, _t5, -(axisZ * _t0));
        dest[destOffset] = java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24));
        return preRotateAxis_scalar_sb2817bfa_1_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, java.lang.Math.fma(_t2, axisY * axisY, _t1), java.lang.Math.fma(_t2, axisZ * axisZ, _t1), _t21, java.lang.Math.fma(axisZ, _t0, _t2 * _t5), java.lang.Math.fma(axisX, _t0, _t2 * _t7), _t24, java.lang.Math.fma(_t2, _t7, -(axisX * _t0)), java.lang.Math.fma(_t2, _t3, -(axisY * _t0)));
    }

    /** {@code preRotateAxis_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateAxis_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t2 = 1.0f - _t1;
        float _t3 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = axisY * axisZ;
        float _t18 = ((_t2) * (axisX * axisX) + (_t1));
        float _t21 = ((axisY) * (_t0) + (_t2 * _t3));
        float _t24 = ((_t2) * (_t5) - (axisZ * _t0));
        dest[destOffset] = ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24))));
        return preRotateAxis_scalar_sb2817bfa_1_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, ((_t2) * (axisY * axisY) + (_t1)), ((_t2) * (axisZ * axisZ) + (_t1)), _t21, ((axisZ) * (_t0) + (_t2 * _t5)), ((axisX) * (_t0) + (_t2 * _t7)), _t24, ((_t2) * (_t7) - (axisX * _t0)), ((_t2) * (_t3) - (axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preRotateAxis_scalar_sb2817bfa_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest[destOffset + 1] = java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24));
        dest[destOffset + 2] = java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24));
        dest[destOffset + 3] = java.lang.Math.fma(_self23, _t21, java.lang.Math.fma(_self03, _t18, _self13 * _t24));
        dest[destOffset + 4] = java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19));
        dest[destOffset + 5] = java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19));
        dest[destOffset + 6] = java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19));
        dest[destOffset + 7] = java.lang.Math.fma(_self23, _t25, java.lang.Math.fma(_self03, _t22, _self13 * _t19));
        dest[destOffset + 8] = java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23));
        dest[destOffset + 9] = java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23));
        dest[destOffset + 11] = java.lang.Math.fma(_self23, _t20, java.lang.Math.fma(_self03, _t26, _self13 * _t23));
        return dest;
    }

    /** Piece 2 of {@code preRotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preRotateAxis_scalar_sb2817bfa_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest[destOffset + 1] = ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24))));
        dest[destOffset + 2] = ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24))));
        dest[destOffset + 3] = ((_self23) * (_t21) + (((_self03) * (_t18) + (_self13 * _t24))));
        dest[destOffset + 4] = ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19))));
        dest[destOffset + 5] = ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19))));
        dest[destOffset + 6] = ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19))));
        dest[destOffset + 7] = ((_self23) * (_t25) + (((_self03) * (_t22) + (_self13 * _t19))));
        dest[destOffset + 8] = ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23))));
        dest[destOffset + 9] = ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23))));
        dest[destOffset + 10] = ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23))));
        dest[destOffset + 11] = ((_self23) * (_t20) + (((_self03) * (_t26) + (_self13 * _t23))));
        return dest;
    }

    public static float[] preRotateAxis_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_scalar_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _axisx = axis[axisOffset];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t2 = 1.0f - _t1;
        float _t3 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return preRotateAxis_scalar_s93c72b4_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], java.lang.Math.fma(_t2, _axisx * _axisx, _t1), java.lang.Math.fma(_t2, _axisy * _axisy, _t1), java.lang.Math.fma(_t2, _axisz * _axisz, _t1), java.lang.Math.fma(_axisy, _t0, _t2 * _t3), java.lang.Math.fma(_axisz, _t0, _t2 * _t5), java.lang.Math.fma(_axisx, _t0, _t2 * _t7), java.lang.Math.fma(_t2, _t5, -(_axisz * _t0)), java.lang.Math.fma(_t2, _t7, -(_axisx * _t0)), java.lang.Math.fma(_t2, _t3, -(_axisy * _t0)));
    }

    /** {@code preRotateAxis_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateAxis_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _axisx = axis[axisOffset];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t2 = 1.0f - _t1;
        float _t3 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return preRotateAxis_scalar_s93c72b4_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], ((_t2) * (_axisx * _axisx) + (_t1)), ((_t2) * (_axisy * _axisy) + (_t1)), ((_t2) * (_axisz * _axisz) + (_t1)), ((_axisy) * (_t0) + (_t2 * _t3)), ((_axisz) * (_t0) + (_t2 * _t5)), ((_axisx) * (_t0) + (_t2 * _t7)), ((_t2) * (_t5) - (_axisz * _t0)), ((_t2) * (_t7) - (_axisx * _t0)), ((_t2) * (_t3) - (_axisy * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preRotateAxis_scalar_s93c72b4_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest[destOffset] = java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24));
        dest[destOffset + 1] = java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24));
        dest[destOffset + 2] = java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24));
        dest[destOffset + 3] = java.lang.Math.fma(_self23, _t21, java.lang.Math.fma(_self03, _t18, _self13 * _t24));
        dest[destOffset + 4] = java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19));
        dest[destOffset + 5] = java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19));
        dest[destOffset + 6] = java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19));
        dest[destOffset + 7] = java.lang.Math.fma(_self23, _t25, java.lang.Math.fma(_self03, _t22, _self13 * _t19));
        dest[destOffset + 8] = java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23));
        dest[destOffset + 9] = java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23));
        dest[destOffset + 11] = java.lang.Math.fma(_self23, _t20, java.lang.Math.fma(_self03, _t26, _self13 * _t23));
        return dest;
    }

    /** Piece 2 of {@code preRotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preRotateAxis_scalar_s93c72b4_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest[destOffset] = ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24))));
        dest[destOffset + 1] = ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24))));
        dest[destOffset + 2] = ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24))));
        dest[destOffset + 3] = ((_self23) * (_t21) + (((_self03) * (_t18) + (_self13 * _t24))));
        dest[destOffset + 4] = ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19))));
        dest[destOffset + 5] = ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19))));
        dest[destOffset + 6] = ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19))));
        dest[destOffset + 7] = ((_self23) * (_t25) + (((_self03) * (_t22) + (_self13 * _t19))));
        dest[destOffset + 8] = ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23))));
        dest[destOffset + 9] = ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23))));
        dest[destOffset + 10] = ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23))));
        dest[destOffset + 11] = ((_self23) * (_t20) + (((_self03) * (_t26) + (_self13 * _t23))));
        return dest;
    }

    public static float[] preRotateQuat_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateQuat_scalar_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return preRotateQuat_scalar_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    /** {@code preRotateQuat_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateQuat_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = -qY;
        float _t2 = -qX;
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        float _t12 = java.lang.Math.fma(-qZ, _t5, 1.0f);
        float _t14 = java.lang.Math.fma(qZ, _t3, _t6);
        float _t17 = java.lang.Math.fma(qY, _t3, -_t7);
        float _t20 = java.lang.Math.fma(_t0, _t4, _t12);
        dest[destOffset] = java.lang.Math.fma(_self20, _t14, java.lang.Math.fma(_self00, _t20, _self10 * _t17));
        return preRotateQuat_scalar_se6ebeb2b_1_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, java.lang.Math.fma(qY, _t3, _t7), java.lang.Math.fma(qZ, _t4, _t8), _t17, java.lang.Math.fma(qZ, _t4, -_t8), java.lang.Math.fma(qZ, _t3, -_t6), _t20, java.lang.Math.fma(_t2, _t3, _t12), java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0f)));
    }

    /** {@code preRotateQuat_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateQuat_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = -qY;
        float _t2 = -qX;
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        float _t12 = ((1.0f) - (qZ) * (_t5));
        float _t14 = ((qZ) * (_t3) + (_t6));
        float _t17 = ((qY) * (_t3) - (_t7));
        float _t20 = ((_t0) * (_t4) + (_t12));
        dest[destOffset] = ((_self20) * (_t14) + (((_self00) * (_t20) + (_self10 * _t17))));
        return preRotateQuat_scalar_se6ebeb2b_1_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, ((qY) * (_t3) + (_t7)), ((qZ) * (_t4) + (_t8)), _t17, ((qZ) * (_t4) - (_t8)), ((qZ) * (_t3) - (_t6)), _t20, ((_t2) * (_t3) + (_t12)), ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0f)))));
    }

    /** Piece 2 of {@code preRotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preRotateQuat_scalar_se6ebeb2b_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22) {
        dest[destOffset + 1] = java.lang.Math.fma(_self21, _t14, java.lang.Math.fma(_self01, _t20, _self11 * _t17));
        dest[destOffset + 2] = java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self02, _t20, _self12 * _t17));
        dest[destOffset + 3] = java.lang.Math.fma(_self23, _t14, java.lang.Math.fma(_self03, _t20, _self13 * _t17));
        dest[destOffset + 4] = java.lang.Math.fma(_self20, _t18, java.lang.Math.fma(_self00, _t15, _self10 * _t21));
        dest[destOffset + 5] = java.lang.Math.fma(_self21, _t18, java.lang.Math.fma(_self01, _t15, _self11 * _t21));
        dest[destOffset + 6] = java.lang.Math.fma(_self22, _t18, java.lang.Math.fma(_self02, _t15, _self12 * _t21));
        dest[destOffset + 7] = java.lang.Math.fma(_self23, _t18, java.lang.Math.fma(_self03, _t15, _self13 * _t21));
        dest[destOffset + 8] = java.lang.Math.fma(_self20, _t22, java.lang.Math.fma(_self00, _t19, _self10 * _t16));
        dest[destOffset + 9] = java.lang.Math.fma(_self21, _t22, java.lang.Math.fma(_self01, _t19, _self11 * _t16));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self02, _t19, _self12 * _t16));
        dest[destOffset + 11] = java.lang.Math.fma(_self23, _t22, java.lang.Math.fma(_self03, _t19, _self13 * _t16));
        return dest;
    }

    /** Piece 2 of {@code preRotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preRotateQuat_scalar_se6ebeb2b_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22) {
        dest[destOffset + 1] = ((_self21) * (_t14) + (((_self01) * (_t20) + (_self11 * _t17))));
        dest[destOffset + 2] = ((_self22) * (_t14) + (((_self02) * (_t20) + (_self12 * _t17))));
        dest[destOffset + 3] = ((_self23) * (_t14) + (((_self03) * (_t20) + (_self13 * _t17))));
        dest[destOffset + 4] = ((_self20) * (_t18) + (((_self00) * (_t15) + (_self10 * _t21))));
        dest[destOffset + 5] = ((_self21) * (_t18) + (((_self01) * (_t15) + (_self11 * _t21))));
        dest[destOffset + 6] = ((_self22) * (_t18) + (((_self02) * (_t15) + (_self12 * _t21))));
        dest[destOffset + 7] = ((_self23) * (_t18) + (((_self03) * (_t15) + (_self13 * _t21))));
        dest[destOffset + 8] = ((_self20) * (_t22) + (((_self00) * (_t19) + (_self10 * _t16))));
        dest[destOffset + 9] = ((_self21) * (_t22) + (((_self01) * (_t19) + (_self11 * _t16))));
        dest[destOffset + 10] = ((_self22) * (_t22) + (((_self02) * (_t19) + (_self12 * _t16))));
        dest[destOffset + 11] = ((_self23) * (_t22) + (((_self03) * (_t19) + (_self13 * _t16))));
        return dest;
    }

    public static float[] preRotateQuat_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateQuat_scalar_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
        float _qx = q[qOffset];
        float _qy = q[qOffset + 1];
        float _qz = q[qOffset + 2];
        float _qw = q[qOffset + 3];
        float _t0 = -_qy;
        float _t2 = -_qx;
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        float _t6 = _qw * _t4;
        float _t7 = _qw * _t5;
        float _t8 = _qw * _t3;
        float _t12 = java.lang.Math.fma(-_qz, _t5, 1.0f);
        return preRotateQuat_scalar_sccb0e71d_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], java.lang.Math.fma(_qz, _t3, _t6), java.lang.Math.fma(_qy, _t3, _t7), java.lang.Math.fma(_qz, _t4, _t8), java.lang.Math.fma(_qy, _t3, -_t7), java.lang.Math.fma(_qz, _t4, -_t8), java.lang.Math.fma(_qz, _t3, -_t6), java.lang.Math.fma(_t0, _t4, _t12), java.lang.Math.fma(_t2, _t3, _t12), java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0f)));
    }

    /** {@code preRotateQuat_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateQuat_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qx = q[qOffset];
        float _qy = q[qOffset + 1];
        float _qz = q[qOffset + 2];
        float _qw = q[qOffset + 3];
        float _t0 = -_qy;
        float _t2 = -_qx;
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        float _t6 = _qw * _t4;
        float _t7 = _qw * _t5;
        float _t8 = _qw * _t3;
        float _t12 = ((1.0f) - (_qz) * (_t5));
        return preRotateQuat_scalar_sccb0e71d_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], ((_qz) * (_t3) + (_t6)), ((_qy) * (_t3) + (_t7)), ((_qz) * (_t4) + (_t8)), ((_qy) * (_t3) - (_t7)), ((_qz) * (_t4) - (_t8)), ((_qz) * (_t3) - (_t6)), ((_t0) * (_t4) + (_t12)), ((_t2) * (_t3) + (_t12)), ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0f)))));
    }

    /** Piece 2 of {@code preRotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preRotateQuat_scalar_sccb0e71d_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22) {
        dest[destOffset] = java.lang.Math.fma(_self20, _t14, java.lang.Math.fma(_self00, _t20, _self10 * _t17));
        dest[destOffset + 1] = java.lang.Math.fma(_self21, _t14, java.lang.Math.fma(_self01, _t20, _self11 * _t17));
        dest[destOffset + 2] = java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self02, _t20, _self12 * _t17));
        dest[destOffset + 3] = java.lang.Math.fma(_self23, _t14, java.lang.Math.fma(_self03, _t20, _self13 * _t17));
        dest[destOffset + 4] = java.lang.Math.fma(_self20, _t18, java.lang.Math.fma(_self00, _t15, _self10 * _t21));
        dest[destOffset + 5] = java.lang.Math.fma(_self21, _t18, java.lang.Math.fma(_self01, _t15, _self11 * _t21));
        dest[destOffset + 6] = java.lang.Math.fma(_self22, _t18, java.lang.Math.fma(_self02, _t15, _self12 * _t21));
        dest[destOffset + 7] = java.lang.Math.fma(_self23, _t18, java.lang.Math.fma(_self03, _t15, _self13 * _t21));
        dest[destOffset + 8] = java.lang.Math.fma(_self20, _t22, java.lang.Math.fma(_self00, _t19, _self10 * _t16));
        dest[destOffset + 9] = java.lang.Math.fma(_self21, _t22, java.lang.Math.fma(_self01, _t19, _self11 * _t16));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self02, _t19, _self12 * _t16));
        dest[destOffset + 11] = java.lang.Math.fma(_self23, _t22, java.lang.Math.fma(_self03, _t19, _self13 * _t16));
        return dest;
    }

    /** Piece 2 of {@code preRotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] preRotateQuat_scalar_sccb0e71d_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22) {
        dest[destOffset] = ((_self20) * (_t14) + (((_self00) * (_t20) + (_self10 * _t17))));
        dest[destOffset + 1] = ((_self21) * (_t14) + (((_self01) * (_t20) + (_self11 * _t17))));
        dest[destOffset + 2] = ((_self22) * (_t14) + (((_self02) * (_t20) + (_self12 * _t17))));
        dest[destOffset + 3] = ((_self23) * (_t14) + (((_self03) * (_t20) + (_self13 * _t17))));
        dest[destOffset + 4] = ((_self20) * (_t18) + (((_self00) * (_t15) + (_self10 * _t21))));
        dest[destOffset + 5] = ((_self21) * (_t18) + (((_self01) * (_t15) + (_self11 * _t21))));
        dest[destOffset + 6] = ((_self22) * (_t18) + (((_self02) * (_t15) + (_self12 * _t21))));
        dest[destOffset + 7] = ((_self23) * (_t18) + (((_self03) * (_t15) + (_self13 * _t21))));
        dest[destOffset + 8] = ((_self20) * (_t22) + (((_self00) * (_t19) + (_self10 * _t16))));
        dest[destOffset + 9] = ((_self21) * (_t22) + (((_self01) * (_t19) + (_self11 * _t16))));
        dest[destOffset + 10] = ((_self22) * (_t22) + (((_self02) * (_t19) + (_self12 * _t16))));
        dest[destOffset + 11] = ((_self23) * (_t22) + (((_self03) * (_t19) + (_self13 * _t16))));
        return dest;
    }

    public static float[] preRotateX_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateX_scalar_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self10, _t0, -(_self20 * _t1));
        dest[destOffset + 5] = java.lang.Math.fma(_self11, _t0, -(_self21 * _t1));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t0, -(_self22 * _t1));
        dest[destOffset + 7] = java.lang.Math.fma(_self13, _t0, -(_self23 * _t1));
        dest[destOffset + 8] = java.lang.Math.fma(_self10, _t1, _self20 * _t0);
        dest[destOffset + 9] = java.lang.Math.fma(_self11, _t1, _self21 * _t0);
        dest[destOffset + 10] = java.lang.Math.fma(_self12, _t1, _self22 * _t0);
        dest[destOffset + 11] = java.lang.Math.fma(_self13, _t1, _self23 * _t0);
        return dest;
    }

    /** {@code preRotateX_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateX_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self10) * (_t0) - (_self20 * _t1));
        dest[destOffset + 5] = ((_self11) * (_t0) - (_self21 * _t1));
        dest[destOffset + 6] = ((_self12) * (_t0) - (_self22 * _t1));
        dest[destOffset + 7] = ((_self13) * (_t0) - (_self23 * _t1));
        dest[destOffset + 8] = ((_self10) * (_t1) + (_self20 * _t0));
        dest[destOffset + 9] = ((_self11) * (_t1) + (_self21 * _t0));
        dest[destOffset + 10] = ((_self12) * (_t1) + (_self22 * _t0));
        dest[destOffset + 11] = ((_self13) * (_t1) + (_self23 * _t0));
        return dest;
    }

    public static float[] preRotateY_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateY_scalar_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest[destOffset] = java.lang.Math.fma(_self00, _t0, _self20 * _t1);
        dest[destOffset + 1] = java.lang.Math.fma(_self01, _t0, _self21 * _t1);
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t0, _self22 * _t1);
        dest[destOffset + 3] = java.lang.Math.fma(_self03, _t0, _self23 * _t1);
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_self20, _t0, -(_self00 * _t1));
        dest[destOffset + 9] = java.lang.Math.fma(_self21, _t0, -(_self01 * _t1));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t0, -(_self02 * _t1));
        dest[destOffset + 11] = java.lang.Math.fma(_self23, _t0, -(_self03 * _t1));
        return dest;
    }

    /** {@code preRotateY_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateY_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest[destOffset] = ((_self00) * (_t0) + (_self20 * _t1));
        dest[destOffset + 1] = ((_self01) * (_t0) + (_self21 * _t1));
        dest[destOffset + 2] = ((_self02) * (_t0) + (_self22 * _t1));
        dest[destOffset + 3] = ((_self03) * (_t0) + (_self23 * _t1));
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_self20) * (_t0) - (_self00 * _t1));
        dest[destOffset + 9] = ((_self21) * (_t0) - (_self01 * _t1));
        dest[destOffset + 10] = ((_self22) * (_t0) - (_self02 * _t1));
        dest[destOffset + 11] = ((_self23) * (_t0) - (_self03 * _t1));
        return dest;
    }

    public static float[] preRotateZ_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateZ_scalar_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest[destOffset] = java.lang.Math.fma(_self00, _t0, -(_self10 * _t1));
        dest[destOffset + 1] = java.lang.Math.fma(_self01, _t0, -(_self11 * _t1));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t0, -(_self12 * _t1));
        dest[destOffset + 3] = java.lang.Math.fma(_self03, _t0, -(_self13 * _t1));
        dest[destOffset + 4] = java.lang.Math.fma(_self00, _t1, _self10 * _t0);
        dest[destOffset + 5] = java.lang.Math.fma(_self01, _t1, _self11 * _t0);
        dest[destOffset + 6] = java.lang.Math.fma(_self02, _t1, _self12 * _t0);
        dest[destOffset + 7] = java.lang.Math.fma(_self03, _t1, _self13 * _t0);
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /** {@code preRotateZ_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preRotateZ_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest[destOffset] = ((_self00) * (_t0) - (_self10 * _t1));
        dest[destOffset + 1] = ((_self01) * (_t0) - (_self11 * _t1));
        dest[destOffset + 2] = ((_self02) * (_t0) - (_self12 * _t1));
        dest[destOffset + 3] = ((_self03) * (_t0) - (_self13 * _t1));
        dest[destOffset + 4] = ((_self00) * (_t1) + (_self10 * _t0));
        dest[destOffset + 5] = ((_self01) * (_t1) + (_self11 * _t0));
        dest[destOffset + 6] = ((_self02) * (_t1) + (_self12 * _t0));
        dest[destOffset + 7] = ((_self03) * (_t1) + (_self13 * _t0));
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] preScale_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        dest[destOffset] = src[srcOffset] * vX;
        dest[destOffset + 1] = _self01 * vX;
        dest[destOffset + 2] = _self02 * vX;
        dest[destOffset + 3] = _self03 * vX;
        dest[destOffset + 4] = _self10 * vY;
        dest[destOffset + 5] = _self11 * vY;
        dest[destOffset + 6] = _self12 * vY;
        dest[destOffset + 7] = _self13 * vY;
        dest[destOffset + 8] = _self20 * vZ;
        dest[destOffset + 9] = _self21 * vZ;
        dest[destOffset + 10] = _self22 * vZ;
        dest[destOffset + 11] = _self23 * vZ;
        return dest;
    }

    public static float[] preScale_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        dest[destOffset] = src[srcOffset] * _vx;
        dest[destOffset + 1] = _self01 * _vx;
        dest[destOffset + 2] = _self02 * _vx;
        dest[destOffset + 3] = _self03 * _vx;
        dest[destOffset + 4] = _self10 * _vy;
        dest[destOffset + 5] = _self11 * _vy;
        dest[destOffset + 6] = _self12 * _vy;
        dest[destOffset + 7] = _self13 * _vy;
        dest[destOffset + 8] = _self20 * _vz;
        dest[destOffset + 9] = _self21 * _vz;
        dest[destOffset + 10] = _self22 * _vz;
        dest[destOffset + 11] = _self23 * _vz;
        return dest;
    }

    public static float[] preScale_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        for (int _i = 0; _i < 12; _i++) {
            dest[destOffset + _i] = s * src[srcOffset + _i];
        }
        return dest;
    }

    public static float[] preScaleAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_scalar_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = 1.0f - s;
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = java.lang.Math.fma(s, _self03, pivotX * _t0);
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = java.lang.Math.fma(s, _self13, pivotY * _t0);
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = java.lang.Math.fma(s, _self23, pivotZ * _t0);
        return dest;
    }

    /** {@code preScaleAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preScaleAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = 1.0f - s;
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = ((s) * (_self03) + (pivotX * _t0));
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = ((s) * (_self13) + (pivotY * _t0));
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = ((s) * (_self23) + (pivotZ * _t0));
        return dest;
    }

    public static float[] preScaleAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_scalar_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = 1.0f - s;
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = java.lang.Math.fma(s, _self03, _pivotx * _t0);
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = java.lang.Math.fma(s, _self13, _pivoty * _t0);
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = java.lang.Math.fma(s, _self23, _pivotz * _t0);
        return dest;
    }

    /** {@code preScaleAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preScaleAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = 1.0f - s;
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = ((s) * (_self03) + (_pivotx * _t0));
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = ((s) * (_self13) + (_pivoty * _t0));
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = ((s) * (_self23) + (_pivotz * _t0));
        return dest;
    }

    public static float[] preScaleAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_scalar_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        dest[destOffset] = sX * src[srcOffset];
        dest[destOffset + 1] = sX * _self01;
        dest[destOffset + 2] = sX * _self02;
        dest[destOffset + 3] = java.lang.Math.fma(pivotX, 1.0f - sX, sX * _self03);
        dest[destOffset + 4] = sY * _self10;
        dest[destOffset + 5] = sY * _self11;
        dest[destOffset + 6] = sY * _self12;
        dest[destOffset + 7] = java.lang.Math.fma(pivotY, 1.0f - sY, sY * _self13);
        dest[destOffset + 8] = sZ * _self20;
        dest[destOffset + 9] = sZ * _self21;
        dest[destOffset + 10] = sZ * _self22;
        dest[destOffset + 11] = java.lang.Math.fma(pivotZ, 1.0f - sZ, sZ * _self23);
        return dest;
    }

    /** {@code preScaleAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preScaleAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        dest[destOffset] = sX * src[srcOffset];
        dest[destOffset + 1] = sX * _self01;
        dest[destOffset + 2] = sX * _self02;
        dest[destOffset + 3] = ((pivotX) * (1.0f - sX) + (sX * _self03));
        dest[destOffset + 4] = sY * _self10;
        dest[destOffset + 5] = sY * _self11;
        dest[destOffset + 6] = sY * _self12;
        dest[destOffset + 7] = ((pivotY) * (1.0f - sY) + (sY * _self13));
        dest[destOffset + 8] = sZ * _self20;
        dest[destOffset + 9] = sZ * _self21;
        dest[destOffset + 10] = sZ * _self22;
        dest[destOffset + 11] = ((pivotZ) * (1.0f - sZ) + (sZ * _self23));
        return dest;
    }

    public static float[] preScaleAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_scalar_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_scalar_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@code preScaleAround_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preScaleAround_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        dest[destOffset] = _sx * src[srcOffset];
        dest[destOffset + 1] = _sx * _self01;
        dest[destOffset + 2] = _sx * _self02;
        dest[destOffset + 3] = java.lang.Math.fma(_pivotx, 1.0f - _sx, _sx * _self03);
        dest[destOffset + 4] = _sy * _self10;
        dest[destOffset + 5] = _sy * _self11;
        dest[destOffset + 6] = _sy * _self12;
        dest[destOffset + 7] = java.lang.Math.fma(_pivoty, 1.0f - _sy, _sy * _self13);
        dest[destOffset + 8] = _sz * _self20;
        dest[destOffset + 9] = _sz * _self21;
        dest[destOffset + 10] = _sz * _self22;
        dest[destOffset + 11] = java.lang.Math.fma(_pivotz, 1.0f - _sz, _sz * _self23);
        return dest;
    }

    /** {@code preScaleAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] preScaleAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        dest[destOffset] = _sx * src[srcOffset];
        dest[destOffset + 1] = _sx * _self01;
        dest[destOffset + 2] = _sx * _self02;
        dest[destOffset + 3] = ((_pivotx) * (1.0f - _sx) + (_sx * _self03));
        dest[destOffset + 4] = _sy * _self10;
        dest[destOffset + 5] = _sy * _self11;
        dest[destOffset + 6] = _sy * _self12;
        dest[destOffset + 7] = ((_pivoty) * (1.0f - _sy) + (_sy * _self13));
        dest[destOffset + 8] = _sz * _self20;
        dest[destOffset + 9] = _sz * _self21;
        dest[destOffset + 10] = _sz * _self22;
        dest[destOffset + 11] = ((_pivotz) * (1.0f - _sz) + (_sz * _self23));
        return dest;
    }

    public static float[] preTranslate_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _self03 + vX;
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = _self13 + vY;
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23 + vZ;
        return dest;
    }

    public static float[] preTranslate_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _self03 + _vx;
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = _self13 + _vy;
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23 + _vz;
        return dest;
    }

    public static float[] reflect_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_scalar_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _sp0 = normalX + normalX;
        float _t0 = -_self02;
        float _t9 = _sp0 * normalZ;
        float _t10 = _sp0 * normalY;
        float _t11 = (normalY + normalY) * normalZ;
        float _t12 = java.lang.Math.fma(-2.0f, normalX * normalX, 1.0f);
        float _t13 = java.lang.Math.fma(-2.0f, normalY * normalY, 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, normalZ * normalZ, 1.0f);
        dest[destOffset] = java.lang.Math.fma(_t0, _t9, java.lang.Math.fma(_self00, _t12, -(_self01 * _t10)));
        dest[destOffset + 1] = java.lang.Math.fma(_t0, _t11, java.lang.Math.fma(_self01, _t13, -_self00 * _t10));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self01, -_t11, -_self00 * _t9));
        dest[destOffset + 3] = _self03;
        return reflect_scalar_sfaf1351f_1_fma(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self12, -_self22, _t9, _t10, _t11, _t12, _t13, _t14);
    }

    /** {@code reflect_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] reflect_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _sp0 = normalX + normalX;
        float _t0 = -_self02;
        float _t9 = _sp0 * normalZ;
        float _t10 = _sp0 * normalY;
        float _t11 = (normalY + normalY) * normalZ;
        float _t12 = ((1.0f) - (2.0f) * (normalX * normalX));
        float _t13 = ((1.0f) - (2.0f) * (normalY * normalY));
        float _t14 = ((1.0f) - (2.0f) * (normalZ * normalZ));
        dest[destOffset] = ((_t0) * (_t9) + (((_self00) * (_t12) - (_self01 * _t10))));
        dest[destOffset + 1] = ((_t0) * (_t11) + (((_self01) * (_t13) - (_self00 * _t10))));
        dest[destOffset + 2] = ((_self02) * (_t14) + (((_self01) * (-_t11) - (_self00 * _t9))));
        dest[destOffset + 3] = _self03;
        return reflect_scalar_sfaf1351f_1_mulAdd(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self12, -_self22, _t9, _t10, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code reflect_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] reflect_scalar_sfaf1351f_1_fma(float[] dest, int destOffset, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        dest[destOffset + 4] = java.lang.Math.fma(_t1, _t9, java.lang.Math.fma(_self10, _t12, -(_self11 * _t10)));
        dest[destOffset + 5] = java.lang.Math.fma(_t1, _t11, java.lang.Math.fma(_self11, _t13, -_self10 * _t10));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t14, java.lang.Math.fma(_self11, -_t11, -_self10 * _t9));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_t2, _t9, java.lang.Math.fma(_self20, _t12, -(_self21 * _t10)));
        dest[destOffset + 9] = java.lang.Math.fma(_t2, _t11, java.lang.Math.fma(_self21, _t13, -_self20 * _t10));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self21, -_t11, -_self20 * _t9));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /** Piece 2 of {@code reflect_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] reflect_scalar_sfaf1351f_1_mulAdd(float[] dest, int destOffset, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        dest[destOffset + 4] = ((_t1) * (_t9) + (((_self10) * (_t12) - (_self11 * _t10))));
        dest[destOffset + 5] = ((_t1) * (_t11) + (((_self11) * (_t13) - (_self10 * _t10))));
        dest[destOffset + 6] = ((_self12) * (_t14) + (((_self11) * (-_t11) - (_self10 * _t9))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_t2) * (_t9) + (((_self20) * (_t12) - (_self21 * _t10))));
        dest[destOffset + 9] = ((_t2) * (_t11) + (((_self21) * (_t13) - (_self20 * _t10))));
        dest[destOffset + 10] = ((_self22) * (_t14) + (((_self21) * (-_t11) - (_self20 * _t9))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] reflect_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return reflect_scalar_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_scalar_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@code reflect_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] reflect_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _normalx = normal[normalOffset];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _sp0 = _normalx + _normalx;
        float _t0 = -_self02;
        float _t9 = _sp0 * _normalz;
        float _t10 = _sp0 * _normaly;
        float _t11 = (_normaly + _normaly) * _normalz;
        float _t12 = java.lang.Math.fma(-2.0f, _normalx * _normalx, 1.0f);
        float _t13 = java.lang.Math.fma(-2.0f, _normaly * _normaly, 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, _normalz * _normalz, 1.0f);
        dest[destOffset] = java.lang.Math.fma(_t0, _t9, java.lang.Math.fma(_self00, _t12, -(_self01 * _t10)));
        dest[destOffset + 1] = java.lang.Math.fma(_t0, _t11, java.lang.Math.fma(_self01, _t13, -_self00 * _t10));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self01, -_t11, -_self00 * _t9));
        return reflect_scalar_saa866c7d_1_fma(dest, destOffset, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self12, -_self22, _t9, _t10, _t11, _t12, _t13, _t14);
    }

    /** {@code reflect_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] reflect_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _normalx = normal[normalOffset];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _sp0 = _normalx + _normalx;
        float _t0 = -_self02;
        float _t9 = _sp0 * _normalz;
        float _t10 = _sp0 * _normaly;
        float _t11 = (_normaly + _normaly) * _normalz;
        float _t12 = ((1.0f) - (2.0f) * (_normalx * _normalx));
        float _t13 = ((1.0f) - (2.0f) * (_normaly * _normaly));
        float _t14 = ((1.0f) - (2.0f) * (_normalz * _normalz));
        dest[destOffset] = ((_t0) * (_t9) + (((_self00) * (_t12) - (_self01 * _t10))));
        dest[destOffset + 1] = ((_t0) * (_t11) + (((_self01) * (_t13) - (_self00 * _t10))));
        dest[destOffset + 2] = ((_self02) * (_t14) + (((_self01) * (-_t11) - (_self00 * _t9))));
        return reflect_scalar_saa866c7d_1_mulAdd(dest, destOffset, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self12, -_self22, _t9, _t10, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code reflect_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] reflect_scalar_saa866c7d_1_fma(float[] dest, int destOffset, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_t1, _t9, java.lang.Math.fma(_self10, _t12, -(_self11 * _t10)));
        dest[destOffset + 5] = java.lang.Math.fma(_t1, _t11, java.lang.Math.fma(_self11, _t13, -_self10 * _t10));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t14, java.lang.Math.fma(_self11, -_t11, -_self10 * _t9));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_t2, _t9, java.lang.Math.fma(_self20, _t12, -(_self21 * _t10)));
        dest[destOffset + 9] = java.lang.Math.fma(_t2, _t11, java.lang.Math.fma(_self21, _t13, -_self20 * _t10));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self21, -_t11, -_self20 * _t9));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /** Piece 2 of {@code reflect_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] reflect_scalar_saa866c7d_1_mulAdd(float[] dest, int destOffset, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_t1) * (_t9) + (((_self10) * (_t12) - (_self11 * _t10))));
        dest[destOffset + 5] = ((_t1) * (_t11) + (((_self11) * (_t13) - (_self10 * _t10))));
        dest[destOffset + 6] = ((_self12) * (_t14) + (((_self11) * (-_t11) - (_self10 * _t9))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_t2) * (_t9) + (((_self20) * (_t12) - (_self21 * _t10))));
        dest[destOffset + 9] = ((_t2) * (_t11) + (((_self21) * (_t13) - (_self20 * _t10))));
        dest[destOffset + 10] = ((_self22) * (_t14) + (((_self21) * (-_t11) - (_self20 * _t9))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] rotateAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_scalar_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_scalar_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    /** {@code rotateAround_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateAround_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -rotY;
        float _t2 = -rotX;
        float _t3 = -pivotZ;
        float _t5 = rotX + rotX;
        float _t6 = rotY + rotY;
        float _t7 = rotZ + rotZ;
        float _t8 = rotW * _t6;
        float _t9 = rotW * _t7;
        float _t10 = rotW * _t5;
        float _t11 = rotZ * _t7;
        float _t16 = java.lang.Math.fma(-rotZ, _t7, 1.0f);
        float _t20 = java.lang.Math.fma(rotZ, _t5, _t8);
        float _t25 = java.lang.Math.fma(rotY, _t5, -_t9);
        return rotateAround_scalar_s359efd15_1_fma(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t3, _t5, _t6, _t11, java.lang.Math.fma(rotY, _t5, _t9), java.lang.Math.fma(rotZ, _t6, _t10), _t20, java.lang.Math.fma(rotZ, _t5, -_t8), _t25, java.lang.Math.fma(rotZ, _t6, -_t10), java.lang.Math.fma(_t0, _t6, _t16), java.lang.Math.fma(_t2, _t5, _t16), java.lang.Math.fma(_t2, _t5, java.lang.Math.fma(_t0, _t6, 1.0f)), java.lang.Math.fma(_t3, _t20, java.lang.Math.fma(pivotX, java.lang.Math.fma(rotY, _t6, _t11), -pivotY * _t25)));
    }

    /** {@code rotateAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -rotY;
        float _t2 = -rotX;
        float _t3 = -pivotZ;
        float _t5 = rotX + rotX;
        float _t6 = rotY + rotY;
        float _t7 = rotZ + rotZ;
        float _t8 = rotW * _t6;
        float _t9 = rotW * _t7;
        float _t10 = rotW * _t5;
        float _t11 = rotZ * _t7;
        float _t16 = ((1.0f) - (rotZ) * (_t7));
        float _t20 = ((rotZ) * (_t5) + (_t8));
        float _t25 = ((rotY) * (_t5) - (_t9));
        return rotateAround_scalar_s359efd15_1_mulAdd(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t3, _t5, _t6, _t11, ((rotY) * (_t5) + (_t9)), ((rotZ) * (_t6) + (_t10)), _t20, ((rotZ) * (_t5) - (_t8)), _t25, ((rotZ) * (_t6) - (_t10)), ((_t0) * (_t6) + (_t16)), ((_t2) * (_t5) + (_t16)), ((_t2) * (_t5) + (((_t0) * (_t6) + (1.0f)))), ((_t3) * (_t20) + (((pivotX) * (((rotY) * (_t6) + (_t11))) - (pivotY * _t25)))));
    }

    /** Piece 2 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAround_scalar_s359efd15_1_fma(float[] dest, int destOffset, float rotX, float rotY, float pivotX, float pivotY, float pivotZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t3, float _t5, float _t6, float _t11, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t39) {
        float _t40 = java.lang.Math.fma(_t3, _t26, java.lang.Math.fma(pivotY, java.lang.Math.fma(rotX, _t5, _t11), -pivotX * _t18));
        float _t41 = java.lang.Math.fma(-pivotY, _t19, java.lang.Math.fma(pivotZ, java.lang.Math.fma(rotX, _t5, rotY * _t6), -pivotX * _t24));
        dest[destOffset] = java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t27, _self01 * _t18));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t25, _self01 * _t28));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t29, java.lang.Math.fma(_self00, _t20, _self01 * _t26));
        dest[destOffset + 3] = java.lang.Math.fma(_self00, _t39, java.lang.Math.fma(_self01, _t40, java.lang.Math.fma(_self02, _t41, _self03)));
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t27, _self11 * _t18));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t25, _self11 * _t28));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t29, java.lang.Math.fma(_self10, _t20, _self11 * _t26));
        dest[destOffset + 7] = java.lang.Math.fma(_self10, _t39, java.lang.Math.fma(_self11, _t40, java.lang.Math.fma(_self12, _t41, _self13)));
        return rotateAround_scalar_s359efd15_2_fma(dest, destOffset, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, _t39, _t40, _t41);
    }

    /** Piece 2 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAround_scalar_s359efd15_1_mulAdd(float[] dest, int destOffset, float rotX, float rotY, float pivotX, float pivotY, float pivotZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t3, float _t5, float _t6, float _t11, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t39) {
        float _t40 = ((_t3) * (_t26) + (((pivotY) * (((rotX) * (_t5) + (_t11))) - (pivotX * _t18))));
        float _t41 = ((((pivotZ) * (((rotX) * (_t5) + (rotY * _t6))) - (pivotX * _t24))) - (pivotY) * (_t19));
        dest[destOffset] = ((_self02) * (_t24) + (((_self00) * (_t27) + (_self01 * _t18))));
        dest[destOffset + 1] = ((_self02) * (_t19) + (((_self00) * (_t25) + (_self01 * _t28))));
        dest[destOffset + 2] = ((_self02) * (_t29) + (((_self00) * (_t20) + (_self01 * _t26))));
        dest[destOffset + 3] = ((_self00) * (_t39) + (((_self01) * (_t40) + (((_self02) * (_t41) + (_self03))))));
        dest[destOffset + 4] = ((_self12) * (_t24) + (((_self10) * (_t27) + (_self11 * _t18))));
        dest[destOffset + 5] = ((_self12) * (_t19) + (((_self10) * (_t25) + (_self11 * _t28))));
        dest[destOffset + 6] = ((_self12) * (_t29) + (((_self10) * (_t20) + (_self11 * _t26))));
        dest[destOffset + 7] = ((_self10) * (_t39) + (((_self11) * (_t40) + (((_self12) * (_t41) + (_self13))))));
        return rotateAround_scalar_s359efd15_2_mulAdd(dest, destOffset, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, _t39, _t40, _t41);
    }

    /** Piece 3 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAround_scalar_s359efd15_2_fma(float[] dest, int destOffset, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t39, float _t40, float _t41) {
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t27, _self21 * _t18));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t25, _self21 * _t28));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t29, java.lang.Math.fma(_self20, _t20, _self21 * _t26));
        dest[destOffset + 11] = java.lang.Math.fma(_self20, _t39, java.lang.Math.fma(_self21, _t40, java.lang.Math.fma(_self22, _t41, _self23)));
        return dest;
    }

    /** Piece 3 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAround_scalar_s359efd15_2_mulAdd(float[] dest, int destOffset, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t39, float _t40, float _t41) {
        dest[destOffset + 8] = ((_self22) * (_t24) + (((_self20) * (_t27) + (_self21 * _t18))));
        dest[destOffset + 9] = ((_self22) * (_t19) + (((_self20) * (_t25) + (_self21 * _t28))));
        dest[destOffset + 10] = ((_self22) * (_t29) + (((_self20) * (_t20) + (_self21 * _t26))));
        dest[destOffset + 11] = ((_self20) * (_t39) + (((_self21) * (_t40) + (((_self22) * (_t41) + (_self23))))));
        return dest;
    }

    public static float[] rotateAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_scalar_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return rotateAround_scalar_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    /** {@code rotateAround_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateAround_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _rotx = rot[rotOffset];
        float _roty = rot[rotOffset + 1];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = -_roty;
        float _t2 = -_rotx;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotw * _t7;
        float _t10 = _rotw * _t5;
        float _t16 = java.lang.Math.fma(-_rotz, _t7, 1.0f);
        return rotateAround_scalar_sb2fce318_1_fma(dest, destOffset, _rotx, _roty, pivot[pivotOffset], pivot[pivotOffset + 1], _pivotz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], -_pivotz, _t5, _t6, _rotz * _t7, java.lang.Math.fma(_roty, _t5, _t9), java.lang.Math.fma(_rotz, _t6, _t10), java.lang.Math.fma(_rotz, _t5, _t8), java.lang.Math.fma(_rotz, _t5, -_t8), java.lang.Math.fma(_roty, _t5, -_t9), java.lang.Math.fma(_rotz, _t6, -_t10), java.lang.Math.fma(_t0, _t6, _t16), java.lang.Math.fma(_t2, _t5, _t16), java.lang.Math.fma(_t2, _t5, java.lang.Math.fma(_t0, _t6, 1.0f)));
    }

    /** {@code rotateAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _rotx = rot[rotOffset];
        float _roty = rot[rotOffset + 1];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = -_roty;
        float _t2 = -_rotx;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotw * _t7;
        float _t10 = _rotw * _t5;
        float _t16 = ((1.0f) - (_rotz) * (_t7));
        return rotateAround_scalar_sb2fce318_1_mulAdd(dest, destOffset, _rotx, _roty, pivot[pivotOffset], pivot[pivotOffset + 1], _pivotz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], -_pivotz, _t5, _t6, _rotz * _t7, ((_roty) * (_t5) + (_t9)), ((_rotz) * (_t6) + (_t10)), ((_rotz) * (_t5) + (_t8)), ((_rotz) * (_t5) - (_t8)), ((_roty) * (_t5) - (_t9)), ((_rotz) * (_t6) - (_t10)), ((_t0) * (_t6) + (_t16)), ((_t2) * (_t5) + (_t16)), ((_t2) * (_t5) + (((_t0) * (_t6) + (1.0f)))));
    }

    /** Piece 2 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAround_scalar_sb2fce318_1_fma(float[] dest, int destOffset, float _rotx, float _roty, float _pivotx, float _pivoty, float _pivotz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t3, float _t5, float _t6, float _t11, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        float _t39 = java.lang.Math.fma(_t3, _t20, java.lang.Math.fma(_pivotx, java.lang.Math.fma(_roty, _t6, _t11), -_pivoty * _t25));
        float _t40 = java.lang.Math.fma(_t3, _t26, java.lang.Math.fma(_pivoty, java.lang.Math.fma(_rotx, _t5, _t11), -_pivotx * _t18));
        float _t41 = java.lang.Math.fma(-_pivoty, _t19, java.lang.Math.fma(_pivotz, java.lang.Math.fma(_rotx, _t5, _roty * _t6), -_pivotx * _t24));
        dest[destOffset] = java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t27, _self01 * _t18));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t25, _self01 * _t28));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t29, java.lang.Math.fma(_self00, _t20, _self01 * _t26));
        dest[destOffset + 3] = java.lang.Math.fma(_self00, _t39, java.lang.Math.fma(_self01, _t40, java.lang.Math.fma(_self02, _t41, _self03)));
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t27, _self11 * _t18));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t25, _self11 * _t28));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t29, java.lang.Math.fma(_self10, _t20, _self11 * _t26));
        return rotateAround_scalar_sb2fce318_2_fma(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, _t39, _t40, _t41);
    }

    /** Piece 2 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAround_scalar_sb2fce318_1_mulAdd(float[] dest, int destOffset, float _rotx, float _roty, float _pivotx, float _pivoty, float _pivotz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t3, float _t5, float _t6, float _t11, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        float _t39 = ((_t3) * (_t20) + (((_pivotx) * (((_roty) * (_t6) + (_t11))) - (_pivoty * _t25))));
        float _t40 = ((_t3) * (_t26) + (((_pivoty) * (((_rotx) * (_t5) + (_t11))) - (_pivotx * _t18))));
        float _t41 = ((((_pivotz) * (((_rotx) * (_t5) + (_roty * _t6))) - (_pivotx * _t24))) - (_pivoty) * (_t19));
        dest[destOffset] = ((_self02) * (_t24) + (((_self00) * (_t27) + (_self01 * _t18))));
        dest[destOffset + 1] = ((_self02) * (_t19) + (((_self00) * (_t25) + (_self01 * _t28))));
        dest[destOffset + 2] = ((_self02) * (_t29) + (((_self00) * (_t20) + (_self01 * _t26))));
        dest[destOffset + 3] = ((_self00) * (_t39) + (((_self01) * (_t40) + (((_self02) * (_t41) + (_self03))))));
        dest[destOffset + 4] = ((_self12) * (_t24) + (((_self10) * (_t27) + (_self11 * _t18))));
        dest[destOffset + 5] = ((_self12) * (_t19) + (((_self10) * (_t25) + (_self11 * _t28))));
        dest[destOffset + 6] = ((_self12) * (_t29) + (((_self10) * (_t20) + (_self11 * _t26))));
        return rotateAround_scalar_sb2fce318_2_mulAdd(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, _t39, _t40, _t41);
    }

    /** Piece 3 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAround_scalar_sb2fce318_2_fma(float[] dest, int destOffset, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t39, float _t40, float _t41) {
        dest[destOffset + 7] = java.lang.Math.fma(_self10, _t39, java.lang.Math.fma(_self11, _t40, java.lang.Math.fma(_self12, _t41, _self13)));
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t27, _self21 * _t18));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t25, _self21 * _t28));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t29, java.lang.Math.fma(_self20, _t20, _self21 * _t26));
        dest[destOffset + 11] = java.lang.Math.fma(_self20, _t39, java.lang.Math.fma(_self21, _t40, java.lang.Math.fma(_self22, _t41, _self23)));
        return dest;
    }

    /** Piece 3 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAround_scalar_sb2fce318_2_mulAdd(float[] dest, int destOffset, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t39, float _t40, float _t41) {
        dest[destOffset + 7] = ((_self10) * (_t39) + (((_self11) * (_t40) + (((_self12) * (_t41) + (_self13))))));
        dest[destOffset + 8] = ((_self22) * (_t24) + (((_self20) * (_t27) + (_self21 * _t18))));
        dest[destOffset + 9] = ((_self22) * (_t19) + (((_self20) * (_t25) + (_self21 * _t28))));
        dest[destOffset + 10] = ((_self22) * (_t29) + (((_self20) * (_t20) + (_self21 * _t26))));
        dest[destOffset + 11] = ((_self20) * (_t39) + (((_self21) * (_t40) + (((_self22) * (_t41) + (_self23))))));
        return dest;
    }

    public static float[] rotateAxis_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_scalar_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_scalar_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@code rotateAxis_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateAxis_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisZ;
        float _t6 = axisX * axisY;
        float _t7 = axisY * axisZ;
        float _t18 = java.lang.Math.fma(_t2, axisX * axisX, _t0);
        float _t21 = java.lang.Math.fma(axisZ, _t1, _t2 * _t6);
        float _t24 = java.lang.Math.fma(_t2, _t3, -(axisY * _t1));
        dest[destOffset] = java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21));
        return rotateAxis_scalar_sa858d41_1_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, java.lang.Math.fma(_t2, axisY * axisY, _t0), java.lang.Math.fma(_t2, axisZ * axisZ, _t0), _t21, java.lang.Math.fma(axisX, _t1, _t2 * _t7), java.lang.Math.fma(axisY, _t1, _t2 * _t3), _t24, java.lang.Math.fma(_t2, _t6, -(axisZ * _t1)), java.lang.Math.fma(_t2, _t7, -(axisX * _t1)));
    }

    /** {@code rotateAxis_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateAxis_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisZ;
        float _t6 = axisX * axisY;
        float _t7 = axisY * axisZ;
        float _t18 = ((_t2) * (axisX * axisX) + (_t0));
        float _t21 = ((axisZ) * (_t1) + (_t2 * _t6));
        float _t24 = ((_t2) * (_t3) - (axisY * _t1));
        dest[destOffset] = ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21))));
        return rotateAxis_scalar_sa858d41_1_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, ((_t2) * (axisY * axisY) + (_t0)), ((_t2) * (axisZ * axisZ) + (_t0)), _t21, ((axisX) * (_t1) + (_t2 * _t7)), ((axisY) * (_t1) + (_t2 * _t3)), _t24, ((_t2) * (_t6) - (axisZ * _t1)), ((_t2) * (_t7) - (axisX * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAxis_scalar_sa858d41_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /** Piece 2 of {@code rotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAxis_scalar_sa858d41_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest[destOffset + 1] = ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19))));
        dest[destOffset + 2] = ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21))));
        dest[destOffset + 5] = ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19))));
        dest[destOffset + 6] = ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21))));
        dest[destOffset + 9] = ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19))));
        dest[destOffset + 10] = ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] rotateAxis_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_scalar_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _axisx = axis[axisOffset];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisz;
        float _t6 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return rotateAxis_scalar_s5c785e55_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], java.lang.Math.fma(_t2, _axisx * _axisx, _t0), java.lang.Math.fma(_t2, _axisy * _axisy, _t0), java.lang.Math.fma(_t2, _axisz * _axisz, _t0), java.lang.Math.fma(_axisz, _t1, _t2 * _t6), java.lang.Math.fma(_axisx, _t1, _t2 * _t7), java.lang.Math.fma(_axisy, _t1, _t2 * _t3), java.lang.Math.fma(_t2, _t3, -(_axisy * _t1)), java.lang.Math.fma(_t2, _t6, -(_axisz * _t1)), java.lang.Math.fma(_t2, _t7, -(_axisx * _t1)));
    }

    /** {@code rotateAxis_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateAxis_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _axisx = axis[axisOffset];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisz;
        float _t6 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return rotateAxis_scalar_s5c785e55_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], ((_t2) * (_axisx * _axisx) + (_t0)), ((_t2) * (_axisy * _axisy) + (_t0)), ((_t2) * (_axisz * _axisz) + (_t0)), ((_axisz) * (_t1) + (_t2 * _t6)), ((_axisx) * (_t1) + (_t2 * _t7)), ((_axisy) * (_t1) + (_t2 * _t3)), ((_t2) * (_t3) - (_axisy * _t1)), ((_t2) * (_t6) - (_axisz * _t1)), ((_t2) * (_t7) - (_axisx * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAxis_scalar_s5c785e55_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest[destOffset] = java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /** Piece 2 of {@code rotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAxis_scalar_s5c785e55_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest[destOffset] = ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21))));
        dest[destOffset + 1] = ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19))));
        dest[destOffset + 2] = ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21))));
        dest[destOffset + 5] = ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19))));
        dest[destOffset + 6] = ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21))));
        dest[destOffset + 9] = ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19))));
        dest[destOffset + 10] = ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] rotateQuat_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateQuat_scalar_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_scalar_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    /** {@code rotateQuat_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateQuat_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = -qY;
        float _t2 = -qX;
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        float _t12 = java.lang.Math.fma(-qZ, _t5, 1.0f);
        float _t14 = java.lang.Math.fma(qY, _t3, _t7);
        float _t17 = java.lang.Math.fma(qZ, _t3, -_t6);
        float _t20 = java.lang.Math.fma(_t0, _t4, _t12);
        dest[destOffset] = java.lang.Math.fma(_self02, _t17, java.lang.Math.fma(_self00, _t20, _self01 * _t14));
        return rotateQuat_scalar_s181d6818_1_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, java.lang.Math.fma(qZ, _t4, _t8), java.lang.Math.fma(qZ, _t3, _t6), _t17, java.lang.Math.fma(qY, _t3, -_t7), java.lang.Math.fma(qZ, _t4, -_t8), _t20, java.lang.Math.fma(_t2, _t3, _t12), java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0f)));
    }

    /** {@code rotateQuat_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateQuat_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = -qY;
        float _t2 = -qX;
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        float _t12 = ((1.0f) - (qZ) * (_t5));
        float _t14 = ((qY) * (_t3) + (_t7));
        float _t17 = ((qZ) * (_t3) - (_t6));
        float _t20 = ((_t0) * (_t4) + (_t12));
        dest[destOffset] = ((_self02) * (_t17) + (((_self00) * (_t20) + (_self01 * _t14))));
        return rotateQuat_scalar_s181d6818_1_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, ((qZ) * (_t4) + (_t8)), ((qZ) * (_t3) + (_t6)), _t17, ((qY) * (_t3) - (_t7)), ((qZ) * (_t4) - (_t8)), _t20, ((_t2) * (_t3) + (_t12)), ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0f)))));
    }

    /** Piece 2 of {@code rotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateQuat_scalar_s181d6818_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22) {
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t15, java.lang.Math.fma(_self00, _t18, _self01 * _t21));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t16, _self01 * _t19));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t17, java.lang.Math.fma(_self10, _t20, _self11 * _t14));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t15, java.lang.Math.fma(_self10, _t18, _self11 * _t21));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t16, _self11 * _t19));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t17, java.lang.Math.fma(_self20, _t20, _self21 * _t14));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t15, java.lang.Math.fma(_self20, _t18, _self21 * _t21));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t16, _self21 * _t19));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /** Piece 2 of {@code rotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateQuat_scalar_s181d6818_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22) {
        dest[destOffset + 1] = ((_self02) * (_t15) + (((_self00) * (_t18) + (_self01 * _t21))));
        dest[destOffset + 2] = ((_self02) * (_t22) + (((_self00) * (_t16) + (_self01 * _t19))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t17) + (((_self10) * (_t20) + (_self11 * _t14))));
        dest[destOffset + 5] = ((_self12) * (_t15) + (((_self10) * (_t18) + (_self11 * _t21))));
        dest[destOffset + 6] = ((_self12) * (_t22) + (((_self10) * (_t16) + (_self11 * _t19))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_self22) * (_t17) + (((_self20) * (_t20) + (_self21 * _t14))));
        dest[destOffset + 9] = ((_self22) * (_t15) + (((_self20) * (_t18) + (_self21 * _t21))));
        dest[destOffset + 10] = ((_self22) * (_t22) + (((_self20) * (_t16) + (_self21 * _t19))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] rotateQuat_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateQuat_scalar_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
        float _qx = q[qOffset];
        float _qy = q[qOffset + 1];
        float _qz = q[qOffset + 2];
        float _qw = q[qOffset + 3];
        float _t0 = -_qy;
        float _t2 = -_qx;
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        float _t6 = _qw * _t4;
        float _t7 = _qw * _t5;
        float _t8 = _qw * _t3;
        float _t12 = java.lang.Math.fma(-_qz, _t5, 1.0f);
        return rotateQuat_scalar_s75d8e37a_1_fma(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], java.lang.Math.fma(_qy, _t3, _t7), java.lang.Math.fma(_qz, _t4, _t8), java.lang.Math.fma(_qz, _t3, _t6), java.lang.Math.fma(_qz, _t3, -_t6), java.lang.Math.fma(_qy, _t3, -_t7), java.lang.Math.fma(_qz, _t4, -_t8), java.lang.Math.fma(_t0, _t4, _t12), java.lang.Math.fma(_t2, _t3, _t12), java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0f)));
    }

    /** {@code rotateQuat_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateQuat_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qx = q[qOffset];
        float _qy = q[qOffset + 1];
        float _qz = q[qOffset + 2];
        float _qw = q[qOffset + 3];
        float _t0 = -_qy;
        float _t2 = -_qx;
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        float _t6 = _qw * _t4;
        float _t7 = _qw * _t5;
        float _t8 = _qw * _t3;
        float _t12 = ((1.0f) - (_qz) * (_t5));
        return rotateQuat_scalar_s75d8e37a_1_mulAdd(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], ((_qy) * (_t3) + (_t7)), ((_qz) * (_t4) + (_t8)), ((_qz) * (_t3) + (_t6)), ((_qz) * (_t3) - (_t6)), ((_qy) * (_t3) - (_t7)), ((_qz) * (_t4) - (_t8)), ((_t0) * (_t4) + (_t12)), ((_t2) * (_t3) + (_t12)), ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0f)))));
    }

    /** Piece 2 of {@code rotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateQuat_scalar_s75d8e37a_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22) {
        dest[destOffset] = java.lang.Math.fma(_self02, _t17, java.lang.Math.fma(_self00, _t20, _self01 * _t14));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t15, java.lang.Math.fma(_self00, _t18, _self01 * _t21));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t16, _self01 * _t19));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t17, java.lang.Math.fma(_self10, _t20, _self11 * _t14));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t15, java.lang.Math.fma(_self10, _t18, _self11 * _t21));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t16, _self11 * _t19));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t17, java.lang.Math.fma(_self20, _t20, _self21 * _t14));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t15, java.lang.Math.fma(_self20, _t18, _self21 * _t21));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t16, _self21 * _t19));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /** Piece 2 of {@code rotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateQuat_scalar_s75d8e37a_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22) {
        dest[destOffset] = ((_self02) * (_t17) + (((_self00) * (_t20) + (_self01 * _t14))));
        dest[destOffset + 1] = ((_self02) * (_t15) + (((_self00) * (_t18) + (_self01 * _t21))));
        dest[destOffset + 2] = ((_self02) * (_t22) + (((_self00) * (_t16) + (_self01 * _t19))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t17) + (((_self10) * (_t20) + (_self11 * _t14))));
        dest[destOffset + 5] = ((_self12) * (_t15) + (((_self10) * (_t18) + (_self11 * _t21))));
        dest[destOffset + 6] = ((_self12) * (_t22) + (((_self10) * (_t16) + (_self11 * _t19))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_self22) * (_t17) + (((_self20) * (_t20) + (_self21 * _t14))));
        dest[destOffset + 9] = ((_self22) * (_t15) + (((_self20) * (_t18) + (_self21 * _t21))));
        dest[destOffset + 10] = ((_self22) * (_t22) + (((_self20) * (_t16) + (_self21 * _t19))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] rotateTowards_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_scalar_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return rotateTowards_scalar_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code rotateTowards_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateTowards_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return rotateTowards_scalar_s39edb7c3_1_fma(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** {@code rotateTowards_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateTowards_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return rotateTowards_scalar_s39edb7c3_1_mulAdd(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code rotateTowards_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_scalar_s39edb7c3_1_fma(float[] dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13));
        dest[destOffset + 7] = _self13;
        return rotateTowards_scalar_s39edb7c3_2_fma(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code rotateTowards_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_scalar_s39edb7c3_1_mulAdd(float[] dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39))));
        dest[destOffset + 1] = ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48))));
        dest[destOffset + 2] = ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39))));
        dest[destOffset + 5] = ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48))));
        dest[destOffset + 6] = ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13))));
        dest[destOffset + 7] = _self13;
        return rotateTowards_scalar_s39edb7c3_2_mulAdd(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /**
     * Piece 3 of {@code rotateTowards_scalar}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code rotateTowards}; reached only through it.
     */
    private static float[] rotateTowards_scalar_s39edb7c3_2_fma(float[] dest, int destOffset, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /**
     * Piece 3 of {@code rotateTowards_scalar}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code rotateTowards}; reached only through it.
     */
    private static float[] rotateTowards_scalar_s39edb7c3_2_mulAdd(float[] dest, int destOffset, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 8] = ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39))));
        dest[destOffset + 9] = ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48))));
        dest[destOffset + 10] = ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] rotateTowards_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_scalar_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return rotateTowards_scalar_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code rotateTowards_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateTowards_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return rotateTowards_scalar_sf232950f_1_fma(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** {@code rotateTowards_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateTowards_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return rotateTowards_scalar_sf232950f_1_mulAdd(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t12)), ((_t19) * (_t11) - (_t20 * _t13)), ((_t20) * (_t12) - (_t18 * _t11)));
    }

    /** Piece 2 of {@code rotateTowards_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_scalar_sf232950f_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        float _t46 = java.lang.Math.fma(_t39, _t12, -(_t38 * _t13));
        float _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        float _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        dest[destOffset] = java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48));
        return rotateTowards_scalar_sf232950f_2_fma(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code rotateTowards_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_scalar_sf232950f_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        dest[destOffset] = ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39))));
        dest[destOffset + 1] = ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48))));
        dest[destOffset + 2] = ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39))));
        dest[destOffset + 5] = ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48))));
        return rotateTowards_scalar_sf232950f_2_mulAdd(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /**
     * Piece 3 of {@code rotateTowards_scalar}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code rotateTowards}; reached only through it.
     */
    private static float[] rotateTowards_scalar_sf232950f_2_fma(float[] dest, int destOffset, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /**
     * Piece 3 of {@code rotateTowards_scalar}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code rotateTowards}; reached only through it.
     */
    private static float[] rotateTowards_scalar_sf232950f_2_mulAdd(float[] dest, int destOffset, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 6] = ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39))));
        dest[destOffset + 9] = ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48))));
        dest[destOffset + 10] = ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] rotateTowards_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_scalar_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return rotateTowards_scalar_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code rotateTowards_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateTowards_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return rotateTowards_scalar_s1758169_1_fma(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** {@code rotateTowards_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateTowards_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return rotateTowards_scalar_s1758169_1_mulAdd(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code rotateTowards_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_scalar_s1758169_1_fma(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13));
        dest[destOffset + 7] = _self13;
        return rotateTowards_scalar_s39edb7c3_2_fma(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code rotateTowards_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_scalar_s1758169_1_mulAdd(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        dest[destOffset] = ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39))));
        dest[destOffset + 1] = ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48))));
        dest[destOffset + 2] = ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39))));
        dest[destOffset + 5] = ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48))));
        dest[destOffset + 6] = ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13))));
        dest[destOffset + 7] = _self13;
        return rotateTowards_scalar_s39edb7c3_2_mulAdd(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    public static float[] rotateTowards_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_scalar_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return rotateTowards_scalar_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code rotateTowards_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateTowards_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return rotateTowards_scalar_sdf2b0ec5_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** {@code rotateTowards_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateTowards_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        return rotateTowards_scalar_sdf2b0ec5_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t12)), ((_t19) * (_t11) - (_t20 * _t13)), ((_t20) * (_t12) - (_t18 * _t11)));
    }

    /** Piece 2 of {@code rotateTowards_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_scalar_sdf2b0ec5_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        float _t46 = java.lang.Math.fma(_t39, _t12, -(_t38 * _t13));
        float _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        float _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        dest[destOffset] = java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39));
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48));
        return rotateTowards_scalar_sf232950f_2_fma(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code rotateTowards_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_scalar_sdf2b0ec5_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        dest[destOffset] = ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39))));
        dest[destOffset + 1] = ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48))));
        dest[destOffset + 2] = ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39))));
        dest[destOffset + 5] = ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48))));
        return rotateTowards_scalar_sf232950f_2_mulAdd(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    public static float[] rotateYXZ_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_scalar_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t18 = java.lang.Math.fma(_t8, _t2, _t1 * _t4);
        float _t20 = java.lang.Math.fma(_t6, _t2, -(_t3 * _t4));
        dest[destOffset] = java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t18, _self01 * _t10));
        return rotateYXZ_scalar_s4cccf8b4_1_fma(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t10, _t5 * _t4, _t5 * _t1, _t3 * _t5, _t18, java.lang.Math.fma(_t6, _t4, _t3 * _t2), _t20, java.lang.Math.fma(_t8, _t4, -(_t2 * _t1)));
    }

    /** {@code rotateYXZ_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] rotateYXZ_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t18 = ((_t8) * (_t2) + (_t1 * _t4));
        float _t20 = ((_t6) * (_t2) - (_t3 * _t4));
        dest[destOffset] = ((_self02) * (_t20) + (((_self00) * (_t18) + (_self01 * _t10))));
        return rotateYXZ_scalar_s4cccf8b4_1_mulAdd(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t10, _t5 * _t4, _t5 * _t1, _t3 * _t5, _t18, ((_t6) * (_t4) + (_t3 * _t2)), _t20, ((_t8) * (_t4) - (_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateYXZ_scalar_s4cccf8b4_1_fma(float[] dest, int destOffset, float _t0, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t10, float _t13, float _t14, float _t15, float _t18, float _t19, float _t20, float _t21) {
        dest[destOffset + 1] = java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t21, _self01 * _t13));
        dest[destOffset + 2] = java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self00, _t15, -(_self01 * _t0)));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t18, _self11 * _t10));
        dest[destOffset + 5] = java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t21, _self11 * _t13));
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t14, java.lang.Math.fma(_self10, _t15, -(_self11 * _t0)));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t18, _self21 * _t10));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t21, _self21 * _t13));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self20, _t15, -(_self21 * _t0)));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /** Piece 2 of {@code rotateYXZ_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateYXZ_scalar_s4cccf8b4_1_mulAdd(float[] dest, int destOffset, float _t0, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t10, float _t13, float _t14, float _t15, float _t18, float _t19, float _t20, float _t21) {
        dest[destOffset + 1] = ((_self02) * (_t19) + (((_self00) * (_t21) + (_self01 * _t13))));
        dest[destOffset + 2] = ((_self02) * (_t14) + (((_self00) * (_t15) - (_self01 * _t0))));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = ((_self12) * (_t20) + (((_self10) * (_t18) + (_self11 * _t10))));
        dest[destOffset + 5] = ((_self12) * (_t19) + (((_self10) * (_t21) + (_self11 * _t13))));
        dest[destOffset + 6] = ((_self12) * (_t14) + (((_self10) * (_t15) - (_self11 * _t0))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_self22) * (_t20) + (((_self20) * (_t18) + (_self21 * _t10))));
        dest[destOffset + 9] = ((_self22) * (_t19) + (((_self20) * (_t21) + (_self21 * _t13))));
        dest[destOffset + 10] = ((_self22) * (_t14) + (((_self20) * (_t15) - (_self21 * _t0))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] scale_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = src[srcOffset + _lo] * vX;
            dest[destOffset + _lo + 1] = _eself1 * vY;
            dest[destOffset + _lo + 2] = _eself2 * vZ;
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static float[] scale_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = src[srcOffset + _lo] * _vx;
            dest[destOffset + _lo + 1] = _eself1 * _vy;
            dest[destOffset + _lo + 2] = _eself2 * _vz;
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static float[] scale_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself1 = src[srcOffset + _lo + 1];
            float _eself2 = src[srcOffset + _lo + 2];
            float _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = s * src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = s * _eself1;
            dest[destOffset + _lo + 2] = s * _eself2;
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static float[] scaleAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_scalar_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return scaleAround_scalar_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    /** {@code scaleAround_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] scaleAround_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _t3 = pivotZ * _t0;
        dest[destOffset] = s * _self00;
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, java.lang.Math.fma(_self02, _t3, _self03)));
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, java.lang.Math.fma(_self12, _t3, _self13)));
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, java.lang.Math.fma(_self22, _t3, _self23)));
        return dest;
    }

    /** {@code scaleAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] scaleAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _t3 = pivotZ * _t0;
        dest[destOffset] = s * _self00;
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = ((_self00) * (_t1) + (((_self01) * (_t2) + (((_self02) * (_t3) + (_self03))))));
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = ((_self10) * (_t1) + (((_self11) * (_t2) + (((_self12) * (_t3) + (_self13))))));
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = ((_self20) * (_t1) + (((_self21) * (_t2) + (((_self22) * (_t3) + (_self23))))));
        return dest;
    }

    public static float[] scaleAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_scalar_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return scaleAround_scalar_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@code scaleAround_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] scaleAround_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = 1.0f - s;
        float _t1 = pivot[pivotOffset] * _t0;
        float _t2 = pivot[pivotOffset + 1] * _t0;
        float _t3 = pivot[pivotOffset + 2] * _t0;
        dest[destOffset] = s * _self00;
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, java.lang.Math.fma(_self02, _t3, _self03)));
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, java.lang.Math.fma(_self12, _t3, _self13)));
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, java.lang.Math.fma(_self22, _t3, _self23)));
        return dest;
    }

    /** {@code scaleAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] scaleAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t0 = 1.0f - s;
        float _t1 = pivot[pivotOffset] * _t0;
        float _t2 = pivot[pivotOffset + 1] * _t0;
        float _t3 = pivot[pivotOffset + 2] * _t0;
        dest[destOffset] = s * _self00;
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = ((_self00) * (_t1) + (((_self01) * (_t2) + (((_self02) * (_t3) + (_self03))))));
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = ((_self10) * (_t1) + (((_self11) * (_t2) + (((_self12) * (_t3) + (_self13))))));
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = ((_self20) * (_t1) + (((_self21) * (_t2) + (((_self22) * (_t3) + (_self23))))));
        return dest;
    }

    public static float[] scaleAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_scalar_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_scalar_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    /** {@code scaleAround_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] scaleAround_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        dest[destOffset] = sX * _self00;
        dest[destOffset + 1] = sY * _self01;
        dest[destOffset + 2] = sZ * _self02;
        dest[destOffset + 3] = java.lang.Math.fma(_self00, _t3, java.lang.Math.fma(_self01, _t4, java.lang.Math.fma(_self02, _t5, _self03)));
        dest[destOffset + 4] = sX * _self10;
        dest[destOffset + 5] = sY * _self11;
        dest[destOffset + 6] = sZ * _self12;
        dest[destOffset + 7] = java.lang.Math.fma(_self10, _t3, java.lang.Math.fma(_self11, _t4, java.lang.Math.fma(_self12, _t5, _self13)));
        dest[destOffset + 8] = sX * _self20;
        dest[destOffset + 9] = sY * _self21;
        dest[destOffset + 10] = sZ * _self22;
        dest[destOffset + 11] = java.lang.Math.fma(_self20, _t3, java.lang.Math.fma(_self21, _t4, java.lang.Math.fma(_self22, _t5, _self23)));
        return dest;
    }

    /** {@code scaleAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] scaleAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        dest[destOffset] = sX * _self00;
        dest[destOffset + 1] = sY * _self01;
        dest[destOffset + 2] = sZ * _self02;
        dest[destOffset + 3] = ((_self00) * (_t3) + (((_self01) * (_t4) + (((_self02) * (_t5) + (_self03))))));
        dest[destOffset + 4] = sX * _self10;
        dest[destOffset + 5] = sY * _self11;
        dest[destOffset + 6] = sZ * _self12;
        dest[destOffset + 7] = ((_self10) * (_t3) + (((_self11) * (_t4) + (((_self12) * (_t5) + (_self13))))));
        dest[destOffset + 8] = sX * _self20;
        dest[destOffset + 9] = sY * _self21;
        dest[destOffset + 10] = sZ * _self22;
        dest[destOffset + 11] = ((_self20) * (_t3) + (((_self21) * (_t4) + (((_self22) * (_t5) + (_self23))))));
        return dest;
    }

    public static float[] scaleAround_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_scalar_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_scalar_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@code scaleAround_scalar} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] scaleAround_scalar_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t3 = pivot[pivotOffset] * (1.0f - _sx);
        float _t4 = pivot[pivotOffset + 1] * (1.0f - _sy);
        float _t5 = pivot[pivotOffset + 2] * (1.0f - _sz);
        dest[destOffset] = _sx * _self00;
        dest[destOffset + 1] = _sy * _self01;
        dest[destOffset + 2] = _sz * _self02;
        dest[destOffset + 3] = java.lang.Math.fma(_self00, _t3, java.lang.Math.fma(_self01, _t4, java.lang.Math.fma(_self02, _t5, _self03)));
        dest[destOffset + 4] = _sx * _self10;
        dest[destOffset + 5] = _sy * _self11;
        dest[destOffset + 6] = _sz * _self12;
        dest[destOffset + 7] = java.lang.Math.fma(_self10, _t3, java.lang.Math.fma(_self11, _t4, java.lang.Math.fma(_self12, _t5, _self13)));
        return scaleAround_scalar_s217ab02b_1_fma(dest, destOffset, _sx, _sy, _sz, _self20, _self21, _self22, _self23, _t3, _t4, _t5);
    }

    /** {@code scaleAround_scalar} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] scaleAround_scalar_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        float _self00 = src[srcOffset];
        float _self01 = src[srcOffset + 1];
        float _self02 = src[srcOffset + 2];
        float _self03 = src[srcOffset + 3];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self12 = src[srcOffset + 6];
        float _self13 = src[srcOffset + 7];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 11];
        float _t3 = pivot[pivotOffset] * (1.0f - _sx);
        float _t4 = pivot[pivotOffset + 1] * (1.0f - _sy);
        float _t5 = pivot[pivotOffset + 2] * (1.0f - _sz);
        dest[destOffset] = _sx * _self00;
        dest[destOffset + 1] = _sy * _self01;
        dest[destOffset + 2] = _sz * _self02;
        dest[destOffset + 3] = ((_self00) * (_t3) + (((_self01) * (_t4) + (((_self02) * (_t5) + (_self03))))));
        dest[destOffset + 4] = _sx * _self10;
        dest[destOffset + 5] = _sy * _self11;
        dest[destOffset + 6] = _sz * _self12;
        dest[destOffset + 7] = ((_self10) * (_t3) + (((_self11) * (_t4) + (((_self12) * (_t5) + (_self13))))));
        return scaleAround_scalar_s217ab02b_1_mulAdd(dest, destOffset, _sx, _sy, _sz, _self20, _self21, _self22, _self23, _t3, _t4, _t5);
    }

    /** Piece 2 of {@code scaleAround_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] scaleAround_scalar_s217ab02b_1_fma(float[] dest, int destOffset, float _sx, float _sy, float _sz, float _self20, float _self21, float _self22, float _self23, float _t3, float _t4, float _t5) {
        dest[destOffset + 8] = _sx * _self20;
        dest[destOffset + 9] = _sy * _self21;
        dest[destOffset + 10] = _sz * _self22;
        dest[destOffset + 11] = java.lang.Math.fma(_self20, _t3, java.lang.Math.fma(_self21, _t4, java.lang.Math.fma(_self22, _t5, _self23)));
        return dest;
    }

    /** Piece 2 of {@code scaleAround_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] scaleAround_scalar_s217ab02b_1_mulAdd(float[] dest, int destOffset, float _sx, float _sy, float _sz, float _self20, float _self21, float _self22, float _self23, float _t3, float _t4, float _t5) {
        dest[destOffset + 8] = _sx * _self20;
        dest[destOffset + 9] = _sy * _self21;
        dest[destOffset + 10] = _sz * _self22;
        dest[destOffset + 11] = ((_self20) * (_t3) + (((_self21) * (_t4) + (((_self22) * (_t5) + (_self23))))));
        return dest;
    }

    public static float[] translate_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 4;
                float _eself0 = src[srcOffset + _lo];
                float _eself1 = src[srcOffset + _lo + 1];
                float _eself2 = src[srcOffset + _lo + 2];
                float _eself3 = src[srcOffset + _lo + 3];
                dest[destOffset + _lo] = _eself0;
                dest[destOffset + _lo + 1] = _eself1;
                dest[destOffset + _lo + 2] = _eself2;
                dest[destOffset + _lo + 3] = java.lang.Math.fma(_eself0, vX, java.lang.Math.fma(_eself1, vY, java.lang.Math.fma(_eself2, vZ, _eself3)));
            }
            return dest;
        } else {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 4;
                float _eself0 = src[srcOffset + _lo];
                float _eself1 = src[srcOffset + _lo + 1];
                float _eself2 = src[srcOffset + _lo + 2];
                float _eself3 = src[srcOffset + _lo + 3];
                dest[destOffset + _lo] = _eself0;
                dest[destOffset + _lo + 1] = _eself1;
                dest[destOffset + _lo + 2] = _eself2;
                dest[destOffset + _lo + 3] = ((_eself0) * (vX) + (((_eself1) * (vY) + (((_eself2) * (vZ) + (_eself3))))));
            }
            return dest;
        }
    }

    public static float[] translate_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = v[vOffset];
            float _vy = v[vOffset + 1];
            float _vz = v[vOffset + 2];
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 4;
                float _eself0 = src[srcOffset + _lo];
                float _eself1 = src[srcOffset + _lo + 1];
                float _eself2 = src[srcOffset + _lo + 2];
                float _eself3 = src[srcOffset + _lo + 3];
                dest[destOffset + _lo] = _eself0;
                dest[destOffset + _lo + 1] = _eself1;
                dest[destOffset + _lo + 2] = _eself2;
                dest[destOffset + _lo + 3] = java.lang.Math.fma(_eself0, _vx, java.lang.Math.fma(_eself1, _vy, java.lang.Math.fma(_eself2, _vz, _eself3)));
            }
            return dest;
        } else {
            float _vx = v[vOffset];
            float _vy = v[vOffset + 1];
            float _vz = v[vOffset + 2];
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 4;
                float _eself0 = src[srcOffset + _lo];
                float _eself1 = src[srcOffset + _lo + 1];
                float _eself2 = src[srcOffset + _lo + 2];
                float _eself3 = src[srcOffset + _lo + 3];
                dest[destOffset + _lo] = _eself0;
                dest[destOffset + _lo + 1] = _eself1;
                dest[destOffset + _lo + 2] = _eself2;
                dest[destOffset + _lo + 3] = ((_eself0) * (_vx) + (((_eself1) * (_vy) + (((_eself2) * (_vz) + (_eself3))))));
            }
            return dest;
        }
    }

    public static float[] lerpComposeTRSMul_fma(float[] dest, int destOffset, float[] t1, int t1Offset, float[] t2, int t2Offset, float[] q1, int q1Offset, float[] q2, int q2Offset, float[] s1, int s1Offset, float[] s2, int s2Offset, float[] m, int mOffset, float alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 3;
            int _t2o = t2Offset + _i * 3;
            int _q1o = q1Offset + _i * 4;
            int _q2o = q2Offset + _i * 4;
            int _s1o = s1Offset + _i * 3;
            int _s2o = s2Offset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            float _ax = t1[_t1o], _ay = t1[_t1o + 1], _az = t1[_t1o + 2];
            float _tx = java.lang.Math.fma(alpha, t2[_t2o] - _ax, _ax);
            float _ty = java.lang.Math.fma(alpha, t2[_t2o + 1] - _ay, _ay);
            float _tz = java.lang.Math.fma(alpha, t2[_t2o + 2] - _az, _az);
            float _bx = s1[_s1o], _by = s1[_s1o + 1], _bz = s1[_s1o + 2];
            float _sx = java.lang.Math.fma(alpha, s2[_s2o] - _bx, _bx);
            float _sy = java.lang.Math.fma(alpha, s2[_s2o + 1] - _by, _by);
            float _sz = java.lang.Math.fma(alpha, s2[_s2o + 2] - _bz, _bz);
            float _ux = q1[_q1o], _uy = q1[_q1o + 1], _uz = q1[_q1o + 2], _uw = q1[_q1o + 3];
            float _vx = q2[_q2o], _vy = q2[_q2o + 1], _vz = q2[_q2o + 2], _vw = q2[_q2o + 3];
            float _dot = java.lang.Math.fma(_uw, _vw, java.lang.Math.fma(_uz, _vz, java.lang.Math.fma(_ux, _vx, _uy * _vy)));
            float _wx = _dot < 0.0f ? -_vx : _vx, _wy = _dot < 0.0f ? -_vy : _vy, _wz = _dot < 0.0f ? -_vz : _vz, _ww = _dot < 0.0f ? -_vw : _vw;
            float _rx = java.lang.Math.fma(alpha, _wx - _ux, _ux);
            float _ry = java.lang.Math.fma(alpha, _wy - _uy, _uy);
            float _rz = java.lang.Math.fma(alpha, _wz - _uz, _uz);
            float _rw = java.lang.Math.fma(alpha, _ww - _uw, _uw);
            float _len2 = (_rx * _rx + _ry * _ry) + (_rz * _rz + _rw * _rw);
            float _ninv = _len2 > 0.0f ? 1.0f / (float) java.lang.Math.sqrt(_len2) : 0.0f;
            float _qx = _rx * _ninv, _qy = _ry * _ninv, _qz = _rz * _ninv, _qw = _rw * _ninv;
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            float _m00 = m[_mo], _m01 = m[_mo + 1], _m02 = m[_mo + 2], _m03 = m[_mo + 3];
            float _m10 = m[_mo + 4], _m11 = m[_mo + 5], _m12 = m[_mo + 6], _m13 = m[_mo + 7];
            float _m20 = m[_mo + 8], _m21 = m[_mo + 9], _m22 = m[_mo + 10], _m23 = m[_mo + 11];
            float _e00 = java.lang.Math.fma(_t02, _m20, java.lang.Math.fma(_t01, _m10, _t00 * _m00)), _e01 = java.lang.Math.fma(_t02, _m21, java.lang.Math.fma(_t01, _m11, _t00 * _m01)), _e02 = java.lang.Math.fma(_t02, _m22, java.lang.Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (java.lang.Math.fma(_t02, _m23, java.lang.Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            float _e10 = java.lang.Math.fma(_t12, _m20, java.lang.Math.fma(_t11, _m10, _t10 * _m00)), _e11 = java.lang.Math.fma(_t12, _m21, java.lang.Math.fma(_t11, _m11, _t10 * _m01)), _e12 = java.lang.Math.fma(_t12, _m22, java.lang.Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (java.lang.Math.fma(_t12, _m23, java.lang.Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            float _e20 = java.lang.Math.fma(_t22, _m20, java.lang.Math.fma(_t21, _m10, _t20 * _m00)), _e21 = java.lang.Math.fma(_t22, _m21, java.lang.Math.fma(_t21, _m11, _t20 * _m01)), _e22 = java.lang.Math.fma(_t22, _m22, java.lang.Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (java.lang.Math.fma(_t22, _m23, java.lang.Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest[_do] = _e00;
            dest[_do + 1] = _e01;
            dest[_do + 2] = _e02;
            dest[_do + 3] = _e03;
            dest[_do + 4] = _e10;
            dest[_do + 5] = _e11;
            dest[_do + 6] = _e12;
            dest[_do + 7] = _e13;
            dest[_do + 8] = _e20;
            dest[_do + 9] = _e21;
            dest[_do + 10] = _e22;
            dest[_do + 11] = _e23;
        }
        return dest;
    }

    public static float[] lerpComposeTRSMul_mulAdd(float[] dest, int destOffset, float[] t1, int t1Offset, float[] t2, int t2Offset, float[] q1, int q1Offset, float[] q2, int q2Offset, float[] s1, int s1Offset, float[] s2, int s2Offset, float[] m, int mOffset, float alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 3;
            int _t2o = t2Offset + _i * 3;
            int _q1o = q1Offset + _i * 4;
            int _q2o = q2Offset + _i * 4;
            int _s1o = s1Offset + _i * 3;
            int _s2o = s2Offset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            float _ax = t1[_t1o], _ay = t1[_t1o + 1], _az = t1[_t1o + 2];
            float _bx = s1[_s1o], _by = s1[_s1o + 1], _bz = s1[_s1o + 2];
            float _sx = alpha * (s2[_s2o] - _bx) + _bx;
            float _sy = alpha * (s2[_s2o + 1] - _by) + _by;
            float _sz = alpha * (s2[_s2o + 2] - _bz) + _bz;
            float _ux = q1[_q1o], _uy = q1[_q1o + 1], _uz = q1[_q1o + 2], _uw = q1[_q1o + 3];
            float _vx = q2[_q2o], _vy = q2[_q2o + 1], _vz = q2[_q2o + 2], _vw = q2[_q2o + 3];
            float _dot = _uw * _vw + (_uz * _vz + (_ux * _vx + (_uy * _vy)));
            float _wx = _dot < 0.0f ? -_vx : _vx, _wy = _dot < 0.0f ? -_vy : _vy, _wz = _dot < 0.0f ? -_vz : _vz, _ww = _dot < 0.0f ? -_vw : _vw;
            float _rx = alpha * (_wx - _ux) + _ux;
            float _ry = alpha * (_wy - _uy) + _uy;
            float _rz = alpha * (_wz - _uz) + _uz;
            float _rw = alpha * (_ww - _uw) + _uw;
            float _len2 = (_rx * _rx + _ry * _ry) + (_rz * _rz + _rw * _rw);
            float _ninv = _len2 > 0.0f ? 1.0f / (float) java.lang.Math.sqrt(_len2) : 0.0f;
            float _qx = _rx * _ninv, _qy = _ry * _ninv, _qz = _rz * _ninv, _qw = _rw * _ninv;
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            float _m00 = m[_mo], _m01 = m[_mo + 1], _m02 = m[_mo + 2], _m03 = m[_mo + 3];
            float _m10 = m[_mo + 4], _m11 = m[_mo + 5], _m12 = m[_mo + 6], _m13 = m[_mo + 7];
            float _m20 = m[_mo + 8], _m21 = m[_mo + 9], _m22 = m[_mo + 10], _m23 = m[_mo + 11];
            float _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + (alpha * (t2[_t2o] - _ax) + _ax);
            float _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + (alpha * (t2[_t2o + 1] - _ay) + _ay);
            float _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + (alpha * (t2[_t2o + 2] - _az) + _az);
            dest[_do] = _e00;
            dest[_do + 1] = _e01;
            dest[_do + 2] = _e02;
            dest[_do + 3] = _e03;
            dest[_do + 4] = _e10;
            dest[_do + 5] = _e11;
            dest[_do + 6] = _e12;
            dest[_do + 7] = _e13;
            dest[_do + 8] = _e20;
            dest[_do + 9] = _e21;
            dest[_do + 10] = _e22;
            dest[_do + 11] = _e23;
        }
        return dest;
    }

    public static float[] composeTRSMul_fma(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 3;
            int _rotationo = rotationOffset + _i * 4;
            int _scaleo = scaleOffset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            float _tx = translation[_translationo], _ty = translation[_translationo + 1], _tz = translation[_translationo + 2];
            float _sx = scale[_scaleo], _sy = scale[_scaleo + 1], _sz = scale[_scaleo + 2];
            float _qx = rotation[_rotationo], _qy = rotation[_rotationo + 1], _qz = rotation[_rotationo + 2], _qw = rotation[_rotationo + 3];
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            float _m00 = m[_mo], _m01 = m[_mo + 1], _m02 = m[_mo + 2], _m03 = m[_mo + 3];
            float _m10 = m[_mo + 4], _m11 = m[_mo + 5], _m12 = m[_mo + 6], _m13 = m[_mo + 7];
            float _m20 = m[_mo + 8], _m21 = m[_mo + 9], _m22 = m[_mo + 10], _m23 = m[_mo + 11];
            float _e00 = java.lang.Math.fma(_t02, _m20, java.lang.Math.fma(_t01, _m10, _t00 * _m00)), _e01 = java.lang.Math.fma(_t02, _m21, java.lang.Math.fma(_t01, _m11, _t00 * _m01)), _e02 = java.lang.Math.fma(_t02, _m22, java.lang.Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (java.lang.Math.fma(_t02, _m23, java.lang.Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            float _e10 = java.lang.Math.fma(_t12, _m20, java.lang.Math.fma(_t11, _m10, _t10 * _m00)), _e11 = java.lang.Math.fma(_t12, _m21, java.lang.Math.fma(_t11, _m11, _t10 * _m01)), _e12 = java.lang.Math.fma(_t12, _m22, java.lang.Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (java.lang.Math.fma(_t12, _m23, java.lang.Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            float _e20 = java.lang.Math.fma(_t22, _m20, java.lang.Math.fma(_t21, _m10, _t20 * _m00)), _e21 = java.lang.Math.fma(_t22, _m21, java.lang.Math.fma(_t21, _m11, _t20 * _m01)), _e22 = java.lang.Math.fma(_t22, _m22, java.lang.Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (java.lang.Math.fma(_t22, _m23, java.lang.Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest[_do] = _e00;
            dest[_do + 1] = _e01;
            dest[_do + 2] = _e02;
            dest[_do + 3] = _e03;
            dest[_do + 4] = _e10;
            dest[_do + 5] = _e11;
            dest[_do + 6] = _e12;
            dest[_do + 7] = _e13;
            dest[_do + 8] = _e20;
            dest[_do + 9] = _e21;
            dest[_do + 10] = _e22;
            dest[_do + 11] = _e23;
        }
        return dest;
    }

    public static float[] composeTRSMul_mulAdd(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 3;
            int _rotationo = rotationOffset + _i * 4;
            int _scaleo = scaleOffset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            float _tx = translation[_translationo], _ty = translation[_translationo + 1], _tz = translation[_translationo + 2];
            float _sx = scale[_scaleo], _sy = scale[_scaleo + 1], _sz = scale[_scaleo + 2];
            float _qx = rotation[_rotationo], _qy = rotation[_rotationo + 1], _qz = rotation[_rotationo + 2], _qw = rotation[_rotationo + 3];
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            float _m00 = m[_mo], _m01 = m[_mo + 1], _m02 = m[_mo + 2], _m03 = m[_mo + 3];
            float _m10 = m[_mo + 4], _m11 = m[_mo + 5], _m12 = m[_mo + 6], _m13 = m[_mo + 7];
            float _m20 = m[_mo + 8], _m21 = m[_mo + 9], _m22 = m[_mo + 10], _m23 = m[_mo + 11];
            float _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
            float _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
            float _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
            dest[_do] = _e00;
            dest[_do + 1] = _e01;
            dest[_do + 2] = _e02;
            dest[_do + 3] = _e03;
            dest[_do + 4] = _e10;
            dest[_do + 5] = _e11;
            dest[_do + 6] = _e12;
            dest[_do + 7] = _e13;
            dest[_do + 8] = _e20;
            dest[_do + 9] = _e21;
            dest[_do + 10] = _e22;
            dest[_do + 11] = _e23;
        }
        return dest;
    }

    public static float[] composeTRSMulPadded_fma(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        float _qx = rotation[rotationOffset], _qy = rotation[rotationOffset + 1], _qz = rotation[rotationOffset + 2], _qw = rotation[rotationOffset + 3];
        float _tx = translation[translationOffset], _ty = translation[translationOffset + 1], _tz = translation[translationOffset + 2];
        float _sx = scale[scaleOffset], _sy = scale[scaleOffset + 1], _sz = scale[scaleOffset + 2];
        float _m00 = m[mOffset], _m01 = m[mOffset + 1], _m02 = m[mOffset + 2], _m03 = m[mOffset + 3];
        float _m10 = m[mOffset + 4], _m11 = m[mOffset + 5], _m12 = m[mOffset + 6], _m13 = m[mOffset + 7];
        float _m20 = m[mOffset + 8], _m21 = m[mOffset + 9], _m22 = m[mOffset + 10], _m23 = m[mOffset + 11];
        float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        return composeTRSMulPadded_fma_s5c5ac1c3_1(dest, destOffset, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _xx, _yy, _zz);
    }

    /** Piece 2 of {@code composeTRSMulPadded_fma}, split to fit the inline budget; reached only through it. */
    private static float[] composeTRSMulPadded_fma_s5c5ac1c3_1(float[] dest, int destOffset, float _qx, float _qy, float _qz, float _qw, float _tx, float _ty, float _tz, float _sx, float _sy, float _sz, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _xx, float _yy, float _zz) {
        float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        float _e00 = java.lang.Math.fma(_t02, _m20, java.lang.Math.fma(_t01, _m10, _t00 * _m00)), _e01 = java.lang.Math.fma(_t02, _m21, java.lang.Math.fma(_t01, _m11, _t00 * _m01)), _e02 = java.lang.Math.fma(_t02, _m22, java.lang.Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (java.lang.Math.fma(_t02, _m23, java.lang.Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
        return composeTRSMulPadded_fma_s5c5ac1c3_2(dest, destOffset, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t10, _t11, _t12, _t20, _t21, _t22, _e00, _e01, _e02, _e03);
    }

    /** Piece 3 of {@code composeTRSMulPadded_fma}, split to fit the inline budget; reached only through it. */
    private static float[] composeTRSMulPadded_fma_s5c5ac1c3_2(float[] dest, int destOffset, float _ty, float _tz, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t10, float _t11, float _t12, float _t20, float _t21, float _t22, float _e00, float _e01, float _e02, float _e03) {
        float _e10 = java.lang.Math.fma(_t12, _m20, java.lang.Math.fma(_t11, _m10, _t10 * _m00)), _e11 = java.lang.Math.fma(_t12, _m21, java.lang.Math.fma(_t11, _m11, _t10 * _m01)), _e12 = java.lang.Math.fma(_t12, _m22, java.lang.Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (java.lang.Math.fma(_t12, _m23, java.lang.Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
        float _e20 = java.lang.Math.fma(_t22, _m20, java.lang.Math.fma(_t21, _m10, _t20 * _m00)), _e21 = java.lang.Math.fma(_t22, _m21, java.lang.Math.fma(_t21, _m11, _t20 * _m01)), _e22 = java.lang.Math.fma(_t22, _m22, java.lang.Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (java.lang.Math.fma(_t22, _m23, java.lang.Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
        dest[destOffset] = _e00;
        dest[destOffset + 1] = _e01;
        dest[destOffset + 2] = _e02;
        dest[destOffset + 3] = _e03;
        dest[destOffset + 4] = _e10;
        dest[destOffset + 5] = _e11;
        dest[destOffset + 6] = _e12;
        dest[destOffset + 7] = _e13;
        dest[destOffset + 8] = _e20;
        dest[destOffset + 9] = _e21;
        dest[destOffset + 10] = _e22;
        dest[destOffset + 11] = _e23;
        return dest;
    }

    public static float[] composeTRSMulPadded_mulAdd(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        float _qx = rotation[rotationOffset], _qy = rotation[rotationOffset + 1], _qz = rotation[rotationOffset + 2], _qw = rotation[rotationOffset + 3];
        float _tx = translation[translationOffset], _ty = translation[translationOffset + 1], _tz = translation[translationOffset + 2];
        float _sx = scale[scaleOffset], _sy = scale[scaleOffset + 1], _sz = scale[scaleOffset + 2];
        float _m00 = m[mOffset], _m01 = m[mOffset + 1], _m02 = m[mOffset + 2], _m03 = m[mOffset + 3];
        float _m10 = m[mOffset + 4], _m11 = m[mOffset + 5], _m12 = m[mOffset + 6], _m13 = m[mOffset + 7];
        float _m20 = m[mOffset + 8], _m21 = m[mOffset + 9], _m22 = m[mOffset + 10], _m23 = m[mOffset + 11];
        float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        return composeTRSMulPadded_mulAdd_s43d821be_1(dest, destOffset, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _xx, _yy, _zz);
    }

    /** Piece 2 of {@code composeTRSMulPadded_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] composeTRSMulPadded_mulAdd_s43d821be_1(float[] dest, int destOffset, float _qx, float _qy, float _qz, float _qw, float _tx, float _ty, float _tz, float _sx, float _sy, float _sz, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _xx, float _yy, float _zz) {
        float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        float _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
        return composeTRSMulPadded_mulAdd_s43d821be_2(dest, destOffset, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t10, _t11, _t12, _t20, _t21, _t22, _e00, _e01, _e02, _e03);
    }

    /** Piece 3 of {@code composeTRSMulPadded_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] composeTRSMulPadded_mulAdd_s43d821be_2(float[] dest, int destOffset, float _ty, float _tz, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t10, float _t11, float _t12, float _t20, float _t21, float _t22, float _e00, float _e01, float _e02, float _e03) {
        float _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
        float _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
        dest[destOffset] = _e00;
        dest[destOffset + 1] = _e01;
        dest[destOffset + 2] = _e02;
        dest[destOffset + 3] = _e03;
        dest[destOffset + 4] = _e10;
        dest[destOffset + 5] = _e11;
        dest[destOffset + 6] = _e12;
        dest[destOffset + 7] = _e13;
        dest[destOffset + 8] = _e20;
        dest[destOffset + 9] = _e21;
        dest[destOffset + 10] = _e22;
        dest[destOffset + 11] = _e23;
        return dest;
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
