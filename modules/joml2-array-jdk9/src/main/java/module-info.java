// Descriptor for the scalar variants (joml2-fields / -array and their -jdk9 twins).
// jdk.unsupported is a hard requirement: the Unsafe store/load backend
// (org.joml2.internal.unsafe) is selected by default whenever it links, and a
// `requires static` left modular consumers - and jlink images - without it unless
// they passed --add-modules jdk.unsupported themselves.
module org.joml2 {
    exports org.joml2;
    exports org.joml2.ops;

    requires static org.jspecify;
    requires jdk.unsupported;
}
