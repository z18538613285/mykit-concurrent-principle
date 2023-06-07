package io.gushizhao.basecase.lab02;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 14:09
 *
 * 当我们在调用Java对象的wait()方法或者线程的sleep()方法时，需要捕获并处理InterruptedException异常。如果我们对
 * InterruptedException异常处理不当，则会发生我们意想不到的后果！
 *
 */
public class InterruptedTask implements Runnable{
    /**
     * 通过isInterrupted()方法检查线程是否被中断了，如果中断了就退出while循环。其他线程通过调用执行线程的
     * interrupt()方法来中断执行线程，此时会设置执行线程的中断标志位，从而使currentThread.isInterrupted()返回true，这样就能
     * 够退出while循环。
     * 这看上去没啥问题啊！但真的是这样吗？
     *
     * 处理InterruptedException异常时要小心，如果在调用执行线程的interrupt()方法中断执行线程时，抛出了
     * InterruptedException异常，则在触发InterruptedException异常的同时，JVM会同时把执行线程的中断标志位清除，此时调
     * 用执行线程的isInterrupted()方法时，会返回false。此时，正确的处理方式是在执行线程的run()方法中捕获到
     * InterruptedException异常，并重新设置中断标志位（也就是在捕获InterruptedException异常的catch代码块中，重新调用
     * 当前线程的interrupt()方法）。
     */
    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        while (true){
            if(currentThread.isInterrupted()){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                /**
                 * 正确的处理方式应该是在InterruptedTask类中的run()方法中的while(true)循环中捕获异常之后重新设置中断标志位，
                 */
                currentThread.interrupt();
            }
        }
    }
}
