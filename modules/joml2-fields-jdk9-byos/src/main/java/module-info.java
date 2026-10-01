// Descriptor for the -byos artifacts of the scalar variants (joml2-fields-byos /
// -fields-jdk9-byos): the bring-your-own-storage *Ops over caller-owned float[] /
// NIO buffers / MemorySegment / native addresses, with their per-backing kernels.
// They run on the core module org.joml2 - its Joml configuration and Math, the
// Handedness / DepthRange / FrustumPlane / FrustumCorner parameters of their
// public signatures (hence transitive) and its Unsafe access, which it exports to
// this module alone.
module org.joml2.byos {
    exports org.joml2.ops;

    requires transitive org.joml2;
    requires static org.jspecify;
}
