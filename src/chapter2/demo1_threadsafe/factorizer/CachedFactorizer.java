package chapter2.demo1_threadsafe.factorizer;

import chapter2.demo1_threadsafe.base.Servlet;
import chapter2.demo1_threadsafe.base.ServletRequest;
import chapter2.demo1_threadsafe.base.ServletResponse;
import common.annotation.ThreadSafe;

import java.math.BigInteger;

/**
 * @author kailin
 */
@ThreadSafe
public class CachedFactorizer implements Servlet {

    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = getParm(resp);
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        synchronized (this) {
            if (factors == null) {
                factors = factor(i);
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        writeInfoResponse(resp, factors);
    }
}
