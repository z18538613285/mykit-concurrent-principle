package io.gushizhao.design.behavioralmode.interpreter;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 9:19
 */
public class Test {
    public static void main(String[] args) {
        Context ctx = new Context();
        ctx.add(new SimpleExpression());
        ctx.add(new AdvanceExpression());
        ctx.add(new SimpleExpression());
        for (Expression expression : ctx.getList()) {
            expression.interpret(ctx);
        }

    }
    /**
     * 这是普通解析器！
     * 这是高级解析器！
     * 这是普通解析器！
     */
}
