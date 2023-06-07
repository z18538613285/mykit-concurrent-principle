package io.gushizhao.design.behavioralmode.mediator;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:05
 */
public class Test {
    public static void main(String[] args) {
        Mediator med = new ConcreateMediator();

        // 老板来了
        med.notice("boss");
        // 客户来了
        med.notice("client");
    }
}
