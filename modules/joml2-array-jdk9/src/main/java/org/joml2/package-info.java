// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
/**
 * Math types for 2D/3D graphics: vectors, matrices, quaternions, dual quaternions, rigid
 * transforms, shapes (AABB, sphere, ray, plane, rect, OBB) and their intersection queries, in
 * {@code float}, {@code double} and the integer precisions.
 *
 * <h2>Mutable and immutable variants</h2>
 * <p>The library ships in two API shapes that share type names ({@code Float3},
 * {@code Float4x4}, {@code FloatQuat}, ...) but not code:
 * <ul>
 *   <li><b>Mutable variants</b> ({@code joml2-fields} (scalar fields), {@code joml2-array}, {@code joml2-simd} and
 *   the {@code -jdk9} flavours): each type is an interface pair. {@code Float3R} is the read-only
 *   view (accessors, queries, store methods and dest-form operations); {@code Float3} extends it
 *   with the mutating surface (setters, load methods and in-place self-forms, marked with
 *   {@code @Mutated}). Instances come from the {@link org.joml2.Joml} factory
 *   methods; the implementation classes are internal. Every {@code Float3R}/{@code Float3}
 *   argument must be such a library instance - the implementations access their internal
 *   representation directly and do not accept user classes that implement the interfaces.</li>
 *   <li><b>Immutable variants</b> ({@code joml2-records}, {@code joml2-records-jdk17},
 *   {@code joml2-value}): each type is a record with public component accessors and no
 *   {@code R} view, dest parameters or setters; every operation returns a new instance and
 *   {@code Joml.float3(...)} is merely a convenience over the canonical constructor.</li>
 * </ul>
 *
 * <h2>Valid input</h2>
 * <p>Unless a method's documentation says otherwise, every floating-point input - the
 * components of the receiver and of every argument, and every scalar argument - must be
 * finite (neither NaN nor infinite) and either zero or of a magnitude between
 * <b>{@code 1e-6} and {@code 1e6}</b> for {@code float} and <b>{@code 1e-50} and
 * {@code 1e50}</b> for {@code double}. Integer inputs may take any value of their type; integer
 * results wrap around on overflow, as Java's integer arithmetic does ({@code abs} and
 * {@code negate} of {@code MIN_VALUE} included), unless a method says it saturates.
 * <p>A method whose valid input differs states it in a paragraph starting with
 * <i>Valid input:</i>. The clauses there narrow the contract (a divisor that must be
 * non-zero, a vector that must have unit length, a matrix that must be invertible, an interval
 * a parameter must lie in) or widen it (a far plane that may be infinite, a wider magnitude
 * band for operations that only form sums of squares).
 * <p>For valid input no method produces NaN and no method throws, apart from a documented
 * {@code @throws}. Results may still overflow to infinity. For input outside its valid range
 * the result of a method is undefined: it may be NaN, infinite or merely inaccurate, and it
 * may differ between the library variants.
 * <p>The sign of a zero result is not specified: the generated code simplifies expressions such
 * as {@code 0 + x} to {@code x} and {@code -(a - b)} to {@code b - a}, which can turn a
 * {@code -0.0} into {@code 0.0} or back compared with the formula written out naively.
 *
 * <h2>Global configuration</h2>
 * <p>Each flag is read once, at class initialization, from a system property or a programmatic
 * override set beforehand; changing it later throws {@link IllegalStateException}.
 * <ul>
 *   <li>{@code joml.returnNew} / {@code JomlConfig.setReturnNew}: self-forms return a fresh
 *   instance instead of mutating {@code this} (mutable variants only).</li>
 *   <li>{@code joml.storeLoadBackend} ({@code api} | {@code unsafe}) /
 *   {@code JomlConfig.setStoreLoadBackend}: how buffer and memory-segment store/load methods
 *   and the {@code org.joml2.ops} classes access native memory; defaults to {@code unsafe}
 *   when {@code Unsafe} is available. The backend itself ({@code Joml.storeLoadBackend()}) is
 *   resolved on the first store/load that touches native memory, so a program that never does
 *   never probes {@code sun.misc.Unsafe}.</li>
 *   <li>{@code joml.vectorApi} / {@code JomlConfig.setVectorApi}: {@code false} forces the
 *   scalar fallbacks of the SIMD {@code *Ops} kernels.</li>
 *   <li>{@code joml.useFma} / {@link org.joml2.Math#setUseFma}: whether {@code fma} uses the
 *   fused {@code java.lang.Math.fma} (default {@code true}).</li>
 *   <li>{@code joml.cosFromSin} / {@link org.joml2.Math#setCosFromSin}: whether rotations and the
 *   other operations that need the sine and the cosine of one angle derive the cosine from the
 *   sine (default {@code true}; see {@link org.joml2.Math#cosFromSin(float, float)}).</li>
 *   <li>{@code joml.fastmath}, {@code joml.sinLookup}, {@code joml.sinLookup.bits} /
 *   {@link org.joml2.Math#setFastmath}, {@link org.joml2.Math#setSinLookup},
 *   {@link org.joml2.Math#setSinLookupBits}: fast approximations and the lookup table for
 *   sin/cos/atan2.</li>
 *   <li>{@code joml.strictMath} / {@link org.joml2.Math#setStrictMath}: transcendentals via
 *   {@code StrictMath} for bit-identical results across platforms.</li>
 * </ul>
 */
@NullMarked
package org.joml2;

import org.jspecify.annotations.NullMarked;
