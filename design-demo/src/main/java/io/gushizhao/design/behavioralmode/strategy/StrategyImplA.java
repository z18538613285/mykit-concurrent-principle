package io.gushizhao.design.behavioralmode.strategy;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 11:04
 *
 * ConcreateStrategy
 */
public class StrategyImplA extends Strategy {
    @Override
    public void method() {
        System.out.println("这是第一个实现");
    }
}
