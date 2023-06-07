package io.gushizhao.design.behavioralmode.observer;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:34
 *
 * ConcreteObserver
 */
public class HunagPuPoliceman implements Policeman{
    @Override
    public void action(Citizen citizen) {
        String help = citizen.getHelp();
        if (help.equals("normal")) {
            System.out.println("一切正常，不用出动！");
        }
        if (help.equals("unnormal")) {
            System.out.println("有犯罪行动，黄埔警察出动！");
        }

    }
}
