package io.gushizhao.design.creationmode.singleton;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/31 16:40
 *
 *创建型模式
 * 单例模式
 *
 * 懒汉模式（双重锁同步锁单例模式）单例实例在第一次使用的时候进行创建，这个类是线程安全的，使
 * 用的是 volatile + 双重检测机制来禁止指令重排达到线程安全
 *
 */
public class SingletonExample5 {

    private SingletonExample5(){}
    // 单例 volatile + 双重检测机制来禁止重排
    private volatile static SingletonExample5 instance = null;


    public static SingletonExample5 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample5.class) {
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }


}
