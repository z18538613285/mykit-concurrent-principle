package io.gushizhao.design.behavioralmode.strategy;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 11:06
 *
 * context
 */
public class Context {

    Strategy strategy;
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doMethod() {
        strategy.method();
    }
}
