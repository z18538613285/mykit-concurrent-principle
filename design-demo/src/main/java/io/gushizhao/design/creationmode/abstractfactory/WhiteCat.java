package io.gushizhao.design.creationmode.abstractfactory;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/31 17:55
 *
 * ConcreteProduct：创建产品的实现类BlackCat、BlackDog、WhiteCat、WhiteDog
 * ICat接口的实现类
 */
public class WhiteCat implements ICat{
    @Override
    public void eat() {
        System.out.println("The white cat is eating!");
    }
}
