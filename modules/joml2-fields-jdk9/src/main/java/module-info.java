// Descriptor for the scalar variants (joml2-fields and its -jdk9 twin).
// jdk.unsupported is a hard requirement: the Unsafe store/load backend
// (org.joml2.internal.unsafe) is selected by default whenever it links, and a
// `requires static` left modular consumers - and jlink images - without it unless
// they passed --add-modules jdk.unsupported themselves. The bring-your-own-storage
// *Ops are module org.joml2.byos (joml2-<variant>-byos), whose raw-storage kernels
// run on this module's Unsafe access: exported to it alone.
module org.joml2 {
    exports org.joml2;
    exports org.joml2.internal.unsafe to org.joml2.byos;

    requires static org.jspecify;
    requires jdk.unsupported;
}
