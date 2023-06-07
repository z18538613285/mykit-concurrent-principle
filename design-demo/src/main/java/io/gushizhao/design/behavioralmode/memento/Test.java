package io.gushizhao.design.behavioralmode.memento;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:13
 */
public class Test {
    public static void main(String[] args) {
        Originator org = new Originator();
        org.setState("开会中");

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(org.createMemento()); // 将数据封装在 Caretaker 中

        org.setState("睡觉中");
        org.showState();

        org.setMemento(caretaker.getMemento()); // 将数据重新导入
        org.showState();

    }
    /**
     * 睡觉中
     * 开会中
     */
}
