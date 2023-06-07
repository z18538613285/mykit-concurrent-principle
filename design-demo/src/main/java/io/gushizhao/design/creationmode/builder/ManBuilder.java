package io.gushizhao.design.creationmode.builder;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 9:30
 *
 * ConcreteBuilder
 *
 * Person对象的构造器
 */
public class ManBuilder implements PersonBuilder{
    Person person;

    public ManBuilder() {
        person = new Man();
    }
    @Override
    public void builderHead() {
        person.setHead("建造那人的头");
    }

    @Override
    public void builderBody() {
        person.setBody("建造男人的身体");
    }

    @Override
    public void builderFoot() {
        person.setFoot("建造男人的脚");
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
