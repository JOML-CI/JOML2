/**
 * Kotlin operator/infix/destructuring/indexing extensions over the immutable
 * JOML 2 types. Requires the backing variant (joml2-records or joml2-value,
 * both module org.joml2) transitively: every extension signature names its types.
 */
module org.joml2.kotlin {
    requires transitive org.joml2;
    requires kotlin.stdlib;
    exports org.joml2.kotlin;
}
