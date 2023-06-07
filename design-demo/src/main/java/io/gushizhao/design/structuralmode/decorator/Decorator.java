package io.gushizhao.design.structuralmode.decorator;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 10:39
 *
 * Decorator
 * Decorator抽象类实现Person接口
 *
 */
public abstract class Decorator implements Person{
    protected Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void eat() {
        person.eat();
    }
}
