package io.gushizhao.concurrent.lab09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/21 15:24
 */
public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) throws InterruptedException{
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试 ScheduledThreadPoolExecutor");
            }
        }, 1 , 1, TimeUnit.SECONDS);
        // 主线程休眠 10 秒
        Thread.sleep(10000);

        System.out.println("正在关闭线程池。。。");

        // 关闭线程池
        scheduledExecutorService.shutdown();
        boolean isClosed;
        do {
            isClosed = scheduledExecutorService.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("正在等待线程池中的任务执行完成");
        } while (!isClosed);

        System.out.println("所有线程执行结束，线程池关闭");
    }
}
