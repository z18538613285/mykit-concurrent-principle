package io.gushizhao.design.structuralmode.adapter;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 10:04
 */
public class Test {
    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.adapteeMethod();
        target.adapterMethod();
    }

    /**
     * Adaptee method！
     * Adapter method!
     */
}
