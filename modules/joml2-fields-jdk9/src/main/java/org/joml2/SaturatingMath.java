// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

public final class SaturatingMath {
    private SaturatingMath() {
    }

    /**
     * {@code a + b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int satAdd(int a, int b) {
        return (int) Math.min(Math.max((long) a + (long) b, Integer.MIN_VALUE), Integer.MAX_VALUE);
    }

    /**
     * {@code a - b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int satSub(int a, int b) {
        return (int) Math.min(Math.max((long) a - (long) b, Integer.MIN_VALUE), Integer.MAX_VALUE);
    }

    /**
     * {@code a * b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int satMul(int a, int b) {
        return (int) Math.min(Math.max((long) a * (long) b, Integer.MIN_VALUE), Integer.MAX_VALUE);
    }

    /**
     * {@code -a}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static int satNeg(int a) {
        return -Math.max(a, -Integer.MAX_VALUE);
    }

    /**
     * {@code a + b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long satAddL(long a, long b) {
        long r = a + b;
        if (((a ^ r) & (b ^ r)) >= 0) return r;
        // MAX_VALUE + 0 = MAX_VALUE for a>=0; MAX_VALUE + 1 wraps to MIN_VALUE for a<0.
        return Long.MAX_VALUE + (a >>> 63);
    }

    /**
     * {@code a - b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long satSubL(long a, long b) {
        long r = a - b;
        if (((a ^ b) & (a ^ r)) >= 0) return r;
        return Long.MAX_VALUE + (a >>> 63);
    }

    /**
     * {@code a * b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long satMulL(long a, long b) {
        if (a == 0 || b == 0) return 0;
        // The (a*b)/a==b overflow check itself overflows when a==-1 and
        // b==Long.MIN_VALUE (or vice versa). Handle MIN_VALUE explicitly first.
        if (a == Long.MIN_VALUE) return b == 1 ? Long.MIN_VALUE
                : Long.MAX_VALUE + (b >>> 63 ^ 1L);
        if (b == Long.MIN_VALUE) return a == 1 ? Long.MIN_VALUE
                : Long.MAX_VALUE + (a >>> 63 ^ 1L);
        long r = a * b;
        if (r / a == b) return r;
        // Sign of the (mathematical) product determines saturation endpoint:
        // negative product → MIN_VALUE, non-negative → MAX_VALUE.
        return Long.MAX_VALUE + ((a ^ b) >>> 63);
    }

    /**
     * {@code -a}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static long satNegL(long a) {
        return -Math.max(a, -Long.MAX_VALUE);
    }

    /**
     * {@code a + b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte satAddB(byte a, byte b) { return clampB(a + b); }

    /**
     * {@code a - b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte satSubB(byte a, byte b) { return clampB(a - b); }

    /**
     * {@code a * b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte satMulB(byte a, byte b) { return clampB(a * b); }

    /**
     * {@code -a}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static byte satNegB(byte a) {
        return (byte) -Math.max(a, -Byte.MAX_VALUE);
    }

    /**
     * {@code a + b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short satAddS(short a, short b) { return clampS(a + b); }

    /**
     * {@code a - b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short satSubS(short a, short b) { return clampS(a - b); }

    /**
     * {@code a * b}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short satMulS(short a, short b) { return clampS(a * b); }

    /**
     * {@code -a}, saturated to the range of the type instead of wrapping around.
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public static short satNegS(short a) {
        return (short) -Math.max(a, -Short.MAX_VALUE);
    }

    private static byte clampB(int r) {
        return (byte) Math.min(Math.max(r, Byte.MIN_VALUE), Byte.MAX_VALUE);
    }

    private static short clampS(int r) {
        return (short) Math.min(Math.max(r, Short.MIN_VALUE), Short.MAX_VALUE);
    }
}
