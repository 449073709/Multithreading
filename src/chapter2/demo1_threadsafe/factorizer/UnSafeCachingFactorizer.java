package chapter2.demo1_threadsafe.factorizer;

import chapter2.demo1_threadsafe.base.Servlet;
import chapter2.demo1_threadsafe.base.ServletRequest;
import chapter2.demo1_threadsafe.base.ServletResponse;
import common.annotation.NotThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 所有的状态变量引用都是线程安全的，但在该类中存在着竞态条件，这可能产生错误的结果
 *
 * eg：本例为延迟初始化中的竞态条件
 */
@NotThreadSafe
public class UnSafeCachingFactorizer implements Servlet {

    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = getParm(resp);
        if (i.equals(lastNumber.get())) {
            writeInfoResponse(resp, lastFactors.get());
        } else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            writeInfoResponse(resp, factors);
        }
    }
}
