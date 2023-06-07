package io.gushizhao.basecase.lab03;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 14:23
 *
 * 看几个单例对象的示例代码，其中有些代码是线程安全的，有些则不是线程安全的，
 *
 * 枚举方式实例化 线程安全
 * 单例实例在类装载的时候进行创建(使用静态代码块)
 */
public class SingletonExample07 {

    private SingletonExample07() {}

    private static SingletonExample07 instance = null;

    static {
        instance = new SingletonExample07();
    }

    public static SingletonExample07 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample07 singleton;

        // JVM 保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample07();
        }
        public SingletonExample07 getSingleton() {
            return singleton;
        }
    }
}
