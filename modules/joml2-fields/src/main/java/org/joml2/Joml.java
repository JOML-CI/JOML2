// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.types.*;
import org.joml2.internal.unsafe.UnsafeOpsHolder;

/**
 * Factory for every generated math type ({@code Joml.float4x4()},
 * {@code Joml.float3(1, 2, 3)}, ...) plus the library's global flags and the
 * matrix property bit masks.
 *
 * <p>The generated implementation classes are not part of the public API;
 * instances are obtained exclusively through these factory methods.</p>
 */
public final class Joml {
    private Joml() {}

    // Freeze the JomlConfig overrides before any flag below reads one.
    static {
        JomlConfig.freeze();
    }

    /** Property bit mask: the matrix is known to be affine (last row {@code 0..0 1}). */
    public static final int BIT_AFFINE = 1;

    /** Property bit mask: the matrix is known to be orthogonal, i.e. its upper-left block is orthonormal with positive determinant (a proper rotation; a reflection is affine, not orthogonal). Includes {@link #BIT_AFFINE}. */
    public static final int BIT_ORTHOGONAL = 3;

    /** Property bit mask: the matrix is known to be a pure translation. Includes {@link #BIT_ORTHOGONAL} (a pure translation is rigid). */
    public static final int BIT_TRANSLATION = 7;

    /** Property bit mask: the matrix is known to be the identity. Includes all weaker masks' bits. */
    public static final int BIT_IDENTITY = 15;

    /** The single bit that distinguishes {@link #BIT_AFFINE} from no known properties. */
    public static final int UNIQUE_AFFINE = 1;

    /** The single bit that distinguishes {@link #BIT_ORTHOGONAL} from the next-weaker mask. */
    public static final int UNIQUE_ORTHOGONAL = 2;

    /** The single bit that distinguishes {@link #BIT_TRANSLATION} from the next-weaker mask. */
    public static final int UNIQUE_TRANSLATION = 4;

    /** The single bit that distinguishes {@link #BIT_IDENTITY} from the next-weaker mask. */
    public static final int UNIQUE_IDENTITY = 8;

    /** Whether the computing no-dest self-form operations ({@code v.add(o)}, {@code m.mul(n)},
     *  {@code q.normalize()}, ...) leave {@code this} unchanged and return a freshly allocated
     *  instance holding the result instead of mutating and returning {@code this}. The
     *  {@code set*}, {@code make*}, {@code load*}, {@code composeTRS*} and {@code targetTo} methods
     *  are setters, not computations: they always mutate and return {@code this}. Resolved once at class
     *  initialization from {@link JomlConfig#setReturnNew} or the {@code joml.returnNew} system
     *  property; accepted spellings are {@code -Djoml.returnNew=true} / {@code =false}
     *  (case-insensitive) and a bare {@code -Djoml.returnNew}, which means {@code true}; any other
     *  value logs one warning on {@code System.err} and keeps the default {@code false}. */
    public static final boolean RETURN_NEW = resolveReturnNew();

    /** The JomlConfig override, else {@code -Djoml.returnNew} - a bare one (present, empty value) enables the
     *  mode, like {@code -Djoml.strictMath}. */
    private static boolean resolveReturnNew() {
        Boolean o = JomlConfig.returnNewOverride;
        if (o != null) return o;
        String s = System.getProperty("joml.returnNew");
        if (s == null) return false;
        String v = s.trim();
        if (v.isEmpty() || v.equalsIgnoreCase("true")) return true;
        if (v.equalsIgnoreCase("false")) return false;
        System.err.println("[org.joml2] unrecognised -Djoml.returnNew=" + s
            + " (expected true or false); using the default false");
        return false;
    }

    /** {@return the store/load backend} Resolved from {@link JomlConfig#setStoreLoadBackend} or
     *  {@code -Djoml.storeLoadBackend} ({@code api} or {@code unsafe}, case-insensitive); defaults
     *  to {@link StoreLoadBackend#UNSAFE} when an {@code Unsafe} is available. Requesting
     *  {@code unsafe} on a JVM without one, or an unrecognised value, logs one warning on
     *  {@code System.err} and uses the API backend (respectively the default) instead of failing
     *  later.
     *
     *  <p>The backend is resolved once, on first use: the first store or load on a buffer, memory
     *  segment or raw address, the first {@code *Ops} call on one, or the first call of this
     *  method - not when {@code Joml} initializes, so a program that never stores to or loads from
     *  a buffer, segment or address (heap ones included) never probes {@code Unsafe}. The UNSAFE
     *  backend uses {@code jdk.internal.misc.Unsafe} where the JVM exports {@code jdk.internal.misc}
     *  to the library ({@code --add-exports java.base/jdk.internal.misc=org.joml2},
     *  {@code =ALL-UNNAMED} on the class path; see {@link StoreLoadBackend#UNSAFE}), else
     *  {@code sun.misc.Unsafe}, whose memory access is deprecated for removal (JEP 471); on JDK 24+
     *  its first use prints a warning (JEP 498) unless the JVM runs with
     *  {@code --sun-misc-unsafe-memory-access=allow}; {@code -Djoml.storeLoadBackend=api} avoids it.</p>
     * <p>
     * Valid input: the method reads no input.
     */
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
     *  when the module is absent. Always {@code false} in variants that ship scalar {@code *Ops}.
     *  <p>The SIMD kernels reach arrays through the Vector API, whose bounds checks
     *  {@code -Djdk.incubator.vector.VECTOR_ACCESS_OOB_CHECK=0} turns off: storage accessed
     *  outside its range (invalid input to every {@code *Ops} method, {@code copy} included)
     *  is then read or written past its end instead of throwing
     *  {@link IndexOutOfBoundsException}. */
    public static final boolean VECTOR_API = false;

    /** {@return a new {@code Float2}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Float2 float2() { return new Float2Impl(); }

    /** {@return a new {@code Float3}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Float3 float3() { return new Float3Impl(); }

    /** {@return a new {@code Float4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: the method reads no input. */
    public static Float4 float4() { return new Float4Impl(); }

    /** {@return a new {@code Float2x2}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Float2x2 float2x2() { return new Float2x2Impl(); }

    /** {@return a new {@code Float2x3}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Float2x3 float2x3() { return new Float2x3Impl(); }

    /** {@return a new {@code Float3x3}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Float3x3 float3x3() { return new Float3x3Impl(); }

    /** {@return a new {@code Float3x4}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Float3x4 float3x4() { return new Float3x4Impl(); }

    /** {@return a new {@code Float4x4}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Float4x4 float4x4() { return new Float4x4Impl(); }

    /** {@return a new {@code Float4x3}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Float4x3 float4x3() { return new Float4x3Impl(); }

    /** {@return a new {@code Float4x2}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Float4x2 float4x2() { return new Float4x2Impl(); }

    /** {@return a new {@code Float2x4}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Float2x4 float2x4() { return new Float2x4Impl(); }

    /** {@return a new {@code Float3x2}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Float3x2 float3x2() { return new Float3x2Impl(); }

    /** {@return a new {@code FloatQuat}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static FloatQuat floatQuat() { return new FloatQuatImpl(); }

    /** {@return a new {@code FloatDualQuat}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static FloatDualQuat floatDualQuat() { return new FloatDualQuatImpl(); }

    /** {@return a new {@code FloatRect}, initialized to empty inverted bounds (so any union starts from the first added geometry)} <p>Valid input: the method reads no input. */
    public static FloatRect floatRect() { return new FloatRectImpl(); }

    /** {@return a new {@code FloatTransform}, initialized to the identity transform} <p>Valid input: the method reads no input. */
    public static FloatTransform floatTransform() { return new FloatTransformImpl(); }

    /** {@return a new {@code FloatRigid}, initialized to the identity transform} <p>Valid input: the method reads no input. */
    public static FloatRigid floatRigid() { return new FloatRigidImpl(); }

    /** {@return a new {@code FloatOBB}, initialized to a degenerate box with identity orientation and zero center and extents} <p>Valid input: the method reads no input. */
    public static FloatOBB floatOBB() { return new FloatOBBImpl(); }

    /** {@return a new {@code FloatAABB}, initialized to empty inverted bounds (so any union starts from the first added geometry)} <p>Valid input: the method reads no input. */
    public static FloatAABB floatAABB() { return new FloatAABBImpl(); }

    /** {@return a new {@code FloatSphere}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static FloatSphere floatSphere() { return new FloatSphereImpl(); }

    /** {@return a new {@code FloatRay}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static FloatRay floatRay() { return new FloatRayImpl(); }

    /** {@return a new {@code FloatPlane}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static FloatPlane floatPlane() { return new FloatPlaneImpl(); }

    /** {@return a new {@code FloatTriangle}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static FloatTriangle floatTriangle() { return new FloatTriangleImpl(); }

    /** {@return a new {@code Double2}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Double2 double2() { return new Double2Impl(); }

    /** {@return a new {@code Double3}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Double3 double3() { return new Double3Impl(); }

    /** {@return a new {@code Double4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: the method reads no input. */
    public static Double4 double4() { return new Double4Impl(); }

    /** {@return a new {@code Double2x2}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Double2x2 double2x2() { return new Double2x2Impl(); }

    /** {@return a new {@code Double2x3}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Double2x3 double2x3() { return new Double2x3Impl(); }

    /** {@return a new {@code Double3x3}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Double3x3 double3x3() { return new Double3x3Impl(); }

    /** {@return a new {@code Double3x4}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Double3x4 double3x4() { return new Double3x4Impl(); }

    /** {@return a new {@code Double4x4}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Double4x4 double4x4() { return new Double4x4Impl(); }

    /** {@return a new {@code Double4x3}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Double4x3 double4x3() { return new Double4x3Impl(); }

    /** {@return a new {@code Double4x2}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Double4x2 double4x2() { return new Double4x2Impl(); }

    /** {@return a new {@code Double2x4}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Double2x4 double2x4() { return new Double2x4Impl(); }

    /** {@return a new {@code Double3x2}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static Double3x2 double3x2() { return new Double3x2Impl(); }

    /** {@return a new {@code DoubleQuat}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static DoubleQuat doubleQuat() { return new DoubleQuatImpl(); }

    /** {@return a new {@code DoubleDualQuat}, initialized to the identity} <p>Valid input: the method reads no input. */
    public static DoubleDualQuat doubleDualQuat() { return new DoubleDualQuatImpl(); }

    /** {@return a new {@code DoubleRect}, initialized to empty inverted bounds (so any union starts from the first added geometry)} <p>Valid input: the method reads no input. */
    public static DoubleRect doubleRect() { return new DoubleRectImpl(); }

    /** {@return a new {@code DoubleTransform}, initialized to the identity transform} <p>Valid input: the method reads no input. */
    public static DoubleTransform doubleTransform() { return new DoubleTransformImpl(); }

    /** {@return a new {@code DoubleRigid}, initialized to the identity transform} <p>Valid input: the method reads no input. */
    public static DoubleRigid doubleRigid() { return new DoubleRigidImpl(); }

    /** {@return a new {@code DoubleOBB}, initialized to a degenerate box with identity orientation and zero center and extents} <p>Valid input: the method reads no input. */
    public static DoubleOBB doubleOBB() { return new DoubleOBBImpl(); }

    /** {@return a new {@code DoubleAABB}, initialized to empty inverted bounds (so any union starts from the first added geometry)} <p>Valid input: the method reads no input. */
    public static DoubleAABB doubleAABB() { return new DoubleAABBImpl(); }

    /** {@return a new {@code DoubleSphere}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static DoubleSphere doubleSphere() { return new DoubleSphereImpl(); }

    /** {@return a new {@code DoubleRay}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static DoubleRay doubleRay() { return new DoubleRayImpl(); }

    /** {@return a new {@code DoublePlane}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static DoublePlane doublePlane() { return new DoublePlaneImpl(); }

    /** {@return a new {@code DoubleTriangle}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static DoubleTriangle doubleTriangle() { return new DoubleTriangleImpl(); }

    /** {@return a new {@code Byte2}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Byte2 byte2() { return new Byte2Impl(); }

    /** {@return a new {@code Byte3}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Byte3 byte3() { return new Byte3Impl(); }

    /** {@return a new {@code Byte4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: the method reads no input. */
    public static Byte4 byte4() { return new Byte4Impl(); }

    /** {@return a new {@code Short2}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Short2 short2() { return new Short2Impl(); }

    /** {@return a new {@code Short3}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Short3 short3() { return new Short3Impl(); }

    /** {@return a new {@code Short4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: the method reads no input. */
    public static Short4 short4() { return new Short4Impl(); }

    /** {@return a new {@code Int2}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Int2 int2() { return new Int2Impl(); }

    /** {@return a new {@code Int3}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Int3 int3() { return new Int3Impl(); }

    /** {@return a new {@code Int4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: the method reads no input. */
    public static Int4 int4() { return new Int4Impl(); }

    /** {@return a new {@code IntRect}, initialized to empty inverted bounds (so any union starts from the first added geometry)} <p>Valid input: the method reads no input. */
    public static IntRect intRect() { return new IntRectImpl(); }

    /** {@return a new {@code Long2}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Long2 long2() { return new Long2Impl(); }

    /** {@return a new {@code Long3}, initialized to all zeros} <p>Valid input: the method reads no input. */
    public static Long3 long3() { return new Long3Impl(); }

    /** {@return a new {@code Long4}, initialized to the homogeneous default {@code (0, 0, 0, 1)}} <p>Valid input: the method reads no input. */
    public static Long4 long4() { return new Long4Impl(); }

    /**
     * {@return a new {@code Float2} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Float2 float2(float x, float y) { return new Float2Impl(x, y); }

    /**
     * {@return a new {@code Float3} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Float3 float3(float x, float y, float z) { return new Float3Impl(x, y, z); }

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
    public static Float4 float4(float x, float y, float z, float w) { return new Float4Impl(x, y, z, w); }

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
    public static Float2x2 float2x2(float m00, float m01, float m10, float m11) { return new Float2x2Impl(m00, m01, m10, m11); }

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
    public static Float2x3 float2x3(float m00, float m01, float m02, float m10, float m11, float m12) { return new Float2x3Impl(m00, m01, m02, m10, m11, m12); }

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
    public static Float3x3 float3x3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) { return new Float3x3Impl(m00, m01, m02, m10, m11, m12, m20, m21, m22); }

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
    public static Float3x4 float3x4(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23) { return new Float3x4Impl(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23); }

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
    public static Float4x4 float4x4(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33) { return new Float4x4Impl(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33); }

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
    public static Float4x3 float4x3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32) { return new Float4x3Impl(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32); }

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
    public static Float4x2 float4x2(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31) { return new Float4x2Impl(m00, m01, m10, m11, m20, m21, m30, m31); }

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
    public static Float2x4 float2x4(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13) { return new Float2x4Impl(m00, m01, m02, m03, m10, m11, m12, m13); }

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
    public static Float3x2 float3x2(float m00, float m01, float m10, float m11, float m20, float m21) { return new Float3x2Impl(m00, m01, m10, m11, m20, m21); }

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
    public static FloatQuat floatQuat(float x, float y, float z, float w) { return new FloatQuatImpl(x, y, z, w); }

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
    public static FloatDualQuat floatDualQuat(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW) { return new FloatDualQuatImpl(rX, rY, rZ, rW, dX, dY, dZ, dW); }

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
    public static FloatRect floatRect(float minX, float minY, float maxX, float maxY) { return new FloatRectImpl(minX, minY, maxX, maxY); }

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
    public static FloatTransform floatTransform(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW, float sX, float sY, float sZ) { return new FloatTransformImpl(tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ); }

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
    public static FloatRigid floatRigid(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW) { return new FloatRigidImpl(tX, tY, tZ, rX, rY, rZ, rW); }

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
    public static FloatOBB floatOBB(float cX, float cY, float cZ, float uXx, float uXy, float uXz, float uYx, float uYy, float uYz, float uZx, float uZy, float uZz, float hsX, float hsY, float hsZ) { return new FloatOBBImpl(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ); }

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
    public static FloatAABB floatAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) { return new FloatAABBImpl(minX, minY, minZ, maxX, maxY, maxZ); }

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
    public static FloatSphere floatSphere(float x, float y, float z, float r) { return new FloatSphereImpl(x, y, z, r); }

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
    public static FloatRay floatRay(float oX, float oY, float oZ, float dX, float dY, float dZ) { return new FloatRayImpl(oX, oY, oZ, dX, dY, dZ); }

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
    public static FloatPlane floatPlane(float a, float b, float c, float d) { return new FloatPlaneImpl(a, b, c, d); }

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
    public static FloatTriangle floatTriangle(float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z) { return new FloatTriangleImpl(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z); }

    /**
     * {@return a new {@code Double2} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Double2 double2(double x, double y) { return new Double2Impl(x, y); }

    /**
     * {@return a new {@code Double3} initialized to the given component values}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Double3 double3(double x, double y, double z) { return new Double3Impl(x, y, z); }

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
    public static Double4 double4(double x, double y, double z, double w) { return new Double4Impl(x, y, z, w); }

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
    public static Double2x2 double2x2(double m00, double m01, double m10, double m11) { return new Double2x2Impl(m00, m01, m10, m11); }

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
    public static Double2x3 double2x3(double m00, double m01, double m02, double m10, double m11, double m12) { return new Double2x3Impl(m00, m01, m02, m10, m11, m12); }

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
    public static Double3x3 double3x3(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) { return new Double3x3Impl(m00, m01, m02, m10, m11, m12, m20, m21, m22); }

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
    public static Double3x4 double3x4(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23) { return new Double3x4Impl(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23); }

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
    public static Double4x4 double4x4(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) { return new Double4x4Impl(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33); }

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
    public static Double4x3 double4x3(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double m30, double m31, double m32) { return new Double4x3Impl(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32); }

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
    public static Double4x2 double4x2(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31) { return new Double4x2Impl(m00, m01, m10, m11, m20, m21, m30, m31); }

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
    public static Double2x4 double2x4(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) { return new Double2x4Impl(m00, m01, m02, m03, m10, m11, m12, m13); }

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
    public static Double3x2 double3x2(double m00, double m01, double m10, double m11, double m20, double m21) { return new Double3x2Impl(m00, m01, m10, m11, m20, m21); }

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
    public static DoubleQuat doubleQuat(double x, double y, double z, double w) { return new DoubleQuatImpl(x, y, z, w); }

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
    public static DoubleDualQuat doubleDualQuat(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) { return new DoubleDualQuatImpl(rX, rY, rZ, rW, dX, dY, dZ, dW); }

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
    public static DoubleRect doubleRect(double minX, double minY, double maxX, double maxY) { return new DoubleRectImpl(minX, minY, maxX, maxY); }

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
    public static DoubleTransform doubleTransform(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW, double sX, double sY, double sZ) { return new DoubleTransformImpl(tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ); }

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
    public static DoubleRigid doubleRigid(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW) { return new DoubleRigidImpl(tX, tY, tZ, rX, rY, rZ, rW); }

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
    public static DoubleOBB doubleOBB(double cX, double cY, double cZ, double uXx, double uXy, double uXz, double uYx, double uYy, double uYz, double uZx, double uZy, double uZz, double hsX, double hsY, double hsZ) { return new DoubleOBBImpl(cX, cY, cZ, uXx, uXy, uXz, uYx, uYy, uYz, uZx, uZy, uZz, hsX, hsY, hsZ); }

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
    public static DoubleAABB doubleAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) { return new DoubleAABBImpl(minX, minY, minZ, maxX, maxY, maxZ); }

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
    public static DoubleSphere doubleSphere(double x, double y, double z, double r) { return new DoubleSphereImpl(x, y, z, r); }

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
    public static DoubleRay doubleRay(double oX, double oY, double oZ, double dX, double dY, double dZ) { return new DoubleRayImpl(oX, oY, oZ, dX, dY, dZ); }

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
    public static DoublePlane doublePlane(double a, double b, double c, double d) { return new DoublePlaneImpl(a, b, c, d); }

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
    public static DoubleTriangle doubleTriangle(double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z) { return new DoubleTriangleImpl(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z); }

    /**
     * {@return a new {@code Byte2} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Byte2 byte2(byte x, byte y) { return new Byte2Impl(x, y); }

    /**
     * {@return a new {@code Byte3} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Byte3 byte3(byte x, byte y, byte z) { return new Byte3Impl(x, y, z); }

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
    public static Byte4 byte4(byte x, byte y, byte z, byte w) { return new Byte4Impl(x, y, z, w); }

    /**
     * {@return a new {@code Short2} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Short2 short2(short x, short y) { return new Short2Impl(x, y); }

    /**
     * {@return a new {@code Short3} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Short3 short3(short x, short y, short z) { return new Short3Impl(x, y, z); }

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
    public static Short4 short4(short x, short y, short z, short w) { return new Short4Impl(x, y, z, w); }

    /**
     * {@return a new {@code Int2} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Int2 int2(int x, int y) { return new Int2Impl(x, y); }

    /**
     * {@return a new {@code Int3} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Int3 int3(int x, int y, int z) { return new Int3Impl(x, y, z); }

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
    public static Int4 int4(int x, int y, int z, int w) { return new Int4Impl(x, y, z, w); }

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
    public static IntRect intRect(int minX, int minY, int maxX, int maxY) { return new IntRectImpl(minX, minY, maxX, maxY); }

    /**
     * {@return a new {@code Long2} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     */
    public static Long2 long2(long x, long y) { return new Long2Impl(x, y); }

    /**
     * {@return a new {@code Long3} initialized to the given component values}
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     */
    public static Long3 long3(long x, long y, long z) { return new Long3Impl(x, y, z); }

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
    public static Long4 long4(long x, long y, long z, long w) { return new Long4Impl(x, y, z, w); }

    /**
     * {@return a new {@code Float2} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Float2 float2(float s) { return new Float2Impl(s, s); }

    /**
     * {@return a new {@code Float3} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Float3 float3(float s) { return new Float3Impl(s, s, s); }

    /**
     * {@return a new {@code Float4} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Float4 float4(float s) { return new Float4Impl(s, s, s, s); }

    /**
     * {@return a new {@code Double2} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Double2 double2(double s) { return new Double2Impl(s, s); }

    /**
     * {@return a new {@code Double3} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Double3 double3(double s) { return new Double3Impl(s, s, s); }

    /**
     * {@return a new {@code Double4} with all components set to {@code s}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param s the value assigned to every component
     */
    public static Double4 double4(double s) { return new Double4Impl(s, s, s, s); }

    /**
     * {@return a new {@code Byte2} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Byte2 byte2(byte s) { return new Byte2Impl(s, s); }

    /**
     * {@return a new {@code Byte3} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Byte3 byte3(byte s) { return new Byte3Impl(s, s, s); }

    /**
     * {@return a new {@code Byte4} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Byte4 byte4(byte s) { return new Byte4Impl(s, s, s, s); }

    /**
     * {@return a new {@code Short2} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Short2 short2(short s) { return new Short2Impl(s, s); }

    /**
     * {@return a new {@code Short3} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Short3 short3(short s) { return new Short3Impl(s, s, s); }

    /**
     * {@return a new {@code Short4} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Short4 short4(short s) { return new Short4Impl(s, s, s, s); }

    /**
     * {@return a new {@code Int2} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Int2 int2(int s) { return new Int2Impl(s, s); }

    /**
     * {@return a new {@code Int3} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Int3 int3(int s) { return new Int3Impl(s, s, s); }

    /**
     * {@return a new {@code Int4} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Int4 int4(int s) { return new Int4Impl(s, s, s, s); }

    /**
     * {@return a new {@code Long2} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Long2 long2(long s) { return new Long2Impl(s, s); }

    /**
     * {@return a new {@code Long3} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Long3 long3(long s) { return new Long3Impl(s, s, s); }

    /**
     * {@return a new {@code Long4} with all components set to {@code s}}
     * <p>
     * Valid input: any value.
     *
     * @param s the value assigned to every component
     */
    public static Long4 long4(long s) { return new Long4Impl(s, s, s, s); }

    /**
     * {@return a new {@code Float3} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Float3 float3(float v0, Float2R v1) {
        Float2Impl v1Impl = (Float2Impl) v1;
        return new Float3Impl(v0, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Float3} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Float3 float3(Float2R v0, float v1) {
        Float2Impl v0Impl = (Float2Impl) v0;
        return new Float3Impl(v0Impl.x, v0Impl.y, v1);
    }

    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Float4 float4(float v0, float v1, Float2R v2) {
        Float2Impl v2Impl = (Float2Impl) v2;
        return new Float4Impl(v0, v1, v2Impl.x, v2Impl.y);
    }

    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Float4 float4(float v0, Float2R v1, float v2) {
        Float2Impl v1Impl = (Float2Impl) v1;
        return new Float4Impl(v0, v1Impl.x, v1Impl.y, v2);
    }

    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Float4 float4(float v0, Float3R v1) {
        Float3Impl v1Impl = (Float3Impl) v1;
        return new Float4Impl(v0, v1Impl.x, v1Impl.y, v1Impl.z);
    }

    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Float4 float4(Float2R v0, float v1, float v2) {
        Float2Impl v0Impl = (Float2Impl) v0;
        return new Float4Impl(v0Impl.x, v0Impl.y, v1, v2);
    }

    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Float4 float4(Float2R v0, Float2R v1) {
        Float2Impl v0Impl = (Float2Impl) v0;
        Float2Impl v1Impl = (Float2Impl) v1;
        return new Float4Impl(v0Impl.x, v0Impl.y, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Float4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Float4 float4(Float3R v0, float v1) {
        Float3Impl v0Impl = (Float3Impl) v0;
        return new Float4Impl(v0Impl.x, v0Impl.y, v0Impl.z, v1);
    }

    /**
     * {@return a new {@code Double3} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Double3 double3(double v0, Double2R v1) {
        Double2Impl v1Impl = (Double2Impl) v1;
        return new Double3Impl(v0, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Double3} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Double3 double3(Double2R v0, double v1) {
        Double2Impl v0Impl = (Double2Impl) v0;
        return new Double3Impl(v0Impl.x, v0Impl.y, v1);
    }

    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Double4 double4(double v0, double v1, Double2R v2) {
        Double2Impl v2Impl = (Double2Impl) v2;
        return new Double4Impl(v0, v1, v2Impl.x, v2Impl.y);
    }

    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Double4 double4(double v0, Double2R v1, double v2) {
        Double2Impl v1Impl = (Double2Impl) v1;
        return new Double4Impl(v0, v1Impl.x, v1Impl.y, v2);
    }

    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Double4 double4(double v0, Double3R v1) {
        Double3Impl v1Impl = (Double3Impl) v1;
        return new Double4Impl(v0, v1Impl.x, v1Impl.y, v1Impl.z);
    }

    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Double4 double4(Double2R v0, double v1, double v2) {
        Double2Impl v0Impl = (Double2Impl) v0;
        return new Double4Impl(v0Impl.x, v0Impl.y, v1, v2);
    }

    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Double4 double4(Double2R v0, Double2R v1) {
        Double2Impl v0Impl = (Double2Impl) v0;
        Double2Impl v1Impl = (Double2Impl) v1;
        return new Double4Impl(v0Impl.x, v0Impl.y, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Double4} composed of the given parts, in order}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Double4 double4(Double3R v0, double v1) {
        Double3Impl v0Impl = (Double3Impl) v0;
        return new Double4Impl(v0Impl.x, v0Impl.y, v0Impl.z, v1);
    }

    /**
     * {@return a new {@code Byte3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Byte3 byte3(byte v0, Byte2R v1) {
        Byte2Impl v1Impl = (Byte2Impl) v1;
        return new Byte3Impl(v0, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Byte3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Byte3 byte3(Byte2R v0, byte v1) {
        Byte2Impl v0Impl = (Byte2Impl) v0;
        return new Byte3Impl(v0Impl.x, v0Impl.y, v1);
    }

    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Byte4 byte4(byte v0, byte v1, Byte2R v2) {
        Byte2Impl v2Impl = (Byte2Impl) v2;
        return new Byte4Impl(v0, v1, v2Impl.x, v2Impl.y);
    }

    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Byte4 byte4(byte v0, Byte2R v1, byte v2) {
        Byte2Impl v1Impl = (Byte2Impl) v1;
        return new Byte4Impl(v0, v1Impl.x, v1Impl.y, v2);
    }

    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Byte4 byte4(byte v0, Byte3R v1) {
        Byte3Impl v1Impl = (Byte3Impl) v1;
        return new Byte4Impl(v0, v1Impl.x, v1Impl.y, v1Impl.z);
    }

    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Byte4 byte4(Byte2R v0, byte v1, byte v2) {
        Byte2Impl v0Impl = (Byte2Impl) v0;
        return new Byte4Impl(v0Impl.x, v0Impl.y, v1, v2);
    }

    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Byte4 byte4(Byte2R v0, Byte2R v1) {
        Byte2Impl v0Impl = (Byte2Impl) v0;
        Byte2Impl v1Impl = (Byte2Impl) v1;
        return new Byte4Impl(v0Impl.x, v0Impl.y, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Byte4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Byte4 byte4(Byte3R v0, byte v1) {
        Byte3Impl v0Impl = (Byte3Impl) v0;
        return new Byte4Impl(v0Impl.x, v0Impl.y, v0Impl.z, v1);
    }

    /**
     * {@return a new {@code Short3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Short3 short3(short v0, Short2R v1) {
        Short2Impl v1Impl = (Short2Impl) v1;
        return new Short3Impl(v0, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Short3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Short3 short3(Short2R v0, short v1) {
        Short2Impl v0Impl = (Short2Impl) v0;
        return new Short3Impl(v0Impl.x, v0Impl.y, v1);
    }

    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Short4 short4(short v0, short v1, Short2R v2) {
        Short2Impl v2Impl = (Short2Impl) v2;
        return new Short4Impl(v0, v1, v2Impl.x, v2Impl.y);
    }

    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Short4 short4(short v0, Short2R v1, short v2) {
        Short2Impl v1Impl = (Short2Impl) v1;
        return new Short4Impl(v0, v1Impl.x, v1Impl.y, v2);
    }

    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Short4 short4(short v0, Short3R v1) {
        Short3Impl v1Impl = (Short3Impl) v1;
        return new Short4Impl(v0, v1Impl.x, v1Impl.y, v1Impl.z);
    }

    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Short4 short4(Short2R v0, short v1, short v2) {
        Short2Impl v0Impl = (Short2Impl) v0;
        return new Short4Impl(v0Impl.x, v0Impl.y, v1, v2);
    }

    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Short4 short4(Short2R v0, Short2R v1) {
        Short2Impl v0Impl = (Short2Impl) v0;
        Short2Impl v1Impl = (Short2Impl) v1;
        return new Short4Impl(v0Impl.x, v0Impl.y, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Short4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Short4 short4(Short3R v0, short v1) {
        Short3Impl v0Impl = (Short3Impl) v0;
        return new Short4Impl(v0Impl.x, v0Impl.y, v0Impl.z, v1);
    }

    /**
     * {@return a new {@code Int3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Int3 int3(int v0, Int2R v1) {
        Int2Impl v1Impl = (Int2Impl) v1;
        return new Int3Impl(v0, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Int3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Int3 int3(Int2R v0, int v1) {
        Int2Impl v0Impl = (Int2Impl) v0;
        return new Int3Impl(v0Impl.x, v0Impl.y, v1);
    }

    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Int4 int4(int v0, int v1, Int2R v2) {
        Int2Impl v2Impl = (Int2Impl) v2;
        return new Int4Impl(v0, v1, v2Impl.x, v2Impl.y);
    }

    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Int4 int4(int v0, Int2R v1, int v2) {
        Int2Impl v1Impl = (Int2Impl) v1;
        return new Int4Impl(v0, v1Impl.x, v1Impl.y, v2);
    }

    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Int4 int4(int v0, Int3R v1) {
        Int3Impl v1Impl = (Int3Impl) v1;
        return new Int4Impl(v0, v1Impl.x, v1Impl.y, v1Impl.z);
    }

    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Int4 int4(Int2R v0, int v1, int v2) {
        Int2Impl v0Impl = (Int2Impl) v0;
        return new Int4Impl(v0Impl.x, v0Impl.y, v1, v2);
    }

    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Int4 int4(Int2R v0, Int2R v1) {
        Int2Impl v0Impl = (Int2Impl) v0;
        Int2Impl v1Impl = (Int2Impl) v1;
        return new Int4Impl(v0Impl.x, v0Impl.y, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Int4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Int4 int4(Int3R v0, int v1) {
        Int3Impl v0Impl = (Int3Impl) v0;
        return new Int4Impl(v0Impl.x, v0Impl.y, v0Impl.z, v1);
    }

    /**
     * {@return a new {@code Long3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     */
    public static Long3 long3(long v0, Long2R v1) {
        Long2Impl v1Impl = (Long2Impl) v1;
        return new Long3Impl(v0, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Long3} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     */
    public static Long3 long3(Long2R v0, long v1) {
        Long2Impl v0Impl = (Long2Impl) v0;
        return new Long3Impl(v0Impl.x, v0Impl.y, v1);
    }

    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} component
     * @param v2 the {@code z} and {@code w} components
     */
    public static Long4 long4(long v0, long v1, Long2R v2) {
        Long2Impl v2Impl = (Long2Impl) v2;
        return new Long4Impl(v0, v1, v2Impl.x, v2Impl.y);
    }

    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y} and {@code z} components
     * @param v2 the {@code w} component
     */
    public static Long4 long4(long v0, Long2R v1, long v2) {
        Long2Impl v1Impl = (Long2Impl) v1;
        return new Long4Impl(v0, v1Impl.x, v1Impl.y, v2);
    }

    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} component
     * @param v1 the {@code y}, {@code z} and {@code w} components
     */
    public static Long4 long4(long v0, Long3R v1) {
        Long3Impl v1Impl = (Long3Impl) v1;
        return new Long4Impl(v0, v1Impl.x, v1Impl.y, v1Impl.z);
    }

    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} component
     * @param v2 the {@code w} component
     */
    public static Long4 long4(Long2R v0, long v1, long v2) {
        Long2Impl v0Impl = (Long2Impl) v0;
        return new Long4Impl(v0Impl.x, v0Impl.y, v1, v2);
    }

    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x} and {@code y} components
     * @param v1 the {@code z} and {@code w} components
     */
    public static Long4 long4(Long2R v0, Long2R v1) {
        Long2Impl v0Impl = (Long2Impl) v0;
        Long2Impl v1Impl = (Long2Impl) v1;
        return new Long4Impl(v0Impl.x, v0Impl.y, v1Impl.x, v1Impl.y);
    }

    /**
     * {@return a new {@code Long4} composed of the given parts, in order}
     * <p>
     * Valid input: any value.
     *
     * @param v0 the {@code x}, {@code y} and {@code z} components
     * @param v1 the {@code w} component
     */
    public static Long4 long4(Long3R v0, long v1) {
        Long3Impl v0Impl = (Long3Impl) v0;
        return new Long4Impl(v0Impl.x, v0Impl.y, v0Impl.z, v1);
    }

    /**
     * {@return a new {@code FloatDualQuat} built from its real and dual parts}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param real the real part, i.e. the rotation quaternion
     * @param dual the dual part, carrying the translation
     */
    public static FloatDualQuat floatDualQuat(FloatQuatR real, FloatQuatR dual) {
        FloatQuatImpl realImpl = (FloatQuatImpl) real;
        FloatQuatImpl dualImpl = (FloatQuatImpl) dual;
        return new FloatDualQuatImpl(realImpl.x, realImpl.y, realImpl.z, realImpl.w, dualImpl.x, dualImpl.y, dualImpl.z, dualImpl.w);
    }

    /**
     * {@return a new {@code FloatRect} built from its minimum and maximum corners}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the rectangle
     * @param max the maximum corner of the rectangle
     */
    public static FloatRect floatRect(Float2R min, Float2R max) {
        Float2Impl minImpl = (Float2Impl) min;
        Float2Impl maxImpl = (Float2Impl) max;
        return new FloatRectImpl(minImpl.x, minImpl.y, maxImpl.x, maxImpl.y);
    }

    /**
     * {@return a new {@code FloatTransform} built from its translation, rotation and scale}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     * @param scale the scale along each local axis
     */
    public static FloatTransform floatTransform(Float3R translation, FloatQuatR rotation, Float3R scale) {
        Float3Impl translationImpl = (Float3Impl) translation;
        FloatQuatImpl rotationImpl = (FloatQuatImpl) rotation;
        Float3Impl scaleImpl = (Float3Impl) scale;
        return new FloatTransformImpl(translationImpl.x, translationImpl.y, translationImpl.z, rotationImpl.x, rotationImpl.y, rotationImpl.z, rotationImpl.w, scaleImpl.x, scaleImpl.y, scaleImpl.z);
    }

    /**
     * {@return a new {@code FloatRigid} built from its translation and rotation}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     */
    public static FloatRigid floatRigid(Float3R translation, FloatQuatR rotation) {
        Float3Impl translationImpl = (Float3Impl) translation;
        FloatQuatImpl rotationImpl = (FloatQuatImpl) rotation;
        return new FloatRigidImpl(translationImpl.x, translationImpl.y, translationImpl.z, rotationImpl.x, rotationImpl.y, rotationImpl.z, rotationImpl.w);
    }

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
    public static FloatOBB floatOBB(Float3R center, Float3R axisX, Float3R axisY, Float3R axisZ, Float3R halfSize) {
        Float3Impl centerImpl = (Float3Impl) center;
        Float3Impl axisXImpl = (Float3Impl) axisX;
        Float3Impl axisYImpl = (Float3Impl) axisY;
        Float3Impl axisZImpl = (Float3Impl) axisZ;
        Float3Impl halfSizeImpl = (Float3Impl) halfSize;
        return new FloatOBBImpl(centerImpl.x, centerImpl.y, centerImpl.z, axisXImpl.x, axisXImpl.y, axisXImpl.z, axisYImpl.x, axisYImpl.y, axisYImpl.z, axisZImpl.x, axisZImpl.y, axisZImpl.z, halfSizeImpl.x, halfSizeImpl.y, halfSizeImpl.z);
    }

    /**
     * {@return a new {@code FloatAABB} built from its minimum and maximum corners}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     */
    public static FloatAABB floatAABB(Float3R min, Float3R max) {
        Float3Impl minImpl = (Float3Impl) min;
        Float3Impl maxImpl = (Float3Impl) max;
        return new FloatAABBImpl(minImpl.x, minImpl.y, minImpl.z, maxImpl.x, maxImpl.y, maxImpl.z);
    }

    /**
     * {@return a new {@code FloatSphere} built from its center and radius}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     */
    public static FloatSphere floatSphere(Float3R center, float radius) {
        Float3Impl centerImpl = (Float3Impl) center;
        return new FloatSphereImpl(centerImpl.x, centerImpl.y, centerImpl.z, radius);
    }

    /**
     * {@return a new {@code FloatRay} built from its origin and direction}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param origin the origin of the ray
     * @param direction the direction of the ray, taken as given (not normalized)
     */
    public static FloatRay floatRay(Float3R origin, Float3R direction) {
        Float3Impl originImpl = (Float3Impl) origin;
        Float3Impl directionImpl = (Float3Impl) direction;
        return new FloatRayImpl(originImpl.x, originImpl.y, originImpl.z, directionImpl.x, directionImpl.y, directionImpl.z);
    }

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
    public static FloatPlane floatPlane(Float3R normal, float d) {
        Float3Impl normalImpl = (Float3Impl) normal;
        return new FloatPlaneImpl(normalImpl.x, normalImpl.y, normalImpl.z, d);
    }

    /**
     * {@return a new {@code FloatTriangle} built from its three vertices}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the first vertex
     * @param v1 the second vertex
     * @param v2 the third vertex
     */
    public static FloatTriangle floatTriangle(Float3R v0, Float3R v1, Float3R v2) {
        Float3Impl v0Impl = (Float3Impl) v0;
        Float3Impl v1Impl = (Float3Impl) v1;
        Float3Impl v2Impl = (Float3Impl) v2;
        return new FloatTriangleImpl(v0Impl.x, v0Impl.y, v0Impl.z, v1Impl.x, v1Impl.y, v1Impl.z, v2Impl.x, v2Impl.y, v2Impl.z);
    }

    /**
     * {@return a new {@code DoubleDualQuat} built from its real and dual parts}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param real the real part, i.e. the rotation quaternion
     * @param dual the dual part, carrying the translation
     */
    public static DoubleDualQuat doubleDualQuat(DoubleQuatR real, DoubleQuatR dual) {
        DoubleQuatImpl realImpl = (DoubleQuatImpl) real;
        DoubleQuatImpl dualImpl = (DoubleQuatImpl) dual;
        return new DoubleDualQuatImpl(realImpl.x, realImpl.y, realImpl.z, realImpl.w, dualImpl.x, dualImpl.y, dualImpl.z, dualImpl.w);
    }

    /**
     * {@return a new {@code DoubleRect} built from its minimum and maximum corners}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the rectangle
     * @param max the maximum corner of the rectangle
     */
    public static DoubleRect doubleRect(Double2R min, Double2R max) {
        Double2Impl minImpl = (Double2Impl) min;
        Double2Impl maxImpl = (Double2Impl) max;
        return new DoubleRectImpl(minImpl.x, minImpl.y, maxImpl.x, maxImpl.y);
    }

    /**
     * {@return a new {@code DoubleTransform} built from its translation, rotation and scale}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     * @param scale the scale along each local axis
     */
    public static DoubleTransform doubleTransform(Double3R translation, DoubleQuatR rotation, Double3R scale) {
        Double3Impl translationImpl = (Double3Impl) translation;
        DoubleQuatImpl rotationImpl = (DoubleQuatImpl) rotation;
        Double3Impl scaleImpl = (Double3Impl) scale;
        return new DoubleTransformImpl(translationImpl.x, translationImpl.y, translationImpl.z, rotationImpl.x, rotationImpl.y, rotationImpl.z, rotationImpl.w, scaleImpl.x, scaleImpl.y, scaleImpl.z);
    }

    /**
     * {@return a new {@code DoubleRigid} built from its translation and rotation}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     */
    public static DoubleRigid doubleRigid(Double3R translation, DoubleQuatR rotation) {
        Double3Impl translationImpl = (Double3Impl) translation;
        DoubleQuatImpl rotationImpl = (DoubleQuatImpl) rotation;
        return new DoubleRigidImpl(translationImpl.x, translationImpl.y, translationImpl.z, rotationImpl.x, rotationImpl.y, rotationImpl.z, rotationImpl.w);
    }

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
    public static DoubleOBB doubleOBB(Double3R center, Double3R axisX, Double3R axisY, Double3R axisZ, Double3R halfSize) {
        Double3Impl centerImpl = (Double3Impl) center;
        Double3Impl axisXImpl = (Double3Impl) axisX;
        Double3Impl axisYImpl = (Double3Impl) axisY;
        Double3Impl axisZImpl = (Double3Impl) axisZ;
        Double3Impl halfSizeImpl = (Double3Impl) halfSize;
        return new DoubleOBBImpl(centerImpl.x, centerImpl.y, centerImpl.z, axisXImpl.x, axisXImpl.y, axisXImpl.z, axisYImpl.x, axisYImpl.y, axisYImpl.z, axisZImpl.x, axisZImpl.y, axisZImpl.z, halfSizeImpl.x, halfSizeImpl.y, halfSizeImpl.z);
    }

    /**
     * {@return a new {@code DoubleAABB} built from its minimum and maximum corners}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     */
    public static DoubleAABB doubleAABB(Double3R min, Double3R max) {
        Double3Impl minImpl = (Double3Impl) min;
        Double3Impl maxImpl = (Double3Impl) max;
        return new DoubleAABBImpl(minImpl.x, minImpl.y, minImpl.z, maxImpl.x, maxImpl.y, maxImpl.z);
    }

    /**
     * {@return a new {@code DoubleSphere} built from its center and radius}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     */
    public static DoubleSphere doubleSphere(Double3R center, double radius) {
        Double3Impl centerImpl = (Double3Impl) center;
        return new DoubleSphereImpl(centerImpl.x, centerImpl.y, centerImpl.z, radius);
    }

    /**
     * {@return a new {@code DoubleRay} built from its origin and direction}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param origin the origin of the ray
     * @param direction the direction of the ray, taken as given (not normalized)
     */
    public static DoubleRay doubleRay(Double3R origin, Double3R direction) {
        Double3Impl originImpl = (Double3Impl) origin;
        Double3Impl directionImpl = (Double3Impl) direction;
        return new DoubleRayImpl(originImpl.x, originImpl.y, originImpl.z, directionImpl.x, directionImpl.y, directionImpl.z);
    }

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
    public static DoublePlane doublePlane(Double3R normal, double d) {
        Double3Impl normalImpl = (Double3Impl) normal;
        return new DoublePlaneImpl(normalImpl.x, normalImpl.y, normalImpl.z, d);
    }

    /**
     * {@return a new {@code DoubleTriangle} built from its three vertices}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the first vertex
     * @param v1 the second vertex
     * @param v2 the third vertex
     */
    public static DoubleTriangle doubleTriangle(Double3R v0, Double3R v1, Double3R v2) {
        Double3Impl v0Impl = (Double3Impl) v0;
        Double3Impl v1Impl = (Double3Impl) v1;
        Double3Impl v2Impl = (Double3Impl) v2;
        return new DoubleTriangleImpl(v0Impl.x, v0Impl.y, v0Impl.z, v1Impl.x, v1Impl.y, v1Impl.z, v2Impl.x, v2Impl.y, v2Impl.z);
    }

    /**
     * {@return a new {@code IntRect} built from its minimum and maximum corners}
     * <p>
     * Valid input: any value.
     *
     * @param min the minimum corner of the rectangle
     * @param max the maximum corner of the rectangle
     */
    public static IntRect intRect(Int2R min, Int2R max) {
        Int2Impl minImpl = (Int2Impl) min;
        Int2Impl maxImpl = (Int2Impl) max;
        return new IntRectImpl(minImpl.x, minImpl.y, maxImpl.x, maxImpl.y);
    }

    /**
     * {@return a new {@code Float2x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Float2x2 float2x2(Float2R c0, Float2R c1) {
        Float2Impl c0Impl = (Float2Impl) c0;
        Float2Impl c1Impl = (Float2Impl) c1;
        return new Float2x2Impl(c0Impl.x, c1Impl.x, c0Impl.y, c1Impl.y);
    }

    /**
     * {@return a new {@code Float2x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Float2x3 float2x3(Float2R c0, Float2R c1, Float2R c2) {
        Float2Impl c0Impl = (Float2Impl) c0;
        Float2Impl c1Impl = (Float2Impl) c1;
        Float2Impl c2Impl = (Float2Impl) c2;
        return new Float2x3Impl(c0Impl.x, c1Impl.x, c2Impl.x, c0Impl.y, c1Impl.y, c2Impl.y);
    }

    /**
     * {@return a new {@code Float3x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Float3x3 float3x3(Float3R c0, Float3R c1, Float3R c2) {
        Float3Impl c0Impl = (Float3Impl) c0;
        Float3Impl c1Impl = (Float3Impl) c1;
        Float3Impl c2Impl = (Float3Impl) c2;
        return new Float3x3Impl(c0Impl.x, c1Impl.x, c2Impl.x, c0Impl.y, c1Impl.y, c2Impl.y, c0Impl.z, c1Impl.z, c2Impl.z);
    }

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
    public static Float3x4 float3x4(Float3R c0, Float3R c1, Float3R c2, Float3R c3) {
        Float3Impl c0Impl = (Float3Impl) c0;
        Float3Impl c1Impl = (Float3Impl) c1;
        Float3Impl c2Impl = (Float3Impl) c2;
        Float3Impl c3Impl = (Float3Impl) c3;
        return new Float3x4Impl(c0Impl.x, c1Impl.x, c2Impl.x, c3Impl.x, c0Impl.y, c1Impl.y, c2Impl.y, c3Impl.y, c0Impl.z, c1Impl.z, c2Impl.z, c3Impl.z);
    }

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
    public static Float4x4 float4x4(Float4R c0, Float4R c1, Float4R c2, Float4R c3) {
        Float4Impl c0Impl = (Float4Impl) c0;
        Float4Impl c1Impl = (Float4Impl) c1;
        Float4Impl c2Impl = (Float4Impl) c2;
        Float4Impl c3Impl = (Float4Impl) c3;
        return new Float4x4Impl(c0Impl.x, c1Impl.x, c2Impl.x, c3Impl.x, c0Impl.y, c1Impl.y, c2Impl.y, c3Impl.y, c0Impl.z, c1Impl.z, c2Impl.z, c3Impl.z, c0Impl.w, c1Impl.w, c2Impl.w, c3Impl.w);
    }

    /**
     * {@return a new {@code Float4x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Float4x3 float4x3(Float4R c0, Float4R c1, Float4R c2) {
        Float4Impl c0Impl = (Float4Impl) c0;
        Float4Impl c1Impl = (Float4Impl) c1;
        Float4Impl c2Impl = (Float4Impl) c2;
        return new Float4x3Impl(c0Impl.x, c1Impl.x, c2Impl.x, c0Impl.y, c1Impl.y, c2Impl.y, c0Impl.z, c1Impl.z, c2Impl.z, c0Impl.w, c1Impl.w, c2Impl.w);
    }

    /**
     * {@return a new {@code Float4x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Float4x2 float4x2(Float4R c0, Float4R c1) {
        Float4Impl c0Impl = (Float4Impl) c0;
        Float4Impl c1Impl = (Float4Impl) c1;
        return new Float4x2Impl(c0Impl.x, c1Impl.x, c0Impl.y, c1Impl.y, c0Impl.z, c1Impl.z, c0Impl.w, c1Impl.w);
    }

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
    public static Float2x4 float2x4(Float2R c0, Float2R c1, Float2R c2, Float2R c3) {
        Float2Impl c0Impl = (Float2Impl) c0;
        Float2Impl c1Impl = (Float2Impl) c1;
        Float2Impl c2Impl = (Float2Impl) c2;
        Float2Impl c3Impl = (Float2Impl) c3;
        return new Float2x4Impl(c0Impl.x, c1Impl.x, c2Impl.x, c3Impl.x, c0Impl.y, c1Impl.y, c2Impl.y, c3Impl.y);
    }

    /**
     * {@return a new {@code Float3x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Float3x2 float3x2(Float3R c0, Float3R c1) {
        Float3Impl c0Impl = (Float3Impl) c0;
        Float3Impl c1Impl = (Float3Impl) c1;
        return new Float3x2Impl(c0Impl.x, c1Impl.x, c0Impl.y, c1Impl.y, c0Impl.z, c1Impl.z);
    }

    /**
     * {@return a new {@code Double2x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Double2x2 double2x2(Double2R c0, Double2R c1) {
        Double2Impl c0Impl = (Double2Impl) c0;
        Double2Impl c1Impl = (Double2Impl) c1;
        return new Double2x2Impl(c0Impl.x, c1Impl.x, c0Impl.y, c1Impl.y);
    }

    /**
     * {@return a new {@code Double2x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Double2x3 double2x3(Double2R c0, Double2R c1, Double2R c2) {
        Double2Impl c0Impl = (Double2Impl) c0;
        Double2Impl c1Impl = (Double2Impl) c1;
        Double2Impl c2Impl = (Double2Impl) c2;
        return new Double2x3Impl(c0Impl.x, c1Impl.x, c2Impl.x, c0Impl.y, c1Impl.y, c2Impl.y);
    }

    /**
     * {@return a new {@code Double3x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Double3x3 double3x3(Double3R c0, Double3R c1, Double3R c2) {
        Double3Impl c0Impl = (Double3Impl) c0;
        Double3Impl c1Impl = (Double3Impl) c1;
        Double3Impl c2Impl = (Double3Impl) c2;
        return new Double3x3Impl(c0Impl.x, c1Impl.x, c2Impl.x, c0Impl.y, c1Impl.y, c2Impl.y, c0Impl.z, c1Impl.z, c2Impl.z);
    }

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
    public static Double3x4 double3x4(Double3R c0, Double3R c1, Double3R c2, Double3R c3) {
        Double3Impl c0Impl = (Double3Impl) c0;
        Double3Impl c1Impl = (Double3Impl) c1;
        Double3Impl c2Impl = (Double3Impl) c2;
        Double3Impl c3Impl = (Double3Impl) c3;
        return new Double3x4Impl(c0Impl.x, c1Impl.x, c2Impl.x, c3Impl.x, c0Impl.y, c1Impl.y, c2Impl.y, c3Impl.y, c0Impl.z, c1Impl.z, c2Impl.z, c3Impl.z);
    }

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
    public static Double4x4 double4x4(Double4R c0, Double4R c1, Double4R c2, Double4R c3) {
        Double4Impl c0Impl = (Double4Impl) c0;
        Double4Impl c1Impl = (Double4Impl) c1;
        Double4Impl c2Impl = (Double4Impl) c2;
        Double4Impl c3Impl = (Double4Impl) c3;
        return new Double4x4Impl(c0Impl.x, c1Impl.x, c2Impl.x, c3Impl.x, c0Impl.y, c1Impl.y, c2Impl.y, c3Impl.y, c0Impl.z, c1Impl.z, c2Impl.z, c3Impl.z, c0Impl.w, c1Impl.w, c2Impl.w, c3Impl.w);
    }

    /**
     * {@return a new {@code Double4x3} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public static Double4x3 double4x3(Double4R c0, Double4R c1, Double4R c2) {
        Double4Impl c0Impl = (Double4Impl) c0;
        Double4Impl c1Impl = (Double4Impl) c1;
        Double4Impl c2Impl = (Double4Impl) c2;
        return new Double4x3Impl(c0Impl.x, c1Impl.x, c2Impl.x, c0Impl.y, c1Impl.y, c2Impl.y, c0Impl.z, c1Impl.z, c2Impl.z, c0Impl.w, c1Impl.w, c2Impl.w);
    }

    /**
     * {@return a new {@code Double4x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Double4x2 double4x2(Double4R c0, Double4R c1) {
        Double4Impl c0Impl = (Double4Impl) c0;
        Double4Impl c1Impl = (Double4Impl) c1;
        return new Double4x2Impl(c0Impl.x, c1Impl.x, c0Impl.y, c1Impl.y, c0Impl.z, c1Impl.z, c0Impl.w, c1Impl.w);
    }

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
    public static Double2x4 double2x4(Double2R c0, Double2R c1, Double2R c2, Double2R c3) {
        Double2Impl c0Impl = (Double2Impl) c0;
        Double2Impl c1Impl = (Double2Impl) c1;
        Double2Impl c2Impl = (Double2Impl) c2;
        Double2Impl c3Impl = (Double2Impl) c3;
        return new Double2x4Impl(c0Impl.x, c1Impl.x, c2Impl.x, c3Impl.x, c0Impl.y, c1Impl.y, c2Impl.y, c3Impl.y);
    }

    /**
     * {@return a new {@code Double3x2} built from the given column vectors}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     */
    public static Double3x2 double3x2(Double3R c0, Double3R c1) {
        Double3Impl c0Impl = (Double3Impl) c0;
        Double3Impl c1Impl = (Double3Impl) c1;
        return new Double3x2Impl(c0Impl.x, c1Impl.x, c0Impl.y, c1Impl.y, c0Impl.z, c1Impl.z);
    }

    /**
     * {@return a new {@code Float2x2} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float2x2 float2x2(Float2x3R src) {
        Float2x3Impl srcImpl = (Float2x3Impl) src;
        return new Float2x2Impl(srcImpl.m00, srcImpl.m01, srcImpl.m10, srcImpl.m11);
    }

    /**
     * {@return a new {@code Float2x2} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float2x2 float2x2(Float3x3R src) {
        Float3x3Impl srcImpl = (Float3x3Impl) src;
        return new Float2x2Impl(srcImpl.m00, srcImpl.m01, srcImpl.m10, srcImpl.m11);
    }

    /**
     * {@return a new {@code Float2x3} initialized to {@code src}, identity-extended with a zero translation column}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float2x3 float2x3(Float2x2R src) {
        Float2x2Impl srcImpl = (Float2x2Impl) src;
        return new Float2x3Impl(srcImpl.m00, srcImpl.m01, 0, srcImpl.m10, srcImpl.m11, 0);
    }

    /**
     * {@return a new {@code Float2x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float2x3 float2x3(Float3x3R src) {
        Float3x3Impl srcImpl = (Float3x3Impl) src;
        return new Float2x3Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m10, srcImpl.m11, srcImpl.m12);
    }

    /**
     * {@return a new {@code Float3x3} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x3 float3x3(Float2x3R src) {
        Float2x3Impl srcImpl = (Float2x3Impl) src;
        return new Float3x3Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m10, srcImpl.m11, srcImpl.m12, 0, 0, 1);
    }

    /**
     * {@return a new {@code Float3x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x3 float3x3(Float4x4R src) {
        Float4x4Impl srcImpl = (Float4x4Impl) src;
        return new Float3x3Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m10, srcImpl.m11, srcImpl.m12, srcImpl.m20, srcImpl.m21, srcImpl.m22);
    }

    /**
     * {@return a new {@code Float3x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x3 float3x3(Float3x4R src) {
        Float3x4Impl srcImpl = (Float3x4Impl) src;
        return new Float3x3Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m10, srcImpl.m11, srcImpl.m12, srcImpl.m20, srcImpl.m21, srcImpl.m22);
    }

    /**
     * {@return a new {@code Float3x3} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x3 float3x3(Float2x2R src) {
        Float2x2Impl srcImpl = (Float2x2Impl) src;
        return new Float3x3Impl(srcImpl.m00, srcImpl.m01, 0, srcImpl.m10, srcImpl.m11, 0, 0, 0, 1);
    }

    /**
     * {@return a new {@code Float3x4} initialized to {@code src}, identity-extended with a zero translation column}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x4 float3x4(Float3x3R src) {
        Float3x3Impl srcImpl = (Float3x3Impl) src;
        return new Float3x4Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, 0, srcImpl.m10, srcImpl.m11, srcImpl.m12, 0, srcImpl.m20, srcImpl.m21, srcImpl.m22, 0);
    }

    /**
     * {@return a new {@code Float3x4} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float3x4 float3x4(Float4x4R src) {
        Float4x4Impl srcImpl = (Float4x4Impl) src;
        return new Float3x4Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m03, srcImpl.m10, srcImpl.m11, srcImpl.m12, srcImpl.m13, srcImpl.m20, srcImpl.m21, srcImpl.m22, srcImpl.m23);
    }

    /**
     * {@return a new {@code Float4x4} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float4x4 float4x4(Float3x4R src) {
        Float3x4Impl srcImpl = (Float3x4Impl) src;
        return new Float4x4Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m03, srcImpl.m10, srcImpl.m11, srcImpl.m12, srcImpl.m13, srcImpl.m20, srcImpl.m21, srcImpl.m22, srcImpl.m23, 0, 0, 0, 1);
    }

    /**
     * {@return a new {@code Float4x4} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Float4x4 float4x4(Float3x3R src) {
        Float3x3Impl srcImpl = (Float3x3Impl) src;
        return new Float4x4Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, 0, srcImpl.m10, srcImpl.m11, srcImpl.m12, 0, srcImpl.m20, srcImpl.m21, srcImpl.m22, 0, 0, 0, 0, 1);
    }

    /**
     * {@return a new {@code Double2x2} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double2x2 double2x2(Double2x3R src) {
        Double2x3Impl srcImpl = (Double2x3Impl) src;
        return new Double2x2Impl(srcImpl.m00, srcImpl.m01, srcImpl.m10, srcImpl.m11);
    }

    /**
     * {@return a new {@code Double2x2} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double2x2 double2x2(Double3x3R src) {
        Double3x3Impl srcImpl = (Double3x3Impl) src;
        return new Double2x2Impl(srcImpl.m00, srcImpl.m01, srcImpl.m10, srcImpl.m11);
    }

    /**
     * {@return a new {@code Double2x3} initialized to {@code src}, identity-extended with a zero translation column}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double2x3 double2x3(Double2x2R src) {
        Double2x2Impl srcImpl = (Double2x2Impl) src;
        return new Double2x3Impl(srcImpl.m00, srcImpl.m01, 0, srcImpl.m10, srcImpl.m11, 0);
    }

    /**
     * {@return a new {@code Double2x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double2x3 double2x3(Double3x3R src) {
        Double3x3Impl srcImpl = (Double3x3Impl) src;
        return new Double2x3Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m10, srcImpl.m11, srcImpl.m12);
    }

    /**
     * {@return a new {@code Double3x3} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x3 double3x3(Double2x3R src) {
        Double2x3Impl srcImpl = (Double2x3Impl) src;
        return new Double3x3Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m10, srcImpl.m11, srcImpl.m12, 0, 0, 1);
    }

    /**
     * {@return a new {@code Double3x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x3 double3x3(Double4x4R src) {
        Double4x4Impl srcImpl = (Double4x4Impl) src;
        return new Double3x3Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m10, srcImpl.m11, srcImpl.m12, srcImpl.m20, srcImpl.m21, srcImpl.m22);
    }

    /**
     * {@return a new {@code Double3x3} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x3 double3x3(Double3x4R src) {
        Double3x4Impl srcImpl = (Double3x4Impl) src;
        return new Double3x3Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m10, srcImpl.m11, srcImpl.m12, srcImpl.m20, srcImpl.m21, srcImpl.m22);
    }

    /**
     * {@return a new {@code Double3x3} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x3 double3x3(Double2x2R src) {
        Double2x2Impl srcImpl = (Double2x2Impl) src;
        return new Double3x3Impl(srcImpl.m00, srcImpl.m01, 0, srcImpl.m10, srcImpl.m11, 0, 0, 0, 1);
    }

    /**
     * {@return a new {@code Double3x4} initialized to {@code src}, identity-extended with a zero translation column}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x4 double3x4(Double3x3R src) {
        Double3x3Impl srcImpl = (Double3x3Impl) src;
        return new Double3x4Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, 0, srcImpl.m10, srcImpl.m11, srcImpl.m12, 0, srcImpl.m20, srcImpl.m21, srcImpl.m22, 0);
    }

    /**
     * {@return a new {@code Double3x4} initialized to {@code src}, truncated to the overlapping cells}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double3x4 double3x4(Double4x4R src) {
        Double4x4Impl srcImpl = (Double4x4Impl) src;
        return new Double3x4Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m03, srcImpl.m10, srcImpl.m11, srcImpl.m12, srcImpl.m13, srcImpl.m20, srcImpl.m21, srcImpl.m22, srcImpl.m23);
    }

    /**
     * {@return a new {@code Double4x4} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double4x4 double4x4(Double3x4R src) {
        Double3x4Impl srcImpl = (Double3x4Impl) src;
        return new Double4x4Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, srcImpl.m03, srcImpl.m10, srcImpl.m11, srcImpl.m12, srcImpl.m13, srcImpl.m20, srcImpl.m21, srcImpl.m22, srcImpl.m23, 0, 0, 0, 1);
    }

    /**
     * {@return a new {@code Double4x4} initialized to {@code src}, identity-extended to the full square shape}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public static Double4x4 double4x4(Double3x3R src) {
        Double3x3Impl srcImpl = (Double3x3Impl) src;
        return new Double4x4Impl(srcImpl.m00, srcImpl.m01, srcImpl.m02, 0, srcImpl.m10, srcImpl.m11, srcImpl.m12, 0, srcImpl.m20, srcImpl.m21, srcImpl.m22, 0, 0, 0, 0, 1);
    }

    /**
     * {@return a new {@code Float2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float2 float2(Float2R src) { return new Float2Impl(src); }

    /**
     * {@return a new {@code Float3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float3 float3(Float3R src) { return new Float3Impl(src); }

    /**
     * {@return a new {@code Float4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float4 float4(Float4R src) { return new Float4Impl(src); }

    /**
     * {@return a new {@code Float2x2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float2x2 float2x2(Float2x2R src) { return new Float2x2Impl(src); }

    /**
     * {@return a new {@code Float2x3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float2x3 float2x3(Float2x3R src) { return new Float2x3Impl(src); }

    /**
     * {@return a new {@code Float3x3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float3x3 float3x3(Float3x3R src) { return new Float3x3Impl(src); }

    /**
     * {@return a new {@code Float3x4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float3x4 float3x4(Float3x4R src) { return new Float3x4Impl(src); }

    /**
     * {@return a new {@code Float4x4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float4x4 float4x4(Float4x4R src) { return new Float4x4Impl(src); }

    /**
     * {@return a new {@code Float4x3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float4x3 float4x3(Float4x3R src) { return new Float4x3Impl(src); }

    /**
     * {@return a new {@code Float4x2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float4x2 float4x2(Float4x2R src) { return new Float4x2Impl(src); }

    /**
     * {@return a new {@code Float2x4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float2x4 float2x4(Float2x4R src) { return new Float2x4Impl(src); }

    /**
     * {@return a new {@code Float3x2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Float3x2 float3x2(Float3x2R src) { return new Float3x2Impl(src); }

    /**
     * {@return a new {@code FloatQuat} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatQuat floatQuat(FloatQuatR src) { return new FloatQuatImpl(src); }

    /**
     * {@return a new {@code FloatDualQuat} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatDualQuat floatDualQuat(FloatDualQuatR src) { return new FloatDualQuatImpl(src); }

    /**
     * {@return a new {@code FloatRect} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatRect floatRect(FloatRectR src) { return new FloatRectImpl(src); }

    /**
     * {@return a new {@code FloatTransform} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatTransform floatTransform(FloatTransformR src) { return new FloatTransformImpl(src); }

    /**
     * {@return a new {@code FloatRigid} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatRigid floatRigid(FloatRigidR src) { return new FloatRigidImpl(src); }

    /**
     * {@return a new {@code FloatOBB} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatOBB floatOBB(FloatOBBR src) { return new FloatOBBImpl(src); }

    /**
     * {@return a new {@code FloatAABB} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatAABB floatAABB(FloatAABBR src) { return new FloatAABBImpl(src); }

    /**
     * {@return a new {@code FloatSphere} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatSphere floatSphere(FloatSphereR src) { return new FloatSphereImpl(src); }

    /**
     * {@return a new {@code FloatRay} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatRay floatRay(FloatRayR src) { return new FloatRayImpl(src); }

    /**
     * {@return a new {@code FloatPlane} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatPlane floatPlane(FloatPlaneR src) { return new FloatPlaneImpl(src); }

    /**
     * {@return a new {@code FloatTriangle} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static FloatTriangle floatTriangle(FloatTriangleR src) { return new FloatTriangleImpl(src); }

    /**
     * {@return a new {@code Double2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double2 double2(Double2R src) { return new Double2Impl(src); }

    /**
     * {@return a new {@code Double3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double3 double3(Double3R src) { return new Double3Impl(src); }

    /**
     * {@return a new {@code Double4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double4 double4(Double4R src) { return new Double4Impl(src); }

    /**
     * {@return a new {@code Double2x2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double2x2 double2x2(Double2x2R src) { return new Double2x2Impl(src); }

    /**
     * {@return a new {@code Double2x3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double2x3 double2x3(Double2x3R src) { return new Double2x3Impl(src); }

    /**
     * {@return a new {@code Double3x3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double3x3 double3x3(Double3x3R src) { return new Double3x3Impl(src); }

    /**
     * {@return a new {@code Double3x4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double3x4 double3x4(Double3x4R src) { return new Double3x4Impl(src); }

    /**
     * {@return a new {@code Double4x4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double4x4 double4x4(Double4x4R src) { return new Double4x4Impl(src); }

    /**
     * {@return a new {@code Double4x3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double4x3 double4x3(Double4x3R src) { return new Double4x3Impl(src); }

    /**
     * {@return a new {@code Double4x2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double4x2 double4x2(Double4x2R src) { return new Double4x2Impl(src); }

    /**
     * {@return a new {@code Double2x4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double2x4 double2x4(Double2x4R src) { return new Double2x4Impl(src); }

    /**
     * {@return a new {@code Double3x2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static Double3x2 double3x2(Double3x2R src) { return new Double3x2Impl(src); }

    /**
     * {@return a new {@code DoubleQuat} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoubleQuat doubleQuat(DoubleQuatR src) { return new DoubleQuatImpl(src); }

    /**
     * {@return a new {@code DoubleDualQuat} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoubleDualQuat doubleDualQuat(DoubleDualQuatR src) { return new DoubleDualQuatImpl(src); }

    /**
     * {@return a new {@code DoubleRect} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoubleRect doubleRect(DoubleRectR src) { return new DoubleRectImpl(src); }

    /**
     * {@return a new {@code DoubleTransform} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoubleTransform doubleTransform(DoubleTransformR src) { return new DoubleTransformImpl(src); }

    /**
     * {@return a new {@code DoubleRigid} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoubleRigid doubleRigid(DoubleRigidR src) { return new DoubleRigidImpl(src); }

    /**
     * {@return a new {@code DoubleOBB} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoubleOBB doubleOBB(DoubleOBBR src) { return new DoubleOBBImpl(src); }

    /**
     * {@return a new {@code DoubleAABB} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoubleAABB doubleAABB(DoubleAABBR src) { return new DoubleAABBImpl(src); }

    /**
     * {@return a new {@code DoubleSphere} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoubleSphere doubleSphere(DoubleSphereR src) { return new DoubleSphereImpl(src); }

    /**
     * {@return a new {@code DoubleRay} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoubleRay doubleRay(DoubleRayR src) { return new DoubleRayImpl(src); }

    /**
     * {@return a new {@code DoublePlane} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoublePlane doublePlane(DoublePlaneR src) { return new DoublePlaneImpl(src); }

    /**
     * {@return a new {@code DoubleTriangle} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the instance to copy
     */
    public static DoubleTriangle doubleTriangle(DoubleTriangleR src) { return new DoubleTriangleImpl(src); }

    /**
     * {@return a new {@code Byte2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Byte2 byte2(Byte2R src) { return new Byte2Impl(src); }

    /**
     * {@return a new {@code Byte3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Byte3 byte3(Byte3R src) { return new Byte3Impl(src); }

    /**
     * {@return a new {@code Byte4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Byte4 byte4(Byte4R src) { return new Byte4Impl(src); }

    /**
     * {@return a new {@code Short2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Short2 short2(Short2R src) { return new Short2Impl(src); }

    /**
     * {@return a new {@code Short3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Short3 short3(Short3R src) { return new Short3Impl(src); }

    /**
     * {@return a new {@code Short4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Short4 short4(Short4R src) { return new Short4Impl(src); }

    /**
     * {@return a new {@code Int2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Int2 int2(Int2R src) { return new Int2Impl(src); }

    /**
     * {@return a new {@code Int3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Int3 int3(Int3R src) { return new Int3Impl(src); }

    /**
     * {@return a new {@code Int4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Int4 int4(Int4R src) { return new Int4Impl(src); }

    /**
     * {@return a new {@code IntRect} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static IntRect intRect(IntRectR src) { return new IntRectImpl(src); }

    /**
     * {@return a new {@code Long2} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Long2 long2(Long2R src) { return new Long2Impl(src); }

    /**
     * {@return a new {@code Long3} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Long3 long3(Long3R src) { return new Long3Impl(src); }

    /**
     * {@return a new {@code Long4} initialized to a copy of {@code src}}
     * <p>
     * Valid input: any value.
     *
     * @param src the instance to copy
     */
    public static Long4 long4(Long4R src) { return new Long4Impl(src); }
}
