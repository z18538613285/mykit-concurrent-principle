package io.gushizhao.design.structuralmode.facade;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 10:57
 */
public class Test {
    public static void main(String[] args) {
        ServiceA sa = new ServiceAImpl();
        ServiceB sb = new ServiceBImpl();
        sa.methodA();
        sb.methodB();
        System.out.println("=============");

        // facade
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
    }
    /**
     * 这是服务A
     * 这是服务B
     * =============
     * 这是服务A
     * 这是服务B
     * 这是服务B
     * 这是服务C
     */
}
