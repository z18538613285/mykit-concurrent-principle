package io.gushizhao.design.behavioralmode.command;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 16:52
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }
}
