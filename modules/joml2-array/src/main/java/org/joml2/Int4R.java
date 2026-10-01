// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * Read-only view of a 4D vector of {@code int} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link Int4}. APIs that only read a 4D vector
 * should accept {@code Int4R}, so callers can pass mutable instances without exposing them to
 * modification.
 * <p>
 * Arguments of type {@code Int4R} must be instances created by the library ({@link Joml} factories
 * / the library's own types); the implementations read cached state through the library's own
 * classes, so foreign implementations of the {@code *R} interfaces are not supported as arguments.
 * <p>
 * {@code equals} compares the components element-wise with {@code ==}. {@code hashCode} is
 * consistent with it. Only instances of this library's implementation compare equal to each other;
 * the {@code equals} of a vector never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance: the
 * difference is widened to {@code long} before its magnitude is taken, so the two are compared
 * exactly without overflow, and a negative {@code epsilon} matches nothing.
 */
public interface Int4R {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 16;

    /** {@return a shared all-zero vector} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value. */
    public static Int4R ZERO() { return Holder.ZERO; }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Int4 add(Int4R other, @Mutated Int4 dest);

    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Long4 add(Int4R other, @Mutated Long4 dest);

    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Double4 add(Int4R other, @Mutated Double4 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 add(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 add(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 add(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Int4 ceilDiv(int scalar, @Mutated Int4 dest);

    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long4 ceilDiv(int scalar, @Mutated Long4 dest);

    /**
     * Compute the component-wise ceiling division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Double4 ceilDiv(int scalar, @Mutated Double4 dest);

    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Int4 ceilDiv(Int4R other, @Mutated Int4 dest);

    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long4 ceilDiv(Int4R other, @Mutated Long4 dest);

    /**
     * Compute the component-wise ceiling division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Double4 ceilDiv(Int4R other, @Mutated Double4 dest);

    /**
     * Compute the component-wise ceiling division of this vector by ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 ceilDiv(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Compute the component-wise ceiling division of this vector by ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 ceilDiv(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Compute the component-wise ceiling division of this vector by ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 ceilDiv(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Int4 ceilMod(int scalar, @Mutated Int4 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long4 ceilMod(int scalar, @Mutated Long4 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Double4 ceilMod(int scalar, @Mutated Double4 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Int4 ceilMod(Int4R other, @Mutated Int4 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long4 ceilMod(Int4R other, @Mutated Long4 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Double4 ceilMod(Int4R other, @Mutated Double4 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 ceilMod(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 ceilMod(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Compute the component-wise ceiling modulus of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 ceilMod(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Int4 div(int scalar, @Mutated Int4 dest);

    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long4 div(int scalar, @Mutated Long4 dest);

    /**
     * Divide each component of this vector by {@code scalar} (integer division, truncating toward
     * zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Double4 div(int scalar, @Mutated Double4 dest);

    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Int4 div(Int4R other, @Mutated Int4 dest);

    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long4 div(Int4R other, @Mutated Long4 dest);

    /**
     * Divide this vector component-wise by {@code other} (integer division, truncating toward zero)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Double4 div(Int4R other, @Mutated Double4 dest);

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) (integer
     * division, truncating toward zero) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 div(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) (integer
     * division, truncating toward zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 div(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) (integer
     * division, truncating toward zero) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 div(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Int4 floorDiv(int scalar, @Mutated Int4 dest);

    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long4 floorDiv(int scalar, @Mutated Long4 dest);

    /**
     * Compute the component-wise floor division of this vector by {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Double4 floorDiv(int scalar, @Mutated Double4 dest);

    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Int4 floorDiv(Int4R other, @Mutated Int4 dest);

    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long4 floorDiv(Int4R other, @Mutated Long4 dest);

    /**
     * Compute the component-wise floor division of this vector by {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Double4 floorDiv(Int4R other, @Mutated Double4 dest);

    /**
     * Compute the component-wise floor division of this vector by ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 floorDiv(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Compute the component-wise floor division of this vector by ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 floorDiv(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Compute the component-wise floor division of this vector by ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 floorDiv(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Int4 floorMod(int scalar, @Mutated Int4 dest);

    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long4 floorMod(int scalar, @Mutated Long4 dest);

    /**
     * Compute the component-wise floor modulus of this vector and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Double4 floorMod(int scalar, @Mutated Double4 dest);

    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Int4 floorMod(Int4R other, @Mutated Int4 dest);

    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long4 floorMod(Int4R other, @Mutated Long4 dest);

    /**
     * Compute the component-wise floor modulus of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Double4 floorMod(Int4R other, @Mutated Double4 dest);

    /**
     * Compute the component-wise floor modulus of this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 floorMod(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Compute the component-wise floor modulus of this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 floorMod(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Compute the component-wise floor modulus of this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 floorMod(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    Int4 mul(int scalar, @Mutated Int4 dest);

    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    Long4 mul(int scalar, @Mutated Long4 dest);

    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    Double4 mul(int scalar, @Mutated Double4 dest);

    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Int4 mul(Int4R other, @Mutated Int4 dest);

    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Long4 mul(Int4R other, @Mutated Long4 dest);

    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Double4 mul(Int4R other, @Mutated Double4 dest);

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 mul(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 mul(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 mul(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 negate(@Mutated Int4 dest);

    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 negate(@Mutated Long4 dest);

    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 negate(@Mutated Double4 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Int4 rem(int scalar, @Mutated Int4 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Long4 rem(int scalar, @Mutated Long4 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and {@code scalar} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Double4 rem(int scalar, @Mutated Double4 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Int4 rem(Int4R other, @Mutated Int4 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Long4 rem(Int4R other, @Mutated Long4 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and {@code other} (the
     * remainder carries the sign of the dividend, exactly Java's {@code %}, so it pairs with
     * {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Double4 rem(Int4R other, @Mutated Double4 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) (the remainder carries the sign of the dividend, exactly Java's
     * {@code %}, so it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 rem(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) (the remainder carries the sign of the dividend, exactly Java's
     * {@code %}, so it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 rem(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Compute the component-wise truncated remainder of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) (the remainder carries the sign of the dividend, exactly Java's
     * {@code %}, so it pairs with {@code div}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 rem(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Int4 sub(Int4R other, @Mutated Int4 dest);

    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Long4 sub(Int4R other, @Mutated Long4 dest);

    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Double4 sub(Int4R other, @Mutated Double4 dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 sub(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 sub(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 sub(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    Int4 and(Int4R other, @Mutated Int4 dest);

    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    Long4 and(Int4R other, @Mutated Long4 dest);

    /**
     * Compute the bitwise AND of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise AND
     * @param dest will hold the result
     * @return dest
     */
    Double4 and(Int4R other, @Mutated Double4 dest);

    /**
     * Compute the bitwise AND of each component of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 and(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Compute the bitwise AND of each component of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 and(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Compute the bitwise AND of each component of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 and(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Compute the number of one-bits of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 bitCount(@Mutated Int4 dest);

    /**
     * Compute the number of one-bits of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 bitCount(@Mutated Long4 dest);

    /**
     * Compute the number of one-bits of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 bitCount(@Mutated Double4 dest);

    /**
     * Compute the bitwise NOT of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 not(@Mutated Int4 dest);

    /**
     * Compute the bitwise NOT of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 not(@Mutated Long4 dest);

    /**
     * Compute the bitwise NOT of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 not(@Mutated Double4 dest);

    /**
     * Compute the number of leading zero bits of each component of this vector and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 numberOfLeadingZeros(@Mutated Int4 dest);

    /**
     * Compute the number of leading zero bits of each component of this vector and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 numberOfLeadingZeros(@Mutated Long4 dest);

    /**
     * Compute the number of leading zero bits of each component of this vector and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 numberOfLeadingZeros(@Mutated Double4 dest);

    /**
     * Compute the number of trailing zero bits of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 numberOfTrailingZeros(@Mutated Int4 dest);

    /**
     * Compute the number of trailing zero bits of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 numberOfTrailingZeros(@Mutated Long4 dest);

    /**
     * Compute the number of trailing zero bits of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 numberOfTrailingZeros(@Mutated Double4 dest);

    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    Int4 or(Int4R other, @Mutated Int4 dest);

    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    Long4 or(Int4R other, @Mutated Long4 dest);

    /**
     * Compute the bitwise OR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise OR
     * @param dest will hold the result
     * @return dest
     */
    Double4 or(Int4R other, @Mutated Double4 dest);

    /**
     * Compute the bitwise OR of each component of this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 or(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Compute the bitwise OR of each component of this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 or(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Compute the bitwise OR of each component of this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 or(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Compute the bit-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 reverseBits(@Mutated Int4 dest);

    /**
     * Compute the bit-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 reverseBits(@Mutated Long4 dest);

    /**
     * Compute the bit-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 reverseBits(@Mutated Double4 dest);

    /**
     * Compute the byte-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 reverseBytes(@Mutated Int4 dest);

    /**
     * Compute the byte-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 reverseBytes(@Mutated Long4 dest);

    /**
     * Compute the byte-reversed value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 reverseBytes(@Mutated Double4 dest);

    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    Int4 rotateLeft(int distance, @Mutated Int4 dest);

    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    Long4 rotateLeft(int distance, @Mutated Long4 dest);

    /**
     * Rotate the bits of each component of this vector left by {@code distance} positions and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotateLeft(int distance, @Mutated Double4 dest);

    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    Int4 rotateRight(int distance, @Mutated Int4 dest);

    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    Long4 rotateRight(int distance, @Mutated Long4 dest);

    /**
     * Rotate the bits of each component of this vector right by {@code distance} positions and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param distance the number of bit positions to rotate by
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotateRight(int distance, @Mutated Double4 dest);

    /**
     * Shift each component of this vector left by {@code shift} bits and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Int4 shl(int shift, @Mutated Int4 dest);

    /**
     * Shift each component of this vector left by {@code shift} bits and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Long4 shl(int shift, @Mutated Long4 dest);

    /**
     * Shift each component of this vector left by {@code shift} bits and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Double4 shl(int shift, @Mutated Double4 dest);

    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Int4 shr(int shift, @Mutated Int4 dest);

    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Long4 shr(int shift, @Mutated Long4 dest);

    /**
     * Arithmetically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Double4 shr(int shift, @Mutated Double4 dest);

    /**
     * Logically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Int4 ushr(int shift, @Mutated Int4 dest);

    /**
     * Logically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Long4 ushr(int shift, @Mutated Long4 dest);

    /**
     * Logically shift each component of this vector right by {@code shift} bits and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param shift the number of bit positions to shift by
     * @param dest will hold the result
     * @return dest
     */
    Double4 ushr(int shift, @Mutated Double4 dest);

    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    Int4 xor(Int4R other, @Mutated Int4 dest);

    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    Long4 xor(Int4R other, @Mutated Long4 dest);

    /**
     * Compute the bitwise XOR of each component of this vector and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the bitwise XOR
     * @param dest will hold the result
     * @return dest
     */
    Double4 xor(Int4R other, @Mutated Double4 dest);

    /**
     * Compute the bitwise XOR of each component of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 xor(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Compute the bitwise XOR of each component of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 xor(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Compute the bitwise XOR of each component of this vector and ({@code x}, {@code y},
     * {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 xor(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    Int4 set(int s, @Mutated Int4 dest);

    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    Long4 set(int s, @Mutated Long4 dest);

    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    Double4 set(int s, @Mutated Double4 dest);

    /**
     * Convert this vector to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 toFloat(@Mutated Float4 dest);

    /**
     * Convert this vector to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 toDouble(@Mutated Double4 dest);

    /**
     * Convert this vector to {@code byte} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Byte4 toByte(@Mutated Byte4 dest);

    /**
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Short4 toShort(@Mutated Short4 dest);

    /**
     * Convert this vector to {@code long} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 toLong(@Mutated Long4 dest);

    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 absolute(@Mutated Int4 dest);

    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 absolute(@Mutated Long4 dest);

    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 absolute(@Mutated Double4 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    Int4 clamp(int min, int max, @Mutated Int4 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    Long4 clamp(int min, int max, @Mutated Long4 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    Double4 clamp(int min, int max, @Mutated Double4 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    Int4 clamp(Int4R min, Int4R max, @Mutated Int4 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    Long4 clamp(Int4R min, Int4R max, @Mutated Long4 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    Double4 clamp(Int4R min, Int4R max, @Mutated Double4 dest);

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code (minX, minY, minZ, minW)} must not exceed
     * {@code (maxX, maxY, maxZ, maxW)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minW the {@code w} component of the vector {@code (minX, minY, minZ, minW)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxW the {@code w} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 clamp(int minX, int minY, int minZ, int minW, int maxX, int maxY, int maxZ, int maxW, @Mutated Int4 dest);

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: {@code (minX, minY, minZ, minW)} must not exceed
     * {@code (maxX, maxY, maxZ, maxW)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minW the {@code w} component of the vector {@code (minX, minY, minZ, minW)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxW the {@code w} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 clamp(int minX, int minY, int minZ, int minW, int maxX, int maxY, int maxZ, int maxW, @Mutated Long4 dest);

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (minX, minY, minZ, minW)} must not exceed
     * {@code (maxX, maxY, maxZ, maxW)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minW the {@code w} component of the vector {@code (minX, minY, minZ, minW)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxW the {@code w} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 clamp(int minX, int minY, int minZ, int minW, int maxX, int maxY, int maxZ, int maxW, @Mutated Double4 dest);

    /**
     * Compute the sum of all components of this vector.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    long compAdd();

    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    int compMax();

    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    int compMin();

    /**
     * Compute the product of all components of this vector.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    long compMul();

    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    Int4 cross(Int4R v, Int4R w, @Mutated Int4 dest);

    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    Long4 cross(Int4R v, Int4R w, @Mutated Long4 dest);

    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    Double4 cross(Int4R v, Int4R w, @Mutated Double4 dest);

    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param wX the {@code x} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wY the {@code y} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wZ the {@code z} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wW the {@code w} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 cross(int vX, int vY, int vZ, int vW, int wX, int wY, int wZ, int wW, @Mutated Int4 dest);

    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param wX the {@code x} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wY the {@code y} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wZ the {@code z} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wW the {@code w} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 cross(int vX, int vY, int vZ, int vW, int wX, int wY, int wZ, int wW, @Mutated Long4 dest);

    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param wX the {@code x} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wY the {@code y} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wZ the {@code z} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wW the {@code w} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 cross(int vX, int vY, int vZ, int vW, int wX, int wY, int wZ, int wW, @Mutated Double4 dest);

    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    long distanceSquared(Int4R other);

    /**
     * Compute the squared distance between this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return the squared distance between this vector and ({@code x}, {@code y}, {@code z},
     *        {@code w})
     */
    long distanceSquared(int x, int y, int z, int w);

    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    long dot(Int4R other);

    /**
     * Compute the dot product of this vector and ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return the dot product of this vector and ({@code x}, {@code y}, {@code z}, {@code w})
     */
    long dot(int x, int y, int z, int w);

    /**
     * Compute the squared length of this vector.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    long lengthSquared();

    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    long manhattanDistance(Int4R other);

    /**
     * Compute the Manhattan distance between this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return the Manhattan distance between this vector and ({@code x}, {@code y}, {@code z},
     *        {@code w})
     */
    long manhattanDistance(int x, int y, int z, int w);

    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    long manhattanLength();

    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Int4 max(int scalar, @Mutated Int4 dest);

    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Long4 max(int scalar, @Mutated Long4 dest);

    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Double4 max(int scalar, @Mutated Double4 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Int4 max(Int4R other, @Mutated Int4 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Long4 max(Int4R other, @Mutated Long4 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Double4 max(Int4R other, @Mutated Double4 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 max(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 max(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 max(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Int4 min(int scalar, @Mutated Int4 dest);

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Long4 min(int scalar, @Mutated Long4 dest);

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Double4 min(int scalar, @Mutated Double4 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Int4 min(Int4R other, @Mutated Int4 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Long4 min(Int4R other, @Mutated Long4 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Double4 min(Int4R other, @Mutated Double4 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 min(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 min(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 min(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 sign(@Mutated Int4 dest);

    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 sign(@Mutated Long4 dest);

    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 sign(@Mutated Double4 dest);

    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Int4 satAdd(Int4R other, @Mutated Int4 dest);

    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Long4 satAdd(Int4R other, @Mutated Long4 dest);

    /**
     * Add {@code other} to this vector, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Double4 satAdd(Int4R other, @Mutated Double4 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 satAdd(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 satAdd(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector, clamping to the value range
     * instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 satAdd(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Int4 satMul(Int4R other, @Mutated Int4 dest);

    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Long4 satMul(Int4R other, @Mutated Long4 dest);

    /**
     * Multiply this vector by {@code other}, clamping to the value range instead of overflowing and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Double4 satMul(Int4R other, @Mutated Double4 dest);

    /**
     * Multiply this vector by ({@code x}, {@code y}, {@code z}, {@code w}), clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 satMul(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Multiply this vector by ({@code x}, {@code y}, {@code z}, {@code w}), clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 satMul(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Multiply this vector by ({@code x}, {@code y}, {@code z}, {@code w}), clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 satMul(int x, int y, int z, int w, @Mutated Double4 dest);

    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 satNegate(@Mutated Int4 dest);

    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 satNegate(@Mutated Long4 dest);

    /**
     * Negate this vector, clamping to the value range instead of overflowing and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 satNegate(@Mutated Double4 dest);

    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Int4 satSub(Int4R other, @Mutated Int4 dest);

    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Long4 satSub(Int4R other, @Mutated Long4 dest);

    /**
     * Subtract {@code other} from this vector, clamping to the value range instead of overflowing
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Double4 satSub(Int4R other, @Mutated Double4 dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Int4 satSub(int x, int y, int z, int w, @Mutated Int4 dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Long4 satSub(int x, int y, int z, int w, @Mutated Long4 dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector, clamping to the value
     * range instead of overflowing and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 satSub(int x, int y, int z, int w, @Mutated Double4 dest);
    /** {@return the value of the {@code x} component} <p>Valid input: any value. */
    int x();
    /** {@return the value of the {@code y} component} <p>Valid input: any value. */
    int y();
    /** {@return the value of the {@code z} component} <p>Valid input: any value. */
    int z();
    /** {@return the value of the {@code w} component} <p>Valid input: any value. */
    int w();

    /**
     * Swizzle: copy ({@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 xx(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 xy(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 xz(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 xw(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 yx(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 yy(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 yz(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 yw(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 zx(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 zy(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 zz(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 zw(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 wx(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 wy(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 wz(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 ww(@Mutated Int2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xxx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xxy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xxz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xxw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xyx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xyy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xyz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xyw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xzx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xzy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xzz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xzw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xwx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xwy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xwz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 xww(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yxx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yxy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yxz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yxw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yyx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yyy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yyz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yyw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yzx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yzy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yzz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yzw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 ywx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 ywy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 ywz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 yww(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zxx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zxy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zxz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zxw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zyx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zyy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zyz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zyw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zzx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zzy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zzz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zzw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zwx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zwy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zwz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 zww(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wxx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wxy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wxz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wxw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wyx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wyy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wyz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wyw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wzx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wzy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wzz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wzw(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wwx(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wwy(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 wwz(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int3 www(@Mutated Int3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xxww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xywx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xywy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xywz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xyww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xzww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 xwww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yxww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yywx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yywy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yywz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yyww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 yzww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 ywww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zxww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zywx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zywy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zywz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zyww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zzww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 zwww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wxww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wywx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wywy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wywz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wyww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wzww(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwxx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwxy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwxz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwxw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwyx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwyy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwyz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwyw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwzx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwzy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwzz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwzw(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwwx(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwwy(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwwz(@Mutated Int4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 wwww(@Mutated Int4 dest);

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    default int[] store(@Mutated int[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    int[] store(@Mutated int[] dest, int offset);

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default IntBuffer store(@Mutated IntBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default IntBuffer storeAbsolute(@Mutated IntBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    IntBuffer storeAbsolute(int index, @Mutated IntBuffer dest);

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default IntBuffer storeRelative(@Mutated IntBuffer dest) {
        if (dest.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 4);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer store(@Mutated ByteBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeAbsolute(@Mutated ByteBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 16);
        return dest;
    }

    /**
     * Store the elements into the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    default MemorySegment store(@Mutated MemorySegment dest) { return store(0L, dest); }

    /**
     * Store the elements into the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    MemorySegment store(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    Int4 storeUnsafe(long address);

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    default long[] store(@Mutated long[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    long[] store(@Mutated long[] dest, int offset);

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default LongBuffer store(@Mutated LongBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default LongBuffer storeAbsolute(@Mutated LongBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    LongBuffer storeAbsolute(int index, @Mutated LongBuffer dest);

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default LongBuffer storeRelative(@Mutated LongBuffer dest) {
        if (dest.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 4);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeLong(@Mutated ByteBuffer dest) { return storeLongAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeLongAbsolute(@Mutated ByteBuffer dest) { return storeLongAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeLongAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeLongRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeLongAbsolute(pos, dest);
        dest.position(pos + 32);
        return dest;
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code long}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    default MemorySegment storeLong(@Mutated MemorySegment dest) { return storeLong(0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code long},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    MemorySegment storeLong(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code long}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     */
    Int4 storeLongUnsafe(long address);

    /** {@return whether all components of this vector are finite} Integer components always are, so this always returns {@code true}. <p>Valid input: any value. */
    boolean isFinite();

    /** {@return whether any component of this vector is NaN} Integer components never are, so this always returns {@code false}. <p>Valid input: any value. */
    boolean isNaN();

    /**
     * Compare this vector component-wise against {@code other}, allowing a difference
     * of at most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance:
     * the difference is widened to {@code long} before its magnitude is taken, so the two are
     * compared exactly without overflow, and a negative {@code epsilon} matches nothing.
     * <p>
     * Valid input: any value.
     *
     * @param other the vector to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    boolean equalsEpsilon(Int4R other, int epsilon);

    /**
     * Compare this vector with the given object for element-wise equality.
     * <p>
     * Each component is compared with {@code ==}. Use {@link #equalsEpsilon} for a tolerant
     * comparison.
     * <p>
     * Only instances of this library's implementation compare equal to each other; any other object
     * yields {@code false}.
     * <p>
     * Valid input: any value.
     *
     * @param obj the object to compare with
     * @return {@code true} if {@code obj} is a vector of this library with element-wise equal
     *        components, {@code false} otherwise
     */
    boolean equals(@org.jspecify.annotations.Nullable Object obj);

    /**
     * Compute a hash code consistent with {@link #equals}: it is derived from the components alone.
     * <p>
     * Valid input: any value.
     *
     * @return the hash code of this vector
     */
    int hashCode();

    /** Backs {@code ZERO()}: defers the shared instance's
     *  allocation to first use, avoiding a class-initialization cycle with the
     *  implementation class. Not part of the public API. */
    final class Holder {
        private Holder() {}
        static final Int4R ZERO = Joml.int4(0, 0, 0, 0);
    }
}
