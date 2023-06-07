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
public class SynchronizedTest03 {

    // 2、静态方法（类）同步
    // 对静态方法的同步本质上是对类的同步（静态方法本质上是属于类的方法，而不是对象上的方法），所以即使
    //test和test2属于不同的对象，但是它们都属于SynchronizedTest类的实例，所以也只能顺序的执行method1和method2，不能
    //并发执行。
    public static synchronized void method1() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public static synchronized void method2() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedTest03 test01 = new SynchronizedTest03();
        final SynchronizedTest03 test02 = new SynchronizedTest03();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.method1();
            }
        }).start();
        new Thread(() -> test02.method2()).start();

    }
}
