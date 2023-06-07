package io.gushizhao.basecase.lab08;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/24 14:08
 *
 * synchronized 原理
 *通
 * 过反编译下面的代码来看看synchronized是如何实现对代码块进行同步的
 *  public void method();
 *     Code:
 *        0: aload_0
 *        1: dup
 *        2: astore_1
 *        3: monitorenter
 *        4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        7: ldc           #3                  // String Method 1 start
 *        9: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *       12: aload_1
 *       13: monitorexit
 *       14: goto          22
 *       17: astore_2
 *       18: aload_1
 *       19: monitorexit
 *       20: aload_2
 *       21: athrow
 *       22: return
 *
 *       3: monitorenter
 *       13: monitorexit
 * 关于这两条指令的作用，我们直接参考JVM规范中描述：
 * monitorenter ：
 * 每个对象有一个监视器锁（monitor）。当monitor被占用时就会处于锁定状态，线程执行monitorenter指令时尝试获取
 * monitor的所有权，过程如下：
 * 1、如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者。
 * 2、如果线程已经占有该monitor，只是重新进入，则进入monitor的进入数加1.
 * 3.如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权。
 *
 * monitorexit：
 * 执行monitorexit的线程必须是objectref所对应的monitor的所有者。
 * 指令执行时，monitor的进入数减1，如果减1后进入数为0，那线程退出monitor，不再是这个monitor的所有者。其他被这个
 * monitor阻塞的线程可以尝试去获取这个 monitor 的所有权。
 *
 * 通过这两段描述，我们应该能很清楚的看出synchronized的实现原理，synchronized的语义底层是通过一个monitor的对象来完
 * 成，其实wait/notify等方法也依赖于monitor对象，这就是为什么只有在同步的块或者方法中才能调用wait/notify等方法，否则
 * 会抛出java.lang.IllegalMonitorStateException的异常的原因。
 */
public class SynchronizedDemo {
    //public void method() {
    //    synchronized (this) {
    //        System.out.println("Method 1 start");
    //    }
    //}

    /**
     * 看一下同步方法的反编译结果：
     *  public synchronized void method2();
     *     Code:
     *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *        3: ldc           #3                  // String Hello world!
     *        5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *        8: return
     *
     * 从反编译的结果来看，方法的同步并没有通过指令monitorenter和monitorexit来完成（理论上其实也可以通过这两条指令来实
     * 现），不过相对于普通方法，其常量池中多了ACC_SYNCHRONIZED标示符。JVM就是根据该标示符来实现方法的同步的：当方
     * 法调用时，调用指令将会检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置，如果设置了，执行线程将先获取monitor，获
     * 取成功之后才能执行方法体，方法执行完后再释放monitor。在方法执行期间，其他任何线程都无法再获得同一个monitor对
     * 象。 其实本质上没有区别，只是方法的同步是一种隐式的方式来实现，无需通过字节码来完成。
     *
     */
    public synchronized void method2() {
        System.out.println("Hello world!");
    }

    /**
     * 在使用synchronized关键字加锁时，Java规定了一些隐式的加锁规则。
     *
     * 当使用synchronized关键字修饰代码块时，锁定的是实际传入的对象。
     * 当使用synchronized关键字修饰非静态方法时，锁定的是当前实例对象this。
     * 当使用synchronized关键字修饰静态方法时，锁定的是当前类的Class对象。
     */
}
