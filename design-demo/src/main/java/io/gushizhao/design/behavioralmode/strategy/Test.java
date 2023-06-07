package io.gushizhao.design.behavioralmode.strategy;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 11:07
 */
public class Test {
    public static void main(String[] args) {
        Context context = new Context(new StrategyImplA());
        context.doMethod();;

        context = new Context(new StrategyImplB());
        context.doMethod();;

        context = new Context(new StrategyImplC());
        context.doMethod();;
    }
    /**
     * 这是第一个实现
     * 这是第二个实现
     * 这是第三个实现
     */
}
