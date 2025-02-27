package io.gushizhao.design.creationmode.singleton;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/31 16:40
 *
 *创建型模式
 * 单例模式
 *
 * 懒汉模式，单例实例在第一次使用的时候进行创建，这个类是线程不安全的
 */
public class SingletonExample1 {

    private SingletonExample1(){}
    private static SingletonExample1 instance = null;

    public static SingletonExample1 getInstance() {
        // 多个线程同时调用，可能会创建多个对象
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }


}
