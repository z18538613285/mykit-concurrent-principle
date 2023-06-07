package io.gushizhao.design.behavioralmode.command;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 16:50
 *
 * Command
 */
public abstract class Command {
    protected Receiver receiver;


    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    public abstract void execute();
}
