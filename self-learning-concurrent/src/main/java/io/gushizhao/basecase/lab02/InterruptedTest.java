package io.gushizhao.basecase.lab02;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 14:11
 */
public class InterruptedTest {

    public static void main(String[] args) {
        InterruptedTask interruptedTask = new InterruptedTask();
        Thread interruptedThread = new Thread(interruptedTask);
        interruptedThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        interruptedThread.interrupt();

        /**
         * java.lang.InterruptedException: sleep interrupted
         * 	at java.lang.Thread.sleep(Native Method)
         * 	at io.gushizhao.basecase.lab02.InterruptedTask.run(InterruptedTask.java:27)
         * 	at java.lang.Thread.run(Thread.java:748)
         *
         * 	这竟然跟我们想象的不一样！不一样！不一样！这是为什么呢？
         *
         * 	原因是线程的run()方法在执行的时候，大部
         * 分时间都是阻塞在sleep(100)上，当其他线程通过调用执行线程的interrupt()方法来中断执行线程时，大概率的会触发
         * InterruptedException异常，
         *
         * 在触发InterruptedException异常的同时，JVM会同时把线程的中断标志位清除，所以，这个时
         * 候在run()方法中判断的currentThread.isInterrupted()会返回false，也就不会退出当前while循环了。
         */
    }
}
