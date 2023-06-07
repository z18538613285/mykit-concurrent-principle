package io.gushizhao.design.creationmode.factorymethod;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 9:12
 *
 * ConcreteProduct
 *
 * Work接口的具体实现类
 */
public class StudentWork implements Work{
    @Override
    public void doWork() {
        System.out.println("学生做作业！");
    }
}
