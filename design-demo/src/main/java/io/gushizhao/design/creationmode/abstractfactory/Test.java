package io.gushizhao.design.creationmode.abstractfactory;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/31 18:00
 *
 * 定义一个测试类Test
 */
public class Test {

    public static void main(String[] args) {
        IAnimalFactory blackAnimalFactory = new BlackAnimalFactory();
        ICat blackCat = blackAnimalFactory.createCat();
        blackCat.eat();
        IDog blackDog = blackAnimalFactory.createDog();
        blackDog.eat();

        IAnimalFactory whiteAnimalFactory = new WhiteAnimalFactory();
        ICat whiteCat = whiteAnimalFactory.createCat();
        whiteCat.eat();
        IDog whiteDog = whiteAnimalFactory.createDog();
        whiteDog.eat();
    }
    /**
     * The black cat is eating!
     * The black dog is eating!
     * The white cat is eating!
     * The white dog is eating!
     */
}
