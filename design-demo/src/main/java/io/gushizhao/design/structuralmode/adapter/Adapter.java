package io.gushizhao.design.structuralmode.adapter;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 10:01
 *
 * Target 的实现类
 */
public class Adapter implements Target{
    private Adaptee adaptee;
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    @Override
    public void adapteeMethod() {
        adaptee.adapteeMethod();
    }

    @Override
    public void adapterMethod() {
        System.out.println("Adapter method!");
    }
}
