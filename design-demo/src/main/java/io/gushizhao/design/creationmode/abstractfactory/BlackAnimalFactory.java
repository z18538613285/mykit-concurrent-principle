package io.gushizhao.design.creationmode.abstractfactory;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/31 17:54
 *
 *
 * ConcreteFactory：创建抽象工厂类的两个实现类，WhiteAnimalFactory和BlackAnimalFactory
 *
 * IAnimalFactory抽象工厂的实现类
 */
public class BlackAnimalFactory implements IAnimalFactory{
    @Override
    public ICat createCat() {
        return new BlackCat();
    }

    @Override
    public IDog createDog() {
        return new BlackDog();
    }
}
