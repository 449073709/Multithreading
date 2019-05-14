package common.annotation;

/**
 * 状态变量的线程安全由哪个对象来保护
 */
public @interface GuardedBy {

    String value();

}
