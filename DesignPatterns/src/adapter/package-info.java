package adapter;
/**
 * Adapter（Wrapper）接口转换器模式：
 *      例如：
 *      java-io、
 *      JDBC-ODBC bridge（只是桥接，并非Bridge模式）、
 *      ASM transformer等等
 * 常见的Adapter反而不是严格的Adapter模式，比如WIndowAdapter、KeyAdapter，
 * 抽象类空实现所有接口方法，继承该抽象类选择性重写甚至不重写都可以！
 * 所以说上述两个只是方便编程，严格讲并不是真正的adapter模式。
 */