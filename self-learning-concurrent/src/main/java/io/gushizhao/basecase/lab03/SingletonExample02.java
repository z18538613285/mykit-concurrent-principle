package io.gushizhao.basecase.lab03;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 14:23
 *
 * 看几个单例对象的示例代码，其中有些代码是线程安全的，有些则不是线程安全的，
 *
 * 饿汉模式 线程安全
 * 单例实例在类装载的时候进行创建
 */
public class SingletonExample02 {

    private SingletonExample02() {}

    private static SingletonExample02 instance = new SingletonExample02();

    public static SingletonExample02 getInstance() {
        return instance;
    }
}
