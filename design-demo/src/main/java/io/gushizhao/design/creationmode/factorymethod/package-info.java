package io.gushizhao.design.creationmode.factorymethod;

/**
 * 工厂方法
 * 一、概述
 * 定义一个用于创建对象的接口，让子类决定实例化哪一个类。FactoryMethod使一个类的实例化延迟到
 * 其子类。
 *
 * 二、适用性
 * 当一个类不知道它所必须创建的对象的类的时候。
 * 当一个类希望由它的子类来指定它所创建的对象的时候。
 * 当类将创建对象的职责委托给多个帮助子类中的某一个，并且你希望将哪一个帮助子类是代理者这
 * 一信息局部化的时候。
 *
 * 三、参与者
 * Product 定义工厂方法所创建的对象的接口。
 * ConcreteProduct 实现Product接口。
 * Creator 声明工厂方法，该方法返回一个Product类型的对象。 Creator也可以定义一个工厂方法
 * 的缺省实现，它返回一个缺省的ConcreteProduct对象。 可以调用工厂方法以创建一个Product对
 * 象。
 * ConcreteCreator 重定义工厂方法以返回一个ConcreteProduct实例。
 *
 *
 *
 *
 *
 */