package io.gushizhao.concurrent.executor.state;

import java.util.concurrent.TimeUnit;

/**
 * @Author huzhichao
 * @Description 线程不断休眠
 * @Date 2023/3/20 9:30
 */
public class WaitingTime implements Runnable{
    @Override
    public void run() {

        // 验证线程的TIMED_WAITING状态
        while (true) {
            waitSecond(200);
        }
    }

    // 线程等待多少秒
    public static final void waitSecond(long  seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
