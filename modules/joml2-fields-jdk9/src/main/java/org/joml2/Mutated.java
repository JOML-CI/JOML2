// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks what a generated operation writes to.
 * <p>
 * On a parameter it marks the destination of a dest-form method: {@code add(Float3R other,
 * @Mutated Float3 dest)} stores its result in {@code dest} and returns {@code dest}; the receiver
 * and every other argument are read only. A dest-form method never allocates.
 * <p>
 * On a method it marks a self-form: {@code @Mutated Float3 add(Float3R other)} writes its result
 * to {@code this} and returns {@code this} - unless {@link Joml#RETURN_NEW} is enabled
 * ({@code -Djoml.returnNew=true} or {@code JomlConfig.setReturnNew(true)}), in which case it
 * leaves {@code this} unchanged and returns a freshly allocated instance holding the result.
 * <p>
 * The annotation is retained at runtime, so tools can find the mutating surface reflectively. It
 * only appears in the mutable variants; the immutable record and value variants have no dest
 * parameters and their operations always return new instances.
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mutated {
}
