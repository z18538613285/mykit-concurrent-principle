package io.gushizhao.concurrent.lab10;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 10:05
 *
 * @tips 这里需要注意的是：每个线程的本地变量不是存放在ThreadLocal实例里面的，而是存放在调用线程的threadLocals变量里面的。
 * 也就是说，调用ThreadLocal的set()方法存储的本地变量是存放在具体线程的内存空间中的，而ThreadLocal类只是提供了
 * set()和get()方法来存储和读取本地变量的值，当调用ThreadLocal类的set()方法时，把要存储的值放入调用线程的threadLocals
 * 中存储起来，当调用ThreadLocal类的get()方法时，从当前线程的threadLocals变量中将存储的值取出来。
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 创建第一个线程
        Thread threadA = new Thread(() -> {
            threadLocal.set("ThreadA:" + Thread.currentThread().getName());
            System.out.println("线程A本地变量中的值：" + threadLocal.get());
            threadLocal.remove();
            System.out.println("线程A删除本地变量后 ThreadLocal中的值：" + threadLocal.get());

        });
        // 创建第二个线程
        Thread threadB = new Thread(() -> {
            threadLocal.set("ThreadB:" + Thread.currentThread().getName());
            System.out.println("线程B本地变量中的值：" + threadLocal.get());
            System.out.println("线程B吗没有删除本地变量：" + threadLocal.get());
        });
        // 启动线程A B
        threadA.start();
        threadB.start();

    }
}
