// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

public final class Math {

    public static final double PI = java.lang.Math.PI;
    public static final double E = java.lang.Math.E;
    public static final double PI_TIMES_2 = PI * 2.0;
    public static final float PI_f = (float) java.lang.Math.PI;
    public static final float PI_TIMES_2_f = PI_f * 2.0f;
    public static final double PI_OVER_2 = PI * 0.5;
    public static final float PI_OVER_2_f = (float) (PI * 0.5);
    public static final double PI_OVER_4 = PI * 0.25;
    public static final float PI_OVER_4_f = (float) (PI * 0.25);
    public static final double ONE_OVER_PI = 1.0 / PI;
    public static final float ONE_OVER_PI_f = (float) (1.0 / PI);

    /*
     * Configuration overrides. The setters and the Cfg snapshot both hold the
     * Math.class monitor, so a setter that races the first flag-gated call either
     * lands before the snapshot (and is honoured) or sees mathInitialized and
     * throws - it is never silently dropped.
     */
    private static volatile Boolean fastmathOverride;
    private static volatile Boolean sinLookupOverride;
    private static volatile Integer sinLookupBitsOverride;
    private static volatile Boolean useFmaOverride;
    private static volatile Boolean strictMathOverride;
    private static volatile boolean mathInitialized;

    /** Default sin/cos lookup-table bit width (16384 entries). */
    private static final int DEFAULT_SIN_LOOKUP_BITS = 14;
    /**
     * Smallest accepted sin/cos lookup-table bit width (4 entries): the cosine reads the
     * table a quarter period further on, a whole number of entries only from 4 on (and a
     * 2-entry table held sin(0) and sin(pi), zero for every argument).
     */
    private static final int MIN_SIN_LOOKUP_BITS = 2;
    /** Largest accepted sin/cos lookup-table bit width (16M entries, 64 MiB). */
    private static final int MAX_SIN_LOOKUP_BITS = 24;

    /**
     * Enable or disable the fast-math approximations: the sin/cos lookup table of
     * {@link #setSinLookup sinLookup} and a polynomial atan2 (within 1.5e-4 radians). Without
     * the lookup the sine and cosine are the default ones, whose inline evaluations are faster
     * and more exact than a polynomial over the whole period (and stay inline under
     * {@code joml.strictMath}, being reproducible). Overrides the {@code joml.fastmath} system
     * property
     * (default {@code false}). Must be called before any flag-gated method is
     * used; calling it later throws {@link IllegalStateException}. Thread-safe:
     * a call racing the first use is either honoured or throws.
     * <p>
     * Valid input: any value.
     */
    public static void setFastmath(boolean value) {
        synchronized (Math.class) {
            requireNotInitialized();
            fastmathOverride = Boolean.valueOf(value);
        }
    }

    /**
     * Enable or disable the linear-interpolated sin/cos lookup table.
     * Only effective when {@link #setFastmath fastmath} is also enabled.
     * Overrides the {@code joml.sinLookup} system property (default
     * {@code false}). Must be called before any flag-gated method is used;
     * calling it later throws {@link IllegalStateException}.
     * <p>
     * Valid input: any value.
     */
    public static void setSinLookup(boolean value) {
        synchronized (Math.class) {
            requireNotInitialized();
            sinLookupOverride = Boolean.valueOf(value);
        }
    }

    /**
     * Set the sin/cos lookup-table bit width: the table has {@code 2^bits}
     * entries (plus one guard entry), so each additional bit doubles its
     * memory and halves the interpolation step. Accepted range is
     * {@code 2..24} (4 entries to 16M entries, 64 MiB); default is 14
     * (16384 entries). Overrides the {@code joml.sinLookup.bits} system
     * property. Must be called before any flag-gated method is used; calling
     * it later throws {@link IllegalStateException}.
     * <p>
     * Valid input: {@code bits} must lie in {@code [2, 24]}.
     *
     * @param bits the table bit width, in {@code [2, 24]}
     * @throws IllegalArgumentException if {@code bits} is outside {@code [2, 24]}
     * @throws IllegalStateException if the flags have already been frozen
     */
    public static void setSinLookupBits(int bits) {
        if (bits < MIN_SIN_LOOKUP_BITS || bits > MAX_SIN_LOOKUP_BITS)
            throw new IllegalArgumentException("sinLookupBits must be in [" + MIN_SIN_LOOKUP_BITS + ", "
                    + MAX_SIN_LOOKUP_BITS + "], got " + bits);
        synchronized (Math.class) {
            requireNotInitialized();
            sinLookupBitsOverride = Integer.valueOf(bits);
        }
    }

    /**
     * Set whether {@link #fma(float, float, float)} / {@link #fma(double, double, double)}
     * use the fused {@code java.lang.Math.fma} (a single rounding, hardware FMA on
     * every current desktop and mobile CPU) or the two-rounding {@code a * b + c}.
     * Overrides the {@code joml.useFma} system property (default {@code true}).
     * Turn it off on a CPU without fused multiply-add hardware (x86 before Intel Haswell and
     * AMD Piledriver, Atom-class chips before Gracemont, a virtual machine that hides the
     * feature: HotSpot's {@code -XX:-UseFMA}): there {@code java.lang.Math.fma} is an exact
     * software emulation, thousands of times slower (a 4x4 matrix product took 56-95
     * microseconds instead of 8 ns).
     * Must be called before any flag-gated method is used; calling it later
     * throws {@link IllegalStateException}.
     * <p>
     * Valid input: any value.
     */
    public static void setUseFma(boolean value) {
        synchronized (Math.class) {
            requireNotInitialized();
            useFmaOverride = Boolean.valueOf(value);
        }
    }

    /**
     * Whether {@code fma} uses the fused {@code java.lang.Math.fma}. Querying it
     * freezes the flags (see {@link #setUseFma}).
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resolved {@code useFma} flag
     */
    public static boolean useFma() { return Cfg.USE_FMA; }

    /**
     * Enable or disable strict floating-point mode: the transcendental methods
     * (sin/cos/tan, asin/acos/atan/atan2, sinh/cosh/tanh, exp/expm1, log/log10/log1p,
     * pow/cbrt) delegate to {@link StrictMath} instead of {@link java.lang.Math},
     * producing bit-identical results on every JVM and platform. {@code java.lang.Math}
     * may use platform intrinsics that are only required to be within 1-2 ulp of the
     * correctly rounded result, so its values can differ across platforms.
     * {@link #hypot(double, double) hypot} is the square root of the sum of squares in
     * either mode: plain IEEE arithmetic, the same everywhere already.
     * <p>
     * Only the precise path is affected: when {@link #setFastmath fastmath} is also
     * enabled, the fast-math approximations still win for sin/cos/atan2 (they are pure
     * arithmetic and thus already reproducible; the sin lookup table is built with
     * {@code StrictMath.sin} under strict mode, so it is reproducible too).
     * <p>
     * Full cross-platform determinism additionally requires identical
     * {@code joml.useFma}, {@code joml.fastmath} and (with fastmath)
     * {@code joml.sinLookup}/{@code joml.sinLookup.bits} settings on every machine, and strict
     * FP arithmetic semantics (guaranteed on Java 17+ per JEP 306, and in practice on
     * any SSE2 or ARM64 JVM).
     * <p>
     * Defaults to {@code false}; also settable via {@code -Djoml.strictMath}. Must be
     * called before any flag-gated method is used; calling it later throws
     * {@link IllegalStateException}.
     * <p>
     * Valid input: any value.
     */
    public static void setStrictMath(boolean value) {
        synchronized (Math.class) {
            requireNotInitialized();
            strictMathOverride = Boolean.valueOf(value);
        }
    }

    /**
     * Whether the transcendental methods delegate to {@link StrictMath}. Querying it
     * freezes the flags (see {@link #setStrictMath}).
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resolved {@code strictMath} flag
     */
    public static boolean strictMath() { return Cfg.STRICT_MATH; }

    /** Caller must hold the {@code Math.class} monitor. */
    private static void requireNotInitialized() {
        if (mathInitialized) throw new IllegalStateException(alreadyInitializedMessage());
    }

    private static String alreadyInitializedMessage() {
        return "Math config setters must be called before the first use of a flag-gated "
             + "method (any transcendental such as sin/cos/tan/atan2/exp/log/pow, "
             + "or fma - including through the *Ops classes, whose kernels call "
             + "these and whose SIMD flavour snapshots useFma() in SimdSupport): "
             + "org.joml2.Math has already snapshotted its flags and they are frozen for "
             + "the rest of this JVM run.";
    }

    /**
     * Reads a boolean {@code -Djoml.*} flag. Absent yields {@code dflt}; a bare
     * flag (present with an empty value) or {@code true} (case-insensitive,
     * surrounding whitespace ignored) is {@code true}; {@code false} is
     * {@code false}; any other value logs one warning on {@code System.err} and
     * yields {@code dflt} - a typo must not switch off a flag that is on by default.
     */
    private static boolean flagProperty(String name, boolean dflt) {
        String v = System.getProperty(name);
        if (v == null) return dflt;
        String t = v.trim();
        if (t.isEmpty() || t.equalsIgnoreCase("true")) return true;
        if (t.equalsIgnoreCase("false")) return false;
        System.err.println("[org.joml2] unrecognised -D" + name + "=" + v
                + " (expected true or false); using the default " + dflt);
        return dflt;
    }

    /**
     * Reads {@code -Djoml.sinLookup.bits}: an integer in {@code [2, 24]}
     * (surrounding whitespace ignored). Absent yields the default 14; anything
     * else (empty, non-numeric, out of range) logs one warning on
     * {@code System.err} and yields the default.
     */
    private static int sinLookupBitsProperty() {
        String v = System.getProperty("joml.sinLookup.bits");
        if (v == null) return DEFAULT_SIN_LOOKUP_BITS;
        try {
            int bits = Integer.parseInt(v.trim());
            if (bits >= MIN_SIN_LOOKUP_BITS && bits <= MAX_SIN_LOOKUP_BITS) return bits;
        } catch (NumberFormatException e) {
            // reported below
        }
        System.err.println("[org.joml2] unrecognised -Djoml.sinLookup.bits=" + v
                + " (expected an integer in [" + MIN_SIN_LOOKUP_BITS + ", " + MAX_SIN_LOOKUP_BITS
                + "]); using the default " + DEFAULT_SIN_LOOKUP_BITS);
        return DEFAULT_SIN_LOOKUP_BITS;
    }

    private static final class Cfg {
        static final boolean FASTMATH;
        static final boolean SIN_LOOKUP;
        static final boolean USE_FMA;
        static final boolean STRICT_MATH;
        static final int SIN_LOOKUP_BITS;
        static final int lookupTableSize;
        static final int lookupTableSizeMinus1;
        /** The cosine's index shift: a quarter period, a whole number of entries. */
        static final int lookupQuarter;
        /**
         * Entries per radian, in double with the double 2pi: the index is taken in double too
         * (a float index with a float 2pi drifted by 7e-8 |x|, 0.029 at the hand-off).
         */
        static final double lookupSizeOverPi2;
        static final float[] sinTable;
        /**
         * Largest |argument| the sin/cos lookup table handles itself; beyond it the
         * sine and cosine hand off to the precise path (which also yields NaN for
         * +-Infinity, like java.lang.Math.sin): the table index rad * lookupSizeOverPi2
         * has to stay an exact int (sin(1e6) was 176450 and sin(+Inf) was +Inf).
         */
        static final double FAST_SIN_MAX_ARG;

        static {
            boolean fastmath, sinLookup, useFma, strictMath;
            int sinLookupBits;
            // Snapshot under the same monitor the setters take: a setter either
            // completes before this block (honoured) or observes mathInitialized
            // afterwards (IllegalStateException) - never a silently lost write.
            synchronized (Math.class) {
                Boolean fOv = fastmathOverride;
                fastmath = fOv != null ? fOv.booleanValue() : flagProperty("joml.fastmath", false);
                Boolean lOv = sinLookupOverride;
                sinLookup = lOv != null ? lOv.booleanValue() : flagProperty("joml.sinLookup", false);
                Boolean uOv = useFmaOverride;
                useFma = uOv != null ? uOv.booleanValue() : flagProperty("joml.useFma", true);
                Boolean sOv = strictMathOverride;
                strictMath = sOv != null ? sOv.booleanValue() : flagProperty("joml.strictMath", false);
                Integer bOv = sinLookupBitsOverride;
                sinLookupBits = bOv != null ? bOv.intValue() : sinLookupBitsProperty();
                mathInitialized = true;
            }
            FASTMATH = fastmath;
            SIN_LOOKUP = sinLookup;
            USE_FMA = useFma;
            STRICT_MATH = strictMath;
            SIN_LOOKUP_BITS = sinLookupBits;
            lookupTableSize = 1 << SIN_LOOKUP_BITS;
            lookupTableSizeMinus1 = lookupTableSize - 1;
            lookupQuarter = lookupTableSize >> 2;
            lookupSizeOverPi2 = lookupTableSize / PI_TIMES_2;
            FAST_SIN_MAX_ARG = SIN_LOOKUP ? java.lang.Math.min(0x1p24, 0x1p30 / lookupSizeOverPi2) : 0x1p24;
            if (FASTMATH && SIN_LOOKUP) {
                double step = PI_TIMES_2 / lookupTableSize;
                sinTable = new float[lookupTableSize + 1];
                for (int i = 0; i < lookupTableSize; i++) {
                    sinTable[i] = STRICT_MATH
                            ? (float) java.lang.StrictMath.sin(i * step)
                            : (float) java.lang.Math.sin(i * step);
                }
                // The guard entry is the wrap to table[0], exactly: sin(2pi_f) was 1.75e-7, which a
                // tiny negative argument (interpolating toward it) returned.
                sinTable[lookupTableSize] = sinTable[0];
            } else {
                sinTable = null;
            }
        }
    }


    private Math() {}

    /**
     * The sine of the angle {@code rad} (radians); see {@link #setFastmath} for the approximation.
     * <p>
     * Accuracy: the correctly rounded sine, but for arguments whose sine lies within about 3e-11
     * (relative) of a rounding boundary - about 1 in 140,000 - which may round the other way,
     * within 0.5005 ulp (every float up to {@code 2^24} checked).
     * Up to {@code 2^24} it is evaluated inline in double (see {@link #cos(float)}); beyond, and
     * with {@code joml.strictMath}, it is {@code java.lang.Math.sin} (or {@code StrictMath}'s).
     * With {@code joml.fastmath} it is the inline one under {@code joml.strictMath} too, and with
     * {@code joml.sinLookup} as well the table's. The double sine is evaluated so only where the
     * cosine of the same angle is computed too: see {@link #sinWithCos(double)}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float sin(float rad) {
        // The lookup's zero is its own sine (its reduction turned -0 into +0). Without the lookup
        // the fast sine is the inline one, ahead of strictMath like every fast-math path. (Below
        // 2^-12 the sine rounds to the angle itself, -0 and the subnormals included.) The lookup and
        // the table evaluation are written out, not called: a call level less for C2's MaxInlineLevel.
        if (Cfg.FASTMATH && Cfg.SIN_LOOKUP && java.lang.Math.abs(rad) <= Cfg.FAST_SIN_MAX_ARG) {
            if (rad == 0f) return rad;
            double index = rad * Cfg.lookupSizeOverPi2, floor = java.lang.Math.floor(index);
            int i = (int) floor & Cfg.lookupTableSizeMinus1;
            float sin1 = Cfg.sinTable[i];
            return sin1 + (Cfg.sinTable[i + 1] - sin1) * (float) (index - floor);
        }
        if ((Cfg.FASTMATH || !Cfg.STRICT_MATH) && java.lang.Math.abs(rad) <= 0x1p24f) {
            if (java.lang.Math.abs(rad) < 0x1p-12f) return rad;
            double k = java.lang.Math.rint(rad * TAB_INV_STEP);
            double r = ((rad - k * TAB_STEP_HI) - k * TAB_STEP_MID) - k * TAB_STEP_LO, t = r * r;
            int i = (int) k & 63;
            return (float) fma(TAB_SIN[i], fma(t, fma(t, 1.0 / 24, -0.5), 1.0), TAB_COS[i] * fma(r * t, fma(t, 1.0 / 120, -1.0 / 6), r));
        }
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.sin(rad);
        return (float) java.lang.Math.sin(rad);
    }
    /**
     * The sine of the angle {@code rad} (radians); see {@link #setFastmath} for the approximation.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double sin(double rad) {
        if (Cfg.FASTMATH && Cfg.SIN_LOOKUP && java.lang.Math.abs(rad) <= Cfg.FAST_SIN_MAX_ARG) {
            if (rad == 0.0) return rad;
            double index = rad * Cfg.lookupSizeOverPi2, floor = java.lang.Math.floor(index);
            int i = (int) floor & Cfg.lookupTableSizeMinus1;
            float sin1 = Cfg.sinTable[i];
            return sin1 + (Cfg.sinTable[i + 1] - sin1) * (float) (index - floor);
        }
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.sin(rad);
        return java.lang.Math.sin(rad);
    }

    // The lookup cosine is the lookup sine a quarter of the table on, an exact shift (rad +
    // PI_OVER_2_f rounded to float was off by half an ulp of rad: 3.4e-3 at 65535, 0.46 at 1.6e7).
    /**
     * The cosine of the angle {@code rad} (radians); see {@link #setFastmath} for the
     * approximation.
     * <p>
     * Accuracy: as {@link #sin(float)}, within 0.5005 ulp. Up to {@code 2^24} the float sine and
     * cosine are evaluated inline in double, the angle as {@code k pi/32 + r} with {@code |r| <=
     * pi/64}: {@code sin(k pi/32)} and {@code cos(k pi/32)} from a table, {@code sin r} and
     * {@code cos r} as short polynomials, combined by the angle-sum formulas. A sine and a cosine
     * of one angle share all of it but the last fused multiply-add. {@code java.lang.Math.sin} and
     * {@code cos} are calls, around which every live float register is saved and restored: the
     * rotations built from angles are 13-57% faster for it. Beyond {@code 2^24}, and with
     * {@code joml.strictMath}, {@code java.lang.Math.cos} (or {@code StrictMath}'s).
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float cos(float rad) {
        if (Cfg.FASTMATH && Cfg.SIN_LOOKUP && java.lang.Math.abs(rad) <= Cfg.FAST_SIN_MAX_ARG) {
            double index = rad * Cfg.lookupSizeOverPi2, floor = java.lang.Math.floor(index);
            int i = ((int) floor + Cfg.lookupQuarter) & Cfg.lookupTableSizeMinus1;
            float sin1 = Cfg.sinTable[i];
            return sin1 + (Cfg.sinTable[i + 1] - sin1) * (float) (index - floor);
        }
        if ((Cfg.FASTMATH || !Cfg.STRICT_MATH) && java.lang.Math.abs(rad) <= 0x1p24f) {
            double k = java.lang.Math.rint(rad * TAB_INV_STEP);
            double r = ((rad - k * TAB_STEP_HI) - k * TAB_STEP_MID) - k * TAB_STEP_LO, t = r * r;
            int i = (int) k & 63;
            return (float) fma(TAB_COS[i], fma(t, fma(t, 1.0 / 24, -0.5), 1.0), -(TAB_SIN[i] * fma(r * t, fma(t, 1.0 / 120, -1.0 / 6), r)));
        }
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.cos(rad);
        return (float) java.lang.Math.cos(rad);
    }

    // The float sine and cosine (|x| <= 2^24): x = k pi/32 + r, Cody-Waite with three parts of pi/32
    // (25-bit HI and MID: k < 2^28 times them is exact), |r| <= pi/64; sin r = r - r^3/6 + r^5/120
    // (2.7e-12 relative left out), cos r = 1 - r^2/2 + r^4/24 (1.9e-11 left out, never where the
    // cosine of x is small: there cos(k pi/32) = 0). The table holds the exact 0 and +-1 where they
    // are, so a result near a zero of the sine or cosine comes from the r polynomial alone and is
    // as exact as r. Shared by the sine and the cosine: C2 merges the reduction and the table reads.
    private static final double TAB_INV_STEP = 10.185916357881302;
    private static final double TAB_STEP_HI = 0.09817476943135262;
    private static final double TAB_STEP_MID = 9.93328419518491e-10;
    private static final double TAB_STEP_LO = 3.827021247335479e-18;
    private static final double[] TAB_SIN = new double[64], TAB_COS = new double[64];
    static {
        // sin(j pi/32), j = 0..16, correctly rounded (java.lang.Math.sin(j * PI / 32) is the sine of
        // a rounded angle, and an ulp off for 6 of the 128 entries on AArch64): the same table on
        // every JVM, so the fast-math path that takes it stays reproducible under strictMath.
        double[] q = {0.0, 0x1.917a6bc29b42cp-4, 0x1.8f8b83c69a60bp-3, 0x1.294062ed59f06p-2,
            0x1.87de2a6aea963p-2, 0x1.e2b5d3806f63bp-2, 0x1.1c73b39ae68c8p-1, 0x1.44cf325091dd6p-1,
            0x1.6a09e667f3bcdp-1, 0x1.8bc806b151741p-1, 0x1.a9b66290ea1a3p-1, 0x1.c38b2f180bdb1p-1,
            0x1.d906bcf328d46p-1, 0x1.e9f4156c62ddap-1, 0x1.f6297cff75cb0p-1, 0x1.fd88da3d12526p-1, 1.0};
        for (int k = 0; k < 64; k++) {
            int j = k & 15;
            double a = q[j], b = q[16 - j];
            switch (k >> 4) {
                case 0: TAB_SIN[k] = a; TAB_COS[k] = b; break;
                case 1: TAB_SIN[k] = b; TAB_COS[k] = 0.0 - a; break;
                case 2: TAB_SIN[k] = 0.0 - a; TAB_COS[k] = 0.0 - b; break;
                default: TAB_SIN[k] = 0.0 - b; TAB_COS[k] = a;
            }
        }
    }

    /**
     * The cosine of the angle {@code rad} (radians); see {@link #setFastmath} for the
     * approximation.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double cos(double rad) {
        if (Cfg.FASTMATH && Cfg.SIN_LOOKUP && java.lang.Math.abs(rad) <= Cfg.FAST_SIN_MAX_ARG) {
            double index = rad * Cfg.lookupSizeOverPi2, floor = java.lang.Math.floor(index);
            int i = ((int) floor + Cfg.lookupQuarter) & Cfg.lookupTableSizeMinus1;
            float sin1 = Cfg.sinTable[i];
            return sin1 + (Cfg.sinTable[i + 1] - sin1) * (float) (index - floor);
        }
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.cos(rad);
        return java.lang.Math.cos(rad);
    }

    /**
     * The sine of {@code angle} where its cosine is computed too, by {@link #cosWithSin(double)}:
     * every generated operation that needs both the sine and the cosine of one angle calls the
     * pair, in float and in double. That is the rotation builders ({@code rotate*},
     * {@code preRotate*}, {@code makeRotation*}, {@code rotateAround}, {@code makeFromAxisAngle},
     * on matrices, quaternions, dual quaternions and poses), the vector rotations ({@code rotate},
     * {@code rotateX/Y/Z}, {@code rotateAxis} and their {@code *Around} forms), the quaternion and
     * dual quaternion {@code exp}, {@code pow}, {@code sclerp} and {@code integrate}, the vector
     * {@code slerp}, the random {@code makeUniformDirection} and {@code makeUniformRotation},
     * {@code arcball}, and the oblique and axonometric projections.
     * <p>
     * In float it is {@link #sin(float)} itself. In double it is evaluated inline up to
     * {@code 2^10} with {@code joml.useFma}, as {@link #cosWithSin(double)} describes; else, and
     * with {@code joml.strictMath} (but under {@code joml.fastmath}), it is {@link #sin(double)}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the sine of {@code angle}
     */
    public static float sinWithCos(float angle) {
        // (sin(float) written out again: a call level less)
        if (Cfg.FASTMATH && Cfg.SIN_LOOKUP && java.lang.Math.abs(angle) <= Cfg.FAST_SIN_MAX_ARG) {
            if (angle == 0f) return angle;
            double index = angle * Cfg.lookupSizeOverPi2, floor = java.lang.Math.floor(index);
            int i = (int) floor & Cfg.lookupTableSizeMinus1;
            float sin1 = Cfg.sinTable[i];
            return sin1 + (Cfg.sinTable[i + 1] - sin1) * (float) (index - floor);
        }
        if ((Cfg.FASTMATH || !Cfg.STRICT_MATH) && java.lang.Math.abs(angle) <= 0x1p24f) {
            if (java.lang.Math.abs(angle) < 0x1p-12f) return angle;
            double k = java.lang.Math.rint(angle * TAB_INV_STEP);
            double r = ((angle - k * TAB_STEP_HI) - k * TAB_STEP_MID) - k * TAB_STEP_LO, t = r * r;
            int i = (int) k & 63;
            return (float) fma(TAB_SIN[i], fma(t, fma(t, 1.0 / 24, -0.5), 1.0), TAB_COS[i] * fma(r * t, fma(t, 1.0 / 120, -1.0 / 6), r));
        }
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.sin(angle);
        return (float) java.lang.Math.sin(angle);
    }
    /**
     * The cosine of {@code angle} where its sine is computed too, by {@link #sinWithCos(float)}:
     * see there. In float it is {@link #cos(float)} itself.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the cosine of {@code angle}
     */
    public static float cosWithSin(float angle) {
        // (cos(float) written out again: a call level less)
        if (Cfg.FASTMATH && Cfg.SIN_LOOKUP && java.lang.Math.abs(angle) <= Cfg.FAST_SIN_MAX_ARG) {
            double index = angle * Cfg.lookupSizeOverPi2, floor = java.lang.Math.floor(index);
            int i = ((int) floor + Cfg.lookupQuarter) & Cfg.lookupTableSizeMinus1;
            float sin1 = Cfg.sinTable[i];
            return sin1 + (Cfg.sinTable[i + 1] - sin1) * (float) (index - floor);
        }
        if ((Cfg.FASTMATH || !Cfg.STRICT_MATH) && java.lang.Math.abs(angle) <= 0x1p24f) {
            double k = java.lang.Math.rint(angle * TAB_INV_STEP);
            double r = ((angle - k * TAB_STEP_HI) - k * TAB_STEP_MID) - k * TAB_STEP_LO, t = r * r;
            int i = (int) k & 63;
            return (float) fma(TAB_COS[i], fma(t, fma(t, 1.0 / 24, -0.5), 1.0), -(TAB_SIN[i] * fma(r * t, fma(t, 1.0 / 120, -1.0 / 6), r)));
        }
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.cos(angle);
        return (float) java.lang.Math.cos(angle);
    }
    /**
     * The sine of {@code angle} where its cosine is computed too, by {@link #cosWithSin(double)};
     * see {@link #sinWithCos(float)} for where the pair is used.
     * <p>
     * Accuracy: within 0.752 ulp; see {@link #cosWithSin(double)}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the sine of {@code angle}
     */
    public static double sinWithCos(double angle) {
        // (the evaluation written out, not called: a call level less; sin(double) is the cold rest)
        if (!(Cfg.FASTMATH && Cfg.SIN_LOOKUP) && Cfg.USE_FMA && (Cfg.FASTMATH || !Cfg.STRICT_MATH)
                && java.lang.Math.abs(angle) <= 0x1p10) {
            // (below 2^-26 the sine rounds to the angle itself, -0 and the subnormals included)
            if (java.lang.Math.abs(angle) < 0x1p-26) return angle;
            double k = java.lang.Math.rint(angle * DTAB_INV_STEP);
            double u = fma(-k, DTAB_P1, angle), p = k * DTAB_P2, rh = u - p;
            double rl = ((u - rh) - p) - k * DTAB_P3, t = rh * rh;
            int i = (int) k & 127;
            double sh = DTAB_SIN[i], ch = DTAB_COS[i];
            return sh + fma(ch, rh, fma(ch * rh * t, fma(t, fma(t, S7, S5), S3), fma(sh * t, fma(t, fma(t, C6, C4), -0.5), fma(ch, rl, fma(DTAB_COS_LO[i], rh, DTAB_SIN_LO[i])))));
        }
        return sin(angle);
    }
    /**
     * The cosine of {@code angle} where its sine is computed too, by {@link #sinWithCos(double)};
     * see {@link #sinWithCos(float)} for where the pair is used.
     * <p>
     * Accuracy: within 0.752 ulp, about 1 in 80 not correctly rounded (java.lang.Math.cos: within
     * 1.010 ulp, 1 in 20 not; 3.3 million arguments checked, weighted to the worst table cells).
     * Up to {@code 2^10} and with {@code joml.useFma} the pair is
     * evaluated inline, the angle as {@code k pi/64 + r} with {@code |r| <= pi/128}:
     * {@code sin(k pi/64)} and {@code cos(k pi/64)} from a table, each in two doubles,
     * {@code sin r} and {@code cos r} as polynomials, combined by the angle-sum formulas; the
     * reduction is exact (with {@code pi/64} in three parts and fused multiply-adds), so angles
     * next to a multiple of a right angle keep their small sine or cosine. The sine and the
     * cosine share all of it but the last steps: the double rotations built from angles are
     * 5-51% faster than with {@code java.lang.Math.sin} (a stub call, around which every live
     * float register is saved and restored) and a cosine derived from it as
     * {@code sqrt(1 - sin^2)} (within 4 ulp of 1, far more of a small cosine). A lone sine or
     * cosine stays {@code java.lang.Math}'s: inline, it would cost more than the stub's call.
     * Without {@code joml.useFma}, beyond {@code 2^10}, and with {@code joml.strictMath} (but
     * under {@code joml.fastmath}), it is {@link #cos(double)}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the cosine of {@code angle}
     */
    public static double cosWithSin(double angle) {
        if (!(Cfg.FASTMATH && Cfg.SIN_LOOKUP) && Cfg.USE_FMA && (Cfg.FASTMATH || !Cfg.STRICT_MATH)
                && java.lang.Math.abs(angle) <= 0x1p10) {
            double k = java.lang.Math.rint(angle * DTAB_INV_STEP);
            double u = fma(-k, DTAB_P1, angle), p = k * DTAB_P2, rh = u - p;
            double rl = ((u - rh) - p) - k * DTAB_P3, t = rh * rh;
            int i = (int) k & 127;
            double sh = DTAB_SIN[i], ch = DTAB_COS[i];
            return ch + fma(-sh, rh, fma(-sh * rh * t, fma(t, fma(t, S7, S5), S3), fma(ch * t, fma(t, fma(t, C6, C4), -0.5), fma(-sh, rl, fma(-DTAB_SIN_LO[i], rh, DTAB_COS_LO[i])))));
        }
        return cos(angle);
    }

    // The double sine and cosine (|x| <= 2^10, joml.useFma on): x = k pi/64 + r, r in two
    // doubles rh + rl: pi/64 in three parts, the first a double (x - k P1 is one exact fma), the
    // second of 25 bits (k P2 exact, and so the rounding error of rh = u - k P2 by Fast2Sum, either
    // way round), the third a double (2^-140 left out: 2^-126 of r at the bound); sin r - r and
    // cos r - 1 to r^7 and r^6 (|r| <= pi/128: 4e-19 left out); a table of sin(k pi/64) and
    // cos(k pi/64) in two doubles. Within 0.752 ulp, 1 in 80 not correctly rounded, where
    // java.lang.Math.sin is within 1.012 ulp, 1 in 20 not, and the sine and cosine pair of a
    // rotation costs what the stub's sine and a square root did. Without fma the exact steps take
    // Dekker splits and a longer reduction, slower than java.lang.Math's.
    private static final double DTAB_INV_STEP = 0x1.45f306dc9c883p+4;
    private static final double DTAB_P1 = 0x1.921fb54442d18p-5, DTAB_P2 = 0x1.1a62630000000p-59, DTAB_P3 = 0x1.8a2e03707344ap-86;
    private static final double[] DTAB_SIN = new double[128], DTAB_SIN_LO = new double[128], DTAB_COS = new double[128], DTAB_COS_LO = new double[128];
    static {
        // sin(j pi/64), j = 0..32, correctly rounded, and what is left of each
        double[] q = {0.0, 0x1.91f65f10dd814p-5, 0x1.917a6bc29b42cp-4, 0x1.2c8106e8e613ap-3, 0x1.8f8b83c69a60bp-3, 0x1.f19f97b215f1bp-3, 0x1.294062ed59f06p-2, 0x1.58f9a75ab1fddp-2, 0x1.87de2a6aea963p-2, 0x1.b5d1009e15cc0p-2, 0x1.e2b5d3806f63bp-2, 0x1.073879922ffeep-1, 0x1.1c73b39ae68c8p-1, 0x1.30ff7fce17035p-1, 0x1.44cf325091dd6p-1, 0x1.57d69348ceca0p-1, 0x1.6a09e667f3bcdp-1, 0x1.7b5df226aafafp-1, 0x1.8bc806b151741p-1, 0x1.9b3e047f38741p-1, 0x1.a9b66290ea1a3p-1, 0x1.b728345196e3ep-1, 0x1.c38b2f180bdb1p-1, 0x1.ced7af43cc773p-1, 0x1.d906bcf328d46p-1, 0x1.e212104f686e5p-1, 0x1.e9f4156c62ddap-1, 0x1.f0a7efb9230d7p-1, 0x1.f6297cff75cb0p-1, 0x1.fa7557f08a517p-1, 0x1.fd88da3d12526p-1, 0x1.ff621e3796d7ep-1, 0x1.0000000000000p+0};
        double[] ql = {0.0, -0x1.912bd0d569a90p-61, -0x1.e2718d26ed688p-60, 0x1.13000a89a11e0p-58, -0x1.26d19b9ff8d82p-57, -0x1.42deef11da2c4p-57, -0x1.5d28da2c4612dp-56, -0x1.efdc0d58cf620p-62, -0x1.72cedd3d5a610p-57, 0x1.5b362cb974183p-57, 0x1.e0d891d3c6841p-58, -0x1.a5a014347406cp-55, 0x1.b25dd267f6600p-55, -0x1.efcc626f74a6fp-57, 0x1.8076a2cfdc6b3p-57, -0x1.75720992bfbb2p-55, -0x1.bdd3413b26456p-55, -0x1.0f537acdf0ad7p-56, -0x1.2c5e12ed1336dp-55, -0x1.30ee286712474p-55, 0x1.9f630e8b6dac8p-60, -0x1.bc69f324e6d61p-55, -0x1.6e0b1757c8d07p-56, -0x1.e7b6bb5ab58aep-58, 0x1.457e610231ac2p-56, -0x1.014c76c126527p-55, 0x1.760b1e2e3f81ep-55, 0x1.52c7adc6b4989p-56, 0x1.562172a361fd3p-56, -0x1.7a0a8ca13571fp-55, -0x1.87df6378811c7p-55, -0x1.c57bc2e24aa15p-57, 0.0};
        for (int k = 0; k < 128; k++) {
            int j = k & 31;
            double a = q[j], al = ql[j], b = q[32 - j], bl = ql[32 - j];
            switch (k >> 5) {
                case 0: DTAB_SIN[k] = a; DTAB_SIN_LO[k] = al; DTAB_COS[k] = b; DTAB_COS_LO[k] = bl; break;
                case 1: DTAB_SIN[k] = b; DTAB_SIN_LO[k] = bl; DTAB_COS[k] = 0.0 - a; DTAB_COS_LO[k] = 0.0 - al; break;
                case 2: DTAB_SIN[k] = 0.0 - a; DTAB_SIN_LO[k] = 0.0 - al; DTAB_COS[k] = 0.0 - b; DTAB_COS_LO[k] = 0.0 - bl; break;
                default: DTAB_SIN[k] = 0.0 - b; DTAB_SIN_LO[k] = 0.0 - bl; DTAB_COS[k] = a; DTAB_COS_LO[k] = al;
            }
        }
    }
    private static final double S3 = -1.0 / 6, S5 = 1.0 / 120, S7 = -1.0 / 5040, C4 = 1.0 / 24, C6 = -1.0 / 720;



    /**
     * The tangent of the angle {@code r} (radians).
     * <p>
     * Accuracy: within 0.5003 ulp, about 1 in 550,000 not correctly rounded (every float up to
     * {@code 2^24} checked). Up to {@code 2^24} it is the quotient of
     * the inline sine and cosine of {@link #cos(float)} before their rounding to float (one
     * reduction, one table read each, one division) - where {@code java.lang.Math.tan} is a C call
     * on AArch64 (no stub) and the quotient of its sine and cosine two stub calls: the
     * perspective projections built from a field of view are 45% faster for it. Beyond
     * {@code 2^24}, and with {@code joml.strictMath}, {@code java.lang.Math.tan} (or
     * {@code StrictMath}'s).
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float tan(float r) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.tan(r);
        // (below 2^-12 the tangent rounds to the angle itself, -0 and the subnormals included)
        if (java.lang.Math.abs(r) <= 0x1p24f) {
            if (java.lang.Math.abs(r) < 0x1p-12f) return r;
            // the sine and the cosine of sin(float) and cos(float), sharing their reduction
            double k = java.lang.Math.rint(r * TAB_INV_STEP);
            double d = ((r - k * TAB_STEP_HI) - k * TAB_STEP_MID) - k * TAB_STEP_LO, t = d * d;
            int i = (int) k & 63;
            double c = fma(t, fma(t, 1.0 / 24, -0.5), 1.0), s = fma(d * t, fma(t, 1.0 / 120, -1.0 / 6), d);
            return (float) (fma(TAB_SIN[i], c, TAB_COS[i] * s) / fma(TAB_COS[i], c, -(TAB_SIN[i] * s)));
        }
        return (float) java.lang.Math.tan(r);
    }
    /**
     * The tangent of the angle {@code r} (radians).
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double tan(double r) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.tan(r);
        return java.lang.Math.tan(r);
    }

    /**
     * The hyperbolic sine of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float sinh(float x) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.sinh(x);
        return (float) java.lang.Math.sinh(x);
    }
    /**
     * The hyperbolic sine of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double sinh(double x) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.sinh(x);
        return java.lang.Math.sinh(x);
    }
    /**
     * The hyperbolic cosine of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float cosh(float x) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.cosh(x);
        return (float) java.lang.Math.cosh(x);
    }
    /**
     * The hyperbolic cosine of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double cosh(double x) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.cosh(x);
        return java.lang.Math.cosh(x);
    }
    /**
     * The hyperbolic tangent of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float tanh(float x) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.tanh(x);
        return (float) java.lang.Math.tanh(x);
    }
    /**
     * The hyperbolic tangent of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double tanh(double x) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.tanh(x);
        return java.lang.Math.tanh(x);
    }

    /**
     * The arc sine of {@code r}, as {@code java.lang.Math.asin}.
     * <p>
     * Accuracy: within 0.5008 ulp, about 1 in 750,000 not correctly rounded, and semi-monotonic, as
     * the specification asks of {@code java.lang.Math.asin} (every float in {@code [-1, 1]} and every
     * pair of neighbours checked). Evaluated inline in double, where {@code java.lang.Math.asin} is
     * an fdlibm port in Java, a call: with {@code c = sqrt((1 - |r|)(1 + |r|))}, the cosine of the
     * angle (exact but for the square root's rounding), {@code asin |r| = k pi/8 + asin(|r|
     * cos(k pi/8) - c sin(k pi/8))} for the {@code k} (four comparisons of {@code |r|}) that keeps
     * that argument within {@code sin(pi/16)}, where the arcsine's odd Taylor polynomial to
     * {@code x^11} is exact to well below a float ulp; then {@code r}'s sign. With
     * {@code joml.strictMath}, {@code StrictMath.asin}.
     * <p>
     * Valid input: {@code r} must lie in {@code [-1, 1]}.
     */
    public static float asin(float r) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.asin(r);
        // (written out here and in acos, not in helpers: a call level less for C2's MaxInlineLevel)
        double a = java.lang.Math.abs((double) r);
        int k = (a > 0x1.8f8b83c69a60bp-3 ? 1 : 0) + (a > 0x1.1c73b39ae68c8p-1 ? 1 : 0)
                + (a > 0x1.a9b66290ea1a3p-1 ? 1 : 0) + (a > 0x1.f6297cff75cb0p-1 ? 1 : 0);
        double x = fma(a, TAB_COS[4 * k], -java.lang.Math.sqrt((1 - a) * (1 + a)) * TAB_SIN[4 * k]), t = x * x;
        return (float) java.lang.Math.copySign(fma(k, PI_OVER_8,
                fma(x * t, fma(t, fma(t, fma(t, fma(t, 63.0 / 2816, 35.0 / 1152), 5.0 / 112), 3.0 / 40), 1.0 / 6), x)), r);
    }

    // The float arcsine and arccosine: the angle of |r| is k pi/8 + asin(x), x = |r| cos(k pi/8) -
    // sqrt((1 - |r|)(1 + |r|)) sin(k pi/8) (the angle-difference formula), the products of the
    // float |r| exact in double; k = the number of sin((2j + 1) pi/16), j = 0..3, below |r|, so
    // |x| <= sin(pi/16) = 0.195, where x + x^3/6 + ... + 63 x^11/2816 leaves 3e-10 of it out.
    // cos(k pi/8) and sin(k pi/8) are TAB_COS[4k] and TAB_SIN[4k], the exact 0 and 1 at k = 4.
    private static final double PI_OVER_8 = PI * 0.125;
    /**
     * The arc sine of {@code r}, as {@code java.lang.Math.asin}.
     * <p>
     * Accuracy: within 0.62 ulp, about 1 in 80 not correctly rounded (java.lang.Math.asin, specified
     * to within 1 ulp: 0.88 ulp, 1 in 17 not; 3.2 million arguments checked, most of them near 1/2,
     * where both are at their worst, near 1 and near 0), and semi-monotonic, as the specification
     * asks of java.lang.Math.asin (10 million pairs of neighbours checked). With
     * {@code joml.useFma} it is evaluated inline, where {@code java.lang.Math.asin} is an fdlibm
     * port in Java, a call: {@code asin r = r + r z P(z)} with {@code z = r^2} for
     * {@code |r| <= 1/2}, and above, {@code pi/2 - 2 asin s} with {@code s = sqrt(z)},
     * {@code z = (1 - |r|)/2} - the same polynomial, of degree 12 in {@code z <= 1/4}, minimax for
     * the error it leaves in the angle (2^-59 of it). There the square root's rounding is put back
     * from its exact residual {@code z - s^2}, and the head {@code pi/2 - 2s} is kept exact by a
     * Fast2Sum. Without fma, and with {@code joml.strictMath}, {@code java.lang.Math.asin} (or
     * {@code StrictMath}'s).
     * <p>
     * Valid input: {@code r} must lie in {@code [-1, 1]}.
     */
    public static double asin(double r) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.asin(r);
        if (Cfg.USE_FMA) {
            // (written out here, in acos and in the clamping ones, not in a helper: a call level
            // less; the double minimum keeps the residual's divisor nonzero at r = +-1, where the
            // residual is 0, and leaves 2s alone elsewhere)
            double a = java.lang.Math.abs(r), z, s;
            if (a <= 0.5) { z = a * a; s = a; } else { z = (1 - a) * 0.5; s = java.lang.Math.sqrt(z); }
            double q = s * z, pz = java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z,
                    java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z,
                    java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, 0x1.1134e574c04eep-5, -0x1.6afba0e38dc1ep-6),
                    0x1.71092e6147661p-6), 0x1.b9de38e5b794dp-9), 0x1.64257aa3bec87p-7), 0x1.75014bb614622p-7),
                    0x1.ca2f97570c1c7p-7), 0x1.1c4946fc7ff72p-6), 0x1.6e8be829f8291p-6), 0x1.f1c71a423ab66p-6),
                    0x1.6db6db72e978dp-5), 0x1.333333332cfbap-4), 0x1.555555555557fp-3);
            if (a <= 0.5) return java.lang.Math.copySign(java.lang.Math.fma(q, pz, a), r);
            double h = java.lang.Math.fma(-2, s, PI_OVER_2);
            return java.lang.Math.copySign(h + java.lang.Math.fma(-2, java.lang.Math.fma(q, pz,
                    java.lang.Math.fma(-s, s, z) / java.lang.Math.fma(s, 2, Double.MIN_VALUE)),
                    java.lang.Math.fma(-2, s, PI_OVER_2 - h) + PI_LO * 0.5), r);
        }
        return java.lang.Math.asin(r);
    }

    /**
     * The arc sine of {@code r}, with {@code r} clamped to {@code [-1, 1]} first.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float safeAsin(float r) {
        // (asin(float) written out: a call level less)
        if (r <= -1.0f) return -PI_OVER_2_f;
        if (r >= 1.0f) return PI_OVER_2_f;
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.asin(r);
        double a = java.lang.Math.abs((double) r);
        int k = (a > 0x1.8f8b83c69a60bp-3 ? 1 : 0) + (a > 0x1.1c73b39ae68c8p-1 ? 1 : 0)
                + (a > 0x1.a9b66290ea1a3p-1 ? 1 : 0) + (a > 0x1.f6297cff75cb0p-1 ? 1 : 0);
        double x = fma(a, TAB_COS[4 * k], -java.lang.Math.sqrt((1 - a) * (1 + a)) * TAB_SIN[4 * k]), t = x * x;
        return (float) java.lang.Math.copySign(fma(k, PI_OVER_8,
                fma(x * t, fma(t, fma(t, fma(t, fma(t, 63.0 / 2816, 35.0 / 1152), 5.0 / 112), 3.0 / 40), 1.0 / 6), x)), r);
    }
    /**
     * The arc sine of {@code r}, with {@code r} clamped to {@code [-1, 1]} first.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double safeAsin(double r) {
        // (asin(double) written out: a call level less)
        if (r <= -1.0) return -PI_OVER_2;
        if (r >= 1.0) return PI_OVER_2;
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.asin(r);
        if (Cfg.USE_FMA) {
            double a = java.lang.Math.abs(r), z, s;
            if (a <= 0.5) { z = a * a; s = a; } else { z = (1 - a) * 0.5; s = java.lang.Math.sqrt(z); }
            double q = s * z, pz = java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z,
                    java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z,
                    java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, 0x1.1134e574c04eep-5, -0x1.6afba0e38dc1ep-6),
                    0x1.71092e6147661p-6), 0x1.b9de38e5b794dp-9), 0x1.64257aa3bec87p-7), 0x1.75014bb614622p-7),
                    0x1.ca2f97570c1c7p-7), 0x1.1c4946fc7ff72p-6), 0x1.6e8be829f8291p-6), 0x1.f1c71a423ab66p-6),
                    0x1.6db6db72e978dp-5), 0x1.333333332cfbap-4), 0x1.555555555557fp-3);
            if (a <= 0.5) return java.lang.Math.copySign(java.lang.Math.fma(q, pz, a), r);
            double h = java.lang.Math.fma(-2, s, PI_OVER_2);
            return java.lang.Math.copySign(h + java.lang.Math.fma(-2, java.lang.Math.fma(q, pz,
                    java.lang.Math.fma(-s, s, z) / java.lang.Math.fma(s, 2, Double.MIN_VALUE)),
                    java.lang.Math.fma(-2, s, PI_OVER_2 - h) + PI_LO * 0.5), r);
        }
        return java.lang.Math.asin(r);
    }

    /**
     * The arc cosine of {@code r}, as {@code java.lang.Math.acos}.
     * <p>
     * Accuracy: within 0.5007 ulp, about 1 in 4 million not correctly rounded, and semi-monotonic
     * (every float in {@code [-1, 1]} and every pair of neighbours checked). Evaluated inline in
     * double, from the pieces of {@link #asin(float)}: {@code acos r = (4 - k) pi/8 - asin x} for
     * {@code r >= 0}, and {@code (4 + k) pi/8 + asin x} below, so neither cancels (near
     * {@code r = 1}, {@code k = 4} and the angle is {@code asin} of the small cosine alone). With
     * {@code joml.strictMath}, {@code StrictMath.acos}.
     * <p>
     * Valid input: {@code r} must lie in {@code [-1, 1]}.
     */
    public static float acos(float r) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.acos(r);
        double a = java.lang.Math.abs((double) r), sr = java.lang.Math.copySign(1.0, r);
        int k = (a > 0x1.8f8b83c69a60bp-3 ? 1 : 0) + (a > 0x1.1c73b39ae68c8p-1 ? 1 : 0)
                + (a > 0x1.a9b66290ea1a3p-1 ? 1 : 0) + (a > 0x1.f6297cff75cb0p-1 ? 1 : 0);
        double x = fma(a, TAB_COS[4 * k], -java.lang.Math.sqrt((1 - a) * (1 + a)) * TAB_SIN[4 * k]), t = x * x;
        return (float) fma(4 - sr * k, PI_OVER_8,
                -sr * fma(x * t, fma(t, fma(t, fma(t, fma(t, 63.0 / 2816, 35.0 / 1152), 5.0 / 112), 3.0 / 40), 1.0 / 6), x));
    }
    /**
     * The arc cosine of {@code r}, as {@code java.lang.Math.acos}.
     * <p>
     * Accuracy: within 0.60 ulp, about 1 in 160 not correctly rounded (java.lang.Math.acos, specified
     * to within 1 ulp: 0.90 ulp, 1 in 13 not; the arguments of {@link #asin(double)}), and
     * semi-monotonic (10 million pairs of neighbours checked). With {@code joml.useFma} it is
     * evaluated inline from the pieces of {@link #asin(double)}: {@code pi/2 - asin r} for
     * {@code |r| <= 1/2} (the head {@code pi/2 - r} kept exact by a Fast2Sum), {@code 2 asin s}
     * above and {@code pi - 2 asin s} below. Without fma, and with {@code joml.strictMath},
     * {@code java.lang.Math.acos} (or {@code StrictMath}'s).
     * <p>
     * Valid input: {@code r} must lie in {@code [-1, 1]}.
     */
    public static double acos(double r) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.acos(r);
        if (Cfg.USE_FMA) {
            // (written out, as asin(double): b = +-2 and c = 0 or pi for the sides of 1/2 and -1/2)
            double a = java.lang.Math.abs(r), z, s;
            if (a <= 0.5) { z = r * r; s = r; } else { z = (1 - a) * 0.5; s = java.lang.Math.sqrt(z); }
            double q = s * z, pz = java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z,
                    java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z,
                    java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, 0x1.1134e574c04eep-5, -0x1.6afba0e38dc1ep-6),
                    0x1.71092e6147661p-6), 0x1.b9de38e5b794dp-9), 0x1.64257aa3bec87p-7), 0x1.75014bb614622p-7),
                    0x1.ca2f97570c1c7p-7), 0x1.1c4946fc7ff72p-6), 0x1.6e8be829f8291p-6), 0x1.f1c71a423ab66p-6),
                    0x1.6db6db72e978dp-5), 0x1.333333332cfbap-4), 0x1.555555555557fp-3);
            if (a <= 0.5) {
                double h = PI_OVER_2 - r;
                return h + ((PI_OVER_2 - h) - r - java.lang.Math.fma(q, pz, -PI_LO * 0.5));
            }
            double b = java.lang.Math.copySign(2.0, r), c = (2 - b) * PI_OVER_4, h = java.lang.Math.fma(b, s, c);
            return h + java.lang.Math.fma(b, java.lang.Math.fma(q, pz,
                    java.lang.Math.fma(-s, s, z) / java.lang.Math.fma(s, 2, Double.MIN_VALUE)),
                    java.lang.Math.fma(b, s, c - h) + (2 - b) * (PI_LO * 0.25));
        }
        return java.lang.Math.acos(r);
    }

    /**
     * The arc cosine of {@code v}, with {@code v} clamped to {@code [-1, 1]} first.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float safeAcos(float v) {
        // (acos(float) written out: a call level less)
        if (v < -1.0f) return PI_f;
        if (v > 1.0f) return 0.0f;
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.acos(v);
        double a = java.lang.Math.abs((double) v), sr = java.lang.Math.copySign(1.0, v);
        int k = (a > 0x1.8f8b83c69a60bp-3 ? 1 : 0) + (a > 0x1.1c73b39ae68c8p-1 ? 1 : 0)
                + (a > 0x1.a9b66290ea1a3p-1 ? 1 : 0) + (a > 0x1.f6297cff75cb0p-1 ? 1 : 0);
        double x = fma(a, TAB_COS[4 * k], -java.lang.Math.sqrt((1 - a) * (1 + a)) * TAB_SIN[4 * k]), t = x * x;
        return (float) fma(4 - sr * k, PI_OVER_8,
                -sr * fma(x * t, fma(t, fma(t, fma(t, fma(t, 63.0 / 2816, 35.0 / 1152), 5.0 / 112), 3.0 / 40), 1.0 / 6), x));
    }
    /**
     * The arc cosine of {@code v}, with {@code v} clamped to {@code [-1, 1]} first.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double safeAcos(double v) {
        // (acos(double) written out: a call level less)
        if (v < -1.0) return PI;
        if (v > 1.0) return 0.0;
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.acos(v);
        if (Cfg.USE_FMA) {
            double a = java.lang.Math.abs(v), z, s;
            if (a <= 0.5) { z = v * v; s = v; } else { z = (1 - a) * 0.5; s = java.lang.Math.sqrt(z); }
            double q = s * z, pz = java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z,
                    java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z,
                    java.lang.Math.fma(z, java.lang.Math.fma(z, java.lang.Math.fma(z, 0x1.1134e574c04eep-5, -0x1.6afba0e38dc1ep-6),
                    0x1.71092e6147661p-6), 0x1.b9de38e5b794dp-9), 0x1.64257aa3bec87p-7), 0x1.75014bb614622p-7),
                    0x1.ca2f97570c1c7p-7), 0x1.1c4946fc7ff72p-6), 0x1.6e8be829f8291p-6), 0x1.f1c71a423ab66p-6),
                    0x1.6db6db72e978dp-5), 0x1.333333332cfbap-4), 0x1.555555555557fp-3);
            if (a <= 0.5) {
                double h = PI_OVER_2 - v;
                return h + ((PI_OVER_2 - h) - v - java.lang.Math.fma(q, pz, -PI_LO * 0.5));
            }
            double b = java.lang.Math.copySign(2.0, v), c = (2 - b) * PI_OVER_4, h = java.lang.Math.fma(b, s, c);
            return h + java.lang.Math.fma(b, java.lang.Math.fma(q, pz,
                    java.lang.Math.fma(-s, s, z) / java.lang.Math.fma(s, 2, Double.MIN_VALUE)),
                    java.lang.Math.fma(b, s, c - h) + (2 - b) * (PI_LO * 0.25));
        }
        return java.lang.Math.acos(v);
    }

    /**
     * The arc tangent of {@code r}.
     * <p>
     * Accuracy: as {@link #atan2(float, float)}, of which it is {@code atan2(r, 1)}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float atan(float r) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.atan(r);
        // atan2(r, 1) of atan2(float, float), written out with x = 1 (a call level less)
        double ay = java.lang.Math.abs((double) r);
        double z = java.lang.Math.min(1.0, ay) / java.lang.Math.max(1.0, ay);
        int j = (int) (z * 32 + 0.5);
        double c = j * 0.03125;
        double d = (z - c) / fma(z, c, 1.0), t = d * d;
        double a = DTAB_ATAN[2 * j] + fma(d * t, fma(t, 0.2, -1.0 / 3), d);
        double s = java.lang.Math.signum(1.0 - ay);
        return (float) java.lang.Math.copySign(fma(1.0, fma(s, a, (1 - s) * (PI_OVER_2 / 2)), 0.0), r);
    }
    /**
     * The arc tangent of {@code r}.
     * <p>
     * Accuracy: as {@link #atan2(double, double)}, whose {@code atan2(r, 1)} it is with
     * {@code joml.useFma} (without, and with {@code joml.strictMath}, {@code java.lang.Math.atan},
     * or {@code StrictMath}'s).
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double atan(double r) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.atan(r);
        if (Cfg.USE_FMA) {
            // atan2(r, 1) of atan2(double, double), written out with x = 1 (a call level less):
            // signum(x) = 1, so g = signum(1 - |r|) and q = (1 - g)/4
            double ay = java.lang.Math.abs(r);
            double mn = java.lang.Math.min(1.0, ay), mx = java.lang.Math.max(1.0, ay);
            if (mx == Double.POSITIVE_INFINITY) return java.lang.Math.atan2(r, 1.0);
            int j = (int) (mn / mx * 64 + 0.5);
            double c = j * 0x1p-6;
            double d = fma(-c, mx, mn) / fma(c, mn, mx), t = d * d;
            double g = java.lang.Math.signum(1.0 - ay), q = (1 - g) * 0.25;
            double qh = q * PI, ql = fma(q, PI, -qh) + q * PI_LO;
            double h = fma(g, DTAB_ATAN[j], qh), e = fma(g, DTAB_ATAN[j], qh - h);
            double h2 = fma(g, d, h), e2 = fma(g, d, h - h2);
            return java.lang.Math.copySign(h2 + fma(g, fma(d * t, fma(t, fma(t, -1.0 / 7, 0.2), -1.0 / 3), DTAB_ATAN_LO[j]), e + e2 + ql), r);
        }
        return java.lang.Math.atan(r);
    }

    /**
     * atan on [0, 1] by an odd polynomial a (1 + c3 a^2 + c5 a^4 + c7 a^6), after
     * https://math.stackexchange.com/questions/1098487/atan2-faster-approximation/1105038#answer-1105038,
     * refit (minimax, absolute error 1.51e-4) with the slope 1 at 0 kept and p(1) = pi/4 exactly
     * - in this evaluation order, at both precisions - so both octants meet at |y| = |x| (the
     * original coefficients missed pi/4 by 2e-4: a 4e-4 jump across that diagonal).
     */
    private static double fastAtan2(double y, double x) {
        double ax = x >= 0.0 ? x : -x;
        double ay = y >= 0.0 ? y : -y;
        // Both zero: the ratio below would be NaN. Match java.lang.Math.atan2,
        // which yields +-0 for x = +0 and +-pi for x = -0 (sign taken from y).
        if (ax == 0.0 && ay == 0.0)
            return java.lang.Math.copySign(Double.doubleToRawLongBits(x) < 0 ? PI : 0.0, y);
        // |y| == |x| is the diagonal, ratio 1 - also for two infinities, whose ratio would be NaN
        // (java.lang.Math gives +-pi/4 and +-3pi/4 there); NaN input fails every test.
        double a = ay > ax ? ax / ay : ay == ax ? 1.0 : ay / ax;
        double s = a * a;
        double r = ((((-0.04233020945105401 * s) + 0.1534299488420681) * s) - 0.3257015759935658) * s * a + a;
        if (ay > ax) r = PI_OVER_2 - r;
        if (x < 0.0) r = PI - r;
        // copySign keeps -0.0 negative: atan2(-0.0, -1) is -pi, like java.lang.Math.
        return java.lang.Math.copySign(r, y);
    }

    private static float fastAtan2(float y, float x) {
        float ax = x >= 0.0f ? x : -x;
        float ay = y >= 0.0f ? y : -y;
        if (ax == 0.0f && ay == 0.0f)
            return java.lang.Math.copySign(Float.floatToRawIntBits(x) < 0 ? PI_f : 0.0f, y);
        float a = ay > ax ? ax / ay : ay == ax ? 1.0f : ay / ax;
        float s = a * a;
        float r = ((((-0.0423302092f * s) + 0.153429955f) * s) - 0.325701565f) * s * a + a;
        if (ay > ax) r = PI_OVER_2_f - r;
        if (x < 0.0f) r = PI_f - r;
        return java.lang.Math.copySign(r, y);
    }

    /**
     * The angle of the point {@code (x, y)}; see {@link #setFastmath} for the approximation.
     * <p>
     * Accuracy: the correctly rounded angle but within about 1e-12 (relative) of a rounding
     * boundary, which may round the other way (8 in 40 million pairs), within 0.5001 ulp; the
     * signed zeros and axes exact. It is evaluated inline in double: with {@code z = min/max} of
     * {@code |y|} and {@code |x|}, {@code atan z = atan(j/32) + atan d}, {@code atan(j/32)} from a
     * table, {@code d = (z - j/32) / (1 + z j/32)} ({@code |d| <= 1/64}) as
     * {@code d - d^3/3 + d^5/5}, the octant restored without a branch. {@code java.lang.Math.atan2}
     * (and the {@code atan} it reduces to) is an fdlibm port in Java too big to inline, a call
     * around which every live float register is saved and restored: the Euler angle queries are
     * 53-59% faster for it, {@code angleBetween} and the quaternion {@code angle} 26%. With
     * {@code joml.strictMath}, {@code java.lang.StrictMath.atan2}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float atan2(float y, float x) {
        if (Cfg.FASTMATH) return fastAtan2(y, x);
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.atan2(y, x);
        // (both zero: the special cases of atan2, the quotient below would be 0/0)
        if (x == 0.0f && y == 0.0f) return (float) java.lang.Math.atan2(y, x);
        // In double, written out (a call level less); one of y and x may be infinite (a degenerate
        // path's power-of-two scaling can overflow a dot product: atan2(0, Inf) is 0). d comes from
        // the rounded quotient z, absolutely as exact as z (1e-16, far below a float ulp of
        // atan(j/32) for j >= 1; for j = 0, d is z itself); d^7/7 left out is 1.3e-13 of d. The
        // octant: s = signum(|x| - |y|) picks a or pi/2 - a (|x| = |y|: both pi/4), then signum(x)
        // a or pi - that (x = 0: both pi/2), each one fma; copySign y's sign.
        double ax = java.lang.Math.abs((double) x), ay = java.lang.Math.abs((double) y);
        double z = java.lang.Math.min(ax, ay) / java.lang.Math.max(ax, ay);
        int j = (int) (z * 32 + 0.5);
        double c = j * 0.03125;
        double d = (z - c) / fma(z, c, 1.0), t = d * d;
        double a = DTAB_ATAN[2 * j] + fma(d * t, fma(t, 0.2, -1.0 / 3), d);
        double s = java.lang.Math.signum(ax - ay);
        double r = fma(s, a, (1 - s) * (PI_OVER_2 / 2));
        double sx = java.lang.Math.signum((double) x);
        return (float) java.lang.Math.copySign(fma(sx, r, (1 - sx) * PI_OVER_2), (double) y);
    }

    /**
     * The angle of the point {@code (x, y)}, as {@code java.lang.Math.atan2}; see {@link
     * #setFastmath} for the approximation.
     * <p>
     * Accuracy: within 1.38 ulp, about 1 in 100 not correctly rounded (java.lang.Math.atan2: within
     * 1.46 ulp, 1 in 6 not; 3.6 million argument pairs checked). With {@code joml.useFma} it is
     * evaluated inline: {@code atan(j/64)}
     * from a table in two doubles, a short polynomial for the rest and the octant restored without
     * a branch, where {@code java.lang.Math.atan2} is an fdlibm port in Java too big to inline.
     * Without fma, and with {@code joml.strictMath}, {@code java.lang.Math.atan2} (or
     * {@code StrictMath}'s).
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double atan2(double y, double x) {
        if (Cfg.FASTMATH) return fastAtan2(y, x);
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.atan2(y, x);
        if (Cfg.USE_FMA) {
            // Written out (a call level less): z = min/max(|y|, |x|), j = round(64 z), and atan z =
            // atan(j/64) + atan d with d = (z - j/64)/(1 + z j/64) = (min - c max)/(max + c min),
            // whose numerator is one exact fma (|d| <= 1/128: d - d^3/3 + d^5/5 - d^7/7, 4e-20 left
            // out). The octant: q pi + g (atan(j/64) + atan d) with g = +-1 (0 on the diagonal) and
            // q in quarters, from signum(|x| - |y|) and signum(x) like the float one; the head
            // q pi + g atan(j/64) + g d is kept exact by two Fast2Sums, so only d's own rounding and
            // the last addition are left: within 1.38 ulp - just above z = 1/128 the angle is no
            // larger than d, whose own rounding then counts in full, more so where the angle drops
            // below a power of two. Both zero and an infinite one are java.lang.Math.atan2's
            // special cases.
            // (both zero and an infinite one fall through to java.lang.Math.atan2: the one call of it
            // keeps this method within the 325 bytes of FreqInlineSize)
            double ax = java.lang.Math.abs(x), ay = java.lang.Math.abs(y);
            double mn = java.lang.Math.min(ax, ay), mx = java.lang.Math.max(ax, ay);
            if (mx != 0.0 && mx != Double.POSITIVE_INFINITY) {
                int j = (int) (mn / mx * 64 + 0.5);
                double c = j * 0x1p-6;
                double d = fma(-c, mx, mn) / fma(c, mn, mx), t = d * d;
                double s = java.lang.Math.signum(ax - ay), sx = java.lang.Math.signum(x), g = sx * s;
                double q = sx * (1 - s) * 0.25 + (1 - sx) * 0.5;
                double qh = q * PI, ql = fma(q, PI, -qh) + q * PI_LO;
                double h = fma(g, DTAB_ATAN[j], qh), e = fma(g, DTAB_ATAN[j], qh - h);
                double h2 = fma(g, d, h), e2 = fma(g, d, h - h2);
                return java.lang.Math.copySign(h2 + fma(g, fma(d * t, fma(t, fma(t, -1.0 / 7, 0.2), -1.0 / 3), DTAB_ATAN_LO[j]), e + e2 + ql), y);
            }
        }
        return java.lang.Math.atan2(y, x);
    }

    // atan(j/64), j = 0..64, correctly rounded, and what is left of each: the double atan2's table,
    // whose even entries are the float atan2's atan(j/32).
    private static final double[] DTAB_ATAN = {
        0.0, 0x1.fff555bbb729bp-7, 0x1.ffd55bba97625p-6, 0x1.7fb818430da2ap-5,
        0x1.ff55bb72cfdeap-5, 0x1.3f59f0e7c559dp-4, 0x1.7ee182602f10fp-4, 0x1.be39ebe6f07c3p-4,
        0x1.fd5ba9aac2f6ep-4, 0x1.1e1fafb043727p-3, 0x1.3d6eee8c6626cp-3, 0x1.5c9811e3ec26ap-3,
        0x1.7b97b4bce5b02p-3, 0x1.9a6a8e96c8626p-3, 0x1.b90d7529260a2p-3, 0x1.d77d5df205736p-3,
        0x1.f5b75f92c80ddp-3, 0x1.09dc597d86362p-2, 0x1.18bf5a30bf178p-2, 0x1.278372057ef46p-2,
        0x1.362773707ebccp-2, 0x1.44aa436c2af0ap-2, 0x1.530ad9951cd4ap-2, 0x1.614840309cfe2p-2,
        0x1.6f61941e4def1p-2, 0x1.7d5604b63b3f7p-2, 0x1.8b24d394a1b25p-2, 0x1.98cd5454d6b18p-2,
        0x1.a64eec3cc23fdp-2, 0x1.b3a911da65c6cp-2, 0x1.c0db4c94ec9f0p-2, 0x1.cde53432c1351p-2,
        0x1.dac670561bb4fp-2, 0x1.e77eb7f175a34p-2, 0x1.f40dd0b541418p-2, 0x1.0039c73c1a40cp-1,
        0x1.0657e94db30d0p-1, 0x1.0c6145b5b43dap-1, 0x1.1255d9bfbd2a9p-1, 0x1.1835a88be7c13p-1,
        0x1.1e00babdefeb4p-1, 0x1.23b71e2cc9e6ap-1, 0x1.2958e59308e31p-1, 0x1.2ee628406cbcap-1,
        0x1.345f01cce37bbp-1, 0x1.39c391cd4171ap-1, 0x1.3f13fb89e96f4p-1, 0x1.445065b795b56p-1,
        0x1.4978fa3269ee1p-1, 0x1.4e8de5bb6ec04p-1, 0x1.538f57b89061fp-1, 0x1.587d81f732fbbp-1,
        0x1.5d58987169b18p-1, 0x1.6220d115d7b8ep-1, 0x1.66d663923e087p-1, 0x1.6b798920b3d99p-1,
        0x1.700a7c5784634p-1, 0x1.748978fba8e0fp-1, 0x1.78f6bbd5d315ep-1, 0x1.7d528289fa093p-1,
        0x1.819d0b7158a4dp-1, 0x1.85d69576cc2c5p-1, 0x1.89ff5ff57f1f8p-1, 0x1.8e17aa99cc05ep-1,
        0x1.921fb54442d18p-1};
    private static final double[] DTAB_ATAN_LO = {
        0.0, -0x1.220c39d4dff50p-61, -0x1.5ec431444912cp-60, -0x1.86ef8f794f105p-63,
        -0x1.c934d86d23f1dp-60, 0x1.ac4ce285df847p-58, -0x1.cfb654c0c3d98p-58,
        0x1.f7b8f29a05987p-58, -0x1.cd37686760c17p-59, -0x1.b485914dacf8cp-59,
        0x1.61a3b0ce9281bp-57, -0x1.054ab2c010f3dp-58, 0x1.347b0b4f881cap-58,
        0x1.cf601e7b4348ep-59, 0x1.17b10d2e0e5abp-61, 0x1.c648d1534597ep-57, 0x1.8ab6e3cf7afbdp-57,
        0x1.62e47390cb865p-56, 0x1.30ca4748b1bf9p-57, -0x1.077cdd36dfc81p-56,
        -0x1.963a544b672d8p-57, -0x1.5d5e43c55b3bap-56, -0x1.2566480884082p-57,
        -0x1.a725715711f00p-56, -0x1.c63aae6f6e918p-56, 0x1.69c885c2b249ap-56,
        0x1.b6d0ba3748fa8p-56, 0x1.9e6c988fd0a77p-56, -0x1.24dec1b50b7ffp-56,
        0x1.ae187b1ca5040p-56, -0x1.cc1ce70934c34p-56, -0x1.a2cfa4418f1adp-56,
        0x1.a2b7f222f65e2p-56, 0x1.0e53dc1bf3435p-56, -0x1.a3992dc382a23p-57,
        -0x1.b32c949c9d593p-55, -0x1.d5b495f6349e6p-56, 0x1.974fa13b5404fp-58,
        -0x1.2bdaee1c0ee35p-58, 0x1.c621cec00c301p-55, -0x1.928df287a668fp-58,
        0x1.c421c9f38224ep-57, -0x1.09e73b0c6c087p-56, 0x1.c5d5e9ff0cf8dp-55,
        0x1.1021137c71102p-55, -0x1.2304331d8bf46p-55, 0x1.ecf8b492644f0p-56,
        -0x1.f76d0163f79c8p-56, 0x1.2419a87f2a458p-56, 0x1.4a33dbeb3796cp-55,
        -0x1.1bb74abda520cp-55, -0x1.5e5c9d8c5a950p-56, 0x1.0028e4bc5e7cap-57,
        -0x1.2b785350ee8c1p-57, -0x1.6ea6febe8bbbap-56, -0x1.a80386188c50ep-55,
        -0x1.8c34d25aadef6p-56, 0x1.7b2a6165884a1p-59, 0x1.406a089803740p-55,
        0x1.560821e2f3aa9p-55, -0x1.bf76229d3b917p-56, 0x1.6b66e7fc8b8c3p-57,
        -0x1.55b9a5e177a1bp-55, -0x1.ec182ab042f61p-56, 0x1.1a62633145c07p-55};
    private static final double PI_LO = 0x1.1a62633145c07p-53;

    /**
     * The square root of {@code r}.
     * <p>
     * Valid input: {@code r} must not be negative.
     */
    public static float sqrt(float r) { return (float) java.lang.Math.sqrt(r); }
    /**
     * The square root of {@code r}.
     * <p>
     * Valid input: {@code r} must not be negative.
     */
    public static double sqrt(double r) { return java.lang.Math.sqrt(r); }

    /**
     * The reciprocal square root {@code 1 / sqrt(r)}.
     * <p>
     * Valid input: {@code r} must be positive.
     */
    public static float invsqrt(float r) { return 1.0f / (float) java.lang.Math.sqrt(r); }
    /**
     * The reciprocal square root {@code 1 / sqrt(r)}.
     * <p>
     * Valid input: {@code r} must be positive.
     */
    public static double invsqrt(double r) { return 1.0 / java.lang.Math.sqrt(r); }

    /**
     * {@code a} raised to the power {@code b}, as {@code java.lang.Math.pow}.
     * <p>
     * Valid input: {@code a} must not be negative.
     */
    public static double pow(double a, double b) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.pow(a, b);
        return java.lang.Math.pow(a, b);
    }
    /**
     * Euler's number raised to the power {@code a}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double exp(double a) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.exp(a);
        return java.lang.Math.exp(a);
    }

    /**
     * {@code a * b + c}, fused into one rounding unless {@code joml.useFma} is off (see {@link
     * #setUseFma}).
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float fma(float a, float b, float c) {
        if (Cfg.USE_FMA) return java.lang.Math.fma(a, b, c);
        return a * b + c;
    }
    /**
     * {@code a * b + c}, fused into one rounding unless {@code joml.useFma} is off (see {@link
     * #setUseFma}).
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double fma(double a, double b, double c) {
        if (Cfg.USE_FMA) return java.lang.Math.fma(a, b, c);
        return a * b + c;
    }

    /**
     * The natural logarithm of {@code a}.
     * <p>
     * Valid input: {@code a} must be positive.
     */
    public static float log(float a) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.log(a);
        return (float) java.lang.Math.log(a);
    }
    /**
     * The natural logarithm of {@code a}.
     * <p>
     * Valid input: {@code a} must be positive.
     */
    public static double log(double a) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.log(a);
        return java.lang.Math.log(a);
    }
    /**
     * The base-10 logarithm of {@code a}.
     * <p>
     * Valid input: {@code a} must be positive.
     */
    public static float log10(float a) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.log10(a);
        return (float) java.lang.Math.log10(a);
    }
    /**
     * The base-10 logarithm of {@code a}.
     * <p>
     * Valid input: {@code a} must be positive.
     */
    public static double log10(double a) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.log10(a);
        return java.lang.Math.log10(a);
    }
    /**
     * The base-2 logarithm, exact for every power of two: the exponent plus the logarithm of the
     * significand, {@code log(m) / ln 2} for m in [sqrt(2)/2, sqrt(2)) - so that {@code log2(8)} is
     * 3 rather than {@code log(8) / ln 2 = 2.9999999999999996}, and arguments near 1 keep their
     * relative accuracy (the significand's logarithm is at most 1/2 and does not cancel against
     * the exponent). Zero, negative, NaN and infinite arguments give what {@link #log(double)}
     * gives (-Infinity, NaN, NaN, Infinity).
     * <p>
     * Valid input: {@code a} must be positive.
     */
    public static double log2(double a) {
        if (!(a > 0) || a == Double.POSITIVE_INFINITY) return Cfg.STRICT_MATH ? java.lang.StrictMath.log(a) : java.lang.Math.log(a);
        int shift = 0;
        if (a < Double.MIN_NORMAL) { // subnormal: normalize first
            a *= 0x1p54;
            shift = 54;
        }
        int e = java.lang.Math.getExponent(a);
        double m = java.lang.Math.scalb(a, -e);
        if (m > SQRT_2) {
            m *= 0.5;
            e++;
        }
        return (e - shift) + (Cfg.STRICT_MATH ? java.lang.StrictMath.log(m) : java.lang.Math.log(m)) * INV_LN_2;
    }
    /**
     * The base-2 logarithm, exact for every power of two; see {@link #log2(double)}.
     * <p>
     * Valid input: {@code a} must be positive.
     */
    public static float log2(float a) {
        // (log2(double) written out: a call level less)
        double x = a;
        if (!(x > 0) || x == Double.POSITIVE_INFINITY) return (float) (Cfg.STRICT_MATH ? java.lang.StrictMath.log(x) : java.lang.Math.log(x));
        int shift = 0;
        if (x < Double.MIN_NORMAL) { // subnormal: normalize first
            x *= 0x1p54;
            shift = 54;
        }
        int e = java.lang.Math.getExponent(x);
        double m = java.lang.Math.scalb(x, -e);
        if (m > SQRT_2) {
            m *= 0.5;
            e++;
        }
        return (float) ((e - shift) + (Cfg.STRICT_MATH ? java.lang.StrictMath.log(m) : java.lang.Math.log(m)) * INV_LN_2);
    }

    private static final double INV_LN_2 = 1.4426950408889634;
    private static final double SQRT_2 = 1.4142135623730951;
    /**
     * The natural logarithm of {@code 1 + a}, accurate for small {@code a}.
     * <p>
     * Valid input: {@code a} must lie in {@code (-1, Infinity)}.
     */
    public static float log1p(float a) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.log1p(a);
        return (float) java.lang.Math.log1p(a);
    }
    /**
     * The natural logarithm of {@code 1 + a}, accurate for small {@code a}.
     * <p>
     * Valid input: {@code a} must lie in {@code (-1, Infinity)}.
     */
    public static double log1p(double a) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.log1p(a);
        return java.lang.Math.log1p(a);
    }
    /**
     * {@code e^a - 1}, accurate for small {@code a}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float expm1(float a) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.expm1(a);
        return (float) java.lang.Math.expm1(a);
    }
    /**
     * {@code e^a - 1}, accurate for small {@code a}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double expm1(double a) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.expm1(a);
        return java.lang.Math.expm1(a);
    }
    // java.lang.Math.hypot is the fdlibm port in Java, scaling around overflow and underflow and
    // sorting out NaN and the infinities: 826 ns (float) and 679 ns (double) for 256 calls, against
    // 140 ns for the square root of the sum of squares - which for valid input neither overflows nor
    // underflows (in double for a float: 1e6^2 and 1e-6^2 are far inside its range, and the squares
    // exact, so the float result is the correctly rounded hypot but for a rare double rounding).
    // Plain IEEE arithmetic, the same on every platform: no StrictMath branch.
    /**
     * {@code sqrt(x * x + y * y)}, computed in double.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float hypot(float x, float y) {
        double dx = x, dy = y;
        return (float) java.lang.Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * {@code sqrt(x * x + y * y)}, the sum of squares fused (see {@link #setUseFma}).
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double hypot(double x, double y) { return java.lang.Math.sqrt(fma(x, x, y * y)); }
    /**
     * The cube root of {@code a}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float cbrt(float a) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.cbrt(a);
        return (float) java.lang.Math.cbrt(a);
    }
    /**
     * The cube root of {@code a}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double cbrt(double a) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.cbrt(a);
        return java.lang.Math.cbrt(a);
    }
    /**
     * {@code a} raised to the power {@code b}, as {@code java.lang.Math.pow}.
     * <p>
     * Valid input: {@code a} must not be negative.
     */
    public static float pow(float a, float b) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.pow(a, b);
        return (float) java.lang.Math.pow(a, b);
    }
    /**
     * Euler's number raised to the power {@code a}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float exp(float a) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.exp(a);
        return (float) java.lang.Math.exp(a);
    }
    /**
     * The size of a unit in the last place of the argument, as {@code java.lang.Math.ulp}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float ulp(float f) { return java.lang.Math.ulp(f); }
    /**
     * The size of a unit in the last place of the argument, as {@code java.lang.Math.ulp}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double ulp(double d) { return java.lang.Math.ulp(d); }
    /**
     * The adjacent floating-point value towards positive infinity.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float nextUp(float f) {
        if (Float.isNaN(f) || f == Float.POSITIVE_INFINITY) return f;
        f += 0.0f;
        return Float.intBitsToFloat(Float.floatToRawIntBits(f) + (f >= 0.0f ? +1 : -1));
    }
    /**
     * The adjacent floating-point value towards positive infinity.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double nextUp(double d) {
        if (Double.isNaN(d) || d == Double.POSITIVE_INFINITY) return d;
        d += 0.0d;
        return Double.longBitsToDouble(Double.doubleToRawLongBits(d) + (d >= 0.0d ? +1L : -1L));
    }
    /**
     * The adjacent floating-point value towards negative infinity.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float nextDown(float f) {
        if (Float.isNaN(f) || f == Float.NEGATIVE_INFINITY) return f;
        if (f == 0.0f) return -Float.MIN_VALUE;
        return Float.intBitsToFloat(Float.floatToRawIntBits(f) + (f > 0.0f ? -1 : +1));
    }
    /**
     * The adjacent floating-point value towards negative infinity.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double nextDown(double d) {
        if (Double.isNaN(d) || d == Double.NEGATIVE_INFINITY) return d;
        if (d == 0.0d) return -Double.MIN_VALUE;
        return Double.longBitsToDouble(Double.doubleToRawLongBits(d) + (d > 0.0d ? -1L : +1L));
    }
    /**
     * {@code magnitude} with the sign bit of {@code sign}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float copySign(float magnitude, float sign) {
        return Float.intBitsToFloat((Float.floatToRawIntBits(sign) & 0x80000000)
                | (Float.floatToRawIntBits(magnitude) & 0x7fffffff));
    }
    /**
     * {@code magnitude} with the sign bit of {@code sign}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double copySign(double magnitude, double sign) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(sign) & 0x8000000000000000L)
                | (Double.doubleToRawLongBits(magnitude) & 0x7fffffffffffffffL));
    }

    /**
     * The absolute value of the argument, as {@code java.lang.Math.abs}.
     * <p>
     * Valid input: any value.
     */
    public static int abs(int r) { return java.lang.Math.abs(r); }
    /**
     * The absolute value of the argument, as {@code java.lang.Math.abs}.
     * <p>
     * Valid input: any value.
     */
    public static long abs(long r) { return java.lang.Math.abs(r); }
    /**
     * The absolute value of the argument, as {@code java.lang.Math.abs}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float abs(float r) { return java.lang.Math.abs(r); }
    /**
     * The absolute value of the argument, as {@code java.lang.Math.abs}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double abs(double r) { return java.lang.Math.abs(r); }

    /**
     * The larger of the two values, as {@code java.lang.Math.max}.
     * <p>
     * Valid input: any value.
     */
    public static int max(int x, int y) { return java.lang.Math.max(x, y); }
    /**
     * The smaller of the two values, as {@code java.lang.Math.min}.
     * <p>
     * Valid input: any value.
     */
    public static int min(int x, int y) { return java.lang.Math.min(x, y); }
    /**
     * The larger of the two values, as {@code java.lang.Math.max}.
     * <p>
     * Valid input: any value.
     */
    public static long max(long x, long y) { return java.lang.Math.max(x, y); }
    /**
     * The smaller of the two values, as {@code java.lang.Math.min}.
     * <p>
     * Valid input: any value.
     */
    public static long min(long x, long y) { return java.lang.Math.min(x, y); }
    // Selects, not java.lang.Math's min/max: x86 C2 compiles Math.min(float, float) to vminps with
    // three vblendvps and a vcmpunordps around it - a NaN operand gives NaN, -0.0 sorts below 0.0 -
    // about a dozen uops, and a select to a compare and a branch. JMH (P-cores, the min and the max
    // of 8 values, 256 sets): 3194 ns against 670 ns on values ordered alike and 1019 ns on random
    // ones (the branches mispredicting); Float4x4.orthoCrop -57%, frustumAabb -49%, the AABB union
    // -52%. For valid input the value is java.lang.Math's but for the sign of a zero, which the
    // package documentation leaves open; the SIMD code paths' lane-wise min/max (the Vector API's,
    // java.lang.Math semantics) agree with them there.
    /**
     * The larger of the two values, by a comparison: either of {@code 0.0} and {@code -0.0} may come
     * back for the two, and a NaN {@code b} gives NaN, a NaN {@code a} gives {@code b}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float max(float a, float b) { return a >= b ? a : b; }
    /**
     * The smaller of the two values, by a comparison: either of {@code 0.0} and {@code -0.0} may come
     * back for the two, and a NaN {@code b} gives NaN, a NaN {@code a} gives {@code b}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float min(float a, float b) { return a <= b ? a : b; }
    /**
     * The larger of the two values, by a comparison: either of {@code 0.0} and {@code -0.0} may come
     * back for the two, and a NaN {@code b} gives NaN, a NaN {@code a} gives {@code b}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double max(double a, double b) { return a >= b ? a : b; }
    /**
     * The smaller of the two values, by a comparison: either of {@code 0.0} and {@code -0.0} may come
     * back for the two, and a NaN {@code b} gives NaN, a NaN {@code a} gives {@code b}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double min(double a, double b) { return a <= b ? a : b; }

    /**
     * {@code v} clamped to {@code [lo, hi]}.
     * <p>
     * Valid input: {@code lo} must not exceed {@code hi}.
     */
    public static int clamp(int v, int lo, int hi) { return java.lang.Math.min(java.lang.Math.max(v, lo), hi); }
    /**
     * {@code v} clamped to {@code [lo, hi]}.
     * <p>
     * Valid input: {@code lo} must not exceed {@code hi}.
     */
    public static long clamp(long v, long lo, long hi) { return java.lang.Math.min(java.lang.Math.max(v, lo), hi); }
    /**
     * {@code v} clamped to {@code [lo, hi]}, by comparisons (see {@link #min(float, float)}): a NaN
     * {@code v} gives NaN.
     * <p>
     * Valid input: {@code lo} must not exceed {@code hi}.
     */
    public static float clamp(float v, float lo, float hi) { return v < lo ? lo : v > hi ? hi : v; }
    /**
     * {@code v} clamped to {@code [lo, hi]}, by comparisons (see {@link #min(float, float)}): a NaN
     * {@code v} gives NaN.
     * <p>
     * Valid input: {@code lo} must not exceed {@code hi}.
     */
    public static double clamp(double v, double lo, double hi) { return v < lo ? lo : v > hi ? hi : v; }

    /**
     * The largest integral value not above {@code v}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double floor(double v) { return java.lang.Math.floor(v); }
    /**
     * The largest integral value not above {@code v}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float floor(float v) { return (float) java.lang.Math.floor(v); }
    /**
     * The smallest integral value not below {@code v}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double ceil(double v) { return java.lang.Math.ceil(v); }
    /**
     * The smallest integral value not below {@code v}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float ceil(float v) { return (float) java.lang.Math.ceil(v); }

    // Integer floor/ceil division. The byte/short overloads return int, NOT the
    // lane type: the generated byte/short vector ops evaluate at int precision
    // (Java promotes sub-int operands) and narrow only when they store into a
    // same-width dest - every such store carries an explicit (byte)/(short)
    // cast, see ExprCompiler.compileMathCall. A widened dest (Byte4 -> Short4/
    // Int4/Long4/...) stores the int result unchanged, so MIN / -1 comes out as
    // the exact 128 / 32768 instead of being wrapped back to MIN by a lane-typed
    // shadow. On the V9 target every lane routes through these shadows
    // (ceilDiv is Java 18+ and forbidden there; floorDiv always takes the
    // baseline shadow), so the int return is what makes V9 agree with V22.
    /**
     * The quotient {@code x / y} rounded towards negative infinity, as {@code java.lang.Math.floorDiv}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static int   floorDiv(int x, int y)     { return java.lang.Math.floorDiv(x, y); }
    /**
     * The quotient {@code x / y} rounded towards negative infinity, as {@code java.lang.Math.floorDiv}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static long  floorDiv(long x, long y)   { return java.lang.Math.floorDiv(x, y); }
    /**
     * The quotient {@code x / y} rounded towards negative infinity, as {@code java.lang.Math.floorDiv}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static int   floorDiv(short x, short y) { return java.lang.Math.floorDiv(x, y); }
    /**
     * The quotient {@code x / y} rounded towards negative infinity, as {@code java.lang.Math.floorDiv}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static int   floorDiv(byte x, byte y)   { return java.lang.Math.floorDiv(x, y); }

    /**
     * The quotient {@code x / y} rounded towards positive infinity.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static int   ceilDiv(int x, int y)      { int q = x / y;  return ((x ^ y) >= 0 && q * y != x) ? q + 1 : q; }
    /**
     * The quotient {@code x / y} rounded towards positive infinity.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static long  ceilDiv(long x, long y)    { long q = x / y; return ((x ^ y) >= 0 && q * y != x) ? q + 1 : q; }
    /**
     * The quotient {@code x / y} rounded towards positive infinity.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static int   ceilDiv(short x, short y)  { int q = x / y; return ((x ^ y) >= 0 && q * y != x) ? q + 1 : q; }
    /**
     * The quotient {@code x / y} rounded towards positive infinity.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static int   ceilDiv(byte x, byte y)    { int q = x / y; return ((x ^ y) >= 0 && q * y != x) ? q + 1 : q; }

    // The modulus results always fit the lane (|r| < |y|, and MIN mod -1 is 0),
    // so these keep returning the lane type.
    /**
     * The remainder of {@link #floorDiv}: {@code x - floorDiv(x, y) * y}, with the sign of {@code y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static int   floorMod(int x, int y)     { return java.lang.Math.floorMod(x, y); }
    /**
     * The remainder of {@link #floorDiv}: {@code x - floorDiv(x, y) * y}, with the sign of {@code y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static long  floorMod(long x, long y)   { return java.lang.Math.floorMod(x, y); }
    /**
     * The remainder of {@link #floorDiv}: {@code x - floorDiv(x, y) * y}, with the sign of {@code y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static short floorMod(short x, short y) { return (short) java.lang.Math.floorMod(x, y); }
    /**
     * The remainder of {@link #floorDiv}: {@code x - floorDiv(x, y) * y}, with the sign of {@code y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static byte  floorMod(byte x, byte y)   { return (byte) java.lang.Math.floorMod(x, y); }

    /**
     * The remainder of {@link #ceilDiv}: {@code x - ceilDiv(x, y) * y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static int   ceilMod(int x, int y)      { int q = x / y; return x - (((x ^ y) >= 0 && q * y != x) ? q + 1 : q) * y; }
    /**
     * The remainder of {@link #ceilDiv}: {@code x - ceilDiv(x, y) * y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static long  ceilMod(long x, long y)    { long q = x / y; return x - (((x ^ y) >= 0 && q * y != x) ? q + 1 : q) * y; }
    /**
     * The remainder of {@link #ceilDiv}: {@code x - ceilDiv(x, y) * y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static short ceilMod(short x, short y)  { int q = x / y; return (short) (x - (((x ^ y) >= 0 && q * y != x) ? q + 1 : q) * y); }
    /**
     * The remainder of {@link #ceilDiv}: {@code x - ceilDiv(x, y) * y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static byte  ceilMod(byte x, byte y)    { int q = x / y; return (byte) (x - (((x ^ y) >= 0 && q * y != x) ? q + 1 : q) * y); }

    /**
     * The number of one bits of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int   bitCount(int x)    { return Integer.bitCount(x); }
    /**
     * The number of one bits of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long  bitCount(long x)   { return Long.bitCount(x); }
    /**
     * The number of one bits of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short bitCount(short x)  { return (short) Integer.bitCount(x & 0xFFFF); }
    /**
     * The number of one bits of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte  bitCount(byte x)   { return (byte) Integer.bitCount(x & 0xFF); }

    /**
     * The number of zero bits above the highest one bit of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int   numberOfLeadingZeros(int x)    { return Integer.numberOfLeadingZeros(x); }
    /**
     * The number of zero bits above the highest one bit of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long  numberOfLeadingZeros(long x)   { return Long.numberOfLeadingZeros(x); }
    /**
     * The number of zero bits above the highest one bit of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short numberOfLeadingZeros(short x)  { return (short) (Integer.numberOfLeadingZeros(x & 0xFFFF) - 16); }
    /**
     * The number of zero bits above the highest one bit of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte  numberOfLeadingZeros(byte x)   { return (byte) (Integer.numberOfLeadingZeros(x & 0xFF) - 24); }

    /**
     * The number of zero bits below the lowest one bit of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int   numberOfTrailingZeros(int x)   { return Integer.numberOfTrailingZeros(x); }
    /**
     * The number of zero bits below the lowest one bit of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long  numberOfTrailingZeros(long x)  { return Long.numberOfTrailingZeros(x); }
    /**
     * The number of zero bits below the lowest one bit of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short numberOfTrailingZeros(short x) { return (short) Integer.numberOfTrailingZeros(x | 0x10000); }
    /**
     * The number of zero bits below the lowest one bit of {@code x}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte  numberOfTrailingZeros(byte x)  { return (byte) Integer.numberOfTrailingZeros(x | 0x100); }

    /**
     * {@code x} with its bits in reverse order.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int   reverseBits(int x)   { return Integer.reverse(x); }
    /**
     * {@code x} with its bits in reverse order.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long  reverseBits(long x)  { return Long.reverse(x); }
    /**
     * {@code x} with its bits in reverse order.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short reverseBits(short x) { return (short) (Integer.reverse(x) >>> 16); }
    /**
     * {@code x} with its bits in reverse order.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte  reverseBits(byte x)  { return (byte) (Integer.reverse(x) >>> 24); }

    /**
     * {@code x} with its bytes in reverse order.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int   reverseBytes(int x)   { return Integer.reverseBytes(x); }
    /**
     * {@code x} with its bytes in reverse order.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long  reverseBytes(long x)  { return Long.reverseBytes(x); }
    /**
     * {@code x} with its bytes in reverse order.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short reverseBytes(short x) { return Short.reverseBytes(x); }
    /**
     * {@code x} with its bytes in reverse order.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte  reverseBytes(byte x)  { return x; }  // identity for 8-bit lanes

    /**
     * {@code x} rotated left by {@code distance} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int   rotateLeft(int x, int distance)     { return Integer.rotateLeft(x, distance); }
    /**
     * {@code x} rotated left by {@code distance} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long  rotateLeft(long x, long distance)   { return Long.rotateLeft(x, (int) distance); }
    /**
     * {@code x} rotated left by {@code distance} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short rotateLeft(short x, short distance) { int v = x & 0xFFFF, d = distance & 15; return (short) (v << d | v >>> (16 - d)); }
    /**
     * {@code x} rotated left by {@code distance} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte  rotateLeft(byte x, byte distance)   { int v = x & 0xFF, d = distance & 7; return (byte) (v << d | v >>> (8 - d)); }

    /**
     * {@code x} rotated right by {@code distance} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int   rotateRight(int x, int distance)     { return Integer.rotateRight(x, distance); }
    /**
     * {@code x} rotated right by {@code distance} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long  rotateRight(long x, long distance)   { return Long.rotateRight(x, (int) distance); }
    /**
     * {@code x} rotated right by {@code distance} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short rotateRight(short x, short distance) { int v = x & 0xFFFF, d = distance & 15; return (short) (v >>> d | v << (16 - d)); }
    /**
     * {@code x} rotated right by {@code distance} bits.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte  rotateRight(byte x, byte distance)   { int v = x & 0xFF, d = distance & 7; return (byte) (v >>> d | v << (8 - d)); }

    /**
     * The integral value closest to {@code v}, ties to even.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double rint(double v) { return java.lang.Math.rint(v); }
    /**
     * The integral value closest to {@code v}, ties to even.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float rint(float v) { return (float) java.lang.Math.rint(v); }
    /**
     * {@code v} rounded to the closest integer, ties towards positive infinity, as {@code
     * java.lang.Math.round}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long round(double v) { return java.lang.Math.round(v); }
    /**
     * {@code v} rounded to the closest integer, ties towards positive infinity, as {@code
     * java.lang.Math.round}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static int round(float v) { return java.lang.Math.round(v); }

    /**
     * {@code v} rounded to the closest integer, ties to even.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static int roundHalfEven(float v) { return (int) java.lang.Math.rint(v); }
    /**
     * {@code v} rounded to the closest integer, ties to even.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static int roundHalfEven(double v) { return (int) java.lang.Math.rint(v); }
    /**
     * {@code v} rounded to the closest integer, ties away from zero.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static int roundHalfUp(float v) {
        return v > 0 ? (int) java.lang.Math.floor(v + 0.5d) : (int) java.lang.Math.ceil(v - 0.5d);
    }
    /**
     * {@code v} rounded to the closest integer, ties away from zero.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static int roundHalfUp(double v) {
        // Ties away from zero without the double rounding of floor(v + 0.5): that turned
        // 0.49999999999999994 into 1 (v + 0.5 rounds up to 1.0 first) and 2^52 + 1 into 2^52 + 2.
        // rint is exact, v - rint(v) is exact (Sterbenz), so a tie is recognised exactly and
        // v +- 0.5 is then an exactly representable integer. (Written out here and in the other
        // half-up and half-down roundings: a call level less.)
        double r = java.lang.Math.rint(v), d = v - r;
        return (int) (d == 0.5 || d == -0.5 ? v + java.lang.Math.copySign(0.5, v) : r);
    }
    /**
     * {@code v} rounded to the closest integer, ties towards zero.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static int roundHalfDown(float v) {
        return v > 0 ? (int) java.lang.Math.ceil(v - 0.5d) : (int) java.lang.Math.floor(v + 0.5d);
    }
    /**
     * {@code v} rounded to the closest integer, ties towards zero.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static int roundHalfDown(double v) {
        double r = java.lang.Math.rint(v), d = v - r;
        return (int) (d == 0.5 || d == -0.5 ? v - java.lang.Math.copySign(0.5, v) : r);
    }

    /**
     * {@code v} rounded to the closest long, ties to even.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long roundLongHalfEven(double v) { return (long) java.lang.Math.rint(v); }
    /**
     * {@code v} rounded to the closest long, ties away from zero.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long roundLongHalfUp(double v) {
        double r = java.lang.Math.rint(v), d = v - r;
        return (long) (d == 0.5 || d == -0.5 ? v + java.lang.Math.copySign(0.5, v) : r);
    }
    /**
     * {@code v} rounded to the closest long, ties towards zero.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long roundLongHalfDown(double v) {
        double r = java.lang.Math.rint(v), d = v - r;
        return (long) (d == 0.5 || d == -0.5 ? v - java.lang.Math.copySign(0.5, v) : r);
    }

    /**
     * {@code v} rounded to an integer by {@code mode}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static int roundUsing(float v, RoundingMode mode) {
        switch (mode) {
            case TRUNCATE: return (int) v;
            case FLOOR:    return (int) java.lang.Math.floor(v);
            case CEILING:  return (int) java.lang.Math.ceil(v);
            case HALF_TOWARD_POSITIVE_INFINITY: return java.lang.Math.round(v);
            case HALF_AWAY_FROM_ZERO: return v > 0 ? (int) java.lang.Math.floor(v + 0.5d) : (int) java.lang.Math.ceil(v - 0.5d);
            case HALF_EVEN: return (int) java.lang.Math.rint(v);
            default: throw new UnsupportedOperationException();
        }
    }
    /**
     * {@code v} rounded to an integer by {@code mode}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static int roundUsing(double v, RoundingMode mode) {
        switch (mode) {
            case TRUNCATE: return (int) v;
            case FLOOR:    return (int) java.lang.Math.floor(v);
            case CEILING:  return (int) java.lang.Math.ceil(v);
            case HALF_TOWARD_POSITIVE_INFINITY: {
                // Saturate like the (int) casts of the other modes instead of
                // wrapping the long (3e9 came out as -1294967296); NaN stays 0.
                long r = java.lang.Math.round(v);
                return r >= Integer.MAX_VALUE ? Integer.MAX_VALUE : r <= Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) r;
            }
            case HALF_AWAY_FROM_ZERO: {
                double r = java.lang.Math.rint(v), d = v - r;
                return (int) (d == 0.5 || d == -0.5 ? v + java.lang.Math.copySign(0.5, v) : r);
            }
            case HALF_EVEN: return (int) java.lang.Math.rint(v);
            default: throw new UnsupportedOperationException();
        }
    }
    /**
     * {@code v} rounded to a long by {@code mode}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long roundLongUsing(double v, RoundingMode mode) {
        switch (mode) {
            case TRUNCATE: return (long) v;
            case FLOOR:    return (long) java.lang.Math.floor(v);
            case CEILING:  return (long) java.lang.Math.ceil(v);
            case HALF_TOWARD_POSITIVE_INFINITY: return java.lang.Math.round(v);
            case HALF_AWAY_FROM_ZERO: {
                double r = java.lang.Math.rint(v), d = v - r;
                return (long) (d == 0.5 || d == -0.5 ? v + java.lang.Math.copySign(0.5, v) : r);
            }
            case HALF_EVEN: return (long) java.lang.Math.rint(v);
            default: throw new UnsupportedOperationException();
        }
    }

    /**
     * The sign of {@code v}: {@code -1}, {@code 0} or {@code 1}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double signum(double v) { return java.lang.Math.signum(v); }
    /**
     * The sign of {@code v}: {@code -1}, {@code 0} or {@code 1}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float signum(float v) { return java.lang.Math.signum(v); }
    /**
     * The sign of {@code v}: {@code -1}, {@code 0} or {@code 1}.
     * <p>
     * Valid input: any value.
     */
    public static int signum(int v) { return (v >> 31) | (-v >>> 31); }
    /**
     * The sign of {@code v}: {@code -1}, {@code 0} or {@code 1}.
     * <p>
     * Valid input: any value.
     */
    public static int signum(long v) { return (int) ((v >> 63) | (-v >>> 63)); }

    /**
     * Whether the argument is neither NaN nor infinite.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static boolean isFinite(double d) { return java.lang.Math.abs(d) <= Double.MAX_VALUE; }
    /**
     * Whether the argument is neither NaN nor infinite.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static boolean isFinite(float f) { return java.lang.Math.abs(f) <= Float.MAX_VALUE; }

    /**
     * {@code angles} converted from degrees to radians.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float toRadians(float angles) { return (float) java.lang.Math.toRadians(angles); }
    /**
     * {@code angles} converted from degrees to radians.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double toRadians(double angles) { return java.lang.Math.toRadians(angles); }
    /**
     * {@code angles} converted from radians to degrees.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float toDegrees(float angles) { return (float) java.lang.Math.toDegrees(angles); }
    /**
     * {@code angles} converted from radians to degrees.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double toDegrees(double angles) { return java.lang.Math.toDegrees(angles); }

    /**
     * A pseudo-random value in {@code [0, 1)}, as {@code java.lang.Math.random}.
     * <p>
     * Valid input: the method reads no input.
     */
    public static double random() { return java.lang.Math.random(); }

    /**
     * Linearly interpolate from {@code a} ({@code t = 0}) to {@code b} ({@code t = 1}) as
     * {@code a + (b - a) * t}, as JOML and glMatrix do: monotone in {@code t} and exact at
     * {@code t = 0}, but at {@code t = 1} exact only up to the rounding of {@code b - a}, which
     * shows when {@code |a|} is much larger than {@code |b|} ({@code lerp(1e8f, 1f, 1f)} is
     * {@code 0}). The form {@code a * (1 - t) + b * t} is exact at both ends but not monotone.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param a the value at {@code t = 0}
     * @param b the value at {@code t = 1}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the interpolated value
     */
    public static float lerp(float a, float b, float t) { return a + (b - a) * t; }

    /**
     * Linearly interpolate from {@code a} to {@code b}; see {@link #lerp(float, float, float)}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param a the value at {@code t = 0}
     * @param b the value at {@code t = 1}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the interpolated value
     */
    public static double lerp(double a, double b, double t) { return a + (b - a) * t; }

    /**
     * Bilinear interpolation of the four corner values {@code q00..q11} at {@code (tx, ty)}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float biLerp(float q00, float q10, float q01, float q11, float tx, float ty) {
        float x1 = q00 + (q10 - q00) * tx;
        float x2 = q01 + (q11 - q01) * tx;
        return x1 + (x2 - x1) * ty;
    }
    /**
     * Bilinear interpolation of the four corner values {@code q00..q11} at {@code (tx, ty)}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double biLerp(double q00, double q10, double q01, double q11, double tx, double ty) {
        double x1 = q00 + (q10 - q00) * tx;
        double x2 = q01 + (q11 - q01) * tx;
        return x1 + (x2 - x1) * ty;
    }

    /**
     * Trilinear interpolation of the eight corner values {@code q000..q111} at {@code (tx, ty,
     * tz)}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float triLerp(float q000, float q100, float q010, float q110,
                                float q001, float q101, float q011, float q111,
                                float tx, float ty, float tz) {
        float x00 = q000 + (q100 - q000) * tx;
        float x10 = q010 + (q110 - q010) * tx;
        float x01 = q001 + (q101 - q001) * tx;
        float x11 = q011 + (q111 - q011) * tx;
        float y0 = x00 + (x10 - x00) * ty;
        float y1 = x01 + (x11 - x01) * ty;
        return y0 + (y1 - y0) * tz;
    }
    /**
     * Trilinear interpolation of the eight corner values {@code q000..q111} at {@code (tx, ty,
     * tz)}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double triLerp(double q000, double q100, double q010, double q110,
                                 double q001, double q101, double q011, double q111,
                                 double tx, double ty, double tz) {
        double x00 = q000 + (q100 - q000) * tx;
        double x10 = q010 + (q110 - q010) * tx;
        double x01 = q001 + (q101 - q001) * tx;
        double x11 = q011 + (q111 - q011) * tx;
        double y0 = x00 + (x10 - x00) * ty;
        double y1 = x01 + (x11 - x01) * ty;
        return y0 + (y1 - y0) * tz;
    }

    /**
     * The interpolation factor {@code t} at which {@code lerp(a, b, t)} is {@code v}: {@code (v -
     * a) / (b - a)}.
     * <p>
     * Valid input: {@code a} and {@code b} must differ.
     */
    public static float inverseLerp(float a, float b, float v) { return (v - a) / (b - a); }
    /**
     * The interpolation factor {@code t} at which {@code lerp(a, b, t)} is {@code v}: {@code (v -
     * a) / (b - a)}.
     * <p>
     * Valid input: {@code a} and {@code b} must differ.
     */
    public static double inverseLerp(double a, double b, double v) { return (v - a) / (b - a); }

    /**
     * Map {@code v} linearly from the range {@code [inMin, inMax]} to {@code [outMin, outMax]}.
     * <p>
     * Valid input: {@code inMin} and {@code inMax} must differ.
     */
    public static float mapLinear(float v, float inMin, float inMax, float outMin, float outMax) {
        return outMin + (v - inMin) * (outMax - outMin) / (inMax - inMin);
    }
    /**
     * Map {@code v} linearly from the range {@code [inMin, inMax]} to {@code [outMin, outMax]}.
     * <p>
     * Valid input: {@code inMin} and {@code inMax} must differ.
     */
    public static double mapLinear(double v, double inMin, double inMax, double outMin, double outMax) {
        return outMin + (v - inMin) * (outMax - outMin) / (inMax - inMin);
    }

    /**
     * The Hermite interpolation {@code t * t * (3 - 2 * t)} of {@code t = clamp((x - edge0) /
     * (edge1 - edge0), 0, 1)}, as GLSL's {@code smoothstep}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     */
    public static float smoothstep(float edge0, float edge1, float x) {
        float u = (x - edge0) / (edge1 - edge0), t = u < 0f ? 0f : u > 1f ? 1f : u;
        return t * t * (3f - 2f * t);
    }
    /**
     * The Hermite interpolation {@code t * t * (3 - 2 * t)} of {@code t = clamp((x - edge0) /
     * (edge1 - edge0), 0, 1)}, as GLSL's {@code smoothstep}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     */
    public static double smoothstep(double edge0, double edge1, double x) {
        double u = (x - edge0) / (edge1 - edge0), t = u < 0.0 ? 0.0 : u > 1.0 ? 1.0 : u;
        return t * t * (3.0 - 2.0 * t);
    }

    /**
     * Ken Perlin's smoother step {@code t * t * t * (t * (6 * t - 15) + 10)} of {@code t = clamp((x
     * - edge0) / (edge1 - edge0), 0, 1)}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     */
    public static float smootherstep(float edge0, float edge1, float x) {
        float u = (x - edge0) / (edge1 - edge0), t = u < 0f ? 0f : u > 1f ? 1f : u;
        return t * t * t * (t * (t * 6f - 15f) + 10f);
    }
    /**
     * Ken Perlin's smoother step {@code t * t * t * (t * (6 * t - 15) + 10)} of {@code t = clamp((x
     * - edge0) / (edge1 - edge0), 0, 1)}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     */
    public static double smootherstep(double edge0, double edge1, double x) {
        double u = (x - edge0) / (edge1 - edge0), t = u < 0.0 ? 0.0 : u > 1.0 ? 1.0 : u;
        return t * t * t * (t * (t * 6.0 - 15.0) + 10.0);
    }

    /**
     * {@code deg} converted from degrees to radians.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float degToRad(float deg) { return deg * (PI_f / 180f); }
    /**
     * {@code deg} converted from degrees to radians.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double degToRad(double deg) { return deg * (PI / 180.0); }
    /**
     * {@code rad} converted from radians to degrees.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float radToDeg(float rad) { return rad * (180f / PI_f); }
    /**
     * {@code rad} converted from radians to degrees.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double radToDeg(double rad) { return rad * (180.0 / PI); }

    /**
     * {@code t} wrapped into {@code [0, length)} (for a positive length). {@code t - floor(t /
     * length) * length} can round to {@code length} itself - for a tiny negative {@code t} - or
     * just below 0 when {@code t / length} rounds up to an integer; both are folded back into the
     * range, a result that rounded to {@code length} as 0 (the same point, modulo {@code length}).
     * NaN propagates.
     * <p>
     * Valid input: {@code length} must be positive.
     */
    public static float repeat(float t, float length) {
        float r = t - (float) java.lang.Math.floor(t / length) * length;
        if (r < 0f) r += length;
        return r >= length ? 0f : r;
    }
    /**
     * {@code t} wrapped into {@code [0, length)}; see {@link #repeat(float, float)}.
     * <p>
     * Valid input: {@code length} must be positive.
     */
    public static double repeat(double t, double length) {
        double r = t - java.lang.Math.floor(t / length) * length;
        if (r < 0.0) r += length;
        return r >= length ? 0.0 : r;
    }
    /**
     * {@code t} bounced back and forth between 0 and {@code length}: it rises to {@code length} and
     * falls back to 0 over each period of {@code 2 * length}.
     * <p>
     * Valid input: {@code length} must be positive.
     */
    public static float pingpong(float t, float length) {
        float p = 2f * length, r = t - (float) java.lang.Math.floor(t / p) * p;
        if (r < 0f) r += p;
        if (r >= p) r = 0f;
        return length - java.lang.Math.abs(r - length);
    }
    /**
     * {@code t} bounced back and forth between 0 and {@code length}; see {@link #pingpong(float,
     * float)}.
     * <p>
     * Valid input: {@code length} must be positive.
     */
    public static double pingpong(double t, double length) {
        double p = 2.0 * length, r = t - java.lang.Math.floor(t / p) * p;
        if (r < 0.0) r += p;
        if (r >= p) r = 0.0;
        return length - java.lang.Math.abs(r - length);
    }
}
