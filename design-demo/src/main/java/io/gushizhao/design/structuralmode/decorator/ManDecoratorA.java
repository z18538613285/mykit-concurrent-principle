package io.gushizhao.design.structuralmode.decorator;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 10:40
 *
 * ConcreteDecorator
 * Decorator的子类
 */
public class ManDecoratorA extends Decorator{

    @Override
    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }

    public void reEat() {
        System.out.println("再吃一顿饭！");
    }
}
