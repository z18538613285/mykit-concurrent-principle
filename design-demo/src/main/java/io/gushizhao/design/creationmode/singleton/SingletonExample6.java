package io.gushizhao.design.creationmode.singleton;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/31 16:40
 *
 *创建型模式
 * 单例模式
 *
 * 饿汉模式，单例实例在类装载的时候（使用静态代码块）进行创建，是线程安全的
 *
 */
public class SingletonExample6 {

    private SingletonExample6(){}

    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    public static SingletonExample6 getInstance() {
        return instance;
    }


}
