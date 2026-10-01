// Descriptor for the standalone immutable variants (joml2-records / -records-jdk17
// / -value): their math types are scalar, only the bundled *Ops kernels use the
// Vector API - guarded by SimdSupport.VECTOR_API with scalar fallbacks - so
// jdk.incubator.vector stays optional. jdk.unsupported is required for the
// Unsafe store/load backend (see module-info-default).
module org.joml2 {
    exports org.joml2;
    exports org.joml2.ops;

    requires static org.jspecify;
    requires jdk.unsupported;
    requires static jdk.incubator.vector;
}
