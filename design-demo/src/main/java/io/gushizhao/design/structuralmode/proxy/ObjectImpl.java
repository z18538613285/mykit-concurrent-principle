package io.gushizhao.design.structuralmode.proxy;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 11:17
 *
 * realSubject
 */
public class ObjectImpl implements Object{
    @Override
    public void action() {
        System.out.println("================");
        System.out.println("================");
        System.out.println("这是被代理的类");
        System.out.println("================");
        System.out.println("================");
    }
}
