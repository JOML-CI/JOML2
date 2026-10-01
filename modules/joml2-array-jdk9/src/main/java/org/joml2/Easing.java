// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

public final class Easing {
    private Easing() {
    }

    private static final float HALF_PI    = (float) (Math.PI * 0.5);
    private static final float PI         = (float) Math.PI;
    private static final float ELASTIC_C4 = (float) (2.0 * Math.PI / 3.0);
    private static final float ELASTIC_C5 = (float) (2.0 * Math.PI / 4.5);
    private static final float BOUNCE_N1  = 7.5625f;
    private static final float BOUNCE_D1  = 2.75f;

    /**
     * The quad easing curve (ease in) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float quadIn(float t)    { return t * t; }
    /**
     * The quad easing curve (ease out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float quadOut(float t)   { float u = 1f - t; return 1f - u * u; }
    /**
     * The quad easing curve (ease in and out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float quadInOut(float t) {
        if (t < 0.5f) return 2f * t * t;
        float u = -2f * t + 2f;
        return 1f - u * u * 0.5f;
    }

    /**
     * The cubic easing curve (ease in) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float cubicIn(float t)    { return t * t * t; }
    /**
     * The cubic easing curve (ease out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float cubicOut(float t)   { float u = 1f - t; return 1f - u * u * u; }
    /**
     * The cubic easing curve (ease in and out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t =
     * 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float cubicInOut(float t) {
        if (t < 0.5f) return 4f * t * t * t;
        float u = -2f * t + 2f;
        return 1f - u * u * u * 0.5f;
    }

    /**
     * The quart easing curve (ease in) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float quartIn(float t)    { return t * t * t * t; }
    /**
     * The quart easing curve (ease out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float quartOut(float t)   { float u = 1f - t; return 1f - u * u * u * u; }
    /**
     * The quart easing curve (ease in and out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t =
     * 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float quartInOut(float t) {
        if (t < 0.5f) return 8f * t * t * t * t;
        float u = -2f * t + 2f;
        return 1f - u * u * u * u * 0.5f;
    }

    /**
     * The quint easing curve (ease in) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float quintIn(float t)    { return t * t * t * t * t; }
    /**
     * The quint easing curve (ease out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float quintOut(float t)   { float u = 1f - t; return 1f - u * u * u * u * u; }
    /**
     * The quint easing curve (ease in and out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t =
     * 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float quintInOut(float t) {
        if (t < 0.5f) return 16f * t * t * t * t * t;
        float u = -2f * t + 2f;
        return 1f - u * u * u * u * u * 0.5f;
    }

    /**
     * The sine easing curve (ease in) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float sineIn(float t)    { return 1f - (float) Math.cos(t * HALF_PI); }
    /**
     * The sine easing curve (ease out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float sineOut(float t)   { return (float) Math.sin(t * HALF_PI); }
    /**
     * The sine easing curve (ease in and out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float sineInOut(float t) { return 0.5f * (1f - (float) Math.cos(PI * t)); }

    /**
     * The expo easing curve (ease in) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float expoIn(float t) {
        return t == 0f ? 0f : (float) Math.pow(2.0, 10.0 * t - 10.0);
    }
    /**
     * The expo easing curve (ease out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float expoOut(float t) {
        return t == 1f ? 1f : 1f - (float) Math.pow(2.0, -10.0 * t);
    }
    /**
     * The expo easing curve (ease in and out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float expoInOut(float t) {
        if (t == 0f) return 0f;
        if (t == 1f) return 1f;
        return t < 0.5f
                ? (float) Math.pow(2.0, 20.0 * t - 10.0) * 0.5f
                : (2f - (float) Math.pow(2.0, -20.0 * t + 10.0)) * 0.5f;
    }

    /**
     * The elastic easing curve (ease in) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float elasticIn(float t) {
        if (t == 0f) return 0f;
        if (t == 1f) return 1f;
        return -(float) Math.pow(2.0, 10.0 * t - 10.0)
                * (float) Math.sin((t * 10.0 - 10.75) * ELASTIC_C4);
    }
    /**
     * The elastic easing curve (ease out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float elasticOut(float t) {
        if (t == 0f) return 0f;
        if (t == 1f) return 1f;
        return (float) Math.pow(2.0, -10.0 * t)
                * (float) Math.sin((t * 10.0 - 0.75) * ELASTIC_C4) + 1f;
    }
    /**
     * The elastic easing curve (ease in and out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t =
     * 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float elasticInOut(float t) {
        if (t == 0f) return 0f;
        if (t == 1f) return 1f;
        if (t < 0.5f) {
            return -0.5f * (float) Math.pow(2.0, 20.0 * t - 10.0)
                    * (float) Math.sin((20.0 * t - 11.125) * ELASTIC_C5);
        }
        return 0.5f * (float) Math.pow(2.0, -20.0 * t + 10.0)
                * (float) Math.sin((20.0 * t - 11.125) * ELASTIC_C5) + 1f;
    }

    /**
     * The bounce easing curve (ease out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float bounceOut(float t) {
        if (t < 1f / BOUNCE_D1) {
            return BOUNCE_N1 * t * t;
        } else if (t < 2f / BOUNCE_D1) {
            float u = t - 1.5f / BOUNCE_D1;
            return BOUNCE_N1 * u * u + 0.75f;
        } else if (t < 2.5f / BOUNCE_D1) {
            float u = t - 2.25f / BOUNCE_D1;
            return BOUNCE_N1 * u * u + 0.9375f;
        }
        float u = t - 2.625f / BOUNCE_D1;
        return BOUNCE_N1 * u * u + 0.984375f;
    }
    /**
     * The bounce easing curve (ease in) at {@code t}: 0 at {@code t = 0}, 1 at {@code t = 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float bounceIn(float t) {
        return 1f - bounceOut(1f - t);
    }
    /**
     * The bounce easing curve (ease in and out) at {@code t}: 0 at {@code t = 0}, 1 at {@code t =
     * 1}.
     * <p>
     * Valid input: {@code t} must lie in {@code [0, 1]}.
     */
    public static float bounceInOut(float t) {
        return t < 0.5f
                ? (1f - bounceOut(1f - 2f * t)) * 0.5f
                : (1f + bounceOut(2f * t - 1f)) * 0.5f;
    }
}
