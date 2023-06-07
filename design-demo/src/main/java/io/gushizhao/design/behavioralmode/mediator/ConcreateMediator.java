package io.gushizhao.design.behavioralmode.mediator;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:00
 */
public class ConcreateMediator extends Mediator{
    private ColleagueA ca;

    private ColleagueB cb;

    public ConcreateMediator() {
        ca = new ColleagueA();
        cb = new ColleagueB();
    }


    @Override
    public void notice(String content) {
        if (content.equals("boss")) {
            // 老板来了，通知员工 A
            ca.action();
        }
        if (content.equals("client")) {
            // 客户来了，通知前台 B
            cb.action();
        }

    }
}
