package io.gushizhao.design.creationmode.builder;

/**
 * Builder
 * Person对象的构造接口
 *
 */
public interface PersonBuilder {

    void builderHead();
    void builderBody();
    void builderFoot();
    Person buildPerson();
}
