package io.gushizhao.basecase.lab03;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 14:23
 *
 * 看几个单例对象的示例代码，其中有些代码是线程安全的，有些则不是线程安全的，
 *
 * 懒汉模式(双重锁同步锁单例模式) 但是线程不安全
 * 单例实例在第一次使用的时候进行创建
 */
public class SingletonExample04 {

    private SingletonExample04() {}

    private static SingletonExample04 instance = null;

    public static SingletonExample04 getInstance() {
        if (instance == null) {
            synchronized(SingletonExample04.class) {
                if (instance == null) {
                    instance = new SingletonExample04();
                }
            }
        }
        return instance;
    }
    /**
     * 线程不安全分析如下
     * 当执行instance = new SingletonExample4();这行代码时，CPU会执行如下指令：
     *  1.memory = allocate() 分配对象的内存空间
     *  2.ctorInstance() 初始化对象
     *  3.instance = memory 设置instance指向刚分配的内存
     *
     *  单纯执行以上三步没啥问题，但是在多线程情况下，可能会发生指令重排序。
     * 指令重排序对单线程没有影响，单线程下CPU可以按照顺序执行以上三个步骤，但是在多线程下，如果发生了指令重排序，则会
     * 打乱上面的三个步骤。
     * 如果发生了JVM和CPU优化，发生重排序时，可能会按照下面的顺序执行：
     *  1.memory = allocate() 分配对象的内存空间
     *  3.instance = memory 设置instance指向刚分配的内存
     *  2.ctorInstance() 初始化对象
     *
     *  假设目前有两个线程A和B同时执行getInstance()方法，A线程执行到instance = new SingletonExample4(); B线程刚执行到第一
     * 个 if (instance == null){处，如果按照1.3.2的顺序，假设线程A执行到3.instance = memory 设置instance指向刚分配的内存，此
     * 时，线程B判断instance已经有值，就会直接return instance;而实际上，线程A还未执行2.ctorInstance() 初始化对象，也就是说
     * 线程B拿到的instance对象还未进行初始化，这个未初始化的instance对象一旦被线程B使用，就会出现问题。
     */
}
