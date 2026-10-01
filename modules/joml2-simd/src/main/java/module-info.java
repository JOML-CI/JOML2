// Descriptor for joml2-simd only. Its math types (org.joml2.internal.types.*Impl)
// use jdk.incubator.vector directly with no scalar fallback, so the module is a
// hard requirement: on the module path it resolves automatically, on the
// classpath consumers pass --add-modules jdk.incubator.vector. The variants with
// a real scalar fallback use module-info-simd-optional instead.
module org.joml2 {
    exports org.joml2;
    exports org.joml2.ops;

    requires static org.jspecify;
    requires jdk.unsupported;
    requires jdk.incubator.vector;
}
