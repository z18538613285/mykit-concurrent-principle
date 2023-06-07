package io.gushizhao.design.creationmode.abstractfactory;

/**
 * 抽象工厂
 * AbstractFactory:定义抽象工程类IAnimalFactory
 *
 */
public interface IAnimalFactory {

    /**
     * 定义创建 ICat 接口实例的方法
     * @return
     */
    ICat createCat();

    /**
     * 定义创建 IDog 接口实例的方法
     * @return
     */
    IDog createDog();

}
