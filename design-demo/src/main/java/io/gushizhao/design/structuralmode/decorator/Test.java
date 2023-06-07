package io.gushizhao.design.structuralmode.decorator;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 10:42
 */
public class Test {
    public static void main(String[] args) {
        Man man = new Man();
        ManDecoratorA md1 = new ManDecoratorA();
        ManDecoratorB md2 = new ManDecoratorB();

        md1.setPerson(man);
        md2.setPerson(md1);
        md2.eat();
    }

    /**
     * 男人在吃！
     * 再吃一顿饭！
     * ManDecoratorA类
     * ===============
     * ManDecoratorB类
     */
}
