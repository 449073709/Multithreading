package chapter2.demo1_threadsafe;

/**
 * 如果内置锁不是可重入的，那么main方法执行时将发生死锁
 */
public class Widget implements Runnable {

    public synchronized void doSomething(int i) {
        System.out.println(Thread.currentThread() + "：" + i);
        if (i > 0) {
            doSomething(--i);
        }
    }

    @Override
    public void run() {
        doSomething(5);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Widget());
        thread.start();
    }

}
