package io.gushizhao.concurrent.executor.state;

import com.sun.org.apache.xpath.internal.axes.WalkingIterator;

import java.util.concurrent.TimeUnit;

/**
 * @Author huzhichao
 * @Description 线程加锁
 * @Date 2023/3/20 9:30
 */
public class WaitingState implements Runnable{
    @Override
    public void run() {

        // 验证线程的 WAITING 状态
        while (true) {
            synchronized (WaitingState.class) {
                try {
                    WaitingState.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
