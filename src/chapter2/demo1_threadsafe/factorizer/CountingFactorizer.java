package chapter2.demo1_threadsafe.factorizer;

import chapter2.demo1_threadsafe.base.Servlet;
import chapter2.demo1_threadsafe.base.ServletRequest;
import chapter2.demo1_threadsafe.base.ServletResponse;
import common.annotation.ThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class CountingFactorizer implements Servlet {

    private final AtomicLong count = new AtomicLong(0);

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = getParm(resp);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();//线程安全的计数器
        writeInfoResponse(resp, factors);
    }

}
