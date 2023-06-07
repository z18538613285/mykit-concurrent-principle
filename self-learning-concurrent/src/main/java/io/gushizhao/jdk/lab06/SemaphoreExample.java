package io.gushizhao.jdk.lab06;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 16:59
 *
 * Semaphore
 * 控制同一时间并发线程的数目。能够完成对于信号量的控制，可以控制某个资源可被同时访问的个数。
 * 提供了两个核心方法——acquire()方法和release()方法。acquire()方法表示获取一个许可，如果没有则等待，release()方法则是
 * 在操作完成后释放对应的许可。Semaphore维护了当前访问的个数，通过提供同步机制来控制同时访问的个数。Semaphore可
 * 以实现有限大小的链表。
 * 使用场景
 * Semaphore常用于仅能提供有限访问的资源，比如：数据库连接数。
 *
 *
 */
public class SemaphoreExample {
    private static final int threadCount = 200;
    private static Log log = LogFactory.get(SemaphoreExample.class);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire(); //获取一个许可
                    test(threadNum);
                    semaphore.release(); //释放一个许可

                    //semaphore.acquire(3); //获取多个许可
                    //test(threadNum);
                    //semaphore.release(3); //释放多个许可

                    // 假设有这样一个场景，并发太高了，即使使用Semaphore进行控制，处理起来也比较棘手。假设系统当前允许的最高并发数是
                    //3，超过3后就需要丢弃，使用Semaphore也能实现这样的场景，

                    // 尝试获取一个许可，也可以尝试获取多个许可
                    // 支持尝试获取许可超时设置，超时后不在等待后续线程的操作
                    // 具体可以参见 Semaphore 的源码
                    if (semaphore.tryAcquire()) {
                        test(threadNum);
                        semaphore.release(); // 释放一个许可
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
    }
    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
