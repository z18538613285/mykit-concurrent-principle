package io.gushizhao.design.structuralmode.decorator;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 10:38
 *
 * ConcreteComponent
 *  Person接口的实现类Man
 *
 */
public class Man implements Person{
    @Override
    public void eat() {
        System.out.println("男人在吃！");
    }
}
