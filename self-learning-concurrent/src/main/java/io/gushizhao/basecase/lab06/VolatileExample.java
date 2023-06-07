package io.gushizhao.basecase.lab06;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/24 10:04
 *
 *
 * 我们可以这样理解Java的内存模型：
 * Java内存模型规范了Java虚拟机（JVM）如何提供按需禁用缓存和编译优化的方法。
 *
 * 说的具体一些，这些方法包括：volatile、synchronized和final关键字，以及Java内存模型中的Happens-Before规则。
 *
 * 1、volatile为何能保证线程间可见？
 * volatile关键字不是Java特有的，在C语言中也存在volatile关键字，这个关键字最原始的意义就是禁用CPU缓存。
 * 此时，Java对这个变量的读写，不能使用CPU缓存，必须从内存中读取和写入。
 *
 * Java内存模式的底层实现
 * 主要是通过内存屏障(memory barrier)禁止重排序的， 即时编译器根据具体的底层体系架构， 将这些内存屏障替换成具体的
 * CPU 指令。 对于编译器而言，内存屏障将限制它所能做的重排序优化。 而对于处理器而言， 内存屏障将会导致缓存的刷新操
 * 作。 比如， 对于volatile， 编译器将在volatile字段的读写操作前后各插入一些内存屏障。
 *
 */
public class VolatileExample {

    int x = 0;
    volatile boolean v = false;

    public void writer() {
        x = 1;
        v = true;
    }
    public void reader() {
        if (v == true) {
            //x的值是多少呢？

            /**
             * 这里，假设线程A执行writer()方法，按照volatile会将v=true写入内存；线程B执行reader()方法，按照volatile，线程B会从内存
             * 中读取变量v，如果线程B读取到的变量v为true，那么，此时的变量x的值是多少呢？？
             * 这个示例程序给人的直觉就是x的值为1，其实，x的值具体是多少和JDK的版本有关，如果使用的JDK版本低于1.5，则x的值可能
             * 为1，也可能为0。如果使用1.5及1.5以上版本的JDK，则x的值就是1。
             * 看到这个，就会有人提出问题了？这是为什么呢？其实，答案就是在JDK1.5版本中的Java内存模型中引入了Happens-Before原
             * 则。
             *
             */

            /**
             * Happens-Before原则
             * 【原则一】程序次序规则
             * 在一个线程中，按照代码的顺序，前面的操作Happens-Before于后面的任意操作。
             * 例如【示例一】中的程序x=1会在v=true之前执行。这个规则比较符合单线程的思维：在同一个线程中，程序在前面对某个变量
             * 的修改一定是对后续操作可见的。
             *
             * 【原则二】volatile变量规则
             * 对一个volatile变量的写操作，Happens-Before于后续对这个变量的读操作。
             * 也就是说，对一个使用了volatile变量的写操作，先行发生于后面对这个变量的读操作。这个需要大家重点理解。
             *
             * 【原则三】传递规则
             * 如果A Happens-Before B，并且B Happens-Before C，则A Happens-Before C。
             * 我们结合【原则一】、【原则二】和【原则三】再来看【示例一】程序，此时，我们可以得出如下结论：
             * （1）x = 1 Happens-Before 写变量v = true，符合【原则一】程序次序规则。
             * （2）写变量v = true Happens-Before 读变量v = true，符合【原则二】volatile变量规则。
             * 再根据【原则三】传递规则，我们可以得出结论：x = 1 Happens-Before 读变量v=true。
             * 也就是说，如果线程B读取到了v=true，那么，线程A设置的x = 1对线程B就是可见的。换句话说，就是此时的线程B能够访问到
             * x=1。
             * 其实，Java 1.5版本的 java.util.concurrent并发工具就是靠volatile语义来实现可见性的。
             *
             * 【原则四】锁定规则
             * 对一个锁的解锁操作 Happens-Before于后续对这个锁的加锁操作。
             *
             * 【原则五】线程启动规则
             * 如果线程A调用线程B的start()方法来启动线程B，则start()操作Happens-Before于线程B中的任意操作。
             *
             * 【原则六】线程终结规则
             * 线程A等待线程B完成（在线程A中调用线程B的join()方法实现），当线程B完成后（线程A调用线程B的join()方法返回），则线
             * 程A能够访问到线程B对共享变量的操作。
             *
             * 【原则七】线程中断规则
             * 对线程interrupt()方法的调用Happens-Before于被中断线程的代码检测到中断事件的发生。
             *
             * 【原则八】对象终结原则
             * 一个对象的初始化完成Happens-Before于它的finalize()方法的开始。
             *
             *
             */

            /**
             * 再说final关键字
             * 使用final关键字修饰的变量，是不会被改变的。但是在Java 1.5之前的版本中，使用final修饰的变量也会出现错误的情况，在Java
             * 1.5版本之后，Java内存模型对使用final关键字修饰的变量的重排序进行了一定的约束。只要我们能够提供正确的构造函数就不会
             * 出现问题。
             * 例如，下面的程序代码，在构造函数中将this赋值给了全局变量global.obj，此时对象初始化还没有完成，此时对象初始化还没有
             * 完成，此时对象初始化还没有完成，重要的事情说三遍！！线程通过global.obj读取的x值可能为0。
             *
             * final x = 0;
             * public FinalFieldExample() { // bad!
             * x = 3;
             * y = 4;
             * // bad construction - allowing this to escape
             * global.obj = this;
             * }
             */
        }
    }
}
