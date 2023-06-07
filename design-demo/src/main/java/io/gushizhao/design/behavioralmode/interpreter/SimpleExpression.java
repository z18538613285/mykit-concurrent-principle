package io.gushizhao.design.behavioralmode.interpreter;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 9:16
 *
 * Expression
 */
public class SimpleExpression extends Expression{
    @Override
    void interpret(Context ctx) {
        System.out.println("这是普通解析器！");
    }
}
