// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

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

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return lookAlong_lh_s9859a898_1_fma(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return lookAlong_lh_s9859a898_1_mulAdd(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s9859a898_1_fma(float[] dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        return lookAlong_lh_s9859a898_2_fma(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s9859a898_1_mulAdd(float[] dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        return lookAlong_lh_s9859a898_2_mulAdd(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_s9859a898_2_fma(float[] dest, int destOffset, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_s9859a898_2_mulAdd(float[] dest, int destOffset, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 8] = ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46))));
        dest[destOffset + 9] = ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47))));
        dest[destOffset + 10] = ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return lookAlong_rh_sb1cf8892_1_fma(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return lookAlong_rh_sb1cf8892_1_mulAdd(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_sb1cf8892_1_fma(float[] dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_sb1cf8892_2_fma(dest, destOffset, _self20, _self21, _self23, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_sb1cf8892_1_mulAdd(float[] dest, int destOffset, float upX, float upY, float upZ, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_sb1cf8892_2_mulAdd(dest, destOffset, _self20, _self21, _self23, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_sb1cf8892_2_fma(float[] dest, int destOffset, float _self20, float _self21, float _self23, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42, float _t49, float _t50, float _t51) {
        dest[destOffset + 8] = java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49));
        dest[destOffset + 9] = java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50));
        dest[destOffset + 10] = java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_sb1cf8892_2_mulAdd(float[] dest, int destOffset, float _self20, float _self21, float _self23, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42, float _t49, float _t50, float _t51) {
        dest[destOffset + 8] = ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49))));
        dest[destOffset + 9] = ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50))));
        dest[destOffset + 10] = ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
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
        return lookAlong_lh_seca95b12_1_fma(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t11, -(_t18 * _t12)));
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
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
        return lookAlong_lh_seca95b12_1_mulAdd(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t11)), ((_t19) * (_t12) - (_t20 * _t13)), ((_t20) * (_t11) - (_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_seca95b12_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        return lookAlong_lh_seca95b12_2_fma(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_seca95b12_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        return lookAlong_lh_seca95b12_2_mulAdd(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_seca95b12_2_fma(float[] dest, int destOffset, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 6] = java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46));
        dest[destOffset + 9] = java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47));
        dest[destOffset + 10] = java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_lh_seca95b12_2_mulAdd(float[] dest, int destOffset, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        dest[destOffset + 6] = ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48))));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46))));
        dest[destOffset + 9] = ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47))));
        dest[destOffset + 10] = ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48))));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
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
        return lookAlong_rh_s79d54354_1_fma(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t21, _t23, java.lang.Math.fma(_t21, _t14, -(_t22 * _t16)), java.lang.Math.fma(_t22, _t15, -(_t23 * _t14)));
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
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
        return lookAlong_rh_s79d54354_1_mulAdd(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t21, _t23, ((_t21) * (_t14) - (_t22 * _t16)), ((_t22) * (_t15) - (_t23 * _t14)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s79d54354_1_fma(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
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
        return lookAlong_rh_s79d54354_2_fma(dest, destOffset, _self10, _self11, _self13, _self20, _self21, _self23, _t1, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s79d54354_1_mulAdd(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
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
        return lookAlong_rh_s79d54354_2_mulAdd(dest, destOffset, _self10, _self11, _self13, _self20, _self21, _self23, _t1, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_s79d54354_2_fma(float[] dest, int destOffset, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42, float _t49, float _t50, float _t51) {
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
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static float[] lookAlong_rh_s79d54354_2_mulAdd(float[] dest, int destOffset, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42, float _t49, float _t50, float _t51) {
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return lookAlong_lh_s3fad34cc_1_fma(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return lookAlong_lh_s3fad34cc_1_mulAdd(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s3fad34cc_1_fma(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        return lookAlong_lh_s9859a898_2_fma(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s3fad34cc_1_mulAdd(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        return lookAlong_lh_s9859a898_2_mulAdd(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return lookAlong_rh_s193180e_1_fma(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return lookAlong_rh_s193180e_1_mulAdd(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s193180e_1_fma(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_sb1cf8892_2_fma(dest, destOffset, _self20, _self21, _self23, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s193180e_1_mulAdd(float[] dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_sb1cf8892_2_mulAdd(dest, destOffset, _self20, _self21, _self23, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
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
        return lookAlong_lh_s19a36b8e_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t11, -(_t18 * _t12)));
    }

    /** {@code lookAlong_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
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
        return lookAlong_lh_s19a36b8e_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t11)), ((_t19) * (_t12) - (_t20 * _t13)), ((_t20) * (_t11) - (_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s19a36b8e_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        return lookAlong_lh_seca95b12_2_fma(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_s19a36b8e_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        return lookAlong_lh_seca95b12_2_mulAdd(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
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
        return lookAlong_rh_s67750d98_1_fma(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t21, _t23, java.lang.Math.fma(_t21, _t14, -(_t22 * _t16)), java.lang.Math.fma(_t22, _t15, -(_t23 * _t14)));
    }

    /** {@code lookAlong_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
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
        return lookAlong_rh_s67750d98_1_mulAdd(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 11], -_self02, -_self12, -_self22, _t14, _t15, _t16, _t21, _t23, ((_t21) * (_t14) - (_t22 * _t16)), ((_t22) * (_t15) - (_t23 * _t14)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s67750d98_1_fma(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
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
        return lookAlong_rh_s79d54354_2_fma(dest, destOffset, _self10, _self11, _self13, _self20, _self21, _self23, _t1, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_s67750d98_1_mulAdd(float[] dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self01, float _self03, float _self10, float _self11, float _self13, float _self20, float _self21, float _self23, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
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
        return lookAlong_rh_s79d54354_2_mulAdd(dest, destOffset, _self10, _self11, _self13, _self20, _self21, _self23, _t1, _t2, _t14, _t15, _t16, _t40, _t41, _t42, _t49, _t50, _t51);
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    /** {@code lookAt_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
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
        return lookAt_lh_s4f06fa92_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, java.lang.Math.fma(eyeZ, _t15, java.lang.Math.fma(eyeX, _t14, eyeY * _t16)), java.lang.Math.fma(_t24, _t16, -(_t25 * _t14)), java.lang.Math.fma(_t25, _t15, -(_t26 * _t16)), java.lang.Math.fma(_t26, _t14, -(_t24 * _t15)));
    }

    /** {@code lookAt_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
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
        return lookAt_lh_s4f06fa92_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, ((eyeZ) * (_t15) + (((eyeX) * (_t14) + (eyeY * _t16)))), ((_t24) * (_t16) - (_t25 * _t14)), ((_t25) * (_t15) - (_t26 * _t16)), ((_t26) * (_t14) - (_t24 * _t15)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_s4f06fa92_1_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t33, float _t34, float _t35) {
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
        return lookAt_lh_s4f06fa92_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(eyeZ, _t44, java.lang.Math.fma(eyeX, _t43, eyeY * _t45)), java.lang.Math.fma(eyeZ, _t56, java.lang.Math.fma(eyeX, _t54, eyeY * _t55)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_s4f06fa92_1_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t33, float _t34, float _t35) {
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
        return lookAt_lh_s4f06fa92_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, ((eyeZ) * (_t44) + (((eyeX) * (_t43) + (eyeY * _t45)))), ((eyeZ) * (_t56) + (((eyeX) * (_t54) + (eyeY * _t55)))));
    }

    /**
     * Piece 3 of {@code lookAt_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_lh_s4f06fa92_2_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
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
     * Piece 3 of {@code lookAt_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_lh_s4f06fa92_2_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    /** {@code lookAt_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
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
        return lookAt_rh_s943596e4_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], -_self02, -_self12, -_self22, _t17, _t18, _t19, java.lang.Math.fma(eyeZ, _t18, java.lang.Math.fma(eyeX, _t17, eyeY * _t19)), _t27, _t29, java.lang.Math.fma(_t27, _t17, -(_t28 * _t19)), java.lang.Math.fma(_t28, _t18, -(_t29 * _t17)));
    }

    /** {@code lookAt_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
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
        return lookAt_rh_s943596e4_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], -_self02, -_self12, -_self22, _t17, _t18, _t19, ((eyeZ) * (_t18) + (((eyeX) * (_t17) + (eyeY * _t19)))), _t27, _t29, ((_t27) * (_t17) - (_t28 * _t19)), ((_t28) * (_t18) - (_t29 * _t17)));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s943596e4_1_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t27, float _t29, float _t36, float _t37) {
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
        return lookAt_rh_s943596e4_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(eyeZ, _t48, java.lang.Math.fma(eyeX, _t46, eyeY * _t47)), java.lang.Math.fma(eyeZ, _t59, java.lang.Math.fma(eyeX, _t57, eyeY * _t58)));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s943596e4_1_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t27, float _t29, float _t36, float _t37) {
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
        return lookAt_rh_s943596e4_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, ((eyeZ) * (_t48) + (((eyeX) * (_t46) + (eyeY * _t47)))), ((eyeZ) * (_t59) + (((eyeX) * (_t57) + (eyeY * _t58)))));
    }

    /**
     * Piece 3 of {@code lookAt_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_rh_s943596e4_2_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
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
     * Piece 3 of {@code lookAt_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_rh_s943596e4_2_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    /** {@code lookAt_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
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
        return lookAt_lh_s8ed8f70_1_fma(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, java.lang.Math.fma(_eyez, _t15, java.lang.Math.fma(_eyex, _t14, _eyey * _t16)), _t23, java.lang.Math.fma(_t23, _t14, _upx));
    }

    /** {@code lookAt_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
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
        return lookAt_lh_s8ed8f70_1_mulAdd(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, ((_eyez) * (_t15) + (((_eyex) * (_t14) + (_eyey * _t16)))), _t23, ((_t23) * (_t14) + (_upx)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_s8ed8f70_1_fma(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t23, float _t24) {
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
        return lookAt_lh_s8ed8f70_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(_eyez, _t44, java.lang.Math.fma(_eyex, _t43, _eyey * _t45)), java.lang.Math.fma(_eyez, _t56, java.lang.Math.fma(_eyex, _t54, _eyey * _t55)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_s8ed8f70_1_mulAdd(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t23, float _t24) {
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
        return lookAt_lh_s8ed8f70_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, ((_eyez) * (_t44) + (((_eyex) * (_t43) + (_eyey * _t45)))), ((_eyez) * (_t56) + (((_eyex) * (_t54) + (_eyey * _t55)))));
    }

    /**
     * Piece 3 of {@code lookAt_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_lh_s8ed8f70_2_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
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
     * Piece 3 of {@code lookAt_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_lh_s8ed8f70_2_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    /** {@code lookAt_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
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
        return lookAt_rh_s6349a11a_2_fma(dest, destOffset, _eyex, _eyey, _eyez, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t17, _t18, _t19, _upx, _upy, _upz, java.lang.Math.fma(_t26, _t19, _upy), java.lang.Math.fma(_t26, _t17, _upx), java.lang.Math.fma(_t26, _t18, _upz));
    }

    /** {@code lookAt_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
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
        return lookAt_rh_s6349a11a_2_mulAdd(dest, destOffset, _eyex, _eyey, _eyez, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t17, _t18, _t19, _upx, _upy, _upz, ((_t26) * (_t19) + (_upy)), ((_t26) * (_t17) + (_upx)), ((_t26) * (_t18) + (_upz)));
    }

    /**
     * Part 1 of {@code lookAt_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_rh_s6349a11a_1_fma(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t19, float _t18, float _t25, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
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
     * Part 1 of {@code lookAt_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static float[] lookAt_rh_s6349a11a_1_mulAdd(float[] dest, int destOffset, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t19, float _t18, float _t25, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
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

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s6349a11a_2_fma(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t17, float _t18, float _t19, float _upx, float _upy, float _upz, float _t27, float _t28, float _t29) {
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
        return lookAt_rh_s6349a11a_1_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, (-_self02), (-_self12), (-_self22), _t17, _t19, _t18, java.lang.Math.fma(_eyez, _t18, java.lang.Math.fma(_eyex, _t17, _eyey * _t19)), _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(_eyez, _t48, java.lang.Math.fma(_eyex, _t46, _eyey * _t47)), java.lang.Math.fma(_eyez, _t59, java.lang.Math.fma(_eyex, _t57, _eyey * _t58)));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s6349a11a_2_mulAdd(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t17, float _t18, float _t19, float _upx, float _upy, float _upz, float _t27, float _t28, float _t29) {
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
        return lookAt_rh_s6349a11a_1_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, (-_self02), (-_self12), (-_self22), _t17, _t19, _t18, ((_eyez) * (_t18) + (((_eyex) * (_t17) + (_eyey * _t19)))), _t46, _t47, _t48, _t57, _t58, _t59, ((_eyez) * (_t48) + (((_eyex) * (_t46) + (_eyey * _t47)))), ((_eyez) * (_t59) + (((_eyex) * (_t57) + (_eyey * _t58)))));
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAt_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
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
        return lookAt_lh_sf3fa9e0e_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, java.lang.Math.fma(eyeZ, _t15, java.lang.Math.fma(eyeX, _t14, eyeY * _t16)), java.lang.Math.fma(_t24, _t16, -(_t25 * _t14)), java.lang.Math.fma(_t25, _t15, -(_t26 * _t16)), java.lang.Math.fma(_t26, _t14, -(_t24 * _t15)));
    }

    /** {@code lookAt_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
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
        return lookAt_lh_sf3fa9e0e_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, ((eyeZ) * (_t15) + (((eyeX) * (_t14) + (eyeY * _t16)))), ((_t24) * (_t16) - (_t25 * _t14)), ((_t25) * (_t15) - (_t26 * _t16)), ((_t26) * (_t14) - (_t24 * _t15)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_sf3fa9e0e_1_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t33, float _t34, float _t35) {
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
        return lookAt_lh_s4f06fa92_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(eyeZ, _t44, java.lang.Math.fma(eyeX, _t43, eyeY * _t45)), java.lang.Math.fma(eyeZ, _t56, java.lang.Math.fma(eyeX, _t54, eyeY * _t55)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_sf3fa9e0e_1_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t33, float _t34, float _t35) {
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
        return lookAt_lh_s4f06fa92_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, ((eyeZ) * (_t44) + (((eyeX) * (_t43) + (eyeY * _t45)))), ((eyeZ) * (_t56) + (((eyeX) * (_t54) + (eyeY * _t55)))));
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAt_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
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
        return lookAt_rh_s95d8eb28_1_fma(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], -_self02, -_self12, -_self22, _t17, _t18, _t19, java.lang.Math.fma(eyeZ, _t18, java.lang.Math.fma(eyeX, _t17, eyeY * _t19)), _t27, _t29, java.lang.Math.fma(_t27, _t17, -(_t28 * _t19)), java.lang.Math.fma(_t28, _t18, -(_t29 * _t17)));
    }

    /** {@code lookAt_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
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
        return lookAt_rh_s95d8eb28_1_mulAdd(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], -_self02, -_self12, -_self22, _t17, _t18, _t19, ((eyeZ) * (_t18) + (((eyeX) * (_t17) + (eyeY * _t19)))), _t27, _t29, ((_t27) * (_t17) - (_t28 * _t19)), ((_t28) * (_t18) - (_t29 * _t17)));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s95d8eb28_1_fma(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t27, float _t29, float _t36, float _t37) {
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
        return lookAt_rh_s943596e4_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(eyeZ, _t48, java.lang.Math.fma(eyeX, _t46, eyeY * _t47)), java.lang.Math.fma(eyeZ, _t59, java.lang.Math.fma(eyeX, _t57, eyeY * _t58)));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s95d8eb28_1_mulAdd(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t27, float _t29, float _t36, float _t37) {
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
        return lookAt_rh_s943596e4_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, ((eyeZ) * (_t48) + (((eyeX) * (_t46) + (eyeY * _t47)))), ((eyeZ) * (_t59) + (((eyeX) * (_t57) + (eyeY * _t58)))));
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
    }

    /** {@code lookAt_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
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
        return lookAt_lh_se6ed9ac4_1_fma(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, java.lang.Math.fma(_eyez, _t15, java.lang.Math.fma(_eyex, _t14, _eyey * _t16)), _t23, java.lang.Math.fma(_t23, _t14, _upx));
    }

    /** {@code lookAt_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
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
        return lookAt_lh_se6ed9ac4_1_mulAdd(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, ((_eyez) * (_t15) + (((_eyex) * (_t14) + (_eyey * _t16)))), _t23, ((_t23) * (_t14) + (_upx)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_se6ed9ac4_1_fma(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t23, float _t24) {
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
        return lookAt_lh_s8ed8f70_2_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, java.lang.Math.fma(_eyez, _t44, java.lang.Math.fma(_eyex, _t43, _eyey * _t45)), java.lang.Math.fma(_eyez, _t56, java.lang.Math.fma(_eyex, _t54, _eyey * _t55)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_se6ed9ac4_1_mulAdd(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t23, float _t24) {
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
        return lookAt_lh_s8ed8f70_2_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, ((_eyez) * (_t44) + (((_eyex) * (_t43) + (_eyey * _t45)))), ((_eyez) * (_t56) + (((_eyex) * (_t54) + (_eyey * _t55)))));
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
    }

    /** {@code lookAt_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
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
        return lookAt_rh_s9d10c996_2_fma(dest, destOffset, epsilon, _eyex, _eyey, _eyez, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t17, _t18, _t19, _upx, _upy, _upz, java.lang.Math.fma(_t26, _t19, _upy), java.lang.Math.fma(_t26, _t17, _upx), java.lang.Math.fma(_t26, _t18, _upz));
    }

    /** {@code lookAt_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
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
        return lookAt_rh_s9d10c996_2_mulAdd(dest, destOffset, epsilon, _eyex, _eyey, _eyez, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t17, _t18, _t19, _upx, _upy, _upz, ((_t26) * (_t19) + (_upy)), ((_t26) * (_t17) + (_upx)), ((_t26) * (_t18) + (_upz)));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s9d10c996_2_fma(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t17, float _t18, float _t19, float _upx, float _upy, float _upz, float _t27, float _t28, float _t29) {
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
        return lookAt_rh_s6349a11a_1_fma(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, (-_self02), (-_self12), (-_self22), _t17, _t19, _t18, java.lang.Math.fma(_eyez, _t18, java.lang.Math.fma(_eyex, _t17, _eyey * _t19)), _t46, _t47, _t48, _t57, _t58, _t59, java.lang.Math.fma(_eyez, _t48, java.lang.Math.fma(_eyex, _t46, _eyey * _t47)), java.lang.Math.fma(_eyez, _t59, java.lang.Math.fma(_eyex, _t57, _eyey * _t58)));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s9d10c996_2_mulAdd(float[] dest, int destOffset, float epsilon, float _eyex, float _eyey, float _eyez, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t17, float _t18, float _t19, float _upx, float _upy, float _upz, float _t27, float _t28, float _t29) {
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
        return lookAt_rh_s6349a11a_1_mulAdd(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, (-_self02), (-_self12), (-_self22), _t17, _t19, _t18, ((_eyez) * (_t18) + (((_eyex) * (_t17) + (_eyey * _t19)))), _t46, _t47, _t48, _t57, _t58, _t59, ((_eyez) * (_t48) + (((_eyex) * (_t46) + (_eyey * _t47)))), ((_eyez) * (_t59) + (((_eyex) * (_t57) + (_eyey * _t58)))));
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
