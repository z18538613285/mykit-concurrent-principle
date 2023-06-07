package io.gushizhao.concurrent.lab04;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/20 14:43
 */
public class TaskHandler implements TaskCallable<TaskResult>{
    @Override
    public TaskResult callable(TaskResult taskResult) {
        // 拿到结果后进一步处理
        System.out.println(taskResult.toString());
        return taskResult;
    }
}
