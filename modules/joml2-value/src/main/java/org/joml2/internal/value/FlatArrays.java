// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.value;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.Objects;

/**
 * Arrays of the value records for their {@code newArray} factories: flat - the elements stored
 * inline, without an object per element - where the JVM lets this module create one, that is when
 * the application exports {@code java.base/jdk.internal.value} to it
 * ({@code --add-exports java.base/jdk.internal.value=org.joml2}, or {@code =ALL-UNNAMED} on the
 * classpath); an ordinary array otherwise. A flat array is null-restricted - every element holds a
 * value, storing {@code null} throws {@link NullPointerException} - and non-atomic: an element written
 * by one thread while another reads it can be read torn. Not public API.
 */
public final class FlatArrays {

    private FlatArrays() {}

    /**
     * {@code ValueClass.newNullRestrictedNonAtomicArray(Class, int, Object)}, or null where it is out
     * of reach: the package not exported to this module, or the JDK-internal API changed.
     */
    private static final MethodHandle NEW_ARRAY = newArrayHandle();

    private static MethodHandle newArrayHandle() {
        try {
            return MethodHandles.lookup().findStatic(Class.forName("jdk.internal.value.ValueClass"),
                    "newNullRestrictedNonAtomicArray",
                    MethodType.methodType(Object[].class, Class.class, int.class, Object.class));
        } catch (ReflectiveOperationException | RuntimeException | LinkageError e) {
            return null;
        }
    }

    /** Whether {@link #newArray} hands out flat arrays. */
    public static final boolean FLAT = NEW_ARRAY != null;

    /**
     * An array of {@code length} elements of value class {@code type}, each {@code init}: flat where
     * {@link #FLAT}, else an ordinary array filled with {@code init}.
     *
     * @throws NullPointerException if {@code init} is null
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static Object[] newArray(Class<?> type, int length, Object init) {
        Objects.requireNonNull(init, "init");
        if (NEW_ARRAY != null) {
            try {
                return (Object[]) NEW_ARRAY.invokeExact(type, length, init);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable t) {
                throw new AssertionError(t);
            }
        }
        Object[] a = (Object[]) java.lang.reflect.Array.newInstance(type, length);
        Arrays.fill(a, init);
        return a;
    }
}
