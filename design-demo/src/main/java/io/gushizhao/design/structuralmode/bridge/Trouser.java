package io.gushizhao.design.structuralmode.bridge;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 11:28
 *
 * ConcreteImplementor
 * 定义ConcreteImplementor类 Trouser
 */
public class Trouser extends Clothing{
    @Override
    public void personDressCloth(Person person) {
        System.out.println(person.getType() + "穿裤子");
    }
}
