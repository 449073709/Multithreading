package chapter2.demo1_threadsafe.factorizer;

import chapter2.demo1_threadsafe.base.Servlet;
import chapter2.demo1_threadsafe.base.ServletRequest;
import chapter2.demo1_threadsafe.base.ServletResponse;
import common.annotation.NotThreadSafe;

import java.math.BigInteger;

/**
 * 在没有同步的情况下统计已请求数量的Servlet（不要这么做）
 */
@NotThreadSafe
public class NotStatelessFactorizer implements Servlet {

    private long count = 0;

    public long getCount() {
        return count;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = getParm(resp);
        BigInteger[] factors = factor(i);
        ++count;//此操作是并非原子，是一个“读取-修改-写入”的操作序列。它被称为“竞态条件”
        writeInfoResponse(resp, factors);
    }

}
