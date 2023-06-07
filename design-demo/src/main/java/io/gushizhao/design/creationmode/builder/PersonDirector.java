package io.gushizhao.design.creationmode.builder;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 9:33
 *
 * Director
 * Person对象的整体构造器
 */
public class PersonDirector {
    public Person constructPerson(PersonBuilder pb) {
        pb.builderHead();
        pb.builderBody();
        pb.builderFoot();
        return pb.buildPerson();
    }
}
