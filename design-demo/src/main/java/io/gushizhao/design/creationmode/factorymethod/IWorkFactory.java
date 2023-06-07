package io.gushizhao.design.creationmode.factorymethod;

/**
 * Creator
 * 抽象工厂接口
 *
 */
public interface IWorkFactory {

    /**
     * 定义获取Work实例对象的方法
     * @return
     */
    Work getWork();
}
