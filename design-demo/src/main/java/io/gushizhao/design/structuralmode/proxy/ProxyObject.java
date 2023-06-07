package io.gushizhao.design.structuralmode.proxy;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 11:16
 *
 * proxy
 */
public class ProxyObject implements Object{

    Object obj;

    public ProxyObject() {
        System.out.println("这是代理类");
        obj = new ObjectImpl();
    }

    @Override
    public void action() {
        System.out.println("代理开始");
        obj.action();
        System.out.println("代理结束");
    }
}
