package io.gushizhao.design.behavioralmode.memento;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:10
 *
 *
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
