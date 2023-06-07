package io.gushizhao.basecase.lab03;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 14:23
 *
 * 看几个单例对象的示例代码，其中有些代码是线程安全的，有些则不是线程安全的，
 *
 * 懒汉模式 线程不安全
 */
public class SingletonExample01 {

    private SingletonExample01() {}

    private static SingletonExample01 instance = null;

    public static SingletonExample01 getInstance() {
        // 多个线程同时调用，可能会创建多个对象
        if (instance == null) {
            instance = new SingletonExample01();
        }
        return instance;
    }
}
