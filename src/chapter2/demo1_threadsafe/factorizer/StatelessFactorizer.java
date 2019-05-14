package chapter2.demo1_threadsafe.factorizer;

import chapter2.demo1_threadsafe.base.Servlet;
import chapter2.demo1_threadsafe.base.ServletRequest;
import chapter2.demo1_threadsafe.base.ServletResponse;
import common.annotation.ThreadSafe;

import java.math.BigInteger;

/**
 * 无状态对象一定是线程安全的。
 *
 * eg：
 * 无状态就是一次操作，不能保存数据。无状态对象(Stateless Bean)，就是没有实例变量的对象 .
 * 不能保存数据，是不变类，是线程安全的。
 *
 * 有状态就是有数据存储功能。有状态对象(Stateful Bean)，就是有实例变量的对象 ，可以保存数据，是非线程安全的。
 * 在不同方法调用间不保留任何状态。
 */
@ThreadSafe
public class StatelessFactorizer implements Servlet {

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = getParm(resp);
        BigInteger[] factors = factor(i);
        writeInfoResponse(resp, factors);
    }

}
