package io.gushizhao.design.structuralmode.facade;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 10:55
 *
 * Facade
 */
public class Facade {
    ServiceA sa;
    ServiceB sb;
    ServiceC sc;

    public Facade() {
        sa = new ServiceAImpl();
        sb = new ServiceBImpl();
        sc = new ServiceCImpl();
    }

    public void methodA() {
        sa.methodA();
        sb.methodB();
    }

    public void methodB() {
        sb.methodB();
        sc.methodC();
    }

    public void methodC() {
        sc.methodC();
        sa.methodA();
    }
}
