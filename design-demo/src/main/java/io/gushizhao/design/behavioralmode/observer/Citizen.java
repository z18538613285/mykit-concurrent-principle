package io.gushizhao.design.behavioralmode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:22
 *
 * Subject
 */
public abstract class Citizen {
    List<Policeman> pols;

    String help = "normal";

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    abstract void sendMessage(String help);

    public void setPolicemen() {
        this.pols = new ArrayList();
    }
    public void register(Policeman pol) {
        this.pols.add(pol);
    }
    public void unRegister(Policeman pol) {
        this.pols.remove(pol);
    }
}
