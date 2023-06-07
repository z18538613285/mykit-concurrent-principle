package io.gushizhao.concurrent.executor.state;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/20 9:49
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new WaitingTime(), "waitTimeThread").start();
        new Thread(new WaitingState(), "waitStateThread").start();


        // BlockedThread-01线程会抢到锁，BlockedThread-02线程会阻塞
        new Thread(new BlockThread(), "BlockedThread-01").start();
        new Thread(new BlockThread(), "BlockedThread-02").start();

        // 使用jps结合jstack命令可以分析线上生产环境的Java进程的异常信息。
    }
}
