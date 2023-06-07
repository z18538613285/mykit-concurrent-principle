package io.gushizhao.basecase.lab07;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/24 10:33
 *
 * synchronized的基本使用
 * synchronized是Java中解决并发问题的一种最常用的方法，也是最简单的一种方法。synchronized的作用主要有三个：
 * （1）确保线程互斥的访问同步代码。
 * （2）保证共享变量的修改能够及时可见。
 * （3）有效解决重排序问题。
 * 从语法上讲，synchronized总共有三种用法：
 * （1）修饰普通方法。
 * （2）修饰静态方法。
 * （3）修饰代码块。
 */
public class SynchronizedTest04 {

    // 2、代码块同步
    // 虽然线程1和线程2都进入了对应的方法开始执行，但是线程2在进入同步块之前，需要等待线程1中同步块执行完成。
    public void method1() {
        System.out.println("Method 1 start");
        try {
            synchronized (this) {
                System.out.println("Method 1 execute");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public void method2() {
        System.out.println("Method 2 start");
        try {
            synchronized (this) {
                System.out.println("Method 2 execute");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedTest04 test = new SynchronizedTest04();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();
        new Thread(() -> test.method2()).start();

    }
}
