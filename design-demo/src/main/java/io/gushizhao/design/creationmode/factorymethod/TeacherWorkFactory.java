package io.gushizhao.design.creationmode.factorymethod;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 9:15
 *
 * ConcreteCreator
 * IWorkFactory工厂的实现类
 *
 */
public class TeacherWorkFactory implements IWorkFactory{
    @Override
    public Work getWork() {
        return new TeacherWork();
    }
}
