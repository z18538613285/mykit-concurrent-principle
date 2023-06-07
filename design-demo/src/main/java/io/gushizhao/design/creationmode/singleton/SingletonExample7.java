package io.gushizhao.design.creationmode.singleton;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/31 16:40
 *
 *创建型模式
 * 单例模式
 *
 * 枚举方式进行实例化，是线程安全的，此种方式也是线程最安全的
 *
 */
public class SingletonExample7 {

    private SingletonExample7(){}

    private static SingletonExample7 instance = null;

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM 保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}
