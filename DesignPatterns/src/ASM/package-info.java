package ASM;
/**
 * ASM is an all purpose Java bytecode manipulation and analysis framework. It can be used to modify existing classes or to dynamically generate classes, directly in binary form. ASM provides some common bytecode transformations and analysis algorithms from which custom complex transformations and code analysis tools can be built. ASM offers similar functionality as other Java bytecode frameworks, but is focused on performance. Because it was designed and implemented to be as small and as fast as possible, it is well suited for use in dynamic systems (but can of course be used in a static way too, e.g. in compilers).
 *
 * ASM is used in many projects, including:
 *
 * the OpenJDK, to generate the lambda call sites, and also in the Nashorn compiler,
 * the Groovy compiler and the Kotlin compiler,
 * Cobertura and Jacoco, to instrument classes in order to measure code coverage,
 * CGLIB, to dynamically generate proxy classes (which are used in other projects such as Mockito and EasyMock),
 * Gradle, to generate some classes at runtime.
 *
 * ASM是一个通用的Java编解码操作和分析框架。它可用于修改现有类或动态生成类，直接以二进制形式生成。ASM 提供了一些常见的字典转换和分析算法，可以从中构建自定义复杂转换和代码分析工具。ASM 提供与其他 Java 子码框架类似的功能，但专注于性能。由于它被设计和实现为尽可能小和尽可能快，它非常适合在动态系统中使用（但当然也可以以静态的方式使用，例如在编译器中）。
 *
 * ASM 用于许多项目，包括：
 *
 * OpenJDK，生成 lambda呼叫站点，也在 Nashorn编译器 中，
 * Groovy编译器 和 Kotlin编译器，
 * Cobertura和Jacoco，仪器类，以衡量代码覆盖，
 * CGLIB，动态生成代理类（用于其他项目，如Mockito和EasyMock），
 * Gradle，在运行时间生成一些类。
 *
 * 【注意】由于需要asm依赖，所以我把这个visitor设计模式相关的框架demo置于HelloSpring模块中（gradle管理依赖）ASM包里面
 */