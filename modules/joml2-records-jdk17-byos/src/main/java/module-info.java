// Descriptor for the -byos artifacts of the variants with the SIMD flavour of the
// bring-your-own-storage *Ops (joml2-simd-byos / -records-byos / -records-jdk17-byos
// / -value-byos): their SIMD cells (org.joml2.internal.opssimd) use the Vector API
// behind SimdSupport.VECTOR_API with scalar fallbacks, so jdk.incubator.vector is
// optional. They run on the core module org.joml2 - its Joml configuration and
// Math, the Handedness / DepthRange / FrustumPlane / FrustumCorner parameters of
// their public signatures (hence transitive), its Unsafe access and SIMD support,
// which it exports to this module alone.
module org.joml2.byos {
    exports org.joml2.ops;

    requires transitive org.joml2;
    requires static org.jspecify;
    requires static jdk.incubator.vector;
}
