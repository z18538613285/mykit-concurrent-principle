package io.gushizhao.concurrent.lab11;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 10:26
 *
 * ThreadLocal变量不具有传递性
 *
 * 使用ThreadLocal存储本地变量不具有传递性，也就是说，同一个ThreadLocal在父线程中设置值后，在子线程中是无法获
 * 取到这个值的，这个现象说明ThreadLocal中存储的本地变量不具有传递性。
 *
 * 那有没有办法在子线程
 * 中获取到主线程设置的值呢？此时，我们可以使用InheritableThreadLocal来解决这个问题
 *
 *
 *
 * stop()方法
 * stop()方法会真的杀死线程。如果线程持有ReentrantLock锁，被stop()的线程并不会自动调用ReentrantLock的unlock()去释放
 * 锁，那其他线程就再也没机会获得ReentrantLock锁， 这样其他线程就再也不能执行ReentrantLock锁锁住的代码逻辑。 所以该
 * 方法就不建议使用了， 类似的方法还有suspend()和resume()方法， 这两个方法同样也都不建议使用了， 所以这里也就不多介绍
 * 了。
 *
 *
 * interrupt()方法
 * interrupt()方法仅仅是通知线程，线程有机会执行一些后续操作，同时也可以无视这个通知。被interrupt的线程，有两种方式接
 * 收通知：一种是异常， 另一种是主动检测。
 *
 *
 * 通过异常接收通知
 *
 * 当线程A处于WAITING、 TIMED_WAITING状态时， 如果其他线程调用线程A的interrupt()方法，则会使线程A返回到RUNNABLE
 * 状态，同时线程A的代码会触发InterruptedException异常。线程转换到WAITING、TIMED_WAITING状态的触发条件，都是调用
 * 了类似wait()、join()、sleep()这样的方法， 我们看这些方法的签名时，发现都会throws InterruptedException这个异常。这个
 * 异常的触发条件就是：其他线程调用了该线程的interrupt()方法。
 * 当线程A处于RUNNABLE状态时，并且阻塞在java.nio.channels.InterruptibleChannel上时， 如果其他线程调用线程A的
 * interrupt()方法，线程A会触发java.nio.channels.ClosedByInterruptException这个异常；当阻塞在java.nio.channels.Selector
 * 上时，如果其他线程调用线程A的interrupt()方法，线程A的java.nio.channels.Selector会立即返回。
 *
 * 主动检测通知
 *
 * 如果线程处于RUNNABLE状态，并且没有阻塞在某个I/O操作上，例如中断计算基因组序列的线程A，此时就得依赖线程A主动检
 * 测中断状态了。如果其他线程调用线程A的interrupt()方法， 那么线程A可以通过isInterrupted()方法， 来检测自己是不是被中断
 * 了。
 */
public class ThreadLocalTest {

    //private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    // InheritableThreadLocal类继承自ThreadLocal类，它能够让子线程访问到在父线程中设置的本地变量的值
    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        // 在主线程设置值
        threadLocal.set("ThreadLocalTest");
        // 在子线程获取值
        Thread thread = new Thread(() -> {
            System.out.println("子线程获取值：" + threadLocal.get());
        });
        // 启动子线程
        thread.start();
        // 在主线程获取值
        System.out.println("主线程获取值：" + threadLocal.get());


    }
}
