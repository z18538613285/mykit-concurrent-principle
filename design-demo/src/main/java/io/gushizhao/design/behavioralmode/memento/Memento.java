package io.gushizhao.design.behavioralmode.memento;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:09
 *
 *
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
