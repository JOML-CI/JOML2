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
    private static final double ONE_OVER_PI_TIMES_2 = 1.0 / PI_TIMES_2;
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
    private static volatile Boolean cosFromSinOverride;
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
     * Enable or disable the fast-math approximations for sin/cos/atan2 (and
     * cosFromSin). Overrides the {@code joml.fastmath} system property
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
     * Enable or disable deriving a cosine from an already computed sine in
     * {@link #cosFromSin(float, float)}, which every generated operation that needs
     * the sine and the cosine of one angle calls - the rotations of matrices,
     * quaternions, poses and vectors among them (the full list is on that method).
     * Disabled, the method evaluates {@link #cos(float)} instead: bit-for-bit the
     * old results, at the cost of a second transcendental per angle. Overrides the
     * {@code joml.cosFromSin} system property (default {@code true}). Must be
     * called before any flag-gated method is used; calling it later throws
     * {@link IllegalStateException}.
     * <p>
     * Valid input: any value.
     */
    public static void setCosFromSin(boolean value) {
        synchronized (Math.class) {
            requireNotInitialized();
            cosFromSinOverride = Boolean.valueOf(value);
        }
    }

    /**
     * Whether {@code cosFromSin} derives the cosine from the sine (see
     * {@link #setCosFromSin}). Querying it freezes the flags.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resolved {@code cosFromSin} flag
     */
    public static boolean cosFromSinEnabled() { return Cfg.COS_FROM_SIN; }

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
     * pow/cbrt/hypot) delegate to {@link StrictMath} instead of {@link java.lang.Math},
     * producing bit-identical results on every JVM and platform. {@code java.lang.Math}
     * may use platform intrinsics that are only required to be within 1-2 ulp of the
     * correctly rounded result, so its values can differ across platforms.
     * <p>
     * Only the precise path is affected: when {@link #setFastmath fastmath} is also
     * enabled, the fast-math approximations still win for sin/cos/atan2 (they are pure
     * arithmetic and thus already reproducible; the sin lookup table is built with
     * {@code StrictMath.sin} under strict mode, so it is reproducible too).
     * <p>
     * Full cross-platform determinism additionally requires identical
     * {@code joml.useFma}, {@code joml.cosFromSin}, {@code joml.fastmath} and (with fastmath)
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
             + "cosFromSin, or fma - including through the *Ops classes, whose kernels call "
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
        static final boolean COS_FROM_SIN;
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
         * Largest |argument| the fast-math sin/cos handle themselves; beyond it
         * they hand off to the precise path (which also yields NaN for NaN and
         * +-Infinity, like java.lang.Math.sin). The Chebyshev path's range
         * reduction v - rint(v/pi)*pi loses a digit per decade of |v| (sin(1e15)
         * was off by 0.02 and sin(3e19) came out as 1e42), and the lookup path
         * needs its table index rad * lookupSizeOverPi2 to stay an exact int
         * (sin(1e6) was 176450 and sin(+Inf) was +Inf).
         */
        static final double FAST_SIN_MAX_ARG;

        static {
            boolean fastmath, sinLookup, useFma, strictMath, cosFromSin;
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
                Boolean cOv = cosFromSinOverride;
                cosFromSin = cOv != null ? cOv.booleanValue() : flagProperty("joml.cosFromSin", true);
                Boolean sOv = strictMathOverride;
                strictMath = sOv != null ? sOv.booleanValue() : flagProperty("joml.strictMath", false);
                Integer bOv = sinLookupBitsOverride;
                sinLookupBits = bOv != null ? bOv.intValue() : sinLookupBitsProperty();
                mathInitialized = true;
            }
            FASTMATH = fastmath;
            SIN_LOOKUP = sinLookup;
            USE_FMA = useFma;
            COS_FROM_SIN = cosFromSin;
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

    /* Chebyshev polynomial coefficients (Roquen, JGO post 361815). */
    private static final double k1 = Double.longBitsToDouble(-4628199217061079959L);
    private static final double k2 = Double.longBitsToDouble(4575957461383549981L);
    private static final double k3 = Double.longBitsToDouble(-4671919876307284301L);
    private static final double k4 = Double.longBitsToDouble(4523617213632129738L);
    private static final double k5 = Double.longBitsToDouble(-4730215344060517252L);
    private static final double k6 = Double.longBitsToDouble(4460268259291226124L);
    private static final double k7 = Double.longBitsToDouble(-4798040743777455072L);

    private Math() {}

    private static double sinRoquenNewk(double v) {
        double i = java.lang.Math.rint(v * ONE_OVER_PI);
        double x = v - i * PI;
        double qs = 1 - 2 * ((int) i & 1);
        double x2 = x * x;
        double r;
        x = qs * x;
        r =        k7;
        r = r * x2 + k6;
        r = r * x2 + k5;
        r = r * x2 + k4;
        r = r * x2 + k3;
        r = r * x2 + k2;
        r = r * x2 + k1;
        return x + x * x2 * r;
    }

    /**
     * The table sine, linearly interpolated, of {@code rad} advanced by {@code shift} entries
     * (0 for the sine, a quarter period for the cosine: an exact shift, where adding pi/2 to a
     * float argument rounded it to half an ulp). The index stays within 2^30 (FAST_SIN_MAX_ARG),
     * so its floor is an exact int.
     */
    private static float sinTheagentdLookup(double rad, int shift) {
        double index = rad * Cfg.lookupSizeOverPi2;
        double floor = java.lang.Math.floor(index);
        float alpha = (float) (index - floor);
        int i = ((int) floor + shift) & Cfg.lookupTableSizeMinus1;
        float sin1 = Cfg.sinTable[i];
        float sin2 = Cfg.sinTable[i + 1];
        return sin1 + (sin2 - sin1) * alpha;
    }

    /**
     * The sine of the angle {@code rad} (radians); see {@link #setFastmath} for the approximation.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float sin(float rad) {
        // NaN fails the bound check and takes the precise path, as +-Inf do. A zero is its own
        // sine: both reductions turned -0 into +0.
        if (Cfg.FASTMATH && java.lang.Math.abs(rad) <= Cfg.FAST_SIN_MAX_ARG) {
            if (rad == 0f) return rad;
            if (Cfg.SIN_LOOKUP) return sinTheagentdLookup(rad, 0);
            return (float) sinRoquenNewk(rad);
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
        if (Cfg.FASTMATH && java.lang.Math.abs(rad) <= Cfg.FAST_SIN_MAX_ARG) {
            if (rad == 0.0) return rad;
            if (Cfg.SIN_LOOKUP) return sinTheagentdLookup(rad, 0);
            return sinRoquenNewk(rad);
        }
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.sin(rad);
        return java.lang.Math.sin(rad);
    }

    // The fast cosine is the fast sine a quarter period on, shifted exactly: in double for the
    // polynomial, by a quarter of the table for the lookup. (rad + PI_OVER_2_f rounded to float
    // was off by half an ulp of rad: 3.4e-3 at 65535, 0.46 at 1.6e7.)
    /**
     * The cosine of the angle {@code rad} (radians); see {@link #setFastmath} for the
     * approximation.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float cos(float rad) {
        if (Cfg.FASTMATH && java.lang.Math.abs(rad) <= Cfg.FAST_SIN_MAX_ARG) {
            if (Cfg.SIN_LOOKUP) return sinTheagentdLookup(rad, Cfg.lookupQuarter);
            return (float) sinRoquenNewk(rad + PI_OVER_2);
        }
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.cos(rad);
        return (float) java.lang.Math.cos(rad);
    }
    /**
     * The cosine of the angle {@code rad} (radians); see {@link #setFastmath} for the
     * approximation.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double cos(double rad) {
        if (Cfg.FASTMATH && java.lang.Math.abs(rad) <= Cfg.FAST_SIN_MAX_ARG) {
            if (Cfg.SIN_LOOKUP) return sinTheagentdLookup(rad, Cfg.lookupQuarter);
            return sinRoquenNewk(rad + PI_OVER_2);
        }
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.cos(rad);
        return java.lang.Math.cos(rad);
    }

    /**
     * The cosine of {@code angle} derived from its already computed sine as
     * {@code sqrt(1 - sin * sin)} with the sign of the quadrant: one square root
     * instead of a second transcendental evaluation. Every generated operation
     * that needs both the sine and the cosine of one angle uses it, in float and in
     * double: the rotation builders ({@code rotate*}, {@code preRotate*},
     * {@code makeRotation*}, {@code rotateAround}, {@code makeFromAxisAngle}, on
     * matrices, quaternions, dual quaternions and poses), the vector rotations
     * ({@code rotate}, {@code rotateX/Y/Z}, {@code rotateAxis} and their
     * {@code *Around} forms), the quaternion and dual quaternion {@code exp},
     * {@code pow}, {@code sclerp} and {@code integrate}, the random
     * {@code makeUniformDirection} and {@code makeUniformRotation}, {@code arcball},
     * and the oblique and axonometric projections.
     * <p>
     * Accuracy: exact where the cosine is large, but near {@code |cos| == 0}
     * (angles near an odd multiple of 90 degrees) {@code 1 - sin * sin} cancels.
     * Within about 2.4e-4 radians of such an angle the float sine is already
     * exactly 1, so the cosine comes back as 0: the absolute error is bounded by
     * about 2.4e-4 (an angle error of 0.014 degrees) and the relative error
     * there is total; in double the same bound is about 1.5e-8. A quaternion
     * derives the cosine of the half angle, so its error sits near 180-degree
     * rotations and is twice as large in angle: about 4.9e-4 radians (0.028
     * degrees) in float, 3e-8 in double. The sign is
     * taken from the angle itself; angles beyond {@code 2^24} (float) or
     * {@code 2^26} (double), where that reduction could lose the quadrant, return
     * the real cosine instead. Use {@link #cos(float)} directly where the precision matters,
     * or turn the derivation off for the whole library with
     * {@code -Djoml.cosFromSin=false} or {@link #setCosFromSin}: every rotation
     * then evaluates the cosine as before. With fastmath enabled the cosine is
     * {@link #cos(float)} instead - the fast cosine within the fast sine's argument
     * bound, the precise one beyond it: the fast sine is an approximation, and
     * squaring an approximate sine would amplify its error near {@code |sin| == 1},
     * while a second polynomial or table evaluation keeps the same uniform error.
     * <p>
     * Valid input: {@code sin} must lie in {@code [-1, 1]}.
     *
     * @param sin the sine of {@code angle}
     * @param angle the angle in radians
     * @return the cosine of {@code angle}
     */
    public static float cosFromSin(float sin, float angle) {
        // Under fastmath the fast cos: it keeps the argument bound of the fast sine (the shifted
        // sine without it lost the quadrant beyond 2^24, a 0.93 cosine for -0.36 at 1e8).
        // Beyond 2^24 the double reduction below no longer keeps the quadrant for every angle
        // (wrong signs from about 2.2e12): such angles take the real cosine. NaN fails the
        // comparison and takes the derivation, which propagates it.
        if (!Cfg.COS_FROM_SIN || Cfg.FASTMATH || java.lang.Math.abs(angle) > 0x1p24f) return cos(angle);
        // fma keeps 1 - sin^2 to one rounding, which is what limits the cosine near |sin| = 1.
        float cos = sqrt(fma(-sin, sin, 1.0f));
        // The sign is the quadrant of the angle, reduced in double with rint. The former float
        // `(int) (a / 2pi)` reduction mis-signed cosines near the quadrant boundaries already for
        // |angle| ~ 100 (max absolute error 2.4e-4, worse with the angle) and overflowed the int
        // beyond ~1.3e10. In double the boundary is off by k * ulp(2pi), so a wrong sign can only
        // strike where |cos| is itself below that - graceful for every finite float angle.
        double r = angle - java.lang.Math.rint(angle * ONE_OVER_PI_TIMES_2) * PI_TIMES_2;
        return java.lang.Math.abs(r) > PI_OVER_2 ? -cos : cos;
    }
    /**
     * Double-precision {@link #cosFromSin(float, float)}: same derivation, with
     * an absolute error bounded by about 1.5e-8 near {@code |cos| == 0}.
     * <p>
     * Valid input: {@code sin} must lie in {@code [-1, 1]}.
     *
     * @param sin the sine of {@code angle}
     * @param angle the angle in radians
     * @return the cosine of {@code angle}
     */
    public static double cosFromSin(double sin, double angle) {
        // The reduction holds the quadrant up to about 1e9 in double: beyond 2^26, the real cosine.
        if (!Cfg.COS_FROM_SIN || Cfg.FASTMATH || java.lang.Math.abs(angle) > 0x1p26) return cos(angle);
        double cos = sqrt(fma(-sin, sin, 1.0));
        // Same reduction as the float overload; rint instead of the truncating int cast, which
        // overflowed beyond |angle| ~ 1.3e10.
        double r = angle - java.lang.Math.rint(angle * ONE_OVER_PI_TIMES_2) * PI_TIMES_2;
        return java.lang.Math.abs(r) > PI_OVER_2 ? -cos : cos;
    }

    /**
     * HotSpot generates sine and cosine stubs on AArch64 but no tangent one, so C2 calls the C
     * fdlibm port (SharedRuntime::dtan) for {@code java.lang.Math.tan} there: 5.8 ns against 3.4 ns
     * for the pair. Their quotient in double rounds to the same float as the double tangent (bit
     * for bit over 524 million arguments in [-100, 100] and around every pole up to 32 pi, with the
     * AArch64 and the x64 stubs alike), so the float overload takes it there. x64 has an Intel
     * LIBM tangent stub like its sine and cosine ones, and one call beats two.
     */
    private static final boolean FLOAT_TAN_VIA_SIN_COS = "aarch64".equals(System.getProperty("os.arch"));

    /**
     * The tangent of the angle {@code r} (radians).
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float tan(float r) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.tan(r);
        if (FLOAT_TAN_VIA_SIN_COS) {
            double d = r;
            return (float) (java.lang.Math.sin(d) / java.lang.Math.cos(d));
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
     * Valid input: {@code r} must lie in {@code [-1, 1]}.
     */
    public static float asin(float r) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.asin(r);
        return (float) java.lang.Math.asin(r);
    }
    /**
     * The arc sine of {@code r}, as {@code java.lang.Math.asin}.
     * <p>
     * Valid input: {@code r} must lie in {@code [-1, 1]}.
     */
    public static double asin(double r) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.asin(r);
        return java.lang.Math.asin(r);
    }

    /**
     * The arc sine of {@code r}, with {@code r} clamped to {@code [-1, 1]} first.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float safeAsin(float r) { return r <= -1.0f ? -PI_OVER_2_f : r >= 1.0f ? PI_OVER_2_f : asin(r); }
    /**
     * The arc sine of {@code r}, with {@code r} clamped to {@code [-1, 1]} first.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double safeAsin(double r) { return r <= -1.0 ? -PI_OVER_2 : r >= 1.0 ? PI_OVER_2 : asin(r); }

    /**
     * The arc cosine of {@code r}, as {@code java.lang.Math.acos}.
     * <p>
     * Valid input: {@code r} must lie in {@code [-1, 1]}.
     */
    public static float acos(float r) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.acos(r);
        return (float) java.lang.Math.acos(r);
    }
    /**
     * The arc cosine of {@code r}, as {@code java.lang.Math.acos}.
     * <p>
     * Valid input: {@code r} must lie in {@code [-1, 1]}.
     */
    public static double acos(double r) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.acos(r);
        return java.lang.Math.acos(r);
    }

    /**
     * The arc cosine of {@code v}, with {@code v} clamped to {@code [-1, 1]} first.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float safeAcos(float v) {
        if (v < -1.0f) return PI_f;
        if (v > 1.0f) return 0.0f;
        return acos(v);
    }
    /**
     * The arc cosine of {@code v}, with {@code v} clamped to {@code [-1, 1]} first.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double safeAcos(double v) {
        if (v < -1.0) return PI;
        if (v > 1.0) return 0.0;
        return acos(v);
    }

    /**
     * The arc tangent of {@code r}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float atan(float r) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.atan(r);
        return (float) java.lang.Math.atan(r);
    }
    /**
     * The arc tangent of {@code r}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double atan(double r) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.atan(r);
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
     * The angle of the point {@code (x, y)}, as {@code java.lang.Math.atan2}; see {@link
     * #setFastmath} for the approximation.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float atan2(float y, float x) {
        if (Cfg.FASTMATH) return fastAtan2(y, x);
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.atan2(y, x);
        // HotSpot intrinsifies atan2 on no platform, and the fdlibm port costs 4.2 ns against 2.6 ns
        // for its core, the atan of the quotient. The quotient of two floats neither overflows nor
        // underflows in double, so atan of it plus the quadrant's pi rounds to the same float as
        // atan2 (bit for bit over 500 million pairs: random bit patterns, the diagonals, both axes);
        // a zero or infinite x and an infinite or NaN y keep the special cases of atan2.
        float ax = java.lang.Math.abs(x);
        if (ax > 0.0f && ax < Float.POSITIVE_INFINITY && java.lang.Math.abs(y) < Float.POSITIVE_INFINITY) {
            double r = java.lang.Math.atan((double) y / x);
            // copySign keeps -0.0 negative: atan2(-0.0, -1) is -pi
            return (float) (x < 0.0f ? r + java.lang.Math.copySign(PI, y) : r);
        }
        return (float) java.lang.Math.atan2(y, x);
    }
    /**
     * The angle of the point {@code (x, y)}, as {@code java.lang.Math.atan2}; see {@link
     * #setFastmath} for the approximation.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double atan2(double y, double x) {
        if (Cfg.FASTMATH) return fastAtan2(y, x);
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.atan2(y, x);
        return java.lang.Math.atan2(y, x);
    }

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
        if (!(a > 0) || a == Double.POSITIVE_INFINITY) return log(a);
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
        return (e - shift) + log(m) * INV_LN_2;
    }
    /**
     * The base-2 logarithm, exact for every power of two; see {@link #log2(double)}.
     * <p>
     * Valid input: {@code a} must be positive.
     */
    public static float log2(float a) {
        return (float) log2((double) a);
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
    /**
     * {@code sqrt(x * x + y * y)} without intermediate overflow or underflow.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static float hypot(float x, float y) {
        if (Cfg.STRICT_MATH) return (float) java.lang.StrictMath.hypot(x, y);
        return (float) java.lang.Math.hypot(x, y);
    }
    /**
     * {@code sqrt(x * x + y * y)} without intermediate overflow or underflow.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double hypot(double x, double y) {
        if (Cfg.STRICT_MATH) return java.lang.StrictMath.hypot(x, y);
        return java.lang.Math.hypot(x, y);
    }
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
    // java.lang.Math semantics - a NaN operand gives NaN and -0.0 is smaller than +0.0 - like
    // the Vector API's lane-wise min/max the SIMD code paths use, so every variant agrees.
    /**
     * The larger of the two values, as {@code java.lang.Math.max} (NaN propagates; {@code -0.0} is
     * below {@code 0.0}).
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float max(float a, float b) { return java.lang.Math.max(a, b); }
    /**
     * The smaller of the two values, as {@code java.lang.Math.min} (NaN propagates; {@code -0.0} is
     * below {@code 0.0}).
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float min(float a, float b) { return java.lang.Math.min(a, b); }
    /**
     * The larger of the two values, as {@code java.lang.Math.max} (NaN propagates; {@code -0.0} is
     * below {@code 0.0}).
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double max(double a, double b) { return java.lang.Math.max(a, b); }
    /**
     * The smaller of the two values, as {@code java.lang.Math.min} (NaN propagates; {@code -0.0} is
     * below {@code 0.0}).
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double min(double a, double b) { return java.lang.Math.min(a, b); }

    /**
     * {@code v} clamped to {@code [lo, hi]}.
     * <p>
     * Valid input: {@code lo} must not exceed {@code hi}.
     */
    public static int clamp(int v, int lo, int hi) { return min(max(v, lo), hi); }
    /**
     * {@code v} clamped to {@code [lo, hi]}.
     * <p>
     * Valid input: {@code lo} must not exceed {@code hi}.
     */
    public static long clamp(long v, long lo, long hi) { return min(max(v, lo), hi); }
    /**
     * {@code v} clamped to {@code [lo, hi]}.
     * <p>
     * Valid input: {@code lo} must not exceed {@code hi}.
     */
    public static float clamp(float v, float lo, float hi) { return min(max(v, lo), hi); }
    /**
     * {@code v} clamped to {@code [lo, hi]}.
     * <p>
     * Valid input: {@code lo} must not exceed {@code hi}.
     */
    public static double clamp(double v, double lo, double hi) { return min(max(v, lo), hi); }

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
    public static int   ceilDiv(short x, short y)  { return ceilDiv((int) x, (int) y); }
    /**
     * The quotient {@code x / y} rounded towards positive infinity.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static int   ceilDiv(byte x, byte y)    { return ceilDiv((int) x, (int) y); }

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
    public static int   ceilMod(int x, int y)      { return x - ceilDiv(x, y) * y; }
    /**
     * The remainder of {@link #ceilDiv}: {@code x - ceilDiv(x, y) * y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static long  ceilMod(long x, long y)    { return x - ceilDiv(x, y) * y; }
    /**
     * The remainder of {@link #ceilDiv}: {@code x - ceilDiv(x, y) * y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static short ceilMod(short x, short y)  { return (short) ceilMod((int) x, (int) y); }
    /**
     * The remainder of {@link #ceilDiv}: {@code x - ceilDiv(x, y) * y}.
     * <p>
     * Valid input: {@code y} must be non-zero.
     */
    public static byte  ceilMod(byte x, byte y)    { return (byte) ceilMod((int) x, (int) y); }

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
    public static int roundHalfUp(double v) { return (int) roundHalfAwayFromZero(v); }
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
    public static int roundHalfDown(double v) { return (int) roundHalfTowardZero(v); }

    /**
     * Round to the nearest integer, ties away from zero, without the double
     * rounding of <code>floor(v + 0.5)</code>: that turned 0.49999999999999994 into
     * 1 (v + 0.5 rounds up to 1.0 first) and 2^52 + 1 into 2^52 + 2. rint is exact,
     * v - rint(v) is exact (Sterbenz), so a tie is recognised exactly and v +- 0.5
     * is then an exactly representable integer.
     */
    private static double roundHalfAwayFromZero(double v) {
        double r = java.lang.Math.rint(v);
        double d = v - r;
        return d == 0.5 || d == -0.5 ? v + java.lang.Math.copySign(0.5, v) : r;
    }

    /** Round to the nearest integer, ties toward zero; see {@link #roundHalfAwayFromZero}. */
    private static double roundHalfTowardZero(double v) {
        double r = java.lang.Math.rint(v);
        double d = v - r;
        return d == 0.5 || d == -0.5 ? v - java.lang.Math.copySign(0.5, v) : r;
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
    public static long roundLongHalfUp(double v) { return (long) roundHalfAwayFromZero(v); }
    /**
     * {@code v} rounded to the closest long, ties towards zero.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long roundLongHalfDown(double v) { return (long) roundHalfTowardZero(v); }

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
            case HALF_AWAY_FROM_ZERO: return roundHalfUp(v);
            case HALF_EVEN: return roundHalfEven(v);
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
            case HALF_AWAY_FROM_ZERO: return roundHalfUp(v);
            case HALF_EVEN: return roundHalfEven(v);
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
            case HALF_AWAY_FROM_ZERO: return roundLongHalfUp(v);
            case HALF_EVEN: return roundLongHalfEven(v);
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
    public static boolean isFinite(double d) { return abs(d) <= Double.MAX_VALUE; }
    /**
     * Whether the argument is neither NaN nor infinite.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static boolean isFinite(float f) { return abs(f) <= Float.MAX_VALUE; }

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
        float x1 = lerp(q00, q10, tx);
        float x2 = lerp(q01, q11, tx);
        return lerp(x1, x2, ty);
    }
    /**
     * Bilinear interpolation of the four corner values {@code q00..q11} at {@code (tx, ty)}.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static double biLerp(double q00, double q10, double q01, double q11, double tx, double ty) {
        double x1 = lerp(q00, q10, tx);
        double x2 = lerp(q01, q11, tx);
        return lerp(x1, x2, ty);
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
        float x00 = lerp(q000, q100, tx);
        float x10 = lerp(q010, q110, tx);
        float x01 = lerp(q001, q101, tx);
        float x11 = lerp(q011, q111, tx);
        float y0 = lerp(x00, x10, ty);
        float y1 = lerp(x01, x11, ty);
        return lerp(y0, y1, tz);
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
        double x00 = lerp(q000, q100, tx);
        double x10 = lerp(q010, q110, tx);
        double x01 = lerp(q001, q101, tx);
        double x11 = lerp(q011, q111, tx);
        double y0 = lerp(x00, x10, ty);
        double y1 = lerp(x01, x11, ty);
        return lerp(y0, y1, tz);
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
        float t = clamp((x - edge0) / (edge1 - edge0), 0f, 1f);
        return t * t * (3f - 2f * t);
    }
    /**
     * The Hermite interpolation {@code t * t * (3 - 2 * t)} of {@code t = clamp((x - edge0) /
     * (edge1 - edge0), 0, 1)}, as GLSL's {@code smoothstep}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     */
    public static double smoothstep(double edge0, double edge1, double x) {
        double t = clamp((x - edge0) / (edge1 - edge0), 0.0, 1.0);
        return t * t * (3.0 - 2.0 * t);
    }

    /**
     * Ken Perlin's smoother step {@code t * t * t * (t * (6 * t - 15) + 10)} of {@code t = clamp((x
     * - edge0) / (edge1 - edge0), 0, 1)}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     */
    public static float smootherstep(float edge0, float edge1, float x) {
        float t = clamp((x - edge0) / (edge1 - edge0), 0f, 1f);
        return t * t * t * (t * (t * 6f - 15f) + 10f);
    }
    /**
     * Ken Perlin's smoother step {@code t * t * t * (t * (6 * t - 15) + 10)} of {@code t = clamp((x
     * - edge0) / (edge1 - edge0), 0, 1)}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     */
    public static double smootherstep(double edge0, double edge1, double x) {
        double t = clamp((x - edge0) / (edge1 - edge0), 0.0, 1.0);
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
        float r = repeat(t, 2f * length);
        return length - java.lang.Math.abs(r - length);
    }
    /**
     * {@code t} bounced back and forth between 0 and {@code length}; see {@link #pingpong(float,
     * float)}.
     * <p>
     * Valid input: {@code length} must be positive.
     */
    public static double pingpong(double t, double length) {
        double r = repeat(t, 2.0 * length);
        return length - java.lang.Math.abs(r - length);
    }
}
