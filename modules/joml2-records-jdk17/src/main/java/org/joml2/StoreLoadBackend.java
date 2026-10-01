// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

/**
 * Selects how the generated {@code <Type>Ops} read/write individual elements of direct
 * {@link java.nio.Buffer}s and raw {@code long} addresses.
 *
 * <p>The default (when {@code -Djoml.storeLoadBackend} is unset and no
 * {@code JomlConfig} override is given) is {@link #UNSAFE} when {@code sun.misc.Unsafe} /
 * {@code jdk.internal.misc.Unsafe} is available, falling back to {@link #API} otherwise -
 * so the library still initializes on a JDK without {@code Unsafe}.</p>
 */
public enum StoreLoadBackend {
    /**
     * Pure NIO/foreign API: {@code buffer.get(index)} etc. Never touches
     * {@code Unsafe}. Select with {@code -Djoml.storeLoadBackend=api} to opt out of the
     * Unsafe default (e.g. to keep bounds/liveness checks, or avoid {@code sun.misc.Unsafe}).
     *
     * <p>On the JDK 9 / JDK 17 variants (no {@code java.lang.foreign.MemorySegment}) the
     * raw-address forms - {@code store*Unsafe(long)} / {@code load*Unsafe(long)} and the
     * {@code <Type>Ops} {@code long} address overloads - are unavailable under this backend
     * and throw {@link UnsupportedOperationException}; select {@link #UNSAFE} to use them on this variant.</p>
     */
    API,
    /**
     * {@code sun.misc.Unsafe}/{@code jdk.internal.misc.Unsafe} direct memory access for
     * direct buffers and raw {@code long} addresses (heap and read-only
     * targets still fall to {@link #API}). The default when {@code Unsafe} is available.
     *
     * <p>{@code sun.misc.Unsafe} memory access is deprecated for removal (JEP 471): on JDK 23+
     * run with {@code --sun-misc-unsafe-memory-access=allow} to silence the warnings, or select
     * {@link #API}. When {@code sun.misc.Unsafe} is unavailable the library falls back to
     * {@link #API} (with one warning if this backend was requested explicitly).</p>
     */
    UNSAFE
}
