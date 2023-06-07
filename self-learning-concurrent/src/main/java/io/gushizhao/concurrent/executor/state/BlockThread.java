package io.gushizhao.concurrent.executor.state;

/**
 * @Author huzhichao
 * @Description 加锁后不在释放锁
 * @Date 2023/3/20 9:30
 */
public class BlockThread implements Runnable{
    @Override
    public void run() {

        //。当启动两个BlockedThread线程时，首先启动的线程会处于 TIMED_WAITING 状态，后启动的线程会处于 BLOCKED 状态。
        synchronized (BlockThread.class) {
            while (true) {
                WaitingTime.waitSecond(100);
            }
        }

    }

}
