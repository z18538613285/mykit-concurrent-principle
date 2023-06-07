package io.gushizhao.design.structuralmode.flyweight;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 14:57
 */
public class Test {
    public static void main(String[] args) {
        // Auto-generated method stub
        FlyWeight fly1 = FlyWeightFactory.getFlyWeight("a");
        fly1.action(1);
        FlyWeight fly2 = FlyWeightFactory.getFlyWeight("a");
        System.out.println(fly2 == fly1);

        FlyWeight fly3 = FlyWeightFactory.getFlyWeight("b");
        fly3.action(2);
        FlyWeight fly4 = FlyWeightFactory.getFlyWeight("c");
        fly4.action(3);
        FlyWeight fly5 = FlyWeightFactory.getFlyWeight("d");
        fly4.action(4);
        System.out.println(FlyWeightFactory.getSize());
    }
    /**
     * 参数值：1
     * true
     * 参数值：2
     * 参数值：3
     * 参数值：4
     * 4
     */
}
