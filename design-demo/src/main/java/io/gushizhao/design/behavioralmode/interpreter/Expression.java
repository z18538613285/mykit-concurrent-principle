package io.gushizhao.design.behavioralmode.interpreter;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 9:15
 *
 * AbstractExpression
 *
 */
public abstract class Expression {
    abstract void interpret(Context ctx);
}
