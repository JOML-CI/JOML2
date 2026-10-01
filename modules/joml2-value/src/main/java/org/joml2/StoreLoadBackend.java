// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

/**
 * Selects how the generated {@code <Type>Ops} read/write individual elements of native and
 * {@code byte[]}-backed heap {@link java.lang.foreign.MemorySegment}s, direct
 * {@link java.nio.Buffer}s, heap {@link java.nio.ByteBuffer}s and raw {@code long} addresses.
 *
 * <p>The default (when {@code -Djoml.storeLoadBackend} is unset and no
 * {@code JomlConfig} override is given) is {@link #UNSAFE} when {@code sun.misc.Unsafe} /
 * {@code jdk.internal.misc.Unsafe} is available, falling back to {@link #API} otherwise -
 * so the library still initializes on a JDK without {@code Unsafe}.</p>
 */
public enum StoreLoadBackend {
    /**
     * Pure NIO/foreign API: {@code segment.get(JAVA_FLOAT, ..)} etc. Never touches
     * {@code Unsafe}. Select with {@code -Djoml.storeLoadBackend=api} to opt out of the
     * Unsafe default (e.g. to keep bounds/liveness checks, or avoid {@code sun.misc.Unsafe}).
     *
     * <p>On the JDK 9 / JDK 17 variants (no {@code java.lang.foreign.MemorySegment}) the
     * raw-address forms - {@code store*Unsafe(long)} / {@code load*Unsafe(long)} and the
     * {@code <Type>Ops} {@code long} address overloads - are unavailable under this backend
     * and throw {@link UnsupportedOperationException}; this variant emulates them through a sized {@code MemorySegment} view instead.</p>
     */
    API,

    /**
     * {@code sun.misc.Unsafe}/{@code jdk.internal.misc.Unsafe} direct memory access for
     * native and {@code byte[]}-backed heap segments, direct buffers, native-order heap {@code ByteBuffer}s and raw {@code long} addresses (stores into read-only targets, segments over other arrays, typed heap buffers and buffers in another byte order still
     * take {@link #API}). The default when {@code Unsafe} is available.
     *
     * <p>It uses {@code jdk.internal.misc.Unsafe} where the JVM exports {@code jdk.internal.misc}
     * to the library - {@code --add-exports java.base/jdk.internal.misc=org.joml2}, or
     * {@code =ALL-UNNAMED} on the class path - and {@code sun.misc.Unsafe} otherwise. Prefer the
     * export: {@code jdk.internal.misc.Unsafe} prints no warning, and its accesses inline into far
     * less bytecode (a 4x4 matrix store into a direct buffer: about 600 bytes instead of about
     * 1,500), so more of a large hot method fits C2's inlining budget. {@code sun.misc.Unsafe}
     * memory access is deprecated for removal (JEP 471): on JDK 24+ its first use prints a warning
     * (JEP 498) unless the JVM runs with {@code --sun-misc-unsafe-memory-access=allow};
     * {@link #API} avoids Unsafe altogether. When neither is available the library falls back to
     * {@link #API} (with one warning if this backend was requested explicitly).</p>
     */
    UNSAFE
}
