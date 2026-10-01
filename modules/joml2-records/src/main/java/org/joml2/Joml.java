// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.unsafe.UnsafeOpsHolder;

/**
 * Factory for every generated math record ({@code Joml.float4x4()},
 * {@code Joml.float3(1, 2, 3)}, ...) plus the library's global flags and the
 * matrix property bit masks.
 */
public final class Joml {
    private Joml() {}

    // Freeze the JomlConfig overrides before any flag below reads one.
    static {
        JomlConfig.freeze();
    }

    /** Property bit mask: the matrix is known to be affine (last row {@code 0..0 1}). */
    public static final int BIT_AFFINE      = 1;
    /** Property bit mask: the matrix is known to be orthogonal, i.e. its upper-left block is orthonormal with positive determinant (a proper rotation; a reflection is affine, not orthogonal). Includes {@link #BIT_AFFINE}. */
    public static final int BIT_ORTHOGONAL  = 3;
    /** Property bit mask: the matrix is known to be a pure translation. Includes {@link #BIT_ORTHOGONAL} (a pure translation is rigid). */
    public static final int BIT_TRANSLATION = 7;
    /** Property bit mask: the matrix is known to be the identity. Includes all weaker masks' bits. */
    public static final int BIT_IDENTITY    = 15;

    /** The single bit that distinguishes {@link #BIT_AFFINE} from no known properties. */
    public static final int UNIQUE_AFFINE      = 1;
    /** The single bit that distinguishes {@link #BIT_ORTHOGONAL} from the next-weaker mask. */
    public static final int UNIQUE_ORTHOGONAL  = 2;
    /** The single bit that distinguishes {@link #BIT_TRANSLATION} from the next-weaker mask. */
    public static final int UNIQUE_TRANSLATION = 4;
    /** The single bit that distinguishes {@link #BIT_IDENTITY} from the next-weaker mask. */
    public static final int UNIQUE_IDENTITY    = 8;

    /** {@return the store/load backend} Resolved from {@link JomlConfig#setStoreLoadBackend} or
     *  {@code -Djoml.storeLoadBackend} ({@code api} or {@code unsafe}, case-insensitive); defaults
     *  to {@link StoreLoadBackend#UNSAFE} when {@code sun.misc.Unsafe} is available. Requesting
     *  {@code unsafe} on a JVM without {@code sun.misc.Unsafe}, or an unrecognised value, logs one
     *  warning on {@code System.err} and uses the API backend (respectively the default) instead of
     *  failing later.
     *
     *  <p>The backend is resolved once, on first use: the first store or load on a buffer, memory
     *  segment or raw address, the first {@code *Ops} call on one, or the first call of this
     *  method - not when {@code Joml} initializes, so a program that never touches native memory
     *  never probes {@code sun.misc.Unsafe}. The UNSAFE backend uses {@code sun.misc.Unsafe}, whose
     *  memory access is deprecated for removal (JEP 471); on JDK 24+ its first use prints a warning
     *  (JEP 498) unless the JVM runs with {@code --sun-misc-unsafe-memory-access=allow}, or select
     * <p>
     * Valid input: any value.
     *  {@code -Djoml.storeLoadBackend=api}.</p> */
    public static StoreLoadBackend storeLoadBackend() {
        return StoreLoadBackendHolder.BACKEND;
    }

    /** Lazy holder of {@link #storeLoadBackend()}: resolving it probes {@code sun.misc.Unsafe}. */
    private static final class StoreLoadBackendHolder {
        static final StoreLoadBackend BACKEND = resolve();

        private static StoreLoadBackend resolve() {
            StoreLoadBackend o = JomlConfig.storeLoadBackendOverride;
            if (o != null) return o == StoreLoadBackend.UNSAFE ? unsafeOrWarn("JomlConfig.setStoreLoadBackend(UNSAFE)") : o;
            String s = System.getProperty("joml.storeLoadBackend");
            if (s == null || s.trim().isEmpty()) return unsafeAvailable() ? StoreLoadBackend.UNSAFE : StoreLoadBackend.API;
            String v = s.trim();
            if (v.equalsIgnoreCase("api")) return StoreLoadBackend.API;
            if (v.equalsIgnoreCase("unsafe")) return unsafeOrWarn("-Djoml.storeLoadBackend=" + s);
            System.err.println("[org.joml2] unrecognised -Djoml.storeLoadBackend=" + s
                + " (expected api or unsafe); using the default backend");
            return unsafeAvailable() ? StoreLoadBackend.UNSAFE : StoreLoadBackend.API;
        }

        /** UNSAFE when {@code sun.misc.Unsafe} is usable, else one warning and API (instead of a
         *  NoClassDefFoundError from the first Unsafe-backed call). */
        private static StoreLoadBackend unsafeOrWarn(String requested) {
            if (unsafeAvailable()) return StoreLoadBackend.UNSAFE;
            System.err.println("[org.joml2] " + requested
                + " requested but sun.misc.Unsafe is not available on this JVM; using the API backend");
            return StoreLoadBackend.API;
        }

        private static boolean unsafeAvailable() {
            try { return UnsafeOpsHolder.U != null; } catch (Throwable t) { return false; }
        }
    }

    /** Whether the bundled SIMD (Vector-API) kernels of the {@code *Ops} classes are
     *  active: the {@code jdk.incubator.vector} module is present at runtime and not
     *  opted out via {@link JomlConfig#setVectorApi} or {@code -Djoml.vectorApi=false}.
     *  {@code JomlConfig.setVectorApi(true)} overrides {@code -Djoml.vectorApi=false} (and
     *  {@code setVectorApi(false)} overrides {@code =true}); an unrecognised property value
     *  logs one warning and disables. The module probe always wins: nothing can enable SIMD
     *  when the module is absent. Always {@code false} in variants that ship scalar {@code *Ops}. */
    public static final boolean VECTOR_API = resolveVectorApi();

    private static boolean resolveVectorApi() {
        // The programmatic override wins over the property in both directions; the module probe always wins.
        Boolean o = JomlConfig.vectorApiOverride;
        if (o != null ? !o : !vectorApiProperty()) return false;
        try {
            return org.joml2.internal.simd.VectorApiProbe.LANES > 0;
        } catch (Throwable t) {
            return false;
        }
    }

    /** {@code -Djoml.vectorApi}: absent, bare (empty) or {@code true} (case-insensitive) enables,
     *  {@code false} disables; any other value logs one warning on {@code System.err} and disables. */
    private static boolean vectorApiProperty() {
        String s = System.getProperty("joml.vectorApi");
        if (s == null) return true;
        String v = s.trim();
        if (v.isEmpty() || v.equalsIgnoreCase("true")) return true;
        if (v.equalsIgnoreCase("false")) return false;
        System.err.println("[org.joml2] unrecognised -Djoml.vectorApi=" + s
            + " (expected true or false); using false");
        return false;
    }

    /** {@return a new {@code Float2}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static Float2 float2() { return new Float2(0, 0); }
    /** {@return a new {@code Float3}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static Float3 float3() { return new Float3(0, 0, 0); }
    /** {@return a new {@code Float4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: any value, NaN and the infinities included. */
    public static Float4 float4() { return new Float4(0, 0, 0, 1); }
    /** {@return a new {@code Float2x2}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Float2x2 float2x2() { return new Float2x2(1, 0, 0, 1); }
    /** {@return a new {@code Float2x3}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Float2x3 float2x3() { return new Float2x3(1, 0, 0, 0, 1, 0); }
    /** {@return a new {@code Float3x3}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Float3x3 float3x3() { return new Float3x3(1, 0, 0, 0, 1, 0, 0, 0, 1); }
    /** {@return a new {@code Float3x4}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Float3x4 float3x4() { return new Float3x4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0); }
    /** {@return a new {@code Float4x4}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Float4x4 float4x4() { return new Float4x4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
    /** {@return a new {@code Float4x3}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Float4x3 float4x3() { return new Float4x3(1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0); }
    /** {@return a new {@code Float4x2}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Float4x2 float4x2() { return new Float4x2(1, 0, 0, 1, 0, 0, 0, 0); }
    /** {@return a new {@code Float2x4}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Float2x4 float2x4() { return new Float2x4(1, 0, 0, 0, 0, 1, 0, 0); }
    /** {@return a new {@code Float3x2}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Float3x2 float3x2() { return new Float3x2(1, 0, 0, 1, 0, 0); }
    /** {@return a new {@code FloatQuat}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatQuat floatQuat() { return new FloatQuat(0, 0, 0, 1); }
    /** {@return a new {@code FloatDualQuat}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatDualQuat floatDualQuat() { return new FloatDualQuat(0, 0, 0, 1, 0, 0, 0, 0); }
    /** {@return a new {@code FloatRect}, initialized to empty inverted bounds (so any union starts from the first added geometry)} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatRect floatRect() { return new FloatRect(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY); }
    /** {@return a new {@code FloatTransform}, initialized to the identity transform} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatTransform floatTransform() { return new FloatTransform(0, 0, 0, 0, 0, 0, 1, 1, 1, 1); }
    /** {@return a new {@code FloatRigid}, initialized to the identity transform} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatRigid floatRigid() { return new FloatRigid(0, 0, 0, 0, 0, 0, 1); }
    /** {@return a new {@code FloatOBB}, initialized to a degenerate box with identity orientation and zero center and extents} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatOBB floatOBB() { return new FloatOBB(0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0); }
    /** {@return a new {@code FloatAABB}, initialized to empty inverted bounds (so any union starts from the first added geometry)} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatAABB floatAABB() { return new FloatAABB(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY); }
    /** {@return a new {@code FloatSphere}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatSphere floatSphere() { return new FloatSphere(0, 0, 0, 0); }
    /** {@return a new {@code FloatRay}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatRay floatRay() { return new FloatRay(0, 0, 0, 0, 0, 0); }
    /** {@return a new {@code FloatPlane}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatPlane floatPlane() { return new FloatPlane(0, 0, 0, 0); }
    /** {@return a new {@code FloatTriangle}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static FloatTriangle floatTriangle() { return new FloatTriangle(0, 0, 0, 0, 0, 0, 0, 0, 0); }
    /** {@return a new {@code Double2}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static Double2 double2() { return new Double2(0, 0); }
    /** {@return a new {@code Double3}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static Double3 double3() { return new Double3(0, 0, 0); }
    /** {@return a new {@code Double4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: any value, NaN and the infinities included. */
    public static Double4 double4() { return new Double4(0, 0, 0, 1); }
    /** {@return a new {@code Double2x2}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Double2x2 double2x2() { return new Double2x2(1, 0, 0, 1); }
    /** {@return a new {@code Double2x3}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Double2x3 double2x3() { return new Double2x3(1, 0, 0, 0, 1, 0); }
    /** {@return a new {@code Double3x3}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Double3x3 double3x3() { return new Double3x3(1, 0, 0, 0, 1, 0, 0, 0, 1); }
    /** {@return a new {@code Double3x4}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Double3x4 double3x4() { return new Double3x4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0); }
    /** {@return a new {@code Double4x4}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Double4x4 double4x4() { return new Double4x4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1); }
    /** {@return a new {@code Double4x3}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Double4x3 double4x3() { return new Double4x3(1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0); }
    /** {@return a new {@code Double4x2}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Double4x2 double4x2() { return new Double4x2(1, 0, 0, 1, 0, 0, 0, 0); }
    /** {@return a new {@code Double2x4}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Double2x4 double2x4() { return new Double2x4(1, 0, 0, 0, 0, 1, 0, 0); }
    /** {@return a new {@code Double3x2}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static Double3x2 double3x2() { return new Double3x2(1, 0, 0, 1, 0, 0); }
    /** {@return a new {@code DoubleQuat}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleQuat doubleQuat() { return new DoubleQuat(0, 0, 0, 1); }
    /** {@return a new {@code DoubleDualQuat}, initialized to the identity} <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleDualQuat doubleDualQuat() { return new DoubleDualQuat(0, 0, 0, 1, 0, 0, 0, 0); }
    /** {@return a new {@code DoubleRect}, initialized to empty inverted bounds (so any union starts from the first added geometry)} <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleRect doubleRect() { return new DoubleRect(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY); }
    /** {@return a new {@code DoubleTransform}, initialized to the identity transform} <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleTransform doubleTransform() { return new DoubleTransform(0, 0, 0, 0, 0, 0, 1, 1, 1, 1); }
    /** {@return a new {@code DoubleRigid}, initialized to the identity transform} <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleRigid doubleRigid() { return new DoubleRigid(0, 0, 0, 0, 0, 0, 1); }
    /** {@return a new {@code DoubleOBB}, initialized to a degenerate box with identity orientation and zero center and extents} <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleOBB doubleOBB() { return new DoubleOBB(0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0); }
    /** {@return a new {@code DoubleAABB}, initialized to empty inverted bounds (so any union starts from the first added geometry)} <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleAABB doubleAABB() { return new DoubleAABB(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY); }
    /** {@return a new {@code DoubleSphere}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleSphere doubleSphere() { return new DoubleSphere(0, 0, 0, 0); }
    /** {@return a new {@code DoubleRay}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleRay doubleRay() { return new DoubleRay(0, 0, 0, 0, 0, 0); }
    /** {@return a new {@code DoublePlane}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static DoublePlane doublePlane() { return new DoublePlane(0, 0, 0, 0); }
    /** {@return a new {@code DoubleTriangle}, initialized to all zeros} <p>Valid input: any value, NaN and the infinities included. */
    public static DoubleTriangle doubleTriangle() { return new DoubleTriangle(0, 0, 0, 0, 0, 0, 0, 0, 0); }
    /** {@return a new {@code Byte2}, initialized to all zeros} <p>Valid input: any value. */
    public static Byte2 byte2() { return new Byte2((byte) 0, (byte) 0); }
    /** {@return a new {@code Byte3}, initialized to all zeros} <p>Valid input: any value. */
    public static Byte3 byte3() { return new Byte3((byte) 0, (byte) 0, (byte) 0); }
    /** {@return a new {@code Byte4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: any value. */
    public static Byte4 byte4() { return new Byte4((byte) 0, (byte) 0, (byte) 0, (byte) 1); }
    /** {@return a new {@code Short2}, initialized to all zeros} <p>Valid input: any value. */
    public static Short2 short2() { return new Short2((short) 0, (short) 0); }
    /** {@return a new {@code Short3}, initialized to all zeros} <p>Valid input: any value. */
    public static Short3 short3() { return new Short3((short) 0, (short) 0, (short) 0); }
    /** {@return a new {@code Short4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: any value. */
    public static Short4 short4() { return new Short4((short) 0, (short) 0, (short) 0, (short) 1); }
    /** {@return a new {@code Int2}, initialized to all zeros} <p>Valid input: any value. */
    public static Int2 int2() { return new Int2(0, 0); }
    /** {@return a new {@code Int3}, initialized to all zeros} <p>Valid input: any value. */
    public static Int3 int3() { return new Int3(0, 0, 0); }
    /** {@return a new {@code Int4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: any value. */
    public static Int4 int4() { return new Int4(0, 0, 0, 1); }
    /** {@return a new {@code IntRect}, initialized to empty inverted bounds (so any union starts from the first added geometry)} <p>Valid input: any value. */
    public static IntRect intRect() { return new IntRect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE); }
    /** {@return a new {@code Long2}, initialized to all zeros} <p>Valid input: any value. */
    public static Long2 long2() { return new Long2(0, 0); }
    /** {@return a new {@code Long3}, initialized to all zeros} <p>Valid input: any value. */
    public static Long3 long3() { return new Long3(0, 0, 0); }
    /** {@return a new {@code Long4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: any value. */
    public static Long4 long4() { return new Long4(0, 0, 0, 1); }

    /**
     * {@return a new {@code Float2} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Float2 float2(float x, float y) { return new Float2(x, y); }
    /**
     * {@return a new {@code Float3} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Float3 float3(float x, float y, float z) { return new Float3(x, y, z); }
    /**
     * {@return a new {@code Float4} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public static Float4 float4(float x, float y, float z, float w) { return new Float4(x, y, z, w); }
    /**
     * {@return a new {@code Float2x2} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     */
    public static Float2x2 float2x2(float m00, float m01, float m10, float m11) { return new Float2x2(m00, m01, m10, m11); }
    /**
     * {@return a new {@code Float2x3} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     */
    public static Float2x3 float2x3(float m00, float m01, float m02, float m10, float m11, float m12) { return new Float2x3(m00, m01, m02, m10, m11, m12); }
    /**
     * {@return a new {@code Float3x3} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     */
    public static Float3x3 float3x3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) { return new Float3x3(m00, m01, m02, m10, m11, m12, m20, m21, m22); }
    /**
     * {@return a new {@code Float3x4} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m03 the element in row 0, column 3
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m13 the element in row 1, column 3
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     * @param m23 the element in row 2, column 3
     */
    public static Float3x4 float3x4(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23) { return new Float3x4(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23); }
    /**
     * {@return a new {@code Float4x4} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m03 the element in row 0, column 3
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m13 the element in row 1, column 3
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     * @param m23 the element in row 2, column 3
     * @param m30 the element in row 3, column 0
     * @param m31 the element in row 3, column 1
     * @param m32 the element in row 3, column 2
     * @param m33 the element in row 3, column 3
     */
    public static Float4x4 float4x4(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33) { return new Float4x4(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33); }
    /**
     * {@return a new {@code Float4x3} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     * @param m30 the element in row 3, column 0
     * @param m31 the element in row 3, column 1
     * @param m32 the element in row 3, column 2
     */
    public static Float4x3 float4x3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32) { return new Float4x3(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32); }
    /**
     * {@return a new {@code Float4x2} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m30 the element in row 3, column 0
     * @param m31 the element in row 3, column 1
     */
    public static Float4x2 float4x2(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31) { return new Float4x2(m00, m01, m10, m11, m20, m21, m30, m31); }
    /**
     * {@return a new {@code Float2x4} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m03 the element in row 0, column 3
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m13 the element in row 1, column 3
     */
    public static Float2x4 float2x4(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13) { return new Float2x4(m00, m01, m02, m03, m10, m11, m12, m13); }
    /**
     * {@return a new {@code Float3x2} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     */
    public static Float3x2 float3x2(float m00, float m01, float m10, float m11, float m20, float m21) { return new Float3x2(m00, m01, m10, m11, m20, m21); }
    /**
     * {@return a new {@code FloatQuat} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public static FloatQuat floatQuat(float x, float y, float z, float w) { return new FloatQuat(x, y, z, w); }
    /**
     * {@return a new {@code FloatDualQuat} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param rX the {@code rX} component
     * @param rY the {@code rY} component
     * @param rZ the {@code rZ} component
     * @param rW the {@code rW} component
     * @param dX the {@code dX} component
     * @param dY the {@code dY} component
     * @param dZ the {@code dZ} component
     * @param dW the {@code dW} component
     */
    public static FloatDualQuat floatDualQuat(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW) { return new FloatDualQuat(rX, rY, rZ, rW, dX, dY, dZ, dW); }
    /**
     * {@return a new {@code FloatRect} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param minX the {@code minX} component
     * @param minY the {@code minY} component
     * @param maxX the {@code maxX} component
     * @param maxY the {@code maxY} component
     */
    public static FloatRect floatRect(float minX, float minY, float maxX, float maxY) { return new FloatRect(minX, minY, maxX, maxY); }
    /**
     * {@return a new {@code FloatTransform} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param tX the {@code tX} component
     * @param tY the {@code tY} component
     * @param tZ the {@code tZ} component
     * @param rX the {@code rX} component
     * @param rY the {@code rY} component
     * @param rZ the {@code rZ} component
     * @param rW the {@code rW} component
     * @param sX the {@code sX} component
     * @param sY the {@code sY} component
     * @param sZ the {@code sZ} component
     */
    public static FloatTransform floatTransform(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW, float sX, float sY, float sZ) { return new FloatTransform(tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ); }
    /**
     * {@return a new {@code FloatRigid} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param tX the {@code tX} component
     * @param tY the {@code tY} component
     * @param tZ the {@code tZ} component
     * @param rX the {@code rX} component
     * @param rY the {@code rY} component
     * @param rZ the {@code rZ} component
     * @param rW the {@code rW} component
     */
    public static FloatRigid floatRigid(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW) { return new FloatRigid(tX, tY, tZ, rX, rY, rZ, rW); }
    /**
     * {@return a new {@code FloatOBB} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param cX the {@code cX} component
     * @param cY the {@code cY} component
     * @param cZ the {@code cZ} component
     * @param uXx the {@code uXx} component
     * @param uXy the {@code uXy} component
     * @param uXz the {@code uXz} component
     * @param uYx the {@code uYx} component
     * @param uYy the {@code uYy} component
     * @param uYz the {@code uYz} component
     * @param uZx the {@code uZx} component
     * @param uZy the {@code uZy} component
     * @param uZz the {@code uZz} component
     * @param hsX the {@code hsX} component
     * @param hsY the {@code hsY} component
     * @param hsZ the {@code hsZ} component
     */
    public static FloatOBB floatOBB(float cX, float cY, float cZ, float uXx, float uXy, float uXz, float uYx, float uYy, float uYz, float uZx, float uZy, float uZz, float hsX, float hsY, float hsZ) { return new FloatOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ); }
    /**
     * {@return a new {@code FloatAABB} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param minX the {@code minX} component
     * @param minY the {@code minY} component
     * @param minZ the {@code minZ} component
     * @param maxX the {@code maxX} component
     * @param maxY the {@code maxY} component
     * @param maxZ the {@code maxZ} component
     */
    public static FloatAABB floatAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) { return new FloatAABB(minX, minY, minZ, maxX, maxY, maxZ); }
    /**
     * {@return a new {@code FloatSphere} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param r the {@code r} component
     */
    public static FloatSphere floatSphere(float x, float y, float z, float r) { return new FloatSphere(x, y, z, r); }
    /**
     * {@return a new {@code FloatRay} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param oX the {@code oX} component
     * @param oY the {@code oY} component
     * @param oZ the {@code oZ} component
     * @param dX the {@code dX} component
     * @param dY the {@code dY} component
     * @param dZ the {@code dZ} component
     */
    public static FloatRay floatRay(float oX, float oY, float oZ, float dX, float dY, float dZ) { return new FloatRay(oX, oY, oZ, dX, dY, dZ); }
    /**
     * {@return a new {@code FloatPlane} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param a the {@code a} component
     * @param b the {@code b} component
     * @param c the {@code c} component
     * @param d the {@code d} component
     */
    public static FloatPlane floatPlane(float a, float b, float c, float d) { return new FloatPlane(a, b, c, d); }
    /**
     * {@return a new {@code FloatTriangle} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0X the {@code v0X} component
     * @param v0Y the {@code v0Y} component
     * @param v0Z the {@code v0Z} component
     * @param v1X the {@code v1X} component
     * @param v1Y the {@code v1Y} component
     * @param v1Z the {@code v1Z} component
     * @param v2X the {@code v2X} component
     * @param v2Y the {@code v2Y} component
     * @param v2Z the {@code v2Z} component
     */
    public static FloatTriangle floatTriangle(float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z) { return new FloatTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z); }
    /**
     * {@return a new {@code Double2} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Double2 double2(double x, double y) { return new Double2(x, y); }
    /**
     * {@return a new {@code Double3} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Double3 double3(double x, double y, double z) { return new Double3(x, y, z); }
    /**
     * {@return a new {@code Double4} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public static Double4 double4(double x, double y, double z, double w) { return new Double4(x, y, z, w); }
    /**
     * {@return a new {@code Double2x2} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     */
    public static Double2x2 double2x2(double m00, double m01, double m10, double m11) { return new Double2x2(m00, m01, m10, m11); }
    /**
     * {@return a new {@code Double2x3} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     */
    public static Double2x3 double2x3(double m00, double m01, double m02, double m10, double m11, double m12) { return new Double2x3(m00, m01, m02, m10, m11, m12); }
    /**
     * {@return a new {@code Double3x3} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     */
    public static Double3x3 double3x3(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) { return new Double3x3(m00, m01, m02, m10, m11, m12, m20, m21, m22); }
    /**
     * {@return a new {@code Double3x4} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m03 the element in row 0, column 3
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m13 the element in row 1, column 3
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     * @param m23 the element in row 2, column 3
     */
    public static Double3x4 double3x4(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23) { return new Double3x4(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23); }
    /**
     * {@return a new {@code Double4x4} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m03 the element in row 0, column 3
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m13 the element in row 1, column 3
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     * @param m23 the element in row 2, column 3
     * @param m30 the element in row 3, column 0
     * @param m31 the element in row 3, column 1
     * @param m32 the element in row 3, column 2
     * @param m33 the element in row 3, column 3
     */
    public static Double4x4 double4x4(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) { return new Double4x4(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33); }
    /**
     * {@return a new {@code Double4x3} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     * @param m30 the element in row 3, column 0
     * @param m31 the element in row 3, column 1
     * @param m32 the element in row 3, column 2
     */
    public static Double4x3 double4x3(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double m30, double m31, double m32) { return new Double4x3(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32); }
    /**
     * {@return a new {@code Double4x2} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m30 the element in row 3, column 0
     * @param m31 the element in row 3, column 1
     */
    public static Double4x2 double4x2(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31) { return new Double4x2(m00, m01, m10, m11, m20, m21, m30, m31); }
    /**
     * {@return a new {@code Double2x4} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m03 the element in row 0, column 3
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m13 the element in row 1, column 3
     */
    public static Double2x4 double2x4(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) { return new Double2x4(m00, m01, m02, m03, m10, m11, m12, m13); }
    /**
     * {@return a new {@code Double3x2} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     */
    public static Double3x2 double3x2(double m00, double m01, double m10, double m11, double m20, double m21) { return new Double3x2(m00, m01, m10, m11, m20, m21); }
    /**
     * {@return a new {@code DoubleQuat} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public static DoubleQuat doubleQuat(double x, double y, double z, double w) { return new DoubleQuat(x, y, z, w); }
    /**
     * {@return a new {@code DoubleDualQuat} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param rX the {@code rX} component
     * @param rY the {@code rY} component
     * @param rZ the {@code rZ} component
     * @param rW the {@code rW} component
     * @param dX the {@code dX} component
     * @param dY the {@code dY} component
     * @param dZ the {@code dZ} component
     * @param dW the {@code dW} component
     */
    public static DoubleDualQuat doubleDualQuat(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) { return new DoubleDualQuat(rX, rY, rZ, rW, dX, dY, dZ, dW); }
    /**
     * {@return a new {@code DoubleRect} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param minX the {@code minX} component
     * @param minY the {@code minY} component
     * @param maxX the {@code maxX} component
     * @param maxY the {@code maxY} component
     */
    public static DoubleRect doubleRect(double minX, double minY, double maxX, double maxY) { return new DoubleRect(minX, minY, maxX, maxY); }
    /**
     * {@return a new {@code DoubleTransform} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param tX the {@code tX} component
     * @param tY the {@code tY} component
     * @param tZ the {@code tZ} component
     * @param rX the {@code rX} component
     * @param rY the {@code rY} component
     * @param rZ the {@code rZ} component
     * @param rW the {@code rW} component
     * @param sX the {@code sX} component
     * @param sY the {@code sY} component
     * @param sZ the {@code sZ} component
     */
    public static DoubleTransform doubleTransform(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW, double sX, double sY, double sZ) { return new DoubleTransform(tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ); }
    /**
     * {@return a new {@code DoubleRigid} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param tX the {@code tX} component
     * @param tY the {@code tY} component
     * @param tZ the {@code tZ} component
     * @param rX the {@code rX} component
     * @param rY the {@code rY} component
     * @param rZ the {@code rZ} component
     * @param rW the {@code rW} component
     */
    public static DoubleRigid doubleRigid(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW) { return new DoubleRigid(tX, tY, tZ, rX, rY, rZ, rW); }
    /**
     * {@return a new {@code DoubleOBB} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param cX the {@code cX} component
     * @param cY the {@code cY} component
     * @param cZ the {@code cZ} component
     * @param uXx the {@code uXx} component
     * @param uXy the {@code uXy} component
     * @param uXz the {@code uXz} component
     * @param uYx the {@code uYx} component
     * @param uYy the {@code uYy} component
     * @param uYz the {@code uYz} component
     * @param uZx the {@code uZx} component
     * @param uZy the {@code uZy} component
     * @param uZz the {@code uZz} component
     * @param hsX the {@code hsX} component
     * @param hsY the {@code hsY} component
     * @param hsZ the {@code hsZ} component
     */
    public static DoubleOBB doubleOBB(double cX, double cY, double cZ, double uXx, double uXy, double uXz, double uYx, double uYy, double uYz, double uZx, double uZy, double uZz, double hsX, double hsY, double hsZ) { return new DoubleOBB(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ); }
    /**
     * {@return a new {@code DoubleAABB} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param minX the {@code minX} component
     * @param minY the {@code minY} component
     * @param minZ the {@code minZ} component
     * @param maxX the {@code maxX} component
     * @param maxY the {@code maxY} component
     * @param maxZ the {@code maxZ} component
     */
    public static DoubleAABB doubleAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) { return new DoubleAABB(minX, minY, minZ, maxX, maxY, maxZ); }
    /**
     * {@return a new {@code DoubleSphere} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param r the {@code r} component
     */
    public static DoubleSphere doubleSphere(double x, double y, double z, double r) { return new DoubleSphere(x, y, z, r); }
    /**
     * {@return a new {@code DoubleRay} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param oX the {@code oX} component
     * @param oY the {@code oY} component
     * @param oZ the {@code oZ} component
     * @param dX the {@code dX} component
     * @param dY the {@code dY} component
     * @param dZ the {@code dZ} component
     */
    public static DoubleRay doubleRay(double oX, double oY, double oZ, double dX, double dY, double dZ) { return new DoubleRay(oX, oY, oZ, dX, dY, dZ); }
    /**
     * {@return a new {@code DoublePlane} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param a the {@code a} component
     * @param b the {@code b} component
     * @param c the {@code c} component
     * @param d the {@code d} component
     */
    public static DoublePlane doublePlane(double a, double b, double c, double d) { return new DoublePlane(a, b, c, d); }
    /**
     * {@return a new {@code DoubleTriangle} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0X the {@code v0X} component
     * @param v0Y the {@code v0Y} component
     * @param v0Z the {@code v0Z} component
     * @param v1X the {@code v1X} component
     * @param v1Y the {@code v1Y} component
     * @param v1Z the {@code v1Z} component
     * @param v2X the {@code v2X} component
     * @param v2Y the {@code v2Y} component
     * @param v2Z the {@code v2Z} component
     */
    public static DoubleTriangle doubleTriangle(double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z) { return new DoubleTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z); }
    /**
     * {@return a new {@code Byte2} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Byte2 byte2(byte x, byte y) { return new Byte2(x, y); }
    /**
     * {@return a new {@code Byte3} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Byte3 byte3(byte x, byte y, byte z) { return new Byte3(x, y, z); }
    /**
     * {@return a new {@code Byte4} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public static Byte4 byte4(byte x, byte y, byte z, byte w) { return new Byte4(x, y, z, w); }
    /**
     * {@return a new {@code Short2} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Short2 short2(short x, short y) { return new Short2(x, y); }
    /**
     * {@return a new {@code Short3} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Short3 short3(short x, short y, short z) { return new Short3(x, y, z); }
    /**
     * {@return a new {@code Short4} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public static Short4 short4(short x, short y, short z, short w) { return new Short4(x, y, z, w); }
    /**
     * {@return a new {@code Int2} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Int2 int2(int x, int y) { return new Int2(x, y); }
    /**
     * {@return a new {@code Int3} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Int3 int3(int x, int y, int z) { return new Int3(x, y, z); }
    /**
     * {@return a new {@code Int4} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public static Int4 int4(int x, int y, int z, int w) { return new Int4(x, y, z, w); }
    /**
     * {@return a new {@code IntRect} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param minX the {@code minX} component
     * @param minY the {@code minY} component
     * @param maxX the {@code maxX} component
     * @param maxY the {@code maxY} component
     */
    public static IntRect intRect(int minX, int minY, int maxX, int maxY) { return new IntRect(minX, minY, maxX, maxY); }
    /**
     * {@return a new {@code Long2} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Long2 long2(long x, long y) { return new Long2(x, y); }
    /**
     * {@return a new {@code Long3} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Long3 long3(long x, long y, long z) { return new Long3(x, y, z); }
    /**
     * {@return a new {@code Long4} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public static Long4 long4(long x, long y, long z, long w) { return new Long4(x, y, z, w); }

    /**
     * {@return a new {@code Float2} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Float2 float2(float s) { return new Float2(s); }
    /**
     * {@return a new {@code Float3} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Float3 float3(float s) { return new Float3(s); }
    /**
     * {@return a new {@code Float4} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Float4 float4(float s) { return new Float4(s); }
    /**
     * {@return a new {@code Double2} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Double2 double2(double s) { return new Double2(s); }
    /**
     * {@return a new {@code Double3} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Double3 double3(double s) { return new Double3(s); }
    /**
     * {@return a new {@code Double4} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Double4 double4(double s) { return new Double4(s); }
    /**
     * {@return a new {@code Byte2} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Byte2 byte2(byte s) { return new Byte2(s); }
    /**
     * {@return a new {@code Byte3} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Byte3 byte3(byte s) { return new Byte3(s); }
    /**
     * {@return a new {@code Byte4} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Byte4 byte4(byte s) { return new Byte4(s); }
    /**
     * {@return a new {@code Short2} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Short2 short2(short s) { return new Short2(s); }
    /**
     * {@return a new {@code Short3} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Short3 short3(short s) { return new Short3(s); }
    /**
     * {@return a new {@code Short4} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Short4 short4(short s) { return new Short4(s); }
    /**
     * {@return a new {@code Int2} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Int2 int2(int s) { return new Int2(s); }
    /**
     * {@return a new {@code Int3} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Int3 int3(int s) { return new Int3(s); }
    /**
     * {@return a new {@code Int4} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Int4 int4(int s) { return new Int4(s); }
    /**
     * {@return a new {@code Long2} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Long2 long2(long s) { return new Long2(s); }
    /**
     * {@return a new {@code Long3} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Long3 long3(long s) { return new Long3(s); }
    /**
     * {@return a new {@code Long4} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Long4 long4(long s) { return new Long4(s); }

    /**
     * {@return a new {@code Float3} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Float3 float3(float v0, Float2 v1) { return new Float3(v0, v1); }
    /**
     * {@return a new {@code Float3} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Float3 float3(Float2 v0, float v1) { return new Float3(v0, v1); }
    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Float4 float4(float v0, float v1, Float2 v2) { return new Float4(v0, v1, v2); }
    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Float4 float4(float v0, Float2 v1, float v2) { return new Float4(v0, v1, v2); }
    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Float4 float4(float v0, Float3 v1) { return new Float4(v0, v1); }
    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Float4 float4(Float2 v0, float v1, float v2) { return new Float4(v0, v1, v2); }
    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Float4 float4(Float2 v0, Float2 v1) { return new Float4(v0, v1); }
    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Float4 float4(Float3 v0, float v1) { return new Float4(v0, v1); }
    /**
     * {@return a new {@code Double3} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Double3 double3(double v0, Double2 v1) { return new Double3(v0, v1); }
    /**
     * {@return a new {@code Double3} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Double3 double3(Double2 v0, double v1) { return new Double3(v0, v1); }
    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Double4 double4(double v0, double v1, Double2 v2) { return new Double4(v0, v1, v2); }
    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Double4 double4(double v0, Double2 v1, double v2) { return new Double4(v0, v1, v2); }
    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Double4 double4(double v0, Double3 v1) { return new Double4(v0, v1); }
    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Double4 double4(Double2 v0, double v1, double v2) { return new Double4(v0, v1, v2); }
    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Double4 double4(Double2 v0, Double2 v1) { return new Double4(v0, v1); }
    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Double4 double4(Double3 v0, double v1) { return new Double4(v0, v1); }
    /**
     * {@return a new {@code Byte3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Byte3 byte3(byte v0, Byte2 v1) { return new Byte3(v0, v1); }
    /**
     * {@return a new {@code Byte3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Byte3 byte3(Byte2 v0, byte v1) { return new Byte3(v0, v1); }
    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Byte4 byte4(byte v0, byte v1, Byte2 v2) { return new Byte4(v0, v1, v2); }
    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Byte4 byte4(byte v0, Byte2 v1, byte v2) { return new Byte4(v0, v1, v2); }
    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Byte4 byte4(byte v0, Byte3 v1) { return new Byte4(v0, v1); }
    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Byte4 byte4(Byte2 v0, byte v1, byte v2) { return new Byte4(v0, v1, v2); }
    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Byte4 byte4(Byte2 v0, Byte2 v1) { return new Byte4(v0, v1); }
    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Byte4 byte4(Byte3 v0, byte v1) { return new Byte4(v0, v1); }
    /**
     * {@return a new {@code Short3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Short3 short3(short v0, Short2 v1) { return new Short3(v0, v1); }
    /**
     * {@return a new {@code Short3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Short3 short3(Short2 v0, short v1) { return new Short3(v0, v1); }
    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Short4 short4(short v0, short v1, Short2 v2) { return new Short4(v0, v1, v2); }
    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Short4 short4(short v0, Short2 v1, short v2) { return new Short4(v0, v1, v2); }
    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Short4 short4(short v0, Short3 v1) { return new Short4(v0, v1); }
    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Short4 short4(Short2 v0, short v1, short v2) { return new Short4(v0, v1, v2); }
    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Short4 short4(Short2 v0, Short2 v1) { return new Short4(v0, v1); }
    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Short4 short4(Short3 v0, short v1) { return new Short4(v0, v1); }
    /**
     * {@return a new {@code Int3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Int3 int3(int v0, Int2 v1) { return new Int3(v0, v1); }
    /**
     * {@return a new {@code Int3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Int3 int3(Int2 v0, int v1) { return new Int3(v0, v1); }
    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Int4 int4(int v0, int v1, Int2 v2) { return new Int4(v0, v1, v2); }
    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Int4 int4(int v0, Int2 v1, int v2) { return new Int4(v0, v1, v2); }
    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Int4 int4(int v0, Int3 v1) { return new Int4(v0, v1); }
    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Int4 int4(Int2 v0, int v1, int v2) { return new Int4(v0, v1, v2); }
    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Int4 int4(Int2 v0, Int2 v1) { return new Int4(v0, v1); }
    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Int4 int4(Int3 v0, int v1) { return new Int4(v0, v1); }
    /**
     * {@return a new {@code Long3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Long3 long3(long v0, Long2 v1) { return new Long3(v0, v1); }
    /**
     * {@return a new {@code Long3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Long3 long3(Long2 v0, long v1) { return new Long3(v0, v1); }
    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Long4 long4(long v0, long v1, Long2 v2) { return new Long4(v0, v1, v2); }
    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Long4 long4(long v0, Long2 v1, long v2) { return new Long4(v0, v1, v2); }
    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Long4 long4(long v0, Long3 v1) { return new Long4(v0, v1); }
    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Long4 long4(Long2 v0, long v1, long v2) { return new Long4(v0, v1, v2); }
    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Long4 long4(Long2 v0, Long2 v1) { return new Long4(v0, v1); }
    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Long4 long4(Long3 v0, long v1) { return new Long4(v0, v1); }

    /**
     * {@return a new {@code FloatDualQuat} built from its real and dual parts}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param real the real part, i.e. the rotation quaternion
     * @param dual the dual part, carrying the translation
     */
    public static FloatDualQuat floatDualQuat(FloatQuat real, FloatQuat dual) { return new FloatDualQuat(real, dual); }
    /**
     * {@return a new {@code FloatRect} built from its minimum and maximum corners}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the rectangle
     * @param max the maximum corner of the rectangle
     */
    public static FloatRect floatRect(Float2 min, Float2 max) { return new FloatRect(min, max); }
    /**
     * {@return a new {@code FloatTransform} built from its translation, rotation and scale}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     * @param scale the scale along each local axis
     */
    public static FloatTransform floatTransform(Float3 translation, FloatQuat rotation, Float3 scale) { return new FloatTransform(translation, rotation, scale); }
    /**
     * {@return a new {@code FloatRigid} built from its translation and rotation}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     */
    public static FloatRigid floatRigid(Float3 translation, FloatQuat rotation) { return new FloatRigid(translation, rotation); }
    /**
     * {@return a new {@code FloatOBB} built from its center, its three local axes and its half-size}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the box
     * @param axisX the local x axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param axisY the local y axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param axisZ the local z axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param halfSize the half extent of the box along each local axis
     */
    public static FloatOBB floatOBB(Float3 center, Float3 axisX, Float3 axisY, Float3 axisZ, Float3 halfSize) { return new FloatOBB(center, axisX, axisY, axisZ, halfSize); }
    /**
     * {@return a new {@code FloatAABB} built from its minimum and maximum corners}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     */
    public static FloatAABB floatAABB(Float3 min, Float3 max) { return new FloatAABB(min, max); }
    /**
     * {@return a new {@code FloatSphere} built from its center and radius}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     */
    public static FloatSphere floatSphere(Float3 center, float radius) { return new FloatSphere(center, radius); }
    /**
     * {@return a new {@code FloatRay} built from its origin and direction}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param origin the origin of the ray
     * @param direction the direction of the ray, taken as given (not normalized)
     */
    public static FloatRay floatRay(Float3 origin, Float3 direction) { return new FloatRay(origin, direction); }
    /**
     * {@return a new {@code FloatPlane} built from its normal {@code (a, b, c)} and the {@code d} coefficient of the plane equation {@code a*x + b*y + c*z + d = 0}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param normal the normal {@code (a, b, c)} of the plane, taken as given (not normalized)
     * @param d the {@code d} coefficient of the plane equation {@code a*x + b*y + c*z + d = 0}; for
     *        a unit normal, the signed distance of the origin to the plane, positive on the side
     *        the normal points to
     */
    public static FloatPlane floatPlane(Float3 normal, float d) { return new FloatPlane(normal, d); }
    /**
     * {@return a new {@code FloatTriangle} built from its three vertices}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the first vertex
     * @param v1 the second vertex
     * @param v2 the third vertex
     */
    public static FloatTriangle floatTriangle(Float3 v0, Float3 v1, Float3 v2) { return new FloatTriangle(v0, v1, v2); }
    /**
     * {@return a new {@code DoubleDualQuat} built from its real and dual parts}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param real the real part, i.e. the rotation quaternion
     * @param dual the dual part, carrying the translation
     */
    public static DoubleDualQuat doubleDualQuat(DoubleQuat real, DoubleQuat dual) { return new DoubleDualQuat(real, dual); }
    /**
     * {@return a new {@code DoubleRect} built from its minimum and maximum corners}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the rectangle
     * @param max the maximum corner of the rectangle
     */
    public static DoubleRect doubleRect(Double2 min, Double2 max) { return new DoubleRect(min, max); }
    /**
     * {@return a new {@code DoubleTransform} built from its translation, rotation and scale}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     * @param scale the scale along each local axis
     */
    public static DoubleTransform doubleTransform(Double3 translation, DoubleQuat rotation, Double3 scale) { return new DoubleTransform(translation, rotation, scale); }
    /**
     * {@return a new {@code DoubleRigid} built from its translation and rotation}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     */
    public static DoubleRigid doubleRigid(Double3 translation, DoubleQuat rotation) { return new DoubleRigid(translation, rotation); }
    /**
     * {@return a new {@code DoubleOBB} built from its center, its three local axes and its half-size}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the box
     * @param axisX the local x axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param axisY the local y axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param axisZ the local z axis of the box, taken as given (expected to be unit length and
     *        orthogonal to the other two axes)
     * @param halfSize the half extent of the box along each local axis
     */
    public static DoubleOBB doubleOBB(Double3 center, Double3 axisX, Double3 axisY, Double3 axisZ, Double3 halfSize) { return new DoubleOBB(center, axisX, axisY, axisZ, halfSize); }
    /**
     * {@return a new {@code DoubleAABB} built from its minimum and maximum corners}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     */
    public static DoubleAABB doubleAABB(Double3 min, Double3 max) { return new DoubleAABB(min, max); }
    /**
     * {@return a new {@code DoubleSphere} built from its center and radius}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     */
    public static DoubleSphere doubleSphere(Double3 center, double radius) { return new DoubleSphere(center, radius); }
    /**
     * {@return a new {@code DoubleRay} built from its origin and direction}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param origin the origin of the ray
     * @param direction the direction of the ray, taken as given (not normalized)
     */
    public static DoubleRay doubleRay(Double3 origin, Double3 direction) { return new DoubleRay(origin, direction); }
    /**
     * {@return a new {@code DoublePlane} built from its normal {@code (a, b, c)} and the {@code d} coefficient of the plane equation {@code a*x + b*y + c*z + d = 0}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param normal the normal {@code (a, b, c)} of the plane, taken as given (not normalized)
     * @param d the {@code d} coefficient of the plane equation {@code a*x + b*y + c*z + d = 0}; for
     *        a unit normal, the signed distance of the origin to the plane, positive on the side
     *        the normal points to
     */
    public static DoublePlane doublePlane(Double3 normal, double d) { return new DoublePlane(normal, d); }
    /**
     * {@return a new {@code DoubleTriangle} built from its three vertices}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the first vertex
     * @param v1 the second vertex
     * @param v2 the third vertex
     */
    public static DoubleTriangle doubleTriangle(Double3 v0, Double3 v1, Double3 v2) { return new DoubleTriangle(v0, v1, v2); }
    /**
     * {@return a new {@code IntRect} built from its minimum and maximum corners}
     * <p>
     * Valid input: any value.
     *
     * @param min the minimum corner of the rectangle
     * @param max the maximum corner of the rectangle
     */
    public static IntRect intRect(Int2 min, Int2 max) { return new IntRect(min, max); }

    /**
     * {@return a new {@code Float2x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Float2x2 float2x2(Float2 c0, Float2 c1) { return new Float2x2(c0, c1); }
    /**
     * {@return a new {@code Float2x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Float2x3 float2x3(Float2 c0, Float2 c1, Float2 c2) { return new Float2x3(c0, c1, c2); }
    /**
     * {@return a new {@code Float3x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Float3x3 float3x3(Float3 c0, Float3 c1, Float3 c2) { return new Float3x3(c0, c1, c2); }
    /**
     * {@return a new {@code Float3x4} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     * @param c3 the fourth column
     */
    public static Float3x4 float3x4(Float3 c0, Float3 c1, Float3 c2, Float3 c3) { return new Float3x4(c0, c1, c2, c3); }
    /**
     * {@return a new {@code Float4x4} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     * @param c3 the fourth column
     */
    public static Float4x4 float4x4(Float4 c0, Float4 c1, Float4 c2, Float4 c3) { return new Float4x4(c0, c1, c2, c3); }
    /**
     * {@return a new {@code Float4x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Float4x3 float4x3(Float4 c0, Float4 c1, Float4 c2) { return new Float4x3(c0, c1, c2); }
    /**
     * {@return a new {@code Float4x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Float4x2 float4x2(Float4 c0, Float4 c1) { return new Float4x2(c0, c1); }
    /**
     * {@return a new {@code Float2x4} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     * @param c3 the fourth column
     */
    public static Float2x4 float2x4(Float2 c0, Float2 c1, Float2 c2, Float2 c3) { return new Float2x4(c0, c1, c2, c3); }
    /**
     * {@return a new {@code Float3x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Float3x2 float3x2(Float3 c0, Float3 c1) { return new Float3x2(c0, c1); }
    /**
     * {@return a new {@code Double2x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Double2x2 double2x2(Double2 c0, Double2 c1) { return new Double2x2(c0, c1); }
    /**
     * {@return a new {@code Double2x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Double2x3 double2x3(Double2 c0, Double2 c1, Double2 c2) { return new Double2x3(c0, c1, c2); }
    /**
     * {@return a new {@code Double3x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Double3x3 double3x3(Double3 c0, Double3 c1, Double3 c2) { return new Double3x3(c0, c1, c2); }
    /**
     * {@return a new {@code Double3x4} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     * @param c3 the fourth column
     */
    public static Double3x4 double3x4(Double3 c0, Double3 c1, Double3 c2, Double3 c3) { return new Double3x4(c0, c1, c2, c3); }
    /**
     * {@return a new {@code Double4x4} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     * @param c3 the fourth column
     */
    public static Double4x4 double4x4(Double4 c0, Double4 c1, Double4 c2, Double4 c3) { return new Double4x4(c0, c1, c2, c3); }
    /**
     * {@return a new {@code Double4x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Double4x3 double4x3(Double4 c0, Double4 c1, Double4 c2) { return new Double4x3(c0, c1, c2); }
    /**
     * {@return a new {@code Double4x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Double4x2 double4x2(Double4 c0, Double4 c1) { return new Double4x2(c0, c1); }
    /**
     * {@return a new {@code Double2x4} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     * @param c3 the fourth column
     */
    public static Double2x4 double2x4(Double2 c0, Double2 c1, Double2 c2, Double2 c3) { return new Double2x4(c0, c1, c2, c3); }
    /**
     * {@return a new {@code Double3x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Double3x2 double3x2(Double3 c0, Double3 c1) { return new Double3x2(c0, c1); }

    /**
     * {@return a new {@code Float2x2} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float2x2 float2x2(Float2x3 src) { return new Float2x2(src); }
    /**
     * {@return a new {@code Float2x2} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float2x2 float2x2(Float3x3 src) { return new Float2x2(src); }
    /**
     * {@return a new {@code Float2x3} initialized to {@code src}, identity-extended with a zero translation column}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float2x3 float2x3(Float2x2 src) { return new Float2x3(src); }
    /**
     * {@return a new {@code Float2x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float2x3 float2x3(Float3x3 src) { return new Float2x3(src); }
    /**
     * {@return a new {@code Float3x3} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x3 float3x3(Float2x3 src) { return new Float3x3(src); }
    /**
     * {@return a new {@code Float3x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x3 float3x3(Float4x4 src) { return new Float3x3(src); }
    /**
     * {@return a new {@code Float3x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x3 float3x3(Float3x4 src) { return new Float3x3(src); }
    /**
     * {@return a new {@code Float3x3} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x3 float3x3(Float2x2 src) { return new Float3x3(src); }
    /**
     * {@return a new {@code Float3x4} initialized to {@code src}, identity-extended with a zero translation column}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x4 float3x4(Float3x3 src) { return new Float3x4(src); }
    /**
     * {@return a new {@code Float3x4} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x4 float3x4(Float4x4 src) { return new Float3x4(src); }
    /**
     * {@return a new {@code Float4x4} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float4x4 float4x4(Float3x4 src) { return new Float4x4(src); }
    /**
     * {@return a new {@code Float4x4} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float4x4 float4x4(Float3x3 src) { return new Float4x4(src); }
    /**
     * {@return a new {@code Double2x2} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double2x2 double2x2(Double2x3 src) { return new Double2x2(src); }
    /**
     * {@return a new {@code Double2x2} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double2x2 double2x2(Double3x3 src) { return new Double2x2(src); }
    /**
     * {@return a new {@code Double2x3} initialized to {@code src}, identity-extended with a zero translation column}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double2x3 double2x3(Double2x2 src) { return new Double2x3(src); }
    /**
     * {@return a new {@code Double2x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double2x3 double2x3(Double3x3 src) { return new Double2x3(src); }
    /**
     * {@return a new {@code Double3x3} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x3 double3x3(Double2x3 src) { return new Double3x3(src); }
    /**
     * {@return a new {@code Double3x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x3 double3x3(Double4x4 src) { return new Double3x3(src); }
    /**
     * {@return a new {@code Double3x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x3 double3x3(Double3x4 src) { return new Double3x3(src); }
    /**
     * {@return a new {@code Double3x3} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x3 double3x3(Double2x2 src) { return new Double3x3(src); }
    /**
     * {@return a new {@code Double3x4} initialized to {@code src}, identity-extended with a zero translation column}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x4 double3x4(Double3x3 src) { return new Double3x4(src); }
    /**
     * {@return a new {@code Double3x4} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x4 double3x4(Double4x4 src) { return new Double3x4(src); }
    /**
     * {@return a new {@code Double4x4} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double4x4 double4x4(Double3x4 src) { return new Double4x4(src); }
    /**
     * {@return a new {@code Double4x4} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double4x4 double4x4(Double3x3 src) { return new Double4x4(src); }

    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float2 float2(Float2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float3 float3(Float3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float4 float4(Float4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float2x2 float2x2(Float2x2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float2x3 float2x3(Float2x3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float3x3 float3x3(Float3x3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float3x4 float3x4(Float3x4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float4x4 float4x4(Float4x4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float4x3 float4x3(Float4x3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float4x2 float4x2(Float4x2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float2x4 float2x4(Float2x4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Float3x2 float3x2(Float3x2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatQuat floatQuat(FloatQuat src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatDualQuat floatDualQuat(FloatDualQuat src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatRect floatRect(FloatRect src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatTransform floatTransform(FloatTransform src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatRigid floatRigid(FloatRigid src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatOBB floatOBB(FloatOBB src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatAABB floatAABB(FloatAABB src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatSphere floatSphere(FloatSphere src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatRay floatRay(FloatRay src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatPlane floatPlane(FloatPlane src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static FloatTriangle floatTriangle(FloatTriangle src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double2 double2(Double2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double3 double3(Double3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double4 double4(Double4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double2x2 double2x2(Double2x2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double2x3 double2x3(Double2x3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double3x3 double3x3(Double3x3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double3x4 double3x4(Double3x4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double4x4 double4x4(Double4x4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double4x3 double4x3(Double4x3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double4x2 double4x2(Double4x2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double2x4 double2x4(Double2x4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static Double3x2 double3x2(Double3x2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoubleQuat doubleQuat(DoubleQuat src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoubleDualQuat doubleDualQuat(DoubleDualQuat src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoubleRect doubleRect(DoubleRect src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoubleTransform doubleTransform(DoubleTransform src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoubleRigid doubleRigid(DoubleRigid src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoubleOBB doubleOBB(DoubleOBB src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoubleAABB doubleAABB(DoubleAABB src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoubleSphere doubleSphere(DoubleSphere src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoubleRay doubleRay(DoubleRay src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoublePlane doublePlane(DoublePlane src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to return
     */
    public static DoubleTriangle doubleTriangle(DoubleTriangle src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Byte2 byte2(Byte2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Byte3 byte3(Byte3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Byte4 byte4(Byte4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Short2 short2(Short2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Short3 short3(Short3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Short4 short4(Short4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Int2 int2(Int2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Int3 int3(Int3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Int4 int4(Int4 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static IntRect intRect(IntRect src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Long2 long2(Long2 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Long3 long3(Long3 src) { return src; }
    /**
     * {@return {@code src} itself - records are immutable, so no copy is needed}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to return
     */
    public static Long4 long4(Long4 src) { return src; }
}
