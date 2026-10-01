// Descriptor for the standalone immutable variants (joml2-records / -records-jdk17
// / -value): their math types are scalar; only the Vector API probe behind
// Joml.VECTOR_API uses jdk.incubator.vector, so it stays optional. The SIMD *Ops
// kernels that run on it are module org.joml2.byos (joml2-<variant>-byos) - guarded
// by SimdSupport.VECTOR_API with scalar fallbacks - and use this module's Unsafe
// access and SIMD support: exported to it alone. jdk.unsupported is required for
// the Unsafe store/load backend (see module-info-default).
module org.joml2 {
    exports org.joml2;
    exports org.joml2.internal.unsafe to org.joml2.byos;
    exports org.joml2.internal.simd to org.joml2.byos;

    requires static org.jspecify;
    requires jdk.unsupported;
    requires static jdk.incubator.vector;
}
