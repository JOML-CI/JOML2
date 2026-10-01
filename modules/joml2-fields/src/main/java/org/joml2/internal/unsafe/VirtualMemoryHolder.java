// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.unsafe;

import java.lang.foreign.MemorySegment;

/**
 * One shared segment spanning the whole address space: how the API store/load backend reaches
 * raw addresses. {@code MemorySegment.reinterpret} is a restricted method, so a JVM that denies
 * this module native access ({@code --illegal-native-access=deny}) leaves the segment unavailable
 * and {@link #virtualMemory()} throws instead.
 */
public final class VirtualMemoryHolder {
    private VirtualMemoryHolder() {}
    private static final MemorySegment VIRTUAL_MEMORY;
    private static final IllegalCallerException DENIED;

    static {
        MemorySegment memory = null;
        IllegalCallerException denied = null;
        try {
            memory = MemorySegment.ofAddress(0L).reinterpret(Long.MAX_VALUE);
        } catch (IllegalCallerException e) {
            denied = e;
        }
        VIRTUAL_MEMORY = memory;
        DENIED = denied;
    }

    /**
     * {@return the segment spanning the whole address space}
     *
     * @throws UnsupportedOperationException if the JVM denies this module native access
     */
    public static MemorySegment virtualMemory() {
        MemorySegment memory = VIRTUAL_MEMORY;
        if (memory == null)
            throw new UnsupportedOperationException("raw-address store/load with the API backend needs native access"
                + " (MemorySegment.reinterpret is restricted): run with --enable-native-access=org.joml2"
                + " (ALL-UNNAMED on the class path), or select the UNSAFE backend", DENIED);
        return memory;
    }
}
