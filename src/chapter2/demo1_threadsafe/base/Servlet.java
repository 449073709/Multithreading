package chapter2.demo1_threadsafe.base;

import java.math.BigInteger;

/**
 * 模拟Servlet接口
 */
public interface Servlet {

    /**
     * @param req 线程安全
     * @param resp 线程安全
     */
    void service(ServletRequest req, ServletResponse resp);

    /**
     * 写入Response
     */
    default void writeInfoResponse(ServletResponse resp, BigInteger[] factors) {

    }

    /**
     * 因式分解
     */
    default BigInteger[] factor(BigInteger i) {
        return null;
    }

    /**
     * 从ServletResponse获取值
     */
    default BigInteger getParm(ServletResponse resp) {
        return new BigInteger("0");
    }

}
