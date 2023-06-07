package io.gushizhao.basecase.lab03;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 14:23
 *
 * 看几个单例对象的示例代码，其中有些代码是线程安全的，有些则不是线程安全的，
 *
 * 懒汉模式(双重锁同步锁单例模式) 线程安全 使用的是 volatile + 双重检测机制来禁止指令重排达到线程安全
 * 单例实例在第一次使用的时候进行创建
 */
public class SingletonExample05 {

    private SingletonExample05() {}

    //单例对象 volatile + 双重检测机制来禁止指令重排
    private volatile static SingletonExample05 instance = null;

    public static SingletonExample05 getInstance() {
        if (instance == null) {
            synchronized(SingletonExample05.class) {
                if (instance == null) {
                    instance = new SingletonExample05();
                }
            }
        }
        return instance;
    }

}
