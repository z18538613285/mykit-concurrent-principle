package io.gushizhao.design.structuralmode.decorator;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 10:40
 *
 * ConcreteDecorator
 * Decorator的子类
 */
public class ManDecoratorB extends Decorator{

    @Override
    public void eat() {
        super.eat();
        System.out.println("===============");
        System.out.println("ManDecoratorB类");
    }

}
