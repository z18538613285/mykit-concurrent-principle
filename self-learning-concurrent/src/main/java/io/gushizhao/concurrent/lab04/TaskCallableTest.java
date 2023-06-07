package io.gushizhao.concurrent.lab04;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/20 14:49
 *
 * @tips 定义一个回调接口，并在接口中定义接收任务结果数据的方法，具体逻辑在回调接口的实现类中完成。将回调接
 * 口与任务参数一同放进线程或线程池中运行，任务运行后调用接口方法，执行回调接口实现类中的逻辑来处理结果数据。
 */
public class TaskCallableTest {
    public static void main(String[] args) {
        TaskCallable<TaskResult> taskCallable = new TaskHandler();
        TaskExecutor taskExecutor = new TaskExecutor(taskCallable, "测试回调任务");
        new Thread(taskExecutor).start();
    }
}
