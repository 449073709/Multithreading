package chapter2.demo1_threadsafe.factorizer;

import chapter2.demo1_threadsafe.base.Servlet;
import chapter2.demo1_threadsafe.base.ServletRequest;
import chapter2.demo1_threadsafe.base.ServletResponse;
import common.annotation.GuardedBy;
import common.annotation.ThreadSafe;

import java.math.BigInteger;

/**
 * 这个Servlet能正确缓存最新计算结果，但并发性却非常糟糕（不要这么做）
 */
@ThreadSafe
public class SynchronizedFactorizer implements Servlet {

    @GuardedBy("this")
    private BigInteger lastNumber;
    @GuardedBy("this")
    private BigInteger[] lastFactors;


    /**
     * 加了同步锁
     */
    public synchronized void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = getParm(resp);
        if (i.equals(lastNumber)) {
            writeInfoResponse(resp, lastFactors);
        } else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            writeInfoResponse(resp, factors);
        }
    }
}
