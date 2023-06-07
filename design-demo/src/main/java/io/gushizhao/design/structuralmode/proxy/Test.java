package io.gushizhao.design.structuralmode.proxy;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 11:20
 */
public class Test {
    public static void main(String[] args) {
        Object obj = new ProxyObject();
        obj.action();
    }
    /**
     * 这是代理类
     * 代理开始
     * ================
     * ================
     * 这是被代理的类
     * ================
     * ================
     * 代理结束
     *
     * Process finished with exit code 0
     */
}
