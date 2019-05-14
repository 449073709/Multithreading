package chapter2.demo1_threadsafe;

import chapter2.demo1_threadsafe.base.ExpensiveObject;
import common.annotation.NotThreadSafe;

/**
 * 延迟初始化中的竞态条件。“先检查后执行”就是延迟初始化。
 */
@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }

}
