package io.gushizhao.basecase.lab03;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 14:23
 *
 * 看几个单例对象的示例代码，其中有些代码是线程安全的，有些则不是线程安全的，
 *
 * 懒汉模式 线程安全 ，
 * 单例实例在第一次使用的时候进行创建，但是这个写法不推荐
 */
public class SingletonExample03 {

    private SingletonExample03() {}

    private static SingletonExample03 instance = null;

    public static synchronized SingletonExample03 getInstance() {
        if (instance == null) {
            instance = new SingletonExample03();
        }
        return instance;
    }
}
