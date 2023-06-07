package io.gushizhao.concurrent.lab04;

import java.io.Serializable;

/**
 * @Author huzhichao
 * @Description 任务执行结果
 * @Date 2023/3/20 14:40
 *
 * 创建回调接口的实现类
 */
public class TaskResult implements Serializable {

    public static final long serialVersionUUID = 8678277072402730062L;
    // 任务状态
    private Integer taskStatus;
    // 任务消息
    private String taskMessage;
    // 任务结果数据
    private String taskResult;


    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskMessage() {
        return taskMessage;
    }

    public void setTaskMessage(String taskMessage) {
        this.taskMessage = taskMessage;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "taskStatus=" + taskStatus +
                ", taskMessage='" + taskMessage + '\'' +
                ", taskResult='" + taskResult + '\'' +
                '}';
    }
}
