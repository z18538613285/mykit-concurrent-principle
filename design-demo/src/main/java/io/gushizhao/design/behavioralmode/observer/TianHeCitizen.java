package io.gushizhao.design.behavioralmode.observer;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:30
 *
 * ConcreteSubject
 */
public class TianHeCitizen extends Citizen{

    public TianHeCitizen(Policeman pol) {
        setPolicemen();
        register(pol);
    }
    @Override
    void sendMessage(String help) {
        setHelp(help);
        for (int i = 0; i < pols.size(); i++) {
            Policeman policeman = pols.get(i);
            // 通知警察行动
            policeman.action(this);
        }
    }
}
