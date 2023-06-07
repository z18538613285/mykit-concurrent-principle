package io.gushizhao.design.behavioralmode.command;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 16:51
 *
 * ConcreteCommand
 */
public class CommandImpl extends Command{
    public CommandImpl(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        receiver.receive();
    }
}
