package io.gushizhao.design.behavioralmode.command;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 16:53
 */
public class Test {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new CommandImpl(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.execute();

    }
    /**
     * This is Receive class!
     */
}
