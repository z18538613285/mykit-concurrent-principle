package io.gushizhao.basecase.lab03;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 14:20
 *
 * 对象溢出示例代码
 * 其中，内部类InnerClass的构造方法中包含了对封装实例Escape的隐含的引用(体现在InnerClass的构造方法中引用了
 * Escape.this)，在对象没有被正确构造完成之前，就会被发布，有可能存在不安全的因素。
 *
 * 一个导致this在构造期间溢出的错误：在构造函数中，启动一个线程，无论是隐式的启动还是显式的启动，都会造成this引用的
 * 溢出（因为新线程总是在所属对象构造完毕之前就已经看到this引用了）。所以，如果要在构造函数中创建线程，则不要在构造
 * 函数中启动线程，可以使用一个专有的start()方法或者一个初始化方法，来统一启动线程，可以采用工厂方法和私有构造函数来
 * 完成对象的创建和监听器的注册，之后统一启动线程，来避免溢出。
 */
public class Escape {
    private static Log log = LogFactory.get(Escape.class);

    private int thisCanBeEscape = 0;
    public Escape(){
        new InnerClass();
    }
    private class InnerClass{
        public InnerClass(){
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }
    public static void main(String[] args){
        new Escape();
    }
}
